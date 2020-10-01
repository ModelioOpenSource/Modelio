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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
    @objid ("acf077dc-fc1b-468c-8785-162480c8f398")
    public static final String STEREOTYPE_NAME = "UML2DeploymentSpecification";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("dd96f242-855a-4470-a8fb-79a9f38970b5")
    protected final Artifact elt;

    /**
     * Tells whether a {@link UML2DeploymentSpecification proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << UML2DeploymentSpecification >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("99bed33e-3275-4bbd-a270-50b79fb4aaa3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DeploymentSpecification.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << UML2DeploymentSpecification >> then instantiate a {@link UML2DeploymentSpecification} proxy.
     * 
     * @return a {@link UML2DeploymentSpecification} proxy on the created {@link Artifact}.
     */
    @objid ("af6db794-5c49-4b4e-a2f9-212e47c66ab9")
    public static UML2DeploymentSpecification create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
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
    @objid ("7404e030-8a0f-4601-8d7c-8e29ec959ba8")
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
    @objid ("9ca1e803-0c62-429f-8221-408995a6b95d")
    public static UML2DeploymentSpecification safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (UML2DeploymentSpecification.canInstantiate(obj))
        	return new UML2DeploymentSpecification(obj);
        else
        	throw new IllegalArgumentException("UML2DeploymentSpecification: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("990f64c6-8c28-4fa3-97ca-2b246dd5166b")
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
    @objid ("a5bab06f-2afa-4fa6-89b6-f6b2fe73c003")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("1578d0df-9081-48e7-9000-c5d2ea3f5003")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8d4b8b4e-0bea-4a9f-8896-205686d4fcbb")
    protected UML2DeploymentSpecification(Artifact elt) {
        this.elt = elt;
    }

    @objid ("573ae85e-4d62-4b49-b9e5-9267a47f1a8d")
    public static final class MdaTypes {
        @objid ("273cf199-1b17-4a71-b825-183b1f781d2d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fcbdd866-5e90-4995-a72b-bbefdce373ae")
        private static Stereotype MDAASSOCDEP;

        @objid ("579667b7-865a-4694-92ad-7ff68ed96528")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("15cd298b-5f84-4797-8faf-4272cafe7e50")
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
