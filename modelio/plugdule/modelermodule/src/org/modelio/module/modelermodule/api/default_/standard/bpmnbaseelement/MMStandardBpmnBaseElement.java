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
    @objid ("9dc8550e-daae-4a26-ab63-0aa7b7ae2cbc")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link BpmnBaseElement} represented by this proxy, never null.
     */
    @objid ("b5135a30-b9bd-4694-bb2a-cc36bc71f380")
    protected final BpmnBaseElement elt;

    /**
     * Tells whether a {@link MMStandardBpmnBaseElement proxy} can be instantiated from a {@link MObject} checking it is a {@link BpmnBaseElement}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6b032ef7-c5ae-4875-8297-0580abfb402a")
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
    @objid ("eee9128e-6bdb-4fe7-a807-bd862f9a1737")
    public static MMStandardBpmnBaseElement instantiate(BpmnBaseElement obj) {
        return MMStandardBpmnBaseElement.canInstantiate(obj) ? new MMStandardBpmnBaseElement(obj) : null;
    }

    @objid ("b00840c5-a65f-41c1-8c7b-744af70ee9bf")
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
    @objid ("d55b52e5-66a3-468f-b3a5-3c07acc8fb9e")
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
    @objid ("a309a397-687c-43d9-ace8-1c9538fe2035")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardBpmnBaseElement.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("b77b4e08-a1af-4cd4-adb4-409073a8a2da")
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
    @objid ("276f2d06-2894-4be9-9924-470512fdb526")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardBpmnBaseElement.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("96110001-6742-4e55-b808-8146c1f8dfc2")
    protected MMStandardBpmnBaseElement(BpmnBaseElement elt) {
        this.elt = elt;
    }

    @objid ("980aa497-96a4-4509-83df-a5d6279e37ef")
    public static final class MdaTypes {
        @objid ("c101d083-05ed-448e-b5f0-876dfee592b3")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("168fbb64-7db6-454d-bda8-b37c00c7f1c7")
        private static Stereotype MDAASSOCDEP;

        @objid ("b07e94f8-3f2e-4926-b7da-aa1a9a810dcf")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b48f5278-4609-47d1-9c25-800f21793c6c")
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
