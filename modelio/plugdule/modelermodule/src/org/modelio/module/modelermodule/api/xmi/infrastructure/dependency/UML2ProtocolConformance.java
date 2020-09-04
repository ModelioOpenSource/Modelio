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
 * Proxy class to handle a {@link Dependency} with << UML2ProtocolConformance >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fd03066d-0a51-4fb7-9404-e4fdea747c28")
public class UML2ProtocolConformance {
    @objid ("7882e918-7c82-4fb4-9fde-183633cf7a0f")
    public static final String STEREOTYPE_NAME = "UML2ProtocolConformance";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("133c4ebe-b9ac-48f2-be13-693aa7d17d61")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ProtocolConformance proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ProtocolConformance >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("eeef0d55-3d79-4114-a6d4-76da6cefbce6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ProtocolConformance.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ProtocolConformance >> then instantiate a {@link UML2ProtocolConformance} proxy.
     * 
     * @return a {@link UML2ProtocolConformance} proxy on the created {@link Dependency}.
     */
    @objid ("26d5785e-c232-4634-ad17-60e3a6eb79ec")
    public static UML2ProtocolConformance create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ProtocolConformance.STEREOTYPE_NAME);
        return UML2ProtocolConformance.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ProtocolConformance} proxy from a {@link Dependency} stereotyped << UML2ProtocolConformance >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ProtocolConformance} proxy or <i>null</i>.
     */
    @objid ("4f9f1c62-6b23-4742-9efd-d4127ac7500e")
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
    @objid ("20dc8b03-8e6c-4409-870b-c4e4fdfcb708")
    public static UML2ProtocolConformance safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ProtocolConformance.canInstantiate(obj))
        	return new UML2ProtocolConformance(obj);
        else
        	throw new IllegalArgumentException("UML2ProtocolConformance: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("aa65672b-ef00-4c0d-a4c6-c18288c62104")
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
    @objid ("25c2a6a0-0096-48cd-acaf-0127f07d5882")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("6acfcd3c-a232-4f1c-a26a-cf54117db9b9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1d50647b-6c2b-49f7-9c4a-867465f8a228")
    protected UML2ProtocolConformance(Dependency elt) {
        this.elt = elt;
    }

    @objid ("04468c8e-8b2c-41f7-86f3-27c6af364806")
    public static final class MdaTypes {
        @objid ("8db747ae-3d87-47e8-b894-6eca1577d3d0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0ad4ed72-5075-49b2-b494-4154d71289a9")
        private static Stereotype MDAASSOCDEP;

        @objid ("88fadd86-e7c2-48ea-ba99-b33ad88e9d83")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("074571b4-2435-48af-b46e-5d916669fd20")
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
