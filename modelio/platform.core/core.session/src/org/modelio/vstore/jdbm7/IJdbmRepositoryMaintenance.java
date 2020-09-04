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

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * JDBM repository maintenance operations.
 */
@objid ("533fe1ff-c00f-4bc6-9d12-c41c9cd0ba07")
public interface IJdbmRepositoryMaintenance {
    /**
     * Defragments the repository and recover unused disk space.
     * @param monitor a Modelio progress monitor
     * @throws java.io.IOException in case of failure
     */
    @objid ("e18c3549-1221-4689-ac5f-93740a88d5a9")
    void defragment(IModelioProgress monitor) throws IOException;

}
