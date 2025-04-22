import java.util.Scanner;

/**
 * Algoritmo de cifrado por permutación de columnas (transposición invertida).
 * Se toma como clave el número de columnas (n) de una matriz cuadrada.
 * El mensaje (puede contener espacios) se coloca columna por columna en la matriz,
 * y los espacios sobrantes se completan con '*'.
 * El mensaje cifrado se obtiene leyendo la matriz fila por fila.
 */
public class CifradoPermutacionColumnas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;

        // 1) Lectura de la clave (número de columnas) con validación
        while (true) {
            System.out.print("Ingrese el número de columnas (clave): ");
            String linea = sc.nextLine();
            if (linea.isEmpty()) {
                System.out.println("Error: no ingresó ningún valor. Intente de nuevo.");
                continue;
            }
            try {
                n = Integer.parseInt(linea);
                if (n <= 0) {
                    System.out.println("Error: el número debe ser un entero positivo mayor que cero.");
                } else {
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error: valor no es un número entero válido. Intente de nuevo.");
            }
        }

        String mensaje;
        String mensajeSinEspacios;

        // 2) Lectura del mensaje original con validación
        while (true) {
            System.out.print("Ingrese el mensaje a cifrar: ");
            mensaje = sc.nextLine();
            if (mensaje.isEmpty()) {
                System.out.println("Error: mensaje vacío. Intente de nuevo.");
                continue;
            }
            // 3) Eliminación de espacios para el proceso de cifrado
            mensajeSinEspacios = mensaje.replace(" ", "");
            // 4) Validación: el texto (sin espacios) no debe exceder n * n
            if (mensajeSinEspacios.length() > n * n) {
                System.out.printf(
                    "Error: la longitud del mensaje sin espacios (%d) excede n x n (%d).\n",
                    mensajeSinEspacios.length(), n * n
                );
                System.out.println("Intente con un mensaje más corto.");
            } else {
                break;
            }
        }

        // 5) Creación de la matriz cuadrada de cifrado (columna por columna)
        char[][] matriz = new char[n][n];
        int idx = 0;
        for (int col = 0; col < n; col++) {
            for (int fila = 0; fila < n; fila++) {
                if (idx < mensajeSinEspacios.length()) {
                    matriz[fila][col] = mensajeSinEspacios.charAt(idx++);
                } else {
                    matriz[fila][col] = '*';
                }
            }
        }

        // 6) Impresión de la matriz de cifrado
        System.out.println("\nMatriz de cifrado:");
        for (int fila = 0; fila < n; fila++) {
            for (int col = 0; col < n; col++) {
                System.out.print(matriz[fila][col] + " ");
            }
            System.out.println();
        }

        // 7) Construcción del mensaje cifrado leyendo fila por fila
        StringBuilder cifrado = new StringBuilder();
        for (int fila = 0; fila < n; fila++) {
            for (int col = 0; col < n; col++) {
                cifrado.append(matriz[fila][col]);
            }
        }

        // 8) Mostrar resultados finales
        System.out.println("\nMensaje original: " + mensaje);
        System.out.println("Mensaje cifrado: " + cifrado.toString());

        sc.close();
    }
}