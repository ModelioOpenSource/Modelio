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
    @objid ("794ad4aa-e902-4328-be8a-2d2751abe9e0")
    public static final String SUMMARY_NOTETYPE = "summary";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("a260a869-ecf9-4f25-92f2-a88f9ce5f945")
    protected final Operation elt;

    /**
     * Tells whether a {@link MMStandardOperation proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("d7d925c5-f778-423c-b70f-4d4ce648507d")
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
    @objid ("0261be02-9bcd-4e62-ab38-4378b217c07c")
    public static MMStandardOperation instantiate(Operation obj) {
        return MMStandardOperation.canInstantiate(obj) ? new MMStandardOperation(obj) : null;
    }

    @objid ("b5d3aae0-c31f-417e-bb03-e1dd23bd697a")
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
    @objid ("ccc01915-9949-4c14-84aa-1ca34a2bf6ab")
    public Operation getElement() {
        return this.elt;
    }

    /**
     * Getter for note 'summary'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("1f45744c-2985-4c46-bc62-3ee8e6f1ca0c")
    public String getSummaryNote() {
        return this.elt.getNoteContent(MMStandardOperation.MdaTypes.SUMMARY_NOTETYPE_ELT);
    }

    @objid ("e69bfa08-05c6-4fb8-be0f-b4d2b5dbc9ad")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for note 'summary'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("4b86a4ca-0e27-45fe-b387-9a9ce68aa54d")
    public void setSummaryNote(String value) {
        this.elt.putNoteContent(MMStandardOperation.MdaTypes.SUMMARY_NOTETYPE_ELT, value);
    }

    @objid ("d7cc2ff5-0917-48c1-93a8-f4d084b9aaa7")
    protected  MMStandardOperation(Operation elt) {
        this.elt = elt;
    }

    @objid ("6311b55f-0b8b-4db8-a14d-54974cabd5fb")
    public static final class MdaTypes {
        @objid ("a97e83e1-688c-4401-a704-27d16238be02")
        public static NoteType SUMMARY_NOTETYPE_ELT;

        @objid ("73e8769a-1ddc-4424-898d-020ecd64a6ff")
        private static Stereotype MDAASSOCDEP;

        @objid ("30c4eaff-d577-4dd3-8e94-3b60d6ff41d2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fe12666e-d89c-4bb8-bfd4-f0d9032c6858")
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
