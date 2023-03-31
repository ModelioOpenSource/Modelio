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
package org.modelio.module.modelermodule.api.default_.standard.class_;

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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << implementationClass >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a8d3476-0e47-4713-8073-82df91f999f5")
public class ImplementationClass {
    @objid ("4ce169d4-3d73-48f2-ac09-26bf49325162")
    public static final String STEREOTYPE_NAME = "implementationClass";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("bd5ef178-ec7a-4966-ac98-8ac21415abb2")
    protected final Class elt;

    /**
     * Tells whether a {@link ImplementationClass proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << implementationClass >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fd866cb3-dd69-4010-b632-dc5a0ef8c7dc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImplementationClass.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << implementationClass >> then instantiate a {@link ImplementationClass} proxy.
     * 
     * @return a {@link ImplementationClass} proxy on the created {@link Class}.
     */
    @objid ("62145aea-4195-483d-91f0-6ed9c3ffe409")
    public static ImplementationClass create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImplementationClass.STEREOTYPE_NAME);
        return ImplementationClass.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link ImplementationClass} proxy from a {@link Class} stereotyped << implementationClass >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link ImplementationClass} proxy or <i>null</i>.
     */
    @objid ("7aff66df-5039-4863-8159-512aafbb861c")
    public static ImplementationClass instantiate(Class obj) {
        return ImplementationClass.canInstantiate(obj) ? new ImplementationClass(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImplementationClass} proxy from a {@link Class} stereotyped << implementationClass >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link ImplementationClass} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("00c9d73e-3ed5-49bb-9570-9e678c872564")
    public static ImplementationClass safeInstantiate(Class obj) throws IllegalArgumentException {
        if (ImplementationClass.canInstantiate(obj))
        	return new ImplementationClass(obj);
        else
        	throw new IllegalArgumentException("ImplementationClass: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("80329395-5809-4943-b006-0df621b9d88a")
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
        ImplementationClass other = (ImplementationClass) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("f47ef29f-f6a3-4cee-bb01-6df184680e98")
    public Class getElement() {
        return this.elt;
    }

    @objid ("4a16f797-a0e2-4292-b9a7-0cd6af0cb2d3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("a43c4f56-6cff-4949-8daf-38328881a0a8")
    protected  ImplementationClass(Class elt) {
        this.elt = elt;
    }

    @objid ("1b7811d3-b98b-4855-a8c5-b056228fbf8f")
    public static final class MdaTypes {
        @objid ("96e567b6-8732-4854-b844-f39aea17bfbe")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("46fd8065-3360-4944-b988-b41b467baf37")
        private static Stereotype MDAASSOCDEP;

        @objid ("9dd0e398-e225-40a4-b6a3-2612e08bb119")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1dded8a1-5ffa-413b-8c49-50c76e080b75")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00bc0050-0000-006b-0000-000000000000");
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
