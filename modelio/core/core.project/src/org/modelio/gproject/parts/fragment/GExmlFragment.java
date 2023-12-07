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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.FragmentAuthenticationException;
import org.modelio.gproject.FragmentMigrationNeededException;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.gproject.migration.FragmentMigratorWithBackup;
import org.modelio.gproject.parts.IGModelFragmentMigrator;
import org.modelio.gproject.parts.fragment.migration.ChainedExmlFragmentMigrator;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.permission.BasicAccessManager;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.version.ModelioVersion;
import org.modelio.vstore.exml.common.RepositoryVersions;
import org.modelio.vstore.exml.local.ExmlBase;
import org.modelio.vstore.exml.resource.IExmlRepositoryGeometry;
import org.modelio.vstore.exml.resource.LocalExmlResourceProvider;

/**
 * Local EXML repository fragment.
 */
@objid ("a24e1936-3b42-4fdf-867f-7f5ca470cd4a")
public class GExmlFragment extends AbstractGModelFragment {
    @objid ("94636919-5183-4107-a057-301536b707c7")
    private ExmlBase repository;

    /**
     * Initialize the RAMC fragment.
     * @param desc the part descriptor
     */
    @objid ("c62cd13d-8038-441a-8d5c-aa22e8597c06")
    public  GExmlFragment(GProjectPartDescriptor desc) {
        super(desc);
    }

    @objid ("07c2299b-4f37-43c4-b8aa-3885ad7eff5d")
    @Override
    public GProjectPartDescriptor getDescriptor() {
        return super.getDescriptor();
    }

    @objid ("b385f09a-bcb8-4111-9ed8-68f6e9a72bbd")
    @Override
    public IRepository getRepository() {
        return this.repository;
    }

    @objid ("cd69ed52-cc13-4ad5-849f-f194b4cfe4fa")
    @Override
    protected IRepository doMountInitRepository(IGProject project, IModelioProgress aMonitor) throws IOException, FragmentAuthenticationException {
        final Path location = getDataDirectory();
        
        final LocalExmlResourceProvider resProvider = createResourceProvider();
        
        this.repository = new ExmlBase(resProvider);
        
        // Create the project structure if new fragment
        if (!Files.isDirectory(location)) {
            this.repository.create(project.getSession().getMetamodel());
        
            // Add metamodel version file
            saveMmVersion(getCurrentMmDescriptor());
        
        }
        return this.repository;
    }

    /**
     * Instantiate a configured {@link IRepository}.
     * @param session unused.
     * @return a ready repository.
     * @throws IOException on I/O failure
     * @throws FragmentAuthenticationException on authentication error.
     */
    @objid ("3f731dc9-f75a-4dad-a008-86de08771986")
    public IRepository instantiateRepository(ICoreSession session) throws IOException, FragmentAuthenticationException {
        final LocalExmlResourceProvider resProvider = createResourceProvider();
        
        final ExmlBase ret = new ExmlBase(resProvider);
        
        final Path location = getDataDirectory();
        if (! Files.isDirectory(location)) {
            throw new NoSuchFileException(location.toString());
        }
        return ret;
    }

    @objid ("45ce3f2e-81c6-4fb5-91c0-516a1ea6c7af")
    private void saveMmVersion(final MetamodelVersionDescriptor mmVersion) throws IOException {
        final Path mmVersionPath = getMmVersionPath();
        Files.createDirectories(mmVersionPath.getParent());
        
        try (Writer out = Files.newBufferedWriter(mmVersionPath, StandardCharsets.UTF_8)) {
            mmVersion.write(out);
        }
        
    }

    /**
     * @return the file containing the repository's metamodel version.
     */
    @objid ("bfa6a2cb-ecb1-4c93-b9ac-dbbe628cafff")
    protected Path getMmVersionPath() {
        return getDataDirectory().resolve(IExmlRepositoryGeometry.ADMIN_DIRNAME).resolve(MMVERSION_FILE_NAME);
    }

    /**
     * Instantiate an EXML resources provider for this fragment.
     * @return a new EXML resources provider for this fragment.
     */
    @objid ("ba15ed42-1c9d-4beb-bb82-0624a8a8a867")
    protected LocalExmlResourceProvider createResourceProvider() {
        return new LocalExmlResourceProvider(getDataDirectory(), getRuntimeDirectory(), getId());
    }

    @objid ("d8ebe886-7014-4898-a025-cb2d9d4210b3")
    @Override
    protected IAccessManager doInitAccessManager() {
        final BasicAccessManager ret = new BasicAccessManager();
        if (isReadOnly()) {
            ret.setWriteable(false);
        }
        return ret;
    }

    @objid ("1016a327-5992-4cf4-a2d5-cd372e6b1f72")
    protected boolean isReadOnly() {
        return !getAccessRights().isEditable();
    }

    @objid ("6ee1ec76-747b-4ed3-a2f9-a068e7de80b1")
    @Override
    protected Collection<MObject> doGetRoots() throws IOException {
        final SmMetamodel mm = getProjectMetamodel();
        final Collection<MObject> roots = this.repository.findByClass(mm.getMClass(AbstractProject.class), true);
        return roots;
    }

    @objid ("4b7eab3d-3e6c-4e32-b49b-bc2d04a6a803")
    @Override
    protected void doUmountPostProcess(IGProject project, IModelioProgress monitor) {
        this.repository = null;
    }

