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
package org.modelio.vcore.swap;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.RecordManagerOptions;
import jdbm.Serializer;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.IMetaOf;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISwap;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.utils.jdbm.LongSerializer;

/**
 * JDBM based implementation of Modelio swap.
 */
@objid ("cd738866-8499-11e1-b644-001ec947ccaf")
public class JdbmSwap implements ISwap {
    @objid ("f5f214ca-84b5-11e1-b644-001ec947ccaf")
    private static int commitFreq = 10;

    @objid ("f6d23057-84b5-11e1-b644-001ec947ccaf")
    private RecordManager db;

    @objid ("f6d2305e-84b5-11e1-b644-001ec947ccaf")
    private Map<String, Long> uidMap;

    @objid ("f5f214c9-84b5-11e1-b644-001ec947ccaf")
    private int opCount = 0;

    @objid ("cc507d4f-8544-11e1-afeb-001ec947ccaf")
    private String swapPath;

    @objid ("dcb9c2a2-493b-11e2-91c9-001ec947ccaf")
    private final Serializer<CacheEntry> dataSerializer;

    @objid ("f5f214cc-84b5-11e1-b644-001ec947ccaf")
    private Index<IRepositoryObject> storeIndex = new Index<>();

    @objid ("f5f214cf-84b5-11e1-b644-001ec947ccaf")
    private Index<IMetaOf> metaObjectIndex = new Index<>();

    @objid ("09533858-2fee-486b-bee9-03b3fa1cd4a4")
    private final CacheEntry saveEntry = new CacheEntry();

    @objid ("d8d9b396-8499-11e1-b644-001ec947ccaf")
    @Override
    public synchronized void swap(final SmObjectData data) {
        //        Log.trace("Swap "+data.getClassOf().getName()+ " "+data.getUuid());
        try {
            this.saveEntry.data = data;
            this.saveEntry.metaId = this.metaObjectIndex.getId(data.getMetaOf());
            this.saveEntry.storeHandleId = this.storeIndex.getId(data.getRepositoryObject());
        
            long recid = this.db.insert(this.saveEntry, this.dataSerializer);
            this.uidMap.put(data.getUuid(), Long.valueOf(recid));
        
            commitSometimes();
        } catch (IOException e) {
            throw new IOError(e);
        }
        
    }

    @objid ("d8dc15ed-8499-11e1-b644-001ec947ccaf")
    @Override
    public synchronized SmObjectData restore(final String uuid) {
        try {
            Long lrecid = this.uidMap.remove(uuid);
            if (lrecid == null) {
                return null;
            }
        
            long recid = lrecid.longValue();
            CacheEntry entry = this.db.fetch(recid, this.dataSerializer);
            if (entry == null) {
                throw new IOException(recid+" record containing "+uuid.toString()+" object not found in swap.");
            }
        
            SmObjectData ret = entry.data;
            ret.setRepositoryObject(this.storeIndex.getObject(entry.storeHandleId));
            ret.setMetaOf(this.metaObjectIndex.getObject(entry.metaId));
        
        
            this.db.delete(recid);
        
            //            Log.trace("Swap restoring: "+ret.getClassOf().getName()+ " "+ret.getUuid());
        
            commitSometimes();
        
            return ret;
        } catch (IOException e) {
            throw new IOError(e);
        }
        
    }

    /**
     * Initializes the swap to the given directory.
     * <p>
     * The directory will be deleted on close.
     * @param metamodel the metamodel
     * @param swapDirectory The swap path. Must be a directory, preferably empty.
     */
    @objid ("f5f214d7-84b5-11e1-b644-001ec947ccaf")
    public  JdbmSwap(SmMetamodel metamodel, final File swapDirectory) {
        this.swapPath = swapDirectory.getAbsolutePath();
        this.dataSerializer = new CacheEntrySerializer(metamodel);
        
        try {
            Properties props = new Properties();
            props.setProperty(RecordManagerOptions.DISABLE_TRANSACTIONS, "true");
            props.setProperty(RecordManagerOptions.CACHE_TYPE, "none");
            props.setProperty(RecordManagerOptions.CACHE_SIZE, "0");
            this.db = RecordManagerFactory.createRecordManager(this.swapPath+"/swap", props );
        
            this.uidMap = this.db.hashMap("uid", null, LongSerializer.instance);
        
        } catch (IOException e) {
            throw new IOError(e);
        }
        
        //        Log.trace("Swap initialized to:"+swapDirectory);
        
    }

