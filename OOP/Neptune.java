public class Neptune extends Currency implements Exchangeable {
    public Neptune(double totalFunds) {
        super("NeptuneNuggets", totalFunds, "Neptune");
    }

    @Override
    public double toEarthDollars(double amount) {
        return amount / ED_NN;
    }

    @Override
    public double fromEarthDollars(double EarthDollars) {
        return EarthDollars * ED_NN;
    }

    @Override
    public void exchange(Currency other, double amount) {
        double USD = this.toEarthDollars(amount);
        double fee = 5.0; 

        if (totalFunds >= amount + fee) {
            totalFunds -= (amount + fee);
            double converted_fund = other.fromEarthDollars(USD);
            other.totalFunds += converted_fund;
            System.out.printf("Converting from %s to %s and initiating transfer…",this.getCurrencyName(), other.getCurrencyName());
            System.out.println();
            System.out.printf("%.2f NeptuneNuggets = %.2f EarthDollars = %.2f %s", amount, USD, converted_fund, other.getCurrencyName());
            System.out.println();
            System.out.printf("Neptune exchange fee is 5.00");
            System.out.printf("Neptune has a total of %.2f NeptuneNuggets%n", totalFunds);
            System.out.printf("%s has a total of %.2f %s%n", other.getPlanetName(), other.getTotalFunds(), other.getCurrencyName());
            System.out.println();
        } else {
            System.out.println("Uh oh :( -");
            System.out.println("Uh oh :( - " + this.getCurrencyName() + " only has an available balance of " + String.format("%.2f", this.getTotalFunds()) + ", which is less than " + String.format("%.2f", (amount+fee)) + "!");
            return;
        }
    }
}
