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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} metaclass.
 * <p>Description:
 * <br/><i>MMInfrastructureDependency</i></p>
 */
@objid ("22a9f638-1054-46a9-bc97-bf8c9c1092de")
public class MMInfrastructureDependency {
    @objid ("7dbbaba2-3d78-4804-845f-312814c279e1")
    public static final String CAUSE_DEPTH_TAGTYPE = "cause_depth";

    @objid ("8ee06f36-f780-4900-9e4c-0504bb42a788")
    public static final String CONSEQUENCE_DEPTH_TAGTYPE = "consequence_depth";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("6c5d1bac-eecb-4c4e-a9fb-cbdab52263a3")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MMInfrastructureDependency proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b7ed2bd2-0d72-4908-af39-713e94193aa3")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Dependency);
    }

    /**
     * Tries to instantiate a {@link MMInfrastructureDependency} proxy from a {@link Dependency} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link MMInfrastructureDependency} proxy or <i>null</i>.
     */
    @objid ("26e61065-5328-403a-a764-921325153d7e")
    public static MMInfrastructureDependency instantiate(Dependency obj) {
        return MMInfrastructureDependency.canInstantiate(obj) ? new MMInfrastructureDependency(obj) : null;
    }

    @objid ("c87582df-5fac-4240-a05f-7ab7348bd4ba")
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
        MMInfrastructureDependency other = (MMInfrastructureDependency) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for string property 'cause_depth'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("7d6726fe-a94e-44af-a5e0-7d630db42b1e")
    public String getCause_depth() {
        return this.elt.getTagValue(MMInfrastructureDependency.MdaTypes.CAUSE_DEPTH_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'consequence_depth'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6151397e-bd66-4d53-8328-cd73d8af683b")
    public String getConsequence_depth() {
        return this.elt.getTagValue(MMInfrastructureDependency.MdaTypes.CONSEQUENCE_DEPTH_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("6430bdf6-e260-4b0d-9523-769c272d20ae")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("fc533b25-e653-48ec-b0b1-d82d85550262")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'cause_depth'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("6300f204-d488-4a80-97ce-613fc502c31e")
    public void setCause_depth(String value) {
        this.elt.putTagValue(MMInfrastructureDependency.MdaTypes.CAUSE_DEPTH_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'consequence_depth'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("470d596c-3887-4fad-9d1a-ef4a8795390f")
    public void setConsequence_depth(String value) {
        this.elt.putTagValue(MMInfrastructureDependency.MdaTypes.CONSEQUENCE_DEPTH_TAGTYPE_ELT, value);
    }

    @objid ("6d1d2074-c2b1-42e3-bb49-a83df8a6cf34")
    protected MMInfrastructureDependency(Dependency elt) {
        this.elt = elt;
    }

    @objid ("d5e43762-4cac-4fda-a3d6-c611ca0ac6ea")
    public static final class MdaTypes {
        @objid ("5112815e-f5ea-4000-beb3-57eac263850f")
        public static TagType CAUSE_DEPTH_TAGTYPE_ELT;

        @objid ("3c6efc23-d49b-4a81-9f42-b7f015048e71")
        public static TagType CONSEQUENCE_DEPTH_TAGTYPE_ELT;

        @objid ("040b6f5f-f0f3-4bac-8aa5-3ffb11f22625")
        private static Stereotype MDAASSOCDEP;

        @objid ("23b7aa2e-e4e9-4596-98bd-05599fba4be8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("67171e63-354a-40f8-8d69-965e1b4a0c04")
        public static void init(IModuleContext ctx) {
            CAUSE_DEPTH_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "01ec2468-0000-0aba-0000-000000000000");
            CONSEQUENCE_DEPTH_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "01ec2468-0000-0abf-0000-000000000000");
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
