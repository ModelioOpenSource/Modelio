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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.attribute;

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
@objid ("a354b54f-eb25-48e3-b8b4-a731ce7d91a5")
public class MMStandardAttribute {
    @objid ("75dd6899-5d43-414f-b6f9-d798db374fc7")
    public static final String PERSISTENCE_TAGTYPE = "persistence";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("6c7c3f68-5454-47a4-886d-71820396101c")
    protected final Attribute elt;

    /**
     * Tells whether a {@link MMStandardAttribute proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("32f26f0b-fd5e-46bf-93a2-4439a6fd1609")
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
    @objid ("2c484cbd-c114-43cc-8483-f0d6c723e281")
    public static MMStandardAttribute instantiate(Attribute obj) {
        return MMStandardAttribute.canInstantiate(obj) ? new MMStandardAttribute(obj) : null;
    }

    @objid ("e55c0cbe-ee40-4ccc-817e-d6a2dde811d2")
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
    @objid ("4f92d0a6-565d-4899-9c40-97d8bf9930a4")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'persistence'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("af69644c-3017-442c-8c90-4ddcc8d104ff")
    public List<String> getPersistence() {
        return this.elt.getTagValues(MMStandardAttribute.MdaTypes.PERSISTENCE_TAGTYPE_ELT);
    }

    @objid ("caf0e6a7-811b-4edb-a969-ff2da827508f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'persistence'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b2c5994a-b76b-4ede-827d-c35fd07af479")
    public void setPersistence(List<String> values) {
        this.elt.putTagValues(MMStandardAttribute.MdaTypes.PERSISTENCE_TAGTYPE_ELT, values);
    }

    @objid ("0cab8ad2-e4e5-4686-b4b8-9a884010e9b8")
    protected MMStandardAttribute(Attribute elt) {
        this.elt = elt;
    }

    @objid ("c0bed777-9e92-4f82-ab83-213b08aa557f")
    public static final class MdaTypes {
        @objid ("5821cb2b-371d-4af6-8e8c-ce6d5e2020d5")
        public static TagType PERSISTENCE_TAGTYPE_ELT;

        @objid ("33462897-73c3-476c-b7b2-61cd63621d92")
        private static Stereotype MDAASSOCDEP;

        @objid ("d5d4a8e9-392e-4740-97c1-c28e7ad1f909")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("68d6821d-6c45-4616-af25-66d44274d5c5")
        public static void init(IModuleContext ctx) {
            PERSISTENCE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00700680-0000-01eb-0000-000000000000");
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
