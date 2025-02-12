import java.util.Random;
import java.util.Scanner;

public class PruebaU {
    private static final int TAMANO = 25;
    private static final char[][] sopa = new char[TAMANO][TAMANO];
    private static final Random random = new Random();
    private static final char RELLENO = '#';
    private static final int INTENTOS_MAXIMOS = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número de palabras: ");
        int numPalabras = sc.nextInt();
        sc.nextLine(); // Consumir la línea

        String[] palabras = new String[numPalabras];
        for (int i = 0; i < numPalabras; i++) {
            palabras[i] = pedirPalabra(sc, i + 1);
        }

        // Permitir modificación de palabras antes de generar la sopa
        modificarPalabras(sc, palabras);

        inicializarSopa();
        colocarPalabras(palabras);
        imprimirSopa();

        int intentos = 0;
        int palabrasEncontradas = 0;
        while (intentos < INTENTOS_MAXIMOS && palabrasEncontradas < numPalabras) {
            System.out.print("Ingrese una palabra a buscar: ");
            String palabraBuscar = sc.nextLine().toUpperCase();

            if (resolverSopa(palabraBuscar)) {
                System.out.println("Se encontró la palabra y se ha reemplazado con '#'.");
                palabrasEncontradas++;
            } else {
                System.out.println("No se encontró la palabra.");
                intentos++;
            }

            imprimirSopa();
        }

        if (palabrasEncontradas == numPalabras) {
            System.out.println("¡Felicidades! Has encontrado todas las palabras.");
        } else {
            System.out.println("¡Has perdido! No lograste encontrar todas las palabras en " + INTENTOS_MAXIMOS + " intentos.");
        }

        sc.close();
    }

    private static String pedirPalabra(Scanner sc, int numero) {
        String palabra;
        do {
            System.out.print("Ingrese la palabra " + numero + " (entre 6 y 15 caracteres): ");
            palabra = sc.nextLine().toUpperCase();
            if (palabra.length() < 6 || palabra.length() > 15) {
                System.out.println("Error: La palabra debe tener entre 6 y 15 caracteres.");
            }
        } while (palabra.length() < 6 || palabra.length() > 15);
        return palabra;
    }

    private static void modificarPalabras(Scanner sc, String[] palabras) {
        boolean modificar;
        do {
            System.out.println("¿Desea modificar alguna palabra antes de iniciar la sopa de letras? (y/n)");
            String respuesta = sc.nextLine();
            modificar = respuesta.equalsIgnoreCase("y");

            if (modificar) {
                System.out.println("Palabras ingresadas:");
                for (int i = 0; i < palabras.length; i++) {
                    System.out.println((i + 1) + ". " + palabras[i]);
                }

                System.out.print("Ingrese el número de la palabra que desea modificar: ");
                int indice = sc.nextInt() - 1;
                sc.nextLine(); // Consumir la línea

                if (indice >= 0 && indice < palabras.length) {
                    palabras[indice] = pedirPalabra(sc, indice + 1);
                } else {
                    System.out.println("Número inválido. Intente de nuevo.");
                }
            }
        } while (modificar);
    }

    private static void inicializarSopa() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                sopa[i][j] = (char) ('A' + random.nextInt(26));
            }
        }
    }

    private static void colocarPalabras(String[] palabras) {
        for (String palabra : palabras) {
            boolean colocada = false;
            while (!colocada) {
                int fila = random.nextInt(TAMANO);
                int col = random.nextInt(TAMANO);
                boolean horizontal = random.nextBoolean();

                if (puedeColocarse(palabra, fila, col, horizontal)) {
                    colocarPalabra(palabra, fila, col, horizontal);
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

    private static boolean resolverSopa(String palabra) {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (buscarPalabra(palabra, i, j, 0, 1) || buscarPalabra(palabra, i, j, 1, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean buscarPalabra(String palabra, int fila, int col, int deltaFila, int deltaCol) {
        int longitud = palabra.length();
        if (fila + deltaFila * (longitud - 1) >= TAMANO || col + deltaCol * (longitud - 1) >= TAMANO) {
            return false;
        }
        for (int i = 0; i < longitud; i++) {
            if (sopa[fila + i * deltaFila][col + i * deltaCol] != palabra.charAt(i)) {
                return false;
            }
        }
        for (int i = 0; i < longitud; i++) {
            sopa[fila + i * deltaFila][col + i * deltaCol] = RELLENO;
        }
        return true;
    }

    private static void imprimirSopa() {
        for (char[] fila : sopa) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
