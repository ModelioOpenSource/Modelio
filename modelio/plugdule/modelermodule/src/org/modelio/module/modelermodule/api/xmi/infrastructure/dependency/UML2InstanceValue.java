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
 * Proxy class to handle a {@link Dependency} with << UML2InstanceValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("57682dea-36fc-489f-a20b-7834ce24109f")
public class UML2InstanceValue {
    @objid ("458409df-a54f-412d-95e9-33a09ba308ad")
    public static final String STEREOTYPE_NAME = "UML2InstanceValue";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("2f3bbcb2-56e9-4a0e-b2e5-91055026803a")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2InstanceValue proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2InstanceValue >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cce2b017-03f2-4f34-b499-3ece9ab7cade")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InstanceValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2InstanceValue >> then instantiate a {@link UML2InstanceValue} proxy.
     * 
     * @return a {@link UML2InstanceValue} proxy on the created {@link Dependency}.
     */
    @objid ("8cb303aa-31e8-434b-977f-26e45293b49f")
    public static UML2InstanceValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InstanceValue.STEREOTYPE_NAME);
        return UML2InstanceValue.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2InstanceValue} proxy from a {@link Dependency} stereotyped << UML2InstanceValue >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2InstanceValue} proxy or <i>null</i>.
     */
    @objid ("5f2020d7-0edd-4acd-8d38-838cde6b0864")
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
    @objid ("5f68d07e-5617-4da9-a564-c61edfc7e529")
    public static UML2InstanceValue safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2InstanceValue.canInstantiate(obj))
        	return new UML2InstanceValue(obj);
        else
        	throw new IllegalArgumentException("UML2InstanceValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0423b195-8895-45d1-9199-a447cc7cd7ce")
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
    @objid ("172fa2c0-e0e5-44d8-9c98-60b1fcc9dab1")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("97e9108b-17ac-455f-b98c-5e138ba81eb4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a0420c68-0bc3-4e5f-9266-9b8339a45984")
    protected UML2InstanceValue(Dependency elt) {
        this.elt = elt;
    }

    @objid ("212a0d8e-5958-407c-8c93-a9014079a3cb")
    public static final class MdaTypes {
        @objid ("5b68107e-8da8-4169-960c-88291c445e38")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b222f90c-88c1-4245-ada6-cac2ad6d332c")
        private static Stereotype MDAASSOCDEP;

        @objid ("e140561e-a919-451e-b5d5-128a61d03ac6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("781f7db7-dce0-46e4-be6a-84532ad63086")
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
