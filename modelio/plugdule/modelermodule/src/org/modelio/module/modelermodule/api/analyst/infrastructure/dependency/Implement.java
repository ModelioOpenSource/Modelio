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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << implement >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("bd3859e6-6a4f-4b1b-82d2-b97c4db33bd4")
public class Implement {
    @objid ("480d1cff-50e4-4607-985c-15200c6bd397")
    public static final String STEREOTYPE_NAME = "implement";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("97b72b8d-6a8d-485c-9819-f9ee94883828")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Implement proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << implement >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c4b8289b-87b9-4539-8d2e-d021ee75feba")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implement.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << implement >> then instantiate a {@link Implement} proxy.
     * 
     * @return a {@link Implement} proxy on the created {@link Dependency}.
     */
    @objid ("79f33f37-d098-45be-9c4d-3b14a66f7c09")
    public static Implement create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implement.STEREOTYPE_NAME);
        return Implement.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Implement} proxy from a {@link Dependency} stereotyped << implement >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Implement} proxy or <i>null</i>.
     */
    @objid ("8df6dab1-9754-4e4c-a1f4-965e432f34ff")
    public static Implement instantiate(Dependency obj) {
        return Implement.canInstantiate(obj) ? new Implement(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implement} proxy from a {@link Dependency} stereotyped << implement >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Implement} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f018bd4e-f716-4b9a-92e4-ee089bff1c6c")
    public static Implement safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Implement.canInstantiate(obj))
        	return new Implement(obj);
        else
        	throw new IllegalArgumentException("Implement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("de01448d-ce68-4570-ba83-32b8158af06e")
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
        Implement other = (Implement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("9f2b1777-a901-43f6-8b8c-5d3162ab1f66")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("31ff7a57-4aca-465e-a8ed-8c6e9e8d68ea")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e992afd6-b0e8-4edc-bc45-775f22d50892")
    protected Implement(Dependency elt) {
        this.elt = elt;
    }

    @objid ("062103b4-cf74-41a4-8db5-49367f2eaa96")
    public static final class MdaTypes {
        @objid ("6d90e439-b2be-4de2-b87b-f24d754e99b8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("017bd08e-948c-4ae2-a99a-75076ab3ed3b")
        private static Stereotype MDAASSOCDEP;

        @objid ("0646720b-3de1-4f03-b865-5ca950740eb9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("10c9b247-7f36-4801-9773-c988db609858")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0260-0000-000000000000");
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
