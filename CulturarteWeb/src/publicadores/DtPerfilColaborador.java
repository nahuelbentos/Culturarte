/**
 * DtPerfilColaborador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPerfilColaborador  extends publicadores.DtUsuario  implements java.io.Serializable {
    private publicadores.DtPropuestaColaborada[] colaboracionesHechas;

    public DtPerfilColaborador() {
    }

    public DtPerfilColaborador(
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
           publicadores.DtPropuestaColaborada[] colaboracionesHechas) {
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
            usuarioSeguidos);
        this.colaboracionesHechas = colaboracionesHechas;
    }


    /**
     * Gets the colaboracionesHechas value for this DtPerfilColaborador.
     * 
     * @return colaboracionesHechas
     */
    public publicadores.DtPropuestaColaborada[] getColaboracionesHechas() {
        return colaboracionesHechas;
    }


    /**
     * Sets the colaboracionesHechas value for this DtPerfilColaborador.
     * 
     * @param colaboracionesHechas
     */
    public void setColaboracionesHechas(publicadores.DtPropuestaColaborada[] colaboracionesHechas) {
        this.colaboracionesHechas = colaboracionesHechas;
    }

    public publicadores.DtPropuestaColaborada getColaboracionesHechas(int i) {
        return this.colaboracionesHechas[i];
    }

    public void setColaboracionesHechas(int i, publicadores.DtPropuestaColaborada _value) {
        this.colaboracionesHechas[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPerfilColaborador)) return false;
        DtPerfilColaborador other = (DtPerfilColaborador) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.colaboracionesHechas==null && other.getColaboracionesHechas()==null) || 
             (this.colaboracionesHechas!=null &&
              java.util.Arrays.equals(this.colaboracionesHechas, other.getColaboracionesHechas())));
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
        if (getColaboracionesHechas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColaboracionesHechas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColaboracionesHechas(), i);
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
        new org.apache.axis.description.TypeDesc(DtPerfilColaborador.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPerfilColaborador"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colaboracionesHechas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "colaboracionesHechas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuestaColaborada"));
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
