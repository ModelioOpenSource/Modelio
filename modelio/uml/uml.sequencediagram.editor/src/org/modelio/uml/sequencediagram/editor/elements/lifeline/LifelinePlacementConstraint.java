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

package org.modelio.uml.sequencediagram.editor.elements.lifeline;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.uml.sequencediagram.editor.elements.executionoccurencespecification.ExecutionOccurenceSpecificationPlacementConstraint;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.PlacementConstraint;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@link PlacementConstraint} implementation for a lifeline.
 * 
 * @author fpoyer
 */
@objid ("d94cc716-55b6-11e2-877f-002564c97630")
public class LifelinePlacementConstraint extends PlacementConstraint {
    @objid ("d94cc71b-55b6-11e2-877f-002564c97630")
    private static final int ADDITIONAL_SPACE_AT_BOTTOM = 150;

    @objid ("d94cc71d-55b6-11e2-877f-002564c97630")
    private static final int DEFAULT_MIN_HEIGHT = 400;

    @objid ("d94cc720-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d672b3f3-f917-4c02-bda6-0002770c81be")
    private Lifeline lifeline;

    @objid ("8053eba6-8389-4c93-a06d-74a26696a09d")
    private IFigure tmpFig;

    /**
     * Constructor.
     * 
     * @param lifeline the represented lifeline
     * @param x the desired X coordinate in relative coordinates.
     * @param y the desired Y coordinate in relative coordinates.
     * @param width the desired width of the lifeline.
     * @param height the desired height of the lifeline.
     * @param diagram the diagram in which this constraint is used.
     */
    @objid ("d94cc722-55b6-11e2-877f-002564c97630")
    public LifelinePlacementConstraint(final Lifeline lifeline, final int x, final int y, final int width, final int height, final GmSequenceDiagram diagram) {
        // Y and height are meaningless for a lifeline (these coordinates are computed from the ObModel)
        super(x, y, width, height, diagram);
        this.lifeline = lifeline;
    }

    /**
     * Empty constructor for deserialisation. Do not use!
     */
    @objid ("d94cc733-55b6-11e2-877f-002564c97630")
    public LifelinePlacementConstraint() {
        super();
    }

    @objid ("d94e4d99-55b6-11e2-877f-002564c97630")
    @Override
    public Rectangle getUpdatedBounds(final IFigure target) {
        this.tmpFig = target;
        int preferredWidth = target.getPreferredSize().width;
        if (getWidth() < preferredWidth) {
            setWidth(preferredWidth);
        }
        Rectangle ret = new Rectangle(getX(), getY(), getWidth(), getHeight());
        this.tmpFig = null;
        return ret;
    }

    @objid ("d94e4da0-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader reader) {
        super.read(reader);
        this.lifeline = resolveRef((MRef) reader.readProperty("lifeline"));
    }

    @objid ("d94e4da7-55b6-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter writer) {
        super.write(writer);
        writer.writeProperty("lifeline", new MRef(this.lifeline));
    }

    @objid ("d94e4dae-55b6-11e2-877f-002564c97630")
    @Override
    protected int getHeight() {
        // Explore the lifeline to see if it is covered by an ExecutionOccurenceSpecification that receives a
        // Destruction Message. If it does, return its line number. Otherwise return last line of interaction + a delta;
        for (ExecutionOccurenceSpecification blueSquare : this.lifeline.getCoveredBy(ExecutionOccurenceSpecification.class)) {
            if (blueSquare.getReceivedMessage() != null &&
                    blueSquare.getReceivedMessage().getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
                return blueSquare.getLineNumber() -
                        getY() +
                        (ExecutionOccurenceSpecificationPlacementConstraint.DESTRUCTION_SIZE / 2);
            }
        
        }
        // No destroy message received, align the lifeline with the other lifelines, a few pixels under the last line of this interaction.
        Interaction interaction = this.lifeline.getOwner();
        int lastLine = 0;
        for (InteractionFragment fragment : interaction.getFragment()) {
            if (fragment.getLineNumber() > lastLine) {
                lastLine = fragment.getLineNumber();
            }
            if (fragment instanceof InteractionUse &&
                    ((InteractionUse) fragment).getEndLineNumber() > lastLine) {
                lastLine = ((InteractionUse) fragment).getEndLineNumber();
        
            }
            if (fragment instanceof InteractionOperand &&
                    ((InteractionOperand) fragment).getEndLineNumber() > lastLine) {
                lastLine = ((InteractionOperand) fragment).getEndLineNumber();
        
            }
            if (fragment instanceof StateInvariant &&
                    ((StateInvariant) fragment).getEndLineNumber() > lastLine) {
                lastLine = fragment.getLineNumber();
        
            }
        }
        int bottom = lastLine + ADDITIONAL_SPACE_AT_BOTTOM;
        bottom = bottom > DEFAULT_MIN_HEIGHT ? bottom : DEFAULT_MIN_HEIGHT;
        return bottom - getY();
    }

    @objid ("d94e4db3-55b6-11e2-877f-002564c97630")
    @Override
    protected int getY() {
        // Explore the lifeline to see if it is covered by an ExecutionOccurenceSpecification that receives a Creation
        // Message. If it does, return its line number. Otherwise return 0;
        for (ExecutionOccurenceSpecification blueSquare : this.lifeline.getCoveredBy(ExecutionOccurenceSpecification.class)) {
            if (blueSquare.getReceivedMessage() != null &&
                    blueSquare.getReceivedMessage().getSortOfMessage() == MessageSort.CREATEMESSAGE) {
                // compute header offset:
                int offset = this.tmpFig != null ? this.tmpFig.getChildren().size() > 0
                        ? ((IFigure) this.tmpFig.getChildren().get(0)).getPreferredSize().height / 2 : 0 : 0;
                return blueSquare.getLineNumber() - offset;
            }
        
        }
        return 0;
    }

    @objid ("d94e4db8-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("73439b74-3f76-42be-bf5c-b5a4156ab48e")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [");
        if (this.lifeline != null) {
            builder.append("lifeline=");
            builder.append(this.lifeline);
            builder.append(", ");
        }
        builder.append("loc()=(");
        builder.append(getX());
        builder.append(", ");
        builder.append(getY());
        builder.append("), size()=(");
        builder.append(getWidth());
        builder.append(", ");
        builder.append(getHeight());
        builder.append(")]");
        return builder.toString();
    }

}
