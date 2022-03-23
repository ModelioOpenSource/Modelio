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
 * Proxy class to handle a {@link Dependency} with << UML2EndData_Reference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("21ba6909-eafd-4ef8-9976-b21672b3d76a")
public class UML2EndDataReference {
    @objid ("cfe4319e-b234-4db2-b93d-77277d67ebbb")
    public static final String STEREOTYPE_NAME = "UML2EndData_Reference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("9722d47c-fd4f-470c-b455-3978d50d0a38")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndData_Reference >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("bf0cff7a-de60-454f-a3fc-f4eba524d1bf")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndData_Reference >> then instantiate a {@link UML2EndDataReference} proxy.
     * 
     * @return a {@link UML2EndDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("15e7f58c-a7c2-40ff-8112-6b30db538bf4")
    public static UML2EndDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndDataReference.STEREOTYPE_NAME);
        return UML2EndDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndDataReference} proxy from a {@link Dependency} stereotyped << UML2EndData_Reference >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndDataReference} proxy or <i>null</i>.
     */
    @objid ("578139bc-3c5d-4bc6-ba71-04ce90681921")
    public static UML2EndDataReference instantiate(Dependency obj) {
        return UML2EndDataReference.canInstantiate(obj) ? new UML2EndDataReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2EndDataReference} proxy from a {@link Dependency} stereotyped << UML2EndData_Reference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2EndDataReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8ee61ced-3d53-46a4-9ffc-2f1c8ae9d217")
    public static UML2EndDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndDataReference.canInstantiate(obj))
        	return new UML2EndDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3089b9ca-8831-4b22-9ef3-34e584fa161b")
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
        UML2EndDataReference other = (UML2EndDataReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("09d2aab4-db9f-4a34-be22-9d33f5d35bcb")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4f1d284a-a87b-4e95-92de-02c82c1c2bd9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("7122a4ec-5849-4eea-b62d-4c85e1a56646")
    protected  UML2EndDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("fbf92a3f-3305-4158-b8a7-11cae3ac1b3b")
    public static final class MdaTypes {
        @objid ("681690ba-c10c-4a46-90e9-c5912d04fdea")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b0fe07a0-bfc3-4253-a22a-777e2a4db5df")
        private static Stereotype MDAASSOCDEP;

        @objid ("b898f1b6-ee20-414a-9845-e22a7809195e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fd3c70ad-763a-4c4e-8749-6de7da796331")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5d167c0f-df53-11de-b2b1-001302895b2b");
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
