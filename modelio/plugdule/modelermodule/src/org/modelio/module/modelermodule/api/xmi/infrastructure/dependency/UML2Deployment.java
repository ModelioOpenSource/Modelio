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
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2Deployment >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fc3acabf-4f25-4ad1-b9d8-51446901052b")
public class UML2Deployment {
    @objid ("5caa1d19-e9ad-423e-a7cb-58c0d6bd26fe")
    public static final String STEREOTYPE_NAME = "UML2Deployment";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7758a0aa-961a-42b9-8c97-aea201ac78d6")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Deployment proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Deployment >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("21919596-7321-4fb8-8741-19fca9a09c10")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Deployment.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Deployment >> then instantiate a {@link UML2Deployment} proxy.
     * 
     * @return a {@link UML2Deployment} proxy on the created {@link Dependency}.
     */
    @objid ("3c82b35e-a42b-4bd0-a84e-6e075070ad14")
    public static UML2Deployment create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Deployment.STEREOTYPE_NAME);
        return UML2Deployment.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Deployment} proxy from a {@link Dependency} stereotyped << UML2Deployment >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Deployment} proxy or <i>null</i>.
     */
    @objid ("a0b3cc0a-4f73-4fdb-ba3d-eaea3c538327")
    public static UML2Deployment instantiate(Dependency obj) {
        return UML2Deployment.canInstantiate(obj) ? new UML2Deployment(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Deployment} proxy from a {@link Dependency} stereotyped << UML2Deployment >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Deployment} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fd01ab35-d810-46b9-898a-41b4402deef6")
    public static UML2Deployment safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Deployment.canInstantiate(obj))
        	return new UML2Deployment(obj);
        else
        	throw new IllegalArgumentException("UML2Deployment: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1a03b4cb-f6ad-458d-a26c-08082f4963d1")
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
        UML2Deployment other = (UML2Deployment) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("c2c09138-793f-4094-a8e4-81c6f8941700")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("bdca4d83-f5d2-47bc-823b-810735f09abf")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("80e6a12c-75d3-40f5-a323-f9a84086db7c")
    protected  UML2Deployment(Dependency elt) {
        this.elt = elt;
    }

    @objid ("3c7b3b03-b7b6-4b5e-b616-ef29bb173f1b")
    public static final class MdaTypes {
        @objid ("c60c0f59-4d52-4d05-a7a3-1f1cb8c4863f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1f4ca4d2-276d-4eb1-8b12-ccdc1eb71256")
        private static Stereotype MDAASSOCDEP;

        @objid ("43db5912-4c83-4715-a1e2-8c3faaa388f5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("40aa80ef-2536-4b1d-aaed-285365773b20")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6faa55e3-5d0b-11df-a996-001302895b2b");
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
