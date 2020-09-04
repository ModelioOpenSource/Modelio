/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Proxy class to handle a {@link Dependency} with << synonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("85cec5dc-8795-48b4-b7c1-726492848407")
public class Synonym {
    @objid ("3824b4ff-61db-4f62-958d-f19b831d3cf7")
    public static final String STEREOTYPE_NAME = "synonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("9ea25e6a-9e47-448b-adf1-dafcbded0976")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Synonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << synonym >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("2354dfcc-c8c5-4653-bbbd-4f070242fe33")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Synonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << synonym >> then instantiate a {@link Synonym} proxy.
     * 
     * @return a {@link Synonym} proxy on the created {@link Dependency}.
     */
    @objid ("0d259b11-50ee-4736-b711-b2268bd05e41")
    public static Synonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Synonym.STEREOTYPE_NAME);
        return Synonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Synonym} proxy from a {@link Dependency} stereotyped << synonym >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Synonym} proxy or <i>null</i>.
     */
    @objid ("431926bd-4c45-466b-9e28-b15b642a4b90")
    public static Synonym instantiate(Dependency obj) {
        return Synonym.canInstantiate(obj) ? new Synonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Synonym} proxy from a {@link Dependency} stereotyped << synonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Synonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b3b19393-f361-498e-8e5c-f72688f71af7")
    public static Synonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Synonym.canInstantiate(obj))
        	return new Synonym(obj);
        else
        	throw new IllegalArgumentException("Synonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2d714d91-f80d-4597-8e51-2069b2d7cdce")
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
        Synonym other = (Synonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("dc8231f3-4e96-4f5e-9518-ef569d8691c7")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("35baee1e-7799-44a5-9ebe-9c45d52935fe")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("eef1962c-4f04-4249-8ce7-7ba8a83d1ccb")
    protected Synonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b89f217e-7c42-4e09-b7aa-7f322e65a884")
    public static final class MdaTypes {
        @objid ("c8009a87-3ff2-427b-bd2f-497c55a5e53c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c5895a28-a61f-4ee0-92ae-2ae8edda20b7")
        private static Stereotype MDAASSOCDEP;

        @objid ("20bb3192-180c-4f4a-9937-091de69debde")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c40d04ef-121c-4be6-a0c8-4284ced60621")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-022e-0000-000000000000");
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
