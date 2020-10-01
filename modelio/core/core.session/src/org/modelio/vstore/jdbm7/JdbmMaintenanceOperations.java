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

package org.modelio.vstore.jdbm7;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * JDBM repository maintenance operations implementation.
 */
@objid ("173eeb57-3768-4242-9278-d60a90741ce0")
class JdbmMaintenanceOperations implements IJdbmRepositoryMaintenance {
    @objid ("9619638b-32f8-4585-9231-b61395eaf0c9")
    private RecordManager db;

    @objid ("4e769ca6-2aa5-4a0e-a3f7-34281d93caaf")
    JdbmMaintenanceOperations(RecordManager db) {
        this.db = db;
    }

    @objid ("b0afdeac-2425-446f-b9b7-4bea852c9909")
    @Override
    public void defragment(IModelioProgress monitor) throws IOException {
        this.db.defrag();
    }

}
