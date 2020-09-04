/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.artifact;

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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Artifact} with << ModelComponentArchive >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("4223c940-dbcd-4fdd-8e4e-dae096aaeeb3")
public class ModelComponentArchive {
    @objid ("e3531232-e052-4186-8f6a-0587a94f9705")
    public static final String STEREOTYPE_NAME = "ModelComponentArchive";

    @objid ("0eb37941-d82f-442f-9296-75435ebf3bfb")
    public static final String MODELCOMPONENTCONTRIBUTORS_TAGTYPE = "ModelComponentContributors";

    @objid ("52cb09db-9173-4ded-9fe4-adc77ec42ab0")
    public static final String MODELCOMPONENTFILES_TAGTYPE = "ModelComponentFiles";

    @objid ("0073a884-054a-4f93-b6db-775238ef0eaa")
    public static final String MODELCOMPONENTPROVIDER_TAGTYPE = "ModelComponentProvider";

    @objid ("0a9d0365-9e30-47d4-ae25-6a628221bf4c")
    public static final String MODELCOMPONENTVERSION_TAGTYPE = "ModelComponentVersion";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("f02d42a9-637e-43ea-97db-4fc82b5b2b31")
    protected final Artifact elt;

    /**
     * Tells whether a {@link ModelComponentArchive proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << ModelComponentArchive >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b9edb2df-7c3e-4a63-b5fe-9511490a02c1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponentArchive.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << ModelComponentArchive >> then instantiate a {@link ModelComponentArchive} proxy.
     * 
     * @return a {@link ModelComponentArchive} proxy on the created {@link Artifact}.
     */
    @objid ("9475ea0f-38e9-4fa6-990a-490bc4a8df1d")
    public static ModelComponentArchive create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponentArchive.STEREOTYPE_NAME);
        return ModelComponentArchive.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponentArchive} proxy from a {@link Artifact} stereotyped << ModelComponentArchive >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link ModelComponentArchive} proxy or <i>null</i>.
     */
    @objid ("489c8675-ee2a-4c82-8185-fbc02ba3cc1f")
    public static ModelComponentArchive instantiate(Artifact obj) {
        return ModelComponentArchive.canInstantiate(obj) ? new ModelComponentArchive(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ModelComponentArchive} proxy from a {@link Artifact} stereotyped << ModelComponentArchive >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link ModelComponentArchive} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b95d3d8b-3922-4c5c-93ef-bd431aa6b85f")
    public static ModelComponentArchive safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (ModelComponentArchive.canInstantiate(obj))
        	return new ModelComponentArchive(obj);
        else
        	throw new IllegalArgumentException("ModelComponentArchive: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("81eba14d-448f-4ff4-97a9-5d115efb97a7")
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
        ModelComponentArchive other = (ModelComponentArchive) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("934a8b55-b455-4d83-b576-bc6d234a58ee")
    public Artifact getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'ModelComponentContributors'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("f520b58d-df23-4957-9423-974ab1d765b9")
    public List<String> getModelComponentContributors() {
        return this.elt.getTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'ModelComponentFiles'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("e1858939-799c-40b3-80ba-86aa229bcc82")
    public List<String> getModelComponentFiles() {
        return this.elt.getTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTFILES_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'ModelComponentProvider'
     * <p>Property description:
     * <br/><i>The provider of the Model Component. 
     * For Model Component provided by a module please indicate "Module Name_of_the_module"</i></p>
     */
    @objid ("bb1c44ac-725a-4b04-9b5d-40fb5b879631")
    public String getModelComponentProvider() {
        return this.elt.getTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTPROVIDER_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'ModelComponentVersion'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7308195e-6a62-4459-abc5-9b7da6b94a32")
    public String getModelComponentVersion() {
        return this.elt.getTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTVERSION_TAGTYPE_ELT);
    }

    @objid ("8ddf8a84-1983-44e0-a911-356dc8859676")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'ModelComponentContributors'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d350a431-0e3c-4f29-a294-6c757ea9b2c7")
    public void setModelComponentContributors(List<String> values) {
        this.elt.putTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for List<String> property 'ModelComponentFiles'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("c5cc16f2-8377-4a16-9520-5561b47d94ad")
    public void setModelComponentFiles(List<String> values) {
        this.elt.putTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTFILES_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'ModelComponentProvider'
     * <p>Property description:
     * <br/><i>The provider of the Model Component. 
     * For Model Component provided by a module please indicate "Module Name_of_the_module"</i></p>
     */
    @objid ("ff3ffb1c-430e-4119-a28f-3d896e48e3ee")
    public void setModelComponentProvider(String value) {
        this.elt.putTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTPROVIDER_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'ModelComponentVersion'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("08483142-0a4d-4f1a-87ab-8e1891e9dcd7")
    public void setModelComponentVersion(String value) {
        this.elt.putTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTVERSION_TAGTYPE_ELT, value);
    }

    @objid ("a5d7d029-a753-47cb-b764-31790c14eac8")
    protected ModelComponentArchive(Artifact elt) {
        this.elt = elt;
    }

    @objid ("5f247bd4-cf59-4bee-b138-b19f5cea78f9")
    public static final class MdaTypes {
        @objid ("16a0870f-e88d-4589-99d9-b8f6cc776b3b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("abf13217-2776-489a-91f9-053ac3d16ec7")
        public static TagType MODELCOMPONENTFILES_TAGTYPE_ELT;

        @objid ("130e4cdb-6a0b-4f9b-827f-1781b492b00a")
        public static TagType MODELCOMPONENTVERSION_TAGTYPE_ELT;

        @objid ("917c0658-6ff9-466a-a51b-f1de47e783a1")
        public static TagType MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT;

        @objid ("cef76278-a95f-4b3c-95ed-03cd12bf9a65")
        public static TagType MODELCOMPONENTPROVIDER_TAGTYPE_ELT;

        @objid ("4fed6a01-1fd5-48a3-9ed1-d15df5ff01f5")
        private static Stereotype MDAASSOCDEP;

        @objid ("e3268a66-2d7d-4b16-b73b-882b38af46a5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5d52fa66-b79c-425d-b658-a551b41e003d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00140d80-0000-0110-0000-000000000000");
            MODELCOMPONENTFILES_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00140d80-0000-0112-0000-000000000000");
            MODELCOMPONENTVERSION_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00140d80-0000-020c-0000-000000000000");
            MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "9834df4e-84a3-4ec8-b216-fd2adda71578");
            MODELCOMPONENTPROVIDER_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "e4efc54e-225e-469e-903e-8df1b1e6f8cd");
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
