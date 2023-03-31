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
    @objid ("24d16547-becf-44d6-9ff4-5c7fd717f333")
    public static final String STEREOTYPE_NAME = "test_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("95d7185f-69f2-4fda-af45-de3f813495ac")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link TestDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << test_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e8f0c0e1-f345-402b-a0e1-e4611bf62f8f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TestDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << test_diagram >> then instantiate a {@link TestDiagram} proxy.
     * 
     * @return a {@link TestDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("7bbaf7d4-908e-4fb1-81ea-53e50a1e824d")
    public static TestDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TestDiagram.STEREOTYPE_NAME);
        return TestDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link TestDiagram} proxy from a {@link StaticDiagram} stereotyped << test_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link TestDiagram} proxy or <i>null</i>.
     */
    @objid ("6c486f2a-4dec-48f5-8d5c-5692707f50c5")
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
    @objid ("61d177d9-c178-4e4e-ab72-0cb532e3d26d")
    public static TestDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (TestDiagram.canInstantiate(obj))
        	return new TestDiagram(obj);
        else
        	throw new IllegalArgumentException("TestDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fa692cf3-0992-4f47-8d30-602ae992093e")
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
    @objid ("a32b7742-4711-4e29-9e7e-cf94d4f1644b")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("2d8fd921-5b59-473a-8286-f71d35c941c6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e58536df-52a7-4b3d-8ee2-17b634a04556")
    protected  TestDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("3c996ab1-fae6-472e-98ca-526b7c97dbfe")
    public static final class MdaTypes {
        @objid ("4a0117ae-8d63-49e0-b96e-f863cd058bd4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ae46d67e-da1f-4fd9-8770-e14818cf3b20")
        private static Stereotype MDAASSOCDEP;

        @objid ("79299391-ebc5-4226-ab2c-eb1933652bfd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bb60dc0f-6e32-49ba-9f24-eba1f06d5ee9")
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
