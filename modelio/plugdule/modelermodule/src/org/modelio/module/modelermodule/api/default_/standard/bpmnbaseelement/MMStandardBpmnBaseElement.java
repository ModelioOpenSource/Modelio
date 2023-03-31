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
package org.modelio.module.modelermodule.api.default_.standard.bpmnbaseelement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
 * Proxy class to handle a {@link BpmnBaseElement} metaclass.
 * <p>Description:
 * <br/><i>MMStandardBpmnBaseElement</i></p>
 */
@objid ("217e7460-6f7c-42ee-b6b0-b61ffc75b657")
public class MMStandardBpmnBaseElement {
    @objid ("30560143-e1f0-4cc6-8053-59919ac6a725")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link BpmnBaseElement} represented by this proxy, never null.
     */
    @objid ("b05784f4-3503-4c85-974f-54d0c213e481")
    protected final BpmnBaseElement elt;

    /**
     * Tells whether a {@link MMStandardBpmnBaseElement proxy} can be instantiated from a {@link MObject} checking it is a {@link BpmnBaseElement}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0b119d56-31c5-4819-8067-87b0ecf93b99")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof BpmnBaseElement);
    }

    /**
     * Tries to instantiate a {@link MMStandardBpmnBaseElement} proxy from a {@link BpmnBaseElement} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a BpmnBaseElement
     * @return a {@link MMStandardBpmnBaseElement} proxy or <i>null</i>.
     */
    @objid ("47d30a8c-ad11-49dd-bbd6-5200967213fc")
    public static MMStandardBpmnBaseElement instantiate(BpmnBaseElement obj) {
        return MMStandardBpmnBaseElement.canInstantiate(obj) ? new MMStandardBpmnBaseElement(obj) : null;
    }

    @objid ("cd38c50d-9e9f-4b1a-8237-cf02ca6773fa")
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
        MMStandardBpmnBaseElement other = (MMStandardBpmnBaseElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link BpmnBaseElement}. 
     * @return the BpmnBaseElement represented by this proxy, never null.
     */
    @objid ("6b66e426-e39e-4a9b-86f8-1cf43c5086c1")
    public BpmnBaseElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("badc8931-0cbf-47d7-b3a4-9db3e46dde18")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardBpmnBaseElement.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("8c5feb2e-91c9-41b5-8968-dfea6a2f43cc")
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
    @objid ("e0f38786-8c50-40b7-9156-dc825ae0b7a6")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardBpmnBaseElement.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("4a5cfdb2-97ba-4c71-865d-7056a93feefb")
    protected  MMStandardBpmnBaseElement(BpmnBaseElement elt) {
        this.elt = elt;
    }

    @objid ("980aa497-96a4-4509-83df-a5d6279e37ef")
    public static final class MdaTypes {
        @objid ("0b387c30-3a41-49c6-b5f7-c77c23e034ee")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("13640aa9-cfc7-4794-b67d-043a21160408")
        private static Stereotype MDAASSOCDEP;

        @objid ("81e96530-4791-46a1-ae7e-ae1e609aab0c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2d94905a-6ec0-4416-8b01-3df22fa6662f")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "df51d048-0ab9-48fd-8432-78e73a9a9ec4");
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
