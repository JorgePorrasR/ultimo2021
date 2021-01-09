package mx.com.systemjorge.mascotasapp;

public class NoticiasVo {

    private String Encabezado;
    private String Seccion;
    private String fecha;
    private String url;
    private String url2;



    public NoticiasVo(){

    }

    public NoticiasVo(String Encabezado, String Seccion, String url, String fecha, String url2){
        this.Encabezado = Encabezado;
        this.Seccion = Seccion;
        this.url = url;
        this.fecha = fecha;
    }



    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEncabezado() {
        return Encabezado;
    }

    public void setEncabezado(String Encabezado) {
        this.Encabezado = Encabezado;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String Seccion) {
        this.Seccion = Seccion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
