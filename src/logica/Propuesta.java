package logica;
import datatype.TipoRetorno;
import java.util.Date;

public class Propuesta {
    
   private String titulo;
   private String descripcion;
   private String imagen;
   private Integer montoNecesario;
   private Date fechaPublicacion;
   private Date fechaEspecatulo;
   private Integer precioEntrada;
   private String lugar;
   private TipoRetorno tipo;

    public Propuesta(String titulo, String descripcion, String imagen, Integer montoNecesario, Date fechaPublicacion, Date fechaEspecatulo, Integer precioEntrada, String lugar, TipoRetorno tipo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.montoNecesario = montoNecesario;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaEspecatulo = fechaEspecatulo;
        this.precioEntrada = precioEntrada;
        this.lugar = lugar;
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaEspecatulo() {
        return fechaEspecatulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getLugar() {
        return lugar;
    }

    public Integer getMontoNecesario() {
        return montoNecesario;
    }

    public Integer getPrecioEntrada() {
        return precioEntrada;
    }

    public TipoRetorno getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaEspecatulo(Date fechaEspecatulo) {
        this.fechaEspecatulo = fechaEspecatulo;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setMontoNecesario(Integer montoNecesario) {
        this.montoNecesario = montoNecesario;
    }

    public void setPrecioEntrada(Integer precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public void setTipo(TipoRetorno tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
   
   
}   