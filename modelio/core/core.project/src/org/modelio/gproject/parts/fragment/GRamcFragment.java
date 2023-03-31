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
package org.modelio.gproject.parts.fragment;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.FragmentAuthenticationException;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.ramc.IModelComponentInfos;
import org.modelio.gproject.data.ramc.ModelComponentArchive;
import org.modelio.gproject.data.ramc.ModelRef;
import org.modelio.gproject.monitor.GProjectEvent;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.files.Unzipper;
import org.modelio.vbasic.net.UriPathAccess;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.permission.BasicAccessManager;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vstore.exml.local.ExmlBase;
import org.modelio.vstore.exml.resource.IExmlRepositoryGeometry;
import org.modelio.vstore.exml.resource.LocalExmlResourceProvider;

/**
 * RAMC fragment stored in a .ramc file.
 * <p>
 * The RAMC archive is extracted to the data directory on mounting.
 */
@objid ("1d42f8e2-ed6b-404f-82fe-8acbd582f916")
public class GRamcFragment extends AbstractGModelFragment {
    @objid ("f0151ac8-8100-4395-bc62-a43276ad92f6")
    protected ExmlBase repository;

    @objid ("f3550114-f864-4010-963c-b9f437ff8653")
    protected Path modelLocation;

    @objid ("4fd098e6-e592-4aac-ba73-8bd1b683896f")
    private List<MObject> roots;

    /**
     * Initialize the RAMC fragment.
     * @param desc the part descriptor
     */
    @objid ("71e6517c-26f5-44e5-a689-7268eb027926")
    public  GRamcFragment(GProjectPartDescriptor desc) {
        super(desc);
    }

    /**
     * Get the model component informations such as the version, the description, the dependencies...
     * @return the model component description.
     * @throws IOException in case of I/O failure.
     */
    @objid ("acc4d59c-6d04-4ab1-8c04-cc3cde236fad")
    @Override
    public IModelComponentInfos getInformations() throws IOException {
        final Path archivePath = extractRamcToLocal(null);
        return new ModelComponentArchive(archivePath, false).getInfos();
    }

    @objid ("b48d3dbd-f9f5-465e-9497-25bc89175208")
    @Override
    public IRepository getRepository() {
        return this.repository;
    }

    @objid ("0245eed3-bdf2-4758-a440-f2295404543c")
    @Override
    protected IRepository doMountInitRepository(IGProject project, IModelioProgress aMonitor) throws IOException, FragmentAuthenticationException {
        // Look for a local path and create the directory if needed
        final Path runtimePath = getRuntimeDirectory();
        Files.createDirectories(runtimePath);
        
        // Copy the RAMC into the data directory if needed
        final Path localUri = extractRamcToLocal(aMonitor);
        
        // The RAMC model is in a "model" sub directory.
        this.modelLocation = localUri.resolve("model");
        
        // Instantiate the repository.
        final LocalExmlResourceProvider resProvider = new LocalExmlResourceProvider(this.modelLocation, this.modelLocation, getId());
        
        this.repository = new ExmlBase(resProvider);
        return this.repository;
    }

    @objid ("34cc9fca-bdcc-4f38-8ef4-8782b85d48b1")
    @Override
    protected void doUmountPostProcess(IGProject project, IModelioProgress monitor) throws IOException {
        this.repository = null;
    }

    @objid ("cec3578d-3a8a-4600-af20-1272c7df803f")
    protected final void assertOpen() throws IllegalStateException {
        if (this.repository == null) {
            throw new IllegalStateException("The '" + getId() + "' fragment is not mount.");
        }
        
    }

    @objid ("b883cc12-b4e5-4d18-adc0-a09a6af6e423")
    @Override
    protected List<MObject> doGetRoots() throws IOException {
        if (this.roots == null) {
            this.roots = new ArrayList<>();
        
            final IModelComponentInfos infos = ModelComponentArchive.getRamcDirectoryInfos(getContentDirectory());
            for (final ModelRef mref : infos.getRoots()) {
                final MObject obj = getRepository().findById(getProjectMetamodel().getMClass(mref.mc), mref.uuid);
                if (obj != null) {
                    this.roots.add(obj);
                }
            }
        }
        return this.roots != null ? this.roots : new ArrayList<>();
    }

