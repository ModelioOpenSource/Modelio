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

package org.modelio.diagram.elements.common.portcontainer;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener.Stub;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Policy that shows a dashed link between all satellites figures and their main figure when any of them is selected.
 * 
 * @author cmarin
 */
@objid ("7f0041da-1dec-11e2-8cad-001ec947c8cc")
public class SatelliteChildrenSelectionPolicy extends GraphicalEditPolicy {
    @objid ("7f0041e9-1dec-11e2-8cad-001ec947c8cc")
    private int state = -1;

    @objid ("6792d65c-f7ae-4b14-8f11-718fab1e7489")
    private EditPartListener selectionListener;

    @objid ("e2fc4b06-80c1-41a7-bb3f-f1c6b25a6f04")
    private EditPartListener compositionListener;

    /**
     * Edit part of the main node.
     */
    @objid ("6b293b6b-1db8-4ed0-8476-1dbcf674b58a")
    private EditPart mainEditPart;

    @objid ("1bbdb8ff-0814-476d-b4cf-9bbcb12ecd8c")
    private List<PolylineConnection> focuslinks = new ArrayList<>();

    /**
     * Extends activate to hook the appropriate listener and to initialize the visual changes for representing
     * selection/focus.
     * @see org.eclipse.gef.EditPolicy#activate()
     */
    @objid ("7f0041ee-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void activate() {
        super.activate();
        addSelectionListener();
        setSelectedState(getHost().getSelected());
    }

    /**
     * Extends deactivate to unhook the seleciton listener and to remove the visual changes for representing
     * selection/focus.
     * @see org.eclipse.gef.EditPolicy#deactivate()
     */
    @objid ("7f0041f2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        removeSelectionListener();
        setSelectedState(EditPart.SELECTED_NONE);
        super.deactivate();
    }

