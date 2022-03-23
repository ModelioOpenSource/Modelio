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
package org.modelio.vcore.model.spi.mm;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Indicates a {@link IMofRepositoryMigrator} failed.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("103b1bca-2f3d-4cf7-a6ef-c39904c78568")
public class MofMigrationException extends Exception {
    @objid ("5e1a16ff-faf6-4d1b-a402-a4c78a63c6b7")
    private static final long serialVersionUID = 1L;

    @objid ("7ee2eaba-3605-4e43-aa4c-96561e9bd22e")
    public  MofMigrationException(String message, Throwable cause) {
        super(message, cause);
    }

    @objid ("f49a8b64-6e94-47b0-ad4a-29fa6765ad28")
    public  MofMigrationException(String message) {
        super(message);
    }

}
