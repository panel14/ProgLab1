package com.company.classes;

import com.company.Main;
import com.company.enums.Actions;
import com.company.interfaces.Flying;
import com.company.interfaces.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Freak extends Creature implements Flying, Log {

    public Freak(){
        super();
    }

    public Freak(String name, int age){
        super(name, age);
    }

    public class FreakToUser {
        static private Map<String, String> dialogDict = new HashMap<String, String>();
        private static String userName = "";
        private String answer = "";
        private Scanner in = new Scanner(System.in);

        public FreakToUser(){
            dialogDict.put("normal", "Oh, what's great!\n");
            dialogDict.put("bad", "That's really sad, but don't worry! Be happy!\n");
            dialogDict.put("great", "Glad to heard it!!!\n");
        }

        public void pickUp() {
            if (userName.equals("")){
                System.out.printf("%s: Hi, man or girl! What's your name?\n", getName());
                userName = in.nextLine();
            }
            System.out.printf("%s: Nice to meet you, %s!\n", getName(), userName);
        }

        public void tellAJoke() throws TerribleJokeException{
            pickUp();

            System.out.printf("%s: Tell me some funny, %s!\n", getName(), userName);
            String joke = in.nextLine();
            if (joke.length() < 25)
                throw new TerribleJokeException("Terrible joke detected");
            else{
                System.out.printf("%s: Hahahaha! You're soo funny %s!\n", getName(), userName);
                controlStats(5);
            }
        }

        public void speakWithUser() {
            boolean isAnsExist = false;
            String unknownAns = "";

            pickUp();

            System.out.printf("%s: So, %s, how are you?\n", getName(), userName);
            answer = in.nextLine();

            String[] keys = dialogDict.keySet().toArray(new String[0]);

            for (int i = 0; i < keys.length; i++){
                if (answer.contains(keys[i])){
                    System.out.printf("%s: %s", getName(), dialogDict.get(keys[i]));
                    isAnsExist = true;
                    break;
                }
                unknownAns = answer;
            }
            if (!isAnsExist){
                System.out.printf("%s: So, I don't now that I should to answer... Could you prompt?\n", getName());
                answer = in.nextLine();
                dialogDict.put(unknownAns, answer + "\n");
                System.out.printf("%s: Thank you, %s! I get it now.\n", getName(), userName);
                isAnsExist = true;
            }

            System.out.printf("%s: Thank you, %s, for dialog, but I need to go. Bye!\n", getName(), userName);
            fly();
        }
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
    public void setSpecialPhrase(String phrase) {
        if (phrase.contains("Hello"))
            throw new SpecialPhraseException("Phrase is too common");
        else if (getName().equals("Batman") && !(phrase.contains("GDE DETONATOR")))
            throw new SpecialPhraseException("This freak must say \"GDE DETONATOR\"");
        else
            this.specialPhrase = phrase;
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
        System.out.printf("%s is flying! Awesome!\n", getName());
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
