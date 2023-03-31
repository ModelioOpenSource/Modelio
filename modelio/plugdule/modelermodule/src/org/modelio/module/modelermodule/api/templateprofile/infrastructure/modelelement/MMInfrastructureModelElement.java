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
    @objid ("c93b83bb-0cb7-4699-895f-1b0630b02fdb")
    public static final String TEMPLATEPARAMETER_LABEL_TAGTYPE = "TemplateParameter.Label";

    @objid ("ed00c567-ff42-4ad0-ac91-0419ab782c64")
    public static final String TEMPLATEPARAMETER_NAME_TAGTYPE = "TemplateParameter.Name";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("91eaeccb-a465-4bd3-98d9-801bd180d873")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMInfrastructureModelElement proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c0a70141-7f9a-4cfe-a070-67ab941788de")
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
    @objid ("2fcc6855-f731-45f3-bd33-2c54acfa9c58")
    public static MMInfrastructureModelElement instantiate(ModelElement obj) {
        return MMInfrastructureModelElement.canInstantiate(obj) ? new MMInfrastructureModelElement(obj) : null;
    }

    @objid ("0af828f4-7f5e-4f2b-9f3d-3802cc8b7d96")
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
    @objid ("e4fa9d22-1996-4d74-8872-507244a97486")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'TemplateParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("cd4c4e96-7b5c-4d19-bf8b-8c43a9e8692c")
    public String getTemplateParameterLabel() {
        return this.elt.getTagValue(MMInfrastructureModelElement.MdaTypes.TEMPLATEPARAMETER_LABEL_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'TemplateParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ee01b4ae-916f-4630-9e88-799320926519")
    public String getTemplateParameterName() {
        return this.elt.getTagValue(MMInfrastructureModelElement.MdaTypes.TEMPLATEPARAMETER_NAME_TAGTYPE_ELT);
    }

    @objid ("1fb85dca-9fb1-4512-a116-0c6114bc664d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'TemplateParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("1c5a84ca-820b-4bf4-8563-ba3cedb668e1")
    public void setTemplateParameterLabel(String value) {
        this.elt.putTagValue(MMInfrastructureModelElement.MdaTypes.TEMPLATEPARAMETER_LABEL_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'TemplateParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("713acecd-cde1-4302-bf6a-6ccfb7c022dd")
    public void setTemplateParameterName(String value) {
        this.elt.putTagValue(MMInfrastructureModelElement.MdaTypes.TEMPLATEPARAMETER_NAME_TAGTYPE_ELT, value);
    }

    @objid ("81462161-aac2-49b1-b813-e9a65b0cc0ee")
    protected  MMInfrastructureModelElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("106438c1-03c7-4b60-adb3-0265b6d5db26")
    public static final class MdaTypes {
        @objid ("6294e72c-12c7-47af-a631-faa2d20529ad")
        public static TagType TEMPLATEPARAMETER_NAME_TAGTYPE_ELT;

        @objid ("1e689428-f5fb-4ad1-a561-b5b1b171d182")
        public static TagType TEMPLATEPARAMETER_LABEL_TAGTYPE_ELT;

        @objid ("0f8e5b60-6133-4689-b0fe-a2354226cf30")
        private static Stereotype MDAASSOCDEP;

        @objid ("826e02aa-2f89-4487-92ec-d7362a2daf25")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2363835e-1294-48e1-8917-c2fb15d19344")
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
