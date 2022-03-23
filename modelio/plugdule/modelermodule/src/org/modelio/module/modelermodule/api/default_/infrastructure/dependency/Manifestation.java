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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << manifestation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("21713da7-5de8-4464-8371-298380f8461d")
public class Manifestation {
    @objid ("b6c871a7-9bfe-40d5-b26c-d65b9bf0bcee")
    public static final String STEREOTYPE_NAME = "manifestation";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("09c7be44-2f68-4686-b8e9-4c2a5b8dd2a5")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Manifestation proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << manifestation >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4be31d2a-e66e-406a-8c3a-f781b4fefa71")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Manifestation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << manifestation >> then instantiate a {@link Manifestation} proxy.
     * 
     * @return a {@link Manifestation} proxy on the created {@link Dependency}.
     */
    @objid ("24aaf366-cb38-44b5-933d-90a80421b514")
    public static Manifestation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Manifestation.STEREOTYPE_NAME);
        return Manifestation.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Manifestation} proxy from a {@link Dependency} stereotyped << manifestation >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Manifestation} proxy or <i>null</i>.
     */
    @objid ("2acf0876-0753-4699-a7d7-43672d061548")
    public static Manifestation instantiate(Dependency obj) {
        return Manifestation.canInstantiate(obj) ? new Manifestation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Manifestation} proxy from a {@link Dependency} stereotyped << manifestation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Manifestation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c5273ce1-ba38-485a-9dec-bae6ba67015a")
    public static Manifestation safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Manifestation.canInstantiate(obj))
        	return new Manifestation(obj);
        else
        	throw new IllegalArgumentException("Manifestation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("972f6dfb-8e4d-42b4-ad0d-bb6ef83829ef")
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
        Manifestation other = (Manifestation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("46a33498-1610-4a25-a5ff-1d9064548aa2")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("3fc98599-2286-469d-acae-654d97697f1d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("9add7ce7-0fae-4cf0-b8e7-d6af1b6fffc8")
    protected  Manifestation(Dependency elt) {
        this.elt = elt;
    }

    @objid ("a2578fb0-0629-4fb7-8870-be21ae2eb03a")
    public static final class MdaTypes {
        @objid ("61d85fe3-6f71-4f7e-9c26-c3e345936fd2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("12cd3c46-fa15-4a78-bedd-b5500d71d337")
        private static Stereotype MDAASSOCDEP;

        @objid ("ead7f34f-b347-4495-846b-d4ab31688640")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("49fe6b84-e31e-45a3-8b19-29cc421d15b7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "d5bccf8e-79b3-48df-8c79-09200aa52d19");
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
