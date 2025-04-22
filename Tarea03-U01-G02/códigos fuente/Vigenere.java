import java.util.Scanner;

public class Vigenere {

    static final int ALFABETO = 26;

    // Genera la key con mismo largo que el texto
    static String generarKey(String str, String key) {
        StringBuilder newKey = new StringBuilder(key);
        while (newKey.length() < str.length()) {
            newKey.append(key.charAt(newKey.length() % key.length()));
        }
        return newKey.toString();
    }

    // Cifra el texto usando la clave
    static String textCifrado(String str, String key) {
        StringBuilder cifrado = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int x = (str.charAt(i) + key.charAt(i)) % ALFABETO;
            x += 'A';
            cifrado.append((char) x);
        }
        return cifrado.toString();
    }

    // Descifra el texto cifrado con la clave
    static String textOriginal(String cifrado, String key) {
        StringBuilder original = new StringBuilder();
        for (int i = 0; i < cifrado.length(); i++) {
            int x = (cifrado.charAt(i) - key.charAt(i) + ALFABETO) % ALFABETO;
            x += 'A';
            original.append((char) x);
        }
        return original.toString();
    }

    // Convierte a mayúsculas
    static String lowerToUpper(String s) {
        return s.toUpperCase();
    }

    // Valida el mensaje (solo letras, sin espacios)
    static boolean mensajeValido(String mensaje) {
        return mensaje.matches("[a-zA-Z]+");
    }

    // Imprime la matriz de Vigenère
    static void imprimirMatrizVigenere() {
        System.out.println("\n MATRIZ DE VIGENÈRE:\n");
    
        // Encabezado superior
        System.out.print("    ");
        for (int i = 0; i < ALFABETO; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.println();
    
        // Línea separadora
        System.out.print("    ");
        for (int i = 0; i < ALFABETO; i++) {
            System.out.print("--");
        }
        System.out.println();
    
        // Filas con encabezado lateral
        for (int i = 0; i < ALFABETO; i++) {
            System.out.print((char) ('A' + i) + " | ");
            for (int j = 0; j < ALFABETO; j++) {
                char letra = (char) ((i + j) % ALFABETO + 'A');
                System.out.print(letra + " ");
            }
            System.out.println();
        }
    }    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String mensaje;

        while (true) {
            System.out.print("Ingrese el mensaje que desea cifrar (solo letras, sin espacios): ");
            mensaje = scanner.nextLine().trim();

            if (mensaje.isEmpty()) {
                System.out.println("Error: el mensaje está vacío. Intente de nuevo.\n");
                continue;
            }

            if (!mensajeValido(mensaje)) {
                System.out.println("Error: el mensaje debe contener solo letras sin espacios ni símbolos ni números. Intente de nuevo.\n");
                continue;
            }

            break; // mensaje válido
        }

        String claveFija = "CLAVE";
        String mensajeMayus = lowerToUpper(mensaje);
        String claveMayus = lowerToUpper(claveFija);

        String key = generarKey(mensajeMayus, claveMayus);
        String cifrado = textCifrado(mensajeMayus, key);
        String descifrado = textOriginal(cifrado, key);

        System.out.println("\n🔐 Resultados:");
        System.out.println("Mensaje Original : " + mensajeMayus);
        System.out.println("Palabra Clave    : " + claveFija);
        System.out.println("Clave Generada   : " + key);
        System.out.println("Texto Cifrado    : " + cifrado);
        System.out.println("Texto Descifrado : " + descifrado);

        imprimirMatrizVigenere();
        scanner.close();
    }
}
