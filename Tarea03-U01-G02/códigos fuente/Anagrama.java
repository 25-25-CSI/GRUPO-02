import java.util.*;

public class Anagrama {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String palabra = "";

        while (true) {
            System.out.print("Ingresa una palabra para ver sus anagramas: ");
            palabra = scan.nextLine().trim();

            // Validación 1: Entrada vacía
            if (palabra.isEmpty()) {
                System.out.println("Error: Entrada vacía. Escribe una palabra.");
                continue;
            }

            // Validación 2: Verifica si hay más de una palabra
            if (palabra.split("\\s+").length > 1) {
                System.out.println("Error: Solo se permite una palabra sin espacios.");
                continue;
            }

            // Validación 3: Límite de longitud
            if (palabra.length() > 9) {
                System.out.println("Error: La palabra es demasiado larga. Máximo 9 letras.");
                continue;
            }

            // Si pasó todas las validaciones, salimos del bucle
            break;
        }

        scan.close();

        // Generar anagramas
        List<String> anagramas = new ArrayList<>();
        generarPermutaciones("", palabra, anagramas);

        // Eliminar duplicados y ordenar
        Set<String> unicos = new TreeSet<>(anagramas);
        List<String> listaFinal = new ArrayList<>(unicos);

        System.out.println("Número total de anagramas únicos: " + listaFinal.size());

        int mostrar = Math.min(10, listaFinal.size());
        if (mostrar == 10) {
            System.out.println("Primeras 10 permutaciones ordenadas alfabéticamente:");
        } else {
            System.out.println("Permutaciones ordenadas alfabéticamente:");
        }

        for (int i = 0; i < mostrar; i++) {
            System.out.println((i + 1) + ". " + listaFinal.get(i));
        }
    }

    public static void generarPermutaciones(String inicial, String sobrante, List<String> resultado) {
        if (sobrante.isEmpty()) {
            resultado.add(inicial);
        } else {
            for (int i = 0; i < sobrante.length(); i++) {
                generarPermutaciones(
                    inicial + sobrante.charAt(i),
                    sobrante.substring(0, i) + sobrante.substring(i + 1),
                    resultado
                );
            }
        }
    }
}