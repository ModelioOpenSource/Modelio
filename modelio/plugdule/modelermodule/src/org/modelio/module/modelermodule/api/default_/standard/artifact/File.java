/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("360be527-4dcb-4321-acbf-19d38f8b5831")
    public static final String STEREOTYPE_NAME = "file";

    @objid ("b4ba281f-9990-4b91-a503-74be71c4030f")
    public static final String AUTHOR_TAGTYPE = "author";

    @objid ("8c5e0def-f93c-4b47-b88c-74598bae6e0f")
    public static final String DATE_TAGTYPE = "date";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("f8c79a58-33a8-4d68-9275-0ee1188b741c")
    protected final Artifact elt;

    /**
     * Tells whether a {@link File proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << file >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("19c32419-86a0-40ec-b726-b1f5a65f9666")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, File.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << file >> then instantiate a {@link File} proxy.
     * 
     * @return a {@link File} proxy on the created {@link Artifact}.
     */
    @objid ("640f68e1-6547-4768-8dbc-3482841eccc2")
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
    @objid ("fdb2479c-b763-4c11-b54f-2cdf5f65819a")
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
    @objid ("f56ee5b5-34a4-4fe9-8c22-d4ccf239b4a2")
    public static File safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (File.canInstantiate(obj))
        	return new File(obj);
        else
        	throw new IllegalArgumentException("File: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("60d23e7d-e561-4a4b-b93f-8591d33222d8")
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
    @objid ("8f0edd54-cdf9-43b6-be93-570749f50d29")
    public String getAuthor() {
        return this.elt.getTagValue(File.MdaTypes.AUTHOR_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d32ecd74-9cc6-4dd4-ac0c-66639c191bcd")
    public String getDate() {
        return this.elt.getTagValue(File.MdaTypes.DATE_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("e5031563-f7ae-454f-9e39-af8e0d1408bc")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("eff59c55-43e6-4c51-a4c5-04ca7a740cfc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("04ba671d-4982-4388-b8f0-2d258043ae55")
    public void setAuthor(String value) {
        this.elt.putTagValue(File.MdaTypes.AUTHOR_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("52c1065b-055b-4408-a91d-521eef1ccc6a")
    public void setDate(String value) {
        this.elt.putTagValue(File.MdaTypes.DATE_TAGTYPE_ELT, value);
    }

    @objid ("b754f853-d5d6-421e-a9fd-08fed9a4abdb")
    protected File(Artifact elt) {
        this.elt = elt;
    }

    @objid ("7570829c-8959-497c-80cf-9a9640b7fb25")
    public static final class MdaTypes {
        @objid ("2ca272c5-60a0-4373-a1a0-99f98c773c0c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("646554da-2531-46ba-9516-19ca33c82701")
        public static TagType AUTHOR_TAGTYPE_ELT;

        @objid ("2dc67f3a-b360-4bb7-b194-90b280f8d217")
        public static TagType DATE_TAGTYPE_ELT;

        @objid ("fa5e445d-7527-4787-9034-10cc95707379")
        private static Stereotype MDAASSOCDEP;

        @objid ("7d29acad-0251-4fe7-82b6-4b3e4398deea")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b95b440a-404f-42a9-998a-7172e30f7349")
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
