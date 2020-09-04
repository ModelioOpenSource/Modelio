/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.api.impl.model;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class is used to get the module extensions.<br>
 * It's not possible to add or update such extensions using this class. The only way to create new extensions is to use the <i>MDA Modeler tool</i>.
 * <p>
 * <p>
 * 
 * The known extensions are the following:
 * <ul>
 * <li>Stereotypes ({@link Stereotype})</li>
 * <li>Tag types ({@link TagType})</li>
 * <li>Note types ({@link NoteType})</li>
 * <li>Resource types ({@link ResourceType})</li>
 * <li>PropertyDefinitions ({@link PropertyDefinition})</li>
 * </ul>
 */
@objid ("6409a672-168b-4358-9ee1-08bfbdb44335")
public class MetamodelExtensions implements IMetamodelExtensions {
    @objid ("fc85f2c7-15c6-4757-933b-7c642ce10d7e")
    private IMModelServices modelService;

    /**
     * Default constructor initializing the model service.
     * @param modelService the model service used to find mda extensions.
     */
    @objid ("b9ee8668-665f-4fde-8a1e-81da6117d149")
    public MetamodelExtensions(final IMModelServices modelService) {
        this.modelService = modelService;
    }

    @objid ("d1987829-8635-45c9-8a81-9d8d3751fcc8")
    @Override
    public List<Stereotype> findStereotypes(final String stereotypeName, final MClass metaclass) {
        return this.modelService.findStereotypes(".*", stereotypeName, metaclass);
    }

    @objid ("451a7110-bdb2-48e4-af11-bbae6a27fff9")
    @Override
    public Stereotype getStereotype(final String stereotypeName, final MClass metaclass) {
        try {
            return this.modelService.getStereotype(".*", stereotypeName, metaclass);
        } catch (@SuppressWarnings ("unused") ElementNotUniqueException e) {
            return null;
        }
    }

    @objid ("5eaacbcc-ade1-45b2-adcd-2f9568ba57f1")
    @Override
    public List<Stereotype> findStereotypes(final String moduleName, final String stereotypeName, final MClass metaclass) {
        return this.modelService.findStereotypes(moduleName, stereotypeName, metaclass);
    }

    @objid ("fbf68202-087c-4164-8ff4-d514bb65a248")
    @Override
    public Stereotype getStereotype(final String moduleName, final String stereotypeName, final MClass metaclass) {
        try {
            return this.modelService.getStereotype(moduleName, stereotypeName, metaclass);
        } catch (@SuppressWarnings ("unused") ElementNotUniqueException e) {
            return null;
        }
    }

    @objid ("e271cb4a-a7bd-4089-9990-38e0b3430db9")
    @Override
    public List<NoteType> findNoteTypes(final String noteTypeName, final MClass metaclass) {
        return this.modelService.findNoteTypes(".*", ".*", noteTypeName, metaclass);
    }

    @objid ("f5048fb3-ddcb-4a40-959d-6f50e51cf7f2")
    @Override
    public NoteType getNoteType(final String moduleName, final String noteTypeName, final MClass metaclass) {
        try {
            return this.modelService.getNoteType(moduleName, ".*", noteTypeName, metaclass);
        } catch (@SuppressWarnings ("unused") ElementNotUniqueException e) {
            return null;
        }
    }

    @objid ("65800c35-4002-499d-8b68-33f88e02fa62")
    @Override
    public List<NoteType> findNoteTypes(final String moduleName, final String noteTypeName, final MClass metaclass) {
        return this.modelService.findNoteTypes(moduleName, ".*", noteTypeName, metaclass);
    }

