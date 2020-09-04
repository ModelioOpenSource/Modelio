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
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * JDBM based implementation of {@link IUserNodeIndex}.
 */
@objid ("6c53b775-18c8-40c3-90b2-a817eb0558cd")
class CrossRefsIndex {
    /**
     * Sorted Map used as a Set.
     * <p>
     * key = used object id.
     * value = collection of user CMS nodes id.
     */
    @objid ("d74aa380-50d7-45f6-a3d5-e1354503756c")
    private final PrimaryTreeMap<CrossRefsIndex.UseEntry,Boolean> users;

    @objid ("14317c47-a3ad-459f-b9a1-90aa8fc6c759")
    private final StringTable symbolTable;

    /**
     * Initialize the index.
     * @param db the JDBM database.
     * @param symbolTable the string symbols table
     * @throws java.io.IOException if the index is broken
     */
    @objid ("ce809f78-5eb3-46ee-983d-a6f234e041b9")
    @SuppressWarnings("unchecked")
    public CrossRefsIndex(final RecordManager db, StringTable symbolTable) throws IOException {
        this.symbolTable = symbolTable;
        try {
            UseEntrySerializer keySerializer = new UseEntrySerializer();
            this.users = db.treeMap("usersV16", ComparableComparator.INSTANCE, null, keySerializer);
        } catch (InternalError e) {
            throw new IOException(e);
        } catch (IOError e) {
            throw new IOException(e);
        }
    }

    @objid ("c55306ae-2b8a-44c3-8a79-da22f94bf57c")
    public Collection<MRef> getSources(String depName, final MRef targetObjId) throws IOException {
        try {
            Long depId = this.symbolTable.findKey(depName);
            if (depId==null) {
                return Collections.emptyList();
            }
            
            //Note: don't use isEmpty() or size() on 'found', they iterate the whole map
            SortedMap<UseEntry, Boolean> found = subMap(this.users, targetObjId, depId);
            
            if (! found.entrySet().iterator().hasNext()) {
                return Collections.emptyList();
            } 
        
            Collection<MRef> ret = new ArrayList<>();
            found.forEach((k,v) -> {
                ret.add(k.srcCmsNodeId);
            } );
            
            return ret;
            
        } catch (InternalError e) {
            throw new IOException(e);
        } catch (IOError e) {
            throw new IOException(e);
        } catch (NullPointerException e) {
            dumpUsers(System.err);
            throw e;
        }
    }

    @objid ("3ba2dbe1-c0fe-445a-9b44-7c32f8739151")
    public void addUse(final MRef sourceRef, String depName, final MRef targetRef) throws IOException {
        try {
            long depLid = this.symbolTable.getOrAddKey(depName);
            
            this.users.put(new UseEntry(sourceRef, depLid, targetRef), Boolean.TRUE);
        } catch (InternalError e) {
            throw new IOException(e);
        } catch (IOError e) {
            throw new IOException(e);
        }
    }

    @objid ("30bc64a2-094d-4756-b871-3bba6dc353c0")
    private void dumpUsers(final PrintStream out) {
        /*
        out.println("Users CMS nodes index dump:");
        for (Entry<UseEntry, Boolean>  en: this.users.entrySet()) {
            UseEntry k = en.getKey();
            out.println(" - "+k+" used by:");
            for (MRef  user: en.getValue().get(this.db, this.idCollSerializer)) {
                out.println("   - "+user);
            }
        }*/
    }

    @objid ("a73e1b02-9067-4c58-9980-26bba945084e")
    private static SortedMap<UseEntry, Boolean> subMap(SortedMap<UseEntry, Boolean> from, MRef nodeId, long depId) {
        SortedMap<UseEntry, Boolean> ret = from.subMap(
                new UseEntry(UseEntry.MIN, (depId == -1 ? Long.MIN_VALUE : depId), nodeId), 
                new UseEntry(UseEntry.MAX, (depId == -1 ? Long.MAX_VALUE : depId), nodeId));
        return ret;
    }

