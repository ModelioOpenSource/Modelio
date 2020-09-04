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

package org.modelio.vstore.jdbm.index;

import java.io.IOError;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.jdbm.JdbmRepository;
import org.modelio.vstore.jdbm.impl.Helper;

/**
 * Indexes for {@link JdbmRepository}
 */
@objid ("f44e42cc-7dcf-4393-af3b-b5560e8f19a3")
public class JdbmIndex {
    @objid ("ed6f78d7-536c-446b-8b93-4a266ba144a3")
    private final RecordManager db;

    @objid ("8e61ca9d-514f-4998-96a6-befe7bcc8eeb")
    private static final String IDX_CLASS_PREFIX = "idx.class.";

    /**
     * Global object index.
     * <p>
     * First key is the metaclass name.
     * The second key is the UUID, the value is not used.
     */
    @objid ("99a510e0-9b19-46fa-b8e9-95f0b475bce3")
    private final Map<String, Map<String,Boolean>> objectsIndex;

    @objid ("5d4ae898-8a3b-4a89-adb0-6b3b27f89b7c")
    private final CrossRefsIndex userIdx;

    /**
     * Initialize the index.
     * @param symbolTable
     * 
     * @param db the JDBM database storing the index.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("96390c9e-2b45-4baf-9495-3bd0c689ece1")
    public JdbmIndex(final RecordManager db, StringTable symbolTable) throws IOException {
        this.db = db;
        
        try {
            this.objectsIndex = new HashMap<>();
            this.userIdx = new CrossRefsIndex(db, symbolTable);
        
            //dumpIndex(db);
        } catch (IOError e) {
            throw new IOException(e);
        }
    }

    /**
     * Get all instances of a metaclass.
     * 
     * @param cls a metaclass
     * @return all stored instances references.
     * @throws java.io.IOError in case of I/O error
     */
    @objid ("8bd10374-024a-40b6-8b43-5c5cc8815ba6")
    public Collection<String> getByMClass(final SmClass cls) throws IOError {
        return getObjectIndex(cls).keySet();
    }

    /**
     * Remove the given object from main indexes.
     * 
     * @param id the reference to remove
     * @throws java.io.IOError in case of I/O failure
     */
    @objid ("1ad1908d-044c-494c-84b3-a3c010e4183c")
    public void removeObj(final MRef id) throws IOError {
        getObjectIndex(id.mc).remove(id.uuid);
    }

    /**
     * Get local objects referring to the given local or foreign object.
     * 
     * @param depName the dependency name
     * @param objRef a local or foreign object
     * @return all referred local objects.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("1b5085e1-54da-4dd0-8360-7c565fe53be1")
    public Collection<MRef> getSources(String depName, final MRef objRef) throws IOException {
        return this.userIdx.getSources(depName, objRef);
    }

    /**
     * Index the given model object.
     * 
     * @param obj a model object to index
     * @throws java.io.IOError in case of I/O failure
     */
    @objid ("8acceaec-3e0d-4432-b9ca-93a60e07e55c")
    public void addToMain(SmObjectImpl obj) throws IOError {
        MClass mClass = obj.getMClass();
        String uuid = obj.getUuid();
        addRefToMain(mClass, uuid);
    }

    /**
     * Add the given model object to cross references indexes.
     * <p>
     * Caller must remove existing references first if the object was already in the index.
     * 
     * @param obj a model object to index
     * @throws java.io.IOException in case of I/O failure
     */
    @objid ("fbebd766-7189-4ddc-bfa5-d13c8f2e847b")
    public void addCrossRefs(SmObjectImpl obj) throws IOException {
        try {
            MRef sourceRef = new MRef(obj);
        
            //Foreign references
            for (SmDependency dep : obj.getClassOf().getAllDepDef()) {
                if (Helper.isPersistent(dep)) {
                    if (!Helper.isPersistent(dep.getSymetric())) {
                        // one way navigable
                        for (SmObjectImpl val : obj.getDepValList(dep)) {
                            MRef valRef = new MRef(val);
        
                            this.userIdx.addUse(sourceRef, dep.getName(), valRef);
                        }
                    }
                } 
            }
        } catch (IOError e ) {
            throw new IOException(e);
        }
    }

    /**
     * Get the object index for the given metaclass.
     * 
     * @param mClass a metaclass
     * @return the class objects index
     */
    @objid ("f76fc982-d043-43c2-8efc-8670fcf7dd5f")
    protected Map<String, Boolean> getObjectIndex(final MClass mClass) {
        String clsName = mClass.getQualifiedName();
        return getObjectIndex(clsName);
    }

    /**
     * Get the object index for the given metaclass.
     * 
     * @param className a metaclass name
     * @return the class objects index
     */
    @objid ("cfe5238a-a279-4b06-9c0e-abe4aaaf8303")
    protected Map<String, Boolean> getObjectIndex(String className) {
        Map<String, Boolean> idx = this.objectsIndex.get(className);
        if (idx == null) {
            idx = this.db.hashMap(IDX_CLASS_PREFIX+className,
                    null, null);
        
        }
        return idx;
    }

    /**
     * Add the given reference to the main index
     * 
     * @param mClass the metaclass
     * @param uuid the UUID
     */
    @objid ("83042ab7-5bf0-42ab-8ec2-488b2eb3c9c3")
    public void addRefToMain(MClass mClass, String uuid) {
        Map<String, Boolean> ref = getObjectIndex(mClass);
        ref.put(uuid, Boolean.TRUE);
    }

    @objid ("4d403039-1dff-4d45-856b-e99f016cebdc")
    public void addCrossRef(MRef objRef, SmDependency dep, Collection<MRef> values) throws IOException {
        if (Helper.isPersistent(dep)) {
            if (!Helper.isPersistent(dep.getSymetric())) {
                // one way navigable
                for (MRef valRef : values) {
                    this.userIdx.addUse(objRef, dep.getName(), valRef);
                }
            }
        } else {
            // This is the opposite role
            String oppName = dep.getSymetric().getName();
            for (MRef valRef : values) {
                this.userIdx.addUse(valRef, oppName, objRef);
            }
        }
    }

    @objid ("1d598db3-41d3-45b3-91f0-6d2f27f921ab")
    public void removeCrossRef(MRef srcRef, String depName, MRef targetRef) throws IOException {
        this.userIdx.removeUse(srcRef, depName, targetRef);
    }

}
