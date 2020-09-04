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
    @objid ("09170cd2-41c1-4035-87af-5bf579d9af63")
    public static final String STEREOTYPE_NAME = "process";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("2901da54-4216-4d58-b19e-f9c248dbbd42")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Process proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << process >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3982e293-5bf1-4a51-9a92-afc50df6f136")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << process >> then instantiate a {@link Process} proxy.
     * 
     * @return a {@link Process} proxy on the created {@link Classifier}.
     */
    @objid ("ab9f62a9-93f7-487e-8bf8-370c9fe8987f")
    public static Process create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME);
        return Process.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link Classifier} stereotyped << process >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Process} proxy or <i>null</i>.
     */
    @objid ("226a20c3-c82d-4c28-bf8c-882af571a239")
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
    @objid ("cdf74021-472e-41d9-b110-ab86f60f8ca6")
    public static Process safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Process.canInstantiate(obj))
        	return new Process(obj);
        else
        	throw new IllegalArgumentException("Process: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3ba345e7-cfa8-4070-bdef-59c936759c74")
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
    @objid ("2e8408eb-9fd8-45f6-a35e-1c60af3231a5")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("9c98b98f-71dd-4c9a-bf2c-9f29b9f03ec1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6d899d75-21c5-4d76-8cbd-35002c3d0e4e")
    protected Process(Classifier elt) {
        this.elt = elt;
    }

    @objid ("3f8020b0-6840-4956-b848-948e2dcce204")
    public static final class MdaTypes {
        @objid ("7c28c25c-d86b-4608-adc3-b8f0ed7f69fe")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("de0f0c40-4bed-4508-b7fd-7da2ed951367")
        private static Stereotype MDAASSOCDEP;

        @objid ("56d658bb-70fe-4fc6-a807-2d4967a3f0f4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("102800ff-36cf-47cd-a9c2-af3597a7434e")
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
