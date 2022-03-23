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
package org.modelio.vstore.jdbm;

import java.io.File;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.plugin.VCoreSession;

/**
 * Indicates the {@link JdbmRepository} format is incompatible with the current format.
 * <p>
 * The opened repository can only be deleted.
 */
@objid ("e057838d-3f99-4836-b13f-e28f39f60e7a")
public class InvalidFormatException extends IOException {
    @objid ("c06ef8ea-8838-4552-bc62-07f65a25185a")
    private static final long serialVersionUID = 1L;

    @objid ("aac53376-7f5f-43bd-bef1-3267a5f3b3be")
    private int expectedVersion;

    @objid ("e6358890-9fe2-432b-abb8-e67944e7183a")
    private int readVersion;

    @objid ("9d53b8a4-307b-4e5b-92e3-e886f1d34e8f")
    private String repoName;

    @objid ("2c30977e-287c-4daa-9542-a0db702be9cc")
    private File repositoryPath;

    /**
     * Constructor
     * @param repoName the repository user friendly name
     * @param repositoryPath the repository path
     * @param readVersion the read version
     * @param expectedVersion the expected format version
     */
    @objid ("65ce0aa0-f385-4e09-bb26-e2825da9d464")
    public  InvalidFormatException(String repoName, File repositoryPath, int readVersion, int expectedVersion) {
        this.repoName = repoName;
        this.readVersion = readVersion;
        this.expectedVersion = expectedVersion;
        this.repositoryPath = repositoryPath;
        
    }

    @objid ("bd9d0f7d-41f3-4684-9fc1-c24e155a594e")
    @Override
    public String getMessage() {
        return VCoreSession.I18N.getMessage("JdbmRepository.InvalidFormatVersion", 
                                                this.repoName, 
                                                this.readVersion, 
                                                this.expectedVersion, 
                                                this.repositoryPath);
        
    }

    /**
     * @return the expected version
     */
    @objid ("2ac137ee-826f-4a13-ad76-47c24a3e2e28")
    public int getExpectedVersion() {
        return this.expectedVersion;
    }

    /**
     * @return the read version
     */
    @objid ("4fd5178f-f4a1-4a02-85e1-aa1c8df83fdb")
    public int getReadVersion() {
        return this.readVersion;
    }

    /**
     * @return the repository user friendly name
     */
    @objid ("b63bc895-9c89-416b-93fd-6b17f21b49db")
    public String getRepositoryName() {
        return this.repoName;
    }

}
