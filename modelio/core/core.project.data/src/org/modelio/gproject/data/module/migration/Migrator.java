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
package org.modelio.gproject.data.module.migration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import org.modelio.gproject.data.module.jaxbv1.JxbClasspath;
import org.modelio.gproject.data.module.jaxbv1.JxbClasspath.Entry;
import org.modelio.gproject.data.module.jaxbv1.JxbContextualCommand;
import org.modelio.gproject.data.module.jaxbv1.JxbContextualCommand.Contribution;
import org.modelio.gproject.data.module.jaxbv1.JxbDiagramCommand;
import org.modelio.gproject.data.module.jaxbv1.JxbDiagramCommandBox;
import org.modelio.gproject.data.module.jaxbv1.JxbDiagramCommandLink;
import org.modelio.gproject.data.module.jaxbv1.JxbDocpath;
import org.modelio.gproject.data.module.jaxbv1.JxbExterndocumenttype;
import org.modelio.gproject.data.module.jaxbv1.JxbHandler;
import org.modelio.gproject.data.module.jaxbv1.JxbModule;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies.Optional;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies.Required;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram.Palette;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.ElementCreationCommand;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter.JxbEnumeration;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbAnonymousStereotype;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype.Icons;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype.Icons.Diagram;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype.Icons.Explorer;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype.Icons.Small;
import org.modelio.gproject.data.module.jaxbv1.JxbNotetype;
import org.modelio.gproject.data.module.jaxbv1.JxbScope;
import org.modelio.gproject.data.module.jaxbv1.JxbTaggedvalues;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Command;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Enumeration;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Enumeration.Jxbv2Literal;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2ExternDocumentType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Handler;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Handler.Jxbv2HParameter;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Optional;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Required;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu.Jxbv2CommandRef;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette.Jxbv2ToolRef;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters.Jxbv2Parameter;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Icon;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Image;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2MultiPathes;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2MultiPathes.Jxbv2PathEntry;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2NoteType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Scope;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2TagType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Tool;
import org.modelio.gproject.data.module.jaxbv2.ObjectFactory;

@objid ("d47e40a4-35de-4477-ae53-bd3f80d8dbb8")
public class Migrator {
    @objid ("8ad46312-b0ff-42e9-b198-9222344f5314")
    private final ObjectFactory factoryV2 = new ObjectFactory();

    @objid ("d0e0b0e4-2c29-4621-b375-18606ab21c0f")
    public Jxbv2Module migrate(JxbModule moduleV1) {
        // Create the module
        final Jxbv2Module moduleV2 = migrateModule(moduleV1);
        return moduleV2;
    }

    @objid ("9288ab85-8237-416e-9559-6854ccd5ef1a")
    private Jxbv2Module migrateModule(JxbModule moduleV1) {
        // Create the module
        final Jxbv2Module moduleV2 = this.factoryV2.createModule();
        
        // Attributes and trivial cases
        moduleV2.setAuthor(moduleV1.getAuthor());
        moduleV2.setBinaryversion(moduleV1.getBinaryversion());
        moduleV2.setClazz(moduleV1.getClazz());
        moduleV2.setId(moduleV1.getName());
        moduleV2.setImage(moduleV1.getImage());
        moduleV2.setUid(moduleV1.getUid());
        moduleV2.setVersion(moduleV1.getVersion());
        moduleV2.setSchemaLevel(2L);
        
        // Composed elements
        // ClassPath
        migrateClassPath(moduleV1, moduleV2);
        
        // DocPath
        migrateDocPath(moduleV1, moduleV2);
        
        // Dependencies
        migrateDependencies(moduleV1, moduleV2);
        
        // Parameters
        migrateParameters(moduleV1, moduleV2);
        
        // Profile
        migrateProfiles(moduleV1, moduleV2);
        
        // Gui
        migrateGUI(moduleV1, moduleV2);
        
        // PropertyTypes
        // nothing to do since PropertyTypes did not exist in V1
        return moduleV2;
    }

    @objid ("dcd62cee-ef90-4c7d-88a8-b4d95ca31db7")
    private void migrateClassPath(JxbModule moduleV1, Jxbv2Module moduleV2) {
        final JxbClasspath classpathV1 = V1Utils.getClassPath(moduleV1);
        if (classpathV1 != null) {
            final Jxbv2MultiPathes classpathV2 = this.factoryV2.createMultiPathes();
            for (final Entry entryV1 : classpathV1.getEntry()) {
                final Jxbv2PathEntry entryV2 = this.factoryV2.createMultiPathesPathEntry();
                entryV2.setPath(entryV1.getPath());
                classpathV2.getPathEntry().add(entryV2);
            }
            moduleV2.setClassPath(classpathV2);
        }
        
    }

