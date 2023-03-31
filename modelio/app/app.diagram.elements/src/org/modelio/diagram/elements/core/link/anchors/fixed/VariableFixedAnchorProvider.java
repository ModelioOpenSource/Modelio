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
package org.modelio.diagram.elements.core.link.anchors.fixed;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Default Factory of all possible FixedAnchors for a rectangular figure.
 */
@objid ("236b07fb-4e92-4624-ab05-ef933ca26651")
@Deprecated
class VariableFixedAnchorProvider extends AbstractFixedNodeAnchorProvider {
    @objid ("68ea9d2c-450f-4806-a03a-7ca6151b6425")
    private static final int DIST_BETWEEN_ANCHORS = 30;

    /**
     * C'tor setting a default minimum distance of {@value #DIST_BETWEEN_ANCHORS} between anchors.
     */
    @objid ("791f154a-0ebd-4c63-9eb2-36c7596ff2eb")
    public  VariableFixedAnchorProvider() {
        this(DIST_BETWEEN_ANCHORS);
    }

    /**
     * C'tor.
     * @param margin the minimum distance to keep between anchors.
     */
    @objid ("38fbcee8-86ed-48bb-a99f-2435f242c3ee")
    public  VariableFixedAnchorProvider(int margin) {
        super(new VariableFixedAnchorFactory("variable", margin));
    }

}
