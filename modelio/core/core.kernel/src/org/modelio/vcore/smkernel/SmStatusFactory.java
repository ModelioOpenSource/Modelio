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
package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Service class used to instantiate, load and save {@link IPStatus} and {@link IRStatus}
 * from a {@link SmObjectData}.
 */
@objid ("8ec46bf9-fd99-4c58-98cc-11035e5dbdd2")
public class SmStatusFactory {
    /**
     * Flags that are set to FALSE on CMS node elements by default.
     */
    @objid ("3796cd8e-d27b-11e1-b069-001ec947ccaf")
    private static final long DEFAULT_CMSNODE_RSTATUS_FALSE = IRStatus.BEINGDELETED | IRStatus.DELETED |
                IRStatus.MASK_CMS | IRStatus.RAMC | IRStatus.SHELL | IRStatus.MASK_AUDIT;

    /**
     * Flags that are set to FALSE on non CMS node elements by default.
     */
    @objid ("3796cd94-d27b-11e1-b069-001ec947ccaf")
    private static final long DEFAULT_RSTATUS_FALSE = IRStatus.BEINGDELETED | IRStatus.DELETED |
                IRStatus.RAMC | IRStatus.SHELL | IRStatus.MASK_AUDIT;

    /**
     * Flags that are set to TRUE on an object.
     */
    @objid ("838aca2c-3be4-4dd5-a572-79e50ec2641b")
    private static final long DEFAULT_RSTATUS_TRUE = IRStatus.MASK_USER | IRStatus.MASK_DOMAIN;

    /**
     * Flags that are set to TRUE on an object.
     */
    @objid ("5e908c8e-88d2-4d64-b693-6f2b31d53302")
    private static final long DEFAULT_PSTATUS_TRUE = IPStatus.MASK_OBJECT;

    /**
     * Read the persistent and runtime status from the given long.
     * @param data the model object to initialize
     * @param readLong the read statuses
     */
    @objid ("2de2e232-1828-4f3f-9a1c-6c61139ad1ec")
    public static void deserializeStatuses(SmObjectData data, long readLong) {
        data.status = readLong;
    }

    /**
     * Reset the runtime status of the given model object data.
     * @param data a model object data.
     */
    @objid ("a176a4c0-220c-4387-a96c-72cd9ef24e66")
    public static void resetRStatus(ISmObjectData data) {
        final SmClass cls = data.getClassOf();
        if (cls.isCmsNode()) {
            data.setRFlags(DEFAULT_CMSNODE_RSTATUS_FALSE, StatusState.FALSE);
        } else {
            data.setRFlags(DEFAULT_RSTATUS_FALSE, StatusState.FALSE);
        }
        data.setRFlags(DEFAULT_RSTATUS_TRUE, StatusState.TRUE);
        
    }

    /**
     * Reset the persistent status of the given model object data.
     * @param data a model object data.
     */
    @objid ("a3946258-7ae5-4689-be3f-4c1279897e68")
    public static void resetPStatus(ISmObjectData data) {
        data.setPFlags(DEFAULT_PSTATUS_TRUE, StatusState.TRUE);
    }

}
