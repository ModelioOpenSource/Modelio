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
    @objid ("8f06e5ad-ac80-4343-93cc-97d03c7731d3")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link StateVertex} represented by this proxy, never null.
     */
    @objid ("5e1158b8-e84e-41af-a849-0221ca1891cc")
    protected final StateVertex elt;

    /**
     * Tells whether a {@link MMStandardStateVertex proxy} can be instantiated from a {@link MObject} checking it is a {@link StateVertex}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ca180f80-d04c-4ee9-8cd0-f00f65d493e7")
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
    @objid ("0080208c-8d76-4dd5-a1d6-0d210882822c")
    public static MMStandardStateVertex instantiate(StateVertex obj) {
        return MMStandardStateVertex.canInstantiate(obj) ? new MMStandardStateVertex(obj) : null;
    }

    @objid ("31bd9804-15ae-4866-b428-96abea19d21b")
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
    @objid ("c16fde55-0306-4cfd-9e11-a5c23841971c")
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
    @objid ("5b8bf9f3-2e74-4c7b-b501-c6207c4da3f6")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardStateVertex.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("18556875-bffa-42c6-b022-e6f185df24b1")
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
    @objid ("69f2e32b-69f5-4673-8d1d-a7f49bc2634f")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardStateVertex.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("3cae0108-f9fe-4b9e-9cdd-8bbd650d8f75")
    protected  MMStandardStateVertex(StateVertex elt) {
        this.elt = elt;
    }

    @objid ("db11b7bf-2f53-4769-b93a-99fae2ccea36")
    public static final class MdaTypes {
        @objid ("b13cad69-5206-4612-8a9f-bf1d7c25c138")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("48b843ac-4d15-43e1-9a41-4289c27e0410")
        private static Stereotype MDAASSOCDEP;

        @objid ("4ff63abd-4426-45ce-a6b0-5e442e301f3f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d39e0c6f-da00-4b24-ab2d-66e19f9417e6")
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
