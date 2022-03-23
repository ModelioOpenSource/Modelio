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
package org.modelio.vstore.jdbm7.index;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.jdbm7.impl.Helper;

/**
 * Indexes
 */
@objid ("10553ffb-2ec7-4a86-8204-dad07faec2e7")
public class JdbmIndex {
    /**
     * Local objects used by a foreign object.
     * <p>
     * Inverse of {@link #users}
     */
    @objid ("70e01768-e3d9-48a4-a018-a349c913fe55")
    private PrimaryHashMap<MRef, Collection<MRef>> users;

    /**
     * Foreign objects used by an object
     */
    @objid ("26bec644-41bc-46f0-8de6-3064fd70da52")
    private Map<MRef , Collection<MRef>> uses;

    @objid ("00df0c10-b822-4c2a-9a7f-343d2774b594")
    private RecordManager db;

    @objid ("65baccba-a2dc-4764-85fa-eedb9c626bd8")
    private static final String IDX_CLASS_PREFIX = "idx.class.";

    /**
     * global object index.
     * The key is a model object and the value the CMS node
     * it is stored into. A CMS node is stored into itself.
     */
    @objid ("20e8e387-258a-4cd6-99e2-8108a56b216b")
    private Map<String, Map<String, Boolean>> objectsIndex;

    /**
     * Initialize the index.
     * @param db the JDBM database storing the index.
     * @throws IOException in case of I/O error
     */
    @objid ("a8766620-9d96-41a2-8266-0e1a47c8de61")
    public  JdbmIndex(final RecordManager db) throws IOException {
        this.db = db;
        try {
            this.objectsIndex = new HashMap<>();
            this.users = db.hashMap("idx.users", MRefSerializer.instance, MRefCollectionSerializer.instance);
            this.uses = db.hashMap("idx.uses", MRefSerializer.instance, MRefCollectionSerializer.instance);
        
            //dumpIndex(db);
        } catch (IOError e) {
            throw new IOException(e);
        }
        
    }

    /**
     * Get all instances of a metaclass.
     * @param cls a metaclass
     * @return all stored instances references.
     * @throws IOError in case of I/O error
     */
    @objid ("5dc411bf-c15d-4a59-a0bc-8b2eab3a3bfc")
    public Collection<String> getByMClass(final SmClass cls) throws IOError {
        return getObjectIndex(cls).keySet();
    }

    /**
     * Remove the given object from main indexes.
     * @param id the reference to remove
     * @throws IOError in case of I/O failure
     */
    @objid ("d95fd7c2-7a46-4a3a-82bf-efe6a724c3a5")
    public void removeObj(final MRef id) throws IOError {
        getObjectIndex(id.mc).remove(id.uuid);
    }

    @objid ("6df13612-2709-44c4-9292-c3249fe393a1")
    private static void addTo(Map<MRef, Collection<MRef>> map, MRef k, Collection<MRef> newUsers) throws IOError {
        Collection<MRef> ref = map.get(k);
        if (ref == null) {
            map.put(k, newUsers);
        } else {
            ref.addAll(newUsers);
            map.put(k, ref);
        }
        
    }

    @objid ("0d0d3039-b6b2-4672-96d0-8bb529a56361")
    private void dumpForeignUsers(final PrintStream out) {
        out.println("Foreign user object index dump:");
        for (Entry<MRef, Collection<MRef>>  en: this.users.entrySet()) {
            out.println(" - "+en.getKey()+" used by:");
            for (MRef  user: en.getValue()) {
                out.println("   - "+user);
            }
        }
        
    }

    /**
     * Get local objects referring to the given local or foreign object.
     * @param objRef a local or foreign object
     * @return all referred local objects.
     * @throws IOError in case of I/O error
     */
    @objid ("f859860b-1348-4c79-a7e5-2ba3894d623e")
    public Collection<MRef> getUserRefs(final MRef objRef) throws IOError {
        try {
            Collection<MRef> objForeign = this.users.get(objRef);
            if (objForeign == null) {
                return Collections.emptyList();
            } else {
                return objForeign;
            }
        } catch (NullPointerException e) {
            dumpForeignUsers(System.err);
            throw e;
        }
        
    }

    /**
     * Remove the given object from cross reference indexes.
     * @param toRemove the reference to remove
     * @throws IOError in case of I/O failure
     */
    @objid ("203a10f1-eefe-4060-b4ca-58ab31fd4846")
    public void removeCrossRefs(final MRef toRemove) throws IOError {
        Collection<MRef> objUses = this.uses.get(toRemove);
        if (objUses != null) {
            for (MRef usedObj : objUses) {
                Collection<MRef> usedUsers = this.users.get(usedObj);
                if (usedUsers != null) {
                    usedUsers.remove(toRemove);
                    if (usedUsers.isEmpty()) {
                        this.users.remove(usedObj);
                    } else {
                        this.users.put(usedObj, usedUsers);
                    }
                }
            }
            this.uses.remove(toRemove);
        }
        
        this.users.remove(toRemove);
        
    }

