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
package org.modelio.module.modelermodule.api.default_.standard.enumerationliteral;

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
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link EnumerationLiteral} metaclass.
 * <p>Description:
 * <br/><i>MMStandardEnumerationLiteral</i></p>
 */
@objid ("27985be4-3c2b-471e-891b-0b85372133e3")
public class MMStandardEnumerationLiteral {
    @objid ("b67f04d2-38d9-4b47-b1f5-a027cfe0b7df")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link EnumerationLiteral} represented by this proxy, never null.
     */
    @objid ("536f64bd-3549-48ad-a7df-ff22633a9180")
    protected final EnumerationLiteral elt;

    /**
     * Tells whether a {@link MMStandardEnumerationLiteral proxy} can be instantiated from a {@link MObject} checking it is a {@link EnumerationLiteral}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("589fcf74-d69d-4d76-b92e-7846fb7bb125")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof EnumerationLiteral);
    }

    /**
     * Tries to instantiate a {@link MMStandardEnumerationLiteral} proxy from a {@link EnumerationLiteral} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a EnumerationLiteral
     * @return a {@link MMStandardEnumerationLiteral} proxy or <i>null</i>.
     */
    @objid ("91b636eb-88ab-4705-8e40-d1e11595c9a8")
    public static MMStandardEnumerationLiteral instantiate(EnumerationLiteral obj) {
        return MMStandardEnumerationLiteral.canInstantiate(obj) ? new MMStandardEnumerationLiteral(obj) : null;
    }

    @objid ("21abdd8c-1fb0-4eae-821e-ed57ecfd11c3")
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
        MMStandardEnumerationLiteral other = (MMStandardEnumerationLiteral) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link EnumerationLiteral}. 
     * @return the EnumerationLiteral represented by this proxy, never null.
     */
    @objid ("d6279e2d-0377-4de5-844a-031ff9f55ba5")
    public EnumerationLiteral getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("54b7d9ce-0584-4efb-8748-bb3b77e87261")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardEnumerationLiteral.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("ff4c0989-a662-4919-97e8-a6f473e472d2")
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
    @objid ("0101f220-560e-4ae7-950a-d20733c66d76")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardEnumerationLiteral.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("d1b692b2-dfd7-4c82-b688-0074f91013fc")
    protected  MMStandardEnumerationLiteral(EnumerationLiteral elt) {
        this.elt = elt;
    }

    @objid ("6f5965c2-fbdf-4859-b5bd-d5e55216cde7")
    public static final class MdaTypes {
        @objid ("9464bae5-c64e-4fee-8b7e-5bed578df40b")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("3fe946dc-f01a-40d8-afec-a603f7e7f016")
        private static Stereotype MDAASSOCDEP;

        @objid ("a6ecc193-5fb5-4f13-9391-e57a00af1fb0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("12b2fa60-9fb5-41f2-b165-5378036e6b90")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "fb4e837f-19a1-4f43-b240-5efd7db90b11");
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
