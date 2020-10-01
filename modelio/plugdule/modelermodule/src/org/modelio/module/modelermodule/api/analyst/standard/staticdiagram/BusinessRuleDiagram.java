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
    @objid ("271ddb9f-fee0-4568-9bc4-a31f67cf855e")
    public static final String STEREOTYPE_NAME = "business_rule_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("d447dd5d-8f7c-4337-8452-cac8e7a75d0e")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link BusinessRuleDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << business_rule_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("60cf7c43-ae12-4dd8-af55-b409c2fbe15b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, BusinessRuleDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << business_rule_diagram >> then instantiate a {@link BusinessRuleDiagram} proxy.
     * 
     * @return a {@link BusinessRuleDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("3b77d56a-5343-4bc5-a197-834d6f0f4486")
    public static BusinessRuleDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
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
    @objid ("ac8abc8c-d9ac-4fb5-8877-37d34c18f193")
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
    @objid ("d7f66ae6-1e06-410f-828e-90fb995faf37")
    public static BusinessRuleDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (BusinessRuleDiagram.canInstantiate(obj))
        	return new BusinessRuleDiagram(obj);
        else
        	throw new IllegalArgumentException("BusinessRuleDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9f080792-b63e-45ab-a5bc-55099abd755e")
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
    @objid ("13aca57e-17a3-4153-9994-44a5d542b593")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("7d398614-77f0-441a-ba8e-2eda7e130bd0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e9702495-8c19-47b1-9a03-eed08cfe1631")
    protected BusinessRuleDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("cb89aa15-04f6-45ea-9d10-613b4dc93dc4")
    public static final class MdaTypes {
        @objid ("cfa24201-cdbf-4c81-916d-e2f0026729ad")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5cb3155d-381f-4827-9fb5-9fa37b3bc458")
        private static Stereotype MDAASSOCDEP;

        @objid ("9753054a-0178-4840-9484-9ece624c4a25")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c5a23c47-4ef8-41bc-803c-26570fc56b4f")
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
