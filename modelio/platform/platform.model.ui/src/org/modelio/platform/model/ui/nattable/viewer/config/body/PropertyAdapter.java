/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.platform.model.ui.nattable.viewer.config.body;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._float.DefaultFloatNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._integer.DefaultIntegerNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._unsigned.DefaultUnsignedNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number.date.DefaultDateNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number.time.DefaultTimeNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.multi.DefaultMultiStringNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Provides conversion facilities for PropertyDefinition values.
 */
@objid ("95fba909-9296-43c6-b00a-86ede05dac6d")
public class PropertyAdapter {
    /**
     * Get the property type as a java class.
     * @param pdef the property definition.
     * @param value the value to get the type of.
     * @return the property type as a java class.
     */
    @objid ("cbfc21cc-0507-4305-8d1c-ac10240cb17c")
    public static INatValue getNatValue(PropertyDefinition pdef, Object value) {
        // If there is no base type, can only return the string
        PropertyType type = pdef.getType();
        if (type == null || type.getBaseType() == null) {
            return new DefaultStringNatValue((String) value, false);
        }
        
        switch (type.getBaseType()) {
        case BOOLEAN:
            Boolean booleanValue = value instanceof Boolean ? (Boolean) value : Boolean.parseBoolean(Objects.toString(value));
            return new DefaultBooleanNatValue(booleanValue);
        case ENUMERATE:
            if (type instanceof EnumeratedPropertyType) {
                List<PropertyEnumerationLitteral> litterals = ((EnumeratedPropertyType) type).getLitteral();
                List<String> values = new ArrayList<>(litterals.size());
        
                for (PropertyEnumerationLitteral l : litterals) {
                    values.add(l.getName());
                }
                return new DefaultStringChoiceNatValue((String) value, false, values, false);
            } else {
                return new DefaultStringNatValue((String) value, false);
            }
        case FLOAT:
            Float floatValue;
            try {
                floatValue = value instanceof Float ? (Float) value : Float.parseFloat(Objects.toString(value));
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                floatValue = new Float(0);
            }
            return new DefaultFloatNatValue(floatValue);
        case UNSIGNED:
            Integer unsignedValue;
            try {
                unsignedValue = value instanceof Integer ? (Integer) value : Integer.parseInt(Objects.toString(value));
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                unsignedValue = 0;
            }
            return new DefaultUnsignedNatValue(unsignedValue);
        case INTEGER:
            Integer integerValue;
            try {
                integerValue = value instanceof Integer ? (Integer) value : Integer.parseInt(Objects.toString(value));
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                integerValue = 0;
            }
            return new DefaultIntegerNatValue(integerValue);
        case ELEMENT:
        case RICHTEXT:
            DefaultElementNatValue elementNatValue = new DefaultElementNatValue((MObject) value, true, getAllowedMetaclasses(pdef));
        
            Stereotype stereotype = getAllowedStereotype(pdef);
            if (stereotype != null) {
                elementNatValue.setElementFilter(new StereotypeFilter(stereotype));
            }
        
            return elementNatValue;
        case TIME:
            Date timeValue;
            if (value instanceof Date) {
                timeValue = (Date) value;
            } else {
                try {
                    timeValue = new Date(Long.parseLong((String) value));
                } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                    timeValue = null;
                }
            }
            return new DefaultTimeNatValue(timeValue);
        case DATE:
            Date dateValue;
            if (value instanceof Date) {
                dateValue = (Date) value;
            } else {
                try {
                    dateValue = new Date(Long.parseLong((String) value));
                } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                    dateValue = null;
                }
            }
            return new DefaultDateNatValue(dateValue);
        case MULTIELEMENT:
            DefaultMultiElementNatValue multiElementNatValue = new DefaultMultiElementNatValue((List<MObject>) value, false, getAllowedMetaclasses(pdef));
        
            stereotype = getAllowedStereotype(pdef);
            if (stereotype != null) {
                multiElementNatValue.setElementFilter(new StereotypeFilter(stereotype));
            }
        
            return multiElementNatValue;
        case MULTISTRING:
            return new DefaultMultiStringNatValue((List<String>) value, false);
        case STRING:
        case TEXT:
        default:
            return new DefaultStringNatValue((String) value, false);
        }
        
    }

    @objid ("bd98e95b-d5d5-4051-8255-05c8f4799b36")
    private static Stereotype getAllowedStereotype(PropertyDefinition pdef) {
        String value = pdef.getProperty("Constraints", "stereotype");
        if (value != null) {
            try {
                CoreSession session = CoreSession.getSession(pdef);
                return (Stereotype) session.getModel().findByRef(new MRef(value));
            } catch (@SuppressWarnings ("unused") final IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    @objid ("d5ad70bf-ee1d-4a44-8e88-fb9ac57104e5")
    private static List<Class<? extends MObject>> getAllowedMetaclasses(PropertyDefinition pdef) {
        List<Class<? extends MObject>> allowedMetaclass;
        String mClass = pdef.getProperty("Constraints", "metaclass");
        if (mClass != null) {
            allowedMetaclass = Collections.singletonList(pdef.getMClass().getMetamodel().getMClass(mClass).getJavaInterface());
        } else {
            allowedMetaclass = Collections.singletonList(Element.class);
        }
        return allowedMetaclass;
    }

    @objid ("11ce5312-14e0-49b1-9ee4-059fa4b98956")
    private static class StereotypeFilter implements IMObjectFilter {
        @objid ("135ac311-6156-4086-8272-bb990342738e")
        private Stereotype stereotype;

        @objid ("0b1491ee-de0f-4e00-9042-796da5a1feb2")
        public  StereotypeFilter(Stereotype stereotype) {
            this.stereotype = stereotype;
        }

        @objid ("cd302366-d175-47ed-888d-61e5ce914de0")
        @Override
        public boolean accept(MObject element) {
            if (element instanceof ModelElement) {
                return ((ModelElement) element).getExtension().contains(this.stereotype);
            }
            return false;
        }

    }

}
