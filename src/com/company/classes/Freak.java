package com.company.classes;

import com.company.enums.Actions;
import com.company.interfaces.Flying;
import com.company.interfaces.Log;

public class Freak extends Creature implements Flying, Log {

    public Freak(){
        super();
    }

    public Freak(String name, int age){
        super(name, age);
    }

    @Override
    public void doSome(Actions action) {
        switch (action){
            case ROFL -> speak("If bears were bees, they would never think of " +
                    "building their house at that height.");
            case NOTHING -> System.out.printf("%s do nothing. Sad but interesting\n", this.getName());
            case CLEAN -> speak("No, cleaning is not my deal");
            case SPEAK -> this.speak(specialPhrase);
            case FOR_SLAVA -> {
                if ((this.getName().equals("SLAVA")))
                    speak("What I did?");
                else
                    speak("Who is SLAVA?");
            }
        }
    }

    @Override
    public void interactWith(People human) {
        speak("Hello, "+ human.getName() +"! Come on, I'll show you me house!");
        human.controlStats(15);
        human.reactOnInter(15,this);
    }

    @Override
    protected void reactOnInter(int funChange, People slave) {
        String sName = slave.getName();
        speak("Don't worry, " + sName + ". Let's do something precolnoe");
        slave.controlStats(10);
        if (sName.contains("Malish")) {
            speak("Finally Malish. We met again on this roof.");
            controlStats(100);
        }
        else
            speak("Oh, I got you mixed up. But we'll have some fun anyway!");
    }

    @Override
    public void interactWith(Freak freak) {
        speak("Its can be only one, " + freak.getName());
        freak.controlStats(-10);
        freak.reactOnInter(-10,this);
    }

    @Override
    protected void reactOnInter(int funChange, Freak slave) {
        speak("I don't want a war with you, " + slave.getName() + "Better become a friends.");
        System.out.printf("They weren't laughing, but deep down they knew they would be best friends." +
                "Forever.");
    }

    @Override
    public void showInLog() {
        System.out.printf("Freak: %s\nAge: %d\nFun: %d\n", this.getName(), this.getAge(), this.fun);
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        result += 31 * getAge();
        return result;
    }

    @Override
    public void fly() {
        System.out.printf("%s is flying! Awesome!");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Freak freak = (Freak) obj;
        if (getName() != freak.getName())
            return false;
        if (getAge() != freak.getAge())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "package.com.classes.Freak["+
                "this.name = " + this.getName() +
                "; this.age = " + this.getAge() + "]";
    }
}
