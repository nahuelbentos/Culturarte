/**
 * DtInfoPago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtInfoPago  implements java.io.Serializable {
    private java.lang.String nickColaborador;

    private publicadores.DtPago pago;

    private java.lang.String titPropuesta;

    public DtInfoPago() {
    }

    public DtInfoPago(
           java.lang.String nickColaborador,
           publicadores.DtPago pago,
           java.lang.String titPropuesta) {
           this.nickColaborador = nickColaborador;
           this.pago = pago;
           this.titPropuesta = titPropuesta;
    }


    /**
     * Gets the nickColaborador value for this DtInfoPago.
     * 
     * @return nickColaborador
     */
    public java.lang.String getNickColaborador() {
        return nickColaborador;
    }


    /**
     * Sets the nickColaborador value for this DtInfoPago.
     * 
     * @param nickColaborador
     */
    public void setNickColaborador(java.lang.String nickColaborador) {
        this.nickColaborador = nickColaborador;
    }


    /**
     * Gets the pago value for this DtInfoPago.
     * 
     * @return pago
     */
    public publicadores.DtPago getPago() {
        return pago;
    }


    /**
     * Sets the pago value for this DtInfoPago.
     * 
     * @param pago
     */
    public void setPago(publicadores.DtPago pago) {
        this.pago = pago;
    }


    /**
     * Gets the titPropuesta value for this DtInfoPago.
     * 
     * @return titPropuesta
     */
    public java.lang.String getTitPropuesta() {
        return titPropuesta;
    }


    /**
     * Sets the titPropuesta value for this DtInfoPago.
     * 
     * @param titPropuesta
     */
    public void setTitPropuesta(java.lang.String titPropuesta) {
        this.titPropuesta = titPropuesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtInfoPago)) return false;
        DtInfoPago other = (DtInfoPago) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nickColaborador==null && other.getNickColaborador()==null) || 
             (this.nickColaborador!=null &&
              this.nickColaborador.equals(other.getNickColaborador()))) &&
            ((this.pago==null && other.getPago()==null) || 
             (this.pago!=null &&
              this.pago.equals(other.getPago()))) &&
            ((this.titPropuesta==null && other.getTitPropuesta()==null) || 
             (this.titPropuesta!=null &&
              this.titPropuesta.equals(other.getTitPropuesta())));
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
        if (getNickColaborador() != null) {
            _hashCode += getNickColaborador().hashCode();
        }
        if (getPago() != null) {
            _hashCode += getPago().hashCode();
        }
        if (getTitPropuesta() != null) {
            _hashCode += getTitPropuesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtInfoPago.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtInfoPago"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nickColaborador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nickColaborador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPago"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titPropuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titPropuesta"));
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
