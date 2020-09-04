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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("e70ca31a-238e-47c0-916a-d157dd83e59e")
    public static final String STEREOTYPE_NAME = "UML2InputValue";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("f87800fe-0583-4998-b083-3a4bd128221c")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2InputValue proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2InputValue >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a23186af-4c84-4459-919f-3048a2224c20")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InputValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2InputValue >> then instantiate a {@link UML2InputValue} proxy.
     * 
     * @return a {@link UML2InputValue} proxy on the created {@link InputPin}.
     */
    @objid ("76aeafe9-8ebb-413f-a2bd-3a79d8c8aa5c")
    public static UML2InputValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InputValue.STEREOTYPE_NAME);
        return UML2InputValue.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2InputValue} proxy from a {@link InputPin} stereotyped << UML2InputValue >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2InputValue} proxy or <i>null</i>.
     */
    @objid ("7ca75aac-1951-473e-bd63-d7b94e500210")
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
    @objid ("d98860e7-3a97-4907-b8f7-c7c0b2ffde99")
    public static UML2InputValue safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2InputValue.canInstantiate(obj))
        	return new UML2InputValue(obj);
        else
        	throw new IllegalArgumentException("UML2InputValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("22ef1e40-967e-4bda-b7e9-9a50d18bfded")
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
    @objid ("d48d5296-4445-476b-9653-26965b4d558e")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("9d6d0fcf-2c5e-44c3-a087-89559f7c3fdf")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5daf7da3-b5e1-46f4-ac90-8b400c9d6a10")
    protected UML2InputValue(InputPin elt) {
        this.elt = elt;
    }

    @objid ("01baa51d-5b0b-4b35-8446-fe87b63e0a81")
    public static final class MdaTypes {
        @objid ("e6924841-9ef3-48f9-95fe-246bc965f0fb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("733ca0cd-777f-4e4c-a635-504586c337b4")
        private static Stereotype MDAASSOCDEP;

        @objid ("bed33311-d369-48b5-92e1-646837572d07")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7e3d332b-8b45-41dc-92ae-91602ee3d992")
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
