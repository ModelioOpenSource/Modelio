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
 * Proxy class to handle a {@link Dependency} with << UML2Abstraction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9d1e11d0-fed8-4531-a0ff-914ea423a867")
public class UML2Abstraction {
    @objid ("31a2dbde-dd03-4099-9faa-ba3ba4e54425")
    public static final String STEREOTYPE_NAME = "UML2Abstraction";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("3d5365a9-f42d-48cf-aa93-954f2b1071b1")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Abstraction proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Abstraction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("15d66700-768d-4435-b21d-537ccfeef94f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Abstraction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Abstraction >> then instantiate a {@link UML2Abstraction} proxy.
     * 
     * @return a {@link UML2Abstraction} proxy on the created {@link Dependency}.
     */
    @objid ("a3967ac2-5f72-4d86-bdc2-5fc3265b1427")
    public static UML2Abstraction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Abstraction.STEREOTYPE_NAME);
        return UML2Abstraction.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Abstraction} proxy from a {@link Dependency} stereotyped << UML2Abstraction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Abstraction} proxy or <i>null</i>.
     */
    @objid ("7b1416df-289b-4abe-86de-9f166682832f")
    public static UML2Abstraction instantiate(Dependency obj) {
        return UML2Abstraction.canInstantiate(obj) ? new UML2Abstraction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Abstraction} proxy from a {@link Dependency} stereotyped << UML2Abstraction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Abstraction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("87ae9e20-cf00-4f2a-9314-26d0429a50ad")
    public static UML2Abstraction safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Abstraction.canInstantiate(obj))
        	return new UML2Abstraction(obj);
        else
        	throw new IllegalArgumentException("UML2Abstraction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b649642d-d1ad-4ffd-becc-b0fcc78d0695")
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
        UML2Abstraction other = (UML2Abstraction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("68c1b242-3221-43d5-8a95-fea31e78bce4")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("f21333b6-d918-4cf0-9d7e-e3fcae11021d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e4d720d3-7814-410c-be3e-af2f8518d582")
    protected UML2Abstraction(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6d9d3b85-b56b-481e-8799-f55b8683359f")
    public static final class MdaTypes {
        @objid ("77fae54d-ff49-4074-a2d6-5bb4408a384a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9236505f-67ba-4df4-8e3b-e5bae1f1d51e")
        private static Stereotype MDAASSOCDEP;

        @objid ("3eb3b646-5f3a-486c-bdce-6fa1516ec211")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("27ed7786-30b3-44bd-8759-86610bec5b2a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b355cc6c-c4aa-11df-b100-001302895b2b");
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
