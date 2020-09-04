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
 * Proxy class to handle a {@link Dependency} with << UML2ClassifierReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ef0b69f8-a0bc-4e50-aa2f-4bd55a6ed606")
public class UML2ClassifierReference {
    @objid ("91597c4f-2fe8-4a3b-af5d-476952af6e2d")
    public static final String STEREOTYPE_NAME = "UML2ClassifierReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("c46eb8e4-c40d-4f03-89a8-f64e2647f842")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ClassifierReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ClassifierReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b99729e1-c948-490c-b4f2-b3fb754fde5b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ClassifierReference >> then instantiate a {@link UML2ClassifierReference} proxy.
     * 
     * @return a {@link UML2ClassifierReference} proxy on the created {@link Dependency}.
     */
    @objid ("7e95e201-4329-4484-b9a5-c6cd30fd89a0")
    public static UML2ClassifierReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierReference.STEREOTYPE_NAME);
        return UML2ClassifierReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierReference} proxy from a {@link Dependency} stereotyped << UML2ClassifierReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ClassifierReference} proxy or <i>null</i>.
     */
    @objid ("25260a1e-9a63-47f7-8295-7d346701817a")
    public static UML2ClassifierReference instantiate(Dependency obj) {
        return UML2ClassifierReference.canInstantiate(obj) ? new UML2ClassifierReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierReference} proxy from a {@link Dependency} stereotyped << UML2ClassifierReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ClassifierReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8e3dc0ab-5db1-4549-8005-1a59decc3be7")
    public static UML2ClassifierReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ClassifierReference.canInstantiate(obj))
        	return new UML2ClassifierReference(obj);
        else
        	throw new IllegalArgumentException("UML2ClassifierReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("72d02ce5-c211-4bb5-b4af-c3fd23c5f286")
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
        UML2ClassifierReference other = (UML2ClassifierReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("38f297c9-a1e0-44c1-9b3b-81db9051bd3e")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("0a7d7e1e-7d30-450d-b976-a3c72bef7586")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("96d7f5fb-9510-4568-b278-9bb8ae7ca50d")
    protected UML2ClassifierReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("218b6658-c8ad-417a-877f-66b90843c474")
    public static final class MdaTypes {
        @objid ("b35fca8a-d40b-491e-af01-ae37bdb28c93")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("200a5adc-d7b8-4708-bdae-a7339d3a3651")
        private static Stereotype MDAASSOCDEP;

        @objid ("076b4b28-1bbe-4e7a-85e9-8ebadd79cd42")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e8a6396d-898f-414d-a632-17b0154531a1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ed95fa9b-de99-11de-905b-001302895b2b");
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
