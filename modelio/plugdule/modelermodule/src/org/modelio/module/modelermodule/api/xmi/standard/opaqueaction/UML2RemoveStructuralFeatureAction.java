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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RemoveStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("483e6c61-31d1-4ff3-912d-56394bd2ed10")
public class UML2RemoveStructuralFeatureAction {
    @objid ("3b196f4a-9c27-43f4-a3f3-485704b09eea")
    public static final String STEREOTYPE_NAME = "UML2RemoveStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("b08c1046-1f17-4d9b-bba1-a4ddbbe32531")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RemoveStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("82e31807-18d6-45a0-acbb-05e9eb0aa4d3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> then instantiate a {@link UML2RemoveStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("d29c70fb-cfb8-41d4-8450-7f7dfec95c43")
    public static UML2RemoveStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2RemoveStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("e276aa3f-b29f-4444-8427-1cd92e2615ac")
    public static UML2RemoveStructuralFeatureAction instantiate(OpaqueAction obj) {
        return UML2RemoveStructuralFeatureAction.canInstantiate(obj) ? new UML2RemoveStructuralFeatureAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("49802061-cce7-490d-b146-c2f0286ebb05")
    public static UML2RemoveStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RemoveStructuralFeatureAction.canInstantiate(obj))
        	return new UML2RemoveStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("792d3799-09f5-49ed-aa4f-69ef87f69db2")
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
        UML2RemoveStructuralFeatureAction other = (UML2RemoveStructuralFeatureAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("0f048f65-ccdc-42b7-92b9-b81ca5267744")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("88fc8225-26ee-401f-9b91-03060a3b8000")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9be3a96c-5f3d-4c8e-8663-041a69d59b21")
    protected UML2RemoveStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5059f30e-33d1-46b2-a966-8c981d0db622")
    public static final class MdaTypes {
        @objid ("b5517c5a-5aa0-45b7-8eba-e175a64714cd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b1caf085-40e6-4429-ba42-e6b7abab83de")
        private static Stereotype MDAASSOCDEP;

        @objid ("3f89c8ce-c1a6-469d-a0b8-648fa81ecca9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0029c896-da55-49eb-ba13-398548d8960a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "35b84299-c2fd-11de-8ac8-001302895b2b");
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
