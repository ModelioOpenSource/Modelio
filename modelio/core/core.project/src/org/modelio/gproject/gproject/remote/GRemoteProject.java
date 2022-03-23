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
package org.modelio.gproject.gproject.remote;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.version.Version;
import org.modelio.version.ModelioVersion;

/**
 * Abstract class parent of all remote project.
 */
@objid ("eb079112-0bee-4ae3-9c1b-f09e57c13e70")
public abstract class GRemoteProject extends GProject {
    /**
     * Check the expected Modelio version with the current Modelio version.
     * <p>
     * Checks the Modelio version matches the server defined version.
     * For server project Modelio version must be same or build compatible with the server version.
     * @throws IOException if the Modelio version does not match.
     */
    @objid ("7de7e182-677e-4146-8aec-f1181b72f81c")
    @Override
    protected void validateModelioVersion() throws IOException {
        Version expectedVersion = getExpectedModelioVersion().withoutBuild();
        
        if (expectedVersion == null) {
            // Suppose old 3.3.x project
            expectedVersion = new Version(3, 3, 0);
        }
        
        if (expectedVersion.isNewerThan(ModelioVersion.MAJOR_MINOR)) {
            throw new IOException(CoreProject.I18N.getMessage(
                    "GProject.modelioTooOld",
                    getName(),
                    expectedVersion.toString("V.R"),
                    ModelioVersion.MAJOR_MINOR.toString("V.R")));
        } else if (!expectedVersion.equals(ModelioVersion.MAJOR_MINOR)) {
            // For server project Modelio must be build compatible
            throw new IOException(CoreProject.I18N.getMessage(
                    "GProject.serverVersionDoesNotMatch",
                    getName(),
                    expectedVersion.toString("V.R"),
                    ModelioVersion.MAJOR_MINOR.toString("V.R")));
        }
        
    }

}
