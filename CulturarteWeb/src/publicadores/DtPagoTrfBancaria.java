/**
 * DtPagoTrfBancaria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPagoTrfBancaria  extends publicadores.DtPago  implements java.io.Serializable {
    private java.lang.String nombreBanco;

    private java.lang.String nombreTitular;

    private java.lang.String numCuenta;

    public DtPagoTrfBancaria() {
    }

    public DtPagoTrfBancaria(
           boolean compEmitido,
           java.util.Calendar fechaEmitido,
           java.lang.Long id,
           double montoAPagar,
           java.lang.String nombreBanco,
           java.lang.String nombreTitular,
           java.lang.String numCuenta) {
        super(
            compEmitido,
            fechaEmitido,
            id,
            montoAPagar);
        this.nombreBanco = nombreBanco;
        this.nombreTitular = nombreTitular;
        this.numCuenta = numCuenta;
    }


    /**
     * Gets the nombreBanco value for this DtPagoTrfBancaria.
     * 
     * @return nombreBanco
     */
    public java.lang.String getNombreBanco() {
        return nombreBanco;
    }


    /**
     * Sets the nombreBanco value for this DtPagoTrfBancaria.
     * 
     * @param nombreBanco
     */
    public void setNombreBanco(java.lang.String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }


    /**
     * Gets the nombreTitular value for this DtPagoTrfBancaria.
     * 
     * @return nombreTitular
     */
    public java.lang.String getNombreTitular() {
        return nombreTitular;
    }


    /**
     * Sets the nombreTitular value for this DtPagoTrfBancaria.
     * 
     * @param nombreTitular
     */
    public void setNombreTitular(java.lang.String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }


    /**
     * Gets the numCuenta value for this DtPagoTrfBancaria.
     * 
     * @return numCuenta
     */
    public java.lang.String getNumCuenta() {
        return numCuenta;
    }


    /**
     * Sets the numCuenta value for this DtPagoTrfBancaria.
     * 
     * @param numCuenta
     */
    public void setNumCuenta(java.lang.String numCuenta) {
        this.numCuenta = numCuenta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPagoTrfBancaria)) return false;
        DtPagoTrfBancaria other = (DtPagoTrfBancaria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.nombreBanco==null && other.getNombreBanco()==null) || 
             (this.nombreBanco!=null &&
              this.nombreBanco.equals(other.getNombreBanco()))) &&
            ((this.nombreTitular==null && other.getNombreTitular()==null) || 
             (this.nombreTitular!=null &&
              this.nombreTitular.equals(other.getNombreTitular()))) &&
            ((this.numCuenta==null && other.getNumCuenta()==null) || 
             (this.numCuenta!=null &&
              this.numCuenta.equals(other.getNumCuenta())));
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
        if (getNombreBanco() != null) {
            _hashCode += getNombreBanco().hashCode();
        }
        if (getNombreTitular() != null) {
            _hashCode += getNombreTitular().hashCode();
        }
        if (getNumCuenta() != null) {
            _hashCode += getNumCuenta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtPagoTrfBancaria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPagoTrfBancaria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreBanco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreBanco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numCuenta"));
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
