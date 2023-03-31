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
    @objid ("076b0f79-dfe9-4e73-983a-fbacd0e6f3e8")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link ActivityNode} represented by this proxy, never null.
     */
    @objid ("2a7dfb2b-f190-443c-96df-9a1e515dd13e")
    protected final ActivityNode elt;

    /**
     * Tells whether a {@link MMStandardActivityNode proxy} can be instantiated from a {@link MObject} checking it is a {@link ActivityNode}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("6c2e28ff-08cb-4f5c-9655-9c35998a2083")
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
    @objid ("4202f4c2-0033-4d89-9e6a-c6d1f01327a6")
    public static MMStandardActivityNode instantiate(ActivityNode obj) {
        return MMStandardActivityNode.canInstantiate(obj) ? new MMStandardActivityNode(obj) : null;
    }

    @objid ("fa06f184-e558-4b36-b730-993e2be57f5f")
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
    @objid ("b937bb08-b563-4be6-98c9-86fe6e3b81e2")
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
    @objid ("7e710e03-95f2-4a3b-b2f3-2d23e9ddd588")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardActivityNode.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("7e34be2e-79f6-478f-b704-549158906833")
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
    @objid ("3734bb05-7e72-49f9-bb02-5592aa2c526d")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardActivityNode.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("45c1a336-29ff-4166-b1d3-3d7c277e3223")
    protected  MMStandardActivityNode(ActivityNode elt) {
        this.elt = elt;
    }

    @objid ("c5234589-d061-4954-bf1d-94d265683b1a")
    public static final class MdaTypes {
        @objid ("ef1fa7b2-dbb0-4477-bb31-35972d3e1ddb")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("01297e26-99ca-4fe4-9a24-d479c1d6420f")
        private static Stereotype MDAASSOCDEP;

        @objid ("111511c5-b870-46ce-9df5-b33862fa71db")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6d15ccdf-2b83-4104-ab2c-748ebe8eec9a")
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
