/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.Objects;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.plugin.Api;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MAttribute;

/**
 * Implements {@link IFormFieldData} to access and modify the value of an {@link MAttribute} on
 * a {@link ModelElement}.
 */
@objid ("a5be3576-6aa1-462c-ac90-b20d1e39b9d6")
public class MAttributeFieldData implements IFormFieldData {
    @objid ("b61be070-73ac-48d9-a47e-e92ed0f48b9c")
    private final ModelElement elt;

    @objid ("da348d62-987d-4a2c-84fc-9785958e7ba7")
    private final MAttributeFieldType type;

    /**
     * Initialize the field.
     * @param session the modeling session to use to modify the model
     * @param elt the element being edited.
     * @param mAtt the element's meta-attribute being edited.
     */
    @objid ("a756afe9-4853-48c0-879b-c782e9e2ad29")
    public MAttributeFieldData(final IModelingSession session, ModelElement elt, MAttribute mAtt) {
        this.elt = elt;
        this.type = new MAttributeFieldType(session, mAtt);
    }

    @objid ("cac12dca-1b31-4565-9fcd-d5863de30627")
    @Override
    public String getName() {
        return this.type.getName();
    }

    @objid ("51ac549a-b374-468e-8d5e-2c7480cc0aec")
    @Override
    public IFormFieldType getType() {
        return this.type;
    }

    @objid ("de09d8e6-dd8b-4f8f-aec1-eb087b895996")
    @Override
    public Object getValue() {
        return this.type.getValue(this.elt);
    }

    @objid ("0f06362a-0dff-4a98-b3d3-dab853db6781")
    @Override
    public void setValue(Object value) {
        this.type.setValue(this.elt, value);
    }

    /**
     * Implements an IFormFieldType based on a MAttribute
     */
    @objid ("b4233e0e-a7d4-4653-b7fb-004308f39019")
    private static class MAttributeFieldType implements IFormFieldType {
        @objid ("a950a980-b996-4c38-9b70-b387bdaad7e3")
        private final MAttribute mAtt;

        @objid ("d4315a41-aae5-4bb2-945a-8c505abf1919")
        private final IModelingSession session;

        @objid ("ff7a599d-e028-4112-b796-02c28aaa0ece")
        public MAttributeFieldType(final IModelingSession session, MAttribute mAtt) {
            this.session = session;
            this.mAtt = mAtt;
        }

        @objid ("bd3c6994-f32a-4ea7-8b49-cdca170ce8ce")
        public String convertToString(Object obj) {
            return Objects.toString(obj);
        }

        @objid ("3ab1330f-f6be-46e6-841a-966c3bf9ffdd")
        public Object convertFromString(String attValue) {
            // No MAttribute: log an error and return null object
            if (this.mAtt == null) {
                Api.LOG.error("MAttributeFieldData: mAtt is null!");
                return null;
            }
            final Class<?> attType = this.mAtt.getType();
            if (attType == UUID.class) {
                return UUID.fromString(attValue);
            } else if (attType == String.class) {
                return attValue;
            } else if (attType == Integer.class) {
                return Integer.valueOf(attValue);
            } else if (attType == Long.class) {
                return Long.valueOf(attValue);
            } else if (attType == Float.class) {
                return Float.valueOf(attValue);
            } else if (attType == Double.class) {
                return Double.valueOf(attValue);
            } else if (attType == Boolean.class) {
                return Boolean.valueOf(attValue);
            } else if (attType.isEnum()) {
                @SuppressWarnings ({ "unchecked", "rawtypes" })
                Enum<?> ret = Enum.valueOf((Class<Enum>) attType, attValue);
                return ret;
            } else {
                // Unknown type
                // this.modelLoader.loadAttribute(data, att, attValueBuilder);
                Api.LOG.error("MAttributeFieldData: Unknown type '%s' for '%s' meta-attribute", attType.getSimpleName(),
                        this.mAtt.getName());
                return attValue;
            }
        }

        /**
         * Set the value of the {@link #mAtt} on an element.
         * @param elt the element to set a value in.
         * @param value the value to set.
         */
        @objid ("0e69479c-f5a1-4a91-a147-1889111da719")
        public void setValue(ModelElement elt, Object value) {
            Object matchingValue = value;
            if (value instanceof String && this.mAtt.getType() != String.class) {
                matchingValue = convertFromString((String) value);
            }
            
            try (ITransaction t = this.session.createTransaction("Modify " + this.mAtt.getName())) {
                elt.mSet(this.mAtt, matchingValue);
                t.commit();
            } catch (final RuntimeException e) {
                Api.LOG.error(e);
            }
        }

        @objid ("6bdae022-44c7-498d-b05d-5a538e21c881")
        @Override
        public String getName() {
            return this.mAtt.getName();
        }

        /**
         * Get the value of the current {@link #mAtt} on an element.
         * @param elt the element to look for a value in.
         * @return the value of the property. Might be <code>null</code>.
         */
        @objid ("37c6158a-8af2-4dc5-a7da-38f82e1a5d9d")
        public Object getValue(ModelElement elt) {
            return elt.mGet(this.mAtt);
        }

        @objid ("94d4846a-88a3-4299-a4ff-e2d427565785")
        @SuppressWarnings ("unchecked")
        @Override
        public Object[] getEnumeratedValues() {
            final Class<?> attType = this.mAtt.getType();
            if (attType.isEnum()) {
                return ((Class<Enum<?>>) attType).getEnumConstants();
            }
            return null;
        }

        @objid ("e3233a78-a4a8-4960-b18e-a6f59239cd97")
        @Override
        public boolean isValidValue(String value) {
            return true;
        }

    }

}
