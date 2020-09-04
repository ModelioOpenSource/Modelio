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

package org.modelio.api.ui.form.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.plugin.Api;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

@objid ("e9f0dde4-25c5-40d8-b0b5-616b069085ba")
public class NoteFieldData implements IFormFieldData {
    @objid ("47499fa0-ec7e-4330-805e-c292f8418632")
    private final String moduleName;

    @objid ("cb6310cc-a61b-4d40-947b-e1c77ea42962")
    private final String newNoteType;

    @objid ("5b3b3158-bcc0-4a85-9266-ab7aaeb40c18")
    private final String noteLabel;

    @objid ("2ae33ac7-f57b-4ec5-8297-47889738421d")
    private final String noteTypeName;

    @objid ("3c124840-9335-41e5-ab6a-0c7dc5936ea6")
    private final ModelElement me;

    @objid ("10e4c359-017d-48ea-8730-dffd1f4fb6af")
    private final IModelingSession modelingSession;

    @objid ("d9dd5f0a-2640-4359-a450-b6272d42dbb0")
    private final NoteType noteType;

    /**
     * Creates a note field data.
     * <p>
     * Notes will be created as HTML if the note type MIME type is HTML, plain text in all other cases.
     * 
     * @param modelingSession the Modeling session, needed since 3.8 .
     * @param me the edited element
     * @param moduleName the note type module
     * @param noteTypeName the note type module name
     */
    @objid ("98e77c5a-90a1-44dd-9f6b-f6e0399c2719")
    public NoteFieldData(IModelingSession modelingSession, ModelElement me, String moduleName, String noteTypeName) {
        this.modelingSession = Objects.requireNonNull(modelingSession);
        this.me = Objects.requireNonNull(me);
        this.moduleName = Objects.requireNonNull(moduleName);
        this.noteTypeName = Objects.requireNonNull(noteTypeName);
        this.noteType = resolveNoteModel(me, moduleName, noteTypeName);
        
        if(this.noteType != null && this.noteType.getMimeType().contains("html") ){
            this.newNoteType = "html";
        } else {
            this.newNoteType = "plain";
        }
        
        this.noteLabel = computeNoteLabel(modelingSession, this.noteType, noteTypeName);
    }

    /**
     * @param modelingSession the Modeling session, needed since 3.8 .
     * @param me the edited element
     * @param moduleName the note type module
     * @param noteTypeName the note type module name
     * @param newNoteType the new notes MIME type : either "plain" or "html"
     */
    @objid ("d6307a8d-4c79-472f-82dc-e7685a328b85")
    public NoteFieldData(IModelingSession modelingSession, ModelElement me, String moduleName, String noteTypeName, String newNoteType) {
        this.modelingSession = Objects.requireNonNull(modelingSession);
        this.me = Objects.requireNonNull(me);
        this.moduleName = Objects.requireNonNull(moduleName);
        this.noteTypeName = Objects.requireNonNull(noteTypeName);
        this.noteType = resolveNoteModel(me, moduleName, noteTypeName);
        
        if(newNoteType.contains("html") && this.noteType != null && this.noteType.getMimeType().contains("html") ){
            this.newNoteType = "html";
        } else {
            this.newNoteType = newNoteType;
        }
        
        this.noteLabel = computeNoteLabel(modelingSession, this.noteType, noteTypeName);
    }

    @objid ("4b2478df-a23c-4ae2-bc5b-6067d7638692")
    @Override
    public String getName() {
        return this.noteLabel;
    }

    @objid ("c21fecb4-e6ce-40e6-901a-333890f5ac9e")
    @Override
    public IFormFieldType getType() {
        return new NoteFieldType();
    }

    @objid ("3d5a354a-7b74-40b9-94e4-0be8788144ca")
    @Override
    public Object getValue() {
        final String s = this.me.getNoteContent(this.moduleName, this.noteTypeName);
        return s != null ? s : "";
    }

