package com.mx.nibble.dbf.obra;

import java.util.*;

public class DatosGenerales {
    public Double obra_id;
    public String obra;
    public String concurso;
    public Date fecha_concurso;
    public String descripcion;
    public String ubicacion;
    public Double obra_presupuesto;      
    public Date fecha_inicio;
    public Date fecha_termino;
    public String presupuesto_version;

    public Double getObraId(){return obra_id;}
    public String getObra(){return obra;}
    public String getConcurso(){return concurso;}
    public Date getFecha_concurso(){return fecha_concurso;}
    public String getDescripcion(){return descripcion;}
    public String getUbicacion(){return ubicacion;}
    public Double getObra_presupuesto(){return obra_presupuesto;}
    public Date getFecha_inicio(){return fecha_inicio;}
    public Date getFecha_termino(){return fecha_termino;}
    public String getPresupuesto_version(){return presupuesto_version;}
    
    public void setObraId(Double newObraId){obra_id =newObraId;}
    public void setObra(String newObra){obra =newObra;}
    public void setConcurso(String newConcurso){ concurso=newConcurso;}
    public void setFecha_concurso(Date newFecha_concurso){fecha_concurso=newFecha_concurso;}
    public void setDescripcion(String newDescription){descripcion=newDescription;}
    public void setUbicacion(String newUbicacion){ubicacion=newUbicacion;}
    public void setObra_presupuesto(Double newObra_presupuesto){obra_presupuesto=newObra_presupuesto;}
    public void setFecha_inicio(Date newFecha_inicio){fecha_inicio=newFecha_inicio;}
    public void setFecha_termino(Date newFecha_inicio){fecha_termino=newFecha_inicio;}
    public void setPresupuesto_version(String newPresupuesto_version){presupuesto_version=newPresupuesto_version;}  
}
