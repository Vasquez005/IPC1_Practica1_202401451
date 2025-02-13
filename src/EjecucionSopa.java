import java.util.Random;
import java.util.Scanner;

public class EjecucionSopa {
    private static final int TAMANO = 25;
    private static final char[][] sopa = new char[TAMANO][TAMANO];
    private static final Random random = new Random();
    private static final char RELLENO = '#';
    private static final int INTENTOS_MAXIMOS = 4;

    private static String[] bancoPalabras = new String[100]; // Banco de palabras
    private static int totalPalabras = 0; // Contador de palabras en el banco

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ SOPA DE LETRAS ---");
            System.out.println("1. Ingresar palabras");
            System.out.println("2. Modificar una palabra");
            System.out.println("3. Eliminar una palabra");
            System.out.println("4. Iniciar juego");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir la línea

            switch (opcion) {
                case 1:
                    ingresarPalabras(sc);
                    break;
                case 2:
                    modificarPalabra(sc);
                    break;
                case 3:
                    eliminarPalabra(sc);
                    break;
                case 4:
                    iniciarJuego(sc);
                    break;
                case 5:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        sc.close();
    }

    public static void ingresarPalabras(Scanner sc) {
        System.out.print("Ingrese la cantidad de palabras a agregar: ");
        int numPalabras = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numPalabras; i++) {
            if (totalPalabras >= bancoPalabras.length) {
                System.out.println("El banco de palabras está lleno.");
                break;
            }

            String palabra;
            do {
                System.out.print("Ingrese la palabra " + (totalPalabras + 1) + " (6-15 caracteres): ");
                palabra = sc.nextLine().toUpperCase();
                if (palabra.length() < 6 || palabra.length() > 15) {
                    System.out.println("Error: La palabra debe tener entre 6 y 15 caracteres.");
                }
            } while (palabra.length() < 6 || palabra.length() > 15);

            bancoPalabras[totalPalabras++] = palabra;
        }
    }

    public static void modificarPalabra(Scanner sc) {
        if (totalPalabras == 0) {
            System.out.println("No hay palabras en el banco para modificar.");
            return;
        }

        mostrarBancoPalabras();
        System.out.print("Ingrese el número de la palabra que desea modificar: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if (indice >= 0 && indice < totalPalabras) {
            String nuevaPalabra;
            do {
                System.out.print("Ingrese la nueva palabra (6-15 caracteres): ");
                nuevaPalabra = sc.nextLine().toUpperCase();
                if (nuevaPalabra.length() < 6 || nuevaPalabra.length() > 15) {
                    System.out.println("Error: La palabra debe tener entre 6 y 15 caracteres.");
                }
            } while (nuevaPalabra.length() < 6 || nuevaPalabra.length() > 15);

            bancoPalabras[indice] = nuevaPalabra;
            System.out.println("Palabra modificada correctamente.");
        } else {
            System.out.println("Número inválido.");
        }
    }

    public static void eliminarPalabra(Scanner sc) {
        if (totalPalabras == 0) {
            System.out.println("No hay palabras en el banco para eliminar.");
            return;
        }

        mostrarBancoPalabras();
        System.out.print("Ingrese el número de la palabra que desea eliminar: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if (indice >= 0 && indice < totalPalabras) {
            for (int i = indice; i < totalPalabras - 1; i++) {
                bancoPalabras[i] = bancoPalabras[i + 1];
            }
            bancoPalabras[totalPalabras - 1] = null;
            totalPalabras--;
            System.out.println("Palabra eliminada correctamente.");
        } else {
            System.out.println("Número inválido.");
        }
    }

    public static void iniciarJuego(Scanner sc) {
        if (totalPalabras == 0) {
            System.out.println("No hay palabras en el banco para jugar.");
            return;
        }

        inicializarSopa();
        colocarPalabras();
        imprimirSopa();

        int intentos = 0;
        int palabrasEncontradas = 0;
        int palabrasPendientes = totalPalabras;
        int puntaje = 25; // Se inicia con 25 puntos

        while (intentos < INTENTOS_MAXIMOS && palabrasEncontradas < totalPalabras) {
            System.out.println("\nPalabras encontradas: " + palabrasEncontradas);
            System.out.println("Palabras pendientes: " + palabrasPendientes);
            System.out.println("Puntaje actual: " + puntaje);
            System.out.print("Ingrese una palabra a buscar: ");
            String palabraBuscar = sc.nextLine().toUpperCase();

            if (marcarPalabraEnSopa(palabraBuscar)) {
                int puntosGanados = palabraBuscar.length(); // Gana puntos según la longitud de la palabra
                puntaje += puntosGanados;
                palabrasEncontradas++;
                palabrasPendientes--;
                System.out.println("¡Palabra encontrada! Ganaste " + puntosGanados + " puntos.");
            } else {
                puntaje -= 5; // Pierde 5 puntos por cada error
                intentos++;
                System.out.println("No se encontró la palabra. Pierdes 5 puntos.");
            }

            imprimirSopa();

            if (palabrasEncontradas == totalPalabras) {
                System.out.println("\n¡Felicidades! Has encontrado todas las palabras.");
                System.out.println("Puntaje final: " + puntaje);
                return;
            }
        }

        System.out.println("\n¡Has perdido! No lograste encontrar todas las palabras en " + INTENTOS_MAXIMOS + " intentos.");
        System.out.println("Palabras encontradas: " + palabrasEncontradas);
        System.out.println("Palabras que no lograste encontrar: " + palabrasPendientes);
        System.out.println("Puntaje final: " + puntaje);
    }


    private static void inicializarSopa() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                sopa[i][j] = (char) ('A' + random.nextInt(26));
            }
        }
    }

    private static void colocarPalabras() {
        for (int i = 0; i < totalPalabras; i++) {
            boolean colocada = false;
            while (!colocada) {
                int fila = random.nextInt(TAMANO);
                int col = random.nextInt(TAMANO);
                boolean horizontal = random.nextBoolean();

                if (puedeColocarse(bancoPalabras[i], fila, col, horizontal)) {
                    colocarPalabra(bancoPalabras[i], fila, col, horizontal);
                    colocada = true;
                }
            }
        }
    }

    private static boolean puedeColocarse(String palabra, int fila, int col, boolean horizontal) {
        if (horizontal) {
            return col + palabra.length() <= TAMANO;
        } else {
            return fila + palabra.length() <= TAMANO;
        }
    }

    private static void colocarPalabra(String palabra, int fila, int col, boolean horizontal) {
        for (int i = 0; i < palabra.length(); i++) {
            if (horizontal) {
                sopa[fila][col + i] = palabra.charAt(i);
            } else {
                sopa[fila + i][col] = palabra.charAt(i);
            }
        }
    }

    private static boolean marcarPalabraEnSopa(String palabra) {
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int col = 0; col < TAMANO; col++) {
                // Verificar si la palabra aparece en horizontal
                if (col + palabra.length() <= TAMANO) {
                    boolean encontrada = true;
                    for (int i = 0; i < palabra.length(); i++) {
                        if (sopa[fila][col + i] != palabra.charAt(i)) {
                            encontrada = false;
                            break;
                        }
                    }
                    if (encontrada) {
                        for (int i = 0; i < palabra.length(); i++) {
                            sopa[fila][col + i] = RELLENO;
                        }
                        return true;
                    }
                }

                // Verificar si la palabra aparece en vertical
                if (fila + palabra.length() <= TAMANO) {
                    boolean encontrada = true;
                    for (int i = 0; i < palabra.length(); i++) {
                        if (sopa[fila + i][col] != palabra.charAt(i)) {
                            encontrada = false;
                            break;
                        }
                    }
                    if (encontrada) {
                        for (int i = 0; i < palabra.length(); i++) {
                            sopa[fila + i][col] = RELLENO;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void imprimirSopa() {
        for (char[] fila : sopa) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private static void mostrarBancoPalabras() {
        System.out.println("Palabras en el banco:");
        for (int i = 0; i < totalPalabras; i++) {
            System.out.println((i + 1) + ". " + bancoPalabras[i]);
        }
    }
}
