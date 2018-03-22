package alarma;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * Clase para trabajar con el hilo que extiende de thread
 * @author acabezaslopez
 */
public class Reloj extends Thread {

    private boolean encendido;
    private boolean detenido;
    private static boolean detenerAlarma;
    private final SimpleDateFormat formato;
    private final Calendar c = Calendar.getInstance();
    public Date horaDespertar = new Date(System.currentTimeMillis());
/**
 * Constructor de Reloj
 */
    public Reloj() {
        super();
        encendido = true;
         detenido = false;
        formato = new SimpleDateFormat("HH:mm:ss");

    }
/**
 * Método en el que se solicita al usuario la hora,minuto,segundo para programar la alarma
 * No devuelve nada
 */
    public void timeDespertar() {
        c.setTime(horaDespertar);
        int dia = c.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dia);
        // Si la hora es posterior a las 8am se programa la alarma para el dia siguiente
        if (c.get(Calendar.HOUR_OF_DAY) >= 22) {
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
        }

        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(JOptionPane.showInputDialog("Introduce hora para la alarma :")));
        c.set(Calendar.MINUTE, Integer.parseInt(JOptionPane.showInputDialog("Introduce minuto para la alarma :")));
        c.set(Calendar.SECOND, Integer.parseInt(JOptionPane.showInputDialog("Introduce segundo para la alarma :")));

        horaDespertar = c.getTime();

        System.out.println("Alarma programada para  :"+horaDespertar);

        System.out.println(c.get(Calendar.DAY_OF_WEEK));
        // El despertador suena cada 24h (una vez al dia 86400000)
        int tiempoRepeticion = 1000;

        // Programamos el despertador para que "suene"  todos los dias de la semana
        Timer temporizador = new Timer();

        temporizador.schedule(
                new Time(), horaDespertar, tiempoRepeticion);
    }
/**
 * Método getter devuelve una variable tipo Date
 * @return  devuelve objeto tipo Date horaDespertar que contiene la hora de nuestra alarma
 */
    public Date getHoraDespertar() {
        return horaDespertar;
    }
/**
 * Método que para el hilo del rejol y lanza una excepción sino se puede parar 
 * No devuelve nada
 */
    public void parar() {
        encendido = false;
        detenido = true;
        try {
            join(); //Espera a que el hilo muera...
        } catch (InterruptedException ex) {
            System.err.println("Ups! No me puedo detener " + ex.getMessage());
        }
    }
/**
 * Método que detiene el hilo del reloj
 * No devuelve nada
 */
    public void detener() {
        detenido = true;
    }
/**
 * 
 * Método que detiene el hilo de alarma
 * No devuelve nada
 */
        public void detenerAl() {
        this.detenerAlarma = true;
    }
    /**
     * Método getter que devuelve detenerAlarma
     * @return detenerAlarma variable de tipo boolean
     */
    public static boolean isDetenido() {
        return detenerAlarma;
    }
    /**
     * Código ejecutable por el hilo que muestra el tiempo de sistema segundo a segundo
     * Código ejecutable por el hilo
     */
    @Override
    public void run() { 
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
/**
 * Método que reanuda la ejecución del hilo
 * No devuelve nada
 */
    public void reanudar() {
        detenido = false;
        synchronized (this) {
            notify(); //Desbloquear, pasar a estado de ejecución
        }
    }

}