    @objid ("f5f214dd-84b5-11e1-b644-001ec947ccaf")
    private void commitSometimes() throws IOException {
        this.opCount++;
        if (this.opCount == commitFreq) {
            //            Log.trace("Swap commiting swap...");
            this.db.commit();
            //            Log.trace("   commiting swap done.");
        
            this.opCount = 0;
        }
        
    }

    @objid ("f5f214df-84b5-11e1-b644-001ec947ccaf")
    @Override
    protected void finalize() throws Throwable {
        close();
        
        super.finalize();
        
        
    }

    @objid ("f5f214e2-84b5-11e1-b644-001ec947ccaf")
    @Override
    public void close() {
        if (this.db != null) {
            try {
                this.db.close();
                this.db = null;
                FileUtils.delete(this.swapPath);
            } catch (IOException e) {
                Log.warning("Failed to close and delete the swap space on "+this.swapPath+":");
                Log.warning(e);
            }
        }
        
    }

    /**
     * Set the commit to disk frequency.
     * <p>
     * The value is the number of call to {@link #swap(SmObjectData)} leading to one commit.
     * @return the commit to swap frequency.
     */
    @objid ("dcbc24f5-493b-11e2-91c9-001ec947ccaf")
    public static int getCommitFreq() {
        return commitFreq;
    }

    /**
     * Set the commit to disk frequency.
     * <p>
     * The value is the number of call to {@link #swap(SmObjectData)} leading to one commit.
     * @param commitFreq the commit to swap frequency
     */
    @objid ("dcbc24fa-493b-11e2-91c9-001ec947ccaf")
    public static void setCommitFreq(int commitFreq) {
        JdbmSwap.commitFreq = commitFreq;
    }

    @objid ("f5f214d2-84b5-11e1-b644-001ec947ccaf")
    static class CacheEntry implements Serializable {
        @objid ("f5f214d5-84b5-11e1-b644-001ec947ccaf")
        int metaId;

        @objid ("f5f214d6-84b5-11e1-b644-001ec947ccaf")
        int storeHandleId;

        @objid ("a387c84c-84b6-11e1-b644-001ec947ccaf")
        private static final long serialVersionUID = 941804254816339523L;

        @objid ("f5f214d4-84b5-11e1-b644-001ec947ccaf")
        SmObjectData data;

        @objid ("dd913d06-cb55-11e1-87f1-001ec947ccaf")
        public  CacheEntry() {
            // nothing to do
        }

    }

    /**
     * Index that assigns an unique integer to an object and can retrieve one from the other.
     * <p>
     * The objects are all stored by weak reference.
     * 
     * @param <T> the type of the elements to index.
     */
    @objid ("f5f214e6-84b5-11e1-b644-001ec947ccaf")
    private static class Index<T> {
        @objid ("f5f214ea-84b5-11e1-b644-001ec947ccaf")
        private int indexCount = 0;

        @objid ("f6d492b0-84b5-11e1-b644-001ec947ccaf")
        private Map<T, Integer> objectToIdMap = new WeakHashMap<>();

        @objid ("f5f214ef-84b5-11e1-b644-001ec947ccaf")
        private Map<Integer, WeakReference<T>> idToObjectMap = new HashMap<>();

        @objid ("f5f214f6-84b5-11e1-b644-001ec947ccaf")
        public synchronized int getId(final T repositoryObject) {
            if (repositoryObject == null) {
                throw new IllegalArgumentException("null not permitted.");
            }
            
            Integer i = this.objectToIdMap.get(repositoryObject);
            if (i == null) {
                i = Integer.valueOf(this.indexCount);
                this.objectToIdMap.put(repositoryObject, i);
                this.idToObjectMap.put(i, new WeakReference<>(repositoryObject));
                this.indexCount++;
            }
            return i.intValue();
        }

        @objid ("f5f214fd-84b5-11e1-b644-001ec947ccaf")
        public synchronized T getObject(final int index) {
            WeakReference<T> ref = this.idToObjectMap.get(Integer.valueOf(index));
            
            assert (ref != null) : (index+" object not in map.");
            assert (ref.get() != null) : (index+" object no more in memory.");
            return ref.get();
        }

        @objid ("dd913d08-cb55-11e1-87f1-001ec947ccaf")
        public  Index() {
            // nothing to do
        }

    }

}
