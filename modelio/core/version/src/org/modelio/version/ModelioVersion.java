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
package org.modelio.version;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * Holds the version of the running Modelio.
 */
@objid ("d5582ece-182a-413b-8d4e-a8eb3ba2dc66")
public final class ModelioVersion {
    /**
     * Modelio version as a string.
     */
    @objid ("53a2d18d-300b-4607-9904-301d9723dcb4")
    public static final String STR_VERSION = "5.4.1";

    /**
     * Modelio build ID
     */
    @objid ("1529cdd7-3f74-4c61-8fc8-40aa079c2611")
    public static final String BUILDID = "DEV".toString();

    /**
     * Modelio version as a {@link Version}.
     */
    @objid ("92b718f9-5a96-46ce-a7c4-a8d99fafb722")
    public static final Version VERSION = new Version(STR_VERSION);

    /**
     * "major.minor" Modelio version as a {@link Version}.
     * <p>
     * The build version is reset.
     * To be used to compare versions without build.
     */
    @objid ("fe83f5ec-a833-4293-96ee-d8d13b3b3e9d")
    public static final Version MAJOR_MINOR = VERSION.withoutBuild();

    /**
     * No instance.
     */
    @objid ("f53a0942-7a4a-43bc-a017-3063e1cdc709")
    private  ModelioVersion() {
        // no instance
    }

    /**
     * Checks that the proposed version is compatible with this Modelio version.
     * Only Modelio MAJOR_MINOR is checked (build ignored).
     * @return false if versionToCheck is newer than Modelio.MAJOR_MINOR
     */
    @objid ("84f760a0-8779-4499-9409-eee9ccc87c77")
    public static boolean isCompatible(Version versionToCheck) {
        if (versionToCheck != null) {
            if (versionToCheck.withoutBuild().isNewerThan(ModelioVersion.MAJOR_MINOR)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
