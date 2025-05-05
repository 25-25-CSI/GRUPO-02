import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class RSA {
    private static final BigInteger e = new BigInteger("65537");  // Exponente público
    private static final BigInteger d = new BigInteger("2540897217461104834362484983533079153786029476336825067633056156370314195700149780908025304567709281764074148042437795977490400646558083595732496367555389461170184028875513683851809179659392130179253755056790933329582061994282321055778429271112850580726037095982179109287698939779183666497463703921845248509818899582896929774996352859480545746659953910299385156629385944022540107217721129624491385197619740702898990612580253407084904562825344122042723442544201000210417809878415174959794830225149808405166263853497438277160454068366970737434513726440980624908584745691305834967010196756380460192394056172117155879089");
    private static final BigInteger n = new BigInteger("16101748875593981815800528299811888563303504348664030043152247740939149943101243746485841466808451861641677169347075346718200295595384160945776004120868725255414029731845243379836667760498514176891673813439020865185404135725057880684875889350082019664513686241794424975955249646909960915879286597959618911856287691500724418225648273693322608907948932280125255516182872975553803687705206638654467410338012683629543963507292901193064797351935792922846202803153910829999371663153338061144130582575630803075300572520733455648598902152075432867914810373388404686984583134929326842665864717477221536145093189212033344037503");

    public static void main(String[] args) {
        // Cambiar el archivo por uno diferente según lo desees
        String fileName = "archivo_10000000_palabras.txt"; // Cambiar aquí el nombre del archivo
        File file = new File(fileName);

        try {
            String message = readFile(file);
            System.out.println("Procesando archivo: " + fileName);

            // Cifrar el mensaje
            String encryptedMessage = encrypt(message);
            System.out.println("Mensaje cifrado");

            // Opcionalmente puedes guardar o mostrar el mensaje cifrado (aunque no se recomienda)
            // System.out.println("Mensaje cifrado: " + encryptedMessage);

            // Descifrar el mensaje
            String decryptedMessage = decrypt(encryptedMessage);
            System.out.println("Mensaje descifrado");

        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }

    // Método para leer el contenido de un archivo
    private static String readFile(File file) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append(" ");
            }
        }
        return content.toString().trim();
    }

    // Método para cifrar el mensaje
    private static String encrypt(String message) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        BigInteger messageBigInt = new BigInteger(bytes);

        if (messageBigInt.compareTo(n) >= 0) {
            // Si el mensaje es demasiado largo, fragmentarlo en trozos
            StringBuilder encryptedMessage = new StringBuilder();
            int chunkSize = n.bitLength() / 8;  // Tamaño del fragmento (dependiendo de n)
            for (int i = 0; i < bytes.length; i += chunkSize) {
                byte[] chunk = Arrays.copyOfRange(bytes, i, Math.min(i + chunkSize, bytes.length));
                BigInteger chunkBigInt = new BigInteger(chunk);
                encryptedMessage.append(chunkBigInt.modPow(e, n).toString(16)).append(" ");  // Cifrar cada trozo
            }
            return encryptedMessage.toString().trim();  // Devolver los trozos cifrados como un solo string
        }

        // Si el mensaje es pequeño, cifrarlo todo
        BigInteger encrypted = messageBigInt.modPow(e, n);
        return encrypted.toString(16);
    }

    // Método para descifrar el mensaje
    private static String decrypt(String encryptedMessage) {
        String[] chunks = encryptedMessage.split(" ");
        StringBuilder decryptedMessage = new StringBuilder();

        for (String chunk : chunks) {
            BigInteger encryptedBigInt = new BigInteger(chunk, 16);
            BigInteger decryptedBigInt = encryptedBigInt.modPow(d, n);
            decryptedMessage.append(new String(decryptedBigInt.toByteArray(), StandardCharsets.UTF_8));
        }

        return decryptedMessage.toString();
    }
}
