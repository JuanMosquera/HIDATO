package Vistas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Mario
 */
public class VentanaPrincipal extends JFrame
{
    
    JPanel panel;
    
    /**
     * Constructor de la VentanaPrincipal el cual llama al metodo initComponents
     * @param panel Corresponde al panel que se dibujara en la ventana principal
     */
    public VentanaPrincipal(JPanel panel)
    {
        initComponents(panel);
        setMyLayout();
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Muestra un mensaje una vez que el usuario ejecuta la accion de cerrar la ventana
     */
    public void salir()
    {
        int opcion;
        String mensaje = "Desea salir del juego?";
        String titulo = "Salir";
        
        opcion = JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION);
        if(opcion==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
    
    /**
     * Establece el tamano y la localizacion de los componentes dentro de la ventana principal
     */
    private void setMyLayout()
    {
        GroupLayout layout  = new GroupLayout(getContentPane());
        
        //Alinea cada uno de los componentes en cuanto a su posicion horizontal
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.Alignment.TRAILING,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
        );
        
        //Alinea cada uno de los componentes en cuanto a su posicion vertical
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE));
    }
    
    /**
     * Inicializa las componentes de la ventana principal
     * @param panel Corresponde al panel que se dibuja en la ventana principal
     */
    private void initComponents(JPanel panel)
    {
        
        this.panel = panel;
        
        setTitle("Hidato");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                salir();
            }
        });
    }
    
    /**
     * Oculta el panel principal de la ventana y establece el panel que le llega
     * como parametro, como el panel principal. Redimenciona la ventan.
     * @param panel Panel que pasara a ser el principal
     */
    public void setPanel(JPanel panel) 
    {
        this.panel.setVisible(false);
        if (panel !=  null) 
        {
            this.panel = panel;
            this.panel.setVisible(true);
            setMyLayout();
            if (getExtendedState() != MAXIMIZED_BOTH) 
            {
                pack();
                setLocationRelativeTo(null);
            }
        }
    }
}
