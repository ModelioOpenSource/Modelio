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

package org.modelio.gproject.module.catalog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Enumeration.Jxbv2Literal;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2ExternDocumentType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Icon;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Image;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2NoteType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition.Jxbv2Parameter;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyDefinition;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyTableDefinition;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2StereotypeRef;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2TagType;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectCreator;
import org.modelio.gproject.gproject.GProjectFactory;
import org.modelio.gproject.ramc.core.model.ModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.gproject.ramc.core.packaging.RamcPackager;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.mda.ModuleParameterType;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.session.impl.GenericFactory;
import org.modelio.vcore.session.impl.SmFactory;
import org.modelio.vcore.session.impl.permission.BasicAccessManager;
import org.modelio.vcore.session.impl.storage.memory.MemoryRepository;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.version.ModelioVersion;

/**
 * Helper class building a module model component from a {@link Jxbv2Module}.
 */
@objid ("156a6e31-0c96-11e2-b501-002564c97630")
class RamcBuilder {
    @objid ("072a479f-9fa5-4e5a-bbc9-645da488cf86")
    private final Jxbv2Module loadedModule;

    @objid ("a9855352-3530-428d-bc78-5e6d190c0a0a")
    private final Map<String, PropertyType> propertyTypeCache = new HashMap<>();

    @objid ("276696b3-05c9-456f-be1f-a2402e89b4fe")
    private final Collection<IGMetamodelExtension> metamodelExtensions;

    /**
     * @param loadedModule the module JAXB model
     */
    @objid ("8e8bdc7f-7d4f-4d31-a8a4-57292f9848e7")
    RamcBuilder(Collection<IGMetamodelExtension> metamodelExtensions, Jxbv2Module loadedModule) {
        this.metamodelExtensions = metamodelExtensions;
        this.loadedModule = loadedModule;
    }

    /**
     * Helper method creating a model component in several steps:
     * <ol>
     * <li>create a temporary in memory {@link GProject}.</li>
     * <li>create a full {@link ModuleComponent} from a {@link Jxbv2Module}</li>
     * <li>package the model component containing this module</li>
     * <li>close the temporary project and delete it.</li>
     * </ol>
     * 
     * @param ramcPath the path to package the ramc into.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws java.io.IOException in case of failure
     */
    @objid ("2c95c269-f37d-11e1-a3c7-002564c97630")
    public void createRamc(Path ramcPath, IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, 60);
        
        Path tempDirectory = Files.createTempDirectory("ModelioCatalog");
        
        Files.createDirectories(ramcPath.getParent());
        
        ProjectDescriptor projectDescriptor = GProjectCreator.buildEmptyProject(this.loadedModule.getId(), tempDirectory, ModelioVersion.VERSION);
        GProject gproject = GProjectFactory.from(projectDescriptor)
                .withMetamodelExtensions(this.metamodelExtensions)
                .open(mon.newChild(10));
        
        try {
            IModelComponentContributor contributor = new DescriptionContributor((CoreSession) gproject.getSession());
        
            Artifact artifact = createRamcModel(gproject, mon.newChild(10));
            // Run packaging
            RamcPackager packager = new RamcPackager(gproject, artifact, ramcPath, Arrays.asList(contributor));
            // For module static-model ramcs do not export the artifact
            // itself because this artifact is only a temporary one used to
            // package the ramc. Furthermore, keeping it in the ramc would cause
            // trouble when removing the module.
            packager.setIncludeArtifact(false);
            packager.run(mon.newChild(30));
        } finally {
            gproject.close();
        }
        
