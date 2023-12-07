/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0088a34c-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ModelElementImpl extends ElementImpl implements ModelElement {
    @objid ("80f3dc16-cdd8-48b6-90e7-0a9e55768ac2")
    @Override
    public final void addStereotype(String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        MModelServices modelServices = new MModelServices(CoreSession.getSession(this));
        
        String realModuleName = moduleName;
        String realStereotypeName = stereotypeName;
        
        // Since Modelio 3.4, stereotypeName might contain "module#stereotype"
        int sharpIdx = stereotypeName.indexOf("#");
        if (sharpIdx != -1) {
            realModuleName = stereotypeName.substring(0, sharpIdx);
            realStereotypeName = stereotypeName.substring(sharpIdx + 1);
        }
        
        List<Stereotype> stereotypes = modelServices.findStereotypes(realModuleName, realStereotypeName, getMClass());
        if (stereotypes.isEmpty()) {
            throw new ExtensionNotFoundException("'" + realStereotypeName + "' stereotype not found");
        } else if (stereotypes.size() == 1) {
            Stereotype ste = stereotypes.get(0);
            List<Stereotype> existingStereotypes = getExtension();
            if (!existingStereotypes.contains(ste)) {
                existingStereotypes.add(ste);
            }
        } else {
            throw new IllegalArgumentException("'" + realStereotypeName + "' stereotype is not unique in module '" + realModuleName + "'");
        }
        
    }

    @objid ("0ca61d4e-418c-443f-be4b-e7c66435aba9")
    @Override
    public final String getLocalProperty(String key) {
        LocalPropertyTable table = getLocalProperties();
        return table != null ? table.getProperty(key) : null;
    }

    @objid ("97ee8964-d803-11e1-b25c-001ec947ccaf")
    @Override
    @Deprecated
    public final Note getNote(String moduleName, String noteTypeName) {
        for (Note note : this.getDescriptor()) {
            final NoteType noteType = note.getModel();
            if (noteType != null && noteType.getName().equals(noteTypeName)) {
                if (noteType.getModule() == null) {
                    if (moduleName == null) {
                        return note;
                    }
                } else if (noteType.getModule().getName().equals(moduleName)) {
                    return note;
                }
            }
        }
        return null;
    }

    @objid ("6fe4526d-2baa-43ff-93e4-a31cedd7f6ab")
    @Override
    public final Note getNote(String moduleName, String ownerName, String noteTypeName) {
        for (Note note : this.getDescriptor()) {
            final NoteType noteType = note.getModel();
            if (noteType != null && noteType.getName().equals(noteTypeName)) {
                if (isValidMdaExtension(noteType.getModule(), noteType.getCompositionOwner(), moduleName, ownerName)) {
                    return note;
                }
            }
        }
        return null;
    }

    @objid ("32cdbed3-fa79-457f-b0a7-3f3bb1ecf400")
    @Override
    public Note getNote(NoteType noteType) {
        for (Note note : this.getDescriptor()) {
            final NoteType lnoteType = note.getModel();
            if (Objects.equals(lnoteType, noteType)) {
                return note;
            }
        }
        return null;
    }

    @objid ("97ee896c-d803-11e1-b25c-001ec947ccaf")
    @Override
    public final String getNoteContent(String moduleName, String noteTypeName) {
        Note note = getNote(moduleName, noteTypeName);
        return note != null ? note.getContent() : null;
    }

    @objid ("24b27cd9-430c-4f07-b403-2c21bb5f1a75")
    @Override
    public final String getNoteContent(String moduleName, String ownerName, String noteTypeName) {
        Note note = getNote(moduleName, ownerName, noteTypeName);
        return note != null ? note.getContent() : null;
    }

    @objid ("707da258-ee26-4a14-9eb2-5737c0accf3a")
    @Override
    public String getNoteContent(NoteType noteType) {
        Note note = getNote(noteType);
        return note != null ? note.getContent() : null;
    }

    @objid ("aedcff9b-2830-11e2-bf07-001ec947ccaf")
    @Override
    public final PropertyTable getProperties(String name) {
        for (PropertyTable t : getProperties()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    @objid ("aedcffa1-2830-11e2-bf07-001ec947ccaf")
    @Override
    public final String getProperty(String tableName, String key) {
        PropertyTable props = getProperties(tableName);
        if (props == null) {
            return null;
        } else {
            return props.getProperty(key);
        }
        
    }

    @objid ("1d48ff5f-be07-4379-a570-386efed731a9")
    @Override
    public String getProperty(String moduleName, String stereotypeName, String key) throws ExtensionNotFoundException {
        Stereotype s = getStereotype(moduleName, stereotypeName);
        if (s == null) {
            throw new ExtensionNotFoundException("'" + stereotypeName + "' stereotype not found");
        }
        return getProperty(s, key);
    }

    @objid ("04b55263-aadd-4e21-9bf9-4a467f2b8a83")
    @Override
    public Stereotype getStereotype(String moduleName, String stereotypeName) {
        if (stereotypeName == null) {
            throw new IllegalArgumentException("stereotype name cannot be null");
        }
        
        String realModuleName = moduleName;
        String realStereotypeName = stereotypeName;
        
        // Since Modelio 3.4, stereotypeName might contain "module#stereotype"
        int sharpIdx = stereotypeName.indexOf("#");
        if (sharpIdx != -1) {
            realModuleName = stereotypeName.substring(0, sharpIdx);
            realStereotypeName = stereotypeName.substring(sharpIdx + 1);
        }
        
        for (Stereotype s : getExtension()) {
            if (inheritsFrom(s, moduleName, realStereotypeName)) {
                return s;
            }
        }
        return null;
    }

    @objid ("97ee8976-d803-11e1-b25c-001ec947ccaf")
    @Override
    @Deprecated
    public final TaggedValue getTag(String moduleName, String tagTypeName) {
        for (TaggedValue tag : getTag()) {
            TagType tagType = tag.getDefinition();
            if (tagType != null && tagType.getName().equals(tagTypeName)) {
                if (tagType.getModule() == null) {
                    if (moduleName == null) {
                        return tag;
                    }
                } else if (tagType.getModule().getName().equals(moduleName)) {
                    return tag;
                }
            }
        }
        return null;
    }

    @objid ("aa0424b7-e5cb-4d21-a005-926eb60c684d")
    @Override
    public final TaggedValue getTag(String moduleName, String ownerName, String tagTypeName) {
        for (TaggedValue tag : getTag()) {
            TagType tagType = tag.getDefinition();
            if (tagType != null && tagType.getName().equals(tagTypeName)) {
                if (isValidMdaExtension(tagType.getModule(), tagType.getCompositionOwner(), moduleName, ownerName)) {
                    return tag;
                }
            }
        }
        return null;
    }

    @objid ("a1eb44bf-3824-4ab6-98c4-9d7395139319")
    @Override
    public TaggedValue getTag(TagType tagType) {
        for (TaggedValue tag : getTag()) {
            TagType ltagType = tag.getDefinition();
            if (Objects.equals(tagType, ltagType)) {
                return tag;
            }
        }
        return null;
    }

    @objid ("97ee897e-d803-11e1-b25c-001ec947ccaf")
    @Override
    public final String getTagValue(String moduleName, String tagTypeName) {
        TaggedValue tag = getTag(moduleName, tagTypeName);
        if (tag == null) {
            return null;
        }
        List<TagParameter> tagParameters = tag.getActual();
        return tagParameters.isEmpty() ? null : tagParameters.get(0).getValue();
    }

    @objid ("5fad3e93-5f65-4232-9713-e184e3289bd6")
    @Override
    public final String getTagValue(String moduleName, String ownerName, String tagTypeName) {
        TaggedValue tag = getTag(moduleName, ownerName, tagTypeName);
        if (tag == null) {
            return null;
        }
        List<TagParameter> tagParameters = tag.getActual();
        return tagParameters.isEmpty() ? null : tagParameters.get(0).getValue();
    }

    @objid ("7366d993-7a52-444b-813c-318c5d0d6bd7")
    @Override
    public String getTagValue(TagType tagType) {
        TaggedValue tag = getTag(tagType);
        if (tag == null) {
            return null;
        }
        List<TagParameter> tagParameters = tag.getActual();
        return tagParameters.isEmpty() ? null : tagParameters.get(0).getValue();
    }

    @objid ("97ee8989-d803-11e1-b25c-001ec947ccaf")
    @Override
    public final List<String> getTagValues(String moduleName, String tagTypeName) {
        TaggedValue tag = getTag(moduleName, tagTypeName);
        if (tag == null) {
            return null;
        }
        List<TagParameter> tagParameters = tag.getActual();
        List<String> parameters = new ArrayList<>(tagParameters.size());
        for (TagParameter parameter : tagParameters) {
            parameters.add(parameter.getValue());
        }
        return parameters;
    }

    @objid ("e7e02ff2-05ef-4ec8-9bd2-a802787d8fd3")
    @Override
    public final List<String> getTagValues(String moduleName, String ownerName, String tagTypeName) {
        TaggedValue tag = getTag(moduleName, ownerName, tagTypeName);
        if (tag == null) {
            return null;
        }
        List<TagParameter> tagParameters = tag.getActual();
        List<String> parameters = new ArrayList<>(tagParameters.size());
        for (TagParameter parameter : tagParameters) {
            parameters.add(parameter.getValue());
        }
        return parameters;
    }

    @objid ("34b8b378-65dd-469c-8b99-490c6c39b46d")
    @Override
    public List<String> getTagValues(TagType tagType) {
        TaggedValue tag = getTag(tagType);
        if (tag == null) {
            return null;
        }
        List<TagParameter> tagParameters = tag.getActual();
        List<String> parameters = new ArrayList<>(tagParameters.size());
        for (TagParameter parameter : tagParameters) {
            parameters.add(parameter.getValue());
        }
        return parameters;
    }

    @objid ("d93c8a6c-7273-430f-98b5-49148b8ad01e")
    @Override
    public final boolean isStereotyped(Stereotype stereotype) {
        if (stereotype == null) {
            throw new IllegalArgumentException("isStereotyped(): stereotype  cannot be null");
        }
        
        for (Stereotype s : this.getExtension()) {
            if (inheritsFrom(s, stereotype)) {
                return true;
            }
        }
        return false;
    }

    @objid ("97ee8995-d803-11e1-b25c-001ec947ccaf")
    @Override
    public final boolean isStereotyped(String moduleName, String stereotypeName) {
        if (stereotypeName == null) {
            throw new IllegalArgumentException("isStereotyped(): stereotype name cannot be null");
        }
        return getStereotype(moduleName, stereotypeName) != null;
    }

    @objid ("97f0ecc2-d803-11e1-b25c-001ec947ccaf")
    @Override
    public final boolean isTagged(String moduleName, String tagTypeName) {
        for (TaggedValue tag : getTag()) {
            TagType tagType = tag.getDefinition();
            if (tagType != null && tagType.getName().equals(tagTypeName)) {
                if (tagType.getModule() == null) {
                    if (moduleName == null) {
                        return true;
                    }
                } else if (tagType.getModule().getName().equals(moduleName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @objid ("dd0b1d4b-4261-4535-b8ea-83f84daac0b4")
    @Override
    public final boolean isTagged(String moduleName, String ownerName, String tagTypeName) {
        for (TaggedValue tag : getTag()) {
            TagType tagType = tag.getDefinition();
            if (tagType != null && tagType.getName().equals(tagTypeName)) {
                if (tagType.getModule() == null) {
                    if (moduleName == null) {
                        return true;
                    }
                } else if (tagType.getModule().getName().equals(moduleName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @objid ("0cca5182-86da-4d52-bcaa-b3b104696bd6")
    @Override
    public boolean isTagged(TagType tagType) {
        for (TaggedValue tag : getTag()) {
            TagType ltagType = tag.getDefinition();
            if (Objects.equals(tagType, ltagType)) {
                return true;
            }
        }
        return false;
    }

    @objid ("7abfeac2-e619-4405-9895-b83d3b8c818f")
    @Override
    public void putNoteContent(String moduleName, String noteTypeName, String content) throws ExtensionNotFoundException {
        Note theNote = getNote(moduleName, noteTypeName);
        
        if (content == null) {
            // Delete the note if no more value
            if (theNote != null) {
                theNote.delete();
            }
            return;
        }
        
        if (theNote == null) {
            // No such note on element, the factory creates it if there is only
            // one matching
            // type
            IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
            factory.createNote(moduleName, ".*", noteTypeName, this, content);
        } else {
            theNote.setContent(content);
        }
        
    }

    @objid ("fb9eb565-3a4a-464e-a4f6-a962d1416b26")
    @Override
    public void putNoteContent(String moduleName, String ownerName, String noteTypeName, String content) throws ExtensionNotFoundException {
        Note theNote = getNote(moduleName, ownerName, noteTypeName);
        
        if (content == null) {
            // Delete the note if no more value
            if (theNote != null) {
                theNote.delete();
            }
            return;
        }
        
        if (theNote == null) {
            // No such note on element, the factory creates it if there is only
            // one matching
            // type
            IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
            factory.createNote(moduleName, ownerName, noteTypeName, this, content);
        } else {
            theNote.setContent(content);
        }
        
    }

    @objid ("aefaf984-0498-4ec8-8b97-ffc07b89d10f")
    @Override
    public void putNoteContent(NoteType noteType, String content) throws ExtensionNotFoundException {
        Note theNote = getNote(noteType);
        
        if (content == null) {
            // Delete the note if no more value
            if (theNote != null) {
                theNote.delete();
            }
            return;
        }
        
        if (theNote == null) {
            // No such note on element, the factory creates it if there is only
            // one matching
            // type
            IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
            factory.createNote(noteType, this, content);
        } else {
            theNote.setContent(content);
        }
        
    }

    @objid ("ce50811d-dbfa-4cea-9586-7f014f1fadd4")
    @Override
    public void putTagValue(String moduleName, String tagTypeName, String value) throws ExtensionNotFoundException {
        TaggedValue tag = getTag(moduleName, tagTypeName);
        
        if (value == null) {
            // Delete the tag if no more value
            if (tag != null) {
                tag.delete();
            }
            return;
        }
        
        IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
        
        // Create the tag if not present
        if (tag == null) {
            tag = factory.createTaggedValue(moduleName, ".*", tagTypeName, this);
        }
        
        final List<TagParameter> oldParameters = tag.getActual();
        int cpt = 0;
        
        // Replace existing parameter values and delete spare ones
        for (int i = 0; i < oldParameters.size() && i < 1; i++) {
            oldParameters.get(i).setValue(value);
            cpt++;
        }
        
        // Delete spare parameter
        while (oldParameters.size() > 1) {
            oldParameters.get(oldParameters.size() - 1).delete();
        }
        
        // Add missing parameter
        if (cpt < 1) {
            factory.createTagParameter(value, tag);
        }
        
    }

    @objid ("0574e663-cdbf-45db-ad97-46fa076df907")
    @Override
    public void putTagValue(String moduleName, String ownerName, String tagTypeName, String value) throws ExtensionNotFoundException {
        TaggedValue tag = getTag(moduleName, ownerName, tagTypeName);
        
        if (value == null) {
            // Delete the tag if no more value
            if (tag != null) {
                tag.delete();
            }
            return;
        }
        
        IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
        
        // Create the tag if not present
        if (tag == null) {
            tag = factory.createTaggedValue(moduleName, ownerName, tagTypeName, this);
        }
        
        final List<TagParameter> oldParameters = tag.getActual();
        int cpt = 0;
        
        // Replace existing parameter values and delete spare ones
        for (int i = 0; i < oldParameters.size() && i < 1; i++) {
            oldParameters.get(i).setValue(value);
            cpt++;
        }
        
        // Delete spare parameter
        while (oldParameters.size() > 1) {
            oldParameters.get(oldParameters.size() - 1).delete();
        }
        
        // Add missing parameter
        if (cpt < 1) {
            factory.createTagParameter(value, tag);
        }
        
    }

    @objid ("a8565815-c2ed-4bed-9435-06497e5cde4b")
    @Override
    public void putTagValue(TagType tagType, String value) throws ExtensionNotFoundException {
        TaggedValue tag = getTag(tagType);
        
        if (value == null) {
            // Delete the tag if no more value
            if (tag != null) {
                tag.delete();
            }
            return;
        }
        
        IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
        
        // Create the tag if not present
        if (tag == null) {
            tag = factory.createTaggedValue(tagType, this);
        }
        
        final List<TagParameter> oldParameters = tag.getActual();
        int cpt = 0;
        
        // Replace existing parameter values and delete spare ones
        for (int i = 0; i < oldParameters.size() && i < 1; i++) {
            oldParameters.get(i).setValue(value);
            cpt++;
        }
        
        // Delete spare parameter
        while (oldParameters.size() > 1) {
            oldParameters.get(oldParameters.size() - 1).delete();
        }
        
        // Add missing parameter
        if (cpt < 1) {
            factory.createTagParameter(value, tag);
        }
        
    }

    @objid ("fb629575-89cc-4917-9950-111a2836ed6b")
    @Override
    public void putTagValues(String moduleName, String tagTypeName, List<String> values) throws ExtensionNotFoundException {
        TaggedValue tag = getTag(moduleName, tagTypeName);
        
        if (values == null || values.isEmpty()) {
            // Delete the tag if no more value
            if (tag != null) {
                tag.delete();
            }
            return;
        }
        
        IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
        
        // Create tag if not present
        if (tag == null) {
            tag = factory.createTaggedValue(moduleName, ".*", tagTypeName, this);
        }
        
        // Set the tag parameters values, merging existing values and new values
        final int newSize = values.size();
        final List<TagParameter> oldParameters = tag.getActual();
        int cpt = 0;
        
        // Replace existing parameter values and delete spare ones
        for (int i = 0; i < oldParameters.size() && i < newSize; i++) {
            oldParameters.get(i).setValue(values.get(i));
            cpt++;
        }
        
        // Delete spare parameter
        while (oldParameters.size() > newSize) {
            oldParameters.get(oldParameters.size() - 1).delete();
        }
        
        // Add missing parameters
        while (cpt < newSize) {
            factory.createTagParameter(values.get(cpt), tag);
            cpt++;
        }
        
    }

    @objid ("97a550a5-eb52-4d06-bd64-643e0261ceaa")
    @Override
    public void putTagValues(String moduleName, String ownerName, String tagTypeName, List<String> values) throws ExtensionNotFoundException {
        TaggedValue tag = getTag(moduleName, ownerName, tagTypeName);
        
        if (values == null || values.isEmpty()) {
            // Delete the tag if no more value
            if (tag != null) {
                tag.delete();
            }
            return;
        }
        
        IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
        
        // Create tag if not present
        if (tag == null) {
            tag = factory.createTaggedValue(moduleName, ownerName, tagTypeName, this);
        }
        
        // Set the tag parameters values, merging existing values and new values
        final int newSize = values.size();
        final List<TagParameter> oldParameters = tag.getActual();
        int cpt = 0;
        
        // Replace existing parameter values and delete spare ones
        for (int i = 0; i < oldParameters.size() && i < newSize; i++) {
            oldParameters.get(i).setValue(values.get(i));
            cpt++;
        }
        
        // Delete spare parameter
        while (oldParameters.size() > newSize) {
            oldParameters.get(oldParameters.size() - 1).delete();
        }
        
        // Add missing parameters
        while (cpt < newSize) {
            factory.createTagParameter(values.get(cpt), tag);
            cpt++;
        }
        
    }

    @objid ("ffbcd909-2397-4e8a-9efe-658f9da43ca9")
    @Override
    public void putTagValues(TagType tagType, List<String> values) throws ExtensionNotFoundException {
        TaggedValue tag = getTag(tagType);
        
        if (values == null || values.isEmpty()) {
            // Delete the tag if no more value
            if (tag != null) {
                tag.delete();
            }
            return;
        }
        
        IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
        
        // Create tag if not present
        if (tag == null) {
            tag = factory.createTaggedValue(tagType, this);
        }
        
        // Set the tag parameters values, merging existing values and new values
        final int newSize = values.size();
        final List<TagParameter> oldParameters = tag.getActual();
        int cpt = 0;
        
        // Replace existing parameter values and delete spare ones
        for (int i = 0; i < oldParameters.size() && i < newSize; i++) {
            oldParameters.get(i).setValue(values.get(i));
            cpt++;
        }
        
        // Delete spare parameter
        while (oldParameters.size() > newSize) {
            oldParameters.get(oldParameters.size() - 1).delete();
        }
        
        // Add missing parameters
        while (cpt < newSize) {
            factory.createTagParameter(values.get(cpt), tag);
            cpt++;
        }
        
    }

    @objid ("7cb75488-166c-4c68-99b1-b4c847647aa6")
    @Override
    public void removeNotes(String moduleName, String noteTypeName) {
        List<Note> toRemove = new ArrayList<>();
        for (Note note : getDescriptor()) {
            NoteType noteType = note.getModel();
            if (noteType != null && noteType.getName().equals(noteTypeName)) {
                if (noteType.getModule() == null) {
                    if (moduleName == null) {
                        toRemove.add(note);
                    }
                } else if (noteType.getModule().getName().equals(moduleName)) {
                    toRemove.add(note);
                }
            }
        }
        for (Note note : toRemove) {
            note.delete();
        }
        
    }

    @objid ("ffe91118-0bd9-444c-a41c-883cda6c2ace")
    @Override
    public void removeNotes(String moduleName, String ownerName, String noteTypeName) {
        List<Note> toRemove = new ArrayList<>();
        for (Note note : getDescriptor()) {
            NoteType noteType = note.getModel();
            if (noteType != null && noteType.getName().equals(noteTypeName)) {
                if (noteType.getModule() == null) {
                    if (moduleName == null) {
                        toRemove.add(note);
                    }
                } else if (noteType.getModule().getName().equals(moduleName)) {
                    toRemove.add(note);
                }
            }
        }
        for (Note note : toRemove) {
            note.delete();
        }
        
    }

    @objid ("6a9c5ff8-29f1-407a-8081-49abbfddd958")
    @Override
    public void removeNotes(NoteType noteType) {
        List<Note> toRemove = new ArrayList<>();
        for (Note note : getDescriptor()) {
            NoteType lnoteType = note.getModel();
            if (Objects.equals(noteType, lnoteType)) {
                toRemove.add(note);
            }
        }
        for (Note note : toRemove) {
            note.delete();
        }
        
    }

    @objid ("dbd8e19d-f363-4e05-9c12-87254ac70f24")
    @Override
    public void removeStereotypes(String moduleName, String stereotypeName) {
        List<Stereotype> toRemove = new ArrayList<>();
        
        String realModuleName = moduleName;
        String realStereotypeName = stereotypeName;
        
        // Since Modelio 3.4, stereotypeName might contain "module#stereotype"
        int sharpIdx = stereotypeName.indexOf("#");
        if (sharpIdx != -1) {
            realModuleName = stereotypeName.substring(0, sharpIdx);
            realStereotypeName = stereotypeName.substring(sharpIdx + 1);
        }
        
        for (Stereotype stereotype : getExtension()) {
            if (stereotype.getName().equals(realStereotypeName)) {
                if (stereotype.getModule() == null) {
                    if (realModuleName == null) {
                        toRemove.add(stereotype);
                    }
                } else if (stereotype.getModule().getName().equals(realModuleName)) {
                    toRemove.add(stereotype);
                }
            }
        }
        
        getExtension().removeAll(toRemove);
        
    }

    @objid ("fe9fdc86-7c1f-402a-a53d-bbce0f8e6d4b")
    @Override
    public void removeTags(String moduleName, String tagTypeName) {
        List<TaggedValue> toRemove = new ArrayList<>();
        
        for (TaggedValue tag : getTag()) {
            TagType tagType = tag.getDefinition();
            if (tagType != null && tagType.getName().equals(tagTypeName)) {
                if (tagType.getModule() == null) {
                    if (moduleName == null) {
                        toRemove.add(tag);
                    }
                } else if (tagType.getModule().getName().equals(moduleName)) {
                    toRemove.add(tag);
                }
            }
        }
        
        for (TaggedValue tag : toRemove) {
            tag.delete();
        }
        
    }

    @objid ("4fc0bbf8-860d-4e32-ae11-f03e0ad39613")
    @Override
    public void removeTags(String moduleName, String ownerName, String tagTypeName) {
        List<TaggedValue> toRemove = new ArrayList<>();
        
        for (TaggedValue tag : getTag()) {
            TagType tagType = tag.getDefinition();
            if (tagType != null && tagType.getName().equals(tagTypeName)) {
                if (tagType.getModule() == null) {
                    if (moduleName == null) {
                        toRemove.add(tag);
                    }
                } else if (tagType.getModule().getName().equals(moduleName)) {
                    toRemove.add(tag);
                }
            }
        }
        
        for (TaggedValue tag : toRemove) {
            tag.delete();
        }
        
    }

    @objid ("dbbcb2ed-0f87-4e9e-b48d-5dc4ea6e009c")
    @Override
    public void removeTags(TagType tagType) {
        List<TaggedValue> toRemove = new ArrayList<>();
        
        for (TaggedValue tag : getTag()) {
            TagType ltagType = tag.getDefinition();
            if (Objects.equals(tagType, ltagType)) {
                toRemove.add(tag);
            }
        }
        
        for (TaggedValue tag : toRemove) {
            tag.delete();
        }
        
    }

    @objid ("c1843b5a-2198-47a6-9bf5-79d3435cadc5")
    @Override
    public void setLocalProperty(String key, String value) {
        LocalPropertyTable table = getLocalProperties();
        if (table == null) {
            IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
            table = factory.createLocalPropertyTable();
            table.setLocalAnnoted(this);
        }
        table.setProperty(key, value);
        
    }

    @objid ("6f56b93d-1506-457e-932f-0b5c13dc205e")
    @Override
    public void setProperty(String tableName, String key, String value) {
        PropertyTable props = getProperties(tableName);
        if (props == null) {
            IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
            props = factory.createPropertyTable();
            props.setName(tableName);
            props.setOwner(this);
        }
        
        props.setProperty(key, value);
        
    }

    @objid ("d7bb4f95-2012-4a9e-9606-8db9208f9d84")
    @Override
    public void setProperty(String moduleName, String stereotypeName, String key, String value) throws ExtensionNotFoundException {
        if (stereotypeName == null) {
            throw new IllegalArgumentException("setProperty(): stereotype name cannot be null");
        }
        
        Stereotype s = getStereotype(moduleName, stereotypeName);
        if (s == null) {
            throw new ExtensionNotFoundException("'" + stereotypeName + "' stereotype not found");
        }
        setProperty(s, key, value);
        
    }

    /**
     * Tells whether 'stereotype' or one of its inheritance parent matches the given 'stereotypeName'.
     * @param stereotype a stereotype
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName the name of another stereotype.
     * @return <code>true</code> if 'stereotype' or one of its inheritance parent matches the given 'stereotypeName'.
     */
    @objid ("2bb117ce-82e7-424a-9352-c37a231c95c5")
    private boolean inheritsFrom(Stereotype stereotype, String moduleName, String stereotypeName) {
        if (stereotype.getName().equals(stereotypeName)) {
            if (stereotype.getModule() == null) {
                if (moduleName == null) {
                    return true;
                }
            } else if (stereotype.getModule().getName().equals(moduleName)) {
                return true;
            }
        }
        
        if (stereotype.getParent() != null) {
            if (inheritsFrom(stereotype.getParent(), moduleName, stereotypeName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Answer to the question: does 'metaclass' or one of its inheritance parent matches the given 'metaclassName'.
     * @param metaclass a metaclass
     * @param metaclassName the name of a metaclass.
     * @return <code>true</code> if 'metaclass' or one of its inheritance parent matches the given 'metaclassName'.
     */
    @objid ("ac922deb-c137-43d7-a4d4-71224cbc94fa")
    private boolean inheritsFrom(MClass metaclass, String metaclassName) {
        String name = metaclass.getQualifiedName();
        if (name.matches(metaclassName == null || metaclassName.isEmpty() ? ".*" : metaclassName) || name.equals(metaclassName)) {
            return true;
        }
        
        if (metaclass.getSuper() != null) {
            if (inheritsFrom(metaclass.getSuper(), metaclassName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true is 'aStereotype' is equals or inherits from 'refStereotype'
     */
    @objid ("37ef2cd5-04ca-4d69-8256-640f1e6a37fc")
    private boolean inheritsFrom(Stereotype aStereotype, Stereotype refStereotype) {
        if (aStereotype.equals(refStereotype)) {
            return true;
        } else {
            return aStereotype.hasBase(refStereotype);
        }
        
    }

    /**
     * Resolve a metaclass from its name.
     */
    @objid ("dff73cd1-712e-43bd-8fff-febb93f6c930")
    private MClass getBaseClass(String baseName) throws MetaclassNotFoundException {
        MClass smBase = getMClass().getMetamodel().getMClass(baseName);
        
        if (smBase == null) {
            throw new MetaclassNotFoundException(baseName);
        }
        return smBase;
    }

    /**
     * Answer to the question: does an Mda extension (TagType, NoteType...) belong to a {@link Stereotype} or {@link MetaclassReference} named 'ownerName' in a module 'moduleName'.
     * @return <code>true</code> if 'ownerName' matches the name of the 'owner' and 'moduleName' matches the name of the module.
     */
    @objid ("68d0055e-08fe-4215-b786-19291f27c8ec")
    private boolean isValidMdaExtension(ModuleComponent module, MObject owner, String moduleName, String ownerName) throws IllegalArgumentException {
        if (moduleName == null) {
            throw new IllegalArgumentException("Module name must not be null");
        }
        
        if (ownerName == null) {
            throw new IllegalArgumentException("Owner name must not be null");
        }
        
        if (owner == null) {
            throw new IllegalArgumentException("Owner must not be null");
        }
        
        if (owner instanceof Stereotype) {
            return module != null && module.getName().equals(moduleName) && inheritsFrom((Stereotype) owner, moduleName, ownerName);
        } else {
            try {
                return module != null && module.getName().equals(moduleName) && inheritsFrom(getBaseClass(((MetaclassReference) owner).getReferencedClassName()), ownerName);
            } catch (@SuppressWarnings ("unused") MetaclassNotFoundException e) {
                return false;
            }
        }
        
    }

    @objid ("2745d6dd-e102-4473-b519-36f4c8d9bda4")
    @Override
    public final TypedPropertyTable getProperties(Stereotype stereotype) {
        if (stereotype == null) {
            throw new IllegalArgumentException("getProperties(Stereotype): stereotype cannot be null");
        }
        return (TypedPropertyTable) getProperties(stereotype.getUuid());
    }

    @objid ("bf911c85-43b3-4949-b492-82753801cc4a")
    @Override
    public String getProperty(Stereotype stereotype, String key) {
        if (stereotype == null) {
            throw new IllegalArgumentException("getProperty(): stereotype cannot be null");
        }
        
        PropertyTable props = getProperties(stereotype);
        if (props == null) {
            return null;
        } else {
            return props.getProperty(key);
        }
        
    }

    @objid ("26f4bc98-c831-40ec-a161-3e45c2e61936")
    @Override
    public final void setProperty(Stereotype stereotype, String key, String value) throws ExtensionNotFoundException {
        TypedPropertyTable props = getProperties(stereotype);
        if (props == null) {
            IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
            props = factory.createTypedPropertyTable();
            props.setName(stereotype.getUuid());
            props.setOwner(this);
            props.setType(stereotype.getDefinedTable());
        }
        
        props.setProperty(key, value);
        
    }

    @objid ("4df39b0d-e208-4bf7-8fde-91e83ddd50a1")
    @Override
    public String getProperty(MetaclassReference ref, String key) {
        if (ref == null) {
            throw new IllegalArgumentException("getProperty(): metaclass reference cannot be null");
        }
        
        PropertyTable props = getProperties(ref);
        if (props == null) {
            return null;
        } else {
            return props.getProperty(key);
        }
        
    }

    @objid ("adc32447-ec66-4c9d-b328-fa70fde57649")
    @Override
    public final void setProperty(MetaclassReference ref, String key, String value) throws ExtensionNotFoundException {
        TypedPropertyTable props = getProperties(ref);
        if (props == null) {
            IInfrastructureModelFactory factory = MTools.get(this).getModelFactory(IInfrastructureModelFactory.class);
            props = factory.createTypedPropertyTable();
            props.setName(ref.getUuid());
            props.setOwner(this);
            props.setType(ref.getDefinedTable());
        }
        
        props.setProperty(key, value);
        
    }

    @objid ("036a50a8-dfbf-43c3-a8e8-1e6ecbf37753")
    @Override
    public final TypedPropertyTable getProperties(MetaclassReference ref) {
        if (ref == null) {
            throw new IllegalArgumentException("getProperties(MetaclassReference): metaclass reference cannot be null");
        }
        return (TypedPropertyTable) getProperties(ref.getUuid());
    }

    @objid ("579659f5-1b43-42d8-886d-a3f60b8c9f54")
    @Override
    public String getName() {
        return (String) getAttVal(((ModelElementSmClass)getClassOf()).getNameAtt());
    }

    @objid ("c75ad143-d275-4625-beb3-187f37a83571")
    @Override
    public void setName(String value) {
        setAttVal(((ModelElementSmClass)getClassOf()).getNameAtt(), value);
    }

    @objid ("6e4d7d86-8be5-46fd-9c61-b3daab3e2883")
    @Override
    public LocalPropertyTable getLocalProperties() {
        Object obj = getDepVal(((ModelElementSmClass)getClassOf()).getLocalPropertiesDep());
        return (obj instanceof LocalPropertyTable)? (LocalPropertyTable)obj : null;
    }

    @objid ("3b7eb12a-6314-478c-be04-511e6bb6b9b4")
    @Override
    public void setLocalProperties(LocalPropertyTable value) {
        appendDepVal(((ModelElementSmClass)getClassOf()).getLocalPropertiesDep(), (SmObjectImpl)value);
    }

    @objid ("0f4953d8-89db-4143-bfd9-d140ba2db36d")
    @Override
    public EList<Stereotype> getExtension() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getExtensionDep());
    }

    @objid ("2d3ea436-397d-4da5-b55f-b072b258e1d1")
    @Override
    public <T extends Stereotype> List<T> getExtension(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Stereotype element : getExtension()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("dc20afc9-77e5-43f4-958a-1185a2238f02")
    @Override
    public EList<Dependency> getDependsOnDependency() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getDependsOnDependencyDep());
    }

    @objid ("c4d0a666-6474-44d8-a53c-9cc428f703fb")
    @Override
    public <T extends Dependency> List<T> getDependsOnDependency(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Dependency element : getDependsOnDependency()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("8120318f-8d25-41a6-a166-cd5806716791")
    @Override
    public EList<TaggedValue> getTag() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getTagDep());
    }

    @objid ("b2250c73-1ad6-43b8-8eda-6c5373958cc0")
    @Override
    public <T extends TaggedValue> List<T> getTag(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TaggedValue element : getTag()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("c3232cb7-427c-4fb5-9a2d-eff0cd2cf871")
    @Override
    public EList<Dependency> getImpactedDependency() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getImpactedDependencyDep());
    }

    @objid ("a7ef1423-47e8-400d-8e93-bed20bc77980")
    @Override
    public <T extends Dependency> List<T> getImpactedDependency(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Dependency element : getImpactedDependency()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("d0057a99-ae50-4b7b-802a-4685c8f2360b")
    @Override
    public EList<PropertyTable> getProperties() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getPropertiesDep());
    }

    @objid ("e9f0f494-4e8e-412d-a326-3a1755e4b7e6")
    @Override
    public <T extends PropertyTable> List<T> getProperties(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PropertyTable element : getProperties()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("0e99fd64-1eb4-4602-94c6-cced880af277")
    @Override
    public EList<AbstractDiagram> getProduct() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getProductDep());
    }

    @objid ("fbbade5e-8b88-47cd-afb0-637ff09e2909")
    @Override
    public <T extends AbstractDiagram> List<T> getProduct(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AbstractDiagram element : getProduct()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("ada2f194-1f5b-4830-a355-17638c30f12f")
    @Override
    public EList<Note> getDescriptor() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getDescriptorDep());
    }

    @objid ("2fcd1f8e-1c56-4546-a172-b226329f0096")
    @Override
    public <T extends Note> List<T> getDescriptor(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Note element : getDescriptor()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("c057400d-c68e-4463-b60d-234003c2d824")
    @Override
    public EList<MatrixDefinition> getMatrix() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getMatrixDep());
    }

    @objid ("c057e974-dbe9-44e6-9ef2-eae0daae9eb4")
    @Override
    public <T extends MatrixDefinition> List<T> getMatrix(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final MatrixDefinition element : getMatrix()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("24b1c60f-50c9-4f11-a618-8429e3af79c0")
    @Override
    public EList<ImpactLink> getImpactImpacted() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getImpactImpactedDep());
    }

    @objid ("9fdbdc90-7732-4f28-9414-33857d61c5c7")
    @Override
    public <T extends ImpactLink> List<T> getImpactImpacted(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ImpactLink element : getImpactImpacted()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("08126554-3241-4ce3-b1b3-0cab44649b82")
    @Override
    public EList<ImpactLink> getImpactDependsOn() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getImpactDependsOnDep());
    }

    @objid ("b1c823c1-5f3d-48c5-8107-dc3a8d93f65c")
    @Override
    public <T extends ImpactLink> List<T> getImpactDependsOn(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ImpactLink element : getImpactDependsOn()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("6b51cdff-f34f-437f-8234-7ddf148f4a2d")
    @Override
    public EList<AbstractResource> getAttached() {
        return new SmList<>(this, ((ModelElementSmClass)getClassOf()).getAttachedDep());
    }

    @objid ("389d828a-765a-40c6-b6d4-57ccd52269f7")
    @Override
    public <T extends AbstractResource> List<T> getAttached(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AbstractResource element : getAttached()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("e55e0d04-640f-48d1-b41b-c56d62680653")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("979e812f-12a9-4c4f-a832-ecc5732e0a55")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("898e3bd1-e25c-4823-b6dc-5d5eaea624b1")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitModelElement(this);
    }

}
