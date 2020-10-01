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
 * Proxy class to handle a {@link OpaqueAction} with << UML2RemoveVariableValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d8be3f15-859f-4f75-bcf6-706f60bc54c9")
public class UML2RemoveVariableValueAction {
    @objid ("34aec7e7-6a7f-4934-beb8-fb9544a63923")
    public static final String STEREOTYPE_NAME = "UML2RemoveVariableValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ce99d0c4-e9e3-4f0a-8608-32bb8f64b463")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2RemoveVariableValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a6b1237f-8bca-46d0-95f2-2feecc233ee4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveVariableValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> then instantiate a {@link UML2RemoveVariableValueAction} proxy.
     * 
     * @return a {@link UML2RemoveVariableValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("5504cc9d-cbb9-49e9-81d0-05d81dc41649")
    public static UML2RemoveVariableValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveVariableValueAction.STEREOTYPE_NAME);
        return UML2RemoveVariableValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2RemoveVariableValueAction} proxy or <i>null</i>.
     */
    @objid ("4ae227b8-4733-4ac5-ab9e-0af2875b0eef")
    public static UML2RemoveVariableValueAction instantiate(OpaqueAction obj) {
        return UML2RemoveVariableValueAction.canInstantiate(obj) ? new UML2RemoveVariableValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2RemoveVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2RemoveVariableValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("84cca2cc-8869-4d02-b701-4439243924a1")
    public static UML2RemoveVariableValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2RemoveVariableValueAction.canInstantiate(obj))
        	return new UML2RemoveVariableValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveVariableValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5c94f7de-8b57-4f09-92be-68d6eb5876d2")
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
        UML2RemoveVariableValueAction other = (UML2RemoveVariableValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("45652ba9-2e73-4281-8368-a6b1ce724ea8")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("fcc3f08d-494e-4d4a-9af7-f0fa3ed8bfc2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("90d227b0-04dd-446d-8355-011dca67500e")
    protected UML2RemoveVariableValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("4788dd43-ab73-4535-a12f-facdd8ad949a")
    public static final class MdaTypes {
        @objid ("a5839410-089d-42d8-8ad2-781d32c69080")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ab7ba619-c110-4d8a-9398-01b1a9a74571")
        private static Stereotype MDAASSOCDEP;

        @objid ("9651b90e-4a0e-466d-b0db-baba4b6a804d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c9d37a89-7adc-46f3-b448-4d7d58ea6ebd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "435869cb-c2fd-11de-8ac8-001302895b2b");
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
