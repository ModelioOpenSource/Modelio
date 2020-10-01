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
 * Proxy class to handle a {@link OpaqueAction} with << UML2AddStructuralFeatureValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("24df620c-84f5-4efe-a9d0-98c1fa6cf86f")
public class UML2AddStructuralFeatureValueAction {
    @objid ("706e4d20-b985-4938-9d25-270d7ff1e4e1")
    public static final String STEREOTYPE_NAME = "UML2AddStructuralFeatureValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("db05b216-1885-4f99-8f50-000d069794af")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2AddStructuralFeatureValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a7c68db4-745c-48e4-8c8b-a9bb25a7920e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >> then instantiate a {@link UML2AddStructuralFeatureValueAction} proxy.
     * 
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("996858ca-b27d-4999-be55-d18713ddda4f")
    public static UML2AddStructuralFeatureValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME);
        return UML2AddStructuralFeatureValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2AddStructuralFeatureValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy or <i>null</i>.
     */
    @objid ("7fc8c6df-aaee-4839-b73e-d265a2f25ea2")
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
    @objid ("bd752f65-c12e-4ba0-8c1b-123a7bb08bf3")
    public static UML2AddStructuralFeatureValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2AddStructuralFeatureValueAction.canInstantiate(obj))
        	return new UML2AddStructuralFeatureValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2AddStructuralFeatureValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bdd39b33-8d08-4126-9a77-8de383d0db7b")
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
    @objid ("e0abf976-10cf-4466-8b48-6b5d5b98c04e")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("5f38dc97-6aa3-4f49-a9b7-6c6b48b11c62")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("374cbf7b-f3ca-4000-8311-c87c1bc1e94d")
    protected UML2AddStructuralFeatureValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("24fa7251-4d39-4e6e-9612-9484b246a75b")
    public static final class MdaTypes {
        @objid ("4bed389b-44de-4aaa-b82f-49c5ca888119")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9426a330-9d63-4ca0-9e23-c0d333ae46d6")
        private static Stereotype MDAASSOCDEP;

        @objid ("8d6d92d5-ab29-4e59-90ac-fbee4e9014c0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3db306aa-e79e-4fc4-ac68-1e023b2571d1")
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
