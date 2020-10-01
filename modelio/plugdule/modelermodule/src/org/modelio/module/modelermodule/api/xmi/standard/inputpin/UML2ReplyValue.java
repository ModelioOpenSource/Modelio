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
 * Proxy class to handle a {@link InputPin} with << UML2ReplyValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c4735e0b-dee0-4706-908a-49b4f1702ab4")
public class UML2ReplyValue {
    @objid ("965b97d9-f6b6-47dc-b9ca-d25abbbc3978")
    public static final String STEREOTYPE_NAME = "UML2ReplyValue";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("deae3237-9afd-4449-94d4-b5fc17b0e22f")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2ReplyValue proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2ReplyValue >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("69f4eddd-6de3-4a5c-b8f9-51bd87b6a253")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReplyValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2ReplyValue >> then instantiate a {@link UML2ReplyValue} proxy.
     * 
     * @return a {@link UML2ReplyValue} proxy on the created {@link InputPin}.
     */
    @objid ("b012c29e-3cba-4a86-9832-cd60427d882f")
    public static UML2ReplyValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReplyValue.STEREOTYPE_NAME);
        return UML2ReplyValue.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReplyValue} proxy from a {@link InputPin} stereotyped << UML2ReplyValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2ReplyValue} proxy or <i>null</i>.
     */
    @objid ("a8dc1b94-1a07-4088-940c-f623da743271")
    public static UML2ReplyValue instantiate(InputPin obj) {
        return UML2ReplyValue.canInstantiate(obj) ? new UML2ReplyValue(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReplyValue} proxy from a {@link InputPin} stereotyped << UML2ReplyValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2ReplyValue} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("faabb696-7ecb-4452-90ec-4d5e902a161f")
    public static UML2ReplyValue safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2ReplyValue.canInstantiate(obj))
        	return new UML2ReplyValue(obj);
        else
        	throw new IllegalArgumentException("UML2ReplyValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("06594033-5c8e-48a4-b3ec-0b8601761f97")
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
        UML2ReplyValue other = (UML2ReplyValue) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("e01c74cb-d96b-46fd-a7ce-ba90b6eb113d")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("816bbbcd-8202-41a5-92f3-83601382149a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c19b3d34-6771-49b0-802b-224b8b4d0122")
    protected UML2ReplyValue(InputPin elt) {
        this.elt = elt;
    }

    @objid ("9cd261d9-6c29-4e32-b584-35f043493a76")
    public static final class MdaTypes {
        @objid ("0c0d9df0-94f0-436a-8324-49dc3f20a713")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("106f2306-0ac5-4c9a-99ba-6168dbd645dc")
        private static Stereotype MDAASSOCDEP;

        @objid ("a70192c8-f4c2-4213-8b64-a3432a653f3b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("17ae605e-9628-414a-865b-fd775db07bb8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "bdfb2aae-89a6-49b6-9a0d-3a5e4519cb31");
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
