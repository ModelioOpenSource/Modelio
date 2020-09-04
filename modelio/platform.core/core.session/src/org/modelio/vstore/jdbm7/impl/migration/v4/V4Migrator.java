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

package org.modelio.vstore.jdbm7.impl.migration.v4;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
import org.modelio.vstore.jdbm7.JdbmRepository;
import org.modelio.vstore.jdbm7.index.JdbmIndex;

@objid ("05c838bc-9481-4928-ae6e-05fb76ed09be")
public class V4Migrator {
    /**
     * Set to true to delete the old database after successfull migration.
     */
    @objid ("03d5da92-6683-497c-bff2-ce87c25c2039")
    private static final boolean DELETE_OLD_DB = false;

    @objid ("2df37a03-0c8b-4272-82d1-c3c8b7755169")
    private final String dbName;

    @objid ("c3c1424d-69ee-46cd-a9b7-d70a8cbf962b")
    private RecordManager oldDb;

    @objid ("dc11b5e6-3417-4580-98ac-fbce9c22535d")
    private RecordManager newDb;

    @objid ("41f31531-01f2-4c24-b689-18c62f7939ab")
    private V4Transcoder v4Transcoder;

    @objid ("861d36fc-a4a4-4bbb-b97f-05b533bebc3e")
    private final SmMetamodel metamodel;

    @objid ("22f1233c-3d44-49f4-b36f-0a3319355d8f")
    private final File oldDbPath;

    @objid ("d29ee93a-0157-4ba3-8ed4-0688521ef61f")
    private final File newDbTempPath;

    /**
     * @param dbPath The JDBM database directory.
     * @param dbName the JDBM database name.
     * @param metamodel the metamodel to use for migration. Must match the old database format.
     */
    @objid ("51505e5e-137c-438c-9086-e2f958d1f874")
    public V4Migrator(File dbPath, String dbName, SmMetamodel metamodel) {
        this.oldDbPath = dbPath;
        this.dbName = dbName;
        this.metamodel = metamodel;
        this.newDbTempPath = new File (dbPath.getParentFile(), "tmp_new");
    }

    @objid ("86c86f2a-770b-46bd-b08c-810653276605")
    private void recoverFromFailure(IModelioProgress monitor) throws FileSystemException, IOException {
        Path oldDbTempPath = getOldDbTempPath();
        if (oldDbTempPath.toFile().isDirectory()) {
            monitor.subTask(VCoreSession.getMessage("jdbm.V4Migrator.RestoringOldDb", this.dbName, oldDbTempPath));
            Log.trace(" JDBM migration: original database present in '%s' temp dir, a previous migration may have failed. ", oldDbTempPath);
            Files.move(oldDbTempPath, this.oldDbPath.toPath() );
        }
        
        if (this.newDbTempPath.isDirectory()) {
            Log.trace(" JDBM migration: reseting aborted migration");
            monitor.subTask(VCoreSession.getMessage("jdbm.V4Migrator.ResetAbortedMigration", this.dbName));
            FileUtils.delete(this.newDbTempPath);
        }
    }

    @objid ("1512c98a-b8ac-4848-b97b-988e34a4a8c4")
    private void initializeDbs(IModelioProgress monitor) throws IOException {
        Files.createDirectories(this.newDbTempPath.toPath());
        
        this.oldDb = JdbmRepository.instantiateDb(this.oldDbPath, this.dbName);
        this.newDb = JdbmRepository.instantiateDb(this.newDbTempPath, this.dbName);
            
        JdbmIndex indexes = new JdbmIndex(this.newDb);
        this.v4Transcoder = new V4Transcoder(this.metamodel, indexes);
    }

    @objid ("6333dae6-66dc-4426-b262-e4e955de5f03")
    public void execute(IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, VCoreSession.getMessage("jdbm.V4Migrator.MigrateBaseTask", this.dbName), 100);
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
    @objid ("9605aadb-021d-486d-a91a-540cccec3bdc")
    private void commitMigration(SubProgress mon) throws DirectoryNotEmptyException, FileSystemException, IOException, NoSuchFileException, SecurityException {
        Path indexDirPath = this.oldDbPath.toPath();
        Path oldDbTmpPath = getOldDbTempPath();
        
        // Copy blobs 
        mon.subTask(VCoreSession.getMessage("jdbm.V4Migrator.CopyingBlobs", this.dbName));
        Log.trace(" JDBM migration: Copy blobs to '%s' temp place ...", oldDbTmpPath);
        FileUtils.copyDirectoryTo(indexDirPath.resolve("blobs"), this.newDbTempPath.toPath().resolve("blobs"));
        
        // Swap old and new directory
        
        mon.subTask(VCoreSession.getMessage("jdbm.V4Migrator.SwappingDatabases", this.dbName));
        
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

    @objid ("088f666f-ce56-462e-8718-b5c1902f0550")
    private Path getOldDbTempPath() {
        return this.oldDbPath.toPath().resolveSibling(".old_v4");
    }

    @objid ("b3278881-d563-4113-9292-9654b72fde64")
    private void executeMigration(IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, VCoreSession.getMessage("jdbm.V4Migrator.MigrateBaseTask", this.dbName), 5);
        
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
        mon.subTask(VCoreSession.getMessage("jdbm.V4Migrator.ConvertingData", this.dbName));
        
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
                mon.subTask(VCoreSession.getMessage("jdbm.V4Migrator.Converting.flushing", this.dbName, i));
                this.newDb.commit();
                mon.subTask(VCoreSession.getMessage("jdbm.V4Migrator.Converting.progress", this.dbName, i));
            }
            if (i % 21 == 0) {
                mon.subTask(VCoreSession.getMessage("jdbm.V4Migrator.Converting.progress", this.dbName, i));
                mon.worked(1);
                mon.setWorkRemaining(5);
            }
        }
        
        Log.trace(" JDBM migration: %d elements converted.", i);
        Log.trace(" JDBM migration: conversion last commit...");
        
        mon.setWorkRemaining(3);
        mon.subTask(VCoreSession.getMessage("jdbm.V4Migrator.Converting.finish", this.dbName, i));
        this.newDb.commit();
        mon.worked(1);
    }

    /**
     * Autocloseable that closes both JDBM bases.
     */
    @objid ("8a14b5f4-7465-4faf-93db-abd9c5b83669")
    private class DbCloser implements Closeable {
        @objid ("4019914a-1c4d-4165-a63c-c36d01117ce8")
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
