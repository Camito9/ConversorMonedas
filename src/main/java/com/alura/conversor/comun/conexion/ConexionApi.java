package com.alura.conversor.comun.conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import static com.alura.conversor.comun.constantes.ConstantesApi.URL_API;

public class ConexionApi {

    public static final String DIVISOR = "/";
    public static final String tipoSolicitud = "pair";

    public String obtenerValorPesos(String codModeExtranjera, String codMonedaLocal) throws IOException {
        String urlDatos = String.format(URL_API, tipoSolicitud, codModeExtranjera, codMonedaLocal);
        URL url = new URL(urlDatos);
        URLConnection conexion = url.openConnection();

        try (Reader reader = new InputStreamReader(conexion.getInputStream());
             BufferedReader br = new BufferedReader(reader)) {
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: No se pudo obtener el valor de pesos");
            return null
                    ; // Otra opción sería lanzar una excepción personalizada.
        }
    }


}
