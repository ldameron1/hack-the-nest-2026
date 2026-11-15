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
        
        //String name, int hp, int healsLeft)
        Fighter player = new Fighter("player", 25, 4);
        Fighter enemy = new Fighter("enemy", 21, 4);
        while (player.hp > 0 && enemy.hp > 0) {
            System.out.println("Select Option: 1. attack 2. heal");
            int choice = input.nextInt();

            if (choice == 1)
              {
                //remmebe rthat this is 0 indexed.
                int hpAmount = randomNum.nextInt(7);
                enemy.hp = (enemy.hp - hpAmount);
                System.out.println("You decided to hit the other guy, -" + hpAmount + "hp.");
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
                int choice2 = randomNum.nextInt(2);
                if (choice2 == 1) 
                {
                    int hpAmount = randomNum.nextInt(7);
                    player.hp = (player.hp - hpAmount);
                    System.out.println("enemy decided to hit the you, -" + hpAmount + "hp.");
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
        }
    }

}

