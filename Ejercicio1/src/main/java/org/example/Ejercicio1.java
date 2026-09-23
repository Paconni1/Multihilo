package org.example;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Ejercicio1 {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        File archivoSalida = new File("resultado_ping.txt");

        ProcessBuilder pb = new ProcessBuilder("ping", "8.8.8.8");

        pb.redirectOutput(archivoSalida);

        try {
            System.out.println("Iniciando la prueba de red...");
            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();

            if(codigoSalida == 0){
                System.out.println("Proceso completado con éxito!");
                System.out.println("Revisa el archivo generado en :" + archivoSalida.getAbsolutePath());
            }else{
                System.out.println("El proceso fallo con codigo de error" + codigoSalida);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar ejecutar el comando");
            e.printStackTrace();

    }
}
}
