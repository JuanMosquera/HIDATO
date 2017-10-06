package Utiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
/**
 * @author Mario
 */
public class Archivos {

    /* Retorna la ruta completa donde se encuentra actualmente la aplicacion */
    public static String getPath() {

        String path = Utiles.class.getResource("/Utiles").toString();

        path = path.substring("file:/".length(), path.indexOf("/Utiles"));
        path = path.replace("%20", " ");

        return path;
    }

    /* Abre un archivo de acceso aleatorio (lo crea si no existe) llamado 'filePath' en
        el directorio 'dirPath' */
    public static RandomAccessFile openFile(String dirPath, String filePath)
      throws FileNotFoundException {

        RandomAccessFile file;

        try {
            file = new RandomAccessFile(dirPath + filePath, "rw");
        }
        catch (FileNotFoundException ex) {
            if ((new File(dirPath)).mkdir())
                file = new RandomAccessFile(dirPath + filePath, "rw");
            else throw new FileNotFoundException();
        }

        return file;
    }

}