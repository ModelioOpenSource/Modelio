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
    @objid ("a93870d7-fddf-45d8-b8cc-df741115c472")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link RequiredInterface} represented by this proxy, never null.
     */
    @objid ("0d4a90b2-9eaa-4daf-90df-d1584c09e07e")
    protected final RequiredInterface elt;

    /**
     * Tells whether a {@link MMStandardRequiredInterface proxy} can be instantiated from a {@link MObject} checking it is a {@link RequiredInterface}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9e8d9bf7-9f11-448b-a743-305ad378de45")
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
    @objid ("f15f91b1-be54-4c4a-9e02-07183f107bdd")
    public static MMStandardRequiredInterface instantiate(RequiredInterface obj) {
        return MMStandardRequiredInterface.canInstantiate(obj) ? new MMStandardRequiredInterface(obj) : null;
    }

    @objid ("915e3b5b-bcec-4957-8422-7b7f8f9579a3")
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
    @objid ("7f5453a6-0485-48c8-b6c8-a9db73e070c9")
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
    @objid ("8a3d47bb-a2f2-421c-985a-a4efe1b9cf85")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardRequiredInterface.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("7fa16b94-94d9-4b5d-a679-cd01ba6829ac")
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
    @objid ("e86325ce-250b-4637-8824-a87f2bea33bb")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardRequiredInterface.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("94b8354b-a637-4eb9-a46f-2a5424428438")
    protected  MMStandardRequiredInterface(RequiredInterface elt) {
        this.elt = elt;
    }

    @objid ("48b065bc-b369-4cb3-81bc-d4c196c2395e")
    public static final class MdaTypes {
        @objid ("53690d17-f611-4096-a395-a399a5671744")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("5541fbcd-f3f2-4ced-b0a6-d5b0a9d32379")
        private static Stereotype MDAASSOCDEP;

        @objid ("c6e9634b-61c4-446f-82cc-65c47553e336")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5f5cd138-9332-44eb-b3ce-959751625576")
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
