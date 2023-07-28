package com.alura.conversor.comun;

import com.alura.conversor.entidad.Moneda;
import com.alura.conversor.entidad.RespuestaApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* La clase GestorMonedas actúa como una interfaz para administrar un Map de monedas.
*/
public class GestorMonedas {

    private Map<String, Moneda> monedas;

    /**
     * Constructor de la clase GestorMonedas.
     * @param monedas Un Map que contiene las monedas con sus códigos ISO como clave.
     */
    public GestorMonedas(Map<String, Moneda> monedas) {
        this.monedas = monedas;
    }

    /**
     * Agrega una moneda al Map de monedas.
     * @param codigo El código ISO de la moneda.
     * @param moneda La instancia de Moneda a agregar.
     */
    public void agregarMoneda(String codigo, Moneda moneda) {
        monedas.put(codigo, moneda);
    }

    /**
     * Obtiene una moneda del Map de monedas utilizando su código ISO.
     * @param codigo El código ISO de la moneda.
     * @return La instancia de Moneda correspondiente al código ISO proporcionado.
     */
    public Moneda obtenerMoneda(String codigo) {
        return monedas.get(codigo);
    }

    /**
     * Obtiene el valor en pesos de una moneda utilizando su código ISO.
     * @param codigo El código ISO de la moneda.
     * @return El valor en pesos de la moneda correspondiente al código ISO proporcionado.
     */
    public double obtenerValorPesos(String codigo) {
        Moneda moneda = monedas.get(codigo);
        return moneda.getValorEnPesos();
    }

    /**
     * Obtiene el nombre de una moneda utilizando su código ISO.
     * @param codigo El código ISO de la moneda.
     * @return El nombre de la moneda correspondiente al código ISO proporcionado.
     */
    public String obtenerNombreMoneda(String codigo) {
        Moneda moneda = monedas.get(codigo);
        return moneda.getNombre();
    }

    /**
     * Obtiene el código ISO de una moneda utilizando su código ISO.
     * @param codigo El código ISO de la moneda.
     * @return El código ISO de la moneda correspondiente al código ISO proporcionado.
     */
    public String obtenerCodigoISO(String codigo) {
        Moneda moneda = monedas.get(codigo);
        return moneda.getCodigoISO();
    }

    public List<String> obtenerListaMonedas(){
        List<String> coincidencias = new ArrayList<>();
        for (Moneda moneda : monedas.values()) {
            coincidencias.add(moneda.getNombre());
            }
        return coincidencias;
    }

    public Moneda obtenerMonedaNombre(String nombreMoneda){
        for (Moneda moneda : monedas.values()) {
            if (Objects.equals(moneda.getNombre(), nombreMoneda)){
                return moneda;
            }
        }
        return null;
    }

    public double invertirACOP( double valorPeso){
        double calculo = (1/valorPeso);
        System.out.println(calculo);
        return calculo ;
    }

}