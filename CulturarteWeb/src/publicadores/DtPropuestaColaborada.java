/**
 * DtPropuestaColaborada.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPropuestaColaborada  implements java.io.Serializable {
    private java.lang.String descripcion;

    private publicadores.EstadoPropuesta estadoActual;

    private byte[] imagen;

    private double montoAportado;

    private publicadores.DtProponente proponenteACargo;

    private java.lang.String titulo;

    public DtPropuestaColaborada() {
    }

    public DtPropuestaColaborada(
           java.lang.String descripcion,
           publicadores.EstadoPropuesta estadoActual,
           byte[] imagen,
           double montoAportado,
           publicadores.DtProponente proponenteACargo,
           java.lang.String titulo) {
           this.descripcion = descripcion;
           this.estadoActual = estadoActual;
           this.imagen = imagen;
           this.montoAportado = montoAportado;
           this.proponenteACargo = proponenteACargo;
           this.titulo = titulo;
    }


    /**
     * Gets the descripcion value for this DtPropuestaColaborada.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this DtPropuestaColaborada.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the estadoActual value for this DtPropuestaColaborada.
     * 
     * @return estadoActual
     */
    public publicadores.EstadoPropuesta getEstadoActual() {
        return estadoActual;
    }


    /**
     * Sets the estadoActual value for this DtPropuestaColaborada.
     * 
     * @param estadoActual
     */
    public void setEstadoActual(publicadores.EstadoPropuesta estadoActual) {
        this.estadoActual = estadoActual;
    }


    /**
     * Gets the imagen value for this DtPropuestaColaborada.
     * 
     * @return imagen
     */
    public byte[] getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this DtPropuestaColaborada.
     * 
     * @param imagen
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the montoAportado value for this DtPropuestaColaborada.
     * 
     * @return montoAportado
     */
    public double getMontoAportado() {
        return montoAportado;
    }


    /**
     * Sets the montoAportado value for this DtPropuestaColaborada.
     * 
     * @param montoAportado
     */
    public void setMontoAportado(double montoAportado) {
        this.montoAportado = montoAportado;
    }


    /**
     * Gets the proponenteACargo value for this DtPropuestaColaborada.
     * 
     * @return proponenteACargo
     */
    public publicadores.DtProponente getProponenteACargo() {
        return proponenteACargo;
    }


    /**
     * Sets the proponenteACargo value for this DtPropuestaColaborada.
     * 
     * @param proponenteACargo
     */
    public void setProponenteACargo(publicadores.DtProponente proponenteACargo) {
        this.proponenteACargo = proponenteACargo;
    }


    /**
     * Gets the titulo value for this DtPropuestaColaborada.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this DtPropuestaColaborada.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPropuestaColaborada)) return false;
        DtPropuestaColaborada other = (DtPropuestaColaborada) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.estadoActual==null && other.getEstadoActual()==null) || 
             (this.estadoActual!=null &&
              this.estadoActual.equals(other.getEstadoActual()))) &&
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              java.util.Arrays.equals(this.imagen, other.getImagen()))) &&
            this.montoAportado == other.getMontoAportado() &&
            ((this.proponenteACargo==null && other.getProponenteACargo()==null) || 
             (this.proponenteACargo!=null &&
              this.proponenteACargo.equals(other.getProponenteACargo()))) &&
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getEstadoActual() != null) {
            _hashCode += getEstadoActual().hashCode();
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
        _hashCode += new Double(getMontoAportado()).hashCode();
        if (getProponenteACargo() != null) {
            _hashCode += getProponenteACargo().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtPropuestaColaborada.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuestaColaborada"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("imagen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoAportado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoAportado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
