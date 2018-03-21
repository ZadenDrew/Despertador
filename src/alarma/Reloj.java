package alarma;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class Reloj extends Thread {

    private boolean encendido;
    private boolean detenido;
    private static boolean detenerAlarma;
    private final SimpleDateFormat formato;
    private final Calendar c = Calendar.getInstance();
    public Date horaDespertar = new Date(System.currentTimeMillis());

    public Reloj() {
        super();
        encendido = true;
         detenido = false;
        formato = new SimpleDateFormat("HH:mm:ss");

    }

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
 * 
 * @return 
 */
    public Date getHoraDespertar() {
        return horaDespertar;
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

        public void detenerAl() {
        this.detenerAlarma = true;
    }
    /**
     * 
     * @return 
     */
    public static boolean isDetenido() {
        return detenerAlarma;
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
