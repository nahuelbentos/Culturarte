/**
 * DtCategoria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtCategoria  implements java.io.Serializable {
    private java.lang.String nombre;

    private publicadores.DtCategoria[] subCategorias;

    private publicadores.DtCategoria[] superCategorias;

    public DtCategoria() {
    }

    public DtCategoria(
           java.lang.String nombre,
           publicadores.DtCategoria[] subCategorias,
           publicadores.DtCategoria[] superCategorias) {
           this.nombre = nombre;
           this.subCategorias = subCategorias;
           this.superCategorias = superCategorias;
    }


    /**
     * Gets the nombre value for this DtCategoria.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this DtCategoria.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the subCategorias value for this DtCategoria.
     * 
     * @return subCategorias
     */
    public publicadores.DtCategoria[] getSubCategorias() {
        return subCategorias;
    }


    /**
     * Sets the subCategorias value for this DtCategoria.
     * 
     * @param subCategorias
     */
    public void setSubCategorias(publicadores.DtCategoria[] subCategorias) {
        this.subCategorias = subCategorias;
    }

    public publicadores.DtCategoria getSubCategorias(int i) {
        return this.subCategorias[i];
    }

    public void setSubCategorias(int i, publicadores.DtCategoria _value) {
        this.subCategorias[i] = _value;
    }


    /**
     * Gets the superCategorias value for this DtCategoria.
     * 
     * @return superCategorias
     */
    public publicadores.DtCategoria[] getSuperCategorias() {
        return superCategorias;
    }


    /**
     * Sets the superCategorias value for this DtCategoria.
     * 
     * @param superCategorias
     */
    public void setSuperCategorias(publicadores.DtCategoria[] superCategorias) {
        this.superCategorias = superCategorias;
    }

    public publicadores.DtCategoria getSuperCategorias(int i) {
        return this.superCategorias[i];
    }

    public void setSuperCategorias(int i, publicadores.DtCategoria _value) {
        this.superCategorias[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtCategoria)) return false;
        DtCategoria other = (DtCategoria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.subCategorias==null && other.getSubCategorias()==null) || 
             (this.subCategorias!=null &&
              java.util.Arrays.equals(this.subCategorias, other.getSubCategorias()))) &&
            ((this.superCategorias==null && other.getSuperCategorias()==null) || 
             (this.superCategorias!=null &&
              java.util.Arrays.equals(this.superCategorias, other.getSuperCategorias())));
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
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getSubCategorias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubCategorias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubCategorias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSuperCategorias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSuperCategorias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSuperCategorias(), i);
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
        new org.apache.axis.description.TypeDesc(DtCategoria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtCategoria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subCategorias");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subCategorias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtCategoria"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("superCategorias");
        elemField.setXmlName(new javax.xml.namespace.QName("", "superCategorias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtCategoria"));
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
