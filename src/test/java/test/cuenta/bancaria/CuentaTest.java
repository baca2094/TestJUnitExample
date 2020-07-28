package test.cuenta.bancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    private Cuenta cuentaDePrueba;
    private Cuenta cuentaVacia;
    private int saldoInicialCLP;
    private int saldoInicialUSD;


    @BeforeEach
    void setUp() {
        cuentaDePrueba = new Cuenta();
        cuentaVacia = new Cuenta(0, 0);
        saldoInicialCLP = 1000000;
        saldoInicialUSD = 1000;
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

    @Nested
    @DisplayName("Test Getters Cuenta")
    class testGetter {

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
        public void testGetNumOperacionesSesion() {
            // Given

            // When
            int nTransacciones = cuentaDePrueba.getNumOperacionesSesion();

            // Then
            assertEquals(0, nTransacciones);
        }

        @Test
        public void testGetNumSesiones() {
            // Given

            // When
            int nSesiones = cuentaDePrueba.getNumSesiones();
            // Then
            assertEquals(0, nSesiones);
        }
    }

    @Nested
    @DisplayName("Tests Depositos en Cuenta")
    class testDeposit {
        @Test
        public void testDepositSuccessCLP() {
            // Given
            int montoADepositar = 5;
            int saldoEsperadoCLP = saldoInicialCLP + montoADepositar;

            // When
            boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoEsperadoCLP, saldoFinalCLP, "Falla saldo al depositar");
            assertTrue(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositSuccessUSD() {
            // Given
            int montoADepositar = 1000000;
            int saldoEsperadoUSD = saldoInicialUSD + montoADepositar;

            // When
            boolean resultado = cuentaDePrueba.deposit(montoADepositar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoEsperadoUSD, saldoFinalUSD, "Falla saldo al depositar");
            assertTrue(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositMinAmountCLP() {
            // Given
            int montoADepositar = 0;

            // When
            boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoInicialCLP, saldoFinalCLP, "Monto cambia al depositar saldo en CLP igual a 0");
            assertTrue(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositMinAmountUSD() {
            // Given
            int montoADepositar = 0;

            // When
            boolean resultado = cuentaDePrueba.deposit(montoADepositar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoInicialUSD, saldoFinalUSD, "Monto cambia al depositar saldo en USD igual a 0");
            assertTrue(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositBelowMinAmountCLP() {
            // Given
            int montoADepositar = -1;
            int saldoEsperadoFalsoCLP = saldoInicialCLP + montoADepositar;

            // When
            boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoInicialCLP, saldoFinalCLP, "Monto cambia al depositar saldo en CLP menor a 0");
            assertNotEquals(saldoEsperadoFalsoCLP, saldoFinalCLP, "Monto baja al depositar saldo en CLP menor a 0");
            assertFalse(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositBelowMinAmountUSD() {
            // Given
            int montoADepositar = -1;
            int saldoEsperadoFalsoUSD = saldoInicialUSD + montoADepositar;

            // When
            boolean resultado = cuentaDePrueba.deposit(montoADepositar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoInicialUSD, saldoFinalUSD, "Monto cambia al depositar saldo en USD menor a 0");
            assertNotEquals(saldoEsperadoFalsoUSD, saldoFinalUSD, "Monto baja al depositar saldo en USD menor a 0");
            assertFalse(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositMaxAmountCLP() {
            // Given
            int montoADepositar = Integer.MAX_VALUE;

            // When
            boolean resultado = cuentaVacia.deposit(montoADepositar, false);
            int saldoFinalCLP = cuentaVacia.getSaldoCLP();

            // Then
            assertEquals(montoADepositar, saldoFinalCLP, "No se deposita el monto máximo en CLP (en cuenta vacía)");
            assertTrue(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositMaxAmountUSD() {
            // Given
            int montoADepositar = Integer.MAX_VALUE;

            // When
            boolean resultado = cuentaVacia.deposit(montoADepositar, true);
            int saldoFinalUSD = cuentaVacia.getSaldoUSD();

            // Then
            assertEquals(montoADepositar, saldoFinalUSD, "No se deposita el monto máximo en USD (en cuenta vacía)");
            assertTrue(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositSaldoOverflowCLP() {
            // Given
            int montoADepositar = Integer.MAX_VALUE;

            // When
            boolean resultado = cuentaDePrueba.deposit(montoADepositar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoInicialCLP, saldoFinalCLP, "Cambia el monto al intentar depositar más de lo permitido");
            assertFalse(resultado, "Resultado inesperado al depositar");
        }

        @Test
        public void testDepositSaldoOverflowUSD() {
            // Given
            int montoADepositar = Integer.MAX_VALUE;

            // When
            boolean resultado = cuentaDePrueba.deposit(montoADepositar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoInicialUSD, saldoFinalUSD, "Cambia el monto al intentar depositar más de lo permitido");
            assertFalse(resultado, "Resultado inesperado al depositar");
        }
    }

    @Nested
    @DisplayName("Tests Retiros de Cuenta")
    class testWithdraw {
        @Test
        public void testWithdrawSuccessCLP() {
            // Given
            int montoASacar = 500;
            int saldoEsperadoCLP = saldoInicialCLP - montoASacar;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoASacar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoEsperadoCLP, saldoFinalCLP, "Falla saldo al retirar");
            assertTrue(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawSuccessUSD() {
            // Given
            int montoASacar = 10;
            int saldoEsperadoUSD = saldoInicialUSD - montoASacar;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoASacar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoEsperadoUSD, saldoFinalUSD, "Falla saldo al retirar");
            assertTrue(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawMinAmountCLP() {
            // Given
            int montoARetirar = 0;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoInicialCLP, saldoFinalCLP, "Monto cambia al retirar saldo en CLP igual a 0");
            assertTrue(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawMinAmountUSD() {
            // Given
            int montoARetirar = 0;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoInicialUSD, saldoFinalUSD, "Monto cambia al retirar saldo en USD igual a 0");
            assertTrue(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawBelowMinAmountCLP() {
            // Given
            int montoARetirar = -1;
            int saldoEsperadoFalsoCLP = saldoInicialCLP - montoARetirar;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoInicialCLP, saldoFinalCLP, "Monto cambia al retirar saldo en CLP menor a 0");
            assertNotEquals(saldoEsperadoFalsoCLP, saldoFinalCLP, "Monto baja al retirar saldo en CLP menor a 0");
            assertFalse(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawBelowMinAmountUSD() {
            // Given
            int montoARetirar = -1;
            int saldoEsperadoFalsoUSD = saldoInicialUSD - montoARetirar;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoInicialUSD, saldoFinalUSD, "Monto cambia al retirar saldo en USD menor a 0");
            assertNotEquals(saldoEsperadoFalsoUSD, saldoFinalUSD, "Monto baja al retirar saldo en USD menor a 0");
            assertFalse(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawSaldoCLP() {
            // Given
            Cuenta cuentaConSaldoMenorMaxRetiro = new Cuenta(10, 10);
            int montoARetirar = 10;
            int saldoEsperadoCLP = 0;

            // When
            boolean resultado = cuentaConSaldoMenorMaxRetiro.withdraw(montoARetirar, false);
            int saldoFinalCLP = cuentaConSaldoMenorMaxRetiro.getSaldoCLP();

            // Then
            assertEquals(saldoEsperadoCLP, saldoFinalCLP, "No se retira el saldo completo");
            assertTrue(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawSaldoUSD() {
            // Given
            Cuenta cuentaConSaldoMenorMaxRetiro = new Cuenta(10, 10);
            int montoARetirar = 10;
            int saldoEsperadoUSD = 0;

            // When
            boolean resultado = cuentaConSaldoMenorMaxRetiro.withdraw(montoARetirar, true);
            int saldoFinalUSD = cuentaConSaldoMenorMaxRetiro.getSaldoUSD();

            // Then
            assertEquals(saldoEsperadoUSD, saldoFinalUSD, "No se retira el saldo completo");
            assertTrue(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawAboveSaldoCLP() {
            // Given
            int montoARetirar = saldoInicialCLP + 1;
            int saldoEsperadoCLP = saldoInicialCLP;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoEsperadoCLP, saldoFinalCLP, "El saldo cambia");
            assertFalse(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawAboveSaldoUSD() {
            // Given
            int montoARetirar = saldoInicialUSD + 1;
            int saldoEsperadoUSD = saldoInicialUSD;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoEsperadoUSD, saldoFinalUSD, "El saldo cambia");
            assertFalse(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawMaxAmountCLP() {
            // Given
            int montoARetirar = 200000;
            int saldoEsperadoCLP = 800000;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoEsperadoCLP, saldoFinalCLP, "No se retira el monto máximo");
            assertTrue(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawMaxAmountUSD() {
            // Given
            int montoARetirar = 100;
            int saldoEsperadoUSD = 900;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoEsperadoUSD, saldoFinalUSD, "No se retira el monto máximo");
            assertTrue(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawAboveMaxAmountCLP() {
            // Given
            int montoARetirar = 200001;
            int saldoEsperadoCLP = saldoInicialCLP;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, false);
            int saldoFinalCLP = cuentaDePrueba.getSaldoCLP();

            // Then
            assertEquals(saldoEsperadoCLP, saldoFinalCLP, "El saldo cambia");
            assertFalse(resultado, "Resultado inesperado al retirar");
        }

        @Test
        public void testWithdrawAboveMaxAmountUSD() {
            // Given
            int montoARetirar = 101;
            int saldoEsperadoUSD = saldoInicialUSD;

            // When
            boolean resultado = cuentaDePrueba.withdraw(montoARetirar, true);
            int saldoFinalUSD = cuentaDePrueba.getSaldoUSD();

            // Then
            assertEquals(saldoEsperadoUSD, saldoFinalUSD, "El saldo cambia");
            assertFalse(resultado, "Resultado inesperado al retirar");
        }
    }

    @Nested
    @DisplayName("Tests Manejo de Sesión")
    class testSessionOperations {
        @Test
        public void testResetNumOperacionesSesion() {
            // Given

            // When
            cuentaDePrueba.incrementNumOperacionesSesion();
            cuentaDePrueba.resetNumOperacionesSesion();

            // Then
            assertEquals(0, cuentaDePrueba.getNumOperacionesSesion());
        }

        @Test
        public void testIncrementNumOperacionesSesion() {
            // Given

            // When
            boolean resultado = cuentaDePrueba.incrementNumOperacionesSesion();

            // Then
            assertEquals(1, cuentaDePrueba.getNumOperacionesSesion(), "No se incrementó el número");
            assertTrue(resultado, "El resultado fue inesperado");
        }

        @Test
        public void testIncrementNumOperacionesSesionWhenAtMax() {
            // Given

            // When
            cuentaDePrueba.incrementNumOperacionesSesion();
            cuentaDePrueba.incrementNumOperacionesSesion();
            cuentaDePrueba.incrementNumOperacionesSesion();
            cuentaDePrueba.incrementNumOperacionesSesion();
            boolean resultado = cuentaDePrueba.incrementNumOperacionesSesion();

            // Then
            assertEquals(4, cuentaDePrueba.getNumOperacionesSesion(), "Se incrementó el número de operaciones");
            assertFalse(resultado);
        }

        @Test
        public void testResetNumSesiones() {
            // Given

            // When
            cuentaDePrueba.incrementNumSesiones();
            cuentaDePrueba.resetNumSesiones();

            // Then
            assertEquals(0, cuentaDePrueba.getNumSesiones());
        }

        @Test
        public void testIncrementNumSesiones() {
            // Given

            // When
            boolean resultado = cuentaDePrueba.incrementNumSesiones();

            // Then
            assertEquals(1, cuentaDePrueba.getNumSesiones(), "No se incrementó el número");
            assertTrue(resultado, "El resultado fue inesperado");
        }

        @Test
        public void testIncrementNumSesionesWhenAtMax() {
            // Given

            // When
            cuentaDePrueba.incrementNumSesiones();
            cuentaDePrueba.incrementNumSesiones();
            cuentaDePrueba.incrementNumSesiones();
            boolean resultado = cuentaDePrueba.incrementNumSesiones();

            // Then
            assertEquals(3, cuentaDePrueba.getNumSesiones(), "Se incrementó el número de sesiones");
            assertFalse(resultado);
        }
    }

    @Nested
    @DisplayName("Tests Historial de Transacciones")
    class testHistorial {
        @Test
        public void testGetHistorialTransaccionesVacio() {
            // Given
            String esperado = "La cuenta ha sido creada. Saldo inicial: CLP 1000000 USD 1000";

            // When
            ArrayList<String> listaObtenida = cuentaDePrueba.getHistorialTransacciones();
            String lastTransaccion = listaObtenida.get(listaObtenida.size() - 1);

            // Then
            assertTrue(lastTransaccion.contains(esperado));
        }

        @Test
        public void testGetHistorialTransaccionesDeposito() {
            // Given
            String esperado = "Realizado depósito de USD 100. Saldo actual: CLP 1000000 USD 1000";

            // When
            cuentaDePrueba.agregarAHistorial(1, 0, 100);
            ArrayList<String> listaObtenida = cuentaDePrueba.getHistorialTransacciones();
            String lastTransaccion = listaObtenida.get(listaObtenida.size() - 1);
            System.out.println(lastTransaccion);

            // Then
            assertTrue(lastTransaccion.contains(esperado));
        }

        @Test
        public void testGetHistorialTransaccionesRetiro() {
            // Given
            String esperado = "Realizado retiro de CLP 100. Saldo actual: CLP 1000000 USD 1000";

            // When
            cuentaDePrueba.agregarAHistorial(-1, 1, 100);
            ArrayList<String> listaObtenida = cuentaDePrueba.getHistorialTransacciones();
            String lastTransaccion = listaObtenida.get(listaObtenida.size() - 1);
            System.out.println(lastTransaccion);

            // Then
            assertTrue(lastTransaccion.contains(esperado));
        }

        @Test
        public void testImprimirHistorial() {
            // Given

            // When

            // Then
        }

        @Test
        public void testAgregarAHistorial() {
            // Given

            // When

            // Then

        }
    }
}