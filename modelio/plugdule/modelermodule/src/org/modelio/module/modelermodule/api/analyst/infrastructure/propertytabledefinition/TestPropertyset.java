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
    @objid ("aa298694-c593-4874-9ef4-69441e0f8621")
    public static final String STEREOTYPE_NAME = "test_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("3308ab0c-d63b-4e46-bf75-fead2feb2409")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link TestPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << test_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5dcd1a57-41d5-41fe-857c-639a69aa27d9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TestPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << test_propertyset >> then instantiate a {@link TestPropertyset} proxy.
     * 
     * @return a {@link TestPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("896bfe6d-e310-41d6-9bb2-02f2f0c8f9b1")
    public static TestPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TestPropertyset.STEREOTYPE_NAME);
        return TestPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link TestPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << test_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link TestPropertyset} proxy or <i>null</i>.
     */
    @objid ("7292949c-9112-41b2-9c25-3196bde7f9ac")
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
    @objid ("251ee5bf-29ab-475c-b0e0-b2db607dc698")
    public static TestPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (TestPropertyset.canInstantiate(obj))
        	return new TestPropertyset(obj);
        else
        	throw new IllegalArgumentException("TestPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f25349f6-168e-4519-916b-a2a7c95f3d19")
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
    @objid ("914c3e62-f567-457f-91aa-15fdc746f81d")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("47af45e5-d197-4cab-8500-29ee55aa908b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("dd0151b2-9482-433c-9c0a-8e43fd589f4e")
    protected TestPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("ba30c140-5731-4627-af12-930f1598286d")
    public static final class MdaTypes {
        @objid ("9d254336-0c7a-42d8-85f9-ecb40adcc969")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9bd68b03-569f-409a-9343-18f9c0797f59")
        private static Stereotype MDAASSOCDEP;

        @objid ("eb6ec6c7-d1dc-44c8-b5e0-becd15fc43d8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7e407912-7d55-4403-aa85-65fa3a46e5ee")
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
