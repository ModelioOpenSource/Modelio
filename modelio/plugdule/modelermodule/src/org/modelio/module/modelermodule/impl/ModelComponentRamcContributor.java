/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.module.modelermodule.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.mc.AbstractModelComponentContributor;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModuleNoteTypes;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleTagTypes;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8642a811-846a-4ee4-aa2d-2e5c4d04d90b")
public class ModelComponentRamcContributor extends AbstractModelComponentContributor {
    @objid ("fc4e5cab-7b28-4998-b439-96ee92ced106")
    private IModelingSession session;

    @objid ("01447961-7687-4321-82b0-9ca8ac5548c1")
    public ModelComponentRamcContributor(final IModule module) {
        super(module);
        this.session = module.getModuleContext().getModelingSession();
    }

    @objid ("6cb304c4-1f2f-41c3-8806-d0ca81c271a6")
    @Override
    public Set<Stereotype> getDependencyStereotypes() {
        // No stereotypes to contribute...
        return Collections.emptySet();
    }

    @objid ("4971131f-bc42-4076-8d5a-fd6d2dcfab71")
    @Override
    public Set<MObject> getElements() {
        // No elements to contribute...
        return Collections.emptySet();
    }

    @objid ("ea5c25c9-801a-480c-9d1c-0a5631e2850a")
    @Override
    public Set<ExportedFileEntry> getFiles() {
        return Collections.emptySet();
    }

    @objid ("1592ab01-6225-4b80-a672-52acb93d0940")
    @Override
    public Set<NoteType> getNoteTypes() {
        // Add standard note types
        Set<NoteType> noteTypes = new HashSet<>();
        try {
            noteTypes.add(getNoteType(ModelElement.class, IModelerModuleNoteTypes.MODELELEMENT_DESCRIPTION));
            noteTypes.add(getNoteType(ModelElement.class, IModelerModuleNoteTypes.MODELELEMENT_COMMENT));
        } catch (ExtensionNotFoundException e) {
            getModule().getModuleContext().getLogService().error(e.getMessage());
        }
        return noteTypes;
    }

    @objid ("07c38313-f924-47f2-b713-33fcd4f9de41")
    @Override
    public Set<TagType> getTagTypes() {
        // Add standard tag types
        Set<TagType> tagTypes = new HashSet<>();
        try {
            tagTypes.add(getTagType(AssociationEnd.class, IModelerModuleTagTypes.ASSOCIATIONEND_NOCODE));
            tagTypes.add(getTagType(Attribute.class, IModelerModuleTagTypes.ATTRIBUTE_NOCODE));
            tagTypes.add(getTagType(GeneralClass.class, IModelerModuleTagTypes.GENERALCLASS_NOCODE));
            tagTypes.add(getTagType(Operation.class, IModelerModuleTagTypes.OPERATION_NOCODE));
            tagTypes.add(getTagType(Package.class, IModelerModuleTagTypes.PACKAGE_NOCODE));
        
            tagTypes.add(getTagType(ModelElement.class, IModelerModuleTagTypes.MODELELEMENT_NODOC));
        } catch (ExtensionNotFoundException e) {
            getModule().getModuleContext().getLogService().error(e.getMessage());
        }
        return tagTypes;
    }

    /**
     * Get a {@link NoteType} from the metamodel extensions.
     */
    @objid ("512b3b51-9d8e-41af-a381-3003ac1d2061")
    private NoteType getNoteType(final java.lang.Class<? extends Element> metaclass, final String noteTypeName) {
        IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();
        return metamodel.getNoteType(IModelerModulePeerModule.MODULE_NAME, noteTypeName, getModule().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(metaclass));
    }

    /**
     * Get a {@link TagType} from the metamodel extensions.
     */
    @objid ("5681fb9b-81b7-451f-967f-ac91055ea7d4")
    private TagType getTagType(final java.lang.Class<? extends Element> metaclass, final String tagTypeName) {
        IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();
        return metamodel.getTagType(IModelerModulePeerModule.MODULE_NAME, tagTypeName, getModule().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(metaclass));
    }

}
