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
package org.modelio.module.modelermodule.api.default_.standard.clause;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
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
 * Proxy class to handle a {@link Clause} metaclass.
 * <p>Description:
 * <br/><i>MMStandardClause</i></p>
 */
@objid ("a662f962-162e-4f8b-ab17-db32712399dc")
public class MMStandardClause {
    @objid ("67a16501-528b-49b1-80f4-6ea96f5d511d")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Clause} represented by this proxy, never null.
     */
    @objid ("07466ab8-4f2a-41df-9079-6aca130e5cba")
    protected final Clause elt;

    /**
     * Tells whether a {@link MMStandardClause proxy} can be instantiated from a {@link MObject} checking it is a {@link Clause}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e8249463-67df-41e5-a5da-acaaf3fa5dfa")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Clause);
    }

    /**
     * Tries to instantiate a {@link MMStandardClause} proxy from a {@link Clause} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Clause
     * @return a {@link MMStandardClause} proxy or <i>null</i>.
     */
    @objid ("3d415c31-f5c9-41e1-bb61-f89ca4c9a082")
    public static MMStandardClause instantiate(Clause obj) {
        return MMStandardClause.canInstantiate(obj) ? new MMStandardClause(obj) : null;
    }

    @objid ("490bcbb8-dd8c-4ed2-ae92-c32e5a73a61c")
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
        MMStandardClause other = (MMStandardClause) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Clause}. 
     * @return the Clause represented by this proxy, never null.
     */
    @objid ("d739c2dd-2504-4919-a7c9-eb0d88a4d3b2")
    public Clause getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("816f5bc3-d781-4763-93f6-16c42d0c3654")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardClause.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("04bd4780-24a8-443d-9a94-5f1c11647116")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("026f0f09-e827-47b1-9bb3-e0ba64638b05")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardClause.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("4a753153-8ca3-49b1-8a2f-2660b869db59")
    protected  MMStandardClause(Clause elt) {
        this.elt = elt;
    }

    @objid ("918f2b5d-e8f4-4ac0-88b1-cd98b0cf24bf")
    public static final class MdaTypes {
        @objid ("b8e7eca8-2875-4430-b9bc-94157eb42e1c")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("9a1cbd69-c16b-432d-9027-d021aab3f843")
        private static Stereotype MDAASSOCDEP;

        @objid ("7245e563-a947-4aca-9491-8324c8e191a8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cc46cff8-7088-4f00-9151-a0bedb49b25f")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "bad0fec1-19d5-4d75-96f9-150f3deb59cf");
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
