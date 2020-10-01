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
package org.modelio.module.modelermodule.api.xmi.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << UML2Reception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("33ec2a3b-ce08-4728-96a1-13288d8a2e8d")
public class UML2Reception {
    @objid ("bf03b1bb-9b58-46c0-a3b3-172509788749")
    public static final String STEREOTYPE_NAME = "UML2Reception";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("411e7410-eef8-41e2-b7ec-1a18068455bc")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2Reception proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2Reception >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a3b19ae8-80e8-43ca-b3d2-d8d5f4b38587")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Reception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2Reception >> then instantiate a {@link UML2Reception} proxy.
     * 
     * @return a {@link UML2Reception} proxy on the created {@link Operation}.
     */
    @objid ("378386f9-6a6c-4e6c-b68e-9485468698f8")
    public static UML2Reception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Reception.STEREOTYPE_NAME);
        return UML2Reception.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2Reception} proxy from a {@link Operation} stereotyped << UML2Reception >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2Reception} proxy or <i>null</i>.
     */
    @objid ("2a2ac83e-3371-49db-a63d-e6471b24f87a")
    public static UML2Reception instantiate(Operation obj) {
        return UML2Reception.canInstantiate(obj) ? new UML2Reception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Reception} proxy from a {@link Operation} stereotyped << UML2Reception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2Reception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3248f6b4-485d-4119-85c3-826ac319c2e3")
    public static UML2Reception safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2Reception.canInstantiate(obj))
        	return new UML2Reception(obj);
        else
        	throw new IllegalArgumentException("UML2Reception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1d3796c4-1dd8-4bc5-995c-5263fda761bc")
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
        UML2Reception other = (UML2Reception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("3fc0ee44-f363-411f-8fa8-536696213a05")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("f98d5be9-5841-49ff-adea-07de80cdb990")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9400b55c-ee82-40ca-94da-516ccc5967d9")
    protected UML2Reception(Operation elt) {
        this.elt = elt;
    }

    @objid ("0ae1c0c7-22eb-45ec-a199-bcb1a4c8b8ef")
    public static final class MdaTypes {
        @objid ("cb1f4a1e-b392-4d89-ab30-b42b9858e30d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dc6be373-90b1-4932-8169-b6cb4e00b1fd")
        private static Stereotype MDAASSOCDEP;

        @objid ("b0e94f63-3f68-44a9-bdb3-2945ed0510fd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f1de3429-8e89-4a79-8d42-88f8f14a2a18")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a46b20b8-26ab-11df-ac88-001302895b2b");
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
