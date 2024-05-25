package tpe;

import java.sql.Date;

public class Procesador {
    private String id_procesador;
    private String codigo_procesador;
    private boolean esta_refrigerado;
    private int año_funcionamiento;

    
    public Procesador(String id_procesador, String codigo_procesador, boolean esta_refrigerado, Integer anio) {
        this.id_procesador = id_procesador;
        this.codigo_procesador = codigo_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.año_funcionamiento = anio;
    }
    public String getId_procesador() {
        return id_procesador;
    }
    public void setId_procesador(String id_procesador) {
        this.id_procesador = id_procesador;
    }
    public String getCodigo_procesador() {
        return codigo_procesador;
    }
    public void setCodigo_procesador(String codigo_procesador) {
        this.codigo_procesador = codigo_procesador;
    }
    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }
    public void setEsta_refrigerado(boolean esta_refrigerado) {
        this.esta_refrigerado = esta_refrigerado;
    }
    public int getAño_funcionamiento() {
        return año_funcionamiento;
    }
    public void setAño_funcionamiento(int año_funcionamiento) {
        this.año_funcionamiento = año_funcionamiento;
    }

}
