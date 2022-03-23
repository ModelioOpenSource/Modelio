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
    @objid ("c9be7ab6-9af9-4e1f-ba20-ce3a610a0f56")
    public static final String STEREOTYPE_NAME = "file";

    @objid ("64273510-0546-4c61-95af-08d07909254f")
    public static final String AUTHOR_TAGTYPE = "author";

    @objid ("0430f8dd-7930-4378-a409-a91d127f96d5")
    public static final String DATE_TAGTYPE = "date";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("a30ebbfd-7f37-460c-a005-14d577394dd4")
    protected final Artifact elt;

    /**
     * Tells whether a {@link File proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << file >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("89d5b466-d905-486d-9053-f1f72239a7b4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, File.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << file >> then instantiate a {@link File} proxy.
     * 
     * @return a {@link File} proxy on the created {@link Artifact}.
     */
    @objid ("8b027010-9e60-4c2d-bdbb-b8554dfe29ac")
    public static File create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
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
    @objid ("f9057a04-3453-491f-8902-f293e168cb3c")
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
    @objid ("4b8164e3-6ea0-4303-9f82-f8c36f2cca80")
    public static File safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (File.canInstantiate(obj))
        	return new File(obj);
        else
        	throw new IllegalArgumentException("File: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("149fb398-56ef-4181-9b9b-2c4c6e0d1bfd")
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
    @objid ("c2e093db-35ea-4e65-93f1-a0ae8987374e")
    public String getAuthor() {
        return this.elt.getTagValue(File.MdaTypes.AUTHOR_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8f309452-291e-4ec4-bea6-d7515e72edde")
    public String getDate() {
        return this.elt.getTagValue(File.MdaTypes.DATE_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("d3e50e31-aa37-402f-bbb8-6a709549a90d")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("b3303566-21c3-4f77-b315-5cf537d8b548")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("745fc0b7-881d-4417-b1ba-7c434dcb2177")
    public void setAuthor(String value) {
        this.elt.putTagValue(File.MdaTypes.AUTHOR_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("afb8576b-b9f0-49ca-9f97-71e0bb201399")
    public void setDate(String value) {
        this.elt.putTagValue(File.MdaTypes.DATE_TAGTYPE_ELT, value);
    }

    @objid ("caaca43f-c80c-4149-8dfa-a4d2707f8513")
    protected  File(Artifact elt) {
        this.elt = elt;
    }

    @objid ("7570829c-8959-497c-80cf-9a9640b7fb25")
    public static final class MdaTypes {
        @objid ("db7a989a-c107-4558-9e44-cc2113ca9646")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("41db67b8-1529-4c8f-9f75-6767b8966e95")
        public static TagType AUTHOR_TAGTYPE_ELT;

        @objid ("d3e910fc-51df-4ea5-8908-b4a4f70e447a")
        public static TagType DATE_TAGTYPE_ELT;

        @objid ("fc67cb94-2c7c-433f-b6ab-c3918023aa92")
        private static Stereotype MDAASSOCDEP;

        @objid ("ffdb0dd1-db7b-4d36-9a3b-519da9f02b8c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e1ad9413-0bf7-4ea2-b7dd-0d340f43ebe1")
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
