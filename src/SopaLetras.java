import java.util.Scanner;
public class SopaLetras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variable para almacenar la opción que el usuario seleccionará en el menú
        int partida;

        inicio:
        do {
            // Mostramos el menú en la consola con sus 5 opciones
            System.out.println("ESCOGE UNA VALOR:");
            System.out.println("1. Nueva Partida");
            System.out.println("2. Historial de partidas");
            System.out.println("3. Puntuaciones mas altas");
            System.out.println("4. Información del estudiante");
            System.out.println("5. Salir");

            // Leemos la opción que ingresa el usuario
            partida = scanner.nextInt();
            scanner.nextLine(); // Consumimos la línea vacía generada al presionar Enter después de ingresar un número.


            switch (partida) {
                case 1:
                    // partida 1.1: Pedimos datos del usuario (nombre, carnet, sección)
                    System.out.println("Ingrese su nombre: ");
                    String nombre = scanner.nextLine(); // Leemos el nombre
                    System.out.println("Ingrese su carnet");
                    String carnet = scanner.nextLine(); // Leemos el carnet
                    System.out.println("Ingrese su sección");
                    String seccion = scanner.nextLine(); // Leemos la sección
                    System.out.println("Te damos la bienvenida a la sopa de letras " + nombre); // Mostramos el saludo

                    // partida 1.2: se presentan 3 opciones (Menu de palabras, jugar, terminar partida)
                    int menu;
                    do {
                        System.out.println("ESCOGE UN VALOR:");
                        System.out.println("1. Menú de palabras");
                        System.out.println("2. Jugar");
                        System.out.println("3. Terminar partida");

                        menu = scanner.nextInt();
                        scanner.nextLine();
                        switch (menu) {

                            case 1:
                                //menu 1: se presentan 4 opciones (insertar, modificar, eliminar, salir)
                                int palabras;
                                do {
                                    System.out.println("ESCOGE UN VALOR");
                                    System.out.println("1. Insertar palabra");
                                    System.out.println("2. Modificar palabra");
                                    System.out.println("3. Eliminar palabra");
                                    System.out.println("4. Salir");

                                    palabras = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (palabras) {
                                        case 1:
                                            // palabras 1: Insertará el no. de palabras y las palabras a escoger
                                            System.out.println("hola");

                                            break;

                                        case 2:
                                            // palabras 2: ingresará una palabra a cambiar y colocara una nueva
                                            System.out.println("hola");
                                            break;

                                        case 3:
                                            // ingresará una palabra y la eliminará
                                            System.out.println("hola");
                                            break;

                                        case 4:
                                            // regresa al menu principal
                                            continue inicio;

                                        default:
                                            System.out.println("Opción inválida. Inténtelo de nuevo.");
                                    }
                                } while (menu != 3); // El bucle se repite hasta que el usuario elija la opción 3 que significa salir.
                                break;

                            case 2:
                                // menu 2: creará la tabla de juego y ejecutará el juego
                                System.out.println("hola");
                                break;

                            case 3:
                                //menu 3: regresara al menu principal
                                System.out.println("hola");
                                break;

                            default:
                                System.out.println("Opción inválida. Inténtelo de nuevo.");
                        }

                    } while (menu != 3); // El bucle se repite hasta que el usuario elija la opción 3 que significa salir.
                    break;

                case 2:
                    // partida 2: mostramos nombre, puntos, fallos, palabras encontradas
                    System.out.print("Ingrese el primer número: ");
                    int numero1 = scanner.nextInt(); // Leemos el primer número

                    System.out.print("Ingrese el segundo número: ");
                    int numero2 = scanner.nextInt(); // Leemos el segundo número

                    // Mostramos la suma de los dos números
                    System.out.println("La suma es: " + (numero1 + numero2));
                    break;

                case 3:
                    // partida 3: Mostramos las 3 puntuaciones más altas descendentemente.
                    System.out.println("Adiós");
                    break;

                case 4:
                    // partida 4: Mostramos los datos del estudiante (nombre, carnet, sección)
                    System.out.println("hola");
                    break;

                case 5:
                    // partida 5: Finaliza la ejecución
                    System.out.println("Adiós");
                    break;

                default:
                    // Mensaje si el usuario ingresa una opción inválida
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }

        } while (partida != 5); // El bucle se repite hasta que el usuario elija la opción 5 que significa salir.

    }
}
