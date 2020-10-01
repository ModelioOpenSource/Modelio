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

package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.InverseHashView;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.helper.StoreReference;
import org.modelio.vbasic.files.StreamException;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vstore.exml.common.index.ICmsNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;

/**
 * JDBM based implementation of {@link ICmsNodeIndex}.
 */
@objid ("e1ee6207-5c83-11e1-863f-001ec947ccaf")
public class CmsNodeIndex implements ICmsNodeIndex {
    @objid ("d554d657-7f1a-11e1-ba70-001ec947ccaf")
    private PrimaryHashMap<Long,StoreReference<Collection<Long>>> cmsNodeContent;

    @objid ("d554d65c-7f1a-11e1-ba70-001ec947ccaf")
    private RecordManager db;

    @objid ("d554d652-7f1a-11e1-ba70-001ec947ccaf")
    private InverseHashView<Long,Long> inverseParent;

    /**
     * 'model object -> CMS node' index.
     * <p>
     * Also serves as global object index.
     * The key is a model object and the value the CMS node
     * it is stored into. A CMS node is stored into itself.
     */
    @objid ("d55273fe-7f1a-11e1-ba70-001ec947ccaf")
    private Map<MClass , PrimaryHashMap<String,ObjectIndexValue>> objectsIndex;

    /**
     * Child CMS node -> parent CMS node index.
     */
    @objid ("d554d64c-7f1a-11e1-ba70-001ec947ccaf")
    private PrimaryHashMap<Long,Long> parentIndex;

    @objid ("fdfe958c-883f-4278-ab20-09b7a5eb0026")
    private final ObjectIndexValueSerializer objIndexValueSerializer;

    @objid ("b5d3ae22-9d63-4445-8501-9732b7f6faea")
    private final SymbolTable<ObjId> objIdTable;

