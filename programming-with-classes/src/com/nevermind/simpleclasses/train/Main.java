package com.nevermind.simpleclasses.train;

import java.time.LocalTime;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Train[] trains=new Train[5];
        trains[0]= new Train("Москва",958,LocalTime.of(11,25));
        trains[1]= new Train("Брест",101,LocalTime.of(15,28));
        trains[2]= new Train("Москва",454,LocalTime.of(17,43));
        trains[3]= new Train("Вильнюс",254,LocalTime.of(0,11));
        trains[4]= new Train("Варшава",665,LocalTime.of(21,59));

        System.out.println(Arrays.toString(trains));

        Train.sortByNumber(trains);

        System.out.println(Arrays.toString(trains));

        Train.sortByDestPoint(trains);

        System.out.println(Arrays.toString(trains));

        Train.printTrainInfo(trains);

    }
}
