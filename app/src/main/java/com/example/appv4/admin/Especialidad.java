package com.example.appv4.admin;

import java.io.Serializable;

public class Especialidad implements Serializable {
    private  String id;
    private  String codigoE;
    private  String especialidad;
    private  String descripcionE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoE() {
        return codigoE;
    }

    public void setCodigoE(String codigoE) {
        this.codigoE = codigoE;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }


    public Especialidad(String codigoE, String especialidad, String descripcionE) {

        this.codigoE = codigoE;
        this.especialidad = especialidad;
        this.descripcionE = descripcionE;
    }

    public Especialidad(){

    }

}
