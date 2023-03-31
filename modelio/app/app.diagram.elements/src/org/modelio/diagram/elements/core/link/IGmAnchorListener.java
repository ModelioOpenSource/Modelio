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
package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Called by the anchor when its location changes.
 */
@objid ("328263df-1c9b-41cb-92fc-b494645de09a")
public interface IGmAnchorListener {
    /**
     * Called by the anchor when its location changes.
     * @param gmLinkAnchor The moved anchor.
     */
    @objid ("3747adba-574a-43a2-8ec6-8eeaa0e259b0")
    void fireAnchorMoved(GmAbstractLinkAnchor gmLinkAnchor);
}

