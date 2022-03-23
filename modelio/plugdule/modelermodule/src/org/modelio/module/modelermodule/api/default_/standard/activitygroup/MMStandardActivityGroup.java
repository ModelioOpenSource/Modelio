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
package org.modelio.module.modelermodule.api.default_.standard.activitygroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
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
 * Proxy class to handle a {@link ActivityGroup} metaclass.
 * <p>Description:
 * <br/><i>MMStandardActivityGroup</i></p>
 */
@objid ("0bddf189-e8ce-4d25-a76d-638e2465787a")
public class MMStandardActivityGroup {
    @objid ("8115d8d4-8d96-4ba8-b62a-2da9e0461868")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link ActivityGroup} represented by this proxy, never null.
     */
    @objid ("9f6ab43a-6396-44ba-8c86-778e7439b4db")
    protected final ActivityGroup elt;

    /**
     * Tells whether a {@link MMStandardActivityGroup proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityGroup}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0a3fe672-d6d2-42c2-9ca2-d5be49666e66")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof ActivityGroup);
    }

    /**
     * Tries to instantiate a {@link MMStandardActivityGroup} proxy from a {@link ActivityGroup} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityGroup
     * @return a {@link MMStandardActivityGroup} proxy or <i>null</i>.
     */
    @objid ("8057662d-eaa4-4382-baff-be302e8f9dfb")
    public static MMStandardActivityGroup instantiate(ActivityGroup obj) {
        return MMStandardActivityGroup.canInstantiate(obj) ? new MMStandardActivityGroup(obj) : null;
    }

    @objid ("2d31712a-ec75-46b9-860f-3f173662e046")
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
        MMStandardActivityGroup other = (MMStandardActivityGroup) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ActivityGroup}. 
     * @return the ActivityGroup represented by this proxy, never null.
     */
    @objid ("7528fb66-0657-4ba2-a48f-0fa648f00d6d")
    public ActivityGroup getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("3feaa27f-27a2-4418-adfd-f022f340ecaf")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardActivityGroup.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("aeec09c6-7055-44a8-abdf-79cc9c0c8d86")
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
    @objid ("fbbe807a-091b-46fd-800a-c1b6b80299c2")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardActivityGroup.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("c33f5d10-a415-4de0-99f8-de5179ece08d")
    protected  MMStandardActivityGroup(ActivityGroup elt) {
        this.elt = elt;
    }

    @objid ("343ed21d-76d6-48fb-b12e-4ffab5eacf51")
    public static final class MdaTypes {
        @objid ("24c53769-0fbd-4649-bc6f-fdeabdd4964a")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("a7417216-16c7-4dfe-a152-66fa3f9575a9")
        private static Stereotype MDAASSOCDEP;

        @objid ("1f896a0d-c8c0-43f5-b5ae-9873e499023f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("837b16f6-19cc-4b31-b429-52fb67c63f01")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "1d89eb4d-8c5e-4ce0-b836-0d4f2d4a86e1");
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
