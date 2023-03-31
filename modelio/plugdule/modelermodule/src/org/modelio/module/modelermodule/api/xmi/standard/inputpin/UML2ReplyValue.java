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
 * Proxy class to handle a {@link InputPin} with << UML2ReplyValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c4735e0b-dee0-4706-908a-49b4f1702ab4")
public class UML2ReplyValue {
    @objid ("9b499a79-4cf4-4448-a125-54b22be888c4")
    public static final String STEREOTYPE_NAME = "UML2ReplyValue";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("13af4da2-1ec1-4a17-b864-93fc27019aca")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2ReplyValue proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2ReplyValue >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("72541561-cbda-4d2b-a530-7b8d92042d06")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReplyValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2ReplyValue >> then instantiate a {@link UML2ReplyValue} proxy.
     * 
     * @return a {@link UML2ReplyValue} proxy on the created {@link InputPin}.
     */
    @objid ("b1118493-e867-4cd9-b00a-15453a6f01e5")
    public static UML2ReplyValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.InputPin");
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
    @objid ("5f7dec5b-e3c1-4ca1-bb7d-69a2019e73ef")
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
    @objid ("c71e8a23-418d-420e-b5ca-6a860f71baee")
    public static UML2ReplyValue safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2ReplyValue.canInstantiate(obj))
        	return new UML2ReplyValue(obj);
        else
        	throw new IllegalArgumentException("UML2ReplyValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8168e0e9-57ff-457c-82c8-8d6f81dae611")
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
    @objid ("30b5fb95-724c-40e3-ad6b-783792e2e4f5")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("d75e1301-8ead-493e-a5dc-067832a51008")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("d0f02009-2298-4062-8861-a76207eb259f")
    protected  UML2ReplyValue(InputPin elt) {
        this.elt = elt;
    }

    @objid ("9cd261d9-6c29-4e32-b584-35f043493a76")
    public static final class MdaTypes {
        @objid ("570b247b-9be7-4930-8827-452b1269fa51")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bcb71ca5-286f-40c3-9022-1116cc0e6d9f")
        private static Stereotype MDAASSOCDEP;

        @objid ("177e3c36-29fa-4c7f-a995-e653896f8ca4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9d10a1ab-6ed0-471c-aea9-9bbae9f150bd")
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
