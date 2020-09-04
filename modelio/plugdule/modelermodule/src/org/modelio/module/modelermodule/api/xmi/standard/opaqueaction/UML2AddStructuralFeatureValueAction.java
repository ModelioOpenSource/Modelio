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
 * Proxy class to handle a {@link OpaqueAction} with << UML2AddStructuralFeatureValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("24df620c-84f5-4efe-a9d0-98c1fa6cf86f")
public class UML2AddStructuralFeatureValueAction {
    @objid ("38b6744c-d95f-40bb-a7c5-ec53477ba41e")
    public static final String STEREOTYPE_NAME = "UML2AddStructuralFeatureValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ba2ae76e-16cc-46fc-b4cf-acec07208974")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2AddStructuralFeatureValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0fb7fe3f-f218-4889-879b-0c122609303b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >> then instantiate a {@link UML2AddStructuralFeatureValueAction} proxy.
     * 
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("f45a0b45-8dea-4b25-ad71-89ca6aea1eac")
    public static UML2AddStructuralFeatureValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME);
        return UML2AddStructuralFeatureValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2AddStructuralFeatureValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy or <i>null</i>.
     */
    @objid ("27438ff9-ba0c-4b0a-89c2-96ffc4574ad3")
    public static UML2AddStructuralFeatureValueAction instantiate(OpaqueAction obj) {
        return UML2AddStructuralFeatureValueAction.canInstantiate(obj) ? new UML2AddStructuralFeatureValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AddStructuralFeatureValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("427aec66-5f06-4b96-b323-062ff2666f34")
    public static UML2AddStructuralFeatureValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2AddStructuralFeatureValueAction.canInstantiate(obj))
        	return new UML2AddStructuralFeatureValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2AddStructuralFeatureValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("920a3ec2-95bb-43ca-b6d5-e6b5c73b439e")
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
        UML2AddStructuralFeatureValueAction other = (UML2AddStructuralFeatureValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("d03c40b5-5dbf-4fe4-b95c-49ed599befa1")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("549056e8-8519-4622-904c-1dbfc3ad9e22")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e6074963-2a40-417c-a420-fbaa97ff7cb9")
    protected UML2AddStructuralFeatureValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("24fa7251-4d39-4e6e-9612-9484b246a75b")
    public static final class MdaTypes {
        @objid ("dcadba89-56bd-40f0-b4f9-71b853f8acd4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("39377bc1-90ff-4ab5-b75c-4912c50bed03")
        private static Stereotype MDAASSOCDEP;

        @objid ("6241accf-00d3-4b7d-b3b8-c833c71b8c01")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("369950eb-f578-473d-a99f-8485e5e65f23")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "994fc1e3-c2f9-11de-8ac8-001302895b2b");
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
