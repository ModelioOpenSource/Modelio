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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << measure >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a5520de-c3f4-49fe-82ab-dd4a9414a61c")
public class Measure {
    @objid ("7453aaf0-f73f-4fbd-b2cb-4504dbfb928a")
    public static final String STEREOTYPE_NAME = "measure";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("32fc5091-f2d1-456d-8cd7-4f6df00ef28a")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Measure proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << measure >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("8c29064a-9b9e-41e8-a8ed-061a9c7be1c9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Measure.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << measure >> then instantiate a {@link Measure} proxy.
     * 
     * @return a {@link Measure} proxy on the created {@link Dependency}.
     */
    @objid ("55811149-f3f3-429f-b6a8-3e777a06c06e")
    public static Measure create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Measure.STEREOTYPE_NAME);
        return Measure.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Measure} proxy from a {@link Dependency} stereotyped << measure >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Measure} proxy or <i>null</i>.
     */
    @objid ("f1123bac-5e0f-4130-b39e-e3815e1c4f00")
    public static Measure instantiate(Dependency obj) {
        return Measure.canInstantiate(obj) ? new Measure(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Measure} proxy from a {@link Dependency} stereotyped << measure >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Measure} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dbec304f-50f1-4891-b9d7-0ad19c133e0d")
    public static Measure safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Measure.canInstantiate(obj))
        	return new Measure(obj);
        else
        	throw new IllegalArgumentException("Measure: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fd0beff1-cefb-46f3-8052-6c515261f324")
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
        Measure other = (Measure) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("81d520bb-ac4d-4dbd-bcf1-01a6d07aeb1d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("8a0b2a38-35c5-4e9d-b490-fe351bc4d95b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("0964eeb0-1a4f-45d7-aa01-eacdc3c9010b")
    protected  Measure(Dependency elt) {
        this.elt = elt;
    }

    @objid ("46bb357b-ca38-48c0-b010-8be5578cd473")
    public static final class MdaTypes {
        @objid ("50b40e14-8dd2-4cb2-b210-715e18489b82")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f6eef42b-9be0-49a8-8265-91e57f5fc92f")
        private static Stereotype MDAASSOCDEP;

        @objid ("f7851eb4-a4e3-4c00-822b-25e13d553467")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("743c46ee-94b1-411f-8003-90bed859193d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0256-0000-000000000000");
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
