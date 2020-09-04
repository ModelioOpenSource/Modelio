/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.api.modelio.model;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Model manipulation services.
 */
@objid ("6588a4d0-820c-11e0-ae7b-002564c97630")
public interface IModelManipulationService {
    /**
     * Clone the given element and attach it to the same parent.
     * @param element the element to clone
     * @return the cloned element
     */
    @objid ("a3c7bdb2-0ecc-11e2-96c4-002564c97630")
    MObject clone(final MObject element);

    /**
     * Copy the given elements under another one.
     * The destination may be in the same project or another open project.
     * @param elements The elements to copy.
     * @param to The destination element
     * @return The copied element for each element to copy.
     */
    @objid ("a3c7e4c6-0ecc-11e2-96c4-002564c97630")
    List<MObject> copyTo(final Collection<? extends MObject> elements, final MObject to);

    /**
     * Move the given elements under another one.
     * The destination must be in the same project.
     * <p>
     * Warning: do not use for elements having several parents like constraint, has it is equivalent to moveTo(elements, to, null).
     * </p>
     * @param elements The elements to copy.
     * @param to The destination element
     */
    @objid ("a3c832e5-0ecc-11e2-96c4-002564c97630")
    void moveTo(final Collection<? extends MObject> elements, final MObject to);

    /**
     * Move the given elements under another one.
     * The destination must be in the same project.
     * @param elements The elements to copy.
     * @param to The destination element
     * @param oldParentHint the old parent to detach the elements from. <b>Mandatory</b> for elements having several parents (like {@link Constraint}), optional otherwise.
     */
    @objid ("550b2fe3-5fb1-42c5-90b7-6e9c885ae70f")
    void moveTo(final Collection<? extends MObject> elements, final MObject to, final MObject oldParentHint);

}
