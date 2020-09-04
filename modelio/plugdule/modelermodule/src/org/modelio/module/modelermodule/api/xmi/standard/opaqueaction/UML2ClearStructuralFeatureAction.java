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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("53b7b102-f9af-40b7-8249-2817a64d671f")
public class UML2ClearStructuralFeatureAction {
    @objid ("0035b750-006f-41d5-97b7-52646730b1e1")
    public static final String STEREOTYPE_NAME = "UML2ClearStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("a6436d55-b97e-484f-99fe-168fb5e970de")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("16f14b61-9503-4097-a414-d307edd3aa33")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >> then instantiate a {@link UML2ClearStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2ClearStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("cff0968e-98fe-42f0-815a-1e34e91be7d4")
    public static UML2ClearStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2ClearStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("ae76ace6-9b11-43a0-8642-57ab5fb2a76e")
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
    @objid ("50a4a3db-1395-4de5-9158-3cae25ce24bc")
    public static UML2ClearStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearStructuralFeatureAction.canInstantiate(obj))
        	return new UML2ClearStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e33ef471-4065-4b35-9efa-05c29b9e3254")
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
    @objid ("036245d1-19bb-42db-a6c9-36a243a6b253")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("08f4c56d-c40a-4fb0-9a5e-ba4b1bd319be")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("872d7dd8-b803-4d37-9f32-631662e5a0a5")
    protected UML2ClearStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("572079e7-f6d0-490a-884b-150e1d9022cb")
    public static final class MdaTypes {
        @objid ("ed4dbb66-ba0e-48bb-a516-e1d50ce5e343")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e8bcbaa8-0970-4577-8feb-473765da66bf")
        private static Stereotype MDAASSOCDEP;

        @objid ("1b7f68a5-18c5-499b-896e-d0e34d481305")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a610eec0-2471-43cc-9137-83fa0f207e31")
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
