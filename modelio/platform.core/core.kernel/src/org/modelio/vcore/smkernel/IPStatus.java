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

package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Persistent status of a {@link SmObjectImpl}.
 */
@objid ("3ced48ff-e5ae-4322-b9f1-c427b30aaa57")
public interface IPStatus {
    /**
     * The object is made visible in the GUI. This value is set for an object and is intrinsic to this object.
     */
    @objid ("4331e0e0-7f8b-4214-bd83-a420cb869a66")
    public static final long OBJECTVISIBLE = 1L << 32;

    /**
     * The object is browsable (visiting its dependencies).This value is set for an object and is intrinsic to this object.
     */
    @objid ("ce19efa1-8d0c-494f-b2b9-7416fa776d50")
    public static final long OBJECTBROWSE = 1L << 33;

    /**
     * The object is writable. This value is set for an object and is intrinsic to this object.
     */
    @objid ("ffbf89d6-6614-48a4-a9da-cacbdd74b0ab")
    public static final long OBJECTWRITE = 1L << 34;

    /**
     * Mask for individual object access
     */
    @objid ("36339226-d27b-11e1-b069-001ec947ccaf")
    public static final long MASK_OBJECT = OBJECTBROWSE | OBJECTVISIBLE | OBJECTWRITE;

    /**
     * All persistent flags used to compute the access rights
     */
    @objid ("05adbb21-da00-4e8f-b332-1396ee45ac1f")
    public static final long MASK_PACCESS = MASK_OBJECT;

    /**
     * Persistent flags that must be set in order for a model object to be modifiable.
     */
    @objid ("e31db11a-1d8b-4213-bb16-aeef66612dd1")
    public static final long PMASK_MODIFIABLE_REQUIRED = IPStatus.OBJECTWRITE;

}
