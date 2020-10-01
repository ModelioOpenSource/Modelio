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
 * Proxy class to handle a {@link Dependency} with << part >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5b672df6-1d49-4ad4-890a-08c3ae1afcb3")
public class Part {
    @objid ("29909669-0611-4860-b9b1-f66a36c47625")
    public static final String STEREOTYPE_NAME = "part";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7d7eb01c-8269-471e-bd80-2a992751d1e9")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Part proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << part >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f0df5177-5804-43bb-a9cd-e59556ea8ea5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Part.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << part >> then instantiate a {@link Part} proxy.
     * 
     * @return a {@link Part} proxy on the created {@link Dependency}.
     */
    @objid ("e5570d32-72f9-4ccc-b199-23b07949b27f")
    public static Part create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Part.STEREOTYPE_NAME);
        return Part.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Part} proxy from a {@link Dependency} stereotyped << part >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Part} proxy or <i>null</i>.
     */
    @objid ("9377f6e5-5bae-4411-8937-4b16e91302b9")
    public static Part instantiate(Dependency obj) {
        return Part.canInstantiate(obj) ? new Part(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Part} proxy from a {@link Dependency} stereotyped << part >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Part} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("86a91da0-aaf7-4690-bdc1-9c4ca7de81b0")
    public static Part safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Part.canInstantiate(obj))
        	return new Part(obj);
        else
        	throw new IllegalArgumentException("Part: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("00907372-1b07-480d-9491-af4c9a03f9dc")
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
        Part other = (Part) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("019611ae-ad6a-4f4c-b368-602bd3905cb1")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("279d3257-6fcc-4816-bb03-83a95f61c7b8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("170dd2ea-fead-4797-b68e-80c7681b1876")
    protected Part(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7ea483f7-ea8b-4bc8-8c23-87eb77766490")
    public static final class MdaTypes {
        @objid ("908b4ae7-6a49-4c4a-93a5-1b682448b9f1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ec7240fb-2e63-4c2f-93ca-e5949f22e4cb")
        private static Stereotype MDAASSOCDEP;

        @objid ("1e372ce0-29da-4ea3-81f0-e4b88c70cee0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b5ea6b46-0cbf-42d8-8940-ef73d38ac309")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-00b7-0000-000000000000");
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
