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
 * Proxy class to handle a {@link Artifact} with << document >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("598e9650-6e45-49b6-90c3-c582c258cc1a")
public class Document {
    @objid ("24c7b1ea-b5c5-4325-97c9-3d89cfa9be20")
    public static final String STEREOTYPE_NAME = "document";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("605854af-7339-4ddc-b166-bae376a8c0ff")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Document proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << document >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5a5e5310-d897-4d85-b6e3-6378d6aecf40")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Document.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << document >> then instantiate a {@link Document} proxy.
     * 
     * @return a {@link Document} proxy on the created {@link Artifact}.
     */
    @objid ("d86c7e45-38b4-4d29-8340-623c8473af7a")
    public static Document create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Document.STEREOTYPE_NAME);
        return Document.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Document} proxy from a {@link Artifact} stereotyped << document >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Document} proxy or <i>null</i>.
     */
    @objid ("e6d493d9-24f5-42ff-b1d7-26e5499d0274")
    public static Document instantiate(Artifact obj) {
        return Document.canInstantiate(obj) ? new Document(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Document} proxy from a {@link Artifact} stereotyped << document >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Document} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9b928f5a-e206-465a-a0bb-ab8e916a4528")
    public static Document safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Document.canInstantiate(obj))
        	return new Document(obj);
        else
        	throw new IllegalArgumentException("Document: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8940aaad-4620-4793-a113-8d7b8f63a6a3")
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
        Document other = (Document) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("dbbf7fe2-a439-43dc-aa83-fd4e894adb98")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("6de5ea25-59b9-4f40-9a2f-c8c31c35e452")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("309aa417-e08c-4edb-b976-d55e63122052")
    protected Document(Artifact elt) {
        this.elt = elt;
    }

    @objid ("fe2f2ca6-6273-4ced-825f-6e1d068d5a3b")
    public static final class MdaTypes {
        @objid ("ae21b288-2201-4d82-82d4-0e7d96977465")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fc7ae342-f574-492a-b9d9-226909859901")
        private static Stereotype MDAASSOCDEP;

        @objid ("4043a1f4-c8a0-4187-ace7-d2337a8c978c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2cf3c9ca-7621-4cd1-9858-6e26a1958996")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "388ba911-9fb3-4117-80af-6099142d7816");
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
