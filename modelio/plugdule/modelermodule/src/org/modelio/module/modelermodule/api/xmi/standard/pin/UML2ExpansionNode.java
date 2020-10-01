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
 * Proxy class to handle a {@link Pin} with << UML2ExpansionNode >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c1c157dd-3d9f-46e4-ae7e-b086613744bc")
public class UML2ExpansionNode {
    @objid ("80154155-7df8-4b6c-912c-cc6a802fd961")
    public static final String STEREOTYPE_NAME = "UML2ExpansionNode";

    /**
     * The underlying {@link Pin} represented by this proxy, never null.
     */
    @objid ("fa3e50e9-3fd5-4079-8ef5-aa6933df0545")
    protected final Pin elt;

    /**
     * Tells whether a {@link UML2ExpansionNode proxy} can be instantiated from a {@link MObject} checking it is a {@link Pin} stereotyped << UML2ExpansionNode >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("75061825-4e60-479c-950c-573817345248")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Pin) && ((Pin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionNode.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Pin} stereotyped << UML2ExpansionNode >> then instantiate a {@link UML2ExpansionNode} proxy.
     * 
     * @return a {@link UML2ExpansionNode} proxy on the created {@link Pin}.
     */
    @objid ("ee180992-565c-4a72-b000-11c8fcc3367d")
    public static UML2ExpansionNode create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Pin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionNode.STEREOTYPE_NAME);
        return UML2ExpansionNode.instantiate((Pin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionNode} proxy from a {@link Pin} stereotyped << UML2ExpansionNode >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Pin
     * @return a {@link UML2ExpansionNode} proxy or <i>null</i>.
     */
    @objid ("88e8b164-0da9-4900-8faa-f02adaed21ad")
    public static UML2ExpansionNode instantiate(Pin obj) {
        return UML2ExpansionNode.canInstantiate(obj) ? new UML2ExpansionNode(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionNode} proxy from a {@link Pin} stereotyped << UML2ExpansionNode >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Pin}
     * @return a {@link UML2ExpansionNode} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a9212d5d-3e70-4f4e-98f8-f0670aa62873")
    public static UML2ExpansionNode safeInstantiate(Pin obj) throws IllegalArgumentException {
        if (UML2ExpansionNode.canInstantiate(obj))
        	return new UML2ExpansionNode(obj);
        else
        	throw new IllegalArgumentException("UML2ExpansionNode: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("605bf534-46a9-426b-95bd-45ffaad63260")
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
        UML2ExpansionNode other = (UML2ExpansionNode) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Pin}. 
     * @return the Pin represented by this proxy, never null.
     */
    @objid ("5a00f6d9-c4a6-46e4-8218-c69659db5134")
    public Pin getElement() {
        return this.elt;
    }

    @objid ("6a55fa98-2cff-4e12-809f-2d99e30d1ffa")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("99847713-a57f-423a-84d5-d0a0a47bdd18")
    protected UML2ExpansionNode(Pin elt) {
        this.elt = elt;
    }

    @objid ("6697fd3d-89c7-4e82-8ddc-c23cc8607673")
    public static final class MdaTypes {
        @objid ("61cd0551-2cb7-4ea7-a287-3e60e08a3431")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fb2088e7-835e-41eb-8ffe-7d4e0c7bad80")
        private static Stereotype MDAASSOCDEP;

        @objid ("af3644af-f703-4890-905a-5ce6e437586f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b2a2d1a6-fa20-44e4-b2bc-8b2843247883")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1b1ba62d-205e-11df-948e-001302895b2b");
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
