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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("53b7b102-f9af-40b7-8249-2817a64d671f")
public class UML2ClearStructuralFeatureAction {
    @objid ("9d28cdfa-3bac-4523-adbd-6d0f7d0e745f")
    public static final String STEREOTYPE_NAME = "UML2ClearStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("f54a156d-f28f-4d0b-abf4-103bc382d6d3")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("adf873b7-19b8-4d79-a76c-79038fb0849e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >> then instantiate a {@link UML2ClearStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2ClearStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("ef8dcad9-8309-4ad5-a778-82d6c4f33719")
    public static UML2ClearStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2ClearStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("1783ddc2-73da-424c-8ca4-b19e231d0f4a")
    public static UML2ClearStructuralFeatureAction instantiate(OpaqueAction obj) {
        return UML2ClearStructuralFeatureAction.canInstantiate(obj) ? new UML2ClearStructuralFeatureAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearStructuralFeatureAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b04617fe-31fa-47f3-bf1c-1e6874cd9c0a")
    public static UML2ClearStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearStructuralFeatureAction.canInstantiate(obj))
        	return new UML2ClearStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b73c6cf0-3e09-453b-bbc6-183167c394ad")
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
        UML2ClearStructuralFeatureAction other = (UML2ClearStructuralFeatureAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("b387539f-e864-46eb-b1bc-df458de6779d")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c3ffa273-b949-43af-b265-a9a6f711e154")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("676f99e8-42fb-4619-b4bf-1b8c6139da3d")
    protected UML2ClearStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("572079e7-f6d0-490a-884b-150e1d9022cb")
    public static final class MdaTypes {
        @objid ("ebd475ac-729f-48bb-9756-fdf7a96d8044")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("799d8a6c-1459-49ec-a7dd-9b9351149041")
        private static Stereotype MDAASSOCDEP;

        @objid ("fc166cfb-6292-4e41-8222-e3f83d9f2d07")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0a3a99e0-944b-4dc2-8fa9-fd65e5ddeec1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "9fb5321d-c2fc-11de-8ac8-001302895b2b");
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
