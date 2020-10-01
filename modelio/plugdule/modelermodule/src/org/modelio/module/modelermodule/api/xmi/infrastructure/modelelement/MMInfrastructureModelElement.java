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
    @objid ("3755db7a-3452-4585-919f-f08eeee261c9")
    public static final String ECOREID_TAGTYPE = "EcoreId";

    @objid ("e6c2248d-a578-417e-bb81-acd9b6a21369")
    public static final String NOTEXPORTED_TAGTYPE = "NotExported";

    @objid ("220526cc-48f9-422c-9d1b-a57c7fa8ef2d")
    public static final String XMIIMPORTED_TAGTYPE = "XMIImported";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("152c9c39-7d58-4306-bb49-0bb9579b7b40")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMInfrastructureModelElement proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("759dd281-cbb4-4d09-9465-086fb2e8c379")
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
    @objid ("f5ba6472-97c8-466a-88a9-73bb664a72c3")
    public static MMInfrastructureModelElement instantiate(ModelElement obj) {
        return MMInfrastructureModelElement.canInstantiate(obj) ? new MMInfrastructureModelElement(obj) : null;
    }

    @objid ("21d40012-1fc3-4a97-a47e-06c5cec7312c")
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
    @objid ("f961c76c-15b1-45e9-8ae0-1119bdf7f93b")
    public String getEcoreId() {
        return this.elt.getTagValue(MMInfrastructureModelElement.MdaTypes.ECOREID_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("34b352f1-4bdb-4f38-a6ca-c2653d08379a")
    public ModelElement getElement() {
        return this.elt;
    }

    @objid ("ccd9efd7-f6b9-41bc-ab8a-e24c454b7e6e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'NotExported'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b7c38f53-e877-43cf-ae0a-a240665a11cb")
    public boolean isNotExported() {
        return this.elt.isTagged(MMInfrastructureModelElement.MdaTypes.NOTEXPORTED_TAGTYPE_ELT);
    }

    /**
     * Getter for boolean property 'XMIImported'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("91e1d012-f179-4a8c-990d-85e04b098a9f")
    public boolean isXMIImported() {
        return this.elt.isTagged(MMInfrastructureModelElement.MdaTypes.XMIIMPORTED_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'EcoreId'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b364bcc4-e91f-41c5-9bac-d6ddcde32220")
    public void setEcoreId(String value) {
        this.elt.putTagValue(MMInfrastructureModelElement.MdaTypes.ECOREID_TAGTYPE_ELT, value);
    }

    /**
     * Setter for boolean property 'NotExported'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("0e76518f-3d48-49a3-9146-609fa55a6b33")
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
    @objid ("2d3faec4-3e98-4ad1-8e2c-b2fdc57ac6dd")
    public void setXMIImported(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMInfrastructureModelElement.MdaTypes.XMIIMPORTED_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMInfrastructureModelElement.MdaTypes.XMIIMPORTED_TAGTYPE_ELT);
    }

    @objid ("24c4efcd-9e83-4f53-8562-65ad11560572")
    protected MMInfrastructureModelElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("5c553672-3a15-4523-b622-8c4014f6e963")
    public static final class MdaTypes {
        @objid ("37836774-91e9-4cc4-a38f-608e9636b74b")
        public static TagType XMIIMPORTED_TAGTYPE_ELT;

        @objid ("38395507-a649-489d-9cb6-4db6f8962efb")
        public static TagType NOTEXPORTED_TAGTYPE_ELT;

        @objid ("48c79b33-4df9-4111-851d-afa65c0f4f32")
        public static TagType ECOREID_TAGTYPE_ELT;

        @objid ("d8fdb948-ff76-4961-becf-9aa24eaee78a")
        private static Stereotype MDAASSOCDEP;

        @objid ("c7f41e22-2f4f-4f24-ac40-33a3fb109361")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e66c5af4-38be-4038-9016-4278cb91630f")
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
