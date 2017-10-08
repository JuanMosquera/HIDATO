package Vistas;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author Mario
 */
public class PanelJuego extends JPanel
{
    private Tablero tablero;
    private BarraSuperiorEnJuego barraSuperior;
    private ContenedorTablero contenedor;
    
    /**
     * Constructor del panel de juego el cual se configura como panel dentro de la ventana principal
     * @param tablero
     */
    public PanelJuego(Tablero tablero)
    {
        initComponents(tablero);
        setMyLayout();
    }
    
    /**
     * Retorna el tiempo que lleva jugando el usuario
     * @return int tiempo
     */
    public int obtenerTiempo() {

        return barraSuperior.obtenerTiempo();
    }
    
    /**
     * Inicia el contador de tiempo
     */
    public void iniciarTiempo() {

        barraSuperior.iniciarTiempo();
    }

    /**
     * Detiene el tiempo
     */
    public void pararTiempo() {

        barraSuperior.pararTiempo();
    }
    
    /**
     * Inicializa todos los componentes que pertenecen al panel de juego
     */
    private void initComponents(Tablero tablero)
    {        
        barraSuperior = new BarraSuperiorEnJuego();
        barraSuperior.setVisible(true);
        
        this.tablero = tablero;
        this.tablero.setVisible(true);
        
        contenedor = new ContenedorTablero(this.tablero);
    }
    
    /**
     * Posiciona todos los componentes que pertenecen al panel de juego.
     */
    private void setMyLayout()
    {
        
        GroupLayout layout = new GroupLayout(this);
        
        setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(barraSuperior, GroupLayout.DEFAULT_SIZE,
              GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contenedor)
        );
        
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(barraSuperior, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(contenedor)
                            .addGap(0, 0, 0))
        );
    }
}
