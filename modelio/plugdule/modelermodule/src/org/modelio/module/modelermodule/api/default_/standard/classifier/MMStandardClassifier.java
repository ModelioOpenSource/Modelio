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
import java.util.List;
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
 * Proxy class to handle a {@link Classifier} metaclass.
 * <p>Description:
 * <br/><i>MMStandardClassifier</i></p>
 */
@objid ("181e65e2-2003-426a-88d1-ffec1d011e64")
public class MMStandardClassifier {
    @objid ("3e0d54c2-c095-4ebd-a7a2-15739d2bee7e")
    public static final String PERSISTENCE_TAGTYPE = "persistence";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("8fe0e69b-c311-4126-9594-02239c115eef")
    protected final Classifier elt;

    /**
     * Tells whether a {@link MMStandardClassifier proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e39ff0fc-cf6e-43a4-a857-3c8fb67080b1")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Classifier);
    }

    /**
     * Tries to instantiate a {@link MMStandardClassifier} proxy from a {@link Classifier} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link MMStandardClassifier} proxy or <i>null</i>.
     */
    @objid ("992ab474-578b-42e8-8ccb-88518617bddc")
    public static MMStandardClassifier instantiate(Classifier obj) {
        return MMStandardClassifier.canInstantiate(obj) ? new MMStandardClassifier(obj) : null;
    }

    @objid ("ef9c6477-8037-4a1c-9408-47c5198db0a3")
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
        MMStandardClassifier other = (MMStandardClassifier) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("c3066169-a8dd-4aa4-9da8-df4ebbceadab")
    public Classifier getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'persistence'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("2e36b7e5-54a4-46e6-b6e4-740f2e3725e3")
    public List<String> getPersistence() {
        return this.elt.getTagValues(MMStandardClassifier.MdaTypes.PERSISTENCE_TAGTYPE_ELT);
    }

    @objid ("429b0669-360d-48f0-995e-f5737ae36beb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for List<String> property 'persistence'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("bfd62c6e-bffc-460a-ba92-d3780483c450")
    public void setPersistence(List<String> values) {
        this.elt.putTagValues(MMStandardClassifier.MdaTypes.PERSISTENCE_TAGTYPE_ELT, values);
    }

    @objid ("15d93639-e5da-4be7-9a38-30e8f9062c61")
    protected  MMStandardClassifier(Classifier elt) {
        this.elt = elt;
    }

    @objid ("b0dd20e3-d783-4cce-98a8-448a6ff4bcbc")
    public static final class MdaTypes {
        @objid ("cb4fbbf2-883b-498f-90fd-ee916b4f3a3f")
        public static TagType PERSISTENCE_TAGTYPE_ELT;

        @objid ("e5da0071-1b7d-47cd-ab71-ee0da5db7454")
        private static Stereotype MDAASSOCDEP;

        @objid ("a6227a22-c6d3-4217-88c7-68b6f3e8d884")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f9dd258c-3617-4480-ae53-4521837ef4d1")
        public static void init(IModuleContext ctx) {
            PERSISTENCE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00700680-0000-01ed-0000-000000000000");
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
