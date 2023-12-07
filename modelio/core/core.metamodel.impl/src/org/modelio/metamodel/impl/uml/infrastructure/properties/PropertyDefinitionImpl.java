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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
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

    @objid ("e9ecf193-a17c-4606-bc2e-485c79739636")
    @Override
    public ModuleComponent getModule() {
        final PropertyTableDefinition tableDef = getOwner();
        return (tableDef != null)? tableDef.getModule() : null;
    }

    @objid ("da82859c-aa65-4d96-aceb-5237a2d5d34e")
    @Override
    public boolean isIsEditable() {
        return (Boolean) getAttVal(((PropertyDefinitionSmClass)getClassOf()).getIsEditableAtt());
    }

    @objid ("6e8a8aab-454a-4228-a38f-a74529dca49c")
    @Override
    public void setIsEditable(boolean value) {
        setAttVal(((PropertyDefinitionSmClass)getClassOf()).getIsEditableAtt(), value);
    }

    @objid ("cbcb02fd-b53e-44bc-841c-ba395157f69d")
    @Override
    public String getDefaultValue() {
        return (String) getAttVal(((PropertyDefinitionSmClass)getClassOf()).getDefaultValueAtt());
    }

    @objid ("0990d2ac-6b7f-4170-9239-96d7e78e44f6")
    @Override
    public void setDefaultValue(String value) {
        setAttVal(((PropertyDefinitionSmClass)getClassOf()).getDefaultValueAtt(), value);
    }

    @objid ("91d095eb-0d6f-4a72-a234-bad0c7d11884")
    @Override
    public PropertyType getType() {
        Object obj = getDepVal(((PropertyDefinitionSmClass)getClassOf()).getTypeDep());
        return (obj instanceof PropertyType)? (PropertyType)obj : null;
    }

    @objid ("695c3edd-4dcb-4c33-88ba-c50f27be7545")
    @Override
    public void setType(PropertyType value) {
        appendDepVal(((PropertyDefinitionSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("ef083850-1690-4003-9ae5-845193c01b0d")
    @Override
    public PropertyTableDefinition getOwner() {
        Object obj = getDepVal(((PropertyDefinitionSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof PropertyTableDefinition)? (PropertyTableDefinition)obj : null;
    }

    @objid ("207f4c80-94e9-4c68-9f1e-ec828023a26f")
    @Override
    public void setOwner(PropertyTableDefinition value) {
        appendDepVal(((PropertyDefinitionSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("3493b699-7d22-4f44-8aed-bce1d1ebc1dc")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((PropertyDefinitionSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("979e2deb-2e27-40d3-ab1d-f4d51510f766")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((PropertyDefinitionSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("11e97a78-3d70-4ab8-bd1e-88cce4e2cdee")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyDefinition(this);
    }

}
