import java.util.Scanner;

interface Billable {
    double calculateTotal(int units);
}

class UtilityBill implements Billable {
    private String customerName;
    private int previousReading;
    private int currentReading;

    public UtilityBill(String name, int prev, int curr) {
        this.customerName = name;
        this.previousReading = prev;
        this.currentReading = curr;
    }

    public double calculateTotal(int units) {
        double rate;

        if (units <= 100) {
            rate = 1.0;
        } else if (units <= 300) {
            rate = 2.0;
        } else {
            rate = 5.0;
        }

        return units * rate;
    }

    public void generateBill() {
        if (previousReading > currentReading) {
            System.out.println("Error: Invalid meter readings");
            return;
        }

        int units = currentReading - previousReading;
        double total = calculateTotal(units);
        double tax = total * 0.1; 

        System.out.println("\n--- Digital Receipt ---");
        System.out.println("Customer: " + customerName);
        System.out.println("Units Consumed: " + units);
        System.out.println("Tax Amount: " + tax);
        System.out.println("Final Total: " + (total + tax));
    }
}

public class SmartPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter Customer Name (or Exit): ");
            String name = sc.nextLine();

            if (name.equalsIgnoreCase("Exit")) {
                break;
            }

            System.out.print("Previous Reading: ");
            int prev = sc.nextInt();

            System.out.print("Current Reading: ");
            int curr = sc.nextInt();
            sc.nextLine(); 

            UtilityBill bill = new UtilityBill(name, prev, curr);
            bill.generateBill();
        }

        sc.close();
    }
}