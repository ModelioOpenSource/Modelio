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
 * Proxy class to handle a {@link Artifact} with << document >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("598e9650-6e45-49b6-90c3-c582c258cc1a")
public class Document {
    @objid ("1f6cb7a8-7cba-4e41-8c0c-60083114f246")
    public static final String STEREOTYPE_NAME = "document";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("8d974f95-981a-49d4-b711-532c9530f621")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Document proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << document >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8ac1145c-a2b4-413c-8bd5-c172d7a62655")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Document.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << document >> then instantiate a {@link Document} proxy.
     * 
     * @return a {@link Document} proxy on the created {@link Artifact}.
     */
    @objid ("e1c40ce8-19b0-46b1-b603-76f0fb4d9e99")
    public static Document create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
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
    @objid ("02ae75de-a825-480d-baaf-a48d396380b4")
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
    @objid ("f1e6b4f6-6839-43e6-8e63-890e35f25eae")
    public static Document safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Document.canInstantiate(obj))
        	return new Document(obj);
        else
        	throw new IllegalArgumentException("Document: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("67ba5309-0602-4115-86bc-6ba1fb40a570")
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
    @objid ("a322aec7-440c-48e3-b956-c1f6f8ea93c9")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("a9636d7e-d652-433d-95bb-67eed2c3a6c8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("2b32f73b-40f3-48fb-ab4d-41677de80ae2")
    protected  Document(Artifact elt) {
        this.elt = elt;
    }

    @objid ("fe2f2ca6-6273-4ced-825f-6e1d068d5a3b")
    public static final class MdaTypes {
        @objid ("f5fd4f71-7ff3-401f-8f19-e3e27ef42f23")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c528ee7e-5113-4eb5-a881-5b472f664841")
        private static Stereotype MDAASSOCDEP;

        @objid ("e128ed00-e6b6-4bd7-8d29-6df338c760e0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2e4c1dac-139b-4a91-9f7e-541590a16955")
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
