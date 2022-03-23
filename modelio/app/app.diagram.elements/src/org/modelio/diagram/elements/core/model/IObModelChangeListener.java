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
package org.modelio.diagram.elements.core.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface that Graphical elements use to update themselves from the Ob model changes.
 */
@objid ("8084e163-1dec-11e2-8cad-001ec947c8cc")
public interface IObModelChangeListener {
    /**
     * Called when the represented element that was previously absent from the model is now present in the model.
     * <p>
     * The Gm element should then refresh itself from the element.
     * @param resolvedElement The now present element.
     */
    @objid ("8084e168-1dec-11e2-8cad-001ec947c8cc")
    void obElementResolved(MObject resolvedElement);

    /**
     * Called when some Elements attributes are modified.
     * <p>
     * The Gm element should update here its label.
     */
    @objid ("8084e16b-1dec-11e2-8cad-001ec947c8cc")
    void obElementsUpdated();

    /**
     * Called when the related element is deleted.
     * <p>
     * The default implementation deletes itself.
     */
    @objid ("838148eb-8215-4cd0-a668-2729ab3482ea")
    void obElementDeleted();

}
