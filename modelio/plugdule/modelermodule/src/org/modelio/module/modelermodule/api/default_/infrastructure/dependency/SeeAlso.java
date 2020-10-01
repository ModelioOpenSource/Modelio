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
 * Proxy class to handle a {@link Dependency} with << see_also >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dab2a566-5d9e-4d14-a078-3daa13429dd7")
public class SeeAlso {
    @objid ("69226518-ea3f-4f86-afcd-210acc499a22")
    public static final String STEREOTYPE_NAME = "see_also";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5aa55c4a-ca5d-48b6-9fac-6dbc46b27fae")
    protected final Dependency elt;

    /**
     * Tells whether a {@link SeeAlso proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << see_also >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("dffbb015-c90e-4b31-b3a2-ef99b628a5e5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, SeeAlso.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << see_also >> then instantiate a {@link SeeAlso} proxy.
     * 
     * @return a {@link SeeAlso} proxy on the created {@link Dependency}.
     */
    @objid ("03374836-ebb0-470c-b8d1-c9d0a30274cc")
    public static SeeAlso create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, SeeAlso.STEREOTYPE_NAME);
        return SeeAlso.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link SeeAlso} proxy from a {@link Dependency} stereotyped << see_also >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link SeeAlso} proxy or <i>null</i>.
     */
    @objid ("6b53434e-171e-4bfe-80ee-7a9d9504a6fc")
    public static SeeAlso instantiate(Dependency obj) {
        return SeeAlso.canInstantiate(obj) ? new SeeAlso(obj) : null;
    }

    /**
     * Tries to instantiate a {@link SeeAlso} proxy from a {@link Dependency} stereotyped << see_also >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link SeeAlso} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e25ed454-fd8b-45cc-b17a-e66ce91928b8")
    public static SeeAlso safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (SeeAlso.canInstantiate(obj))
        	return new SeeAlso(obj);
        else
        	throw new IllegalArgumentException("SeeAlso: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a7a019be-de2a-4236-9c87-4bcb7e42210e")
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
        SeeAlso other = (SeeAlso) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("12894c53-4840-4552-a103-1cfb012accb3")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("ce398737-deeb-448a-b6fb-d0873a9c4fcc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4b198228-07a6-4ff0-a263-441df6d3717b")
    protected SeeAlso(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5b47eb06-542a-4878-a3f2-5e7de42d4e66")
    public static final class MdaTypes {
        @objid ("903c044d-c190-4fc9-9326-39578bcb914d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5fbb8ea4-9efd-415d-8201-f0cf840d2846")
        private static Stereotype MDAASSOCDEP;

        @objid ("b588d15d-4da8-4161-9851-9ae9648d8b16")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9f3e5e3f-9689-437d-8267-3e7f8cc9becf")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0ac7e50f-50c6-4eb6-9107-3d9df92a2b75");
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
