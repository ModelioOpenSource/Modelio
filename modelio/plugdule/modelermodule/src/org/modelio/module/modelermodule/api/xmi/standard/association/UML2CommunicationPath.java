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
package org.modelio.module.modelermodule.api.xmi.standard.association;

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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} with << UML2CommunicationPath >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("81678c9e-2f43-40bc-ad65-f1a578bb0dee")
public class UML2CommunicationPath {
    @objid ("b9a5fef7-6516-4bf4-95d7-73f98e2aef3c")
    public static final String STEREOTYPE_NAME = "UML2CommunicationPath";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("6c857072-c429-40c2-9d73-e82c80c692ea")
    protected final Association elt;

    /**
     * Tells whether a {@link UML2CommunicationPath proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << UML2CommunicationPath >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("628d882e-4dcd-4804-b2c6-e25f152351e1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CommunicationPath.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << UML2CommunicationPath >> then instantiate a {@link UML2CommunicationPath} proxy.
     * 
     * @return a {@link UML2CommunicationPath} proxy on the created {@link Association}.
     */
    @objid ("f5bb0a44-ee9f-42ac-b9e8-087be72badc0")
    public static UML2CommunicationPath create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CommunicationPath.STEREOTYPE_NAME);
        return UML2CommunicationPath.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link UML2CommunicationPath} proxy from a {@link Association} stereotyped << UML2CommunicationPath >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link UML2CommunicationPath} proxy or <i>null</i>.
     */
    @objid ("37b41d25-5bc4-4978-8014-c089da547586")
    public static UML2CommunicationPath instantiate(Association obj) {
        return UML2CommunicationPath.canInstantiate(obj) ? new UML2CommunicationPath(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CommunicationPath} proxy from a {@link Association} stereotyped << UML2CommunicationPath >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link UML2CommunicationPath} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f5652bfa-1170-45f5-9c6d-615e97ba0334")
    public static UML2CommunicationPath safeInstantiate(Association obj) throws IllegalArgumentException {
        if (UML2CommunicationPath.canInstantiate(obj))
        	return new UML2CommunicationPath(obj);
        else
        	throw new IllegalArgumentException("UML2CommunicationPath: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("da280eae-b3a5-4a94-85af-21d7fbba759c")
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
        UML2CommunicationPath other = (UML2CommunicationPath) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("89f0e7bd-6768-4fd6-a1ab-a0482499feec")
    public Association getElement() {
        return this.elt;
    }

    @objid ("e4c5954c-da5f-4b37-8277-5ac9b37342d8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("b5e8e138-8cfc-417d-ada0-03b3019b621e")
    protected  UML2CommunicationPath(Association elt) {
        this.elt = elt;
    }

    @objid ("d8b8c58e-ba0a-4cce-937a-c14207a1cafc")
    public static final class MdaTypes {
        @objid ("9e6de080-1f3f-40e4-9b0e-ecead7772234")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("62470b1e-504f-43d4-8b9e-4464673e06c3")
        private static Stereotype MDAASSOCDEP;

        @objid ("0d1406d4-3378-4702-a642-b0c5c67790f7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("24282698-97d3-49b4-81c1-56c4f902345a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8ed6276e-5821-11df-be59-001302895b2b");
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
