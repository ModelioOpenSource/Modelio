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
    @objid ("6f1992a3-3181-4763-93cd-c831e8bc5a3a")
    public static final String CONSTRAINT_NOTETYPE = "constraint";

    @objid ("6cd2476f-511b-4d89-a251-12281a70c1d4")
    public static final String EXCEPTION_NOTETYPE = "exception";

    @objid ("4974e2eb-a88c-4823-8d9e-61de4d72f788")
    public static final String NONFUNCTIONAL_CONSTRAINT_NOTETYPE = "non-functional constraint";

    @objid ("d4d7edc9-0d93-44ca-bdeb-1f9f0e968652")
    public static final String POSTCONDITION_NOTETYPE = "postcondition";

    @objid ("6a8ea0ba-52a3-4787-898d-26b08221b53a")
    public static final String PRECONDITION_NOTETYPE = "precondition";

    /**
     * The underlying {@link UseCase} represented by this proxy, never null.
     */
    @objid ("2a099b1e-63b4-4add-87cc-ed420ed05cf8")
    protected final UseCase elt;

    /**
     * Tells whether a {@link MMStandardUseCase proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCase}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("64664904-c998-4ea2-b49e-8e813cadf393")
    public static boolean canInstantiate(MObject elt) {
        return elt instanceof UseCase;
    }

    /**
     * Tries to instantiate a {@link MMStandardUseCase} proxy from a {@link UseCase} checking its metaclass.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * 
     * @param obj a UseCase
     * @return a {@link MMStandardUseCase} proxy or <i>null</i>.
     */
    @objid ("04df2826-d711-4d53-b620-f0d9f6bcce13")
    public static MMStandardUseCase instantiate(UseCase obj) {
        return MMStandardUseCase.canInstantiate(obj) ? new MMStandardUseCase(obj) : null;
    }

    @objid ("41706307-66e7-48b2-bc3f-37ffa497a4b3")
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
    @objid ("ad8e764f-388c-4ee3-bbef-d5c2a98e6c10")
    public String getConstraintNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.CONSTRAINT_NOTETYPE_ELT);
    }

    /**
     * Get the underlying {@link UseCase}.
     * 
     * @return the UseCase represented by this proxy, never null.
     */
    @objid ("c73e1093-54c4-45de-af03-f97489a3701b")
    public UseCase getElement() {
        return this.elt;
    }

    /**
     * Getter for note 'exception'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("76baf61e-662c-41b0-a734-0f287cb2ec02")
    public String getExceptionNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.EXCEPTION_NOTETYPE_ELT);
    }

    /**
     * Getter for note 'non-functional constraint'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("11a90fa7-ac25-4848-8c29-09b5e035468e")
    public String getNonfunctionalconstraintNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.NONFUNCTIONAL_CONSTRAINT_NOTETYPE_ELT);
    }

    /**
     * Getter for note 'postcondition'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("46ef7721-b226-44a4-b93a-22b5d29bfdeb")
    public String getPostconditionNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.POSTCONDITION_NOTETYPE_ELT);
    }

    /**
     * Getter for note 'precondition'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("c4b1c43e-469b-4e57-9150-a4f48fc136d6")
    public String getPreconditionNote() {
        return this.elt.getNoteContent(MMStandardUseCase.MdaTypes.PRECONDITION_NOTETYPE_ELT);
    }

    @objid ("2da06a01-512f-4dc4-a135-650e6a84740a")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for note 'constraint'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("73e24e18-2025-49a0-b0a2-ce202ebd7d3f")
    public void setConstraintNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.CONSTRAINT_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'exception'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("61940de8-1485-4083-8a62-4a32c51443ec")
    public void setExceptionNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.EXCEPTION_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'non-functional constraint'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("318e070b-dbd5-4917-8c84-595acfd3850d")
    public void setNonfunctionalconstraintNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.NONFUNCTIONAL_CONSTRAINT_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'postcondition'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("524bd975-1532-42cb-901c-66a78c66cb1e")
    public void setPostconditionNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.POSTCONDITION_NOTETYPE_ELT, value);
    }

    /**
     * Setter for note 'precondition'
     * <p>Note description:
     * <br/><i></i></p>
     */
    @objid ("141f21e5-e5e9-4108-bbb0-0925a50a09aa")
    public void setPreconditionNote(String value) {
        this.elt.putNoteContent(MMStandardUseCase.MdaTypes.PRECONDITION_NOTETYPE_ELT, value);
    }

    @objid ("7e78049b-6fb6-482c-a4ca-fb0733891895")
    protected MMStandardUseCase(UseCase elt) {
        this.elt = elt;
    }

    @objid ("a4eefee2-3142-4a34-b6ab-5f0376134a02")
    public static final class MdaTypes {
        @objid ("d5b6150f-c715-4af4-b475-9179c4afff2f")
        public static NoteType CONSTRAINT_NOTETYPE_ELT;

        @objid ("afd15aac-4919-4504-9bb1-17b316917b08")
        public static NoteType NONFUNCTIONAL_CONSTRAINT_NOTETYPE_ELT;

        @objid ("a2f5e1df-d92a-4241-b3ec-eb830c7cafa2")
        public static NoteType EXCEPTION_NOTETYPE_ELT;

        @objid ("ed92095f-fe92-4d30-9fe6-71ca1c48e3ae")
        public static NoteType POSTCONDITION_NOTETYPE_ELT;

        @objid ("9bffa1e2-ac4b-44d5-a7ea-ed6a0fb3e9fa")
        public static NoteType PRECONDITION_NOTETYPE_ELT;

        @objid ("0a0c1dcb-b2e1-4525-98e7-a5ff050b30b4")
        private static Stereotype MDAASSOCDEP;

        @objid ("0d603d6b-3739-47a6-8d1e-0d7eeb693d10")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("82875d7f-b6cc-46cb-a13d-0bef72b2ac9f")
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
