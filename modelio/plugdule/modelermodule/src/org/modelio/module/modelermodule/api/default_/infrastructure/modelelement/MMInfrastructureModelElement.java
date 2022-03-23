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
package org.modelio.module.modelermodule.api.default_.infrastructure.modelelement;

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
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ModelElement} metaclass.
 * <p>Description:
 * <br/><i>MMInfrastructureModelElement</i></p>
 */
@objid ("888cc0d8-e375-4a2b-9aa5-10f145741ded")
public class MMInfrastructureModelElement {
    @objid ("5893eab1-4ca7-4eaf-955e-bc1baa699c2a")
    public static final String NODOC_TAGTYPE = "nodoc";

    @objid ("6cd2b16d-1631-48fb-8e76-64c878223cee")
    public static final String COMMENT_NOTETYPE = "comment";

    @objid ("2ba2542f-e6d2-453b-aec7-66261c440ec8")
    public static final String DESCRIPTION_NOTETYPE = "description";

    @objid ("67a370f7-ffa5-4fed-99bc-6c56cacf6497")
    public static final String UNDEFINED_NOTETYPE = "undefined";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("60b11620-bb72-41b7-b0af-4fa80f5fcb8d")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMInfrastructureModelElement proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("73fd0e34-655d-4a10-815d-a08f14f562b0")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof ModelElement);
    }

    /**
     * Tries to instantiate a {@link MMInfrastructureModelElement} proxy from a {@link ModelElement} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelElement
     * @return a {@link MMInfrastructureModelElement} proxy or <i>null</i>.
     */
    @objid ("3ebd4766-f007-45d3-bda7-ada619d8182a")
    public static MMInfrastructureModelElement instantiate(ModelElement obj) {
        return MMInfrastructureModelElement.canInstantiate(obj) ? new MMInfrastructureModelElement(obj) : null;
    }

    @objid ("5e5978ec-845a-44c6-b52e-9a080a42b1d9")
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
        MMInfrastructureModelElement other = (MMInfrastructureModelElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for note 'comment'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("244130d8-1613-4fd4-95b2-27d6e60d9027")
    public String getCommentNote() {
        return this.elt.getNoteContent(MMInfrastructureModelElement.MdaTypes.COMMENT_NOTETYPE_ELT);
    }

    /**
     * Getter for note 'description'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("58b44109-e0f9-45e3-a719-0cfcb2c62be0")
    public String getDescriptionNote() {
        return this.elt.getNoteContent(MMInfrastructureModelElement.MdaTypes.DESCRIPTION_NOTETYPE_ELT);
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("6ce23044-5cf9-4439-b6ec-7806b33dd077")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for note 'undefined'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("922dda55-df50-44c7-a14d-8c102cfdeedf")
    public String getUndefinedNote() {
        return this.elt.getNoteContent(MMInfrastructureModelElement.MdaTypes.UNDEFINED_NOTETYPE_ELT);
    }

    @objid ("f4b8bfa3-e5ea-4d47-8a1d-e37906e6c3af")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'nodoc'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e75b1117-553f-4b97-8191-c8143d26c910")
    public boolean isNodoc() {
        return this.elt.isTagged(MMInfrastructureModelElement.MdaTypes.NODOC_TAGTYPE_ELT);
    }

    /**
     * Setter for note 'comment'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("e212cbe9-69d4-45a0-8cc7-585bb66c3d5e")
    public void setCommentNote(String value) {
        this.elt.putNoteContent(MMInfrastructureModelElement.MdaTypes.COMMENT_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'description'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("71ad8144-75d1-4f00-8ac5-3c20602ab292")
    public void setDescriptionNote(String value) {
        this.elt.putNoteContent(MMInfrastructureModelElement.MdaTypes.DESCRIPTION_NOTETYPE_ELT, value);
    }

    /**
     * Setter for boolean property 'nodoc'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("0572a990-75a0-49be-99b5-b90dea63cc50")
    public void setNodoc(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMInfrastructureModelElement.MdaTypes.NODOC_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMInfrastructureModelElement.MdaTypes.NODOC_TAGTYPE_ELT);
    }

    /**
     * Setter for note 'undefined'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("db9e95b8-ae9a-4980-8612-c25b18697deb")
    public void setUndefinedNote(String value) {
        this.elt.putNoteContent(MMInfrastructureModelElement.MdaTypes.UNDEFINED_NOTETYPE_ELT, value);
    }

    @objid ("68d4cdfa-85a9-451d-b661-4f69a576fe35")
    protected  MMInfrastructureModelElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("09cad016-8b4d-4c38-a591-02566b729239")
    public static final class MdaTypes {
        @objid ("92fd3a5d-b992-43d6-957b-0afbeefce901")
        public static TagType NODOC_TAGTYPE_ELT;

        @objid ("0f914dd1-645d-4efe-8c1a-5e6c266797ec")
        public static NoteType DESCRIPTION_NOTETYPE_ELT;

        @objid ("f52fa789-4d68-4a92-a8e0-4b87d49bd4e6")
        public static NoteType UNDEFINED_NOTETYPE_ELT;

        @objid ("1bde2452-f636-4358-9b34-a99a233a375b")
        public static NoteType COMMENT_NOTETYPE_ELT;

        @objid ("88e88145-92fa-4270-9895-5eef5322682c")
        private static Stereotype MDAASSOCDEP;

        @objid ("cfd40fd9-a89c-425c-9946-ca9373d1df71")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("974898b8-b63f-4491-ad13-0cbd87594224")
        public static void init(IModuleContext ctx) {
            NODOC_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "c40494de-2182-11e1-85ce-002564c97630");
            DESCRIPTION_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00000000-0000-3e81-0000-000000000000");
            UNDEFINED_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00000000-0000-3e83-0000-000000000000");
            COMMENT_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00000000-0000-3e80-0000-000000000000");
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
