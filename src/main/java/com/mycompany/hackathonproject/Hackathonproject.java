/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hackathonproject;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ld
 */
public class Hackathonproject {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int playerHP = 20;
        int enemyHP = 20;
        Random randomNum = new Random();

    while (playerHP > 0 && enemyHP > 0)
    {
        
           if (playerHP <= 0)
    {
        System.out.println("You lost, sorry.");
        //return;
    }
    else
    {
    System.out.println("You won, sorry... actually not sorry, good job champ");
    return;
    }
}
    
        System.out.println("Select Option: 1. attack 2. heal");
        int choice = input.nextInt(2);
        if (choice == 1)
        {
          
            //remmebe rthat this is 0 indexed.
              int hpAmount = randomNum.nextInt(7);
            enemyHP = (enemyHP - hpAmount);
            //remmebe rthat this is 0 indexed.
             System.out.println("You decided to hit the other guy," + hpAmount + "hp.");
            
        }
        else
        {
            System.out.println("You decided to heal the you guy, + 5 hp.");
            playerHP = (playerHP + 5);
        }
        //mnow enemy turn
        int choice2 = randomNum.nextInt(2);
         if (choice2 == 1)
        {
            int hpAmount = randomNum.nextInt(7);
            playerHP = (playerHP - hpAmount);
            //remmebe rthat this is 0 indexed.
             System.out.println("enemy decided to hit the you, -" + hpAmount + "hp.");
            
        }
        else
        {
            System.out.println("Enemy decided to heal the enemy, + 5 hp.");
            enemyHP = (enemyHP + 5);
        }
        //enemyCall(); //out here so it operates after
        
        
        
        System.out.println(playerHP + " <- you | the other guy -> " + enemyHP);
    }
 
    /*
    public static int enemyCall()
    {
       Random enemyAction = new Random();
       int action = enemyAction.nextInt(2); // so 0, 1
       if (action == 0)
       {
           return enemyAction.nextInt(2);
       }
       else
       {
           
       }
*/
    }
    
    





