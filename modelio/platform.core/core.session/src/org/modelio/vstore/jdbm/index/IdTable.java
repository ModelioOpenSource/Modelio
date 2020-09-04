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

package org.modelio.vstore.jdbm.index;

import java.io.IOError;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.PrimaryTreeMap;
import jdbm.RecordManager;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.session.plugin.VCoreSession;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Index of all known {@link MRef}.
 * <p>
 * References are indexed by UUID and get a local {@link Long} identifier.
 * 
 * @author cma
 * @since 3.6.1
 */
@objid ("e97ad125-0647-4703-a791-e82930683f79")
@Deprecated
public class IdTable {
    @objid ("ea183a27-aa27-44c2-a8ac-c36479b2b2c5")
    private final RecordManager db;

    @objid ("382f628d-d128-44c1-bc58-9a198e762c9b")
    private final PrimaryHashMap<String,Long> table;

    @objid ("56c39a12-99ef-4bc9-829b-a1a5471df481")
     PrimaryTreeMap<Long,MRef> tableInverse;

    /**
     * @param db the JDBM base
     * @param name the name of this table
     */
    @objid ("b45b3bf6-8b3a-4913-a4f1-35d665f9f358")
    private IdTable(RecordManager db, String name) {
        this.db = db;
        this.table = db.hashMap(name, UTFSerializer.INSTANCE);
        this.tableInverse = db.treeMap(name+"_inverse", MRefSerializer.instance);
    }

    /**
     * Get the MRef from a local id.
     * @param key a local identifier
     * @return the found MRef or null.
     * @throws java.io.IOException on JDBM failure.
     */
    @objid ("d3a3804b-9d6b-4a4b-8b46-d6a32bca8670")
    public MRef getRef(long key) throws IOException {
        return this.db.fetch(key, MRefSerializer.instance);
    }

    /**
     * Get the local identifier for a MRef.
     * <p>
     * Records the MRef and gives him a local identifier if it is missing.
     * @param ref a MRef.
     * @return the exisiting or new local identifier.
     * @throws java.io.IOException on JDBM failure.
     */
    @objid ("cfadbc0b-37ec-4b30-aa44-3086bcfa6612")
    public long getOrAddRef(MRef ref) throws IOException {
        try {
            return this.table.computeIfAbsent(ref.uuid, lsymbol -> {
                    Long newKey = this.tableInverse.newLongKey();
                    this.tableInverse.put(newKey, ref);
                    return newKey;
            });
        } catch (IOError | InternalError e) {
            throw new IOException(e);
        }
    }

    /**
     * @param uuid the uuid to find
     * @return the MRef local ID or -1 if not found.
     * @throws java.io.IOException on JDBM failure
     */
    @objid ("118b2b6e-0478-434c-97d6-e041ed036078")
    public Long findLocalId(String uuid) throws IOException {
        return this.table.find(uuid);
    }

    /**
     * @param ref the MRef to find
     * @return the MRef local ID or -1 if not found.
     * @throws java.io.IOException on JDBM failure
     */
    @objid ("b95b7518-bc8b-40ec-8d8c-932863eded6d")
    public Long findLocalId(MRef ref) throws IOException {
        return findLocalId(ref.uuid);
    }

    /**
     * Create a garbage collector.
     * @param repositoryLabel a user friendly repository label
     * @return a garbage collector for this table.
     * @throws java.io.IOException on I/O error
     */
    @objid ("fe183132-a0e4-4e0b-88b3-67e52b147b8d")
    public GC gc(String repositoryLabel) throws IOException {
        return new GC(repositoryLabel);
    }

    /**
     * Remove an entry from the table.
     * @param lid the local id
     * @throws java.io.IOException on I/O error
     */
    @objid ("eb759ec3-0492-4295-bb7a-a562f8293949")
    public void remove(Long lid) throws IOException {
        MRef ref = this.tableInverse.remove(lid);
        this.table.remove(ref.uuid);
    }

    /**
     * Table garbage collector.
     * @author cma
     */
    @objid ("03a66bde-9d2b-4364-ad2a-0a5bc8e43c96")
    public class GC {
        @objid ("d768a4e8-f3a5-43d3-8adb-79c02093cff6")
        private final Set<Long> walked;

        @objid ("723bae16-c404-4351-9474-57c850ee98d5")
        private String repoLabel;

        /**
         * Initialize a garbage collector.
         * @throws java.io.IOException on I/O error
         */
        @objid ("f6e4ae24-6ff1-49eb-b88d-03888756dc2e")
        GC(String repoLabel) throws IOException {
            this.repoLabel = repoLabel;
            this.walked = new HashSet<>(50_000);
        }

        /**
         * Mark the local id as used.
         * @param lid a local id
         * @throws java.io.IOError on I/O error
         */
        @objid ("731c89c1-b195-4b02-8ec8-9f22f5123027")
        public void mark(Long lid) throws IOError {
            this.walked.add(lid);
        }

        /**
         * Delete unused entried.
         * @param monitor a progress monitor
         * @throws java.io.IOException on I/O error
         */
        @objid ("270ce04e-bdbc-4950-82f1-a89c4792e500")
        public void finish(IModelioProgress monitor) throws IOException {
            int nwalked = this.walked.size();
            SubProgress mon = SubProgress.convert(monitor, (int)(nwalked * 1.5));
            
            int i = 0;
            for (Iterator<Long> it = IdTable.this.table.values().iterator(); it.hasNext();) {
                Long entry = it.next();
                if (! this.walked.contains(entry)) {
                    it.remove();
                } else if (i++ % 31 == 0) {
                    mon.subTask(VCoreSession.getMessage("JdbmRepository.gc.finish.delete", this.repoLabel, i, nwalked));
                    mon.worked(1);
                    mon.setWorkRemaining(5);
                }
            }            
            
            mon.subTask(VCoreSession.getMessage("JdbmRepository.gc.finish.commit", this.repoLabel));
            IdTable.this.db.commit();
            mon.worked(nwalked / 2);
        }

    }

}
