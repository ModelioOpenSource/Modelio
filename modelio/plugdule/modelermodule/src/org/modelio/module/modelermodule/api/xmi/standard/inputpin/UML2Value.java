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
 * Proxy class to handle a {@link InputPin} with << UML2Value >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("520071db-260b-4339-a5b5-5acd89638735")
public class UML2Value {
    @objid ("29a11caf-3382-4bf8-bdfd-315098b4b4e1")
    public static final String STEREOTYPE_NAME = "UML2Value";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("53034631-8c2d-4628-b757-8f956b15f699")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Value proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Value >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4e9716f5-300b-4062-bc44-616cbce41f7e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Value.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Value >> then instantiate a {@link UML2Value} proxy.
     * 
     * @return a {@link UML2Value} proxy on the created {@link InputPin}.
     */
    @objid ("a4cca4ac-d30d-471d-bbf9-77467a98fdf7")
    public static UML2Value create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Value.STEREOTYPE_NAME);
        return UML2Value.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Value} proxy from a {@link InputPin} stereotyped << UML2Value >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Value} proxy or <i>null</i>.
     */
    @objid ("00c4c2e0-912a-4be9-aa48-a305b74edb0a")
    public static UML2Value instantiate(InputPin obj) {
        return UML2Value.canInstantiate(obj) ? new UML2Value(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Value} proxy from a {@link InputPin} stereotyped << UML2Value >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Value} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3dc11929-eebb-4cac-9ad2-29b0ac235020")
    public static UML2Value safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Value.canInstantiate(obj))
        	return new UML2Value(obj);
        else
        	throw new IllegalArgumentException("UML2Value: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("82e6a8f7-f320-4811-9fea-cd1036032265")
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
        UML2Value other = (UML2Value) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("407ad259-c2ee-4690-8998-3395b94a2f9b")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("d72c7cd2-8877-470e-94b2-7d1c5c6ff199")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("943a437d-fa02-4b15-8e23-be1d1b48faca")
    protected UML2Value(InputPin elt) {
        this.elt = elt;
    }

    @objid ("5619aea8-dfda-4511-8227-6948387e1280")
    public static final class MdaTypes {
        @objid ("e37feea2-008c-4544-a0ba-2d77c0297920")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0d8b312f-57e4-48a6-ae70-fb6d127bb7ac")
        private static Stereotype MDAASSOCDEP;

        @objid ("0aaa0810-b428-480d-8ffb-d3c46f1df998")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9aae5ca3-9c20-4ee2-8037-9de49b7b3c5a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "eb8f06b7-de86-11de-905b-001302895b2b");
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
