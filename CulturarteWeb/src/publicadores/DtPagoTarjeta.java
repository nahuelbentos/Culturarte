/**
 * DtPagoTarjeta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPagoTarjeta  extends publicadores.DtPago  implements java.io.Serializable {
    private int cvc;

    private java.util.Calendar fechaVenc;

    private java.lang.String nombreTitular;

    private double nroTarjeta;

    private publicadores.TipoTarjeta tipoTarjeta;

    public DtPagoTarjeta() {
    }

    public DtPagoTarjeta(
           boolean compEmitido,
           java.util.Calendar fechaEmitido,
           java.lang.Long id,
           double montoAPagar,
           int cvc,
           java.util.Calendar fechaVenc,
           java.lang.String nombreTitular,
           double nroTarjeta,
           publicadores.TipoTarjeta tipoTarjeta) {
        super(
            compEmitido,
            fechaEmitido,
            id,
            montoAPagar);
        this.cvc = cvc;
        this.fechaVenc = fechaVenc;
        this.nombreTitular = nombreTitular;
        this.nroTarjeta = nroTarjeta;
        this.tipoTarjeta = tipoTarjeta;
    }


    /**
     * Gets the cvc value for this DtPagoTarjeta.
     * 
     * @return cvc
     */
    public int getCvc() {
        return cvc;
    }


    /**
     * Sets the cvc value for this DtPagoTarjeta.
     * 
     * @param cvc
     */
    public void setCvc(int cvc) {
        this.cvc = cvc;
    }


    /**
     * Gets the fechaVenc value for this DtPagoTarjeta.
     * 
     * @return fechaVenc
     */
    public java.util.Calendar getFechaVenc() {
        return fechaVenc;
    }


    /**
     * Sets the fechaVenc value for this DtPagoTarjeta.
     * 
     * @param fechaVenc
     */
    public void setFechaVenc(java.util.Calendar fechaVenc) {
        this.fechaVenc = fechaVenc;
    }


    /**
     * Gets the nombreTitular value for this DtPagoTarjeta.
     * 
     * @return nombreTitular
     */
    public java.lang.String getNombreTitular() {
        return nombreTitular;
    }


    /**
     * Sets the nombreTitular value for this DtPagoTarjeta.
     * 
     * @param nombreTitular
     */
    public void setNombreTitular(java.lang.String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }


    /**
     * Gets the nroTarjeta value for this DtPagoTarjeta.
     * 
     * @return nroTarjeta
     */
    public double getNroTarjeta() {
        return nroTarjeta;
    }


    /**
     * Sets the nroTarjeta value for this DtPagoTarjeta.
     * 
     * @param nroTarjeta
     */
    public void setNroTarjeta(double nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }


    /**
     * Gets the tipoTarjeta value for this DtPagoTarjeta.
     * 
     * @return tipoTarjeta
     */
    public publicadores.TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }


    /**
     * Sets the tipoTarjeta value for this DtPagoTarjeta.
     * 
     * @param tipoTarjeta
     */
    public void setTipoTarjeta(publicadores.TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPagoTarjeta)) return false;
        DtPagoTarjeta other = (DtPagoTarjeta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.cvc == other.getCvc() &&
            ((this.fechaVenc==null && other.getFechaVenc()==null) || 
             (this.fechaVenc!=null &&
              this.fechaVenc.equals(other.getFechaVenc()))) &&
            ((this.nombreTitular==null && other.getNombreTitular()==null) || 
             (this.nombreTitular!=null &&
              this.nombreTitular.equals(other.getNombreTitular()))) &&
            this.nroTarjeta == other.getNroTarjeta() &&
            ((this.tipoTarjeta==null && other.getTipoTarjeta()==null) || 
             (this.tipoTarjeta!=null &&
              this.tipoTarjeta.equals(other.getTipoTarjeta())));
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
        _hashCode += getCvc();
        if (getFechaVenc() != null) {
            _hashCode += getFechaVenc().hashCode();
        }
        if (getNombreTitular() != null) {
            _hashCode += getNombreTitular().hashCode();
        }
        _hashCode += new Double(getNroTarjeta()).hashCode();
        if (getTipoTarjeta() != null) {
            _hashCode += getTipoTarjeta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtPagoTarjeta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPagoTarjeta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cvc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cvc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaVenc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaVenc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("nroTarjeta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nroTarjeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoTarjeta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoTarjeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "tipoTarjeta"));
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
