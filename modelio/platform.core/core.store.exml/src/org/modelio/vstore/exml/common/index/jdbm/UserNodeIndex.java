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

package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryTreeMap;
import jdbm.RecordManager;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import jdbm.helper.ComparableComparator;
import org.modelio.vbasic.files.StreamException;
import org.modelio.vbasic.log.Log;
import org.modelio.vstore.exml.common.index.IUserNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ObjId;

/**
 * JDBM based implementation of {@link IUserNodeIndex}.
 */
@objid ("e1ee6208-5c83-11e1-863f-001ec947ccaf")
public class UserNodeIndex implements IUserNodeIndex {
    @objid ("f32593c4-1f6e-497d-98fc-c1de22cb22c6")
    private final PrimaryTreeMap<UserNodeIndex.UseEntry,Boolean> usersInverse;

    /**
     * Sorted Map used as a Set.
     * <p>
     * key = used object id.
     * value = collection of user CMS nodes id.
     */
    @objid ("d55011a2-7f1a-11e1-ba70-001ec947ccaf")
    private final PrimaryTreeMap<UserNodeIndex.UseEntry,Boolean> users;

    @objid ("03a04e99-10c4-4db7-8f8d-df8ad2b20fbf")
    private final SymbolTable<String> symbolTable;

    @objid ("e2489abe-e170-4209-ac6b-942af27a75a3")
    private final SymbolTable<ObjId> objIdTable;

