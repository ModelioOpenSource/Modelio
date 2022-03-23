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
package org.modelio.vstore.exml.common.index;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vstore.exml.common.model.ObjId;

/**
 * Interface for the node uses indexes.
 */
@objid ("de5a4021-5c79-11e1-863f-001ec947ccaf")
public interface IUserNodeIndex {
    /**
     * Remove a CMS node from the indexes.
     * @param id
     * @throws IndexException in case of IO failure.
     */
    @objid ("32337f90-5c7b-11e1-863f-001ec947ccaf")
    void remove(final ObjId id) throws IndexException;

    /**
     * Add a used object.
     * @param userNodeId the user CMS node
     * @param depName the dependency from user to used.
     * @param usedObjectId the used object.
     * @throws IndexException in case of IO failure.
     */
    @objid ("32337f93-5c7b-11e1-863f-001ec947ccaf")
    void addUsed(final ObjId userNodeId, String depName, final ObjId usedObjectId) throws IndexException;

    /**
     * Get the CMS nodes using the given object.
     * @param objectId an object ID.
     * @return The ID of all CMS nodes using it.
     * @throws IndexException in case of IO failure.
     */
    @objid ("32337f98-5c7b-11e1-863f-001ec947ccaf")
    Collection<ObjId> getObjectUsers(final ObjId objectId, String depName) throws IndexException;

}
