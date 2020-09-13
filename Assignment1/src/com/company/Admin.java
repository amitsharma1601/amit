package com.company;

import java.io.IOException;
import java.util.*;

public class Admin {
    private int cust_id = 0;
    private ArrayList<Customer> list = new ArrayList<>();

    public void startApp() {

        boolean stop = false;
        list = new ArrayList<>();
        while (!stop) {
            System.out.println("Press 1 to add a new Customer");
            System.out.println("Press 2 to add new car to existing customer");
            System.out.println("Press 3 to list all the customers with their cars name sorted");
            System.out.println("Press 4 to list individual customer details");
            System.out.println("Press 5 to generate prize for the customers");
            System.out.println("Press 6 to quit the application");
            try {
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                scanner.nextLine();
                stop = handleInput(input);
            }
            catch (InputMismatchException ex){
                System.out.println("Illegal input value entered");
            }

        }
    }

    private boolean handleInput(int input) {
        switch (input) {
            case 1:
                addCustomer();
                break;
            case 2:
                addNewCarToCustomer();
                break;
            case 3:
                listAllCustomers();
                break;
            case 4:
                listCustomer();
                break;
            case 5:
                generatePrize();
                break;
            case 6:
                return true;
            default:
                System.out.println("Please enter values between 1 and 6");
        }
        return false;
    }

    private void generatePrize() {
        System.out.println("Enter any three ids");
        List<Integer> ids = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(System.in);
            Set<Integer> randomvalues = new HashSet<>();


            for (int i = 0; i < 6; i++) {
                int num = (int) (Math.random() * 6);
                randomvalues.add(num);
            }

            for (int i = 0; i < 3; i++) {
                int num = scanner.nextInt();
                if (randomvalues.contains(num)) {
                    System.out.println("Customer with Id " + num + " is eligible for prize");
                }
            }

        }
        catch (InputMismatchException e){
            System.out.println("Illegal input entered");
        }

    }


    //to list details of the individual customer
    private void listCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            scanner.nextLine();
            Customer customer = null;
            List<Car> cars = null;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == id) {
                    customer = list.get(i);
                    break;
                }
            }
            if (customer != null) {
                cars = customer.getCars();
            } else {
                System.out.println("Invalid ID entered");
                return;
            }
            for (int j = 0; j < cars.size(); j++) {
                System.out.println(cars.get(j));
            }
        }catch (InputMismatchException ex){
            System.out.println("Illegal input entered");
        }
    }


    // To list all the customers details with their names sorted
    private void listAllCustomers() {
        List<Customer> customers = list;
        Collections.sort(customers, (o1, o2) ->
        {
            return o1.getName().compareTo(o2.getName());
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println(customers.get(i));
            List<Car> cars = list.get(i).getCars();
            for (int j = 0; j < cars.size(); j++) {
                System.out.println(cars.get(j));
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void addNewCarToCustomer() {
        System.out.println("Enter customer id");
        try {
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            scanner.nextLine();
            Customer customer = null;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == id) {
                    customer = list.get(i);
                    addCars(customer.getCars());
                    break;
                }
            }
            if (customer == null) {
                System.out.println("Cutomer id does not exist \n");
            }
        }catch (InputMismatchException ex){
            System.out.println("Illegal Input entered");
        }
    }

    private void addCustomer() {
        System.out.println("Enter customer name :");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        ArrayList<Car> cars = new ArrayList<>();
        addCars(cars);
        Customer customer = new Customer(cust_id++, name, cars);
        list.add(customer);
        System.out.println("Customer added successfully");
        System.out.println();
        System.out.println();
    }

    private void addCars(ArrayList<Car> cars) {
        boolean stop = false;
        while (!stop) {
            System.out.println("Enter Car Brand : 1 for Toyota, 2 for Maruti, 3 for Hyundai, 4 for exit");
            try {
                Scanner scanner = new Scanner(System.in);
                int value = scanner.nextInt();
                if (value == 4) {
                    stop = true;
                    continue;
                }
                if (value > 4) {
                    System.out.println("Invalid Input entered");
                    continue;
                }
                System.out.println("Enter Model of the car:");
                String model = scanner.next();
                System.out.println("Enter price of the car:");
                int price = scanner.nextInt();
                scanner.nextLine();
                Car car = new Car();
                switch (value) {
                    case 1:
                        cars.add(new Toyota(cars.size(), model, price));
                        break;
                    case 2:
                        cars.add(new Maruti(cars.size(), model, price));
                        break;
                    case 3:
                        cars.add(new Hyundai(cars.size(), model, price));
                        break;
                    case 4:
                        stop = true;
                        continue;
                    default:
                        break;
                }
                System.out.println("Car added successfully");
                System.out.println();
                System.out.println();
                System.out.println("Want to add more cars y/n");
                String s = scanner.nextLine().toLowerCase();
                stop = s.equals("n");
            }catch (InputMismatchException ex){
                System.out.println("Illegal input entered");
            }
        }
    }
}