    @objid ("80e4a807-4dc3-42f5-bd9c-62c0a650b788")
    @Override
    public void setValue(Object value) {
        try (ITransaction t = getModelingSession().createTransaction(String.format("Set '%s' note on %s.", this.noteTypeName, this.me))) {
            this.me.putNoteContent(this.moduleName, this.noteTypeName, value.toString());
            t.commit();
        } catch (final ExtensionNotFoundException e) {
            Api.LOG.error(e);
        }
    }

    @objid ("d2b46c40-26f0-4f12-b0ea-44eb12ccb2d7")
    protected static String computeNoteLabel(IModelingSession modelingSession, NoteType noteType, String noteTypeName) {
        if (noteType != null) {
            return modelingSession.getMetamodelExtensions().getLabel(noteType);
        } else {
            return "! Missing "+noteTypeName+"!";
        }
    }

    @objid ("517cf15c-904f-4bf4-834d-66b1d8b531b1")
    protected IModelingSession getModelingSession() {
        return this.modelingSession;
    }

    @objid ("430461c2-4fc5-403d-8b1e-126dad4733fa")
    protected Note getNote() {
        return this.me.getNote(this.moduleName, this.noteTypeName);
    }

    @objid ("60352d5e-1133-429b-9910-731b41ec2499")
    protected NoteType resolveNoteModel(final ModelElement el, final String aModuleName, final String type) {
        final MMetamodel metamodel = el.getMClass().getMetamodel();
        final Collection<NoteType> elts = getModelingSession().findByAtt(NoteType.class, "Name", type);
        final List<NoteType> candidates = new ArrayList<>();
        
        for (NoteType o : elts) {
            if (aModuleName.equals(o.getModule().getName())) {
                candidates.add(o);
            }
        }
           
        // First loop: check strict metaclass equality
        for (final NoteType nType : candidates) {
            if (nType.getOwnerReference() != null) {
                final MClass referenceClass = metamodel.getMClass(nType.getOwnerReference().getReferencedClassName());
                if (el.getMClass() == referenceClass) {
                    return nType;
                }
            } else if (nType.getOwnerStereotype() != null) {
                final MClass steClass = metamodel.getMClass(nType.getOwnerStereotype().getBaseClassName());
                if (el.getMClass() == steClass) {
                    return nType;
                }
            } else {
                continue;
            }
        }
        
        // Second loop: if first one did not give any result, check metaclass compatibility
        for (final NoteType nType : candidates) {
            if (nType.getOwnerReference() != null) {
                final MClass referenceClass = metamodel.getMClass(nType.getOwnerReference().getReferencedClassName());
                if (el.getMClass().hasBase(referenceClass)) {
                    return nType;
                }
            } else if (nType.getOwnerStereotype() != null) {
                final MClass steClass = metamodel.getMClass(nType.getOwnerStereotype().getBaseClassName());
                if (el.getMClass().hasBase(steClass)) {
                    return nType;
                }
            } else {
                continue;
            }
        }
        return candidates.isEmpty() ? null : candidates.iterator().next();
    }

    @objid ("d440815f-db3d-466c-b6a9-9bc7c3b75d57")
    private final class NoteFieldType implements IFormFieldType {
        @objid ("ad16c720-6c53-4d01-ab7b-226fb3882355")
        @Override
        public boolean isValidValue(String value) {
            return true;
        }

        /**
         * Returns either "plain" or "html".
         */
        @objid ("0965169a-a7cc-4f41-8cff-b4cc7b1ae0e4")
        @Override
        public String getName() {
            final Note note = getNote();
            
            if (note != null) {
                return isHtmlNote(note) ? "html" : "plain";
            } else {              
                return NoteFieldData.this.newNoteType;
            }
        }

        @objid ("8dc49753-d44d-44ec-beb2-4aba3fc7838c")
        @Override
        public Object[] getEnumeratedValues() {
            return null;
        }

        @objid ("3c10c871-a70c-4593-b4af-dfd0ff4425ed")
        private boolean isHtmlNote(Note note) {
            String noteMimeType = note.getMimeType();
            final String mimeType = noteMimeType != null && !noteMimeType.isEmpty() ? 
                    noteMimeType : note.getModel().getMimeType();
            return mimeType.contains("html");
        }

    }

}
