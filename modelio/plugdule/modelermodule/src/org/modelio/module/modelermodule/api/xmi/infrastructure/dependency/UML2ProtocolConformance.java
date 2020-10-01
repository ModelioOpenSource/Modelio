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
 * Proxy class to handle a {@link Dependency} with << UML2ProtocolConformance >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fd03066d-0a51-4fb7-9404-e4fdea747c28")
public class UML2ProtocolConformance {
    @objid ("c8934ed0-327a-467b-94da-9b098383174c")
    public static final String STEREOTYPE_NAME = "UML2ProtocolConformance";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("d38d95c8-3666-4740-a51f-46cd0fdaaa6f")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ProtocolConformance proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ProtocolConformance >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5ab25da5-7480-4d02-b7df-4d0709ebac9f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ProtocolConformance.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ProtocolConformance >> then instantiate a {@link UML2ProtocolConformance} proxy.
     * 
     * @return a {@link UML2ProtocolConformance} proxy on the created {@link Dependency}.
     */
    @objid ("958d21bb-2e2b-46d6-acf8-b3f3e2a56348")
    public static UML2ProtocolConformance create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ProtocolConformance.STEREOTYPE_NAME);
        return UML2ProtocolConformance.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ProtocolConformance} proxy from a {@link Dependency} stereotyped << UML2ProtocolConformance >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ProtocolConformance} proxy or <i>null</i>.
     */
    @objid ("9fd851b7-541f-4a13-a3b8-6d19a3d5001a")
    public static UML2ProtocolConformance instantiate(Dependency obj) {
        return UML2ProtocolConformance.canInstantiate(obj) ? new UML2ProtocolConformance(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ProtocolConformance} proxy from a {@link Dependency} stereotyped << UML2ProtocolConformance >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ProtocolConformance} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("84771242-94a5-4df3-a7cc-9c11544b3a6c")
    public static UML2ProtocolConformance safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ProtocolConformance.canInstantiate(obj))
        	return new UML2ProtocolConformance(obj);
        else
        	throw new IllegalArgumentException("UML2ProtocolConformance: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3e7b2092-c3e1-4beb-80fd-e0a13e75e062")
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
        UML2ProtocolConformance other = (UML2ProtocolConformance) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("0f57ba85-510d-4dd6-9e13-3ae031a17d64")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8760568c-3428-44a7-a7c3-5c32ae5777ad")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("52db1a3c-5f5d-4762-9ba4-7ca58bf9559b")
    protected UML2ProtocolConformance(Dependency elt) {
        this.elt = elt;
    }

    @objid ("04468c8e-8b2c-41f7-86f3-27c6af364806")
    public static final class MdaTypes {
        @objid ("44ba738a-a237-4a72-863c-a184b441f5de")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e9cc8ca8-55e4-4616-a54f-90e6fa2bea33")
        private static Stereotype MDAASSOCDEP;

        @objid ("d8575e82-be2b-4a99-9694-7bbe9da4b905")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b0658c3f-2163-4702-bf27-889bfba57a12")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3edfb381-5d0d-11df-a996-001302895b2b");
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
