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
package org.modelio.gproject.parts.fragment.migration;

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
import org.modelio.gproject.FragmentAuthenticationException;
import org.modelio.gproject.MigrationFailedException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.migration.ChainedMofFragmentMigrator;
import org.modelio.gproject.migration.FileFlags;
import org.modelio.gproject.parts.fragment.GExmlFragment;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.AbstractMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMigrationStepDescription;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigratorProvider;
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
@objid ("fbbd078a-546a-42ec-b6f1-444f182b9539")
public class ChainedExmlFragmentMigrator extends ChainedMofFragmentMigrator {
    @objid ("60f28977-d5fb-4b9e-a5a0-5754dda17e0a")
    private final int srcRepositoryFormat;

    @objid ("5d8b8270-eec3-47c6-8eaa-7b56fd4d3a14")
    private final GExmlFragment exmlFragment;

    @objid ("f40f2bf0-af3d-48e0-a5db-b4b3e88ef005")
    private final Path mmVersionPath;

    @objid ("fda953cf-c4f5-42ee-b540-d325ac809d7a")
    private final MetamodelVersionDescriptor targetVersion;

    @objid ("e1d6f129-f230-4bb8-a0fd-3948e9ba432e")
    private final ExmlResource mmDescriptorResource;

    /**
     * @param project the project
     * @param exmlFragment the fragment to migrate
     * @param mmVersionPath the metamodel version file path
     * @param targetVersion the target metamodel version descriptor to write at the end
     * @throws IOException on failure reading the fragment metamodel version
     */
    @objid ("4627bd7f-9526-4051-abd5-cd6827ddbc17")
    public  ChainedExmlFragmentMigrator(IGProject project, GExmlFragment exmlFragment, Path mmVersionPath, MetamodelVersionDescriptor targetVersion, LocalExmlResourceProvider resProvider) throws IOException {
        super(project, exmlFragment, exmlFragment::instantiateRepository);
        this.exmlFragment = exmlFragment;
        this.mmVersionPath = mmVersionPath;
        this.mmDescriptorResource = resProvider.getMetamodelDescriptorResource();
        this.targetVersion = targetVersion;
        this.srcRepositoryFormat = resProvider.readRepositoryVersion().getRepositoryFormat();
        
    }

