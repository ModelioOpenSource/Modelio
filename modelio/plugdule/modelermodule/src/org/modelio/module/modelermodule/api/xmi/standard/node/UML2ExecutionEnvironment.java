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
 * Proxy class to handle a {@link Node} with << UML2ExecutionEnvironment >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c91962aa-081b-4abf-ad28-673f0b5e2aa7")
public class UML2ExecutionEnvironment {
    @objid ("dd4ade7d-6258-4aba-b0aa-5cb57c688e82")
    public static final String STEREOTYPE_NAME = "UML2ExecutionEnvironment";

    /**
     * The underlying {@link Node} represented by this proxy, never null.
     */
    @objid ("3048dcb3-d9d0-479d-8777-6331e093538a")
    protected final Node elt;

    /**
     * Tells whether a {@link UML2ExecutionEnvironment proxy} can be instantiated from a {@link MObject} checking it is a {@link Node} stereotyped << UML2ExecutionEnvironment >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2f33b977-48c1-4149-8600-bc9459d24f12")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Node) && ((Node) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEnvironment.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Node} stereotyped << UML2ExecutionEnvironment >> then instantiate a {@link UML2ExecutionEnvironment} proxy.
     * 
     * @return a {@link UML2ExecutionEnvironment} proxy on the created {@link Node}.
     */
    @objid ("82f82677-a2c5-4796-abae-9c1a55c97381")
    public static UML2ExecutionEnvironment create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Node");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEnvironment.STEREOTYPE_NAME);
        return UML2ExecutionEnvironment.instantiate((Node)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEnvironment} proxy from a {@link Node} stereotyped << UML2ExecutionEnvironment >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Node
     * @return a {@link UML2ExecutionEnvironment} proxy or <i>null</i>.
     */
    @objid ("42eb13c1-5846-4a1a-a160-1dece363adcb")
    public static UML2ExecutionEnvironment instantiate(Node obj) {
        return UML2ExecutionEnvironment.canInstantiate(obj) ? new UML2ExecutionEnvironment(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEnvironment} proxy from a {@link Node} stereotyped << UML2ExecutionEnvironment >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Node}
     * @return a {@link UML2ExecutionEnvironment} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5b9d9ca2-40f4-410f-94e0-d809d1e30839")
    public static UML2ExecutionEnvironment safeInstantiate(Node obj) throws IllegalArgumentException {
        if (UML2ExecutionEnvironment.canInstantiate(obj))
        	return new UML2ExecutionEnvironment(obj);
        else
        	throw new IllegalArgumentException("UML2ExecutionEnvironment: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b1ea584e-ef7f-4169-9510-22eab858945f")
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
        UML2ExecutionEnvironment other = (UML2ExecutionEnvironment) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Node}. 
     * @return the Node represented by this proxy, never null.
     */
    @objid ("131ff550-2232-48b4-a027-45e5ab83a045")
    public Node getElement() {
        return this.elt;
    }

    @objid ("3e6fd20c-910c-4ec1-9f69-fb041e84c10c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7b6a6005-00f7-4d0f-b0ac-651644366c90")
    protected UML2ExecutionEnvironment(Node elt) {
        this.elt = elt;
    }

    @objid ("83082363-6983-47b6-9176-ae729c96eb16")
    public static final class MdaTypes {
        @objid ("ab1d0466-cb43-4ad5-9a55-b72e570a3188")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3979350c-68c7-4c53-b75f-c20f2737a680")
        private static Stereotype MDAASSOCDEP;

        @objid ("48ba3d05-ab70-45be-9f80-8a5aea857c53")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5d07eddf-c152-47a8-b859-5b2802516888")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8e76c95f-5821-11df-be59-001302895b2b");
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
