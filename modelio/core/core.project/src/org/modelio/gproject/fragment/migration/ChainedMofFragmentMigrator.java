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

package org.modelio.gproject.fragment.migration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.fragment.AbstractFragment;
import org.modelio.gproject.fragment.FragmentAuthenticationException;
import org.modelio.gproject.fragment.VersionHelper;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.IBasicLogger;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.vcore.model.spi.mm.IMigrationProvider;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;
import org.modelio.vcore.model.spi.mm.IMigrationStepDescription;
import org.modelio.vcore.model.spi.mm.IMofRepositoryMigrator;
import org.modelio.vcore.model.spi.mm.MetamodelChangeDescriptor;
import org.modelio.vcore.model.spi.mm.MigrationChain;
import org.modelio.vcore.model.spi.mm.MigrationChainResolver;
import org.modelio.vcore.model.spi.mm.MigrationStepDescription;
import org.modelio.vcore.model.spi.mm.MmVersionComparator;
import org.modelio.vcore.model.spi.mm.MofMigrationException;
import org.modelio.vcore.model.spi.mm.MofSession;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.IRepositoryErrorListener;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.session.impl.CoreSessionBuilder;
import org.modelio.vcore.session.impl.permission.BasicAccessManager;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;

/**
 * Migrates a fragment by mounting a MOF metamodel on a temporary modeling session.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("a57d2b01-52ca-40f5-b1e0-05943c5805b8")
public class ChainedMofFragmentMigrator implements IFragmentMigrator, org.modelio.gproject.fragment.migration.IFragmentMigrator.IMigrationProcess {
    @objid ("9c8b8b55-39ca-4ab1-8b10-52e56e2e6a9b")
    private String userMessage;

    @objid ("2e156b56-900c-451e-8f3f-17cfa5930637")
    private boolean migrationChainComputed;

    /**
     * Whether {@link #finish(IModelioProgress)} or {@link #abort(IModelioProgress)} has been called.
     */
    @objid ("0cf4f906-250f-4e86-83dc-d67fef38f88c")
    private boolean terminalCalled;

    @objid ("5f79979b-52dd-4a78-b0c5-f1b3e49d74b8")
    private final AbstractFragment fragToMigrate;

    @objid ("3c427e23-ccb4-458b-9048-81e033a7f31c")
    private final List<IMigrationProvider> migrationCandidates;

    @objid ("b34e5be9-3fab-47c2-b7b0-534d872247c0")
    private MigrationChain migrationChain;

    @objid ("c9a84d27-6e67-4c49-ba2f-dae13d018389")
    private IMigrationReporter migrationReporter;

    @objid ("e196c1a9-c2ec-4918-a0d4-65ed0d2f2984")
    private final GProject project;

    @objid ("edbf1825-b741-47ad-b812-2fc1f9678905")
    private final RepositorySupplier repositoryFactory;

    @objid ("3c51acfa-428f-450e-b48b-8e853765781e")
    private IAccessManager migrationAccessManager;

    @objid ("fb78eeee-8e9b-47f0-abf8-b6ccd5d5ca2b")
    private IBasicLogger oldVCoreLogger;

    @objid ("b41e916e-07ae-4199-b4c5-02057819379a")
    private Optional<MetamodelDescriptor> initialMmDescriptor;

    @objid ("a04e3e56-9838-428d-9d00-f1ee191b634f")
    private MetamodelVersionDescriptor fromMmVersion;

    @objid ("58cb43b5-aa7d-40c4-9a25-a6e91d697d66")
    private MetamodelVersionDescriptor targetMmVersion;

    @objid ("e116422e-31cc-4d4c-9fd8-40f7df8c3756")
    private List<IMigrationStepDescription> stepsDescription;

    /**
     * @param project the project
     * @param fragToMigrate the fragment to migrate
     * @param repositoryFactory a way to instantiate a IRepository from the fragment.
     */
    @objid ("3822581e-1845-4aad-bc5f-a500869fdd54")
    public ChainedMofFragmentMigrator(GProject project, AbstractFragment fragToMigrate, RepositorySupplier repositoryFactory) {
        this.project = project;
        this.fragToMigrate = fragToMigrate;
        this.repositoryFactory = repositoryFactory;
        this.migrationAccessManager = new BasicAccessManager();
        this.migrationCandidates = new ArrayList<>();
    }

    /**
     * Add a MOF migration candidate.
     * 
     * @param migrator a MOF migration candidate.
     * @return this instance
     */
    @objid ("cf1b8ad6-cafb-48d4-9e74-6963658c149c")
    public final ChainedMofFragmentMigrator addCandidate(IMigrationProvider migrator) {
        this.migrationCandidates.add(migrator);
        return this;
    }

    /**
     * Get the migration reporter.
     * <p>
     * Works only once {@link #run(IModelioProgress, IMigrationReporter)} has been called.
     * 
     * @return the migration reporter.
     */
    @objid ("5ad975aa-6053-4f49-b632-30e5db683625")
    protected final IMigrationReporter getMigrationReporter() {
        return this.migrationReporter;
    }

    /**
     * Redefined as final.
     */
    @objid ("f47bbf51-ea51-496f-b947-f506d6d3fa65")
    @Override
    public final void run(IModelioProgress monitor, IMigrationReporter reporter) throws FragmentAuthenticationException, MigrationFailedException {
        IFragmentMigrator.super.run(monitor, reporter);
    }

    /**
     * Run the MOF migrator at the given index in the chain.
     * @param reporter
     * an object to report migration process and result to.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user.
     * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
     * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
     * @param migratorIndex the index of the migrator for {@link #getMigrationChainElement(int)}
     * @throws org.modelio.gproject.fragment.FragmentAuthenticationException on authentication error
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
     */
    @objid ("ee8e1870-716c-43cb-801b-3bb3d9d99988")
    protected final void runMofMigrator(IModelioProgress monitor, int migratorIndex) throws FragmentAuthenticationException, MigrationFailedException {
        SubProgress mon = SubProgress.convert(monitor, 5);
        IMofRepositoryMigrator mofMigrator = getMigrationChainElement(migratorIndex);
        
        MmVersionComparator vcomparator = MmVersionComparator
                .withSource(mofMigrator.getSourceMetamodel())
                .withTarget(mofMigrator.getTargetMetamodel())
                .withCommonRemoved()
                .withMissingSourcesRemoved();
        
        MetamodelVersionDescriptor mmDiff = vcomparator.getTarget();
        String msg = CoreProject.I18N.getMessage("ChainedMofFragmentMigrator.mon.migratingTowardVersion", this.fragToMigrate.getId(), mmDiff);
        mon.subTask(msg);
        
        this.migrationReporter.getLogger().format("\n\nMigrating '%s' from %s toward %s...\n------------------------------------\n", 
                this.fragToMigrate.getId(), vcomparator.getSource(), mmDiff);
        
        try {
            MofSession migrationSession = prepareMofSession(mon.newChild(1), migratorIndex);
            ICoreSession session = migrationSession.getCoreSession();
            try (ITransaction t = session.getTransactionSupport().createTransaction(msg);) {
        
                preMofMigration(mon.newChildSupplier(1), migrationSession, mofMigrator);
        
                mon.subTask(msg);
                mon.setWorkRemaining(5);
                this.migrationReporter.getLogger().println(" Running '" + mofMigrator.getClass().getSimpleName() + "' migrator ... ");
                mofMigrator.run(mon.newChild(3), migrationSession);
        
                mon.setWorkRemaining(4);
                mon.subTask(msg);
        
                postMofMigration(mon.newChildSupplier(2), migrationSession, mofMigrator);
        
                mon.setWorkRemaining(4);
                mon.subTask(msg);
                migrationSession.processScheduledReidentifications(mon.newChildSupplier(3));
        
                t.commit();
                session.save(mon);
            } finally {
                session.close();
            }
        
        } catch (MofMigrationException e) {
            throw new MigrationFailedException(e.getLocalizedMessage(), e);
        } catch (IOException e) {
            throw new MigrationFailedException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    @objid ("bc572b21-d12c-4f7a-b670-12116ec40665")
    private void computeMetamodelMigrators() throws IOException {
        for (IGMetamodelExtension extension : this.project.getEnvironment().getDefaultMetamodelExtensions()) {
            IMigrationProvider mofRepoMigrator = extension.createExtension(IMigrationProvider.class, this.project.getSession());
            if (mofRepoMigrator != null && !this.migrationCandidates.contains(mofRepoMigrator)) {
                this.migrationCandidates.add(mofRepoMigrator);
            }
        }
        
        this.fromMmVersion = this.fragToMigrate.getRequiredMetamodelDescriptor();
        this.targetMmVersion = VersionHelper.getDescriptors(this.project.getSession().getMetamodel());
        
        // Filter out new needed metamodel fragments
        // targetMetamodel.filter(old -> fromMetamodel.getVersion(old.getName()) != null);
        
        // Computes migration chain
        this.migrationChain = resolveMigrationChain(this.fromMmVersion, this.targetMmVersion, this.migrationCandidates);
        this.migrationChainComputed = true;
        
        // Compute detail message
        if (!this.migrationChain.isSuccessful()) {
            MmVersionComparator comp = MmVersionComparator
                    .withSource(this.fromMmVersion)
                    .withTarget(this.targetMmVersion)
                    .withCommonRemoved();
            this.userMessage = CoreProject.I18N.getMessage("ChainedMofFragmentMigrator.noMigrationChainFound", this.fragToMigrate.getId(), comp.getSource(), comp.getTarget());
            this.stepsDescription = Collections.emptyList();
        } else {
            this.userMessage = computeUserMessage();
            this.stepsDescription = computeStepsDescription();
        }
    }

    @objid ("86b63f6b-1356-4b2b-8dd4-a8c96c82ad32")
    private void prepareMetamodel(CoreSession session, int migratorIndex) throws MofMigrationException {
        MofMetamodel metamodel = (MofMetamodel) session.getMetamodel();
        MetamodelChangeDescriptor prevMmChanges = null;
        
        for (int i = this.migrationChain.getSteps().size() - 1; i >= migratorIndex; i--) {
            IMofRepositoryMigrator mofMigrator = getMigrationChainElement(i);
            mofMigrator.prepareMetamodel(metamodel);
            
            // Revert reported CMS node changes
            if (prevMmChanges != null) {
                for (MClassRef mcRef : prevMmChanges.getAddedCmsNodes()) {
                    MofSmClass mc = (MofSmClass) metamodel.getMClass(mcRef.getQualifiedName());
                    if (mc != null) {
                        mc.setIsCmsNode(false);
                    }
                }
        
                for (MClassRef mcRef : prevMmChanges.getRemovedCmsNodes()) {
                    MofSmClass mc = (MofSmClass) metamodel.getMClass(mcRef.getQualifiedName());
                    if (mc != null) {
                        mc.setIsCmsNode(true);
                    }
                }
            }
        
            prevMmChanges = mofMigrator.getMetamodelChanges();
        }
    }

    @objid ("868525d3-45ee-4188-990d-a88490786cc0")
    protected final GProject getProject() {
        return this.project;
    }

    /**
     * Redefine the access manager to use on the temporary repository.
     * @param migrationAccessManager
     */
    @objid ("5b9378ed-db95-48be-8225-a879fa3d25a7")
    protected void setMigrationAccessManager(IAccessManager migrationAccessManager) {
        this.migrationAccessManager = migrationAccessManager;
    }

    @objid ("674fcd6b-f030-48e4-ace9-70785161f158")
    protected IMofRepositoryMigrator getMigrationChainElement(int migratorIndex) {
        return this.migrationChain.getSteps().get(migratorIndex);
    }

    /**
     * Hook called before {@link IMofRepositoryMigrator#run(IModelioProgress, org.modelio.vcore.session.api.ICoreSession, IRepository)}.
     * <p>
     * May be redefined by subclasses to to some post processing.
     * 
     * @param mon a progress monitor with 2 ticks available.
     * @param migrationSession the migration session
     * @param mofMigrator the run migrator.
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on failure
     */
    @objid ("9dbcc505-5c3d-40e3-8ed9-60f3b58630d4")
    protected void preMofMigration(Supplier<SubProgress> mon, MofSession migrationSession, IMofRepositoryMigrator mofMigrator) throws MofMigrationException {
        // nothing by default.
    }

    /**
     * Hook called after {@link IMofRepositoryMigrator#run(IModelioProgress, org.modelio.vcore.session.api.ICoreSession, IRepository)}.
     * <p>
     * May be redefined by subclasses to to some post processing.
     * 
     * @param mon a progress monitor with 2 ticks available.
     * @param migrationSession the migration session
     * @param mofMigrator the run migrator.
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on failure
     */
    @objid ("d8c3cfc7-7d3b-4238-8fdf-c038f088a367")
    protected void postMofMigration(Supplier<SubProgress> mon, MofSession migrationSession, IMofRepositoryMigrator mofMigrator) throws MofMigrationException {
        // nothing by default.
    }

    /**
     * @return the computed migration chain.
     * @throws java.io.IOException on failure computing the migration chain
     */
    @objid ("67b0ffa3-1bd9-4920-85e9-9ca2315943f6")
    public final MigrationChain getMigrationChain() throws IOException {
        if (! this.migrationChainComputed) {
            computeMetamodelMigrators();
        }
        return this.migrationChain;
    }

    /**
     * Creates a {@link MofSession} connected to the repository to migrate.
     * 
     * @param monitor a progress monitor
     * @param migratorIndex the index of the migrators to use to prepare the metamodel.
     * All migrators from the last index to the given one will be called with {@link IMofRepositoryMigrator#prepareMetamodel(MofMetamodel)}.
     * If -1, no migrator will be called.
     * @return the ready MOF session.
     * @throws java.io.IOException in case of I/O error
     * @throws org.modelio.gproject.fragment.FragmentAuthenticationException in case of authentication error
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException in case of other error.
     */
    @objid ("88c169d6-99f2-4ab2-9339-94b65601a556")
    protected final MofSession prepareMofSession(IModelioProgress monitor, int migratorIndex) throws IOException, FragmentAuthenticationException, MofMigrationException {
        CoreSession session = new CoreSessionBuilder()
                .withMetamodel(this.project.getSession().getMetamodel())
                .forMetamodelMigration()
                .build();
        
        boolean ok = false;
        try {
            if (migratorIndex != -1) {
                prepareMetamodel(session, migratorIndex);
            }
        
            IRepository toMigrate = this.repositoryFactory.intantiateRepository(session);
            session.connectRepository(toMigrate, this.migrationAccessManager, monitor);
            toMigrate.getErrorSupport().addErrorListener(new RepositoryErrorListener());
        
            MofSession mofSession = new MofSession(session, toMigrate, getMigrationReporter());
            ok = true;
            return mofSession;
        } finally {
            if (!ok) {
                session.close();
            }
        }
    }

    @objid ("f623256d-2b20-46cf-87c6-ec30ccc75fb4")
    @Override
    public String getRequiredUserActions() {
        return this.userMessage;
    }

    /**
     * Run all MOF migrators in order.
     * 
     * @param monitor the progress monitor
     * @throws org.modelio.gproject.fragment.FragmentAuthenticationException on authentication error
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
     */
    @objid ("6f4ca4b3-25f1-47ad-97a1-353e53850681")
    protected final void runMofMigrators(IModelioProgress monitor) throws FragmentAuthenticationException, MigrationFailedException {
        int migrationChainSize = this.migrationChain.getSteps().size();
        SubProgress mon = SubProgress.convert(monitor, migrationChainSize);
        
        for (int i = 0; i < migrationChainSize; i++) {
            runMofMigrator(mon.newChild(1), i);
        }
    }

    /**
     * Compute a warning message to display to the user if needed.
     * @param fromMetamodel the source metamodel
     * @param targetMetamodel the target metamodel
     * 
     * @return a warning message or empty string, never <i>null</i>.
     */
    @objid ("65caaf3b-cbf5-4abd-ba79-ce0e7087d8cf")
    protected String computeUserMessage() {
        return "";
    }

    /**
     * Build a migration chain between the 2 given metamodels.
     * <p>
     * May be redefined.
     * 
     * @param fromMetamodel the source metamodel
     * @param targetMetamodelDesc the target metamodel
     * @param migrationProviders all known migration providers.
     * @return a chain of migration tools or {@link Optional#empty()} if no chain found.
     */
    @objid ("ba95586a-e2a3-4b74-8a73-7f3e519ecefa")
    protected MigrationChain resolveMigrationChain(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodelDesc, Collection<IMigrationProvider> migrationProviders) {
        return MigrationChainResolver.resolve(
                                        fromMetamodel,
                                        targetMetamodelDesc,
                                        migrationProviders);
    }

    /**
     * Prepare a {@link ICoreSession} connected to the repository to migrate with the final metamodel.
     * 
     * @param monitor a progress monitor
     * @return the ICoreSession
     * @throws org.modelio.gproject.fragment.FragmentAuthenticationException in case of authentication error
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException in case of failure
     */
    @objid ("cb1b86da-e5d1-452f-be6e-87597d2f6505")
    protected final ICoreSession prepareCoreSession(IModelioProgress monitor) throws FragmentAuthenticationException, MigrationFailedException {
        try {
            CoreSession session = new CoreSessionBuilder()
                    .withMetamodel(this.project.getSession().getMetamodel())
                    .build();
        
            boolean ok = false;
            try {
                IRepository toMigrate = this.repositoryFactory.intantiateRepository(session);
                session.connectRepository(toMigrate, this.migrationAccessManager, monitor);
                toMigrate.getErrorSupport().addErrorListener(new RepositoryErrorListener());
        
                ok = true;
                return session;
            } finally {
                if (!ok) {
                    session.close();
                }
            }
        } catch (IOException e) {
            throw new MigrationFailedException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    @objid ("50928092-28d6-492e-995b-948fcd3dacb0")
    protected final MetamodelVersionDescriptor getFinalVersionDescriptor() throws IOException {
        final List<IMofRepositoryMigrator> chain = getMigrationChain().getSteps();
        IMofRepositoryMigrator lastStep = chain.get(chain.size()-1);
        return lastStep.getTargetMetamodel();
    }

    @objid ("67a6dee1-a9ba-48c1-ae24-b235058f2827")
    @SuppressWarnings("unchecked")
    protected final <T extends AbstractFragment> T getFragmentToMigrate() {
        return (T) this.fragToMigrate;
    }

    @objid ("6e738e78-b063-4d0b-b58d-1aef57464634")
    protected final MetamodelDescriptor getFinalMergedMmDescriptor(IMigrationReporter reporter) throws IOException, MofMigrationException {
        Optional<MetamodelDescriptor> initMetamodelDesc = getInitialMetamodelDescriptor();
        MetamodelDescriptor targetMmDesc = getProject().getSession().getMetamodel().serialize();
        
        for (IMofRepositoryMigrator migrator : getMigrationChain().getSteps()) {
            migrator.completeFinalMetamodelDescriptor(targetMmDesc, reporter);
        }
        
        if (initMetamodelDesc.isPresent()) {
            MetamodelDescriptor mmd = initMetamodelDesc.get();
            for (VersionedItem<?> fragEntry : getFinalVersionDescriptor()) {
                targetMmDesc.getFragments().computeIfAbsent(
                        fragEntry.getName(), 
                        fragName -> mmd.getFragments().get(fragName));
            }
        }
        return targetMmDesc;
    }

    @objid ("e49fc15a-7362-4cf1-b54e-a09c4500874b")
    protected final Optional<MetamodelDescriptor> getInitialMetamodelDescriptor() {
        if (this.migrationReporter == null) {
            throw new IllegalStateException("start() not yet called.");
        }
        return this.initialMmDescriptor;
    }

    @objid ("c1c5f1b4-6ef7-4325-b03d-23cbdf7aa76d")
    @Override
    public final IMigrationProcess start(IModelioProgress monitor, IMigrationReporter reporter) throws FragmentAuthenticationException, MigrationFailedException {
        this.terminalCalled = false;
        
        // ensure migration chain has been computed
        try {
            getMigrationChain();
        } catch (IOException e) {
            throw new MigrationFailedException(FileUtils.getLocalizedMessage(e), e);
        }
        
        this.migrationReporter = reporter;
        
        String msg = CoreProject.I18N.getMessage("ChainedMofFragmentMigrator.mon.migration", this.fragToMigrate.getId());
        monitor.subTask(msg);
        monitor.setTaskName(msg);
        
        reporter.getLogger().println(msg);
        reporter.getLogger().println();
        reporter.getLogger().println(getRequiredUserActions());
        reporter.getLogger().println();
        reporter.getLogger().println(getMmDifferencesSummary());
        for (IMigrationStepDescription step : getStepsDescription()) {
            reporter.getLogger().append(" - ").append(step.getStepDescription()).println();
        }
        
        this.oldVCoreLogger = Log.getLogger();
        Log.setLogger(new NestedBasicLogger(this.oldVCoreLogger, reporter.getLogger()));
        
        // Read initial metamodel descriptor
        try {
            IRepository r = this.repositoryFactory.intantiateRepository(getProject().getSession());
            try{
                this.initialMmDescriptor = r.getMetamodelDescriptor();
            } finally {
                r.close();
            }
        } catch (IOException e) {
            throw new MigrationFailedException(FileUtils.getLocalizedMessage(e), e);
        }
        
        doStart( monitor);
        return this;
    }

    /**
     * Hook called by {@link #start(IModelioProgress, IMigrationReporter)} for sub classes.
     * <p>
     * Does nothing by default. May be redefined by sub classes.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user.
     * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
     * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
     * @throws org.modelio.gproject.fragment.FragmentAuthenticationException on authentication failure
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
     */
    @objid ("8f6b6253-0b9f-48e6-967e-49371e7c242f")
    protected void doStart(IModelioProgress monitor) throws FragmentAuthenticationException, MigrationFailedException {
        // nothing, to be redefined
    }

    @objid ("6357c8e9-ee23-435c-83c8-b5dfcba0719c")
    @Override
    public void migrateModel(IModelioProgress monitor) throws MigrationFailedException, FragmentAuthenticationException {
        runMofMigrators(monitor);
    }

    @objid ("50c52554-aeec-476b-931a-efa53502f907")
    @Override
    public final void finish(IModelioProgress monitor) throws MigrationFailedException {
        assert(this.terminalCalled == false);
        
        // Ensure the fragment is not mount
        this.fragToMigrate.unmount();
        
        // Call sub classes
        doFinish(monitor);
        
        // Record process finished
        this.terminalCalled = true;
    }

    /**
     * Hook called by {@link #finish(IModelioProgress)} for sub classes.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user.
     * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
     * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException to abort migration
     */
    @objid ("1937c5e1-969b-4e8a-b60d-ed06ce3dc3fc")
    protected void doFinish(IModelioProgress monitor) throws MigrationFailedException {
        // nothing, to be redefined
    }

    @objid ("db2f541c-2ae9-4fa0-9fc7-7df0d37ebd01")
    @Override
    public final void close() throws MigrationFailedException {
        try {
            if (! this.terminalCalled) {
                abort(null);
            }
            doClose();
        } finally {
            this.terminalCalled = true;
            Log.setLogger(this.oldVCoreLogger);
        }
    }

    /**
     * Hook called by {@link #close()}, to be redefined by sub classes if they have something not done in {@link #doFinish(IModelioProgress)}
     * and {@link #doAbort(IModelioProgress)}.
     * 
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure.
     */
    @objid ("dfdaf2aa-11f2-4a31-96ad-976722f95b74")
    protected void doClose() throws MigrationFailedException {
        // nothing, to be redefined
    }

    @objid ("8f56660d-f8bb-4c95-89a3-24fc6da08ba6")
    @Override
    public final void abort(IModelioProgress monitor) throws MigrationFailedException {
        assert(this.terminalCalled == false);
        
        try {
            doAbort(monitor);
        } finally {
            this.terminalCalled = true;
        }
    }

    /**
     * @param monitor the progress monitor to use for reporting progress to the user.
     * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
     * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
     */
    @objid ("1f7cae3d-c14c-4e47-9722-41a03be07801")
    protected void doAbort(IModelioProgress monitor) throws MigrationFailedException {
    }

    /**
     * Computes the migration steps descriptions from the migration chain.
     * 
     * @return the migration steps descriptions.
     */
    @objid ("284bc8f1-c616-4448-9d04-620d15f718cf")
    protected List<IMigrationStepDescription> computeStepsDescription() {
        List<IMigrationStepDescription> ret = new ArrayList<>(this.migrationChain.getSteps().size());
        
        for (IMofRepositoryMigrator mofMigrator : this.migrationChain.getSteps()) {
            MmVersionComparator vcomparator = MmVersionComparator
                    .withSource(mofMigrator.getSourceMetamodel())
                    .withTarget(mofMigrator.getTargetMetamodel())
                    .withCommonRemoved()
                    .withMissingRemoved();
        
            if (!vcomparator.isTargetCompatible(false)) {
                String msg = CoreProject.I18N.getMessage("ChainedMofFragmentMigrator.detail.migratorline", this.fragToMigrate.getId(), vcomparator.getSource(), vcomparator.getTarget());
                ret.add(new MigrationStepDescription(msg));
            }
        
        }
        return ret;
    }

    @objid ("b1e74b8e-9b56-4001-8ee5-689870934e7e")
    protected final MetamodelVersionDescriptor getFromMmVersion() {
        return this.fromMmVersion;
    }

    @objid ("77a7ad02-5a90-4e92-bcf6-949b5778243d")
    @Deprecated
    protected String getMmDifferencesSummary() {
        MmVersionComparator comp = MmVersionComparator
                .withSource(getFromMmVersion())
                .withTarget(getTargetMmVersion())
                .withCommonRemoved();
        return (CoreProject.I18N.getMessage("ChainedMofFragmentMigrator.detail.first", this.fragToMigrate.getId(), comp.getSource(), comp.getTarget()));
    }

    @objid ("8d77e405-28a4-4c25-a1c0-7991beaa6e4c")
    @Override
    public List<IMigrationStepDescription> getStepsDescription() {
        return this.stepsDescription;
    }

    @objid ("c16f5d1b-cd9b-450a-94ae-93aef74aff0f")
    protected final MetamodelVersionDescriptor getTargetMmVersion() {
        return this.targetMmVersion;
    }

    /**
     * Interface used to instantiate a repository.
     */
    @objid ("d4546770-bc6a-45d2-9473-2a08dbc2a673")
    @FunctionalInterface
    public interface RepositorySupplier {
        /**
         * Instantiate the repository.
         * <p>
         * Does not open it.
         * 
         * @param session the modeling session the repository will be connected to
         * @return the repository
         * @throws org.modelio.gproject.fragment.FragmentAuthenticationException on authentication error
         * @throws java.io.IOException on failure
         */
        @objid ("2cd90fbb-bbc7-45f6-b57a-40fe0f345165")
        IRepository intantiateRepository(ICoreSession session) throws FragmentAuthenticationException, IOException;

    }

    /**
     * Repository error listener plugged on the repository to migrate that put
     * all events into the migration log.
     */
    @objid ("78d58022-16a7-4403-a177-3f932b71f09b")
    private final class RepositoryErrorListener implements IRepositoryErrorListener {
        @objid ("c26fcadc-3880-43df-88a0-2c87a41c75c0")
        private final PrintWriter logger = getMigrationReporter().getLogger();

        @objid ("6eb41a30-3d67-42be-b11e-d4818a350e3f")
        @Override
        public void onWarning(IRepository repository, Throwable e) {
            if (e instanceof IOException) {
                this.logger.printf("Repository warning: %s\n", FileUtils.getLocalizedMessage((IOException) e));
            
                // Put only in Modelio log because most IOException warnings are
                // currently unavoidable in a successful migration
                Log.warning(e);
            } else {
                Log.warning(e);
                this.logger.printf("Repository warning: %s\n", e.toString());
                e.printStackTrace(this.logger);
            }
        }

        @objid ("6a963df8-e69b-4007-8e0b-6607c6d54a5a")
        @Override
        public void onError(IRepository repository, Throwable e) {
            if (e instanceof IOException) {
                this.logger.printf("Repository ERROR: %s\n", FileUtils.getLocalizedMessage((IOException) e));
            } else {
                this.logger.printf("Repository ERROR: %s\n", e.toString());
            }
            e.printStackTrace(this.logger);
        }

    }

}
