package test.cuenta.bancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    private Cuenta cuentaDePrueba;

    @BeforeEach
    void setUp() {
        cuentaDePrueba = new Cuenta();
    }

    @Test
    public void testInicializarCuenta() {
        // Given
        Cuenta cuentaBasica;
        Cuenta cuentaSaldoDistinto1;
        Cuenta cuentaSaldoDistinto2;

        // When
        cuentaBasica = new Cuenta();
        cuentaSaldoDistinto1 = new Cuenta(900000, 1000);
        cuentaSaldoDistinto2 = new Cuenta(1000000, 500);

        // Then
        assertEquals(cuentaDePrueba, cuentaBasica, "Falla al inicializar cuenta básica");
        assertNotEquals(cuentaDePrueba, cuentaSaldoDistinto1, "Falla al comparar cuenta saldo CLP distintos");
        assertNotEquals(cuentaDePrueba, cuentaSaldoDistinto2, "Falla al comparar cuenta saldo USD distintos");
    }

    @Test
    public void testGetSaldoCLP() {
        // Given
        int saldoInicialCLP = 1000000;
        int saldoInicialFalsoCLP = 500;

        // When
        int saldoCuenta = cuentaDePrueba.getSaldoCLP();

        // Then
        assertEquals(saldoInicialCLP, saldoCuenta, "Falla al obtener saldo esperado en CLP");
        assertNotEquals(saldoInicialFalsoCLP, saldoCuenta, "Falla al obtener saldo distinto en CLP");
    }

    @Test
    public void testGetSaldoUSD() {
        // Given
        int saldoInicialUSD = 1000;
        int saldoInicialFalsoUSD = 500;

        // When
        int saldoCuenta = cuentaDePrueba.getSaldoUSD();

        // Then
        assertEquals(saldoInicialUSD, saldoCuenta, "Falla al obtener saldo esperado en USD");
        assertNotEquals(saldoInicialFalsoUSD, saldoCuenta, "Falla al obtener saldo distinto en USD");
    }

    @Test
    public void testDeposit() {
        // Given

        // When

        // Then

    }

    @Test
    public void testWithdraw() {
        // Given

        // When

        // Then

    }
}