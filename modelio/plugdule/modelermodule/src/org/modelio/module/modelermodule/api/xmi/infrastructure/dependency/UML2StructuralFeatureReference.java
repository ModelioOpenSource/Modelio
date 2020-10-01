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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2StructuralFeatureReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("8dcc2244-21d0-4ae1-8601-ed7e4b3c648e")
public class UML2StructuralFeatureReference {
    @objid ("555afb53-520f-41c6-818a-be2096223d5b")
    public static final String STEREOTYPE_NAME = "UML2StructuralFeatureReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("cb12045b-d6b8-445d-a8cf-ee8ea1099f27")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2StructuralFeatureReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2StructuralFeatureReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("49f4e728-3fe2-455e-b427-fd8ea1d5dbb7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StructuralFeatureReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2StructuralFeatureReference >> then instantiate a {@link UML2StructuralFeatureReference} proxy.
     * 
     * @return a {@link UML2StructuralFeatureReference} proxy on the created {@link Dependency}.
     */
    @objid ("05f5f5c1-1fe9-4b76-97b9-686646fc738e")
    public static UML2StructuralFeatureReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StructuralFeatureReference.STEREOTYPE_NAME);
        return UML2StructuralFeatureReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2StructuralFeatureReference} proxy from a {@link Dependency} stereotyped << UML2StructuralFeatureReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2StructuralFeatureReference} proxy or <i>null</i>.
     */
    @objid ("afd5726f-40af-4d20-92c3-4645737aa5f2")
    public static UML2StructuralFeatureReference instantiate(Dependency obj) {
        return UML2StructuralFeatureReference.canInstantiate(obj) ? new UML2StructuralFeatureReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StructuralFeatureReference} proxy from a {@link Dependency} stereotyped << UML2StructuralFeatureReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2StructuralFeatureReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("eb7d84c9-2c1c-4d20-90ae-b0064a2237e8")
    public static UML2StructuralFeatureReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2StructuralFeatureReference.canInstantiate(obj))
        	return new UML2StructuralFeatureReference(obj);
        else
        	throw new IllegalArgumentException("UML2StructuralFeatureReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("88624c22-2758-4f36-aab4-7866db80b722")
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
        UML2StructuralFeatureReference other = (UML2StructuralFeatureReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("317d0269-5825-412a-a91c-209a71e9008f")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("fe02fc67-f196-4d8d-a9ee-ad8c37ab1f02")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1f4670ee-b3aa-4d68-a712-d82519ec56da")
    protected UML2StructuralFeatureReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("fb91f583-43cf-41d9-961d-4c8f8c7ed445")
    public static final class MdaTypes {
        @objid ("12b6c814-ab21-4f2f-871f-9cdb1c6da9dc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6a9bab7c-4f24-4d2b-8a8c-f2a455c28a3c")
        private static Stereotype MDAASSOCDEP;

        @objid ("3404ad40-9b0e-473f-a4ce-ae51af6a7950")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("630625ca-58f9-42bc-84ef-f58361572e9d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ed0bb1c3-de99-11de-905b-001302895b2b");
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
