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
 * Proxy class to handle a {@link Artifact} with << executable >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d4573417-3d08-45a2-b10e-0d1b9d7f1d7d")
public class Executable {
    @objid ("e305c0ae-a640-46f0-9f0e-04bfe34df959")
    public static final String STEREOTYPE_NAME = "executable";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("27c99e7d-783f-476e-87c9-abd0c5dbffda")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Executable proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << executable >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a218dc87-3414-4500-aef1-e036b46e867b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Executable.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << executable >> then instantiate a {@link Executable} proxy.
     * 
     * @return a {@link Executable} proxy on the created {@link Artifact}.
     */
    @objid ("c06bc7cc-2d83-4efe-b812-fe90231f5a21")
    public static Executable create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Executable.STEREOTYPE_NAME);
        return Executable.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Executable} proxy from a {@link Artifact} stereotyped << executable >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Executable} proxy or <i>null</i>.
     */
    @objid ("f1b53a91-7a60-46cc-86c3-2b8004e1c9f5")
    public static Executable instantiate(Artifact obj) {
        return Executable.canInstantiate(obj) ? new Executable(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Executable} proxy from a {@link Artifact} stereotyped << executable >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Executable} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c53fc1fa-2742-41d2-9087-364a19fc3799")
    public static Executable safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Executable.canInstantiate(obj))
        	return new Executable(obj);
        else
        	throw new IllegalArgumentException("Executable: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6e69a351-8848-405e-97fa-80df14fd236f")
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
        Executable other = (Executable) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("5f4f74b4-1979-4629-aa41-7c9bc003f037")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("bab08b14-a842-4f85-96ce-55beba2585c6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("4c74f67f-7f13-4819-9206-6fdffa119cb7")
    protected  Executable(Artifact elt) {
        this.elt = elt;
    }

    @objid ("51e459a7-b6fd-484f-af65-d1a7e5948cea")
    public static final class MdaTypes {
        @objid ("7093ca2e-29ab-43af-8e6d-7e4d5d10e98d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("665e4a45-6681-46ac-8b53-e6f5ce7ece7d")
        private static Stereotype MDAASSOCDEP;

        @objid ("4d7554e1-6f06-46b6-bc04-67cd76d92b4d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3ec4e53d-483f-4425-82ba-535296ac63ad")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01c3-0000-000000000000");
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