    /**
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(Request)
     */
    @objid ("7f0041f6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (RequestConstants.REQ_SELECTION.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    /**
     * Adds an EditPartListener to the host to observe selection/focus changes.
     */
    @objid ("7f004202-1dec-11e2-8cad-001ec947c8cc")
    protected void addSelectionListener() {
        if (this.compositionListener == null) {
            this.compositionListener = new CompositionListener();
        }
        
        if (this.selectionListener == null) {
            this.selectionListener = new SelectionListener();
        }
        
        getHost().addEditPartListener(this.selectionListener);
        getHost().addEditPartListener(this.compositionListener);
        
        for (Object o : getHost().getChildren()) {
            EditPart c = (EditPart) o;
            c.addEditPartListener(this.selectionListener);
            if (isMainPart(c)) {
                this.mainEditPart = c;
            }
        }
    }

    /**
     * Get the selection listener used to show satellite links.
     * 
     * @return the selection listener.
     */
    @objid ("7f004205-1dec-11e2-8cad-001ec947c8cc")
    protected EditPartListener getSelectionListener() {
        return this.selectionListener;
    }

    @objid ("7f00420c-1dec-11e2-8cad-001ec947c8cc")
    protected void hideSelection() {
        for (PolylineConnection l : this.focuslinks) {
            removeFeedback(l);
        }
        this.focuslinks.clear();
    }

    @objid ("7f00420e-1dec-11e2-8cad-001ec947c8cc")
    protected boolean isMainPart(final EditPart child) {
        if (!(child.getModel() instanceof GmNodeModel)) {
            return false;
        }
        
        GmNodeModel m = (GmNodeModel) child.getModel();
        GmPortContainer pc = (GmPortContainer) m.getParentNode();
        return pc != null && pc.getMainNode() == m;
    }

    @objid ("7f004216-1dec-11e2-8cad-001ec947c8cc")
    protected boolean isSatellitePart(final EditPart child) {
        if (!(child.getModel() instanceof GmNodeModel)) {
            return false;
        }
        
        GmNodeModel m = (GmNodeModel) child.getModel();
        GmPortContainer pc = (GmPortContainer) m.getParentNode();
        return pc.isSatellite(m);
    }

    /**
     * Removes the EditPartListeners used to observe selection
     */
    @objid ("7f02a422-1dec-11e2-8cad-001ec947c8cc")
    protected void removeSelectionListener() {
        if (this.selectionListener != null) {
            getHost().removeEditPartListener(this.selectionListener);
            getHost().removeEditPartListener(this.compositionListener);
        
            for (Object o : getHost().getChildren()) {
                EditPart c = (EditPart) o;
                c.removeEditPartListener(this.selectionListener);
            }
        }
    }

    @objid ("7f02a425-1dec-11e2-8cad-001ec947c8cc")
    protected void setMainEditPart(final EditPart p) {
        this.mainEditPart = p;
    }

    /**
     * Sets the internal selection value. This method is called automatically by the listener. If the selection value is
     * changed, the appropriate method is called to show the specified selection type.
     * 
     * @param type the type of selection the EditPolicy should display
     */
    @objid ("7f02a42b-1dec-11e2-8cad-001ec947c8cc")
    protected void setSelectedState(final int type) {
        if (this.state == type) {
            return;
        }
        this.state = type;
        if (type == EditPart.SELECTED_PRIMARY) {
            showPrimarySelection();
        } else if (type == EditPart.SELECTED) {
            showSelection();
        } else {
            hideSelection();
        }
    }

    /**
     * Calls {@link #showSelection()} by default. Override to distinguish between primary and normal selection.
     */
    @objid ("7f02a430-1dec-11e2-8cad-001ec947c8cc")
    protected void showPrimarySelection() {
        showSelection();
    }

    @objid ("7f02a433-1dec-11e2-8cad-001ec947c8cc")
    protected void showSelection() {
        if (this.mainEditPart == null) {
            return;
        }
        
        final IFigure mainfig = ((GraphicalEditPart) this.mainEditPart).getFigure();
        final ConnectionAnchor targetAnchor = new ChopboxAnchor(mainfig);
        
        for (Object o : getHost().getChildren()) {
            EditPart childPart = (EditPart) o;
            if (isSatellitePart(childPart)) {
                final IFigure childFig = ((GraphicalEditPart) childPart).getFigure();
                final ConnectionAnchor srcAnchor = new ChopboxAnchor(childFig);
                PolylineConnection focuslink = new PolylineConnection();
                focuslink.setSourceAnchor(srcAnchor);
                focuslink.setTargetAnchor(targetAnchor);
                focuslink.setLineStyle(org.eclipse.swt.SWT.LINE_DOT);
                addFeedback(focuslink);
                this.focuslinks.add(focuslink);
            }
        }
    }

    /**
     * Selection listener that show links from satellites to main node.
     * 
     * @author cmarin
     */
    @objid ("7f02a435-1dec-11e2-8cad-001ec947c8cc")
    private class SelectionListener extends Stub {
        @objid ("7f02a439-1dec-11e2-8cad-001ec947c8cc")
        public SelectionListener() {
        }

        @objid ("7f02a43b-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void selectedStateChanged(final EditPart part) {
            setSelectedState(part.getSelected());
        }

    }

    /**
     * Listen children addition to put a selection listener on them.
     * 
     * @author cmarin
     */
    @objid ("7f02a442-1dec-11e2-8cad-001ec947c8cc")
    private class CompositionListener extends Stub {
        @objid ("7f02a446-1dec-11e2-8cad-001ec947c8cc")
        public CompositionListener() {
        }

        @objid ("7f02a448-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void childAdded(final EditPart child, final int index) {
            if (isMainPart(child)) {
                child.addEditPartListener(getSelectionListener());
                setMainEditPart(child);
            } else if (isSatellitePart(child)) {
                child.addEditPartListener(getSelectionListener());
            }
        }

        @objid ("7f02a451-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void removingChild(final EditPart child, final int index) {
            child.removeEditPartListener(getSelectionListener());
            
            hideSelection();
            
            if (isMainPart(child)) {
                setMainEditPart(null);
            }
        }

    }

}