    @objid ("600e1364-503e-4978-b9af-13ee48b52997")
    private void migrateDocPath(JxbModule moduleV1, Jxbv2Module moduleV2) {
        final JxbDocpath docpathV1 = V1Utils.getDocPath(moduleV1);
        if (docpathV1 != null) {
        
            final Jxbv2MultiPathes docpathV2 = this.factoryV2.createMultiPathes();
            for (final org.modelio.gproject.data.module.jaxbv1.JxbDocpath.Entry entryV1 : docpathV1.getEntry()) {
                final Jxbv2PathEntry entryV2 = this.factoryV2.createMultiPathesPathEntry();
                entryV2.setPath(entryV1.getPath());
                docpathV2.getPathEntry().add(entryV2);
            }
        
            if (moduleV2.getResources() == null) {
                moduleV2.setResources(this.factoryV2.createModuleResources());
            }
            moduleV2.getResources().setDocFiles(docpathV2);
        }
        
    }

    @objid ("4c78031a-f9b2-400b-84d1-b2a357ec850e")
    private void migrateDependencies(JxbModule moduleV1, Jxbv2Module moduleV2) {
        // Create V2 Dependencies section
        moduleV2.setDependencies(this.factoryV2.createModuleDependencies());
        
        for (final Optional depV1 : V1Utils.getOptionalDependencies(moduleV1)) {
            final Jxbv2Optional depV2 = this.factoryV2.createModuleDependenciesOptional();
            depV2.setName(depV1.getName());
            depV2.setVersion(depV1.getVersion());
            moduleV2.getDependencies().getOptional().add(depV2);
        }
        
        for (final Required depV1 : V1Utils.getRequiredDependencies(moduleV1)) {
            final Jxbv2Required depV2 = this.factoryV2.createModuleDependenciesRequired();
            depV2.setName(depV1.getName());
            depV2.setVersion(depV1.getVersion());
            moduleV2.getDependencies().getRequired().add(depV2);
        }
        
    }

    @objid ("23228bcf-fa56-43c7-9b9e-3d2d45a67734")
    private void migrateParameters(JxbModule moduleV1, Jxbv2Module moduleV2) {
        // Create V2 Parameters section
        moduleV2.setParameters(this.factoryV2.createModuleParameters());
        
        for (final JxbParameter paramV1 : V1Utils.getParameters(moduleV1)) {
            // Create new V2 parameter
            final Jxbv2Parameter paramV2 = this.factoryV2.createModuleParametersParameter();
        
            // Simple cases
            paramV2.setDefaultValue(paramV1.getDefaultValue());
            paramV2.setDescription(paramV2.getDescription());
        
            paramV2.setGroup(paramV1.getGroup());
            paramV2.setId(paramV1.getName());
            paramV2.setLabel(paramV1.getLabel());
            paramV2.setType(paramV1.getType());
            paramV2.setUid(paramV1.getUid());
        
            // Enumeration
            final JxbEnumeration enumV1 = V1Utils.getParameterEnumeration(paramV1);
            if (enumV1 != null) {
                paramV2.setEnumeration(this.factoryV2.createEnumeration());
                migrateParameterEnumeration(enumV1, paramV2.getEnumeration());
            }
        
            // Description
            final String descriptionV1 = V1Utils.getParameterDescription(paramV1);
            if (descriptionV1 != null) {
                paramV2.setDescription(descriptionV1);
            }
        
            // Add V2 parameter
            moduleV2.getParameters().getParameter().add(paramV2);
        }
        
    }

    @objid ("8bb48769-f0f5-4142-9340-cc8e65f80921")
    private void migrateProfiles(JxbModule moduleV1, Jxbv2Module moduleV2) {
        // Create V2 Profiles section
        moduleV2.setProfiles(this.factoryV2.createModuleProfiles());
        
        for (final JxbProfile profileV1 : V1Utils.getProfiles(moduleV1)) {
            final Jxbv2Profile profileV2 = this.factoryV2.createModuleProfilesProfile();
            migrateOneProfile(profileV1, profileV2);
            moduleV2.getProfiles().getProfile().add(profileV2);
        }
        
    }

    @objid ("35b0c00d-2aab-49da-aa0c-e3091638574c")
    private void migrateOneProfile(JxbProfile profileV1, Jxbv2Profile profileV2) {
        // Attributes
        profileV2.setId(profileV1.getName());
        profileV2.setUid(profileV1.getUid());
        
        // Stereotypes
        migrateStereotypes(profileV1, profileV2);
        
        // MetaclassReferences
        migrateMetaclassReferences(profileV1, profileV2);
        
    }

    @objid ("1059a9f9-5b94-4412-aa5e-5cc21c246528")
    private void migrateStereotypes(JxbProfile profileV1, Jxbv2Profile profileV2) {
        for (final JxbStereotype stV1 : V1Utils.getStereotypes(profileV1)) {
            final Jxbv2Stereotype stV2 = this.factoryV2.createModuleProfilesProfileStereotype();
            migrateOneStereotype(stV1, stV2);
            profileV2.getStereotype().add(stV2);
        }
        
    }

