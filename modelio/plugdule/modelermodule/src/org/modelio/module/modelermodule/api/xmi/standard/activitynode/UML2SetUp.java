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
    @objid ("d9ee6664-d95e-40cb-aa47-6b63e1bca59d")
    public static final String STEREOTYPE_NAME = "UML2SetUp";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("7818ac25-d2cd-43d7-b316-cbbcc1c9441f")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link UML2SetUp proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode} stereotyped << UML2SetUp >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("715b2655-b7cb-4221-b5cf-2d76c1d45c12")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ActivityNode) && ((ActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SetUp.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ActivityNode} stereotyped << UML2SetUp >> then instantiate a {@link UML2SetUp} proxy.
     * 
     * @return a {@link UML2SetUp} proxy on the created {@link ActivityNode}.
     */
    @objid ("831bc981-09d2-4217-ba17-d81a4030f29c")
    public static UML2SetUp create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SetUp.STEREOTYPE_NAME);
        return UML2SetUp.instantiate((ActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2SetUp} proxy from a {@link ActivityNode} stereotyped << UML2SetUp >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityNode
     * @return a {@link UML2SetUp} proxy or <i>null</i>.
     */
    @objid ("89e87cb8-f51b-46cc-8b70-163fca377dab")
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
    @objid ("b4611276-22c6-4a21-87d4-e720cd649a3c")
    public static UML2SetUp safeInstantiate(ActivityNode obj) throws IllegalArgumentException {
        if (UML2SetUp.canInstantiate(obj))
        	return new UML2SetUp(obj);
        else
        	throw new IllegalArgumentException("UML2SetUp: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("39005bf5-ba61-4165-85c3-68193984f7b7")
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
    @objid ("a17fbc33-2e68-45c8-b77a-4faae61c74ae")
    public ActivityNode getElement() {
        return this.elt;
    }

    @objid ("d6ecf3a1-32a6-4f03-b9c1-58563ecafd06")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0215de43-c94f-440a-a1e3-47f2ea6c0381")
    protected UML2SetUp(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("f4de3ad0-deac-44e6-8597-cd122260702a")
    public static final class MdaTypes {
        @objid ("8e536ea0-07e2-4096-b5e7-d1e08add2916")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("84c8f820-137f-4b3d-a3ac-ba6e9edad6b2")
        private static Stereotype MDAASSOCDEP;

        @objid ("65ae734f-ac70-4187-8d35-5596cce99f4c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d03eed70-3658-4ba5-9f7a-03db5703f146")
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