    /**
     * Initialize the index.
     * @param objIdTable
     * 
     * @param db the JDBM database string the index.
     * @throws org.modelio.vstore.exml.common.index.IndexException if the index is broken
     */
    @objid ("e1ee620c-5c83-11e1-863f-001ec947ccaf")
    public CmsNodeIndex(final RecordManager db, SymbolTable<ObjId> objIdTable) throws IndexException {
        try {
            this.objIdTable = objIdTable;
            this.db = db;
            this.objIndexValueSerializer = new ObjectIndexValueSerializer();
        
            this.parentIndex = db.hashMap("parentIndex");
        
            this.objectsIndex = new HashMap<>();
        
            this.cmsNodeContent = db.hashMap("cmsNodeContent");
        
            initReverseIndexes();
        
            //dumpIndex(db);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee620e-5c83-11e1-863f-001ec947ccaf")
    @Override
    public void addCmsNode(final ObjIdName idn) throws IndexException {
        try {
            getClassObjIndex(idn.classof).put(
                    idn.id, 
                    new ObjectIndexValue(
                            idn.name, 
                            this.objIdTable.getOrAddKey(idn.toObjId())));
        
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee620d-5c83-11e1-863f-001ec947ccaf")
    @Override
    public void addObject(final ObjId cmsNodeId, final ObjIdName objectIdn) throws IndexException {
        try {
            getClassObjIndex(objectIdn.classof).put(objectIdn.id, 
                    new ObjectIndexValue(objectIdn.name,
                            this.objIdTable.getOrAddKey(cmsNodeId)));
            addCmsNodeContent(cmsNodeId, objectIdn.toObjId());
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee61ff-5c83-11e1-863f-001ec947ccaf")
    @Override
    public Collection<String> getByMClass(final SmClass cls) throws IndexException {
        try {
            PrimaryHashMap<String, ObjectIndexValue> clsIndex = findObjectIndex(cls);
            if (clsIndex == null) {
                return Collections.emptyList();
            } else {
                return clsIndex.keySet();
            }
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee6212-5c83-11e1-863f-001ec947ccaf")
    @Override
    public ObjId getCmsNodeOf(final ObjId id) throws IndexException {
        try {
            final ObjectIndexValue idxValue = findObjectIndexEntry(id);
            if (idxValue == null) {
                return null;
            } else {
                return this.objIdTable.getValue(idxValue.cmsNodeLid);
            } 
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e7ff26c9-55ba-11e2-81b0-001ec947ccaf")
    @Override
    public String getName(ObjId id) throws IndexException {
        try {
            final ObjectIndexValue idxValue = findObjectIndexEntry(id);
            if (idxValue == null) {
                return null;
            } else {
                return idxValue.name;
            } 
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee620f-5c83-11e1-863f-001ec947ccaf")
    @Override
    public ObjId getParentNodeOf(final ObjId id) throws IndexException {
        try {
            long lid = this.objIdTable.findKey(id);
            if (lid==-1) {
                return null;
            }
                
            Long parentk = this.parentIndex.find(lid);
            return parentk == null ? null : this.objIdTable.getValue(parentk);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("82d690c7-5ca7-11e1-863f-001ec947ccaf")
    @Override
    public boolean isEmpty() throws IndexException {
        try {
            // JDBM has a inefficient implementation of isEmpty() that iterates the whole map.
            return ! this.cmsNodeContent.entrySet().iterator().hasNext();
            //return this.cmsNodeContent.isEmpty();
        
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee61fd-5c83-11e1-863f-001ec947ccaf")
    @Override
    public boolean isStored(final ObjId id) throws IndexException {
        try {
            return findObjectIndexEntry(id) != null;
        
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee61fe-5c83-11e1-863f-001ec947ccaf")
    @Override
    public void removeObj(final ObjId id) throws IndexException {
        try {
            long lid = this.objIdTable.findKey(id);
            Iterable<Long> nodeContent = load(this.cmsNodeContent.get(lid));
            if (nodeContent != null) {
                // Remove CMS node elements from the index
                for (Long childLid : nodeContent) {
                    ObjId child = this.objIdTable.getValue(childLid);
                    Map<String, ObjectIndexValue> childIndex = getClassObjIndex(child.classof);
                    ObjectIndexValue val = childIndex.remove(child.id);
                    if (val != null && lid != val.cmsNodeLid) {
                        // The child has moved to another CMS node, put it back into the objects index.
                        // perf note: as moving occurs only sometime I prefer to remove always the element
                        // then put it back if it was wrong instead of first reading it then delete it.
                        // It spares an index access in most cases.
                        childIndex.put(child.id, val);
                    }
        
                }
        
                // Remove the CMS node content entry
                this.cmsNodeContent.remove(lid);
            }
        
            // For non CMS node objects, remove it from the CMS node index
            final ObjectIndexValue idxVal = findObjectIndexEntry(id);
            if (idxVal != null) {
                removeFromCmsNodeContent(idxVal.cmsNodeLid, id);
                getClassObjIndex(id.classof).remove(id.id);
            }
        
            this.parentIndex.remove(lid);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e1ee6210-5c83-11e1-863f-001ec947ccaf")
    @Override
    public void setParent(final ObjId cmsNodeId, final ObjId parentId) throws IndexException {
        try {
            long cmsNodeLid = this.objIdTable.getOrAddKey(cmsNodeId);
            long parentLid = this.objIdTable.getOrAddKey(parentId);
            this.parentIndex.put(cmsNodeLid, parentLid);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("690abc20-4b8b-11e2-91c9-001ec947ccaf")
    private void addCmsNodeContent(ObjId parent, ObjId child) throws IOException {
        long parentLid = this.objIdTable.findKey(parent);
        long childLid = this.objIdTable.getOrAddKey(child);
        StoreReference<Collection<Long>> ref = this.cmsNodeContent.find(parentLid);
        if (ref == null) {
            Collection<Long> l = new ArrayList<>(1);
            l.add(childLid);
            ref = new StoreReference<>(this.db, l);
            this.cmsNodeContent.put(parentLid, ref);
        } else {
            Collection<Long> l = load(ref);
            l.add(childLid);
            this.db.update(ref.getRecId(), l);
        }
    }

    @objid ("86140e47-797c-11e1-9633-001ec947ccaf")
    private void dumpIndex() {
        Log.warning(" Dumping CmsNodeIndex.objectsIndex:");
        
        for (Entry<MClass, PrimaryHashMap<String, ObjectIndexValue>>  entry: this.objectsIndex.entrySet()) {
            PrimaryHashMap<String, ObjectIndexValue> objIndex = entry.getValue();
            if (objIndex != null) {
                Log.warning("  %d %s",objIndex.size(), entry.getKey().getQualifiedName());
            }
        }
        
        Log.warning("\n\n Dumping CmsNodeIndex.parentIndex:");
        for (Entry<Long, Long> i : this.parentIndex.entrySet()) {
            Log.warning("  %s parent = %s", dumpObjId(i.getKey()), dumpObjId(i.getValue()));
        }
        
        Log.warning("  %d elements in parentIndex", this.parentIndex.size());
        //Log.warning("  " + this.inverseParent.size()+" elements in inverseParent");
        //Log.warning("  " + this.inverseObjectToNode.size()+" elements in inverseObjectToNode");
    }

    /**
     * Get the object index where this metaclass elements should be stored.
     * <p>
     * Return <i>null</i> if the index is missing.
     * 
     * @param cls a metaclass
     * @return its index or <i>null</i>.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("a30fd2d3-d388-4c6e-ae38-91874118a257")
    private PrimaryHashMap<String,ObjectIndexValue> findObjectIndex(MClass cls) throws IOException {
        PrimaryHashMap<String, ObjectIndexValue> idx = this.objectsIndex.get(cls);
        if (idx == null && !this.objectsIndex.containsKey(cls)) {
            long r = this.db.getNamedObject("objectsIndex."+cls.getQualifiedName());
            if (r == 0) {
                // remember there is no index for this class
                this.objectsIndex.put(cls, null);
            } else {
                // load the index
                return getClassObjIndex(cls);
            }
        }
        return  idx;
    }

    /**
     * Find the object index entry.
     * <p>
     * Return <i>null</i> if the object is not in the index.
     * 
     * @param id the object identifier
     * @return the index entry or <i>null</i>.
     * @throws java.io.IOException in case of I/O error.
     */
    @objid ("1cdc4679-716e-4998-8622-b18745d7e5d6")
    private ObjectIndexValue findObjectIndexEntry(final ObjId id) throws IOException {
        PrimaryHashMap<String, ObjectIndexValue> objectIndex = findObjectIndex(id.classof);
        if (objectIndex == null) {
            return null;
        } else {
            return objectIndex.find(id.id);
        }
    }

    /**
     * Get the object index for a metaclass.
     * <p>
     * The index is created if missing.
     * 
     * @param cls a metaclass
     * @return the metaclass objects index.
     */
    @objid ("483c4f74-89a9-4ff6-93ed-f28dd157cf3d")
    private PrimaryHashMap<String,ObjectIndexValue> getClassObjIndex(MClass cls) {
        PrimaryHashMap<String, ObjectIndexValue> ret = this.objectsIndex.get(cls);
        if (ret == null) {
            ret = this.db.hashMap("objectsIndex."+cls.getQualifiedName(), null, this.objIndexValueSerializer);
            this.objectsIndex.put(cls, ret);
        
        }
        return ret;
    }

    @objid ("82d42eb7-5ca7-11e1-863f-001ec947ccaf")
    private void initReverseIndexes() {
        //ObjIdIterableSerializer s = new ObjIdIterableSerializer(this.idCollSerializer);
        this.inverseParent = SecondaryKeyHelper.inverseHashView(this.parentIndex, "inverseParent", null);
    }

    @objid ("82d690cd-5ca7-11e1-863f-001ec947ccaf")
    private Collection<Long> load(final StoreReference<Collection<Long>> storeReference) {
        if (storeReference==null) {
            return null;
        } else {
            return storeReference.get(this.db);
        }
    }

    @objid ("690abc25-4b8b-11e2-91c9-001ec947ccaf")
    private void removeFromCmsNodeContent(long cmsParentLid, ObjId id) throws IOException {
        StoreReference<Collection<Long>> ref = this.cmsNodeContent.find(cmsParentLid);
        if (ref != null) {
            Collection<Long> l = load(ref);
            l.remove(this.objIdTable.findKey(id));
            if (l.isEmpty()) {
                ref.remove(this.db);
                this.cmsNodeContent.remove(cmsParentLid);
            } else {
                this.db.update(ref.getRecId(), l);
            }
        }
    }

    @objid ("38572aad-c546-41d9-999b-b223c4a24323")
    @Override
    public Stream<ObjIdName> idByMClass(final SmClass cls) throws IndexException, StreamException {
        try {
            PrimaryHashMap<String, ObjectIndexValue> clsIndex = findObjectIndex(cls);
            if (clsIndex == null) {
                return Stream.empty();
            } else {
                return StreamSupport
                        .stream(new JdbmSpliterator<>(clsIndex.entrySet().spliterator()), false)
                        .map(entry -> new ObjIdName(cls, entry.getValue().name, entry.getKey()));
            }
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("b3a50054-f423-48a3-8ec2-27aa2e25b140")
    private String dumpObjId(Long idk) {
        if (idk == null) {
            return "<null>";
        } else {
            try {
                ObjId id = this.objIdTable.getValue(idk);
                if (id == null) {
                    return "<missing "+idk+">";
                } else {
                    return id.toString();
                }
            } catch (IOException e) {
                return idk + ": " + e.toString();
            }
        }
    }

    @objid ("a5fcee23-9ff0-4260-8954-ef749038555e")
    @Override
    public Iterable<ObjId> getCmsNodeContent(ObjId cmsNodeId) throws JdbmIndexException {
        try {
            long cmsNodeLid = this.objIdTable.findKey(cmsNodeId);
            if (cmsNodeLid==-1) {
                return Collections.emptyList();
            }
            
            Iterable<Long> contentLids = load(this.cmsNodeContent.find(cmsNodeLid));
            if (contentLids == null) {
                return Collections.emptyList();
            }
            
            return () -> {
                Iterator<Long> it = contentLids.iterator();
                return new Iterator<ObjId>() {
        
                    @Override
                    public ObjId next() {
                        try {
                            return CmsNodeIndex.this.objIdTable.getValue(it.next());
                        } catch (IOException e) {
                            throw new StreamException(e);
                        }
                    }
        
                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }
                };
            };
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

}
