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
    @objid ("089bbf0d-6290-49cc-b113-4c8b6a8dac47")
    public static final String STEREOTYPE_NAME = "UML2Reception";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("f76eb22e-1002-4836-b602-9410fe55a4b5")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2Reception proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2Reception >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6e24775d-5ebd-4522-9007-32e200c79138")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Reception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2Reception >> then instantiate a {@link UML2Reception} proxy.
     * 
     * @return a {@link UML2Reception} proxy on the created {@link Operation}.
     */
    @objid ("4a95a77a-2c20-4586-a5f6-628a8d03b78f")
    public static UML2Reception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Operation");
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
    @objid ("cbea5e0c-e226-4518-936d-74d6c960bd11")
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
    @objid ("09d3fe5e-68f4-49b5-a492-fccc0ce01258")
    public static UML2Reception safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2Reception.canInstantiate(obj))
        	return new UML2Reception(obj);
        else
        	throw new IllegalArgumentException("UML2Reception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("20177aa8-5c8e-4c7b-9737-b28bc95329fb")
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
    @objid ("7e54d221-9898-4cd7-8f6c-67931f1c49a0")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("8778f458-7679-44d1-9aa3-370303cb9ab5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("6ba1b97d-10f2-4f93-9b2a-58ba9f41c963")
    protected  UML2Reception(Operation elt) {
        this.elt = elt;
    }

    @objid ("0ae1c0c7-22eb-45ec-a199-bcb1a4c8b8ef")
    public static final class MdaTypes {
        @objid ("443462e8-f48a-4c69-9ae7-35c500a45508")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("96c3503c-0258-4a93-81e8-b4a8bf4b09c6")
        private static Stereotype MDAASSOCDEP;

        @objid ("44ba2e00-5752-49d0-ad2c-08b209a59cd2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f19d9c2d-b0e7-476c-ade9-73d42e20fa1a")
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
