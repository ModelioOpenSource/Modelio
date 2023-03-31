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
 * Proxy class to handle a {@link Artifact} with << directory >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d378822b-d3d3-4f51-9c86-6bd5d820f76d")
public class Directory {
    @objid ("b3a61c88-e31b-4362-ad98-1150abf20c4c")
    public static final String STEREOTYPE_NAME = "directory";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("8840dd5f-8f3c-44f7-8ef0-7f76ce22ca0c")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Directory proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << directory >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("07d0a9bc-c847-4ddd-b2b2-ee3f53f4ab68")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Directory.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << directory >> then instantiate a {@link Directory} proxy.
     * 
     * @return a {@link Directory} proxy on the created {@link Artifact}.
     */
    @objid ("75fe0b3e-2d19-4cdc-a21e-d9f474be3c10")
    public static Directory create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Directory.STEREOTYPE_NAME);
        return Directory.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Directory} proxy from a {@link Artifact} stereotyped << directory >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Directory} proxy or <i>null</i>.
     */
    @objid ("1a076987-a37c-467d-93cd-06d7fbeb54ca")
    public static Directory instantiate(Artifact obj) {
        return Directory.canInstantiate(obj) ? new Directory(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Directory} proxy from a {@link Artifact} stereotyped << directory >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Directory} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fb837cd1-1f1f-49d8-949b-413896082531")
    public static Directory safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Directory.canInstantiate(obj))
        	return new Directory(obj);
        else
        	throw new IllegalArgumentException("Directory: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9f8ff55b-c3e3-4b9c-b2ca-af27d79002b8")
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
        Directory other = (Directory) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("3a2ed95b-bde4-440f-bf7b-9c031aa95b6d")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("9d1293d8-17e0-4b7c-bee7-f31a261b100f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e616a4f1-f4ab-4814-a8d8-2db6c130b150")
    protected  Directory(Artifact elt) {
        this.elt = elt;
    }

    @objid ("c8a5a0d9-e215-4019-9561-d0323c58164f")
    public static final class MdaTypes {
        @objid ("7b751203-b075-4ffd-ac47-a7f040c5850c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("84b0d6f4-3fb3-4ef7-b8c1-95f2f23f7aec")
        private static Stereotype MDAASSOCDEP;

        @objid ("16f4d254-9404-42e9-86ab-1849976bdb88")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e8b7c2d4-9a80-4e61-816f-1334e1d9578c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6432b987-256f-4121-9428-a89d364c2cef");
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
