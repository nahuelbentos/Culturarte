/**
 * DtPerfilProponente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPerfilProponente  extends publicadores.DtProponente  implements java.io.Serializable {
    private publicadores.DtPropuesta[] prCanceladas;

    private publicadores.DtPropuesta[] prEnFinanciacion;

    private publicadores.DtPropuesta[] prFinanciadas;

    private publicadores.DtPropuesta[] prIngresadas;

    private publicadores.DtPropuesta[] prNoFinanciadas;

    private publicadores.DtPropuesta[] prPublicadas;

    public DtPerfilProponente() {
    }

    public DtPerfilProponente(
           java.lang.String apellido,
           java.lang.String email,
           java.util.Calendar fechaNacimiento,
           byte[] imagen,
           java.lang.String nickname,
           java.lang.String nombre,
           org.apache.axis.types.UnsignedShort[] password,
           java.lang.String passwordStr,
           java.lang.String[] tituloFavoritas,
           java.lang.String[] usuarioSeguidos,
           java.lang.String biografia,
           java.lang.String direccion,
           java.lang.String sitioWeb,
           publicadores.DtPropuesta[] prCanceladas,
           publicadores.DtPropuesta[] prEnFinanciacion,
           publicadores.DtPropuesta[] prFinanciadas,
           publicadores.DtPropuesta[] prIngresadas,
           publicadores.DtPropuesta[] prNoFinanciadas,
           publicadores.DtPropuesta[] prPublicadas) {
        super(
            apellido,
            email,
            fechaNacimiento,
            imagen,
            nickname,
            nombre,
            password,
            passwordStr,
            tituloFavoritas,
            usuarioSeguidos,
            biografia,
            direccion,
            sitioWeb);
        this.prCanceladas = prCanceladas;
        this.prEnFinanciacion = prEnFinanciacion;
        this.prFinanciadas = prFinanciadas;
        this.prIngresadas = prIngresadas;
        this.prNoFinanciadas = prNoFinanciadas;
        this.prPublicadas = prPublicadas;
    }


    /**
     * Gets the prCanceladas value for this DtPerfilProponente.
     * 
     * @return prCanceladas
     */
    public publicadores.DtPropuesta[] getPrCanceladas() {
        return prCanceladas;
    }


    /**
     * Sets the prCanceladas value for this DtPerfilProponente.
     * 
     * @param prCanceladas
     */
    public void setPrCanceladas(publicadores.DtPropuesta[] prCanceladas) {
        this.prCanceladas = prCanceladas;
    }

    public publicadores.DtPropuesta getPrCanceladas(int i) {
        return this.prCanceladas[i];
    }

    public void setPrCanceladas(int i, publicadores.DtPropuesta _value) {
        this.prCanceladas[i] = _value;
    }


    /**
     * Gets the prEnFinanciacion value for this DtPerfilProponente.
     * 
     * @return prEnFinanciacion
     */
    public publicadores.DtPropuesta[] getPrEnFinanciacion() {
        return prEnFinanciacion;
    }


    /**
     * Sets the prEnFinanciacion value for this DtPerfilProponente.
     * 
     * @param prEnFinanciacion
     */
    public void setPrEnFinanciacion(publicadores.DtPropuesta[] prEnFinanciacion) {
        this.prEnFinanciacion = prEnFinanciacion;
    }

    public publicadores.DtPropuesta getPrEnFinanciacion(int i) {
        return this.prEnFinanciacion[i];
    }

    public void setPrEnFinanciacion(int i, publicadores.DtPropuesta _value) {
        this.prEnFinanciacion[i] = _value;
    }


    /**
     * Gets the prFinanciadas value for this DtPerfilProponente.
     * 
     * @return prFinanciadas
     */
    public publicadores.DtPropuesta[] getPrFinanciadas() {
        return prFinanciadas;
    }


    /**
     * Sets the prFinanciadas value for this DtPerfilProponente.
     * 
     * @param prFinanciadas
     */
    public void setPrFinanciadas(publicadores.DtPropuesta[] prFinanciadas) {
        this.prFinanciadas = prFinanciadas;
    }

    public publicadores.DtPropuesta getPrFinanciadas(int i) {
        return this.prFinanciadas[i];
    }

    public void setPrFinanciadas(int i, publicadores.DtPropuesta _value) {
        this.prFinanciadas[i] = _value;
    }


    /**
     * Gets the prIngresadas value for this DtPerfilProponente.
     * 
     * @return prIngresadas
     */
    public publicadores.DtPropuesta[] getPrIngresadas() {
        return prIngresadas;
    }


    /**
     * Sets the prIngresadas value for this DtPerfilProponente.
     * 
     * @param prIngresadas
     */
    public void setPrIngresadas(publicadores.DtPropuesta[] prIngresadas) {
        this.prIngresadas = prIngresadas;
    }

    public publicadores.DtPropuesta getPrIngresadas(int i) {
        return this.prIngresadas[i];
    }

    public void setPrIngresadas(int i, publicadores.DtPropuesta _value) {
        this.prIngresadas[i] = _value;
    }


    /**
     * Gets the prNoFinanciadas value for this DtPerfilProponente.
     * 
     * @return prNoFinanciadas
     */
    public publicadores.DtPropuesta[] getPrNoFinanciadas() {
        return prNoFinanciadas;
    }


    /**
     * Sets the prNoFinanciadas value for this DtPerfilProponente.
     * 
     * @param prNoFinanciadas
     */
    public void setPrNoFinanciadas(publicadores.DtPropuesta[] prNoFinanciadas) {
        this.prNoFinanciadas = prNoFinanciadas;
    }

    public publicadores.DtPropuesta getPrNoFinanciadas(int i) {
        return this.prNoFinanciadas[i];
    }

    public void setPrNoFinanciadas(int i, publicadores.DtPropuesta _value) {
        this.prNoFinanciadas[i] = _value;
    }


    /**
     * Gets the prPublicadas value for this DtPerfilProponente.
     * 
     * @return prPublicadas
     */
    public publicadores.DtPropuesta[] getPrPublicadas() {
        return prPublicadas;
    }


    /**
     * Sets the prPublicadas value for this DtPerfilProponente.
     * 
     * @param prPublicadas
     */
    public void setPrPublicadas(publicadores.DtPropuesta[] prPublicadas) {
        this.prPublicadas = prPublicadas;
    }

    public publicadores.DtPropuesta getPrPublicadas(int i) {
        return this.prPublicadas[i];
    }

    public void setPrPublicadas(int i, publicadores.DtPropuesta _value) {
        this.prPublicadas[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPerfilProponente)) return false;
        DtPerfilProponente other = (DtPerfilProponente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.prCanceladas==null && other.getPrCanceladas()==null) || 
             (this.prCanceladas!=null &&
              java.util.Arrays.equals(this.prCanceladas, other.getPrCanceladas()))) &&
            ((this.prEnFinanciacion==null && other.getPrEnFinanciacion()==null) || 
             (this.prEnFinanciacion!=null &&
              java.util.Arrays.equals(this.prEnFinanciacion, other.getPrEnFinanciacion()))) &&
            ((this.prFinanciadas==null && other.getPrFinanciadas()==null) || 
             (this.prFinanciadas!=null &&
              java.util.Arrays.equals(this.prFinanciadas, other.getPrFinanciadas()))) &&
            ((this.prIngresadas==null && other.getPrIngresadas()==null) || 
             (this.prIngresadas!=null &&
              java.util.Arrays.equals(this.prIngresadas, other.getPrIngresadas()))) &&
            ((this.prNoFinanciadas==null && other.getPrNoFinanciadas()==null) || 
             (this.prNoFinanciadas!=null &&
              java.util.Arrays.equals(this.prNoFinanciadas, other.getPrNoFinanciadas()))) &&
            ((this.prPublicadas==null && other.getPrPublicadas()==null) || 
             (this.prPublicadas!=null &&
              java.util.Arrays.equals(this.prPublicadas, other.getPrPublicadas())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getPrCanceladas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrCanceladas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrCanceladas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPrEnFinanciacion() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrEnFinanciacion());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrEnFinanciacion(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPrFinanciadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrFinanciadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrFinanciadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPrIngresadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrIngresadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrIngresadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPrNoFinanciadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrNoFinanciadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrNoFinanciadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPrPublicadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrPublicadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrPublicadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtPerfilProponente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPerfilProponente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prCanceladas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prCanceladas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prEnFinanciacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prEnFinanciacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prFinanciadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prFinanciadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prIngresadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prIngresadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prNoFinanciadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prNoFinanciadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prPublicadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prPublicadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
