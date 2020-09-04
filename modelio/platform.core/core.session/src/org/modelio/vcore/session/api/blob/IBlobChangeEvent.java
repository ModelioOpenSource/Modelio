/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.session.api.blob;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Interface for blobs change events.
 */
@objid ("f34bf303-90eb-4951-b448-ed09799888df")
public interface IBlobChangeEvent {
    /**
     * Get the new blobs.
     * 
     * @return the identifier of created blobs.
     */
    @objid ("4bb33d1f-c76f-4fcd-954c-21ea5b31fd52")
    Collection<IBlobInfo> getCreatedBlobs();

    /**
     * Get the deleted blobs.
     * 
     * @return the identifier of deleted blobs.
     */
    @objid ("28ee8742-7fc6-4781-ae85-2efeaf44168d")
    Collection<IBlobInfo> getDeletedBlobs();

    /**
     * Get the modified blobs.
     * 
     * @return the identifier of modified blobs.
     */
    @objid ("6b3ea614-e83c-4720-bf2c-4a143a86234a")
    Collection<IBlobInfo> getUpdatedBlobs();

}
