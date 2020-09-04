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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("60654162-1718-41c0-97c9-9715f44b3e1d")
    public static final String STEREOTYPE_NAME = "UML2LoopVariableInput";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("494bc17a-9982-46ed-a6d6-b2ed571eb232")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2LoopVariableInput proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2LoopVariableInput >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("37512212-b1c7-4ed9-9da9-d63c77c22540")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2LoopVariableInput.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2LoopVariableInput >> then instantiate a {@link UML2LoopVariableInput} proxy.
     * 
     * @return a {@link UML2LoopVariableInput} proxy on the created {@link InputPin}.
     */
    @objid ("91a353fc-762b-4d03-8211-5b93fcbf3611")
    public static UML2LoopVariableInput create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2LoopVariableInput.STEREOTYPE_NAME);
        return UML2LoopVariableInput.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2LoopVariableInput} proxy from a {@link InputPin} stereotyped << UML2LoopVariableInput >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2LoopVariableInput} proxy or <i>null</i>.
     */
    @objid ("4deb8491-c29a-4349-ba27-b41d951b7105")
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
    @objid ("2df7c345-4b92-4079-b194-eeb7464401d8")
    public static UML2LoopVariableInput safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2LoopVariableInput.canInstantiate(obj))
        	return new UML2LoopVariableInput(obj);
        else
        	throw new IllegalArgumentException("UML2LoopVariableInput: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("20e5aa4d-3315-40ee-97a6-7bc49847dbad")
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
    @objid ("7e05d1eb-8f53-4cf1-b287-e6aab79bddde")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("f4235196-0520-49dd-b84b-1665df91bd0d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("58cae1e5-25bf-47be-8c03-6ea090233ef3")
    protected UML2LoopVariableInput(InputPin elt) {
        this.elt = elt;
    }

    @objid ("2bccab18-b4c5-42a8-a3d6-068429925e3a")
    public static final class MdaTypes {
        @objid ("5705494b-764b-4743-8f6a-938dc88c335d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9495d91c-e00f-48ae-a533-3b34c1aac9b6")
        private static Stereotype MDAASSOCDEP;

        @objid ("f3746085-b926-4754-aebb-b5c13c3db08b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a01869f3-cf9d-4726-8cf2-dcad8e41b844")
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
