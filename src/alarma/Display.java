package alarma;

import java.util.Calendar;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author andrea
 */
public class Display extends TimerTask {

    // Alarma alarma = new Alarma();
    Time despertar = new Time();

    public Display() {
    }

    /**
     *
     * @param clock
     */
    public void mostrarHora(Reloj clock) {

        int opcion;
        do {
            System.out.println("1. Arrancar");
            System.out.println("2. Detener");
            System.out.println("3. Reanudar");
            System.out.println("4. Alarma");
            System.out.println("5. Salir");
            System.out.print("-> ");

            opcion = Integer.parseInt(JOptionPane.showInputDialog("Que opción desea :"));
            switch (opcion) {
                case 1:
                    clock.start();
                    break;
                case 2:
                    clock.detener();
                    break;
                case 3:
                    clock.reanudar();
                    break;
                case 4:
                    despertar.timeDespertar();
                    despertar.run();

                    break;
                case 5:
                    clock.parar();
                    break;
            }
        } while (opcion != 5);
        System.exit(0);
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
            case 2: // Durante los dias de diario suena el despertador
            case 3:
            case 4:
            case 5:
            case 6:
                System.out.println("Despierta!!!");
                break;
            case 7: // Es sabado puedes seguir durmiendo
                System.out.println("Zzz...");
                break;
        }
    }
}
