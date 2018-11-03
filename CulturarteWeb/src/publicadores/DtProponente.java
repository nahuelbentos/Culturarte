/**
 * DtProponente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtProponente  extends publicadores.DtUsuario  implements java.io.Serializable {
    private java.lang.String biografia;

    private java.lang.String direccion;

    private java.lang.String sitioWeb;

    public DtProponente() {
    }

    public DtProponente(
           java.lang.String apellido,
           java.lang.String email,
           java.util.Calendar fechaNacimiento,
           byte[] imagen,
           java.lang.String nickname,
           java.lang.String nombre,
           org.apache.axis.types.UnsignedShort[] password,
           java.lang.String passwordString,
           java.lang.String[] tituloFavoritas,
           java.lang.String[] usuarioSeguidos,
           java.lang.String biografia,
           java.lang.String direccion,
           java.lang.String sitioWeb) {
        super(
            apellido,
            email,
            fechaNacimiento,
            imagen,
            nickname,
            nombre,
            password,
            passwordString,
            tituloFavoritas,
            usuarioSeguidos);
        this.biografia = biografia;
        this.direccion = direccion;
        this.sitioWeb = sitioWeb;
    }


    /**
     * Gets the biografia value for this DtProponente.
     * 
     * @return biografia
     */
    public java.lang.String getBiografia() {
        return biografia;
    }


    /**
     * Sets the biografia value for this DtProponente.
     * 
     * @param biografia
     */
    public void setBiografia(java.lang.String biografia) {
        this.biografia = biografia;
    }


    /**
     * Gets the direccion value for this DtProponente.
     * 
     * @return direccion
     */
    public java.lang.String getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this DtProponente.
     * 
     * @param direccion
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }


    /**
     * Gets the sitioWeb value for this DtProponente.
     * 
     * @return sitioWeb
     */
    public java.lang.String getSitioWeb() {
        return sitioWeb;
    }


    /**
     * Sets the sitioWeb value for this DtProponente.
     * 
     * @param sitioWeb
     */
    public void setSitioWeb(java.lang.String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtProponente)) return false;
        DtProponente other = (DtProponente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.biografia==null && other.getBiografia()==null) || 
             (this.biografia!=null &&
              this.biografia.equals(other.getBiografia()))) &&
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.sitioWeb==null && other.getSitioWeb()==null) || 
             (this.sitioWeb!=null &&
              this.sitioWeb.equals(other.getSitioWeb())));
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
        if (getBiografia() != null) {
            _hashCode += getBiografia().hashCode();
        }
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getSitioWeb() != null) {
            _hashCode += getSitioWeb().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtProponente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtProponente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("biografia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "biografia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sitioWeb");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sitioWeb"));
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
