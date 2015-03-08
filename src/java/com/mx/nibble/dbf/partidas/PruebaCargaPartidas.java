package com.mx.nibble.dbf.partidas;

import com.mx.nibble.dbf.conceptos.CargaConceptos;
import com.mx.nibble.dbf.obra.CargaDatosGenerales;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.LoggerFactory;

import org.xBaseJ.xBaseJException;

public class PruebaCargaPartidas {

    org.slf4j.Logger logger = LoggerFactory.getLogger(PruebaCargaPartidas.class);

    public static void main(String[] arguments) throws xBaseJException, IllegalAccessException, SQLException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //logger.debug(dateFormat.format(date));
        String fileName = "CONC AMECA LA VILLITA";
        String path = "/home/seven/Dropbox/Documentos/CONC AMECA LA VILLITA/";
        //Carga Datos Generales
        CargaDatosGenerales lData = new CargaDatosGenerales();
        lData.CargaDatosGenerales(path, fileName);
        lData.exportarCSVDatosGenerales(path + fileName + "DatGral.csv");
        lData.InsertaDatosGenerales();
        //logger.debug("Obraid: " + lData.getDatGral().getObraId());

        CargaPartidas lPartidas = new CargaPartidas();
        lPartidas.CargaPartidas(path, fileName, lData.getDatGral().getObraId());
        lPartidas.exportarCSVPartidas(path + fileName + "Partidas.csv");
        lPartidas.InsertaPartidas(lData.getDatGral().getObraId());
        //Carga conceptos
        CargaConceptos lConceptos = new CargaConceptos();
        lConceptos.CargaConceptos(path, fileName, lPartidas.get(), lData.getDatGral().getObraId());
        lConceptos.InsertaConceptos();
        lConceptos.exportarCSVConceptos(path + fileName + "Conceptos.csv");
        date = new Date();
        //logger.debug(dateFormat.format(date));
    }
}
