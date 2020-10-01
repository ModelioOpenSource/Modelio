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
    @objid ("5f330d77-0a80-4ce8-8e30-d8365678398f")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    /**
     * The underlying {@link CollaborationUse} represented by this proxy, never null.
     */
    @objid ("6788db2f-2210-47d8-b1d7-0579619e6dbd")
    protected final CollaborationUse elt;

    /**
     * Tells whether a {@link MMStandardCollaborationUse proxy} can be instantiated from a {@link MObject} checking it is a {@link CollaborationUse}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9f3e1f82-8700-43c7-9f55-13574f262c54")
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
    @objid ("23fe0ff3-7b3b-4331-8dd3-98bf5c3f29b7")
    public static MMStandardCollaborationUse instantiate(CollaborationUse obj) {
        return MMStandardCollaborationUse.canInstantiate(obj) ? new MMStandardCollaborationUse(obj) : null;
    }

    @objid ("39127451-b40e-45d2-b04f-d6b713e32c03")
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
    @objid ("b9d32946-478f-4ae5-9119-8cfe35b57e9e")
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
    @objid ("d5afd90e-b813-4d33-8d42-6e306dd5a2d8")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardCollaborationUse.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("7a82c771-5acf-480c-a1d0-2acb9b510657")
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
    @objid ("75c1914f-e7e5-41f8-b34c-4e70617046c1")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardCollaborationUse.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("de447eef-5978-4338-8d15-2cabd6d3ae0b")
    protected MMStandardCollaborationUse(CollaborationUse elt) {
        this.elt = elt;
    }

    @objid ("ef03759f-6b6c-40ee-8af0-d55ea8529562")
    public static final class MdaTypes {
        @objid ("e4f74a2b-f9b7-497f-8ee2-966e030c078a")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("d962eb85-8708-4357-893d-1d6202fdde56")
        private static Stereotype MDAASSOCDEP;

        @objid ("b3b14973-bfbb-4828-bda9-5ec2cf29a155")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4dac7399-498a-4b58-86c8-28e908cc7508")
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
