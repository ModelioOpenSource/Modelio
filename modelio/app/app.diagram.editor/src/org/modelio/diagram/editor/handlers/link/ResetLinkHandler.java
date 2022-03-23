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
@objid ("4d469da0-4fd1-4643-9591-1458899a225f")
public class ResetLinkHandler extends AbstractLinkHandler {
    /**
     * Execute the command.
     * @param selection the current diagram selection.
     */
    @objid ("26ec5cc8-e086-4d2d-87e6-0e7f7d552b26")
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
    @objid ("84ce3a81-f6f5-49c6-a4c4-6660fa298db7")
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

    @objid ("c93c7256-c065-4065-9b89-c69151e9dcc2")
    private static class ResetLinkCommand extends Command {
        @objid ("367d52fa-5a9a-4c6f-9995-f146fab03830")
        private List<LinkEditPart> linkEditPaths;

        @objid ("d21f9c1f-fab3-4e88-a90a-404be1f91223")
        public  ResetLinkCommand(List<LinkEditPart> linkEditPaths) {
            this.linkEditPaths = linkEditPaths;
        }

        @objid ("69fb4ad5-5734-4b4e-8759-d6af6c0459f2")
        @Override
        public void execute() {
            for (LinkEditPart linkEditPart : this.linkEditPaths) {
                GmLink gmLink = linkEditPart.getModel();
            
                AutoOrthogonalRouter router = new AutoOrthogonalRouter()
                        .setCleanupManualPoints(false)
                        .setIgnoreAutomaticPoints(true);
            
                // we need a new GmPath in all cases otherwise no property change is detected...
                IGmPath path = new GmPath(gmLink.getPath());
                List<MPoint> newConstraint = router.computeMPointRoute((Connection) linkEditPart.getFigure(), (List<MPoint>) path.getPathData());
            
                // remove first and last points that are anchors
                AutoOrthogonalRouter.routeToConstraint(newConstraint);
            
                path.setPathData(newConstraint);
                gmLink.setLayoutData(path);
            }
            
        }

        @objid ("b1d3a5f8-1244-469d-9590-a78db3f9cbdb")
        @Override
        public boolean canExecute() {
            return !this.linkEditPaths.isEmpty();
        }

    }

}
