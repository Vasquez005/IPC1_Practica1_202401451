import java.util.Scanner;

public class MenuPrincipal {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variable para almacenar la opción que el usuario seleccionará en el menú
        int partida;
        saltoMenu: //Variable para retornar a este punto
        do {
            // Mostramos el menú en la consola con sus 5 opciones
            System.out.println("--MENÚ PRINCIPAL--");
            System.out.println("1. Nueva Partida");
            System.out.println("2. Historial de partidas");
            System.out.println("3. Puntuaciones mas altas");
            System.out.println("4. Información del estudiante");
            System.out.println("5. Salir");
            System.out.println("ESCOGE UNA VALOR:");

            // Leemos la opción que ingresa el usuario
            partida = scanner.nextInt();
            scanner.nextLine();


            switch (partida) {
                case 1:
                    //partida 1.1: ejecuta el metodo NuevoUsuario
                    EjecucionSopa.NuevoUsuario(scanner);

                    // partida 1.2: se presentan 3 opciones (Menu de palabras, jugar, terminar partida)
                    // Variable para almacenar la opción que el usuario seleccionará en el menú
                    int menu;
                    menuLoop: //Variable para retornar a este punto
                    do {
                        System.out.println("--MENÚ SECUNDARIO--");
                        System.out.println("1. Menú de palabras");
                        System.out.println("2. Jugar");
                        System.out.println("3. Terminar partida");
                        System.out.println("ESCOGE UN VALOR:");

                        // Leemos la opción que ingresa el usuario
                        menu = scanner.nextInt();
                        scanner.nextLine();

                        switch (menu) {
                            case 1:
                                //menu 1: se presentan 4 opciones (insertar, modificar, eliminar, salir)
                                // Variable para almacenar la opción que el usuario seleccionará en el menú
                                int palabras;
                                do {
                                    System.out.println("--MENÚ DE PALABRAS--");
                                    System.out.println("1. Insertar palabras");
                                    System.out.println("2. Modificar palabra");
                                    System.out.println("3. Eliminar palabra");
                                    System.out.println("4. Salir");
                                    System.out.println("ESCOGE UN VALOR:");

                                    // Leemos la opción que ingresa el usuario
                                    palabras = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (palabras) {
                                        case 1:
                                            // palabras 1: Insertará el no. de palabras y las palabras a escoger del usuario
                                            EjecucionSopa.ingresarPalabras(scanner);
                                            break;

                                        case 2:
                                            // palabras 2: ingresará el no. de palabra a cambiar y colocará una nueva
                                            EjecucionSopa.modificarPalabra(scanner);
                                            break;

                                        case 3:
                                            // palabras 3: ingresará el no. de palabra y la eliminará
                                            EjecucionSopa.eliminarPalabra(scanner);
                                            break;

                                        case 4:
                                            continue menuLoop; // regresa al menu secundario
                                        default:
                                            System.out.println("Opción inválida. Inténtelo de nuevo.");
                                    }
                                } while (menu != 4); // El bucle se repite hasta que el usuario elija la opción 3 que significa salir.
                                break;

                            case 2:
                                // menu 2: creará la tabla de juego y ejecutará el juego
                                EjecucionSopa.inicioJuego(scanner);
                                break;

                            case 3:
                                //menu 3: regresara al menu principal
                                continue saltoMenu;
                            default:
                                System.out.println("Opción inválida. Inténtelo de nuevo.");
                        }

                    } while (menu != 3); // El bucle se repite hasta que el usuario elija la opción 3 que significa salir.
                    break;

                case 2:
                    // partida 2: mostramos nombre, puntos, fallos, palabras encontradas
                    EjecucionSopa.mostrarHistorialPartidas();
                    break;

                case 3:
                    // partida 3: Mostramos las 3 puntuaciones más altas descendentemente.
                    System.out.println("Apartado no disponible en estos momentos");
                    break;

                case 4:
                    // partida 4: Mostramos los datos del estudiante (nombre, carnet, sección)
                    EjecucionSopa.InformacionEstudiante(scanner);
                    break;

                case 5:
                    // partida 5: Finaliza la ejecución
                    System.out.println("Gracias por jugar, vuelve pronto");
                    break;

                default:
                    // Mensaje si el usuario ingresa una opción inválida
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }

        } while (partida != 5); // El bucle se repite hasta que el usuario elija la opción 5 que significa salir.

    }
}
