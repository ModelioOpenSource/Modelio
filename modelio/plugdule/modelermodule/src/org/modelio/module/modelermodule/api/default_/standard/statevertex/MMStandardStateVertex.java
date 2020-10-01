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
package org.modelio.module.modelermodule.api.default_.standard.statevertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
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
 * Proxy class to handle a {@link StateVertex} metaclass.
 * <p>Description:
 * <br/><i>MMStandardStateVertex</i></p>
 */
@objid ("8dd31a07-2225-4171-802c-0efe726a0f0e")
public class MMStandardStateVertex {
    @objid ("20c6db83-53c6-4e8f-967e-2cd22834860f")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link StateVertex} represented by this proxy, never null.
     */
    @objid ("59520877-d5f0-425c-a5cc-344570ff3725")
    protected final StateVertex elt;

    /**
     * Tells whether a {@link MMStandardStateVertex proxy} can be instantiated from a {@link MObject} checking it is a {@link StateVertex}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a03be5e5-b6cc-4374-a8fe-256f1788c324")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof StateVertex);
    }

    /**
     * Tries to instantiate a {@link MMStandardStateVertex} proxy from a {@link StateVertex} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StateVertex
     * @return a {@link MMStandardStateVertex} proxy or <i>null</i>.
     */
    @objid ("5574327f-e2a0-41cf-b571-adf05a67614c")
    public static MMStandardStateVertex instantiate(StateVertex obj) {
        return MMStandardStateVertex.canInstantiate(obj) ? new MMStandardStateVertex(obj) : null;
    }

    @objid ("a948d914-675e-4abf-a79a-2f24fb943bc1")
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
        MMStandardStateVertex other = (MMStandardStateVertex) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StateVertex}. 
     * @return the StateVertex represented by this proxy, never null.
     */
    @objid ("48e35fa1-10e7-4f16-af95-eed43fdb941d")
    public StateVertex getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("c5ab54f2-ed86-462a-b916-1df9987a094d")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardStateVertex.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("ab1d6b47-176c-4f4b-a8bf-3ed9365e6608")
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
    @objid ("af6335db-9ff8-43b0-b16b-91bb0d2b665b")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardStateVertex.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("aec5f2c6-7e51-4ea9-88e9-18117dde0651")
    protected MMStandardStateVertex(StateVertex elt) {
        this.elt = elt;
    }

    @objid ("db11b7bf-2f53-4769-b93a-99fae2ccea36")
    public static final class MdaTypes {
        @objid ("5a5cb069-2c77-4f8f-9204-461b04972f4e")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("b485d337-a857-42a4-8407-daea9cb207bb")
        private static Stereotype MDAASSOCDEP;

        @objid ("c7ff181d-b3d8-4bfd-9dd5-cd81bdb3e34c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2ae1d5ae-23dc-48aa-be9e-1c690188964b")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "d3ff662b-b8a7-4cc0-8476-070defbe8926");
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
