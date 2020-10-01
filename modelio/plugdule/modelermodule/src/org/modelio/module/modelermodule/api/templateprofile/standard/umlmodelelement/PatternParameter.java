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
    @objid ("c5dfc5ec-6dbc-484a-a8a4-972863f98214")
    public static final String STEREOTYPE_NAME = "PatternParameter";

    @objid ("aacbf7b6-400d-4653-aab9-54e52c2d9446")
    public static final String PATTERNPARAMETER_LABEL_TAGTYPE = "PatternParameter.Label";

    @objid ("89472dca-dd94-49ed-8801-c1c6adbde29f")
    public static final String PATTERNPARAMETER_NAME_TAGTYPE = "PatternParameter.Name";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("bdd66051-abe5-4397-8370-3d2e9f13782f")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link PatternParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << PatternParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("bce19a6b-3bd9-433c-9fba-f86eba60cc81")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PatternParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << PatternParameter >> then instantiate a {@link PatternParameter} proxy.
     * 
     * @return a {@link PatternParameter} proxy on the created {@link UmlModelElement}.
     */
    @objid ("cc7a2e7e-d6a4-4fa0-a4cb-f08549a52377")
    public static PatternParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PatternParameter.STEREOTYPE_NAME);
        return PatternParameter.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link PatternParameter} proxy from a {@link UmlModelElement} stereotyped << PatternParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link PatternParameter} proxy or <i>null</i>.
     */
    @objid ("4ad24d9a-a5aa-4c0c-b8d4-a2de6d6ad8dd")
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
    @objid ("6bba4d84-2bf0-41fe-8547-74678781c9b6")
    public static PatternParameter safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (PatternParameter.canInstantiate(obj))
        	return new PatternParameter(obj);
        else
        	throw new IllegalArgumentException("PatternParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("97e3bf34-7bc4-4905-b42e-e8c0640ccdb5")
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
    @objid ("706aeb79-2870-4dc4-998e-11bfd0c261d8")
    public UmlModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'PatternParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b54fae24-b01f-4f9c-bbe3-e4e0c077bc6b")
    public String getPatternParameterLabel() {
        return this.elt.getTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_LABEL_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'PatternParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("a3b6c61c-59d0-4121-8e3e-e896b0085d3e")
    public String getPatternParameterName() {
        return this.elt.getTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_NAME_TAGTYPE_ELT);
    }

    @objid ("6fb19d67-6c38-47ec-bb16-776a6a6eefd6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'PatternParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e94b221c-8e1d-46b6-a8b4-83324ce36409")
    public void setPatternParameterLabel(String value) {
        this.elt.putTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_LABEL_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'PatternParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("40cad761-444d-42ed-8f44-4d35be4ff331")
    public void setPatternParameterName(String value) {
        this.elt.putTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_NAME_TAGTYPE_ELT, value);
    }

    @objid ("7613f709-92be-4dfc-9842-26155dc10d44")
    protected PatternParameter(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("95543ba9-bbac-4691-835b-eb384142a7bc")
    public static final class MdaTypes {
        @objid ("9727d0ae-5923-401c-9f0e-b861bc8890ad")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7a6c0ea4-49a2-4371-b6b4-bf97dccc1e6e")
        public static TagType PATTERNPARAMETER_NAME_TAGTYPE_ELT;

        @objid ("a49ef2f8-7a9b-4994-aaae-d6e24f98d9be")
        public static TagType PATTERNPARAMETER_LABEL_TAGTYPE_ELT;

        @objid ("51290ee0-abe8-4ee3-a5c9-7a7fa1586f88")
        private static Stereotype MDAASSOCDEP;

        @objid ("ebf68ef6-373c-47ac-b851-fe89d9b6de64")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("aae4c0bf-c37d-4acb-ae7b-cdb133533e4f")
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
