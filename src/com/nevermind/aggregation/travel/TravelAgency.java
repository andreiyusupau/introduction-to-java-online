package com.nevermind.aggregation.travel;

import java.math.BigDecimal;
import java.util.Arrays;

/*5. Туристические путевки. Сформировать набор предложений клиенту по выбору туристической путевки
различного типа (отдых, экскурсии, лечение, шопинг, круиз и т. д.) для оптимального выбора. Учитывать
возможность выбора транспорта, питания и числа дней. Реализовать выбор и сортировку путевок.*/


/*В целом видится работа программы по принципу какого-нибудь airbnb (только вместо жилья путевки).
Клиент заходит на сайт (TravelAgency , который содержит все возможные путевки) и ставит нужные фильтры,
 а ему в ответ приходит список из подходящих путевок*/

public class TravelAgency {

    private String name; //название агенства
    private Client[] clients; //список клиентов
    private TouristVoucher[] touristVouchers; //список путевок

    public TravelAgency(String name) {
        this.name = name;
        touristVouchers = new TouristVoucher[0];
    }

    //создать клиента
    public Client createClient(String name) {
        return new Client(name, touristVouchers, this);
    }

    //создать путевку
    public void createVoucher(TouristVoucher touristVoucher) {
        touristVouchers = Arrays.copyOf(touristVouchers, touristVouchers.length + 1); //расширяем массив
        touristVouchers[touristVouchers.length - 1] = touristVoucher; //добавляем новый элемент
    }

    //метод для удаления книги
    public void deleteVoucher(String name) {
        touristVouchers = Arrays.stream(touristVouchers) //преобразуем массив в поток
                .filter(v -> !(v.getVoucherName().equals(name))) //если название путевки совпадает с удаляемым, не возврращаем его
                .toArray(TouristVoucher[]::new); //создаем новый массив и присваиваем его значеие переменной touristVouchers

        //обновляем список путевок всем клиентам
        for (Client client : clients) {
            client.clearProposed();
        }
    }

    //сортировка путевок по цене
    public void sortVouchers() {
        Arrays.sort(touristVouchers);
    }

    public TouristVoucher[] getTouristVouchers() {
        return touristVouchers;
    }
}


class Client {

    private String name; //имя клиента
    private TouristVoucher[] proposedVouchers; //предлагаемые путевки
    private final TravelAgency agency; //агенство клиента

    public Client(String name, TouristVoucher[] proposedVouchers, TravelAgency agency) {
        this.name = name;
        this.proposedVouchers = proposedVouchers;
        this.agency = agency;
    }

    //поиск путевки по типу
    public Client searchByType(VoucherType[] desiredVoucherType) {

        TouristVoucher[] desiredVouchers = new TouristVoucher[0];//создаем временный массив

        for (TouristVoucher voucher : proposedVouchers) {//обходим список предлагаемых путевок

            for (VoucherType voucherType : desiredVoucherType) { //обходим все желаемые варианты типов путевок

                if (voucherType.equals(voucher.getVoucherType())) {

                    desiredVouchers = addVoucher(desiredVouchers, voucher);//если путевка соотвествует условию добавляем во временный массив
                }
            }
        }
        this.setProposedVouchers(desiredVouchers); //присваиваем массиву предложений дял клиента новое значение

        return this;//возвращаем сам объект дял возможности цепного вызова поисковых фильтров
    }

    //поиск путевки по транспорту
    public Client searchByTransport(TransportType[] desiredTransportType) {

        TouristVoucher[] desiredVouchers = new TouristVoucher[0];//создаем временный массив

        for (TouristVoucher voucher : proposedVouchers) {//обходим список предлагаемых путевок

            for (TransportType transportType : desiredTransportType) { //обходим все желаемые варианты типов путевок

                if (transportType.equals(voucher.getTransportType())) {

                    desiredVouchers = addVoucher(desiredVouchers, voucher);//если путевка соотвествует условию добавляем во временный массив
                }
            }
        }
        this.setProposedVouchers(desiredVouchers); //присваиваем массиву предложений дял клиента новое значение

        return this;//возвращаем сам объект дял возможности цепного вызова поисковых фильтров
    }

    //поиск путевки по типу питания
    public Client searchByFood(FoodType[] desiredFoodType) {

        TouristVoucher[] desiredVouchers = new TouristVoucher[0];//создаем временный массив

        for (TouristVoucher voucher : proposedVouchers) {//обходим список предлагаемых путевок

            for (FoodType foodType : desiredFoodType) { //обходим все желаемые варианты типов путевок

                if (foodType.equals(voucher.getFoodType())) {

                    desiredVouchers = addVoucher(desiredVouchers, voucher);//если путевка соотвествует условию добавляем во временный массив
                }
            }
        }
        this.setProposedVouchers(desiredVouchers); //присваиваем массиву предложений дял клиента новое значение

        return this;//возвращаем сам объект дял возможности цепного вызова поисковых фильтров
    }

