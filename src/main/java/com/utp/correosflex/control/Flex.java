/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.correosflex.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author Leo
 */
public class Flex {
    public String filtrarCorreos(String texto) {
        String textoSalida = "";

        try {
            // si estás en Linux cambia el nombre del archivo a 'linux.out'
            String rutaEjecutable = getClass().getClassLoader().getResource("flex/windows.exe").getPath();

            // ejecuta el proceso
            ProcessBuilder constructorProceso = new ProcessBuilder(rutaEjecutable);
            constructorProceso.redirectErrorStream(true); // redirige errores a la salida estándar
            Process proceso = constructorProceso.start();

            // pasa el texto al proceso
            try (OutputStream salidaProceso = proceso.getOutputStream()) {
                salidaProceso.write(texto.getBytes());
                salidaProceso.flush();
            }

            // lee la salida del proceso
            try (InputStream entradaProceso = proceso.getInputStream();
                 BufferedReader lector = new BufferedReader(new InputStreamReader(entradaProceso))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    textoSalida += linea + "\n";
                }
            }

            proceso.waitFor(); // espera a que el proceso termine

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            textoSalida = "Error al filtrar correos: " + e.getMessage();
        }

        return textoSalida;
    }
}
