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
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << test_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ff83f56b-339b-4fdb-b3f0-8f9a92979687")
public class TestDiagram {
    @objid ("76351f8d-0ffe-45f7-ae76-b0116a4a6375")
    public static final String STEREOTYPE_NAME = "test_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("ef9222d1-3af2-4667-a4b4-4b43b2e8e884")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link TestDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << test_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0007436f-da16-4af2-ac7e-3c3d47200321")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TestDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << test_diagram >> then instantiate a {@link TestDiagram} proxy.
     * 
     * @return a {@link TestDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("8c995f8b-9e3f-4ddf-81ea-09c3835576a0")
    public static TestDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TestDiagram.STEREOTYPE_NAME);
        return TestDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link TestDiagram} proxy from a {@link StaticDiagram} stereotyped << test_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link TestDiagram} proxy or <i>null</i>.
     */
    @objid ("a1db1aac-3723-4467-8ff7-49fdb51e9ce5")
    public static TestDiagram instantiate(StaticDiagram obj) {
        return TestDiagram.canInstantiate(obj) ? new TestDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TestDiagram} proxy from a {@link StaticDiagram} stereotyped << test_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link TestDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6c66edc9-7fd3-43a5-82a8-f9a1bc0a3f12")
    public static TestDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (TestDiagram.canInstantiate(obj))
        	return new TestDiagram(obj);
        else
        	throw new IllegalArgumentException("TestDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("58de6832-d7b6-4c3c-afef-234a3df38eb2")
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
        TestDiagram other = (TestDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("a411a622-d5b8-44cc-ad3f-72daf4534d3e")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("7573240a-c485-4a95-b0ad-cce30fefddc7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("dc2b4b5c-e50c-4058-84d4-329c673e70a5")
    protected TestDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("3c996ab1-fae6-472e-98ca-526b7c97dbfe")
    public static final class MdaTypes {
        @objid ("476586b1-93ab-413e-96f6-8babfa43b78d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7c4d148c-bc7b-4ffd-98b7-924e9c2ada7e")
        private static Stereotype MDAASSOCDEP;

        @objid ("f6fffc52-f878-4257-8acc-b3e3f066ffee")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("84dc3fde-4a34-4ea9-a75c-1033ef788e4b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e1d2f141-a387-4fd1-bff7-3f7dcbcb8718");
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
