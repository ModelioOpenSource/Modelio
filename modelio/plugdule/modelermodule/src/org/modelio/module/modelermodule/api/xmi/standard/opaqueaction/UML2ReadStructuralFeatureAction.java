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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2b0053d4-f916-41fb-ae12-2a439d6dce56")
public class UML2ReadStructuralFeatureAction {
    @objid ("04e5223f-3a44-4a9c-8bd8-edbc93269f73")
    public static final String STEREOTYPE_NAME = "UML2ReadStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("798a9fbb-c878-47f6-be8e-49cb43e01974")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c6f5f3a5-0d6a-45d3-b258-9b97dfabad5a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> then instantiate a {@link UML2ReadStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2ReadStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("142c7090-19f9-4b3f-9b50-3c2769158099")
    public static UML2ReadStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2ReadStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("f25d4ebe-7a70-457f-8bda-4a61b9c1548f")
    public static UML2ReadStructuralFeatureAction instantiate(OpaqueAction obj) {
        return UML2ReadStructuralFeatureAction.canInstantiate(obj) ? new UML2ReadStructuralFeatureAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadStructuralFeatureAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("93281b95-3a77-4802-9afa-f53ee3c1fc74")
    public static UML2ReadStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadStructuralFeatureAction.canInstantiate(obj))
        	return new UML2ReadStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("eae02832-e105-4591-ab40-e7ba79708d54")
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
        UML2ReadStructuralFeatureAction other = (UML2ReadStructuralFeatureAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("1fc92c8a-8bb6-4b1d-aa1f-d8ec42ea749a")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("6cadb792-1ee8-47e3-8fe1-df237406b351")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("23febffb-0694-446a-adc6-3edd820510bd")
    protected UML2ReadStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("e8682448-1fad-44d6-88fd-6b31e1005623")
    public static final class MdaTypes {
        @objid ("6fe7222e-9888-4640-9bb7-7cb8f8a40bdf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ef584a69-892a-48d4-b10e-c309898034a6")
        private static Stereotype MDAASSOCDEP;

        @objid ("03ad9a27-8339-4808-aab9-001524e7b223")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3949cc5c-ed2d-4649-b14d-b49c5281dd49")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b9654705-c2f9-11de-8ac8-001302895b2b");
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
