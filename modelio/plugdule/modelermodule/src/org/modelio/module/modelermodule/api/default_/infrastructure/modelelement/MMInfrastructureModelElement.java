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
    @objid ("ea1b4060-3625-47dc-9b95-82659c754198")
    public static final String NODOC_TAGTYPE = "nodoc";

    @objid ("94cae03d-e364-4ca2-b731-0c2b5bf62d30")
    public static final String COMMENT_NOTETYPE = "comment";

    @objid ("573d7047-d9b1-4ffb-8245-b24407aa4ebc")
    public static final String DESCRIPTION_NOTETYPE = "description";

    @objid ("2e952d37-68f4-4ff3-af29-3406260e4f1b")
    public static final String UNDEFINED_NOTETYPE = "undefined";

    /**
     * The underlying {@link ModelElement} represented by this proxy, never null.
     */
    @objid ("e9c006dc-1c55-41a3-84c4-8cd89c7a365a")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMInfrastructureModelElement proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelElement}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("5af6da5f-3c57-4d08-b8b1-c675c8e91e1e")
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
    @objid ("1e1e8878-41e5-4b0f-aebc-f3612db141a3")
    public static MMInfrastructureModelElement instantiate(ModelElement obj) {
        return MMInfrastructureModelElement.canInstantiate(obj) ? new MMInfrastructureModelElement(obj) : null;
    }

    @objid ("ead245ca-e038-4837-8fcd-b32420facd4c")
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
    @objid ("588450b7-d011-4a46-9b7a-d6af8f94b386")
    public String getCommentNote() {
        return this.elt.getNoteContent(MMInfrastructureModelElement.MdaTypes.COMMENT_NOTETYPE_ELT);
    }

    /**
     * Getter for note 'description'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("c35100c4-f8a6-4ad5-bfe0-6dac1a763adc")
    public String getDescriptionNote() {
        return this.elt.getNoteContent(MMInfrastructureModelElement.MdaTypes.DESCRIPTION_NOTETYPE_ELT);
    }

    /**
     * Get the underlying {@link ModelElement}. 
     * @return the ModelElement represented by this proxy, never null.
     */
    @objid ("1abf3f1c-fd22-492e-8c07-6da86e8558dd")
    public ModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for note 'undefined'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("f3624215-921c-4679-93fc-5153ded43609")
    public String getUndefinedNote() {
        return this.elt.getNoteContent(MMInfrastructureModelElement.MdaTypes.UNDEFINED_NOTETYPE_ELT);
    }

    @objid ("8546b43a-3388-4dcc-9659-f3df3a8c2b58")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'nodoc'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("3d10e2e0-6c87-4c1e-ad3e-21cfc1264671")
    public boolean isNodoc() {
        return this.elt.isTagged(MMInfrastructureModelElement.MdaTypes.NODOC_TAGTYPE_ELT);
    }

    /**
     * Setter for note 'comment'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("d8302eb3-a738-462e-8a32-ea5d4a482bbb")
    public void setCommentNote(String value) {
        this.elt.putNoteContent(MMInfrastructureModelElement.MdaTypes.COMMENT_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'description'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("36fe6a33-1eed-4b35-94de-218ae742687b")
    public void setDescriptionNote(String value) {
        this.elt.putNoteContent(MMInfrastructureModelElement.MdaTypes.DESCRIPTION_NOTETYPE_ELT, value);
    }

    /**
     * Setter for boolean property 'nodoc'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("0a310c5a-03f9-4c0e-8da4-97add86c74b1")
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
    @objid ("9e842f34-6197-48e5-ae6f-b209d30fb052")
    public void setUndefinedNote(String value) {
        this.elt.putNoteContent(MMInfrastructureModelElement.MdaTypes.UNDEFINED_NOTETYPE_ELT, value);
    }

    @objid ("c6b570a2-f115-4169-9557-4e438c2c5f2f")
    protected MMInfrastructureModelElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("09cad016-8b4d-4c38-a591-02566b729239")
    public static final class MdaTypes {
        @objid ("7ef0da14-2a27-49a0-a706-9e1f08b2022c")
        public static TagType NODOC_TAGTYPE_ELT;

        @objid ("fbebf697-4086-4ebb-abff-a2b75813de7b")
        public static NoteType DESCRIPTION_NOTETYPE_ELT;

        @objid ("34a4f9fe-87ac-48f4-8948-386c4421a89f")
        public static NoteType UNDEFINED_NOTETYPE_ELT;

        @objid ("3a0f2331-6ae9-464b-a65c-fef9c7627fb5")
        public static NoteType COMMENT_NOTETYPE_ELT;

        @objid ("8bd335d0-f129-4d47-aa48-43bbdfd2f4eb")
        private static Stereotype MDAASSOCDEP;

        @objid ("a73d2fcf-9a6a-41ac-95fb-d56dbb06b808")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e08a6f3b-4073-45f5-86ec-7ffba67d64dc")
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
