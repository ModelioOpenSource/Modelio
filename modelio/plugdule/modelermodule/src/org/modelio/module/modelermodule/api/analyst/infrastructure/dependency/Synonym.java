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
 * Proxy class to handle a {@link Dependency} with << synonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("85cec5dc-8795-48b4-b7c1-726492848407")
public class Synonym {
    @objid ("fe948970-52e2-4d73-adf7-5e1ca95c169b")
    public static final String STEREOTYPE_NAME = "synonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("b36a427a-fec0-44e0-870f-4c085154dba7")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Synonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << synonym >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("348ad3b9-6183-489a-b341-94e5e4c91d9a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Synonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << synonym >> then instantiate a {@link Synonym} proxy.
     * 
     * @return a {@link Synonym} proxy on the created {@link Dependency}.
     */
    @objid ("376d14e7-8d29-4394-8ecb-16a2da9ecb8f")
    public static Synonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Synonym.STEREOTYPE_NAME);
        return Synonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Synonym} proxy from a {@link Dependency} stereotyped << synonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Synonym} proxy or <i>null</i>.
     */
    @objid ("2ea04b46-6adb-4907-ba73-56c7856458bd")
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
    @objid ("2afed0bc-77c2-48f0-a0e0-ec1c6fd9fd30")
    public static Synonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Synonym.canInstantiate(obj))
        	return new Synonym(obj);
        else
        	throw new IllegalArgumentException("Synonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("02c0430a-25ee-449f-a12d-e7170c275a7b")
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
    @objid ("9c99279c-a1b6-4bc8-b4f3-4ceb7e6f766c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8c41840a-e82a-49ce-b0d5-ee5595b67ccb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ef8f5ae6-79a1-4491-b0c2-2bc07d5ca13d")
    protected Synonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b89f217e-7c42-4e09-b7aa-7f322e65a884")
    public static final class MdaTypes {
        @objid ("332398fb-8760-42d3-b35e-6b055abcf07e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e714357f-e4ac-4a1d-b7d0-5f907e39039e")
        private static Stereotype MDAASSOCDEP;

        @objid ("adc847ca-9cc8-4458-81d5-e27aed8e050d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4706a35a-ce58-4251-95c0-a22685ccb38e")
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
