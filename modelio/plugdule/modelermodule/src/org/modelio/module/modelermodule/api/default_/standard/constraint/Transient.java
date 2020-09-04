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
 * Proxy class to handle a {@link Constraint} with << transient >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("be1ed99b-3e3c-4bf1-87aa-3fdc95e547fe")
public class Transient {
    @objid ("0ffc851c-b9fb-45ae-b95e-edcbb25670a4")
    public static final String STEREOTYPE_NAME = "transient";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("26e300d0-5c20-4d2d-baae-0ffad2d7d7ce")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Transient proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << transient >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9608bfde-2e32-4db2-b299-8d9f011c0223")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Transient.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << transient >> then instantiate a {@link Transient} proxy.
     * 
     * @return a {@link Transient} proxy on the created {@link Constraint}.
     */
    @objid ("537e2855-4b2d-4640-a7bf-fc92d466caca")
    public static Transient create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Transient.STEREOTYPE_NAME);
        return Transient.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Transient} proxy from a {@link Constraint} stereotyped << transient >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Transient} proxy or <i>null</i>.
     */
    @objid ("007e810d-301c-4fe6-b12e-62fd062e692d")
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
    @objid ("6de41c94-60b6-4254-b65f-c91530c975fb")
    public static Transient safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Transient.canInstantiate(obj))
        	return new Transient(obj);
        else
        	throw new IllegalArgumentException("Transient: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3f7eefe1-2289-4619-b12e-18f7102862b7")
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
    @objid ("e1748e67-55c3-44ad-a616-9f98e5434ee2")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("a334bab2-40d4-4494-ab8c-d76a11422272")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6b083a8f-7a27-4d9a-8a02-c4e37e4c904e")
    protected Transient(Constraint elt) {
        this.elt = elt;
    }

    @objid ("dd8d10f4-9b21-4aa7-810c-526be96a91dd")
    public static final class MdaTypes {
        @objid ("812bb153-291e-46e2-9da6-d8a2376a6de9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("400cd504-d95e-4b39-b048-2bbd1afac21a")
        private static Stereotype MDAASSOCDEP;

        @objid ("5b11c5e0-b3fb-421c-b460-e28eae79ceff")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("58eda9d6-98f4-48ed-98ce-06db3ba2bfd4")
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
