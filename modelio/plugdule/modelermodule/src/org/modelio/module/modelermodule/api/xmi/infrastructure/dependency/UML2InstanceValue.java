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
 * Proxy class to handle a {@link Dependency} with << UML2InstanceValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("57682dea-36fc-489f-a20b-7834ce24109f")
public class UML2InstanceValue {
    @objid ("bd9e7727-955a-4b96-8576-c0833cf28076")
    public static final String STEREOTYPE_NAME = "UML2InstanceValue";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("ffe6ed5c-32ea-4c20-bce8-90a3cfb9eb93")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2InstanceValue proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2InstanceValue >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b13e8fd4-025f-4c08-8ea3-108a7244fdd8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InstanceValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2InstanceValue >> then instantiate a {@link UML2InstanceValue} proxy.
     * 
     * @return a {@link UML2InstanceValue} proxy on the created {@link Dependency}.
     */
    @objid ("83867f77-31c1-41e5-a694-b60ff2a68b83")
    public static UML2InstanceValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InstanceValue.STEREOTYPE_NAME);
        return UML2InstanceValue.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2InstanceValue} proxy from a {@link Dependency} stereotyped << UML2InstanceValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2InstanceValue} proxy or <i>null</i>.
     */
    @objid ("f42da06e-1461-4fd7-a249-800607bb6250")
    public static UML2InstanceValue instantiate(Dependency obj) {
        return UML2InstanceValue.canInstantiate(obj) ? new UML2InstanceValue(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InstanceValue} proxy from a {@link Dependency} stereotyped << UML2InstanceValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2InstanceValue} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("33202de9-4503-4c8d-990d-9f61c5faec60")
    public static UML2InstanceValue safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2InstanceValue.canInstantiate(obj))
        	return new UML2InstanceValue(obj);
        else
        	throw new IllegalArgumentException("UML2InstanceValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e1f677ac-1b92-4f4a-a14f-bba17275f8b5")
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
        UML2InstanceValue other = (UML2InstanceValue) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("45c68ea7-d03c-406b-920a-90463029e535")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("6bca2c02-fe43-4015-96b2-85d18e394642")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f2d80847-790f-42ea-a6b9-b9d5bed7f2f1")
    protected UML2InstanceValue(Dependency elt) {
        this.elt = elt;
    }

    @objid ("212a0d8e-5958-407c-8c93-a9014079a3cb")
    public static final class MdaTypes {
        @objid ("d592374c-e711-4873-bebc-e4dda99b3d8b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f68a0cdf-1da9-46bc-820d-21cbb2d08074")
        private static Stereotype MDAASSOCDEP;

        @objid ("a9fe326c-aa78-41be-85d9-e35557ee317e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f09a045e-7935-4c9e-ba21-dc4c0bcdb6f4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5791cd76-03ec-11e2-9c63-0027103f347d");
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
