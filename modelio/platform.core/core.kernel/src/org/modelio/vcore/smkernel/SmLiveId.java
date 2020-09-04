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
 * Service class to compute a model object live identifier.
 * <p>
 * The live identifier is a 64bit integer composed of <ul>
 * <li> The kernel id
 * <li> The metaclass id
 * <li> The repository id
 * </ul>
 * <p>
 * This identifier is valid while the project is opened.
 * It is used to help recovering a swapped object.
 * 
 * @see ISwap
 * @see IKernelServiceProvider
 */
@objid ("003d0360-fd1a-1f27-a7da-001ec947cd2a")
public final class SmLiveId {
    @objid ("007a24a2-221f-10bf-bd58-001ec947cd2a")
    private static final long kidMask = 0x0000FFFF00000000L;

    @objid ("007eaedc-221f-10bf-bd58-001ec947cd2a")
    private static final int kidshift = 32;

    @objid ("007ec836-221f-10bf-bd58-001ec947cd2a")
    private static final long ridMask = 0x00000000FFFF0000L;

    @objid ("007edaec-221f-10bf-bd58-001ec947cd2a")
    private static final int ridshift = 16;

    @objid ("007eeda2-221f-10bf-bd58-001ec947cd2a")
    private static final long classidMask = 0x000000000000FFFFL;

    @objid ("007f00a8-221f-10bf-bd58-001ec947cd2a")
    private static final int classshift = 0;

    /**
     * @param liveId a live id.
     * @return the kernel id.
     */
    @objid ("0081bec4-221f-10bf-bd58-001ec947cd2a")
    public static short getKid(long liveId) {
        return (short) ((liveId & kidMask) >> kidshift);
    }

    /**
     * @param liveId a live id.
     * @return the metaclass id.
     */
    @objid ("00822d82-221f-10bf-bd58-001ec947cd2a")
    public static short getClassId(long liveId) {
        return (short) ((liveId & classidMask) >> classshift);
    }

    /**
     * @param liveId a live id.
     * @return the repository id.
     */
    @objid ("0082ed12-221f-10bf-bd58-001ec947cd2a")
    public static byte getRid(long liveId) {
        return (byte) ((liveId & ridMask) >> ridshift);
    }

    /**
     * Build a live identifier.
     * @param kid a kernel id.
     * @param rid a repository id.
     * @param classid a metaclass id.
     * @return the live id.
     */
    @objid ("00830f54-221f-10bf-bd58-001ec947cd2a")
    public static long make(short kid, short rid, short classid) {
        return ((((long) kid) << kidshift) & kidMask) | ((((long) classid) << classshift) & classidMask) | ((((long) rid) << ridshift) & ridMask);
    }

    /**
     * Dumps a live identifier.
     * @param liveId a live id.
     * @return its dump.
     */
    @objid ("7c6981dd-3fa3-11e2-b198-002564c97630")
    public static String toString(long liveId) {
        return "kid = " + getKid(liveId) + " rid = " + getRid(liveId) + " classid = " + getClassId(liveId);
    }

    @objid ("540bd860-e3a8-4f36-8d67-49326b57968a")
    private SmLiveId() {
        // no instance
    }

}
