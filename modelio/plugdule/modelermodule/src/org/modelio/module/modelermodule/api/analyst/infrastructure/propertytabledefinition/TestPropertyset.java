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
package org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition;

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
 * Proxy class to handle a {@link PropertyTableDefinition} with << test_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6d0ae6b5-5de2-44ca-873b-f025f26616a4")
public class TestPropertyset {
    @objid ("bab9fa43-7fe9-4004-b437-5510d78447ef")
    public static final String STEREOTYPE_NAME = "test_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("01bf3810-890b-4620-be36-f14799c83fe9")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link TestPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << test_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("32a550aa-1624-42b5-baa2-0b3841c96fb2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TestPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << test_propertyset >> then instantiate a {@link TestPropertyset} proxy.
     * 
     * @return a {@link TestPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("6bf2198a-66cb-4220-8094-7df4ce306bb3")
    public static TestPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TestPropertyset.STEREOTYPE_NAME);
        return TestPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link TestPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << test_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link TestPropertyset} proxy or <i>null</i>.
     */
    @objid ("ff22acad-56ac-48d6-9b37-6a89627681f0")
    public static TestPropertyset instantiate(PropertyTableDefinition obj) {
        return TestPropertyset.canInstantiate(obj) ? new TestPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TestPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << test_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link TestPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("52b4e5db-0b73-467b-917f-992fb65c67ab")
    public static TestPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (TestPropertyset.canInstantiate(obj))
        	return new TestPropertyset(obj);
        else
        	throw new IllegalArgumentException("TestPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c98f3228-3aef-46c2-a806-f19cc361c1c5")
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
        TestPropertyset other = (TestPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("18c55c16-f066-44c6-8894-85a738ab8a6a")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("79314e0e-8f02-409a-96d2-4d97e0a16050")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("15688628-f7c6-4e82-a64d-71cb75bcf761")
    protected TestPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("ba30c140-5731-4627-af12-930f1598286d")
    public static final class MdaTypes {
        @objid ("d52e9932-621a-438e-8ce5-c01108e3d311")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("93a6d6f3-44bf-42e3-b816-4db2994d30c9")
        private static Stereotype MDAASSOCDEP;

        @objid ("decd83e3-b4e6-4e7e-ad45-2638a48c9d11")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d5a885d3-ee94-4c0f-8c71-6a6401d8cd42")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "859f8b76-5acc-4a9c-a5eb-973467388b13");
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