    @objid ("53fc6435-fb85-47c1-b599-72d68a49593e")
    @Override
    public void migrateModel(IModelioProgress monitor) throws MigrationFailedException, FragmentAuthenticationException {
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

    @objid ("0b1121bc-2da9-41e6-a86f-ae1ea7a0b39c")
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

    @objid ("e0100e0b-76a8-49a3-948e-ed23203032ca")
    @Override
    protected void doStart(IModelioProgress monitor) throws FragmentAuthenticationException, MigrationFailedException {
        super.doStart(monitor);
        
        try {
            new FileFlags(this.exmlFragment).putMigrationFlag("migrator", getClass().getName());
        
        } catch (RuntimeException e) {
            e.printStackTrace(getMigrationReporter().getLogger());
            throw new MigrationFailedException(CoreProject.I18N.getMessage(
                    "ChainedExmlFragmentMigrator.MigrationFailed",
                    this.exmlFragment.getId(),
                    e.toString()), e);
        } catch (IOException e) {
            throw handleIOException( e);
        }
        
    }

    @objid ("af9d4200-a792-448c-b982-0f93a3601b66")
    @Override
    protected void postMofMigration(Supplier<SubProgress> mon, MofSession migrationSession, IMofRepositoryMigrator mofMigrator) throws MofMigrationException {
        try {
            handleCmsNodeChanges(mon, migrationSession, mofMigrator);
        } catch (MetaclassNotFoundException e) {
            throw new MofMigrationException(e.getLocalizedMessage(), e);
        }
        
    }

    @objid ("a3ca9d06-5d73-467d-915a-19e5794228e8")
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

    @objid ("176f0e49-fdd3-46ba-a10f-7823a12d0fbe")
    @Override
    protected MigrationChain resolveMigrationChain(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel, Collection<IMofRepositoryMigratorProvider> migrationProviders) {
        MigrationChain ret = super.resolveMigrationChain(fromMetamodel, targetMetamodel, migrationProviders);
        if (isFormatChangeNeeded() && ret.isNoopMigrationChain()) {
            // Add a dummy provider to force repository format migration
            return ret.add(new RepoFormatMigrationNeededMigrator(fromMetamodel, targetMetamodel));
        } else {
            return ret;
        }
        
    }

    @objid ("e3ff7e7e-0eee-4f73-85d1-f56a6b60e1e4")
    private GExmlFragment getExmlFragment() {
        return this.exmlFragment;
    }

    /**
     * Handle CMS node metaclass changes.
     * @param mmchanges the metamodel change descriptor
     */
    @objid ("41922abb-45b4-4fb6-96d0-7a12a8f0459c")
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

    @objid ("fa9b4200-ae85-4cd4-b824-eb78a296db36")
    private MigrationFailedException handleIOException(IOException e) {
        e.printStackTrace(getMigrationReporter().getLogger());
        return new MigrationFailedException(CoreProject.I18N.getMessage("ChainedExmlFragmentMigrator.MigrationFailed",
                                                                                this.exmlFragment.getId(),
                                                                                FileUtils.getLocalizedMessage(e)), e);
        
    }

    @objid ("cd6c3c3b-e45d-49a6-b540-e550c25af550")
    private boolean isFormatChangeNeeded() {
        return this.srcRepositoryFormat < RepositoryVersions.CURRENT_FORMAT;
    }

    @objid ("284fd176-422b-4515-abff-f6f006d07c51")
    private void migrateRepositoryFormat(IModelioProgress monitor, PrintWriter logger) throws IOException, FragmentAuthenticationException, MofMigrationException {
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
     * @throws IOException in case of I/O failure
     */
    @objid ("1f325235-789b-40a8-9ea2-5ae530378d0e")
    private void writeMmVersion() throws IOException, MofMigrationException {
        try (Writer out = Files.newBufferedWriter(this.mmVersionPath, StandardCharsets.UTF_8)) {
            this.targetVersion.write(out);
        }
        
        // Force the metamodel descriptor because at this state
        // the repository MM descriptor may contain old metamodels elements.
        writeMetamodelDescriptor(getFinalMergedMmDescriptor(getMigrationReporter()));
        
    }

    @objid ("d1c248fb-17f3-4c73-a894-3d8a43cfb50a")
    private void writeMetamodelDescriptor(MetamodelDescriptor finalMergedMmDescriptor) throws IOException {
        try (OutputStream out = this.mmDescriptorResource.bufferedWrite()){
            new MetamodelDescriptorWriter().write(finalMergedMmDescriptor, out);
        }
        
    }

    @objid ("20e39e43-c419-4386-bd5e-e285cbb124bc")
    @Override
    protected List<IMigrationStepDescription> computeStepsDescription() {
        try {
            List<IMigrationStepDescription> ret;
            if (getMigrationChain().isNoopMigrationChain()) {
                // No migration step needed
                ret = new ArrayList<>();
                ret.add(new MigrationStepDescription(CoreProject.I18N.getMessage(
                        "ChainedExmlFragmentMigrator.SaveMmVersionNeeded",
                        this.exmlFragment.getId())));
            } else {
                ret = super.computeStepsDescription();
            }
        
            if (isFormatChangeNeeded()) {
                ret.add(new MigrationStepDescription(CoreProject.I18N.getMessage(
                        "GExmlFragment.RepositoryFormatNeedMigration",
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
    @objid ("556bdf5c-1662-4062-a3c5-651b39859a11")
    private static final class RepoFormatMigrationNeededMigrator extends AbstractMofRepositoryMigrator {
        @objid ("c3aab746-4c66-4067-a6a2-500bc4e65239")
        public  RepoFormatMigrationNeededMigrator(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel) {
            super(fromMetamodel, targetMetamodel);
        }

    }

}
