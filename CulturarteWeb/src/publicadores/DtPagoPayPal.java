/**
 * DtPagoPayPal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPagoPayPal  extends publicadores.DtPago  implements java.io.Serializable {
    private java.lang.String nombreTitular;

    private java.lang.String numeroCuenta;

    public DtPagoPayPal() {
    }

    public DtPagoPayPal(
           boolean compEmitido,
           java.util.Calendar fechaEmitido,
           java.lang.Long id,
           double montoAPagar,
           java.lang.String nombreTitular,
           java.lang.String numeroCuenta) {
        super(
            compEmitido,
            fechaEmitido,
            id,
            montoAPagar);
        this.nombreTitular = nombreTitular;
        this.numeroCuenta = numeroCuenta;
    }


    /**
     * Gets the nombreTitular value for this DtPagoPayPal.
     * 
     * @return nombreTitular
     */
    public java.lang.String getNombreTitular() {
        return nombreTitular;
    }


    /**
     * Sets the nombreTitular value for this DtPagoPayPal.
     * 
     * @param nombreTitular
     */
    public void setNombreTitular(java.lang.String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }


    /**
     * Gets the numeroCuenta value for this DtPagoPayPal.
     * 
     * @return numeroCuenta
     */
    public java.lang.String getNumeroCuenta() {
        return numeroCuenta;
    }


    /**
     * Sets the numeroCuenta value for this DtPagoPayPal.
     * 
     * @param numeroCuenta
     */
    public void setNumeroCuenta(java.lang.String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPagoPayPal)) return false;
        DtPagoPayPal other = (DtPagoPayPal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.nombreTitular==null && other.getNombreTitular()==null) || 
             (this.nombreTitular!=null &&
              this.nombreTitular.equals(other.getNombreTitular()))) &&
            ((this.numeroCuenta==null && other.getNumeroCuenta()==null) || 
             (this.numeroCuenta!=null &&
              this.numeroCuenta.equals(other.getNumeroCuenta())));
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
        if (getNombreTitular() != null) {
            _hashCode += getNombreTitular().hashCode();
        }
        if (getNumeroCuenta() != null) {
            _hashCode += getNumeroCuenta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtPagoPayPal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPagoPayPal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCuenta"));
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