    @objid ("66057366-33df-42d9-b0fd-14a306316f92")
    public void removeUse(MRef srcRef, String depName, MRef targetRef) throws IOException {
        try {
            Long depLid = this.symbolTable.findKey(depName);
            if (depLid != null) {
                this.users.remove(new UseEntry(srcRef, depLid.longValue(), targetRef));
            }
        } catch (InternalError e) {
            throw new IOException(e);
        } catch (IOError e) {
            throw new IOException(e);
        }
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
    @objid ("ecbf73fa-9242-4773-aea8-46547e7f0355")
    private static class UseEntry implements Comparable<UseEntry> {
        /**
         * The id of the dependency
         */
        @objid ("3a9c0c7f-1d0a-47f2-8fc4-e514aff073da")
         final long depId;

        /**
         * The CMS node is of the source
         */
        @objid ("9eb51433-3975-4054-abee-40b99e6f7ced")
         final MRef srcCmsNodeId;

        /**
         * The target object Id.
         */
        @objid ("7940a3a2-0338-4ca4-9918-c2e6664fa61a")
         final MRef targetObjectId;

        /**
         * Min Id stub for Tree.subMap(min, max)
         */
        @objid ("42a2f17f-c62e-4794-bcb9-8c1224c987a4")
        public static final MRef MIN = new MRef("\0", "\0", "");

        /**
         * Max Id stub for Tree.subMap(min, max)
         */
        @objid ("a4c2470b-c626-4908-90ff-c949f2f5958d")
        public static final MRef MAX = new MRef(String.valueOf(Character.MAX_VALUE), String.valueOf(Character.MAX_VALUE), String.valueOf(Character.MAX_VALUE));

        @objid ("204d0b90-17cb-4d5b-95fb-8869774b28e7")
        public UseEntry(MRef srcId, long depId, MRef targetId) {
            super();
            
            this.srcCmsNodeId = srcId;
            this.depId = depId;
            this.targetObjectId = targetId;
        }

        @objid ("1c00436f-da19-4e54-bf90-404170d995d6")
        @Override
        public String toString() {
            return String.format("UseEntry[from %s, dep %d, to %s]", this.srcCmsNodeId, this.depId, this.targetObjectId);
        }

        @objid ("ad958973-d902-4b34-808d-1ec9e0ac7622")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (this.depId ^ (this.depId >>> 32));
            result = prime * result + ((this.srcCmsNodeId == null) ? 0 : this.srcCmsNodeId.hashCode());
            result = prime * result + ((this.targetObjectId == null) ? 0 : this.targetObjectId.hashCode());
            return result;
        }

        @objid ("0ae28c39-b6fa-469f-bc4d-c411218efeb3")
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
            if (this.srcCmsNodeId == null) {
                if (other.srcCmsNodeId != null) {
                    return false;
                }
            } else if (!this.srcCmsNodeId.equals(other.srcCmsNodeId)) {
                return false;
            }
            if (this.targetObjectId == null) {
                if (other.targetObjectId != null) {
                    return false;
                }
            } else if (!this.targetObjectId.equals(other.targetObjectId)) {
                return false;
            }
            return true;
        }

        @objid ("dfe29a9a-5c88-433a-b2ea-5bf602fa9d8b")
        @Override
        public int compareTo(UseEntry o2) {
            int c = compare(this.targetObjectId , o2.targetObjectId);
            
            if (c==0) {
                c = Long.compare(this.depId , o2.depId);
            }
            
            if (c==0) {
                c = compare(this.srcCmsNodeId , o2.srcCmsNodeId);
            }
            return c;
        }

        @objid ("6e393aec-c979-4b25-94ba-e5b7ba24e992")
        private static int compare(MRef a, MRef b) {
            return a.uuid.compareTo(b.uuid);
        }

    }

    @objid ("46276cf3-576b-47d5-bd62-2a5467ca32c9")
    private static class UseEntrySerializer implements Serializer<UseEntry> {
        @objid ("9159a800-f348-422f-884e-313830dac0bf")
        @Override
        public void serialize(SerializerOutput out, UseEntry obj) throws IOException {
            // Stubs are not meant to be persisted
            assert (obj.srcCmsNodeId!=UseEntry.MIN);
            assert (obj.srcCmsNodeId!=UseEntry.MAX);
            assert (obj.targetObjectId!=UseEntry.MIN);
            assert (obj.targetObjectId!=UseEntry.MAX);
            
            MRefSerializer.instance.serialize(out, obj.srcCmsNodeId);
            out.writeLong(obj.depId);
            MRefSerializer.instance.serialize(out, obj.targetObjectId);
        }

        @objid ("f75369dd-54a7-4f30-a3b2-4114a78bddc1")
        @Override
        public UseEntry deserialize(SerializerInput in) throws ClassNotFoundException, IOException {
            MRef src = MRefSerializer.instance.deserialize(in);
            long depId = in.readLong();
            MRef target = MRefSerializer.instance.deserialize(in);
            return new UseEntry(src, depId, target);
        }

    }

}
