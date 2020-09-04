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
    @objid ("06db12bf-a352-446f-ac56-76b72bb61678")
    public static final String STEREOTYPE_NAME = "refine";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("15808d13-b073-455e-96f6-a78b606a4a97")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Refine proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << refine >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4771b844-5125-49c4-a4c3-884aad35f323")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Refine.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << refine >> then instantiate a {@link Refine} proxy.
     * 
     * @return a {@link Refine} proxy on the created {@link Dependency}.
     */
    @objid ("e98f27c1-3d37-4010-89f4-4ca5c6273184")
    public static Refine create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Refine.STEREOTYPE_NAME);
        return Refine.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Refine} proxy from a {@link Dependency} stereotyped << refine >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Refine} proxy or <i>null</i>.
     */
    @objid ("3a932dc3-de77-470a-9073-d7432550abc9")
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
    @objid ("9c66631e-395f-4ae8-92f1-1831219a0c64")
    public static Refine safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Refine.canInstantiate(obj))
        	return new Refine(obj);
        else
        	throw new IllegalArgumentException("Refine: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a459145f-38d9-4da7-8b12-66f265e66bd4")
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
    @objid ("961a6ed8-4b3e-48f0-a791-2a8a6410930d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("1299caa9-e8b4-4f62-b1b3-85288df7f584")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0c080904-a818-4f5d-bf69-2faf46b87582")
    protected Refine(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6828706b-4056-48b1-9924-ed9cdf5d7d8d")
    public static final class MdaTypes {
        @objid ("15be993e-62f9-440d-a437-57aa66d07a93")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6c7cab0c-5b23-4d92-b249-da46b80629d6")
        private static Stereotype MDAASSOCDEP;

        @objid ("5efd8123-c45e-460e-8202-b1ac80dcab0a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("da4d51bf-7086-40f0-90d3-ac1f4041077c")
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
