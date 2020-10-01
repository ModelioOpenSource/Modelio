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
package org.modelio.module.modelermodule.api.default_.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} metaclass.
 * <p>Description:
 * <br/><i>MMStandardOperation</i></p>
 */
@objid ("ab8de4e7-e6a1-4760-8096-da69fc4a8d36")
public class MMStandardOperation {
    @objid ("9fe27d49-18c5-423f-8129-46a323956858")
    public static final String SUMMARY_NOTETYPE = "summary";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("deb984d1-a343-4823-8021-f17d839b6365")
    protected final Operation elt;

    /**
     * Tells whether a {@link MMStandardOperation proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d2b25372-3a2b-4c0a-a486-d0af4dfb12ba")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Operation);
    }

    /**
     * Tries to instantiate a {@link MMStandardOperation} proxy from a {@link Operation} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link MMStandardOperation} proxy or <i>null</i>.
     */
    @objid ("1c025c6c-02d9-4eca-94f3-45e7037e1cd7")
    public static MMStandardOperation instantiate(Operation obj) {
        return MMStandardOperation.canInstantiate(obj) ? new MMStandardOperation(obj) : null;
    }

    @objid ("1cd7d615-3dd5-43ad-bfc9-3dcb9693e090")
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
        MMStandardOperation other = (MMStandardOperation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("7d840a42-ec92-4996-8a5b-f253a37cf83c")
    public Operation getElement() {
        return this.elt;
    }

    /**
     * Getter for note 'summary'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("bed149f4-dd50-4a70-8df9-e547d3acc866")
    public String getSummaryNote() {
        return this.elt.getNoteContent(MMStandardOperation.MdaTypes.SUMMARY_NOTETYPE_ELT);
    }

    @objid ("b818aac7-d6de-496e-8d39-70a6459eaa32")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for note 'summary'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("3cb94f6f-f5c9-4f5b-8661-6263ef64c3de")
    public void setSummaryNote(String value) {
        this.elt.putNoteContent(MMStandardOperation.MdaTypes.SUMMARY_NOTETYPE_ELT, value);
    }

    @objid ("639ad04a-665e-46da-9f60-8b156279494a")
    protected MMStandardOperation(Operation elt) {
        this.elt = elt;
    }

    @objid ("6311b55f-0b8b-4db8-a14d-54974cabd5fb")
    public static final class MdaTypes {
        @objid ("4f4d897d-b40a-4934-bd75-a1a5aa07bb4c")
        public static NoteType SUMMARY_NOTETYPE_ELT;

        @objid ("c8a347a6-4c22-4c42-ad8a-b4aa801254d8")
        private static Stereotype MDAASSOCDEP;

        @objid ("db2cfff6-1f26-4d41-9592-01f7917c6f15")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("697b4d64-6653-41f3-9575-1e49e785a40b")
        public static void init(IModuleContext ctx) {
            SUMMARY_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00540400-0000-011f-0000-000000000000");
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
