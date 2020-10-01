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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReduceAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f1c94473-28e1-4fe7-ac2d-cd800cd05029")
public class UML2ReduceAction {
    @objid ("9c37b901-282d-46a1-b045-bb9d552f72b2")
    public static final String STEREOTYPE_NAME = "UML2ReduceAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("cb69d7da-cb03-4ba9-9f42-2c82ba146b8d")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReduceAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReduceAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d14cfcbd-4af7-499d-8625-4af8eee69701")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReduceAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReduceAction >> then instantiate a {@link UML2ReduceAction} proxy.
     * 
     * @return a {@link UML2ReduceAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0e711e66-f0c9-4e6e-b63f-4b5e54a4fc80")
    public static UML2ReduceAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReduceAction.STEREOTYPE_NAME);
        return UML2ReduceAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReduceAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReduceAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReduceAction} proxy or <i>null</i>.
     */
    @objid ("9b7a9136-ce60-41d5-bd9a-0050611abe28")
    public static UML2ReduceAction instantiate(OpaqueAction obj) {
        return UML2ReduceAction.canInstantiate(obj) ? new UML2ReduceAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReduceAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReduceAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReduceAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b2680c20-23e5-4fa8-a5f6-baa6a8b37eca")
    public static UML2ReduceAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReduceAction.canInstantiate(obj))
        	return new UML2ReduceAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReduceAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b3896935-6261-4f61-9785-b4a115f68fb6")
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
        UML2ReduceAction other = (UML2ReduceAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("c116c2c7-64bf-48d4-9f81-f9590ffa760b")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("ac03ce90-4c58-4a28-a365-01abc95dd201")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b39c72b4-93ea-47b7-bfd5-ac9e4b75cb40")
    protected UML2ReduceAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("bb02c893-e717-4243-aea0-fd3720614737")
    public static final class MdaTypes {
        @objid ("2464947e-3063-41c7-9fd7-bc0107aa2f17")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8865909c-b721-4a39-a82c-abf8213dd742")
        private static Stereotype MDAASSOCDEP;

        @objid ("11d146b5-43e1-41f2-9938-48c56ecefb38")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("96676766-c3f3-445c-82d7-c0141260c319")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "2eb4ec1b-c2fd-11de-8ac8-001302895b2b");
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
