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

package org.modelio.vstore.jdbm.impl.migration.v4;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import jdbm.helper.OpenByteArrayInputStream;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.session.plugin.VCoreSession;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.jdbm.JdbmRepository;
import org.modelio.vstore.jdbm.index.JdbmIndex;
import org.modelio.vstore.jdbm.index.StringTable;

@objid ("788c2f7f-924f-40cc-bf0e-398a42fdba9d")
public class V4Migrator {
    /**
     * Set to true to delete the old database after successfull migration.
     */
    @objid ("37948474-894e-4441-bcb6-0f409664d40a")
    private static final boolean DELETE_OLD_DB = false;

    @objid ("60ef16f2-4ceb-47a7-b374-30d824128976")
    private final String dbName;

    @objid ("30d47e00-c4af-4157-a03a-dbb28bac8022")
    private RecordManager oldDb;

    @objid ("5f806405-7642-45ff-84e1-387377e3ebfe")
    private RecordManager newDb;

    @objid ("f50397db-1d60-4d93-a790-d95ae98918d6")
    private V4Transcoder v4Transcoder;

    @objid ("4aec31a0-53b7-447d-9a90-77eec6175c40")
    private final SmMetamodel metamodel;

    @objid ("42a634a4-2736-42e5-8f75-e8f90ddd7b10")
    private final File oldDbPath;

    @objid ("ab495b5f-f5f5-4087-ab21-7d75e17d305d")
    private final File newDbTempPath;

    /**
     * @param dbPath The JDBM database directory.
     * @param dbName the JDBM database name.
     * @param metamodel the metamodel to use for migration. Must match the old database format.
     */
    @objid ("e71e343d-afe8-4a8e-ab14-7de82e1e092b")
    public V4Migrator(File dbPath, String dbName, SmMetamodel metamodel) {
        this.oldDbPath = dbPath;
        this.dbName = dbName;
        this.metamodel = metamodel;
        this.newDbTempPath = new File (dbPath.getParentFile(), "tmp_new");
    }

    @objid ("44aae9a5-3d46-40b3-8b16-5ba4e21c6042")
    private void recoverFromFailure(IModelioProgress monitor) throws IOException {
        Path oldDbTempPath = getOldDbTempPath();
        if (oldDbTempPath.toFile().isDirectory()) {
            monitor.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.RestoringOldDb", this.dbName, oldDbTempPath));
            Log.trace(" JDBM migration: original database present in '%s' temp dir, a previous migration may have failed. ", oldDbTempPath);
            Files.move(oldDbTempPath, this.oldDbPath.toPath() );
        }
        
        if (this.newDbTempPath.isDirectory()) {
            Log.trace(" JDBM migration: reseting aborted migration");
            monitor.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.ResetAbortedMigration", this.dbName));
            FileUtils.delete(this.newDbTempPath);
        }
    }

    @objid ("4de59346-b039-4855-8df4-66ded85344d2")
    private void initializeDbs(IModelioProgress monitor) throws IOException {
        Files.createDirectories(this.newDbTempPath.toPath());
        
        this.oldDb = JdbmRepository.instantiateDb(this.oldDbPath, this.dbName);
        this.newDb = JdbmRepository.instantiateDb(this.newDbTempPath, this.dbName);
            
        StringTable stringTable = new StringTable(this.newDb, "table_String");
        JdbmIndex indexes = new JdbmIndex(this.newDb, stringTable);
        this.v4Transcoder = new V4Transcoder(this.metamodel, indexes);
    }

    @objid ("7c6f7297-4bb8-4022-8982-a4108a914138")
    public void execute(IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, VCoreSession.I18N.getMessage("jdbm.V4Migrator.MigrateBaseTask", this.dbName), 100);
        Log.trace("Migrating '%s' JDBM base in '%s'", this.dbName, this.oldDbPath);
        
        recoverFromFailure(mon.newChild(5));
        
        try (Closeable c = new DbCloser()){
            initializeDbs(mon.newChild(5));
        
            executeMigration(mon.newChild(80));
        }
        
        commitMigration(mon.newChild(5));
        
