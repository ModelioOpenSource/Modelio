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

/**
 * Bit flags for SmObject runtime and permanent status.
 * <p>
 * The status is decomposed in 2 32 bits parts : runtime status (lower bits) and persistent status (high bits).
 * <p>
 * Each status part is decomposed in 2 layers: a flags layer and a definition layer. Each flag as another flag that tells if the
 * flag is defined on this status. If not, the reader has to look for the composition owner status.
 */
@objid ("00567e08-2c99-1f20-85a5-001ec947cd2a")
public final class SmStatus implements IRStatus, IPStatus {
    /**
     * All flags area.
     * <p>
     * All flags constants must be defined in this area.
     * It contains bits from 0 to 15 and from 32 to 47.
     */
    @objid ("325165b5-d27b-11e1-b069-001ec947ccaf")
    private static final long GFLAGS = 0x0000_ffff_0000_ffffL;

    /**
     * All flag masks area
     * <p>
     * No flag constant must be defined in this area.
     */
    @objid ("325165b2-d27b-11e1-b069-001ec947ccaf")
    private static final long GMASK = ~GFLAGS;

    /**
     * Flags that must never be undefined in order for Modelio to work.
     */
    @objid ("ca840a0e-d5a6-11e1-adbb-001ec947ccaf")
    public static final long MASK_NEVER_UNDEFINED = SHELL | MASK_DELETE | LOADING | RESTORED_FROM_SWAP;

    /**
     * Persistent flags area.
     * <p>
     * The area was initially from 32 to 47.
     * Remove the CMS and repository status part that was in the persistent part.
     */
    @objid ("325165ab-d27b-11e1-b069-001ec947ccaf")
     static final long PFLAGS = 0x0000_ffff_0000_0000L & ~ (MASK_CMS | MASK_REPO);

    /**
     * Persistent flags mask area
     */
    @objid ("325165af-d27b-11e1-b069-001ec947ccaf")
     static final long PMASK = PFLAGS << 16; // 0xffff_0000_0000_0000L;

    /**
     * Runtime flags area.
     * <p>
     * The area was initially from 0 to 15.
     * Add the CMS status part that was in the persistent part.
     */
    @objid ("325165a5-d27b-11e1-b069-001ec947ccaf")
     static final long RFLAGS = 0x0000_0000_0000_ffffL | MASK_CMS | MASK_REPO;

    /**
     * Persistent part of a SmStatus.
     */
    @objid ("fc5fc7d9-b344-40e1-be54-529ab3a03d60")
     static final long PERSISTENT_BITS = PFLAGS | PMASK;

    /**
     * Runtime flags mask area
     */
    @objid ("325165a8-d27b-11e1-b069-001ec947ccaf")
    private static final long RMASK = RFLAGS << 16; // 0x0000_0000_ffff_0000L;

    @objid ("004918ee-fd1a-1f27-a7da-001ec947cd2a")
    private static final long serialVersionUID = 2436486008927826622L;

    /**
     * Tells whether all the given flags are set in this status. Returns:
     * <ul>
     * <li> <code>StatusState.UNDEFINED</code> if any of the flags is not defined,
     * <li> <code>StatusState.TRUE</code> if all of them are defined and set to <code>StatusState.TRUE</code>,
     * <li> <code>StatusState.FALSE</code> if all of them are defined and some are set to <code>StatusState.FALSE</code>.
     * </ul>
     * 
     * @param status the status to test
     * @param bitdef the flags to test
     * @return <code>StatusState.UNDEFINED</code> if any of the flags is not defined, <code>StatusState.TRUE</code> if all of them are defined
     * and set, else <code>StatusState.FALSE</code>.
     */
    @objid ("5bb14364-8ebc-11e1-81e9-001ec947ccaf")
    public static StatusState areAllSet(long status, final long bitdef) {
        // The bit defs must not contain any mask bit
        assert ((bitdef & SmStatus.GMASK) == 0) : flagsToString(bitdef);
        
        long defmask = toMask(bitdef);
        long ourdef = fromMask(status);
        
        // Look if any required bit is defined to false
        if (((status | ~ourdef) & bitdef) != bitdef) {
            return StatusState.FALSE;
        }
        
        // No required bit is false, look if any required bit is not defined
        if ((status & defmask) != defmask) {
            return StatusState.UNDEFINED;
        }
        
        // Here all required bits are defined, and true
        return StatusState.TRUE;
    }

