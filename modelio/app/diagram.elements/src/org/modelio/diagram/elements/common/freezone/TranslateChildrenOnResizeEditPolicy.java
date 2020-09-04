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

package org.modelio.diagram.elements.common.freezone;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.commands.PostLayoutCommand;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Edit policy that catches {@link PostLayoutCommand#REQ_TYPE} requests to translate children
 * if the request tells this edit part was resized toward left and/or top.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("cf6a4a79-c6ac-4f5a-8b3c-d2cd5fa57aa4")
public class TranslateChildrenOnResizeEditPolicy extends GraphicalEditPolicy {
    @objid ("c8963af4-7b6a-41d6-b663-699515deb543")
    @Override
    public boolean understandsRequest(Request req) {
        return req.getType().equals(PostLayoutCommand.REQ_TYPE);
    }

    @objid ("2d1fae46-2332-42a4-b1d1-675d030ebad0")
    @Override
    public Command getCommand(Request request) {
        if (request.getType().equals(PostLayoutCommand.REQ_TYPE)) {
            return getTranslateChildrenCommand();
        }
        return null;
    }

    @objid ("d367786c-114a-41fd-be97-73a18e91b2da")
    private Command getTranslateChildrenCommand() {
        if (getHost().getChildren().isEmpty()) {
            return null;
        } else {
            return new TranslateChildrenOnResizeCommand((GraphicalEditPart) getHost());
        }
    }

    @objid ("f0edc3a0-5d78-4dbf-8f3f-74f30c70abbb")
    protected static class TranslateChildrenOnResizeCommand extends Command {
        @objid ("5e074fa9-e542-42b6-b469-68826a73de30")
        private Rectangle oldBounds;

        @objid ("990cc070-6546-48b4-885f-bcea7d8ca13d")
        private GraphicalEditPart editPart;

        @objid ("a1da07dd-d906-4e6a-a29a-ef383ad61891")
        public TranslateChildrenOnResizeCommand(GraphicalEditPart host) {
            IFigure hostFigure = host.getFigure();
            this.editPart = host;
            this.oldBounds = hostFigure.getBounds().getCopy();
        }

        @objid ("34f80763-f162-43ae-b83d-50f7c7406e60")
        @Override
        public void execute() {
            IFigure hostFigure = this.editPart.getFigure();
            hostFigure.getUpdateManager().performValidation();
            Rectangle newBounds = hostFigure.getBounds();
            
            if (this.oldBounds.equals(newBounds)) {
                return;
            }
            
            Rectangle absOldBounds = this.oldBounds.getCopy();
            Rectangle absNewBounds = newBounds.getCopy();
            
            hostFigure.translateToAbsolute(absNewBounds);
            hostFigure.translateToAbsolute(absOldBounds);
            
            Dimension minsize = hostFigure.getMinimumSize().getCopy();
            hostFigure.translateToAbsolute(minsize);
            
            int dx = absNewBounds.x - absOldBounds.x;
            int dy = absNewBounds.y - absOldBounds.y;
            int dw = absNewBounds.width - absOldBounds.width;
            int dh = absNewBounds.height - absOldBounds.height;
            
            int tx = 0;
            int ty = 0;
            
            if (dx < 0 && dw > 0) {
                // expanded left border toward left
                tx = Math.min(-dx, dw);
            } else if (dx > 0 && dw < 0) {
                // shrunk left border toward right
                int curW = absNewBounds.width();
                tx = - Math.min(dx, curW - minsize.width());
            }
            
            if (dy < 0 && dh > 0) {
                // expanded top border toward top
                ty = Math.min(-dy, dh);
            }  else if (dy > 0 && dh < 0) {
                // shrunk top border toward bottom
                int curW = absNewBounds.height();
                tx = - Math.min(dy, curW - minsize.height());
            }
            
            if (tx != 0 || ty != 0) {
            //                DiagramElements.LOG.debug(String.format("%s: translate children by (%d, %d) because bounds changed: location += (%d, %d), size += (%d, %d)\n\t\t for %s",
            //                        getClass().getSimpleName(), tx, ty, dx, dy, dw, dh, this.editPart));
                ChangeBoundsRequest r = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
                r.setEditParts(this.editPart.getChildren());
                r.getMoveDelta().setLocation(tx, ty);
                r.getExtendedData().put("noautoexpand", Boolean.TRUE); // to avoid some infinite loops from auto expand edit policies
            
                Command cmd = this.editPart.getCommand(r);
                if (cmd != null && cmd.canExecute()) {
                    cmd.execute();
                } else if (cmd==null) {
                    DiagramElements.LOG.warning("TranslateChildrenOnResizeEditPolicy: Cannot get command for children translate of dx=%d, dy=%d on %s", dx, dy, this.editPart);
                } else {
                    DiagramElements.LOG.warning("TranslateChildrenOnResizeEditPolicy: {%s} command is not executable for children translate of dx=%d, dy=%d on %s", cmd, dx, dy, this.editPart);
                }
            }
        }

        @objid ("07aed917-97bb-4734-9e21-1bffdb8c6350")
        @Override
        public String toString() {
            return getClass().getSimpleName()+" "+this.editPart+" children from "+this.oldBounds;
        }

    }

}
