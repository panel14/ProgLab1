package com.company.classes;

import com.company.enums.Actions;
import com.company.interfaces.Log;

import java.util.Random;
import java.util.Scanner;

public class People extends Creature implements Log {
    public boolean isCleaner = false;

    public People(){
        class MakeByUser{
            private Scanner in = new Scanner(System.in);

            public String createName(){
                System.out.println("Unknown : Hello, Creator. What's my name?");
                return in.nextLine();
            }

            public int createAge(String name){
                System.out.printf("%s : What's my age, Creator?\n",name);
                return in.nextInt();
            }
        }

        MakeByUser god = new MakeByUser();
        setName(god.createName());
        setAge(god.createAge(this.getName()));
        System.out.printf("%s: I'm not a cleaner I deserve.\n", getName());
        isCleaner = false;
        System.out.printf("%s is created!\n", getName());
    }

    public People(String name, int age){
        super(name, age);
    }

    public People(String name, int age, boolean isCleaner){
        super(name, age);
        this.isCleaner = isCleaner;
    }

    @Override
    public void doSome(Actions action) {
        switch (action){
            case ROFL -> {
                if (fun > 75)
                    speak("Do you think I will not outplay you? I won't destroy you? " +
                        "I will destroy you.");
                else
                    speak("I should joke, but I'm in a bad mood.");
            }
            case NOTHING -> System.out.printf("%s do nothing. Sad\n", this.getName());
            case CLEAN -> {
                if (isCleaner)
                    speak("So, I should clean this roof");
                else
                    speak("So, there is a lot of snow on this roof. Of course I'll not remove this.");
            }
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
        speak("Hi, " +human.getName()+"! Nice to meet you on this roof!");
        Random random = new Random();
        int funChange = random.nextInt(20);
        human.controlStats(funChange);
        human.reactOnInter(funChange, this);
    }

    @Override
    protected void reactOnInter(int funChange, People slave) {
        if (funChange >= 10 && funChange <= 20){
            speak("Haven't see you for a long time, " + slave.getName()+ "! Be careful there.");
            funChange += 5;
        }
        else{
            speak("Hi, " + slave.getName() + "... I feel sad a little bit");
            funChange -= 10;
        }
        slave.controlStats(funChange);
    }

    @Override
    public void interactWith(Freak freak) {
        speak("Wow! I never seen anything like it! My name is " + this.getName() +
                ". Let's get acquainted, " + freak.getName()+"!");
        this.controlStats(10);
        freak.controlStats(10);
        freak.reactOnInter(10,this);
    }

    @Override
    protected void reactOnInter(int funChange, Freak slave) {
        speak("Of course, "+ slave.getName() + "! I'll be happy to see it!.");
        funChange += 5;
        slave.controlStats(funChange);
    }

    @Override
    public void showInLog() {
        System.out.printf("Human: %s\nAge: %d\nFun: %d\n", this.getName(), this.getAge(), this.fun);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        People people = (People) obj;
        if (getName() != people.getName())
            return false;
        if (getAge() != people.getAge())
            return false;
        if (isCleaner != people.isCleaner)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = getName() == null ? 0 : getName().hashCode();
        int clean = isCleaner ? 20 : 10;
        result += 31 * getAge();
        result += 31 * clean;
        return result;
    }

    @Override
    public String toString() {
        return "package.com.classes.People["+
                "this.name = " + this.getName() +
                "; this.age = " + this.getAge() +
                "; this.isCleaner = " + isCleaner + "]";
    }
}
