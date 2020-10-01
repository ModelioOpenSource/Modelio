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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << manifestation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("21713da7-5de8-4464-8371-298380f8461d")
public class Manifestation {
    @objid ("b364ac9a-a137-4c47-a8a5-eb3b4058df02")
    public static final String STEREOTYPE_NAME = "manifestation";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("8e55374c-1d94-4f78-ac39-10f4b6c58484")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Manifestation proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << manifestation >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("648b1b67-d0d2-44ba-b4c7-b79edf518275")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Manifestation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << manifestation >> then instantiate a {@link Manifestation} proxy.
     * 
     * @return a {@link Manifestation} proxy on the created {@link Dependency}.
     */
    @objid ("86b05099-a96d-4a5c-98ff-4aab429f2658")
    public static Manifestation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Manifestation.STEREOTYPE_NAME);
        return Manifestation.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Manifestation} proxy from a {@link Dependency} stereotyped << manifestation >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Manifestation} proxy or <i>null</i>.
     */
    @objid ("30d719ea-dfca-4eb4-89dd-1545efaebce8")
    public static Manifestation instantiate(Dependency obj) {
        return Manifestation.canInstantiate(obj) ? new Manifestation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Manifestation} proxy from a {@link Dependency} stereotyped << manifestation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Manifestation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("db66ca45-98b3-4adf-bf01-984633107c69")
    public static Manifestation safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Manifestation.canInstantiate(obj))
        	return new Manifestation(obj);
        else
        	throw new IllegalArgumentException("Manifestation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("84baa7f7-b615-4f47-808e-8b9ade560470")
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
        Manifestation other = (Manifestation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("e78380da-6adb-4570-9723-2db9e7cf2f0c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("5c9ee9e7-6fdb-407f-b76b-d65ede73ada8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2964edb7-d690-4de6-a462-dfdad6be41fc")
    protected Manifestation(Dependency elt) {
        this.elt = elt;
    }

    @objid ("a2578fb0-0629-4fb7-8870-be21ae2eb03a")
    public static final class MdaTypes {
        @objid ("d2db8e83-10ec-4180-abe7-e4a515e672bc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ed2fa3e8-8a12-4eb0-ab29-667b9eec11d7")
        private static Stereotype MDAASSOCDEP;

        @objid ("248cdeeb-9a74-4fe0-b665-6e895018703e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("09669bce-c916-4a11-bd0d-451b503ece5f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "d5bccf8e-79b3-48df-8c79-09200aa52d19");
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
