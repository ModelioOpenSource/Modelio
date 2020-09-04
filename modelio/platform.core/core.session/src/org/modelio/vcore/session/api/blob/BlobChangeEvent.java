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

package org.modelio.vcore.session.api.blob;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Default implementation for {@link IBlobChangeEvent}.
 */
@objid ("189d970a-21e5-4778-953a-d6109f133524")
public class BlobChangeEvent implements IBlobChangeEvent {
    @objid ("ac2fc435-d566-41fc-a69d-a9958b3efc84")
    protected Collection<IBlobInfo> createdBlobs;

    @objid ("ce7d6bd1-4508-42a2-9a51-669e65a18cc7")
    protected Collection<IBlobInfo> deletedBlobs;

    @objid ("926349a7-b42a-47b7-b6e8-0ab345b8cb70")
    protected Collection<IBlobInfo> updatedBlobs;

    /**
     * Initialize a blob change event.
     * 
     * @param createdBlobs created blob identifiers.
     * @param deletedBlobs deleted blob identifiers.
     * @param updatedBlobs updated blob identifiers.
     */
    @objid ("ff9827a6-33dd-46a5-a8a3-d47fa7a74ca2")
    public BlobChangeEvent(Collection<IBlobInfo> createdBlobs, Collection<IBlobInfo> deletedBlobs, Collection<IBlobInfo> updatedBlobs) {
        this.createdBlobs = createdBlobs;
        this.deletedBlobs = deletedBlobs;
        this.updatedBlobs = updatedBlobs;
        
        // Shields collections with unmodifiable collections when assertions are enabled.
        assert shieldFields();
    }

    /**
     * Protected empty constructor.
     * <p>
     * To be called or redefined by sub classes.
     */
    @objid ("ca399fb1-7db5-453a-8ea5-66a952ee6677")
    protected BlobChangeEvent() {
        // nothing
    }

    /**
     * Get the new blobs.
     * 
     * @return the identifier of created blobs.
     */
    @objid ("d59f724f-7fe4-4bf3-bc90-24eee271d32d")
    @Override
    public Collection<IBlobInfo> getCreatedBlobs() {
        return this.createdBlobs;
    }

    /**
     * Get the deleted blobs.
     * 
     * @return the identifier of deleted blobs.
     */
    @objid ("c577d88f-9f6a-4a60-b308-c6f2ad58583b")
    @Override
    public Collection<IBlobInfo> getDeletedBlobs() {
        return this.deletedBlobs;
    }

    /**
     * Get the modified blobs.
     * 
     * @return the identifier of modified blobs.
     */
    @objid ("40ab327f-0905-4936-98ca-d804eecad841")
    @Override
    public Collection<IBlobInfo> getUpdatedBlobs() {
        return this.updatedBlobs;
    }

    /**
     * Shields the collections from modifications with unmodifiable collections.
     * <p>
     * Called by {@link #BlobChangeEvent(Collection, Collection, Collection)}
     * only when assertions are enabled.
     * May be called by sub classes.
     * 
     * @return always <code>true</code>.
     */
    @objid ("0173d951-24c6-4e2b-931b-f3aa32d8996c")
    protected boolean shieldFields() {
        this.createdBlobs = Collections.unmodifiableCollection(this.createdBlobs);
        this.deletedBlobs = Collections.unmodifiableCollection(this.deletedBlobs);
        this.updatedBlobs = Collections.unmodifiableCollection(this.updatedBlobs);
        return true;
    }

}
