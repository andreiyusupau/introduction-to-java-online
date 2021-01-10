package com.nevermind.seaport.director;

import com.nevermind.seaport.entity.Container;
import com.nevermind.seaport.entity.Ship;

import java.util.concurrent.Semaphore;

//Причал. Интерфейс Runnable для запуска в отдельном потоке
public class Dock implements Runnable {
    private Ship currentShip; //текущий корабль на разгрузке
    private Seaport seaport; //ссылка на порт для взаимодействия
    private Semaphore shipSem; //семафор доступа к списку кораблей
    private Semaphore contSem; //семафор доступа к списку контейнеров в порту
    private int stage = 0; //этап 0 - швартовка корабля, 1 - разгрузка корабля, 2 - загрузка корабля


    public Dock(Seaport seaport, Semaphore shipSem, Semaphore contSem) {
        this.seaport = seaport;
        this.shipSem = shipSem;
        this.contSem = contSem;
    }

    //переопределяем метод run
    @Override
    public void run() {
        System.out.println("Док "+ toString()+ " запущен");
        //В заивисмости от текущей стадии вызываем соответствующий метод
        while (true) {
            switch (stage) {
                case 0 -> setCurrentShip(); //принять корабль
                case 1 -> unloadCurrentShip(); //разгрузить
                case 2 -> loadCurrentShip(); //загрузить
            }
        }
    }

    //принять корабль
    public void setCurrentShip() {

        try {
//пытаемся взять контроль над списком кораблей
            shipSem.acquire();
            System.out.println("Док "+ toString()+" включил семафор кораблей");

            Thread.sleep(200);//для наглядности отображения результата работы программы в консоли

            //берем первый корабль из очереди
            currentShip = seaport.nextShip();
            System.out.println("Док взял корабль.Текущее число кораблей в очереди- "+seaport.getShipCount());

            //отдаем контроль над списком кораблей
            shipSem.release();
            System.out.println("Док "+ toString()+" выключил семафор кораблей");

            //если корабль причалил переходим к разгрузке
            if (currentShip != null) {
                stage = 1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //разгрузка корабля
    public void unloadCurrentShip() {
        try {
            //пытаемся взять контроль над списком контейнеров в порту
            contSem.acquire();
            System.out.println("Док "+ toString()+" включил семафор контейнеров");

            System.out.println("РАЗГРУЗКА");
            Thread.sleep(200); //для наглядности отображения результата работы программы в консоли

            //счетчик числа транзитных грузов , которые поплывут дальше без выгрузки
            int transit=0;

            //обходим список грузов
            for (int i = 0; i < currentShip.getCargo().size(); i++) {
                //если пункт назначения груза не совпадает с пунктом назначеняи корабля, пытаемся его выгрузить
                if (!currentShip.getCargo().get(i).getDestination().equals(currentShip.getDestination())) {

                    //если позволяет вместимость порта
                    if(seaport.checkCapacity()){
                    seaport.addContainer(currentShip.getCargo().remove(i)); //переносим контейнер в порт
                    }
                }else {
                    //если контейнер транзитный обновляем счетчик
                    transit++;
                }
            }

            //отдаем контроль над списком контейнеров
            contSem.release();
            System.out.println("Док "+ toString()+" выключил семафор контейнеров");

            //если на борту остались только тарнзитные грузы, переходим к загрузке корабля
            if (currentShip.getCargo().size()==transit) {
                stage = 2;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void loadCurrentShip() {
        try {
            //пытаемся взять контроль над списком контейнеров в порту
            contSem.acquire();
            System.out.println("Док "+ toString()+" включил семафор контейнеров");

            System.out.println("ЗАГРУЗКА");
            Thread.sleep(200);//задержка для наглядности отображения в консоли

            //текущая загрузка корабля
            int currLoad;
            currLoad=currentShip.getCargo().size();

            //пытаемся дозаполнить корабль "до предела"
            for (int i = currLoad; i < currentShip.getCarryingCapacity(); i++) {

                //берем контейнер из порта
                Container container;
                container = seaport.getContainer(currentShip.getDestination());

                //если удалось взять, кладем в корабль, если нет - прерываем цикл (контейнеры в порту кончились)
                if (container != null) {
                    currentShip.getCargo().add(container);
                } else {
                    break;
                }
            }
            System.out.println("текущая загрузка корабля "+currLoad+" необходимая - " +currentShip.getCarryingCapacity());

           //отдаем контроль над списком контейнеров
            contSem.release();
            System.out.println("Док "+ toString()+" выключил семафор контейнеров");

            //если корабль загружен полностью отпускаем его, иначе заходим на очередной круг
            if (currentShip.getCargo().size() == currentShip.getCarryingCapacity()) {
                System.out.println("Док "+ toString()+" выпустил корабль");
                stage = 0;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
