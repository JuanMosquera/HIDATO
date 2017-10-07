package Vistas;

import Controlador.Controlador;
import Utiles.Utiles;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/**
 *
 * @author Mario
 */
public class PanelInicio extends JPanel 
{
    private final static int MIN_SIZE_TABLERO = 3;
    private final static int MAX_SIZE_TABLERO = 25;
    
    
    private JLabel labelNombre;
    private JTextField fieldNombre;
    private JLabel labelAnchura;
    private JTextField fieldAnchura;
    private JLabel labelAltura;
    private JTextField fieldAltura;
    private JRadioButton radioFacil;
    private JRadioButton radioMedio;
    private JRadioButton radioDificil;
    private ButtonGroup grupoBotones;
    private JLabel labelMensajes;
    private JButton botonGenerar;

    /**
     * Constructor del Panel de Inicio donde se configura la partida
     */
    public PanelInicio() 
    {
        initComponents();
        setMyLayout();
    }

    /**
     * Metodo llamado al usuario clickar en el boton de generar. Comprueba que
     * los datos ingresados sean correctos y una vez que se ven que son 
     * correctos crea el tablero y va al panel del mismo
     */
    private void accionGenerar() 
    {
        String anchuraStr = fieldAnchura.getText().trim();
        String alturaStr = fieldAltura.getText().trim();
        int anchura;
        int altura;
        int dificultad;
        
        if(radioFacil.isSelected()==true)
        {
            dificultad = 1;
        }
        else if(radioMedio.isSelected()==true)
        {
            dificultad = 2;
        }
        else
        {
            dificultad = 3;
        }
        
        System.out.println(dificultad);

        labelMensajes.setText("");

        if (!Utiles.esInt(anchuraStr))
        {
            labelMensajes.setText("Anchura debe ser un número.");
        }
        else if (!Utiles.esInt(alturaStr))
        {
            labelMensajes.setText("Altura debe ser un número.");
        }
        else 
        {
            anchura = Integer.parseInt(anchuraStr);
            altura = Integer.parseInt(alturaStr);
            if (anchura < MIN_SIZE_TABLERO || anchura > MAX_SIZE_TABLERO)
            {
                labelMensajes.setText("Anchura debe ser un número entre " +
                  Integer.toString(MIN_SIZE_TABLERO) + " y " +
                  Integer.toString(MAX_SIZE_TABLERO) + ".");
            }
            else if (altura < MIN_SIZE_TABLERO || altura > MAX_SIZE_TABLERO)
            {
                labelMensajes.setText("Altura debe ser un número entre " +
                  Integer.toString(MIN_SIZE_TABLERO) + " y " +
                  Integer.toString(MAX_SIZE_TABLERO) + ".");
            }
            else 
            {
                Controlador.obtenerInstancia().iniciarPartida(altura, anchura, dificultad);
            }
        }
    }
    

    /**
     * Inicializa todos los componentes pertenecientes al panel de inicio
     */
    private void initComponents() 
    {
        
        labelNombre = new JLabel();
        labelNombre.setText("Nombre del tablero:");

        fieldNombre = new JTextField();
        
        labelAnchura = new JLabel();
        labelAnchura.setText("Anchura:");

        fieldAnchura = new JTextField();

        labelAltura = new JLabel();
        labelAltura.setText("Altura:");

        fieldAltura = new JTextField();
        
        radioFacil = new JRadioButton();
        radioFacil.setText("Fácil");
        radioFacil.setSelected(true);

        radioMedio = new JRadioButton();
        radioMedio.setText("Intermedio");

        radioDificil = new JRadioButton();
        radioDificil.setText("Difícil");

        grupoBotones = new ButtonGroup();
        grupoBotones.add(radioFacil);
        grupoBotones.add(radioMedio);
        grupoBotones.add(radioDificil);

        labelMensajes = new JLabel();
        labelMensajes.setText(" ");
        labelMensajes.setHorizontalAlignment(JLabel.CENTER);
        labelMensajes.setForeground(Color.red);

        botonGenerar = new JButton();
        botonGenerar.setText("Generar");
        botonGenerar.setToolTipText("Generar un tablero de hidato.");
        botonGenerar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                accionGenerar();
            }
        });

        setMinimumSize(new Dimension(435, 395));
    }

    /**
     * Alinea cada uno de los componentes del panel de inicio
     */
    private void setMyLayout() {

        GroupLayout layout = new GroupLayout(this);

        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup
                          (GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING,
                              layout.createSequentialGroup()
                                .addComponent(labelNombre)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                  11, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelAnchura)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                  62, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelAltura)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                  73, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup
                          (GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup
                                  (GroupLayout.Alignment.TRAILING)
                                    .addComponent(fieldAnchura,
                                      GroupLayout.Alignment.LEADING,
                                      GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(fieldAltura, GroupLayout.DEFAULT_SIZE,
                                      130, Short.MAX_VALUE)
                                .addComponent(fieldNombre,
                                      GroupLayout.Alignment.LEADING,
                                      GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                .addGap(35, 35, 35)))))
                .addGap(46, 46, 46))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMensajes, GroupLayout.Alignment.LEADING,
                      GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)                        
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10))
                    .addGroup(GroupLayout.Alignment.LEADING,
                      layout.createSequentialGroup()
                              .addGap(150, 150, 150)
                        .addComponent(botonGenerar, GroupLayout.PREFERRED_SIZE, 108,
                          GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                        .addComponent(radioFacil)
                        .addGap(50, 50, 50)
                        .addComponent(radioMedio)
                        .addGap(50, 50, 50)
                        .addComponent(radioDificil)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 87,
                          Short.MAX_VALUE)
                    ))
                .addGap(10, 10, 10))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                            .addGap(50,50,50)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(fieldNombre, GroupLayout.PREFERRED_SIZE, 20,
                      GroupLayout.PREFERRED_SIZE))
                            .addGap(50,50,50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldAnchura, GroupLayout.PREFERRED_SIZE,
                      GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAnchura))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGap(50,50,50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldAltura, GroupLayout.PREFERRED_SIZE,
                      GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAltura))
                .addGap(50, 50, 50))
                    .addGap(50,50,50)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                    .addGap(300, 300,
                  300)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(radioFacil)
                    .addComponent(radioMedio)
                    .addComponent(radioDificil))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGap(50, 50,
                  50)
                .addComponent(labelMensajes)
                .addGap(50, 50,
                  50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGenerar))
                    .addGap(15, 15, 15))
        );
    }
}
