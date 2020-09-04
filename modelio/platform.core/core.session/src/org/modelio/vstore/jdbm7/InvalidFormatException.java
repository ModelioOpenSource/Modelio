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

package org.modelio.vstore.jdbm7;

import java.io.File;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.plugin.VCoreSession;

/**
 * Indicates the {@link JdbmRepository} format is incompatible with the current format.
 * <p>
 * The opened repository can only be deleted.
 */
@objid ("75a43faf-140c-40c6-ae96-120cb10fadda")
public class InvalidFormatException extends IOException {
    @objid ("986ff9a1-881d-468e-a716-50a7f5cb39fd")
    private static final long serialVersionUID = 1L;

    @objid ("77e2ea76-f90b-4d14-90bc-1d583646997e")
    private int expectedVersion;

    @objid ("37969e71-142c-489e-9ff9-1f9685c8b790")
    private int readVersion;

    @objid ("fd67c54d-0365-4f5a-b9fd-191e06f199ce")
    private String repoName;

    @objid ("9aba5960-cafb-4615-9665-fe61dbaf07c0")
    private File repositoryPath;

    /**
     * Constructor
     * @param repoName the repository user friendly name
     * @param repositoryPath the repository path
     * @param readVersion the read version
     * @param expectedVersion the expected format version
     */
    @objid ("31d04c18-1927-44cf-8a47-d2f2b38a0d35")
    public InvalidFormatException(String repoName, File repositoryPath, int readVersion, int expectedVersion) {
        this.repoName = repoName;
        this.readVersion = readVersion;
        this.expectedVersion = expectedVersion;
        this.repositoryPath = repositoryPath;
    }

    @objid ("72372a43-46af-414c-a639-6a7349e3a553")
    @Override
    public String getMessage() {
        return VCoreSession.getMessage("JdbmRepository.InvalidFormatVersion", 
                                                this.repoName, 
                                                this.readVersion, 
                                                this.expectedVersion, 
                                                this.repositoryPath);
    }

    /**
     * @return the expected version
     */
    @objid ("c4692579-83ca-48e5-a97d-c09ef2797e96")
    public int getExpectedVersion() {
        return this.expectedVersion;
    }

    /**
     * @return the read version
     */
    @objid ("bb098746-4ac0-42f7-bb1d-0f5cc44d2be9")
    public int getReadVersion() {
        return this.readVersion;
    }

    /**
     * @return the repository user friendly name
     */
    @objid ("b053bccf-8e18-4ba7-a12c-79c7444e4f2e")
    public String getRepositoryName() {
        return this.repoName;
    }

}
