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

package org.modelio.gproject.fragment.exml.migration;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.fragment.FragmentAuthenticationException;
import org.modelio.gproject.fragment.exml.ExmlFragment;
import org.modelio.gproject.fragment.migration.ChainedMofFragmentMigrator;
import org.modelio.gproject.fragment.migration.FileFlags;
import org.modelio.gproject.fragment.migration.MigrationFailedException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.AbstractMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMigrationProvider;
import org.modelio.vcore.model.spi.mm.IMigrationStepDescription;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.model.spi.mm.MetamodelChangeDescriptor;
import org.modelio.vcore.model.spi.mm.MigrationChain;
import org.modelio.vcore.model.spi.mm.MigrationStepDescription;
import org.modelio.vcore.model.spi.mm.MofMigrationException;
import org.modelio.vcore.model.spi.mm.MofSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorWriter;
import org.modelio.vstore.exml.common.AbstractExmlRepository;
import org.modelio.vstore.exml.common.RepositoryVersions;
import org.modelio.vstore.exml.resource.IExmlResourceProvider.ExmlResource;
import org.modelio.vstore.exml.resource.LocalExmlResourceProvider;
import org.modelio.vstore.exml.resource.migration.MigratorFrom1To2;

/**
 * Chained MOF migrator that also handles SVN locks and commits.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("b71044f6-872b-42aa-b9b4-8dd446259dc2")
public class ChainedExmlFragmentMigrator extends ChainedMofFragmentMigrator {
    @objid ("edec7292-71cb-4f47-97b3-6244271795c0")
    private final int srcRepositoryFormat;

    @objid ("890b9de1-397b-48f6-a375-16a457eac9e4")
    private final ExmlFragment exmlFragment;

    @objid ("2cec65bb-75b5-4b96-a246-5cefd89743a7")
    private final Path mmVersionPath;

    @objid ("ee36c0d2-5e61-48c5-abbc-2e335ad166de")
    private final MetamodelVersionDescriptor targetVersion;

    @objid ("50a76402-da77-4614-a308-383520d4ec3a")
    private final ExmlResource mmDescriptorResource;

    /**
     * @param formatMigrationNeeded
     * @param project the project
     * @param exmlFragment the fragment to migrate
     * @param mmVersionPath the metamodel version file path
     * @param targetVersion the target metamodel version descriptor to write at the end
     * @throws java.io.IOException on failure reading the fragment metamodel version
     */
    @objid ("62eac4a6-794c-4044-8926-781613d77e41")
    public ChainedExmlFragmentMigrator(GProject project, ExmlFragment exmlFragment, Path mmVersionPath, MetamodelVersionDescriptor targetVersion, LocalExmlResourceProvider resProvider) throws IOException {
        super(project, exmlFragment, exmlFragment::instantiateRepository);
        this.exmlFragment = exmlFragment;
        this.mmVersionPath = mmVersionPath;
        this.mmDescriptorResource = resProvider.getMetamodelDescriptorResource();
        this.targetVersion = targetVersion;
        this.srcRepositoryFormat = resProvider.readRepositoryVersion().getRepositoryFormat();
    }

    @objid ("da613c91-8ac7-442d-bc36-609e498ec747")
    @Override
    public void migrateModel(IModelioProgress monitor) throws FragmentAuthenticationException, MigrationFailedException {
        SubProgress mon = SubProgress.convert(monitor, isFormatChangeNeeded() ? 2 : 1);
        
        try {
            // Repository format change
            if (isFormatChangeNeeded()) {
                migrateRepositoryFormat(mon.newChild(1), getMigrationReporter().getLogger());
            }
        
            super.migrateModel(mon.newChild(1));
        
            // Write mm version and descriptor
            writeMmVersion();
        
        } catch (IOException e) {
            throw handleIOException( e);
        } catch (MofMigrationException e) {
            throw new MigrationFailedException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("e4487b81-9c70-4698-bea2-d326084fa4d3")
    @Override
    protected void doFinish(IModelioProgress monitor) throws MigrationFailedException {
        super.doFinish(monitor);
        try {
        
            new FileFlags(getExmlFragment()).removeMigrationFlag("migrator");
        
            getMigrationReporter().getLogger().println("'"+getExmlFragment().getId()+"' migration sucessful.");
        } catch (IOException e) {
            throw handleIOException( e);
        }
    }

    @objid ("dd816cf0-8fec-49ba-bf9d-a784f8dde400")
    @Override
    protected void doStart(IModelioProgress monitor) throws FragmentAuthenticationException, MigrationFailedException {
        super.doStart(monitor);
        
        try {
            new FileFlags(this.exmlFragment).putMigrationFlag("migrator", getClass().getName());
        
        } catch (RuntimeException e) {
            e.printStackTrace(getMigrationReporter().getLogger());
            throw new MigrationFailedException(CoreProject.getMessage(
                    "ChainedExmlFragmentMigrator.MigrationFailed",
                    this.exmlFragment.getId(),
                    e.toString()), e);
        } catch (IOException e) {
            throw handleIOException( e);
        }
    }

    @objid ("5b9db5c3-101c-4b6a-8a4f-c441d9dac7fe")
    @Override
    protected void postMofMigration(Supplier<SubProgress> mon, MofSession migrationSession, IMofRepositoryMigrator mofMigrator) throws MofMigrationException {
        try {
            handleCmsNodeChanges(mon, migrationSession, mofMigrator);
        } catch (MetaclassNotFoundException e) {
            throw new MofMigrationException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("c7d96910-6fd6-497b-be55-e072aae53133")
    @Override
    protected void preMofMigration(Supplier<SubProgress> mon, MofSession migrationSession, IMofRepositoryMigrator mofMigrator) throws MofMigrationException {
        getMigrationReporter().getLogger().println("Rebuilding '"+this.exmlFragment.getId()+"' indexes...");
        
        try {
            final AbstractExmlRepository repository = (AbstractExmlRepository) migrationSession.getTargetRepository();
        
            // Ensure all directories exist
            repository.getResourceProvider().updateRepositoryStructure(getProject().getSession().getMetamodel());
        
            // Rebuild indexes from scratch to handle updates and by precaution
            repository.getMaintenance().rebuildIndexes(mon.get());
        } catch (IOException e) {
            throw new MofMigrationException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    @objid ("bf63deb3-6bbb-41d2-9dca-6cf202a1a6a5")
    @Override
    protected MigrationChain resolveMigrationChain(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel, Collection<IMigrationProvider> migrationProviders) {
        MigrationChain ret = super.resolveMigrationChain(fromMetamodel, targetMetamodel, migrationProviders);
        if (isFormatChangeNeeded() && ret.isNoopMigrationChain()) {
            // Add a dummy provider to force repository format migration
            return ret.add(new RepoFormatMigrationNeededMigrator(fromMetamodel, targetMetamodel));
        } else {
            return ret;
        }
    }

    @objid ("e897553b-410b-416d-97e0-95f39b0b86c7")
    private ExmlFragment getExmlFragment() {
        return this.exmlFragment;
    }

    /**
     * Handle CMS node metaclass changes.
     * @param monitorSupplier
     * @param mofMigrator
     * @param repository
     * @param session
     * @param mmchanges the metamodel change descriptor
     * @throws MetaclassNotFoundException
     */
    @objid ("c66fff5d-8381-4586-92f4-1e5b7037385d")
    private void handleCmsNodeChanges(Supplier<SubProgress> monitorSupplier, MofSession migrationSession, IMofRepositoryMigrator mofMigrator) throws MetaclassNotFoundException {
        MetamodelChangeDescriptor mmchanges = mofMigrator.getMetamodelChanges();
        if (! (mmchanges.getAddedCmsNodes().isEmpty() && mmchanges.getRemovedCmsNodes().isEmpty())) {
            SubProgress mon = monitorSupplier.get();
            int totalWork = mmchanges.getAddedCmsNodes().size() + mmchanges.getRemovedCmsNodes().size();
            mon.beginTask("", totalWork);
        
            for (MClassRef classRef : mmchanges.getAddedCmsNodes()) {
                SmClass mClass = migrationSession.getMetamodel().getMClass(classRef.getQualifiedName());
                if (mClass != null) {
                    for (MObject obj : migrationSession.findByClass(mClass, false)) {
                        if (obj.isModifiable()) {
                            // regenerate object
                            getMigrationReporter().getLogger().println("CMS node change:  + '"+obj+"' is a new CMS node.");
                            migrationSession.replace(obj, mClass, null, true);
                        }
                    }
                }
                mon.worked(1);
            }
        
            for (MClassRef classRef : mmchanges.getRemovedCmsNodes()) {
                SmClass mClass = migrationSession.getMetaclass(classRef.getQualifiedName());
                for (MObject obj : migrationSession.findByClass(mClass, false)) {
                    if (obj.isModifiable()) {
                        // regenerate object
                        getMigrationReporter().getLogger().println("CMS node change:  + '"+obj+"' is a new CMS node.");
                        migrationSession.replace(obj, mClass, null, true);
                    }
                }
                mon.worked(1);
            }
            
            getMigrationReporter().getLogger().println("'"+this.exmlFragment.getId()+"' CMS nodes rewritten");
        }
    }

    @objid ("86f48317-b926-4b32-9c9e-9718ed4695b3")
    private MigrationFailedException handleIOException(IOException e) {
        e.printStackTrace(getMigrationReporter().getLogger());
        return new MigrationFailedException(CoreProject.getMessage("ChainedExmlFragmentMigrator.MigrationFailed",
                                                                                this.exmlFragment.getId(),
                                                                                FileUtils.getLocalizedMessage(e)), e);
    }

    @objid ("0b9c904f-4aa3-4a00-86f4-bf34b8c18f51")
    private boolean isFormatChangeNeeded() {
        return this.srcRepositoryFormat < RepositoryVersions.CURRENT_FORMAT;
    }

    @objid ("b23127d8-1e11-4d4c-8ac6-7c2f1b428bdc")
    private void migrateRepositoryFormat(IModelioProgress monitor, PrintWriter logger) throws FragmentAuthenticationException, IOException, MofMigrationException {
        if (isFormatChangeNeeded()) {
            SubProgress mon = SubProgress.convert(monitor, 10);
            
            IMofSession sess = prepareMofSession(mon.newChild(1), 0);
            try {
                new MigratorFrom1To2(
                        this.exmlFragment.getDataDirectory(), 
                        sess.getMetamodel(), 
                        logger)
                .execute(mon.newChild(9));
            } finally {
                sess.getCoreSession().close();
            }
        }
    }

    /**
     * Write the new metamodel version.
     * @throws MofMigrationException
     * @throws java.io.IOException in case of I/O failure
     */
    @objid ("5e73c73f-c307-450c-9be3-116ce67f0468")
    private void writeMmVersion() throws IOException, MofMigrationException {
        try (Writer out = Files.newBufferedWriter(this.mmVersionPath, StandardCharsets.UTF_8)) {
            this.targetVersion.write(out);
        }
        
        // Force the metamodel descriptor because at this state
        // the repository MM descriptor may contain old metamodels elements.
        writeMetamodelDescriptor(getFinalMergedMmDescriptor(getMigrationReporter()));
    }

    @objid ("fb8e3a4a-07c5-4378-9fda-185909821d7c")
    private void writeMetamodelDescriptor(MetamodelDescriptor finalMergedMmDescriptor) throws IOException {
        try (OutputStream out = this.mmDescriptorResource.bufferedWrite()){
            new MetamodelDescriptorWriter().write(finalMergedMmDescriptor, out);
        }
    }

    @objid ("604cfee4-68fa-4ea9-9fe7-4ee4cc5c93c1")
    @Override
    protected List<IMigrationStepDescription> computeStepsDescription() {
        try {
            List<IMigrationStepDescription> ret;
            if (getMigrationChain().isNoopMigrationChain()) {
                // No migration step needed
                ret = new ArrayList<>();
                ret.add(new MigrationStepDescription(CoreProject.getMessage(
                        "ChainedExmlFragmentMigrator.SaveMmVersionNeeded", 
                        this.exmlFragment.getId())));
            } else {
                ret = super.computeStepsDescription();
            }
            
            if (isFormatChangeNeeded()) {
                ret.add(new MigrationStepDescription(CoreProject.getMessage(
                        "ExmlFragment.RepositoryFormatNeedMigration", 
                        this.exmlFragment.getId(),
                        this.srcRepositoryFormat,
                        RepositoryVersions.CURRENT_FORMAT)));
        
            }
            
            return ret;
        } catch (IOException e) {
            // ignore and fallback to super().
        }
        return super.computeStepsDescription();
    }

    /**
     * Dummy migrator to force repository format migration.
     * @author cma
     * @since 3.7.1
     */
    @objid ("cb03b356-69d3-457a-b6a0-ea4e025121ea")
    private static final class RepoFormatMigrationNeededMigrator extends AbstractMofRepositoryMigrator {
        @objid ("534814b7-f5ae-40e2-8df1-43e0267826ae")
        public RepoFormatMigrationNeededMigrator(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel) {
            super(fromMetamodel, targetMetamodel);
        }

    }

}