    @objid ("e8a1fb6a-0759-469f-96c4-4b8f0bb6bf10")
    private void migrateMetaclassReferences(JxbProfile profileV1, Jxbv2Profile profileV2) {
        for (final JxbAnonymousStereotype stV1 : V1Utils.getAnonymousStereotypes(profileV1)) {
            final Jxbv2MetaclassReference stV2 = this.factoryV2.createModuleProfilesProfileMetaclassReference();
            migrateOneMetaclassReference(stV1, stV2);
            profileV2.getMetaclassReference().add(stV2);
        }
        
    }

    @objid ("1a05deb5-69e1-4e5c-8bde-945696b8082c")
    private void migrateOneStereotype(JxbStereotype stV1, Jxbv2Stereotype stV2) {
        // Attributes
        stV2.setName(stV1.getName());
        stV2.setIsHidden(stV1.getIsHidden());
        stV2.setLabel(stV1.getLabel());
        stV2.setMetaclass(stV1.getMetaclass());
        stV2.setOwnerStereotype(stV1.getOwnerStereotype());
        stV2.setUid(stV1.getUid());
        
        // Composed elements
        stV2.setTagTypes(this.factoryV2.createModuleProfilesProfileStereotypeTagTypes());
        
        for (final Object obj : stV1.getIconsOrTaggedvaluesOrNotetype()) {
            if (obj instanceof Icons) {
                migrateIcons((Icons) obj, stV2);
            }
        
            // TagType
            if (obj instanceof JxbTaggedvalues) {
                final JxbTaggedvalues taggedValuesV1 = (JxbTaggedvalues) obj;
                final Jxbv2TagType tagTypeV2 = this.factoryV2.createTagType();
        
                tagTypeV2.setName(taggedValuesV1.getName());
                tagTypeV2.setIsHidden(taggedValuesV1.getIsHidden());
                tagTypeV2.setIsSigned(taggedValuesV1.getIsSigned());
                tagTypeV2.setLabel(taggedValuesV1.getLabel());
                tagTypeV2.setParameterCard(taggedValuesV1.getParameterCard());
                tagTypeV2.setUid(taggedValuesV1.getUid());
        
                stV2.getTagTypes().getTagType().add(tagTypeV2);
            }
        
            // NoteType
            if (obj instanceof JxbNotetype) {
        
                final JxbNotetype noteTypeV1 = (JxbNotetype) obj;
                final Jxbv2NoteType noteTypeV2 = this.factoryV2.createNoteType();
        
                noteTypeV2.setName(noteTypeV1.getName());
                noteTypeV2.setIsHidden(noteTypeV1.getIsHidden());
                noteTypeV2.setLabel(noteTypeV1.getLabel());
                noteTypeV2.setUid(noteTypeV1.getUid());
        
                if (stV2.getNoteTypes() == null) {
                    stV2.setNoteTypes(this.factoryV2.createModuleProfilesProfileStereotypeNoteTypes());
                }
                stV2.getNoteTypes().getNoteType().add(noteTypeV2);
            }
        
            // ExternDocumentType
            if (obj instanceof JxbExterndocumenttype) {
        
                final JxbExterndocumenttype noteTypeV1 = (JxbExterndocumenttype) obj;
                final Jxbv2ExternDocumentType noteTypeV2 = this.factoryV2.createExternDocumentType();
        
                noteTypeV2.setName(noteTypeV1.getName());
                noteTypeV2.setIsHidden(noteTypeV1.getIsHidden());
                noteTypeV2.setLabel(noteTypeV1.getLabel());
                noteTypeV2.setUid(noteTypeV1.getUid());
        
                if (stV2.getExternDocumentTypes() == null) {
                    stV2.setExternDocumentTypes(this.factoryV2.createModuleProfilesProfileStereotypeExternDocumentTypes());
                }
                stV2.getExternDocumentTypes().getExternDocumentType().add(noteTypeV2);
            }
        
            // PropertyTable
            // nothing to do since PropertyTable did not exist in V1
        
        }
        
    }

    /**
     * @param stV2
     * @param obj
     */
    @objid ("81cb5bb7-f5b2-4c83-b73c-e1ef6ca904ef")
    private void migrateIcons(Icons icons, Jxbv2Stereotype stV2) {
        for (final Object obj : icons.getSmallOrExplorerOrDiagram()) {
            if (obj instanceof Small) {
                // Migrated as V2 icon (might override 'Explorer')
                final Jxbv2Icon iconV2 = this.factoryV2.createModuleProfilesProfileStereotypeIcon();
                iconV2.setPath(((Small) obj).getPath());
                stV2.setIcon(iconV2);
            }
            if (obj instanceof Explorer) {
                // Migrated as V2 icon (might override 'Small')
                final Jxbv2Icon iconV2 = this.factoryV2.createModuleProfilesProfileStereotypeIcon();
                iconV2.setPath(((Explorer) obj).getPath());
                stV2.setIcon(iconV2);
            }
            if (obj instanceof Diagram) {
                // Migrated as V2 image
                final Jxbv2Image imageV2 = this.factoryV2.createModuleProfilesProfileStereotypeImage();
                imageV2.setPath(((Diagram) obj).getPath());
                stV2.setImage(imageV2);
            }
        }
        
    }

