package com.nevermind.simpleclasses.student;

public class Main {

    public static void main(String[] args) {

        //создаем массив студентов
        Student[] students = new Student[10];
        students[0] = new Student("Джек", "Воробей", 23958, new int[]{5, 2, 3, 4,7});
        students[1] = new Student("Аль", "Пачино", 12412, new int[]{1, 8, 9, 6, 4});
        students[2] = new Student("Олдос", "Хаксли", 2432, new int[]{9, 10, 9, 10, 10});
        students[3] = new Student("Джон", "Голт", 76878, new int[]{9, 10, 9, 10, 8});
        students[4] = new Student("Дориан", "Грей", 564, new int[]{4, 7, 8, 9, 10});
        students[5] = new Student("Джейсон", "Стейтем", 56655, new int[]{1, 2, 2, 1, 2});
        students[6] = new Student("Брэд", "Питт", 456754, new int[]{9, 10, 9, 10, 10});
        students[7] = new Student("Сара", "Коннор", 47678, new int[]{10, 10, 9, 10, 10});
        students[7] = new Student("Мила", "Йовович", 93487, new int[]{10, 10, 9, 10, 1});
        students[8] = new Student("Джим", "Бим", 65675, new int[]{2, 3, 4, 9, 6});
        students[9] = new Student("Юлий", "Цезарь", 47567, new int[]{10, 10, 10, 10, 10});

        //выводим на печать отличников
        for (Student student : students) {
            Student.printStraightA(student);
        }
    }
}
