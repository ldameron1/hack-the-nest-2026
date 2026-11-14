#!/usr/bin/env bash
set -euo pipefail

project_root="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/.." && pwd)"
if [[ -z "${EXA_API_KEY:-}" && -f "$project_root/.env" ]]; then
    # Load the locally owned environment file; never print its contents.
    source "$project_root/.env"
    export EXA_API_KEY
fi

exec java "$project_root/src/ExaSearch.java" "$@"
