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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkObjectEndAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f3d58a44-d387-43bb-90ad-1becc98b66d6")
public class UML2ReadLinkObjectEndAction {
    @objid ("aec5e714-5e00-4e03-8943-633f6256fc0b")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkObjectEndAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("daa8909c-546a-4fa6-ac6d-f1a5df224ed2")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkObjectEndAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("723a2d5b-ed9d-46a6-bacd-2e9a708d8822")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> then instantiate a {@link UML2ReadLinkObjectEndAction} proxy.
     * 
     * @return a {@link UML2ReadLinkObjectEndAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("48ee09a5-2005-4e00-bab9-2610133736b1")
    public static UML2ReadLinkObjectEndAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndAction.STEREOTYPE_NAME);
        return UML2ReadLinkObjectEndAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkObjectEndAction} proxy or <i>null</i>.
     */
    @objid ("cda42fcd-ef85-459c-acc8-03413749619b")
    public static UML2ReadLinkObjectEndAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkObjectEndAction.canInstantiate(obj) ? new UML2ReadLinkObjectEndAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkObjectEndAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bd717cbd-287f-45a0-8f9a-4acee8ae5319")
    public static UML2ReadLinkObjectEndAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkObjectEndAction.canInstantiate(obj))
        	return new UML2ReadLinkObjectEndAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkObjectEndAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9ce5ab42-849c-4c48-a562-9f831179f23c")
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
        UML2ReadLinkObjectEndAction other = (UML2ReadLinkObjectEndAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("0feb7519-2a26-446e-8f01-72d047c33c5a")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("e11c8d80-941b-4b89-a3b8-73fc610e800b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2ba9b72d-fe9e-4bf4-9a2f-522f7fa6e7cc")
    protected UML2ReadLinkObjectEndAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("b56e0188-35ac-4bc6-abe5-96c4afc8d821")
    public static final class MdaTypes {
        @objid ("798a0c95-a4e1-4236-b618-893d620e1e5b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b5c17f2a-7680-49f3-a3c2-05512ea8c0eb")
        private static Stereotype MDAASSOCDEP;

        @objid ("c3e01758-3e00-45b5-9ddd-47a0d2fdec89")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("de34088b-dfea-4fee-bc66-810747614855")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f9061fa3-c2fc-11de-8ac8-001302895b2b");
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
