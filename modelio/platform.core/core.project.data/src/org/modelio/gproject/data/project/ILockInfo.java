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

package org.modelio.gproject.data.project;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Project lock informations.
 * @author cmarin
 */
@objid ("1c646ebd-315a-45ed-9fac-fe67e161c532")
public interface ILockInfo {
    /**
     * Get the lock owner login name.
     * 
     * @return the login name of the lock owner.
     */
    @objid ("d31f52b3-514c-4a0c-87b6-e3e735c9b55e")
    String getOwner();

    /**
     * Get the date when the lock was put.
     * 
     * @return the lock date.
     */
    @objid ("6dc69712-740a-40ae-8dfd-93b1e63f9d90")
    Date getDate();

    /**
     * Tells whether the lock has been put by the current Java virtual machine or not.<br>
     * 
     * @return <code>true</code> the lock has been put by the current JVM, <code>false</code> in all other cases.
     */
    @objid ("e23a63d6-6480-4d2b-abf3-3f177943e16b")
    boolean isSelf();

    /**
     * Get the host name of the machine owning the lock.
     * 
     * @return the lock host name.
     */
    @objid ("160c03b4-cbbc-4619-9367-1a79749cf5c3")
    String getHostName();

    /**
     * Get the Java virtual machine identifier.
     * <p>
     * This identifier has the "processId
     * @machineName" format.
     * 
     * @return the JVM identifier.
     */
    @objid ("33474b60-af50-44aa-ba35-dc44f41631fc")
    String getJvmIdentifier();

}
