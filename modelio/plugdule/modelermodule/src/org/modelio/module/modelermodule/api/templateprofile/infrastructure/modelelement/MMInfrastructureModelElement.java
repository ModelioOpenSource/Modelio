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
package org.modelio.module.modelermodule.api.templateprofile.infrastructure.modelelement;

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
 * Proxy class to handle a {@link ModelElement} metaclass.
 * <p>Description:
 * <br/><i>MMInfrastructureModelElement</i></p>
 */
@objid ("44c66b89-768f-4d86-b19c-d73adf71771c")
public class MMInfrastructureModelElement {
    @objid ("1471b711-415e-4566-b731-d2e11f40b5b9")
    public static final String TEMPLATEPARAMETER_LABEL_TAGTYPE = "TemplateParameter.Label";

    @objid ("e1c77b7f-c6f0-4b68-a72b-ec81237802ba")
    public static final String TEMPLATEPARAMETER_NAME_TAGTYPE = "TemplateParameter.Name";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("5381e62b-2125-4870-883b-7f6a680c45a9")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMInfrastructureModelElement proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4ee2ef1f-be40-496e-895b-19e9f3a61466")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof ModelElement);
    }

    /**
     * Tries to instantiate a {@link MMInfrastructureModelElement} proxy from a {@link ModelElement} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link MMInfrastructureModelElement} proxy or <i>null</i>.
     */
    @objid ("4bd67667-8695-4088-8e8d-774a2ca105ee")
    public static MMInfrastructureModelElement instantiate(ModelElement obj) {
        return MMInfrastructureModelElement.canInstantiate(obj) ? new MMInfrastructureModelElement(obj) : null;
    }

    @objid ("008b9e22-382c-4784-8ae2-9c39f150ce49")
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
        MMInfrastructureModelElement other = (MMInfrastructureModelElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("652101a0-b409-4a9b-a081-c97eea1b26a5")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'TemplateParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8654bc62-97d7-435d-b411-00613299d92a")
    public String getTemplateParameterLabel() {
        return this.elt.getTagValue(MMInfrastructureModelElement.MdaTypes.TEMPLATEPARAMETER_LABEL_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'TemplateParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("9ee8deea-c862-48f6-a191-454b58a9880a")
    public String getTemplateParameterName() {
        return this.elt.getTagValue(MMInfrastructureModelElement.MdaTypes.TEMPLATEPARAMETER_NAME_TAGTYPE_ELT);
    }

    @objid ("7eb626b6-bfed-499d-9ec7-e79ad9043138")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'TemplateParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("0696fd9e-bfd8-4861-96c9-06efa2ad6669")
    public void setTemplateParameterLabel(String value) {
        this.elt.putTagValue(MMInfrastructureModelElement.MdaTypes.TEMPLATEPARAMETER_LABEL_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'TemplateParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e218529b-2ed7-4c2e-a129-e0e800b1a273")
    public void setTemplateParameterName(String value) {
        this.elt.putTagValue(MMInfrastructureModelElement.MdaTypes.TEMPLATEPARAMETER_NAME_TAGTYPE_ELT, value);
    }

    @objid ("07d01ddc-2e6e-497f-86eb-d5fc272d2045")
    protected MMInfrastructureModelElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("106438c1-03c7-4b60-adb3-0265b6d5db26")
    public static final class MdaTypes {
        @objid ("a3e9735a-c33f-4149-9488-db0c9a3f6d87")
        public static TagType TEMPLATEPARAMETER_NAME_TAGTYPE_ELT;

        @objid ("0ab5e508-33ae-4739-a954-0b7f2f14ffc2")
        public static TagType TEMPLATEPARAMETER_LABEL_TAGTYPE_ELT;

        @objid ("9c6662a5-dbf6-43b6-9741-d6223241bee6")
        private static Stereotype MDAASSOCDEP;

        @objid ("c82d848b-c3b8-47d2-9be6-618bc2ed80e3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("961e8cf9-123c-48f9-8b12-002f606cd8a7")
        public static void init(IModuleContext ctx) {
            TEMPLATEPARAMETER_NAME_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "072f1214-8a88-11df-9e1a-0014224f9977");
            TEMPLATEPARAMETER_LABEL_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "d7f8ca90-8d91-11df-98b9-0014224f9977");
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
