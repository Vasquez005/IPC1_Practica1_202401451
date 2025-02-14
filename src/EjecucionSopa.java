import java.util.Random;
import java.util.Scanner;

public class EjecucionSopa {
    // Matriz para almacenar usuarios (máximo de 10 usuarios)
    static String[][] usuarios = new String[10][3]; // [número de usuarios][nombre, carnet, sección]
    static int contadorUsuarios = 0;
    //----------
    // Matriz para almacenar Historial de partidas (máximo 20 registros)
    static String[][] historialPartidas = new String[20][4]; // [numero de partidas][nombre, puntaje, fallos, palabras encontradas]
    static int contadorPartidas = 0;
    //----------
    //Declaración de variables utilizadas para el proyecto
    private static final int TAMANO = 25; // Tamaño de la matriz
    private static final char[][] sopa = new char[TAMANO][TAMANO];
    private static final Random random = new Random();
    private static final char RELLENO = '#'; //Relleno de las palabras encontradas
    private static final int INTENTOS_MAXIMOS = 4; //Definiendo el tamaño máximo de los intentos por usuario
    private static String[] bancoPalabras = new String[100]; // Cramos un Banco de palabras con máximo de 100 palabras
    private static int totalPalabras = 0; // Contador de palabras en el banco de palabras




    /* Proceso que ejecuta la creación de un nuevo usuario al seleccionar "Nueva Partida",
    pedimos datos del usuario (nombre, carnet, sección)*//// ////////////////////
    public static void NuevoUsuario(Scanner sc) {
        if (contadorUsuarios < 10) { // Verificamos que haya espacio en la matriz
            System.out.println("Ingrese su nombre: ");
            String nombre = sc.nextLine(); // Leemos el nombre
            System.out.println("Ingrese su carnet");
            String carnet = sc.nextLine(); // Leemos el carnet
            System.out.println("Ingrese su sección");
            String seccion = sc.nextLine(); // Leemos la sección
            System.out.println("Te damos la bienvenida a la sopa de letras " + nombre);

            // Guardamos en la matriz
            usuarios[contadorUsuarios][0] = nombre;
            usuarios[contadorUsuarios][1] = carnet;
            usuarios[contadorUsuarios][2] = seccion;
            contadorUsuarios++; // Incrementamos el contador de usuarios

        } else {
            System.out.println("Límite de usuarios alcanzado. No se pueden registrar más usuarios :)");
        }
    }

    // Proceso que ejecuta la opción "Informacion del estudiante"/////////////////////////
    public static void InformacionEstudiante(Scanner sc) {
        if (contadorUsuarios == 0) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("Ingrese el nombre del usuario que desea consultar:");
        String nombreConsulta = sc.nextLine();
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
    }

