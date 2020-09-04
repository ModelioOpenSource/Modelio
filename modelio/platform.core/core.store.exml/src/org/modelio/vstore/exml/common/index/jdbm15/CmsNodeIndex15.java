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

package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.index.ICmsNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;

/**
 * JDBM based implementation of {@link ICmsNodeIndex}.
 */
@objid ("b9d30a07-a8f2-4a9a-8b5c-6b22c2f5bc16")
class CmsNodeIndex15 implements ICmsNodeIndex {
    @objid ("aaf828a7-f629-4d99-92fa-db431f412b00")
    private PrimaryHashMap<ObjId,StoreReference<Collection<ObjId>>> cmsNodeContent;

    @objid ("1cd2c957-a88c-4000-b1a8-a408085fdfa7")
    private RecordManager db;

    @objid ("3cd2d35c-79b2-420b-a327-fd4e7fbc1948")
    private InverseHashView<ObjId,ObjId> inverseParent;

    /**
     * 'model object -> CMS node' index.
     * <p>
     * Also serve as global object index.
     * The key is a model object and the value the CMS node
     * it is stored into. A CMS node is stored into itself.
     */
    @objid ("ee0709b1-3922-4137-bed7-dded630627d2")
    private Map<MClass , PrimaryHashMap<String,ObjectIndexValue>> objectsIndex;

    /**
     * Child CMS node -> parent CMS node index.
     */
    @objid ("945eeda1-461b-4a88-ab69-07d014e3310c")
    private PrimaryHashMap<ObjId,ObjId> parentIndex;

    @objid ("3c59278d-a901-40b2-b24c-21ea0695530d")
    private final ObjIdCollectionSerializer idCollSerializer;

    @objid ("d18458eb-d81b-4790-ab02-85a5de7cb07c")
    private final ObjectIndexValueSerializer objIndexValueSerializer;

