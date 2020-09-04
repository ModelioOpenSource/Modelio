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
 * Proxy class to handle a {@link Constraint} with << invariant >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e56d6561-c974-4569-834a-dbe37b352acd")
public class Invariant {
    @objid ("7f228a19-4956-423c-8461-ca3963308187")
    public static final String STEREOTYPE_NAME = "invariant";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("3ea573ed-be31-4937-84f4-14b7cc5c55e7")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Invariant proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << invariant >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d8f57f81-7195-4688-9829-102c50059025")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Invariant.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << invariant >> then instantiate a {@link Invariant} proxy.
     * 
     * @return a {@link Invariant} proxy on the created {@link Constraint}.
     */
    @objid ("03025284-5d96-4bad-a1bc-45d2c697365e")
    public static Invariant create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Invariant.STEREOTYPE_NAME);
        return Invariant.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Invariant} proxy from a {@link Constraint} stereotyped << invariant >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Invariant} proxy or <i>null</i>.
     */
    @objid ("cd44348b-dd84-4e93-a8d0-d5902434f7cf")
    public static Invariant instantiate(Constraint obj) {
        return Invariant.canInstantiate(obj) ? new Invariant(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Invariant} proxy from a {@link Constraint} stereotyped << invariant >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Invariant} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("861d36db-e630-4947-b648-ce80c520efd0")
    public static Invariant safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Invariant.canInstantiate(obj))
        	return new Invariant(obj);
        else
        	throw new IllegalArgumentException("Invariant: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("67078c75-1852-43ac-a9e7-d1834c29d004")
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
        Invariant other = (Invariant) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("f6370a42-e7ec-4f61-b35f-1b208cfc871a")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("6e655ffb-4222-4aab-bc15-ecd4561ce357")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b20aef6a-6658-4218-873d-2739afea0999")
    protected Invariant(Constraint elt) {
        this.elt = elt;
    }

    @objid ("2ffe4bc5-f27a-4f95-9bdc-ffea03ee62b0")
    public static final class MdaTypes {
        @objid ("6a0e97b3-b626-48a4-bbd0-8c95cd67d40f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1788b7a8-3886-48e2-a705-07676b0b14b5")
        private static Stereotype MDAASSOCDEP;

        @objid ("ae3788b6-2879-4c37-afda-0bb871dda36f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7b913ee8-32e5-4ea0-8434-896a1494bfd4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c44-0000-000000000000");
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
