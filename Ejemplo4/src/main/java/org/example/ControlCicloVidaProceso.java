package org.example;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ControlCicloVidaProceso {
    public static void main(String[] args) {
        boolean esWindows = false;
        int tiempoTrabajo = 5;
        System.getProperty("os.name").toLowerCase().contains("win");

        // Comando que simula un trabajo largo (10 segundos)
        ProcessBuilder pb = esWindows;
                if(esWindows){
                pb = new ProcessBuilder("ping", "-n", String.valueOf(tiempoTrabajo), "127.0.0.1");
    }else {
                pb = new ProcessBuilder("sleep",String.valueOf(tiempoTrabajo));
                }
        try {
            System.out.println("=== INICIANDO SUBPROCESO ===");
            Process proceso = pb.start();
            long pid = proceso.pid();
            System.out.println("Proceso iniciado con PID: " + pid);
            // 1. Monitoreo asíncrono mediante onExit() (Java 9+)
            proceso.onExit().thenAccept(p ->
                    System.out.println("[Callback Asíncrono] El proceso PID " +
                            p.pid() + " ha finalizado.")
            );
            // 2. Espera con Timeout (esperamos máximo 3 segundos)
            System.out.println("Esperando finalización (máximo 3 segundos)...");
            boolean terminadoApertura = proceso.waitFor(3, TimeUnit.SECONDS);
            // 3. Control del estado si no termina a tiempo
            if (!terminadoApertura) {
                System.out.println("ATENCIÓN: El proceso ha superado el límite de tiempo de 3s.");

                        System.out.println("Enviando orden de terminación limpia (destroy)...");
                proceso.destroy();
                // Damos un margen de 2 segundos para la terminación limpia
                if (!proceso.waitFor(2, TimeUnit.SECONDS)) {
                    System.out.println("El proceso no responde. Forzando terminación abrupta (destroyForcibly)...");
                    proceso.destroyForcibly();
                }
            }
            // 4. Espera final y lectura del código de salida
            int codigoSalida = proceso.waitFor();
            System.out.println("\n=== RESUMEN DE EJECUCIÓN ===");
            System.out.println("PID: " + pid);
            System.out.println("¿Está vivo?: " + proceso.isAlive());
            System.out.println("Código de salida final: " + codigoSalida);
        } catch (IOException e) {
            System.err.println("Error de E/S al lanzar el proceso: " +
                    e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("El hilo principal fue interrumpido: " +
                    e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}

