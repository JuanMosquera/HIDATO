/*  Class ControladorVistas:
    Descripcion: Gestiona todo el funcionamiento de la capa de presentacion de la
        aplicacion y se comunica con la capa de dominio.
    Autor: miguel.angel.vico
    Colaboradores: daniel.camarasa, alex.catarineu
    Revisado: 30/12/2009 05:56 */

package Vistas;

import Dominio.ControladorPartida;
import Dominio.ControladorTablero;
import Excepciones.InstanceRepeatedException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorVistas {

    /* Controladores de Dominio */
    private final static ControladorPartida CTRL_PARTIDA =
      ControladorPartida.getInstance();
    private final static ControladorTablero CTRL_TABLERO =
      ControladorTablero.getInstance();

    /* Componentes */
    private VentanaPrincipal ventana;
    private PanelLogin panelLogin;
    private PanelEnJuego panelEnJuego;
    private PanelProponerTablero panelProponerTablero;
    private PanelProponerTopologia panelProponerTopologia;

    /* Variables de control */
    private String partidaActual;
    private boolean partidaIniciada;
    
    private final static ControladorVistas INSTANCIA = new ControladorVistas();

    /* PRE: - */
    private ControladorVistas() {

        initComponents();
    }
    /* POST: Crea la instancia de ControladorVistas */

    /* PRE: - */
    public static ControladorVistas getInstance() {

        return INSTANCIA;
    }
    /* POST: Retorna la instancia de ControladorVistas */

    /* POST: Elimina la cuenta del usuario actual */

    /* OPERACIONES RELACIONADAS CON EL MANEJO DE LAS PARTIDAS */
    /* PRE: - */
    public void nuevaPartida() 
    {
        new NuevaPartida().setVisible(true);
    }
    /* POST: Crea y muestra un dialogo de configuracion de una nueva partida */

    /* PRE: - */
    public void procesarNuevaPartida(String tableroOriginal) {

        ArrayList<Object[]> lista = new ArrayList<Object[]>();
        int[] dificultad = new int[1];
        String mensaje = "Es posible que el sistema no funcione correctamente.";
        String titulo = "Error";
        String tableroEnCurso;

        try {
            tableroEnCurso = CTRL_TABLERO.crearTableroEnCurso(tableroOriginal,
              dificultad);
            partidaActual = CTRL_PARTIDA.nuevaPartida(tableroEnCurso);
            eliminarPaneles();
            panelEnJuego = new PanelEnJuego(arrayToTablero
              (CTRL_TABLERO.obtenerContenidoTablero()));
            ventana.setPanel(panelEnJuego);
            partidaIniciada = false;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + ".\n" + mensaje,
              titulo, JOptionPane.ERROR_MESSAGE);
        }
    }
    /* POST: Crea una nueva partida para jugar al tablero identificado por
        'tableroOriginal' */

    /* PRE: - */
    public void abandonarPartida() {

        int opcion;
        String mensaje = "¿Desea guardar sus progresos antes de abandonar la partida?";
        String titulo = "Abandonar Partida";

        panelEnJuego.pararTiempo();
        opcion = JOptionPane.showConfirmDialog(null, mensaje, titulo,
          JOptionPane.YES_NO_CANCEL_OPTION);
        if (opcion == JOptionPane.NO_OPTION ||
          (opcion == JOptionPane.YES_OPTION)) {
            partidaIniciada = false;
            partidaActual = null;
        }
        else panelEnJuego.iniciarTiempo();
    }
    /* POST: Realiza la accion de abandonar partida, para ello muestra un dialogo de
        confirmacion, en caso afirmativo abandonara la partida actual, en caso negativo
        aborta la accion */

    /* PRE: - */
    public void resetearPartida() {

        int opcion;
        String mensaje = "¿Seguro que desea resetear todo el tablero?";
        String titulo = "Resetear Partida";

        opcion = JOptionPane.showConfirmDialog(null, mensaje, titulo,
          JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            panelEnJuego.resetearTablero();
            CTRL_TABLERO.resetearTablero();
        }
    }
    /* POST: Realiza la accion de resetear la partida, para ello muestra un dialogo de
        confirmacion, en caso afirmativo procedera con resetear todo el tablero en curso
        actual, en caso negativo aborta la accion */

    /* PRE: - */
    public void finalizarPartida() {

        int puntuacion;
        int pistas;
        int tiempoJuego;
        String mensaje = "Es posible que el sistema no funcione correctamente.";
        String titulo = "Error";

        try {
            panelEnJuego.pararTiempo();
            if (CTRL_TABLERO.esTableroValido()) {
                partidaIniciada = false;
                tiempoJuego = panelEnJuego.obtenerTiempo();
                new FinalizarPartida(0, tiempoJuego, 10).mostrar();
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Solución incorrecta. Sigue " +
                  "intentandolo.", "Solución incorrecta",
                  JOptionPane.INFORMATION_MESSAGE);
                panelEnJuego.iniciarTiempo();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + ".\n" +
              mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        }
    }
    /* POST: Realiza la accion de finalizar partida, para ello verifica si el tablero
        en curso actual es correcto, en caso negativo muestra un mensaje y permite seguir
        jugando, en caso afirmativo mostrara un dialogo en el que se muestra la puntuacion
        tiempo y algunos datos mas sobre la partida actual */

    /* PRE: - */
    public boolean esPartidaIniciada() {

        return partidaIniciada;
    }
    /* POST: Retorna cierto si la partida actual se ha iniciado */

    /* PRE: - */
    public void setPartidaIniciada(boolean partidaIniciada) {

        this.partidaIniciada = partidaIniciada;
    }
    /* POST: Establece que se ha iniciado la partida actual y desbloquea las opciones
        pertinentes de la barra de menu */

    /* OPERACIONES RELACIONADAS CON EL MANEJO DE LOS TABLEROS */
    /* PRE: - */
    public Object[][] obtenerListaTableros(int opcion) {
        
        List<Object[]> listaTableros = null;
        Object[][] lista = null;
        Object[] tupla;
        String mensaje = "Es posible que el sistema no funcione correctamente.";
        String titulo = "Error";

        try {
            listaTableros = CTRL_TABLERO.obtenerListaTableros(opcion);

            lista = new Object[listaTableros.size()][3];
            for (int i = 0; i < listaTableros.size(); ++i) {
                tupla = listaTableros.get(i);
                lista[i][0] = tupla[0];
                switch (((Integer)tupla[1]).intValue()) {
                    case ControladorTablero.DIF_FACIL:
                        lista[i][2] = "Fácil";
                        break;
                    case ControladorTablero.DIF_MEDIO:
                        lista[i][2] = "Medio";
                        break;
                    case ControladorTablero.DIF_DIFICIL:
                        lista[i][2] = "Difícil";
                        break;
                    case ControladorTablero.DIF_PERSONALIZADA:
                        lista[i][2] = "Personalizada";
                        break;
                    default:
                        break;
                }
                switch (((Integer)tupla[2]).intValue()) {
                    case ControladorTablero.TOP_RECTANGULO:
                        lista[i][1] = "Rectángulo";
                        break;
                    case ControladorTablero.TOP_TRIANGULO:
                        lista[i][1] = "Triángulo";
                        break;
                    case ControladorTablero.TOP_ROMBO:
                        lista[i][1] = "Rombo";
                        break;
                    case ControladorTablero.TOP_ELIPSE:
                        lista[i][1] = "Elipse";
                        break;
                    case ControladorTablero.TOP_PERSONALIZADA:
                        lista[i][1] = "Personalizada";
                        break;
                    default:
                        break;
                }
            }

            return lista;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + ".\n" + mensaje,
              titulo, JOptionPane.ERROR_MESSAGE);
        }

        return lista;
    }
    /* POST: Retorna un array de tuplas con toda la informacion de los tableros del
        repositorio de tableros */

    /* PRE: - */
    public boolean generarTablero(String idTablero, int dificultad, int topologia,
      int numPrefijadas, int[][] formaTopologia) throws InstanceRepeatedException {

        int estado;
        int opcion;
        int[] minimoPrefijadas = new int[1];
        VentanaProgreso progreso;
        String mensaje = "Es posible que el sistema no funcione correctamente.";
        String titulo = "Error";

        try {
            if (CTRL_TABLERO.existeTablero(idTablero))
                throw new InstanceRepeatedException(idTablero);

            progreso = new VentanaProgreso(45, "Generando");
            CTRL_TABLERO.generarTablero(numPrefijadas, formaTopologia,
              progreso.obtenerThread());
            
            progreso.setVisible(true);

            estado = CTRL_TABLERO.confirmarTableroGenerado(idTablero, dificultad,
              topologia, formaTopologia, minimoPrefijadas);

            switch (estado) {
                case ControladorTablero.ABORTADO:
                    if (minimoPrefijadas[0] != -1) {
                        opcion = JOptionPane.showConfirmDialog(null, "Procedimiento " +
                          "abortado.\n\nSe ha encontrado un tablero de las mismas\n" +
                          "características con " + Integer.toString(minimoPrefijadas[0]) +
                          " casillas prefijadas.\n\n¿Desea utilizar dicho tablero?",
                          "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                        if (opcion == JOptionPane.YES_OPTION) {
                            CTRL_TABLERO.confirmarMejorTableroGenerado(idTablero,
                              dificultad, topologia, formaTopologia);
                            procesarNuevaPartida(idTablero);
                            return true;
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Procedimiento abortado.",
                          "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case ControladorTablero.CORRECTO:
                    procesarNuevaPartida(idTablero);
                    return true;
                default:
                    break;
            }
        }
        catch (InstanceRepeatedException e) {
            throw e;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + ".\n" + mensaje,
              titulo, JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }
    /* POST: Realiza la accion de ejecutar el algoritmo de generacion. En caso de que
        dicho algoritmo acabe correctamente creara dicho tablero y una nueva partida
        para jugar el tablero, en otro caso mostrara el error correspondiente */


    /* PRE: - */
    public void crearProponerTablero(String idTablero, int topologia,
      int[][] formaTopologia, int[] limitePrefijadas, NuevaPartida nuevaPartida)
      throws InstanceRepeatedException {

        boolean existe = false;
        String mensaje = "Es posible que el sistema no funcione correctamente.";
        String titulo = "Error";

        try {
            existe = CTRL_TABLERO.existeTablero(idTablero);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + ".\n" + mensaje,
              titulo, JOptionPane.ERROR_MESSAGE);
        }

        if (existe) throw new InstanceRepeatedException(idTablero);

        eliminarPaneles();
        panelProponerTablero = new PanelProponerTablero(idTablero, topologia,
          formaTopologia, limitePrefijadas, nuevaPartida);
        ventana.setPanel(panelProponerTablero);
    }
    /* POST: Crea y muestra un panel principal dedicado a proponer un tablero */

    /* PRE: - */
    public void proponerTablero(String idTablero, int topologia, int[][] contenido) {

        int estado;
        VentanaProgreso progreso = new VentanaProgreso(30, "Validando");
        ArrayList<Object[]> lista;
        Object[] tupla;
        String mensaje = "Es posible que el sistema no funcione correctamente.";
        String titulo = "Error";
        
        try {
            CTRL_TABLERO.proponerTablero(contenido, progreso.obtenerThread());

            progreso.setVisible(true);

            estado = CTRL_TABLERO.confirmarTableroPropuesto(idTablero, topologia, contenido);

            switch (estado) {
                case ControladorTablero.ABORTADO:
                    JOptionPane.showMessageDialog(null, "Procedimiento abortado.",
                      "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case ControladorTablero.NO_SOLUCION:
                    JOptionPane.showMessageDialog(null, "El tablero no tiene " +
                      "solución.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case ControladorTablero.CORRECTO:
                    ControladorVistas.getInstance().procesarNuevaPartida(idTablero);
                    break;
                case ControladorTablero.MAS_UNA_SOLUCION:
                    JOptionPane.showMessageDialog(null, "El tablero tiene más " +
                      "de una solución.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + ".\n" + mensaje,
              titulo, JOptionPane.ERROR_MESSAGE);
        }
    }
    /* POST: Realiza la accion de ejecutar el algoritmo de verificacion. En caso de que
        dicho algoritmo acabe correctamente creara dicho tablero y una nueva partida
        para jugar el tablero, en otro caso mostrara el error correspondiente */

    /* PRE: - */
    public void abortarAlgoritmo() {

        CTRL_TABLERO.abortarAlgoritmo();
    }
    /* POST: Aborta el algoritmo que se este ejecutando en este momento, ya sea el de
        generacion como el de verificacion */

    /* PRE: - */
    public void proponerTopologia(int[][] formaTopologia, NuevaPartida nPartida) {

        eliminarPaneles();
        panelProponerTopologia = new PanelProponerTopologia(formaTopologia, nPartida);
        ventana.setPanel(panelProponerTopologia);
    }
    /* POST: Crea y muestra un panel principal dedicado a proponer una topologia */

    /* PRE: - */
    public boolean repositorio() {

        Repositorio repositorio = new Repositorio();

        repositorio.setVisible(true);

        return repositorio.esAceptado();
    }
    /* POST: Crea y muestra una ventana de dialogo de repositorio. Retorna cierto si
        se ha pulsado aceptar en dicha ventana */

    /* PRE: - */
    public void darPista() {

        panelEnJuego.introducirPista(CTRL_TABLERO.darPista());
    }
    /* POST: Muestra una pista sobre el tablero en curso actual. La pista puede ser
        marcar un valor incorrecto (en el caso que los haya) o rellenar una casilla
        vacia */

    /* PRE: - */
    public void insertarValor(int x, int y, int valor) {

        CTRL_TABLERO.insertarValor(y, x, valor);
    }
    /* POST: Transmite la accion de insertar el valor 'valor' en la casilla (x, y) del
        tablero en curso actual, al dominio de la aplicacion */

    /* PRE: - */
    public void quitarValor(int x, int y) {

        CTRL_TABLERO.quitarValor(y, x);
    }
    /* POST: Transmite la accion de quitar el valor de la casilla (x, y) del tablero
        en curso actual, al dominio de la aplicacion */

    /* OPERACIONES RELACIONADAS CON EL MANEJO DE LAS ESTADISTICAS Y RANKING */

    /* PRE: - */
    public void actualizarAbandonadas(int tiempoEmpleado) 
    {
        String mensaje = "Es posible que el sistema no funcione correctamente.";
        String titulo = "Error";
        JOptionPane.showMessageDialog(null, mensaje,titulo, JOptionPane.ERROR_MESSAGE);
    }

    /* OPERACIONES AUXILIARES */
    /* PRE: - */
    public void iniciarTiempo() {

        if (panelEnJuego != null) panelEnJuego.iniciarTiempo();
    }
    /* POST: Inicia/continua la contabilizacion del tiempo de partida */

    /* PRE: - */
    public void pararTiempo() {

        if (panelEnJuego != null) panelEnJuego.pararTiempo();
    }
    /* POST: Para la contabilizacion del tiempo de partida */

    /* PRE: - */
    public void imprimir() {

        new Imprimir(CTRL_TABLERO.obtenerContenidoTablero());
    }
    /* POST: Manda a imprimir el tablero actual que se esta jugando */

    /* PRE: - */
    public void deshacer() {

        if (panelEnJuego != null) panelEnJuego.deshacer();
        else if (panelProponerTablero != null) panelProponerTablero.deshacer();
        else panelProponerTopologia.deshacer();
    }
    /* POST: Deshace la ultima accion realizada sobre algun tablero, ya sea del modo
        jugar, modo proponer tablero o modo proponer topologia */


    /* PRE: - */
    public void salir() 
    {
        System.exit(0);
    }
    /* POST: Realiza la accion de salir de la aplicacion. En el caso de haber una partida
        cargada, pregunta si se desea guardar la partida antes, en caso negativo cierra
        la aplicacion, en caso positivo manda a guardar la partida y cierra la aplicacion,
        en cualquier otro caso se cancela la accion de salir */

    /* PRE: - */
//    public void ocultarPanel() {
//
//        ventana.setPanel(new PanelUser());
//    }
    /* POST: Oculta el panel principal actual */

    /* PRE: - */
    private void eliminarPaneles() {

        panelLogin = null;
        panelEnJuego = null;
        panelProponerTablero = null;
        panelProponerTopologia = null;
    }
    /* POST: Elimina todos los paneles principales que pudiera haber inicializados */

    /* PRE: - */
    public void ejecutar() {

        ventana.setVisible(true);
    }
    /* POST: Inicia la ejecucion de la aplicacion */

    /* PRE: - */
    private void logout() {

        eliminarPaneles();
        panelLogin = new PanelLogin();
        ventana.setPanel(panelLogin);
        partidaActual = null;
        partidaIniciada = false;
    }
    /* POST: Realiza los cambios en los componentes y variables que comporta el cerrar
        sesion */

    /* PRE: - */
    private Tablero arrayToTablero(int[][][] array) {

        int altura = array[0].length;
        int anchura = array[0][0].length;
        Tablero tablero = new Tablero(altura, anchura);

        for (int i = 0; i < altura; ++i)
            for (int j = 0; j < anchura; ++j)
                if (array[1][i][j] == ControladorTablero.CAS_INACTIVA)
                    tablero.setDesactiva(i, j);

        for (int i = 0; i < altura; ++i) {
            for (int j = 0; j < anchura; ++j) {
                if (array[0][i][j] > 0)
                    tablero.setValor(i, j, array[0][i][j]);
                if (array[1][i][j] == ControladorTablero.CAS_PREFIJADA)
                    tablero.setPrefijada(i, j);
            }
        }
        tablero.modoJugar();

        return tablero;
    }
    /* POST: Retorna una instancia de Tablero a partir del contenido de 2 matrices de
        enteros que definen dicho tablero */

    /* PRE: - */
    private void initComponents() {

        panelLogin = new PanelLogin();
        ventana = new VentanaPrincipal(panelLogin);
        panelEnJuego = null;
        panelProponerTablero = null;
        panelProponerTopologia = null;

        partidaActual = null;
        partidaIniciada = false;
    }
    /* POST: Crea e inicializa correctamente todos los componentes y variables usadas por
        el controlador de vistas */
}
