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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2LoopVariableInput >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6a8233a5-9eb3-40a5-83aa-e84ebe4d59b4")
public class UML2LoopVariableInput {
    @objid ("bff328d0-09b4-43e7-a0e0-018ad3edb5b0")
    public static final String STEREOTYPE_NAME = "UML2LoopVariableInput";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("8f1a7345-2306-44bf-b2d8-7a884fdcf1e2")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2LoopVariableInput proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2LoopVariableInput >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d48b848d-1ed8-4ba4-b3aa-f56e5e22ada4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2LoopVariableInput.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2LoopVariableInput >> then instantiate a {@link UML2LoopVariableInput} proxy.
     * 
     * @return a {@link UML2LoopVariableInput} proxy on the created {@link InputPin}.
     */
    @objid ("89a2e322-1ecf-419e-b26d-33e64e0d09ae")
    public static UML2LoopVariableInput create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2LoopVariableInput.STEREOTYPE_NAME);
        return UML2LoopVariableInput.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2LoopVariableInput} proxy from a {@link InputPin} stereotyped << UML2LoopVariableInput >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2LoopVariableInput} proxy or <i>null</i>.
     */
    @objid ("a20d22d4-132e-4119-b473-1a177c4b310b")
    public static UML2LoopVariableInput instantiate(InputPin obj) {
        return UML2LoopVariableInput.canInstantiate(obj) ? new UML2LoopVariableInput(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2LoopVariableInput} proxy from a {@link InputPin} stereotyped << UML2LoopVariableInput >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2LoopVariableInput} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("11b27afb-18fb-4e72-8a36-517c7433a557")
    public static UML2LoopVariableInput safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2LoopVariableInput.canInstantiate(obj))
        	return new UML2LoopVariableInput(obj);
        else
        	throw new IllegalArgumentException("UML2LoopVariableInput: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c4443f73-f524-493b-87b0-2e541152386e")
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
        UML2LoopVariableInput other = (UML2LoopVariableInput) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("e4bbd5cf-b38b-4958-b3eb-01bad79cb01a")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("a982a14a-5fa8-423a-90ce-f09b624d901c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("478097ac-1a11-4c50-8b3a-2cbaafd5e483")
    protected UML2LoopVariableInput(InputPin elt) {
        this.elt = elt;
    }

    @objid ("2bccab18-b4c5-42a8-a3d6-068429925e3a")
    public static final class MdaTypes {
        @objid ("bcf88c35-d076-4012-ba5a-1ddb5659f637")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7943e1b7-a689-4ae5-bee2-c88954c90062")
        private static Stereotype MDAASSOCDEP;

        @objid ("4a71b6b7-941f-4257-837f-3cc3c61669da")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2c2c8c54-7c8c-439c-8e26-5e3630dbbe05")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "7a7f049a-6b5f-4db9-9f79-8e327ca90297");
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
