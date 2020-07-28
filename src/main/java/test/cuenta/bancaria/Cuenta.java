package test.cuenta.bancaria;

import java.util.ArrayList;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cuenta {

    private int saldoCLP;
    private int saldoUSD;
    private int numOperacionesSesion;
    private int numSesiones;
    private ArrayList<String> historialTransacciones;

    // Constructores
    public Cuenta() {
        this.saldoCLP = 1000000;
        this.saldoUSD = 1000;
        this.numOperacionesSesion = 0;
        this.numSesiones = 0;
        this.historialTransacciones = new ArrayList<String>();
        this.agregarAHistorial(0);
    }

    public Cuenta(int saldoCLPInicial, int saldoUSDInicial) {
        this.saldoCLP = saldoCLPInicial;
        this.saldoUSD = saldoUSDInicial;
        this.numOperacionesSesion = 0;
        this.numSesiones = 0;
        this.historialTransacciones = new ArrayList<String>();
        this.agregarAHistorial(0);
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

    public int getNumSesiones() {
        return numSesiones;
    }

    public ArrayList<String> getHistorialTransacciones() {
        return historialTransacciones;
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
    // Retorna:
    // true = operación fue exitosa
    // false = no se pudo realizar la operación
    public boolean deposit(int amount, boolean cuentaEnUSD) {
        boolean success = false;
        // Monto negativo
        if (amount < 0) {
            return success;
        }
        // Overflow depósito
        try {
            // Depositar según tipo de cuenta
            if (cuentaEnUSD) {
                this.saldoUSD = Math.addExact(this.saldoUSD, amount);
            }
            else {
                this.saldoCLP = Math.addExact(this.saldoCLP, amount);
            }
            success = true;
        }
        catch (ArithmeticException e) {
            return success;
        }
        // Operación exitosa
        return success;
    }

    // Retiro
    // cuentaEnUSD = true => cuenta en USD
    // cuentaEnUSD = false => cuenta en CLP
    // Retorna:
    // true = operación fue exitosa
    // false = no se pudo realizar la operación
    public boolean withdraw(int amount, boolean cuentaEnUSD) {
        boolean success = false;
        // Monto negativo
        if (amount < 0) {
            return success;
        }
        if (cuentaEnUSD) {
            if (amount > 100) {
                return success;
            }
            this.saldoUSD -= amount;
        }
        else {
            if (amount > 200000) {
                return success;
            }
            this.saldoCLP -= amount;
        }
        // Operación exitosa
        success = true;
        return success;
    }

    // Manejo de sesiones
    public void resetNumOperacionesSesion() {
        this.numOperacionesSesion = 0;
    }

    public boolean incrementNumOperacionesSesion() {
        if (this.numOperacionesSesion == 4) {
            return false;
        }
        this.numOperacionesSesion++;
        return true;
    }

    public void resetNumSesiones() {
        this.numSesiones = 0;
    }

    public boolean incrementNumSesiones() {
        if (this.numSesiones == 3) {
            return false;
        }
        this.numSesiones++;
        return true;
    }

    // Historial de transacciones

    // Según tipo de transacción:
    // 0: Creación
    // 1: Depósito
    // -1: Retiro
    public void agregarAHistorial(int tipoTransaccion) {
        String dateTimeActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS"));
        dateTimeActual = "["+dateTimeActual+"]";
        System.out.println(dateTimeActual);
        switch (tipoTransaccion) {
            // Creación cuenta
            case (0): {

            }
            // Depósito
            case (1): {

            }
            // Retiro
            case (-1): {

            }
        }
    }
}
