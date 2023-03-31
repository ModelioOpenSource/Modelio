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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReplyAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1072f154-5793-424b-9dbf-2eaa07f6a142")
public class UML2ReplyAction {
    @objid ("4d7b215e-d3b6-461b-b068-57d0679e5200")
    public static final String STEREOTYPE_NAME = "UML2ReplyAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("c1216b9d-bd8e-48b4-b2bd-9e1278ad1d78")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReplyAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReplyAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f7830bd2-c7ca-4887-b58b-56c561f05cda")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReplyAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReplyAction >> then instantiate a {@link UML2ReplyAction} proxy.
     * 
     * @return a {@link UML2ReplyAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("012f5cf8-58ab-4f89-b83d-ade7dd87249b")
    public static UML2ReplyAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReplyAction.STEREOTYPE_NAME);
        return UML2ReplyAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReplyAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReplyAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReplyAction} proxy or <i>null</i>.
     */
    @objid ("5f819e88-b152-45ca-b18c-15ae46257748")
    public static UML2ReplyAction instantiate(OpaqueAction obj) {
        return UML2ReplyAction.canInstantiate(obj) ? new UML2ReplyAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReplyAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReplyAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReplyAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8b6963bf-47d0-43b9-a8e3-2e3d2ae1532e")
    public static UML2ReplyAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReplyAction.canInstantiate(obj))
        	return new UML2ReplyAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReplyAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("44982948-03b8-42e1-84e6-25433d4039b0")
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
        UML2ReplyAction other = (UML2ReplyAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("6a60904e-1c97-4d61-8467-2bf732fa9f28")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("137ed813-c482-466b-bcd7-e25e53c9b267")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3b9597f5-aea6-45de-97a2-eaa8a4a57ab3")
    protected  UML2ReplyAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("e3df1d05-1797-48da-8ef8-fa4d80b22d66")
    public static final class MdaTypes {
        @objid ("7c075f66-7c0c-42bd-bbcf-48a48efac161")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("108e9c14-2a16-42e9-940d-bd94a28c3d5c")
        private static Stereotype MDAASSOCDEP;

        @objid ("56aa337b-4707-4ae1-a744-0bfe6f5ac3a3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f86c2285-ead5-4a24-a9ad-9c5d1cdee987")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c6a579b6-5d0d-11df-a996-001302895b2b");
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
