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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << new >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0b720f13-4154-4751-a90f-aadaef5fa9dd")
public class New {
    @objid ("ce32891f-cc3e-4d9e-935f-c51b5af8526e")
    public static final String STEREOTYPE_NAME = "new";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("f6d8a23a-dbba-4a5b-98a5-896209f27e29")
    protected final Constraint elt;

    /**
     * Tells whether a {@link New proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << new >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a4fae60b-d0b8-4944-8d4c-b1aef0b98a18")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, New.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << new >> then instantiate a {@link New} proxy.
     * 
     * @return a {@link New} proxy on the created {@link Constraint}.
     */
    @objid ("9d2a4277-22a1-4f00-9628-5bc996cf9b6a")
    public static New create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, New.STEREOTYPE_NAME);
        return New.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link New} proxy from a {@link Constraint} stereotyped << new >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link New} proxy or <i>null</i>.
     */
    @objid ("d41e4b39-efc5-4142-8683-115d90a95717")
    public static New instantiate(Constraint obj) {
        return New.canInstantiate(obj) ? new New(obj) : null;
    }

    /**
     * Tries to instantiate a {@link New} proxy from a {@link Constraint} stereotyped << new >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link New} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b60d21ff-f4f7-4707-90c2-6254a600561f")
    public static New safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (New.canInstantiate(obj))
        	return new New(obj);
        else
        	throw new IllegalArgumentException("New: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("40c0f951-a507-4d46-a20b-7a78fc36bbfc")
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
        New other = (New) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("fd574def-1e9a-4205-813a-6a298153dfe0")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("05e68dd7-05e8-476e-87b4-f79f795f3484")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("63a6b241-bae4-4de8-b5dd-3d419d364d13")
    protected New(Constraint elt) {
        this.elt = elt;
    }

    @objid ("2f9de4ba-2666-4917-a258-dbdafc9f0143")
    public static final class MdaTypes {
        @objid ("ed29add5-39fd-49c7-9d2f-c9ae1d3b47dd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("aa4cf0a8-edae-4506-b93c-097e128714a1")
        private static Stereotype MDAASSOCDEP;

        @objid ("4aa7b239-7b89-45aa-99e8-b0800348029c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("63cfd9fe-1711-4439-940e-61762b0fbf90")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f9-0000-000000000000");
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