    // Proceso que ejecuta la opción "Insertar palabras"//////////////////////////
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
                System.out.print("Ingrese la palabra " + (totalPalabras + 1) + " (6-15 caracteres permitidos): ");
                palabra = sc.nextLine().toUpperCase();
                if (palabra.length() < 6 || palabra.length() > 15) {
                    System.out.println("Error: La palabra debe tener entre 6 y 15 caracteres.");
                }
            } while (palabra.length() < 6 || palabra.length() > 15);

            bancoPalabras[totalPalabras++] = palabra;
        }
    }

    // Proceso que ejecuta la opción "Modificar palabra"///////////////////////////
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
                System.out.print("Ingrese la nueva palabra (6-15 caracteres permitidos): ");
                nuevaPalabra = sc.nextLine().toUpperCase();
                if (nuevaPalabra.length() < 6 || nuevaPalabra.length() > 15) {
                    System.out.println("Error: La palabra debe tener entre 6 y 15 caracteres.");
                }
            } while (nuevaPalabra.length() < 6 || nuevaPalabra.length() > 15);

            bancoPalabras[indice] = nuevaPalabra;
            System.out.println("Palabra modificada correctamente.");
        } else {
            System.out.println("Número inválido, escoge otro.");
        }
    }

    // Proceso que ejecuta la opción "Eliminar palabra"//////////////////////////
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
            System.out.println("Número inválido, escoge otro.");
        }
    }

    // Proceso que ejecuta la opción "Jugar"////////////////////////////////
    public static void inicioJuego(Scanner sc) {
        if (totalPalabras == 0) {
            System.out.println("No hay palabras en el banco para jugar.");
            return;
        }

        System.out.print("Ingrese su nombre antes de iniciar la partida (tal y como lo escribes al inicio): ");
        String nombreJugador = sc.nextLine();

        inicializarSopa();
        colocarPalabras();
        imprimirSopa();

        int intentos = 0;
        int palabrasEncontradas = 0;
        int palabrasPendientes = totalPalabras;
        int puntaje = 25;

        //Proceso que ejecuta nuestro contador bajo la sopa de letras
        while (intentos < INTENTOS_MAXIMOS && palabrasEncontradas < totalPalabras) {
            System.out.println("\nPalabras encontradas: " + palabrasEncontradas);
            System.out.println("Palabras pendientes: " + palabrasPendientes);
            System.out.println("Puntaje actual: " + puntaje);
            System.out.print("Ingrese una palabra a buscar: ");
            String palabraBuscar = sc.nextLine().toUpperCase();

            if (marcarPalabraEnSopa(palabraBuscar)) {
                int puntosGanados = palabraBuscar.length();
                puntaje += puntosGanados;
                palabrasEncontradas++;
                palabrasPendientes--;
                System.out.println("¡Palabra encontrada! Ganaste " + puntosGanados + " puntos.");
            } else {
                puntaje -= 5; // 5 puntos por error
                intentos++;
                System.out.println("No se encontró la palabra. Pierdes 5 puntos.");
            }
            imprimirSopa();

            if (palabrasEncontradas == totalPalabras) {
                System.out.println("\n¡Felicidades! Has encontrado todas las palabras.");
                System.out.println("Puntaje final: " + puntaje);

                registrarHistorialPartida(nombreJugador, puntaje, intentos, palabrasEncontradas);
                return;
            }
        }
        System.out.println("\n¡Has perdido! No lograste encontrar todas las palabras en " + INTENTOS_MAXIMOS + " intentos.");
        System.out.println("Palabras encontradas: " + palabrasEncontradas);
        System.out.println("Palabras que no lograste encontrar: " + palabrasPendientes);
        System.out.println("Puntaje final: " + puntaje);

        registrarHistorialPartida(nombreJugador, puntaje, intentos, palabrasEncontradas);
    }
    // ---------------------MÉTODOS AUXILIARES:-----------------------------
    // Proceso que ejecuta el tamaño y el rellenado de la sopa de letras///////////////
    private static void inicializarSopa() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                sopa[i][j] = (char) ('A' + random.nextInt(26));
            }
        }
    }

    // Proceso que ejecuta la colocación de palabras dentro de la sopa de letras de forma aleatoria///////////
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

    // Proceso que ejecuta la verificación para que una palabra no se "salga" de la sopa de letras/////////////
    private static boolean puedeColocarse(String palabra, int fila, int col, boolean horizontal) {
        if (horizontal) {
            return col + palabra.length() <= TAMANO;
        } else {
            return fila + palabra.length() <= TAMANO;
        }
    }

    //Proceso que coloca una palabra dentro de la sopa de letras////////////////////////////
    private static void colocarPalabra(String palabra, int fila, int col, boolean horizontal) {
        for (int i = 0; i < palabra.length(); i++) {
            if (horizontal) {
                sopa[fila][col + i] = palabra.charAt(i);
            } else {
                sopa[fila + i][col] = palabra.charAt(i);
            }
        }
    }

    //Proceso que registra datos para poder realizar el historial de partidas.////////////////
    public static void registrarHistorialPartida(String nombre, int puntaje, int fallos, int palabrasEncontradas) {
        if (contadorPartidas < 20) { // Verificar espacio en contadoPartidas
            historialPartidas[contadorPartidas][0] = nombre;
            historialPartidas[contadorPartidas][1] = String.valueOf(puntaje);
            historialPartidas[contadorPartidas][2] = String.valueOf(fallos);
            historialPartidas[contadorPartidas][3] = String.valueOf(palabrasEncontradas);
            contadorPartidas++;
        } else {
            System.out.println("El historial de partidas está lleno, no se pueden registrar más.");
        }
    }

    // Proceso que ejecuta la opción "Historial de partidas"//////////////////
    public static void mostrarHistorialPartidas() {
        if (contadorPartidas == 0) {
            System.out.println("No hay partidas registradas en el historial.");
            return;
        }

        System.out.println("--- HISTORIAL DE PARTIDAS ---");
        for (int i = 0; i < contadorPartidas; i++) {
            System.out.println("Jugador: " + historialPartidas[i][0]);
            System.out.println("Puntaje final: " + historialPartidas[i][1]);
            System.out.println("Fallos: " + historialPartidas[i][2]);
            System.out.println("Palabras encontradas: " + historialPartidas[i][3]);
            System.out.println("-----------------------------\n");
        }
    }

    // Proceso que ejecuta una búsqueda de las palabras en la matriz, si la encuentra la remplaza por "RELLENO".////////////
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

    //Proceso que imprime la sopa de letras de manera organizada///////////////
    private static void imprimirSopa() {
        for (char[] fila : sopa) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    //Proceo que imprime en consola las palabras del "Banco de palabras"//////////////
    private static void mostrarBancoPalabras() {
        System.out.println("Palabras en el banco:");
        for (int i = 0; i < totalPalabras; i++) {
            System.out.println((i + 1) + ". " + bancoPalabras[i]);
        }
    }
}

/*gracias por su atención :)*/