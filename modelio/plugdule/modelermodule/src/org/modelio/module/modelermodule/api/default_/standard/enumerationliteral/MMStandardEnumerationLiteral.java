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
    @objid ("2dbf4332-0681-48b7-8491-764bd91e9133")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link EnumerationLiteral} represented by this proxy, never null.
     */
    @objid ("2248f06b-a99d-421e-bc8d-d40e8e28f830")
    protected final EnumerationLiteral elt;

    /**
     * Tells whether a {@link MMStandardEnumerationLiteral proxy} can be instantiated from a {@link MObject} checking it is a {@link EnumerationLiteral}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("563e6d1f-ae54-40dd-a340-5926ba815bc0")
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
    @objid ("aed521d7-d254-461f-a2cd-fae53c9ebf11")
    public static MMStandardEnumerationLiteral instantiate(EnumerationLiteral obj) {
        return MMStandardEnumerationLiteral.canInstantiate(obj) ? new MMStandardEnumerationLiteral(obj) : null;
    }

    @objid ("55300895-f78d-40b0-a5d8-9f6b01defcfe")
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
    @objid ("17e7c3ec-07a9-475e-9ba8-ebe7d3feee85")
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
    @objid ("8c361642-49bf-422b-b2f5-f2c87911a6f0")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardEnumerationLiteral.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("c3be3400-654f-4a78-8475-d48897ebd831")
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
    @objid ("158aa62b-7477-4904-9407-0ab0c545aac1")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardEnumerationLiteral.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("cd15344f-26b8-4493-b415-ae74e3b8e3af")
    protected MMStandardEnumerationLiteral(EnumerationLiteral elt) {
        this.elt = elt;
    }

    @objid ("6f5965c2-fbdf-4859-b5bd-d5e55216cde7")
    public static final class MdaTypes {
        @objid ("a01981fd-717b-43a0-9261-e7191dfa4850")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("1814820e-d971-4fcc-b4ad-574f1420b097")
        private static Stereotype MDAASSOCDEP;

        @objid ("5eca8a49-51ab-400a-9d9b-209cd936f439")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("960d7996-82b3-4804-aebf-1fd02db34dd5")
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
