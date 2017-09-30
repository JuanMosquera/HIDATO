/*  Class ControladorPartida
    Descripcion: Gestiona usuarios y tableros ya generados, las partidas quedan
        univocamente identificadas por su identificador. Una partida solo sera
        finalizada, si se ha explicitado su fin.
    Autor: daniel.camarasa
    Revisado: 20/12/2009 19:21 */

package Dominio;

public class ControladorPartida {

    private final static String NOMBRE_PARTIDA = "save";
    public final static int ORDENACION_POR_NOMBRE = 1;
    public final static int ORDENACION_POR_FECHA = 2;
    private final static int x100_POR_PISTA = 10;
    
    private final static ControladorPartida INSTANCIA = new ControladorPartida();

    private Partida partidaActual;
    private boolean esPartidaNueva;

    /* PRE: - */
    private ControladorPartida() {
        
        partidaActual = null;
    }
    /* POST: Se crea la intancia del ControladorPartidas */

    /* PRE: - */
    public static ControladorPartida getInstance() {

        return INSTANCIA;
    }
    /* POST: Retorna la instancia del ControladorPartida */
    
    /* PRE: - */
    public String nuevaPartida(String tablero) {

        /* para evitar que haya identificadores de partida repetidos nos 
        apoyamos en la fecha y hora actuales */

        String nuevoId = NOMBRE_PARTIDA;

        partidaActual = new Partida(nuevoId, tablero);
        esPartidaNueva = true;

        return nuevoId;
    }
    /* POST: Creamos una nueva instancia de Partida devolviendo el nuevo identificador */


    /* PRE: 'puntuacion' >= 0; 'tiempo' >= 0; 'pistas' >= 0 */
    /* EXC 'IOException': Excepcion de entrada/salida */
    /* EXC 'InstanceNotFoundException': identificador de partida actual no encontrado */
    public int finalizarPartida(int puntuacion, int tiempo, int pistas)
    {
        puntuacion = penalizaPuntuacion(puntuacion, tiempo, pistas);    
        return puntuacion;
    }
    /* POST: La partida actual pasa a estar finalizada y se devuelve una puntuacion
        resultado de aplicar penalizaciones, -1 indica que la partida ya habia sido
        finalizada. */

    /* PRE: - */
    private int penalizaPuntuacion (int puntuacion, int tiempo, int pistas) {

        double puntos = (double)puntuacion;
        double puntuacionTotal = (100 * puntos * puntos) / Math.sqrt((double)tiempo);
        double penalizacionTotal = Math.min(100.0,
          (double)x100_POR_PISTA * (double)pistas);

        return (int)((puntuacionTotal * (100.0 - penalizacionTotal)) / 100.0);
    }
    /* POST: Devuelve la puntuacion rebajada conforme a una penalizacion en funcion de
        tiempo y pistas */

    
}

