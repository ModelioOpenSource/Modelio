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
 * Proxy class to handle a {@link Dependency} with << UML2AssociationReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("77a332fe-d12f-47f1-abad-e85c4227ad5a")
public class UML2AssociationReference {
    @objid ("665d58bc-6d3e-4c66-8343-e442c7649ae5")
    public static final String STEREOTYPE_NAME = "UML2AssociationReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("2e0df085-f706-4d78-9c87-767700447075")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2AssociationReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2AssociationReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("780e515d-1fcd-409f-a3f4-c92891c19d43")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AssociationReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2AssociationReference >> then instantiate a {@link UML2AssociationReference} proxy.
     * 
     * @return a {@link UML2AssociationReference} proxy on the created {@link Dependency}.
     */
    @objid ("c0640827-4d7d-40cf-a9a9-74523158b2d5")
    public static UML2AssociationReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AssociationReference.STEREOTYPE_NAME);
        return UML2AssociationReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2AssociationReference} proxy from a {@link Dependency} stereotyped << UML2AssociationReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2AssociationReference} proxy or <i>null</i>.
     */
    @objid ("60dbb5a2-a773-4e7c-bc68-5348b400a25c")
    public static UML2AssociationReference instantiate(Dependency obj) {
        return UML2AssociationReference.canInstantiate(obj) ? new UML2AssociationReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AssociationReference} proxy from a {@link Dependency} stereotyped << UML2AssociationReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2AssociationReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b86fd365-a133-431a-94d6-7c0e85ee2d11")
    public static UML2AssociationReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2AssociationReference.canInstantiate(obj))
        	return new UML2AssociationReference(obj);
        else
        	throw new IllegalArgumentException("UML2AssociationReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("75cb1471-988e-42b4-a0d8-93ab030fb507")
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
        UML2AssociationReference other = (UML2AssociationReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("bb516c2f-d312-4e4f-a1f7-400eb89e8020")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("89af1bb6-a9e5-4550-b7be-a0a58dc00bdd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("209492cd-faf0-4f57-9b55-af41e007e2a1")
    protected UML2AssociationReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b489f045-60a4-467e-9539-1f3d00766293")
    public static final class MdaTypes {
        @objid ("3ea7a66e-34cb-4edb-a1d6-0f07711535bd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ea89fdbf-51b1-4f50-8d6a-00db04aaaa47")
        private static Stereotype MDAASSOCDEP;

        @objid ("f90a9e73-6f9b-4ecf-a3b3-812cd2324fcd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("657e687b-eccf-4b21-894f-f325eac6ebae")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ee97796b-de99-11de-905b-001302895b2b");
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
