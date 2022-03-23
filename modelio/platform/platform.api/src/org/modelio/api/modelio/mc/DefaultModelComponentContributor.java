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
package org.modelio.api.modelio.mc;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor.ExportedFileEntry;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of the {@link IModelComponentContributor} interface.
 */
@objid ("d2e68a50-ebcd-11dd-aa31-0014222a9f79")
public class DefaultModelComponentContributor extends AbstractModelComponentContributor {
    @objid ("dff90141-edf0-11e1-82c2-001ec947ccaf")
    private Set<Stereotype> dependencyStereotypes;

    @objid ("dff90144-edf0-11e1-82c2-001ec947ccaf")
    private Set<NoteType> noteTypes;

    @objid ("dff90147-edf0-11e1-82c2-001ec947ccaf")
    private Set<TagType> tagTypes;

    @objid ("dff9014a-edf0-11e1-82c2-001ec947ccaf")
    private Set<MObject> elements;

    @objid ("b3fb26aa-65e1-4a67-b4f0-3ff6d554f510")
    protected Set<ExportedFileEntry> files;

    /**
     * Default constructor, initializing all fields.
     * @param module the module contibuting to the model component.
     */
    @objid ("7ba9d230-ee0f-11dd-bb8c-0014222a9f79")
    public  DefaultModelComponentContributor(IModule module) {
        super(module);
        this.files = new HashSet<>();
        this.elements = new HashSet<>();
        this.dependencyStereotypes = new HashSet<>();
        this.noteTypes = new HashSet<>();
        this.tagTypes = new HashSet<>();
        
    }

    /**
     * Return the set of dependency stereotypes that must be added to a model component.
     * <p>
     * Only dependencies that own one of the stereotype of the set will be exported in the model component.
     * @param stereotype the stereotype to add
     */
    @objid ("a3e817c6-0ecc-11e2-96c4-002564c97630")
    public void addDependencyStereotype(Stereotype stereotype) {
        this.dependencyStereotypes.add(stereotype);
    }

    /**
     * Adds a file path to the set of files path that must be added to a model component.
     * <p>
     * The provided paths must be relative to the project path. If the provided path does not match a file in the
     * project, it will be ignored.
     * @param exportedFile the file to package in the model component.
     * @param exportRelativePath the path to deploy the file into.
     */
    @objid ("052e03f0-51de-11de-9cea-8000600fe800")
    public void addFiles(String exportedFile, String exportRelativePath) {
        this.files.add(new ExportedFileEntry(Paths.get(exportedFile), exportRelativePath));
    }

    /**
     * Adds a note type to the set of note types that must be added to a model component.
     * @param noteType the note type to add
     */
    @objid ("a3e88cf9-0ecc-11e2-96c4-002564c97630")
    public void addNoteType(NoteType noteType) {
        this.noteTypes.add(noteType);
    }

    /**
     * Adds a tagged value to the set of tagged value types that must be added to a model component.
     * @param tagType the type to add
     */
    @objid ("a3e8db1f-0ecc-11e2-96c4-002564c97630")
    public void addTagType(TagType tagType) {
        this.tagTypes.add(tagType);
    }

    /**
     * Return the set of dependency stereotypes that must be added to a model component.
     * <p>
     * The Strings returned by the method are the names of the stereotypes that extends the Dependency link. Only
     * dependencies that own one of the stereotype of the set will be exported in the model component.
     * @return a set of String representing the stereotype names.
     */
    @objid ("ed9ec4f2-ebcd-11dd-aa31-0014222a9f79")
    @Override
    public Set<Stereotype> getDependencyStereotypes() {
        return this.dependencyStereotypes;
    }

    /**
     * Return the set of Elements that must be added to a model component.
     * @return a set of elements
     */
    @objid ("eda1274d-ebcd-11dd-aa31-0014222a9f79")
    @Override
    public Set<MObject> getElements() {
        return this.elements;
    }

    /**
     * Return the set of files path that must be added to a model component.
     * <p>
     * The provided paths must be relative to the project path. If the provided path does not match a file in the
     * project, it will be ignored.
     * @return a set of relative paths
     */
    @objid ("ed9ec4fa-ebcd-11dd-aa31-0014222a9f79")
    @Override
    public Set<ExportedFileEntry> getFiles() {
        return this.files;
    }

    /**
     * Return the set of note types that must be added to a model component.
     * @return a set of String representing the note type names.
     */
    @objid ("eda12751-ebcd-11dd-aa31-0014222a9f79")
    @Override
    public Set<NoteType> getNoteTypes() {
        return this.noteTypes;
    }

    /**
     * Return the set of tagged value types that must be added to a model component.
     * @return a set of String representing the tagged value type names.
     */
    @objid ("eda389ad-ebcd-11dd-aa31-0014222a9f79")
    @Override
    public Set<TagType> getTagTypes() {
        return this.tagTypes;
    }

    /**
     * Adds a model element that must be added to a model component.
     * @param element the model element to add.
     * @since 3.6
     */
    @objid ("dd185be3-c4b1-4225-9ae1-fad45e337a67")
    public void addElement(MObject element) {
        this.elements.add(element);
    }

}
