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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0064a19a-ec87-1098-b22e-001ec947cd2a")
public class PropertyDefinitionImpl extends ModelElementImpl implements PropertyDefinition {
    @objid ("3c5568a5-bac1-400e-8437-f60b4b052b62")
    private static final String LIST_SEPARATOR = "|#|";

    @objid ("88e2ff27-2335-42ae-b778-d39669b65f29")
    private static final String LIST_SEPARATOR_PATTERN = "\\|#\\|";

    @objid ("f3771db5-7ee6-4736-9723-fa5572cc0abe")
    @Override
    public Object convertToObject(String value, ModelElement object) {
        // If there is no base type, can only return the string
        if (getType() == null || getType().getBaseType() == null) {
            return value;
        }
        
        // Conversion based on the base type of the property definition
        switch (getType().getBaseType()) {
        case BOOLEAN:
            return new Boolean(value);
        case FLOAT:
            if (value == null || value.isEmpty()) {
                return Float.valueOf("0.0");
            } else {
                return Float.valueOf(value);
            }
        case UNSIGNED:
        case INTEGER:
            if (value == null || value.isEmpty()) {
                return Integer.valueOf(0);
            }
            try {
                return Integer.valueOf(Float.valueOf(value).intValue());
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                return Integer.valueOf(Integer.MIN_VALUE);
            }
        case ENUMERATE:
            if (value != null) {
                // Enums can be stored as simple String or as MRefs...
                MObject valueElt = findElement(value);
                return valueElt != null ? valueElt.getName() : value;
            }
            return null;
        case ELEMENT:
        case RICHTEXT:
            if (value != null) {
                return findElement(value);
            }
            return null;
        case TIME:
            if (value == null || value.isEmpty()) {
                return null;
            }
            try {
                return new Date(Long.parseLong(value));
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                return null;
            }
        case DATE:
            if (value == null || value.isEmpty()) {
                return null;
            }
            try {
                return new Date(Long.parseLong(value));
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                return null;
            }
        case MULTIELEMENT:
            if (value != null) {
                List<MObject> ret = new ArrayList<>();
                for (String s : value.split(PropertyDefinitionImpl.LIST_SEPARATOR_PATTERN)) {
                    MObject elt = findElement(s);
                    if (elt != null) {
                        ret.add(elt);
                    }
                }
                return ret;
            }
            return null;
        case MULTISTRING:
            if (value != null) {
                List<String> ret = new ArrayList<>();
                if (!value.isEmpty()) {
                    for (String s : value.split(PropertyDefinitionImpl.LIST_SEPARATOR_PATTERN)) {
                        ret.add(s);
                    }
                }
                return ret;
            }
            return null;
        case STRING:
        case TEXT:
        default:
            // Return value as it is
            return value;
        }
        
    }

    @objid ("0698d3b7-30c2-403a-b040-c601503f3655")
    @Override
    public String convertToString(Object value, ModelElement object) {
        if (value == null) {
            return "";
        }
        
        PropertyType type = getType();
        switch (type != null ? type.getBaseType() : PropertyBaseType.TEXT) {
        case BOOLEAN:
            return value instanceof Boolean ? Boolean.toString((Boolean) value) : value.toString();
        case ENUMERATE:
            return value.toString();
        case INTEGER:
            return value instanceof Integer ? Integer.toString((int) value) : value.toString();
        case STRING:
            return value.toString();
        case DATE:
            return value instanceof Date ? String.valueOf(((Date) value).getTime()) : value.toString();
        case ELEMENT:
            return value.toString();
        case FLOAT:
            return value instanceof Float ? Float.toString((float) value) : value.toString();
        case RICHTEXT:
            return value.toString();
        case TEXT:
            return value.toString();
        case TIME:
            return value instanceof Date ? String.valueOf(((Date) value).getTime()) : value.toString();
        case UNSIGNED:
            return value instanceof Integer ? Integer.toString((int) value) : value.toString();
        case MULTIELEMENT:
            StringBuilder ret = new StringBuilder();
            for (Iterator<MObject> iterator = ((List<MObject>) value).iterator(); iterator.hasNext();) {
                MObject val = iterator.next();
                ret.append(val.toString());
                if (iterator.hasNext()) {
                    ret.append(PropertyDefinitionImpl.LIST_SEPARATOR);
                }
            }
            return ret.toString();
        case MULTISTRING:
            ret = new StringBuilder();
            for (Iterator<String> iterator = ((List<String>) value).iterator(); iterator.hasNext();) {
                String val = iterator.next();
                ret.append(val);
                if (iterator.hasNext()) {
                    ret.append(PropertyDefinitionImpl.LIST_SEPARATOR);
                }
            }
            return ret.toString();
        default:
            return "?" + value.getClass().getSimpleName() + "?";
        }
        
    }

