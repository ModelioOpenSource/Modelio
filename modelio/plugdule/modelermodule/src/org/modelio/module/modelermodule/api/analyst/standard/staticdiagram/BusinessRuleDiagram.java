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
 * Proxy class to handle a {@link StaticDiagram} with << business_rule_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b8e1e546-cae7-4787-a2ae-463c7c107079")
public class BusinessRuleDiagram {
    @objid ("39102e66-b14b-4761-bbe1-e27bf6ec3b22")
    public static final String STEREOTYPE_NAME = "business_rule_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("48bfadd3-3c82-481b-9d5e-7c5b81d2cba0")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link BusinessRuleDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << business_rule_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fe77354d-4cfd-42a9-bbb1-c7af9fd88723")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, BusinessRuleDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << business_rule_diagram >> then instantiate a {@link BusinessRuleDiagram} proxy.
     * 
     * @return a {@link BusinessRuleDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("f9f44b4d-cc6b-4b82-b5d0-72dc09b00f7e")
    public static BusinessRuleDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, BusinessRuleDiagram.STEREOTYPE_NAME);
        return BusinessRuleDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link BusinessRuleDiagram} proxy from a {@link StaticDiagram} stereotyped << business_rule_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link BusinessRuleDiagram} proxy or <i>null</i>.
     */
    @objid ("f6ec7fe6-c5c2-48e8-833e-46b49cd5b647")
    public static BusinessRuleDiagram instantiate(StaticDiagram obj) {
        return BusinessRuleDiagram.canInstantiate(obj) ? new BusinessRuleDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link BusinessRuleDiagram} proxy from a {@link StaticDiagram} stereotyped << business_rule_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link BusinessRuleDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b6957bd5-862e-40e1-8e52-dd81ab1b4448")
    public static BusinessRuleDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (BusinessRuleDiagram.canInstantiate(obj))
        	return new BusinessRuleDiagram(obj);
        else
        	throw new IllegalArgumentException("BusinessRuleDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fbaba0d2-0daa-40e4-98f2-ae56e674af67")
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
        BusinessRuleDiagram other = (BusinessRuleDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("22ddc971-60e9-426d-8688-566814ba6399")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("d81ceb3a-0110-46cc-a2c2-d138864f9184")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("8c19c9ed-0c57-4dc1-a8de-f1fcf7e8b05c")
    protected  BusinessRuleDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("cb89aa15-04f6-45ea-9d10-613b4dc93dc4")
    public static final class MdaTypes {
        @objid ("2f563562-66d8-4c06-bc61-c86902ced33d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b27d92de-1014-44d7-8ae7-f074918e0a1f")
        private static Stereotype MDAASSOCDEP;

        @objid ("3461826e-5fb5-4915-8112-b79776cc001d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a1544d11-aae9-4fc5-a015-fff297f20a90")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0aca-0000-000000000000");
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