    /**
     * Return the string representation of the given flags combination.
     * Flags are to be found in {@link IRStatus} and {@link IPStatus}.
     * 
     * @param bitdef a combination of lags, not a status.
     * @return the string representation.
     */
    @objid ("711884ea-76b5-4104-b4b9-5c5dc85023df")
    public static String flagsToString(long bitdef) {
        long status = bitdef | toMask(bitdef);
        long invalid = bitdef & GMASK;
        
        if (invalid == 0) {
            return toString(status);
        } else {
            return toString(status) + " invalid mask bits:" + toString(invalid | fromMask(invalid));
        }
    }

    /**
     * Get the value and mask bits filtered by the given access mask.
     * 
     * @param status a status
     * @param bitdef an access mask
     * @return the value and mask bits
     */
    @objid ("002117ea-f15a-1f3c-aafd-001ec947cd2a")
    public static long getBits(long status, long bitdef) {
        assert ((bitdef & SmStatus.GMASK) == 0) : flagsToString(bitdef);
        
        final long m = bitdef | toMask(bitdef);
        return status & m;
    }

    /**
     * Get flags defined to FALSE in the given status
     * 
     * @param status a status
     * @return the flags defined to FALSE.
     */
    @objid ("cdabd86a-f8d6-488b-b0d1-5c9854adc64a")
    public static long getFalseFlags(long status) {
        long definedFlags = fromMask(status);
        return (~status & definedFlags);
    }

    /**
     * Get the persistent value and mask bits.
     * 
     * @param status a status
     * @return the persistent value and mask bits.
     */
    @objid ("57adbdd6-bf40-4c5c-9c3e-e74524f0a2b5")
    public static long getPersistentBits(long status) {
        return status & PERSISTENT_BITS;
    }

    /**
     * Tells whether any of the given flags is set to TRUE.
     * <ul>
     * <li> <code>StatusState.TRUE</code> if any of them are defined and set to <code>StatusState.TRUE</code>,
     * <li> <code>StatusState.UNDEFINED</code> if no flag is TRUE and at least one flag is UNDEFINED,
     * <li> <code>StatusState.FALSE</code> if all of them are defined to <code>StatusState.FALSE</code>.
     * </ul>
     * 
     * @param status a status
     * @param bitdef the flags to test
     * @return the test result.
     */
    @objid ("5bb1435d-8ebc-11e1-81e9-001ec947ccaf")
    public static StatusState isAnySet(long status, final long bitdef) {
        // The bit defs must not contain any mask bit
        assert ((bitdef & SmStatus.GMASK) == 0) : flagsToString(bitdef);
        
        long mask = toMask(bitdef);
        
        if ((status & bitdef) != 0) {
            return StatusState.TRUE;
        } else if ((mask & status) != mask) {
            return StatusState.UNDEFINED;
        } else {
            return StatusState.FALSE;
        }
    }

    /**
     * Test whether all bit masks are set.
     * 
     * @param status the status to test.
     * @return true if all flags are defined, false if some flags need to be read on another status
     */
    @objid ("32562a4d-d27b-11e1-b069-001ec947ccaf")
    public static boolean isComplete(long status) {
        return (status & SmStatus.GMASK) == SmStatus.GMASK;
    }

    /**
     * Set the given flags state, except FALSE flags are left untouched.
     * <p>
     * Use the constants defined in {@link IRStatus} and {@link IPStatus}.
     * No delete flag must be undefined, in the other case Modelio behavior is undefined.
     * 
     * @param astatus the initial status
     * @param trueFlags a combination of flags to set.
     * @param falseFlags a combination of flags to unset.
     * @param undefFlags a combination of flags to undefine.
     * @return the modified status
     */
    @objid ("6f5c9a77-d78d-4076-889a-b26d354224d7")
    public static long setNotFalseFlags(final long astatus, long trueFlags, long falseFlags, long undefFlags) {
        long status = astatus;
        
        // The mask bits must not overlap
        assert ((trueFlags & falseFlags) == 0) : flagsToString((trueFlags & falseFlags));
        assert ((falseFlags & undefFlags) == 0) : flagsToString((falseFlags & undefFlags));
        assert ((trueFlags & undefFlags) == 0) : flagsToString((trueFlags & undefFlags));
        
        // The bit defs must not contain any mask bit
        assert (((trueFlags| falseFlags| undefFlags) & SmStatus.GMASK) == 0) :
            flagsToString((trueFlags| falseFlags| undefFlags));
        
        final long origFalseFlags = getFalseFlags(status);
        
        if (trueFlags != 0) {
            // No FALSE flag must be changed to TRUE
            long lTrueFlags = trueFlags & ~origFalseFlags;
            status |= toMask(lTrueFlags);
            status |= lTrueFlags;
        }
        
        if (falseFlags != 0) {
            status |= toMask(falseFlags);
            status &= ~falseFlags;
        }
        
        if (undefFlags != 0) {
            // No delete flag must be undefined, in the other case Modelio
            // behavior is undefined.
            assert ((undefFlags & MASK_NEVER_UNDEFINED) == 0) : toString(undefFlags)+ " contains bits that must never be undefined";
        
            // No FALSE flag must be changed to UNDEFINED
        
            long lUndefFlags = undefFlags & ~origFalseFlags;
            status &= ~(toMask(lUndefFlags) | lUndefFlags);
        }
        return status;
    }