    /**
     * Index the given model object.
     * @param obj a model object to index
     * @throws IOError in case of I/O failure
     */
    @objid ("21133a92-f024-4163-babf-a50c59d01367")
    public void addToMain(SmObjectImpl obj) throws IOError {
        MClass mClass = obj.getMClass();
        String uuid = obj.getUuid();
        addRefToMain(mClass, uuid);
        
    }

    /**
     * Add the given model object to cross references indexes.
     * <p>
     * Call {@link #removeCrossRefs(MRef)} first if the object was already in the index.
     * @param obj a model object to index
     * @throws IOException in case of I/O failure
     */
    @objid ("60aaf68b-fe2b-4246-98b3-4a4023a2cb29")
    public void addCrossRefs(SmObjectImpl obj) throws IOException {
        try {
            MRef objRef = new MRef(obj);
        
            Collection<MRef> newUsers = new ArrayList<>();
            Collection<MRef> newUses = new ArrayList<>();
        
            //Foreign references
            for (SmDependency dep : obj.getClassOf().getAllDepDef()) {
                if (Helper.isPersistent(dep)) {
                    if (!Helper.isPersistent(dep.getSymetric())) {
                        // one way navigable
                        for (SmObjectImpl val : obj.getDepValList(dep)) {
                            MRef valRef = new MRef(val);
        
                            addTo(this.users, valRef, objRef);
                            newUses.add(valRef);
                        }
                    }
                } 
                /* 25/10/2016: Opposite roles should not be indexed
                 * else {
                    // This is the opposite role
                    for (SmObjectImpl val : obj.getDepValList(dep)) {
                        MRef valRef = new MRef(val);
                        newUsers.add(valRef);
                        addTo(this.uses, valRef, objRef);
                    }
                }
                */
            }
        
            addTo(this.users, objRef, newUsers);
            addTo(this.uses, objRef, newUses);
        
        } catch (IOError e ) {
            throw new IOException(e);
        }
        
    }

    @objid ("74eca7df-d7e4-412f-9391-058353fad1a4")
    private static void addTo(final Map<MRef, Collection<MRef>> map, final MRef k, final MRef v) throws IOError {
        Collection<MRef> l = map.get(k);
        if (l == null) {
            l = new ArrayList<>(1);
            l.add(v);
            map.put(k, l);
        } else {
            l.add(v);
            map.put(k, l);
        }
        
    }

    /**
     * Get the object index for the given metaclass.
     * @param mClass a metaclass
     * @return the class objects index
     */
    @objid ("6e9b49b9-1ce6-479d-8099-2309968c9e5e")
    protected Map<String, Boolean> getObjectIndex(final MClass mClass) {
        String clsName = mClass.getName();
        return getObjectIndex(clsName);
    }

    /**
     * Get the object index for the given metaclass.
     * @param className a metaclass name
     * @return the class objects index
     */
    @objid ("bce252d6-8b3a-420e-825f-1387f766ffb1")
    protected Map<String, Boolean> getObjectIndex(String className) {
        Map<String, Boolean> idx = this.objectsIndex.get(className);
        if (idx == null) {
            //FIXME handle class name collision in different metamodels
            idx = this.db.hashMap(IDX_CLASS_PREFIX+className,
                    null, null);
        
        }
        return idx;
    }

    /**
     * Add the given reference to the main index
     * @param mClass the metaclass
     * @param uuid the UUID
     */
    @objid ("93337cf4-1943-4232-9e59-b6575e17c481")
    public void addRefToMain(MClass mClass, String uuid) {
        Map<String, Boolean> ref = getObjectIndex(mClass);
        ref.put(uuid, Boolean.TRUE);
        
    }

    @objid ("5af04c5d-8acb-4222-ad9e-a01c7bf9041f")
    public void addCrossRef(MRef objRef, SmDependency dep, Collection<MRef> values) {
        if (Helper.isPersistent(dep)) {
            if (!Helper.isPersistent(dep.getSymetric())) {
                // one way navigable
                for (MRef valRef : values) {
                    addTo(this.users, valRef, objRef);
                    addTo(this.uses, objRef, valRef);
                }
            }
        } else {
            // This is the opposite role
            for (MRef valRef : values) {
                addTo(this.uses, valRef, objRef);
                addTo(this.users, objRef, valRef);
            }
        }
        
    }

}
