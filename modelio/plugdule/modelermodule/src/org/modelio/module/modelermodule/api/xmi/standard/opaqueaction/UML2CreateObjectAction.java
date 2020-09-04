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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("38d61282-de49-4b5f-9fa5-4c894fb317ea")
public class UML2CreateObjectAction {
    @objid ("0e7b8c3b-a0bc-41da-8722-8f08e1904918")
    public static final String STEREOTYPE_NAME = "UML2CreateObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("dc3112bd-8950-44d4-b1b1-dd8b0cf61d2a")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("04e87e40-26b7-48b4-abb3-d2fa92b1a433")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> then instantiate a {@link UML2CreateObjectAction} proxy.
     * 
     * @return a {@link UML2CreateObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("a4cd82bd-a897-4531-809e-418c4f2d996e")
    public static UML2CreateObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateObjectAction.STEREOTYPE_NAME);
        return UML2CreateObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateObjectAction} proxy or <i>null</i>.
     */
    @objid ("71dcabad-4076-4e1c-95d1-b14b9b6a32c8")
    public static UML2CreateObjectAction instantiate(OpaqueAction obj) {
        return UML2CreateObjectAction.canInstantiate(obj) ? new UML2CreateObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("eca3f4f2-995b-4047-9f8a-ce13850fa62e")
    public static UML2CreateObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateObjectAction.canInstantiate(obj))
        	return new UML2CreateObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("693b95d1-b150-4b80-a5a4-efdcc20809d3")
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
        UML2CreateObjectAction other = (UML2CreateObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("a09e7733-1451-4abf-a47c-68c29cb4b124")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("b367625b-bca0-4c39-91d2-713ef9b9e830")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ac1eb02b-dc97-4f04-a093-fb7d2c6aaa0e")
    protected UML2CreateObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("c60db936-9649-41b7-9b99-afbe48991116")
    public static final class MdaTypes {
        @objid ("b24d455f-6a86-4698-9005-a1e0be975201")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("105cdecd-8084-4317-a498-c8dd05dbcc67")
        private static Stereotype MDAASSOCDEP;

        @objid ("395b934b-1eea-4a9f-a72f-b31dfa0670f9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0c91e9ac-2a8f-4ff2-920a-23cb0032f787")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5582e283-c2f9-11de-8ac8-001302895b2b");
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
