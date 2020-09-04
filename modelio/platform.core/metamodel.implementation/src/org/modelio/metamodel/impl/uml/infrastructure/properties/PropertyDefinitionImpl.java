/* 
 * Copyright 2013-2018 Modeliosoft
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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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
        if (getType().getBaseType() == null) {
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
                return new Date();
            }
            try {
                return new Date(Long.parseLong(value));
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                return new Date();
            }
        case DATE:
            if (value == null || value.isEmpty()) {
                return new Date();
            }
            try {
                return new Date(Long.parseLong(value));
            } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                return new Date();
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
        
        switch (getType().getBaseType()) {
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

    @objid ("7b6e45ae-5d07-44da-83f5-31fbc4415636")
    @Override
    public boolean isIsEditable() {
        return (Boolean) getAttVal(((PropertyDefinitionSmClass) getClassOf()).getIsEditableAtt());
    }

    @objid ("90957c36-7a5c-4abf-bd67-eeaff76580c2")
    @Override
    public void setIsEditable(boolean value) {
        setAttVal(((PropertyDefinitionSmClass) getClassOf()).getIsEditableAtt(), value);
    }

    @objid ("fecee775-78c1-441e-bbc4-329b618a8379")
    @Override
    public String getDefaultValue() {
        return (String) getAttVal(((PropertyDefinitionSmClass) getClassOf()).getDefaultValueAtt());
    }

    @objid ("a0db1ae9-6524-4d6a-90cb-94c9110bcdcc")
    @Override
    public void setDefaultValue(String value) {
        setAttVal(((PropertyDefinitionSmClass) getClassOf()).getDefaultValueAtt(), value);
    }

    @objid ("2caec450-5053-4285-a6cd-907f2ce19827")
    @Override
    public PropertyType getType() {
        Object obj = getDepVal(((PropertyDefinitionSmClass) getClassOf()).getTypeDep());
        return (obj instanceof PropertyType) ? (PropertyType) obj : null;
    }

    @objid ("cc0d7e24-8cb2-49b1-a3dd-41b06716551c")
    @Override
    public void setType(PropertyType value) {
        appendDepVal(((PropertyDefinitionSmClass) getClassOf()).getTypeDep(), (SmObjectImpl) value);
    }

    @objid ("1f7ba5f2-9818-4540-b61e-d0b8792d2708")
    @Override
    public PropertyTableDefinition getOwner() {
        Object obj = getDepVal(((PropertyDefinitionSmClass) getClassOf()).getOwnerDep());
        return (obj instanceof PropertyTableDefinition) ? (PropertyTableDefinition) obj : null;
    }

    @objid ("f973d22b-d818-44b8-9a28-32eeea76ae62")
    @Override
    public void setOwner(PropertyTableDefinition value) {
        appendDepVal(((PropertyDefinitionSmClass) getClassOf()).getOwnerDep(), (SmObjectImpl) value);
    }

    @objid ("38d2d6fb-9768-459f-b691-3c7997df10b9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl) this.getDepVal(((PropertyDefinitionSmClass) getClassOf()).getOwnerDep());
        if (obj != null) {
            return obj;
        }
        return super.getCompositionOwner();
    }

    @objid ("75ce4cb7-418f-4c0c-90ee-1a47f41be99f")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((PropertyDefinitionSmClass) getClassOf()).getOwnerDep();
        obj = (SmObjectImpl) this.getDepVal(dep);
        if (obj != null) {
            return new SmDepVal(dep, obj);
        }
        return super.getCompositionRelation();
    }

    @objid ("22ae5072-4632-43cb-999e-1c6a89a8f603")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyDefinition(this);
    }

}
