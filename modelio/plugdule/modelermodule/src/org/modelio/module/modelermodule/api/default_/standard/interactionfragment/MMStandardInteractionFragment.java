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
    @objid ("cffdde3f-a5b1-4da5-a097-147e229ebf1a")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link InteractionFragment} represented by this proxy, never null.
     */
    @objid ("8d1e4e5a-cfde-4f14-8606-6ebf2f175471")
    protected final InteractionFragment elt;

    /**
     * Tells whether a {@link MMStandardInteractionFragment proxy} can be instantiated from a {@link MObject} checking it is a {@link InteractionFragment}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("071a71b5-8057-4f83-ae9e-a585af85eecf")
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
    @objid ("171a560a-a8f9-4f54-96eb-a1f08d3d7032")
    public static MMStandardInteractionFragment instantiate(InteractionFragment obj) {
        return MMStandardInteractionFragment.canInstantiate(obj) ? new MMStandardInteractionFragment(obj) : null;
    }

    @objid ("609017d5-30a0-497a-9c82-106e937759a5")
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
    @objid ("db579f9f-211f-401c-9120-7d256b24c4c0")
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
    @objid ("ee262308-66eb-4ecb-b2ff-accba0eac226")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardInteractionFragment.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("aa6db3a7-36a5-4083-b40d-ff792bea8817")
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
    @objid ("0ec7359b-175e-43ab-81b1-e20b7ed15e0b")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardInteractionFragment.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("725efb21-2238-4396-b01f-4a73fd6b9f5b")
    protected  MMStandardInteractionFragment(InteractionFragment elt) {
        this.elt = elt;
    }

    @objid ("385be880-2e41-4668-a364-913f895f5d26")
    public static final class MdaTypes {
        @objid ("a125b8e4-6a02-4804-b71b-e9dbd827b605")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("0875a2fd-f27d-48b1-b759-20ed010ccc85")
        private static Stereotype MDAASSOCDEP;

        @objid ("46a61cb4-7e11-402f-b007-336a2c831b55")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f90aecde-546e-447a-a388-e473f4d46814")
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