    @objid ("f37f44c8-9c5e-469e-8c88-64d044973089")
    private void migrateOneMetaclassReference(JxbAnonymousStereotype stV1, Jxbv2MetaclassReference stV2) {
        // Attributes
        stV2.setMetaclass(stV1.getMetaclass());
        stV2.setUid(stV1.getUid());
        
        // Composed elements
        for (final Object obj : stV1.getTaggedvaluesOrNotetypeOrExterndocumenttype()) {
        
            // TagType
            if (obj instanceof JxbTaggedvalues) {
                final JxbTaggedvalues taggedValuesV1 = (JxbTaggedvalues) obj;
                final Jxbv2TagType tagTypeV2 = this.factoryV2.createTagType();
        
                tagTypeV2.setName(taggedValuesV1.getName());
                tagTypeV2.setIsHidden(taggedValuesV1.getIsHidden());
                tagTypeV2.setIsSigned(taggedValuesV1.getIsSigned());
                tagTypeV2.setLabel(taggedValuesV1.getLabel());
                tagTypeV2.setParameterCard(taggedValuesV1.getParameterCard());
                tagTypeV2.setUid(taggedValuesV1.getUid());
        
                if (stV2.getTagTypes() == null) {
                    stV2.setTagTypes(this.factoryV2.createModuleProfilesProfileMetaclassReferenceTagTypes());
                }
                stV2.getTagTypes().getTagType().add(tagTypeV2);
            }
        
            // NoteType
            if (obj instanceof JxbNotetype) {
                final JxbNotetype noteTypeV1 = (JxbNotetype) obj;
                final Jxbv2NoteType noteTypeV2 = this.factoryV2.createNoteType();
        
                noteTypeV2.setName(noteTypeV1.getName());
                noteTypeV2.setIsHidden(noteTypeV1.getIsHidden());
                noteTypeV2.setLabel(noteTypeV1.getLabel());
                noteTypeV2.setUid(noteTypeV1.getUid());
        
                if (stV2.getNoteTypes() == null) {
                    stV2.setNoteTypes(this.factoryV2.createModuleProfilesProfileMetaclassReferenceNoteTypes());
                }
                stV2.getNoteTypes().getNoteType().add(noteTypeV2);
            }
        
            // ExternDocumentType
            if (obj instanceof JxbExterndocumenttype) {
                final JxbExterndocumenttype noteTypeV1 = (JxbExterndocumenttype) obj;
                final Jxbv2ExternDocumentType noteTypeV2 = this.factoryV2.createExternDocumentType();
        
                noteTypeV2.setName(noteTypeV1.getName());
                noteTypeV2.setIsHidden(noteTypeV1.getIsHidden());
                noteTypeV2.setLabel(noteTypeV1.getLabel());
                noteTypeV2.setUid(noteTypeV1.getUid());
        
                if (stV2.getExternDocumentTypes() == null) {
                    stV2.setExternDocumentTypes(this.factoryV2.createModuleProfilesProfileMetaclassReferenceExternDocumentTypes());
                }
                stV2.getExternDocumentTypes().getExternDocumentType().add(noteTypeV2);
            }
        }
        
    }

    /**
     * Migration of the GUI of the module
     * @param moduleV1
     * @param moduleV2
     */
    @objid ("3e1aee9a-1096-4d75-aa9a-b880085e9853")
    private void migrateGUI(JxbModule moduleV1, Jxbv2Module moduleV2) {
        final Gui guiV1 = V1Utils.getGui(moduleV1);
        
        if (guiV1 == null) {
            return;
        }
        
        final Jxbv2Gui guiV2 = this.factoryV2.createModuleGui();
        moduleV2.setGui(guiV2);
        
        // Commands
        migrateCommands(guiV1, guiV2);
        
        // Tools
        migrateTools(guiV1, guiV2);
        
        // Contextual menu
        migrateContextualMenu(guiV1, guiV2);
        
        // Diagrams
        migrateDiagrams(guiV1, guiV2);
        
        // Views
        migrateViews(guiV1, guiV2);
        
    }

