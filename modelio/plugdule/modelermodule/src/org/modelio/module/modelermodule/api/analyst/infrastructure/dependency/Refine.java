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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
 * Proxy class to handle a {@link Dependency} with << refine >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("065ef2c0-b7f4-4881-9acb-45b54c44a00d")
public class Refine {
    @objid ("44e216da-8a0d-486d-8e85-746c77c0d60e")
    public static final String STEREOTYPE_NAME = "refine";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("8fd61005-ec0c-4b93-8a08-d9dc763d3fe2")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Refine proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << refine >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8848029c-ef0a-4bfe-b815-91e4fd0a8ab4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Refine.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << refine >> then instantiate a {@link Refine} proxy.
     * 
     * @return a {@link Refine} proxy on the created {@link Dependency}.
     */
    @objid ("c1285a39-d779-4365-8fa4-91978cc1be6e")
    public static Refine create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Refine.STEREOTYPE_NAME);
        return Refine.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Refine} proxy from a {@link Dependency} stereotyped << refine >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Refine} proxy or <i>null</i>.
     */
    @objid ("ae280b1c-40bf-4d96-ba25-3e0acee0440a")
    public static Refine instantiate(Dependency obj) {
        return Refine.canInstantiate(obj) ? new Refine(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Refine} proxy from a {@link Dependency} stereotyped << refine >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Refine} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a705db40-6b2a-40d0-a587-cadf50bb9ae2")
    public static Refine safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Refine.canInstantiate(obj))
        	return new Refine(obj);
        else
        	throw new IllegalArgumentException("Refine: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9a4b73d2-a789-4f7a-8361-6ccc86cd0cc2")
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
        Refine other = (Refine) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("95d617e2-0e33-4800-afbd-d303c6444173")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("1e00b642-d81c-4ac8-80fa-505a4930c091")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a69b8911-30e5-476a-b8b8-98fdb753aa61")
    protected Refine(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6828706b-4056-48b1-9924-ed9cdf5d7d8d")
    public static final class MdaTypes {
        @objid ("33fc5bbc-b888-4f2c-9d7d-7803550a222b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a3fe99d4-0ad6-474c-b446-7454a416b28a")
        private static Stereotype MDAASSOCDEP;

        @objid ("649f7317-8f55-4ac3-bf36-ffdcd5b43ab2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dd5917b6-fe38-438c-8c45-494acd33e955")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-021f-0000-000000000000");
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
