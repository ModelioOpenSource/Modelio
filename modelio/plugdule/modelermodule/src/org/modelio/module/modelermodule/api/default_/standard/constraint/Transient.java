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
 * Proxy class to handle a {@link Constraint} with << transient >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("be1ed99b-3e3c-4bf1-87aa-3fdc95e547fe")
public class Transient {
    @objid ("c043c3fd-6ca0-4ca2-9309-3d49bb1c9cb7")
    public static final String STEREOTYPE_NAME = "transient";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("29e9e78b-6842-4b8c-b305-32c53779516d")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Transient proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << transient >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f7c5b7d7-2c84-4cd6-a11b-c489773f58c6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Transient.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << transient >> then instantiate a {@link Transient} proxy.
     * 
     * @return a {@link Transient} proxy on the created {@link Constraint}.
     */
    @objid ("a8528efc-6840-4111-8647-7dc1bed6488d")
    public static Transient create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Transient.STEREOTYPE_NAME);
        return Transient.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Transient} proxy from a {@link Constraint} stereotyped << transient >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Transient} proxy or <i>null</i>.
     */
    @objid ("55aef7fb-3f1e-4657-b7b1-97807fb2aaed")
    public static Transient instantiate(Constraint obj) {
        return Transient.canInstantiate(obj) ? new Transient(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Transient} proxy from a {@link Constraint} stereotyped << transient >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Transient} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d1708e59-af65-4e8f-9488-7fa1ceccbb60")
    public static Transient safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Transient.canInstantiate(obj))
        	return new Transient(obj);
        else
        	throw new IllegalArgumentException("Transient: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("616c9e10-21b1-4b9c-8105-3598154245ec")
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
        Transient other = (Transient) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("669684fc-3ae9-48f6-a079-18e6ddf680f4")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("56767906-95f4-432e-8926-560e7d0ce52f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0d2b09fa-50cd-4783-b007-01f6bfedf0e3")
    protected Transient(Constraint elt) {
        this.elt = elt;
    }

    @objid ("dd8d10f4-9b21-4aa7-810c-526be96a91dd")
    public static final class MdaTypes {
        @objid ("d7a47b21-4474-49bf-ae28-cdf060480451")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bc75508d-fec0-4072-a2e0-77ba7a89a40b")
        private static Stereotype MDAASSOCDEP;

        @objid ("45fd0aa1-9b28-4589-9945-b50e6cf762ae")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a1b43a75-6356-4300-92d6-483d1cd92326")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01fe-0000-000000000000");
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
