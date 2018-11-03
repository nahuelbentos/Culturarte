/**
 * DtPerfilUsuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtPerfilUsuario  extends publicadores.DtUsuario  implements java.io.Serializable {
    private publicadores.DtColaboracion[] colaboracionesHechas;

    private publicadores.DtPropuestaColaborada[] propuestasColaboradas;

    private publicadores.DtPropuesta[] propuestasCreadas;

    private publicadores.DtPropuesta[] propuestasFavoritas;

    private publicadores.DtPropuesta[] propuestasPublicadas;

    private publicadores.DtColaborador[] seguidoresColaboradores;

    private publicadores.DtProponente[] seguidoresProponentes;

    private publicadores.DtColaborador[] seguidosColaboradores;

    private publicadores.DtProponente[] seguidosProponentes;

    public DtPerfilUsuario() {
    }

    public DtPerfilUsuario(
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
           publicadores.DtColaboracion[] colaboracionesHechas,
           publicadores.DtPropuestaColaborada[] propuestasColaboradas,
           publicadores.DtPropuesta[] propuestasCreadas,
           publicadores.DtPropuesta[] propuestasFavoritas,
           publicadores.DtPropuesta[] propuestasPublicadas,
           publicadores.DtColaborador[] seguidoresColaboradores,
           publicadores.DtProponente[] seguidoresProponentes,
           publicadores.DtColaborador[] seguidosColaboradores,
           publicadores.DtProponente[] seguidosProponentes) {
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
        this.colaboracionesHechas = colaboracionesHechas;
        this.propuestasColaboradas = propuestasColaboradas;
        this.propuestasCreadas = propuestasCreadas;
        this.propuestasFavoritas = propuestasFavoritas;
        this.propuestasPublicadas = propuestasPublicadas;
        this.seguidoresColaboradores = seguidoresColaboradores;
        this.seguidoresProponentes = seguidoresProponentes;
        this.seguidosColaboradores = seguidosColaboradores;
        this.seguidosProponentes = seguidosProponentes;
    }


    /**
     * Gets the colaboracionesHechas value for this DtPerfilUsuario.
     * 
     * @return colaboracionesHechas
     */
    public publicadores.DtColaboracion[] getColaboracionesHechas() {
        return colaboracionesHechas;
    }


    /**
     * Sets the colaboracionesHechas value for this DtPerfilUsuario.
     * 
     * @param colaboracionesHechas
     */
    public void setColaboracionesHechas(publicadores.DtColaboracion[] colaboracionesHechas) {
        this.colaboracionesHechas = colaboracionesHechas;
    }

    public publicadores.DtColaboracion getColaboracionesHechas(int i) {
        return this.colaboracionesHechas[i];
    }

    public void setColaboracionesHechas(int i, publicadores.DtColaboracion _value) {
        this.colaboracionesHechas[i] = _value;
    }


    /**
     * Gets the propuestasColaboradas value for this DtPerfilUsuario.
     * 
     * @return propuestasColaboradas
     */
    public publicadores.DtPropuestaColaborada[] getPropuestasColaboradas() {
        return propuestasColaboradas;
    }


    /**
     * Sets the propuestasColaboradas value for this DtPerfilUsuario.
     * 
     * @param propuestasColaboradas
     */
    public void setPropuestasColaboradas(publicadores.DtPropuestaColaborada[] propuestasColaboradas) {
        this.propuestasColaboradas = propuestasColaboradas;
    }

    public publicadores.DtPropuestaColaborada getPropuestasColaboradas(int i) {
        return this.propuestasColaboradas[i];
    }

    public void setPropuestasColaboradas(int i, publicadores.DtPropuestaColaborada _value) {
        this.propuestasColaboradas[i] = _value;
    }


    /**
     * Gets the propuestasCreadas value for this DtPerfilUsuario.
     * 
     * @return propuestasCreadas
     */
    public publicadores.DtPropuesta[] getPropuestasCreadas() {
        return propuestasCreadas;
    }


    /**
     * Sets the propuestasCreadas value for this DtPerfilUsuario.
     * 
     * @param propuestasCreadas
     */
    public void setPropuestasCreadas(publicadores.DtPropuesta[] propuestasCreadas) {
        this.propuestasCreadas = propuestasCreadas;
    }

    public publicadores.DtPropuesta getPropuestasCreadas(int i) {
        return this.propuestasCreadas[i];
    }

    public void setPropuestasCreadas(int i, publicadores.DtPropuesta _value) {
        this.propuestasCreadas[i] = _value;
    }


    /**
     * Gets the propuestasFavoritas value for this DtPerfilUsuario.
     * 
     * @return propuestasFavoritas
     */
    public publicadores.DtPropuesta[] getPropuestasFavoritas() {
        return propuestasFavoritas;
    }


    /**
     * Sets the propuestasFavoritas value for this DtPerfilUsuario.
     * 
     * @param propuestasFavoritas
     */
    public void setPropuestasFavoritas(publicadores.DtPropuesta[] propuestasFavoritas) {
        this.propuestasFavoritas = propuestasFavoritas;
    }

    public publicadores.DtPropuesta getPropuestasFavoritas(int i) {
        return this.propuestasFavoritas[i];
    }

    public void setPropuestasFavoritas(int i, publicadores.DtPropuesta _value) {
        this.propuestasFavoritas[i] = _value;
    }


    /**
     * Gets the propuestasPublicadas value for this DtPerfilUsuario.
     * 
     * @return propuestasPublicadas
     */
    public publicadores.DtPropuesta[] getPropuestasPublicadas() {
        return propuestasPublicadas;
    }


    /**
     * Sets the propuestasPublicadas value for this DtPerfilUsuario.
     * 
     * @param propuestasPublicadas
     */
    public void setPropuestasPublicadas(publicadores.DtPropuesta[] propuestasPublicadas) {
        this.propuestasPublicadas = propuestasPublicadas;
    }

    public publicadores.DtPropuesta getPropuestasPublicadas(int i) {
        return this.propuestasPublicadas[i];
    }

    public void setPropuestasPublicadas(int i, publicadores.DtPropuesta _value) {
        this.propuestasPublicadas[i] = _value;
    }


    /**
     * Gets the seguidoresColaboradores value for this DtPerfilUsuario.
     * 
     * @return seguidoresColaboradores
     */
    public publicadores.DtColaborador[] getSeguidoresColaboradores() {
        return seguidoresColaboradores;
    }


    /**
     * Sets the seguidoresColaboradores value for this DtPerfilUsuario.
     * 
     * @param seguidoresColaboradores
     */
    public void setSeguidoresColaboradores(publicadores.DtColaborador[] seguidoresColaboradores) {
        this.seguidoresColaboradores = seguidoresColaboradores;
    }

    public publicadores.DtColaborador getSeguidoresColaboradores(int i) {
        return this.seguidoresColaboradores[i];
    }

    public void setSeguidoresColaboradores(int i, publicadores.DtColaborador _value) {
        this.seguidoresColaboradores[i] = _value;
    }


    /**
     * Gets the seguidoresProponentes value for this DtPerfilUsuario.
     * 
     * @return seguidoresProponentes
     */
    public publicadores.DtProponente[] getSeguidoresProponentes() {
        return seguidoresProponentes;
    }


    /**
     * Sets the seguidoresProponentes value for this DtPerfilUsuario.
     * 
     * @param seguidoresProponentes
     */
    public void setSeguidoresProponentes(publicadores.DtProponente[] seguidoresProponentes) {
        this.seguidoresProponentes = seguidoresProponentes;
    }

    public publicadores.DtProponente getSeguidoresProponentes(int i) {
        return this.seguidoresProponentes[i];
    }

    public void setSeguidoresProponentes(int i, publicadores.DtProponente _value) {
        this.seguidoresProponentes[i] = _value;
    }


    /**
     * Gets the seguidosColaboradores value for this DtPerfilUsuario.
     * 
     * @return seguidosColaboradores
     */
    public publicadores.DtColaborador[] getSeguidosColaboradores() {
        return seguidosColaboradores;
    }


    /**
     * Sets the seguidosColaboradores value for this DtPerfilUsuario.
     * 
     * @param seguidosColaboradores
     */
    public void setSeguidosColaboradores(publicadores.DtColaborador[] seguidosColaboradores) {
        this.seguidosColaboradores = seguidosColaboradores;
    }

    public publicadores.DtColaborador getSeguidosColaboradores(int i) {
        return this.seguidosColaboradores[i];
    }

    public void setSeguidosColaboradores(int i, publicadores.DtColaborador _value) {
        this.seguidosColaboradores[i] = _value;
    }


    /**
     * Gets the seguidosProponentes value for this DtPerfilUsuario.
     * 
     * @return seguidosProponentes
     */
    public publicadores.DtProponente[] getSeguidosProponentes() {
        return seguidosProponentes;
    }


    /**
     * Sets the seguidosProponentes value for this DtPerfilUsuario.
     * 
     * @param seguidosProponentes
     */
    public void setSeguidosProponentes(publicadores.DtProponente[] seguidosProponentes) {
        this.seguidosProponentes = seguidosProponentes;
    }

    public publicadores.DtProponente getSeguidosProponentes(int i) {
        return this.seguidosProponentes[i];
    }

    public void setSeguidosProponentes(int i, publicadores.DtProponente _value) {
        this.seguidosProponentes[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtPerfilUsuario)) return false;
        DtPerfilUsuario other = (DtPerfilUsuario) obj;
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
              java.util.Arrays.equals(this.colaboracionesHechas, other.getColaboracionesHechas()))) &&
            ((this.propuestasColaboradas==null && other.getPropuestasColaboradas()==null) || 
             (this.propuestasColaboradas!=null &&
              java.util.Arrays.equals(this.propuestasColaboradas, other.getPropuestasColaboradas()))) &&
            ((this.propuestasCreadas==null && other.getPropuestasCreadas()==null) || 
             (this.propuestasCreadas!=null &&
              java.util.Arrays.equals(this.propuestasCreadas, other.getPropuestasCreadas()))) &&
            ((this.propuestasFavoritas==null && other.getPropuestasFavoritas()==null) || 
             (this.propuestasFavoritas!=null &&
              java.util.Arrays.equals(this.propuestasFavoritas, other.getPropuestasFavoritas()))) &&
            ((this.propuestasPublicadas==null && other.getPropuestasPublicadas()==null) || 
             (this.propuestasPublicadas!=null &&
              java.util.Arrays.equals(this.propuestasPublicadas, other.getPropuestasPublicadas()))) &&
            ((this.seguidoresColaboradores==null && other.getSeguidoresColaboradores()==null) || 
             (this.seguidoresColaboradores!=null &&
              java.util.Arrays.equals(this.seguidoresColaboradores, other.getSeguidoresColaboradores()))) &&
            ((this.seguidoresProponentes==null && other.getSeguidoresProponentes()==null) || 
             (this.seguidoresProponentes!=null &&
              java.util.Arrays.equals(this.seguidoresProponentes, other.getSeguidoresProponentes()))) &&
            ((this.seguidosColaboradores==null && other.getSeguidosColaboradores()==null) || 
             (this.seguidosColaboradores!=null &&
              java.util.Arrays.equals(this.seguidosColaboradores, other.getSeguidosColaboradores()))) &&
            ((this.seguidosProponentes==null && other.getSeguidosProponentes()==null) || 
             (this.seguidosProponentes!=null &&
              java.util.Arrays.equals(this.seguidosProponentes, other.getSeguidosProponentes())));
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
        if (getPropuestasColaboradas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPropuestasColaboradas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPropuestasColaboradas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPropuestasCreadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPropuestasCreadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPropuestasCreadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPropuestasFavoritas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPropuestasFavoritas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPropuestasFavoritas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPropuestasPublicadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPropuestasPublicadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPropuestasPublicadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSeguidoresColaboradores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeguidoresColaboradores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSeguidoresColaboradores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSeguidoresProponentes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeguidoresProponentes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSeguidoresProponentes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSeguidosColaboradores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeguidosColaboradores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSeguidosColaboradores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSeguidosProponentes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeguidosProponentes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSeguidosProponentes(), i);
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
        new org.apache.axis.description.TypeDesc(DtPerfilUsuario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPerfilUsuario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colaboracionesHechas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "colaboracionesHechas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtColaboracion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propuestasColaboradas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propuestasColaboradas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuestaColaborada"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propuestasCreadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propuestasCreadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propuestasFavoritas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propuestasFavoritas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propuestasPublicadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propuestasPublicadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtPropuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seguidoresColaboradores");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seguidoresColaboradores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtColaborador"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seguidoresProponentes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seguidoresProponentes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtProponente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seguidosColaboradores");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seguidosColaboradores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtColaborador"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seguidosProponentes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seguidosProponentes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtProponente"));
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
