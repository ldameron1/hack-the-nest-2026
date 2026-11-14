#!/usr/bin/env bash
# Run this from your own WSL terminal, outside the agent's sandbox.
# Optional first argument: another config path (for testing).
set -euo pipefail

python3 - "$@" <<'PY'
import datetime
import os
from pathlib import Path
import re
import shutil
import stat
import sys
import tempfile
import tomllib

if len(sys.argv) > 2:
    sys.exit('Usage: bash enable-codex-network.sh [config-path]')

codex_dir = Path(os.environ.get('CODEX_HOME', str(Path.home() / '.codex')))
path = Path(sys.argv[1]).expanduser() if len(sys.argv) == 2 else codex_dir / 'config.toml'
path = path.resolve()
text = path.read_text() if path.exists() else ''
try:
    config = tomllib.loads(text)
except tomllib.TOMLDecodeError:
    sys.exit('Config contains invalid TOML. No changes made.')

if 'default_permissions' in config or 'permissions' in config:
    sys.exit('This config uses permission profiles. No changes made: enable networking in the selected profile instead.')

if config.get('sandbox_mode', 'workspace-write') != 'workspace-write':
    sys.exit('This config selects another sandbox mode. No changes made.')

if config.get('sandbox_workspace_write', {}).get('network_access') is True:
    print('Command network access is already enabled in this config.')
    print('Restart the task. Managed policy can still restrict networking.')
    sys.exit(0)

lines = text.splitlines(keepends=True)
section = re.compile(r'^\s*\[\s*sandbox_workspace_write\s*\]\s*(?:#.*)?$')
start = next((i for i, line in enumerate(lines) if section.match(line.rstrip('\r\n'))), None)
if start is None:
    if 'sandbox_workspace_write' in config:
        sys.exit('Nonstandard sandbox config format. No changes made.')
    updated = text.rstrip('\r\n') + '\n\n[sandbox_workspace_write]\nnetwork_access = true\n'
else:
    end = next((i for i in range(start + 1, len(lines)) if lines[i].lstrip().startswith('[')), len(lines))
    field = re.compile(r'^(\s*network_access\s*=\s*)(true|false)(\s*(?:#.*)?)$')
    found = False
    for i in range(start + 1, end):
        match = field.match(lines[i].rstrip('\r\n'))
        if match:
            lines[i] = match.group(1) + 'true' + match.group(3) + '\n'
            found = True
            break
    if not found:
        lines.insert(start + 1, 'network_access = true\n')
    updated = ''.join(lines)

try:
    parsed = tomllib.loads(updated)
except tomllib.TOMLDecodeError:
    sys.exit('Could not safely update this config format. No changes made.')
if parsed.get('sandbox_workspace_write', {}).get('network_access') is not True:
    sys.exit('Could not enable network access. No changes made.')

path.parent.mkdir(parents=True, exist_ok=True)
mode = stat.S_IMODE(path.stat().st_mode) if path.exists() else 0o600
if path.exists():
    stamp = datetime.datetime.now().strftime('%Y%m%d-%H%M%S-%f')
    backup = path.with_name(path.name + '.backup-' + stamp)
    shutil.copy2(path, backup)
    os.chmod(backup, 0o600)
    print(f'Backup: {backup}')

fd, temporary = tempfile.mkstemp(prefix='.config-', dir=path.parent)
try:
    with os.fdopen(fd, 'w') as output:
        output.write(updated)
    os.chmod(temporary, mode)
    os.replace(temporary, path)
finally:
    if os.path.exists(temporary):
        os.unlink(temporary)

print(f'Enabled command network access in {path}')
print('Restart the Codex task before retrying the Exa installer.')
print('This does not override managed policies or change WSL DNS.')
PY
