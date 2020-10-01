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
package org.modelio.module.modelermodule.api.default_.standard.behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
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
 * Proxy class to handle a {@link Behavior} metaclass.
 * <p>Description:
 * <br/><i>MMStandardBehavior</i></p>
 */
@objid ("043c0bab-0bc3-4937-ae36-b6b0e2034c97")
public class MMStandardBehavior {
    @objid ("6e5a38d4-402e-4ee9-b529-a14692e41565")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link Behavior} represented by this proxy, never null.
     */
    @objid ("175064f6-a64f-48ea-9f3c-9e22807adf73")
    protected final Behavior elt;

    /**
     * Tells whether a {@link MMStandardBehavior proxy} can be instantiated from a {@link MObject} checking it is a {@link Behavior}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7388fef8-5ce1-4d3d-9950-7badd90e41ca")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Behavior);
    }

    /**
     * Tries to instantiate a {@link MMStandardBehavior} proxy from a {@link Behavior} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Behavior
     * @return a {@link MMStandardBehavior} proxy or <i>null</i>.
     */
    @objid ("fc8594fc-c4d6-4a91-8e41-a8eeb67c91ce")
    public static MMStandardBehavior instantiate(Behavior obj) {
        return MMStandardBehavior.canInstantiate(obj) ? new MMStandardBehavior(obj) : null;
    }

    @objid ("f924a8ef-2ae0-43d5-958f-f8c75dc0a6cd")
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
        MMStandardBehavior other = (MMStandardBehavior) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Behavior}. 
     * @return the Behavior represented by this proxy, never null.
     */
    @objid ("a55d4533-cd5f-4fee-850f-b8cf1eb637b6")
    public Behavior getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("3a07cde0-1367-4ab8-ac12-c877a94792b7")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardBehavior.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("435674b9-67e3-4c9a-a564-5c75f70a2f78")
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
    @objid ("b8083b3f-fdba-42d7-b8a9-7a1073464e55")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardBehavior.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("de8dd78f-b6b4-480d-a593-0bae832628cc")
    protected MMStandardBehavior(Behavior elt) {
        this.elt = elt;
    }

    @objid ("4285c834-40c4-46e2-8ad4-8e9e1a317a1e")
    public static final class MdaTypes {
        @objid ("f372d33e-4a47-4b9b-a0da-ce3b5f9f12ef")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("00341356-e006-447b-b2c9-0c66ec3c3915")
        private static Stereotype MDAASSOCDEP;

        @objid ("cdbe34e4-f44f-4425-93e1-dc3fde56b8af")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2153751a-a25c-439b-87a2-57404c1f4d03")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "aec5d702-6a89-471f-a273-22561cc5ed18");
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
