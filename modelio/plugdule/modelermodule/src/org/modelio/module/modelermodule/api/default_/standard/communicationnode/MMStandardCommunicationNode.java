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
package org.modelio.module.modelermodule.api.default_.standard.communicationnode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
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
 * Proxy class to handle a {@link CommunicationNode} metaclass.
 * <p>Description:
 * <br/><i>MMStandardCommunicationNode</i></p>
 */
@objid ("5f900373-caa2-4ed8-8500-e14484de600a")
public class MMStandardCommunicationNode {
    @objid ("3f93df3e-c71b-4074-b370-3624cf1d53c4")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link CommunicationNode} represented by this proxy, never null.
     */
    @objid ("838512b7-5814-4059-bb5e-a900961611e4")
    protected final CommunicationNode elt;

    /**
     * Tells whether a {@link MMStandardCommunicationNode proxy} can be instantiated from a {@link MObject} checking it is a {@link CommunicationNode}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5f46cf6d-bb65-4b9e-853c-8ddea1e2b333")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof CommunicationNode);
    }

    /**
     * Tries to instantiate a {@link MMStandardCommunicationNode} proxy from a {@link CommunicationNode} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a CommunicationNode
     * @return a {@link MMStandardCommunicationNode} proxy or <i>null</i>.
     */
    @objid ("f753af74-690e-4698-bc75-56812994c943")
    public static MMStandardCommunicationNode instantiate(CommunicationNode obj) {
        return MMStandardCommunicationNode.canInstantiate(obj) ? new MMStandardCommunicationNode(obj) : null;
    }

    @objid ("e5a3b60f-461d-456a-a425-377355d50b88")
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
        MMStandardCommunicationNode other = (MMStandardCommunicationNode) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link CommunicationNode}. 
     * @return the CommunicationNode represented by this proxy, never null.
     */
    @objid ("abf50441-124b-496b-a4a8-331c9a571936")
    public CommunicationNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("7307717a-7332-419f-8415-b9f74b97d69a")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardCommunicationNode.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("677534e0-ca61-489d-8b2c-be4674379d2e")
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
    @objid ("8c93c1f2-53df-4246-8f87-88fbced53cd9")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardCommunicationNode.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("9f109ae0-8ac3-4f56-b7e2-262805303def")
    protected  MMStandardCommunicationNode(CommunicationNode elt) {
        this.elt = elt;
    }

    @objid ("78ff58f8-0cc9-4b5d-ac11-18bcca756539")
    public static final class MdaTypes {
        @objid ("f052bf0d-3521-4804-8f66-e65fe3bd0227")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("e3f9b37e-00a7-49c4-a0f9-560494b9a0a6")
        private static Stereotype MDAASSOCDEP;

        @objid ("38c3d36c-87b9-419c-a75d-fd25c29bb095")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("46580270-22b6-43da-b0c0-d0a56cd75dfe")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "82b4ccc9-7f05-4ea8-9a48-7ad0f8dfc051");
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
