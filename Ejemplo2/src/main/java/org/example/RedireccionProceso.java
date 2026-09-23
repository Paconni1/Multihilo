package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// ejercicio 2
public class RedireccionProceso {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("ping", "127.0.0.1");

        pb.inheritIO();
        // Opción B: Redirigir la salida directamente a un archivo en disco
        // File log = new File("salida_ping.txt");
        Process p = pb.start();
        p.waitFor();
    }

    //ejercicio 3
    /*
        public static void main(String[] args) throws Exception {
            // Ejemplo: Lanzar el intérprete de Python o un subproceso interactivo
            ProcessBuilder pb = new ProcessBuilder("python", "-u", "-c",
                    "import sys; nombre = sys.stdin.readline(); print('Hola ' + nombre)");


            Process p = pb.start();
            // 1. Escribir en la entrada del hijo usando getOutputStream()
            try (PrintWriter writer = new PrintWriter(p.getOutputStream())) {
                writer.println("Carlos");
                writer.flush(); // Forzamos el envío inmediato
            }
            // 2. Leer la respuesta del hijo
            try (Scanner scanner = new Scanner(p.getInputStream())) {
                if (scanner.hasNextLine()) {
                    System.out.println("Respuesta recibida: " +
                            scanner.nextLine());
                }
            }

            p.waitFor();
        }  */
    }


