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
 * Proxy class to handle a {@link Dependency} with << assigned >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1f6b5b83-851b-4d23-b980-2de82874fa5b")
public class Assigned {
    @objid ("f6760111-84ca-4822-b4de-692f548a48cf")
    public static final String STEREOTYPE_NAME = "assigned";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("6e485635-7bdf-4144-82bc-7ac6de1282b5")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Assigned proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << assigned >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0dbd9c8b-2d01-45c1-b429-c1e1af52547a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Assigned.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << assigned >> then instantiate a {@link Assigned} proxy.
     * 
     * @return a {@link Assigned} proxy on the created {@link Dependency}.
     */
    @objid ("07b3868b-08c6-4e5d-a1d5-c47aacea0679")
    public static Assigned create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Assigned.STEREOTYPE_NAME);
        return Assigned.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Assigned} proxy from a {@link Dependency} stereotyped << assigned >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Assigned} proxy or <i>null</i>.
     */
    @objid ("263d0185-4c46-406e-95bb-b9369fb66d11")
    public static Assigned instantiate(Dependency obj) {
        return Assigned.canInstantiate(obj) ? new Assigned(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Assigned} proxy from a {@link Dependency} stereotyped << assigned >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Assigned} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("14500f2a-c1a7-40fa-95ad-d62dbca9a6c7")
    public static Assigned safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Assigned.canInstantiate(obj))
        	return new Assigned(obj);
        else
        	throw new IllegalArgumentException("Assigned: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e08c6fe8-77b2-4d06-9299-728eea485607")
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
        Assigned other = (Assigned) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("9c24c0fe-0a87-43b7-9884-9ccfad44e757")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("9f8ce0d1-3940-4b07-9055-91d915b2b7d9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b87bad88-c98f-44f1-9c4b-24f99f9edab0")
    protected Assigned(Dependency elt) {
        this.elt = elt;
    }

    @objid ("52e42aa6-442a-4a00-a707-08c1820fc9cd")
    public static final class MdaTypes {
        @objid ("f31d8e32-6460-4032-82fa-8fd2e713a956")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f3987d61-8c21-4728-8179-2856dd7ec202")
        private static Stereotype MDAASSOCDEP;

        @objid ("7ba6c7f2-55e0-4123-9d37-f107ce45c048")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3ac670cb-dd3c-49c2-bba6-112abb874c65")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-025b-0000-000000000000");
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
