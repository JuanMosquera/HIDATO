package Modelo;
/**
 *
 * @author jfwc1
 */
public class NodoDoble 
{    
    private Object dato;
    private NodoDoble li;
    private NodoDoble ld;

    public NodoDoble() 
    {
        dato = null;
        li = null;
        ld = null;
    }
    
    public NodoDoble(Object dato) {
        this.dato = dato;
        li = null;
        ld = null;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoDoble getLi() {
        return li;
    }

    public void setLi(NodoDoble li) {
        this.li = li;
    }

    public NodoDoble getLd() {
        return ld;
    }

    public void setLd(NodoDoble ld) {
        this.ld = ld;
    }
    
}
