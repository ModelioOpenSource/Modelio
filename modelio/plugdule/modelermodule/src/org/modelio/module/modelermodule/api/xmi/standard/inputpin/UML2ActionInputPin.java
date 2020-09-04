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
 * Proxy class to handle a {@link InputPin} with << UML2ActionInputPin >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3bedd0c8-c338-4af8-95d9-6c58807f3ac3")
public class UML2ActionInputPin {
    @objid ("92cd8192-6864-4ba4-b6b6-b1c7a4a47d39")
    public static final String STEREOTYPE_NAME = "UML2ActionInputPin";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("d2541f10-c3ff-42e0-b414-54f1621ced55")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2ActionInputPin proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2ActionInputPin >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("11ce825f-c62c-4a2c-9d23-5f2efc0b6609")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ActionInputPin.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2ActionInputPin >> then instantiate a {@link UML2ActionInputPin} proxy.
     * 
     * @return a {@link UML2ActionInputPin} proxy on the created {@link InputPin}.
     */
    @objid ("e386fd5b-ef18-405d-97bc-314d7a29af57")
    public static UML2ActionInputPin create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ActionInputPin.STEREOTYPE_NAME);
        return UML2ActionInputPin.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ActionInputPin} proxy from a {@link InputPin} stereotyped << UML2ActionInputPin >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2ActionInputPin} proxy or <i>null</i>.
     */
    @objid ("78aa5b0a-3002-4095-838d-d26d24c64a81")
    public static UML2ActionInputPin instantiate(InputPin obj) {
        return UML2ActionInputPin.canInstantiate(obj) ? new UML2ActionInputPin(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ActionInputPin} proxy from a {@link InputPin} stereotyped << UML2ActionInputPin >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2ActionInputPin} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("34a0810d-2998-46a2-8e94-da8e9d7ac021")
    public static UML2ActionInputPin safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2ActionInputPin.canInstantiate(obj))
        	return new UML2ActionInputPin(obj);
        else
        	throw new IllegalArgumentException("UML2ActionInputPin: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("726c5d0d-d0df-4c11-8c8a-bbc09f5148bd")
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
        UML2ActionInputPin other = (UML2ActionInputPin) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("4b9cbabb-2627-4023-b482-89fd6a045637")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("a0b06c9a-ce58-4ca5-956a-c4d554f88933")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("968aa58a-a323-45aa-8993-8ba9ee017bba")
    protected UML2ActionInputPin(InputPin elt) {
        this.elt = elt;
    }

    @objid ("dc5d92d9-0b36-408d-b559-7f7dd32d402b")
    public static final class MdaTypes {
        @objid ("b0de1c96-6236-437d-94d7-171576fa3fed")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e3165ec8-fe52-4ecf-b315-c0dce52f15c7")
        private static Stereotype MDAASSOCDEP;

        @objid ("80147980-9d1a-4a32-831a-1eb9670d4cab")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cb6ba6ef-ca71-43ee-a95e-3dd96364a7a1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0bd72298-5d08-11df-a996-001302895b2b");
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
