/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.artifact;

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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Artifact} with << url >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("74934370-1154-4850-a9ee-5d3c634680ac")
public class Url {
    @objid ("a8c5390e-e36c-4af0-a83f-f9c6e96e64e2")
    public static final String STEREOTYPE_NAME = "url";

    @objid ("3d584031-d314-41f4-a285-e2e2c669a89b")
    public static final String AUTHOR_TAGTYPE = "author";

    @objid ("85111adf-ae04-475b-a63b-5baec56f26d3")
    public static final String DATE_TAGTYPE = "date";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("5d9a6228-783e-425e-94de-b0f47004ca9d")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Url proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << url >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("7658b0cd-84ca-416b-bb35-c0c4eaa74299")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Url.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << url >> then instantiate a {@link Url} proxy.
     * 
     * @return a {@link Url} proxy on the created {@link Artifact}.
     */
    @objid ("7d54245a-3107-464f-a703-04bf969e0d44")
    public static Url create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Url.STEREOTYPE_NAME);
        return Url.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Url} proxy from a {@link Artifact} stereotyped << url >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Url} proxy or <i>null</i>.
     */
    @objid ("f4a4e2e2-d2d8-408f-ab07-3d8d88f72432")
    public static Url instantiate(Artifact obj) {
        return Url.canInstantiate(obj) ? new Url(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Url} proxy from a {@link Artifact} stereotyped << url >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Url} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fc9ff08a-55d0-4235-a69b-b0a092036a54")
    public static Url safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Url.canInstantiate(obj))
        	return new Url(obj);
        else
        	throw new IllegalArgumentException("Url: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f461ffd9-6939-49b1-ba18-9a36dd3f49e9")
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
        Url other = (Url) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7d72262d-1120-4b38-95e3-a83b6d101269")
    public String getAuthor() {
        return this.elt.getTagValue(Url.MdaTypes.AUTHOR_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8888e6b6-8bf1-4399-8e87-205bf80d97c7")
    public String getDate() {
        return this.elt.getTagValue(Url.MdaTypes.DATE_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("65d71a6d-8a71-42b8-9dfb-2bce1ee25d84")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("d99adff2-1e40-4316-b9e9-36d4e72c1ef8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("0c1b1586-5fc3-48b0-b643-314f284780eb")
    public void setAuthor(String value) {
        this.elt.putTagValue(Url.MdaTypes.AUTHOR_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("84ec43d1-8666-4ef0-927c-b0aa1d0b42e7")
    public void setDate(String value) {
        this.elt.putTagValue(Url.MdaTypes.DATE_TAGTYPE_ELT, value);
    }

    @objid ("225c4178-74ca-47de-bac5-598418544dc9")
    protected Url(Artifact elt) {
        this.elt = elt;
    }

    @objid ("1612b182-f258-483a-a67a-a48cfaece602")
    public static final class MdaTypes {
        @objid ("a22f05a5-73ff-4cbe-9db1-c35c2d628246")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bbdc4e21-b527-4d9c-bcc4-02009c72436e")
        public static TagType AUTHOR_TAGTYPE_ELT;

        @objid ("91521464-b122-4c8b-8faa-121daae33d70")
        public static TagType DATE_TAGTYPE_ELT;

        @objid ("288f5b65-3e50-4261-b179-d7e95c56ecac")
        private static Stereotype MDAASSOCDEP;

        @objid ("0fef3e1a-1322-4916-8047-ee22160d143d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3ff3f5c3-2acd-41be-9cce-a9e2978d0839")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c7ba7024-eff5-4039-a4d4-c9ddcd0a3aed");
            AUTHOR_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7269505c-f5f2-4330-926b-19049c8f3c92");
            DATE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "2a483a85-be6f-4684-8ece-b6cec8ba7620");
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
