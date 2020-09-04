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
 * Proxy class to handle a {@link Classifier} with << utility >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("361f955a-46bb-4174-ae99-420074e6c928")
public class Utility {
    @objid ("770f2316-71bd-429f-9661-f48d47f3f517")
    public static final String STEREOTYPE_NAME = "utility";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("3e29968e-817d-4d02-b81f-36e3f9bb6791")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Utility proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << utility >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e8d29535-accd-4807-95b1-0c807181e34b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Utility.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << utility >> then instantiate a {@link Utility} proxy.
     * 
     * @return a {@link Utility} proxy on the created {@link Classifier}.
     */
    @objid ("c52a8a97-ee9e-4f03-ae71-14150458e503")
    public static Utility create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Utility.STEREOTYPE_NAME);
        return Utility.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Utility} proxy from a {@link Classifier} stereotyped << utility >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Utility} proxy or <i>null</i>.
     */
    @objid ("53812cf2-0f32-49ab-b0fc-51e1a87b947d")
    public static Utility instantiate(Classifier obj) {
        return Utility.canInstantiate(obj) ? new Utility(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Utility} proxy from a {@link Classifier} stereotyped << utility >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Utility} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5b7ffec1-912e-43aa-b020-f277965e70ad")
    public static Utility safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Utility.canInstantiate(obj))
        	return new Utility(obj);
        else
        	throw new IllegalArgumentException("Utility: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c8032725-833a-4239-ae4f-bbe25de32f25")
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
        Utility other = (Utility) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("e96e2f7e-2104-4836-8850-1183540377d2")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("f710b2f0-3bbc-4769-ab63-19b8b7df6ece")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("513f5e09-e777-43ee-988c-c2ecb66b97ad")
    protected Utility(Classifier elt) {
        this.elt = elt;
    }

    @objid ("0a6fc122-40b5-4691-a722-7d1756955c7a")
    public static final class MdaTypes {
        @objid ("003d7182-c07c-463c-b943-f1882d619c20")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("44caf82c-4fd5-4d88-a423-3e4371988f9b")
        private static Stereotype MDAASSOCDEP;

        @objid ("9fee2a36-a983-4f0a-998b-cce527e0da22")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8cbdc28b-cbf7-4539-88dc-3c10b69fed3c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01bf-0000-000000000000");
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
