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
 * Proxy class to handle a {@link Dependency} with << antonym >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e3e8030d-e474-473c-b754-e7e5465e68e7")
public class Antonym {
    @objid ("769555e5-2993-4530-9f50-450415ec3962")
    public static final String STEREOTYPE_NAME = "antonym";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("11b09145-5882-4865-8a9e-69b96c4f99fc")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Antonym proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << antonym >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("58167804-0a4a-4c79-b7c9-85b9dea0fed4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Antonym.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << antonym >> then instantiate a {@link Antonym} proxy.
     * 
     * @return a {@link Antonym} proxy on the created {@link Dependency}.
     */
    @objid ("76379f42-e9b1-428f-99c8-7455fd94c0d4")
    public static Antonym create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Antonym.STEREOTYPE_NAME);
        return Antonym.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Antonym} proxy from a {@link Dependency} stereotyped << antonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Antonym} proxy or <i>null</i>.
     */
    @objid ("5941a748-f8ac-4ce2-92a2-d2ca8a0e9dac")
    public static Antonym instantiate(Dependency obj) {
        return Antonym.canInstantiate(obj) ? new Antonym(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Antonym} proxy from a {@link Dependency} stereotyped << antonym >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Antonym} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("aedce9c8-e131-4e14-98a9-8b3eacc52aae")
    public static Antonym safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Antonym.canInstantiate(obj))
        	return new Antonym(obj);
        else
        	throw new IllegalArgumentException("Antonym: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("878cb31d-fac0-412f-bbe9-e49085a42a75")
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
        Antonym other = (Antonym) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("2124ceb5-c60e-4896-b09b-0208ce231103")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("11b98ab9-4be5-4a27-9d77-eb4a49600df3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e79ba06f-90fa-4efe-b662-dfac843175e1")
    protected  Antonym(Dependency elt) {
        this.elt = elt;
    }

    @objid ("e8185a79-1f85-46e2-9ae1-2d7bcf9b15b1")
    public static final class MdaTypes {
        @objid ("686aa614-877b-4c52-8509-090b46a58678")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("550298cc-03df-4478-999b-785f6665ace3")
        private static Stereotype MDAASSOCDEP;

        @objid ("43155ae3-63aa-4e06-9526-0ba39bc5e073")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5d70e6eb-441c-4efb-9a28-65fab0cca8ac")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0233-0000-000000000000");
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
