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
 * Proxy class to handle a {@link Dependency} with << UML2MethodReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("747f7e0d-5c8c-413d-bdbb-641dfe37801b")
public class UML2MethodReference {
    @objid ("2b4d8e1c-1c58-40dc-91f3-f130fac90af7")
    public static final String STEREOTYPE_NAME = "UML2MethodReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("d68735cc-872c-4ac4-b23f-497913f5d979")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2MethodReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2MethodReference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5eebccfb-45e0-4ccb-bb71-4bd9f51ad297")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2MethodReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2MethodReference >> then instantiate a {@link UML2MethodReference} proxy.
     * 
     * @return a {@link UML2MethodReference} proxy on the created {@link Dependency}.
     */
    @objid ("72fc8db6-8f6c-4826-947f-376b95c13f13")
    public static UML2MethodReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2MethodReference.STEREOTYPE_NAME);
        return UML2MethodReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2MethodReference} proxy from a {@link Dependency} stereotyped << UML2MethodReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2MethodReference} proxy or <i>null</i>.
     */
    @objid ("45436a15-ae44-47c7-afd2-f50fae0e3f1c")
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
    @objid ("dbbac218-f79e-453d-845d-047ec60b66f8")
    public static UML2MethodReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2MethodReference.canInstantiate(obj))
        	return new UML2MethodReference(obj);
        else
        	throw new IllegalArgumentException("UML2MethodReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("509d6705-636a-4b5b-81f3-c1d7e68c3d47")
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
    @objid ("08c25928-6804-4d9e-9612-8b0559d4f4f9")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("09524d3a-4ff8-43e7-be00-7aee1a196af5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("b999dca9-a12c-416d-9130-3ba97986ec21")
    protected  UML2MethodReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("de8df399-f6e6-443c-be21-019af7e50895")
    public static final class MdaTypes {
        @objid ("20ba5334-5df7-4c1a-98a6-e7707fc9d9cc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bd87cc71-8d24-476e-a132-4449a8453dfb")
        private static Stereotype MDAASSOCDEP;

        @objid ("90505b7d-ac25-430b-8808-51a0a0c485ca")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7ab8f358-2ac6-4318-a19f-7d158a9d019d")
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
