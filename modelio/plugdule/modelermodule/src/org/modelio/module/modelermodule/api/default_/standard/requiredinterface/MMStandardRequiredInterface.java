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
package org.modelio.module.modelermodule.api.default_.standard.requiredinterface;

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
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link RequiredInterface} metaclass.
 * <p>Description:
 * <br/><i>MMStandardRequiredInterface</i></p>
 */
@objid ("b70a28e8-d216-4c66-b01e-c7a32379eb1d")
public class MMStandardRequiredInterface {
    @objid ("528cab1a-5516-4a44-baf6-1d795f2e5a97")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link RequiredInterface} represented by this proxy, never null.
     */
    @objid ("4bc3d17c-592d-469d-8e55-e2eb08cb623e")
    protected final RequiredInterface elt;

    /**
     * Tells whether a {@link MMStandardRequiredInterface proxy} can be instantiated from a {@link MObject} checking it is a {@link RequiredInterface}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("65b26693-8cdb-4cc3-a362-3a8a92762285")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof RequiredInterface);
    }

    /**
     * Tries to instantiate a {@link MMStandardRequiredInterface} proxy from a {@link RequiredInterface} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a RequiredInterface
     * @return a {@link MMStandardRequiredInterface} proxy or <i>null</i>.
     */
    @objid ("014407ae-a43d-43ea-b595-214eb3dae61a")
    public static MMStandardRequiredInterface instantiate(RequiredInterface obj) {
        return MMStandardRequiredInterface.canInstantiate(obj) ? new MMStandardRequiredInterface(obj) : null;
    }

    @objid ("78103469-dd4a-4229-90ae-627e64881c45")
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
        MMStandardRequiredInterface other = (MMStandardRequiredInterface) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link RequiredInterface}. 
     * @return the RequiredInterface represented by this proxy, never null.
     */
    @objid ("84ab72be-23f1-4184-b2f3-8552166e57cf")
    public RequiredInterface getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("2ce718c1-d3a0-4399-b025-c5036c33d3c1")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardRequiredInterface.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("5b6875ea-01d8-41d8-9ffa-bf809bb077dc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("11ee5598-e784-4800-8b9f-9bae117f81bf")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardRequiredInterface.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("63e546d6-44f3-4167-bc94-dd92d73b7a1c")
    protected MMStandardRequiredInterface(RequiredInterface elt) {
        this.elt = elt;
    }

    @objid ("48b065bc-b369-4cb3-81bc-d4c196c2395e")
    public static final class MdaTypes {
        @objid ("543bf540-4ecc-4cad-bf32-980b0e6f10d1")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("4500e78f-f98a-49e8-82ea-72116ef26893")
        private static Stereotype MDAASSOCDEP;

        @objid ("93d30f7d-893b-4e54-ab9a-e6a9e575d8a8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("10473fb2-9a16-47fa-bc87-3cbfbb7f2182")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "d7dfcad5-d8a0-4dfd-aa6e-a691909dc3f4");
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
