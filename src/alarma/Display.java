package alarma;

import javax.swing.JOptionPane;

/**
 *
 * @author andrea
 */
public class Display {

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
            System.out.println("3. Detener Alarma");
            System.out.println("4. Reanudar");
            System.out.println("5. Alarma");
            System.out.println("6. Parar Reloj");
            System.out.println("7. Salir");
            System.out.print("-> \n");

            opcion = Integer.parseInt(JOptionPane.showInputDialog("Que opción desea :"));
            switch (opcion) {
                case 1:
                    clock.start();
                    break;
                case 2:
                    clock.detener();
                    break;
                case 3:
                    clock.detenerAl();
                    break;

                case 4:
                    clock.reanudar();
                    break;
                case 5:
                    clock.timeDespertar();
                        despertar.run();

                    break;
                case 6:
                    clock.parar();
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        } while (opcion != 7);
        System.exit(0);
    }

}
