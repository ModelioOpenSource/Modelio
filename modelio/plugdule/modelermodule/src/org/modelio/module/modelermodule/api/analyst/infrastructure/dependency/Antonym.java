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
 * Proxy class to handle a {@link Dependency} with << antonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e3e8030d-e474-473c-b754-e7e5465e68e7")
public class Antonym {
    @objid ("9659a130-2fcf-4c08-a8d6-f79d1e48b747")
    public static final String STEREOTYPE_NAME = "antonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("fe41e2eb-86e0-4374-986e-47a01e8b19c1")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Antonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << antonym >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("24b11f21-db2f-4ab3-8534-fecbb819b3a4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Antonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << antonym >> then instantiate a {@link Antonym} proxy.
     * 
     * @return a {@link Antonym} proxy on the created {@link Dependency}.
     */
    @objid ("1526dc96-a499-4dd2-a07d-fea42b6ddcdb")
    public static Antonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Antonym.STEREOTYPE_NAME);
        return Antonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Antonym} proxy from a {@link Dependency} stereotyped << antonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Antonym} proxy or <i>null</i>.
     */
    @objid ("df6deaee-7e98-4d5e-acdd-10a7b21d5871")
    public static Antonym instantiate(Dependency obj) {
        return Antonym.canInstantiate(obj) ? new Antonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Antonym} proxy from a {@link Dependency} stereotyped << antonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Antonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("345c540c-9710-42b3-978a-edac8145d321")
    public static Antonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Antonym.canInstantiate(obj))
        	return new Antonym(obj);
        else
        	throw new IllegalArgumentException("Antonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1dd6ab65-a32c-41e7-a995-49d72dd8657e")
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
        Antonym other = (Antonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("602aa6a2-7474-4b0e-bb70-4fd4789fe94e")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("e241841e-d7f6-4963-b6fe-ad04f03a77ce")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("18c7d34e-8203-426c-adbb-e97fb856d8d1")
    protected Antonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("e8185a79-1f85-46e2-9ae1-2d7bcf9b15b1")
    public static final class MdaTypes {
        @objid ("42f1534d-8d33-443f-a847-521fdc7a81cc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("db0d7f47-6901-4f56-acae-09e5adba7e73")
        private static Stereotype MDAASSOCDEP;

        @objid ("fae1d9f5-122d-43f1-8536-e3fcab9174d5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f950c60f-8b9b-407b-a4a1-760b2609bcac")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0233-0000-000000000000");
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
