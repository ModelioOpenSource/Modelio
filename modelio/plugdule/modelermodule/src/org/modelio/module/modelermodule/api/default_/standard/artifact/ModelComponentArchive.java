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
    @objid ("8136bd15-bf52-4b04-8b4e-dfc4be388ee5")
    public static final String STEREOTYPE_NAME = "ModelComponentArchive";

    @objid ("caf5d6a2-1298-4cba-8fb7-7a942bbe3d7e")
    public static final String MODELCOMPONENTCONTRIBUTORS_TAGTYPE = "ModelComponentContributors";

    @objid ("9aed04f4-a070-4723-9d89-65abb8fef3b2")
    public static final String MODELCOMPONENTFILES_TAGTYPE = "ModelComponentFiles";

    @objid ("848e4f1e-cac3-495c-997a-cca925249d15")
    public static final String MODELCOMPONENTPROVIDER_TAGTYPE = "ModelComponentProvider";

    @objid ("b39e6dc5-36cb-44cb-9713-0c5fb5de580c")
    public static final String MODELCOMPONENTVERSION_TAGTYPE = "ModelComponentVersion";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("bb89969f-8e1e-4615-89cc-023ff3957bd4")
    protected final Artifact elt;

    /**
     * Tells whether a {@link ModelComponentArchive proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << ModelComponentArchive >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("38d1a25c-9344-49bd-ad80-ea7597379e6b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponentArchive.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << ModelComponentArchive >> then instantiate a {@link ModelComponentArchive} proxy.
     * 
     * @return a {@link ModelComponentArchive} proxy on the created {@link Artifact}.
     */
    @objid ("1046d816-bf22-42d1-9ba5-43cac3cb88d3")
    public static ModelComponentArchive create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponentArchive.STEREOTYPE_NAME);
        return ModelComponentArchive.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponentArchive} proxy from a {@link Artifact} stereotyped << ModelComponentArchive >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link ModelComponentArchive} proxy or <i>null</i>.
     */
    @objid ("472f2e14-0838-4c40-8ad2-d6b16d3ed023")
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
    @objid ("818ba2b4-3346-47c4-bf07-e5ae5bde5e1e")
    public static ModelComponentArchive safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (ModelComponentArchive.canInstantiate(obj))
        	return new ModelComponentArchive(obj);
        else
        	throw new IllegalArgumentException("ModelComponentArchive: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f529d907-6530-441a-b7ec-3996f71e882a")
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
    @objid ("16ec5e62-ebe6-4043-add6-726cee5dfb27")
    public Artifact getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'ModelComponentContributors'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("84cec3e9-2963-4a55-a721-d09fdcb2f0b1")
    public List<String> getModelComponentContributors() {
        return this.elt.getTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'ModelComponentFiles'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("2fd358d5-3b57-4d3f-ba69-1f3bc7dd52d4")
    public List<String> getModelComponentFiles() {
        return this.elt.getTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTFILES_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'ModelComponentProvider'
     * <p>Property description:
     * <br/><i>The provider of the Model Component. 
     * For Model Component provided by a module please indicate "Module Name_of_the_module"</i></p>
     */
    @objid ("f06415ac-7617-4984-9d14-abeb398ddbd4")
    public String getModelComponentProvider() {
        return this.elt.getTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTPROVIDER_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'ModelComponentVersion'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("97c10f33-bbdd-4962-8bf4-25574fafe948")
    public String getModelComponentVersion() {
        return this.elt.getTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTVERSION_TAGTYPE_ELT);
    }

    @objid ("99bb4d57-a6b7-4d50-82d2-8fb874136fdd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'ModelComponentContributors'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("2e84b7f8-0d24-40e4-ba32-a229a071cebb")
    public void setModelComponentContributors(List<String> values) {
        this.elt.putTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for List<String> property 'ModelComponentFiles'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("67bed6ff-ec35-4cfb-bf08-ac1cc419b5ae")
    public void setModelComponentFiles(List<String> values) {
        this.elt.putTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTFILES_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'ModelComponentProvider'
     * <p>Property description:
     * <br/><i>The provider of the Model Component. 
     * For Model Component provided by a module please indicate "Module Name_of_the_module"</i></p>
     */
    @objid ("404b8016-6323-40b8-a36e-9aa79aad8523")
    public void setModelComponentProvider(String value) {
        this.elt.putTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTPROVIDER_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'ModelComponentVersion'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("60598e2f-ac3b-4a6a-b716-2ab0c4e0eab4")
    public void setModelComponentVersion(String value) {
        this.elt.putTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTVERSION_TAGTYPE_ELT, value);
    }

    @objid ("838d8a58-3782-4ac0-a1ec-10184c768039")
    protected ModelComponentArchive(Artifact elt) {
        this.elt = elt;
    }

    @objid ("5f247bd4-cf59-4bee-b138-b19f5cea78f9")
    public static final class MdaTypes {
        @objid ("4d424c1a-7189-4713-9f40-1ed1a6501698")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("15e36c19-e2e2-4370-b5bf-27d56fbca927")
        public static TagType MODELCOMPONENTFILES_TAGTYPE_ELT;

        @objid ("c42fc271-4681-4101-a24e-c4260cc2e235")
        public static TagType MODELCOMPONENTVERSION_TAGTYPE_ELT;

        @objid ("2ca9711b-9f4b-430e-8ed6-81ff105ed910")
        public static TagType MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT;

        @objid ("d0c71307-e6ae-4c3a-80cc-c49cb74e8be1")
        public static TagType MODELCOMPONENTPROVIDER_TAGTYPE_ELT;

        @objid ("6cb4be4a-74cc-41ab-940b-64ae195a8416")
        private static Stereotype MDAASSOCDEP;

        @objid ("8fbf9598-204a-4ce7-9630-8fb0a35a90d0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d85dfa97-2f2f-4dcc-b401-4fa3d292d8dc")
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
