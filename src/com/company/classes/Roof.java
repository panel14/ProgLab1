package com.company.classes;

import com.company.enums.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Roof {

    private static int roofCount = 0;
    private static String location = "Silicon Village";

    public Roof(){
        roofCount++;
        RoofManager.getRoofManager().putRoof(this);
    }

    public static class RoofManager{
        private static RoofManager roofManager;
        private static ArrayList<Roof> roofs;

        private RoofManager(){
            roofs = new ArrayList<Roof>();
        };

        public void putRoof(Roof roof){
            roofs.add(roof);
        }

        public ArrayList<Roof> getRoofsList(){
            return roofs;
        }

        public static RoofManager getRoofManager(){
            if (roofManager == null)
                roofManager = new RoofManager();
            return roofManager;
        }

        public int getRoofCount(){
            return Roof.roofCount;
        }

        public void getLocation(){
            System.out.printf("Manager: We at %s now.\n", Roof.location);
        }

        public void setLocation(String location){
            Roof.location = location;
            System.out.printf("Manager: Now we're moving to %s.\n", location);
        }
    }

    private ArrayList<People> people = new ArrayList<People>();
    private ArrayList<Freak> freaks = new ArrayList<Freak>();

    public void put(People human){
        people.add(human);
    }

    public int getPeopleSize(){
        return people.size();
    }

    public void put(Freak freak){
        freaks.add(freak);
    }

    public int getFreaksSize(){
        return freaks.size();
    }

    public void startAction(){
        System.out.println("Roof");
        printNewLine();
        printPrologue();
        int total = people.size() + freaks.size();
        if (total > 1){
            int[] curCr = getInteraction();
            getDoSome(curCr);
            printEnd();
        }
        else
            System.out.println("Not enough creatures on the roof.");
    }

    private void printPrologue(){
        System.out.println("We found ourselves on one of the rooftops of a very ordinary house.\n" +
                "But look - there are so many people here and ... not only. Let's see what happens.");
        printNewLine();
    }

    private void printNewLine(){
        System.out.println("--------------//---------------");
    }

    private void printEnd(){
        System.out.println("This is what happens on the roof!");
        printNewLine();
    }

    private int[] getInteraction(){
        int totalP = people.size();
        int totalF = freaks.size();
        Random random = new Random();

        int curP = -1;
        int curF = -1;
        if (totalP > 0 && totalF >0){
            curP = random.nextInt(totalP);
            curF = random.nextInt(totalF);
            freaks.get(curF).interactWith(people.get(curP));
            curP = random.nextInt(totalP);
            curF = random.nextInt(totalF);
            people.get(curP).interactWith(freaks.get(curF));
        }
        else if (totalP > 1){
            curP = random.nextInt(totalP);
            int anothP = random.nextInt(totalP);
            people.get(curP).interactWith(people.get(anothP));
        }
        else{
            curF = random.nextInt(totalF);
            int anothF = random.nextInt(totalF);
            freaks.get(curF).interactWith(freaks.get(anothF));
        }
        printNewLine();

        return new int[]{curP, curF};
    }

    private void getDoSome(int[] res){
        System.out.println("Well we saw how they connect, but but what else can they do on the roof?");
        printNewLine();
        if (res[0] != -1){
            System.out.println("What you can do, " + people.get(res[0]).getName() + "?");
            people.get(res[0]).doSome(getAction());
        }
        if (res[1] != -1){
            System.out.println("Maybe you show as something, " + freaks.get(res[0]).getName() + "?");
            freaks.get(res[1]).doSome(getAction());
            //freaks.get(res[1]).fly();
        }
        printNewLine();
    }

    private Actions getAction(){
        Random random = new Random();
        int actNum = random.nextInt(Actions.values().length);
        return Actions.values()[actNum];
    }

    @Override
    public String toString() {
        return "package.com.classes.Roof["+
                "this.people = " + people.toString() +
                "this.freaks = " + freaks.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Roof roof = (Roof) obj;
        if (!(people.containsAll(roof.people) && roof.people.containsAll(people)))
            return false;
        if (!(freaks.containsAll(roof.freaks)) && roof.freaks.containsAll(freaks))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < people.size(); i++)
            result += people.get(i).hashCode();
        for (int i = 0; i < freaks.size(); i++)
            result += freaks.get(i).hashCode();
        return result;
    }
}
