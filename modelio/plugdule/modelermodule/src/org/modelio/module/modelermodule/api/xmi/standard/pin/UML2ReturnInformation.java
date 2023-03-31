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
 * Proxy class to handle a {@link Pin} with << UML2ReturnInformation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a592276c-6700-4520-af3c-8bb54ab17733")
public class UML2ReturnInformation {
    @objid ("95d2fdb4-8c94-44dc-8fcb-cf1cfdc36431")
    public static final String STEREOTYPE_NAME = "UML2ReturnInformation";

    /**
     * The underlying {@link Pin} represented by this proxy, never null.
     */
    @objid ("e010e2de-5860-40a0-b93b-ce21e0d96232")
    protected final Pin elt;

    /**
     * Tells whether a {@link UML2ReturnInformation proxy} can be instantiated from a {@link MObject} checking it is a {@link Pin} stereotyped << UML2ReturnInformation >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f2b44d39-7c92-4a06-89da-30866f664395")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Pin) && ((Pin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReturnInformation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Pin} stereotyped << UML2ReturnInformation >> then instantiate a {@link UML2ReturnInformation} proxy.
     * 
     * @return a {@link UML2ReturnInformation} proxy on the created {@link Pin}.
     */
    @objid ("ea58d8ea-4874-4746-9a71-f15ea2130242")
    public static UML2ReturnInformation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Pin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReturnInformation.STEREOTYPE_NAME);
        return UML2ReturnInformation.instantiate((Pin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReturnInformation} proxy from a {@link Pin} stereotyped << UML2ReturnInformation >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Pin
     * @return a {@link UML2ReturnInformation} proxy or <i>null</i>.
     */
    @objid ("836fb61a-8713-4fad-8a46-fb04795c2dde")
    public static UML2ReturnInformation instantiate(Pin obj) {
        return UML2ReturnInformation.canInstantiate(obj) ? new UML2ReturnInformation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReturnInformation} proxy from a {@link Pin} stereotyped << UML2ReturnInformation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Pin}
     * @return a {@link UML2ReturnInformation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4b84c636-64ec-4ab8-99d3-b142c08c6b8f")
    public static UML2ReturnInformation safeInstantiate(Pin obj) throws IllegalArgumentException {
        if (UML2ReturnInformation.canInstantiate(obj))
        	return new UML2ReturnInformation(obj);
        else
        	throw new IllegalArgumentException("UML2ReturnInformation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9ce17745-b160-45cd-a531-ce2089c02bc4")
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
        UML2ReturnInformation other = (UML2ReturnInformation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Pin}. 
     * @return the Pin represented by this proxy, never null.
     */
    @objid ("4672a4ca-6cba-4e75-be99-f26409ab1fcd")
    public Pin getElement() {
        return this.elt;
    }

    @objid ("893f6e00-7268-4574-a06f-a993dc249b9e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3f071a70-8334-4d0d-9037-e971654c5c4f")
    protected  UML2ReturnInformation(Pin elt) {
        this.elt = elt;
    }

    @objid ("4350d302-8b4a-4b36-9f3e-09cc98ca12e6")
    public static final class MdaTypes {
        @objid ("a27427fa-e4d8-4ed5-8237-a1d454af77c7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("521ddcb0-f72a-4512-82eb-cad9a41dffe0")
        private static Stereotype MDAASSOCDEP;

        @objid ("bcb6c597-63b0-4575-86f0-80bb2f62b67c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b3888103-964d-496f-9769-a4f82d387534")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0936024c-9d51-4bc0-99b5-e8f72ae60007");
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
