package Vistas;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
/**
 *
 * @author Mario
 */
public class ContenedorTablero extends JScrollPane
{
    
    private JPanel contenedor;
    private Tablero tablero;
    
    public ContenedorTablero(Tablero tablero)
    {
        initComponents(tablero);
        setMyLayout();
    }
    
    private void setMyLayout()
    {
        GroupLayout layout = new GroupLayout(contenedor);
        
        contenedor.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(tablero, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, 
                        GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tablero, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    private void initComponents(Tablero tablero)
    {
        this.tablero = tablero;
        
        contenedor = new JPanel();
        
        setViewportView(contenedor);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }   
    
}
