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
 * Proxy class to handle a {@link Dependency} with << UML2MethodReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("747f7e0d-5c8c-413d-bdbb-641dfe37801b")
public class UML2MethodReference {
    @objid ("21d4053e-04a3-4454-b734-c2f5b1e461c8")
    public static final String STEREOTYPE_NAME = "UML2MethodReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("9651f9fd-6db2-4d3b-8599-43207464b4ab")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2MethodReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2MethodReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("13ce3775-4583-421c-ba38-1378bfaa5626")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2MethodReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2MethodReference >> then instantiate a {@link UML2MethodReference} proxy.
     * 
     * @return a {@link UML2MethodReference} proxy on the created {@link Dependency}.
     */
    @objid ("2f45eef7-587a-41ff-aca2-cdd57017c002")
    public static UML2MethodReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2MethodReference.STEREOTYPE_NAME);
        return UML2MethodReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2MethodReference} proxy from a {@link Dependency} stereotyped << UML2MethodReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2MethodReference} proxy or <i>null</i>.
     */
    @objid ("2441bbfa-bab6-4a65-bc9a-5388d43ce81d")
    public static UML2MethodReference instantiate(Dependency obj) {
        return UML2MethodReference.canInstantiate(obj) ? new UML2MethodReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2MethodReference} proxy from a {@link Dependency} stereotyped << UML2MethodReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2MethodReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f7c2e20e-2a8b-47e8-b2fd-be2401b4e6c9")
    public static UML2MethodReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2MethodReference.canInstantiate(obj))
        	return new UML2MethodReference(obj);
        else
        	throw new IllegalArgumentException("UML2MethodReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b1e58f55-6005-4ea9-89ad-cf0e28138a2e")
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
        UML2MethodReference other = (UML2MethodReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("1f313049-2278-426e-96f0-ef8b7428b739")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4900a909-cb6e-4ef6-a0f8-ddd352cd289a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6837e665-d62a-4796-bccb-229aeb2f1033")
    protected UML2MethodReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("de8df399-f6e6-443c-be21-019af7e50895")
    public static final class MdaTypes {
        @objid ("37d90d3a-68e9-4585-8f0c-597dd75a11b5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6e137c67-6004-4faa-a89b-23f3ce206ec8")
        private static Stereotype MDAASSOCDEP;

        @objid ("e2bf8bee-5fd9-4555-beee-719a8669ce3a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("24bba27c-162e-4f7b-9928-763812768140")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e445c33b-de99-11de-905b-001302895b2b");
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
