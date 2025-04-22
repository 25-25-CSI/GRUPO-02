import java.util.Scanner;

public class AlgoritmoCuatro {

    private static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String leerMensaje() {
        Scanner scanner = new Scanner(System.in);
        String mensaje = "";
        
        while (true) {
            System.out.print("Ingrese el mensaje a cifrar: ");
            mensaje = scanner.nextLine().toUpperCase().replaceAll("\\s+", "");
            
            if (mensaje.isEmpty()) {
                System.out.println("Error: No ha ingresado ningún mensaje. Por favor, intente nuevamente.");
            } else {
                break;
            }
        }
        
        return mensaje;
    }

    private static int leerDesplazamiento() {
        Scanner scanner = new Scanner(System.in);
        int desplazamiento = -1;
        
        while (true) {
            System.out.print("Ingrese el desplazamiento (0-" + (ALFABETO.length() - 1) + "): ");
            
            try {
                desplazamiento = scanner.nextInt();
                
                if (desplazamiento >= 0 && desplazamiento < ALFABETO.length()) {
                    break;
                } else {
                    System.out.println("Error: El desplazamiento debe estar entre 0 y " + (ALFABETO.length() - 1) + ". Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número entero válido. Intente nuevamente.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
        
        return desplazamiento;
    }

    private static String generarAlfabetoCifrado(int desplazamiento) {
        return ALFABETO.substring(desplazamiento) + ALFABETO.substring(0, desplazamiento);
    }

    private static String cifrarMensaje(String mensaje, String alfabetoCifrado) {
        StringBuilder resultado = new StringBuilder();
        for (char c : mensaje.toCharArray()) {
            int indice = ALFABETO.indexOf(c);
            resultado.append(indice != -1 ? alfabetoCifrado.charAt(indice) : c);
        }
        return resultado.toString();
    }

    private static void mostrarResultados(String mensaje, int desplazamiento, String alfabetoCifrado, String mensajeCifrado) {
        System.out.println("\n=== RESULTADOS DEL CIFRADO ===");
        System.out.println("Alfabeto original : " + ALFABETO);
        System.out.println("Alfabeto cifrado  : " + alfabetoCifrado);
        System.out.println("Mensaje original  : " + mensaje);
        System.out.println("Mensaje cifrado   : " + mensajeCifrado);
        System.out.println("Desplazamiento    : " + desplazamiento);
    }

    public static void main(String[] args) {
        System.out.println("=== CIFRADO CÉSAR ===");
        
        String mensaje = leerMensaje();
        int desplazamiento = leerDesplazamiento();

        String alfabetoCifrado = generarAlfabetoCifrado(desplazamiento);
        String mensajeCifrado = cifrarMensaje(mensaje, alfabetoCifrado);

        mostrarResultados(mensaje, desplazamiento, alfabetoCifrado, mensajeCifrado);
    }
}