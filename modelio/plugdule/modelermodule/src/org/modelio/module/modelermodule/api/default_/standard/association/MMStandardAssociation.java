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
package org.modelio.module.modelermodule.api.default_.standard.association;

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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} metaclass.
 * <p>Description:
 * <br/><i>MMStandardAssociation</i></p>
 */
@objid ("95d5c7ab-0cac-40f5-acd4-31f25a45ec38")
public class MMStandardAssociation {
    @objid ("6d3de253-f377-485e-bc40-3319d861115c")
    public static final String PERSISTENCE_TAGTYPE = "persistence";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("84c2ec12-c72a-4dec-80a8-09a0fa0ccbf5")
    protected final Association elt;

    /**
     * Tells whether a {@link MMStandardAssociation proxy} can be instantiated from a {@link MObject} checking it is a {@link Association}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("f42f6ede-5176-4550-939c-2da24f88a1a1")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Association);
    }

    /**
     * Tries to instantiate a {@link MMStandardAssociation} proxy from a {@link Association} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link MMStandardAssociation} proxy or <i>null</i>.
     */
    @objid ("f4c43372-76e4-4b3b-9d4c-d341e64d8e30")
    public static MMStandardAssociation instantiate(Association obj) {
        return MMStandardAssociation.canInstantiate(obj) ? new MMStandardAssociation(obj) : null;
    }

    @objid ("d8b2305f-7776-4b68-83de-bb186bf38655")
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
        MMStandardAssociation other = (MMStandardAssociation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("428b9833-9a03-4654-ad87-a8e532b36ef4")
    public Association getElement() {
        return this.elt;
    }

    /**
     * Getter for List<String> property 'persistence'
     * <p>Property description:<br/>
     * <i></i></p>
     */
    @objid ("49fbccd0-c250-443a-a371-0faca0a29e78")
    public List<String> getPersistence() {
        return this.elt.getTagValues(MMStandardAssociation.MdaTypes.PERSISTENCE_TAGTYPE_ELT);
    }

    @objid ("6f09c892-9152-40f4-b1b3-c58f1d2eafa4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for List<String> property 'persistence'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("9043ec13-c329-4a65-b8db-bb4de26d2c5c")
    public void setPersistence(List<String> values) {
        this.elt.putTagValues(MMStandardAssociation.MdaTypes.PERSISTENCE_TAGTYPE_ELT, values);
    }

    @objid ("3d6b95d0-1bf6-4a46-9379-971840907731")
    protected  MMStandardAssociation(Association elt) {
        this.elt = elt;
    }

    @objid ("abb2b0b8-5b9d-4464-b11c-f433e23036af")
    public static final class MdaTypes {
        @objid ("493da7c5-e267-44e2-aa68-de5ec1d25cbf")
        public static TagType PERSISTENCE_TAGTYPE_ELT;

        @objid ("91dfb3fd-c48f-4f5a-bc45-d5a3fee20ee0")
        private static Stereotype MDAASSOCDEP;

        @objid ("4f8825d5-87ea-4cda-85b4-a03c61ddaca6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ebad3c83-fab6-4218-b567-f932a2c92aa1")
        public static void init(IModuleContext ctx) {
            PERSISTENCE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00700680-0000-01e9-0000-000000000000");
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
