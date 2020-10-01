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
package org.modelio.module.modelermodule.api.default_.standard.interactionfragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link InteractionFragment} metaclass.
 * <p>Description:
 * <br/><i>MMStandardInteractionFragment</i></p>
 */
@objid ("61594814-18dd-4b16-99f8-7a7a8f6e0dae")
public class MMStandardInteractionFragment {
    @objid ("6c50cc27-8a56-42a9-837c-f70f46ab877f")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link InteractionFragment} represented by this proxy, never null.
     */
    @objid ("1867e49c-9f3b-479b-b740-aceba7230d3f")
    protected final InteractionFragment elt;

    /**
     * Tells whether a {@link MMStandardInteractionFragment proxy} can be instantiated from a {@link MObject} checking it is a {@link InteractionFragment}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e84e3797-1fd7-45b6-94e9-4f0629f89d16")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof InteractionFragment);
    }

    /**
     * Tries to instantiate a {@link MMStandardInteractionFragment} proxy from a {@link InteractionFragment} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InteractionFragment
     * @return a {@link MMStandardInteractionFragment} proxy or <i>null</i>.
     */
    @objid ("5d983b90-b189-4fbc-90a0-67aec7e418ba")
    public static MMStandardInteractionFragment instantiate(InteractionFragment obj) {
        return MMStandardInteractionFragment.canInstantiate(obj) ? new MMStandardInteractionFragment(obj) : null;
    }

    @objid ("d94cb04b-dc45-4f23-8485-94aeb463ab17")
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
        MMStandardInteractionFragment other = (MMStandardInteractionFragment) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InteractionFragment}. 
     * @return the InteractionFragment represented by this proxy, never null.
     */
    @objid ("d2e10ccf-ca5d-4761-b870-61108d0708b3")
    public InteractionFragment getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("c8d3a095-be1b-436a-9646-c78f80d64e84")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardInteractionFragment.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("4e59a47a-62c3-4d38-9224-d9db373b9e6a")
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
    @objid ("f271dc93-4911-4272-9c28-c738d5724871")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardInteractionFragment.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("61c68717-48f5-4c55-b6a6-ac30530ff4c7")
    protected MMStandardInteractionFragment(InteractionFragment elt) {
        this.elt = elt;
    }

    @objid ("385be880-2e41-4668-a364-913f895f5d26")
    public static final class MdaTypes {
        @objid ("fe8a76d0-9a59-485e-9c7f-ed82fd9cbbd6")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("e18feca9-ba50-4fc4-91aa-e2bb0434345f")
        private static Stereotype MDAASSOCDEP;

        @objid ("d0810b3a-be88-4573-8edb-722fd68fae98")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f7ef52db-e64f-49f1-b1e7-b37b5998f179")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "a1907f56-2ae2-4a5d-93ef-fa577e9d07f3");
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
