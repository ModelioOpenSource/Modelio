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

package org.modelio.diagram.editor.sequence.elements.stateinvariant;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.PlacementConstraint;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@link PlacementConstraint} implementation for a StateInvariant.
 * 
 * @author fpoyer
 */
@objid ("d99c1fb2-55b6-11e2-877f-002564c97630")
public class StateInvariantPlacementConstraint extends PlacementConstraint {
    @objid ("d99c1fb7-55b6-11e2-877f-002564c97630")
    private static final int MINIMUM_WIDTH = 50;

    @objid ("d99c1fb9-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("09eb175a-5371-45d3-904a-a0a6dadd46a6")
    private StateInvariant stateInvariant;

    /**
     * Constructor.
     * @param y
     * @param width
     * @param height
     * @param stateInvariant the represented StateInvariant
     * @param x the desired X coordinate in absolute coordinates.
     * @param diagram the diagram in which this constraint is used.
     */
    @objid ("d99c1fbb-55b6-11e2-877f-002564c97630")
    public StateInvariantPlacementConstraint(final StateInvariant stateInvariant, final int x, final int y, final int width, final int height, final GmSequenceDiagram diagram) {
        super(x, y, width, height, diagram);
        this.stateInvariant = stateInvariant;
        if (this.stateInvariant.isValid()) {
            setY(this.stateInvariant.getLineNumber());
            setHeight(this.stateInvariant.getEndLineNumber() - getY());
        }
    }

    /**
     * Empty constructor for deserialisation. Do not use!
     */
    @objid ("d99da629-55b6-11e2-877f-002564c97630")
    public StateInvariantPlacementConstraint() {
        super();
    }

    @objid ("d99da62c-55b6-11e2-877f-002564c97630")
    @Override
    public Rectangle getUpdatedBounds(final IFigure target) {
        Rectangle rect = super.getUpdatedBounds(target);
        // Make sure rect has a minimum width
        if (rect.width < StateInvariantPlacementConstraint.MINIMUM_WIDTH) {
            rect.width = StateInvariantPlacementConstraint.MINIMUM_WIDTH;
        }
        // Translate to the left so that state invariant is centered on the reference X.
        rect.translate(-rect.width / 2, 0);
        return rect;
    }

    @objid ("d99da633-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader reader) {
        super.read(reader);
        this.stateInvariant = resolveRef((MRef) reader.readProperty("StateInvariant"));
        if (this.stateInvariant.isValid()) {
            setY(this.stateInvariant.getLineNumber());
            setHeight(this.stateInvariant.getEndLineNumber() - getY());
        }
    }

    @objid ("d99da63a-55b6-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter writer) {
        super.write(writer);
        writer.writeProperty("StateInvariant", new MRef(this.stateInvariant));
    }

    @objid ("d99da641-55b6-11e2-877f-002564c97630")
    @Override
    protected int getHeight() {
        if (this.stateInvariant.isValid()) {
            setHeight(this.stateInvariant.getEndLineNumber() - getY());
        }
        return super.getHeight();
    }

    @objid ("d99da646-55b6-11e2-877f-002564c97630")
    @Override
    protected int getY() {
        if (this.stateInvariant.isValid()) {
            setY(this.stateInvariant.getLineNumber());
        }
        return super.getY();
    }

    @objid ("d99da64b-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
