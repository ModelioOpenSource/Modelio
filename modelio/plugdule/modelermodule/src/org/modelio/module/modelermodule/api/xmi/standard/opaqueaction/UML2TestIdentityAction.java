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
 * Proxy class to handle a {@link OpaqueAction} with << UML2TestIdentityAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4865d52c-c25c-4167-ada9-55cc79a6e9e3")
public class UML2TestIdentityAction {
    @objid ("0a048802-e77d-41d1-b641-36d4a1331483")
    public static final String STEREOTYPE_NAME = "UML2TestIdentityAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ec98faca-ea13-4155-a403-60673c2401cc")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2TestIdentityAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0a32da37-d8ab-47aa-be87-ec5d506d63a2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2TestIdentityAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> then instantiate a {@link UML2TestIdentityAction} proxy.
     * 
     * @return a {@link UML2TestIdentityAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("f378788a-5d36-4c7c-8507-31ad4aa28339")
    public static UML2TestIdentityAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2TestIdentityAction.STEREOTYPE_NAME);
        return UML2TestIdentityAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2TestIdentityAction} proxy from a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2TestIdentityAction} proxy or <i>null</i>.
     */
    @objid ("54775b0b-3e25-497a-8748-2cf6cbe4f2c9")
    public static UML2TestIdentityAction instantiate(OpaqueAction obj) {
        return UML2TestIdentityAction.canInstantiate(obj) ? new UML2TestIdentityAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2TestIdentityAction} proxy from a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2TestIdentityAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f6f26295-853f-4b63-99db-5dc834a962f9")
    public static UML2TestIdentityAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2TestIdentityAction.canInstantiate(obj))
        	return new UML2TestIdentityAction(obj);
        else
        	throw new IllegalArgumentException("UML2TestIdentityAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("73da57ac-e95b-45db-8873-e98f49875b35")
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
        UML2TestIdentityAction other = (UML2TestIdentityAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("e2d5d6c6-0ff8-40c4-a407-589c9ce0a058")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("aa23fcc3-bbb6-4801-8b12-f60d8e652778")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("f0a602b2-ee02-4597-b30d-f89e2cc94d16")
    protected  UML2TestIdentityAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("203724f2-4489-44f6-9abf-f0908a8c4247")
    public static final class MdaTypes {
        @objid ("e7ac57fa-fd4b-4ba0-8342-696fe11e83d9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e6d490f5-a32d-4b6b-a3f1-9b41769153bf")
        private static Stereotype MDAASSOCDEP;

        @objid ("b229d918-c3fa-4373-8652-c365e3877168")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6934a90d-435f-4c32-ab0a-8ca2834fc578")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6a3f6989-c2fd-11de-8ac8-001302895b2b");
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
