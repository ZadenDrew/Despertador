package despertador;

import alarma.Display;
import alarma.Reloj;
import java.util.Date;


/**
 *
 * @author andrea
 */
public class Despertador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date fecha = new Date();
        System.out.println(fecha);
        Display disp = new Display();
        Reloj clock = new Reloj();
        disp.mostrarHora(clock);
     
    }

}
