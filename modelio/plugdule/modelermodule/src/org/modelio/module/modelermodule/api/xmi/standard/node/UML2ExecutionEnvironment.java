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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("87561c74-3269-4add-ac48-f48a38f1b895")
    public static final String STEREOTYPE_NAME = "UML2ExecutionEnvironment";

    /**
     * The underlying {@link Node} represented by this proxy, never null.
     */
    @objid ("a1c026a0-e307-4a8f-adb7-83ee5d57d91d")
    protected final Node elt;

    /**
     * Tells whether a {@link UML2ExecutionEnvironment proxy} can be instantiated from a {@link MObject} checking it is a {@link Node} stereotyped << UML2ExecutionEnvironment >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c2df78cd-07af-464e-bb65-88d258b9b653")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Node) && ((Node) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEnvironment.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Node} stereotyped << UML2ExecutionEnvironment >> then instantiate a {@link UML2ExecutionEnvironment} proxy.
     * 
     * @return a {@link UML2ExecutionEnvironment} proxy on the created {@link Node}.
     */
    @objid ("8297181a-6f07-486b-ae28-6bb385053bb0")
    public static UML2ExecutionEnvironment create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Node");
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
    @objid ("06263948-5d98-45a7-bebb-7f930137e215")
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
    @objid ("76250939-a717-4cf4-88de-b7f17319bcbc")
    public static UML2ExecutionEnvironment safeInstantiate(Node obj) throws IllegalArgumentException {
        if (UML2ExecutionEnvironment.canInstantiate(obj))
        	return new UML2ExecutionEnvironment(obj);
        else
        	throw new IllegalArgumentException("UML2ExecutionEnvironment: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d0d4474d-628c-48ec-99fb-8154addcefbb")
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
    @objid ("60c2ed43-8629-459e-ab22-25b667071bec")
    public Node getElement() {
        return this.elt;
    }

    @objid ("cd9554f7-f13d-49a8-a047-1a54943b63d8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("4179f5bb-0c73-4cb4-83fd-c6bfb5e4ad53")
    protected  UML2ExecutionEnvironment(Node elt) {
        this.elt = elt;
    }

    @objid ("83082363-6983-47b6-9176-ae729c96eb16")
    public static final class MdaTypes {
        @objid ("4a3ea539-8002-45a6-b1d3-faec5b3fb379")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2a5a3ee1-1f12-45b1-817c-63e6d1401617")
        private static Stereotype MDAASSOCDEP;

        @objid ("902efa2c-3464-4b51-bb56-1f1744f0417f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("65ab9ce2-c56a-4fce-a9b8-5bdc3e7281a7")
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
