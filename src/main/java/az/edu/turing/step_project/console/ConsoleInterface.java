package az.edu.turing.step_project.console;

import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner;

    public ConsoleInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Online-board");
        System.out.println("2. Show flight info");
        System.out.println("3. Search and book a flight");
        System.out.println("4. Cancel booking");
        System.out.println("5. My flights");
        System.out.println("6. Exit");
    }

    public int getChoice() {
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    public String getFlightId() {
        System.out.print("Enter flight ID: ");
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}

