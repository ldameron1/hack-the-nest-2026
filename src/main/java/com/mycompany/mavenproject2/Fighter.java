/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

import java.util.Random;

/**
 *
 * @author ld
 */
public class Fighter 
{
    String name; //later we can use scanner for name. if of user.
    int hp;
    int healsLeft; //i guess we want these to be inherent to each fighter
   //as a litte twist on the nomral formula.
    //int whoOwns;
    Fighter(String name, int hp, int healsLeft, int maxHp, int attackPower)
    {
        this.name = name;
        this.hp = hp;
        this.healsLeft = healsLeft;
        this.maxHp = maxHp;
        this.attackPower = attackPower;
    }
    public boolean heal()
    {
      
     if (this.maxHp > hp)
     {
        if (this.healsLeft > 0)
        {
            this.hp=(this.hp + 5);
            this.healsLeft--;
            return true;
        }
        else
        {
            return false;
        }
     }
     else
     {
        System.out.println("Can't exceed max hp.");
        return false;
    }
    }
    public int attack(Fighter target)
    {
        //this will be modifiable and extensible later to specify different attack
        //types, etc.
        //remmebe rthat this is 0 indexed.
        Random randomNum = new Random();
        int damage = randomNum.nextInt(this.attackPower);
        return damage;
    }
        //2 = the user, 1 = a protected enemy, 0 = capturab;e/recuritable enemy
       //never mind dont ened
       /* 
        if (this.whoOwns == 2)
        {
            
        }
      */
}

