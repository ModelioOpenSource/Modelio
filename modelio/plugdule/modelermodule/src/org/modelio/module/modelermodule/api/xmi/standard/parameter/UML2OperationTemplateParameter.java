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
    @objid ("73a5a882-6bf0-4e26-96d8-185dbec1e746")
    public static final String STEREOTYPE_NAME = "UML2OperationTemplateParameter";

    /**
     * The underlying {@link Parameter} represented by this proxy, never null.
     */
    @objid ("ed1a65cf-40ec-4f6b-ba43-84d1f25f10c5")
    protected final Parameter elt;

    /**
     * Tells whether a {@link UML2OperationTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link Parameter} stereotyped << UML2OperationTemplateParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("55ef2bb4-5486-40b2-abf2-c53b8b2c3763")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Parameter) && ((Parameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2OperationTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Parameter} stereotyped << UML2OperationTemplateParameter >> then instantiate a {@link UML2OperationTemplateParameter} proxy.
     * 
     * @return a {@link UML2OperationTemplateParameter} proxy on the created {@link Parameter}.
     */
    @objid ("cb688129-a91f-4f28-84c8-b1a7d25fa27e")
    public static UML2OperationTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Parameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2OperationTemplateParameter.STEREOTYPE_NAME);
        return UML2OperationTemplateParameter.instantiate((Parameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2OperationTemplateParameter} proxy from a {@link Parameter} stereotyped << UML2OperationTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Parameter
     * @return a {@link UML2OperationTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("1f53da16-3fa2-467b-98d8-2efe2b29ec4f")
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
    @objid ("36dd3d3a-2c95-4dbd-a2c7-dab69c6cf7f5")
    public static UML2OperationTemplateParameter safeInstantiate(Parameter obj) throws IllegalArgumentException {
        if (UML2OperationTemplateParameter.canInstantiate(obj))
        	return new UML2OperationTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2OperationTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a992ebb0-b5c1-4dfe-a477-2b76b19c554b")
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
    @objid ("7ff98d94-88bf-4281-8cd5-a39c5eb6dd06")
    public Parameter getElement() {
        return this.elt;
    }

    @objid ("4ffa4998-2710-4036-a5f5-10857877c4ad")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5e7e0bb9-7263-4235-8880-86394c96d99b")
    protected UML2OperationTemplateParameter(Parameter elt) {
        this.elt = elt;
    }

    @objid ("f0c89734-17df-443d-90e3-d99c1720d92b")
    public static final class MdaTypes {
        @objid ("a38d235b-7502-4394-bed6-b719eaa6801b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("274af908-7586-4fc8-b1c5-2d45d14afc5f")
        private static Stereotype MDAASSOCDEP;

        @objid ("8722a715-8ad7-44e1-9741-5a9bea796010")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("65da5ae9-907d-4672-9ba5-72796cbfdc25")
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