    @objid ("07cf416d-7632-4e85-836b-cf29418c72dd")
    @Override
    protected IAccessManager doInitAccessManager() {
        final BasicAccessManager ret = new BasicAccessManager();
        ret.setWriteable(false);
        ret.setRamc(true);
        return ret;
    }

    /**
     * Redefined to delete all deployed extern files.
     */
    @objid ("7e0f4cee-980f-464c-a0dc-46902aabe261")
    @Override
    protected void doDelete(final IGProject project, final IModelioProgress monitor) {
        try {
            removeExportedFilesOfFragment(project, monitor);
        } catch (final IOException e) {
            project.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this, e));
        }
        
    }

    /**
     * Copy the RAMC file into the fragment data directory if the URI is not relative to the data directory.
     * 
     * To ensure that the ramc contents are updated in case of version change while avoiding useless costly zip extractions, a tag file is used as follows:<ul>
     * <li> each time the ramc "content" is unzipped a tag file named T_V.R.C is created where V.R.C represents the RAMC version</li>
     * <li> when the extract method is called again the existence of the proper tag file is first checked. If present then no extraction is carried out and the existing path and content are returned</li>
     * <li> when a new version is required, obviously the tagfile does not exist (the existing tagfile does not match) and a new extraction is carried out</li>
     * </ul>
     * @param monitor a progress monitor
     * @return the local ramc file location
     * @throws IOException in case of failure.
     */
    @objid ("af8039c4-5c93-4d59-82af-8a098adae77d")
    private Path extractRamcToLocal(final IModelioProgress monitor) throws IOException {
        final Path ramcContentDir = getContentDirectory();
        final Path tagFile = ramcContentDir.resolve(getDescriptor().getVersion().toString("T_V.R.C"));
        
        if (Files.isDirectory(ramcContentDir) && Files.exists(ramcContentDir.resolve(tagFile))) {
            // Content is present and up-to-date, fast exit
            return ramcContentDir;
        }
        
        // Here we have to copy the RAMC to local dir.
        
        // Delete any previous content
        FileUtils.delete(ramcContentDir);
        Files.createDirectories(ramcContentDir);
        
        // Resolve RAMC archive location
        // Note : the .ramc may be directly in the RAMC data directory (getDataDirectory() ).
        URI uri = getDescriptor().getLocation();
        if (!uri.isAbsolute()) {
            uri = getProject().getPfs().getProjectPath().toUri().resolve(uri);
        }
        
        // Open an access to the archive, downloading it if remote.
        try (UriPathAccess acc = new UriPathAccess(uri, resolveAuthData())) {
            final SubProgress mon = SubProgress.convert(monitor, 2);
            final String progressLabel = CoreProject.I18N.getMessage("RamcFileFragment.ExtractRamcFrom", getId(), uri);
            mon.subTask(progressLabel);
        
            // Unzip the archive
            new Unzipper()
            .setProgressLabelPrefix(progressLabel)
            .unzip(acc.getPath(), ramcContentDir, mon.newChild(1));
        
            // Deploy exported files
            mon.subTask(CoreProject.I18N.getMessage("RamcFileFragment.DeployRamcFiles", getId(), uri));
            new ModelComponentArchive(ramcContentDir, false).installExportedFiles(getProject().getPfs().getProjectPath(), mon.newChild(1));
        
            // Create the tag file
            Files.createFile(tagFile);
        
            return ramcContentDir;
        } catch (final MalformedURLException e1) {
            final String msg = CoreProject.I18N.getMessage("RamcFileFragment.InvalidUri", uri, e1.getLocalizedMessage());
            throw new IOException(msg, e1);
        } catch (final IllegalArgumentException e1) {
            final String msg = CoreProject.I18N.getMessage("RamcFileFragment.InvalidUri", uri, e1.getLocalizedMessage());
            throw new IOException(msg, e1);
        }
        
    }

    /**
     * Delete other files deployed with the RAMC such as extern libraries or source files.
     * @param monitor a progress monitor
     * @throws IOException in case of failure.
     */
    @objid ("cf0a175d-0656-4b09-a715-7b665fb58694")
    private void removeExportedFilesOfFragment(IGProject project, final IModelioProgress monitor) throws IOException {
        Path contentDirectory = project.getPfs().getProjectDataPath().resolve(FRAGMENTS_SUBDIR).resolve(this.encodedDirectoryName).resolve("content");
        Path deploymentPath = project.getPfs().getProjectPath();
        
        final ModelComponentArchive modelComponentArchive = new ModelComponentArchive(contentDirectory, false);
        modelComponentArchive.removeExportedFiles(deploymentPath, monitor);
        
    }

    /**
     * Get the directory where the RAMC archive is extracted.
     * @return the extracted RAMC directory.
     */
    @objid ("18919eca-1964-4dfa-8408-5853594708ba")
    private Path getContentDirectory() {
        return getDataDirectory().resolve("content");
    }

    @objid ("707e76dc-1aeb-441b-be7e-05b6b2b57bbd")
    @Override
    public MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException {
        final MetamodelVersionDescriptor mmDesc = new MetamodelVersionDescriptor();
        
        boolean found = false;
        for (final VersionedItem<?> versionedItem : getInformations().getRequiredMetamodelFragments()) {
            final VersionedItem<Void> v = new VersionedItem<>(versionedItem.getName(), versionedItem.getVersion());
            mmDesc.addDescriptor(v);
            found = true;
        }
        
        if (!found) {
            // Old RAMC with no metamodel version info.
            // Try to guess from file presence.
            Version v;
            final Path modelio2_model = getContentDirectory().resolve("model.xml");
            final Path modelio3_stamp = getContentDirectory().resolve("model").resolve(IExmlRepositoryGeometry.ADMIN_DIRNAME).resolve("stamp.dat");
            final Path modelio3_format_version = getContentDirectory().resolve("model").resolve(IExmlRepositoryGeometry.FORMAT_VERSION_PATH);
            if (Files.isRegularFile(modelio3_format_version)) {
                // Assume last Modelio 3.1.x - 8020 : 24/04/2012
                // Modelio 3.1 - 9020: 28/11/2013
                v = VersionHelper.convert(9020);
            } else if (Files.isRegularFile(modelio3_stamp)) {
                // Assume last Modelio 3.0.x :
                // Modelio Phoenix 3.0 - 9017: 04/09/2013
                v = VersionHelper.convert(9017);
            } else if (Files.isRegularFile(modelio2_model)) {
                // Assume last Modelio 2.2 - 8020 : 24/04/2012
                v = VersionHelper.convert(8020);
            } else {
                // Set to Modelio 2.0 by default
                v = VersionHelper.convert(8000);
            }
        
            mmDesc.addDescriptor(new VersionedItem<Void>(StandardMetamodel.NAME, v));
        }
        return mmDesc;
    }

    @objid ("827bd39b-0620-443d-83ec-04ea8d061b9f")
    @Override
    public void rename(final String name, final IModelioProgress aMonitor) throws IOException {
        throw new UnsupportedOperationException();
    }

    @objid ("cb35ee8c-de56-421d-986d-c111744b09a1")
    @Override
    protected void doInstall(IGProject aProject, IModelioProgress monitor) throws GPartException {
        // Do nothing
    }

    @objid ("bf3c9236-72a9-48ce-b3db-4a1c5085b23c")
    @Override
    protected void doUninstall(IGProject aProject, IModelioProgress monitor) throws GPartException {
        // Do nothing
    }

}
