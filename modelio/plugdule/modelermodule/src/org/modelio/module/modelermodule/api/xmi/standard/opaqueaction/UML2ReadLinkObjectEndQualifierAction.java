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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkObjectEndQualifierAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f2df080d-78a1-4a10-bc63-92b34862282d")
public class UML2ReadLinkObjectEndQualifierAction {
    @objid ("f406ffd2-0b6b-4d49-a441-02d0303d9ccf")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkObjectEndQualifierAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("6838380b-a937-403c-b33a-49f0743b4985")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkObjectEndQualifierAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("24043ce9-95c4-467c-b95f-1c7b9a17a9bf")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> then instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy.
     * 
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("a3351a57-5754-4f24-8445-0ab331223e3a")
    public static UML2ReadLinkObjectEndQualifierAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndQualifierAction.STEREOTYPE_NAME);
        return UML2ReadLinkObjectEndQualifierAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy or <i>null</i>.
     */
    @objid ("c7433fa1-ca0e-4df2-ae66-1aed64009fc3")
    public static UML2ReadLinkObjectEndQualifierAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkObjectEndQualifierAction.canInstantiate(obj) ? new UML2ReadLinkObjectEndQualifierAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndQualifierAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndQualifierAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkObjectEndQualifierAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a92ef688-c81e-4dfe-9202-eb706efd8c13")
    public static UML2ReadLinkObjectEndQualifierAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkObjectEndQualifierAction.canInstantiate(obj))
        	return new UML2ReadLinkObjectEndQualifierAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkObjectEndQualifierAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("25b83f1a-17f5-45bc-827b-6498f4ead788")
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
        UML2ReadLinkObjectEndQualifierAction other = (UML2ReadLinkObjectEndQualifierAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("23e5b3c2-1e8f-4ddd-bb40-a32340ecae3f")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("28698b6e-41fb-4b25-9474-d59f637734a8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e0b6920e-5eba-4ea7-89a9-8ef69014d3b3")
    protected UML2ReadLinkObjectEndQualifierAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5227cd16-da95-4b40-a5e0-22322a3d69eb")
    public static final class MdaTypes {
        @objid ("f0b0b91a-97df-46be-853e-344ea4060a48")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d3be0e2c-2365-4e47-8b51-c932b61befc2")
        private static Stereotype MDAASSOCDEP;

        @objid ("cc1748c1-e5c1-4afe-b0e1-20f43e5e8135")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4f2296bb-9480-42b2-b3ba-2cda2effbda7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "06edcdd9-c2fd-11de-8ac8-001302895b2b");
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
