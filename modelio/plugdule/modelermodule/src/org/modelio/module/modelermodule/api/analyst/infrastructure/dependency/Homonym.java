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
 * Proxy class to handle a {@link Dependency} with << homonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ee86437c-78c7-4033-9a77-f40e04f46719")
public class Homonym {
    @objid ("50fa43db-c3b7-4e79-b453-7932e36b5817")
    public static final String STEREOTYPE_NAME = "homonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("fb88b2f7-a4df-435b-b7a8-48d85063adef")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Homonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << homonym >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("47d8764d-b748-4724-a7e0-eb00c9a1217b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Homonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << homonym >> then instantiate a {@link Homonym} proxy.
     * 
     * @return a {@link Homonym} proxy on the created {@link Dependency}.
     */
    @objid ("b6bbdfb8-da3d-4d29-8cd2-eadf55e6dcdc")
    public static Homonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Homonym.STEREOTYPE_NAME);
        return Homonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Homonym} proxy from a {@link Dependency} stereotyped << homonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Homonym} proxy or <i>null</i>.
     */
    @objid ("8df0e794-da81-4942-a641-4e65a79c0a70")
    public static Homonym instantiate(Dependency obj) {
        return Homonym.canInstantiate(obj) ? new Homonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Homonym} proxy from a {@link Dependency} stereotyped << homonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Homonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dd4a1d5d-92d8-4c21-bcd1-778ab135095c")
    public static Homonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Homonym.canInstantiate(obj))
        	return new Homonym(obj);
        else
        	throw new IllegalArgumentException("Homonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a3cd95f6-4f6c-49cf-9ebf-767a4bc76394")
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
        Homonym other = (Homonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("54e21e7e-2c5b-418b-b3a8-0f90c7924e37")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4c0c7308-2c44-49b5-ba42-5890264f57ab")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7da161d4-ab0c-4539-8381-8c97a9ec3b28")
    protected Homonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("8225eb66-4057-4d15-8d1d-414b4382bf6b")
    public static final class MdaTypes {
        @objid ("aff3efde-c5cd-49c4-9982-db1fe880aa4a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b1d5f9ed-e0fd-4380-bb96-7063ff0ebc2e")
        private static Stereotype MDAASSOCDEP;

        @objid ("7a278c60-41a8-4403-94bb-d392cc787427")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ba1abc0d-a961-4ee6-b2a4-f2d72df5ba08")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0238-0000-000000000000");
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
