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

package org.modelio.diagram.elements.common.portcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * <p>
 * Simple command that translate all children of an edit part by a given delta.
 * </p>
 * <p>
 * Note that this command makes this assertion that children's layout data (aka constraint) is a {@link Rectangle}.
 * 
 * @author fpoyer
 */
@objid ("7f050695-1dec-11e2-8cad-001ec947c8cc")
public class TranslateChildrenCommand extends Command {
    @objid ("203d7de4-1640-4738-b817-55a77d459ca4")
    private GraphicalEditPart container;

    @objid ("215d89c3-3c21-48a1-90ce-4db4e0256c04")
    private Point moveDelta;

    @objid ("c09238b9-90a0-4de1-83ed-f21e08bc37aa")
    private static final PrecisionRectangle tempRect = new PrecisionRectangle();

    @objid ("9ab51266-ca86-4ee8-985d-079bf027daa1")
    private static final PrecisionPoint tempPoint = new PrecisionPoint();

    /**
     * C'tor.
     * 
     * @param container the edit part which children must be translated.
     * @param moveDelta the translation delta.
     */
    @objid ("7f0506a7-1dec-11e2-8cad-001ec947c8cc")
    public TranslateChildrenCommand(GraphicalEditPart container, Point moveDelta) {
        this.container = container;
        this.moveDelta = moveDelta;
    }

    @objid ("7f0506b0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        for (Object childObj : this.container.getChildren()) {
            EditPart childEditPart = (EditPart) childObj;
            GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
            if (this.container.getModel().equals(childModel.getParent())) {
                // There is a possibility (when the port container is deleting
                // a child) that the edit part has a child whose model is not a
                // child of the GmPortContainer.
                assert (childModel.getLayoutData() == null || childModel.getLayoutData() instanceof Rectangle
                        || childModel.getLayoutData() instanceof PortConstraint) : "Unexpected type of constraint, this command can only work with Rectangle or PortContraint, found: " +
                                childModel.getLayoutData();
                if (childModel.getLayoutData() instanceof Rectangle) {
                    Rectangle originalRect = (Rectangle) childModel.getLayoutData();
                    Rectangle translatedRect = translateRect(originalRect);
                    childModel.setLayoutData(translatedRect);
                } else if (childModel.getLayoutData() instanceof PortConstraint) {
                    PortConstraint oldConstraint = (PortConstraint) childModel.getLayoutData();
                    PortConstraint newConstraint = new PortConstraint();
                    newConstraint.setRequestedBounds(translateRect(oldConstraint.getRequestedBounds()));
                    if (oldConstraint.getRequestedCenter() != null) {
                        newConstraint.setRequestedCenter(translatePoint(oldConstraint.getRequestedCenter()));
                    }
                    newConstraint.setReferenceBorder(oldConstraint.getReferenceBorder());
                    childModel.setLayoutData(newConstraint);
                }
            }
        }
    }

    @objid ("7f0506b3-1dec-11e2-8cad-001ec947c8cc")
    private Rectangle translateRect(final Rectangle rect) {
        TranslateChildrenCommand.tempRect.setBounds(rect);
        this.container.getFigure().translateToAbsolute(TranslateChildrenCommand.tempRect);
        TranslateChildrenCommand.tempRect.translate(this.moveDelta);
        this.container.getFigure().translateToRelative(TranslateChildrenCommand.tempRect);
        return new Rectangle(TranslateChildrenCommand.tempRect);
    }

    @objid ("7f0506bd-1dec-11e2-8cad-001ec947c8cc")
    private Point translatePoint(final Point point) {
        TranslateChildrenCommand.tempPoint.setLocation(point);
        this.container.getFigure().translateToAbsolute(TranslateChildrenCommand.tempPoint);
        TranslateChildrenCommand.tempPoint.translate(this.moveDelta);
        this.container.getFigure().translateToRelative(TranslateChildrenCommand.tempPoint);
        return new Point(TranslateChildrenCommand.tempPoint);
    }

}
