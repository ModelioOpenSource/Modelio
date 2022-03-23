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
package org.modelio.gproject.fragment.ramcfile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.ramc.IModelComponentInfos;
import org.modelio.gproject.data.ramc.ModelComponentArchive;
import org.modelio.gproject.data.ramc.ModelRef;
import org.modelio.gproject.fragment.AbstractFragment;
import org.modelio.gproject.fragment.VersionHelper;
import org.modelio.gproject.gproject.GProjectEvent;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.metamodel.StandardMetamodel;
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
@objid ("f0e56183-cc02-11e1-87f1-001ec947ccaf")
public class RamcFileFragment extends AbstractFragment {
    /**
     * Fragment type identifier.
     */
    @objid ("746f79dc-cc3e-11e1-87f1-001ec947ccaf")
    public static final FragmentType TYPE = FragmentType.RAMC;

    @objid ("7420cc95-cc3e-11e1-87f1-001ec947ccaf")
    protected Path modelLocation;

    @objid ("7420cc97-cc3e-11e1-87f1-001ec947ccaf")
    protected ExmlBase repository;

    @objid ("7420cc98-cc3e-11e1-87f1-001ec947ccaf")
    protected URI uri;

    @objid ("bae38a1c-9935-4d5c-83da-1815dc1ede1b")
    private List<MObject> roots;

    /**
     * Initialize the RAMC fragment.
     * @param id the fragment ID.
     * @param uri the ramc file location as an URI
     * @param definitionScope definition scope
     * @param properties not used.
     * @param authConf authentication configuration
     */
    @objid ("7420cc9c-cc3e-11e1-87f1-001ec947ccaf")
     RamcFileFragment(final String id, final URI uri, final DefinitionScope definitionScope, final GProperties properties, final GAuthConf authConf) {
        super(id, definitionScope, properties, authConf);
        Objects.requireNonNull(uri, "URI must be non null");
        
        this.uri = uri;
        
    }

    /**
     * Get the model component informations such as the version, the description, the dependencies...
     * @return the model component description.
     * @throws IOException in case of I/O failure.
     */
    @objid ("cc07bd6d-d668-4067-b513-b7afaac79182")
    @Override
    public IModelComponentInfos getInformations() throws IOException {
        final Path archivePath = extractRamcToLocal(null);
        return new ModelComponentArchive(archivePath, false).getInfos();
    }

    @objid ("7420cca7-cc3e-11e1-87f1-001ec947ccaf")
    @Override
    public IRepository getRepository() {
        return this.repository;
    }

    @objid ("7420ccaf-cc3e-11e1-87f1-001ec947ccaf")
    @Override
    public FragmentType getType() {
        return RamcFileFragment.TYPE;
    }

    @objid ("7420ccb4-cc3e-11e1-87f1-001ec947ccaf")
    @Override
    public URI getUri() {
        return this.uri;
    }

    @objid ("7420cca1-cc3e-11e1-87f1-001ec947ccaf")
    @Override
    protected IRepository doMountInitRepository(final IModelioProgress aMonitor) throws IOException {
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

    @objid ("7420cca4-cc3e-11e1-87f1-001ec947ccaf")
    @Override
    protected void doUnmountPostProcess() throws IOException {
        this.repository = null;
    }

    @objid ("24310712-d0da-11e1-b069-001ec947ccaf")
    protected final void assertOpen() throws IllegalStateException {
        if (this.repository == null) {
            throw new IllegalStateException("The '" + getId() + "' fragment is not mount.");
        }
        
    }

    @objid ("7420ccb9-cc3e-11e1-87f1-001ec947ccaf")
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

    @objid ("dd691981-395a-11e2-a6db-001ec947ccaf")
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
    @objid ("4e9cb211-21ec-4f64-9002-87e35edc1543")
    @Override
    protected void doDelete(final IModelioProgress monitor) {
        try {
            removeExportedFilesOfFragment(monitor);
        } catch (final IOException e) {
            getProject().getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this, e));
        }
        
    }

    /**
     * Copy the RAMC file into the fragment data directory if the URI is not
     * relative to the data directory.
     * @param monitor a progress monitor
     * @return the local ramc file location
     * @throws IOException in case of failure.
     */
    @objid ("b419188e-0baa-11e2-bed6-001ec947ccaf")
    private Path extractRamcToLocal(final IModelioProgress monitor) throws IOException {
        final Path localRamcPath = getContentDirectory();
        
        if (Files.isDirectory(localRamcPath)) {
            return localRamcPath;
        } else {
            // We have to copy the RAMC to local dir.
            Files.createDirectories(getDataDirectory());
        
            try (UriPathAccess acc = new UriPathAccess(this.uri, getAuthData())) {
                final SubProgress mon = SubProgress.convert(monitor, 2);
                final String progressLabel = CoreProject.I18N.getMessage("RamcFileFragment.ExtractRamcFrom", getId(), this.uri);
                mon.subTask(progressLabel);
        
                new Unzipper()
                .setProgressLabelPrefix(progressLabel)
                .unzip(acc.getPath(), localRamcPath, mon.newChild(1));
        
                // Deploy exported files
                mon.subTask(CoreProject.I18N.getMessage("RamcFileFragment.DeployRamcFiles", getId(), this.uri));
                new ModelComponentArchive(localRamcPath, false)
                .installExportedFiles(getProject().getProjectFileStructure().getProjectPath(), mon.newChild(1));
        
                return localRamcPath;
            } catch (final MalformedURLException e1) {
                final String msg = CoreProject.I18N.getMessage("RamcFileFragment.InvalidUri", this.uri, e1.getLocalizedMessage());
                throw new IOException(msg, e1);
            } catch (final IllegalArgumentException e1) {
                final String msg = CoreProject.I18N.getMessage("RamcFileFragment.InvalidUri", this.uri, e1.getLocalizedMessage());
                throw new IOException(msg, e1);
            }
        }
        
    }

    /**
     * Delete other files deployed with the RAMC such as extern libraries
     * or source files.
     * @param monitor a progress monitor
     * @throws IOException in case of failure.
     */
    @objid ("85eb632b-e430-41b1-8852-4e6635050d84")
    private void removeExportedFilesOfFragment(final IModelioProgress monitor) throws IOException {
        final ModelComponentArchive modelComponentArchive = new ModelComponentArchive(getContentDirectory(), false);
        final Path deploymentPath = getProject().getProjectFileStructure().getProjectDataPath();
        modelComponentArchive.removeExportedFiles(deploymentPath, monitor);
        
    }

    /**
     * Get the directory where the RAMC archive is extracted.
     * @return the extracted RAMC directory.
     */
    @objid ("0614b2bc-cefb-436d-9be2-84630005c75e")
    private Path getContentDirectory() {
        return getDataDirectory().resolve("content");
    }

    @objid ("39044ea2-7a39-47c3-b400-404b03190ea0")
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

    @objid ("e420b280-dd1d-4461-8997-22577d535d18")
    @Override
    public void rename(final String name, final IModelioProgress aMonitor) throws IOException {
        throw new UnsupportedOperationException();
    }

}
