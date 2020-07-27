package test.cuenta.bancaria;

public class Cuenta {

    private int saldoCLP;
    private int saldoUSD;

    // Constructor
    public Cuenta() {
        this.saldoCLP = 1000000;
        this.saldoUSD = 1000;
    }

    // Getters and setters
    public int getSaldoCLP() {
        return saldoCLP;
    }

    private void setSaldoCLP(int saldoCLP) {
        this.saldoCLP = saldoCLP;
    }

    public int getSaldoUSD() {
        return saldoUSD;
    }

    private void setSaldoUSD(int saldoUSD) {
        this.saldoUSD = saldoUSD;
    }

    // Methods
    public void deposit(int amount) {

    }

    public void withdraw(int amount) {

    }

}
