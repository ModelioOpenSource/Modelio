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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement;

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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UmlModelElement} with << PatternParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5e418c31-b192-4b0e-978e-0c376b0d9327")
public class PatternParameter {
    @objid ("3598afb0-ab06-4943-9ab5-a6c869c4e52e")
    public static final String STEREOTYPE_NAME = "PatternParameter";

    @objid ("a3d1e969-85de-4df1-ae0a-0488a7d1724c")
    public static final String PATTERNPARAMETER_LABEL_TAGTYPE = "PatternParameter.Label";

    @objid ("34c3bc68-4b6b-44be-a030-5bfc5d5ba62a")
    public static final String PATTERNPARAMETER_NAME_TAGTYPE = "PatternParameter.Name";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("fd137c74-73bd-4e30-8615-47d25c5d2b78")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link PatternParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << PatternParameter >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d8003f99-a3ff-479a-aba2-62243ca3fd33")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PatternParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << PatternParameter >> then instantiate a {@link PatternParameter} proxy.
     * 
     * @return a {@link PatternParameter} proxy on the created {@link UmlModelElement}.
     */
    @objid ("9446577f-4795-4a25-9a4d-46faa51317e5")
    public static PatternParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PatternParameter.STEREOTYPE_NAME);
        return PatternParameter.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link PatternParameter} proxy from a {@link UmlModelElement} stereotyped << PatternParameter >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link PatternParameter} proxy or <i>null</i>.
     */
    @objid ("b05a9d16-d57f-45ea-b97d-b56a728b6ca5")
    public static PatternParameter instantiate(UmlModelElement obj) {
        return PatternParameter.canInstantiate(obj) ? new PatternParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PatternParameter} proxy from a {@link UmlModelElement} stereotyped << PatternParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UmlModelElement}
     * @return a {@link PatternParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("26175ac3-a5d8-40f3-928f-6aa2b6f91f71")
    public static PatternParameter safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (PatternParameter.canInstantiate(obj))
        	return new PatternParameter(obj);
        else
        	throw new IllegalArgumentException("PatternParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("10c2ce80-fca8-4887-8b06-a2d736df9ab1")
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
        PatternParameter other = (PatternParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UmlModelElement}. 
     * @return the UmlModelElement represented by this proxy, never null.
     */
    @objid ("d373eae9-3b46-4ad5-9fd0-9ffe02f5018d")
    public UmlModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'PatternParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("1ca8b2f4-8bae-4b12-b5f5-f077980f9671")
    public String getPatternParameterLabel() {
        return this.elt.getTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_LABEL_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'PatternParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("03155026-5f7f-4b55-9ff4-aa3eb3001dcb")
    public String getPatternParameterName() {
        return this.elt.getTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_NAME_TAGTYPE_ELT);
    }

    @objid ("58c27ac8-a5ff-4efa-add7-e23898e45ae3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'PatternParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("cdd572cc-f1bd-4a2a-b1a6-4830ddd3fb22")
    public void setPatternParameterLabel(String value) {
        this.elt.putTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_LABEL_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'PatternParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7e5ec96e-9b78-4582-b7a3-0cb90c4bf110")
    public void setPatternParameterName(String value) {
        this.elt.putTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_NAME_TAGTYPE_ELT, value);
    }

    @objid ("8074265c-1d21-4031-9f04-edda3d653733")
    protected PatternParameter(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("95543ba9-bbac-4691-835b-eb384142a7bc")
    public static final class MdaTypes {
        @objid ("19828621-cae7-45a4-a25b-5dbb7adcf449")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a9740d54-138c-467b-849b-8418e3bbc34d")
        public static TagType PATTERNPARAMETER_NAME_TAGTYPE_ELT;

        @objid ("32cb074b-166f-45c8-bab2-bdc3462abf9c")
        public static TagType PATTERNPARAMETER_LABEL_TAGTYPE_ELT;

        @objid ("93c830e2-b90a-4af4-9412-16e4dd482074")
        private static Stereotype MDAASSOCDEP;

        @objid ("1459a360-6356-405c-be42-824a3f261a79")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("339fb5a4-0787-4cbd-aa80-e46af802cf59")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3c4678f8-f169-11df-ae59-0014224f9977");
            PATTERNPARAMETER_NAME_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "f2db899a-f169-11df-ae59-0014224f9977");
            PATTERNPARAMETER_LABEL_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "f739b2fd-f169-11df-ae59-0014224f9977");
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
