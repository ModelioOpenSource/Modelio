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
 * Proxy class to handle a {@link Artifact} with << file >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e6570b85-f019-476d-af0a-f5b65c28d70c")
public class File {
    @objid ("7f9ad613-c20f-416e-bf2b-07de3baf3d37")
    public static final String STEREOTYPE_NAME = "file";

    @objid ("c78ccf1e-a306-40b1-8fa1-76ea27074033")
    public static final String AUTHOR_TAGTYPE = "author";

    @objid ("2b3171cf-e55b-424f-899d-73acd9c01901")
    public static final String DATE_TAGTYPE = "date";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("ad0e2b5e-350c-4973-990f-30cd4de1b894")
    protected final Artifact elt;

    /**
     * Tells whether a {@link File proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << file >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0525fa4f-7531-4038-b1f2-119614d08fd1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, File.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << file >> then instantiate a {@link File} proxy.
     * 
     * @return a {@link File} proxy on the created {@link Artifact}.
     */
    @objid ("6e782951-51a7-4e34-bee1-9e573a476440")
    public static File create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, File.STEREOTYPE_NAME);
        return File.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link File} proxy from a {@link Artifact} stereotyped << file >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link File} proxy or <i>null</i>.
     */
    @objid ("26b55882-e37f-456c-9a3d-405436a95455")
    public static File instantiate(Artifact obj) {
        return File.canInstantiate(obj) ? new File(obj) : null;
    }

    /**
     * Tries to instantiate a {@link File} proxy from a {@link Artifact} stereotyped << file >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link File} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f941b474-f934-40c0-9e19-a73f193b20cf")
    public static File safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (File.canInstantiate(obj))
        	return new File(obj);
        else
        	throw new IllegalArgumentException("File: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2af8a488-ffda-434b-aec9-26a54e364ac5")
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
        File other = (File) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ffe6dea8-f4cf-4cc7-90b6-a8496dc34adc")
    public String getAuthor() {
        return this.elt.getTagValue(File.MdaTypes.AUTHOR_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6c2034f0-9302-4f23-9cf6-26c1079f4f0c")
    public String getDate() {
        return this.elt.getTagValue(File.MdaTypes.DATE_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("1d36db21-193f-429b-8833-bb97e007f0d1")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("c201b4b0-cc6d-45a6-8af8-042f842d0073")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d6ec1c2e-d37b-40a2-80ef-804f200c0502")
    public void setAuthor(String value) {
        this.elt.putTagValue(File.MdaTypes.AUTHOR_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ef3024c0-a410-45dd-9772-2b28f583acca")
    public void setDate(String value) {
        this.elt.putTagValue(File.MdaTypes.DATE_TAGTYPE_ELT, value);
    }

    @objid ("d6182101-a1b2-4215-b7be-dbcdc785c49f")
    protected File(Artifact elt) {
        this.elt = elt;
    }

    @objid ("7570829c-8959-497c-80cf-9a9640b7fb25")
    public static final class MdaTypes {
        @objid ("43c7176a-0dfe-44ef-929c-c8327e63ebf0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fa3f154a-b71e-4122-bb53-fbe54dc2b4c7")
        public static TagType AUTHOR_TAGTYPE_ELT;

        @objid ("fc5fc23a-86e8-44ef-89ad-464b6f9f4192")
        public static TagType DATE_TAGTYPE_ELT;

        @objid ("7e9f9b69-b946-4da5-a310-a7176c24509d")
        private static Stereotype MDAASSOCDEP;

        @objid ("f3e11632-d557-4e86-bcba-df705877909e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6b6c96f4-b687-4bec-82c2-dbb2c334eb83")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0226fd5c-caf5-4cb4-b25c-06e493b37b2d");
            AUTHOR_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "58d3ae88-5b5e-44ba-9bb7-0b6ed000fbb9");
            DATE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "fe23d1eb-75d1-48f5-abab-2248ad1d0dbf");
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
