package com.nevermind.simpleclasses.counter;

/*5. Опишите класс, реализующий десятичный счетчик, который может увеличивать или уменьшать свое значение
на единицу в заданном диапазоне. Предусмотрите инициализацию счетчика значениями по умолчанию и
произвольными значениями. Счетчик имеет методы увеличения и уменьшения состояния, и метод
позволяющее получить его текущее состояние. Написать код, демонстрирующий все возможности класса.*/

public class Counter {

    private int c;  //счетчик
    private final int MAX; //верхний предел
    private final int MIN; //нижний предел

    //конструктор по умолчанию
    public Counter() {
        c = 0;
        MAX = 10;
        MIN = -10;
    }

    //конструктор со входными параметрами
    public Counter(int c, int max, int min) {

        //если параметры заданы неверно инициализируем поля как по умолчанию, иначе инициализируем входными значениями
      if(c<min||c>max){
          this.c = 0;
          MAX = 10;
          MIN = -10;
      }else{
          this.c = c;
          MAX = max;
          MIN = min;
      }
    }

    //увеличить значение счетчика на 1, если это не превысит максимум
    public void increment() {
        if (c < MAX)
            c++;
    }

    //увеньшить значение счетчика на 1, , если это не превысит минимум
    public  void decrement() {
        if (c > MIN)
            c--;
    }

    //вывести текущее значение счетчика
    public  void print() {
        System.out.println("Текущее значение счетчика: "+c);
    }
}