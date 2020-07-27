package test.cuenta.bancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    private Cuenta cuentaDePrueba;
    private Cuenta cuentaVacia;

    @BeforeEach
    void setUp() {
        cuentaDePrueba = new Cuenta();
        cuentaVacia = new Cuenta(0, 0);
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
    public void testGetNumTransaccionesSesion() {
        // Given

        // When
        int nTransacciones = cuentaDePrueba.getNumTransaccionesSesion();

        // Then
        assertEquals(0, nTransacciones);
    }

    @Test
    public void testDepositSuccessCLP() {
        // Given
        int montoADepositar = 1000000;
        int saldoInicialCLP = cuentaDePrueba.getSaldoCLP();
        int saldoEsperadoCLP = saldoInicialCLP+montoADepositar;

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
        int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

        // Then
        assertEquals(saldoEsperadoCLP, saldoFinalCLP, "Falla saldo al depositar");
        assertEquals(true, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositSuccessUSD() {
        // Given
        int montoADepositar = 1000000;
        int saldoInicialUSD = cuentaDePrueba.getSaldoUSD();
        int saldoEsperadoUSD = saldoInicialUSD+montoADepositar;

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, true);
        int saldoFinalUSD = cuentaDePrueba.getSaldoCLP();

        // Then
        assertEquals(saldoEsperadoUSD, saldoFinalUSD, "Falla saldo al depositar");
        assertEquals(true, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositMinAmountCLP() {
        // Given
        int saldoInicialCLP = cuentaDePrueba.getSaldoCLP();
        int montoADepositar = 0;

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
        int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

        // Then
        assertEquals(saldoInicialCLP, saldoFinalCLP, "Monto cambia al depositar saldo en CLP igual a 0");
        assertEquals(true, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositMinAmountUSD() {
        // Given
        int saldoInicialUSD = cuentaDePrueba.getSaldoUSD();
        int montoADepositar = 0;

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, true);
        int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

        // Then
        assertEquals(saldoInicialUSD, saldoFinalUSD, "Monto cambia al depositar saldo en USD igual a 0");
        assertEquals(true, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositBelowMinAmountCLP() {
        // Given
        int montoADepositar = -1;
        int saldoInicialCLP = cuentaDePrueba.getSaldoCLP();
        int saldoEsperadoFalsoCLP = saldoInicialCLP+montoADepositar;

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
        int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

        // Then
        assertEquals(saldoInicialCLP, saldoFinalCLP, "Monto cambia al depositar saldo en CLP menor a 0");
        assertNotEquals(saldoEsperadoFalsoCLP, saldoFinalCLP, "Monto baja al depositar saldo en CLP menor a 0");
        assertEquals(false, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositBelowMinAmountUSD() {
        // Given
        int montoADepositar = -1;
        int saldoInicialUSD = cuentaDePrueba.getSaldoUSD();
        int saldoEsperadoFalsoUSD = saldoInicialUSD+montoADepositar;

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, true);
        int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

        // Then
        assertEquals(saldoInicialUSD, saldoFinalUSD, "Monto cambia al depositar saldo en USD menor a 0");
        assertNotEquals(saldoEsperadoFalsoUSD, saldoFinalUSD, "Monto baja al depositar saldo en USD menor a 0");
        assertEquals(false, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositMaxAmountCLPconEmptyAccount() {
        // Given
        int montoADepositar = Integer.MAX_VALUE;
        int saldoInicialCLP = cuentaVacia.getSaldoCLP();
        int saldoEsperadoCLP = saldoInicialCLP+montoADepositar;

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
        int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

        // Then
        assertEquals(saldoEsperadoCLP, saldoFinalCLP, "No se deposita el monto máximo en CLP (en cuenta vacía)");
        assertEquals(true, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositMaxAmountCLP() {
        // Given
        int montoADepositar = Integer.MAX_VALUE;
        int saldoInicialCLP = cuentaVacia.getSaldoCLP();

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
        int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

        // Then
        assertEquals(saldoInicialCLP, saldoFinalCLP, "Cambia el monto al depositar monto máximo en cuenta no vacía");
        assertEquals(false, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositMaxAmountUSDconEmptyAccount() {
        // Given
        int montoADepositar = Integer.MAX_VALUE;
        int saldoInicialUSD = cuentaVacia.getSaldoUSD();
        int saldoEsperadoUSD = saldoInicialUSD+montoADepositar;

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
        int saldoFinalUSD = cuentaDePrueba.getSaldoCLP();

        // Then
        assertEquals(saldoEsperadoUSD, saldoFinalUSD, "No se deposita el monto máximo en USD (en cuenta vacía)");
        assertEquals(true, resultado, "Resultado inesperado al depositar");
    }

    @Test
    public void testDepositMaxAmountUSD() {
        // Given
        int montoADepositar = Integer.MAX_VALUE;
        int saldoInicialUSD = cuentaVacia.getSaldoUSD();

        // When
        boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
        int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

        // Then
        assertEquals(saldoInicialUSD, saldoFinalUSD, "Cambia el monto al depositar monto máximo en cuenta no vacía");
        assertEquals(false, resultado, "Resultado inesperado al depositar");
    }




    @Test
    public void testWithdraw() {
        // Given

        // When

        // Then

    }
}