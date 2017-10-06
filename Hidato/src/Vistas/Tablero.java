package Vistas;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Mario
 */
public class Tablero extends JPanel
{
    private int alto;
    private int ancho;
    ArrayList<JTextField> tableroHidatoJugable;

    /**
     * Constructor de la clase tablero
     * @param alto Define el alto del tablero de hidato
     * @param ancho Define el ancho del tablero de hidato
     */
    public Tablero(int alto, int ancho)
    {
        initComponents(alto, ancho);
    }
    
    /**
     * Crea el tablero que se mostrara en pantalla a manera de tabla e 
     * inicializa cada una de las casillas como un campo en el cual se puede 
     * ingresar un numero
     * @param panelTablero Ingresa el panel en el cual se va a dibujar la tabla
     * y se dimensiona
     * @param tableroHidatoJugable Array con campos de texto que vonformaran 
     * las casillas
     */
    private void createGrid(JPanel panelTablero,ArrayList<JTextField> tableroHidatoJugable)
    {
    	panelTablero.removeAll();
    	tableroHidatoJugable.clear();
    	panelTablero.setLayout(new GridLayout(alto,ancho));
    	int alturaCasilla = 25;
        int anchoCasilla = 25;
        Font fuenteCasilla = new Font("SansSerif", Font.BOLD, 20);
        //Inicializa cada casilla del tablero
        for(int i=0;i<(alto*ancho);i++)
        {
            JTextField casilla = new JTextField();
            casilla.setName("field"+(i+1));
            casilla.setPreferredSize(new Dimension(alturaCasilla,anchoCasilla));
            casilla.setFont(fuenteCasilla);
            casilla.setHorizontalAlignment(JTextField.CENTER);
            casilla.setEnabled(false);/*En primera instancia las casilla no se
                pueden editar*/
            tableroHidatoJugable.add(casilla);
         }
        //Anade cada casilla al tablero
        for(JTextField field:tableroHidatoJugable)
        {
            panelTablero.add(field);
        }
        panelTablero.revalidate();
        panelTablero.repaint();
    }

    /**
     * Inicializa los componentes correspondientes al tablero
     * @param alto corresponde al alto del tablero
     * @param ancho corresponde al ancho del tablero
     */
    private void initComponents(int alto, int ancho)
    {
        this.alto = alto;
        this.ancho = ancho;
        tableroHidatoJugable = new ArrayList<JTextField>();        
        setLayout(new GridLayout(alto, ancho));
        createGrid(this, tableroHidatoJugable);
    }
    
    

    
}
