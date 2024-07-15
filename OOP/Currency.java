public abstract class Currency implements Exchangeable{
    String currencyName;
    double totalFunds;
    String planetName;

    public Currency(String currencyName, double totalFunds, String planetName) {
        this.currencyName = currencyName;
        this.totalFunds = totalFunds;
        this.planetName = planetName;
    }

    public abstract double toEarthDollars(double amount);
    public abstract double fromEarthDollars(double EarthDollars);


    public String getCurrencyName() {
        return currencyName;
    }

    public double getTotalFunds() {
        return totalFunds;
    }

    public String getPlanetName() {
        return planetName;
    }
}

