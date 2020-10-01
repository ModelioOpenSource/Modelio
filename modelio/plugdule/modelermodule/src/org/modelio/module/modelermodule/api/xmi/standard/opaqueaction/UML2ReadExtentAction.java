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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadExtentAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f44efdd0-3ec1-476f-899a-8869358862ec")
public class UML2ReadExtentAction {
    @objid ("47db0ea8-da77-4361-9a43-cc977ac5bc54")
    public static final String STEREOTYPE_NAME = "UML2ReadExtentAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("bee014e7-9c08-4a89-891a-0ff895818f2b")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadExtentAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("86e1828e-c33c-4142-bc22-7a1b9c1ca496")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadExtentAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> then instantiate a {@link UML2ReadExtentAction} proxy.
     * 
     * @return a {@link UML2ReadExtentAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("1d08d5c9-7639-4834-b6dd-7ab235df1e98")
    public static UML2ReadExtentAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadExtentAction.STEREOTYPE_NAME);
        return UML2ReadExtentAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadExtentAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadExtentAction} proxy or <i>null</i>.
     */
    @objid ("e03bde92-0e07-41d1-be92-a34b75a1c315")
    public static UML2ReadExtentAction instantiate(OpaqueAction obj) {
        return UML2ReadExtentAction.canInstantiate(obj) ? new UML2ReadExtentAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadExtentAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadExtentAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadExtentAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0b83a816-3b4c-4247-aa9f-7b51eeac6448")
    public static UML2ReadExtentAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadExtentAction.canInstantiate(obj))
        	return new UML2ReadExtentAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadExtentAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("67fe532b-3c56-4de2-87a1-2e6eb196072f")
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
        UML2ReadExtentAction other = (UML2ReadExtentAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("ac165933-62b9-48d7-ab78-6c37b15fef12")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("eddfa4d1-dab6-4286-adbe-d169e65a03b0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8c719f57-ee8f-4cf9-a31c-67e260123d6b")
    protected UML2ReadExtentAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("5b0571b6-d970-4ab3-9d56-4b8aba8bc9d6")
    public static final class MdaTypes {
        @objid ("dc838695-d30e-442d-acb0-3e6b5966089b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("04ed4389-faeb-401d-a274-b2590593e716")
        private static Stereotype MDAASSOCDEP;

        @objid ("211ae069-8abe-46de-b3da-10287ceabc4b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b649d490-5dd1-42c3-8d57-f2e78175224d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c4b3add1-c2f9-11de-8ac8-001302895b2b");
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
