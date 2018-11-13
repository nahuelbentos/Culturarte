/**
 * DtColaboracion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtColaboracion  implements java.io.Serializable {
    private java.lang.String colaborador;

    private boolean compEmitido;

    private java.util.Calendar fechaAporte;

    private double monto;

    private boolean pago;

    private publicadores.TipoRetorno tipo;

    private java.lang.String tituloPropuesta;

    public DtColaboracion() {
    }

    public DtColaboracion(
           java.lang.String colaborador,
           boolean compEmitido,
           java.util.Calendar fechaAporte,
           double monto,
           boolean pago,
           publicadores.TipoRetorno tipo,
           java.lang.String tituloPropuesta) {
           this.colaborador = colaborador;
           this.compEmitido = compEmitido;
           this.fechaAporte = fechaAporte;
           this.monto = monto;
           this.pago = pago;
           this.tipo = tipo;
           this.tituloPropuesta = tituloPropuesta;
    }


    /**
     * Gets the colaborador value for this DtColaboracion.
     * 
     * @return colaborador
     */
    public java.lang.String getColaborador() {
        return colaborador;
    }


    /**
     * Sets the colaborador value for this DtColaboracion.
     * 
     * @param colaborador
     */
    public void setColaborador(java.lang.String colaborador) {
        this.colaborador = colaborador;
    }


    /**
     * Gets the compEmitido value for this DtColaboracion.
     * 
     * @return compEmitido
     */
    public boolean isCompEmitido() {
        return compEmitido;
    }


    /**
     * Sets the compEmitido value for this DtColaboracion.
     * 
     * @param compEmitido
     */
    public void setCompEmitido(boolean compEmitido) {
        this.compEmitido = compEmitido;
    }


    /**
     * Gets the fechaAporte value for this DtColaboracion.
     * 
     * @return fechaAporte
     */
    public java.util.Calendar getFechaAporte() {
        return fechaAporte;
    }


    /**
     * Sets the fechaAporte value for this DtColaboracion.
     * 
     * @param fechaAporte
     */
    public void setFechaAporte(java.util.Calendar fechaAporte) {
        this.fechaAporte = fechaAporte;
    }


    /**
     * Gets the monto value for this DtColaboracion.
     * 
     * @return monto
     */
    public double getMonto() {
        return monto;
    }


    /**
     * Sets the monto value for this DtColaboracion.
     * 
     * @param monto
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }


    /**
     * Gets the pago value for this DtColaboracion.
     * 
     * @return pago
     */
    public boolean isPago() {
        return pago;
    }


    /**
     * Sets the pago value for this DtColaboracion.
     * 
     * @param pago
     */
    public void setPago(boolean pago) {
        this.pago = pago;
    }


    /**
     * Gets the tipo value for this DtColaboracion.
     * 
     * @return tipo
     */
    public publicadores.TipoRetorno getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this DtColaboracion.
     * 
     * @param tipo
     */
    public void setTipo(publicadores.TipoRetorno tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the tituloPropuesta value for this DtColaboracion.
     * 
     * @return tituloPropuesta
     */
    public java.lang.String getTituloPropuesta() {
        return tituloPropuesta;
    }


    /**
     * Sets the tituloPropuesta value for this DtColaboracion.
     * 
     * @param tituloPropuesta
     */
    public void setTituloPropuesta(java.lang.String tituloPropuesta) {
        this.tituloPropuesta = tituloPropuesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtColaboracion)) return false;
        DtColaboracion other = (DtColaboracion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.colaborador==null && other.getColaborador()==null) || 
             (this.colaborador!=null &&
              this.colaborador.equals(other.getColaborador()))) &&
            this.compEmitido == other.isCompEmitido() &&
            ((this.fechaAporte==null && other.getFechaAporte()==null) || 
             (this.fechaAporte!=null &&
              this.fechaAporte.equals(other.getFechaAporte()))) &&
            this.monto == other.getMonto() &&
            this.pago == other.isPago() &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.tituloPropuesta==null && other.getTituloPropuesta()==null) || 
             (this.tituloPropuesta!=null &&
              this.tituloPropuesta.equals(other.getTituloPropuesta())));
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
        if (getColaborador() != null) {
            _hashCode += getColaborador().hashCode();
        }
        _hashCode += (isCompEmitido() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFechaAporte() != null) {
            _hashCode += getFechaAporte().hashCode();
        }
        _hashCode += new Double(getMonto()).hashCode();
        _hashCode += (isPago() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getTituloPropuesta() != null) {
            _hashCode += getTituloPropuesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtColaboracion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtColaboracion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colaborador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "colaborador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compEmitido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "compEmitido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaAporte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaAporte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "monto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("tituloPropuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tituloPropuesta"));
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
