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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Listener of blob changes.
 * <p>
 * To be registered on the {@link IBlobSupport}.
 */
@objid ("b67340ff-e227-4d23-be30-50d3a31efedb")
public interface IBlobChangeListener {
    /**
     * Method called when some blobs were externally modified.
     * <p>
     * This method is called only when the blobs change cause is external
     * to the running Modelio instance, eg. when a Subversion update
     * modifies blobs. It may not be called if a Modelio plugin or module
     * modifies the blobs.
     * @param ev a blob change event.
     */
    @objid ("9e15860a-7366-483b-8f26-2732d1fe658b")
    void blobsChanged(IBlobChangeEvent ev);

}
