import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {

    // Method for calculating the price after applying the discount
    public static double applyDiscount(double totalPrice, int days) {
        double discount = 0;
        if (days > 10) {
            discount = 0.07; // 7% discount
        } else if (days > 7) {
            discount = 0.04; // 4% discount
        } else if (days > 5) {
            discount = 0.03; // 3% discount
        } else if (days >= 3) {
            discount = 0.02; // 2% discount
        }

        double discountedPrice = totalPrice - (totalPrice * discount);
        System.out.println("Discount applied: " + (discount * 100) + "% ");
        return discountedPrice;
    }

    // Method to generate a random license plate number
    public static String generateLicensePlate() {
        String[] provinces = {"ก", "ข", "ค", "ฆ", "ง", "จ", "ฉ", "ช", "ซ", "ญ"};
        String province = provinces[(int) (Math.random() * provinces.length)];
        int number = (int) (Math.random() * 9000) + 1000; // Generate random number between 1000 and 9999
        return province + province + " " + number; // Example: กก 1234
    }

    // Method for printing the receipt
    public static void printReceipt(String carType, int days, double totalPrice, double discountedPrice, String licensePlate, String name, String startDate, String endDate) {
        System.out.println("\n--- Receipt ---");
        System.out.println("Renter Name: " + name);
        System.out.println("Car Type: " + carType);
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Total Rental Days: " + days + " days");
        System.out.println("Total Rental Price (before discount): " + totalPrice + " THB");
        if (totalPrice == discountedPrice) {
            System.out.println("Yor discount is " +"0" + " THB");
        }else {
            System.out.println("Yor discount is "+ (totalPrice - discountedPrice) + " THB");
        }
        System.out.println("Total Rental Price (after discount): " + discountedPrice + " THB");
        System.out.println("Thank you for using our service!\n");
    }

    // Method for calculating the total rental price
    public static double rent(String carBrand, String carType, int days) {
        double totalPrice = 0;

        if (carBrand.equalsIgnoreCase("toyota")) {
            switch (carType.toLowerCase()) {
                case "corolla": totalPrice = days * 550; break;
                case "camry": totalPrice = days * 1200; break;
                case "hilux": totalPrice = days * 1500; break;
                default: System.out.println("Invalid model for Toyota."); break;
            }
        } else if (carBrand.equalsIgnoreCase("honda")) {
            switch (carType.toLowerCase()) {
                case "civic": totalPrice = days * 600; break;
                case "accord": totalPrice = days * 1200; break;
                case "hr-v": totalPrice = days * 1300; break;
                default: System.out.println("Invalid model for Honda."); break;
            }
        } else if (carBrand.equalsIgnoreCase("mercedes")) {
            switch (carType.toLowerCase()) {
                case "a-class": totalPrice = days * 600; break;
                case "c-class": totalPrice = days * 1200; break;
                case "e-class": totalPrice = days * 1300; break;
                default: System.out.println("Invalid model for Mercedes."); break;
            }
        } else if (carBrand.equalsIgnoreCase("bmw")) {
            switch (carType.toLowerCase()) {
                case "3 series": totalPrice = days * 600; break;
                case "5 series": totalPrice = days * 1200; break;
                case "x5": totalPrice = days * 1300; break;
                default: System.out.println("Invalid model for BMW."); break;
            }

        } else if (carBrand.equalsIgnoreCase("tesla")) {
            switch (carType.toLowerCase()) {
                case "model 3": totalPrice = days * 600; break;
                case "model s": totalPrice = days * 1200; break;
                case "model x": totalPrice = days * 1300; break;
                default: System.out.println("Invalid model for Tesla."); break;
            }

        }
        return totalPrice;
    }
    // Method for selecting the car model based on brand
    public static String selectCarModel(String brand) {
        Scanner scanner = new Scanner(System.in);
        String selectedCarModel = "";

        String[] models = {};
        switch (brand.toLowerCase()) {
            case "toyota":
                models = new String[]{"Corolla", "Camry", "Hilux"};
                break;
            case "honda":
                models = new String[]{"Civic", "Accord", "HR-V"};
                break;
            case "mercedes":
                models = new String[]{"A-Class", "C-Class", "E-Class"};
                break;
            case "bmw":
                models = new String[]{"3 Series", "5 Series", "X5"};
                break;
            case "tesla":
                models = new String[]{"Model 3", "Model S", "Model X"};
                break;
            default:
                System.out.println("Invalid brand selected.");
                return "";
        }

        while (selectedCarModel.isEmpty()) {
            System.out.println("Select the model:");
            for (int i = 0; i < models.length; i++) {
                System.out.println((i + 1) + ". " + models[i]);
            }
            System.out.print("Enter the number or name of the model: ");

            String input = scanner.nextLine();
            try {
                // Check if the input is a number
                int modelChoice = Integer.parseInt(input);
                if (modelChoice >= 1 && modelChoice <= models.length) {
                    selectedCarModel = models[modelChoice - 1];
                } else {
                    System.out.println("Invalid number choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                // If input is not a number, match it with the model names
                for (String model : models) {
                    if (model.equalsIgnoreCase(input)) {
                        selectedCarModel = model;
                        break;
                    }
                }
                if (selectedCarModel.isEmpty()) {
                    System.out.println("Invalid model name. Please try again.");
                }
            }
        }
        return selectedCarModel;
    }

    // Method to calculate the number of rental days between two dates
    public static int getDaysFromDate(int year, int month, int day, int[] daysInMonth) {
        int totalDays = 0;

        // คำนวณจำนวนวันจากปี
        totalDays += (year - 1900) * 365 + (year - 1900) / 4; // ปีอธิกสุรทิน
        if (year % 4 == 0 && month <= 2) totalDays--; // ปรับปีอธิกสุรทิน (ถ้าเดือนก่อนหรือกุมภาพันธ์)

        // คำนวณจำนวนวันจากเดือน
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }

        // คำนวณจำนวนวันจากวันในเดือน
        totalDays += day;

        return totalDays;
    }

    public static void saveCustomer(String carType, int days, double totalPrice, double discountedPrice, String licensePlate, String name, String startDate, String endDate) {
        try (FileWriter writer = new FileWriter("/Users/jaifha_wongkunta/Documents/wuttikan/java-rentalcar-project/customers.txt", true)) {
            writer.write("Renter Name: " + name + "\n");
            writer.write("Car Type: " + carType + "\n");
            writer.write("License Plate: " + licensePlate + "\n");
            writer.write("Start Date: " + startDate + "\n");
            writer.write("End Date: " + endDate + "\n");
            writer.write("Total Rental Days: " + days + " days \n");
            writer.write("Total Rental Price (before discount): " + totalPrice + " THB \n");
            if (totalPrice == discountedPrice) {
                writer.write("Yor discount is " +"0" + " THB \n");
            }else {
                writer.write("Yor discount is "+ (totalPrice - discountedPrice) + " THB \n");
            }
            writer.write("Total Rental Price (after discount): " + discountedPrice + " THB \n");
            writer.write("---------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error writing to text file: " + e.getMessage());
        }
    }

    // Method to display rental information
    public static void displayRentalInfo() {
        try {
            File file = new File("/Users/jaifha_wongkunta/Documents/wuttikan/java-rentalcar-project/customers.txt");
            Scanner fileScanner = new Scanner(file);
            System.out.println("\n--- Rental Information ---");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Main menu method
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Vehicle Rental System =====");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Rental Information");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");

            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "1":
                case "rent a vehicle":
                    rentVehicle(scanner);
                    break;
                case "2":
                case "rental information":
                    displayRentalInfo();
                    break;
                case "3":
                case "exit":
                    System.out.println("Thank you for using Vehicle Rental System");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void rentVehicle(Scanner scanner) {
        // Select the car brand
        String[] carBrands = {"Toyota", "Honda", "Mercedes", "BMW", "Tesla"};
        String selectedCarBrand = "";

        while (selectedCarBrand.isEmpty()) {
            System.out.println("Please select the car brand:");
            for (int i = 0; i < carBrands.length; i++) {
                System.out.println((i + 1) + ". " + carBrands[i]);
            }
            System.out.print("Enter number or name of the brands: ");
            String input = scanner.nextLine();

            try {
                int brandChoice = Integer.parseInt(input);
                if (brandChoice >= 1 && brandChoice <= carBrands.length) {
                    selectedCarBrand = carBrands[brandChoice - 1];
                } else {
                    System.out.println("Invalid number. Please try again.");
                }
            } catch (NumberFormatException e) {
                for (String brand : carBrands) {
                    if (brand.equalsIgnoreCase(input)) {
                        selectedCarBrand = brand;
                        break;
                    }
                }
                if (selectedCarBrand.isEmpty()) {
                    System.out.println("Invalid brand name. Please try again.");
                }
            }
        }
        System.out.println("You selected: " + selectedCarBrand);

        // Select the car model
        String selectedCarModel = selectCarModel(selectedCarBrand);

        // Display the selected model
        if (!selectedCarModel.isEmpty()) {
            System.out.println("You selected: " + selectedCarBrand + " " + selectedCarModel);
        }

        // Get rental start and end dates
        System.out.println("Please enter the start and end dates for the rental:");
        System.out.print("Enter start day: ");
        int startDay = scanner.nextInt();
        System.out.print("Enter start month: ");
        int startMonth = scanner.nextInt();
        System.out.print("Enter start year: ");
        int startYear = scanner.nextInt();

        System.out.print("Enter end day: ");
        int endDay = scanner.nextInt();
        System.out.print("Enter end month: ");
        int endMonth = scanner.nextInt();
        System.out.print("Enter end year: ");
        int endYear = scanner.nextInt();

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String startDate = startDay + "/" + startMonth + "/" + startYear;
        String endDate = endDay + "/" + endMonth + "/" + endYear;

        // Calculate the number of rental days
        int startrent = getDaysFromDate(startYear,startMonth,startDay,daysInMonth);
        int endrent = getDaysFromDate(endYear,endMonth,endDay,daysInMonth);
        int daysBetween = endrent - startrent;
        System.out.println("Total rental days: " + daysBetween);

        // Calculate the rental price
        double totalPrice = rent(selectedCarBrand, selectedCarModel, daysBetween);
        double discountedPrice = applyDiscount(totalPrice, daysBetween);

        // Generate the license plate
        String licensePlate = generateLicensePlate();

        // Get personal information
        scanner.nextLine();  // Consume the leftover newline
        System.out.print("Name: ");
        String name = scanner.nextLine();

        // Print the receipt
        printReceipt(selectedCarBrand + " " + selectedCarModel, daysBetween, totalPrice, discountedPrice, licensePlate, name, startDate, endDate);
        saveCustomer(selectedCarBrand + " " + selectedCarModel, daysBetween, totalPrice, discountedPrice, licensePlate, name, startDate, endDate);
    }

    public static void main(String[] args) {
        showMenu();
    }
}