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
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2InputValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1c34bb71-2360-4ab4-a7be-dc80c0436183")
public class UML2InputValue {
    @objid ("9719d6ad-5e49-4e04-8c80-24b1a0182d1a")
    public static final String STEREOTYPE_NAME = "UML2InputValue";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("964156fb-b010-4489-9fb3-9373e2a2e471")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2InputValue proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2InputValue >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f242a15c-d7ac-45f2-814c-42e8b46acc40")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InputValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2InputValue >> then instantiate a {@link UML2InputValue} proxy.
     * 
     * @return a {@link UML2InputValue} proxy on the created {@link InputPin}.
     */
    @objid ("4c391895-33bd-46f1-9682-592337716cad")
    public static UML2InputValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InputValue.STEREOTYPE_NAME);
        return UML2InputValue.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2InputValue} proxy from a {@link InputPin} stereotyped << UML2InputValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2InputValue} proxy or <i>null</i>.
     */
    @objid ("6c03c72b-983a-4863-8a94-892eb612578a")
    public static UML2InputValue instantiate(InputPin obj) {
        return UML2InputValue.canInstantiate(obj) ? new UML2InputValue(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InputValue} proxy from a {@link InputPin} stereotyped << UML2InputValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2InputValue} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9306fa00-530e-4205-aaf2-da63dab471ec")
    public static UML2InputValue safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2InputValue.canInstantiate(obj))
        	return new UML2InputValue(obj);
        else
        	throw new IllegalArgumentException("UML2InputValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("292f9964-285d-4150-8f93-7084dad36cc2")
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
        UML2InputValue other = (UML2InputValue) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("a427b496-f8a9-4c18-9fd7-3d1082707f39")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("24b6f492-cd9b-46fe-b17e-cadfd034cbca")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c1daea14-059b-4426-b43e-e32ee8fff31e")
    protected  UML2InputValue(InputPin elt) {
        this.elt = elt;
    }

    @objid ("01baa51d-5b0b-4b35-8446-fe87b63e0a81")
    public static final class MdaTypes {
        @objid ("a7a02088-6788-4a29-879d-831bfdbf4c08")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fb5c0018-cf21-4e89-93d3-bf1f1b20b6bd")
        private static Stereotype MDAASSOCDEP;

        @objid ("b22c43bf-8952-461f-ab32-c59b46df7bd0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ecef34c7-7d9d-4c62-a53b-4758a6d94620")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a81a2a04-07b3-4a26-8b1e-5b4ebaa67990");
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
