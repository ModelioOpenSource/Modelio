/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.model.event;

import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This interface provide the API of a Model Change Event
 */
@objid ("01f413bc-0000-009c-0000-000000000000")
public interface IModelChangeEvent {
    /**
     * Used to get the list of moved elements.
     * <p><p>
     * An element is considered as moved only if it was not created or deleted during
     * the transaction.
     * <p><p>
     * The {@link IElementMovedEvent} class is used to determine more precise information.
     * 
     * @return A list of move events
     */
    @objid ("01f413bc-0000-00e2-0000-000000000000")
    List<IElementMovedEvent> getMoveEvents();

    /**
     * Used to get the list of deleted elements.
     * <p><p>
     * Only the top deleted elements are listed here. For example if a package P1, which contained a
     * class C1, has been deleted, only the package P1
     * will be given through the use of this returned list.
     * 
     * @return The list of deleted elements
     */
    @objid ("01f413bc-0000-00ea-0000-000000000000")
    List<IElementDeletedEvent> getDeleteEvents();

    /**
     * Used to get the list of created elements.
     * <p><p>
     * Only the top created elements are listed here. For example if a package P1 has been created
     * and in the same way, a class C1 has been created and added to the package, only the package P1
     * will be given through the use of this returned list.
     * 
     * @return The list of created elements
     */
    @objid ("a3daa9ee-0ecc-11e2-96c4-002564c97630")
    Set<MObject> getCreationEvents();

    /**
     * Used to get the list of updated elements.
     * <p><p>
     * An element is considered as updated only if it was not created or deleted during
     * the transaction.
     * 
     * @return The updated elements
     */
    @objid ("a3dad102-0ecc-11e2-96c4-002564c97630")
    Set<MObject> getUpdateEvents();

}
