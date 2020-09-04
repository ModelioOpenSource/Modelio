/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.module.modelermodule.api.xmi.standard.outputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
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
 * Proxy class to handle a {@link OutputPin} with << UML2Decider >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3b0b1cd2-7362-4d64-8798-cb185dd04589")
public class UML2Decider {
    @objid ("b38e8483-54da-425c-ac60-c17a88351bee")
    public static final String STEREOTYPE_NAME = "UML2Decider";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("6ef9bef2-eb67-4dcf-8f56-af13e3c1e8b3")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2Decider proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2Decider >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6d6e02fa-181b-4ece-a1ed-3686271264bc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Decider.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2Decider >> then instantiate a {@link UML2Decider} proxy.
     * 
     * @return a {@link UML2Decider} proxy on the created {@link OutputPin}.
     */
    @objid ("d0a82330-8f89-44ed-a710-3ca1f88116eb")
    public static UML2Decider create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Decider.STEREOTYPE_NAME);
        return UML2Decider.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Decider} proxy from a {@link OutputPin} stereotyped << UML2Decider >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2Decider} proxy or <i>null</i>.
     */
    @objid ("73bfb807-9641-422e-94ac-1eba96d12ae3")
    public static UML2Decider instantiate(OutputPin obj) {
        return UML2Decider.canInstantiate(obj) ? new UML2Decider(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Decider} proxy from a {@link OutputPin} stereotyped << UML2Decider >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2Decider} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8eda5694-306d-4df8-82ff-530ccf2d7117")
    public static UML2Decider safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2Decider.canInstantiate(obj))
        	return new UML2Decider(obj);
        else
        	throw new IllegalArgumentException("UML2Decider: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("01097461-abce-46d9-b8f7-e766c355ecc3")
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
        UML2Decider other = (UML2Decider) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("a2e6d9d7-c208-4cca-84d2-d01fa80f5af8")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("1ec848ad-bf58-4dad-8f56-b2291b8c4bf0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("86d7fafb-80ec-4a49-af4f-8affe3271509")
    protected UML2Decider(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("31eec6ec-04ba-451c-a15c-cacea4d48a26")
    public static final class MdaTypes {
        @objid ("779927d5-3a24-4e57-8bf5-cd39fe7655ab")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("96115e09-8875-47f0-9ea3-f4f628d1a2c9")
        private static Stereotype MDAASSOCDEP;

        @objid ("1d26792d-fbf4-4a7b-9bee-c5fd2715cc59")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a0f77c9d-1527-4359-a76a-f12d7a3ddda3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "27ac6d48-32c8-11e0-91f3-0027103f347c");
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
