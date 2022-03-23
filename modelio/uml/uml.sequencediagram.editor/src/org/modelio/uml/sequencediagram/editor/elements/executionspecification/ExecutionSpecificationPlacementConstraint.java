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
package org.modelio.uml.sequencediagram.editor.elements.executionspecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.PlacementConstraint;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@link PlacementConstraint} implementation for an execution.
 * 
 * @author fpoyer
 */
@objid ("d8e9988f-55b6-11e2-877f-002564c97630")
public class ExecutionSpecificationPlacementConstraint extends PlacementConstraint {
    @objid ("d8e99894-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d0bf3872-6cd2-4f84-b0fb-bb520f143d05")
    private ExecutionSpecification execution;

    /**
     * Constructor.
     * @param execution the represented execution
     * @param x the desired X coordinate in absolute coordinates.
     * @param diagram the diagram in which this constraint is used.
     */
    @objid ("d8e99896-55b6-11e2-877f-002564c97630")
    public  ExecutionSpecificationPlacementConstraint(final ExecutionSpecification execution, final int x, final int y, final int width, final int height, final GmSequenceDiagram diagram) {
        super(x, y, width, height, diagram);
        this.execution = execution;
        if (this.execution.isValid()) {
            setY(this.execution.getStart().getLineNumber());
            setHeight(this.execution.getFinish().getLineNumber() - getY());
        }
        
    }

    /**
     * Empty constructor for deserialisation. Do not use!
     */
    @objid ("d8eb1f06-55b6-11e2-877f-002564c97630")
    public  ExecutionSpecificationPlacementConstraint() {
        super();
    }

    @objid ("d8eb1f09-55b6-11e2-877f-002564c97630")
    @Override
    public Rectangle getUpdatedBounds(final IFigure target) {
        Point topLeft = new Point(getX(), getY());
        // Translate to the left so that execution visible part is centered on the reference X.
        topLeft.translate(-(ExecutionSpecificationEditPart.EXECUTION_WIDTH / 2), 0);
        Dimension dimension = new Dimension(target.getPreferredSize().width, this.getHeight());
        return new Rectangle(topLeft, dimension);
    }

    @objid ("d8eb1f10-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader reader) {
        super.read(reader);
        this.execution = resolveRef((MRef) reader.readProperty("execution"));
        if (this.execution.isValid()) {
            setY(this.execution.getStart().getLineNumber());
            setHeight(this.execution.getFinish().getLineNumber() - getY());
        }
        
    }

    @objid ("d8eb1f17-55b6-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter writer) {
        super.write(writer);
        writer.writeProperty("execution", new MRef(this.execution));
        
    }

    @objid ("d8eb1f1e-55b6-11e2-877f-002564c97630")
    @Override
    protected int getHeight() {
        if (this.execution.isValid()) {
            setHeight(this.execution.getFinish().getLineNumber() - getY());
        }
        return super.getHeight();
    }

    @objid ("d8eb1f23-55b6-11e2-877f-002564c97630")
    @Override
    protected int getY() {
        if (this.execution.isValid()) {
            setY(this.execution.getStart().getLineNumber());
        }
        return super.getY();
    }

    @objid ("d8eb1f28-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
