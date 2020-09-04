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
    @objid ("675ffb93-6b92-4344-85b3-959be2717d46")
    public static final String STEREOTYPE_NAME = "ModelComponentArchive";

    @objid ("7052abeb-0ffd-4d1e-996b-5af812dd565a")
    public static final String MODELCOMPONENTCONTRIBUTORS_TAGTYPE = "ModelComponentContributors";

    @objid ("e5dbc092-6f4e-43c2-8598-6fc7016d0c62")
    public static final String MODELCOMPONENTFILES_TAGTYPE = "ModelComponentFiles";

    @objid ("db8e72b9-e33a-42a6-988c-a603b2c82386")
    public static final String MODELCOMPONENTPROVIDER_TAGTYPE = "ModelComponentProvider";

    @objid ("77450eef-d48d-468f-b264-4897e3aec1aa")
    public static final String MODELCOMPONENTVERSION_TAGTYPE = "ModelComponentVersion";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("1b2226ce-d67d-433e-a14a-e3452ceb5d1d")
    protected final Artifact elt;

    /**
     * Tells whether a {@link ModelComponentArchive proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << ModelComponentArchive >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6beaee7c-6f79-4464-97dc-1e768853667b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponentArchive.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << ModelComponentArchive >> then instantiate a {@link ModelComponentArchive} proxy.
     * 
     * @return a {@link ModelComponentArchive} proxy on the created {@link Artifact}.
     */
    @objid ("970b730a-5817-4505-8d81-b10223bbbfab")
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
    @objid ("1c157a26-51c0-4f7e-a50f-87e588c3fcf6")
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
    @objid ("2232457b-dfa7-4a2a-9f89-cf72a6b19e88")
    public static ModelComponentArchive safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (ModelComponentArchive.canInstantiate(obj))
        	return new ModelComponentArchive(obj);
        else
        	throw new IllegalArgumentException("ModelComponentArchive: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9b99365c-66b5-4537-ad70-433b614c6a2c")
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
    @objid ("00a6cf33-516c-4046-97ef-43c8b642fb56")
    public Artifact getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'ModelComponentContributors'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("1c233b86-a1a1-416b-ba95-81a94fc872cf")
    public List<String> getModelComponentContributors() {
        return this.elt.getTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT);
    }

    /**
     * Getter for List<String> property 'ModelComponentFiles'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("e444c433-3bfe-4301-a6ba-0af88348d7e4")
    public List<String> getModelComponentFiles() {
        return this.elt.getTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTFILES_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'ModelComponentProvider'
     * <p>Property description:
     * <br/><i>The provider of the Model Component. 
     * For Model Component provided by a module please indicate "Module Name_of_the_module"</i></p>
     */
    @objid ("73acaa35-8a8a-474d-b7e1-2d0f82ba65d3")
    public String getModelComponentProvider() {
        return this.elt.getTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTPROVIDER_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'ModelComponentVersion'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7008c630-5e1f-4e2f-b679-0476e6108f1d")
    public String getModelComponentVersion() {
        return this.elt.getTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTVERSION_TAGTYPE_ELT);
    }

    @objid ("318a258f-29fe-4ec3-bbff-0bda67be5506")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'ModelComponentContributors'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5ad6fafd-f3a0-4211-9dd3-1eb65dd044a5")
    public void setModelComponentContributors(List<String> values) {
        this.elt.putTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT, values);
    }

    /**
     * Setter for List<String> property 'ModelComponentFiles'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f8f33b74-0a3e-4805-be75-ac6d28164723")
    public void setModelComponentFiles(List<String> values) {
        this.elt.putTagValues(ModelComponentArchive.MdaTypes.MODELCOMPONENTFILES_TAGTYPE_ELT, values);
    }

    /**
     * Setter for string property 'ModelComponentProvider'
     * <p>Property description:
     * <br/><i>The provider of the Model Component. 
     * For Model Component provided by a module please indicate "Module Name_of_the_module"</i></p>
     */
    @objid ("536c21b0-a95f-4f4a-a67e-99605aafee0b")
    public void setModelComponentProvider(String value) {
        this.elt.putTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTPROVIDER_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'ModelComponentVersion'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("4c959933-16d0-4fb5-939c-fcf4a3620595")
    public void setModelComponentVersion(String value) {
        this.elt.putTagValue(ModelComponentArchive.MdaTypes.MODELCOMPONENTVERSION_TAGTYPE_ELT, value);
    }

    @objid ("4e173293-3944-461a-ad18-6f67750cf540")
    protected ModelComponentArchive(Artifact elt) {
        this.elt = elt;
    }

    @objid ("5f247bd4-cf59-4bee-b138-b19f5cea78f9")
    public static final class MdaTypes {
        @objid ("9a72b3c8-17a7-4ba4-a299-b107175b8ec2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4ffdde36-165d-4fbb-92b3-108c51a12ec5")
        public static TagType MODELCOMPONENTFILES_TAGTYPE_ELT;

        @objid ("9145a962-883d-409d-a3a5-a9958829d24c")
        public static TagType MODELCOMPONENTVERSION_TAGTYPE_ELT;

        @objid ("a602e264-3165-4816-9ebf-a16287a9e1e2")
        public static TagType MODELCOMPONENTCONTRIBUTORS_TAGTYPE_ELT;

        @objid ("e4c35554-db03-4e32-953a-2b8014c4e636")
        public static TagType MODELCOMPONENTPROVIDER_TAGTYPE_ELT;

        @objid ("2494f1a3-c004-432d-9cd7-a566faafeca7")
        private static Stereotype MDAASSOCDEP;

        @objid ("a0c4a9d0-647d-4967-83ab-b7ee89d1b992")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("26ac0639-b480-43c8-83ea-a0b208ee7156")
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
