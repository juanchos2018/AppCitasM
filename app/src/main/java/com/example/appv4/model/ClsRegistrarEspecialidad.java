package com.example.appv4.model;

public class ClsRegistrarEspecialidad {

    private String idAREA;
    private String codigoAREA;
    private String nombreAREA;
    private String descripcionAREA;


    public ClsRegistrarEspecialidad() {

    }

    public ClsRegistrarEspecialidad(String idAREA, String codigoAREA, String nombreAREA, String descripcionAREA) {
        this.idAREA = idAREA;
        this.codigoAREA = codigoAREA;
        this.nombreAREA = nombreAREA;
        this.descripcionAREA = descripcionAREA;
    }

    public String getIdAREA() {
        return idAREA;
    }

    public void setIdAREA(String idAREA) {
        this.idAREA = idAREA;
    }

    public String getCodigoAREA() {
        return codigoAREA;
    }

    public void setCodigoAREA(String codigoAREA) {
        this.codigoAREA = codigoAREA;
    }

    public String getNombreAREA() {
        return nombreAREA;
    }

    public void setNombreAREA(String nombreAREA) {
        this.nombreAREA = nombreAREA;
    }

    public String getDescripcionAREA() {
        return descripcionAREA;
    }

    public void setDescripcionAREA(String descripcionAREA) {
        this.descripcionAREA = descripcionAREA;
    }
}
