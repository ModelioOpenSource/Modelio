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

package org.modelio.vcore.model;

import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Exception thrown when adding to the cache an object with the same identifier as another one.
 */
@objid ("96bc235f-089c-11e2-b33c-001ec947ccaf")
public class DuplicateObjectException extends Exception {
    @objid ("f53b8388-08b1-11e2-b33c-001ec947ccaf")
    private static final long serialVersionUID = 1L;

    @objid ("91e7d2dd-01c3-4fe2-812b-2cca4361faf4")
    private final String oid;

    /**
     * Debug flag. Set to true to store threads dump into the exception.
     */
    @objid ("ba3fc402-51f4-492e-b4a9-6b4464ca836d")
    public static boolean WITH_THREAD_DUMP = false;

    @objid ("f53b838a-08b1-11e2-b33c-001ec947ccaf")
    private final SmObjectImpl dupObj;

    @objid ("f53b838b-08b1-11e2-b33c-001ec947ccaf")
    private final SmObjectImpl origObj;

    @objid ("b4589a8b-c804-42d2-b354-76a25468a11c")
    private Map<Thread,StackTraceElement[]> threadDump;

    /**
     * Just to keep a reference on the data and avoid it to be garbaged.
     */
    @objid ("4b4d3d5a-17ed-437b-a9af-2dd6da59fb27")
    @SuppressWarnings("unused")
    private final ISmObjectData dupData;

    /**
     * Just to keep a reference on the data and avoid it to be garbaged.
     */
    @objid ("9c96d36d-7574-4f53-a57c-cca28b1d316a")
    @SuppressWarnings("unused")
    private final ISmObjectData origData;

    /**
     * Initialize the exception.
     * 
     * @param oid the common UUID
     * @param origObj the original object
     * @param dupObj the duplicate object
     */
    @objid ("f53b838d-08b1-11e2-b33c-001ec947ccaf")
    public DuplicateObjectException(String oid, SmObjectImpl origObj, SmObjectImpl dupObj) {
        this.oid = oid;
        this.dupObj = dupObj;
        this.dupData = dupObj.getData();
        this.origObj = origObj;
        this.origData = origObj.getData();
        
        if (WITH_THREAD_DUMP) {
            this.threadDump = Thread.getAllStackTraces();
        }
    }

    @objid ("f53b8393-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public String getMessage() {
        if (this.origObj == this.dupObj) {
            return this.origObj+" registered 2 times with different data.\n\n"+getThreadDump();
        } else {
            final IRepositoryObject origRepo = this.origObj != null ? this.origObj.getRepositoryObject() : null;
            final IRepositoryObject dupRepo = this.dupObj != null ? this.dupObj.getRepositoryObject() : null;
            return ("Duplicate '" + this.oid 
                    + "' objects detected:\n - original:  " 
                    + this.origObj 
                    + " in "+ origRepo
                    + "\n - new: " 
                    + this.dupObj
                    + " in "+ dupRepo
                    + ".\n\n"
                    + getThreadDump());
        }
    }

    /**
     * @return the duplicate object.
     */
    @objid ("f53b8398-08b1-11e2-b33c-001ec947ccaf")
    public MObject getDuplicateObj() {
        return this.dupObj;
    }

    /**
     * @return the original object.
     */
    @objid ("f53b839d-08b1-11e2-b33c-001ec947ccaf")
    public MObject getOriginalObj() {
        return this.origObj;
    }

    /**
     * @return the UUID of both objects.
     */
    @objid ("f53b83a2-08b1-11e2-b33c-001ec947ccaf")
    public String getOid() {
        return this.oid;
    }

    /**
     * Get the stack trace of all threads at the time where the exception was created.
     * 
     * @return all threads stack trace.
     */
    @objid ("eba19efc-e22a-44d6-a83a-f103ddffd956")
    public String getThreadDump() {
        if (WITH_THREAD_DUMP) {
            StringBuilder s = new StringBuilder();
            s.append("Thread dump:\n");
            for (Entry<Thread, StackTraceElement[]>  entry : this.threadDump.entrySet()) {
                s.append(entry.getKey().toString());
                s.append(":\n");
                for (StackTraceElement st : entry.getValue()) {
                    s.append("   at ");
                    s.append(st.toString());
                    s.append("\n");
                }
                s.append("\n");
            }
            return s.toString();
        } else {
            return "";
        }
    }

}
