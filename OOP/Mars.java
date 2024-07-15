public class Mars extends Currency implements Exchangeable {
    
    public Mars(double totalFunds) {
        super("MarsMoney", totalFunds, "Mars");
    }

    @Override
    public double toEarthDollars(double amount) {
        return amount / ED_MM;
    }

    @Override
    public double fromEarthDollars(double EarthDollars) {
        return EarthDollars * ED_MM;
    }

    @Override
    public void exchange(Currency other, double amount) {

        double USD = this.toEarthDollars(amount);
        double fee = amount * 0.10;

        if (totalFunds >= amount + fee) {
            totalFunds -= (amount + fee);
            double converted_fund = other.fromEarthDollars(USD);
            other.totalFunds += converted_fund;
            System.out.printf("Converting from %s to %s and initiating transfer…",this.getCurrencyName(), other.getCurrencyName());
            System.out.println();
            System.out.printf("%.2f MarsMoney = %.2f EarthDollars = %.2f %s", amount, USD, converted_fund, other.getCurrencyName());
            System.out.println();
            System.out.printf("Mars exchange fee is %.2f MarsMoney%n", fee);
            System.out.printf("Mars has a total of %.2f MarsMoney%n", totalFunds);
            System.out.printf("%s has a total of %.2f %s%n", other.getPlanetName(), other.getTotalFunds(), other.getCurrencyName());
            System.out.println();
        } else {
            System.out.println("Uh oh :( - " + this.getCurrencyName() + " only has an available balance of " + String.format("%.2f", this.getTotalFunds()) + ", which is less than " + String.format("%.2f", (amount+fee)) + "!");
            
            System.out.println();
            return;
            
        }
    }
}
