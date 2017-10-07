package Utiles;
/**
 * @author Mario
 */
public class Utiles {


    /* Retorna cierto si el String 'string' representa un valor entero */
    public static boolean esInt(String string) {

        int num;

        try {
            num = Integer.parseInt(string);
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }


    /* Retorna un String que expresa el valor de 'segundos' en el formato "HH:MM:SS" */
    public static String segundosToTiempo(int segundos) {

        int minutos;
        int horas;
        String segundosStr;
        String minutosStr;
        String horasStr;

        minutos = segundos / 60;
        segundos = segundos % 60;
        horas = minutos / 60;
        minutos = minutos % 60;

        segundosStr = Integer.toString(segundos);
        if (segundos < 10) segundosStr = "0" + segundosStr;
        minutosStr = Integer.toString(minutos);
        if (minutos < 10) minutosStr = "0" + minutosStr;
        horasStr = Integer.toString(horas);
        if (horas < 10) horasStr = "0" + horasStr;

        return horasStr + ":" + minutosStr + ":" + segundosStr;
    }
}