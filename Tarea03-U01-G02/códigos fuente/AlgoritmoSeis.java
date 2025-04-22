import java.util.Scanner;

public class AlgoritmoSeis {

    private static final String[] FILAS = { "*", "Q", "W", "E", "R", "T" };
    private static final String[] COLUMNAS = { "A", "S", "D", "F", "G" };
    private static final char[][] MATRIZ_CIFRADO = {
            { ' ', 'A', 'S', 'D', 'F', 'G' },
            { 'Q', 'a', 'b', 'c', 'd', 'e' },
            { 'W', 'f', 'g', 'h', 'i', 'j' },
            { 'E', 'k', 'l', 'm', 'n', 'o' },
            { 'R', 'p', 'q', 'r', 's', 't' },
            { 'T', 'u', 'v', 'x', 'y', 'z' }
    };
    private static final String CARACTER_NO_ENCONTRADO = "**";
    private static final String MENSAJE_INSTRUCCION = "Ingrese el mensaje a cifrar: ";
    private static final String MENSAJE_ADVERTENCIA = "¡Advertencia! No ha ingresado ningún mensaje. Por favor, intente nuevamente.";

    private static void ejecutarPrograma() {
        Scanner scanner = new Scanner(System.in);
        String mensaje = "";
        
        // Validar que se ingrese un mensaje
        while (mensaje.trim().isEmpty()) {
            System.out.print(MENSAJE_INSTRUCCION);
            mensaje = scanner.nextLine().toLowerCase();
            
            if (mensaje.trim().isEmpty()) {
                System.out.println(MENSAJE_ADVERTENCIA);
            }
        }

        String mensajeCifrado = cifrarMensaje(mensaje);
        mostrarResultados(mensaje, mensajeCifrado);
    }

    private static String cifrarMensaje(String mensaje) {
        StringBuilder cifrado = new StringBuilder();

        for (char caracter : mensaje.toCharArray()) {
            String coordenadas = obtenerCoordenadasCaracter(caracter);
            cifrado.append(coordenadas).append(" ");
        }

        return cifrado.toString().trim();
    }

    private static String obtenerCoordenadasCaracter(char caracter) {
        // Buscar en la matriz (empezamos desde 1 para saltar la fila/columna de teclas)
        for (int fila = 1; fila < MATRIZ_CIFRADO.length; fila++) {
            for (int col = 1; col < MATRIZ_CIFRADO[fila].length; col++) {
                if (MATRIZ_CIFRADO[fila][col] == caracter) {
                    // Usamos las teclas de fila y columna como coordenadas
                    return FILAS[fila] + COLUMNAS[col - 1];
                }
            }
        }

        return CARACTER_NO_ENCONTRADO;
    }

    private static void mostrarResultados(String mensajeOriginal, String mensajeCifrado) {
        mostrarMatrizCifrado();
        System.out.print("\n=== RESULTADOS DEL CIFRADO ===");
        System.out.println("\nMensaje original: " + mensajeOriginal);
        System.out.println("Mensaje cifrado: " + mensajeCifrado);
    }

    private static void mostrarMatrizCifrado() {
        System.out.println("\n=== MATRIZ DE CIFRADO ===");
        for (char[] fila : MATRIZ_CIFRADO) {
            for (char caracter : fila) {
                System.out.print(caracter + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            ejecutarPrograma();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}

