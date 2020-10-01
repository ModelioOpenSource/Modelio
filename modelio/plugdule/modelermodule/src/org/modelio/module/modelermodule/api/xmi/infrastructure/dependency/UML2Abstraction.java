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
 * Proxy class to handle a {@link Dependency} with << UML2Abstraction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9d1e11d0-fed8-4531-a0ff-914ea423a867")
public class UML2Abstraction {
    @objid ("cae3d43b-e739-4e59-9b89-d4834bb6ddb3")
    public static final String STEREOTYPE_NAME = "UML2Abstraction";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e12a4425-2f7f-4ddd-87cb-504c642943d9")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Abstraction proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Abstraction >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b3fae75f-7545-40db-8b4d-dd16c48ed0e3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Abstraction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Abstraction >> then instantiate a {@link UML2Abstraction} proxy.
     * 
     * @return a {@link UML2Abstraction} proxy on the created {@link Dependency}.
     */
    @objid ("be61921d-593d-416c-b979-318d9e9d9d01")
    public static UML2Abstraction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Abstraction.STEREOTYPE_NAME);
        return UML2Abstraction.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Abstraction} proxy from a {@link Dependency} stereotyped << UML2Abstraction >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Abstraction} proxy or <i>null</i>.
     */
    @objid ("3b150e17-f03d-471d-87bf-c7a0f2f8e3dd")
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
    @objid ("ef11247b-40ce-43c5-b17f-254031440c50")
    public static UML2Abstraction safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Abstraction.canInstantiate(obj))
        	return new UML2Abstraction(obj);
        else
        	throw new IllegalArgumentException("UML2Abstraction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("892bc6ad-9352-4d9c-af62-24c501f3be6e")
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
    @objid ("57b9c596-6fe5-4f78-b30a-abe787932997")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("b0f19be9-0668-4900-9c4a-566212f05c41")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c617601a-b4f2-4a2b-a746-ff4db76b1cd7")
    protected UML2Abstraction(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6d9d3b85-b56b-481e-8799-f55b8683359f")
    public static final class MdaTypes {
        @objid ("2e5c98c8-9e0f-48ec-a7e5-e9558262a411")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("99e1b3ef-4181-4683-b65d-21becba4fb0a")
        private static Stereotype MDAASSOCDEP;

        @objid ("f14b8c0d-df20-49ec-bb22-76725cb60592")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("799f82a3-9253-48d4-a603-bd5b641a8937")
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