    /**
     * Initialize the index.
     * @param metamodel
     * @param db the JDBM database string the index.
     * @throws org.modelio.vstore.exml.common.index.IndexException if the index is broken
     */
    @objid ("489783e0-c574-489f-ae0f-6ede7fb44a33")
    public CmsNodeIndex15(final RecordManager db, SmMetamodel metamodel) throws IndexException {
        try {
            this.db = db;
            ObjIdSerializer idSerializer = new ObjIdSerializer(metamodel);
            this.objIndexValueSerializer = new ObjectIndexValueSerializer(idSerializer);
            this.idCollSerializer = new ObjIdCollectionSerializer(idSerializer);
        
            this.parentIndex = db.hashMap("parentIndex", idSerializer, idSerializer);
        
            this.objectsIndex = new HashMap<>();
        
            this.cmsNodeContent = db.hashMap("cmsNodeContent", idSerializer);
        
            initReverseIndexes();
        
            //dumpIndex(db);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e7e7b112-d7b7-4c9d-83c4-bb995c3282fb")
    @Override
    public void addCmsNode(final ObjIdName idn) throws IndexException {
        try {
            getClassObjIndex(idn.classof).put(idn.id, new ObjectIndexValue(idn.name, idn.toObjId()));
        
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("21864b3e-6360-494c-bb15-4f960dbd2b9c")
    @Override
    public void addObject(final ObjId cmsNodeId, final ObjIdName objectIdn) throws IndexException {
        try {
            getClassObjIndex(objectIdn.classof).put(objectIdn.id, new ObjectIndexValue(objectIdn.name, cmsNodeId));
            addCmsNodeContent(cmsNodeId, objectIdn.toObjId());
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("ef11a58d-9b68-4e9b-b9e5-5d88d9a89292")
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

    @objid ("0030fc09-4730-4549-8b22-886e38494471")
    @Override
    public ObjId getCmsNodeOf(final ObjId id) throws IndexException {
        try {
            final ObjectIndexValue idxValue = findObjectIndexEntry(id);
            if (idxValue == null) {
                return null;
            } else {
                return idxValue.cmsNodeId;
            } 
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("938e183c-2d5f-4607-8b82-0016151e69a3")
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

    @objid ("21d3a86f-2552-4cd0-8b1e-11b4b10158c0")
    @Override
    public ObjId getParentNodeOf(final ObjId id) throws IndexException {
        try {
            return this.parentIndex.find(id);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("e2941c29-c452-4f0c-ae09-4cb97f620982")
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

    @objid ("43f993ca-0e9c-4138-ad69-f4c00de95aa2")
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

    @objid ("9f843147-879a-4f2b-aa55-c9f283f687a8")
    @Override
    public void removeObj(final ObjId id) throws IndexException {
        try {
            Iterable<ObjId> nodeContent = load(this.cmsNodeContent.get(id));
            if (nodeContent != null) {
                // Remove CMS node elements from the index
                for (ObjId child : nodeContent) {
                    Map<String, ObjectIndexValue> childIndex = getClassObjIndex(child.classof);
                    ObjectIndexValue val = childIndex.remove(child.id);
                    if (val != null && !id.equals(val.cmsNodeId)) {
                        // The child has moved to another CMS node, put it back into the index.
                        // perf note: as moving occurs only sometime I prefer removing the element always
                        // and put it back if it was wrong instead of first reading it then delete it.
                        // It spares an index access in most cases.
                        childIndex.put(child.id, val);
                    }
        
                }
        
                // Remove the CMS node content entry
                this.cmsNodeContent.remove(id);
            }
        
            // For non CMS node objects, remove it from the CMS node index
            final ObjectIndexValue idxVal = findObjectIndexEntry(id);
            if (idxVal != null) {
                ObjId cmsParent = idxVal.cmsNodeId;
                removeFromCmsNodeContent(cmsParent, id);
                getClassObjIndex(id.classof).remove(id.id);
            }
        
            this.parentIndex.remove(id);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("5842a78f-7713-42d2-9048-db63a3ca0935")
    @Override
    public void setParent(final ObjId cmsNodeId, final ObjId parentId) throws IndexException {
        this.parentIndex.put(cmsNodeId, parentId);
    }

    @objid ("bcaea031-fb39-48c2-80de-8ee93aa0fe20")
    private void addCmsNodeContent(ObjId parent, ObjId child) throws IOException {
        StoreReference<Collection<ObjId>> ref = this.cmsNodeContent.get(parent);
        if (ref == null) {
            Collection<ObjId> l = new ArrayList<>(1);
            l.add(child);
            ref = new StoreReference<>(this.db, l, this.idCollSerializer);
            this.cmsNodeContent.put(parent, ref);
        } else {
            Collection<ObjId> l = load(ref);
            l.add(child);
            this.db.update(ref.getRecId(), l, this.idCollSerializer);
        }
    }

    @objid ("56883799-5514-479b-a653-31209c2ffc67")
    private void dumpIndex() {
        System.out.println(" Dumping CmsNodeIndex.objectsIndex:");
        
        for (Entry<MClass, PrimaryHashMap<String, ObjectIndexValue>>  entry: this.objectsIndex.entrySet()) {
            PrimaryHashMap<String, ObjectIndexValue> objIndex = entry.getValue();
            if (objIndex != null) {
                System.out.println("  " + objIndex.size()+" "+entry.getKey().getQualifiedName());
            }
        }
        
        System.out.println("\n\n Dumping CmsNodeIndex.parentIndex:");
        for (Entry<ObjId, ObjId> i : this.parentIndex.entrySet()) {
            System.out.println("  "+ i.getKey()+" parent = "+i.getValue());
        }
        
        System.out.println("  " + this.parentIndex.size()+" elements in parentIndex");
        //System.out.println("  " + this.inverseParent.size()+" elements in inverseParent");
        //System.out.println("  " + this.inverseObjectToNode.size()+" elements in inverseObjectToNode");
    }

    /**
     * Get the object index where this metaclass elements should be stored.
     * <p>
     * Return <i>null</i> if the index is missing.
     * @param cls a metaclass
     * @return its index or <i>null</i>.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("3f134ec4-fc32-4091-8dbe-bcdf20f5f20f")
    private PrimaryHashMap<String,ObjectIndexValue> findObjectIndex(MClass cls) throws IOException {
        PrimaryHashMap<String, ObjectIndexValue> idx = this.objectsIndex.get(cls);
        if (idx == null && !this.objectsIndex.containsKey(cls)) {
            long r = this.db.getNamedObject("objectsIndex."+cls.getQualifiedName());
            if (r != 0) {
                // load the index
                return getClassObjIndex(cls);
            } else {
                // remember there is no index for this class
                this.objectsIndex.put(cls, null);
            }
        }
        return  idx;
    }

    /**
     * Find the object index entry.
     * <p>
     * Return <i>null</i> if the object is not in the index.
     * @param id the object identifier
     * @return the index entry or <i>null</i>.
     * @throws java.io.IOException in case of I/O error.
     */
    @objid ("b5e6ad14-4e91-4015-aae3-8a0f72add130")
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
     * @param cls a metaclass
     * @return the metaclass objects index.
     */
    @objid ("8644e2a7-8c71-4fd9-821a-304a24e2ee8b")
    private PrimaryHashMap<String,ObjectIndexValue> getClassObjIndex(MClass cls) {
        PrimaryHashMap<String, ObjectIndexValue> ret = this.objectsIndex.get(cls);
        if (ret == null) {
            ret = this.db.hashMap("objectsIndex."+cls.getQualifiedName(), null, this.objIndexValueSerializer);
            this.objectsIndex.put(cls, ret);
        
        }
        return ret;
    }

    @objid ("13740e09-d133-4395-8e75-070d0d3c7f0d")
    private void initReverseIndexes() {
        ObjIdIterableSerializer s = new ObjIdIterableSerializer(this.idCollSerializer);
        this.inverseParent = SecondaryKeyHelper.inverseHashView(this.parentIndex, "inverseParent", s);
    }

    @objid ("93af3000-0f15-4d40-972c-c27f376f326c")
    private Collection<ObjId> load(final StoreReference<Collection<ObjId>> storeReference) {
        if (storeReference==null) {
            return null;
        } else {
            return storeReference.get(this.db, this.idCollSerializer);
        }
    }

    @objid ("2b53eabc-c540-44a3-8da5-ba5dd94b4d09")
    private void removeFromCmsNodeContent(ObjId cmsParent, ObjId id) throws IOException {
        StoreReference<Collection<ObjId>> ref = this.cmsNodeContent.get(cmsParent);
        if (ref != null) {
            Collection<ObjId> l = load(ref);
            l.remove(id);
            if (l.isEmpty()) {
                ref.remove(this.db);
                this.cmsNodeContent.remove(cmsParent);
            } else {
                this.db.update(ref.getRecId(), l, this.idCollSerializer);
            }
        }
    }

    @objid ("3b7be5b0-71b4-4777-9e5b-74fdb5f4f24f")
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

    @objid ("8032cb18-9e6a-4ebc-ac33-9e6778af5306")
    @Override
    public Iterable<ObjId> getCmsNodeContent(ObjId cmsNodeId) throws IndexException {
        // TODO Auto-generated method stub
        StoreReference<Collection<ObjId>> contentRef;
        try {
            contentRef = this.cmsNodeContent.find(cmsNodeId);
            if (contentRef == null) {
                return Collections.emptyList();
            }
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
        return null;
    }

}
