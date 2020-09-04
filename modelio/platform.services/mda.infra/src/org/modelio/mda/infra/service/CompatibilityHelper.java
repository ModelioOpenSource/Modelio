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

package org.modelio.mda.infra.service;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.vbasic.version.Version;
import org.modelio.version.ModelioVersion;

/**
 * Service class that tells whether a module is compatible with this Modelio version.
 */
@objid ("2deedf3d-1f3a-45ef-84f1-438c035ff6f3")
public class CompatibilityHelper {
    /**
     * Module must be made for Modelio 3.4 at least
     */
    @objid ("ab483fe3-14b9-4004-ac55-97660d38d215")
    private static final Version MIN_MODELIO_VERSION = new Version(3, 4, 00);

    /**
     * Tells whether a module with this compatibility level can be run.
     * @param level a CompatibilityLevel
     * @return true if a module with this compatibility level can be run
     */
    @objid ("c8581fd0-7510-44b9-b35b-c6364ff382eb")
    public static boolean isCompatible(CompatibilityLevel level) {
        return (level != CompatibilityLevel.MODELIO_TOO_OLD && level != CompatibilityLevel.MODULE_TOO_OLD);
    }

    /**
     * Get the compatibility level of a module handle with the current Modelio version.
     * @param mh the module handle
     * @return the compatibility level
     */
    @objid ("6b20b4a7-0cd8-45ba-a2ad-311c53fbc011")
    public static CompatibilityLevel getCompatibilityLevel(IModuleHandle mh) {
        Version requiredVersion = mh.getBinaryVersion();
        return CompatibilityHelper.getCompatibilityLevel(ModelioVersion.VERSION, requiredVersion);
    }

    /**
     * Get the compatibility level of a module binary version with the given Modelio version.
     * @param modelioVersion the Modelio binary version
     * @param moduleBinaryVersion the module required binary version
     * @return the binary compatibility level
     */
    @objid ("2f742ca4-6789-4d67-869e-52f512cefda2")
    public static CompatibilityLevel getCompatibilityLevel(Version modelioVersion, Version moduleBinaryVersion) {
        if (moduleBinaryVersion == null) {
            return CompatibilityLevel.MODULE_TOO_OLD;
        }
        
        if (moduleBinaryVersion.isOlderThan(CompatibilityHelper.MIN_MODELIO_VERSION)) {
            return CompatibilityLevel.MODULE_TOO_OLD;
        }
        
        int moV = modelioVersion.getMajorVersion();
        int moR = modelioVersion.getMinorVersion();
        // int moM = modelioVersion.getMetamodelVersion();
        
        int mhV = moduleBinaryVersion.getMajorVersion();
        int mhR = moduleBinaryVersion.getMinorVersion();
        // int mhM = moduleBinaryVersion.getMetamodelVersion();
        
        if (moV > mhV) {
            // Modelio Major version greater then expected by module
            // => let conclude that the module not compatible because too old
            return CompatibilityLevel.MODULE_TOO_OLD;
        } else if (moV < mhV) {
            // Modelio Major version is smaller than expected by module
            // => the module is not compatible because Modelio is too old
            return CompatibilityLevel.MODELIO_TOO_OLD;
        } else {
            // Same Major version
            if (moR > mhR) {
                // => might be compatible by ascending compat of modelio, still have to check the metamodel
                return CompatibilityLevel.COMPATIBLE;
        
                // TODO find a way to compare metamodels
                /*
                 * if (moM > mhM) {
                 * // Modelio metamodel more recent than expected by module
                 * // => ascending compatibility metamodel
                 * return CompatibilityLevel.COMPATIBLE;
                 * } else if (moM == mhM) {
                 * // Same Metamodel version
                 * return CompatibilityLevel.COMPATIBLE;
                 * } else {
                 * // Modelio metamodel older than expected by module
                 * // => not compatible, modelio too old
                 * return CompatibilityLevel.MODELIO_TOO_OLD;
                 * }
                 */
            } else if (moR == mhR) {
                // Same Minor version
                return CompatibilityLevel.FULLYCOMPATIBLE;
        
                // TODO find a way to compare metamodels
                /*
                 * if (moM > mhM) {
                 * // Modelio metamodel more recent than expected by module
                 * // => ascending compatibility metamodel
                 * return CompatibilityLevel.COMPATIBLE;
                 * } else if (moM == mhM) {
                 * // Same Metamodel version
                 * // => full compatibility :)
                 * return CompatibilityLevel.FULLYCOMPATIBLE;
                 * } else {
                 * // Modelio metamodel older than expected by module
                 * // => not compatible, modelio too old
                 * return CompatibilityLevel.MODELIO_TOO_OLD;
                 * }
                 */
            } else /* moR < mhR */ {
                // Modelio too old
                return CompatibilityLevel.MODELIO_TOO_OLD;
            }
        }
    }

    /**
     * Module compatibility levels with the current Modelio.
     */
    @objid ("40e4baa5-cacd-4261-a95f-2dfa972b7499")
    public enum CompatibilityLevel {
        /**
         * No problem
         */
        FULLYCOMPATIBLE,
        /**
         * The module should be compatible but upgrading to a newer version is recommended.
         */
        COMPATIBLE,
        /**
         * Modelio is too old
         */
        MODELIO_TOO_OLD,
        /**
         * The module is too old.
         */
        MODULE_TOO_OLD;
    }

}
