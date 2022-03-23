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
package org.modelio.module.modelermodule.api.default_.standard.collaborationuse;

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
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link CollaborationUse} metaclass.
 * <p>Description:
 * <br/><i>MMStandardCollaborationUse</i></p>
 */
@objid ("b4012f6b-b988-4bdb-843b-d0a9344ea3fa")
public class MMStandardCollaborationUse {
    @objid ("1971150a-5f02-4f9e-8638-0daa5c71b132")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link CollaborationUse} represented by this proxy, never null.
     */
    @objid ("66b45b41-5e2b-48a7-b7a2-3439e4317f0f")
    protected final CollaborationUse elt;

    /**
     * Tells whether a {@link MMStandardCollaborationUse proxy} can be instantiated from a {@link MObject} checking it is a {@link CollaborationUse}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b366d5e5-efda-4cff-a532-dcd7c76272d8")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof CollaborationUse);
    }

    /**
     * Tries to instantiate a {@link MMStandardCollaborationUse} proxy from a {@link CollaborationUse} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a CollaborationUse
     * @return a {@link MMStandardCollaborationUse} proxy or <i>null</i>.
     */
    @objid ("c4f2e3b7-6831-444c-81c7-b3624a1219d6")
    public static MMStandardCollaborationUse instantiate(CollaborationUse obj) {
        return MMStandardCollaborationUse.canInstantiate(obj) ? new MMStandardCollaborationUse(obj) : null;
    }

    @objid ("fd82e792-cfa5-40e3-bd68-9539e9f39173")
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
        MMStandardCollaborationUse other = (MMStandardCollaborationUse) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link CollaborationUse}. 
     * @return the CollaborationUse represented by this proxy, never null.
     */
    @objid ("2bf75b78-84fa-4b78-8d76-340f62a26011")
    public CollaborationUse getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("143d9fd1-48cb-4a83-a5c9-7d6ca67c4645")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardCollaborationUse.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("1d4d0f3d-bfea-439a-90f9-79989ec7d185")
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
    @objid ("55f52c12-e907-4bde-8bc8-b1578948ecd6")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardCollaborationUse.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("94f882af-3bd3-4e9f-9bc3-b1b6a5157a8e")
    protected  MMStandardCollaborationUse(CollaborationUse elt) {
        this.elt = elt;
    }

    @objid ("ef03759f-6b6c-40ee-8af0-d55ea8529562")
    public static final class MdaTypes {
        @objid ("e5127d91-c68a-490e-8ff5-45965f7d5af1")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("ed1a7e6b-015b-4181-bccb-f1a11a4c3388")
        private static Stereotype MDAASSOCDEP;

        @objid ("3b060b8f-d2ef-41ea-8310-69a2d3904a17")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ebba2c5d-fbab-4f95-86d4-b31ab4ed4457")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "885e070a-b0d2-4da8-92a5-8ecd396f4afe");
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