    /**
     * Set the state of the given flags.
     * <p>
     * No delete flag must be undefined, in the other case Modelio behavior is undefined.
     * 
     * @param origStatus the original status value.
     * @param bitdef the flags to modify.
     * @param state the new state of the flags
     * @return the modified status
     */
    @objid ("00569258-2c99-1f20-85a5-001ec947cd2a")
    public static long setFlags(long origStatus, final long bitdef, StatusState state) {
        long status = origStatus;
        
        // The bit defs must not contain any mask bit
        assert ((bitdef & SmStatus.GMASK) == 0) : flagsToString(bitdef);
        
        switch (state) {
        case TRUE:
            status |= toMask(bitdef);
            status |= bitdef;
            break;
        case FALSE:
            status |= toMask(bitdef);
            status &= ~bitdef;
            break;
        case UNDEFINED:
            // No delete flag must be undefined, in the other case Modelio
            // behavior is undefined.
            assert ((bitdef & MASK_NEVER_UNDEFINED) == 0) : toString(bitdef)+ " contains bits that must never be undefined";
        
            status &= ~(toMask(bitdef) | bitdef);
            break;
        default:
            break;
        
        }
        return status;
    }

    /**
     * Set the given flags state.
     * <p>
     * Use the constants defined in {@link IRStatus} and {@link IPStatus}.
     * No delete flag must be undefined, in the other case Modelio behavior is undefined.
     * 
     * @param astatus the initial status
     * @param trueFlags a combination of flags to set.
     * @param falseFlags a combination of flags to unset.
     * @param undefFlags a combination of flags to undefine.
     * @return the modified status
     */
    @objid ("d22bfdd1-3fdd-40fb-bfaf-a917d584bb42")
    public static long setFlags(final long astatus, long trueFlags, long falseFlags, long undefFlags) {
        long status = astatus;
        
        // The mask bits must not overlap
        assert ((trueFlags & falseFlags) == 0) : flagsToString((trueFlags & falseFlags));
        assert ((falseFlags & undefFlags) == 0) : flagsToString((falseFlags & undefFlags));
        assert ((trueFlags & undefFlags) == 0) : flagsToString((trueFlags & undefFlags));
        
        // The bit defs must not contain any mask bit
        assert (((trueFlags| falseFlags| undefFlags) & SmStatus.GMASK) == 0) :
            flagsToString((trueFlags| falseFlags| undefFlags));
        
        if (trueFlags != 0) {
            status |= toMask(trueFlags);
            status |= trueFlags;
        }
        
        if (falseFlags != 0) {
            status |= toMask(falseFlags);
            status &= ~falseFlags;
        }
        
        if (undefFlags != 0) {
            // No delete flag must be undefined, in the other case Modelio
            // behavior is undefined.
            assert ((undefFlags & MASK_NEVER_UNDEFINED) == 0) : toString(undefFlags)+ " contains bits that must never be undefined";
        
            status &= ~(toMask(undefFlags) | undefFlags);
        }
        return status;
    }

