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
 * Proxy class to handle a {@link Artifact} with << document >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("598e9650-6e45-49b6-90c3-c582c258cc1a")
public class Document {
    @objid ("d6fdeaa7-9002-4421-b246-ecd336b70eb3")
    public static final String STEREOTYPE_NAME = "document";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("511a2b64-327e-4d4c-88da-13375e38df67")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Document proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << document >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("27545eb2-751a-423c-b3e1-5cf43901c080")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Document.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << document >> then instantiate a {@link Document} proxy.
     * 
     * @return a {@link Document} proxy on the created {@link Artifact}.
     */
    @objid ("dfb6c79e-57b0-484b-a7f6-1ca5cfb26990")
    public static Document create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Document.STEREOTYPE_NAME);
        return Document.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Document} proxy from a {@link Artifact} stereotyped << document >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Document} proxy or <i>null</i>.
     */
    @objid ("76487048-e72f-4d7d-b4bb-0147191b40be")
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
    @objid ("d371955e-afaa-473b-9408-6cca47eb68ef")
    public static Document safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Document.canInstantiate(obj))
        	return new Document(obj);
        else
        	throw new IllegalArgumentException("Document: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cc51a54e-f2a5-458f-bd3d-f38bb1dbc2fc")
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
    @objid ("0549868f-9c5f-4a3a-ac51-72522ca8bb3c")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("66becc32-81ed-4ad8-8c61-cab6594681ce")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("52b68953-7b49-47b1-821c-5602ee775c6a")
    protected Document(Artifact elt) {
        this.elt = elt;
    }

    @objid ("fe2f2ca6-6273-4ced-825f-6e1d068d5a3b")
    public static final class MdaTypes {
        @objid ("20e22ddc-ccf8-4491-b744-7d9a84caa727")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9ec05881-fa38-4953-a6d1-ea165961157b")
        private static Stereotype MDAASSOCDEP;

        @objid ("359a0070-1510-4aef-b16b-e2d715e6ebbd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7c8694ce-ba2b-48c3-8ae1-1f67501780f2")
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
