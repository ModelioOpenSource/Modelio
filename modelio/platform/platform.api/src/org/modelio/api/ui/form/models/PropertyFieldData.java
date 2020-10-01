/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui.form.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.plugin.Api;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;

/**
 * Implements IFormfieldData by delegating some accessors to a specific implementation of an "PropertyDefinition-aware"
 * IFormFieldType
 * <p/>
 */
@objid ("a07e5217-a597-44d4-9819-8ef3a90ade02")
public class PropertyFieldData implements IFormFieldData {
    @objid ("f4a5320a-d6ff-4d37-8305-f3d3bedaed62")
    private final ModelElement elt;

    @objid ("e422ff6e-6c9f-4bb9-9bc7-604264d4b217")
    private final PropertyFieldType type;

    @objid ("0f57de98-b88d-4f92-b903-617215291072")
    public PropertyFieldData(IModelingSession session, ModelElement elt, PropertyDefinition pdef) {
        this.elt = elt;
        this.type = new PropertyFieldType(session, pdef);
    }

    @objid ("327b271c-f793-4533-9345-2a8f2530c973")
    @Override
    public String getName() {
        return this.type.getName();
    }

    @objid ("416ab716-0b55-4eb6-97b4-904a0291c4a2")
    @Override
    public IFormFieldType getType() {
        return this.type;
    }

    @objid ("3312cce0-a5ee-4ab1-84f9-9585ee9a5b68")
    @Override
    public Object getValue() {
        return this.type.getValue(this.elt);
    }

    @objid ("a2e3d015-b973-438e-aa7d-924a8c92e813")
    @Override
    public void setValue(Object value) {
        this.type.setValue(this.elt, value);
    }

    /**
     * Implements an IFormFieldType based on a {@link PropertyDefinition}.
     */
    @objid ("20cdee9e-298b-4ba2-9a60-4128d506b3fc")
    private static class PropertyFieldType implements IFormFieldType {
        @objid ("9148c6d4-65fe-44d6-ac30-d656f05fb72c")
        private final PropertyDefinition pdef;

        @objid ("34a71a7e-67b9-4737-9ed9-9a5b6ee63a85")
        private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());

