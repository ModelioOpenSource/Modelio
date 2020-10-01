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
    @objid ("bce458b6-de5c-48f8-a1cd-91cf5e18d3c0")
    public static final String NOCODE_TAGTYPE = "nocode";

    @objid ("a1da89ea-367a-4b48-9f1e-cd1fb3c5c4bb")
    public static final String TYPE_TAGTYPE = "type";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("fd9c752d-52c8-4695-8d4a-a20dae46ccec")
    protected final Attribute elt;

    /**
     * Tells whether a {@link MMStandardAttribute proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1cddb16a-c3f0-4372-9959-c0a2c92a8ca5")
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
    @objid ("9d8bf993-74c8-44d3-9e11-aeb9e91df2af")
    public static MMStandardAttribute instantiate(Attribute obj) {
        return MMStandardAttribute.canInstantiate(obj) ? new MMStandardAttribute(obj) : null;
    }

    @objid ("6e3e0d5a-f42b-4f00-b89e-9a7cc2f28bd5")
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
    @objid ("7819f4c0-ca28-46b7-8a5a-390b192cc897")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'type'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("b281e7e9-aef3-4310-8347-890346fc03cf")
    public List<String> getType() {
        return this.elt.getTagValues(MMStandardAttribute.MdaTypes.TYPE_TAGTYPE_ELT);
    }

    @objid ("0c293ba3-8fae-4031-932f-0d898fb4cf0f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("571ddf93-deae-4d9d-88c6-1d1deb032bce")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardAttribute.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("134486f8-37c9-4020-89d0-31ba2f0272f5")
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
    @objid ("e3848cc5-d48e-424c-8aa9-cbc5dc17dd21")
    public void setType(List<String> values) {
        this.elt.putTagValues(MMStandardAttribute.MdaTypes.TYPE_TAGTYPE_ELT, values);
    }

    @objid ("75e1f90a-36de-43a3-8307-92febd927ab2")
    protected MMStandardAttribute(Attribute elt) {
        this.elt = elt;
    }

    @objid ("717a9f5d-5619-4ca1-b70b-296a38497d73")
    public static final class MdaTypes {
        @objid ("9a136d31-81e1-4565-b117-3c3f335402ba")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("803d730d-b6af-441c-a71e-ee084488e25d")
        public static TagType TYPE_TAGTYPE_ELT;

        @objid ("fce87b79-a2f1-4312-b630-0c1810390d30")
        private static Stereotype MDAASSOCDEP;

        @objid ("f06c195f-3c6b-4689-9086-5004179e24ff")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d50503da-f173-4c3b-83f7-ace26f5a4b9d")
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
