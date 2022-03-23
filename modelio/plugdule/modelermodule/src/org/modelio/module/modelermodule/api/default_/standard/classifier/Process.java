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
    @objid ("34628b56-1a24-400c-982f-0bb1d8ab3d04")
    public static final String STEREOTYPE_NAME = "process";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("bda06da0-4d01-45d5-859f-f8fc017aa582")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Process proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << process >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f6e4266e-3965-4784-ab47-e0524c611b00")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << process >> then instantiate a {@link Process} proxy.
     * 
     * @return a {@link Process} proxy on the created {@link Classifier}.
     */
    @objid ("d58b8848-da7a-4e0f-ba44-f5c809b63d52")
    public static Process create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Classifier");
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
    @objid ("89f14ff8-a742-4428-a339-73da8e18e7df")
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
    @objid ("f29e831e-a2e7-445c-98e4-4f32b1127a31")
    public static Process safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Process.canInstantiate(obj))
        	return new Process(obj);
        else
        	throw new IllegalArgumentException("Process: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("adbc02ba-496e-4d51-b7d8-d019027548da")
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
    @objid ("14cc99f7-4fec-402d-9788-50c42f66bcaa")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("d723181c-7bb2-48e3-9797-7d0fee582e45")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("bed9b255-936f-4eb9-ad8e-fa04d14e48b6")
    protected  Process(Classifier elt) {
        this.elt = elt;
    }

    @objid ("3f8020b0-6840-4956-b848-948e2dcce204")
    public static final class MdaTypes {
        @objid ("401b6235-4535-499a-aee6-0283cff9656d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3cc54343-6cb3-4329-bfda-dff76984e976")
        private static Stereotype MDAASSOCDEP;

        @objid ("7af92189-ade2-4281-acd0-1f6dcfe2e002")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e5361c53-2d27-4850-afa1-eac8852a8896")
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
