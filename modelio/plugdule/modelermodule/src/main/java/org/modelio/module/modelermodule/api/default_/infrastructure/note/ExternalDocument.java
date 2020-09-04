/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.note;

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
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Note} with << ExternalDocument >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7b670613-5bc2-4326-b6e3-1f8c5e4fe52e")
public class ExternalDocument {
    @objid ("ec2c25c3-80c2-4884-bc3c-369cb480f3d3")
    public static final String STEREOTYPE_NAME = "ExternalDocument";

    @objid ("14fc89c5-1eac-4576-bc41-61138ced0ed7")
    public static final String LINKLABEL_TAGTYPE = "LinkLabel";

    @objid ("f07155cf-0e5c-44b5-aaf0-9bea00925909")
    public static final String ISLINK_TAGTYPE = "isLink";

    /**
     * The underlying {@link Note} represented by this proxy, never null.
     */
    @objid ("a94f59c3-ab3c-487e-a6f4-6bde819c9842")
    protected final Note elt;

    /**
     * Tells whether a {@link ExternalDocument proxy} can be instantiated from a {@link MObject} checking it is a {@link Note} stereotyped << ExternalDocument >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1ef5a21d-b48a-4136-920c-b517b53b32f0")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Note) && ((Note) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ExternalDocument.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Note} stereotyped << ExternalDocument >> then instantiate a {@link ExternalDocument} proxy.
     * 
     * @return a {@link ExternalDocument} proxy on the created {@link Note}.
     */
    @objid ("c1543af4-f133-4047-85e2-54161400c7ee")
    public static ExternalDocument create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Note");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ExternalDocument.STEREOTYPE_NAME);
        return ExternalDocument.instantiate((Note)e);
    }

    /**
     * Tries to instantiate a {@link ExternalDocument} proxy from a {@link Note} stereotyped << ExternalDocument >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Note
     * @return a {@link ExternalDocument} proxy or <i>null</i>.
     */
    @objid ("7d5ca205-46e1-4c12-aaee-6a141298de8c")
    public static ExternalDocument instantiate(Note obj) {
        return ExternalDocument.canInstantiate(obj) ? new ExternalDocument(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ExternalDocument} proxy from a {@link Note} stereotyped << ExternalDocument >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Note}
     * @return a {@link ExternalDocument} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("db0eec5a-7b15-4031-ac64-e4d3e450b1d6")
    public static ExternalDocument safeInstantiate(Note obj) throws IllegalArgumentException {
        if (ExternalDocument.canInstantiate(obj))
        	return new ExternalDocument(obj);
        else
        	throw new IllegalArgumentException("ExternalDocument: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7dac0ed8-6919-4e33-b6d6-1a001b5930fc")
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
        ExternalDocument other = (ExternalDocument) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Note}. 
     * @return the Note represented by this proxy, never null.
     */
    @objid ("0b023520-7bdd-4786-80d9-705f5691e359")
    public Note getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'LinkLabel'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("71dc106d-6fb5-4ca5-9343-e2a524579089")
    public String getLinkLabel() {
        return this.elt.getTagValue(ExternalDocument.MdaTypes.LINKLABEL_TAGTYPE_ELT);
    }

    @objid ("b941667b-d6b3-42e7-826b-21a6b162264d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'isLink'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("35099247-632e-41ec-a463-633973dd16cd")
    public boolean isIsLink() {
        return this.elt.isTagged(ExternalDocument.MdaTypes.ISLINK_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'isLink'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6d19a3bb-f835-4859-99f7-fc6565511b4b")
    public void setIsLink(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(ExternalDocument.MdaTypes.ISLINK_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(ExternalDocument.MdaTypes.ISLINK_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'LinkLabel'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f3777d4a-deb6-4012-843b-a45dd5f8ab51")
    public void setLinkLabel(String value) {
        this.elt.putTagValue(ExternalDocument.MdaTypes.LINKLABEL_TAGTYPE_ELT, value);
    }

    @objid ("5786064c-24d5-43f4-b239-f89d3087771e")
    protected ExternalDocument(Note elt) {
        this.elt = elt;
    }

    @objid ("8daed5a0-e2f1-4a7f-accc-c1b2e6b7509c")
    public static final class MdaTypes {
        @objid ("6de218bd-e858-4427-b544-90ae41ec0149")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("957f6272-c04c-419c-9c67-1d041974bf5a")
        public static TagType ISLINK_TAGTYPE_ELT;

        @objid ("793f27ef-2743-451a-94b3-9a4025118ada")
        public static TagType LINKLABEL_TAGTYPE_ELT;

        @objid ("a2ef2f00-51a9-4843-986e-1917788f891a")
        private static Stereotype MDAASSOCDEP;

        @objid ("31642aee-7b73-4912-89b7-02dd2ba43773")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c804ec0e-ca54-4501-a462-6b83b5788adb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c2e23680-96f0-11de-a322-001fe2c988b8");
            ISLINK_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "0da68860-96f1-11de-a322-001fe2c988b8");
            LINKLABEL_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "4027c488-2b5c-11df-9426-00137279a5d1");
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
