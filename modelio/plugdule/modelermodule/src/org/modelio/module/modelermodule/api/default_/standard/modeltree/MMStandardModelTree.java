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
package org.modelio.module.modelermodule.api.default_.standard.modeltree;

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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
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
 * Proxy class to handle a {@link ModelTree} metaclass.
 * <p>Description:
 * <br/><i>MMStandardModelTree</i></p>
 */
@objid ("baa544d0-f02c-4448-af0f-f4f47fc570c8")
public class MMStandardModelTree {
    @objid ("219b155d-611c-473d-b43a-b005815a2308")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    @objid ("b56f112a-a46c-4f66-a094-12e2dabf939e")
    public static final String SUMMARY_NOTETYPE = "summary";

    /**
     * The underlying {@link ModelTree} represented by this proxy, never null.
     */
    @objid ("8c4b8a2e-445a-4919-ae5f-b97b66cc85da")
    protected final ModelTree elt;

    /**
     * Tells whether a {@link MMStandardModelTree proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelTree}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ec7bd8e4-321c-444d-8699-ad6ed58ec3af")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof ModelTree);
    }

    /**
     * Tries to instantiate a {@link MMStandardModelTree} proxy from a {@link ModelTree} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ModelTree
     * @return a {@link MMStandardModelTree} proxy or <i>null</i>.
     */
    @objid ("28777314-2f5c-421a-ab4d-1761458a779d")
    public static MMStandardModelTree instantiate(ModelTree obj) {
        return MMStandardModelTree.canInstantiate(obj) ? new MMStandardModelTree(obj) : null;
    }

    @objid ("c6de0d59-29b3-4801-9120-2e36bdb2e877")
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
        MMStandardModelTree other = (MMStandardModelTree) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ModelTree}. 
     * @return the ModelTree represented by this proxy, never null.
     */
    @objid ("49d2b86c-0192-4760-a024-f3f882b909a2")
    public ModelTree getElement() {
        return this.elt;
    }

    /**
     * Getter for note 'summary'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("0fe77477-fd01-4cb8-a521-ff4d4e8d3dd5")
    public String getSummaryNote() {
        return this.elt.getNoteContent(MMStandardModelTree.MdaTypes.SUMMARY_NOTETYPE_ELT);
    }

    /**
     * Getter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("9cb5f357-241f-4e0e-b7d9-fe4c48b01207")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardModelTree.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("20737bde-0cb4-4482-9059-6667653ee72c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for note 'summary'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("de084a1d-9a53-49a1-b2e2-11acf637734c")
    public void setSummaryNote(String value) {
        this.elt.putNoteContent(MMStandardModelTree.MdaTypes.SUMMARY_NOTETYPE_ELT, value);
    }

    /**
     * Setter for string property 'userDiagramImage'
     * <p>Property description:
     * <br/><i>Image file path to use in diagrams when unmasked in custom image mode.
     * 
     * The file path must be either absolute or relative to the project path.</i></p>
     */
    @objid ("579261a5-ce0c-4c31-8519-4916811bdf2f")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardModelTree.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("2888226c-fe2a-4243-8245-e7362c43bbb6")
    protected MMStandardModelTree(ModelTree elt) {
        this.elt = elt;
    }

    @objid ("cfb7543a-2527-4217-9c51-756155a8762a")
    public static final class MdaTypes {
        @objid ("4d3b9de9-b58f-40a6-a174-11ee4b9b0abf")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("ae1723c1-3b6c-414b-8c08-e24030decd19")
        public static NoteType SUMMARY_NOTETYPE_ELT;

        @objid ("d6970d84-8eb5-4441-baaa-1282630edeed")
        private static Stereotype MDAASSOCDEP;

        @objid ("ed45ae45-b98d-4b53-8033-7e08ee3148dc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b79061a4-d63b-472c-8459-b83ac75ec7d4")
        public static void init(IModuleContext ctx) {
            USERDIAGRAMIMAGE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "706d4a6a-3306-46c4-9ce8-af23457cc2ef");
            SUMMARY_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00000000-0000-3e84-0000-000000000000");
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
