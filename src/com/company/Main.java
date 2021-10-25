package com.company;

import com.company.classes.Freak;
import com.company.classes.People;
import com.company.classes.Roof;
import com.company.enums.Actions;

import java.io.Console;
import java.io.IOException;

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

        Roof.RoofManager roofManager = new Roof.RoofManager();
        System.out.println(roofManager.getRoofCount());
        roofManager.getLocation();
        roofManager.setLocation("Saint-Petersburg");

        Freak.FreakToUser god = Karl.new FreakToUser();
        god.pickUp();
    }
}
