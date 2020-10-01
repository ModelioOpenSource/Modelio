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
package org.modelio.module.modelermodule.api.default_.standard.instance;

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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Instance} metaclass.
 * <p>Description:
 * <br/><i>MMStandardInstance</i></p>
 */
@objid ("08200525-45e3-4d06-8b76-4561caa352ab")
public class MMStandardInstance {
    @objid ("c9fb2d37-f304-4a37-9dd8-39204d1bee82")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Instance} represented by this proxy, never null.
     */
    @objid ("366d5132-dba3-47a5-b245-0ef22f49a889")
    protected final Instance elt;

    /**
     * Tells whether a {@link MMStandardInstance proxy} can be instantiated from a {@link MObject} checking it is a {@link Instance}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5c0d4c1f-6328-48f8-9e0b-8ec8b0507ec8")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Instance);
    }

    /**
     * Tries to instantiate a {@link MMStandardInstance} proxy from a {@link Instance} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Instance
     * @return a {@link MMStandardInstance} proxy or <i>null</i>.
     */
    @objid ("4c58d363-a78b-4882-abde-534b33267b1d")
    public static MMStandardInstance instantiate(Instance obj) {
        return MMStandardInstance.canInstantiate(obj) ? new MMStandardInstance(obj) : null;
    }

    @objid ("d70574e7-8fb2-441a-8c62-1ce57a77183f")
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
        MMStandardInstance other = (MMStandardInstance) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Instance}. 
     * @return the Instance represented by this proxy, never null.
     */
    @objid ("897d15ef-72be-4931-a71e-aeb8f191b455")
    public Instance getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("644c2e50-deae-4b0c-9176-84484e358653")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardInstance.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("7577c15b-a492-4c81-a421-9259876009d6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("75ccec49-0fd1-4c57-ab92-18c6784fad1c")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardInstance.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("8ae01849-d9ef-451d-8d1a-eb1169d4754e")
    protected MMStandardInstance(Instance elt) {
        this.elt = elt;
    }

    @objid ("2ed015b2-9ff3-4225-b42f-702e906a25ff")
    public static final class MdaTypes {
        @objid ("c225aa5f-1731-4d2e-aa56-0e17a6ebfa21")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("9892b17f-58c4-475d-a071-4d9fea080249")
        private static Stereotype MDAASSOCDEP;

        @objid ("0953a165-54cc-46b0-9da2-752e8576da63")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3e27ed2b-862c-4442-997d-3c3ef96ce293")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "1a2def6e-2827-48c4-b013-7cbd96e8f99b");
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
