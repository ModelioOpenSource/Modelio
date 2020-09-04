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
 * Proxy class to handle a {@link Node} with << UML2Device >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("35943c26-6bcb-4f3a-8cc4-05428af13dcf")
public class UML2Device {
    @objid ("a19ac1a1-a614-43c3-9529-30aad1190b65")
    public static final String STEREOTYPE_NAME = "UML2Device";

    /**
     * The underlying {@link Node} represented by this proxy, never null.
     */
    @objid ("dea68365-a538-4656-8a6a-1f0e7cbdc822")
    protected final Node elt;

    /**
     * Tells whether a {@link UML2Device proxy} can be instantiated from a {@link MObject} checking it is a {@link Node} stereotyped << UML2Device >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3c9be6a3-7237-484d-80fa-4ad5f68c3db4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Node) && ((Node) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Device.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Node} stereotyped << UML2Device >> then instantiate a {@link UML2Device} proxy.
     * 
     * @return a {@link UML2Device} proxy on the created {@link Node}.
     */
    @objid ("665bb7a6-069e-46d1-9937-cf339152ecc2")
    public static UML2Device create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Node");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Device.STEREOTYPE_NAME);
        return UML2Device.instantiate((Node)e);
    }

    /**
     * Tries to instantiate a {@link UML2Device} proxy from a {@link Node} stereotyped << UML2Device >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Node
     * @return a {@link UML2Device} proxy or <i>null</i>.
     */
    @objid ("e088ba1c-1917-4b87-a1c2-c4540a4ec7f3")
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
    @objid ("62f6c249-1a6c-4fae-b793-74748d86a710")
    public static UML2Device safeInstantiate(Node obj) throws IllegalArgumentException {
        if (UML2Device.canInstantiate(obj))
        	return new UML2Device(obj);
        else
        	throw new IllegalArgumentException("UML2Device: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("80963abd-2958-40fc-8941-2144a8c859bf")
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
    @objid ("56da6eb9-f25b-412d-9f1a-49d1b432aee0")
    public Node getElement() {
        return this.elt;
    }

    @objid ("db82fc6e-28c9-41df-8cce-8bdc548c80b9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5189b75b-8603-4b78-a307-f138104d6c99")
    protected UML2Device(Node elt) {
        this.elt = elt;
    }

    @objid ("523ea551-51fc-4d7a-8cc8-4bf7ca4c5370")
    public static final class MdaTypes {
        @objid ("720bf798-3b45-41b6-945e-b3c61b77dcea")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5f51a1ed-1654-4441-9916-3b1c2966a318")
        private static Stereotype MDAASSOCDEP;

        @objid ("168dd386-0885-4d1c-bf3e-d9ef4b077859")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0813420b-b969-4ab1-a5ba-2f330ec91eee")
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
