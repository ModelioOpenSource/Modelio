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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearAssociationAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("288adbf4-c847-449e-8f5e-4defde131fe0")
public class UML2ClearAssociationAction {
    @objid ("6e2563bc-f0d5-4531-b5ce-1f319dc4f641")
    public static final String STEREOTYPE_NAME = "UML2ClearAssociationAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("3cbbe4f1-7497-4653-94a6-da4b6b760b2c")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearAssociationAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a6c6f878-2417-4951-9006-a2fb50acb522")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearAssociationAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearAssociationAction >> then instantiate a {@link UML2ClearAssociationAction} proxy.
     * 
     * @return a {@link UML2ClearAssociationAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("d1b5a1f0-983d-4042-bbd2-7f0e3db96810")
    public static UML2ClearAssociationAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
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
    @objid ("b05cc189-7b2b-49ef-aca7-9653189b4714")
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
    @objid ("5c3c957c-2cd5-4ae5-ad37-e8183fcc92f2")
    public static UML2ClearAssociationAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearAssociationAction.canInstantiate(obj))
        	return new UML2ClearAssociationAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearAssociationAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("aa973dda-2f5e-4b5e-a56b-aab8f0aa86f3")
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
    @objid ("687ff973-c76e-49e3-8af9-669e8e8f34b0")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c10078b6-c368-474a-9565-1a14f1987560")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a13b12c5-c771-485e-8ce1-539ee59c6a60")
    protected UML2ClearAssociationAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("c62134b2-bb18-4381-9964-0e7216166c07")
    public static final class MdaTypes {
        @objid ("0c370776-6aaa-490a-9e6a-164a583a1012")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("78aaec09-f6bb-4699-a68d-181559fb232d")
        private static Stereotype MDAASSOCDEP;

        @objid ("8df34c83-78e4-43e2-a36d-86be35831a41")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("21c75aa1-5cf4-4f52-87f5-77045e73d72c")
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