    /**
     * Return a string representation of the given status flags.
     * 
     * @param status the status to print
     * @return the string representation.
     */
    @objid ("0056953c-2c99-1f20-85a5-001ec947cd2a")
    public static String toString(long status) {
        StringBuilder s = new StringBuilder();
        
        // Memory management
        dumpIfSet(status, SHELL, "SHELL, ", s);
        dumpIfSet(status, RAMC, "RAMC, ", s);
        dumpIfSet(status, DELETED, "DELETED, ", s);
        dumpIfSet(status, BEINGDELETED, "BEINGDELETED, ", s);
        dumpIfSet(status, LOADING, "LOADING, ", s);
        dumpIfSet(status, RESTORED_FROM_SWAP, "RESTORED_FROM_SWAP, ", s);
        
        // CMS management
        dump(status, CMSSYNC, "CMSSYNC, ", s);
        dump(status, CMSMODIFIED, "CMSMODIFIED, ", s);
        dump(status, CMSMANAGED, "CMSMANAGED, ", s);
        dump(status, CMSREADONLY, "CMSREADONLY, ", s);
        dumpIfSet(status, CMSTOADD, "CMSTOADD, ", s);
        dumpIfSet(status, CMSTODELETE, "CMSTODELETE, ", s);
        dumpIfSet(status, CMSCONFLICT, "CMSCONFLICT, ", s);
        
        // Access rights
        dump(status, USERVISIBLE, "USERVISIBLE, ", s);
        dump(status, USERBROWSE, "USERBROWSE, ", s);
        dump(status, USERWRITE, "USERWRITE, ", s);
        
        dump(status, DOMAINBROWSE, "DOMAINBROWSE, ", s);
        dump(status, DOMAINVISIBLE, "DOMAINVISIBLE, ", s);
        dump(status, DOMAINWRITE, "DOMAINWRITE, ", s);
        
        dump(status, OBJECTBROWSE, "OBJECTBROWSE, ", s);
        dump(status, OBJECTVISIBLE, "OBJECTVISIBLE, ", s);
        dump(status, OBJECTWRITE, "OBJECTWRITE, ", s);
        
        
        if (s.length() > 0) {
            // remove the last ", "
            return s.substring(0, s.length() - 2);
        } else {
            return "";
        }
    }

    /**
     * Merges another status into a destination one.
     * <p>
     * All flags defined to <code>true</code> or <code>false</code> in the <i>other</i> status will be copied in this status.
     * Other flags will be left untouched.
     * 
     * @param destStatus the status value to modify.
     * @param other the status value to merge
     * @return the merged status value
     */
    @objid ("ab140a41-d287-11e1-b069-001ec947ccaf")
    static long combine(long destStatus, long other) {
        long lstatus = destStatus;
        
        // Report parent TRUE flags to undefined flags
        lstatus |= (other & GFLAGS & fromMask(other) & ~fromMask(destStatus));
        
        // Set flags defined on parent as defined
        lstatus |= (other & GMASK);
        return lstatus;
    }

    /**
     * Modify the persistent part of a status value.
     * 
     * @param status the status to modify
     * @param persistentBits the persistent part to replace to
     * @return the modified status value
     */
    @objid ("a5bd24cb-cf65-4c88-a72b-d54edc79d3b5")
    static long setPersistentPart(long status, long persistentBits) {
        long pstatus = persistentBits & PERSISTENT_BITS;
        long rstatus = status & ~PERSISTENT_BITS;
        return pstatus | rstatus;
    }

    @objid ("d3a914d9-3b68-468c-9bad-9ca35f69444f")
    private SmStatus() {
        // no instance
        throw new AssertionError();
    }

    @objid ("0c769dd2-d4cd-11e1-b069-001ec947ccaf")
    private static void dump(long status, long flag, String name, StringBuilder s) {
        switch (areAllSet(status, flag)) {
        case FALSE:
            s.append("!");
            s.append(name);
            break;
        case TRUE:
            s.append(name);
            break;
        case UNDEFINED:
            break;
        default:
            break;
        
        }
    }

    @objid ("464deb0b-f799-4bfd-a6c6-ab069c5a9518")
    private static void dumpIfSet(long status, long flag, String name, StringBuilder s) {
        switch (areAllSet(status, flag)) {
        case UNDEFINED:
        case FALSE:
            break;
        case TRUE:
            s.append(name);
            break;
        default:
            break;
        
        }
    }

    /**
     * Convert definition mask flags to value flags .
     * 
     * @param status a complete status
     * @return flags that are defined
     */
    @objid ("c81303fc-d58f-11e1-b069-001ec947ccaf")
    private static final long fromMask(final long status) {
        return (status & GMASK) >> 16;
    }

    /**
     * Convert value flags to definition mask flags.
     * 
     * @param flags value flags
     * @return definition mask
     */
    @objid ("32562a45-d27b-11e1-b069-001ec947ccaf")
    private static final long toMask(final long flags) {
        return (flags & GFLAGS) << 16;
    }

    /**
     * Return a string representation of the given status flags.
     * <p>
     * same as toString(). Exists to allow it being called by Jython for which
     * 'toString' seems to be overwritten by the interpreter.
     * @see SmStatus#toString()
     * 
     * @param status the status to print
     * @return the string representation.
     */
    @objid ("13380269-c53f-4c8f-b0d9-4f025e7be104")
    public static String asString(long status) {
        return toString(status);
    }

}
