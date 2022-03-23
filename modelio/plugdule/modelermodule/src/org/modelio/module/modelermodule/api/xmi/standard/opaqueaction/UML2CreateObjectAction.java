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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("38d61282-de49-4b5f-9fa5-4c894fb317ea")
public class UML2CreateObjectAction {
    @objid ("e185b916-1b2b-4700-afcf-3de0206f215c")
    public static final String STEREOTYPE_NAME = "UML2CreateObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ca36ad76-283c-4d87-8602-0f5b40912c23")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a09339dd-74b1-46d3-be56-827c690a5713")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> then instantiate a {@link UML2CreateObjectAction} proxy.
     * 
     * @return a {@link UML2CreateObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("474769fa-86a7-4dba-a448-d7b2729cebcb")
    public static UML2CreateObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateObjectAction.STEREOTYPE_NAME);
        return UML2CreateObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateObjectAction} proxy or <i>null</i>.
     */
    @objid ("c87599c3-67d8-4cd3-b2a7-67f30d2e9a44")
    public static UML2CreateObjectAction instantiate(OpaqueAction obj) {
        return UML2CreateObjectAction.canInstantiate(obj) ? new UML2CreateObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("52e53453-b981-43c2-9df2-bd0a38b29918")
    public static UML2CreateObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateObjectAction.canInstantiate(obj))
        	return new UML2CreateObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("36339a0d-c6f8-4919-b253-3c32613392de")
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
        UML2CreateObjectAction other = (UML2CreateObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("d8be86ba-8ab2-459f-a477-edf82aa8ac08")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("6908be42-e6ea-4911-b853-f9dd51d6dde2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("abe97c37-4101-426d-98d5-73e6b16a6b0d")
    protected  UML2CreateObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("c60db936-9649-41b7-9b99-afbe48991116")
    public static final class MdaTypes {
        @objid ("b6cebc5b-9d77-4468-b768-66af62f52ccd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("074db37a-1916-49ac-a557-006ca0dcf678")
        private static Stereotype MDAASSOCDEP;

        @objid ("81819965-e5a6-44ba-b855-46d0bb1ca2ff")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("352bd1e1-1030-4dbf-93e4-3119ffe04fe1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5582e283-c2f9-11de-8ac8-001302895b2b");
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