    @objid ("dbec51e2-3bc4-4c30-86be-4df91104a860")
    private void migrateDiagrams(Gui guiV1, Jxbv2Gui guiV2) {
        guiV2.setDiagrams(this.factoryV2.createModuleGuiDiagrams());
        
        for (final CustomizedDiagram diagramV1 : V1Utils.getCustomizedDiagrams(guiV1)) {
        
            final Jxbv2DiagramType diagramV2 = this.factoryV2.createModuleGuiDiagramsDiagramType();
        
            diagramV2.setBaseDiagram(diagramV1.getBaseDiagram());
            diagramV2.setStereotype(diagramV1.getStereotype());
        
            for (final Object obj : diagramV1.getPaletteOrStyle()) {
                // Migrate Palette
                final Palette paletteV1 = V1Utils.getDiagramPalette(diagramV1);
                if (paletteV1 != null) {
                    final Jxbv2Palette paletteV2 = this.factoryV2.createModuleGuiDiagramsDiagramTypePalette();
                    migratePalette(diagramV1, paletteV1, paletteV2);
                    diagramV2.setPalette(paletteV2);
                }
        
                // Migrate Style => V1 Style do not migrate in V2. nothing to do
            }
        
            // Add standard handler
            Jxbv2Handler handler = new Jxbv2Handler();
            handler.setClazz("StandardCustomizer");
            diagramV2.setHandler(handler);
        
            guiV2.getDiagrams().getDiagramType().add(diagramV2);
        }
        
    }

    @objid ("d5ac771b-3634-481c-8dc2-4ae389785d99")
    private void migratePalette(CustomizedDiagram diagramV1, Palette paletteV1, Jxbv2Palette paletteV2) {
        paletteV2.setKeepBasePalette(diagramV1.isKeepBasePalette());
        
        for (final JAXBElement<?> obj : paletteV1.getDiagramCommandOrDiagramCommandBoxOrDiagramCommandLink()) {
            String toolNameV1 = "";
            String toolGroupV1 = "";
        
            if (obj.getValue() instanceof JxbDiagramCommand) {
                toolNameV1 = ((JxbDiagramCommand) obj.getValue()).getName();
                toolGroupV1 = ((JxbDiagramCommand) obj.getValue()).getGroup();
            }
            if (obj.getValue() instanceof JxbDiagramCommandBox) {
                toolNameV1 = ((JxbDiagramCommandBox) obj.getValue()).getName();
                toolGroupV1 = ((JxbDiagramCommandBox) obj.getValue()).getGroup();
            }
            if (obj.getValue() instanceof JxbDiagramCommandLink) {
                toolNameV1 = ((JxbDiagramCommandLink) obj.getValue()).getName();
                toolGroupV1 = ((JxbDiagramCommandLink) obj.getValue()).getGroup();
            }
        
            final Jxbv2ToolRef toolrefV2 = this.factoryV2.createModuleGuiDiagramsDiagramTypePaletteToolRef();
            toolrefV2.setRefid(toolNameV1);
            toolrefV2.setGroup(toolGroupV1);
            paletteV2.getToolRef().add(toolrefV2);
        }
        
    }

    @objid ("665e4b5f-a2fb-45cd-9c6f-c449ef9ebe8d")
    private void migrateContextualMenu(org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui guiV1, Jxbv2Gui guiV2) {
        final Jxbv2ContextualMenu menuV2 = this.factoryV2.createModuleGuiContextualMenu();
        guiV2.getContextualMenu().add(menuV2);
        
        for (final JxbModule.Gui.Command commandV1 : V1Utils.getCommands(guiV1)) {
            final Contribution contribution = V1Utils.getContribution(commandV1);
            if ("contextualpopup".equals(contribution.getLocation())) {
                final Jxbv2CommandRef ref = this.factoryV2.createModuleGuiContextualMenuCommandRef();
                ref.setRefid(commandV1.getName());
                menuV2.getCommandRef().add(ref);
            }
        }
        
        for (final ElementCreationCommand commandV1 : V1Utils.getCreationCommands(guiV1)) {
            final Contribution contribution = V1Utils.getContribution(commandV1);
            if ("contextualpopup".equals(contribution.getLocation())) {
                final Jxbv2CommandRef ref = this.factoryV2.createModuleGuiContextualMenuCommandRef();
                ref.setRefid(commandV1.getName());
                menuV2.getCommandRef().add(ref);
            }
        }
        
    }

