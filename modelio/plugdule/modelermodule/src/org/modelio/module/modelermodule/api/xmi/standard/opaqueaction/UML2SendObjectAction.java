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
 * Proxy class to handle a {@link OpaqueAction} with << UML2SendObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1de174fc-1ed7-4452-9f8b-57656fbeb200")
public class UML2SendObjectAction {
    @objid ("40419a4f-2a36-4b7f-a5d2-57cabcd13fbf")
    public static final String STEREOTYPE_NAME = "UML2SendObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("415fdf81-84e9-4b2a-90f5-fe50a806a74f")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2SendObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2SendObjectAction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8600003b-1703-48b0-813a-b7e1552f6ff8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SendObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2SendObjectAction >> then instantiate a {@link UML2SendObjectAction} proxy.
     * 
     * @return a {@link UML2SendObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("4388f1ae-585b-4cb4-8d8f-96deb3be51cf")
    public static UML2SendObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SendObjectAction.STEREOTYPE_NAME);
        return UML2SendObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2SendObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2SendObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2SendObjectAction} proxy or <i>null</i>.
     */
    @objid ("0f78413a-20fa-4c8d-bca9-725cde129508")
    public static UML2SendObjectAction instantiate(OpaqueAction obj) {
        return UML2SendObjectAction.canInstantiate(obj) ? new UML2SendObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2SendObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2SendObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2SendObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2bc0c6f0-c61f-447b-99f1-b6368910c047")
    public static UML2SendObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2SendObjectAction.canInstantiate(obj))
        	return new UML2SendObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2SendObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b839f395-1b40-425e-8c18-9b7d489493fe")
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
        UML2SendObjectAction other = (UML2SendObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("e6fd5822-c9f6-461b-9f56-2c255def84b8")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c090329a-88e0-43f0-b11c-b7c242b5b1fb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("232b7ac0-83fb-43b9-920d-bf8b165313f8")
    protected UML2SendObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("66231600-a2af-46ef-9aea-d8405fd2e16e")
    public static final class MdaTypes {
        @objid ("b84d9757-841f-4c6d-b4ad-e2b6cf602296")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f0cdede7-4a61-4415-8acb-da52b92b6aff")
        private static Stereotype MDAASSOCDEP;

        @objid ("838b2660-dea0-4976-a4fc-f0bba7454325")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("68c860f5-8179-4308-a0d0-93dda07a4ce2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "52d7cccb-c2fd-11de-8ac8-001302895b2b");
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
