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
 * Proxy class to handle a {@link OpaqueAction} with << UML2AddStructuralFeatureValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("24df620c-84f5-4efe-a9d0-98c1fa6cf86f")
public class UML2AddStructuralFeatureValueAction {
    @objid ("a796b588-7d20-426c-9150-2a4cb6184a62")
    public static final String STEREOTYPE_NAME = "UML2AddStructuralFeatureValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("147f05d5-9cb2-4193-b555-28fb8468ea03")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2AddStructuralFeatureValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d397063a-96a7-449d-bec4-dd92e9d1490a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AddStructuralFeatureValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2AddStructuralFeatureValueAction >> then instantiate a {@link UML2AddStructuralFeatureValueAction} proxy.
     * 
     * @return a {@link UML2AddStructuralFeatureValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b0fa11bc-5946-4603-9dbf-993c2e1c959f")
    public static UML2AddStructuralFeatureValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
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
    @objid ("7bd5a388-b9e6-4f3a-aecb-fa35b3c82faf")
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
    @objid ("b99b5362-ee5c-47b8-9102-b1265c7786d0")
    public static UML2AddStructuralFeatureValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2AddStructuralFeatureValueAction.canInstantiate(obj))
        	return new UML2AddStructuralFeatureValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2AddStructuralFeatureValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("329fd32a-ff06-464e-bb37-0a1172ef987b")
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
    @objid ("ed433e67-f320-4886-bbde-16125596840b")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c854e14e-1cc0-4789-93d3-6870a507db2b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("67dfcf10-a583-4f80-8ede-6e412f1f8c29")
    protected  UML2AddStructuralFeatureValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("24fa7251-4d39-4e6e-9612-9484b246a75b")
    public static final class MdaTypes {
        @objid ("297038c4-49b8-47e2-93c7-0b4d335b526e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b0963e0b-ebc7-40ea-9263-b3a708888aa4")
        private static Stereotype MDAASSOCDEP;

        @objid ("334a7eaf-3662-437c-82fb-0b7c3aabe1e5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d9e12c42-b010-40d3-9d04-2251044ce1a3")
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
