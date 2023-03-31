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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << impact_subroot >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("22b736f4-9a7f-47e0-addf-c7a7e736d6b7")
public class ImpactSubroot {
    @objid ("37ef5cd0-de7e-4098-9a00-7cffeb22684a")
    public static final String STEREOTYPE_NAME = "impact_subroot";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("4cf54d63-4a34-4277-8681-91da679ad4ec")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactSubroot proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_subroot >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8038a88f-f378-450b-ba21-ca7e6d5d6f07")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactSubroot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_subroot >> then instantiate a {@link ImpactSubroot} proxy.
     * 
     * @return a {@link ImpactSubroot} proxy on the created {@link Dependency}.
     */
    @objid ("17690f1f-03f1-4fe9-8792-3fd1780ebf2b")
    public static ImpactSubroot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactSubroot.STEREOTYPE_NAME);
        return ImpactSubroot.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactSubroot} proxy from a {@link Dependency} stereotyped << impact_subroot >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactSubroot} proxy or <i>null</i>.
     */
    @objid ("34da8c10-0d9d-459c-8869-2b680c319ba3")
    public static ImpactSubroot instantiate(Dependency obj) {
        return ImpactSubroot.canInstantiate(obj) ? new ImpactSubroot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactSubroot} proxy from a {@link Dependency} stereotyped << impact_subroot >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactSubroot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("73f7e32f-0063-4be1-9b76-b7f466f77cd0")
    public static ImpactSubroot safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactSubroot.canInstantiate(obj))
        	return new ImpactSubroot(obj);
        else
        	throw new IllegalArgumentException("ImpactSubroot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0eba29d2-59a8-457d-896f-e6682a2ccf3b")
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
        ImpactSubroot other = (ImpactSubroot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("ec468cd3-b40e-4b92-be16-81eb9817619a")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("9f8732f9-715e-4395-b36f-eb1f41c5c781")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c3fad0e6-7491-4a3e-8d2a-894b049c6a76")
    protected  ImpactSubroot(Dependency elt) {
        this.elt = elt;
    }

    @objid ("32cf63bf-4543-49bf-a3df-7d91136132db")
    public static final class MdaTypes {
        @objid ("4e507172-08d0-48b4-9b91-2ced7c1ac908")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2fb0982e-fd1e-4821-9c46-87c1314cfa06")
        private static Stereotype MDAASSOCDEP;

        @objid ("c945e418-0e82-451f-b40d-480ccce7d764")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("516f5d48-2862-4438-9dfa-813d0ceadcd3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec2468-0000-0ac6-0000-000000000000");
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
