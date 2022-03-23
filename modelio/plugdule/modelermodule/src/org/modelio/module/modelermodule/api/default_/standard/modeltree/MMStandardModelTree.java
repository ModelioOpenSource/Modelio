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
    @objid ("d65b4184-b146-4c6c-b3d0-207ee6a37fe1")
    public static final String USERDIAGRAMIMAGE_TAGTYPE = "userDiagramImage";

    @objid ("416c5706-6067-44e2-86a7-5a195f7e72c1")
    public static final String SUMMARY_NOTETYPE = "summary";

    /**
     * The underlying {@link ModelTree} represented by this proxy, never null.
     */
    @objid ("4232ed54-2b8a-4c46-a900-714ff1eac264")
    protected final ModelTree elt;

    /**
     * Tells whether a {@link MMStandardModelTree proxy} can be instantiated from a {@link MObject} checking it is a {@link ModelTree}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("7a6f9f40-e691-4c2b-b3ff-7b7c60d4ec90")
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
    @objid ("f7041d35-b4c4-4bd4-bb81-4d1db7cd80ce")
    public static MMStandardModelTree instantiate(ModelTree obj) {
        return MMStandardModelTree.canInstantiate(obj) ? new MMStandardModelTree(obj) : null;
    }

    @objid ("f5270fd3-a8db-47ba-9f16-5f656cbcaa1b")
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
    @objid ("f8228192-2c51-49fe-b911-81fdafc999c0")
    public ModelTree getElement() {
        return this.elt;
    }

    /**
     * Getter for note 'summary'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("03f7c614-2fe7-42a5-8a55-84929f526cde")
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
    @objid ("954dd683-ae55-4380-8c66-bd427f65b2a2")
    public String getUserDiagramImage() {
        return this.elt.getTagValue(MMStandardModelTree.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT);
    }

    @objid ("70fabd82-dc68-4de2-b26d-c1233f6a7c88")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for note 'summary'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("5f4dcb75-0f47-462c-9fda-6ca3da73957d")
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
    @objid ("4c120f8a-d601-4509-8430-b4e56477d4ca")
    public void setUserDiagramImage(String value) {
        this.elt.putTagValue(MMStandardModelTree.MdaTypes.USERDIAGRAMIMAGE_TAGTYPE_ELT, value);
    }

    @objid ("203d92db-76ca-4ff6-9598-1d50cc59f99a")
    protected  MMStandardModelTree(ModelTree elt) {
        this.elt = elt;
    }

    @objid ("cfb7543a-2527-4217-9c51-756155a8762a")
    public static final class MdaTypes {
        @objid ("5b6f49c1-6893-48a4-8f78-89a622f2cdd9")
        public static TagType USERDIAGRAMIMAGE_TAGTYPE_ELT;

        @objid ("56eb243d-ea8f-498d-a6e3-9ef451586687")
        public static NoteType SUMMARY_NOTETYPE_ELT;

        @objid ("9b876efe-0d51-4579-90ad-3a5846c6469c")
        private static Stereotype MDAASSOCDEP;

        @objid ("7f0bfc07-473f-4ef3-919d-5bfd72d89e39")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5596acfa-0830-4f19-8553-916a473a4cfd")
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
