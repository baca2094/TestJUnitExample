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

        // When

        // Then

    }

    @Test
    public void testGetSaldoUSD() {
        // Given

        // When

        // Then

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