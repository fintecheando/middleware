package com.mx.nibble.dbf.obra;

import java.io.IOException;
import org.xBaseJ.xBaseJException;

public class PruebaCargaDatosGenerales {
    
    public static void main(String [] arguments) throws xBaseJException, IllegalAccessException,IOException{
        DatosGenerales gData = new DatosGenerales();
        CargaDatosGenerales lData = new CargaDatosGenerales();
        lData.CargaDatosGenerales("/home/seven/Dropbox/Documentos/CONC AMECA LA VILLITA/", "CONC AMECA LA VILLITA");
        lData.exportarCSVDatosGenerales("/home/seven/Dropbox/Documentos/CONC AMECA LA VILLITA/CONC AMECA LA VILLITAC.csv");
        lData.InsertaDatosGenerales();
    }
}