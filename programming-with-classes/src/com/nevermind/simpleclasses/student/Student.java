package com.nevermind.simpleclasses.student;

/*3. Создайте класс с именем Student, содержащий поля: фамилия и инициалы, номер группы, успеваемость (массив
из пяти элементов). Создайте массив из десяти элементов такого типа. Добавьте возможность вывода фамилий и
номеров групп студентов, имеющих оценки, равные только 9 или 10.*/

public class Student {
    //поля класса

    //имя
    String name;

    //фамилия
    String surname;

    //номер группы
    int groupNum;

    //массив оценок
    int[] marks;

    //конструктор
    public Student(String name, String surname, int groupNum, int[] marks) throws IllegalArgumentException{

        //проверяем, что количество отметок равно 5 иначе выбрасываем исключение
        if(marks.length!=5){
            throw new IllegalArgumentException("Количество оценок должно быть равно 5");
        }else {
            this.name = name;
            this.surname = surname;
            this.groupNum = groupNum;
            this.marks = marks;
        }
    }

    //если студент является отличником выводим на печать.
    public static void printStraightA(Student s){
        if(isStraightA(s)){
            System.out.println("Отличник "+s.surname+". Номер группы "+s.groupNum);
        }
    }

    //вывести на печать , если отличник
    private static boolean isStraightA(Student s) {

        //обход оценок
        for (int mark : s.marks) {

            //если хоть одна оценка ниже 9, вернуть false
            if (mark < 9) {
               return false;
            }
        }
       return true;
    }
}
