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
package org.modelio.gproject.ramc.core.model;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor.ExportedFileEntry;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.impl.CoreSession;

/**
 * This class is an in-memory convenient representation of a Model Component (RAMC) as described by an annotated {@link Artifact}.
 * <p>
 * The constructor is used to convert from an {@link Artifact} into an instance of this <code>ModelComponent</code> class.
 * <p>
 * A <code>ModelComponent</code> can be modified in order to change its configuration. However, only the {@link #updateArtifact()} method will actually modify the artifact itself, thereby providing a convenient means of updating the model artifact
 * configuration to the values of <i>this</i> <code>ModelComponent</code>.
 */
@objid ("41acd6e0-e9f3-440a-88f2-f10a20ae830c")
public class ModelComponent implements Comparable<ModelComponent>, IModelComponent {
    @objid ("a7fb39d2-f33a-4ddc-9790-dabba964fd41")
    private static final String MODELER_MODULE = "ModelerModule";

    @objid ("150ee641-cd06-4bdb-9561-a5adc9d9efd3")
    private final TreeMap<String, String> contributors;

    @objid ("1688e461-81f6-46bd-8997-6410a7e49d40")
    private String description;

    @objid ("5d12ad53-cbb7-4061-bdfd-440b79fb4547")
    private String name;

    @objid ("6328849d-c4de-40c4-9942-c80fe886e7d1")
    private String provider;

    @objid ("6a43a5fc-ed9c-4658-9884-6f41f5499eab")
    private final Artifact artifact;

    @objid ("34e4502f-e320-4dc3-875f-fc52312ecbf2")
    private final Set<Element> exportedElements;

    @objid ("4b6735c0-6fbd-4614-90b8-0fda3b58a36c")
    private final Set<ExportedFileEntry> exportedFiles;

    @objid ("d3e4b776-e2d4-4138-afc4-c0ebd0bc3822")
    private final Set<ModelComponent> requiredModelComponents;

    @objid ("b93b47af-b05f-4c39-9585-759d111cfc73")
    private Version version;

    /**
     * Initialize a <code>ModelComponent</code> from an {@link Artifact} .
     * @param ramc the model component artifact.
     */
    @objid ("c738cb04-0d54-4fa4-a865-fe0e5be87c05")
    public  ModelComponent(final Artifact ramc) {
        this.artifact = ramc;
        this.name = Loader.getName(ramc);
        this.version = Loader.getVersion(ramc);
        this.provider = Loader.getProvider(ramc);
        this.description = Loader.getDescription(ramc);
        
        this.exportedElements = Loader.getManifested(ramc);
        this.requiredModelComponents = Loader.getDependencies(ramc);
        this.exportedFiles = Loader.getExportedFiles(ramc);
        this.contributors = Loader.getContributors(ramc);
        
    }

    @objid ("40e79687-d525-400a-9127-c7abacea4c8d")
    public void addExportedFile(final ExportedFileEntry file) {
        this.exportedFiles.add(file);
    }

    @objid ("894f1b27-de2a-4cb0-b55b-af841e56b423")
    @Override
    public int compareTo(final ModelComponent model) {
        int result = this.name.compareTo(model.name);
        if (result == 0) {
            result = this.version.toString().compareTo(model.version.toString());
        }
        return result;
    }

    @objid ("350bca5b-c8f0-45b0-8d66-65043cbbd9f6")
    @Override
    public Artifact getArtifact() {
        return this.artifact;
    }

    @objid ("4a6d8180-eb0e-4f43-a76d-e799eff3a6a9")
    @Override
    public Map<String, String> getContributingModules() {
        return this.contributors;
    }

    @objid ("428a1f71-2044-4c71-92d7-60291b16b134")
    @Override
    public String getDescription() {
        return this.description;
    }

    @objid ("db43abe4-6162-4703-b11f-71c7deeac57e")
    @Override
    public Set<Element> getExportedElements() {
        return this.exportedElements;
    }

    @objid ("1a2220cc-4444-4716-a115-6e367315f485")
    @Override
    public Set<ExportedFileEntry> getExportedFiles() {
        return this.exportedFiles;
    }

