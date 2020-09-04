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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e4af2c83-13be-47e2-8ac2-04a5585645e4")
public class UML2ReadLinkAction {
    @objid ("535b3173-32e1-4fd4-b905-738e3d313a42")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("4bd6f7de-5afd-42c7-a02b-0f1ffc203b36")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("53e33250-7f79-455b-9bf7-5513cc758470")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkAction >> then instantiate a {@link UML2ReadLinkAction} proxy.
     * 
     * @return a {@link UML2ReadLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("a45bd2a6-eba7-43d4-9fd3-5a8a7850b8dd")
    public static UML2ReadLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkAction.STEREOTYPE_NAME);
        return UML2ReadLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkAction} proxy or <i>null</i>.
     */
    @objid ("455f617e-d93a-435d-839f-02c30426ccc2")
    public static UML2ReadLinkAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkAction.canInstantiate(obj) ? new UML2ReadLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3ae9a669-f1ff-448f-8fe8-44992d233dc1")
    public static UML2ReadLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkAction.canInstantiate(obj))
        	return new UML2ReadLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("38e66edf-d345-415c-afe5-4eec26459c83")
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
        UML2ReadLinkAction other = (UML2ReadLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("c0406fe7-56f8-478c-8d25-8b65f35b33a0")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("9255d35d-075f-4dab-af72-ba81f5878c31")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2bfb593f-25ff-4bfd-a8b9-8f53cf7dce11")
    protected UML2ReadLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f6b43d50-d0f2-4317-8e56-ff05aca49733")
    public static final class MdaTypes {
        @objid ("ce363822-5e20-4c5b-9549-5eb77741bb8e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("af61a3d7-66b0-447f-9525-0eb8574b58da")
        private static Stereotype MDAASSOCDEP;

        @objid ("6d3dc025-5ed3-48ba-a36e-77f46eaf8403")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f140ea35-e98c-426d-a9c6-26517d0798b2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6e2770bf-c2f9-11de-8ac8-001302895b2b");
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
