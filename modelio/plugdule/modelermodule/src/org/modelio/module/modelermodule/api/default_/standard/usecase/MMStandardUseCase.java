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
package org.modelio.module.modelermodule.api.default_.standard.usecase;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UseCase} metaclass.
 * <p>Description:
 * <br/><i>MMStandardUseCase</i></p>
 */
@objid ("f6043a62-c9cb-47a0-ae6f-aa985c4e5c1f")
public class MMStandardUseCase {
    @objid ("e7bebd90-f556-40dd-a020-b4091cafb465")
    public static final String CONSTRAINT_NOTETYPE = "constraint";

    @objid ("67011a94-5ea7-4f4e-aa89-17ca85227baa")
    public static final String EXCEPTION_NOTETYPE = "exception";

    @objid ("79826d94-4bb6-45dd-9e9b-ab99230ab75a")
    public static final String NONFUNCTIONAL_CONSTRAINT_NOTETYPE = "non-functional constraint";

    @objid ("8046d8bb-aa6d-477d-9352-f470421fe01e")
    public static final String POSTCONDITION_NOTETYPE = "postcondition";

    @objid ("3da5e503-c0d0-4844-930e-191ec477ac59")
    public static final String PRECONDITION_NOTETYPE = "precondition";

    /**
     * The underlying {@link UseCase} represented by this proxy, never null.
     */
    @objid ("bae055d0-f0a0-4500-84b0-13ad694aac67")
    protected final UseCase elt;

    /**
     * Tells whether a {@link MMStandardUseCase proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCase}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9e82a627-a0be-49c9-b154-11a4eab30eca")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof UseCase);
    }

    /**
     * Tries to instantiate a {@link MMStandardUseCase} proxy from a {@link UseCase} checking its metaclass.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UseCase
     * @return a {@link MMStandardUseCase} proxy or <i>null</i>.
     */
    @objid ("26b961b1-2330-476f-a383-041875926f7f")
    public static MMStandardUseCase instantiate(UseCase obj) {
        return MMStandardUseCase.canInstantiate(obj) ? new MMStandardUseCase(obj) : null;
    }

    @objid ("37a6a62b-e588-4ca3-9a81-cd4b361ef208")
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
        MMStandardUseCase other = (MMStandardUseCase) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Getter for note 'constraint'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("cc54cca9-87ca-4e4c-8b49-d5868e2c9855")
    public String getConstraintNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.CONSTRAINT_NOTETYPE_ELT);
    }

    /**
     * Get the underlying {@link UseCase}.
     * @return the UseCase represented by this proxy, never null.
     */
    @objid ("16a4be06-0e7b-487c-925d-5332ad29d4ff")
    public UseCase getElement() {
        return this.elt;
    }

    /**
     * Getter for note 'exception'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("df70764a-4708-4b78-961f-3717b7876696")
    public String getExceptionNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.EXCEPTION_NOTETYPE_ELT);
    }

    /**
     * Getter for note 'non-functional constraint'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("54109d8b-f4b7-4c46-8bde-d74afccd78ea")
    public String getNonfunctionalconstraintNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.NONFUNCTIONAL_CONSTRAINT_NOTETYPE_ELT);
    }

    /**
     * Getter for note 'postcondition'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("4c90bf82-2040-4cd1-97ea-ae9ba9af4187")
    public String getPostconditionNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.POSTCONDITION_NOTETYPE_ELT);
    }

    /**
     * Getter for note 'precondition'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("6b2673bb-eef8-4b17-89f7-72e5eb306387")
    public String getPreconditionNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.PRECONDITION_NOTETYPE_ELT);
    }

    @objid ("3cbb11d2-232d-41c8-a155-e06a7e39a383")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for note 'constraint'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("fe17bf8a-adbe-4071-8032-4d20347fbc56")
    public void setConstraintNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.CONSTRAINT_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'exception'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("490ba0a4-8746-4b73-8020-9dfebc69d028")
    public void setExceptionNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.EXCEPTION_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'non-functional constraint'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("1a6fdfdd-b65a-4154-9b33-ece6860770cf")
    public void setNonfunctionalconstraintNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.NONFUNCTIONAL_CONSTRAINT_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'postcondition'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("d774e2ee-4398-4669-8c4e-bc459bff9c13")
    public void setPostconditionNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.POSTCONDITION_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'precondition'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("b9fd08c8-9476-4642-9854-8a3ceef7fb23")
    public void setPreconditionNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.PRECONDITION_NOTETYPE_ELT, value);
    }

    @objid ("5193f1ab-431f-4e16-8a3d-9ae72b6e6c20")
    protected  MMStandardUseCase(UseCase elt) {
        this.elt = elt;
    }

    @objid ("a4eefee2-3142-4a34-b6ab-5f0376134a02")
    public static final class MdaTypes {
        @objid ("24ef26b7-bb12-4727-9599-4dec97562224")
        public static NoteType CONSTRAINT_NOTETYPE_ELT;

        @objid ("17b71069-79b7-432f-a6f5-f5eba9e3ebc3")
        public static NoteType NONFUNCTIONAL_CONSTRAINT_NOTETYPE_ELT;

        @objid ("c14dbc27-5232-4fce-8b3f-47ccff711aed")
        public static NoteType EXCEPTION_NOTETYPE_ELT;

        @objid ("da4a163e-3a4b-4c35-be05-43ec1df3d2c7")
        public static NoteType POSTCONDITION_NOTETYPE_ELT;

        @objid ("f9324a20-3f58-4128-b3c0-2d6b1f8d0726")
        public static NoteType PRECONDITION_NOTETYPE_ELT;

        @objid ("eafd302c-6842-491f-8bec-6567cf8cb3e2")
        private static Stereotype MDAASSOCDEP;

        @objid ("812977b2-f880-475d-bb69-cadc3f592530")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("765ba899-2e41-4229-b16d-ec0332dcecf4")
        public static void init(IModuleContext ctx) {
            CONSTRAINT_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00bc0b8c-0000-251b-0000-000000000000");
            NONFUNCTIONAL_CONSTRAINT_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00bc0c08-0000-242c-0000-000000000000");
            EXCEPTION_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00bc0b8c-0000-2519-0000-000000000000");
            POSTCONDITION_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00bc0b8c-0000-2517-0000-000000000000");
            PRECONDITION_NOTETYPE_ELT = ctx.getModelingSession().findElementById(NoteType.class, "00bc0b8c-0000-2515-0000-000000000000");
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
