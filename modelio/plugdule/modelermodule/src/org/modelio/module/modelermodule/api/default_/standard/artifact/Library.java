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
 * Proxy class to handle a {@link Artifact} with << library >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6adfb96d-3843-4138-b8fa-f7724e9f9ec8")
public class Library {
    @objid ("d9ee7947-1519-4777-b6bc-31ca3997b324")
    public static final String STEREOTYPE_NAME = "library";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("e93de8d4-83eb-450d-8740-29ae7271658f")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Library proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << library >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9dbcb9dd-9cf4-41ca-972c-998cfc1d6812")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Library.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << library >> then instantiate a {@link Library} proxy.
     * 
     * @return a {@link Library} proxy on the created {@link Artifact}.
     */
    @objid ("96851944-e82d-4884-bc88-bc05c156d9d0")
    public static Library create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Library.STEREOTYPE_NAME);
        return Library.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Library} proxy from a {@link Artifact} stereotyped << library >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Library} proxy or <i>null</i>.
     */
    @objid ("1ca0bea9-2636-4b7f-9c72-96e71abaaecb")
    public static Library instantiate(Artifact obj) {
        return Library.canInstantiate(obj) ? new Library(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Library} proxy from a {@link Artifact} stereotyped << library >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Library} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c0c56ddb-ad49-425e-a118-2c3a6ffc7fbf")
    public static Library safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Library.canInstantiate(obj))
        	return new Library(obj);
        else
        	throw new IllegalArgumentException("Library: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("08c9a436-f0a7-45f8-98e8-999207e50a1b")
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
        Library other = (Library) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("064cce45-5e1f-4211-8251-b6fb4e3ec96c")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("a3312ade-9f5b-48fc-a853-41cd60f229c1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("74b3d794-f148-4d85-9763-561dee3641d1")
    protected  Library(Artifact elt) {
        this.elt = elt;
    }

    @objid ("7c651d53-3107-491d-9c5d-92dc5fa1b829")
    public static final class MdaTypes {
        @objid ("c3a466ed-92e6-4d5f-9109-3872a57c3b33")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7eeb5702-50c9-4867-899c-56b9b5c41770")
        private static Stereotype MDAASSOCDEP;

        @objid ("89e1bb02-bb40-4aa4-921d-a28f485e7bbe")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("316fa355-00c5-4d75-ae12-4fb4cd9e9056")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01c5-0000-000000000000");
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
