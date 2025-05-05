package com.cripto;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class MD5FileHasher {
    public static void main(String[] args) {

        String filePath = "archivo_10000000_palabras.txt";  

        long tE1Start, tE1End;
        long tE2Start, tE2End;
        long tE3Start, tE3End;

        byte[] fileBytes;

        // === Etapa 1: Lectura del archivo ===
        try {
            tE1Start = System.currentTimeMillis();
            fileBytes = Files.readAllBytes(Paths.get(filePath));
            tE1End = System.currentTimeMillis();
            System.out.println("T-E1 (Lectura de archivo): " + (tE1End - tE1Start) + " ms");
        } catch (Exception e) {
            System.err.println("Error al leer el archivo '" + filePath + "': " + e.getMessage());
            return;
        }

        MessageDigest md;
        // === Etapa 2: Inicialización del MessageDigest MD5 ===
        try {
            tE2Start = System.currentTimeMillis();
            md = MessageDigest.getInstance("MD5");
            tE2End = System.currentTimeMillis();
            System.out.println("T-E2 (Inicialización MD5): " + (tE2End - tE2Start) + " ms");
            System.out.println("Clave de cifrado: No aplica (MD5 es un hash sin clave)");
        } catch (Exception e) {
            System.err.println("Error al inicializar MD5: " + e.getMessage());
            return;
        }

        byte[] hashBytes;
        // === Etapa 3: Cálculo del hash MD5 ===
        try {
            tE3Start = System.currentTimeMillis();
            hashBytes = md.digest(fileBytes);
            tE3End = System.currentTimeMillis();
            System.out.println("T-E3 (Cálculo MD5): " + (tE3End - tE3Start) + " ms");
            System.out.println("Mensaje cifrado.");
        } catch (Exception e) {
            System.err.println("Error al calcular MD5: " + e.getMessage());
            return;
        }

        // Mostrar el hash en hexadecimal
        StringBuilder hex = new StringBuilder();
        for (byte b : hashBytes) {
            String h = Integer.toHexString(0xFF & b);
            if (h.length() == 1) hex.append('0');
            hex.append(h);
        }
        System.out.println("MD5 Hash (hex): " + hex.toString());
    }
}