    /**
     * In V2 there is a single 'Tools' section that holds all the tools collected from V1 in diagram palettes.
     * @param guiV1
     * @param guiV2
     */
    @objid ("ed5741e1-2ca0-43c4-b795-8f428fbde8d1")
    private void migrateTools(Gui guiV1, Jxbv2Gui guiV2) {
        // Create Tools section
        guiV2.setTools(this.factoryV2.createModuleGuiTools());
        
        // Collect tools
        for (final CustomizedDiagram diagramV1 : V1Utils.getCustomizedDiagrams(guiV1)) {
            final Palette paletteV1 = V1Utils.getDiagramPalette(diagramV1);
            if (paletteV1 != null) {
                for (final JAXBElement<?> obj : paletteV1.getDiagramCommandOrDiagramCommandBoxOrDiagramCommandLink()) {
        
                    if (obj.getValue() instanceof JxbDiagramCommand) {
                        final JxbDiagramCommand cmdV1 = (JxbDiagramCommand) obj.getValue();
                        final Jxbv2Tool cmdV2 = this.factoryV2.createTool();
                        migrateBoxTool(cmdV1, cmdV2);
                        guiV2.getTools().getTool().add(cmdV2);
                    }
                    if (obj.getValue() instanceof JxbDiagramCommandBox) {
                        final JxbDiagramCommandBox cmdV1 = (JxbDiagramCommandBox) obj.getValue();
                        final Jxbv2Tool cmdV2 = this.factoryV2.createTool();
                        migrateBoxTool(cmdV1, cmdV2);
                        guiV2.getTools().getTool().add(cmdV2);
                    }
                    if (obj.getValue() instanceof JxbDiagramCommandLink) {
                        final JxbDiagramCommandLink cmdV1 = (JxbDiagramCommandLink) obj.getValue();
                        final Jxbv2Tool cmdV2 = this.factoryV2.createTool();
                        migrateLinkTool(cmdV1, cmdV2);
                        guiV2.getTools().getTool().add(cmdV2);
                    }
                }
            }
        }
        
    }

    @objid ("f04c89f5-b5e0-45a3-8ede-b3a74614709f")
    private void migrateLinkTool(JxbDiagramCommandLink cmdV1, Jxbv2Tool cmdV2) {
        // Attributes
        cmdV2.setId(cmdV1.getName());
        cmdV2.setImage(cmdV1.getImage());
        cmdV2.setLabel(cmdV1.getLabel());
        cmdV2.setTooltip(cmdV1.getTooltip());
        
        // Composed (Handler, Scopes)
        final JxbHandler handlerV1 = cmdV1.getHandler();
        final Jxbv2Handler handlerV2 = this.factoryV2.createHandler();
        
        migrateToolHandler(handlerV1, handlerV2, "Link");
        
        cmdV2.setHandler(handlerV2);
        
        for (final JxbScope sourceScopeV1 : cmdV1.getScopeSource()) {
            final Jxbv2Scope sourceScopeV2 = this.factoryV2.createScope();
            sourceScopeV2.setMetaclass(sourceScopeV1.getMetaclass());
            sourceScopeV2.setStereotype(sourceScopeV1.getStereotype());
            cmdV2.getScopeSource().add(sourceScopeV2);
        }
        
        for (final JxbScope targetScopeV1 : cmdV1.getScopeTarget()) {
            final Jxbv2Scope targetScopeV2 = this.factoryV2.createScope();
            targetScopeV2.setMetaclass(targetScopeV1.getMetaclass());
            targetScopeV2.setStereotype(targetScopeV1.getStereotype());
            cmdV2.getScopeTarget().add(targetScopeV2);
        }
        
    }

    @objid ("566baee7-8620-4448-bfb6-b834c7e0fd67")
    private void migrateBoxTool(JxbDiagramCommandBox cmdV1, Jxbv2Tool cmdV2) {
        // Attributes
        cmdV2.setId(cmdV1.getName());
        cmdV2.setImage(cmdV1.getImage());
        cmdV2.setLabel(cmdV1.getLabel());
        cmdV2.setTooltip(cmdV1.getTooltip());
        
        // Composed (Handler)
        for (final Object obj : cmdV1.getScopeOrHandler()) {
        
            if (obj instanceof JxbHandler) {
                final JxbHandler handlerV1 = (JxbHandler) obj;
                final Jxbv2Handler handlerV2 = this.factoryV2.createHandler();
                migrateToolHandler(handlerV1, handlerV2, "Box");
                cmdV2.setHandler(handlerV2);
            }
            if (obj instanceof JxbScope) {
                final JxbScope scopeV1 = (JxbScope) obj;
                final Jxbv2Scope scopeV2 = this.factoryV2.createScope();
                scopeV2.setMetaclass(scopeV1.getMetaclass());
                scopeV2.setStereotype(scopeV1.getStereotype());
                cmdV2.getScopeTarget().add(scopeV2);
            }
        }
        
    }

    @objid ("eea5c2e1-98f4-421d-a5da-4266002d1bb2")
    private void migrateBoxTool(JxbDiagramCommand cmdV1, Jxbv2Tool cmdV2) {
        // Attributes
        cmdV2.setId(cmdV1.getName());
        cmdV2.setImage(cmdV1.getImage());
        cmdV2.setLabel(cmdV1.getLabel());
        cmdV2.setTooltip(cmdV1.getTooltip());
        
        // Composed (Handler)
        final JxbHandler handlerV1 = cmdV1.getHandler();
        final Jxbv2Handler handlerV2 = this.factoryV2.createHandler();
        migrateToolHandler(handlerV1, handlerV2, "Box");
        cmdV2.setHandler(handlerV2);
        
    }