    //поиск путевки по длительности
    public Client searchByDuration(int minDuration, int maxDuration) {

        TouristVoucher[] desiredVouchers = new TouristVoucher[0]; //создаем временный массив

        for (TouristVoucher voucher : proposedVouchers) { //обходим список предлагаемых путевок

            if (voucher.getDuration() >= minDuration && voucher.getDuration() <= maxDuration) {
                desiredVouchers = addVoucher(desiredVouchers, voucher); //если путевка соотвествует условию добавляем во временный массив
            }
        }

        this.setProposedVouchers(desiredVouchers); //присваиваем массиву предложений дял клиента новое значение

        return this; //возвращаем сам объект дял возможности цепного вызова поисковых фильтров
    }

    //обнулить фильтры
    public Client clearProposed() {
        this.setProposedVouchers(agency.getTouristVouchers()); //обновляем перечень путевок из полного списка
        return this; //возвращаем сам объект дял возможности цепного вызова
    }

    //добавить путевку
    private TouristVoucher[] addVoucher(TouristVoucher[] touristVouchers, TouristVoucher touristVoucher) {
        touristVouchers = Arrays.copyOf(touristVouchers, touristVouchers.length + 1); //расширяем массив
        touristVouchers[touristVouchers.length - 1] = touristVoucher; //добавляем новый элемент
        return touristVouchers; //возвращаем массив
    }

    @Override
    public String toString() {
        return "Предложение для клиента " + name +
                ", подобранные путевки:\n" +
                (proposedVouchers.length > 0 ? Arrays.toString(proposedVouchers) : "по вашему запросу ничего не найдено.");
    }

    //геттеры и сеттеры
    public TouristVoucher[] getProposedVouchers() {
        return proposedVouchers;
    }

    public void setProposedVouchers(TouristVoucher[] proposedVouchers) {
        this.proposedVouchers = proposedVouchers;
    }

}


//путевка
class TouristVoucher implements Comparable<TouristVoucher> {

    private String voucherName; //название
    private VoucherType voucherType; //тип
    private TransportType transportType; //тип транспорта
    private FoodType foodType; //тип питания
    private int duration; //длительность
    private BigDecimal price; //цена

    //конструктор
    public TouristVoucher(String voucherName, VoucherType voucherType, TransportType transportType, FoodType foodType, int duration, double price) {
        this.voucherName = voucherName;
        this.voucherType = voucherType;
        this.transportType = transportType;
        this.foodType = foodType;
        this.duration = duration;
        this.price = BigDecimal.valueOf(price);
    }

    //метод для сортировки путевок по цене
    @Override
    public int compareTo(TouristVoucher o) {
        return o.getPrice().compareTo(this.getPrice());
    }

    @Override
    public String toString() {
        return "Путевка {" +
                "Название \"" + voucherName + "\"" +
                ", тип путевки - " + voucherType +
                ", транспорт - " + transportType +
                ", питание - " + foodType +
                ", длительность - " + duration + " дня(ей)" +
                ", цена " + price + " руб." +
                "}\n";
    }

    //геттеры
    public String getVoucherName() {
        return voucherName;
    }

    public VoucherType getVoucherType() {
        return voucherType;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public int getDuration() {
        return duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

}

//типы путевок
enum VoucherType {

    RECREATION("отдых"),
    TOUR("тур"),
    TREATMENT("оздоровление"),
    SHOPPING("шоппинг"),
    CRUISE("круиз"),
    SPORTS("спорт");

    //поле названия путевки
    private final String text;

    VoucherType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

//виды транспорта
enum TransportType {
    BUS("автобус"),
    TRAIN("поезд"),
    AIRPLANE("самолет"),
    SHIP("корабль"),
    BIKE("велосипед"),
    JETPACK("джетпак");

    //поле названия транспорта
    private final String text;

    //конструктор
    TransportType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

//виды питания - на русский переводить нет смысла
enum FoodType {
    ROOM_ONLY,
    BED_AND_BREAKFAST,
    HALF_BOARD,
    FULL_BOARD,
    ALL_INCLUSIVE,
    ALL_INCLUSIVE_PREMIUM,
    ULTRA_ALL_INCLUSIVE
}