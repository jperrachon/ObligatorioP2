package entities;
import java.util.Date;

public class tuplaCancion {

    private Cancion cancion;
    private Pais pais;
    private Date fecha;
    private int puesto;


    public tuplaCancion(Pais pais, Date fecha, int puesto, Cancion cancion) {
        this.pais = pais;
        this.fecha = fecha;
        this.puesto = puesto;
        this.cancion = cancion;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public Cancion getCancion() {
        return cancion;
    }

}
