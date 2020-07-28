package test.cuenta.bancaria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Condiciones iniciales
        Cuenta cuenta = new Cuenta();
        Scanner inputScanner = new Scanner(System.in);
        String input;
        boolean cerrarSesion;
        boolean opcionValida;
        // Pantalla
        while (true) {
            cerrarSesion = false;
            opcionValida = false;
            System.out.println("\nBienvenido a Banco Azul, selecciona operación a realizar:");
            // Opciones
            System.out.println("\"1\": Depósito");
            System.out.println("\"2\": Retiro");
            System.out.println("\"3\": Ver transacciones");
            System.out.println("\"4\": Cerrar sesión");

            // Leer input
            input = inputScanner.nextLine().trim();
            int intInput;
            try {
                intInput = Integer.parseInt(input);
            }
            catch (Exception e) {
                System.out.println("Opción inválida. Por favor inténtelo nuevamente.");
                continue;
            }

            // Depósito
            if (intInput == 1) {
                opcionValida = true;
                while (true) {
                    System.out.println("Ingrese Monto a depositar y moneda, formato (Currency Amount)");
                    input = inputScanner.nextLine().trim();
                    String[] inputs = input.split(" ");
                    String currency = inputs[0];
                    String stringAmount = inputs[1];
                    int amount;
                    // Validar monto
                    try {
                        amount = Integer.parseInt(stringAmount);
                    }
                    catch (Exception e) {
                        System.out.println("Monto inválido. Por favor inténtelo nuevamente.");
                        continue;
                    }
                    // Validar currency
                    boolean cuentaEnUSD;
                    int tipoCuenta;
                    if (currency.equals("CLP")) {
                        cuentaEnUSD = false;
                        tipoCuenta = 1;
                    }
                    else if (currency.equals("USD")) {
                        cuentaEnUSD = true;
                        tipoCuenta = 0;
                    }
                    else {
                        System.out.println("Moneda inválida. Por favor inténtelo nuevamente.");
                        continue;
                    }

                    // Depositar
                    boolean resultado = cuenta.deposit(amount, cuentaEnUSD);
                    // Éxito transacción
                    if (resultado) {
                        cuenta.agregarAHistorial(1, tipoCuenta, amount);
                        boolean resultadoOperacion = cuenta.incrementNumOperacionesSesion();
                        if (!resultadoOperacion) {
                            System.out.println("No le quedan más operaciones, se ha cerrado la sesión.");
                            cuenta.resetNumOperacionesSesion();
                            cerrarSesion = true;
                        }
                    }
                    // Fallo
                    else {
                        System.out.println("No es posible depositar ese monto. Por favor inténtelo nuevamente.");
                        continue;
                    }
                    break;
                }
            }
            // Retiro
            if (intInput == 2) {
                opcionValida = true;
                while (true) {
                    System.out.println("Ingrese Monto a retirar y moneda, formato (Currency Amount)");
                    input = inputScanner.nextLine().trim();
                    String[] inputs = input.split(" ");
                    String currency = inputs[0];
                    String stringAmount = inputs[1];
                    int amount;
                    // Validar monto
                    try {
                        amount = Integer.parseInt(stringAmount);
                    }
                    catch (Exception e) {
                        System.out.println("Monto inválido. Por favor inténtelo nuevamente.");
                        continue;
                    }
                    // Validar currency
                    boolean cuentaEnUSD;
                    int tipoCuenta;
                    if (currency.equals("CLP")) {
                        cuentaEnUSD = false;
                        tipoCuenta = 1;
                    }
                    else if (currency.equals("USD")) {
                        cuentaEnUSD = true;
                        tipoCuenta = 0;
                    }
                    else {
                        System.out.println("Moneda inválida. Por favor inténtelo nuevamente.");
                        continue;
                    }

                    // Retirar
                    boolean resultado = cuenta.withdraw(amount, cuentaEnUSD);
                    // Éxito transacción
                    if (resultado) {
                        cuenta.agregarAHistorial(-1, tipoCuenta, amount);
                        boolean resultadoOperacion = cuenta.incrementNumOperacionesSesion();
                        if (!resultadoOperacion) {
                            System.out.println("No le quedan más operaciones, se ha cerrado la sesión.");
                            cuenta.resetNumOperacionesSesion();
                            cerrarSesion = true;
                        }
                    }
                    // Fallo
                    else {
                        System.out.println("No es posible retirar ese monto. Por favor inténtelo nuevamente.");
                        continue;
                    }
                    break;
                }
            }
            // Ver transacciones
            if (intInput == 3) {
                opcionValida = true;
                System.out.println("\n Historial de Transacciones:");
                cuenta.imprimirHistorial();
                System.out.println();
            }
            // Cerrar sesión
            if ((intInput == 4) || (cerrarSesion)) {
                opcionValida = true;
                // Terminar sesión
                boolean resultado = cuenta.incrementNumSesiones();
                // No hay más sesiones
                if (!resultado) {
                    System.out.println("Se ha cerrado la sesión, no le quedan más sesiones.");
                    System.out.println("Gracias por su visita, que tenga un buen día!");
                    System.exit(0);
                }

                // Recibir input
                System.out.println("Se ha cerrado la sesión, para volver a iniciar sesión " +
                        "presione \"1\" o para salir presione cualquier otra tecla");
                input = inputScanner.nextLine().trim();
                // Volver a iniciar sesión
                if (input.equals("1")) {
                    cuenta.resetNumOperacionesSesion();
                }
                // Salir
                else {
                    System.out.println("Gracias por su visita, que tenga un buen día!");
                    System.exit(0);
                }
            }
            // Error
            if (!opcionValida) {
                System.out.println("Opción inválida. Por favor inténtelo nuevamente.");
                continue;
            }
            System.out.println("Volviendo a la pantalla principal");
        }
    }
}