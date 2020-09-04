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

package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.SecondaryKeyExtractor;
import jdbm.helper.StoreReference;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.index.IUserNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ObjId;

/**
 * JDBM based implementation of {@link IUserNodeIndex}.
 */
@objid ("0130fc85-2004-4df0-82d9-90ca1fdf95c1")
class UserNodeIndex implements IUserNodeIndex {
    @objid ("297b66eb-ccd2-451e-8b52-9d12e9e5d79a")
    private Map<ObjId , Iterable<ObjId>> usersInverse;

    /**
     * key = used object id.
     * value = collection of user CMS nodes id.
     */
    @objid ("98759fc8-70b5-455d-af9b-59ec4e9ee0d2")
    private PrimaryHashMap<ObjId,StoreReference<Collection<ObjId>>> users;

    @objid ("ecf8ed73-bd82-4220-aa10-39571fabf829")
    private RecordManager db;

    @objid ("d41c7c0d-ebf7-45a4-9fe1-144536cca556")
    private final ObjIdSerializer objIdSerializer;

    @objid ("d7deb86b-5711-4321-9252-cca7daa6a66e")
    private final ObjIdCollectionSerializer idCollSerializer;

    /**
     * Initialize the index.
     * @param metamodel
     * 
     * @param db the JDBM database.
     * @throws org.modelio.vstore.exml.common.index.IndexException if the index is broken
     */
    @objid ("b0393a03-3bf0-4f22-9fe6-b8a73ca124ea")
    public UserNodeIndex(final RecordManager db, SmMetamodel metamodel) throws IndexException {
        this.db = db;
        this.objIdSerializer = new ObjIdSerializer(metamodel);
        this.idCollSerializer = new ObjIdCollectionSerializer(this.objIdSerializer);
        try {
            this.users = db.hashMap("users", this.objIdSerializer);
            initReverseIndexes();
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("d853b634-4027-4680-a4f0-f47362643b26")
    private Collection<ObjId> getObjectUsers(final ObjId objectId) throws IndexException {
        try {
            StoreReference<Collection<ObjId>> usersRef = this.users.get(objectId);
            if (usersRef == null) {
                return Collections.emptyList();
            } else {
                return usersRef.get(this.db, this.idCollSerializer);
            }
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (NullPointerException e) {
            dumpUsers(System.err);
            throw e;
        }
    }

    @objid ("e38bbb7d-fdd5-4fe4-9c7d-505f2470b45c")
    private void addUsed(final ObjId userNodeId, final ObjId usedObjectId) throws IndexException {
        try {
            addTo(this.users, usedObjectId, userNodeId);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("68660f8e-acb0-45af-88cf-3e223edab386")
    @Override
    public void remove(final ObjId toRemove) throws IndexException {
        try {
            //Remove from the used list
            Iterable<ObjId> used = this.usersInverse.get(toRemove);
            if (used != null) {
                for (ObjId id : used) {
                    removeFrom(this.users, id, toRemove);
                }
            }
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("145778e9-9ff1-4a59-8151-d1e26cb7f8b5")
    private void initReverseIndexes() {
        SecondaryKeyExtractor<Iterable<ObjId>, ObjId, StoreReference<Collection<ObjId>>> secKeyExtractor = new SecondaryKeyExtractor<Iterable<ObjId>, ObjId, StoreReference<Collection<ObjId>>>() {
        
            @SuppressWarnings("synthetic-access")
            @Override
            public Iterable<ObjId> extractSecondaryKey(ObjId key,
                    StoreReference<Collection<ObjId>> value) {
                return value.get(UserNodeIndex.this.db, UserNodeIndex.this.idCollSerializer);
            }
        };
        
        ObjIdIterableSerializer iterableSerializer = new ObjIdIterableSerializer(this.idCollSerializer);
        
        this.usersInverse = SecondaryKeyHelper.secondaryHashMapManyToOne("usersInverse",
                secKeyExtractor ,
                this.users,
                this.objIdSerializer,
                iterableSerializer);
    }

    @objid ("41f7326d-10d5-41ae-9cb4-4bc0feef38e2")
    private Collection<ObjId> load(final StoreReference<Collection<ObjId>> storeReference) {
        return storeReference.get(this.db, this.idCollSerializer);
    }

    @objid ("a741aad8-a39c-419c-884b-4afc67f94d89")
    private void addTo(final Map<ObjId, StoreReference<Collection<ObjId>>> map, final ObjId k, final ObjId v) throws IOException {
        Collection<ObjId> l = null;
        StoreReference<Collection<ObjId>> ref = map.get(k);
        if (ref == null) {
            l = new ArrayList<>(1);
            l.add(v);
            ref = new StoreReference<>(this.db, l, this.idCollSerializer);
            map.put(k, ref);
        } else {
            l = load(ref);
            l.add(v);
            this.db.update(ref.getRecId(), l, this.idCollSerializer);
        }
    }

    @objid ("0a730636-1b0f-43e1-b94a-b7ee2442d639")
    private void removeFrom(final Map<ObjId, StoreReference<Collection<ObjId>>> map, final ObjId k, final ObjId v) throws IOException {
        StoreReference<Collection<ObjId>> ref = map.get(k);
        if (ref != null) {
            Collection<ObjId> l = load(ref);
            l.remove(v);
            this.db.update(ref.getRecId(), l, this.idCollSerializer);
        }
    }

    @objid ("833ee005-7770-4168-a553-2477cb11037d")
    private void dumpUsers(final PrintStream out) {
        out.println("Users CMS nodes index dump:");
        for (Entry<ObjId, StoreReference<Collection<ObjId>>>  en: this.users.entrySet()) {
            out.println(" - "+en.getKey()+" used by:");
            for (ObjId  user: en.getValue().get(this.db, this.idCollSerializer)) {
                out.println("   - "+user);
            }
        }
    }

    @objid ("51c8b388-bbd3-4a01-87d0-62487ee44723")
    @Override
    public Collection<ObjId> getObjectUsers(ObjId objectId, String depName) throws IndexException {
        return getObjectUsers(objectId);
    }

    @objid ("da880f32-6f7e-4f57-92b8-3860ffe3df31")
    @Override
    public void addUsed(ObjId userNodeId, String depName, ObjId usedObjectId) throws IndexException {
        addUsed(userNodeId, usedObjectId);
    }

}
