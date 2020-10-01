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

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * JDBM repository maintenance operations implementation.
 */
@objid ("c141c2e5-1a61-4aed-b9e0-c9f78490800c")
class JdbmMaintenanceOperations implements IJdbmRepositoryMaintenance {
    @objid ("8720d5e6-0cb3-40ec-bd81-41991dd29bb4")
    private RecordManager db;

    @objid ("41e12ab1-936c-4197-bbb8-f65cd8f567b0")
    JdbmMaintenanceOperations(RecordManager db) {
        this.db = db;
    }

    @objid ("66d36bf7-6160-4501-bbbb-c0e109fb1abb")
    @Override
    public void defragment(IModelioProgress monitor) throws IOException {
        this.db.defrag();
    }

}
