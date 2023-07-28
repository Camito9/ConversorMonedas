package com.alura.conversor.entidad;


import com.google.gson.Gson;

public class RespuestaApi {
    private String result;
    private String documentation;
    private String terms_of_use;
    private long time_last_update_unix;
    private String time_last_update_utc;
    private long time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;
    private String target_code;
    private double conversion_rate;

    // Constructor, getters y setters...

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getTermsOfUse() {
        return terms_of_use;
    }

    public void setTermsOfUse(String terms_of_use) {
        this.terms_of_use = terms_of_use;
    }

    public long getTimeLastUpdateUnix() {
        return time_last_update_unix;
    }

    public void setTimeLastUpdateUnix(long time_last_update_unix) {
        this.time_last_update_unix = time_last_update_unix;
    }

    public String getTimeLastUpdateUTC() {
        return time_last_update_utc;
    }

    public void setTimeLastUpdateUTC(String time_last_update_utc) {
        this.time_last_update_utc = time_last_update_utc;
    }

    public long getTimeNextUpdateUnix() {
        return time_next_update_unix;
    }

    public void setTimeNextUpdateUnix(long time_next_update_unix) {
        this.time_next_update_unix = time_next_update_unix;
    }

    public String getTimeNextUpdateUTC() {
        return time_next_update_utc;
    }

    public void setTimeNextUpdateUTC(String time_next_update_utc) {
        this.time_next_update_utc = time_next_update_utc;
    }

    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public String getTargetCode() {
        return target_code;
    }

    public void setTargetCode(String target_code) {
        this.target_code = target_code;
    }

    public double getConversionRate() {
        return conversion_rate;
    }

    public void setConversionRate(double conversion_rate) {
        this.conversion_rate = conversion_rate;
    }


    public RespuestaApi convertirJsonARespuesta(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, RespuestaApi.class);
    }

    @Override
    public String toString() {
        return "RespuestaApi{" +
                "result='" + result + '\'' +
                ", documentation='" + documentation + '\'' +
                ", terms_of_use='" + terms_of_use + '\'' +
                ", time_last_update_unix=" + time_last_update_unix +
                ", time_last_update_utc='" + time_last_update_utc + '\'' +
                ", time_next_update_unix=" + time_next_update_unix +
                ", time_next_update_utc='" + time_next_update_utc + '\'' +
                ", base_code='" + base_code + '\'' +
                ", target_code='" + target_code + '\'' +
                ", conversion_rate=" + conversion_rate +
                '}';
    }
}
