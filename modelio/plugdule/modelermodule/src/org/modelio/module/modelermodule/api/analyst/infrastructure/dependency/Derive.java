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
 * Proxy class to handle a {@link Dependency} with << derive >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0a2b7f32-1cd2-44cd-b61d-f4464bbf91de")
public class Derive {
    @objid ("c90cc1e5-069a-48db-9f7a-87ebf2b3dda0")
    public static final String STEREOTYPE_NAME = "derive";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7f4ba3ee-3eee-4599-ae24-1e88a7e7995e")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Derive proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << derive >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("316a9b45-e4e3-496c-b4e2-250b55a5b8ff")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Derive.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << derive >> then instantiate a {@link Derive} proxy.
     * 
     * @return a {@link Derive} proxy on the created {@link Dependency}.
     */
    @objid ("3679ee3c-e28c-47ef-8e2f-7a6a30797a02")
    public static Derive create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Derive.STEREOTYPE_NAME);
        return Derive.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Derive} proxy from a {@link Dependency} stereotyped << derive >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Derive} proxy or <i>null</i>.
     */
    @objid ("a3c28166-8af8-4621-98f3-3cde2840101d")
    public static Derive instantiate(Dependency obj) {
        return Derive.canInstantiate(obj) ? new Derive(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Derive} proxy from a {@link Dependency} stereotyped << derive >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Derive} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2415075d-fab8-4bc9-a246-b5215b47c210")
    public static Derive safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Derive.canInstantiate(obj))
        	return new Derive(obj);
        else
        	throw new IllegalArgumentException("Derive: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("af2e8731-b921-45a9-9815-4fc57a460f3a")
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
        Derive other = (Derive) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("dd56d01f-9467-48e1-abd5-bb8fb6885bcc")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("f45d1c2e-f9f5-4c69-bdde-7f1c2563a07e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("4b2737c4-9548-4b01-803c-90f1a37469d6")
    protected  Derive(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7dea825e-1f89-4805-9f37-7ff8a5c37024")
    public static final class MdaTypes {
        @objid ("be7667b0-0241-49a0-85ff-38c7a933dc26")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4b8aee77-7499-40ba-b091-70386988592d")
        private static Stereotype MDAASSOCDEP;

        @objid ("b639effd-2e55-4b5b-b46b-dbab29625822")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("07c73a2f-0316-4dc9-a436-9259a9e881eb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-021a-0000-000000000000");
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