    @objid ("c478a154-caa9-429a-ab7a-1fb026ca67d0")
    private void migrateHandler(JxbHandler handlerV1, Jxbv2Handler handlerV2) {
        if (handlerV1.getClazz() != null) {
            handlerV2.setClazz(handlerV1.getClazz());
        } else {
            handlerV2.setClazz("GenericElementCreationHandler");
            if (handlerV1.getMetaclass() != null && !handlerV1.getMetaclass().isEmpty()) {
                final Jxbv2HParameter metaclass = this.factoryV2.createHandlerHParameter();
                metaclass.setName("metaclass");
                metaclass.setValue(handlerV1.getMetaclass());
                handlerV2.getHParameter().add(metaclass);
            }
            if (handlerV1.getStereotype() != null && !handlerV1.getStereotype().isEmpty()) {
                final Jxbv2HParameter stereotype = this.factoryV2.createHandlerHParameter();
                stereotype.setName("stereotype");
                stereotype.setValue(handlerV1.getStereotype());
                handlerV2.getHParameter().add(stereotype);
            }
            if (handlerV1.getRelation() != null && !handlerV1.getRelation().isEmpty()) {
                final Jxbv2HParameter relation = this.factoryV2.createHandlerHParameter();
                relation.setName("relation");
                relation.setValue(handlerV1.getRelation());
                handlerV2.getHParameter().add(relation);
            }
        }
        
    }

    /**
     * Command are collected from V1 in 'command', 'element-creation-command'
     * @param guiV1
     * @param guiV2
     */
    @objid ("205ee236-d62c-451f-8dbf-f569c3322756")
    private void migrateCommands(Gui guiV1, Jxbv2Gui guiV2) {
        // Create V2 commands section
        guiV2.setCommands(this.factoryV2.createModuleGuiCommands());
        
        // Both V1 'commands' and 'element creation commands' go in the V2 Commands section
        for (final JxbModule.Gui.Command commandV1 : V1Utils.getCommands(guiV1)) {
            final Jxbv2Command commandV2 = this.factoryV2.createCommand();
            migrateOneCommand(commandV1, commandV2);
            guiV2.getCommands().getCommand().add(commandV2);
        }
        for (final JxbModule.Gui.ElementCreationCommand commandV1 : V1Utils.getCreationCommands(guiV1)) {
            final Jxbv2Command commandV2 = this.factoryV2.createCommand();
            migrateOneCreationCommand(commandV1, commandV2);
            guiV2.getCommands().getCommand().add(commandV2);
        }
        
    }

    /**
     * @param commandV1
     * @param commandV2
     */
    @objid ("30ef00e7-b262-4fb6-9c33-dd348364ed3e")
    private void migrateOneCommand(org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.Command commandV1, Jxbv2Command commandV2) {
        commandV2.setGroup(commandV1.getGroup());
        commandV2.setGroupImage(commandV1.getGroupImage());
        
        for (final Object xobj : commandV1.getScopeOrHandlerOrContribution()) {
            if (xobj instanceof JxbHandler) {
                final JxbHandler handlerV1 = (JxbHandler) xobj;
                final Jxbv2Handler handlerV2 = this.factoryV2.createHandler();
                migrateHandler(handlerV1, handlerV2);
                commandV2.setHandler(handlerV2);
            }
            if (xobj instanceof JxbScope) {
                final JxbScope scopeV1 = (JxbScope) xobj;
                final Jxbv2Scope scopeV2 = this.factoryV2.createScope();
                scopeV2.setMetaclass(scopeV1.getMetaclass());
                scopeV2.setStereotype(scopeV1.getStereotype());
                commandV2.getScope().add(scopeV2);
            }
            if (xobj instanceof JxbContextualCommand.Contribution) {
                // contributions are no longer needed
            }
        }
        
        commandV2.setId(commandV1.getName());
        commandV2.setLabel(commandV1.getLabel());
        commandV2.setModifyModel(commandV1.getModifyModel());
        commandV2.setTooltip(commandV1.getTooltip());
        
    }

