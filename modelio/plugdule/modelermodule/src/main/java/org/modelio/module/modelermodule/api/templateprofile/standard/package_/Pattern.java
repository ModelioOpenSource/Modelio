/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
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
    @objid ("29eab4d2-1d39-4bff-8a6d-07e0f61c1444")
    public static final String STEREOTYPE_NAME = "Pattern";

    @objid ("32c8977f-3814-451c-8be4-909929005ca9")
    public static final String TEMPLATE_CATEGORIES_TAGTYPE = "Template.Categories";

    @objid ("f1d82230-4c97-410f-8d17-86b3192c27f3")
    public static final String TEMPLATE_FILE_TAGTYPE = "Template.File";

    @objid ("b23be86b-cfde-45d0-b3e3-59a05565befa")
    public static final String TEMPLATE_IMAGE_TAGTYPE = "Template.Image";

    @objid ("994d2aa6-455b-43b5-b1f7-3e73b678c309")
    public static final String TEMPLATE_PARAMETERS_TAGTYPE = "Template.Parameters";

    @objid ("dc6d153e-c49b-4073-af46-45f47ff25186")
    public static final String TEMPLATE_STRINGPARAMETERS_TAGTYPE = "Template.StringParameters";

    @objid ("9556d8d6-ce20-4062-94e5-893e02c78041")
    public static final String TEMPLATE_VERSION_TAGTYPE = "Template.Version";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("91692791-a0b7-4abf-9244-e42d429494dc")
    protected final Package elt;

    /**
     * Tells whether a {@link Pattern proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << Pattern >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f60070ee-bfa5-4625-b403-0c467ed9e17e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Pattern.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << Pattern >> then instantiate a {@link Pattern} proxy.
     * 
     * @return a {@link Pattern} proxy on the created {@link Package}.
     */
    @objid ("4ae9af8c-e89c-49ac-a251-58fe467cc717")
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
    @objid ("3b4ee26a-8143-4dd1-986a-b1a428a6cb0f")
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
    @objid ("3c2623c4-42bf-4c7e-9c66-a192999adeb7")
    public static Pattern safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Pattern.canInstantiate(obj))
        	return new Pattern(obj);
        else
        	throw new IllegalArgumentException("Pattern: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0fd23efd-a0c5-4778-8adf-df8045570eb0")
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
    @objid ("a9702f4f-77d6-4762-afec-7681c61033b5")
    public Package getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'Template.Categories'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("6e21ca74-7590-4559-96b9-2f473f4f9362")
    public List<String> getTemplateCategories() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_CATEGORIES_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.File'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5489ba06-b12a-471d-ae08-6ce7e8584dcd")
    public String getTemplateFile() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_FILE_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.Image'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f86cdd13-614a-4941-aa6e-f30bd085d79c")
    public String getTemplateImage() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_IMAGE_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'Template.Parameters'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("459d7ccd-0172-4129-a51e-69efa94f6473")
    public List<String> getTemplateParameters() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_PARAMETERS_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'Template.StringParameters'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("c78a281f-d398-494a-9011-cb7ffd66dd65")
    public List<String> getTemplateStringParameters() {
        return this.elt.getTagValues(Pattern.MdaTypes.TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'Template.Version'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("1c55aa84-2bee-4515-92dd-142a580fb01b")
    public String getTemplateVersion() {
        return this.elt.getTagValue(Pattern.MdaTypes.TEMPLATE_VERSION_TAGTYPE_ELT);
    }

    @objid ("73012c2c-e7a3-4a91-b61d-d64917eae1c9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'Template.Categories'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d2933d07-8c20-4a61-b0d3-fe054c1161df")
    public void setTemplateCategories(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_CATEGORIES_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'Template.File'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("2b76c0f2-ba66-4c97-8e42-85626d317765")
    public void setTemplateFile(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_FILE_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'Template.Image'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("28e9da2c-e7d5-4493-ae02-fa0faa8d97e8")
    public void setTemplateImage(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_IMAGE_TAGTYPE_ELT, value);
    }

    /**
     * Setter for List<String> property 'Template.Parameters'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("9f83a7ad-4732-4689-947e-f714694538be")
    public void setTemplateParameters(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_PARAMETERS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for List<String> property 'Template.StringParameters'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("2d6ed789-8e81-423e-8b07-4ec9c80fcdca")
    public void setTemplateStringParameters(List<String> values) {
        this.elt.putTagValues(Pattern.MdaTypes.TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'Template.Version'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("4cacad52-76f5-4604-b02b-a937cdc38a11")
    public void setTemplateVersion(String value) {
        this.elt.putTagValue(Pattern.MdaTypes.TEMPLATE_VERSION_TAGTYPE_ELT, value);
    }

    @objid ("8883b8ea-bf01-49c2-8ffe-5b5c9a6c5b70")
    protected Pattern(Package elt) {
        this.elt = elt;
    }

    @objid ("54db8a75-1b63-4aac-9476-5a7732417e7a")
    public static final class MdaTypes {
        @objid ("4a4e3384-f48b-4a4e-819e-08bdacc889c7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("989d6289-a863-4d09-ad4e-5ce2391aec3a")
        public static TagType TEMPLATE_VERSION_TAGTYPE_ELT;

        @objid ("8a703436-6541-41ed-b44d-1c09d8d02257")
        public static TagType TEMPLATE_IMAGE_TAGTYPE_ELT;

        @objid ("a724b6a0-1518-4156-8d18-f623102f5337")
        public static TagType TEMPLATE_CATEGORIES_TAGTYPE_ELT;

        @objid ("f5d74a2a-2f8d-43a0-b103-440f5db2172f")
        public static TagType TEMPLATE_FILE_TAGTYPE_ELT;

        @objid ("d96f6541-021f-4101-bc4d-2b8a51703ee7")
        public static TagType TEMPLATE_STRINGPARAMETERS_TAGTYPE_ELT;

        @objid ("024be30d-97a5-4662-b0f4-26d052f3ed64")
        public static TagType TEMPLATE_PARAMETERS_TAGTYPE_ELT;

        @objid ("4eedfb06-d4df-4db7-8e87-2be55f6cd20a")
        private static Stereotype MDAASSOCDEP;

        @objid ("8e9e09ac-6fe3-49fc-af28-62e123137746")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f43c0f41-7c12-4e9e-baf2-3c0e90a13fb1")
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
