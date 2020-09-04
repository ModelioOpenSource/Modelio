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
 * Proxy class to handle a {@link InputPin} with << UML2First >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1d8bbfa9-4d67-49bd-9cf2-8020cc4981d7")
public class UML2First {
    @objid ("7fd753ff-672d-4631-9d5e-1a88bf7ef1f0")
    public static final String STEREOTYPE_NAME = "UML2First";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("4f72164c-9601-4990-b9e8-f619d295d41b")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2First proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2First >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e832de19-01a5-428c-890d-772d50974b68")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2First.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2First >> then instantiate a {@link UML2First} proxy.
     * 
     * @return a {@link UML2First} proxy on the created {@link InputPin}.
     */
    @objid ("03c21ba7-fd92-4dc3-8ef2-e38f1bf2a3cb")
    public static UML2First create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2First.STEREOTYPE_NAME);
        return UML2First.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2First} proxy from a {@link InputPin} stereotyped << UML2First >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2First} proxy or <i>null</i>.
     */
    @objid ("80cd318f-8c1c-44fb-b7a8-544d9b2bce3c")
    public static UML2First instantiate(InputPin obj) {
        return UML2First.canInstantiate(obj) ? new UML2First(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2First} proxy from a {@link InputPin} stereotyped << UML2First >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2First} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("afb6249f-3414-4abf-8595-f769b11d39e3")
    public static UML2First safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2First.canInstantiate(obj))
        	return new UML2First(obj);
        else
        	throw new IllegalArgumentException("UML2First: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c9502d01-9dec-4cce-8596-5727a81f45d1")
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
        UML2First other = (UML2First) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("0b885231-5b6b-42cc-84a2-8f2d337b38ab")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("db4bf9d3-c44c-4282-8a17-da5b5a503da6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2900762f-ece2-451b-b265-ba96498129dd")
    protected UML2First(InputPin elt) {
        this.elt = elt;
    }

    @objid ("231fcc4f-1387-4364-b0db-960af406ba29")
    public static final class MdaTypes {
        @objid ("bb03b1d7-4938-41ee-9d48-b11468e9a83b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("de5ef19d-3ae6-4518-aa84-7971815c06c2")
        private static Stereotype MDAASSOCDEP;

        @objid ("e4498c5e-3e9e-46c3-8b80-e2c1638a9fe4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("22193ec6-1c0b-4f64-94e3-018ce60e1c5f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "37d0688a-c308-11de-8ac8-001302895b2b");
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
