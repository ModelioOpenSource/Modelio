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
package org.modelio.module.modelermodule.api.xmi.standard.parameter;

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
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Parameter} with << UML2OperationTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d3e40d9b-99b6-479e-872c-bab0545bb6db")
public class UML2OperationTemplateParameter {
    @objid ("6b46bedf-a0ab-455e-9218-05c48b1b30a2")
    public static final String STEREOTYPE_NAME = "UML2OperationTemplateParameter";

    /**
     * The underlying {@link Parameter} represented by this proxy, never null.
     */
    @objid ("0e23ee0b-ca26-4ecb-9be3-ddac052bae15")
    protected final Parameter elt;

    /**
     * Tells whether a {@link UML2OperationTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link Parameter} stereotyped << UML2OperationTemplateParameter >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("916f373a-5869-41a0-a0ce-3ce20fd24596")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Parameter) && ((Parameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2OperationTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Parameter} stereotyped << UML2OperationTemplateParameter >> then instantiate a {@link UML2OperationTemplateParameter} proxy.
     * 
     * @return a {@link UML2OperationTemplateParameter} proxy on the created {@link Parameter}.
     */
    @objid ("17fbbcb5-42b1-44d2-a80a-e8a550c0df12")
    public static UML2OperationTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Parameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2OperationTemplateParameter.STEREOTYPE_NAME);
        return UML2OperationTemplateParameter.instantiate((Parameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2OperationTemplateParameter} proxy from a {@link Parameter} stereotyped << UML2OperationTemplateParameter >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Parameter
     * @return a {@link UML2OperationTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("03987421-7b49-4af0-8c92-9585e0f5614f")
    public static UML2OperationTemplateParameter instantiate(Parameter obj) {
        return UML2OperationTemplateParameter.canInstantiate(obj) ? new UML2OperationTemplateParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2OperationTemplateParameter} proxy from a {@link Parameter} stereotyped << UML2OperationTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Parameter}
     * @return a {@link UML2OperationTemplateParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d63ee59a-cf1a-470c-bfb2-5030d61b208a")
    public static UML2OperationTemplateParameter safeInstantiate(Parameter obj) throws IllegalArgumentException {
        if (UML2OperationTemplateParameter.canInstantiate(obj))
        	return new UML2OperationTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2OperationTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("03a23f6f-bfbb-4710-b37c-3375d1731873")
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
        UML2OperationTemplateParameter other = (UML2OperationTemplateParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Parameter}. 
     * @return the Parameter represented by this proxy, never null.
     */
    @objid ("928a51c9-b2f0-45e7-bbb6-59a489fd022e")
    public Parameter getElement() {
        return this.elt;
    }

    @objid ("8b8bafd1-f706-4002-bfd2-9e0150c73c88")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0d4ab33f-e233-485c-ae12-b537a4fa4efe")
    protected UML2OperationTemplateParameter(Parameter elt) {
        this.elt = elt;
    }

    @objid ("f0c89734-17df-443d-90e3-d99c1720d92b")
    public static final class MdaTypes {
        @objid ("5f5b2dbb-3dc8-495d-a39b-a770a5483229")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1ef69035-ba41-4c58-92e3-5a974e6d525c")
        private static Stereotype MDAASSOCDEP;

        @objid ("c0ada7e6-48c2-49ac-a6e0-42019fe51a60")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("50d5ad2d-7926-4291-9bcf-cfdeb789840c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "07ba6521-5d0d-11df-a996-001302895b2b");
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
