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
package org.modelio.module.modelermodule.api.default_.standard.association;

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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} metaclass.
 * <p>Description:
 * <br/><i>MMStandardAssociation</i></p>
 */
@objid ("95d5c7ab-0cac-40f5-acd4-31f25a45ec38")
public class MMStandardAssociation {
    @objid ("98bef021-09ec-4b53-8ef9-d109be3d3fd4")
    public static final String PERSISTENCE_TAGTYPE = "persistence";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("3583fb7a-6bac-4132-843e-b99c37ee8619")
    protected final Association elt;

    /**
     * Tells whether a {@link MMStandardAssociation proxy} can be instantiated from a {@link MObject} checking it is a {@link Association}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5fa3ba9e-93e6-4652-9177-55438cb798ec")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Association);
    }

    /**
     * Tries to instantiate a {@link MMStandardAssociation} proxy from a {@link Association} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link MMStandardAssociation} proxy or <i>null</i>.
     */
    @objid ("8e26eabc-ca07-4f0a-994b-429e05d9338e")
    public static MMStandardAssociation instantiate(Association obj) {
        return MMStandardAssociation.canInstantiate(obj) ? new MMStandardAssociation(obj) : null;
    }

    @objid ("0f5d7306-e9a2-47f4-864a-3179ab6f9f37")
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
        MMStandardAssociation other = (MMStandardAssociation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("ebfc8d14-9148-4ea2-aabd-f5deddeca50d")
    public Association getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'persistence'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("69ce3776-0d72-4cb9-bd06-17e4e53f8273")
    public List<String> getPersistence() {
        return this.elt.getTagValues(MMStandardAssociation.MdaTypes.PERSISTENCE_TAGTYPE_ELT);
    }

    @objid ("7dbc6095-a358-44cc-bdf3-ca18d4e1aa32")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'persistence'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("486b1878-ed8a-48da-9000-2939895348ee")
    public void setPersistence(List<String> values) {
        this.elt.putTagValues(MMStandardAssociation.MdaTypes.PERSISTENCE_TAGTYPE_ELT, values);
    }

    @objid ("c308c9b6-e0b4-487f-98eb-5dcf55ec89c9")
    protected MMStandardAssociation(Association elt) {
        this.elt = elt;
    }

    @objid ("abb2b0b8-5b9d-4464-b11c-f433e23036af")
    public static final class MdaTypes {
        @objid ("d5d9c360-c8a3-42f1-8392-2bdb829bdb1d")
        public static TagType PERSISTENCE_TAGTYPE_ELT;

        @objid ("e919a643-7ba5-45cd-8024-e5f5bb97385c")
        private static Stereotype MDAASSOCDEP;

        @objid ("f505506b-23ad-484a-97fd-5e5915e8f7b1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("939b058c-128c-4a12-aae0-35c4bc095122")
        public static void init(IModuleContext ctx) {
            PERSISTENCE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00700680-0000-01e9-0000-000000000000");
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
