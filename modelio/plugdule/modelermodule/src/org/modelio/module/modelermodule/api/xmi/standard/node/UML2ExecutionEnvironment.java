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
    @objid ("fbaae4eb-5517-4b53-a96b-ed789bb16c74")
    public static final String STEREOTYPE_NAME = "UML2ExecutionEnvironment";

    /**
     * The underlying {@link Node} represented by this proxy, never null.
     */
    @objid ("765cae26-8ca7-4e68-8f8f-a0810a26cca5")
    protected final Node elt;

    /**
     * Tells whether a {@link UML2ExecutionEnvironment proxy} can be instantiated from a {@link MObject} checking it is a {@link Node} stereotyped << UML2ExecutionEnvironment >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d1a3c7be-df23-46b4-9cf9-b42f82984736")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Node) && ((Node) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEnvironment.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Node} stereotyped << UML2ExecutionEnvironment >> then instantiate a {@link UML2ExecutionEnvironment} proxy.
     * 
     * @return a {@link UML2ExecutionEnvironment} proxy on the created {@link Node}.
     */
    @objid ("e7978db7-c829-4692-903a-39b3d60c77c0")
    public static UML2ExecutionEnvironment create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Node");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEnvironment.STEREOTYPE_NAME);
        return UML2ExecutionEnvironment.instantiate((Node)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEnvironment} proxy from a {@link Node} stereotyped << UML2ExecutionEnvironment >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Node
     * @return a {@link UML2ExecutionEnvironment} proxy or <i>null</i>.
     */
    @objid ("9834b07b-6a08-4453-8d17-4cb3ed61a5e3")
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
    @objid ("ed40cd78-6af8-4ba9-bc21-bbdfae363200")
    public static UML2ExecutionEnvironment safeInstantiate(Node obj) throws IllegalArgumentException {
        if (UML2ExecutionEnvironment.canInstantiate(obj))
        	return new UML2ExecutionEnvironment(obj);
        else
        	throw new IllegalArgumentException("UML2ExecutionEnvironment: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("51a0b8ce-f11a-4288-82ee-c850cfab7958")
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
    @objid ("483cf0b7-68ff-4794-9789-a89a95eeda0e")
    public Node getElement() {
        return this.elt;
    }

    @objid ("3d5dedae-7435-44b4-b3cd-20cd8bb5c1c6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0d93fe80-8288-4003-8075-797e3146035b")
    protected UML2ExecutionEnvironment(Node elt) {
        this.elt = elt;
    }

    @objid ("83082363-6983-47b6-9176-ae729c96eb16")
    public static final class MdaTypes {
        @objid ("6692e891-205c-4897-9e08-4bcd6f820e77")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b7118067-5b62-4c5d-87c9-7d12c5284cc5")
        private static Stereotype MDAASSOCDEP;

        @objid ("891615fe-ff23-469c-9c03-00196855866d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("38ef4ded-2f5d-4e6f-ad52-97970d5e4704")
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
