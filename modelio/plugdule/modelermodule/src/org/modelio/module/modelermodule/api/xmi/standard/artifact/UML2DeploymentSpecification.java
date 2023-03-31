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
package org.modelio.module.modelermodule.api.xmi.standard.artifact;

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
 * Proxy class to handle a {@link Artifact} with << UML2DeploymentSpecification >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a5115a85-757b-44ec-a53a-583442d3e59e")
public class UML2DeploymentSpecification {
    @objid ("32191595-eb7a-4e90-97e6-99d0b580dd0d")
    public static final String STEREOTYPE_NAME = "UML2DeploymentSpecification";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("1feb98de-a13c-49a4-be18-b98e5eeefeeb")
    protected final Artifact elt;

    /**
     * Tells whether a {@link UML2DeploymentSpecification proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << UML2DeploymentSpecification >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6a385521-30a7-4b31-8f44-5a83a31c3cec")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DeploymentSpecification.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << UML2DeploymentSpecification >> then instantiate a {@link UML2DeploymentSpecification} proxy.
     * 
     * @return a {@link UML2DeploymentSpecification} proxy on the created {@link Artifact}.
     */
    @objid ("a8ad5669-9249-4ab8-adb7-0e7e47083b20")
    public static UML2DeploymentSpecification create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DeploymentSpecification.STEREOTYPE_NAME);
        return UML2DeploymentSpecification.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link UML2DeploymentSpecification} proxy from a {@link Artifact} stereotyped << UML2DeploymentSpecification >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link UML2DeploymentSpecification} proxy or <i>null</i>.
     */
    @objid ("93a7ee96-a3da-44c7-a245-087f8ce6a685")
    public static UML2DeploymentSpecification instantiate(Artifact obj) {
        return UML2DeploymentSpecification.canInstantiate(obj) ? new UML2DeploymentSpecification(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DeploymentSpecification} proxy from a {@link Artifact} stereotyped << UML2DeploymentSpecification >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link UML2DeploymentSpecification} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3bac3422-3c89-4f50-ba47-822815a70710")
    public static UML2DeploymentSpecification safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (UML2DeploymentSpecification.canInstantiate(obj))
        	return new UML2DeploymentSpecification(obj);
        else
        	throw new IllegalArgumentException("UML2DeploymentSpecification: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("388771be-3530-4152-b3fa-d9a8547a966c")
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
        UML2DeploymentSpecification other = (UML2DeploymentSpecification) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("2ad6de2a-169c-420e-afb5-81a649ab46a9")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("aaa3fce3-d460-4b3a-8d60-3ea4c656683f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("21497b1b-4f28-497f-aeb8-043572d63590")
    protected  UML2DeploymentSpecification(Artifact elt) {
        this.elt = elt;
    }

    @objid ("573ae85e-4d62-4b49-b9e5-9267a47f1a8d")
    public static final class MdaTypes {
        @objid ("02d597dc-707b-441c-9951-ac02bc2c5112")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b2b85481-9e4c-4311-8c29-e0b7e5e1d759")
        private static Stereotype MDAASSOCDEP;

        @objid ("e5396d7d-e4eb-43cc-8453-266c73185a9a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c46ebf5b-2ed1-4f4b-bf89-c2c42c5d1330")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8c44d73d-5d0b-11df-a996-001302895b2b");
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