    @objid ("a8a69579-4acb-4729-a377-60ab3d8026e6")
    @Override
    public IFragmentInfos getInformations() throws IOException {
        final Path infosuri = getDataDirectory().resolve("manifest.properties");
        try (InputStream is = Files.newInputStream(infosuri);
                InputStreamReader in = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            final Properties p = new Properties();
            p.load(in);
            return new ExmlFragmentInfos(p.getProperty("name"), p.getProperty("description"), new Version(p.getProperty("version")), new Version(p.getProperty("modelioversion")));
        } catch (FileNotFoundException | NoSuchFileException e) {
            Log.warning("No '" + infosuri + "' infos version file, use default values.");
            return new ExmlFragmentInfos(getId(), "", new Version("0.0.0"), ModelioVersion.VERSION);
        }
        
    }

    @objid ("52b718a7-9558-4751-bde2-69fcfaf1538a")
    @Override
    public MetamodelVersionDescriptor getRequiredMetamodelDescriptor() throws IOException {
        final Path p = getMmVersionPath();
        
        try (BufferedReader in = Files.newBufferedReader(p, StandardCharsets.UTF_8);) {
            return VersionHelper.convert(new MetamodelVersionDescriptor(in));
        } catch (FileNotFoundException | NoSuchFileException e) {
            // Assume Modelio 3.1 MM version
            Log.warning("No '" + p + "' metamodel version file. Assume Modelio 3.1 (9020) metamodel version.");
            final MetamodelVersionDescriptor guessed = VersionHelper.getDescriptors(9020);
            return guessed;
        }
        
    }

    @objid ("84de4c95-ac3d-41f8-9346-935892291b53")
    @Override
    protected void doInstall(IGProject aProject, IModelioProgress monitor) throws GPartException {
        // Do nothing
    }

    @objid ("23fa4311-a49a-4ba2-ad5b-644a7dfb94cc")
    @Override
    protected void doUninstall(IGProject aProject, IModelioProgress monitor) throws GPartException {
        // Do nothing
    }

    @objid ("8cd45763-a710-4995-a76a-c64cf561e614")
    @Override
    protected void checkVersions() throws FragmentMigrationNeededException, IOException {
        super.checkVersions();
        
        final RepositoryVersions repoVersion = this.repository.getResourceProvider().readRepositoryVersion();
        if (repoVersion.getRepositoryFormat() < 2) {
            throw new FragmentMigrationNeededException(
                    this,
                    getRequiredMetamodelDescriptor(),
                    CoreProject.I18N.getMessage("ExmlFragment.RepositoryFormatNeedMigration",
                            getId(),
                            repoVersion.getRepositoryFormat(),
                            RepositoryVersions.CURRENT_FORMAT));
        }
        
        // If we reach this line no exception is thrown and no migration is needed.
        if (!isReadOnly()) {
            final MetamodelVersionDescriptor neededMm = getRequiredMetamodelDescriptor();
            final MetamodelVersionDescriptor currentMm = getCurrentMmDescriptor();
        
            if (! neededMm.isSame(currentMm)) {
                // save the metamodel version
                saveMmVersion(currentMm);
            }
        }
        
    }

    @objid ("f5578c8c-d67a-4904-81ab-8d5480df25ee")
    @Override
    public IGModelFragmentMigrator getMigrator(final MetamodelVersionDescriptor targetMetamodel) throws IOException {
        final LocalExmlResourceProvider resProvider = createResourceProvider();
        
        final ChainedExmlFragmentMigrator chainedMigrator = new ChainedExmlFragmentMigrator(
                getProject(),
                this,
                getMmVersionPath(),
                targetMetamodel,
                resProvider);
        
        if (chainedMigrator.getMigrationChain().isSuccessful()) {
            return new FragmentMigratorWithBackup(this, chainedMigrator);
        } else {
            return null;
        }
        
    }

    /**
     * EXML fragment informations.
     */
    @objid ("66cc43af-b554-4462-b682-0a28f28f6ed3")
    public static class ExmlFragmentInfos implements IFragmentInfos {
        @objid ("ccd1be37-bd5b-432e-a235-b0b8b16701c9")
        private final String description;

        @objid ("286b64be-55d9-4e18-8872-d61aa9cac934")
        private final String name;

        @objid ("d56bba01-ffe8-4fbb-9c41-b41736c7bf58")
        private final Version version;

        @objid ("f81fbc0e-2bdc-477a-b288-0cb1b9d50357")
        private final Version modelioVersion;

        @objid ("271cae31-b816-4f66-83c2-fb8e9ecf93d3")
        public  ExmlFragmentInfos(final String name, final String description, final Version version, final Version modelioVersion) {
            super();
            this.description = description;
            this.name = name;
            this.version = version;
            this.modelioVersion = modelioVersion;
            
        }

        @objid ("37315087-3cfd-46bd-aca2-0beefeb8aaad")
        @Override
        public String getDescription() {
            return this.description;
        }

        @objid ("3236ab3c-e716-4523-bfcf-ee7deb25e270")
        @Override
        public Version getModelioVersion() {
            return this.modelioVersion;
        }

        @objid ("49042aeb-5e29-43dd-8e41-0eccf4e66a17")
        @Override
        public String getName() {
            return this.name;
        }

        @objid ("2ce0e2c8-668a-4e8b-8db2-aa30954ddea8")
        @Override
        public Version getVersion() {
            return this.version;
        }

    }

}
