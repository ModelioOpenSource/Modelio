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
 * Proxy class to handle a {@link Dependency} with << UML2ComponentRealization >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("20892636-39d2-4f82-9ef5-673a7149b405")
public class UML2ComponentRealization {
    @objid ("0e323b32-6101-4ebf-b301-087bc37bd15b")
    public static final String STEREOTYPE_NAME = "UML2ComponentRealization";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7dea6a11-2985-4587-9fdc-2f8ffd2d8df4")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ComponentRealization proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ComponentRealization >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("130abf5c-880e-458e-adba-13b2ea0581bf")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ComponentRealization.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ComponentRealization >> then instantiate a {@link UML2ComponentRealization} proxy.
     * 
     * @return a {@link UML2ComponentRealization} proxy on the created {@link Dependency}.
     */
    @objid ("8af631e0-258c-49a5-aeab-027e22f19a01")
    public static UML2ComponentRealization create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ComponentRealization.STEREOTYPE_NAME);
        return UML2ComponentRealization.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ComponentRealization} proxy from a {@link Dependency} stereotyped << UML2ComponentRealization >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ComponentRealization} proxy or <i>null</i>.
     */
    @objid ("2d1532d3-8df3-4d2f-9548-46608a8a7533")
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
    @objid ("fd23de31-61d6-4282-963e-0d4bc41cd8fc")
    public static UML2ComponentRealization safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ComponentRealization.canInstantiate(obj))
        	return new UML2ComponentRealization(obj);
        else
        	throw new IllegalArgumentException("UML2ComponentRealization: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d7328515-188b-4889-ba50-c5fceadc7d45")
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
    @objid ("f47b8ce4-417a-4e7f-b356-fa92d64719da")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("2e11864d-2921-49c5-8170-5ebe2b2ed91e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a267e7b2-00aa-450d-a71d-07a3b5772e17")
    protected UML2ComponentRealization(Dependency elt) {
        this.elt = elt;
    }

    @objid ("c07294a9-7636-4649-a593-5c852a1a29bd")
    public static final class MdaTypes {
        @objid ("d28adcaf-e2ad-4b53-87b3-1d1c23c4f8d8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3986ac8c-41d5-42a9-aa63-44f937d63e4d")
        private static Stereotype MDAASSOCDEP;

        @objid ("93e154bf-7fcc-4ccb-bb52-5a7e404a268e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("17ed631b-6a91-4745-b94a-b543537d3a90")
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
