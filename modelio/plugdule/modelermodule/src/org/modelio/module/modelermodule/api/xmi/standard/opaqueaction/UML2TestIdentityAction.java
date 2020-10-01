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
 * Proxy class to handle a {@link OpaqueAction} with << UML2TestIdentityAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4865d52c-c25c-4167-ada9-55cc79a6e9e3")
public class UML2TestIdentityAction {
    @objid ("68a6662a-bb90-4a6c-8641-5729d2ef36b2")
    public static final String STEREOTYPE_NAME = "UML2TestIdentityAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("85bc70c4-2571-4f2f-928a-e261bd620fa4")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2TestIdentityAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("838df595-27f1-43bf-b5ee-294993cb49e7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2TestIdentityAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> then instantiate a {@link UML2TestIdentityAction} proxy.
     * 
     * @return a {@link UML2TestIdentityAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("80fb098e-8323-4f93-bfe5-e7efddf2d995")
    public static UML2TestIdentityAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2TestIdentityAction.STEREOTYPE_NAME);
        return UML2TestIdentityAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2TestIdentityAction} proxy from a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2TestIdentityAction} proxy or <i>null</i>.
     */
    @objid ("ba8743f5-07db-4af9-8406-d93daa333ab7")
    public static UML2TestIdentityAction instantiate(OpaqueAction obj) {
        return UML2TestIdentityAction.canInstantiate(obj) ? new UML2TestIdentityAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2TestIdentityAction} proxy from a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2TestIdentityAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0b631ef0-7e54-4d34-a830-a1673b8e8ec1")
    public static UML2TestIdentityAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2TestIdentityAction.canInstantiate(obj))
        	return new UML2TestIdentityAction(obj);
        else
        	throw new IllegalArgumentException("UML2TestIdentityAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("94c60038-7252-4883-b6d2-c233db9e0745")
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
        UML2TestIdentityAction other = (UML2TestIdentityAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("057798d5-7da9-484f-89a9-c5ca87333cbe")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("b69fec2c-bc39-43ea-9f35-e4815a50b907")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c20450ff-fc47-43f1-9151-4b2fea26045e")
    protected UML2TestIdentityAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("203724f2-4489-44f6-9abf-f0908a8c4247")
    public static final class MdaTypes {
        @objid ("d8dff84a-a22c-4603-90bf-22c99c1953e1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2122d578-d5f4-410e-a70c-5bb83c27209e")
        private static Stereotype MDAASSOCDEP;

        @objid ("5abeb297-e21e-42cf-b222-fe286081f930")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0ca6d5dd-da39-47b7-b3fa-930e5db44bc3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6a3f6989-c2fd-11de-8ac8-001302895b2b");
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
