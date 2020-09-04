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
 * Proxy class to handle a {@link OpaqueAction} with << UML2UnmarshallAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("79f49b91-19a8-4376-b6f1-84c1d30a8534")
public class UML2UnmarshallAction {
    @objid ("44783975-c10b-489e-bead-a9180c5e4197")
    public static final String STEREOTYPE_NAME = "UML2UnmarshallAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("b8dbfb63-a4ae-49bd-8e80-e4a118f4d8eb")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2UnmarshallAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e5e458e1-f612-46c6-8ad0-5be19f0edb28")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2UnmarshallAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> then instantiate a {@link UML2UnmarshallAction} proxy.
     * 
     * @return a {@link UML2UnmarshallAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b0536fa5-9a3e-4abb-a860-ee9c58407bd5")
    public static UML2UnmarshallAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2UnmarshallAction.STEREOTYPE_NAME);
        return UML2UnmarshallAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2UnmarshallAction} proxy from a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2UnmarshallAction} proxy or <i>null</i>.
     */
    @objid ("efd3da13-e1eb-46f5-bee4-21d80a462497")
    public static UML2UnmarshallAction instantiate(OpaqueAction obj) {
        return UML2UnmarshallAction.canInstantiate(obj) ? new UML2UnmarshallAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2UnmarshallAction} proxy from a {@link OpaqueAction} stereotyped << UML2UnmarshallAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2UnmarshallAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("eb18579c-fec2-44e2-8ad5-fe2ff6b86363")
    public static UML2UnmarshallAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2UnmarshallAction.canInstantiate(obj))
        	return new UML2UnmarshallAction(obj);
        else
        	throw new IllegalArgumentException("UML2UnmarshallAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8d7ea849-631d-41f8-a31d-00eddc65525f")
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
        UML2UnmarshallAction other = (UML2UnmarshallAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("0046cc91-0efd-4405-b3d9-b3c4bb123b1f")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("8e455ced-f375-4738-ac1c-b8ef21b31431")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("17af7fc6-1b92-46e4-a2fb-ee1beabbaa9f")
    protected UML2UnmarshallAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("3dccb5eb-58b9-4a9d-ae64-d0336e7eb538")
    public static final class MdaTypes {
        @objid ("4a4a139e-5c80-4057-979d-c6c39465c3da")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("55a55987-5353-4201-ad79-f31da4f76d93")
        private static Stereotype MDAASSOCDEP;

        @objid ("2c78e2d5-adf0-4906-992b-1b0e550001a6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1979e190-e996-4c24-98ff-9986f8b4eafe")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "76c174ab-c2fd-11de-8ac8-001302895b2b");
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
