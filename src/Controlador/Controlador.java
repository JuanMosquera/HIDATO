package Controlador;

import Vistas.PanelInicio;
import Vistas.PanelJuego;
import Vistas.Tablero;
import Vistas.VentanaPrincipal;
/**
 * @author Mario
 */
public class Controlador 
{
    private final VentanaPrincipal ventanaPrincipal;
    private final PanelInicio panelInicio;
    private PanelJuego panelJuego;
    private Tablero tablero;
    
    private final static Controlador INSTANCIA = new Controlador();
    
    /**
     * Constructor de la clase Controlador
     */
    public Controlador()
    {
        panelInicio = new PanelInicio();
        ventanaPrincipal = new VentanaPrincipal(panelInicio);
    }
    
    /**
     * Retorna la variable estatica INSTANCIA la cual consiste en el propio 
     * Controlador con esto se pueden realizar llamados a metodos de la clase
     * Controlador en otras clases, omitiendo el proceso de inicializar el
     * Controlador en cada clase necesaria
     * @return INSTANCIA con una instancia de Controlador
     */
    public static Controlador obtenerInstancia()
    {
        return INSTANCIA;
    }
    
    /**
     * Hace que la ventana principal sea visible
     */
    public void ejecutar()
    {
        ventanaPrincipal.setVisible(true);
    }

    /**
     * Inicia el juego de hidato
     * @param altura Se designa la altura que tendra el tablero de hidato
     * @param anchura Se designa la anchura que tendra el tablero de hidato
     * @param dificultad Se designa la dificultad del Hidato
     */
    public void iniciarPartida(int altura, int anchura, int dificultad)
    {
        tablero = new Tablero(altura, anchura, dificultad);
        panelJuego = new PanelJuego(tablero);
        ventanaPrincipal.setPanel(panelJuego);
    }
    
    public void habilitarCampos()
    {
        tablero.habilitarCampos();
    }
    
    public void guardarTablero()
    {
        
    }
   
}
