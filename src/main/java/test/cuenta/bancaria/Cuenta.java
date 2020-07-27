package test.cuenta.bancaria;

import java.util.Objects;

public class Cuenta {

    private int saldoCLP;
    private int saldoUSD;
    private int numOperacionesSesion;


    // Constructores
    public Cuenta() {
        this.saldoCLP = 1000000;
        this.saldoUSD = 1000;
        this.numOperacionesSesion = 0;
    }

    public Cuenta(int saldoCLPInicial, int saldoUSDInicial) {
        this.saldoCLP = saldoCLPInicial;
        this.saldoUSD = saldoUSDInicial;
        this.numOperacionesSesion = 0;
    }

    // Getters
    public int getSaldoCLP() {
        return saldoCLP;
    }

    public int getSaldoUSD() {
        return saldoUSD;
    }

    public int getNumOperacionesSesion() {
        return numOperacionesSesion;
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

    // Depósito
    // cuentaEnUSD = true => cuenta en USD
    // cuentaEnUSD = false => cuenta en CLP
    public boolean deposit(int amount, boolean cuentaEnUSD) {
        boolean success = false;
        if (cuentaEnUSD) {

        }
        else {

        }
        return success;
    }

    // Retiro
    // cuentaEnUSD = true => cuenta en USD
    // cuentaEnUSD = false => cuenta en CLP
    public boolean withdraw(int amount, boolean cuentaEnUSD) {
        boolean success = false;
        if (cuentaEnUSD) {

        }
        else {

        }
        return success;
    }

}
