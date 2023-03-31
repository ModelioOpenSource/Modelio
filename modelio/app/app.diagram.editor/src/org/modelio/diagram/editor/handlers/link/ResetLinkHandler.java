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
package org.modelio.diagram.editor.handlers.link;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.draw2d.Connection;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.platform.model.ui.swt.SelectionHelper;

/**
 * Handler for the "Reset link" contextual command.
 * <p>
 * Applies the {@link AutoOrthogonalRouter} on all selected links, reseting automatic points.
 * </p>
 */
@objid ("c9b8ddb0-6072-4358-9c12-a0c81475f5f6")
public class ResetLinkHandler extends AbstractLinkHandler {
    /**
     * Execute the command.
     * @param selection the current diagram selection.
     */
    @objid ("5dd5ad59-51b1-477a-a312-09ccb152d883")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<LinkEditPart> linkEditPaths = SelectionHelper.toList(selection, LinkEditPart.class);
        linkEditPaths.get(0).getViewer().getEditDomain().getCommandStack().execute(new ResetLinkCommand(linkEditPaths));
        
    }

    /**
     * Makes sure the selection contains only orthogonal links
     * @param selection the current diagram selection.
     * @return <code>true</code> if the handler can be executed.
     */
    @objid ("7647b052-eef7-43a1-a02b-ae6451f00b90")
    @Override
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        // Call super
        if (!super.canExecute(selection)) {
            return false;
        }
        
        for (final LinkEditPart linkEditpart : SelectionHelper.toList(selection, LinkEditPart.class)) {
            final GmLink link = linkEditpart.getModel();
        
            // Deactivate on non-orthogonal links
            if (link.getPath().getRouterKind() != ConnectionRouterId.ORTHOGONAL) {
                return false;
            }
        }
        return true;
    }

    /**
     * to be used as VisibleWhen expression in the E4 model.
     * @param selection the Eclispe selection
     * @return true if the command is visible
     */
    @objid ("bba834d5-6bd1-4566-af0b-4456d5acdb63")
    @Evaluate
    public boolean isVisible(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        // Call super
        /*
        if (!super.isVisible(selection)) {
            return false;
        }*/
        
        List<LinkEditPart> linkEditParts = SelectionHelper.toList(selection, LinkEditPart.class);
        for (final LinkEditPart linkEditpart : linkEditParts) {
            final GmLink link = linkEditpart.getModel();
        
            // Deactivate on non-orthogonal links
            if (link.getPath().getRouterKind() == ConnectionRouterId.ORTHOGONAL) {
                return true;
            }
        }
        return false;
    }

    @objid ("c6aa7e6e-31ff-4eb9-9718-481b55c96a69")
    private static class ResetLinkCommand extends Command {
        @objid ("c219ea55-1e9a-4a59-88b0-9aded2b033b1")
        private List<LinkEditPart> linkEditPaths;

        @objid ("4a800875-415f-4b6f-9f25-706df3f902d3")
        public  ResetLinkCommand(List<LinkEditPart> linkEditPaths) {
            this.linkEditPaths = linkEditPaths;
        }

        @objid ("b4ce1f74-c4e5-4ad0-9af2-256207d6a684")
        @Override
        public void execute() {
            for (LinkEditPart linkEditPart : this.linkEditPaths) {
                GmLink gmLink = linkEditPart.getModel();
            
                AutoOrthogonalRouter router = new AutoOrthogonalRouter()
                        .setCleanupManualPoints(false)
                        .setRerouteWrongSectionFromPreviousManualPoint(true);
            
                // we need a new GmPath in all cases otherwise no property change is detected...
                IGmPath path = new GmPath(gmLink.getPath());
                @SuppressWarnings ("unchecked")
                List<MPoint> pathData = (List<MPoint>) path.getPathData();
                pathData.removeIf(p -> !p.isFixed());
                List<MPoint> newConstraint = router.computeMPointRoute((Connection) linkEditPart.getFigure(), pathData);
            
                // remove first and last points that are anchors
                AutoOrthogonalRouter.routeToConstraint(newConstraint);
            
                path.setPathData(newConstraint);
                gmLink.setLayoutData(path);
            }
            
        }

        @objid ("0db24463-6655-415d-b67f-53a2686c4859")
        @Override
        public boolean canExecute() {
            return !this.linkEditPaths.isEmpty();
        }

    }

}
