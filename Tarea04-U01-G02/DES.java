import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class DES {

    public static void main(String[] args) throws Exception {
        String rutaArchivo = "archivo_10_palabras.txt";
        String contenido = "";

        // 1. Lectura del archivo y conteo de caracteres
        long inicioLectura = System.currentTimeMillis();
        try {
            contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
        long finLectura = System.currentTimeMillis();

        // Eliminar solo los espacios ' ' y contar los caracteres restantes
        String contenidoSinEspacios = contenido.replace(" ", "");
        int caracteresEntrada = contenidoSinEspacios.length();

        System.out.println("Tiempo de lectura del archivo: " + (finLectura - inicioLectura) + " ms");
        System.out.println("#caracteresEntrada: " + caracteresEntrada);

        // 2. Generación de la clave
        long inicioClave = System.currentTimeMillis();
        KeyGenerator generadorClave = KeyGenerator.getInstance("DES");
        generadorClave.init(56); // DES usa claves de 56 bits
        SecretKey claveSecreta = generadorClave.generateKey();
        long finClave = System.currentTimeMillis();

        String claveBase64 = Base64.getEncoder().encodeToString(claveSecreta.getEncoded());
        System.out.println("Tiempo de generación de la clave: " + (finClave - inicioClave) + " ms");
        System.out.println("¡Advertencia! Solo con fines educativos se muestra la clave.");
        System.out.println("Clave utilizada (Base64): " + claveBase64);
      

        // 3. Cifrado
        Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);

        long inicioCifrado = System.currentTimeMillis();
        byte[] mensajeCifrado = cifrador.doFinal(contenido.getBytes());
        long finCifrado = System.currentTimeMillis();

        String mensajeCifradoBase64 = Base64.getEncoder().encodeToString(mensajeCifrado);
        String cifradoSinEspacios = mensajeCifradoBase64.replace(" ", "");
        int caracteresSalida = cifradoSinEspacios.length();


        System.out.println("Mensaje cifrado");
       // System.out.println("Mensaje cifrado10: " + mensajeCifradoBase64);
        System.out.println("Tiempo de cifrado: " + (finCifrado - inicioCifrado) + " ms");
        System.out.println("#caracteresSalida: " + caracteresSalida);

        // 4. Descifrado
        cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
        long inicioDescifrado = System.currentTimeMillis();
        byte[] mensajeDescifrado = cifrador.doFinal(Base64.getDecoder().decode(mensajeCifradoBase64));
        long finDescifrado = System.currentTimeMillis();

        String mensajeFinal = new String(mensajeDescifrado);
        System.out.println("Mensaje descifrado");
       // System.out.println("Mensaje descifrado10: " + mensajeFinal);
        System.out.println("Tiempo de descifrado: " + (finDescifrado - inicioDescifrado) + " ms");
    }
}
