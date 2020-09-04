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
    @objid ("393f2b6a-167c-45b3-9221-ab5ce259124c")
    public static final String STEREOTYPE_NAME = "UML2ExpansionNode";

    /**
     * The underlying {@link Pin} represented by this proxy, never null.
     */
    @objid ("134e17fd-9b21-4169-80a8-dfabc4355da0")
    protected final Pin elt;

    /**
     * Tells whether a {@link UML2ExpansionNode proxy} can be instantiated from a {@link MObject} checking it is a {@link Pin} stereotyped << UML2ExpansionNode >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1ac2d9d2-060e-44a5-8cf3-f5cf2dd66d87")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Pin) && ((Pin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionNode.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Pin} stereotyped << UML2ExpansionNode >> then instantiate a {@link UML2ExpansionNode} proxy.
     * 
     * @return a {@link UML2ExpansionNode} proxy on the created {@link Pin}.
     */
    @objid ("b3250cc4-cc77-4ad5-8ebd-479973bcec91")
    public static UML2ExpansionNode create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Pin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionNode.STEREOTYPE_NAME);
        return UML2ExpansionNode.instantiate((Pin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionNode} proxy from a {@link Pin} stereotyped << UML2ExpansionNode >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Pin
     * @return a {@link UML2ExpansionNode} proxy or <i>null</i>.
     */
    @objid ("53c8f3d7-e40a-4715-b682-7a0c519925f7")
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
    @objid ("cf4e72f6-3a9a-4fc3-8340-9a5ec0658f14")
    public static UML2ExpansionNode safeInstantiate(Pin obj) throws IllegalArgumentException {
        if (UML2ExpansionNode.canInstantiate(obj))
        	return new UML2ExpansionNode(obj);
        else
        	throw new IllegalArgumentException("UML2ExpansionNode: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("df04f91c-d78d-431f-ba55-a987a1ec7056")
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
    @objid ("592fe636-5beb-4a7b-ae8d-02b2c42ba57b")
    public Pin getElement() {
        return this.elt;
    }

    @objid ("6848da88-74fa-4a91-9e23-97155cd706ee")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("aaadb390-45d9-47a1-b06d-fb0be83e6eec")
    protected UML2ExpansionNode(Pin elt) {
        this.elt = elt;
    }

    @objid ("6697fd3d-89c7-4e82-8ddc-c23cc8607673")
    public static final class MdaTypes {
        @objid ("b8c43464-0567-4552-ac9e-4df7e3745cf1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("74310dbd-8920-42f6-8cac-5670faf356d7")
        private static Stereotype MDAASSOCDEP;

        @objid ("0be7e14b-09a6-44e3-9cf0-79c3703ab347")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4df8fe13-e235-4bed-8f7f-e0e332e01ed7")
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
