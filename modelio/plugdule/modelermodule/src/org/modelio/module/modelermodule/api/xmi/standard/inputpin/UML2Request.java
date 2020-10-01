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
 * Proxy class to handle a {@link InputPin} with << UML2Request >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1e13d871-b6e7-425b-b323-3853d5ba793a")
public class UML2Request {
    @objid ("d0eced7c-7b5a-40cb-9186-8e3497f0cc90")
    public static final String STEREOTYPE_NAME = "UML2Request";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("5b586b7b-6d8e-4e68-89ed-2d57fbd04665")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Request proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Request >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4b60f100-6cfa-41ab-8d61-23e04a0f77ab")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Request.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Request >> then instantiate a {@link UML2Request} proxy.
     * 
     * @return a {@link UML2Request} proxy on the created {@link InputPin}.
     */
    @objid ("6ce63b8e-c681-4855-80b1-8dde194b5763")
    public static UML2Request create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Request.STEREOTYPE_NAME);
        return UML2Request.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Request} proxy from a {@link InputPin} stereotyped << UML2Request >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Request} proxy or <i>null</i>.
     */
    @objid ("686987c9-3970-4336-8e4f-40fd5483fee1")
    public static UML2Request instantiate(InputPin obj) {
        return UML2Request.canInstantiate(obj) ? new UML2Request(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Request} proxy from a {@link InputPin} stereotyped << UML2Request >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Request} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a1646dae-aa6b-43ed-9d56-16388833dc4c")
    public static UML2Request safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Request.canInstantiate(obj))
        	return new UML2Request(obj);
        else
        	throw new IllegalArgumentException("UML2Request: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("52b9a0ee-583b-4e52-90ca-738c7542ee5d")
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
        UML2Request other = (UML2Request) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("76ebaf72-bd6a-4ebb-979c-98d1d5163a39")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("bd512995-0df2-4b97-866e-2dc8c1675229")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e8eee988-7b39-49f4-8cba-a9d07c1d8d97")
    protected UML2Request(InputPin elt) {
        this.elt = elt;
    }

    @objid ("3e4f51a5-49eb-42d4-9cf5-f8ecc89b07e5")
    public static final class MdaTypes {
        @objid ("a67b9bdd-3ecf-4f79-a6e3-b411d83bffeb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("74a02e71-4c76-42b7-8135-1806022c77dd")
        private static Stereotype MDAASSOCDEP;

        @objid ("098e5e5f-3350-4434-b6d8-353ff0193be6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7415e3ca-831b-4505-b236-cd0b87634d25")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e0ff915d-8d3f-4da8-84be-5aed471a16c5");
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
