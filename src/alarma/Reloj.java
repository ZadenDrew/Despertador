package alarma;

import java.text.SimpleDateFormat;
import java.util.*;

public class Reloj extends Thread {

    private boolean encendido;
    private boolean detenido;
    private final SimpleDateFormat formato;

    public Reloj() {
        super();
        encendido = true;
        detenido = false;
        formato = new SimpleDateFormat("HH:mm:ss");
    }

    public void parar() {
        encendido = false;
        detenido = true;
        try {
            join(); //Espera a que el hilo muera...
        } catch (InterruptedException ex) {
            System.err.println("Ups! No me puedo detener " + ex.getMessage());
        }
    }

    public void detener() {
        detenido = true;
    }

    @Override
    public void run() { //Código ejecutable por el hilo
        while (encendido) {
            if (detenido) {
                synchronized (this) {
                    try {
                        wait(); //Bloquear
                    } catch (InterruptedException ex) {
                    }
                }
            }
            Calendar tiempo = Calendar.getInstance();
            System.out.println(formato.format(tiempo.getTime()));
            try {
                sleep(1000); //Suspender por 1 segundo
            } catch (InterruptedException ex) {
            }
        }
    }

    public void reanudar() {
        detenido = false;
        synchronized (this) {
            notify(); //Desbloquear, pasar a estado de ejecución
        }
    }

}
