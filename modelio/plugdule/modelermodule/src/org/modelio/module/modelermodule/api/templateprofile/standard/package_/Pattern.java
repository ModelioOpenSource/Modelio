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
    @objid ("a150a752-6e5d-4e08-b5c1-c09cb80a4fe2")
    public static final String STEREOTYPE_NAME = "Pattern";

    @objid ("56099036-3394-4478-82d6-64aba5698cc7")
    public static final String TEMPLATE_CATEGORIES_TAGTYPE = "Template.Categories";

    @objid ("b1c75a89-f1b7-4210-9af4-1fe1371dd0c8")
    public static final String TEMPLATE_FILE_TAGTYPE = "Template.File";

    @objid ("d2c4a130-0330-4c65-9e4c-9e226dccdd2a")
    public static final String TEMPLATE_IMAGE_TAGTYPE = "Template.Image";

    @objid ("93615695-71ae-4ff1-9681-e3c17f6e16ba")
    public static final String TEMPLATE_PARAMETERS_TAGTYPE = "Template.Parameters";

    @objid ("a9830093-ee1e-4905-b54c-5669d1cbcd06")
    public static final String TEMPLATE_STRINGPARAMETERS_TAGTYPE = "Template.StringParameters";

    @objid ("ef558fb1-b90f-4bb4-b254-011d044cb1c4")
    public static final String TEMPLATE_VERSION_TAGTYPE = "Template.Version";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("1b7308f3-8f98-4d85-8882-883bd22bb7c9")
    protected final Package elt;

    /**
     * Tells whether a {@link Pattern proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << Pattern >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("580902b8-fe1b-42f6-ba40-3691bd589af7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Pattern.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << Pattern >> then instantiate a {@link Pattern} proxy.
     * 
     * @return a {@link Pattern} proxy on the created {@link Package}.
     */
    @objid ("9b65f829-80d2-4aab-83b7-9d7458b5368a")
    public static Pattern create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Pattern.STEREOTYPE_NAME);
        return Pattern.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Pattern} proxy from a {@link Package} stereotyped << Pattern >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Pattern} proxy or <i>null</i>.
     */
    @objid ("ddacf83e-9216-4fee-b443-e7f18cb24f7a")
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
    @objid ("a8184a8a-11cc-4c4a-ab68-e99fc65f274e")
    public static Pattern safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Pattern.canInstantiate(obj))
        	return new Pattern(obj);
        else
        	throw new IllegalArgumentException("Pattern: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("81f507fd-0fb4-4ae6-966c-d9d5ce34af21")
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
    @objid ("d1902890-ca54-41c7-b5f3-7dc58bba52a7")
    public Package getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'Template.Categories'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("698ee78d-46ab-42f6-955a-e9eaf3f4d21e")
    public List<String> getTemplateCategories() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_CATEGORIES_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.File'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7b30a07f-f872-4689-b881-59e4cb59dc34")
    public String getTemplateFile() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_FILE_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.Image'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("784a9ad1-6a39-403f-9881-bc99ebd21b37")
    public String getTemplateImage() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_IMAGE_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'Template.Parameters'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("5a7dabbf-8622-494e-a338-48cbb31cb11f")
    public List<String> getTemplateParameters() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_PARAMETERS_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'Template.StringParameters'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("5071471e-fef7-4598-8f45-03f8132aa287")
    public List<String> getTemplateStringParameters() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.Version'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6d879455-0b08-4b2b-a0ba-2c273f757e9f")
    public String getTemplateVersion() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_VERSION_TAGTYPE_ELT);
    }

    @objid ("9d1fd0ec-33b0-414e-804f-ddc6a58f4755")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'Template.Categories'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("1eb76ffa-6f86-43e6-b6df-1fb7a1c6962d")
    public void setTemplateCategories(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_CATEGORIES_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'Template.File'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("41e63082-2e07-47c4-87dc-f2790719334a")
    public void setTemplateFile(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_FILE_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'Template.Image'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("dcb88e65-01d3-41fd-ad49-ecc4e6c0137f")
    public void setTemplateImage(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_IMAGE_TAGTYPE_ELT, value);
    }

    /**
     * Setter for List<String> property 'Template.Parameters'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("fd2a0e27-2a10-40cf-bf5f-d3b899e4917f")
    public void setTemplateParameters(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_PARAMETERS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for List<String> property 'Template.StringParameters'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("15c72913-ab8d-49cb-abe8-1628856b026b")
    public void setTemplateStringParameters(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'Template.Version'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("2116305e-e18a-4049-ba59-050dd2c5267a")
    public void setTemplateVersion(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_VERSION_TAGTYPE_ELT, value);
    }

    @objid ("16ab68a6-65a9-4566-ba97-8c3929c79cfa")
    protected Pattern(Package elt) {
        this.elt = elt;
    }

    @objid ("54db8a75-1b63-4aac-9476-5a7732417e7a")
    public static final class MdaTypes {
        @objid ("d1a39cbd-bc54-4954-8559-de7f54e3e76e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("465fc7cb-aa19-40f2-bf32-10a495f30dc0")
        public static TagType TEMPLATE_VERSION_TAGTYPE_ELT;

        @objid ("c722dab5-93ee-424e-a535-d5359e6ef862")
        public static TagType TEMPLATE_IMAGE_TAGTYPE_ELT;

        @objid ("07b74427-fed8-4eac-a21f-3ca6f4239185")
        public static TagType TEMPLATE_CATEGORIES_TAGTYPE_ELT;

        @objid ("2b6b89e5-4126-4a1a-aa33-ab3d44a34654")
        public static TagType TEMPLATE_FILE_TAGTYPE_ELT;

        @objid ("0fd9e20f-f724-4bd8-b8cc-654fbd741ab0")
        public static TagType TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT;

        @objid ("1953dfe8-358f-49fc-a6d8-842221187f0a")
        public static TagType TEMPLATE_PARAMETERS_TAGTYPE_ELT;

        @objid ("fbf6974f-a4ac-439d-af64-602fa072d883")
        private static Stereotype MDAASSOCDEP;

        @objid ("cc0cbfc9-6875-4277-9c19-e182165d21d0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3801a0e1-5ae4-4574-b24e-5f3ed1959305")
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
