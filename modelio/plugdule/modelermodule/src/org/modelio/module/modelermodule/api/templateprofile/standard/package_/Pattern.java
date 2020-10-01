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
    @objid ("e9f1bd77-18ad-4667-9067-1ec7a57ff49b")
    public static final String STEREOTYPE_NAME = "Pattern";

    @objid ("4f2bbf3e-f0dd-44b2-a980-9217dfce8a88")
    public static final String TEMPLATE_CATEGORIES_TAGTYPE = "Template.Categories";

    @objid ("284e143f-0982-4a46-aa55-94f83ddb91ed")
    public static final String TEMPLATE_FILE_TAGTYPE = "Template.File";

    @objid ("2b2fc1cb-1aff-4955-8e04-0fef9c56b448")
    public static final String TEMPLATE_IMAGE_TAGTYPE = "Template.Image";

    @objid ("7b7aad39-941c-40c8-a6be-3c9485ca7dde")
    public static final String TEMPLATE_PARAMETERS_TAGTYPE = "Template.Parameters";

    @objid ("a92b7cb9-a448-405a-9533-00ffbaee78e0")
    public static final String TEMPLATE_STRINGPARAMETERS_TAGTYPE = "Template.StringParameters";

    @objid ("ba8aff27-745f-4300-bc21-acd589a24c17")
    public static final String TEMPLATE_VERSION_TAGTYPE = "Template.Version";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("abe24f56-03ed-45dc-a766-267c9213e383")
    protected final Package elt;

    /**
     * Tells whether a {@link Pattern proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << Pattern >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a10b9b3d-1b83-42c0-a83a-3b42c6553f63")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Pattern.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << Pattern >> then instantiate a {@link Pattern} proxy.
     * 
     * @return a {@link Pattern} proxy on the created {@link Package}.
     */
    @objid ("43cadea6-ef53-49d3-b962-c9637ad5f31b")
    public static Pattern create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
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
    @objid ("6dc7a5c2-249c-41bc-8b1b-335c79a7e0c5")
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
    @objid ("87146230-016d-4b0d-a0f5-596f95f1b552")
    public static Pattern safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Pattern.canInstantiate(obj))
        	return new Pattern(obj);
        else
        	throw new IllegalArgumentException("Pattern: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2228e17c-ca56-4a90-b9c1-62ced10ca50e")
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
    @objid ("0c31a233-462e-483c-b7b8-5db9b3be0528")
    public Package getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'Template.Categories'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("53822155-c472-472e-947a-480c2b4881a0")
    public List<String> getTemplateCategories() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_CATEGORIES_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.File'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("32a69609-2951-4568-9ec7-08ef1b20b261")
    public String getTemplateFile() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_FILE_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.Image'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("fe5983ac-40b9-48f1-9763-84ce3ddfea9d")
    public String getTemplateImage() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_IMAGE_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'Template.Parameters'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("4b612163-153b-4a99-b1fb-5f27b32804b9")
    public List<String> getTemplateParameters() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_PARAMETERS_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'Template.StringParameters'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("61029679-90de-450b-9a37-14666967c805")
    public List<String> getTemplateStringParameters() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.Version'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("10559de4-5772-4c32-a07c-183d716424ed")
    public String getTemplateVersion() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_VERSION_TAGTYPE_ELT);
    }

    @objid ("ae79b527-d398-4c90-aaf6-dcfafce40236")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'Template.Categories'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b2746912-ce3d-47a6-a298-b81113ae0abf")
    public void setTemplateCategories(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_CATEGORIES_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'Template.File'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6b603bef-c9a8-45c5-8e50-2aab8bc6858c")
    public void setTemplateFile(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_FILE_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'Template.Image'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("a4807fb7-ceac-4749-a364-a7a33977316d")
    public void setTemplateImage(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_IMAGE_TAGTYPE_ELT, value);
    }

    /**
     * Setter for List<String> property 'Template.Parameters'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5f4a3b1d-2c9b-4754-9376-4948ccdedf8a")
    public void setTemplateParameters(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_PARAMETERS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for List<String> property 'Template.StringParameters'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("4aeb2d6f-bbff-4661-87a5-a7eaa8225759")
    public void setTemplateStringParameters(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'Template.Version'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("c940d8fc-7818-4d66-9edd-c54e359e7e12")
    public void setTemplateVersion(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_VERSION_TAGTYPE_ELT, value);
    }

    @objid ("786c79d3-a0c3-4d68-8598-d62525af984b")
    protected Pattern(Package elt) {
        this.elt = elt;
    }

    @objid ("54db8a75-1b63-4aac-9476-5a7732417e7a")
    public static final class MdaTypes {
        @objid ("069d5d66-8d0c-4773-b415-b0a55153ed32")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ca92edf1-371c-49b2-a4d3-1ab120268157")
        public static TagType TEMPLATE_VERSION_TAGTYPE_ELT;

        @objid ("12b19ed0-12e2-4e7d-8f14-308b271af539")
        public static TagType TEMPLATE_IMAGE_TAGTYPE_ELT;

        @objid ("4faed1ed-b82f-4d14-864d-a7f0abb2c4d4")
        public static TagType TEMPLATE_CATEGORIES_TAGTYPE_ELT;

        @objid ("ee3b4374-af61-4dc7-8078-825e22c457dd")
        public static TagType TEMPLATE_FILE_TAGTYPE_ELT;

        @objid ("2336b7e9-89fd-4c75-add1-1c55922a8020")
        public static TagType TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT;

        @objid ("a4dcd116-2ef1-4c4e-ab79-af2c59f499f9")
        public static TagType TEMPLATE_PARAMETERS_TAGTYPE_ELT;

        @objid ("a3eecbd4-51c0-48ba-80b4-6452299ec3f9")
        private static Stereotype MDAASSOCDEP;

        @objid ("cb9999ec-eee3-4a34-85a7-f9dd1f7fdd3e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("eadbec84-5226-48c5-bffe-5264b0a9f0e3")
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
