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
 * Proxy class to handle a {@link Constraint} with << postcondition >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0cfb1227-3745-4c76-8e1a-0c23d0f7a8f4")
public class Postcondition {
    @objid ("95fa0ae7-0221-4621-92cc-e776a8219200")
    public static final String STEREOTYPE_NAME = "postcondition";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("b6bb26bc-de97-4e76-8301-f1cbc212f3d0")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Postcondition proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << postcondition >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("515db978-8dbe-4691-874b-676061759225")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Postcondition.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << postcondition >> then instantiate a {@link Postcondition} proxy.
     * 
     * @return a {@link Postcondition} proxy on the created {@link Constraint}.
     */
    @objid ("e6df380e-e9f5-4c50-89d6-c88c8d60d4c1")
    public static Postcondition create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Postcondition.STEREOTYPE_NAME);
        return Postcondition.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Postcondition} proxy from a {@link Constraint} stereotyped << postcondition >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Postcondition} proxy or <i>null</i>.
     */
    @objid ("4af37719-1064-419d-aa5a-5824b1d551c1")
    public static Postcondition instantiate(Constraint obj) {
        return Postcondition.canInstantiate(obj) ? new Postcondition(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Postcondition} proxy from a {@link Constraint} stereotyped << postcondition >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Postcondition} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("39f34ee8-90a3-4ef6-9c0a-11f291a691e5")
    public static Postcondition safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Postcondition.canInstantiate(obj))
        	return new Postcondition(obj);
        else
        	throw new IllegalArgumentException("Postcondition: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("15dc6578-5bb0-4936-ab13-64331b9aa1ea")
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
        Postcondition other = (Postcondition) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("fd1c635e-e92e-4182-b1c7-c7f460c9fdf5")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("e10a7505-5868-4fb2-ad9b-50db96d89621")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0062300d-e710-4682-a78b-8001cb340503")
    protected Postcondition(Constraint elt) {
        this.elt = elt;
    }

    @objid ("889980ab-e2c4-4755-bd2a-5c65867bd2d2")
    public static final class MdaTypes {
        @objid ("004f6500-47ab-4b07-b832-48a5620eabca")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0228e929-54ff-40c3-abbe-a1faa0b76356")
        private static Stereotype MDAASSOCDEP;

        @objid ("c4372425-5741-4e76-b448-ab9357eab67f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b1e73619-703f-43c5-8f09-123c0e23fdcb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c46-0000-000000000000");
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
