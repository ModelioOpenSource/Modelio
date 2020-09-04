/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.executionoccurencespecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

@objid ("d8e1f740-55b6-11e2-877f-002564c97630")
class SlaveAnchor extends AbstractConnectionAnchor implements AnchorListener {
    @objid ("d8e1f741-55b6-11e2-877f-002564c97630")
    private MasterExecutionOccurrenceSpecificationAnchor masterAnchor;

    /**
     * C'tor.
     * 
     * @param masterAnchor the anchor this anchor is a slave of.
     * @param owner the owner figure on which bounds this anchor will be placed.
     */
    @objid ("d8e1f742-55b6-11e2-877f-002564c97630")
    public SlaveAnchor(final MasterExecutionOccurrenceSpecificationAnchor masterAnchor, final IFigure owner) {
        super(owner);
        this.masterAnchor = masterAnchor;
        this.masterAnchor.addAnchorListener(this);
    }

    @objid ("d8e1f749-55b6-11e2-877f-002564c97630")
    @Override
    public Point getLocation(final Point reference) {
        return this.masterAnchor.getLastValidLocation();
    }

    @objid ("d8e1f750-55b6-11e2-877f-002564c97630")
    @Override
    public void anchorMoved(final ConnectionAnchor anchor) {
        fireAnchorMoved();
    }

}
