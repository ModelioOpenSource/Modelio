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

package org.modelio.linkeditor.panel.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.graph.Subgraph;

/**
 * A "bus" used to enhance readability of links.
 */
@objid ("1badd242-5e33-11e2-b81d-002564c97630")
public class EdgeBus extends Subgraph {
    /**
     * C'tor.
     */
    @objid ("1badd246-5e33-11e2-b81d-002564c97630")
    public EdgeBus() {
        super(null);
        this.width = 3;
        this.height = 3;
    }

}
