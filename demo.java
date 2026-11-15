package com.mycompany.hackathonproject;
import java.util.Random;
import java.util.Scanner;

class Demo {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int playerHP = 20;
        int enemyHP = 20;
        Random randomNum = new Random();

        while (playerHP > 0 && enemyHP > 0) {
            System.out.println("Select Option: 1. attack 2. heal");
            int choice = input.nextInt();

            if (choice == 1) {
                //remmebe rthat this is 0 indexed.
                int hpAmount = randomNum.nextInt(7);
                enemyHP = (enemyHP - hpAmount);
                System.out.println("You decided to hit the other guy," + hpAmount + "hp.");
            } else {
                System.out.println("You decided to heal the you guy, + 5 hp.");
                playerHP = (playerHP + 5);
            }

            //mnow enemy turn (only if still alive)
            if (enemyHP > 0) {
                int choice2 = randomNum.nextInt(2);
                if (choice2 == 1) {
                    int hpAmount = randomNum.nextInt(7);
                    playerHP = (playerHP - hpAmount);
                    System.out.println("enemy decided to hit the you, -" + hpAmount + "hp.");
                } else {
                    System.out.println("Enemy decided to heal the enemy, + 5 hp.");
                    enemyHP = (enemyHP + 5);
                }
            }

            System.out.println(playerHP + " <- you | the other guy -> " + enemyHP);
        }

        if (playerHP <= 0) {
            System.out.println("You lost, sorry.");
        } else {
            System.out.println("You won, sorry... actually not sorry, good job champ");
        }
    }
}
