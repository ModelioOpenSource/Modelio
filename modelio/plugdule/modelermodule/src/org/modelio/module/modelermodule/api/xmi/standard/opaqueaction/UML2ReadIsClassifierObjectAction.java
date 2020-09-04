/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("16117e1d-09f6-4ebc-887e-740f5167c6de")
    public static final String STEREOTYPE_NAME = "UML2ReadIsClassifierObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("b500d0df-aa06-4ae2-822e-d4c665019aca")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadIsClassifierObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4ca563fb-0471-4b8e-b9e3-9e3b508c0e0f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >> then instantiate a {@link UML2ReadIsClassifierObjectAction} proxy.
     * 
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("c9464521-5c0e-42d2-8a64-00f2a04907be")
    public static UML2ReadIsClassifierObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadIsClassifierObjectAction.STEREOTYPE_NAME);
        return UML2ReadIsClassifierObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadIsClassifierObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadIsClassifierObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadIsClassifierObjectAction} proxy or <i>null</i>.
     */
    @objid ("d71de10b-13bf-4463-afa4-235ad9811de9")
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
    @objid ("ccf4306b-7909-4472-951c-65fa58e0a351")
    public static UML2ReadIsClassifierObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadIsClassifierObjectAction.canInstantiate(obj))
        	return new UML2ReadIsClassifierObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadIsClassifierObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c0a3eb17-0c8d-4800-b149-f13fd5ac1037")
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
    @objid ("d98a943c-e41d-412a-97b9-be2a1f30f39f")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c1aacb4f-a720-4881-8acd-d9b8bb0992b7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("553ef88b-02f8-49eb-a5cf-3fbc6d560853")
    protected UML2ReadIsClassifierObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("56ed5dde-97b3-43a7-80e9-7b0b898107d8")
    public static final class MdaTypes {
        @objid ("71486305-a0a3-4b1b-ac9b-56e6e94a028f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("07680e66-750a-4c69-a952-1b1a30f9e206")
        private static Stereotype MDAASSOCDEP;

        @objid ("3e96de35-d720-4992-85f6-7664950ea3e2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("891defbe-d47e-4b60-8f78-59bbfbc32a55")
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
