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
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.code.standard.attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Attribute} metaclass.
 * <p>Description:
 * <br/><i>MMStandardAttribute</i></p>
 */
@objid ("17e58d9f-0c91-43fe-83fe-d95e748a0c6f")
public class MMStandardAttribute {
    @objid ("db23b819-8d01-480a-b8e4-f686ecfa4aa9")
    public static final String NOCODE_TAGTYPE = "nocode";

    @objid ("d0bc4aa3-428b-46f5-87d2-ffa596dc1885")
    public static final String TYPE_TAGTYPE = "type";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("2ea497f4-1b4a-4225-85a3-475f284c620f")
    protected final Attribute elt;

    /**
     * Tells whether a {@link MMStandardAttribute proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("bac525a3-f532-4f8a-b839-ca051f6188af")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Attribute);
    }

    /**
     * Tries to instantiate a {@link MMStandardAttribute} proxy from a {@link Attribute} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Attribute
     * @return a {@link MMStandardAttribute} proxy or <i>null</i>.
     */
    @objid ("e24604af-7dd2-4339-8acd-bd23d0315492")
    public static MMStandardAttribute instantiate(Attribute obj) {
        return MMStandardAttribute.canInstantiate(obj) ? new MMStandardAttribute(obj) : null;
    }

    @objid ("d2ebdcfc-3d1e-4008-b7f8-b3713f473bc2")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MMStandardAttribute other = (MMStandardAttribute) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Attribute}. 
     * @return the Attribute represented by this proxy, never null.
     */
    @objid ("581402b5-3824-472a-a63f-8ef8f530816b")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'type'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("6259b055-8d1e-424a-8a97-7eb20b698236")
    public List<String> getType() {
        return this.elt.getTagValues(MMStandardAttribute.MdaTypes.TYPE_TAGTYPE_ELT);
    }

    @objid ("22199f1e-7484-4d5e-9ffa-dec720bef8d4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("89da4457-3343-42f2-b062-3fcd6b798be3")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardAttribute.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e782e6cb-0fb0-4776-95b0-9ac84de94057")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardAttribute.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardAttribute.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for List<String> property 'type'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8e3767da-8944-430f-9c18-5e87741417e5")
    public void setType(List<String> values) {
        this.elt.putTagValues(MMStandardAttribute.MdaTypes.TYPE_TAGTYPE_ELT, values);
    }

    @objid ("e42eeb2e-8c55-41b0-9822-da8ffb52f17b")
    protected  MMStandardAttribute(Attribute elt) {
        this.elt = elt;
    }

    @objid ("717a9f5d-5619-4ca1-b70b-296a38497d73")
    public static final class MdaTypes {
        @objid ("df9332dc-2df5-41a9-a3d0-3474841a5324")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("2206ce9d-319d-47d3-b8bf-420850fc2273")
        public static TagType TYPE_TAGTYPE_ELT;

        @objid ("3ef64ae6-fba2-4e18-b4e4-8411c466f657")
        private static Stereotype MDAASSOCDEP;

        @objid ("1f796359-e821-4b9e-863c-5f77a4df0c1d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("310b2e81-ac9d-4e54-b2f1-a6cd2aa50df8")
        public static void init(IModuleContext ctx) {
            NOCODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-36bb-0000-000000000000");
            TYPE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-3765-0000-000000000000");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
            
        }
	static {
        		if(ModelerModuleModule.getInstance() != null) {
        			init(ModelerModuleModule.getInstance().getModuleContext());
        		}
        	}
        
    }

}
