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
    @objid ("65856075-40a7-449f-9448-d9d15fcfc61c")
    public static final String PERSISTENCE_TAGTYPE = "persistence";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("a35a3781-bc83-43d7-9f41-3ba06bd7a1fc")
    protected final Classifier elt;

    /**
     * Tells whether a {@link MMStandardClassifier proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a793a347-0a54-4326-923f-db787b514031")
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
    @objid ("7ce3cecd-a4b4-4476-9638-6108002322b0")
    public static MMStandardClassifier instantiate(Classifier obj) {
        return MMStandardClassifier.canInstantiate(obj) ? new MMStandardClassifier(obj) : null;
    }

    @objid ("8e34bdfc-b1e6-43ce-806f-d0ac6e6b1dbe")
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
    @objid ("e2b72948-5632-4d6f-bd25-90084e85199e")
    public Classifier getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'persistence'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("dcedb078-a9c4-4110-86e6-ce468956bc55")
    public List<String> getPersistence() {
        return this.elt.getTagValues(MMStandardClassifier.MdaTypes.PERSISTENCE_TAGTYPE_ELT);
    }

    @objid ("84fe748c-d3f9-436c-a7d7-ee3e30d0ebb0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for List<String> property 'persistence'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7bb9d905-d35b-4e4f-9d4a-0803566b707b")
    public void setPersistence(List<String> values) {
        this.elt.putTagValues(MMStandardClassifier.MdaTypes.PERSISTENCE_TAGTYPE_ELT, values);
    }

    @objid ("2fb1ee81-5b76-4cdc-9da8-2c80e716ee5f")
    protected MMStandardClassifier(Classifier elt) {
        this.elt = elt;
    }

    @objid ("b0dd20e3-d783-4cce-98a8-448a6ff4bcbc")
    public static final class MdaTypes {
        @objid ("9ea3f7a6-1a34-4cf5-b139-b773c64315a5")
        public static TagType PERSISTENCE_TAGTYPE_ELT;

        @objid ("bae1c5ec-c387-4996-92f9-810ecc9220d6")
        private static Stereotype MDAASSOCDEP;

        @objid ("887ca43a-6ed8-414b-96d3-2787768e0955")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2ad33bd4-0e02-426a-b8ad-98958ae51c38")
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
