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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearStructuralFeatureAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("53b7b102-f9af-40b7-8249-2817a64d671f")
public class UML2ClearStructuralFeatureAction {
    @objid ("5db2a664-e9dc-4b51-b669-aeb9466e8921")
    public static final String STEREOTYPE_NAME = "UML2ClearStructuralFeatureAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("3f81a7eb-93b7-4520-956f-39dfde6b0407")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearStructuralFeatureAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8d3b1198-431b-482e-8d45-de1daa288d92")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearStructuralFeatureAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearStructuralFeatureAction >> then instantiate a {@link UML2ClearStructuralFeatureAction} proxy.
     * 
     * @return a {@link UML2ClearStructuralFeatureAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("40b246b3-6fb0-4b18-b0f4-6fb8c5dde6ac")
    public static UML2ClearStructuralFeatureAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
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
    @objid ("0116ca0f-969a-4ad6-81c9-8eda1e25820e")
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
    @objid ("a092ee10-3677-4598-839e-04bcc270bb93")
    public static UML2ClearStructuralFeatureAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearStructuralFeatureAction.canInstantiate(obj))
        	return new UML2ClearStructuralFeatureAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearStructuralFeatureAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e7cb97f3-ad7c-4c19-af59-a8b7b071d29e")
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
    @objid ("3c24fb30-60d3-408b-9000-53018199735e")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("22e208e5-e0cf-4327-91a9-65bedfea5341")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("1f823f04-419c-4dba-aa84-c90f18bf96a0")
    protected  UML2ClearStructuralFeatureAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("572079e7-f6d0-490a-884b-150e1d9022cb")
    public static final class MdaTypes {
        @objid ("cda69b37-11fc-4dff-84f8-b2d1129da9fe")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e5ffd86e-5df1-4d5b-9f6c-b2f64512f787")
        private static Stereotype MDAASSOCDEP;

        @objid ("62ae5785-6741-4d4a-b608-412312aab79d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cf30e48b-b979-4fed-ba4c-9799a72ce2d3")
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
