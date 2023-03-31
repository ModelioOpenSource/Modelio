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
    @objid ("5ee68467-8835-4778-885f-b6a2639d66f5")
    public static final String STEREOTYPE_NAME = "test_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("374cc197-e15f-4b8c-bf2e-d3aa64844c45")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link TestPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << test_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b0ac59c2-a194-4b83-a5d9-5de56e6ab850")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TestPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << test_propertyset >> then instantiate a {@link TestPropertyset} proxy.
     * 
     * @return a {@link TestPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("cf14feb1-534d-48a9-84e1-4cd0eecfd796")
    public static TestPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.PropertyTableDefinition");
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
    @objid ("1fad6ce5-f033-49dc-b951-1a3b196afae3")
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
    @objid ("a90bc263-9adb-460b-9c0e-8c0c34b3491b")
    public static TestPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (TestPropertyset.canInstantiate(obj))
        	return new TestPropertyset(obj);
        else
        	throw new IllegalArgumentException("TestPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e2f33cab-0c15-4e90-a1bd-2f2a7a141ee2")
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
    @objid ("4a7bb6ac-bf87-4886-92f5-b209c68766b6")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("647d0042-1272-4513-b605-3e1971b9261a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("ad884841-af59-47d4-89ec-3d5ec156452f")
    protected  TestPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("ba30c140-5731-4627-af12-930f1598286d")
    public static final class MdaTypes {
        @objid ("3243b046-7588-4276-9706-6784addad9f2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("aa4afbb0-4ab1-4b92-b809-5611d8fc7807")
        private static Stereotype MDAASSOCDEP;

        @objid ("5f176a04-1466-4e92-8ffc-4f48e4759640")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bb18874f-61ce-468b-8967-70ad21030092")
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
