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
    @objid ("d26bc34d-c190-48d9-b0c5-1190a5d3f941")
    public static final String STEREOTYPE_NAME = "url";

    @objid ("fb13e73d-836a-4acc-a854-618375777cbc")
    public static final String AUTHOR_TAGTYPE = "author";

    @objid ("3eb9b90b-5e3a-48ab-bde6-38c62773808c")
    public static final String DATE_TAGTYPE = "date";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("020ab9cf-4302-4355-a95c-4b42651cfb6b")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Url proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << url >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e2b71978-a0fc-445b-8fe1-345f14057d89")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Url.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << url >> then instantiate a {@link Url} proxy.
     * 
     * @return a {@link Url} proxy on the created {@link Artifact}.
     */
    @objid ("e0d19071-4c1a-4aa9-8c06-0300f85454d7")
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
    @objid ("4269dd06-2fe3-41d0-9b22-24dc0aeec6a7")
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
    @objid ("48049927-642d-4ec6-b19d-d277150d63c7")
    public static Url safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Url.canInstantiate(obj))
        	return new Url(obj);
        else
        	throw new IllegalArgumentException("Url: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8870b962-b56c-4b48-bee9-3f3b07a07598")
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
    @objid ("f0899410-9087-4594-aa71-1b1c72dee190")
    public String getAuthor() {
        return this.elt.getTagValue(Url.MdaTypes.AUTHOR_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e9660290-dd65-4c1f-ade6-3bc836832cc4")
    public String getDate() {
        return this.elt.getTagValue(Url.MdaTypes.DATE_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("6b66a175-50c6-498c-ab0a-e172464d8827")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("bd12795d-a0f0-4ad9-b49f-c30c6c1164ec")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'author'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("80b7cd7f-52f7-487e-ac97-54c683ae684d")
    public void setAuthor(String value) {
        this.elt.putTagValue(Url.MdaTypes.AUTHOR_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'date'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("3316b1ec-2455-4c4e-9f2d-b399e0924ac3")
    public void setDate(String value) {
        this.elt.putTagValue(Url.MdaTypes.DATE_TAGTYPE_ELT, value);
    }

    @objid ("9c2d1536-0963-4efd-9777-4e0bbf3240ff")
    protected Url(Artifact elt) {
        this.elt = elt;
    }

    @objid ("1612b182-f258-483a-a67a-a48cfaece602")
    public static final class MdaTypes {
        @objid ("c5cf9f44-ba5d-4dde-b53e-6ee958d59946")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d01baefa-4e52-41a4-9182-9a7ca1d464fd")
        public static TagType AUTHOR_TAGTYPE_ELT;

        @objid ("a77fba8e-0fa4-4687-839d-1ab08093bb47")
        public static TagType DATE_TAGTYPE_ELT;

        @objid ("f2e9c989-3da1-41b9-9944-c38fed9ed9d3")
        private static Stereotype MDAASSOCDEP;

        @objid ("4d037aad-c144-4e95-abe9-821b24af490a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5793cf55-d02b-4bfc-8a1f-35f5af4af51e")
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
