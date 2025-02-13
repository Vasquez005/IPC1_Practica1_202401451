import java.util.Scanner;

public class MenuPrincipal {

    // Matriz para almacenar usuarios [máximo 10 usuarios]
    static String[][] usuarios = new String[10][3]; // [número de usuarios][nombre, carnet, sección]
    static int contadorUsuarios = 0; // Contador de usuarios almacenados

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variable para almacenar la opción que el usuario seleccionará en el menú
        int partida;
        saltoMenu:
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
            scanner.nextLine(); // Consumimos la línea vacía generada al presionar Enter después de ingresar un número.


            switch (partida) {
                case 1:
                    if (contadorUsuarios < 10) { // Verificamos que haya espacio en la matriz
                    // partida 1.1: Pedimos datos del usuario (nombre, carnet, sección)
                    System.out.println("Ingrese su nombre: ");
                    String nombre = scanner.nextLine(); // Leemos el nombre
                    System.out.println("Ingrese su carnet");
                    String carnet = scanner.nextLine(); // Leemos el carnet
                    System.out.println("Ingrese su sección");
                    String seccion = scanner.nextLine(); // Leemos la sección
                    System.out.println("Te damos la bienvenida a la sopa de letras " + nombre); // Mostramos el saludo

                    // Guardamos en la matriz
                    usuarios[contadorUsuarios][0] = nombre;
                    usuarios[contadorUsuarios][1] = carnet;
                    usuarios[contadorUsuarios][2] = seccion;
                    contadorUsuarios++; // Incrementamos el contador de usuarios

            } else {
                System.out.println("Límite de usuarios alcanzado. No se pueden registrar más.");
            }

                    // partida 1.2: se presentan 3 opciones (Menu de palabras, jugar, terminar partida)
                    int menu;
                    menuLoop:
                    do {
                        System.out.println("--MENÚ SECUNDARIO--");
                        System.out.println("1. Menú de palabras");
                        System.out.println("2. Jugar");
                        System.out.println("3. Terminar partida");
                        System.out.println("ESCOGE UN VALOR:");

                        menu = scanner.nextInt();
                        scanner.nextLine();

                        switch (menu) {
                            case 1:
                                //menu 1: se presentan 4 opciones (insertar, modificar, eliminar, salir)
                                int palabras;
                                do {
                                    System.out.println("--MENÚ DE PALABRAS--");
                                    System.out.println("1. Insertar palabra");
                                    System.out.println("2. Modificar palabra");
                                    System.out.println("3. Eliminar palabra");
                                    System.out.println("4. Salir");
                                    System.out.println("ESCOGE UN VALOR:");

                                    palabras = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (palabras) {
                                        case 1:
                                            // palabras 1: Insertará el no. de palabras y las palabras a escoger
                                            EjecucionSopa.ingresarPalabras(scanner);
                                            break;

                                        case 2:
                                            // palabras 2: ingresará una palabra a cambiar y colocara una nueva
                                            EjecucionSopa.modificarPalabra(scanner);
                                            break;

                                        case 3:
                                            // ingresará una palabra y la eliminará
                                            EjecucionSopa.eliminarPalabra(scanner);
                                            break;

                                        case 4:
                                            continue menuLoop;
                                            // regresa al menu principal
                                        default:
                                            System.out.println("Opción inválida. Inténtelo de nuevo.");
                                    }
                                } while (menu != 4); // El bucle se repite hasta que el usuario elija la opción 3 que significa salir.
                                break;

                            case 2:
                                // menu 2: creará la tabla de juego y ejecutará el juego
                                EjecucionSopa.iniciarJuego(scanner);
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
                    if (contadorUsuarios == 0) {
                        System.out.println("No hay usuarios registrados.");
                        break;
                    }

                    System.out.println("Ingrese el nombre del usuario que desea consultar:");
                    String nombreConsulta = scanner.nextLine();
                    boolean encontrado = false;

                    for (int i = 0; i < contadorUsuarios; i++) {
                        if (usuarios[i][0].equalsIgnoreCase(nombreConsulta)) {
                            System.out.println("Información del estudiante:");
                            System.out.println("Nombre: " + usuarios[i][0]);
                            System.out.println("Carnet: " + usuarios[i][1]);
                            System.out.println("Sección: " + usuarios[i][2]);
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Usuario no encontrado.");
                    }
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
