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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2b0053d4-f916-41fb-ae12-2a439d6dce56")
public class UML2ReadStructuralFeatureAction {
    @objid ("02b98873-2443-4f5b-baf1-4e1fb695225d")
    public static final String STEREOTYPE_NAME = "UML2ReadStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("d29ddacd-2b89-4650-b6be-70d065ac5c19")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9b982f78-0869-4836-ad39-c2f3e0d496ab")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >> then instantiate a {@link UML2ReadStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2ReadStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("8d15e30d-93c4-4ef4-a308-cf73359c8e88")
    public static UML2ReadStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadStructuralFeatureAction.STEREOTYPE_NAME);
        return UML2ReadStructuralFeatureAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadStructuralFeatureAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadStructuralFeatureAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadStructuralFeatureAction} proxy or <i>null</i>.
     */
    @objid ("5670eb82-d5b1-4f43-af93-b4d4c64a6c79")
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
    @objid ("4a9fe9c2-971f-4648-9db4-8b633fbfa7bb")
    public static UML2ReadStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadStructuralFeatureAction.canInstantiate(obj))
        	return new UML2ReadStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a92afc6e-dd9f-4f0d-8640-70195c9a68d6")
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
    @objid ("80b6f89c-4031-45a7-888f-e4189b318791")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("8ec17c49-7878-4774-8038-68a4d534b501")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e3ce96b3-4667-48bb-8834-a74aa117de30")
    protected UML2ReadStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("e8682448-1fad-44d6-88fd-6b31e1005623")
    public static final class MdaTypes {
        @objid ("55fad0a3-88d8-48c1-b924-54633d0d90d0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6395d8de-26f9-4f7d-8fff-cc77da97172c")
        private static Stereotype MDAASSOCDEP;

        @objid ("cb4d398d-b288-49e6-99a1-8a07413ab306")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7b4c8e5b-138b-4829-b32e-ba7c802bf4d5")
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
