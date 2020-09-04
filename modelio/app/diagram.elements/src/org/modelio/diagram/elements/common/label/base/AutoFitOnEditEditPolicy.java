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

package org.modelio.diagram.elements.common.label.base;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.model.GmModel;

/**
 * Fit to new content on text edition.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("bee7cdfd-15a2-4947-9906-f0106b5a8482")
public class AutoFitOnEditEditPolicy extends GraphicalEditPolicy {
    /**
     * The role to use to register this policy.
     */
    @objid ("91f92505-4b67-4b23-8105-4dc2476e8d89")
    public static final Object ROLE = "fit_to_content_on_edit";

    @objid ("499816da-7cf5-41cc-83b1-a88421b311d1")
    @Override
    public Command getCommand(Request request) {
        if (request.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
            return new AutoFitCommand((GraphicalEditPart) getHost());
        }
        return null;
    }

    /**
     * Fit label figure to content according to the Ob model.
     * 
     * @author cmarin
     * @since 3.4
     */
    @objid ("54a719ba-f7d5-4a6f-9eae-0cb645bea612")
    protected class AutoFitCommand extends Command {
        @objid ("aadf7732-a23d-45a5-95d6-2cb7bb60b957")
        private static final boolean AUTO_RESIZE_TRACES = false;

        @objid ("102b261d-1d97-49ce-bd00-f099f435b6b2")
        private final GraphicalEditPart editPart;

        @objid ("7b2a2cf5-02ce-4ce0-a97e-f42be40347f9")
        @Override
        public void execute() {
            GmModel gmModel = (GmModel) this.editPart.getModel();
            IFigure fig = this.editPart.getFigure();
            
            Dimension oldSize = fig.getSize();
            Dimension oldPrefSize = fig.getPreferredSize(-1, -1);
            
            // need to refresh gm model (and view) now because it will be triggered only after transaction commit.
            gmModel.obElementsUpdated();
            
            int wHint = -1;
            if (!oldSize.equals(oldPrefSize)) {
                // Keep same width
                wHint = oldSize.width;
            }
            
            Dimension prefSize = fig.getPreferredSize(wHint, -1);
            
            if (!oldSize.equals(prefSize)) {
                ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
                req.setEditParts(this.editPart);
                Rectangle requestedBounds = fig.getBounds().getCopy().setSize(prefSize);
                RequestHelper.setDeltas(req, fig, requestedBounds);
            
                Command cmd = this.editPart.getCommand(req);
                if (cmd != null && cmd.canExecute()) {
                    cmd.execute();
                }
            }
        }

        @objid ("110d35a3-61bb-42a4-80ef-9b79fd843eb9")
        public AutoFitCommand(GraphicalEditPart editPart) {
            this.editPart = editPart;
        }

    }

}
