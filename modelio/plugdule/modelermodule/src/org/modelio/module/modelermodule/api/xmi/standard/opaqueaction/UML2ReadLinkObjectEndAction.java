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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkObjectEndAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f3d58a44-d387-43bb-90ad-1becc98b66d6")
public class UML2ReadLinkObjectEndAction {
    @objid ("8e4525d9-44c2-4dc3-a29a-550045bd92ca")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkObjectEndAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("8bc8375d-16ce-4f8d-8520-bb1a57edfa04")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkObjectEndAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("351a4cdf-0796-4ffb-80a5-a44fccc82c0c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> then instantiate a {@link UML2ReadLinkObjectEndAction} proxy.
     * 
     * @return a {@link UML2ReadLinkObjectEndAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("d4ade6af-751f-4c98-8cbb-867bc2800c52")
    public static UML2ReadLinkObjectEndAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndAction.STEREOTYPE_NAME);
        return UML2ReadLinkObjectEndAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkObjectEndAction} proxy or <i>null</i>.
     */
    @objid ("3f73bfb1-9d0f-404c-8e6a-cd76fa1d58a4")
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
    @objid ("e9f524db-1273-4866-b44c-74c26f63a083")
    public static UML2ReadLinkObjectEndAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkObjectEndAction.canInstantiate(obj))
        	return new UML2ReadLinkObjectEndAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkObjectEndAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("dbe5201b-0e6e-4ebc-9df2-411077b9c16a")
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
    @objid ("6fa36b7b-cedf-4a33-a728-0122f00b80d4")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("e5ab69e8-85d4-4845-a755-e6285343ed44")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d28046e9-822d-459e-b469-ec10ddb272f5")
    protected UML2ReadLinkObjectEndAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("b56e0188-35ac-4bc6-abe5-96c4afc8d821")
    public static final class MdaTypes {
        @objid ("6690b0aa-c83b-440b-9f04-8d5ea2548155")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0d831680-71dc-482a-b11a-efbd49280375")
        private static Stereotype MDAASSOCDEP;

        @objid ("48161a4a-98b3-4ed7-9671-19ab907f9ebd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9c33b21f-17ef-4b06-9685-9e7d0b2839de")
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
