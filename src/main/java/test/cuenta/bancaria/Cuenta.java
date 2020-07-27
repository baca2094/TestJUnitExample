package test.cuenta.bancaria;

import java.util.Objects;

public class Cuenta {

    private int saldoCLP;
    private int saldoUSD;

    // Constructores
    public Cuenta() {
        this.saldoCLP = 1000000;
        this.saldoUSD = 1000;
    }

    public Cuenta(int saldoCLPInicial, int saldoUSDInicial) {
        this.saldoCLP = saldoCLPInicial;
        this.saldoUSD = saldoUSDInicial;
    }

    // Getters
    public int getSaldoCLP() {
        return saldoCLP;
    }

    public int getSaldoUSD() {
        return saldoUSD;
    }

    // Equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return saldoCLP == cuenta.saldoCLP &&
                saldoUSD == cuenta.saldoUSD;
    }

    @Override
    public int hashCode() {
        return Objects.hash(saldoCLP, saldoUSD);
    }

    // Methods
    public void deposit(int amount, String tipo) {


    }

    public void withdraw(int amount, String tipo) {

    }

}
