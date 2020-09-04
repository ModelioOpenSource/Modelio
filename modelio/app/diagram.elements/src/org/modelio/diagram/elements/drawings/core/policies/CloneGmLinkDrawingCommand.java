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

package org.modelio.diagram.elements.drawings.core.policies;

import java.lang.reflect.InvocationTargetException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Commands that clones a drawing link.
 */
@objid ("949f645d-b44b-4413-8698-1f016d6268fe")
public class CloneGmLinkDrawingCommand extends Command implements RequestConstants {
    @objid ("a9c338e3-90a5-467a-b5df-18abb6e31d3e")
    private ConnectionEditPart editPartToClone;

    @objid ("8a29ee6b-2f01-4411-9bd4-9565086ac243")
    private Point moveDelta;

    /**
     * Initialize the command
     * @param editPartToClone the connection edit part to move
     * @param moveDelta the move delta in absolute coordinates
     */
    @objid ("c372e6cf-0b1d-4d46-af3e-fba84359d7b0")
    public CloneGmLinkDrawingCommand(ConnectionEditPart editPartToClone, Point moveDelta) {
        this.editPartToClone = editPartToClone;
        this.moveDelta = moveDelta;
    }

    @objid ("8d3876ad-23b6-4475-867d-580b51b37a48")
    @Override
    public boolean canExecute() {
        IGmDrawingLink toClone = (IGmDrawingLink) this.editPartToClone.getModel();
        
        MObject el = toClone.getFrom().getLayer().getRelatedElement();
        if (!MTools.getAuthTool().canModify(el)) {
            return false;
        }
        
        el = toClone.getTo().getLayer().getRelatedElement();
        if (!MTools.getAuthTool().canModify(el)) {
            return false;
        }
        return true;
    }

    @objid ("5764d923-4e3a-4568-a005-24c2f0eb31cf")
    @Override
    public void execute() {
        // 1) clone the link model
        IGmDrawingLink toClone = (IGmDrawingLink) this.editPartToClone.getModel();
        IGmDrawingLink cloned = copyModel(toClone);
        
        // Look for the cloned connection edit part
        ConnectionEditPart clonedPart = (ConnectionEditPart) this.editPartToClone.getViewer().getEditPartRegistry().get(cloned);
        
        if (clonedPart == null) {
            throw new IllegalStateException("No editPart for " + cloned);
        }
        
        Connection fig = (Connection) this.editPartToClone.getFigure();
        
        // Move the source
        ReconnectRequest r2 = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
        Point pSource = fig.getPoints().getFirstPoint();
        Point pAbsSource = pSource.getCopy();
        fig.translateToAbsolute(pAbsSource);
        pAbsSource.translate(this.moveDelta);
        r2.setLocation(pAbsSource);
        r2.setTargetEditPart(this.editPartToClone.getSource());
        r2.setConnectionEditPart(clonedPart);
        
        runRequest(r2);
        
        // Move the target
        ReconnectRequest r3 = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
        Point pDest = fig.getPoints().getLastPoint();
        Point pAbsDest = pDest.getCopy();
        fig.translateToAbsolute(pAbsDest);
        pAbsDest.translate(this.moveDelta);
        r3.setLocation(pAbsDest);
        r3.setTargetEditPart(this.editPartToClone.getTarget());
        r3.setConnectionEditPart(clonedPart);
        
        runRequest(r3);
    }

    @objid ("a980c18f-c3ea-4496-aa91-4c3a9a8930f4")
    private void runRequest(ReconnectRequest r) {
        EditPart target = r.getTarget().getTargetEditPart(r);
        Command c = target.getCommand(r);
        
        if (c == null || !c.canExecute()) {
            DiagramElements.LOG.warning(new IllegalStateException("Cannot run " + r + " with " + c + " command."));
            return;
        }
        
        c.execute();
    }

    @objid ("33298b95-8460-429b-bd43-02a251caaa4e")
    private IGmDrawingLink copyModel(IGmDrawingLink toCopy) {
        try {
            IGmDrawingLink newlink = toCopy.getClass().getConstructor(IGmDiagram.class).newInstance(toCopy.getDiagram());
            // TODO : handle future composite nodes
        
            final GmPath newPath = new GmPath(toCopy.getPath());
            newlink.setLayoutData(newPath);
        
            // Copy graphic options
            IStyle origStyle = toCopy.getPersistedStyle();
            IStyle newStyle = newlink.getPersistedStyle();
            newStyle.setCascadedStyle(origStyle.getCascadedStyle());
            for (StyleKey k : origStyle.getLocalKeys()) {
                newStyle.setProperty(k, origStyle.getProperty(k));
            }
        
            toCopy.getFrom().addStartingDrawingLink(newlink);
            toCopy.getTo().addEndingDrawingLink(newlink);
        
            return newlink;
        
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // TODO : report in another way ?
            throw new RuntimeException(e.toString(), e);
        }
    }

}
