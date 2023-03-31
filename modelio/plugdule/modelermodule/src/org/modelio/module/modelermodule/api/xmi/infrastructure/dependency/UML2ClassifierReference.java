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
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
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
    @objid ("771914e2-2b47-48e6-8ab6-de0949973491")
    public static final String STEREOTYPE_NAME = "UML2ClassifierReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("a993864f-8417-4b8c-8e51-7d0eecd85a96")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ClassifierReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ClassifierReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9e59c029-9782-46ff-b3ad-6d63430f4afb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ClassifierReference >> then instantiate a {@link UML2ClassifierReference} proxy.
     * 
     * @return a {@link UML2ClassifierReference} proxy on the created {@link Dependency}.
     */
    @objid ("647fc24b-8264-4456-85e6-6511fe0202dd")
    public static UML2ClassifierReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
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
    @objid ("0c5bd67f-88d4-4a0d-a196-46e6398e42ae")
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
    @objid ("730ddd5d-f1b9-434a-9158-e7f8d0bdb6ab")
    public static UML2ClassifierReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ClassifierReference.canInstantiate(obj))
        	return new UML2ClassifierReference(obj);
        else
        	throw new IllegalArgumentException("UML2ClassifierReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d6f702ad-dedb-44f0-be08-dafda9d1fd8f")
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
    @objid ("69d45abe-fba9-4299-a561-450a753faf76")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("3299c6fb-5ad9-4ebd-9f69-980bc918a2f9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("23bfdfc4-696f-43ab-8ffc-0b7aaeb46f55")
    protected  UML2ClassifierReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("218b6658-c8ad-417a-877f-66b90843c474")
    public static final class MdaTypes {
        @objid ("b309aae0-d8db-406e-9251-2e17a7c6a419")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1bb9029d-48be-4b37-94ad-0ab07c35e2ee")
        private static Stereotype MDAASSOCDEP;

        @objid ("5b023b3f-cddc-4ec1-b936-3104a3132110")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0a6453ec-839e-488f-9fee-d5bf90c1d87c")
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
