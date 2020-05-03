package com.nevermind.present;
/*Задача 5.
Создать консольное приложение, удовлетворяющее следующим требованиям:
• Корректно спроектируйте и реализуйте предметную область задачи.
• Для создания объектов из иерархии классов продумайте возможность использования порождающих шаблонов
проектирования.
• Реализуйте проверку данных, вводимых пользователем, но не на стороне клиента.
• для проверки корректности переданных данных можно применить регулярные выражения.
• Меню выбора действия пользователем можно не реализовывать, используйте заглушку.
• Особое условие: переопределите, где необходимо, методы toString(), equals() и hashCode().

Вариант B. Подарки. Реализовать приложение, позволяющее создавать подарки (объект, представляющий собой
подарок). Составляющими целого подарка являются сладости и упаковка.*/
class Client{

        }
public class Present {
    private Wrapping wrapping;
   private Candy [] candies;

    public Present(Wrapping wrapping, Candy[] candies) {
        this.wrapping = wrapping;
        this.candies = candies;
    }

    //Шаблон строитель
    static class PresentBuilder{
        private Wrapping wrapping;
        private Candy[] candies = new Candy[0];
        PresentBuilder(){

        }
        public PresentBuilder addWrapping(){
            return this;
        }
        public PresentBuilder addCandies(int candyCount){
            candies= new Candy[candyCount];
            return this;
        }
        public Present build(){
            return new Present(wrapping,candies);
        }
    }
}
class Wrapping{

}
class Candy{

}
