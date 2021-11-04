package com.company;

import com.company.classes.*;
import com.company.interfaces.Monitoring;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        People bob = new People("Bob", 19, false);
        People mal = new People("Bob", 19, false);

        Freak Karl = new Freak("Bob",19);
        Freak Ktulhu = new Freak("Ktulhu", 1000);

        Roof roof = new Roof();
        roof.put(bob);
        roof.put(mal);
        roof.put(Karl);
        roof.put(Ktulhu);

        roof.startAction();

        Freak.FreakToUser god = Karl.new FreakToUser();

/*        god.speakWithUser();
        god.speakWithUser();

        People den = new People();
        den.showInLog();*/

        Ktulhu.setSpecialPhrase("Hello, I'm" + Ktulhu.getName());

        try {
            god.tellAJoke();
        } catch (TerribleJokeException e) {
            System.out.println(e.getMessage());
            System.out.println("Change your joke please, it's not funny");
        }

        Monitoring monitoring = new Monitoring() {

            @Override
            public void getResume() {
                Roof.RoofManager roofManager = Roof.RoofManager.getRoofManager();
                ArrayList<Roof> roofs = roofManager.getRoofsList();
                printIntro();
                System.out.printf(" Roofs count - %d\n", roofs.size());
                printSeparator();
                for (int i = 0; i < roofs.size(); i++){
                    System.out.printf("Roof %d\nPeople count: %d\nFreaks count: %d\n", (i + 1),
                            roofs.get(i).getPeopleSize(), roofs.get(i).getFreaksSize());
                    printSeparator();
                }
            }

            private void printSeparator(){
                System.out.println("---------------------------");
            }

            private void printIntro(){
                System.out.println("-----Monitoring System-----");
            }
        };

        monitoring.getResume();
    }
}
