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
package org.modelio.module.modelermodule.api.templateprofile.standard.package_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << Pattern >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f262c9ee-11ab-45a0-82eb-98e618810a63")
public class Pattern {
    @objid ("43c37a88-b938-46c8-b9e6-d45b15930a9d")
    public static final String STEREOTYPE_NAME = "Pattern";

    @objid ("0cff3195-9379-4748-94f0-df4b25cb2464")
    public static final String TEMPLATE_CATEGORIES_TAGTYPE = "Template.Categories";

    @objid ("25a13d57-77bd-422c-ad07-077f4b7d76b3")
    public static final String TEMPLATE_FILE_TAGTYPE = "Template.File";

    @objid ("c115a6ac-fbfa-4bf1-a6c1-335936aa940d")
    public static final String TEMPLATE_IMAGE_TAGTYPE = "Template.Image";

    @objid ("6f48440e-5691-4d2b-9376-e62b9e6fe1f6")
    public static final String TEMPLATE_PARAMETERS_TAGTYPE = "Template.Parameters";

    @objid ("e3cc4095-4c29-4540-936e-849caf88cbf4")
    public static final String TEMPLATE_STRINGPARAMETERS_TAGTYPE = "Template.StringParameters";

    @objid ("af225da6-0ba7-49ae-accf-068620da2663")
    public static final String TEMPLATE_VERSION_TAGTYPE = "Template.Version";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("c88d5193-21a0-4f3f-af80-9759a01521e7")
    protected final Package elt;

    /**
     * Tells whether a {@link Pattern proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << Pattern >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ea63004e-bec9-4d33-bac6-6edeffe00ca5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Pattern.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << Pattern >> then instantiate a {@link Pattern} proxy.
     * 
     * @return a {@link Pattern} proxy on the created {@link Package}.
     */
    @objid ("77b7fcf1-3da0-434b-a6de-1bea4da00781")
    public static Pattern create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Pattern.STEREOTYPE_NAME);
        return Pattern.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Pattern} proxy from a {@link Package} stereotyped << Pattern >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Pattern} proxy or <i>null</i>.
     */
    @objid ("95fe3e05-79d6-4e41-a9e3-0902d4180f18")
    public static Pattern instantiate(Package obj) {
        return Pattern.canInstantiate(obj) ? new Pattern(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Pattern} proxy from a {@link Package} stereotyped << Pattern >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Pattern} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("255e29ff-da72-4456-bdda-b3de891cd1c7")
    public static Pattern safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Pattern.canInstantiate(obj))
        	return new Pattern(obj);
        else
        	throw new IllegalArgumentException("Pattern: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d78e2b93-9693-482c-a4ca-248c8de4f8c6")
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
        Pattern other = (Pattern) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("d8fc42c0-adb2-4b1c-848d-7e002efb92d4")
    public Package getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'Template.Categories'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("c13a93af-4f05-48e8-aa7c-51510bad3f14")
    public List<String> getTemplateCategories() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_CATEGORIES_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.File'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("270d946b-5cd8-4419-a56a-37c28af5d9cc")
    public String getTemplateFile() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_FILE_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.Image'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("822f65e1-22af-4064-add0-f0e839f3e305")
    public String getTemplateImage() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_IMAGE_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'Template.Parameters'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("e3afc846-cbda-47b4-8d3e-4bae0586f5f0")
    public List<String> getTemplateParameters() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_PARAMETERS_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'Template.StringParameters'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("0a47330f-9cf3-4a0d-bf20-5f7ca0e11ed7")
    public List<String> getTemplateStringParameters() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.Version'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("a7051ab6-2695-4f60-8351-cfee57af27cc")
    public String getTemplateVersion() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_VERSION_TAGTYPE_ELT);
    }

    @objid ("d335d571-5fc5-405d-83fa-a86e828a7bf3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for List<String> property 'Template.Categories'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("37ea8e9d-eac0-4204-9b70-7862c415dd2a")
    public void setTemplateCategories(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_CATEGORIES_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'Template.File'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("1de7f5a5-bc2f-4387-9a00-7c8872a4d2f8")
    public void setTemplateFile(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_FILE_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'Template.Image'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5b67e6a3-0d71-4aaf-b149-9e81e3ae552c")
    public void setTemplateImage(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_IMAGE_TAGTYPE_ELT, value);
    }

    /**
     * Setter for List<String> property 'Template.Parameters'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("c60c6715-aaa9-4093-aa39-10a5377b1276")
    public void setTemplateParameters(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_PARAMETERS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for List<String> property 'Template.StringParameters'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ad5cb2f4-ea5d-4f30-8021-183e614e975f")
    public void setTemplateStringParameters(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'Template.Version'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f5fe2201-0166-4e85-86e7-ede8c7adc45f")
    public void setTemplateVersion(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_VERSION_TAGTYPE_ELT, value);
    }

    @objid ("26e71b0a-c8c6-4b24-a24f-5f29105eebe1")
    protected  Pattern(Package elt) {
        this.elt = elt;
    }

    @objid ("54db8a75-1b63-4aac-9476-5a7732417e7a")
    public static final class MdaTypes {
        @objid ("1c00a2d2-7d68-4508-9bf5-9541d80cb833")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9399e9fb-d255-4fec-9c26-2562c7b9f9c4")
        public static TagType TEMPLATE_VERSION_TAGTYPE_ELT;

        @objid ("986b39af-c1e0-41c4-bfb4-83b50357ada5")
        public static TagType TEMPLATE_IMAGE_TAGTYPE_ELT;

        @objid ("025c4699-d68e-4dbc-8e09-27eca9a49927")
        public static TagType TEMPLATE_CATEGORIES_TAGTYPE_ELT;

        @objid ("967206c7-3400-4ff2-8160-d26368390b8e")
        public static TagType TEMPLATE_FILE_TAGTYPE_ELT;

        @objid ("1e66e157-24b3-4679-b417-5cc183e9ae5a")
        public static TagType TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT;

        @objid ("d78ced52-8139-4f8c-b1a1-bd48710268fc")
        public static TagType TEMPLATE_PARAMETERS_TAGTYPE_ELT;

        @objid ("fb9e74ab-d019-463a-8eeb-fbcd6c5883b4")
        private static Stereotype MDAASSOCDEP;

        @objid ("143d5d47-c434-4519-bc16-5eac376583ab")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("12d5cea2-5792-4d9c-87c8-d5cd27f41157")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "641a5778-89a9-11df-9978-0014224f9977");
            TEMPLATE_VERSION_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "dc10adee-8a8b-11df-9e1a-0014224f9977");
            TEMPLATE_IMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "e0523ecf-8a8b-11df-9e1a-0014224f9977");
            TEMPLATE_CATEGORIES_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "f0e3af4a-8a8b-11df-9e1a-0014224f9977");
            TEMPLATE_FILE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "56ba0e27-90e3-11df-8b98-0014224f9977");
            TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "d0bbc251-94a3-11df-b1e5-0014224f9977");
            TEMPLATE_PARAMETERS_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "120a832b-6bf6-4b03-900f-f60e86f19363");
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
