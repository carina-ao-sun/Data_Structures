public interface Exchangeable {
    double ED_MM = 1.30;
    double ED_NN = 2.00;
    double ED_SS = 0.87;

    public void exchange(Currency other, double amount);
    

}
