package alarma;

import javax.swing.JOptionPane;

/**
 *Esta es la clase para mostrar el menú
 * @author  acabezaslopez
 */
public class Display {

    Time despertar = new Time();
/**
 * Constructor por defecto
 */
    public Display() {
    }
/**
 * Método que muestra al usuario las opciones del menú
 * @param clock es un objeto tipo Reloj con el cual puedo llamar a los métodos necesarios
 */
    public void mostrarHora(Reloj clock) {

        int opcion;
        do {
            System.out.println("1. Arrancar");
            System.out.println("2. Detener");
            System.out.println("3. Detener Alarma\\ON/OFF");
            System.out.println("4. Reanudar");
            System.out.println("5. Alarma \\SET HR/SET ALARMA");
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
