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
 * Proxy class to handle a {@link Node} with << UML2Device >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("35943c26-6bcb-4f3a-8cc4-05428af13dcf")
public class UML2Device {
    @objid ("2b95b4fa-50db-41d5-b9a8-7e4a8850ce58")
    public static final String STEREOTYPE_NAME = "UML2Device";

    /**
     * The underlying {@link Node} represented by this proxy, never null.
     */
    @objid ("33c881c3-f48b-4ae8-bec3-b082f2ba4427")
    protected final Node elt;

    /**
     * Tells whether a {@link UML2Device proxy} can be instantiated from a {@link MObject} checking it is a {@link Node} stereotyped << UML2Device >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("1523a500-8b49-4333-b35b-ede0ce7dac87")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Node) && ((Node) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Device.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Node} stereotyped << UML2Device >> then instantiate a {@link UML2Device} proxy.
     * 
     * @return a {@link UML2Device} proxy on the created {@link Node}.
     */
    @objid ("e1f73ab7-66e6-4866-8442-6103454c9178")
    public static UML2Device create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Node");
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
    @objid ("643fe5b4-82d0-4a91-82bb-7d771a85d61b")
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
    @objid ("73a99dc6-1103-4ed5-9ed7-28cd913077ec")
    public static UML2Device safeInstantiate(Node obj) throws IllegalArgumentException {
        if (UML2Device.canInstantiate(obj))
        	return new UML2Device(obj);
        else
        	throw new IllegalArgumentException("UML2Device: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6e35e5f7-5077-4bcd-b1a8-741b73f15cca")
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
    @objid ("ff39be27-1ad6-44e3-8f0e-f7a72cc55016")
    public Node getElement() {
        return this.elt;
    }

    @objid ("e4787cb6-7e8f-4dac-bc89-f716ef6159d7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e4b0d49c-7dae-4341-90fa-c6d8f696497b")
    protected  UML2Device(Node elt) {
        this.elt = elt;
    }

    @objid ("523ea551-51fc-4d7a-8cc8-4bf7ca4c5370")
    public static final class MdaTypes {
        @objid ("53b87e2d-9cd8-4431-996d-fdaf1aba8ba5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("87168f1e-42be-4f0c-8046-1a4333496a30")
        private static Stereotype MDAASSOCDEP;

        @objid ("0f69278a-41d7-422d-bf95-9b99e9859907")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7704282d-ae5b-4654-8962-fe5b7aa1978e")
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
