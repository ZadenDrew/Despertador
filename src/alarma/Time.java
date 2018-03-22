package alarma;

import java.util.Calendar;
import java.util.TimerTask;

/**
 * Clase que extiende de TimerTask
 * @author  acabezaslopez
 */
public class Time extends TimerTask {
/**
 * Constructor por defecto de tipo Time
 */
    public Time() {
    }
/**
 * Método que ejecuta el hilo de alarma teniendo en cuenta el día de la semana
 * Nuestra Alarma sólo sonara de lunes a viernes
 * No devuelve nada 
 */
    @Override
    public void run() {
        Calendar diaActual = Calendar.getInstance();

        if (Reloj.isDetenido() == false) {

            switch (diaActual.get(Calendar.DAY_OF_WEEK)) {
                case 1: // Es domingo puedes seguir durmiendo y se apaga el despertador
                    System.out.println("Zzz...");
                    this.cancel();
                    System.out.println("Temporizador apagado.");
                    break;
                // Durante los dias de diario suena el despertador
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    System.out.println("Despierta!!!RRRRRRRRRIIIIIINNNNGGGG");
                    break;
                case 7: // Es sabado puedes seguir durmiendo
                    System.out.println("Zzz...");
                    break;
            }
        } else {
            System.out.println("Zoone");     
            cancel();
        }
    }
}
