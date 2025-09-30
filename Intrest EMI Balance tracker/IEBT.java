import java.util.*;

// Loan class: holds loan data + calculations
class Loan {
    private double principal;
    private double annualRate;
    private double timeYears;
    private int months;

    // Constructor
    public Loan(double principal, double annualRate, double timeYears, int months) {
        this.principal = principal;
        this.annualRate = annualRate;
        this.timeYears = timeYears;
        this.months = months;
    }

    // Getters
    public double getPrincipal() { return principal; }
    public double getAnnualRate() { return annualRate; }
    public double getTimeYears() { return timeYears; }
    public int getMonths() { return months; }

    // Simple Interest
    public double calculateSimpleInterest() {
        return (principal * annualRate * timeYears) / 100;
    }

    // Compound Interest
    public double calculateCompoundInterest() {
        return principal * Math.pow((1 + annualRate / 100), timeYears) - principal;
    }

    // EMI
    public double calculateEMI() {
        double monthlyRate = (annualRate / 100) / 12;
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, months))
                / (Math.pow(1 + monthlyRate, months) - 1);
    }

    // Remaining Balance
    public double remainingBalance(int paymentsMade) {
        double emi = calculateEMI();
        double monthlyRate = (annualRate / 100) / 12;
        return principal * Math.pow(1 + monthlyRate, paymentsMade)
                - emi * ((Math.pow(1 + monthlyRate, paymentsMade) - 1) / monthlyRate);
    }
}

public class LoanCalculatorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Loan> loans = new ArrayList<>(); // Data structure

        System.out.println("===== Loan Calculator =====");
        System.out.print("Enter Loan Amount: ");
        double principal = sc.nextDouble();
        System.out.print("Enter Interest Rate (% per year): ");
        double rate = sc.nextDouble();
        System.out.print("Enter Loan Time (in years): ");
        double time = sc.nextDouble();
        System.out.print("Enter Loan Period (in months): ");
        int months = sc.nextInt();

        // Create Loan object and store in list
        Loan loan = new Loan(principal, rate, time, months);
        loans.add(loan);

        for (Loan l : loans) {
            double si = l.calculateSimpleInterest();
            System.out.println("\nSimple Interest = " + si);
            System.out.println("Total to Pay with Simple Interest = " + (l.getPrincipal() + si));

            double ci = l.calculateCompoundInterest();
            System.out.println("\n Compound Interest = " + ci);
            System.out.println(" Total to Pay with Compound Interest = " + (l.getPrincipal() + ci));

            double emi = l.calculateEMI();
            System.out.printf("\n Monthly EMI = %.2f\n", emi);
            System.out.printf("Total Payment (using EMI) = %.1f\n", emi * l.getMonths());
            System.out.printf(" Extra Money Paid as Interest = %.1f\n", (emi * l.getMonths()) - l.getPrincipal());

            System.out.print("\nEnter how many months you already paid: ");
            int paid = sc.nextInt();
            double balance = l.remainingBalance(paid);
            System.out.printf("Remaining Loan after %d months = %.2f\n", paid, balance);
        }

        System.out.println("\n===== Thank You!=====");
        sc.close();
    }
}       this is my program and referrence ppt