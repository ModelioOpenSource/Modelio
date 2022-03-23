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
    @objid ("c6c08dc7-a624-48c9-ab3f-588c829fc4e7")
    public static final String CAUSE_DEPTH_TAGTYPE = "cause_depth";

    @objid ("fb9c9283-8985-4e3d-befc-85ff71abfe03")
    public static final String CONSEQUENCE_DEPTH_TAGTYPE = "consequence_depth";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("76b0d2a4-c10a-42f9-857a-b3d63c95f93d")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MMInfrastructureDependency proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d2921539-8742-4ad7-8cce-6417552b7c64")
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
    @objid ("74da1b3c-7182-470f-9562-cb85826170c3")
    public static MMInfrastructureDependency instantiate(Dependency obj) {
        return MMInfrastructureDependency.canInstantiate(obj) ? new MMInfrastructureDependency(obj) : null;
    }

    @objid ("02624e7f-ea45-4dec-961e-71fb2f5767a6")
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
    @objid ("d5ba7ea0-e7b4-4f4c-bcbb-3d1a4b329f67")
    public String getCause_depth() {
        return this.elt.getTagValue(MMInfrastructureDependency.MdaTypes.CAUSE_DEPTH_TAGTYPE_ELT);
    }

    /**
     * Getter for string property 'consequence_depth'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e6779617-f93b-46a8-ab7a-e446324bc649")
    public String getConsequence_depth() {
        return this.elt.getTagValue(MMInfrastructureDependency.MdaTypes.CONSEQUENCE_DEPTH_TAGTYPE_ELT);
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("fc61a553-63c1-4426-87e1-3daccc388d9c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("d607dc8a-ddde-42c0-86eb-906b44623aed")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'cause_depth'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("de60f1ac-e60c-430e-ba68-303bf0c7a3ce")
    public void setCause_depth(String value) {
        this.elt.putTagValue(MMInfrastructureDependency.MdaTypes.CAUSE_DEPTH_TAGTYPE_ELT, value);
    }

    /**
     * Setter for string property 'consequence_depth'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("45bde2d5-7b6a-40cd-b82c-69862703cb8b")
    public void setConsequence_depth(String value) {
        this.elt.putTagValue(MMInfrastructureDependency.MdaTypes.CONSEQUENCE_DEPTH_TAGTYPE_ELT, value);
    }

    @objid ("b5397608-ac8c-4c2e-8dec-46c8bcb21938")
    protected  MMInfrastructureDependency(Dependency elt) {
        this.elt = elt;
    }

    @objid ("d5e43762-4cac-4fda-a3d6-c611ca0ac6ea")
    public static final class MdaTypes {
        @objid ("86eabec8-122e-44fd-9216-bfaa0d2ebb6c")
        public static TagType CAUSE_DEPTH_TAGTYPE_ELT;

        @objid ("31d02ce4-d03c-418b-90e2-5be87940d28e")
        public static TagType CONSEQUENCE_DEPTH_TAGTYPE_ELT;

        @objid ("e971a650-a3aa-4be4-8cd0-5cf8622633a3")
        private static Stereotype MDAASSOCDEP;

        @objid ("b8224422-4e59-4f5c-86da-128e6c4b0b79")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c9242e24-73a7-4b42-abfa-8581fadde744")
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
