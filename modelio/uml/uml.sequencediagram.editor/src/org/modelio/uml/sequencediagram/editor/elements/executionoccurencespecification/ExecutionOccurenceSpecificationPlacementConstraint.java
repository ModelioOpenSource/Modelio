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
package org.modelio.uml.sequencediagram.editor.elements.executionoccurencespecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.PlacementConstraint;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Placement constraint for the ExecutionOccurenceSpecification.
 * 
 * @author fpoyer
 */
@objid ("d8da564c-55b6-11e2-877f-002564c97630")
public class ExecutionOccurenceSpecificationPlacementConstraint extends PlacementConstraint {
    @objid ("d8da5651-55b6-11e2-877f-002564c97630")
    private static final int DEFAULT_SIZE = 11;

    @objid ("d8da5653-55b6-11e2-877f-002564c97630")
    public static final int DESTRUCTION_SIZE = 21;

    @objid ("d8da5655-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("ad90d86e-d540-4cbf-b2e2-b9b5e31d1b05")
    private ExecutionOccurenceSpecification executionOccurenceSpecification;

    /**
     * Constructor.
     * @param executionOccurenceSpecification the represented execution
     * @param x the desired X coordinate in absolute coordinates.
     * @param y the desired Y coordinate in absolute coordinates.
     * @param width the desired width.
     * @param height the desired height.
     * @param diagram the diagram in which this constraint is used.
     */
    @objid ("d8dbdcb9-55b6-11e2-877f-002564c97630")
    public  ExecutionOccurenceSpecificationPlacementConstraint(final ExecutionOccurenceSpecification executionOccurenceSpecification, final int x, final int y, final int width, final int height, final GmSequenceDiagram diagram) {
        super(x, y, width, height, diagram);
        
        this.executionOccurenceSpecification = executionOccurenceSpecification;
        if (this.executionOccurenceSpecification != null && this.executionOccurenceSpecification.isValid()) {
            setY(this.executionOccurenceSpecification.getLineNumber());
        
            Message m = getValidReceivedMessage();
            if (m != null && m.getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
                setHeight(ExecutionOccurenceSpecificationPlacementConstraint.DESTRUCTION_SIZE);
            } else {
                setHeight(ExecutionOccurenceSpecificationPlacementConstraint.DEFAULT_SIZE);
            }
        }
        
    }

    /**
     * Empty constructor for deserialisation. Do not use!
     */
    @objid ("d8dbdcca-55b6-11e2-877f-002564c97630")
    public  ExecutionOccurenceSpecificationPlacementConstraint() {
        super();
    }

    @objid ("d8dbdccd-55b6-11e2-877f-002564c97630")
    @Override
    public Rectangle getUpdatedBounds(final IFigure target) {
        Point topLeft = new Point(getX(), getY());
        Dimension dimension = new Dimension(getWidth(), getHeight());
        // Translate to the left so that it is centered on the reference X.
        Message m = getValidReceivedMessage();
        if (m != null && m.getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
            topLeft.translate(-(ExecutionOccurenceSpecificationPlacementConstraint.DESTRUCTION_SIZE / 2), -(ExecutionOccurenceSpecificationPlacementConstraint.DESTRUCTION_SIZE / 2));
        } else {
            topLeft.translate(-(ExecutionOccurenceSpecificationPlacementConstraint.DEFAULT_SIZE / 2), -(ExecutionOccurenceSpecificationPlacementConstraint.DEFAULT_SIZE / 2));
        }
        return new Rectangle(topLeft, dimension);
    }

    @objid ("d8dbdcd4-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader reader) {
        super.read(reader);
        MRef ref = (MRef) reader.readProperty("executionOccurenceSpecification");
        this.executionOccurenceSpecification = ref != null ? resolveRef(ref) : null;
        
        if (this.executionOccurenceSpecification != null && this.executionOccurenceSpecification.isValid()) {
            setY(this.executionOccurenceSpecification.getLineNumber());
        
            Message receivedMessage = this.executionOccurenceSpecification.getReceivedMessage();
            if (receivedMessage != null &&
                    receivedMessage.isValid() &&
                    receivedMessage.getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
                setHeight(ExecutionOccurenceSpecificationPlacementConstraint.DESTRUCTION_SIZE);
            } else {
                setHeight(ExecutionOccurenceSpecificationPlacementConstraint.DEFAULT_SIZE);
            }
        }
        
    }

    @objid ("d8dbdcdb-55b6-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter writer) {
        super.write(writer);
        if (this.executionOccurenceSpecification != null) {
            writer.writeProperty("executionOccurenceSpecification", new MRef(this.executionOccurenceSpecification));
        }
        
    }

    @objid ("d8dbdce2-55b6-11e2-877f-002564c97630")
    @Override
    protected int getHeight() {
        Message m = getValidReceivedMessage();
        if (m != null && m.getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
            return ExecutionOccurenceSpecificationPlacementConstraint.DESTRUCTION_SIZE;
        } else {
            return ExecutionOccurenceSpecificationPlacementConstraint.DEFAULT_SIZE;
        }
        
    }

    @objid ("d8dbdce6-55b6-11e2-877f-002564c97630")
    @Override
    protected int getWidth() {
        Message m = getValidReceivedMessage();
        if (m != null && m.getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
            return ExecutionOccurenceSpecificationPlacementConstraint.DESTRUCTION_SIZE;
        } else {
            return ExecutionOccurenceSpecificationPlacementConstraint.DEFAULT_SIZE;
        }
        
    }

    @objid ("d8dbdcea-55b6-11e2-877f-002564c97630")
    @Override
    protected int getY() {
        if (this.executionOccurenceSpecification != null && this.executionOccurenceSpecification.isValid()) {
            return this.executionOccurenceSpecification.getLineNumber();
        } else {
            return super.getY();
        }
        
    }

    @objid ("d8dbdcee-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return ExecutionOccurenceSpecificationPlacementConstraint.MAJOR_VERSION;
    }

    @objid ("890c8914-002f-4701-9176-9ff15fca88d0")
    private Message getValidReceivedMessage() {
        if (this.executionOccurenceSpecification != null &&
                this.executionOccurenceSpecification.isValid()) {
            final Message receivedMessage = this.executionOccurenceSpecification.getReceivedMessage();
            if (receivedMessage != null && receivedMessage.isValid()) {
                return receivedMessage;
            }
        }
        return null;
    }

}
