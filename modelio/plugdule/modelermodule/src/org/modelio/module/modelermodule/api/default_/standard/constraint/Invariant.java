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
 * Proxy class to handle a {@link Constraint} with << invariant >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e56d6561-c974-4569-834a-dbe37b352acd")
public class Invariant {
    @objid ("0522a254-f6fc-42aa-8d37-8c0c32c726ad")
    public static final String STEREOTYPE_NAME = "invariant";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("b8a17dee-f89d-48ca-b164-e79a03d71730")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Invariant proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << invariant >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8a3c7837-8024-4523-8afd-883987ce4d6b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Invariant.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << invariant >> then instantiate a {@link Invariant} proxy.
     * 
     * @return a {@link Invariant} proxy on the created {@link Constraint}.
     */
    @objid ("7ead4327-a139-40bd-938c-588e472aa7ca")
    public static Invariant create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Invariant.STEREOTYPE_NAME);
        return Invariant.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Invariant} proxy from a {@link Constraint} stereotyped << invariant >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Invariant} proxy or <i>null</i>.
     */
    @objid ("e49c6a8c-ea6b-4020-909f-0a2d8817e68e")
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
    @objid ("401a083d-4488-44bd-a653-004362af1822")
    public static Invariant safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Invariant.canInstantiate(obj))
        	return new Invariant(obj);
        else
        	throw new IllegalArgumentException("Invariant: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("72b148f3-5981-405f-8937-a80153a09062")
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
    @objid ("be64939c-ec9e-4a2a-b220-efd432cd3cab")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("73a98e5d-71cc-43cb-a301-499a7684e0f9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f7c5599d-7336-4f99-82cb-b103841239b6")
    protected Invariant(Constraint elt) {
        this.elt = elt;
    }

    @objid ("2ffe4bc5-f27a-4f95-9bdc-ffea03ee62b0")
    public static final class MdaTypes {
        @objid ("2935926e-a460-41ae-8e7e-6cc4d51623e6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("90e7cd5b-8e42-4081-b1b3-ca8e1f55fde1")
        private static Stereotype MDAASSOCDEP;

        @objid ("46614feb-0d4d-41d1-a64e-ac33b6098a32")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b35fbe46-7eaf-4cad-b5d1-aec63110f7f2")
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
