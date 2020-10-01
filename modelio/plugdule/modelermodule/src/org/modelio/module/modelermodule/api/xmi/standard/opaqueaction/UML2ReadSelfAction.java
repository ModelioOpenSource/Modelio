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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadSelfAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("797d5347-a197-414c-84b4-44f2f877b47e")
public class UML2ReadSelfAction {
    @objid ("f37ce612-b75b-49c1-b838-2a147c796966")
    public static final String STEREOTYPE_NAME = "UML2ReadSelfAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("62811217-615a-4bc3-837d-255bb51529fb")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadSelfAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e36c3566-2e3b-4d42-b6e2-9de520b7a60c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadSelfAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> then instantiate a {@link UML2ReadSelfAction} proxy.
     * 
     * @return a {@link UML2ReadSelfAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("2e1b3887-8e33-4dce-a7c9-cd02522d0120")
    public static UML2ReadSelfAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadSelfAction.STEREOTYPE_NAME);
        return UML2ReadSelfAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadSelfAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadSelfAction} proxy or <i>null</i>.
     */
    @objid ("4ddba35e-4a48-4774-a748-50adf032f4e5")
    public static UML2ReadSelfAction instantiate(OpaqueAction obj) {
        return UML2ReadSelfAction.canInstantiate(obj) ? new UML2ReadSelfAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadSelfAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadSelfAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("085a5dc6-c879-4bd0-b240-7444cb7f0b6e")
    public static UML2ReadSelfAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadSelfAction.canInstantiate(obj))
        	return new UML2ReadSelfAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadSelfAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0364e33e-c29a-407b-aff4-a89c4aa8f908")
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
        UML2ReadSelfAction other = (UML2ReadSelfAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("b29c8a47-4554-4f55-b9cf-ded1272393c1")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("3a197cad-2192-4928-b299-c92aa24b61ca")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ad1f21ff-18fd-4bb6-9bc1-5278a26edda7")
    protected UML2ReadSelfAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("9b695724-53ea-403f-aee0-9d75de248162")
    public static final class MdaTypes {
        @objid ("4fb27f2c-297e-4731-95c8-d1bf08628e55")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("568c4f2f-948d-47e8-8964-49cf6fd028c5")
        private static Stereotype MDAASSOCDEP;

        @objid ("65a882b7-505a-441a-86ca-8fa530c0e799")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("850f4254-6a42-43b8-857d-81e4bb2ba751")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "120a35e7-c2fd-11de-8ac8-001302895b2b");
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
