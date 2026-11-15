/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author ld
 */
public class Fighter {
    String name; //later we can use scanner for name. if of user.
    int hp;
    int healsLeft; //i guess we want these to be inherent to each fighter
   //as a litte twist on the nomral formula.
    //int whoOwns;
    Fighter(String name, int hp, int healsLeft)
    {
        this.name = name;
        this.hp = hp;
        this.healsLeft = healsLeft;
    }
    public boolean heal()
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
        //2 = the user, 1 = a protected enemy, 0 = capturab;e/recuritable enemy
       //never mind dont ened
       /* 
        if (this.whoOwns == 2)
        {
            
        }
      */
    }
}
