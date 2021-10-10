package com.company;

import com.company.classes.Freak;
import com.company.classes.People;
import com.company.classes.Roof;
import com.company.enums.Actions;

import java.io.Console;

public class Main {

    public static void main(String[] args) {
        People bob = new People("Bob", 19, false);
        People mal = new People("Bob", 19, false);

        Freak Karl = new Freak("Bob",19);
        Freak Ktulhu = new Freak("Ktulhu", 1000);

        Roof roof = new Roof();
        roof.put(bob);
        roof.put(mal);

        Roof roof1 = new Roof();
        roof1.put(Karl);
        roof1.put(Ktulhu);

        //roof.startAction();

        System.out.println(roof.equals(roof1));

        System.out.println(roof.hashCode());
        System.out.println(roof1.hashCode());
    }
}