        @objid ("33b87d55-b659-484c-94de-cb7885edd8be")
        private static final DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.getDefault());

        @objid ("8c522acd-9553-470a-84e0-88f166514b8e")
        private IModelingSession session;

        @objid ("dee93dea-0239-4386-8e7e-befbee7e1563")
        public PropertyFieldType(IModelingSession session, PropertyDefinition pdef) {
            this.session = session;
            this.pdef = pdef;
        }

        @objid ("bf16d227-3672-47b0-b65f-3321fc0da15c")
        @Override
        public String getName() {
            return this.session.getMetamodelExtensions().getLabel(this.pdef);
        }

        /**
         * Get the value of the current {@link PropertyDefinition} on an element.
         * 
         * @param elt the element to look for a value in.
         * @return the value of the property. Might be <code>null</code>.
         */
        @objid ("7f93bf1c-1a9e-4d9e-be37-bc0ddb5d3269")
        public Object getValue(ModelElement elt) {
            TypedPropertyTable properties = getProperties(elt);
            if (properties != null) {
                return properties.getPropertyObject(this.pdef);
            }
            return this.pdef.convertToObject(this.pdef.getDefaultValue(), elt);
        }

        /**
         * Set the value of the {@link PropertyDefinition} on an element.
         * <p/>
         * If no {@link TypedPropertyTable} exists for that property, a new one is created.
         * 
         * @param elt the element to set a value in.
         * @param value the value to set in the element's properties.
         */
        @objid ("9bb3dab5-3a93-49e3-b27a-717f5ba6d64b")
        public void setValue(ModelElement elt, Object value) {
            final Object oldValue = getValue(elt);
            if (!Objects.equals(oldValue, value)) {
                try (ITransaction t = this.session.createTransaction("Modify " + this.pdef.getName())) {
                    TypedPropertyTable properties = getProperties(elt);
                    if (properties == null) {
                        properties = this.session.getModel().createTypedPropertyTable();
                        properties.setName(computePropertyTableId(elt));
                        properties.setType(this.pdef.getOwner());
                        properties.setOwner(elt);
                    }
                    properties.setPropertyObject(this.pdef, value);
                    t.commit();
                } catch (final Exception e) {
                    Api.LOG.error(e);
                }
            }
        }

        @objid ("d7e0e292-ee04-41b4-8974-c1af1228de62")
        @Override
        public Object[] getEnumeratedValues() {
            if (this.pdef.getType() instanceof EnumeratedPropertyType) {
                return ((EnumeratedPropertyType) this.pdef.getType()).getLitteral().toArray();
            }
            return null;
        }

        @objid ("63f524bc-bf1f-4459-ba97-d6b5299a7e6c")
        @Override
        public boolean isValidValue(String value) {
            if (value == null) {
                return false;
            }
            
            switch (this.pdef.getType().getBaseType()) {
            case STRING:
            case TEXT:
                return true;
            
            case ENUMERATE:
                return ((EnumeratedPropertyType) this.pdef.getType()).getLitteral(value) != null;
            
            case BOOLEAN:
                return true;
            case INTEGER:
                try {
                    Integer.decode(value);
                    return true;
                } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                    return false;
                }
            case UNSIGNED:
                try {
                    return Integer.decode(value) > 0;
                } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                    return false;
                }
            case FLOAT:
                try {
                    Float.valueOf(value);
                    return true;
                } catch (@SuppressWarnings ("unused") NumberFormatException | NullPointerException e) {
                    return false;
                }
            
            case DATE:
                try {
                    PropertyFieldType.dateFormat.parse(value);
                    return true;
                } catch (@SuppressWarnings ("unused") final ParseException e) {
                    return false;
                }
            case TIME:
                try {
                    PropertyFieldType.timeFormat.parse(value);
                    return true;
                } catch (@SuppressWarnings ("unused") final ParseException e) {
                    return false;
                }
            
            case ELEMENT:
                return true;
            case RICHTEXT:
                return true;
            default:
                return false;
            }
        }

        /**
         * Get the {@link TypedPropertyTable} typed by {@link #pdef}'s owner {@link PropertyTableDefinition}.
         * 
         * @param elt the element to look for a {@link TypedPropertyTable} in.
         * @return a {@link TypedPropertyTable} or <code>null</code>.
         */
        @objid ("d05e9dda-1818-4eb1-b648-2dcec231cfa9")
        private TypedPropertyTable getProperties(ModelElement elt) {
            String propertyTableId = computePropertyTableId(elt);
            return (TypedPropertyTable) elt.getProperties(propertyTableId);
        }

        @objid ("938fa394-4782-4de3-8346-1ddbce71337e")
        private String computePropertyTableId(ModelElement elt) {
            String propertyTableId = null;
            Stereotype baseStereotype = this.pdef.getOwner().getOwnerStereotype();
            for (Stereotype s : elt.getExtension()) {
                if (PropertyFieldType.inheritsFrom(s, baseStereotype)) {
                    propertyTableId = s.getUuid().toString();
                    break;
                }
            }
            if (propertyTableId == null) {
                if (baseStereotype != null) {
                    propertyTableId = baseStereotype.getUuid().toString();
                } else {
                    propertyTableId = this.pdef.getOwner().getOwnerReference().getUuid().toString();
                }
            }
            return propertyTableId;
        }

        /**
         * Answer to the question: is 'stereotype' a child of the stereotype named by 'stereotypeName' Companion method of the
         * public isStereoyped() method
         * @param stereotypeName
         * the name of another stereotype.
         * 
         * @param stereotype a stereotype
         * @return <code>true</code> if 'stereotype' a child of the stereotype named by 'stereotypeName' else <code>false</code>.
         */
        @objid ("cae52d2f-f6f7-4ea1-9b9c-59317ce7221b")
        private static boolean inheritsFrom(Stereotype stereotype, Stereotype baseStereotype) {
            if (stereotype.equals(baseStereotype)) {
                return true;
            }
            
            if (stereotype.getParent() != null) {
                if (PropertyFieldType.inheritsFrom(stereotype.getParent(), baseStereotype)) {
                    return true;
                }
            }
            return false;
        }

    }

}
