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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RemoveStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("483e6c61-31d1-4ff3-912d-56394bd2ed10")
public class UML2RemoveStructuralFeatureAction {
    @objid ("c4372514-bd40-40aa-91f3-d4232ca651eb")
    public static final String STEREOTYPE_NAME = "UML2RemoveStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("a8b4a67d-9aa4-473c-a7aa-8e9678a1c317")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RemoveStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("dcc9c362-a757-45e0-a4ac-039fbc9727d5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >> then instantiate a {@link UML2RemoveStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("e29e55c5-63f9-49a7-8882-b3bc732a3b04")
    public static UML2RemoveStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2RemoveStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveStructuralFeatureAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RemoveStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("9dbcc5c5-bc02-46d4-ba33-4127f821e45a")
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
    @objid ("975a46b4-a27d-4d75-aaf6-66ecf38f9976")
    public static UML2RemoveStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RemoveStructuralFeatureAction.canInstantiate(obj))
        	return new UML2RemoveStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("08575691-783b-4e8b-ad49-ab2b5a9ecab3")
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
    @objid ("c06f15e0-98a4-459c-9c34-fc9dfcf5f415")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("f1c9519b-ad2c-4378-8170-3166daa403ae")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9327a3cf-f05b-4b4c-8e36-f944bd85db58")
    protected UML2RemoveStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5059f30e-33d1-46b2-a966-8c981d0db622")
    public static final class MdaTypes {
        @objid ("a20acbae-fb8f-4946-8910-4eee4733bd41")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3349f5b5-429a-4475-a0d8-7fcb073d90eb")
        private static Stereotype MDAASSOCDEP;

        @objid ("085af5e0-a901-44ed-ab29-15586dd9f13f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3cdfe356-8f9e-4a07-8ea6-182048e7703e")
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
