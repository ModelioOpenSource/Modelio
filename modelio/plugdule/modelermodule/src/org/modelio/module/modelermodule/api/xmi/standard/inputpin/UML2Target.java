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
 * Proxy class to handle a {@link InputPin} with << UML2Target >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c9ef7950-aef3-455f-bd22-02a260ca445a")
public class UML2Target {
    @objid ("88ec51c2-f1ad-45a4-af29-fc2b0bbe73ea")
    public static final String STEREOTYPE_NAME = "UML2Target";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("a659a45c-64d9-4e20-b872-6454c1cc5c7a")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Target proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Target >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3a6f5445-e180-408d-8553-01e8d6507231")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Target.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Target >> then instantiate a {@link UML2Target} proxy.
     * 
     * @return a {@link UML2Target} proxy on the created {@link InputPin}.
     */
    @objid ("770d5481-eca5-4cfb-a379-87f0af7fe389")
    public static UML2Target create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Target.STEREOTYPE_NAME);
        return UML2Target.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Target} proxy from a {@link InputPin} stereotyped << UML2Target >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Target} proxy or <i>null</i>.
     */
    @objid ("73fd6762-52e3-4d11-b459-5ee38ceacea5")
    public static UML2Target instantiate(InputPin obj) {
        return UML2Target.canInstantiate(obj) ? new UML2Target(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Target} proxy from a {@link InputPin} stereotyped << UML2Target >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Target} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ec378084-fe75-4f9d-9ee4-bac2d6111b2a")
    public static UML2Target safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Target.canInstantiate(obj))
        	return new UML2Target(obj);
        else
        	throw new IllegalArgumentException("UML2Target: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f4ff4f23-46cb-44c2-9c97-9d6481fa3df8")
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
        UML2Target other = (UML2Target) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("a23e9305-fa9e-455e-9d20-dbb0a6c30792")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("dd6d3d10-8c77-4bc7-8435-bd46af0676c3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c73e977c-4d8b-411b-b262-20e4f2f944c4")
    protected UML2Target(InputPin elt) {
        this.elt = elt;
    }

    @objid ("f7e8a899-0f5c-4177-848d-e2e7b037113a")
    public static final class MdaTypes {
        @objid ("c4b5b59a-be8a-45bd-919a-9d3eb9027c61")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("cc971048-ce88-4d3b-b722-e76fa975e0d0")
        private static Stereotype MDAASSOCDEP;

        @objid ("bae38481-b5b7-4acc-9546-20139ffae125")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1895537a-ff7a-4a2e-9dec-2fdf90998a08")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "aa99ee06-c495-11de-ada1-001302895b2b");
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