    @objid ("b7c789bb-de38-45ea-957a-d3d9a6512dc7")
    @Override
    public NoteType getNoteType(final Stereotype stereotype, final String noteTypeName) {
        List<NoteType> results = new ArrayList<>();
        for (NoteType noteType : stereotype.getDefinedNoteType()) {
            if (nameMatches(noteType, noteTypeName)) {
                results.add(noteType);
            }
        }
        
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @objid ("8a2c709f-e6df-49fb-bddb-99dc9441c53d")
    @Override
    public List<ResourceType> findExternDocumentTypes(final String externDocumentTypeName, final MClass metaclass) {
        return this.modelService.findResourceTypes(".*", ".*", externDocumentTypeName, metaclass);
    }

    @objid ("329ec535-7d2b-4706-8739-a26835cfd48a")
    @Override
    public List<ResourceType> findExternDocumentTypes(final String moduleName, final String externDocumentTypeName, final MClass metaclass) {
        return this.modelService.findResourceTypes(moduleName, ".*", externDocumentTypeName, metaclass);
    }

    @objid ("3f5cc3c6-0e56-46f4-9b51-003f99d1ef57")
    @Override
    public ResourceType getExternDocumentType(final String moduleName, final String externDocumentTypeName, final MClass metaclass) {
        try {
            return this.modelService.getResourceType(moduleName, ".*", externDocumentTypeName, metaclass);
        } catch (@SuppressWarnings ("unused") ElementNotUniqueException e) {
            return null;
        }
    }

    @objid ("c1039865-3d70-444f-b2eb-431d8157dc34")
    @Override
    public ResourceType getExternDocumentType(final Stereotype stereotype, final String resourceTypeName) {
        List<ResourceType> results = new ArrayList<>();
        for (ResourceType resourceType : stereotype.getDefinedResourceType()) {
            if (nameMatches(resourceType, resourceTypeName)) {
                results.add(resourceType);
            }
        }
        
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @objid ("ae21accb-96ac-4ff9-956c-19b8689dbbaf")
    @Override
    public List<TagType> findTagTypes(final String tagTypeName, final MClass metaclass) {
        return this.modelService.findTagTypes(".*", ".*", tagTypeName, metaclass);
    }

    @objid ("6a1104ae-e110-43a5-bb03-fd15d6ebb193")
    @Override
    public List<TagType> findTagTypes(final String moduleName, final String tagTypeName, final MClass metaclass) {
        return this.modelService.findTagTypes(moduleName, ".*", tagTypeName, metaclass);
    }

    @objid ("c4d88778-63cb-4d29-99d4-346045df34b5")
    @Override
    public TagType getTagType(final String moduleName, final String tagTypeName, final MClass metaclass) {
        try {
            return this.modelService.getTagType(moduleName, ".*", tagTypeName, metaclass);
        } catch (@SuppressWarnings ("unused") ElementNotUniqueException e) {
            return null;
        }
    }

    @objid ("5fe5eb24-f885-4fd1-888d-bb0db43af2d9")
    @Override
    public TagType getTagType(final Stereotype stereotype, final String tagTypeName) {
        List<TagType> results = new ArrayList<>();
        for (TagType tagType : stereotype.getDefinedTagType()) {
            if (nameMatches(tagType, tagTypeName)) {
                results.add(tagType);
            }
        }
        
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @objid ("47acd5b5-ee1b-4605-b122-b34e4e6cd5c5")
    @Override
    public String getLabel(final Stereotype stereotype) {
        return ModuleI18NService.getLabel(stereotype);
    }

    @objid ("25b8a355-b042-48e7-9d19-7cb868c8a44a")
    @Override
    public String getLabel(final TagType tagType) {
        return ModuleI18NService.getLabel(tagType);
    }

    @objid ("5c2431fd-79fd-4f43-af6b-bc13f0fff1b0")
    @Override
    public String getLabel(final NoteType noteType) {
        return ModuleI18NService.getLabel(noteType);
    }

    @objid ("a1cf88d8-89c6-451e-ac3e-a344c973fc59")
    @Override
    public String getLabel(final ResourceType docType) {
        return ModuleI18NService.getLabel(docType);
    }

    @objid ("be72b2a5-b041-4d83-984a-9d04a2d87a5a")
    @Override
    public String getLabel(final PropertyDefinition pdef) {
        return ModuleI18NService.getLabel(pdef);
    }

    @objid ("dd400467-a0ce-4c9c-9c82-6049ed06e9a1")
    @Override
    public String getDescription(final PropertyDefinition pdef) {
        return ModuleI18NService.getDescription(pdef);
    }

    @objid ("d42e5a6f-ccc0-4aca-bdc0-bb6062615a84")
    private boolean nameMatches(MObject child, String pattern) {
        String name = child.getName();
        return name.matches((pattern == null || pattern.isEmpty()) ? ".*" : pattern) || name.equals(pattern);
    }

}
