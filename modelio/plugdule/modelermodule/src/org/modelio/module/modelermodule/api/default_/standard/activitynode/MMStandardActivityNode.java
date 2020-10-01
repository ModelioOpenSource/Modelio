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
package org.modelio.module.modelermodule.api.default_.standard.activitynode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
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
 * Proxy class to handle a {@link ActivityNode} metaclass.
 * <p>Description:
 * <br/><i>MMStandardActivityNode</i></p>
 */
@objid ("b6421fcb-7bab-473d-916e-1ca2b76111bf")
public class MMStandardActivityNode {
    @objid ("41f15020-daef-44d8-bba6-6f2a8f90db4f")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("c89a2bfc-26b1-4dd9-9adf-e7dc5c085401")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link MMStandardActivityNode proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("65c815fd-3f79-43b8-b35d-df0006ad7c3c")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof ActivityNode);
    }

    /**
     * Tries to instantiate a {@link MMStandardActivityNode} proxy from a {@link ActivityNode} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ActivityNode
     * @return a {@link MMStandardActivityNode} proxy or <i>null</i>.
     */
    @objid ("98fe2e6c-adfb-411c-b1d7-4faef74175b6")
    public static MMStandardActivityNode instantiate(ActivityNode obj) {
        return MMStandardActivityNode.canInstantiate(obj) ? new MMStandardActivityNode(obj) : null;
    }

    @objid ("afbd2ec6-9a12-49d7-92e8-63acdeaefb36")
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
        MMStandardActivityNode other = (MMStandardActivityNode) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ActivityNode}. 
     * @return the ActivityNode represented by this proxy, never null.
     */
    @objid ("0fa29939-eca5-4396-84a4-4667bfda2ec6")
    public ActivityNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("fd8c52a9-cbdd-4fd4-9c46-6d2952059df5")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardActivityNode.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("19adc1c3-2133-48df-bc17-2183065e76a9")
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
    @objid ("e736b3b3-77fa-4eb2-b0e4-ba6ff86bcf73")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardActivityNode.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("0d88b8ca-47a6-41a5-a2f9-5814aa0bf80a")
    protected MMStandardActivityNode(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("c5234589-d061-4954-bf1d-94d265683b1a")
    public static final class MdaTypes {
        @objid ("f03e7354-76b6-4581-b70c-9c2e519d2da6")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("74f66ada-d7bb-458e-835a-ddc82e0712ea")
        private static Stereotype MDAASSOCDEP;

        @objid ("9b361cde-4dd0-4955-8e19-bd525136de17")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dafa4778-a60a-4e0c-84ed-e40c868cdfe3")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "c5770acc-29d9-49c6-9cd2-977b4474e48d");
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