        Log.trace("JDBM migration successful.");
        monitor.done();
    }

    /**
     * Move temp database to final directory and delelte old database.
     * @param newChild the progress monitor
     * @throws IOException
     * @throws SecurityException
     */
    @objid ("5df0c632-8c0d-4e14-9a2c-07286895afb3")
    private void commitMigration(SubProgress mon) throws SecurityException, IOException {
        Path indexDirPath = this.oldDbPath.toPath();
        Path oldDbTmpPath = getOldDbTempPath();
        
        // Copy blobs 
        mon.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.CopyingBlobs", this.dbName));
        Log.trace(" JDBM migration: Copy blobs to '%s' temp place ...", oldDbTmpPath);
        FileUtils.copyDirectoryTo(indexDirPath.resolve("blobs"), this.newDbTempPath.toPath().resolve("blobs"));
        
        // Swap old and new directory
        
        mon.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.SwappingDatabases", this.dbName));
        
        Log.trace(" JDBM migration: move old database to '%s' temp place...", oldDbTmpPath);
        Files.move(indexDirPath, oldDbTmpPath);
        
        Log.trace(" JDBM migration: move new database from '%s' to '%s' final place...", this.newDbTempPath, indexDirPath);
        Files.move(this.newDbTempPath.toPath(), indexDirPath);
        
        if (DELETE_OLD_DB) {
            // Delete the old database
            Log.trace(" JDBM migration: delete old database in '%s' temp place...", oldDbTmpPath);
            FileUtils.delete(oldDbTmpPath);
        }
    }

    @objid ("8e52353b-bf5b-4b05-b820-385e20587e5b")
    private Path getOldDbTempPath() {
        return this.oldDbPath.toPath().resolveSibling(".old_v4");
    }

    @objid ("48ca7dc2-705f-4699-804f-bff89e614a32")
    private void executeMigration(IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, VCoreSession.I18N.getMessage("jdbm.V4Migrator.MigrateBaseTask", this.dbName), 5);
        
        Log.trace(" JDBM migration: connecting maps");
        
        PrimaryHashMap<UUID,byte[]> oldDbContent = this.oldDb.hashMap("main", V4UuidSerializer.instance, null);
        PrimaryHashMap<String,byte[]> newDbContent = this.newDb.hashMap("main", null, null);
        
        // Reusable byte array streams
        ByteArrayOutputStream os = new ByteArrayOutputStream(200);
        @SuppressWarnings("resource")
        final SerializerOutput out = new SerializerOutput(os);
        @SuppressWarnings("resource")
        final OpenByteArrayInputStream bis = new OpenByteArrayInputStream(new byte[0]);
        @SuppressWarnings("resource")
        final SerializerInput in = new SerializerInput(bis);
        
        Log.trace(" JDBM migration: converting data...");
        mon.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.ConvertingData", this.dbName));
        
        int i = 0;
        for (Entry<UUID, byte[]> entry : oldDbContent.entrySet()) {
        
            UUID uuid = entry.getKey();
            byte[] bdata = entry.getValue();
            
            // reconfigure input and output streams
            os.reset();
            out.__resetWrittenCounter();
            bis.reset(bdata, bdata.length);
        
            // Read, convert data and update new indexes
            this.v4Transcoder.transcode(in, out, uuid);
        
            // Put converted data
            newDbContent.put(uuid.toString(), os.toByteArray());
        
            // Commit sometimes to avoid OOME
            ++i;
            if (i % 500 == 0) {
                mon.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.Converting.flushing", this.dbName, i));
                this.newDb.commit();
                mon.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.Converting.progress", this.dbName, i));
            }
            if (i % 21 == 0) {
                mon.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.Converting.progress", this.dbName, i));
                mon.worked(1);
                mon.setWorkRemaining(5);
            }
        }
        
        Log.trace(" JDBM migration: %d elements converted.", i);
        Log.trace(" JDBM migration: conversion last commit...");
        
        mon.setWorkRemaining(3);
        mon.subTask(VCoreSession.I18N.getMessage("jdbm.V4Migrator.Converting.finish", this.dbName, i));
        this.newDb.commit();
        mon.worked(1);
    }

    /**
     * Autocloseable that closes both JDBM bases.
     */
    @objid ("aa3d0ba0-f47f-4fa9-bb70-bd05a8ff8a9f")
    private class DbCloser implements Closeable {
        @objid ("e5db38d9-ea55-4eae-850b-6a54a33c435e")
        @Override
        public void close() throws IOException {
            IOException err = null;
            if (V4Migrator.this.oldDb != null) {
                try {
                    V4Migrator.this.oldDb.close();
                    V4Migrator.this.oldDb = null;
                } catch (IOException e) {
                    err = e;
                }
            }
            
            if (V4Migrator.this.newDb != null) {
                try {
                    V4Migrator.this.newDb.close();
                    V4Migrator.this.newDb = null;
                } catch (IOException e) {
                    if (err != null) {
                        err.addSuppressed(e);
                    } else {
                        err = e;
                    }
                }
            }
            
            if (err != null) {
                throw err;
            }
        }

    }

}
