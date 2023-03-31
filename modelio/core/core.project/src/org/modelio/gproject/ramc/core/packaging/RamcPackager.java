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
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.gproject.project.AbstractGProject;
import org.modelio.gproject.project.GProject;
import org.modelio.gproject.ramc.core.model.ModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor.ExportedFileEntry;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.files.Zipper;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSessionBuilder;
import org.modelio.vcore.session.impl.permission.BasicAccessManager;
import org.modelio.vstore.exml.local.ExmlBase;

/**
 * Model component (RAMC) packager.
 * <p>
 * Usage :
 * <ul>
 * <li>use a constructor like {@link #RamcPackager(GProject, Artifact, Path)},
 * <li>call {@link #run(IModelioProgress)}.
 * </li>
 */
@objid ("88190e3b-9e9c-11e1-a22d-001ec947ccaf")
public class RamcPackager {
    @objid ("f7558748-f5e8-4f18-b036-50bda9a1450b")
    private boolean includeArtifact;

    @objid ("d3a3c16f-cb72-11e1-87f1-001ec947ccaf")
    private IGProject gproject;

    @objid ("d3a3c16a-cb72-11e1-87f1-001ec947ccaf")
    private ModelComponent ramc;

    @objid ("503e4c0e-9596-4ba5-a9f1-d05ca22f608f")
    private List<IModelComponentContributor> contributors;

    @objid ("c2e03ff9-a5b8-11e1-aa98-001ec947ccaf")
    private Path archivePath;

    @objid ("d3a3c170-cb72-11e1-87f1-001ec947ccaf")
    private Path workPath;

    /**
     * @param gproject the project
     * @param artifact the RAMC artifact
     * @param archivePath the archive path
     */
    @objid ("7ebc81ec-ccc1-4db4-90a8-2f1a06e60ba5")
    public  RamcPackager(IGProject gproject, Artifact artifact, final Path archivePath) {
        this(gproject, artifact, archivePath, new ArrayList<IModelComponentContributor>());
    }

    /**
     * @param gproject the project
     * @param artifact the RAMC artifact
     * @param archivePath the archive path
     * @param contributors the RAMC contributors
     */
    @objid ("c2e03ffa-a5b8-11e1-aa98-001ec947ccaf")
    public  RamcPackager(IGProject gproject, Artifact artifact, final Path archivePath, List<IModelComponentContributor> contributors) {
        this.archivePath = archivePath;
        this.ramc = new ModelComponent(artifact);
        this.gproject = gproject;
        this.contributors = contributors;
        this.includeArtifact = true;
        
        // Force the metamodel version to the current one.
        Version v = this.ramc.getVersion();
        v = new Version(v.getMajorVersion(), v.getMinorVersion(), v.getBuildVersion());
        this.ramc.setRamcVersion(v);
        
    }

    /**
     * Run the packaging.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
     * to call done() on the given monitor. Accepts <i>null</i>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @throws IOException if any error occurs.
     */
    @objid ("c2e03ffd-a5b8-11e1-aa98-001ec947ccaf")
    public void run(final IModelioProgress monitor) throws IOException {
        SubProgress subMonitor = SubProgress.convert(monitor, 40);
        
        this.workPath = Files.createTempDirectory("ramc-export");
        Path modelPath = this.workPath.resolve("model");
        Files.createDirectories(modelPath);
        
        Metadatas metadatas = new Metadatas(this.ramc);
        
        // Export the model
        exportModel(modelPath, metadatas, subMonitor.newChild(10));
        
        // Export the additional files
        exportFiles(this.workPath, metadatas, subMonitor.newChild(10));
        
        // Write the metadat.xml file
        metadatas.write(this.workPath);
        
        // Create the compressed archive
        RamcPackager.createCompressedArchive(this.workPath, this.archivePath, subMonitor.newChild(10));
        
        // Cleanup
        FileUtils.delete(this.workPath);
        
    }

    @objid ("c2e0400a-a5b8-11e1-aa98-001ec947ccaf")
    private void exportModel(Path modelPath, Metadatas metadatas, SubProgress monitor) throws IOException {
        SubProgress subMonitor = SubProgress.convert(monitor, 60);
        subMonitor.subTask(CoreProject.I18N.getString("RamcPackager.ExportModel"));
        
        ICoreSession srcSession = this.gproject.getSession();
        ICoreSession targetSession = new CoreSessionBuilder()
                .withMetamodel(srcSession.getMetamodel())
                .build();
        
        // register metamodel target session on metamodel extensions
        IGProject project = AbstractGProject.getProject(srcSession);
        for (IGMetamodelExtension mmExt : project.getProjectEnvironment().getDefaultMetamodelExtensions()) {
            // Register metamodel extensions
            mmExt.register(targetSession);
        }
        
        try {
            ExmlBase targetRepository = new ExmlBase(modelPath, this.ramc.getName());
            targetRepository.create(targetSession.getMetamodel());
            targetSession.getRepositorySupport().connectRepository(
                    targetRepository,
                    new BasicAccessManager(),
                    subMonitor.newChild(10));
        
            // Configure the exporter
            ModelExporter exporter = new ModelExporter(srcSession, targetSession, targetRepository);
            exporter.configureModelExporter(this.ramc, this.includeArtifact, this.contributors);
            subMonitor.worked(10);
        
            // Run the exporter
            try (ITransaction t = targetSession.getTransactionSupport().createTransaction("Export model to RAMC archive")) {
                exporter.run(metadatas);
                subMonitor.worked(10);
                t.commit();
                subMonitor.worked(10);
            }
        
            // Save the exported repository
            targetSession.save(subMonitor.newChild(10));
        
            // Compress the indexes, they have plenty holes
            targetRepository.getMaintenance().compressIndexes(subMonitor.newChild(10));
        } finally {
            targetSession.close();
        }
        
    }

    @objid ("d3a623c0-cb72-11e1-87f1-001ec947ccaf")
    private void exportFiles(Path exportPath, Metadatas metadatas, SubProgress subMonitor) throws IOException {
        subMonitor.subTask(CoreProject.I18N.getString("RamcPackager.ExportFiles"));
        FilesExporter exporter = new FilesExporter(exportPath, this.gproject.getPfs().getProjectPath());
        exporter.run(getFilesToExport(), metadatas, subMonitor);
        
    }

    @objid ("c2e04000-a5b8-11e1-aa98-001ec947ccaf")
    private static void createCompressedArchive(Path dataPath, Path archive, IModelioProgress subMonitor) throws IOException, ZipException {
        subMonitor.subTask(MessageFormat.format(CoreProject.I18N.getString("RamcPackager.ArchiveModelComponent"),
                archive.toString()));
        
        Zipper zip = new Zipper(archive);
        zip.compressContent(dataPath, subMonitor, null);
        
    }

    @objid ("3ae7dea2-eb51-473c-a8ed-969d6d1ec1af")
    private List<ExportedFileEntry> getFilesToExport() {
        List<ExportedFileEntry> exportedFiles = new ArrayList<>();
        
        // Files from RAMC definition
        for (ExportedFileEntry file : this.ramc.getExportedFiles()) {
            exportedFiles.add(file);
        }
        
        // Files from contributors
        for (IModelComponentContributor contributor : this.contributors) {
            for (ExportedFileEntry file : contributor.getFiles()) {
                exportedFiles.add(file);
            }
        }
        return exportedFiles;
    }

    /**
     * @param onOff true to include the RAMC artifact in the archive.
     */
    @objid ("fcc9a043-0eee-4d43-aa9a-720f28bba6a5")
    public void setIncludeArtifact(boolean onOff) {
        this.includeArtifact = onOff;
    }

}
