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
    @objid ("c76a9406-84f9-4511-b457-e0e9752a75ac")
    public static final String STEREOTYPE_NAME = "business_rule_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("ddb9dfbc-9dad-424f-9471-90b7bb9e1822")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link BusinessRuleDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << business_rule_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b34b9e1c-fa4c-4227-80ea-f42b00ac8977")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, BusinessRuleDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << business_rule_diagram >> then instantiate a {@link BusinessRuleDiagram} proxy.
     * 
     * @return a {@link BusinessRuleDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("b561142e-97b5-41d9-8c07-6793950c307f")
    public static BusinessRuleDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, BusinessRuleDiagram.STEREOTYPE_NAME);
        return BusinessRuleDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link BusinessRuleDiagram} proxy from a {@link StaticDiagram} stereotyped << business_rule_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link BusinessRuleDiagram} proxy or <i>null</i>.
     */
    @objid ("4784d46f-1539-4668-9501-90c529533f60")
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
    @objid ("b03af01c-8004-4a79-a1a3-d6c993aec381")
    public static BusinessRuleDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (BusinessRuleDiagram.canInstantiate(obj))
        	return new BusinessRuleDiagram(obj);
        else
        	throw new IllegalArgumentException("BusinessRuleDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bcf8f7e1-6eb7-4f6c-b719-539696e11f3b")
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
    @objid ("7f97c2f5-af4a-40a4-bf4d-4756356bee5c")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("dcc96eeb-1c4c-445f-95ac-a32e200b2924")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2d211db2-57fd-4a53-9e44-ee93c687efec")
    protected BusinessRuleDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("cb89aa15-04f6-45ea-9d10-613b4dc93dc4")
    public static final class MdaTypes {
        @objid ("2daac03d-2c7f-44da-a337-2dab73fc7707")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b6e85e6f-388c-409d-aa66-96a2ec1ff870")
        private static Stereotype MDAASSOCDEP;

        @objid ("aa4e0d4f-cfd7-42b5-a2f9-d9ff049483fd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2c5ca61e-a7f9-4cb4-a386-6f59e403ed02")
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
