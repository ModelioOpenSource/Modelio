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
    @objid ("db42218f-6f6a-4b9c-b359-ef34d3eee27c")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link ActivityGroup} represented by this proxy, never null.
     */
    @objid ("a7ca63a6-6e13-490d-81c2-34edbb9ffafc")
    protected final ActivityGroup elt;

    /**
     * Tells whether a {@link MMStandardActivityGroup proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityGroup}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("88aa29a1-1402-4a80-8e9d-11fd22776eec")
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
    @objid ("1450f8bf-8081-4e47-95a8-7bb840ab8c23")
    public static MMStandardActivityGroup instantiate(ActivityGroup obj) {
        return MMStandardActivityGroup.canInstantiate(obj) ? new MMStandardActivityGroup(obj) : null;
    }

    @objid ("82216940-7eb1-4d39-b110-a20808647afd")
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
    @objid ("b6403018-4c39-409b-8cbf-39716e7d3769")
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
    @objid ("45c25923-237d-4c2e-9556-3fe0ff3cf777")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardActivityGroup.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("04517e8b-8599-4636-8456-376734548c0c")
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
    @objid ("aa8de955-e664-47cf-9e53-5afac7f2df8d")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardActivityGroup.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("06655dd9-f263-4905-a00c-7e92eefea1c9")
    protected MMStandardActivityGroup(ActivityGroup elt) {
        this.elt = elt;
    }

    @objid ("343ed21d-76d6-48fb-b12e-4ffab5eacf51")
    public static final class MdaTypes {
        @objid ("6ee4a817-7a85-4cf1-a815-442b292dd61f")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("475506fd-8e95-4a30-b979-276e158dd016")
        private static Stereotype MDAASSOCDEP;

        @objid ("567e1151-4673-42cf-a37f-24467bbab070")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("77308ae5-7076-4441-b8a6-d2f33576adaf")
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
