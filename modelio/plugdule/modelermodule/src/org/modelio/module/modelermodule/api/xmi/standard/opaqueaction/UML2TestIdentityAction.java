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
 * Proxy class to handle a {@link OpaqueAction} with << UML2TestIdentityAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4865d52c-c25c-4167-ada9-55cc79a6e9e3")
public class UML2TestIdentityAction {
    @objid ("c64ed0a8-aa21-4e45-9df8-c20e4f500143")
    public static final String STEREOTYPE_NAME = "UML2TestIdentityAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("9a16ea1c-4da9-425a-82e9-635a34f36851")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2TestIdentityAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d1e28b10-f1ec-4388-b966-d2e961b2902d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2TestIdentityAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2TestIdentityAction >> then instantiate a {@link UML2TestIdentityAction} proxy.
     * 
     * @return a {@link UML2TestIdentityAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("f8562788-1417-4fca-b3ac-e4bae4dae518")
    public static UML2TestIdentityAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2TestIdentityAction.STEREOTYPE_NAME);
        return UML2TestIdentityAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2TestIdentityAction} proxy from a {@link OpaqueAction} stereotyped << UML2TestIdentityAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2TestIdentityAction} proxy or <i>null</i>.
     */
    @objid ("33e1bbb9-ff50-47b1-8ecd-19466eb913d0")
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
    @objid ("cabc0bf9-c837-49d0-b2b0-de4fb8939556")
    public static UML2TestIdentityAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2TestIdentityAction.canInstantiate(obj))
        	return new UML2TestIdentityAction(obj);
        else
        	throw new IllegalArgumentException("UML2TestIdentityAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3fe89e17-0c53-4a7a-b15d-1822eee0e413")
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
    @objid ("be10251b-0d3d-47ec-bb4c-5cef1fe4bbff")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("fa6951d5-2bb0-4248-a3a5-c6e0845bbbb2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("86ed5d21-69e4-4b45-bcbc-68dab35cd859")
    protected UML2TestIdentityAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("203724f2-4489-44f6-9abf-f0908a8c4247")
    public static final class MdaTypes {
        @objid ("87d10833-e97f-4faa-ad81-4986f0c8859d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c1800eb9-afac-42d9-9cd9-6855fe695d36")
        private static Stereotype MDAASSOCDEP;

        @objid ("72ae0318-54fa-4747-9780-5ec4d54eec34")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fa533f83-589f-41ae-a6ea-623eab9f8782")
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
