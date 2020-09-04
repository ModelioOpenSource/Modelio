/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("dec985fe-73f9-4c9e-a725-35c4d3be868f")
    public static final String STEREOTYPE_NAME = "UML2DeploymentSpecification";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("0ac953a5-bfb3-4b51-8c21-81dc8a720b3e")
    protected final Artifact elt;

    /**
     * Tells whether a {@link UML2DeploymentSpecification proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << UML2DeploymentSpecification >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0be77363-5c55-4f62-b985-d22df4d14ac8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DeploymentSpecification.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << UML2DeploymentSpecification >> then instantiate a {@link UML2DeploymentSpecification} proxy.
     * 
     * @return a {@link UML2DeploymentSpecification} proxy on the created {@link Artifact}.
     */
    @objid ("ec8fd72e-7215-4419-a074-b8697b8eaa55")
    public static UML2DeploymentSpecification create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DeploymentSpecification.STEREOTYPE_NAME);
        return UML2DeploymentSpecification.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link UML2DeploymentSpecification} proxy from a {@link Artifact} stereotyped << UML2DeploymentSpecification >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link UML2DeploymentSpecification} proxy or <i>null</i>.
     */
    @objid ("a636162e-91d3-4748-8851-df8b86573b34")
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
    @objid ("2ad39cd3-82f9-491b-9f17-e4ca251ec4b9")
    public static UML2DeploymentSpecification safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (UML2DeploymentSpecification.canInstantiate(obj))
        	return new UML2DeploymentSpecification(obj);
        else
        	throw new IllegalArgumentException("UML2DeploymentSpecification: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bfc2a847-6d06-4b37-a82a-2fa5583e2de5")
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
    @objid ("93dcefae-36b0-4b95-869b-2f499308b2f0")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("31a33a43-0747-4a89-a019-c0b943b66525")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8c195d7d-d667-4210-af00-e4c1fb49c6c6")
    protected UML2DeploymentSpecification(Artifact elt) {
        this.elt = elt;
    }

    @objid ("573ae85e-4d62-4b49-b9e5-9267a47f1a8d")
    public static final class MdaTypes {
        @objid ("375b5216-784f-4ad8-80ad-aca6784a16c0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d455b91e-a1c3-4761-b046-494cd8718771")
        private static Stereotype MDAASSOCDEP;

        @objid ("13f3789d-d36b-46a9-b635-20d3d07debe2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c83eb8d6-34bd-4ac4-9403-05e56a70a23a")
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
