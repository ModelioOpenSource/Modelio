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
package org.modelio.diagram.elements.core.link.migration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.core.model.IPostLoadAction;

@objid ("dbfa292f-29a4-45a6-8257-f7d55b3ed510")
public class LinkPathFixer implements IPostLoadAction {
    @objid ("9ab1790a-3890-4b26-a080-c456eab24cbe")
    private GmLink gmLink;

    @objid ("9a55e16e-5b69-4025-8774-bfea42af1160")
    public  LinkPathFixer(GmLink gmLink) {
        this.gmLink = gmLink;
    }

    @objid ("1f036c46-5cf1-4601-af03-7be3428aa9a9")
    @Override
    public void run(EditPartViewer viewer) {
        GraphicalEditPart linkEditPart = (GraphicalEditPart) viewer.getEditPartRegistry().get(this.gmLink);
        if (linkEditPart != null && linkEditPart.getFigure() instanceof Connection) {
            Connection connection = (Connection) linkEditPart.getFigure();
            @SuppressWarnings ("unchecked")
            List<Bendpoint> routingConstraint = (List<Bendpoint>) connection.getRoutingConstraint();
            if (routingConstraint.size() > 0) {
                IFigure figure = ((GraphicalEditPart) ((GraphicalEditPart) viewer.getEditPartRegistry().get(this.gmLink.getDiagram())).getParent()).getFigure();
                Rectangle originBounds = figure.getBounds().getCopy();
                figure.translateToAbsolute(originBounds);
                Point negated = originBounds.getTopLeft().getNegated();
        
                List<Point> points = BendPointUtils.draw2dConstraintToModelConstraint(routingConstraint);
                for (Point p : points) {
                    p.translate(negated);
                }
                applyLayoutData(points);
            }
        }
        
    }

    /**
     * Update the layout data of the {@link #gmLink}.
     * @param newPathData a list of points.
     */
    @objid ("445b27f2-6384-479c-9045-d12762e8f5c9")
    private void applyLayoutData(List<Point> newPathData) {
        GmPath newGmPath = new GmPath(this.gmLink.getPath());
        newGmPath.setPathData(newPathData);
        this.gmLink.setLayoutData(newGmPath);
        
    }

}
