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
 * Proxy class to handle a {@link Dependency} with << UML2EndData_Reference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("21ba6909-eafd-4ef8-9976-b21672b3d76a")
public class UML2EndDataReference {
    @objid ("d6121fa8-2c2d-4670-b3f3-1ef85a59e71f")
    public static final String STEREOTYPE_NAME = "UML2EndData_Reference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("b322ae58-7d7c-4bfe-8a12-e9403ff5c958")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndData_Reference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ab635685-6448-43ec-a80e-ecc8082fca29")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndData_Reference >> then instantiate a {@link UML2EndDataReference} proxy.
     * 
     * @return a {@link UML2EndDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("91bce355-7d06-4ae4-bb51-74ef7638654c")
    public static UML2EndDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndDataReference.STEREOTYPE_NAME);
        return UML2EndDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndDataReference} proxy from a {@link Dependency} stereotyped << UML2EndData_Reference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndDataReference} proxy or <i>null</i>.
     */
    @objid ("e6d7578e-cb53-4f79-a4fe-876882c85c19")
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
    @objid ("821750be-eb5a-4826-9c69-2645cef5fecf")
    public static UML2EndDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndDataReference.canInstantiate(obj))
        	return new UML2EndDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("64915c94-9e15-40ce-8771-372b0f267550")
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
    @objid ("b06e5298-905a-4777-8699-945b9aeafcf3")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8787bb84-4a34-47fc-af28-db08bf9bc09c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d3111f70-4dd0-4ba1-9110-0548fd7afdd5")
    protected UML2EndDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("fbf92a3f-3305-4158-b8a7-11cae3ac1b3b")
    public static final class MdaTypes {
        @objid ("995ec951-05c4-45ff-8a84-406b33a5c0a0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f8b2599b-8023-4c83-ad2e-faa27b91918d")
        private static Stereotype MDAASSOCDEP;

        @objid ("079b3c96-a30d-4f35-91b1-3a7c851d98bb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b6518b82-ae65-4e95-bde2-a37070cfca3e")
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
