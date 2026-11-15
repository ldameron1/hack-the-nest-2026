/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author ld
 */
public class Mavenproject2 
{

   public static void main(String[] args) 
   {
        Scanner input = new Scanner(System.in);
         Random randomNum = new Random();
        
         System.out.println("Select Option (type, then hit enter): 1. Tom The Bear: 200/200 HP, 0 heals, 1 Attack Power, 2. Jason the Squirrel: 30/30 HP, 2 heals, 5 Attack Power 3. Sam the Snail: 10/50 HP, 10 heals, 4 Attack Power ");
            int choice = input.nextInt();
          
         

        //String name, int hp, int healsLeft)
        Fighter player;
          switch (choice)
          {
                case 1:
                    //tom the bear
                    player = new Fighter("player", 200, 0, 200, 1 );
                    break;
                case 2:
                    //jason the squirrel
                    player = new Fighter("player", 30, 2, 30, 3 );
                    break;
                case 3:
                    //sam the snail
                    break;
                default:
                    System.out.println("Valid option not received, selecting ");
                    player = new Fighter("player", 200, 0, 200, 1 );
          }             
                                               
                            
        Fighter enemy = new Fighter("enemy", 21, 4, 25, 25 );
        
 
        
        while (player.hp > 0 && enemy.hp > 0) 
        {              
                
 
                while (player.hp > 0 && enemy.hp > 0) {
                System.out.println("Select Option (type, then hit enter): 1. attack 2. heal");
                choice = input.nextInt();
                
                if (choice == 1)
                {
                int damage = player.attack(enemy);
                enemy.hp = enemy.hp - damage;
                System.out.println(damage + " points inflicted. The enemy's new hp after the attack is " + enemy.hp);
        }
        else 
        {
        
        if (player.healsLeft == 0) 
        {
        System.out.println("The player is out of healing, failed to heal.");
        }
        else
        {
        player.heal();
        System.out.println("The player has healed");
        }
        }
        
        //mnow enemy turn (only if still alive)
        if (enemy.hp > 0)
        {
        //Random randomNum = new Random();
        int choice2 = randomNum.nextInt(2);
        if (choice2 == 1)
        {
        int damage = enemy.attack(player);
        player.hp = player.hp - damage;
        System.out.println(damage + " points inflicted. The player's new hp after the attack is " + player.hp);
        }
        
        else
        {
        
        if (enemy.healsLeft == 0)
        {
        System.out.println("The enemy is out of healing, failed to heal.");
        }
        else
        {
        enemy.heal();
        System.out.println("The enemy has healed");
        }
        }
        }
        
        System.out.println(player.hp + " <- you | the other guy -> " + enemy.hp);
        }
        
        if (player.hp <= 0)
        {
        System.out.println("You lost, sorry.");
        }
        else
        {
        System.out.println("You won, sorry... actually not sorry, good job champ");
        }  }
    }

}

