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
    @objid ("36c58b04-1ab7-44cd-978c-11b854ea9fa1")
    public static final String STEREOTYPE_NAME = "ModelComponentArchive";

    @objid ("2f52c0f9-705d-412a-a940-7c63476edd43")
    public static final String MODELCOMPONENTCONTRIBUTORS_TAGTYPE = "ModelComponentContributors";

    @objid ("98f0fe9e-dc40-433d-b17d-0faed1628a3f")
    public static final String MODELCOMPONENTFILES_TAGTYPE = "ModelComponentFiles";

    @objid ("3456e062-951b-4cda-bf37-19ee0436eb5f")
    public static final String MODELCOMPONENTPROVIDER_TAGTYPE = "ModelComponentProvider";

    @objid ("8184a2c0-32e7-41c5-935e-110a856fd4eb")
    public static final String MODELCOMPONENTVERSION_TAGTYPE = "ModelComponentVersion";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("9cd7f512-723b-4909-b2c5-ba901be73af7")
    protected final Artifact elt;

    /**
     * Tells whether a {@link ModelComponentArchive proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << ModelComponentArchive >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8685de4f-412c-4933-84f1-47aff4bf522a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponentArchive.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << ModelComponentArchive >> then instantiate a {@link ModelComponentArchive} proxy.
     * 
     * @return a {@link ModelComponentArchive} proxy on the created {@link Artifact}.
     */
    @objid ("8c71f9dc-db04-49fe-9441-1921e4c60f3f")
    public static ModelComponentArchive create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
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
    @objid ("e01a9d4b-49ee-454a-b6a3-fc727dc9ec6f")
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
    @objid ("ae5ee85d-d840-4a74-b8e9-4bb708538e32")
    public static ModelComponentArchive safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (ModelComponentArchive.canInstantiate(obj))
        	return new ModelComponentArchive(obj);
        else
        	throw new IllegalArgumentException("ModelComponentArchive: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e849736c-9f23-41ea-964d-4f17d5fa8c2f")
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
    @objid ("7ca1ea11-b29b-4956-a888-f2f60a525fd2")
    public Artifact getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'ModelComponentContributors'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("c69c767c-7e94-4d4f-9ed9-ac68e336bf55")
    public List<String> getModelComponentContributors() {
        return this.elt.getTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'ModelComponentFiles'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("fd1215e0-ab2a-44fe-bd5a-6ee95703831f")
    public List<String> getModelComponentFiles() {
        return this.elt.getTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTFILES_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'ModelComponentProvider'
     * <p>Property description:
     * <br/><i>The provider of the Model Component. 
     * For Model Component provided by a module please indicate "Module Name_of_the_module"</i></p>
     */
    @objid ("6ebd535a-aeb8-4db3-b681-c5d52640ef10")
    public String getModelComponentProvider() {
        return this.elt.getTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTPROVIDER_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'ModelComponentVersion'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5f8ecd9e-baff-44cb-9375-98d83fb367e2")
    public String getModelComponentVersion() {
        return this.elt.getTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTVERSION_TAGTYPE_ELT);
    }

    @objid ("df1e27c1-106c-41d5-b9df-b0d80d31337f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for List<String> property 'ModelComponentContributors'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("c163a415-1f83-4b5a-b9a4-97a0f940fa60")
    public void setModelComponentContributors(List<String> values) {
        this.elt.putTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for List<String> property 'ModelComponentFiles'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("36c71872-bb7b-4230-b89e-ae6beff5f9e2")
    public void setModelComponentFiles(List<String> values) {
        this.elt.putTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTFILES_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'ModelComponentProvider'
     * <p>Property description:
     * <br/><i>The provider of the Model Component. 
     * For Model Component provided by a module please indicate "Module Name_of_the_module"</i></p>
     */
    @objid ("4b6cbee4-4d9e-4396-ade6-f65b35d7d5e6")
    public void setModelComponentProvider(String value) {
        this.elt.putTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTPROVIDER_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'ModelComponentVersion'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("2cc4b7af-eceb-4959-a2dc-62dd0c4842f5")
    public void setModelComponentVersion(String value) {
        this.elt.putTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTVERSION_TAGTYPE_ELT, value);
    }

    @objid ("653be466-02f6-4581-92fc-04e5acfe6ec5")
    protected  ModelComponentArchive(Artifact elt) {
        this.elt = elt;
    }

    @objid ("5f247bd4-cf59-4bee-b138-b19f5cea78f9")
    public static final class MdaTypes {
        @objid ("a404089c-0d56-4eba-a36f-9ef15da9a0de")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5104e547-0161-4eaf-9946-298220fc54e8")
        public static TagType MODELCOMPONENTFILES_TAGTYPE_ELT;

        @objid ("a877e8ee-13bf-4faf-aaed-c91d4fdd5659")
        public static TagType MODELCOMPONENTVERSION_TAGTYPE_ELT;

        @objid ("b78aa6b1-543a-45ff-a251-75247a558a26")
        public static TagType MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT;

        @objid ("eccaf0b7-e774-49ed-b39e-9b16905596bd")
        public static TagType MODELCOMPONENTPROVIDER_TAGTYPE_ELT;

        @objid ("3edcf787-c733-4eff-a7df-ea1fcce74af2")
        private static Stereotype MDAASSOCDEP;

        @objid ("6217253a-7bac-4fb3-b643-3ce85bc6427d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("15a4ec21-03cc-4101-aab4-73d354f4660d")
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