        // TODO this is a quite naive implementation, it should deal with
        // project path for delegating project
        try {
            FileUtils.delete(tempDirectory);
        } catch (IOException e) {
            Log.warning(e);
        }
    }

    @objid ("a2ff7b65-45ed-4732-ad44-ce82cb54afe2")
    private MetaclassReference createMetaclassReference(ModelFactory modelfactory, Jxbv2MetaclassReference loadedRef, Profile profile) {
        MetaclassReference mcRef = modelfactory.createMetaclassReference(loadedRef, profile);
        if (loadedRef.getTagTypes() != null) {
            for (Jxbv2TagType loadedTagType : loadedRef.getTagTypes().getTagType()) {
                modelfactory.createTagType(loadedTagType, mcRef);
            }
        }
        if (loadedRef.getNoteTypes() != null) {
            for (Jxbv2NoteType loadedNoteType : loadedRef.getNoteTypes().getNoteType()) {
                modelfactory.createNoteType(loadedNoteType, mcRef);
            }
        }
        if (loadedRef.getExternDocumentTypes() != null) {
            for (Jxbv2ExternDocumentType loadedDocumentType : loadedRef.getExternDocumentTypes().getExternDocumentType()) {
                modelfactory.createResourceType(loadedDocumentType, mcRef);
            }
        }
        Jxbv2PropertyTableDefinition loadedTableType = loadedRef.getPropertyTable();
        if (loadedTableType != null) {
            createTableType(modelfactory, loadedTableType, mcRef);
        }
        return mcRef;
    }

    @objid ("fa6406a5-03d1-4875-ae65-d18110d36136")
    private Profile createProfile(ModelFactory modelfactory, Jxbv2Profile loadedProfile, ModuleComponent module) {
        Profile profile = modelfactory.createProfile(loadedProfile, module);
        
        for (Jxbv2Stereotype loadedStereotype : loadedProfile.getStereotype()) {
            createStereotype(modelfactory, loadedStereotype, profile);
        }
        
        for (Jxbv2MetaclassReference loadedRef : loadedProfile.getMetaclassReference()) {
            createMetaclassReference(modelfactory, loadedRef, profile);
        }
        return profile;
    }

    @objid ("6b4a6f4b-99aa-4e43-b1c7-e96f31534596")
    private Artifact createRamcModel(GProject gproject, IModelioProgress monitor) throws IOException {
        ICoreSession session = gproject.getSession();
        
        // Create a temporary memory repository
        IRepository repo = new MemoryRepository();
        session.getRepositorySupport().connectRepository(repo, new BasicAccessManager(), monitor);
        
        // Configure a dedicated model factory in the repository
        ModelFactory modelfactory = new ModelFactory(session, repo);
        
        // Create the RAMC model
        try (ITransaction t = session.getTransactionSupport().createTransaction("Create " + this.loadedModule.getId() + " module RAMC")) {
            // Create a project to model the ramc
            GenericFactory gf = session.getModel().getGenericFactory();
            Project ramcProject = gf.create(Project.class, repo);
            Package ramcProjectRoot = gf.create(Package.class, repo);
            ramcProject.getModel().add(ramcProjectRoot);
        
            // Create the module itself
            ModuleComponent module = modelfactory.createModule(this.loadedModule);
        
            // First create the propertyTypes
            if (this.loadedModule.getPropertyTypes() != null) {
                for (Jxbv2PropertyType loadedPropertyType : this.loadedModule.getPropertyTypes().getPropertyType()) {
                    modelfactory.createPropertyType(loadedPropertyType, module);
                }
            }
        
            // For performance reasons set up a cache of all known property types
            for (PropertyType pType : session.getModel().findByClass(PropertyType.class)) {
                this.propertyTypeCache.put(pType.getName(), pType);
            }
        
            // Import Profiles, Stereotypes, TagTypes and so on.
            if (this.loadedModule.getProfiles() != null) {
                for (Jxbv2Profile loadedProfile : this.loadedModule.getProfiles().getProfile()) {
                    createProfile(modelfactory, loadedProfile, module);
                }
            }
        
            // Import parameters
            if (this.loadedModule.getParameters() != null) {
                for (Jxbv2Module.Jxbv2Parameters.Jxbv2Parameter loadedParam : this.loadedModule
                        .getParameters().getParameter()) {
                    modelfactory.createConfigParam(loadedParam, module);
                }
            }
        
            // Post processing
            // Establish inheritance links between stereotypes
            if (this.loadedModule.getProfiles() != null) {
                for (Jxbv2Profile profileDesc : this.loadedModule.getProfiles().getProfile()) {
                    for (Jxbv2Stereotype stereotypeDesc : profileDesc.getStereotype()) {
                        String parentStereotypeId = stereotypeDesc.getOwnerStereotype();
                        if (parentStereotypeId != null && !parentStereotypeId.isEmpty()) {
                            Stereotype current = session.getModel().findById(Stereotype.class, stereotypeDesc.getUid());
        
                            // Set the new parent
                            current.setParent(modelfactory.getStereotype(session, parentStereotypeId));
                        }
                    }
                }
            }
        
            // Define RAMC packaging.
            // Generate the RAMC Artifact
            Artifact ramcArtifact = gf.create(Artifact.class, ramcProjectRoot);
            ramcProjectRoot.getOwnedElement().add(ramcArtifact);
            ramcArtifact.setName(this.loadedModule.getId());
        
            ModelComponent ramcFact = new ModelComponent(ramcArtifact);
            ramcFact.setRamcName(this.loadedModule.getId());
            ramcFact.setRamcVersion(new Version(this.loadedModule.getVersion()));
            ramcFact.setProvider("Module " + this.loadedModule.getId());
            ramcFact.getExportedElements().add(module);
            ramcFact.updateArtifact();
        
            t.commit();
        
            return ramcArtifact;
        
        }
    }

    @objid ("560948bf-7c0d-490c-a1d3-2fc8ec0456d8")
    private Stereotype createStereotype(ModelFactory modelfactory, Jxbv2Stereotype loadedStereotype, Profile profile) {
        Stereotype stereotype = modelfactory.createStereotype(loadedStereotype, profile);
        
        if (loadedStereotype.getTagTypes() != null) {
            for (Jxbv2TagType loadedTagType : loadedStereotype.getTagTypes().getTagType()) {
                modelfactory.createTagType(loadedTagType, stereotype);
            }
        }
        if (loadedStereotype.getNoteTypes() != null) {
            for (Jxbv2NoteType loadedNoteType : loadedStereotype.getNoteTypes().getNoteType()) {
                modelfactory.createNoteType(loadedNoteType, stereotype);
            }
        }
        if (loadedStereotype.getExternDocumentTypes() != null) {
            for (Jxbv2ExternDocumentType loadedDocumentType : loadedStereotype.getExternDocumentTypes().getExternDocumentType()) {
                modelfactory.createResourceType(loadedDocumentType, stereotype);
            }
        }
        Jxbv2PropertyTableDefinition loadedTableType = loadedStereotype.getPropertyTable();
        if (loadedTableType != null) {
            createTableType(modelfactory, loadedTableType, stereotype);
        }
        return stereotype;
    }

    @objid ("6e1a628d-abf4-4bcf-af5d-4971fcd09032")
    private void createTableType(ModelFactory modelfactory, Jxbv2PropertyTableDefinition loadedTableType, Element owner) {
        PropertyTableDefinition tableType = modelfactory.createTableType(loadedTableType, owner);
        
        for (Jxbv2PropertyDefinition loadedPropertyDefinition : loadedTableType.getPropertyDefinition()) {
            PropertyType pType;
        
            String refId = loadedPropertyDefinition.getTypeRef().getId();
            if (refId.startsWith("mref#")) {
                MRef ref = new MRef(refId.substring(5));
                pType = (PropertyType) modelfactory.getObjectReference(modelfactory.metamodel.getMClass(ref.mc).getJavaInterface(), ref.uuid, ref.name);
            } else {
                pType = modelfactory.getObjectReference(PropertyType.class, (refId), "");
            }
        
            modelfactory.createPropertyDefinition(loadedPropertyDefinition, tableType, pType);
        }
    }

    /**
     * A basic MDA elements factory from JAXB elements.
     */
    @objid ("2c90b933-f37d-11e1-a3c7-002564c97630")
    static class ModelFactory {
        @objid ("2c912e64-f37d-11e1-a3c7-002564c97630")
        private final SmFactory model;

        @objid ("2c912e65-f37d-11e1-a3c7-002564c97630")
        private final IRepository repo;

        @objid ("5f569288-2d52-47b7-a830-61784d7fa179")
        private final SmMetamodel metamodel;

        /**
         * Constructor.
         * 
         * @param session the core modeling session.
         * @param repo the repository where elements will be created.
         */
        @objid ("2c915573-f37d-11e1-a3c7-002564c97630")
        public ModelFactory(final ICoreSession session, IRepository repo) {
            this.model = ((CoreSession) session).getSmFactory();
            this.repo = repo;
            this.metamodel = session.getMetamodel();
        }

        @objid ("2c917c83-f37d-11e1-a3c7-002564c97630")
        public ModuleParameter createConfigParam(final org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters.Jxbv2Parameter loadedParameter, final ModuleComponent owner) {
            ModuleParameter moduleParam = (ModuleParameter) this.model.createObject(getClass(ModuleParameter.class), this.repo,
                    (loadedParameter.getUid()));
            
            // Set parameter type
            final String type = loadedParameter.getType();
            switch (type != null ? type : "String") {
            case "Boolean":
                moduleParam.setType(ModuleParameterType.TYPE_PARAM_BOOLEAN);
                break;
            case "Color":
                moduleParam.setType(ModuleParameterType.TYPE_PARAM_COLOR);
                break;
            case "Directory":
                moduleParam.setType(ModuleParameterType.TYPE_PARAM_DIRECTORY);
                break;
            case "Enum":
                moduleParam.setType(ModuleParameterType.TYPE_PARAM_ENUM);
                break;
            case "File":
                moduleParam.setType(ModuleParameterType.TYPE_PARAM_FILE);
                break;
            case "Integer":
                moduleParam.setType(ModuleParameterType.TYPE_PARAM_INTEGER);
                break;
            case "Password":
                moduleParam.setType(ModuleParameterType.TYPE_PARAM_PASSWORD);
                break;
            case "String":
            default:
                moduleParam.setType(ModuleParameterType.TYPE_PARAM_STRING);
                break;
            }
            
            // Create enumeration typing the parameter
            if (loadedParameter.getEnumeration() != null) {
                EnumeratedPropertyType e = (EnumeratedPropertyType) this.model.createObject(getClass(EnumeratedPropertyType.class), this.repo);
            
                // Create literals
                for (Jxbv2Literal literal : loadedParameter.getEnumeration().getLiteral()) {
                    PropertyEnumerationLitteral l = (PropertyEnumerationLitteral) this.model.createObject(getClass(PropertyEnumerationLitteral.class), this.repo);
            
                    e.getLitteral().add(l);
                    l.setName(literal.getValue());
                }
            
                e.setName(loadedParameter.getId());
                e.setModuleOwner(owner);
                moduleParam.setEnumType(e);
            }
            
            moduleParam.setDefaultValue(loadedParameter.getDefaultValue() != null ? loadedParameter.getDefaultValue() : "");
            moduleParam.setName(loadedParameter.getId());
            moduleParam.setOwner(owner);
            return moduleParam;
        }

        @objid ("2c943ba7-f37d-11e1-a3c7-002564c97630")
        public ResourceType createResourceType(final Jxbv2ExternDocumentType loadedResourceType, final Element owner) {
            ResourceType element = (ResourceType) this.model.createObject(getClass(ResourceType.class),
                    this.repo, (loadedResourceType.getUid()));
            
            element.setName(loadedResourceType.getName());
            
            if (loadedResourceType.getIsHidden() != null) {
                element.setIsHidden(loadedResourceType.getIsHidden().equals("true"));
            }
            element.setLabelKey(loadedResourceType.getLabel() != null ? loadedResourceType.getLabel() : loadedResourceType.getName());
            
            if (owner instanceof Stereotype) {
                element.setOwnerStereotype((Stereotype) owner);
            } else if (owner instanceof MetaclassReference) {
                element.setOwnerReference((MetaclassReference) owner);
            }
            
            // Handle extensions
            if (loadedResourceType.getExtensions() != null) {
                applyStereotypeRefs(element, loadedResourceType.getExtensions().getStereotypeRef());
            }
            return element;
        }

        @objid ("5694c9d2-8618-4335-84b3-f5c046b26b23")
        public PropertyTableDefinition createTableType(Jxbv2PropertyTableDefinition loadedTableType, Element owner) {
            PropertyTableDefinition element = (PropertyTableDefinition) this.model.createObject(getClass(PropertyTableDefinition.class), this.repo, (loadedTableType.getUid()));
            
            if (owner instanceof Stereotype) {
                element.setOwnerStereotype((Stereotype) owner);
                if (element.getOwnerReference() != null) {
                    element.setOwnerReference(null);
                }
            } else if (owner instanceof MetaclassReference) {
                element.setOwnerReference((MetaclassReference) owner);
                if (element.getOwnerStereotype() != null) {
                    element.setOwnerStereotype(null);
                }
            }
            
            element.setName(loadedTableType.getId());
            
            // Handle extensions
            if (loadedTableType.getExtensions() != null) {
                applyStereotypeRefs(element, loadedTableType.getExtensions().getStereotypeRef());
            }
            return element;
        }

        @objid ("2c91a396-f37d-11e1-a3c7-002564c97630")
        public MetaclassReference createMetaclassReference(final org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference loadedMetaclassReference, final Profile owner) {
            MetaclassReference element = (MetaclassReference) this.model.createObject(getClass(MetaclassReference.class),
                    this.repo, (loadedMetaclassReference.getUid()));
            
            element.setReferencedClassName(loadedMetaclassReference.getMetaclass());
            element.setOwnerProfile(owner);
            return element;
        }

        /**
         * Create a module.
         * 
         * @param loadedModule entry in .xml file
         * @return the created module.
         */
        @objid ("2c91f1b4-f37d-11e1-a3c7-002564c97630")
        public ModuleComponent createModule(final Jxbv2Module loadedModule) {
            ModuleComponent element = (ModuleComponent) this.model.createObject(getClass(ModuleComponent.class), this.repo,
                    (loadedModule.getUid()));
            
            Version version = new Version(loadedModule.getVersion());
            element.setMajVersion(version.getMajorVersion());
            element.setMinVersion(version.getMinorVersion());
            element.setMinMinVersion(Integer.toString(version.getBuildVersion()));
            
            element.setName(loadedModule.getId());
            element.setJavaClassName(loadedModule.getClazz());
            return element;
        }

        @objid ("2c9218c4-f37d-11e1-a3c7-002564c97630")
        public NoteType createNoteType(final Jxbv2NoteType loadedNoteType, final Element owner) {
            NoteType element = (NoteType) this.model.createObject(getClass(NoteType.class), this.repo,
                    loadedNoteType.getUid());
            
            element.setName(loadedNoteType.getName());
            
            if (loadedNoteType.getIsHidden() != null) {
                element.setIsHidden(loadedNoteType.getIsHidden().equals("true"));
            }
            
            if (loadedNoteType.getMimeType() != null) {
                element.setMimeType(loadedNoteType.getMimeType());
            } else {
                element.setMimeType("text/plain");
            }
            
            element.setLabelKey(loadedNoteType.getLabel() != null ? loadedNoteType.getLabel() : loadedNoteType.getName());
            
            if (owner instanceof Stereotype) {
                element.setOwnerStereotype((Stereotype) owner);
                if (element.getOwnerReference() != null) {
                    element.setOwnerReference(null);
                }
            } else if (owner instanceof MetaclassReference) {
                element.setOwnerReference((MetaclassReference) owner);
                if (element.getOwnerStereotype() != null) {
                    element.setOwnerStereotype(null);
                }
            }
            
            // Handle extensions
            if (loadedNoteType.getExtensions() != null) {
                applyStereotypeRefs(element, loadedNoteType.getExtensions().getStereotypeRef());
            }
            return element;
        }

        @objid ("2c923fd6-f37d-11e1-a3c7-002564c97630")
        public Profile createProfile(final Jxbv2Profile loadedProfile, final ModuleComponent owner) {
            Profile element = (Profile) this.model.createObject(getClass(Profile.class), this.repo,
                    (loadedProfile.getUid()));
            
            element.setName(loadedProfile.getId());
            element.setOwnerModule(owner);
            
            // Handle extensions
            if (loadedProfile.getExtensions() != null) {
                applyStereotypeRefs(element, loadedProfile.getExtensions().getStereotypeRef());
            }
            return element;
        }

        /**
         * @param loadedStereotype XML node
         * @param owner the project
         * @return the created stereotype.
         */
        @objid ("2c9266e8-f37d-11e1-a3c7-002564c97630")
        public Stereotype createStereotype(final org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype loadedStereotype, final Profile owner) {
            Stereotype element = (Stereotype) this.model.createObject(getClass(Stereotype.class), this.repo, (loadedStereotype.getUid()));
            element.setIsAbstract(loadedStereotype.isIsAbstract());
            
            element.setName(loadedStereotype.getName());
            element.setOwner(owner);
            
            Jxbv2Icon icon = loadedStereotype.getIcon();
            if (icon != null && icon.getPath() != null) {
                element.setIcon(icon.getPath());
            }
            
            Jxbv2Image image = loadedStereotype.getImage();
            if (image != null && image.getPath() != null) {
                element.setImage(image.getPath());
            }
            
            if (loadedStereotype.getIsHidden() != null) {
                element.setIsHidden(loadedStereotype.getIsHidden().equals("true"));
            }
            
            if (loadedStereotype.getMetaclass() != null) {
                element.setBaseClassName(loadedStereotype.getMetaclass());
            }
            
            element.setLabelKey(loadedStereotype.getLabel() != null ? loadedStereotype.getLabel() : loadedStereotype.getName());
            
            // Handle extensions
            if (loadedStereotype.getExtensions() != null) {
                applyStereotypeRefs(element, loadedStereotype.getExtensions().getStereotypeRef());
            }
            return element;
        }

        @objid ("2c92b506-f37d-11e1-a3c7-002564c97630")
        public TagType createTagType(final Jxbv2TagType loadedTagType, final Element owner) {
            TagType element = (TagType) this.model.createObject(getClass(TagType.class), this.repo, (loadedTagType.getUid()));
            
            element.setName(loadedTagType.getName());
            
            if (loadedTagType.getIsHidden() != null) {
                element.setIsHidden(loadedTagType.getIsHidden().equals("true"));
            }
            
            element.setLabelKey(loadedTagType.getLabel() != null ? loadedTagType.getLabel() : loadedTagType.getName());
            
            if (loadedTagType.getParameterCard() != null) {
                element.setParamNumber(loadedTagType.getParameterCard());
            }
            
            if (loadedTagType.getIsSigned() != null) {
                element.setBelongToPrototype(loadedTagType.getIsSigned().equals("true"));
            }
            
            if (owner instanceof Stereotype) {
                element.setOwnerStereotype((Stereotype) owner);
                if (element.getOwnerReference() != null) {
                    element.setOwnerReference(null);
                }
            } else if (owner instanceof MetaclassReference) {
                element.setOwnerReference((MetaclassReference) owner);
                if (element.getOwnerStereotype() != null) {
                    element.setOwnerStereotype(null);
                }
            }
            
            // Handle extensions
            if (loadedTagType.getExtensions() != null) {
                applyStereotypeRefs(element, loadedTagType.getExtensions().getStereotypeRef());
            }
            return element;
        }

        @objid ("39a11622-b9ff-4ed1-8516-518791bb4af8")
        public PropertyDefinition createPropertyDefinition(Jxbv2PropertyDefinition loadedPropertyDefinition, PropertyTableDefinition owner, PropertyType pType) {
            PropertyDefinition element = (PropertyDefinition) this.model.createObject(getClass(PropertyDefinition.class),
                    this.repo, (loadedPropertyDefinition.getUid()));
            
            element.setName(loadedPropertyDefinition.getId());
            element.setDefaultValue(loadedPropertyDefinition.getDefaultValue());
            element.setIsEditable(loadedPropertyDefinition.isIsEditable());
            element.setType(pType);
            element.setOwner(owner);
            
            if (loadedPropertyDefinition.getDescription() != null && !loadedPropertyDefinition.getDescription().isEmpty()) {
                createDescriptionNote(element, loadedPropertyDefinition.getDescription());
            }
            
            for (Jxbv2Parameter parameter : loadedPropertyDefinition.getParameter()) {
                element.setProperty("Constraints", parameter.getName(), parameter.getValue());
            }
            return element;
        }

        @objid ("e1af37d8-91f9-4238-9c40-e3787262c002")
        public PropertyType createPropertyType(Jxbv2PropertyType loadedPropertyType, ModuleComponent owner) {
            PropertyBaseType baseType;
            try {
                baseType = PropertyBaseType.valueOf(loadedPropertyType.getType().toUpperCase());
            } catch (@SuppressWarnings ("unused") IllegalArgumentException | NullPointerException e) {
                baseType = PropertyBaseType.TEXT;
            }
            
            PropertyType element;
            if (baseType == PropertyBaseType.ENUMERATE) {
                element = createEnumeratedPropertyType(loadedPropertyType, owner);
            } else {
                element = createPropertyType(owner, loadedPropertyType, baseType);
            }
            
            if (loadedPropertyType.getDescription() != null && !loadedPropertyType.getDescription().isEmpty()) {
                createDescriptionNote(element, loadedPropertyType.getDescription());
            }
            
            // Handle extensions
            if (loadedPropertyType.getExtensions() != null) {
                applyStereotypeRefs(element, loadedPropertyType.getExtensions().getStereotypeRef());
            }
            return element;
        }

        @objid ("33784cef-7097-43e8-a463-f8ee5cf4bb2c")
        public SmClass getClass(Class<? extends MObject> cls) {
            return this.metamodel.getMClass(cls);
        }

        @objid ("33d557cf-72d9-4023-b39e-4ce988fd018f")
        public <T extends MObject> T getObjectReference(Class<T> cls, final String uuid, final String name) {
            return cls.cast(this.model.getObjectReference(this.metamodel.getMClass(cls), uuid, name));
        }

        @objid ("8f7a96da-a55d-4ad5-8da4-bf6cca6762af")
        private PropertyType createEnumeratedPropertyType(Jxbv2PropertyType loadedPropertyType, ModuleComponent owner) {
            EnumeratedPropertyType element = (EnumeratedPropertyType) this.model.createObject(getClass(EnumeratedPropertyType.class), this.repo,
                    (loadedPropertyType.getUid()));
            
            element.setName(loadedPropertyType.getId());
            element.setModuleOwner(owner);
            element.setBaseType(PropertyBaseType.ENUMERATE);
            
            for (Jxbv2Literal loadedLiteral : loadedPropertyType.getEnumeration().getLiteral()) {
                createPropertyEnumerationLitteral(loadedLiteral, element);
            }
            
            // Handle extensions
            if (loadedPropertyType.getExtensions() != null) {
                applyStereotypeRefs(element, loadedPropertyType.getExtensions().getStereotypeRef());
            }
            return element;
        }

        @objid ("a9b3c9ff-8250-4153-9081-b4846e2b2ef7")
        private PropertyEnumerationLitteral createPropertyEnumerationLitteral(Jxbv2Literal loadedLiteral, EnumeratedPropertyType element) {
            PropertyEnumerationLitteral litteral = (PropertyEnumerationLitteral) this.model.createObject(getClass(PropertyEnumerationLitteral.class), this.repo);
            litteral.setName(loadedLiteral.getValue());
            litteral.setOwner(element);
            
            // Handle extensions
            if (loadedLiteral.getExtensions() != null) {
                applyStereotypeRefs(element, loadedLiteral.getExtensions().getStereotypeRef());
            }
            return litteral;
        }

        @objid ("d3f98048-91a6-477e-8b79-0984cd851a50")
        private PropertyType createPropertyType(ModuleComponent owner, Jxbv2PropertyType loadedPropertyType, PropertyBaseType baseType) {
            PropertyType element = (PropertyType) this.model.createObject(getClass(PropertyType.class), this.repo,
                    (loadedPropertyType.getUid()));
            
            element.setName(loadedPropertyType.getId());
            element.setModuleOwner(owner);
            element.setBaseType(baseType);
            return element;
        }

        @objid ("a92ab15c-d70c-4305-9958-ca87a9ad57f0")
        private void createDescriptionNote(ModelElement modelElement, String noteContent) {
            // Create description note
            Note descriptionNote = (Note) this.model.createObject(getClass(Note.class), this.repo);
            descriptionNote.setContent(noteContent);
            descriptionNote.setSubject(modelElement);
            descriptionNote.setMimeType("text/plain");
            // ModelerModule might not be deployed, reference description NoteType from its UUID
            descriptionNote.setModel(getObjectReference(NoteType.class, "00000000-0000-3e81-0000-000000000000", "description"));
        }

        @objid ("f8e1ceb9-6cfa-4ef7-9670-c691a0cf1d0e")
        private void applyStereotypeRefs(ModelElement element, List<Jxbv2StereotypeRef> refs) {
            ICoreSession session = CoreSession.getSession(element);
            
            for (Jxbv2StereotypeRef stereotypeRef : refs) {
                String uuid = stereotypeRef.getUid();
                if (uuid != null && !uuid.isEmpty()) {
                    // Module might not be deployed, reference description Stereotype from its UUID
                    element.getExtension().add(getStereotype(session, uuid));
                }
            }
        }

        @objid ("40fe22a3-0225-4281-bdb8-e51d7f0229aa")
        public Stereotype getStereotype(ICoreSession session, String uuid) {
            Stereotype stereotype = null;
            if (uuid.startsWith("mref#")) {
                // Find by MRef
                MRef ref = new MRef(uuid.substring(5));
                stereotype = getObjectReference(Stereotype.class, ref.uuid, ref.name);
            } else {
                // Find stereotype by name
                Collection<Stereotype> owners = session.getModel().findByAtt(Stereotype.class, "Name", uuid);
                if (!owners.isEmpty()) {
                    stereotype = owners.iterator().next();
                } else {
                    // Last chance, try a find stereotype by id
                    stereotype = getObjectReference(Stereotype.class, uuid, "");
                }
            }
            return stereotype;
        }

    }

    /**
     * Simple model component contributor adding the 'description' note type.
     */
    @objid ("890efceb-4d42-4969-8f70-508d8ca7c6d2")
    private static class DescriptionContributor implements IModelComponentContributor {
        @objid ("13c1ca3f-e750-4cf2-a59b-57872a6a7173")
        private CoreSession session;

        @objid ("af520571-5d01-4c8b-a0e7-289ac09e2c62")
        public DescriptionContributor(CoreSession session) {
            this.session = session;
        }

        @objid ("9141911d-fd12-42e9-be52-90094efbb6c5")
        @Override
        public Set<TagType> getTagTypes() {
            return Collections.emptySet();
        }

        @objid ("b65c170f-675f-41b1-875b-45793cff4980")
        @Override
        public Set<NoteType> getNoteTypes() {
            // Assume ModelerModule is missing, since a module ramc is built in an empty project.
            Set<NoteType> notes = new HashSet<>();
            try (ITransaction t = this.session.getTransactionSupport().createTransaction("Create description note type")) {
                notes.add((NoteType) this.session.getSmFactory().getObjectReference(this.session.getMetamodel().getMClass(NoteType.class), "00000000-0000-3e81-0000-000000000000", "description"));
                t.commit();
            }
            return notes;
        }

        @objid ("eb27753b-edf6-4863-9708-5825fd90609e")
        @Override
        public Set<ExportedFileEntry> getFiles() {
            return Collections.emptySet();
        }

        @objid ("5ed9e6bb-f852-4693-aaff-a19378fbe2c2")
        @Override
        public Set<MObject> getElements() {
            return Collections.emptySet();
        }

        @objid ("b3714730-1112-4fcd-98e9-83724a46ced2")
        @Override
        public Set<Stereotype> getDependencyStereotypes() {
            // Assume ModelerModule is missing, since a module ramc is built in an empty project.
            Set<Stereotype> stereotypes = new HashSet<>();
            try (ITransaction t = this.session.getTransactionSupport().createTransaction("Create manifestation stereotype")) {
                stereotypes.add((Stereotype) this.session.getSmFactory().getObjectReference(this.session.getMetamodel().getMClass(Stereotype.class), ("d5bccf8e-79b3-48df-8c79-09200aa52d19"), "manifestation"));
                t.commit();
            }
            return stereotypes;
        }

    }

}
