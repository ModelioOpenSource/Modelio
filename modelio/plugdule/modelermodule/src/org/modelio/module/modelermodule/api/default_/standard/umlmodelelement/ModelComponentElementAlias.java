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
package org.modelio.module.modelermodule.api.default_.standard.umlmodelelement;

import java.util.ArrayList;
import java.util.Collections;
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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UmlModelElement} with << ModelComponentElementAlias >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3f02a222-93eb-462f-b374-436e42fd6818")
public class ModelComponentElementAlias {
    @objid ("74738d96-5f1e-4333-8417-18438d48be0d")
    public static final String STEREOTYPE_NAME = "ModelComponentElementAlias";

    @objid ("7bcb9dd2-80f7-4c66-9e9e-eeffe4d7e6e9")
    public static final String UUID_TAGTYPE = "uuid";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("61d2a94f-dc5e-46cc-a023-8549b489a704")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link ModelComponentElementAlias proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5eb2c3d6-8867-4589-a843-276e32a214cd")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponentElementAlias.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> then instantiate a {@link ModelComponentElementAlias} proxy.
     * 
     * @return a {@link ModelComponentElementAlias} proxy on the created {@link UmlModelElement}.
     */
    @objid ("59a0e62b-90c9-4326-b89d-b98b367980d8")
    public static ModelComponentElementAlias create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponentElementAlias.STEREOTYPE_NAME);
        return ModelComponentElementAlias.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponentElementAlias} proxy from a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link ModelComponentElementAlias} proxy or <i>null</i>.
     */
    @objid ("4af2f369-2a30-4978-8a89-65c07b7e5f5f")
    public static ModelComponentElementAlias instantiate(UmlModelElement obj) {
        return ModelComponentElementAlias.canInstantiate(obj) ? new ModelComponentElementAlias(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ModelComponentElementAlias} proxy from a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UmlModelElement}
     * @return a {@link ModelComponentElementAlias} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("65314457-b9f3-485b-8060-e0aad69cc962")
    public static ModelComponentElementAlias safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (ModelComponentElementAlias.canInstantiate(obj))
        	return new ModelComponentElementAlias(obj);
        else
        	throw new IllegalArgumentException("ModelComponentElementAlias: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1e04a91d-78c5-40f4-b527-42ce9c8a3dce")
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
        ModelComponentElementAlias other = (ModelComponentElementAlias) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UmlModelElement}. 
     * @return the UmlModelElement represented by this proxy, never null.
     */
    @objid ("78404578-18f8-4613-a76c-4340b706af83")
    public UmlModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'uuid'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("db4e19bb-1a1c-42ed-ab12-e2571bab43da")
    public String getUuid() {
        return this.elt.getTagValue(ModelComponentElementAlias.MdaTypes.UUID_TAGTYPE_ELT);
    }

    @objid ("f6931781-233b-4de9-99f6-dc598c67d03a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'uuid'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("cab59487-b280-49c9-96a7-3ca7768ca1cc")
    public void setUuid(String value) {
        this.elt.putTagValue(ModelComponentElementAlias.MdaTypes.UUID_TAGTYPE_ELT, value);
    }

    @objid ("ebe9f838-d2a9-4e29-8a55-0437d53a7b5a")
    protected ModelComponentElementAlias(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("5d98506b-4aa3-4855-9a1e-78d997c2836c")
    public static final class MdaTypes {
        @objid ("a3ce0ff0-bde5-499a-9a36-b57e4cf5f23e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f0d78fcf-9ac9-420d-8826-d95fcb3d400e")
        public static TagType UUID_TAGTYPE_ELT;

        @objid ("827acb31-3a60-46b3-8787-fb599cb9d563")
        private static Stereotype MDAASSOCDEP;

        @objid ("d207d8ff-32e2-4ab7-a5a5-a5d771eed570")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6a59ea70-cfe0-4c23-a7b7-6ccad1419212")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3254be34-c7d8-4018-8a68-5de65c30b773");
            UUID_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7ebbd53f-a5f3-433b-872c-4024615af229");
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
