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
package org.modelio.vstore.exml.common;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Maintenance operations on a {@link IExmlBase}.
 */
@objid ("53ff9417-e373-4104-aa8c-91c4dde65efb")
public interface IMaintenanceOperations {
    /**
     * Rebuild the indexes.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done()
     * on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @throws IOException in case of failure.
     */
    @objid ("7c7d512f-ffab-4763-b1c5-720087fbc936")
    void rebuildIndexes(IModelioProgress monitor) throws IOException;

    /**
     * Compress the indexes.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done()
     * on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @throws IOException in case of failure.
     */
    @objid ("1c87bf26-ba93-47e0-b894-a308be01ae03")
    void compressIndexes(IModelioProgress monitor) throws IOException;

    /**
     * Delete the indexes.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done()
     * on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @throws IOException in case of failure.
     */
    @objid ("fed874c8-2e9f-4bc2-9328-b8c22c1fb453")
    void deleteIndexes(IModelioProgress monitor) throws IOException;

    /**
     * Save the repository format versions.
     * @throws IOException on failure.
     */
    @objid ("f6d10f1a-5889-4cbf-8be5-3f80cedbc4fb")
    void writeFormatVersion() throws IOException;

    /**
     * Save the metamodel descriptor.
     * @throws IOException on failure.
     */
    @objid ("a719e924-a65d-4593-ae11-4aafd631e64b")
    void writeMetamodelDescriptor() throws IOException;

}
