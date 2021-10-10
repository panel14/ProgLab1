package com.company.classes;

import com.company.enums.Actions;

import java.util.HashMap;
import java.util.Map;

public abstract class Creature {
    private String name;
    private int age;
    protected int fun = 50;
    public String specialPhrase = "Change my specialPhrase, please";

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = (name != "") ? name : "Uncorrected name";
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = (age > 0) ? age : 18;
    }

    public Creature(){
        this.name = "Unknown";
        this.age = 0;
    }

    public Creature(String name, int age){
        this.name = name;
        this.age = age;
    }

    protected void controlStats(int statChange) {
        fun += statChange;
        System.out.printf("%s's fun changed by %d!\n", name, statChange);
        if (fun < 10)
            System.out.printf("Oh, gotta cheer %s! Its fun = %d\n", name, fun);
        else if (fun > 100)
            System.out.printf("WOW! %s fun is over 100! AbSOlutLy aMazInG!1!\n", name);
    }

    protected abstract void reactOnInter(int funChange, People slave);

    protected abstract void reactOnInter(int funChange, Freak slave);

    public void speak(String phrase) {
        System.out.printf("%s: %s\n", this.getName(), phrase);
    }

    public abstract void doSome(Actions action);

    public abstract void interactWith(People human);

    public abstract void interactWith(Freak freak);

}
