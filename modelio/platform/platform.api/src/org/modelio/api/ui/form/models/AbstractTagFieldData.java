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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * {@link IFormFieldData} for TaggedValue with one TagParameter.
 * @author cma
 * @since 3.7.1
 */
@objid ("460dd8d0-b1c5-484d-b80b-49b163c1e020")
public abstract class AbstractTagFieldData implements IFormFieldData {
    @objid ("ecb7087c-8399-4017-8b92-72ffcbda1392")
    protected final String moduleName;

    @objid ("02bbd658-3e4c-43eb-a317-2e1b7b710311")
    protected final String tagTypeName;

    @objid ("7344d57d-c7a1-4805-91b0-5df14615a704")
    protected final ModelElement editedEl;

    @objid ("d1501e02-d990-43c3-a946-99f81f7ca956")
    private final IModelingSession session;

    @objid ("51d4c665-9945-4b4d-8b47-2598dbd029a5")
    protected final TagType tagType;

    @objid ("c8c540c8-3253-4f27-8c04-5fafafe3e140")
    public  AbstractTagFieldData(IModelingSession session, ModelElement me, String moduleName, String tagTypeName) {
        this.session = session;
        this.editedEl = me;
        this.moduleName = moduleName;
        this.tagTypeName = tagTypeName;
        this.tagType = resolveTagType(me, moduleName, tagTypeName);
        
    }

    @objid ("7702979f-c6ef-438a-ac3a-7c50e4b575e3")
    public  AbstractTagFieldData(IModelingSession session, ModelElement me, TagType tagType) {
        this.session = session;
        this.editedEl = me;
        this.moduleName = tagType.getModule().getName();
        this.tagTypeName = tagType.getName();
        this.tagType = tagType;
        
    }

    @objid ("cc4d0f43-86db-4b82-a98b-1569e104044e")
    @Override
    public String getName() {
        return this.tagType != null ? getModelingSession().getMetamodelExtensions().getLabel(this.tagType) : "! Missing "+this.tagTypeName+"!";
    }

    @objid ("eb1b3485-baf1-4518-a8ed-601bfe134976")
    @Override
    public IFormFieldType getType() {
        return new TagFormFieldType(this.tagType, this.tagTypeName);
    }

    @objid ("a68e2a44-7a2b-46ed-b29e-13ee09246ed7")
    protected IModelingSession getModelingSession() {
        return this.session;
    }

    @objid ("612886b5-7082-4b64-a10a-4e0b9f5a8b52")
    protected TaggedValue getTag() {
        if (this.tagType == null) {
            return null;
        }
        
        for (TaggedValue t : this.editedEl.getTag()) {
            if (Objects.equals(this.tagType, t.getDefinition())) {
                return t;
            }
        }
        return null;
    }

    @objid ("d0ac302f-460a-4e4f-b183-6c42b61e0c8d")
    protected TagType resolveTagType(final ModelElement me, final String aModuleName, final String type) {
        final MMetamodel metamodel = me.getMClass().getMetamodel();
        final Collection<TagType> elts = getModelingSession().findByAtt(TagType.class, "Name", type);
        final List<TagType> candidates = new ArrayList<>();
        
        for (TagType o : elts) {
            if (aModuleName.equals(o.getModule().getName())) {
                candidates.add(o);
            }
        }
           
        // First loop: check strict metaclass equality
        for (final TagType nType : candidates) {
            if (nType.getOwnerReference() != null) {
                final MClass referenceClass = metamodel.getMClass(nType.getOwnerReference().getReferencedClassName());
                if (me.getMClass() == referenceClass) {
                    return nType;
                }
            } else if (nType.getOwnerStereotype() != null) {
                final MClass steClass = metamodel.getMClass(nType.getOwnerStereotype().getBaseClassName());
                if (me.getMClass() == steClass) {
                    return nType;
                }
            } else {
                continue;
            }
        }
        
        // Second loop: if first one did not give any result, check metaclass compatibility
        for (final TagType nType : candidates) {
            if (nType.getOwnerReference() != null) {
                final MClass referenceClass = metamodel.getMClass(nType.getOwnerReference().getReferencedClassName());
                if (me.getMClass().hasBase(referenceClass)) {
                    return nType;
                }
            } else if (nType.getOwnerStereotype() != null) {
                final MClass steClass = metamodel.getMClass(nType.getOwnerStereotype().getBaseClassName());
                if (me.getMClass().hasBase(steClass)) {
                    return nType;
                }
            } else {
                continue;
            }
        }
        return candidates.isEmpty() ? null : candidates.iterator().next();
    }

    @objid ("377e4b24-b67b-4915-a48b-fc0f8916508e")
    protected static class TagFormFieldType implements IFormFieldType {
        @objid ("606b0fed-20d9-4575-ad65-a0c3b02c50ae")
        protected final String tagTypeName;

        @objid ("8d06f440-f604-47f2-864f-c5eaae022c27")
        protected final TagType type;

        @objid ("c3c61fba-1309-447c-8778-1d5ea287a211")
        @Override
        public boolean isValidValue(String value) {
            return true;
        }

        @objid ("556a9446-f917-416b-ae8d-7427edf60300")
        @Override
        public String getName() {
            return this.tagTypeName;
        }

        @objid ("49ec41f7-91d2-4788-8802-93a9b46f59b3")
        @Override
        public Object[] getEnumeratedValues() {
            return null;
        }

        @objid ("5c428fe2-a147-45bf-9e65-601d9ed8ada8")
        public  TagFormFieldType(TagType type, String tagTypeName) {
            this.type = type;
            this.tagTypeName = tagTypeName;
            
        }

    }

}
