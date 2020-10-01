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
 * Proxy class to handle a {@link Dependency} with << UML2ClassifierReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ef0b69f8-a0bc-4e50-aa2f-4bd55a6ed606")
public class UML2ClassifierReference {
    @objid ("4a320caa-190d-42ad-8202-4b719347c2f4")
    public static final String STEREOTYPE_NAME = "UML2ClassifierReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("c17b344d-2dca-4b83-a7f7-b1e520c65535")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ClassifierReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ClassifierReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8e8e62da-ea12-47e4-b018-62a00df7ed91")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ClassifierReference >> then instantiate a {@link UML2ClassifierReference} proxy.
     * 
     * @return a {@link UML2ClassifierReference} proxy on the created {@link Dependency}.
     */
    @objid ("bb7de8b5-2e56-4d4b-8fe0-47119b51dc00")
    public static UML2ClassifierReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierReference.STEREOTYPE_NAME);
        return UML2ClassifierReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierReference} proxy from a {@link Dependency} stereotyped << UML2ClassifierReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ClassifierReference} proxy or <i>null</i>.
     */
    @objid ("96c497a1-8bd9-4184-9575-1b14ddd1dbf0")
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
    @objid ("67473590-b639-4977-bdca-bd14acc01ee9")
    public static UML2ClassifierReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ClassifierReference.canInstantiate(obj))
        	return new UML2ClassifierReference(obj);
        else
        	throw new IllegalArgumentException("UML2ClassifierReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bf96a796-5d9e-4a87-a35c-5312348b6967")
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
    @objid ("80a5d37a-6ee5-4638-aa71-016667fcdb5b")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("0a2a3b35-70b3-4617-8ef9-c7ad16e061c7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bdd3f859-c128-4241-b805-d4dad407aa2f")
    protected UML2ClassifierReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("218b6658-c8ad-417a-877f-66b90843c474")
    public static final class MdaTypes {
        @objid ("05a6e549-4100-4bd9-9135-78c4faa140e3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bf7d883b-e930-4a5e-817c-2e35bf2c304e")
        private static Stereotype MDAASSOCDEP;

        @objid ("34d99e72-9ac3-4cb6-8f81-a798ec42fe65")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7db6310c-53c5-411c-9ffa-365695c12a8c")
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
