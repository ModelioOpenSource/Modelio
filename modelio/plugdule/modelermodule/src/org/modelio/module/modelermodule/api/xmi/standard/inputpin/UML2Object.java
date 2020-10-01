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
 * Proxy class to handle a {@link InputPin} with << UML2Object >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4d9f0770-42a1-41ca-b31a-a5051fa170da")
public class UML2Object {
    @objid ("f847bd1a-116c-42dc-9808-2ddd76842ef4")
    public static final String STEREOTYPE_NAME = "UML2Object";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("81f1956c-db18-49ad-913a-16ec04609a21")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Object proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Object >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("55405fa2-63df-4989-a71b-0cc4047a241f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Object.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Object >> then instantiate a {@link UML2Object} proxy.
     * 
     * @return a {@link UML2Object} proxy on the created {@link InputPin}.
     */
    @objid ("7b8bfa41-69bf-4e29-a8eb-4d97900dee08")
    public static UML2Object create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Object.STEREOTYPE_NAME);
        return UML2Object.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Object} proxy from a {@link InputPin} stereotyped << UML2Object >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Object} proxy or <i>null</i>.
     */
    @objid ("7ec849b4-573b-4d23-829c-7b2a55da4a4f")
    public static UML2Object instantiate(InputPin obj) {
        return UML2Object.canInstantiate(obj) ? new UML2Object(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Object} proxy from a {@link InputPin} stereotyped << UML2Object >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Object} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3629ca4f-8b37-40ec-a389-39c212827284")
    public static UML2Object safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Object.canInstantiate(obj))
        	return new UML2Object(obj);
        else
        	throw new IllegalArgumentException("UML2Object: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5d771d19-3dbe-4b19-bacf-17f46b5e8c13")
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
        UML2Object other = (UML2Object) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("11c39909-2829-4a51-a7c5-633a39a5d34f")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("53fcb6a0-3aa3-4b75-8472-c14e4ea7ac1f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bd2baff8-af9b-4bea-bd1d-52c175022917")
    protected UML2Object(InputPin elt) {
        this.elt = elt;
    }

    @objid ("a5f36406-a8bc-4203-87a4-7ada95b3bbc6")
    public static final class MdaTypes {
        @objid ("eaf9ff6e-a37b-4959-ad82-742302f71bcc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4b9ef00d-5a60-4230-b5d7-5e88b532c7ca")
        private static Stereotype MDAASSOCDEP;

        @objid ("2c3ba614-7e54-4bb4-897e-0d4d73d4e9cf")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("263a7d7a-fc39-414b-a3dd-04e02483ec8e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f82bed81-afcc-41fc-b014-b9ce92bb5377");
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
