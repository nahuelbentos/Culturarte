/**
 * DtPropuesta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPropuesta  implements java.io.Serializable {
    private publicadores.DtCategoria categoria;

    private publicadores.DtColaboracion[] colaboraciones;

    private java.lang.String descripcion;

    private publicadores.EstadoPropuesta estadoActual;

    private publicadores.DtEstado[] estadoHistorial;

    private java.util.Calendar fechaEspecatulo;

    private java.util.Calendar fechaPublicacion;

    private byte[] imagen;

    private java.lang.String lugar;

    private float montoNecesario;

    private float precioEntrada;

    private publicadores.DtProponente proponenteACargo;

    private float recaudado;

    private publicadores.TipoRetorno tipo;

    private java.lang.String titulo;

    public DtPropuesta() {
    }

    public DtPropuesta(
           publicadores.DtCategoria categoria,
           publicadores.DtColaboracion[] colaboraciones,
           java.lang.String descripcion,
           publicadores.EstadoPropuesta estadoActual,
           publicadores.DtEstado[] estadoHistorial,
           java.util.Calendar fechaEspecatulo,
           java.util.Calendar fechaPublicacion,
           byte[] imagen,
           java.lang.String lugar,
           float montoNecesario,
           float precioEntrada,
           publicadores.DtProponente proponenteACargo,
           float recaudado,
           publicadores.TipoRetorno tipo,
           java.lang.String titulo) {
           this.categoria = categoria;
           this.colaboraciones = colaboraciones;
           this.descripcion = descripcion;
           this.estadoActual = estadoActual;
           this.estadoHistorial = estadoHistorial;
           this.fechaEspecatulo = fechaEspecatulo;
           this.fechaPublicacion = fechaPublicacion;
           this.imagen = imagen;
           this.lugar = lugar;
           this.montoNecesario = montoNecesario;
           this.precioEntrada = precioEntrada;
           this.proponenteACargo = proponenteACargo;
           this.recaudado = recaudado;
           this.tipo = tipo;
           this.titulo = titulo;
    }


    /**
     * Gets the categoria value for this DtPropuesta.
     * 
     * @return categoria
     */
    public publicadores.DtCategoria getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this DtPropuesta.
     * 
     * @param categoria
     */
    public void setCategoria(publicadores.DtCategoria categoria) {
        this.categoria = categoria;
    }


    /**
     * Gets the colaboraciones value for this DtPropuesta.
     * 
     * @return colaboraciones
     */
    public publicadores.DtColaboracion[] getColaboraciones() {
        return colaboraciones;
    }


    /**
     * Sets the colaboraciones value for this DtPropuesta.
     * 
     * @param colaboraciones
     */
    public void setColaboraciones(publicadores.DtColaboracion[] colaboraciones) {
        this.colaboraciones = colaboraciones;
    }

    public publicadores.DtColaboracion getColaboraciones(int i) {
        return this.colaboraciones[i];
    }

    public void setColaboraciones(int i, publicadores.DtColaboracion _value) {
        this.colaboraciones[i] = _value;
    }


    /**
     * Gets the descripcion value for this DtPropuesta.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this DtPropuesta.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the estadoActual value for this DtPropuesta.
     * 
     * @return estadoActual
     */
    public publicadores.EstadoPropuesta getEstadoActual() {
        return estadoActual;
    }


    /**
     * Sets the estadoActual value for this DtPropuesta.
     * 
     * @param estadoActual
     */
    public void setEstadoActual(publicadores.EstadoPropuesta estadoActual) {
        this.estadoActual = estadoActual;
    }


    /**
     * Gets the estadoHistorial value for this DtPropuesta.
     * 
     * @return estadoHistorial
     */
    public publicadores.DtEstado[] getEstadoHistorial() {
        return estadoHistorial;
    }


    /**
     * Sets the estadoHistorial value for this DtPropuesta.
     * 
     * @param estadoHistorial
     */
    public void setEstadoHistorial(publicadores.DtEstado[] estadoHistorial) {
        this.estadoHistorial = estadoHistorial;
    }

    public publicadores.DtEstado getEstadoHistorial(int i) {
        return this.estadoHistorial[i];
    }

    public void setEstadoHistorial(int i, publicadores.DtEstado _value) {
        this.estadoHistorial[i] = _value;
    }


    /**
     * Gets the fechaEspecatulo value for this DtPropuesta.
     * 
     * @return fechaEspecatulo
     */
    public java.util.Calendar getFechaEspecatulo() {
        return fechaEspecatulo;
    }


    /**
     * Sets the fechaEspecatulo value for this DtPropuesta.
     * 
     * @param fechaEspecatulo
     */
    public void setFechaEspecatulo(java.util.Calendar fechaEspecatulo) {
        this.fechaEspecatulo = fechaEspecatulo;
    }


    /**
     * Gets the fechaPublicacion value for this DtPropuesta.
     * 
     * @return fechaPublicacion
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this DtPropuesta.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the imagen value for this DtPropuesta.
     * 
     * @return imagen
     */
    public byte[] getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this DtPropuesta.
     * 
     * @param imagen
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the lugar value for this DtPropuesta.
     * 
     * @return lugar
     */
    public java.lang.String getLugar() {
        return lugar;
    }


    /**
     * Sets the lugar value for this DtPropuesta.
     * 
     * @param lugar
     */
    public void setLugar(java.lang.String lugar) {
        this.lugar = lugar;
    }


    /**
     * Gets the montoNecesario value for this DtPropuesta.
     * 
     * @return montoNecesario
     */
    public float getMontoNecesario() {
        return montoNecesario;
    }


    /**
     * Sets the montoNecesario value for this DtPropuesta.
     * 
     * @param montoNecesario
     */
    public void setMontoNecesario(float montoNecesario) {
        this.montoNecesario = montoNecesario;
    }


    /**
     * Gets the precioEntrada value for this DtPropuesta.
     * 
     * @return precioEntrada
     */
    public float getPrecioEntrada() {
        return precioEntrada;
    }


    /**
     * Sets the precioEntrada value for this DtPropuesta.
     * 
     * @param precioEntrada
     */
    public void setPrecioEntrada(float precioEntrada) {
        this.precioEntrada = precioEntrada;
    }


    /**
     * Gets the proponenteACargo value for this DtPropuesta.
     * 
     * @return proponenteACargo
     */
    public publicadores.DtProponente getProponenteACargo() {
        return proponenteACargo;
    }


    /**
     * Sets the proponenteACargo value for this DtPropuesta.
     * 
     * @param proponenteACargo
     */
    public void setProponenteACargo(publicadores.DtProponente proponenteACargo) {
        this.proponenteACargo = proponenteACargo;
    }


    /**
     * Gets the recaudado value for this DtPropuesta.
     * 
     * @return recaudado
     */
    public float getRecaudado() {
        return recaudado;
    }


    /**
     * Sets the recaudado value for this DtPropuesta.
     * 
     * @param recaudado
     */
    public void setRecaudado(float recaudado) {
        this.recaudado = recaudado;
    }


    /**
     * Gets the tipo value for this DtPropuesta.
     * 
     * @return tipo
     */
    public publicadores.TipoRetorno getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this DtPropuesta.
     * 
     * @param tipo
     */
    public void setTipo(publicadores.TipoRetorno tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the titulo value for this DtPropuesta.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this DtPropuesta.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPropuesta)) return false;
        DtPropuesta other = (DtPropuesta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.categoria==null && other.getCategoria()==null) || 
             (this.categoria!=null &&
              this.categoria.equals(other.getCategoria()))) &&
            ((this.colaboraciones==null && other.getColaboraciones()==null) || 
             (this.colaboraciones!=null &&
              java.util.Arrays.equals(this.colaboraciones, other.getColaboraciones()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.estadoActual==null && other.getEstadoActual()==null) || 
             (this.estadoActual!=null &&
              this.estadoActual.equals(other.getEstadoActual()))) &&
            ((this.estadoHistorial==null && other.getEstadoHistorial()==null) || 
             (this.estadoHistorial!=null &&
              java.util.Arrays.equals(this.estadoHistorial, other.getEstadoHistorial()))) &&
            ((this.fechaEspecatulo==null && other.getFechaEspecatulo()==null) || 
             (this.fechaEspecatulo!=null &&
              this.fechaEspecatulo.equals(other.getFechaEspecatulo()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              java.util.Arrays.equals(this.imagen, other.getImagen()))) &&
            ((this.lugar==null && other.getLugar()==null) || 
             (this.lugar!=null &&
              this.lugar.equals(other.getLugar()))) &&
            this.montoNecesario == other.getMontoNecesario() &&
            this.precioEntrada == other.getPrecioEntrada() &&
            ((this.proponenteACargo==null && other.getProponenteACargo()==null) || 
             (this.proponenteACargo!=null &&
              this.proponenteACargo.equals(other.getProponenteACargo()))) &&
            this.recaudado == other.getRecaudado() &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        if (getColaboraciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColaboraciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColaboraciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getEstadoActual() != null) {
            _hashCode += getEstadoActual().hashCode();
        }
        if (getEstadoHistorial() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEstadoHistorial());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEstadoHistorial(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFechaEspecatulo() != null) {
            _hashCode += getFechaEspecatulo().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getImagen() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getImagen());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getImagen(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLugar() != null) {
            _hashCode += getLugar().hashCode();
        }
        _hashCode += new Float(getMontoNecesario()).hashCode();
        _hashCode += new Float(getPrecioEntrada()).hashCode();
        if (getProponenteACargo() != null) {
            _hashCode += getProponenteACargo().hashCode();
        }
        _hashCode += new Float(getRecaudado()).hashCode();
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtPropuesta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "categoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtCategoria"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colaboraciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "colaboraciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtColaboracion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoActual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoActual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "estadoPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoHistorial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoHistorial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtEstado"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEspecatulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaEspecatulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imagen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lugar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lugar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoNecesario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoNecesario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("precioEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "precioEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proponenteACargo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "proponenteACargo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtProponente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recaudado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recaudado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "tipoRetorno"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
