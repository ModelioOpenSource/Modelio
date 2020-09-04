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
 * Proxy class to handle a {@link Dependency} with << UML2ComponentRealization >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("20892636-39d2-4f82-9ef5-673a7149b405")
public class UML2ComponentRealization {
    @objid ("76bf7164-4816-4d0e-9f0c-7c4474b8c87b")
    public static final String STEREOTYPE_NAME = "UML2ComponentRealization";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("d5c92ddc-53b1-4553-9560-487fc7abbeb3")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ComponentRealization proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ComponentRealization >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6dd161d7-0089-413b-aeab-b768cd9c7244")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ComponentRealization.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ComponentRealization >> then instantiate a {@link UML2ComponentRealization} proxy.
     * 
     * @return a {@link UML2ComponentRealization} proxy on the created {@link Dependency}.
     */
    @objid ("907e2584-8e2a-4e1a-a89e-1547164c17d2")
    public static UML2ComponentRealization create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ComponentRealization.STEREOTYPE_NAME);
        return UML2ComponentRealization.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ComponentRealization} proxy from a {@link Dependency} stereotyped << UML2ComponentRealization >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ComponentRealization} proxy or <i>null</i>.
     */
    @objid ("ed2beeab-fde8-442b-98ec-269846108651")
    public static UML2ComponentRealization instantiate(Dependency obj) {
        return UML2ComponentRealization.canInstantiate(obj) ? new UML2ComponentRealization(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ComponentRealization} proxy from a {@link Dependency} stereotyped << UML2ComponentRealization >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ComponentRealization} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7c0bdff9-a5bd-4486-a915-f21258424848")
    public static UML2ComponentRealization safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ComponentRealization.canInstantiate(obj))
        	return new UML2ComponentRealization(obj);
        else
        	throw new IllegalArgumentException("UML2ComponentRealization: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a4ffa396-4b74-453e-9198-e2ea68d9cb30")
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
        UML2ComponentRealization other = (UML2ComponentRealization) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("a1533f10-e753-4651-a8b4-bc755a9b965b")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("ff417bea-fa24-4602-8c37-7c5f4155b111")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5f02c8de-1d15-4d49-9a0f-ae8b63caf312")
    protected UML2ComponentRealization(Dependency elt) {
        this.elt = elt;
    }

    @objid ("c07294a9-7636-4649-a593-5c852a1a29bd")
    public static final class MdaTypes {
        @objid ("9e4c6ae6-69d6-4f3f-b305-a8f144416fda")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("11624801-9cd7-49cb-a230-cc75e034d70f")
        private static Stereotype MDAASSOCDEP;

        @objid ("7a724d1c-7624-4b40-aaeb-fef55f1cc4bd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("367229a2-e94c-4d1a-bb01-ef1c7923556c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "99bf7919-5d09-11df-a996-001302895b2b");
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
