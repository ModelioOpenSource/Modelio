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
package org.modelio.module.modelermodule.api.xmi.standard.pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
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
 * Proxy class to handle a {@link Pin} with << UML2ReturnInformation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a592276c-6700-4520-af3c-8bb54ab17733")
public class UML2ReturnInformation {
    @objid ("0979c765-f9e6-499d-9583-129d62b94616")
    public static final String STEREOTYPE_NAME = "UML2ReturnInformation";

    /**
     * The underlying {@link Pin} represented by this proxy, never null.
     */
    @objid ("24e316bf-47f9-4742-a351-42a92fd617bf")
    protected final Pin elt;

    /**
     * Tells whether a {@link UML2ReturnInformation proxy} can be instantiated from a {@link MObject} checking it is a {@link Pin} stereotyped << UML2ReturnInformation >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4dca34bb-b35d-40cd-8ce4-03e0c3663e04")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Pin) && ((Pin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReturnInformation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Pin} stereotyped << UML2ReturnInformation >> then instantiate a {@link UML2ReturnInformation} proxy.
     * 
     * @return a {@link UML2ReturnInformation} proxy on the created {@link Pin}.
     */
    @objid ("c2fc7caa-591e-444b-93c6-e631965838c2")
    public static UML2ReturnInformation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Pin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReturnInformation.STEREOTYPE_NAME);
        return UML2ReturnInformation.instantiate((Pin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReturnInformation} proxy from a {@link Pin} stereotyped << UML2ReturnInformation >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Pin
     * @return a {@link UML2ReturnInformation} proxy or <i>null</i>.
     */
    @objid ("5d895e9d-50e9-484a-851a-d1f1116c6cf6")
    public static UML2ReturnInformation instantiate(Pin obj) {
        return UML2ReturnInformation.canInstantiate(obj) ? new UML2ReturnInformation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReturnInformation} proxy from a {@link Pin} stereotyped << UML2ReturnInformation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Pin}
     * @return a {@link UML2ReturnInformation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("88a28725-22ff-4fe9-b1f8-01afddd13a11")
    public static UML2ReturnInformation safeInstantiate(Pin obj) throws IllegalArgumentException {
        if (UML2ReturnInformation.canInstantiate(obj))
        	return new UML2ReturnInformation(obj);
        else
        	throw new IllegalArgumentException("UML2ReturnInformation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d9643c14-235c-4a99-ac55-f8a6ac614273")
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
        UML2ReturnInformation other = (UML2ReturnInformation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Pin}. 
     * @return the Pin represented by this proxy, never null.
     */
    @objid ("5815908c-0fc9-4ef2-af28-8a6076ff2431")
    public Pin getElement() {
        return this.elt;
    }

    @objid ("11b13369-e61b-4f80-ba39-745c5084bb42")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d9948178-146c-4470-b683-fa52f7b402e4")
    protected UML2ReturnInformation(Pin elt) {
        this.elt = elt;
    }

    @objid ("4350d302-8b4a-4b36-9f3e-09cc98ca12e6")
    public static final class MdaTypes {
        @objid ("8a0346c8-a7ec-4b6e-9360-a9eea1af4c31")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c435cde3-18a9-44eb-a0e0-0b9c4c7e9362")
        private static Stereotype MDAASSOCDEP;

        @objid ("6eb01646-788e-43bf-ab33-09d0f089aafd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8f80b9cb-0bed-421a-9794-0745fef601d1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0936024c-9d51-4bc0-99b5-e8f72ae60007");
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
