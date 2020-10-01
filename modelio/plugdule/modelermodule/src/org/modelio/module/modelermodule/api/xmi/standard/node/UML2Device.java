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
package org.modelio.module.modelermodule.api.xmi.standard.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Node} with << UML2Device >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("35943c26-6bcb-4f3a-8cc4-05428af13dcf")
public class UML2Device {
    @objid ("ec39c266-6b70-4d70-ae93-9912a9a22560")
    public static final String STEREOTYPE_NAME = "UML2Device";

    /**
     * The underlying {@link Node} represented by this proxy, never null.
     */
    @objid ("88ececd1-8dea-492c-b5c7-b4b3452d5a64")
    protected final Node elt;

    /**
     * Tells whether a {@link UML2Device proxy} can be instantiated from a {@link MObject} checking it is a {@link Node} stereotyped << UML2Device >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b3593551-2783-4d1c-881d-43055199ffb5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Node) && ((Node) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Device.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Node} stereotyped << UML2Device >> then instantiate a {@link UML2Device} proxy.
     * 
     * @return a {@link UML2Device} proxy on the created {@link Node}.
     */
    @objid ("ca3bdef1-c0a1-4eef-be2f-921b5ebb9465")
    public static UML2Device create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Node");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Device.STEREOTYPE_NAME);
        return UML2Device.instantiate((Node)e);
    }

    /**
     * Tries to instantiate a {@link UML2Device} proxy from a {@link Node} stereotyped << UML2Device >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Node
     * @return a {@link UML2Device} proxy or <i>null</i>.
     */
    @objid ("64c854ab-fa41-4564-b951-ac9fcb4841b2")
    public static UML2Device instantiate(Node obj) {
        return UML2Device.canInstantiate(obj) ? new UML2Device(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Device} proxy from a {@link Node} stereotyped << UML2Device >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Node}
     * @return a {@link UML2Device} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a51b6981-06ff-47c7-85f3-8ca53562050e")
    public static UML2Device safeInstantiate(Node obj) throws IllegalArgumentException {
        if (UML2Device.canInstantiate(obj))
        	return new UML2Device(obj);
        else
        	throw new IllegalArgumentException("UML2Device: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b118f8de-a2ce-4642-881e-bb1685e4d30b")
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
        UML2Device other = (UML2Device) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Node}. 
     * @return the Node represented by this proxy, never null.
     */
    @objid ("2727ed8a-46a3-44d0-a92b-fa4b02ce51be")
    public Node getElement() {
        return this.elt;
    }

    @objid ("f81ab41b-d1b8-4e9f-815d-381c146c0526")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("be04691e-5dfe-49e8-aaa3-f3237bdb87ab")
    protected UML2Device(Node elt) {
        this.elt = elt;
    }

    @objid ("523ea551-51fc-4d7a-8cc8-4bf7ca4c5370")
    public static final class MdaTypes {
        @objid ("03e61e6e-841b-4530-927f-0fd31a0509e6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0f20de14-e81c-4c3c-a51d-6efd92359520")
        private static Stereotype MDAASSOCDEP;

        @objid ("56efd96f-c4bc-4599-ac9b-c540d9979f06")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("086576d9-e60f-4763-88a3-54536709d35d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6f3c6234-52b8-11df-a320-001302895b2b");
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
