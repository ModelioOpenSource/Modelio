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

package org.modelio.vcore.session.api.model.change;

import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Represents a model change event.
 * <p>
 * Model change events are triggered for the following situations:
 * <ul>
 * <li> A "session" transaction is committed.
 * <li> A "session" transaction is undone
 * <li> A "session" transaction is redone
 * <li> The repository modifies the model
 * </ul>
 */
@objid ("007fe630-bd79-1f3b-aafd-001ec947cd2a")
public interface IModelChangeEvent {
    /**
     * Get the origin of this model change event
     * @return the model change event cause.
     */
    @objid ("c756ebb0-76b2-493b-8c4d-549e8f84887d")
    ChangeCause getCause();

    /**
     * Get a resume of model object creations.
     * <p>
     * When whole composition trees are created the list only contains the root and CMS node elements .
     * @return the created elements.
     */
    @objid ("002a1b1a-d402-1f3b-aafd-001ec947cd2a")
    Set<MObject> getCreationEvents();

    /**
     * Get a resume of model object deletions.
     * <p>
     * When whole composition trees are deleted the list only contains the root and CMS node elements .
     * @return model objects deletions
     */
    @objid ("002a590e-d402-1f3b-aafd-001ec947cd2a")
    List<IElementDeletedEvent> getDeleteEvents();

    /**
     * Get all model moves.
     * @return all model moves.
     */
    @objid ("002a457c-d402-1f3b-aafd-001ec947cd2a")
    List<IElementMovedEvent> getMoveEvents();

    /**
     * Get all deleted composition root model objects.
     * <p>
     * This list contains all composition tree root that were deleted.
     * @return all deleted composition roots.
     * @since 3.6
     */
    @objid ("4d055f3a-2499-421e-beb2-b573014f3587")
    Set<MObject> getRootDeletionEvents();

    /**
     * Get all modified model objects.
     * <p>
     * This list does not contain the composition owner of created elements, unless other
     * modification was made on the owner.
     * @return all modified model objects.
     */
    @objid ("002a31e0-d402-1f3b-aafd-001ec947cd2a")
    Set<MObject> getUpdateEvents();

    /**
     * Test whether the model change event is empty.
     * <p>
     * An empty model change events does not need to be fired.
     * @return <code>true</code> if the model change event is empty.
     */
    @objid ("f2ba2ad3-dfb5-4e07-a719-3c3f9c83c695")
    boolean isEmpty();

}
