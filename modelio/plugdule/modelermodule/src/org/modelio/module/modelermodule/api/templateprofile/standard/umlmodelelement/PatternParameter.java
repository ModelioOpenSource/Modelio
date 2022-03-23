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
    @objid ("595433e4-5287-4d0b-9211-df9a3dd4494b")
    public static final String STEREOTYPE_NAME = "PatternParameter";

    @objid ("e15133e3-e5f3-40f9-9296-c193ae634300")
    public static final String PATTERNPARAMETER_LABEL_TAGTYPE = "PatternParameter.Label";

    @objid ("602bd689-0cce-4f1b-94dd-a4951a105241")
    public static final String PATTERNPARAMETER_NAME_TAGTYPE = "PatternParameter.Name";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("650635fc-5795-4928-a0c7-f45c0e53c0b8")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link PatternParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << PatternParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9fdfba5c-4964-4ac3-bcdf-01d0cc7d0fe1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PatternParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << PatternParameter >> then instantiate a {@link PatternParameter} proxy.
     * 
     * @return a {@link PatternParameter} proxy on the created {@link UmlModelElement}.
     */
    @objid ("eb3cc5a5-cb7c-4e43-909a-6293e664896e")
    public static PatternParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.UmlModelElement");
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
    @objid ("d97ee82c-3975-4e2e-9f7b-9375e21dc69b")
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
    @objid ("451d0d16-6739-4d0e-8240-1a1ba52a13c1")
    public static PatternParameter safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (PatternParameter.canInstantiate(obj))
        	return new PatternParameter(obj);
        else
        	throw new IllegalArgumentException("PatternParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c10af296-c9d0-424f-bfba-a73563eae788")
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
    @objid ("ce9ffc4a-b018-4272-b858-0171f84f39b0")
    public UmlModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'PatternParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("99ee2b88-1766-48bc-922a-81c4ae57b645")
    public String getPatternParameterLabel() {
        return this.elt.getTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_LABEL_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'PatternParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("3232e43a-7c11-4ee0-bd2b-af34d77f70da")
    public String getPatternParameterName() {
        return this.elt.getTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_NAME_TAGTYPE_ELT);
    }

    @objid ("1a85019e-6863-4eb6-87b2-903942db8daa")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'PatternParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("4cba3505-0b41-4a38-b901-40fb96256820")
    public void setPatternParameterLabel(String value) {
        this.elt.putTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_LABEL_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'PatternParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b94b15ef-968f-4c0b-a257-c1a5726caab4")
    public void setPatternParameterName(String value) {
        this.elt.putTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_NAME_TAGTYPE_ELT, value);
    }

    @objid ("8ae6c30a-03a7-46c4-bcd6-4f168e533601")
    protected  PatternParameter(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("95543ba9-bbac-4691-835b-eb384142a7bc")
    public static final class MdaTypes {
        @objid ("a0d20f96-f864-4a5e-ab6e-6d86f810467e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3ed86e57-40a8-4d25-93f8-464d82180bfc")
        public static TagType PATTERNPARAMETER_NAME_TAGTYPE_ELT;

        @objid ("9d067d5a-25d7-4f16-b5e0-4e23c400336c")
        public static TagType PATTERNPARAMETER_LABEL_TAGTYPE_ELT;

        @objid ("976b6ccd-cdb0-4fbe-9489-27716b5d9f41")
        private static Stereotype MDAASSOCDEP;

        @objid ("ba01f3e5-e253-4ea9-b174-9f5447068d72")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2639f627-3299-4d15-b3d8-67c68ec0661a")
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
