package com.alura.conversor.entidad;

public class Moneda {
        private String nombre;
        private String codigoISO;
        private double valorEnPesos;

        public Moneda(String nombre, String codigoISO, double valorEnPesos) {
            this.nombre = nombre;
            this.codigoISO = codigoISO;
            this.valorEnPesos = valorEnPesos;
        }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    public double getValorEnPesos() {
        return valorEnPesos;
    }

    public void setValorEnPesos(double valorEnPesos) {
        this.valorEnPesos = valorEnPesos;
    }
}
