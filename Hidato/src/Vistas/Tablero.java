package Vistas;

import Modelo.MatrizForma2;
import Modelo.NodoDoble;
import Modelo.Tripleta;
import Modelo.creaHidato;
import java.awt.Color;
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
    private final MatrizForma2 matrizTablero;
    private final creaHidato generadorHidato;

    /**
     * Constructor de la clase tablero
     * @param alto Define el alto del tablero de hidato
     * @param ancho Define el ancho del tablero de hidato
     * @param dificultad Define la dificultad del hidato
     */
    public Tablero(int alto, int ancho, int dificultad)
    {
        matrizTablero = new MatrizForma2(alto, ancho);
        generadorHidato = new creaHidato(matrizTablero);
        generadorHidato.gHidato(1, 1, 0);
        generadorHidato.generarH(matrizTablero, dificultad);
        matrizTablero.mostrarMatrizNormal();
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
    	int alturaCasilla = 35;
        int anchoCasilla = 35;
        Font fuenteCasilla = new Font("SansSerif", Font.BOLD, 20);
        //Inicializa cada casilla del tablero
        for(int i=0;i<(alto*ancho);i++)
        {
            JTextField casilla = new JTextField();
            casilla.setName("campo"+(i+1));
            casilla.setPreferredSize(new Dimension(alturaCasilla,anchoCasilla));
            casilla.setFont(fuenteCasilla);
            casilla.setHorizontalAlignment(JTextField.CENTER);
            casilla.setEnabled(false);/*En primera instancia las casilla no se
                pueden editar*/
            tableroHidatoJugable.add(casilla);
         }
        //Anade cada casilla al tablero
        for(JTextField campo:tableroHidatoJugable)
        {
            panelTablero.add(campo);
        }
        int qf, qc, qv, posicion;
        Tripleta tq;
        NodoDoble q = matrizTablero.nodoCabeza().getLd();        
        while(!matrizTablero.finDeRecorrido(q))
        {
            System.out.println(q.getDato());
            tq = (Tripleta)q.getDato();
            qf = tq.getColumna();
            qc = tq.getFila();
            qv = (int)tq.getValor();
            posicion = convertir(qf,qc);
            if(qv==1)
            {
 
                tableroHidatoJugable.get(posicion).setCaretColor(Color.red);
                tableroHidatoJugable.get(posicion).setText(String.valueOf(qv));
            }
            else if(q.getLd()==null)
            {
                tableroHidatoJugable.get(posicion).setCaretColor(Color.red);
                tableroHidatoJugable.get(posicion).setText(String.valueOf(qv));
            }
            else
            {
                tableroHidatoJugable.get(posicion).setText(String.valueOf(qv));
            }
            q = q.getLd();
        }
        panelTablero.revalidate();
        panelTablero.repaint();
    }
    
    public void habilitarCampos()
    {
        for(int i=0;i<(alto*ancho);i++)
        {
            String contenido = tableroHidatoJugable.get(i).getText();
            if("".equals(contenido))
            {
                tableroHidatoJugable.get(i).setEnabled(true);
            }
         }        
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
    
    private int convertir(int fila, int columna)
    {
        int resultado;
        if(fila>1)
        {
            resultado = ((fila-1)*10)+columna;
        }
        else
        {
            resultado = fila+columna;
        }
        return resultado;
    }
}
