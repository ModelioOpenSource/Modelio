/* 
 * Copyright 2013-2019 Modeliosoft
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.fragment.AbstractFragment;
import org.modelio.gproject.fragment.FragmentAuthenticationException;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;
import org.modelio.vcore.model.spi.mm.IMigrationStepDescription;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Fragment migration wrapper that make a backup before starting and that restores it
 * on failure.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("3ba562d0-9fad-4a8d-b421-02950812078c")
public class FragmentMigratorWithBackup implements IFragmentMigrator {
    @objid ("0b7f0086-9914-4d7b-ba59-c73987566084")
    private final AbstractFragment fragToMigrate;

    @objid ("ac6896df-459f-467a-96cc-9ef2b9b8f7a6")
    private IMigrationReporter migrationReporter;

    @objid ("eff625fc-51b8-4670-a3cd-06695f7a2456")
    private final IFragmentMigrator wrapped;

    /**
     * @param fragToMigrate the fragment to migrate
     * @param wrapped the wrapped migrator
     */
    @objid ("ec41b5d4-413b-44fc-bb07-b7e416cea17b")
    public FragmentMigratorWithBackup(AbstractFragment fragToMigrate, IFragmentMigrator wrapped) {
        this.fragToMigrate = fragToMigrate;
        this.wrapped = wrapped;
    }

    @objid ("b715ec0e-ab99-46d0-88cf-80f0bbe2d2e1")
    protected Path getBackupDirectory() {
        return this.fragToMigrate.getRuntimeDirectory().resolve("migration_backup");
    }

    @objid ("c2dc10bb-62fc-4dbe-bb8e-c2964512f760")
    protected void deleteBackup(IModelioProgress monitor) throws IOException {
        String msg = "Deleting backup ... ";// TODO translate
        SubProgress mon = SubProgress.convert(monitor, msg, 1);
        mon.subTask(msg);
        getMigrationReporter().getLogger().println(msg);
        
        FileUtils.delete(getBackupDirectory());
    }

    @objid ("9f99c5bf-f03d-4e55-8733-3674d66baf98")
    protected void backupDataDir(IModelioProgress monitor) throws IOException {
        String msg = "Backup of data directory... ";// TODO translate
        SubProgress mon = SubProgress.convert(monitor, msg, 1);
        mon.subTask(msg);
        getMigrationReporter().getLogger().println(msg);
        
        Files.createDirectories(getBackupDirectory());
        
        FileUtils.copyDirectoryTo(this.fragToMigrate.getDataDirectory(), getBackupDirectory());
    }

    @objid ("1a1271ef-549d-4ed9-8b6f-324c0789df57")
    protected void restoreBackup(IModelioProgress monitor) throws IOException {
        Path dataDirectory = this.fragToMigrate.getDataDirectory();
        Path backupDirectory = getBackupDirectory();
        
        FileUtils.delete(getFailDirectory());
        
        if (Files.isDirectory(backupDirectory)) {
            String msg = CoreProject.I18N.getMessage("FragmentMigratorWithBackup.mon.restoreBackup", this.fragToMigrate.getId(), backupDirectory);
            SubProgress mon = SubProgress.convert(monitor, msg, 1);
            mon.subTask(msg);
            getMigrationReporter().getLogger().println(msg);
        
            if (Files.isDirectory(dataDirectory)) {
                FragmentMigratorWithBackup.moveDirectory(dataDirectory, getFailDirectory());
            }
            //FileUtils.delete(dataDirectory);
            FragmentMigratorWithBackup.moveDirectory(backupDirectory, dataDirectory);
        
            msg = CoreProject.I18N.getMessage("FragmentMigratorWithBackup.mon.restoreBackup.done", this.fragToMigrate.getId(), backupDirectory);
            getMigrationReporter().getLogger().println(msg);
            getMigrationReporter().getResultReporter().println(msg);
        }
    }

    @objid ("46f5c7b8-1443-4470-98af-42fcab2e2b75")
    public IMigrationReporter getMigrationReporter() {
        return this.migrationReporter;
    }

    @objid ("af0beb28-87fc-4d53-a21e-3de489821903")
    public void setMigrationReporter(IMigrationReporter migrationReporter) {
        this.migrationReporter = migrationReporter;
    }

    @objid ("ae959d8f-8e25-4deb-b61e-f252e6c1b736")
    private Path getMigrationBackupArchiveDirectory() {
        return this.fragToMigrate.getRuntimeDirectory().resolve("migration_archive");
    }

    @objid ("990201e9-e999-46f5-9341-b3263fd896da")
    protected static void moveDirectory(Path toMove, Path newPath) throws IOException {
        try {
            Files.move(toMove, newPath);
        } catch (IOException e) {
            FileUtils.copyDirectoryTo(toMove, newPath);
            FileUtils.delete(toMove);
        }
    }

    @objid ("7f20e27d-3d95-438f-a5f2-63e18fc71e5a")
    private void deleteBackupArchive(SubProgress mon) throws IOException {
        Path backArchiveDir = getMigrationBackupArchiveDirectory();
        
        if (Files.isDirectory(backArchiveDir)) {
            String taskName = CoreProject.I18N.getMessage("FragmentMigratorWithBackup.mon.deleteBackupArchive", this.fragToMigrate.getId(), backArchiveDir);
            mon.subTask(taskName);
            this.migrationReporter.getLogger().println(taskName);
        
            FileUtils.delete(backArchiveDir);
        }
    }

    @objid ("71df1091-0df4-45f5-82f0-88a288e4fb5e")
    private void archiveBackup(SubProgress mon) throws IOException {
        Path backDir = getBackupDirectory();
        Path archiveDir = getMigrationBackupArchiveDirectory();
        
        String taskName = CoreProject.I18N.getMessage("FragmentMigratorWithBackup.mon.archiveBackup",
                this.fragToMigrate.getId(),
                archiveDir,
                backDir);
        mon.subTask(taskName);
        this.migrationReporter.getLogger().println(taskName);
        
        FragmentMigratorWithBackup.moveDirectory(backDir, archiveDir);
    }

    @objid ("749f20c0-1b88-4b99-ac60-22b23893004a")
    @Override
    public List<IMigrationStepDescription> getStepsDescription() {
        return this.wrapped.getStepsDescription();
    }

    @objid ("3a6c0d3c-64e6-40d3-91f9-e7766db1704a")
    @Override
    public IMigrationProcess start(IModelioProgress monitor, IMigrationReporter reporter) throws FragmentAuthenticationException, MigrationFailedException {
        setMigrationReporter(reporter);
        String taskName = CoreProject.I18N.getMessage("FragmentMigratorWithBackup.mon.migration", this.fragToMigrate.getId());
        SubProgress mon = SubProgress.convert(monitor, taskName, 14);
        
        
        try {
            SmMetamodel.TRACE_SHORT_METACLASSNAMES = false;
            
            restoreBackup(mon.newChild(1));
        
            backupDataDir(mon.newChild(1));
        } catch (IOException e) {
            throw new MigrationFailedException(FileUtils.getLocalizedMessage(e), e);
        }
        return new MigrationProcess(monitor, reporter);
    }

    @objid ("ee15c218-5262-4c2e-b8a3-ac85d4de61f2")
    protected Path getFailDirectory() {
        return this.fragToMigrate.getRuntimeDirectory().resolve("migration_failure");
    }

    @objid ("2641851c-2089-41ec-a7d7-c96e8360126a")
    @Override
    public String getRequiredUserActions() {
        return this.wrapped.getRequiredUserActions();
    }

    @objid ("0463b5bb-d491-429a-9dd5-5dfdfbd817f9")
    private final class MigrationProcess implements IMigrationProcess {
        @objid ("01c44aff-6c94-400f-a329-92367c35bd24")
        private boolean ok = false;

        @objid ("e2c5d565-b35d-4bd3-87d3-bfa99b22e525")
        private final IMigrationProcess wrappedProcess;

        @objid ("18828c59-b270-4766-bd6e-0d64c82a1694")
        public MigrationProcess(IModelioProgress monitor, IMigrationReporter reporter) throws FragmentAuthenticationException, MigrationFailedException {
            this.wrappedProcess = FragmentMigratorWithBackup.this.wrapped.start(monitor, reporter);
        }

        @objid ("b9f544c7-7134-4ea1-8efb-42ab14087be0")
        @Override
        public void migrateModel(IModelioProgress monitor) throws FragmentAuthenticationException, MigrationFailedException {
            this.wrappedProcess.migrateModel(monitor);
        }

        @objid ("8193241f-76d7-426e-a969-63b91ea9b65b")
        @Override
        public void finish(IModelioProgress monitor) throws MigrationFailedException {
            SubProgress mon = SubProgress.convert(monitor, 3);
            
            this.wrappedProcess.finish(mon.newChild(1));
            
            try {
                deleteBackupArchive(mon.newChild(1));
                archiveBackup(mon.newChild(1));
                // deleteBackup(mon.newChild(1));
                
                this.ok = true;
            } catch (IOException e) {
                throw new MigrationFailedException(FileUtils.getLocalizedMessage(e), e);
            } finally {
                restoreBackupOnFail(mon.newChild(1));
                
                SmMetamodel.TRACE_SHORT_METACLASSNAMES = true;
            }
        }

        @objid ("e1255d95-99b6-469e-97ed-f39db3556c03")
        @Override
        public void close() throws MigrationFailedException {
            try {
                this.wrappedProcess.close();
            } finally {
                restoreBackupOnFail(null);
            }
        }

        @objid ("357f05e6-3814-4a3c-ac3a-5deec7b1d16e")
        @Override
        public void abort(IModelioProgress monitor) throws MigrationFailedException {
            SubProgress mon = SubProgress.convert(monitor, 2);
            try {
                this.wrappedProcess.abort(mon.newChild(1));
            } finally {
                restoreBackupOnFail(mon.newChild(1));
            }
        }

        @objid ("589c8e9e-d326-468d-a6c2-15a9381483a6")
        private void restoreBackupOnFail(IModelioProgress monitor) {
            if (!this.ok) {
                try {
                    restoreBackup(monitor);
                } catch (IOException e) {
                    String msg = CoreProject.I18N.getMessage("FragmentMigratorWithBackup.restoreBackupFailed",
                            FragmentMigratorWithBackup.this.fragToMigrate.getId(),
                            FileUtils.getLocalizedMessage(e),
                            getBackupDirectory(),
                            FragmentMigratorWithBackup.this.fragToMigrate.getDataDirectory());
                    getMigrationReporter().getResultReporter().println(msg);
                    getMigrationReporter().getLogger().println(msg);
                    e.printStackTrace(getMigrationReporter().getLogger());
            
                }
            }
        }

    }

}
