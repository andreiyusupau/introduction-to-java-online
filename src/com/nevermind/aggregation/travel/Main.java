package com.nevermind.aggregation.travel;

public class Main {

    public static void main(String[] args) {
        //создаем агенство
        TravelAgency airbnb = new TravelAgency("airbnb");

        //наполняем его путевками
        airbnb.createVoucher(new TouristVoucher("Тур по Полесью", VoucherType.TOUR, TransportType.BUS, FoodType.FULL_BOARD, 3, 390));
        airbnb.createVoucher(new TouristVoucher("Тур в Египет", VoucherType.RECREATION, TransportType.AIRPLANE, FoodType.ALL_INCLUSIVE_PREMIUM, 9, 1490));
        airbnb.createVoucher(new TouristVoucher("Круиз по Средиземному морю", VoucherType.CRUISE, TransportType.SHIP, FoodType.ULTRA_ALL_INCLUSIVE, 31, 11000));

      //сортируем их по цене
        airbnb.sortVouchers();

        //создаем клиента
        Client client = airbnb.createClient("Петр");

        //последовательно вводим фильтры
        client.searchByType(new VoucherType[]{VoucherType.TOUR, VoucherType.RECREATION, VoucherType.SPORTS})
                .searchByTransport(new TransportType[]{TransportType.AIRPLANE, TransportType.BUS})
                .searchByFood(new FoodType[]{FoodType.ALL_INCLUSIVE_PREMIUM, FoodType.FULL_BOARD})
                .searchByDuration(2, 10);

        //выводим на экран результат
        System.out.println(client.toString());

        //сбрасываем фильтры
        client.clearProposed();

        //выводим результат
        System.out.println(client.toString());
    }


}
