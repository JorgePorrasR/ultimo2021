package mx.com.systemjorge.mascotasapp;

public class NoticiasVo {

    private String Encabezado;
    private String Seccion;
    private String url;


    public NoticiasVo(){

    }

    public NoticiasVo(String Encabezado, String Seccion, String url){
        this.Encabezado = Encabezado;
        this.Seccion = Seccion;
        this.url = url;
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
