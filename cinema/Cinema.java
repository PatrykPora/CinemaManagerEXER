package cinema;

import java.util.Scanner;

public class Cinema {

    private static int currentIncome = 0;
    private static int numberOfSoldTickets = 0;

    public static String[][] createCinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rowNum = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        String[][] cinemaPlan = new String[rowNum][seatsInRow];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                cinemaPlan[i][j] = "S";
            }
        }
        return cinemaPlan;
    }

    public static void showTheSeats(String[][] cinemaPlan) {
        System.out.println("Cinema:");
        for (int i = 0; i <= cinemaPlan[0].length; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(i + " ");
            }
        }

        System.out.println();

        for (int i = 0; i < cinemaPlan.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinemaPlan[i].length; j++) {
                System.out.print(cinemaPlan[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void buyTicket(String[][] cinema) {
        Scanner scanner = new Scanner(System.in);

        boolean flag;

        do {
            System.out.println("Enter a row number:");
            int clientRowChose = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int clientSeatNumber = scanner.nextInt();

            if (clientRowChose > cinema.length || clientSeatNumber > cinema[0].length
                    || clientRowChose <= 0 || clientSeatNumber <= 0) {
                System.out.println("Wrong input !");
                flag = false;
            } else if (cinema[clientRowChose - 1][clientSeatNumber - 1].equals("B")) {
                System.out.println("That ticket has already been purchased");
                flag = false;
            } else {
                int price;
                if (cinema.length * cinema[0].length <= 60) {
                    price = 10;
                    System.out.println("Ticket price: $" + price);
                } else if (cinema.length % 2 == 0 && clientRowChose <= (cinema.length / 2)) {
                    price = 10;
                    System.out.println("Ticket price: $" + price);
                } else if (cinema.length % 2 == 0 && clientRowChose > (cinema.length / 2)) {
                    price = 8;
                    System.out.println("Ticket price: $" + price);
                } else if (cinema.length % 2 != 0 && clientRowChose <= (cinema.length / 2)) {
                    price = 10;
                    System.out.println("Ticket price: $" + price);
                } else {
                    price = 8;
                    System.out.println("Ticket price: $" + price);
                }
                currentIncome += price;
                numberOfSoldTickets++;
                cinema[clientRowChose - 1][clientSeatNumber - 1] = "B";
                flag = true;
            }
        } while (!flag);
    }


    public static void printMenu() {
        System.out.println("1. Show the seats\n"
                + "2. Buy a ticket\n"
                + "3. Statistics\n"
                + "0. Exit");
    }

    public static void showStatistics(String[][] cinema) {
        int seats = cinema.length * cinema[0].length;
        double percentageOfBooked = ((double) numberOfSoldTickets / seats) * 100;
        int totalIncome;
        if (seats <= 60) {
            totalIncome = seats * 10;
        } else if (cinema.length % 2 == 0) {
            totalIncome = (((cinema.length / 2) * cinema[0].length) * 10) + (((cinema.length / 2) * cinema[0].length) * 8);
        } else {
            totalIncome = (((cinema.length / 2) * cinema[0].length) * 10) + (((cinema.length / 2 + 1) * cinema[0].length) * 8);
        }
        System.out.println("Number of purchased tickets: " + numberOfSoldTickets);
        System.out.printf("Percentage: %.2f%% \n", percentageOfBooked);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }


    public static void main(String[] args) {

        String[][] cinema = createCinema();
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userChoice = scanner.nextInt();

        while (userChoice != 0) {
            switch (userChoice) {
                case 1:
                    showTheSeats(cinema);
                    printMenu();
                    userChoice = scanner.nextInt();
                    break;
                case 2:
                    buyTicket(cinema);
                    printMenu();
                    userChoice = scanner.nextInt();
                    break;
                case 3:
                    showStatistics(cinema);
                    printMenu();
                    userChoice = scanner.nextInt();
                    break;
                case 0:
                    break;
            }
        }

    }
}
