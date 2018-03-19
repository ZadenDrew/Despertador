package alarma;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author andrea
 */
public class Time extends TimerTask {

    Date horaDespertar = new Date(System.currentTimeMillis());

    Calendar c = Calendar.getInstance();

    public Time() {
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

        System.out.println(horaDespertar);

        // System.out.println(c.get(Calendar.DAY_OF_WEEK));
        // El despertador suena cada 24h (una vez al dia)
        int tiempoRepeticion = 86400000;

        // Programamos el despertador para que "suene"  todos los dias de la semana
        Timer temporizador = new Timer();

        temporizador.schedule(
                new Time(), horaDespertar, tiempoRepeticion);
    }

    @Override
    public void run() {
        Calendar diaActual = Calendar.getInstance();

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
    }
}