    @objid ("8178848c-ae9e-419d-85af-98266a43e16f")
    @Override
    public String getName() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.name;
    }

    @objid ("49e33ea8-959b-4332-9ecf-39ff63ea39a9")
    @Override
    public Set<ModelComponent> getRequiredModelComponents() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.requiredModelComponents;
    }

    @objid ("97e095cb-ba49-48c4-af96-a20fdfc8bb05")
    @Override
    public Version getVersion() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.version;
    }

    @objid ("2dfeb02c-cfa7-4497-9283-29c70b2e0a42")
    public void removeExportedFile(final ExportedFileEntry file) {
        this.exportedFiles.remove(file);
    }

    @objid ("815d24aa-b3a4-455a-adc9-67c617468c55")
    public void setRamcDescription(final String value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.description = value;
        
    }

    @objid ("eb824121-49b2-41fa-9719-a7ec5a81b6ff")
    public void setRamcName(final String name) {
        this.name = name;
    }

    @objid ("a8fb3f14-418e-472e-8ab4-39b0e21cdcbf")
    public void setRamcVersion(final Version value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.version = value;
        
    }

    @objid ("5f3438ee-6674-43fb-a147-5985a328ece8")
    public void updateArtifact() {
        Writer.setName(this.artifact, this.name);
        Writer.setDescription(this.artifact, this.description);
        Writer.setVersion(this.artifact, this.version);
        Writer.setProvider(this.artifact, this.provider);
        Writer.setDependencies(this.artifact, this.requiredModelComponents);
        Writer.setManifestedElements(this.artifact, this.exportedElements);
        Writer.setExportedFiles(this.artifact, this.exportedFiles);
        Writer.setContributors(this.artifact, this.contributors);
        
    }

    @objid ("4609ff8a-f11c-4525-a20e-cfc6415e85b8")
    private static boolean isValidManifestedElement(final Element me) {
        return me != null && me.getMClass().isCmsNode();
    }

    @objid ("d5886347-a300-4eb5-94c3-bb43d4578b1c")
    @Override
    public String getProvider() {
        return this.provider;
    }

    @objid ("ef7ef636-7171-4379-adc4-b6253b579ca7")
    public void setProvider(final String provider) {
        this.provider = provider != null ? provider : "";
    }

    @objid ("f09dfe1e-af39-438f-b00c-cfd0147a43d4")
    private static class Loader {
        @objid ("e700a023-4994-4a56-ade0-2c718084486b")
        public static TreeMap<String, String> getContributors(final Artifact ramc) {
            final TreeMap<String, String> contributors = new TreeMap<>();
            for (final TaggedValue taggedvalue : ramc.getTag()) {
                final TagType type = taggedvalue.getDefinition();
                if (type != null && type.getName().equals("ModelComponentContributors")) {
                    for (final TagParameter parameter : taggedvalue.getActual()) {
                        final String[] tokens = parameter.getValue().split("#");
                        if (tokens.length >= 2) {
                            contributors.put(tokens[0], tokens[1]);
                        }
                    }
                }
            }
            return contributors;
        }

        @objid ("4fe182f0-adce-4503-915d-2a6bfd28048d")
        public static TreeSet<ModelComponent> getDependencies(final Artifact ramc) {
            final TreeSet<ModelComponent> deps = new TreeSet<>();
            for (final ElementImport elementImport : ramc.getOwnedImport()) {
                final NameSpace importedNamespace = elementImport.getImportedElement();
                if (importedNamespace instanceof Artifact // &&
                        // importedNamespace.isStereotyped("ModelComponentArchive")
                        ) {
                    final Artifact ramcArtifact = (Artifact) importedNamespace;
                    deps.add(new ModelComponent(ramcArtifact));
                }
            }
            return deps;
        }

        @objid ("7c8841da-59d5-41d1-a6cf-fb693370a8ef")
        public static String getDescription(final Artifact ramc) {
            final List<Note> notes = ramc.getDescriptor();
            for (final Note note : notes) {
                final NoteType type = note.getModel();
                if (type != null && type.getName().equals("description")) {
                    return note.getContent();
                }
            }
            return "";
        }

        @objid ("6a38272c-c7c4-4924-8255-5ae6450f71b8")
        public static TreeSet<ExportedFileEntry> getExportedFiles(final Artifact ramc) {
            final TreeSet<ExportedFileEntry> exportedFiles = new TreeSet<>();
            
            // RAMCs migrated from Modelio 2 might have several tags, read them all
            // In Modelio 3 a unique tag is used with several parameters (one per file)
            final String TAG_TYPE = "ModelComponentFiles";
            for (final TaggedValue taggedvalue : ramc.getTag()) {
                final TagType type = taggedvalue.getDefinition();
                if (type != null && type.getName().equals(TAG_TYPE)) {
                    int nParameters = taggedvalue.getActual().size();
                    final TagParameter[] parameters = taggedvalue.getActual().toArray(new TagParameter[nParameters]);
            
                    if (nParameters > 0 && (nParameters & 1) == 1) {
                        // one param missing
                        final String message = CoreProject.I18N.getMessage("ModelComponent.BadModelComponentFiles",
                                ramc.getName(),
                                TAG_TYPE,
                                Arrays.deepToString(parameters));
                        Log.warning(message);
                        nParameters--; // recover from error
                    }
            
                    for (int i = 0; i < nParameters; i += 2) {
                        exportedFiles.add(new ExportedFileEntry(
                                Paths.get(parameters[i].getValue().replace("\\", File.separator)),
                                parameters[i + 1].getValue().replace("\\", File.separator)));
                    }
                }
            }
            return exportedFiles;
        }

        @objid ("f7f5c288-aa70-44de-aaef-b5723b64738e")
        public static TreeSet<Element> getManifested(final Artifact ramc) {
            final TreeSet<Element> elements = new TreeSet<>(new Comparator<Element>() {
            
                @Override
                public int compare(final Element o1, final Element o2) {
                    return o1.getUuid().compareTo(o2.getUuid());
                }
            
            });
            
            for (final Manifestation manifestation : ramc.getUtilized()) {
                final ModelElement me = manifestation.getUtilizedElement();
                if (ModelComponent.isValidManifestedElement(me)) {
                    elements.add(me);
                }
            }
            
            final CoreSession session = CoreSession.getSession(ramc);
            final Stereotype manifStereotype = (Stereotype) session.getSmFactory().getObjectReference(session.getMetamodel().getMClass(Stereotype.class), "d5bccf8e-79b3-48df-8c79-09200aa52d19", "manifestation");
            for (final Dependency dep : ramc.getDependsOnDependency()) {
                if (dep.getExtension().contains(manifStereotype)) {
                    final ModelElement me = dep.getDependsOn();
                    if (ModelComponent.isValidManifestedElement(me)) {
                        elements.add(me);
                    }
                }
            }
            return elements;
        }

        @objid ("3389bf8b-11d3-4f6b-88c2-9a53d016ba92")
        public static String getName(final Artifact ramc) {
            return ramc.getName();
        }

        @objid ("b01fd8a0-00d5-440c-bad2-a9a541ccce44")
        public static Version getVersion(final Artifact ramc) {
            final List<TaggedValue> taggedValues = ramc.getTag();
            
            for (final TaggedValue taggedValue : taggedValues) {
                final TagType type = taggedValue.getDefinition();
                if (type != null && type.getName().equals("ModelComponentVersion")) {
                    final List<TagParameter> parameters = taggedValue.getActual();
                    if (parameters.size() == 1) {
                        return new Version(parameters.get(0).getValue());
                    }
                }
            }
            return new Version(0, 0, 0);
        }

        @objid ("09e9f15e-b0ee-41c6-b5c5-f088de43c139")
        public static String getProvider(final Artifact ramc) {
            final List<TaggedValue> taggedValues = ramc.getTag();
            
            for (final TaggedValue taggedValue : taggedValues) {
                final TagType type = taggedValue.getDefinition();
                if (type != null && type.getName().equals("ModelComponentProvider")) {
                    final List<TagParameter> parameters = taggedValue.getActual();
                    if (parameters.size() == 1) {
                        return parameters.get(0).getValue();
                    }
                }
            }
            return "";
        }

    }

    @objid ("dddaae39-b775-448d-b0d6-1f2e9976bf53")
    private static class Writer {
        @objid ("361dc1d2-a483-41ff-ad66-55b3917f32c4")
        public static void setContributors(final Artifact ramc, final Map<String, String> contributors) {
            // Remove existing entries
            for (final TaggedValue tag : new ArrayList<>(ramc.getTag())) {
                if ("ModelComponentContributors".equals(tag.getDefinition().getName())) {
                    tag.delete();
                }
            }
            
            // Add new ones
            // Add a unique tag
            final IInfrastructureModelFactory modelFactory = Writer.getInfrastructureModelFactory(ramc);
            TaggedValue contributorsTag;
            try {
                contributorsTag = modelFactory.createTaggedValue(ModelComponent.MODELER_MODULE, "ModelComponentArchive", "ModelComponentContributors", ramc);
            } catch (@SuppressWarnings ("unused") final ExtensionNotFoundException e) {
                // Log.error("Tag type 'ModelerModule#ModelComponentContributors' not found or not unique.");
                return;
            }
            
            // Add one parameter per contributor
            for (final Entry<String, String> entry : contributors.entrySet()) {
                modelFactory.createTagParameter(entry.getKey() + '#' + entry.getValue(), contributorsTag);
            }
            
        }

        @objid ("d70f5e47-ca5e-4af0-9372-b101accf3ab4")
        public static void setDependencies(final Artifact ramc, final Set<ModelComponent> parentRamcs) {
            // Remove existing dependencies
            for (final ElementImport ei : new ArrayList<>(ramc.getOwnedImport())) {
                if (ei.getImportedElement() instanceof Artifact && ei.getImportedElement().isStereotyped(ModelComponent.MODELER_MODULE, "ModelComponentArchive")) {
                    ei.delete();
                }
            }
            
            // Add new ones
            final IStandardModelFactory standardFactory = Writer.getStandardModelFactory(ramc);
            for (final ModelComponent m : parentRamcs) {
                if (m.getArtifact().isStereotyped(ModelComponent.MODELER_MODULE, "ModelComponentArchive")) {
                    standardFactory.createElementImport(ramc, m.getArtifact());
                }
            }
            
        }

        @objid ("145c5d6e-89c8-43cf-81e5-73799d802b11")
        public static void setDescription(final Artifact ramc, final String description) {
            try {
                Note note = ramc.getNote(ModelComponent.MODELER_MODULE, ModelElement.MQNAME, "description");
                if (note == null) {
                    final IInfrastructureModelFactory factory = Writer.getInfrastructureModelFactory(ramc);
                    note = factory.createNote(ModelComponent.MODELER_MODULE, ModelElement.MQNAME, "description", ramc, "");
                }
                if (note != null) {
                    note.setContent(description);
                }
            } catch (@SuppressWarnings ("unused") final ExtensionNotFoundException e) {
                // Log.error("Note type 'ModelerModule#description' not found or not unique");
            }
            
        }

        @objid ("420b993c-1ea6-45cf-8b6b-6858601ad4b0")
        public static void setExportedFiles(final Artifact ramc, final Set<ExportedFileEntry> exportedFiles) {
            // Remove existing entries
            // RAMC migrated from Modelio 2 might have several tags, clean them all
            // In Modelio 3 a unique tag is used with several parameters (two per file)
            for (final TaggedValue tag : new ArrayList<>(ramc.getTag())) {
                if ("ModelComponentFiles".equals(tag.getDefinition().getName())) {
                    tag.delete();
                }
            }
            
            // Add new ones
            // Add a unique tag
            final IInfrastructureModelFactory modelFactory = Writer.getInfrastructureModelFactory(ramc);
            TaggedValue filesTag;
            try {
                filesTag = modelFactory.createTaggedValue(ModelComponent.MODELER_MODULE, "ModelComponentArchive", "ModelComponentFiles", ramc);
            } catch (@SuppressWarnings ("unused") final ExtensionNotFoundException e) {
                // Log.error("Tag type 'ModelerModule#ModelComponentFiles' not found or not unique.");
                return;
            }
            
            // Add two parameters per file. The first one is the exported file
            // path, the second is the deployment relative path
            for (final ExportedFileEntry file : exportedFiles) {
                modelFactory.createTagParameter(file.getFileToExport().toString().replace(File.separator, "/"), filesTag);
                modelFactory.createTagParameter(file.getExportPath().replace(File.separator, "/"), filesTag);
            }
            
        }

        @objid ("c96eeb6f-10d1-4986-ac88-7fb5ed3ccb8f")
        public static void setManifestedElements(final Artifact ramc, final Set<Element> manifestedElements) {
            final Set<Element> toManifest = new HashSet<>(manifestedElements);
            
            // Remove existing manifestations
            for (final Manifestation manifestation : new ArrayList<>(ramc.getUtilized())) {
                final UmlModelElement me = manifestation.getUtilizedElement();
                if (toManifest.contains(me)) {
                    toManifest.remove(me);
                } else {
                    manifestation.delete();
                }
            }
            
            // Remove existing <<manifestation>> dependencies
            final CoreSession session = CoreSession.getSession(ramc);
            final Stereotype manifStereotype = (Stereotype) session.getSmFactory().getObjectReference(session.getMetamodel().getMClass(Stereotype.class), "d5bccf8e-79b3-48df-8c79-09200aa52d19", "manifestation");
            for (final Dependency dep : new ArrayList<>(ramc.getDependsOnDependency())) {
                if (dep.getExtension().contains(manifStereotype)) {
                    final ModelElement me = dep.getDependsOn();
                    if (toManifest.contains(me)) {
                        toManifest.remove(me);
                    } else {
                        dep.delete();
                    }
                }
            }
            
            // Add new ones
            final IStandardModelFactory standardFactory = Writer.getStandardModelFactory(ramc);
            final IInfrastructureModelFactory modelFactory = Writer.getInfrastructureModelFactory(ramc);
            
            for (final Element e : toManifest) {
                if (ModelComponent.isValidManifestedElement(e)) {
                    if (e instanceof UmlModelElement) {
                        final Manifestation m = standardFactory.createManifestation();
                        m.setOwner(ramc);
                        m.setUtilizedElement((UmlModelElement) e);
                    } else if (e instanceof ModelElement) {
                        modelFactory.createDependency(ramc, (ModelElement) e, manifStereotype);
                    }
                }
            }
            
        }

        @objid ("a9e17425-13bc-4f69-9934-8274f44ea64e")
        public static void setName(final Artifact ramc, final String name) {
            ramc.setName(name);
        }

        @objid ("cbaff0fc-a306-4773-a34a-759dc93e1f26")
        public static void setVersion(final Artifact ramc, final Version version) {
            final String v = String.format("%d.%d.%02d", version.getMajorVersion(), version.getMinorVersion(), version.getBuildVersion());
            
            try {
                TaggedValue taggedValue = ramc.getTag(ModelComponent.MODELER_MODULE, "ModelComponentArchive", "ModelComponentVersion");
                if (taggedValue == null) {
                    final IInfrastructureModelFactory factory = Writer.getInfrastructureModelFactory(ramc);
                    taggedValue = factory.createTaggedValue(ModelComponent.MODELER_MODULE, "ModelComponentArchive", "ModelComponentVersion", ramc);
                    factory.createTagParameter("", taggedValue);
                }
            
                final List<TagParameter> parameters = taggedValue.getActual();
                if (parameters.size() > 0) {
                    parameters.get(0).setValue(v);
                }
            } catch (@SuppressWarnings ("unused") final ExtensionNotFoundException e) {
                // Log.error("Tag type 'ModelerModule#ModelComponentVersion not found or not unique.");
            }
            
        }

        @objid ("bd9cc712-0053-4a71-aa13-1af0b63c5923")
        private static IInfrastructureModelFactory getInfrastructureModelFactory(final Artifact ramc) {
            return MTools.get(ramc).getModelFactory(IInfrastructureModelFactory.class);
        }

        @objid ("cf83eb2c-672a-4091-83de-53bb4b0b8cbe")
        private static IStandardModelFactory getStandardModelFactory(final Artifact ramc) {
            return MTools.get(ramc).getModelFactory(IStandardModelFactory.class);
        }

        @objid ("717e2924-dbdb-4ddd-bb6b-4cf230aa40ca")
        public static void setProvider(final Artifact ramc, final String provider) {
            try {
                TaggedValue taggedValue = ramc.getTag(ModelComponent.MODELER_MODULE, "ModelComponentArchive", "ModelComponentProvider");
                if (taggedValue == null) {
                    final IInfrastructureModelFactory factory = Writer.getInfrastructureModelFactory(ramc);
                    taggedValue = factory.createTaggedValue(ModelComponent.MODELER_MODULE, "ModelComponentArchive", "ModelComponentProvider", ramc);
                    factory.createTagParameter("", taggedValue);
                }
            
                final List<TagParameter> parameters = taggedValue.getActual();
                if (parameters.size() > 0) {
                    parameters.get(0).setValue(provider);
                }
            } catch (@SuppressWarnings ("unused") final ExtensionNotFoundException e) {
                // Log.error("Tag type 'ModelerModule#ModelComponentVersion not found or not unique.");
            }
            
        }

    }

}
