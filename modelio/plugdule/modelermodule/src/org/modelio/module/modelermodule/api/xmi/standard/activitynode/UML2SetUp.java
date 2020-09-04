/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.module.modelermodule.api.xmi.standard.activitynode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
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
 * Proxy class to handle a {@link ActivityNode} with << UML2SetUp >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("eb6de59a-6ea2-4168-8059-82456e9bf47e")
public class UML2SetUp {
    @objid ("a7194094-5a2d-4a87-a699-09990db0a643")
    public static final String STEREOTYPE_NAME = "UML2SetUp";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("68aee023-8c05-47a3-b5da-5241abf6c99f")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link UML2SetUp proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode} stereotyped << UML2SetUp >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cab79bb6-6791-48f7-b6e9-805a7ae1511a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ActivityNode) && ((ActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SetUp.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ActivityNode} stereotyped << UML2SetUp >> then instantiate a {@link UML2SetUp} proxy.
     * 
     * @return a {@link UML2SetUp} proxy on the created {@link ActivityNode}.
     */
    @objid ("e7fa30c8-0e91-434d-942d-e5d310a4656e")
    public static UML2SetUp create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SetUp.STEREOTYPE_NAME);
        return UML2SetUp.instantiate((ActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2SetUp} proxy from a {@link ActivityNode} stereotyped << UML2SetUp >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityNode
     * @return a {@link UML2SetUp} proxy or <i>null</i>.
     */
    @objid ("ff37ef6b-1c77-494d-aea5-7198f334f8df")
    public static UML2SetUp instantiate(ActivityNode obj) {
        return UML2SetUp.canInstantiate(obj) ? new UML2SetUp(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2SetUp} proxy from a {@link ActivityNode} stereotyped << UML2SetUp >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ActivityNode}
     * @return a {@link UML2SetUp} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("75d7f107-85ff-4c50-a48f-95d264313918")
    public static UML2SetUp safeInstantiate(ActivityNode obj) throws IllegalArgumentException {
        if (UML2SetUp.canInstantiate(obj))
        	return new UML2SetUp(obj);
        else
        	throw new IllegalArgumentException("UML2SetUp: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c5f0bfab-2bbe-436b-ad3f-c3dc19e45b3c")
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
        UML2SetUp other = (UML2SetUp) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ActivityNode}. 
     * @return the ActivityNode represented by this proxy, never null.
     */
    @objid ("42ed134a-5a3f-47aa-b335-ef69909626d9")
    public ActivityNode getElement() {
        return this.elt;
    }

    @objid ("b54a7b76-634d-4274-b642-d0e800d69328")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("255e7bf0-0151-4c11-88c2-e4753fd31963")
    protected UML2SetUp(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("f4de3ad0-deac-44e6-8597-cd122260702a")
    public static final class MdaTypes {
        @objid ("a8a5ac8c-e500-4435-b4c1-4a8d4af87743")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0422068b-e8d1-4dc1-8f68-0fc5df18b5c9")
        private static Stereotype MDAASSOCDEP;

        @objid ("a8aed421-8974-4c7b-b720-69b5df747168")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2b7a969d-b144-4aca-8e06-577b454d752a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "86eada10-32d9-11e0-91f3-0027103f347c");
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
