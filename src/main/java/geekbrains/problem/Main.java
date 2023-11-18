package geekbrains.problem;

import java.util.HashMap;
import java.util.Random;

public class Main {
    private static final int len = 3;
    private static final int steps = 1000;
    private static boolean[] init(int len){
        boolean[] array = new boolean[len];
        Random random = new Random();
        int index = random.nextInt(len);
        array[index] = true;
        return array;
    }
    private static double getWinPersent(HashMap<Integer, Boolean> map, int steps){
        int count = 0;
        for (Integer key: map.keySet()){
            if (map.get(key)) count++;
        }
        double persent = (double) count/ (double) steps * 100;

        return Math.round(persent * 100.0) / 100.0;
    }
    private static int getOpenIndex(int userChoice, boolean[] doors){
        for (int i = 0; i < doors.length; i++) {
           if (doors[i]) continue;
           if (i == userChoice) continue;
           if (!doors[i]) return i;
        }
        return 0;
    }
    private static int getResultChoice(int userChoice, boolean[] doors){
        int openDoor = getOpenIndex(userChoice, doors);
        for (int i = 0; i < doors.length; i++) {
            if (i == userChoice) continue;
            if (i == openDoor) continue;
            return i;
        }
        return 0;
    }
    public static void main(String[] args) {
        boolean[] doors = init(len);
        Random random = new Random();
        int userChoice = 0;
        HashMap<Integer, Boolean> exp1 = new HashMap<>();
        for (int i = 0; i < steps; i++) {
            userChoice= random.nextInt(len);
            exp1.put(i, doors[userChoice]);
        }
        System.out.println("Игрок не меняет свой первоначальный выбор. Процент побед: " + getWinPersent(exp1, steps));
        int resultChoice = 0;
        HashMap<Integer, Boolean> exp2 = new HashMap<>();
        for (int i = 0; i < steps; i++) {
            userChoice= random.nextInt(len);
            resultChoice = getResultChoice(userChoice, doors);
            exp2.put(i, doors[resultChoice]);
        }
        System.out.println("Игрок меняет свой первоначальный выбор. Процент побед: " +getWinPersent(exp2, steps));
    }
}