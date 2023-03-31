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
package org.modelio.gproject.ramc.core.packaging;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.ramc.IModelComponentInfos;
import org.modelio.gproject.data.ramc.ManifestWriter;
import org.modelio.gproject.data.ramc.ModelRef;
import org.modelio.gproject.ramc.core.model.IModelComponent;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.version.ModelioVersion;

/**
 * Used to store and write the contents of the metadata.xml file of a
 * ModelComponent
 * 
 * @author phv
 */
@objid ("aa3687e8-7908-41f1-9c19-eff0d2ab45c3")
class Metadatas {
    @objid ("284a82fd-846d-4af0-bed8-c7141323bcc9")
    private final List<org.modelio.gproject.data.ramc.IModelComponentInfos.ExportedFile> files = new ArrayList<>();

    @objid ("8217b9f6-b086-4a4e-bab3-394884f91a1d")
    private Collection<MMetamodelFragment> mmFragments;

    @objid ("2a5bdb90-833c-4193-aeca-dd4e44dd723d")
    private final IModelComponent ramc;

    @objid ("63d147eb-f1da-4338-a0a7-4dd686d074a7")
    private final List<MRef> roots = new ArrayList<>();

    /**
     * initialize the meta datas.
     * @param ramc the model component representation.
     */
    @objid ("154faeef-56c6-4de9-85db-0e9deb66ef4b")
    public  Metadatas(IModelComponent ramc) {
        this.ramc = ramc;
    }

    /**
     * Add a file to export.
     * @param fileEntry the entry telling where the file must be deployed.
     * @param archiveFileName the file name in the archive
     */
    @objid ("4e57c868-9fca-491d-8f77-95fdd210f821")
    public void addExportedFileDef(String archiveFileName, String deploymentPath, FileTime fileDate) {
        this.files.add(new IModelComponentInfos.ExportedFile(archiveFileName, Paths.get(deploymentPath), fileDate));
    }

    /**
     * @param root a model element to include
     */
    @objid ("92e4ce92-4d49-4975-be1b-d04b09e09640")
    public void addRoot(MRef root) {
        this.roots.add(root);
    }

    @objid ("544c1a00-5615-49b6-a9ce-aa52239599b9")
    public void setUsedMetamodelFragments(Collection<MMetamodelFragment> fragments) {
        this.mmFragments = fragments;
    }

    /**
     * Writes the "metadatas.xml" file.
     * @param exportPath the directory where "metadatas.xml" file will be written.
     * @throws IOException on failure
     */
    @objid ("9ffcedf6-e353-4f69-a321-1d35164be6e2")
    public void write(Path exportPath) throws IOException {
        final Path metadataFile = exportPath.resolve("metadatas.xml");
        
        try (OutputStream metadataWriter = Files.newOutputStream(metadataFile)) {
            ManifestWriter w = new ManifestWriter();
            IModelComponentInfos manifest = asModelComponentInfos();
        
            w.write(manifest, metadataWriter);
        }
        
    }

    @objid ("aa085837-52e2-4ad0-ad5c-39b0a4ae6426")
    List<MRef> getRoots() {
        return this.roots;
    }

    /**
     * Adapt this instance to {@link IModelComponentInfos} interface.
     * @return the adapter
     */
    @objid ("d40d8002-1dac-40a6-b4e3-c9543e9d8671")
    private IModelComponentInfos asModelComponentInfos() {
        List<IModelComponentInfos.ExportedFile> exportedFiles = new ArrayList<>(this.files);
        exportedFiles.sort(Comparator.comparing(t -> t.getNameInArchive()));
        
        IModelComponentInfos manifest = new IModelComponentInfos() {
            @Override
            public String getProvider() {
                return Metadatas.this.ramc.getProvider();
            }
            @Override
            public Version getVersion() {
                return Metadatas.this.ramc.getVersion();
            }
        
            @Override
            public Version getModelioVersion() {
                return ModelioVersion.VERSION;
            }
        
            @Override
            public List<ModelRef> getRoots() {
                return Metadatas.this.roots.stream()
                        .map(ref -> new ModelRef(ref.mc, ref.uuid, ref.name))
                        .collect(Collectors.toList());
            }
        
            @Override
            public List<VersionedItem<?>> getRequiredModelComponents() {
                return Metadatas.this.ramc.getRequiredModelComponents().stream()
                        .map(mc -> new VersionedItem<>(mc.getName(), mc.getVersion()))
                        .collect(Collectors.toList());
            }
        
            @Override
            public List<VersionedItem<?>> getRequiredMetamodelFragments() {
                return Metadatas.this.mmFragments.stream()
                        .map(mc -> new VersionedItem<>(mc.getName(), mc.getVersion()))
                        .collect(Collectors.toList());
            }
        
            @Override
            public String getName() {
                return Metadatas.this.ramc.getName();
            }
        
            @Override
            public List<ExportedFile> getExportedFiles() {
                return exportedFiles;
            }
        
            @Override
            public String getDescription() {
                return Metadatas.this.ramc.getDescription();
            }
        
            @Override
            public List<VersionedItem<?>> getContributingModules() {
                return Metadatas.this.ramc.getContributingModules()
                        .entrySet()
                        .stream()
                        .map(mc -> new VersionedItem<>(mc.getKey(), new Version(mc.getValue())))
                        .collect(Collectors.toList());
            }
        };
        return manifest;
    }

}
