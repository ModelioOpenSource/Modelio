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
 * Proxy class to handle a {@link Dependency} with << guarantee >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("59095dca-f0dc-4be2-a58b-5035f5929642")
public class Guarantee {
    @objid ("96c4f5d3-1ce0-408b-8a03-da21205f22f4")
    public static final String STEREOTYPE_NAME = "guarantee";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("3ffcc8f4-79c4-4bd8-b4e7-02f62ebe120d")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Guarantee proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << guarantee >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("89ccbf38-b386-4f57-9ba7-f4ef1fad0481")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Guarantee.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << guarantee >> then instantiate a {@link Guarantee} proxy.
     * 
     * @return a {@link Guarantee} proxy on the created {@link Dependency}.
     */
    @objid ("bb60d4c2-b73d-479b-ac13-c84409f5b3e9")
    public static Guarantee create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Guarantee.STEREOTYPE_NAME);
        return Guarantee.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Guarantee} proxy from a {@link Dependency} stereotyped << guarantee >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Guarantee} proxy or <i>null</i>.
     */
    @objid ("948c1b7d-e7f1-453d-8674-2ba03379dd42")
    public static Guarantee instantiate(Dependency obj) {
        return Guarantee.canInstantiate(obj) ? new Guarantee(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Guarantee} proxy from a {@link Dependency} stereotyped << guarantee >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Guarantee} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b34430ec-80ba-4887-9420-d15b03052e88")
    public static Guarantee safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Guarantee.canInstantiate(obj))
        	return new Guarantee(obj);
        else
        	throw new IllegalArgumentException("Guarantee: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5c1afd3c-4f13-4837-a3ad-489bc1734ee7")
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
        Guarantee other = (Guarantee) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("af5a61a5-c3e5-471b-b2c5-c7492e23299c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("87916edc-ad2f-4165-ab2e-9d5a0d2af1c5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("73d033ad-5c6e-48ff-beb5-31a9b50d865d")
    protected  Guarantee(Dependency elt) {
        this.elt = elt;
    }

    @objid ("d5bd302d-6ad5-4ad1-bfa4-bca43f79b810")
    public static final class MdaTypes {
        @objid ("828a65b8-6b34-43dc-9e22-9429bed8f45e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("24e1c303-f28a-4fa1-8210-42e3223a060a")
        private static Stereotype MDAASSOCDEP;

        @objid ("93429fb2-dd86-4413-a48a-d4dafb4d8e7e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ee2a52f5-807f-48b0-b086-c4fbe233a7ee")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0251-0000-000000000000");
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
