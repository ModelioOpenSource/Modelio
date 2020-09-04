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
 * Proxy class to handle a {@link InputPin} with << UML2ReplyValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c4735e0b-dee0-4706-908a-49b4f1702ab4")
public class UML2ReplyValue {
    @objid ("d4ce81fa-6feb-4f80-8ef6-527d78defef0")
    public static final String STEREOTYPE_NAME = "UML2ReplyValue";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("0efa7797-81a4-4bdd-b86f-3ee313ecd138")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2ReplyValue proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2ReplyValue >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("48766451-9fbd-4566-baf6-a9e3ebdc33f5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReplyValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2ReplyValue >> then instantiate a {@link UML2ReplyValue} proxy.
     * 
     * @return a {@link UML2ReplyValue} proxy on the created {@link InputPin}.
     */
    @objid ("3b201066-33c3-42b0-ab64-53a6b3ca0100")
    public static UML2ReplyValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReplyValue.STEREOTYPE_NAME);
        return UML2ReplyValue.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReplyValue} proxy from a {@link InputPin} stereotyped << UML2ReplyValue >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2ReplyValue} proxy or <i>null</i>.
     */
    @objid ("15b04452-0cfb-4f65-baa9-7165c949f8be")
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
    @objid ("0c8941df-2b8a-4f06-908b-4b4ac83ed941")
    public static UML2ReplyValue safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2ReplyValue.canInstantiate(obj))
        	return new UML2ReplyValue(obj);
        else
        	throw new IllegalArgumentException("UML2ReplyValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6f411bc6-4705-4033-b9ea-7dd39748efc3")
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
    @objid ("04a6cc8a-74dc-4cca-9e94-886f9e2d293c")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("750bee2c-bf92-433b-9298-2b969ea33121")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7e4abd0c-7508-4428-8045-4bc1732e26e4")
    protected UML2ReplyValue(InputPin elt) {
        this.elt = elt;
    }

    @objid ("9cd261d9-6c29-4e32-b584-35f043493a76")
    public static final class MdaTypes {
        @objid ("192bf53b-c71a-467c-84bc-e1af3fb92a83")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9e7b6f3d-18fb-4a44-bfbd-68a16720c4c7")
        private static Stereotype MDAASSOCDEP;

        @objid ("6be6e924-309f-4c37-9814-dd9e3daa5f1f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("68372b00-5583-458f-9ad8-52d3e411eec7")
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
