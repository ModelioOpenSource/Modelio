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
package org.modelio.module.modelermodule.api.default_.standard.classifier;

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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Classifier} with << process >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2bf282d0-192b-4378-a060-395cb66630c9")
public class Process {
    @objid ("6d0f4ef3-8f46-43d7-8fce-9e21f496c736")
    public static final String STEREOTYPE_NAME = "process";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("87c250e2-45da-4e00-b751-62d47fa312fe")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Process proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << process >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3725d4a8-91af-492c-a87d-41fe5aec7e5b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << process >> then instantiate a {@link Process} proxy.
     * 
     * @return a {@link Process} proxy on the created {@link Classifier}.
     */
    @objid ("37354d9e-c2e0-455f-8ae0-f4008c2585a8")
    public static Process create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME);
        return Process.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link Classifier} stereotyped << process >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Process} proxy or <i>null</i>.
     */
    @objid ("d830a03e-8e1f-455b-bd67-086b0451b812")
    public static Process instantiate(Classifier obj) {
        return Process.canInstantiate(obj) ? new Process(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link Classifier} stereotyped << process >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Process} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d7a2e1d8-5d27-43a8-b3a8-25d2766a0fe6")
    public static Process safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Process.canInstantiate(obj))
        	return new Process(obj);
        else
        	throw new IllegalArgumentException("Process: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("825c07a1-b6dc-4826-a1be-ec581e98328c")
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
        Process other = (Process) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("20e1fb66-b11d-4038-931b-5f662f8b9b16")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("1c9c43aa-49af-428a-8ed0-c2a3d26173d0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e21ff6ce-b352-41aa-9d27-3bb36d6de55a")
    protected Process(Classifier elt) {
        this.elt = elt;
    }

    @objid ("3f8020b0-6840-4956-b848-948e2dcce204")
    public static final class MdaTypes {
        @objid ("36a84218-47c4-4dc0-be77-033ff1ba9f53")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("70f0af7b-1e04-43cf-b49e-9f1493abd88f")
        private static Stereotype MDAASSOCDEP;

        @objid ("669fb6da-b9de-454d-b413-6d4cbeb836ed")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4e3be2e7-0e31-491d-b1d9-c75b43a42ff0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ef-0000-000000000000");
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
