/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
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
    @objid ("b718176a-2dd4-4ab2-b376-2a8f22f6112f")
    public static final String STEREOTYPE_NAME = "PatternParameter";

    @objid ("9bb743c2-0241-4ed1-a2e3-921680f01a89")
    public static final String PATTERNPARAMETER_LABEL_TAGTYPE = "PatternParameter.Label";

    @objid ("d2ffa196-376f-4172-afe8-3c247982aeb1")
    public static final String PATTERNPARAMETER_NAME_TAGTYPE = "PatternParameter.Name";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("9410fc3e-da81-4e38-82a9-7849f28fdc78")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link PatternParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << PatternParameter >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("7f1abb2c-3aa5-421a-ae8b-d34e092bd774")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PatternParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << PatternParameter >> then instantiate a {@link PatternParameter} proxy.
     * 
     * @return a {@link PatternParameter} proxy on the created {@link UmlModelElement}.
     */
    @objid ("4b8ebf29-5ed3-43fe-9142-b46f48eb9f6e")
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
    @objid ("8a45b965-fa1f-47ab-8aa1-5b41e6e4ddf6")
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
    @objid ("d8f8900c-c4ad-49da-a35d-402de170f191")
    public static PatternParameter safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (PatternParameter.canInstantiate(obj))
        	return new PatternParameter(obj);
        else
        	throw new IllegalArgumentException("PatternParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1b51c120-8880-45e7-8e7f-87fc3e8bdbae")
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
    @objid ("365970c3-741b-4a01-81d2-2b6139dfab82")
    public UmlModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'PatternParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("cf87d3fa-73a0-4533-b21a-4a235c0108e3")
    public String getPatternParameterLabel() {
        return this.elt.getTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_LABEL_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'PatternParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("c9430e32-5923-4873-9196-70620b195552")
    public String getPatternParameterName() {
        return this.elt.getTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_NAME_TAGTYPE_ELT);
    }

    @objid ("0eead8f9-3a70-44d7-b792-546ec1f34547")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'PatternParameter.Label'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d4b0c4e2-e321-4f78-a1e9-4d16f89911bc")
    public void setPatternParameterLabel(String value) {
        this.elt.putTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_LABEL_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'PatternParameter.Name'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("befe44db-6e0d-4858-b30c-2f5641828a92")
    public void setPatternParameterName(String value) {
        this.elt.putTagValue(PatternParameter.MdaTypes.PATTERNPARAMETER_NAME_TAGTYPE_ELT, value);
    }

    @objid ("fb60a038-7cf1-4d39-922d-7c477e05fa29")
    protected PatternParameter(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("95543ba9-bbac-4691-835b-eb384142a7bc")
    public static final class MdaTypes {
        @objid ("89cc4502-164f-4543-a990-e7f59108c8ed")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("07cb396f-6f82-4afa-a89f-d10847ff6a25")
        public static TagType PATTERNPARAMETER_NAME_TAGTYPE_ELT;

        @objid ("2aac02c7-191e-4b6b-a0e3-927ef9c9665a")
        public static TagType PATTERNPARAMETER_LABEL_TAGTYPE_ELT;

        @objid ("86e5be88-f6b2-4669-b45d-9c3f62e2bbcc")
        private static Stereotype MDAASSOCDEP;

        @objid ("f7bffc9d-fe30-40e4-8dd6-f0fd160f6d64")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cef9337f-be17-4e22-a598-003c1f860bdb")
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