    @objid ("9ddc4236-4b88-4c6a-9e75-c547d6441c2b")
    protected MObject findElement(String value) {
        try {
            CoreSession session = CoreSession.getSession(this);
            return session.getModel().findByRef(new MRef(value));
        } catch (@SuppressWarnings ("unused") final IllegalArgumentException e) {
            return null;
        }
        
    }

    @objid ("05cacdc4-5596-4c06-b552-c6df6728f2e4")
    @Override
    public boolean isIsEditable() {
        return (Boolean) getAttVal(((PropertyDefinitionSmClass) getClassOf()).getIsEditableAtt());
    }

    @objid ("fd8a62ac-6f72-42f0-891e-d1c654dc38e0")
    @Override
    public void setIsEditable(boolean value) {
        setAttVal(((PropertyDefinitionSmClass) getClassOf()).getIsEditableAtt(), value);
    }

    @objid ("d208ceaa-0b08-4279-a719-e2ee8284edba")
    @Override
    public String getDefaultValue() {
        return (String) getAttVal(((PropertyDefinitionSmClass) getClassOf()).getDefaultValueAtt());
    }

    @objid ("80849511-0bba-4fb7-b3b8-428cf4a3f3eb")
    @Override
    public void setDefaultValue(String value) {
        setAttVal(((PropertyDefinitionSmClass) getClassOf()).getDefaultValueAtt(), value);
    }

    @objid ("4f5a5c39-c66e-4714-86ef-32db172df9e6")
    @Override
    public PropertyType getType() {
        Object obj = getDepVal(((PropertyDefinitionSmClass) getClassOf()).getTypeDep());
        return obj instanceof PropertyType ? (PropertyType) obj : null;
    }

    @objid ("1c6e3022-f4e8-4406-9184-dfc772eaf52f")
    @Override
    public void setType(PropertyType value) {
        appendDepVal(((PropertyDefinitionSmClass) getClassOf()).getTypeDep(), (SmObjectImpl) value);
    }

    @objid ("bbbc5b15-49d0-45eb-baf8-eeb223a294c7")
    @Override
    public PropertyTableDefinition getOwner() {
        Object obj = getDepVal(((PropertyDefinitionSmClass) getClassOf()).getOwnerDep());
        return obj instanceof PropertyTableDefinition ? (PropertyTableDefinition) obj : null;
    }

    @objid ("9a3917e5-84e9-4c90-a0c4-6d9d10fc3e6a")
    @Override
    public void setOwner(PropertyTableDefinition value) {
        appendDepVal(((PropertyDefinitionSmClass) getClassOf()).getOwnerDep(), (SmObjectImpl) value);
    }

    @objid ("59d66b8f-e74f-4603-9313-8077f387ceb7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl) getDepVal(((PropertyDefinitionSmClass) getClassOf()).getOwnerDep());
        if (obj != null) {
            return obj;
        }
        return super.getCompositionOwner();
    }

    @objid ("d143df50-8250-4d51-b6d0-d4cbc1e995f5")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((PropertyDefinitionSmClass) getClassOf()).getOwnerDep();
        obj = (SmObjectImpl) getDepVal(dep);
        if (obj != null) {
            return new SmDepVal(dep, obj);
        }
        return super.getCompositionRelation();
    }

    @objid ("6843184e-98fb-4972-8e32-2b48908e5a65")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyDefinition(this);
    }

    @objid ("63f1768b-2744-4a81-970b-23b1521eb101")
    @Override
    public String computeLabel(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        
        switch (getType().getBaseType()) {
        case DATE:
            DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
            try {
                Date date = new Date(Long.parseLong(value));
                return dateFormatter.format(date.getTime());
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                Date date = new Date();
                return dateFormatter.format(date.getTime());
            }
        case TIME:
            DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.getDefault());
            try {
                Date date = new Date(Long.parseLong(value));
                return timeFormatter.format(date.getTime());
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                Date date = new Date();
                return timeFormatter.format(date.getTime());
            }
        default:
            return value;
        }
        
    }

}
