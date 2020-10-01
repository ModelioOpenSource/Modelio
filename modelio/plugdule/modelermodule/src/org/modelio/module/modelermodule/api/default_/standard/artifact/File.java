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
    @objid ("19c1171f-de02-4702-94df-d172f4bb2677")
    public static final String STEREOTYPE_NAME = "file";

    @objid ("ec07e79b-7ed5-43a8-94aa-db991db1ef72")
    public static final String AUTHOR_TAGTYPE = "author";

    @objid ("66505a61-942c-49da-9601-1c086191a336")
    public static final String DATE_TAGTYPE = "date";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("5a8f03d8-4f96-4ca4-b9c3-aadd7145e4cc")
    protected final Artifact elt;

    /**
     * Tells whether a {@link File proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << file >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("18dd653f-e90e-44b6-b755-7e84b50e00e6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, File.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << file >> then instantiate a {@link File} proxy.
     * 
     * @return a {@link File} proxy on the created {@link Artifact}.
     */
    @objid ("8f8f4458-1a2e-462a-b726-f576133f3be3")
    public static File create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, File.STEREOTYPE_NAME);
        return File.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link File} proxy from a {@link Artifact} stereotyped << file >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link File} proxy or <i>null</i>.
     */
    @objid ("6e38ad10-d282-43e9-a843-0ebf3f702cd9")
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
    @objid ("c69b3b46-91e1-4e3e-b116-b2eca1d70f11")
    public static File safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (File.canInstantiate(obj))
        	return new File(obj);
        else
        	throw new IllegalArgumentException("File: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7197078d-4024-4aa9-8d23-9c99c924cc3d")
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
    @objid ("5fb25a9c-7585-4917-9bcc-f15d9ef39174")
    public String getAuthor() {
        return this.elt.getTagValue(File.MdaTypes.AUTHOR_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ffb8012d-2eac-45ee-b25b-059311b7354e")
    public String getDate() {
        return this.elt.getTagValue(File.MdaTypes.DATE_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("a3ba6476-de72-4eee-a26c-6a4bf0588e2b")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("ea5b0d06-b64d-4fc1-9a0b-a614fd03a306")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("2fb4f8a3-97f4-425e-aee9-e008198aa30a")
    public void setAuthor(String value) {
        this.elt.putTagValue(File.MdaTypes.AUTHOR_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("5e84a39a-bce0-4376-b413-8b3d740edaf6")
    public void setDate(String value) {
        this.elt.putTagValue(File.MdaTypes.DATE_TAGTYPE_ELT, value);
    }

    @objid ("2401ca3c-4394-4d28-877d-20970a8fee33")
    protected File(Artifact elt) {
        this.elt = elt;
    }

    @objid ("7570829c-8959-497c-80cf-9a9640b7fb25")
    public static final class MdaTypes {
        @objid ("9da4f9c4-7f54-47e9-813b-6d3cc3d1a762")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7b26a47b-54f3-4ef1-b0ce-d1c6d6e7614f")
        public static TagType AUTHOR_TAGTYPE_ELT;

        @objid ("13efa062-c5a5-4145-b724-3534df2bc94d")
        public static TagType DATE_TAGTYPE_ELT;

        @objid ("ac7dbf12-6656-4caa-a3d6-c68994624d6c")
        private static Stereotype MDAASSOCDEP;

        @objid ("36826643-65ff-4f1f-8839-0bffafb4eab1")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("093e7380-2119-4665-8574-30b689f798d2")
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