    /**
     * Initialize the index.
     * @param db the JDBM database.
     * @param symbolTable the string symbols table
     * @param objIdTable the ObjId table
     * @throws org.modelio.vstore.exml.common.index.IndexException if the index is broken
     */
    @objid ("e1ee6203-5c83-11e1-863f-001ec947ccaf")
    @SuppressWarnings("unchecked")
    public UserNodeIndex(final RecordManager db, SymbolTable<String> symbolTable, SymbolTable<ObjId> objIdTable) throws IndexException {
        this.symbolTable = symbolTable;
        this.objIdTable = objIdTable;
        try {
            UseEntrySerializer keySerializer = new UseEntrySerializer();
            this.users = db.treeMap("usersV16", ComparableComparator.INSTANCE, null, keySerializer);
            this.usersInverse = db.treeMap("usersInverseV16", ComparableComparator.INSTANCE, null, keySerializer);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee6204-5c83-11e1-863f-001ec947ccaf")
    @Override
    public Collection<ObjId> getObjectUsers(final ObjId targetObjId, String depName) throws IndexException {
        try {
            long depId = this.symbolTable.findKey(depName);
            if (depId==-1) {
                return Collections.emptyList();
            }
            
            long targetKId = this.objIdTable.findKey(targetObjId);
            if (targetKId==-1) {
                return Collections.emptyList();
            }
            
            SortedMap<UseEntry, Boolean> found = subMap(this.users, targetKId, depId);
            
            if (found.isEmpty()) {
                return Collections.emptyList();
            } 
        
            Collection<ObjId> ret = new ArrayList<>(found.size());
            found.forEach((k,v) -> {
                try {
                    ObjId lsrc = this.objIdTable.getValue(k.srcCmsNodeId);
        
                    final boolean debug = false;
                    if (debug) {
                        ObjId ltarget = this.objIdTable.getValue(k.targetObjectId);
                        String depN = this.symbolTable.getValue(k.depId);
                        Log.trace("  getObjectUsers(%s, '%s'): %s.%s -> %s\n", targetObjId, depName, lsrc, depN,  targetObjId, ltarget);
                    }
        
                    ret.add(lsrc);
                } catch (IOException e) {
                    throw new StreamException(e);
                }
            } );
            return ret;
            
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (StreamException e) {
            throw JdbmIndexException.from((IOException)e.getCause());
        } catch (NullPointerException e) {
            dumpUsers(System.err);
            throw e;
        }
    }

    @objid ("e1ee6205-5c83-11e1-863f-001ec947ccaf")
    @Override
    public void addUsed(final ObjId userNodeId, String depName, final ObjId usedObjectId) throws IndexException {
        try {
            long depK = this.symbolTable.getOrAddKey(depName);
            long userKey = this.objIdTable.getOrAddKey(userNodeId); 
            long usedKey = this.objIdTable.getOrAddKey(usedObjectId);
            
            this.users.put(new UseEntry(userKey, depK, usedKey), Boolean.TRUE);
            this.usersInverse.put(new UseEntry(usedKey, depK, userKey), Boolean.TRUE);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee6206-5c83-11e1-863f-001ec947ccaf")
    @Override
    public void remove(final ObjId toRemove) throws IndexException {
        try {
            long toRemoveK = this.objIdTable.findKey(toRemove);
            if (toRemoveK != -1) {
                SortedMap<UseEntry, Boolean> entriesToRemove = subMap(this.usersInverse, toRemoveK, -1);
        
                //Remove from the used list
                for (UseEntry inv : entriesToRemove.keySet()) {
                    this.users.remove(new UseEntry(toRemoveK, inv.depId, inv.srcCmsNodeId));
                }
        
                entriesToRemove.clear();
            }
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("d4f31703-7f1a-11e1-ba70-001ec947ccaf")
    private void dumpUsers(final PrintStream out) {
        out.println("Users CMS nodes index dump:");
        /*
        for (Entry<UseEntry, Boolean>  en: this.users.entrySet()) {
            UseEntry k = en.getKey();
            out.println(" - "+k+" used by:");
            for (ObjId  user: en.getValue().get(this.db, this.idCollSerializer)) {
                out.println("   - "+user);
            }
        }*/
    }

    /**
     * Get a sub map of the given map filtered for the target node id and.
     * <p>
     * Reminder: {@link UseEntry} are sorted in order by: the target then the dependency id then the source.
     * @param from the map to filter
     * @param nodeId the target node id in the index
     * @param depId the dependency id in the index
     * @return
     */
    @objid ("f1e42539-ace7-4152-aba1-321df366ebb6")
    private SortedMap<UseEntry, Boolean> subMap(SortedMap<UseEntry, Boolean> from, long nodeId, long depId) {
        return from.subMap(
                                        new UseEntry(Long.MIN_VALUE, (depId == -1 ? Long.MIN_VALUE : depId), nodeId), 
                                        new UseEntry(Long.MAX_VALUE, (depId == -1 ? Long.MAX_VALUE : depId), nodeId));
    }

    /**
     * Users index entry.
     * <p>
     * Indexes CMS nodes using individual objects with the dependency
     * from the source object to the target.
     * <p>
     * UseEntry are sorted in order by: the target then the dependency id then the source.
     * @author cma
     * @since 3.6.1
     */
    @objid ("f42f5969-1988-4501-a0ef-0f796b62a984")
    private static class UseEntry implements Comparable<UseEntry> {
        /**
         * The CMS node is of the source
         */
        @objid ("761777b2-d4a3-4040-b1dd-377687663852")
         final long srcCmsNodeId;

        /**
         * The id of the dependency
         */
        @objid ("6e814e3e-efd8-4085-b149-c77fe8e2ced0")
         final long depId;

        /**
         * The target object Id.
         */
        @objid ("15574f42-f2b3-4751-af17-90f2520e4678")
         final long targetObjectId;

        /**
         * Min Id stub for Tree.subMap(min, max)
         */
        @objid ("cc6060f0-3f5c-4801-882f-8119e28fb2ba")
        public static final long MIN = Long.MIN_VALUE;

        /**
         * Max Id stub for Tree.subMap(min, max)
         */
        @objid ("6c81962b-f4e4-422c-8845-2ba7d445b410")
        public static final long MAX = Long.MAX_VALUE;

        @objid ("8298eb0e-d8ba-4048-8b9e-5ac7667c7e98")
        public UseEntry(long srcId, long depId, long targetId) {
            super();
            this.srcCmsNodeId = srcId;
            this.depId = depId;
            this.targetObjectId = targetId;
        }

        @objid ("08aae18d-17f0-4b59-90af-76c0d479d324")
        @Override
        public String toString() {
            return String.format("UseEntry[from %d, dep %d, to %d]", this.srcCmsNodeId, this.depId, this.targetObjectId);
        }

        @objid ("67da514d-8634-4928-b8dd-7976cb418b4b")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (this.depId ^ (this.depId >>> 32));
            result = prime * result + (int) (this.srcCmsNodeId ^ (this.srcCmsNodeId >>> 32));
            result = prime * result + (int) (this.targetObjectId ^ (this.targetObjectId >>> 32));
            return result;
        }

        @objid ("83494938-7659-49de-8c1c-0ade8928d4d7")
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            UseEntry other = (UseEntry) obj;
            if (this.depId != other.depId) {
                return false;
            }
            if (this.srcCmsNodeId != other.srcCmsNodeId) {
                return false;
            }
            if (this.targetObjectId != other.targetObjectId) {
                return false;
            }
            return true;
        }

        @objid ("968bb619-3c3b-4003-b76c-60f9f443c503")
        @Override
        public int compareTo(UseEntry o2) {
            int c = Long.compare(this.targetObjectId , o2.targetObjectId);
            
            if (c==0) {
                c = Long.compare(this.depId , o2.depId);
            }
            
            if (c==0) {
                c = Long.compare(this.srcCmsNodeId , o2.srcCmsNodeId);
            }
            return c;
        }

    }

    @objid ("99dd6e8e-3a15-4898-80ed-7060875b475b")
    private static class UseEntrySerializer implements Serializer<UseEntry> {
        @objid ("efbdec39-e9c7-4932-8ec8-47b332e411a7")
        @Override
        public void serialize(SerializerOutput out, UseEntry obj) throws IOException {
            // Stubs are not meant to be persisted
            assert (obj.srcCmsNodeId!=UseEntry.MIN);
            assert (obj.srcCmsNodeId!=UseEntry.MAX);
            assert (obj.targetObjectId!=UseEntry.MIN);
            assert (obj.targetObjectId!=UseEntry.MAX);
            
            out.writeLong(obj.srcCmsNodeId);
            out.writeLong(obj.depId);
            out.writeLong(obj.targetObjectId);
        }

        @objid ("ae381422-6b94-4c1e-a2ff-840f529c038f")
        @Override
        public UseEntry deserialize(SerializerInput in) throws ClassNotFoundException, IOException {
            long src = in.readLong();
            long depId = in.readLong();
            long target = in.readLong();
            return new UseEntry(src, depId, target);
        }

    }

}
