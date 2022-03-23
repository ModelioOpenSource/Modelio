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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadIsClassifierObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2863745e-922c-4eff-ae51-cb047027a4c0")
public class UML2ReadIsClassifierObjectAction {
    @objid ("2e1dbf33-428f-4d42-b617-90b9faf05176")
    public static final String STEREOTYPE_NAME = "UML2ReadIsClassifierObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("89fae62a-c51c-4358-88ac-b8452c9d97b5")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadIsClassifierObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("87a12b4a-028b-46d3-8aaa-3fb4afb4e8b0")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> then instantiate a {@link UML2ReadIsClassifierObjectAction} proxy.
     * 
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b27be92c-f545-4a3e-972c-8c85dc23a4a4")
    public static UML2ReadIsClassifierObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME);
        return UML2ReadIsClassifierObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadIsClassifierObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy or <i>null</i>.
     */
    @objid ("4baa4085-0a50-41f3-8795-323d68a64817")
    public static UML2ReadIsClassifierObjectAction instantiate(OpaqueAction obj) {
        return UML2ReadIsClassifierObjectAction.canInstantiate(obj) ? new UML2ReadIsClassifierObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadIsClassifierObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c83fca03-b1d1-4795-b9cc-e6f50510ca1c")
    public static UML2ReadIsClassifierObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadIsClassifierObjectAction.canInstantiate(obj))
        	return new UML2ReadIsClassifierObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadIsClassifierObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f441ba62-00a3-46b7-b63c-8b89bc267255")
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
        UML2ReadIsClassifierObjectAction other = (UML2ReadIsClassifierObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("5f0af197-00ef-4fde-b4ee-80bd2d566953")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("76e16175-a5c3-4cb7-ade2-ca115fa099ff")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("6b143204-7056-4d8a-83d2-822a9c9c65e9")
    protected  UML2ReadIsClassifierObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("56ed5dde-97b3-43a7-80e9-7b0b898107d8")
    public static final class MdaTypes {
        @objid ("5b4c42a0-4883-4884-804b-10dfaa32f4a3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b85a3581-3b3d-42bf-b6ed-709ccfc7c16c")
        private static Stereotype MDAASSOCDEP;

        @objid ("e6bb574c-bd1b-403b-9d29-c14b37f0ed9a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("338e4360-0429-45ea-9f5a-c5dab01086f3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e4c6c55f-c2fc-11de-8ac8-001302895b2b");
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
