/**
 * DtPropuestaMinificado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPropuestaMinificado  implements java.io.Serializable {
    private byte[] imagen;

    private java.lang.String proponente;

    private java.lang.String titulo;

    public DtPropuestaMinificado() {
    }

    public DtPropuestaMinificado(
           byte[] imagen,
           java.lang.String proponente,
           java.lang.String titulo) {
           this.imagen = imagen;
           this.proponente = proponente;
           this.titulo = titulo;
    }


    /**
     * Gets the imagen value for this DtPropuestaMinificado.
     * 
     * @return imagen
     */
    public byte[] getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this DtPropuestaMinificado.
     * 
     * @param imagen
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the proponente value for this DtPropuestaMinificado.
     * 
     * @return proponente
     */
    public java.lang.String getProponente() {
        return proponente;
    }


    /**
     * Sets the proponente value for this DtPropuestaMinificado.
     * 
     * @param proponente
     */
    public void setProponente(java.lang.String proponente) {
        this.proponente = proponente;
    }


    /**
     * Gets the titulo value for this DtPropuestaMinificado.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this DtPropuestaMinificado.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPropuestaMinificado)) return false;
        DtPropuestaMinificado other = (DtPropuestaMinificado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              java.util.Arrays.equals(this.imagen, other.getImagen()))) &&
            ((this.proponente==null && other.getProponente()==null) || 
             (this.proponente!=null &&
              this.proponente.equals(other.getProponente()))) &&
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
        if (getProponente() != null) {
            _hashCode += getProponente().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtPropuestaMinificado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuestaMinificado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imagen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proponente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "proponente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