    /**
     * @param commandV1
     * @param commandV2
     */
    @objid ("d1025882-900c-4b21-bb6a-b17f528a71f5")
    private void migrateOneCreationCommand(ElementCreationCommand commandV1, Jxbv2Command commandV2) {
        commandV2.setGroup(commandV1.getGroup());
        commandV2.setGroupImage(commandV1.getGroupImage());
        
        for (final Object xobj : commandV1.getScopeOrHandlerOrContribution()) {
            if (xobj instanceof JxbHandler) {
                final JxbHandler handlerV1 = (JxbHandler) xobj;
                final Jxbv2Handler handlerV2 = this.factoryV2.createHandler();
                migrateHandler(handlerV1, handlerV2);
                commandV2.setHandler(handlerV2);
            }
            if (xobj instanceof JxbScope) {
                final JxbScope scopeV1 = (JxbScope) xobj;
                final Jxbv2Scope scopeV2 = this.factoryV2.createScope();
                scopeV2.setMetaclass(scopeV1.getMetaclass());
                scopeV2.setStereotype(scopeV1.getStereotype());
                commandV2.getScope().add(scopeV2);
            }
            if (xobj instanceof JxbContextualCommand.Contribution) {
                // contributions are no longer needed
            }
        }
        
        commandV2.setId(commandV1.getName());
        commandV2.setLabel(commandV1.getLabel());
        commandV2.setImage(commandV1.getImage());
        commandV2.setTooltip(commandV1.getTooltip());
        commandV2.setModifyModel(commandV1.getModifyModel());
        
    }

    @objid ("c1721027-ff82-4f0d-af99-f07456d92ad0")
    private void migrateViews(org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui guiV1, Jxbv2Gui guiV2) {
        // Create the V2 Views section
        guiV2.setViews(this.factoryV2.createModuleGuiViews());
        
        // Migrate the property pages which are the only available views in V1
        for (final JxbModule.Gui.PropertyPage propertyPageV1 : V1Utils.getPropertyPages(guiV1)) {
            final Jxbv2PropertyPage propertyPageV2 = this.factoryV2.createModuleGuiViewsPropertyPage();
            propertyPageV2.setId(propertyPageV1.getName());
            propertyPageV2.setClazz(propertyPageV1.getClazz());
            propertyPageV2.setImage(propertyPageV1.getImage());
            propertyPageV2.setLabel(propertyPageV1.getLabel());
        
            for (final JxbModule.Gui.Command commandV1 : V1Utils.getCommands(guiV1)) {
                final Contribution contribution = V1Utils.getContribution(commandV1);
                if ("property".equals(contribution.getLocation())) {
                    final org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage.Jxbv2CommandRef ref = this.factoryV2
                            .createModuleGuiViewsPropertyPageCommandRef();
                    ref.setRefid(commandV1.getName());
                    propertyPageV2.getCommandRef().add(ref);
                }
            }
        
            for (final ElementCreationCommand commandV1 : V1Utils.getCreationCommands(guiV1)) {
                final Contribution contribution = V1Utils.getContribution(commandV1);
                if ("property".equals(contribution.getLocation())) {
                    final org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage.Jxbv2CommandRef ref = this.factoryV2
                            .createModuleGuiViewsPropertyPageCommandRef();
                    ref.setRefid(commandV1.getName());
                    propertyPageV2.getCommandRef().add(ref);
                }
            }
        
            guiV2.getViews().getPropertyPage().add(propertyPageV2);
        }
        
    }

    @objid ("7e00bcde-1bde-49dc-b373-1635da1a11f7")
    private void migrateParameterEnumeration(JxbEnumeration enumV1, Jxbv2Enumeration enumV2) {
        for (final JxbEnumeration.Literal literalV1 : enumV1.getLiteral()) {
            final Jxbv2Literal literalV2 = this.factoryV2.createEnumerationLiteral();
            literalV2.setLabel(literalV1.getLabel());
            literalV2.setValue(literalV1.getName());
            enumV2.getLiteral().add(literalV2);
        }
        
    }

    @objid ("1ea4da3a-eb74-4eb6-99d4-10c84e27589b")
    private void migrateToolHandler(JxbHandler handlerV1, Jxbv2Handler handlerV2, String flavor) {
        // In V1 diagram tool never used a class to implement the tool
        // For migrated tool let use the generic handler
        handlerV2.setClazz(flavor);
        
        if (handlerV1.getMetaclass() != null && !handlerV1.getMetaclass().isEmpty()) {
            final Jxbv2HParameter metaclass = this.factoryV2.createHandlerHParameter();
            metaclass.setName("metaclass");
            metaclass.setValue(handlerV1.getMetaclass());
            handlerV2.getHParameter().add(metaclass);
        }
        if (handlerV1.getStereotype() != null && !handlerV1.getStereotype().isEmpty()) {
            final Jxbv2HParameter stereotype = this.factoryV2.createHandlerHParameter();
            stereotype.setName("stereotype");
            stereotype.setValue(handlerV1.getStereotype());
            handlerV2.getHParameter().add(stereotype);
        }
        if (handlerV1.getRelation() != null && !handlerV1.getRelation().isEmpty()) {
            final Jxbv2HParameter relation = this.factoryV2.createHandlerHParameter();
            relation.setName("relation");
            relation.setValue(handlerV1.getRelation());
            handlerV2.getHParameter().add(relation);
        }
        
    }

}
