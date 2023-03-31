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
 * Proxy class to handle a {@link Artifact} with << url >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("74934370-1154-4850-a9ee-5d3c634680ac")
public class Url {
    @objid ("b31d22f5-3cc8-4279-9855-f54d82097bcc")
    public static final String STEREOTYPE_NAME = "url";

    @objid ("0a749783-2715-4b94-9406-8c3cf5ba104e")
    public static final String AUTHOR_TAGTYPE = "author";

    @objid ("cdfb4ac2-f5d8-4f20-ada4-2265e9ceb427")
    public static final String DATE_TAGTYPE = "date";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("4ffc8e13-72de-47e9-b4d7-52a2e41cc06c")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Url proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << url >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("c3c681ef-e9eb-4b5e-83b0-1fbd9787418d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Url.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << url >> then instantiate a {@link Url} proxy.
     * 
     * @return a {@link Url} proxy on the created {@link Artifact}.
     */
    @objid ("cd93004f-9d0b-4615-9a79-8465bc6346d2")
    public static Url create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Url.STEREOTYPE_NAME);
        return Url.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Url} proxy from a {@link Artifact} stereotyped << url >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Url} proxy or <i>null</i>.
     */
    @objid ("70e09c36-a70f-4f5a-af87-1ba55bc2f1de")
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
    @objid ("d3a28eeb-95a6-4939-a280-f6d26fe98acb")
    public static Url safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Url.canInstantiate(obj))
        	return new Url(obj);
        else
        	throw new IllegalArgumentException("Url: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b79ab1d5-4e7a-471f-94ee-a49ea1999cf1")
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
    @objid ("4150cd31-cae9-4c76-a6cb-c6486392e00d")
    public String getAuthor() {
        return this.elt.getTagValue(Url.MdaTypes.AUTHOR_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("92e87b68-27a4-423d-8eda-cba906eda447")
    public String getDate() {
        return this.elt.getTagValue(Url.MdaTypes.DATE_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("756162e0-8c6c-4b79-9281-426c48eb668c")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("832547de-2283-477f-9529-824400d49d65")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("4f66c67f-3580-44aa-8a7b-01bfe4d135c4")
    public void setAuthor(String value) {
        this.elt.putTagValue(Url.MdaTypes.AUTHOR_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7ac1e23f-5813-48cb-a801-3e2d765e40fc")
    public void setDate(String value) {
        this.elt.putTagValue(Url.MdaTypes.DATE_TAGTYPE_ELT, value);
    }

    @objid ("e6692502-ae20-4452-8ab8-150bd25456aa")
    protected  Url(Artifact elt) {
        this.elt = elt;
    }

    @objid ("1612b182-f258-483a-a67a-a48cfaece602")
    public static final class MdaTypes {
        @objid ("544e5695-8ea9-478c-abd9-eee1776b0a32")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e2e6995a-aa21-4925-9ec0-fe7dbe3cab8d")
        public static TagType AUTHOR_TAGTYPE_ELT;

        @objid ("2ce689ac-ebb4-4754-ba99-5ce31d2a291d")
        public static TagType DATE_TAGTYPE_ELT;

        @objid ("e0c0cb88-6c4c-48d9-9dcd-b12e8174ab04")
        private static Stereotype MDAASSOCDEP;

        @objid ("08358d47-699a-4600-970a-8183a61529af")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8d6ae474-56a7-45e9-937a-92b1be54fc73")
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
