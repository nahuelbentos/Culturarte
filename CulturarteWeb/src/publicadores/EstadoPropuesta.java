/**
 * EstadoPropuesta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class EstadoPropuesta implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EstadoPropuesta(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _ingresada = "ingresada";
    public static final java.lang.String _publicada = "publicada";
    public static final java.lang.String _enFinanciacion = "enFinanciacion";
    public static final java.lang.String _financiada = "financiada";
    public static final java.lang.String _noFinanciada = "noFinanciada";
    public static final java.lang.String _cancelada = "cancelada";
    public static final EstadoPropuesta ingresada = new EstadoPropuesta(_ingresada);
    public static final EstadoPropuesta publicada = new EstadoPropuesta(_publicada);
    public static final EstadoPropuesta enFinanciacion = new EstadoPropuesta(_enFinanciacion);
    public static final EstadoPropuesta financiada = new EstadoPropuesta(_financiada);
    public static final EstadoPropuesta noFinanciada = new EstadoPropuesta(_noFinanciada);
    public static final EstadoPropuesta cancelada = new EstadoPropuesta(_cancelada);
    public java.lang.String getValue() { return _value_;}
    public static EstadoPropuesta fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EstadoPropuesta enumeration = (EstadoPropuesta)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EstadoPropuesta fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstadoPropuesta.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "estadoPropuesta"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
