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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearAssociationAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("288adbf4-c847-449e-8f5e-4defde131fe0")
public class UML2ClearAssociationAction {
    @objid ("f9de561e-e83b-499d-a780-9dfb17442f5c")
    public static final String STEREOTYPE_NAME = "UML2ClearAssociationAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("4d7bc4e0-1671-4965-92ca-973f32017323")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearAssociationAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("bcb4e14d-86da-4bfc-a7bb-af2732acd272")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearAssociationAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >> then instantiate a {@link UML2ClearAssociationAction} proxy.
     * 
     * @return a {@link UML2ClearAssociationAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("8f6deee9-48dd-4fd1-911f-3bcb8d6a7766")
    public static UML2ClearAssociationAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearAssociationAction.STEREOTYPE_NAME);
        return UML2ClearAssociationAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearAssociationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearAssociationAction} proxy or <i>null</i>.
     */
    @objid ("db00618d-b396-4a8b-b01e-c56abf572fe7")
    public static UML2ClearAssociationAction instantiate(OpaqueAction obj) {
        return UML2ClearAssociationAction.canInstantiate(obj) ? new UML2ClearAssociationAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearAssociationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearAssociationAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("654971f3-891e-4add-a96a-508c961c8584")
    public static UML2ClearAssociationAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearAssociationAction.canInstantiate(obj))
        	return new UML2ClearAssociationAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearAssociationAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8e57f96f-c5cf-4906-9816-976b77292b32")
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
        UML2ClearAssociationAction other = (UML2ClearAssociationAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("958cf0ff-6e6f-4105-bea4-d7df699787b1")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("7dedd2d5-18be-419c-8750-4805cc82bb11")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("0d9de30a-0173-41f3-a489-8a8d7859d510")
    protected  UML2ClearAssociationAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("c62134b2-bb18-4381-9964-0e7216166c07")
    public static final class MdaTypes {
        @objid ("ed0958e2-bbad-4b80-bd41-44e9f7b3f7a7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2e0f0ef7-3fbe-4040-adef-6a7551b0ba0b")
        private static Stereotype MDAASSOCDEP;

        @objid ("5452bca6-ed5f-4f64-8c93-8368cb5b0f5a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("be85d531-76d9-407f-b954-31a3802cb197")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "89927bbf-c2f9-11de-8ac8-001302895b2b");
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
