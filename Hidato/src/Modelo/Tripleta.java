package Modelo;

/**
 *
 * @author kellytatiana
 */
public class Tripleta {
    private int fila,columna;
    private Object valor;

    public Tripleta() {
    
    }

    
    public Tripleta(int fila, int columna, Object valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    
}
