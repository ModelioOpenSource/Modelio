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
package org.modelio.module.modelermodule.api.xmi.infrastructure.modelelement;

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
@objid ("06737fde-4734-439e-9e34-e3d7d7d6b636")
public class MMInfrastructureModelElement {
    @objid ("5a7069d5-876e-4832-99de-aa53b61ae807")
    public static final String ECOREID_TAGTYPE = "EcoreId";

    @objid ("52989513-61d4-4f86-ae53-2f4b2cf904bd")
    public static final String NOTEXPORTED_TAGTYPE = "NotExported";

    @objid ("6d82f460-382f-421b-88ce-cd98708e0de9")
    public static final String XMIIMPORTED_TAGTYPE = "XMIImported";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("dd819d08-2d34-4b8b-a52b-66b327f65b41")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMInfrastructureModelElement proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3d441db4-8024-4ddc-8bb3-0846d7dbed7f")
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
    @objid ("ae390659-6bad-441b-bf56-78fceae78708")
    public static MMInfrastructureModelElement instantiate(ModelElement obj) {
        return MMInfrastructureModelElement.canInstantiate(obj) ? new MMInfrastructureModelElement(obj) : null;
    }

    @objid ("4afe0313-e364-46ec-adf0-776cde38d508")
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
     * Getter for string property 'EcoreId'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e9c9505d-bf71-4f6e-a415-f65ec973e9ab")
    public String getEcoreId() {
        return this.elt.getTagValue(MMInfrastructureModelElement.MdaTypes.ECOREID_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("d6db2c02-9fbd-4f0d-8b26-263bee6158e3")
    public ModelElement getElement() {
        return this.elt;
    }

    @objid ("1fd3ac69-be0c-4bac-9fa3-4693b9fa1856")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'NotExported'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e80a155b-813a-4296-a8a6-1225aac26dc3")
    public boolean isNotExported() {
        return this.elt.isTagged(MMInfrastructureModelElement.MdaTypes.NOTEXPORTED_TAGTYPE_ELT);
    }

    /**
     * Getter for boolean property 'XMIImported'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("a5388e80-0157-4135-9f62-dd8bd420f0d7")
    public boolean isXMIImported() {
        return this.elt.isTagged(MMInfrastructureModelElement.MdaTypes.XMIIMPORTED_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'EcoreId'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("150cb156-d340-4610-9e4e-b2e22ad0998e")
    public void setEcoreId(String value) {
        this.elt.putTagValue(MMInfrastructureModelElement.MdaTypes.ECOREID_TAGTYPE_ELT, value);
    }

    /**
     * Setter for boolean property 'NotExported'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("bee9ee36-aa3c-4d4c-8964-d00110c4163e")
    public void setNotExported(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMInfrastructureModelElement.MdaTypes.NOTEXPORTED_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMInfrastructureModelElement.MdaTypes.NOTEXPORTED_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'XMIImported'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("004b7013-259d-43cc-90f1-ca33539e95c4")
    public void setXMIImported(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMInfrastructureModelElement.MdaTypes.XMIIMPORTED_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMInfrastructureModelElement.MdaTypes.XMIIMPORTED_TAGTYPE_ELT);
    }

    @objid ("fb5526cb-7b43-4bea-9b94-f502a8da9dbc")
    protected  MMInfrastructureModelElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("5c553672-3a15-4523-b622-8c4014f6e963")
    public static final class MdaTypes {
        @objid ("7102c44a-c14d-472f-ab35-f3984106ff95")
        public static TagType XMIIMPORTED_TAGTYPE_ELT;

        @objid ("dc59db5c-1b4d-4e35-b1dd-362a7d671831")
        public static TagType NOTEXPORTED_TAGTYPE_ELT;

        @objid ("87ba151e-869f-4423-bbd7-a25cb1dbd0e1")
        public static TagType ECOREID_TAGTYPE_ELT;

        @objid ("16cc69a8-9130-46f1-8818-9034e086d014")
        private static Stereotype MDAASSOCDEP;

        @objid ("378d175b-3861-4a61-92e2-6e943bc5b0d0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("90bc4775-d4d9-48aa-8144-27369720a14d")
        public static void init(IModuleContext ctx) {
            XMIIMPORTED_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "01ec045c-0000-373f-0000-000000000000");
            NOTEXPORTED_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "1cbf4295-9bcf-11e0-8162-0027103f347c");
            ECOREID_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "46e094c2-6889-11e1-905a-0027103f347d");
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
