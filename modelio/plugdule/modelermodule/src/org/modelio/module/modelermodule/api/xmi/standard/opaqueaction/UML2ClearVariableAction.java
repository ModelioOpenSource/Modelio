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
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearVariableAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a3faa58b-c1d9-477f-956a-bf64179c20dc")
public class UML2ClearVariableAction {
    @objid ("988d9310-101e-4f71-a917-abf566abf019")
    public static final String STEREOTYPE_NAME = "UML2ClearVariableAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("71a78117-595b-40a4-a42e-8210a4847732")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearVariableAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3cc03096-bc6f-4b1f-9c32-bb47cfb2efd5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearVariableAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> then instantiate a {@link UML2ClearVariableAction} proxy.
     * 
     * @return a {@link UML2ClearVariableAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("d049c26e-b661-4438-bc9f-f33deafc97bb")
    public static UML2ClearVariableAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearVariableAction.STEREOTYPE_NAME);
        return UML2ClearVariableAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearVariableAction} proxy or <i>null</i>.
     */
    @objid ("13fd053e-7642-4519-ab56-e6f33bf9d559")
    public static UML2ClearVariableAction instantiate(OpaqueAction obj) {
        return UML2ClearVariableAction.canInstantiate(obj) ? new UML2ClearVariableAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearVariableAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1c0bfc20-bc04-4f5d-bcc0-f1775405f780")
    public static UML2ClearVariableAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearVariableAction.canInstantiate(obj))
        	return new UML2ClearVariableAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearVariableAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("65fefb9e-33b6-4c38-aef9-12a0f19957aa")
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
        UML2ClearVariableAction other = (UML2ClearVariableAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("2612c0c5-2c45-4864-a0b9-adcdd183b5f5")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c3a9582a-bc71-4eef-91a6-d03882d46cb0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("af742902-ec2c-40b2-8cb3-d7468ffe7d90")
    protected  UML2ClearVariableAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f5cadea9-90dd-4d0b-a894-0d94886a75c3")
    public static final class MdaTypes {
        @objid ("2c098ec9-445d-4e4d-ac62-f544c5312cf6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2d8cce7d-db4d-4bf8-9719-5ae36a95bf0d")
        private static Stereotype MDAASSOCDEP;

        @objid ("cd0a9b53-66a6-4565-aad4-d5298188af98")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7508e0ba-c1e4-4b1b-9d43-0416997447e7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b071b025-c2fc-11de-8ac8-001302895b2b");
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
