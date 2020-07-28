package test.cuenta.bancaria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Condiciones iniciales
        Cuenta cuenta = new Cuenta();
        Scanner inputScanner = new Scanner(System.in);

        // Pantalla
        while (true) {
            System.out.println("\nBienvenido a Banco Azul, selecciona operación a realizar:");
            // Opciones
            System.out.println("\"1\": Depósito");
            System.out.println("\"2\": Retiro");
            System.out.println("\"3\": Ver transacciones");
            System.out.println("\"4\": Cerrar sesión");

            // Leer input
            String input = inputScanner.nextLine().trim();
            int intInput;
            try {
                intInput = Integer.parseInt(input);
            }
            catch (Exception e) {
                System.out.println("Opción inválida. Por favor inténtelo nuevamente.");
                continue;
            }

            switch (intInput) {
                case (1): {
                    System.out.println("Ingrese Monto a depositar y moneda, formato \'Currency Amount\'");
                    break;
                }
                case (2): {
                    System.out.println("Ingrese Monto a retirar y moneda, formato \'Currency Amount\'");
                    break;
                }
                case (3): {
                    cuenta.imprimirHistorial();
                    break;
                }
                case (4): {
                    System.out.println("Se ha cerrado la sesión, para volver a iniciar sesión presione \"1\"");
                    break;
                }
            }
            System.out.println("Volviendo a la pantalla principal");
        }
    }
}
