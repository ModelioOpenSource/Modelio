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
package org.modelio.diagram.elements.core.link.ortho;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.tools.ConnectionBendpointTracker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.platform.ui.UIImages;
import org.modelio.vcore.model.api.MTools;

/**
 * Specialisation of the {@link ConnectionBendpointTracker} to add the information of the previous segment orientation.
 * 
 * @author fpoyer
 */
@objid ("803633c5-1dec-11e2-8cad-001ec947c8cc")
public class OrientedBendpointTracker extends ConnectionBendpointTracker {
    @objid ("90d80577-1e83-11e2-8cad-001ec947c8cc")
    private Orientation orientation = Orientation.NONE;

    @objid ("beeb2af1-bdbb-4614-baaa-f39c3bf96355")
    private Menu savedmenu;

    /**
     * Sets the orientation of the segment preceding the reference bendpoint.
     * @param orientation the orientation of the segment preceding the reference bendpoint.
     */
    @objid ("803895d7-1dec-11e2-8cad-001ec947c8cc")
    public void setOrientation(final Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * C'tor.
     * @param orientation the orientation of the segment preceding the reference bendpoint.
     */
    @objid ("803895dc-1dec-11e2-8cad-001ec947c8cc")
    public  OrientedBendpointTracker(final Orientation orientation) {
        super();
        this.orientation = orientation;
        
    }

    /**
     * C'tor.
     * @param editpart the connection
     * @param i the index of the bendpoint
     * @param orientation the orientation of the segment preceding the reference bendpoint.
     */
    @objid ("803895e1-1dec-11e2-8cad-001ec947c8cc")
    public  OrientedBendpointTracker(final ConnectionEditPart editpart, final int i, final Orientation orientation) {
        super(editpart, i);
        this.orientation = orientation;
        
    }

    @objid ("803895ec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void updateSourceRequest() {
        super.updateSourceRequest();
        BendpointRequest request = (BendpointRequest) getSourceRequest();
        request.getExtendedData().put(Orientation.class, this.orientation);
        
    }

    @objid ("c3c7c71b-c157-4198-9953-522461e42cf8")
    @Override
    protected boolean handleButtonDown(int button) {
        if (button == 3) {
            BendpointRequest request = (BendpointRequest) getSourceRequest();
        
            int index = request.getIndex();
            IGmLinkObject gmLink = (IGmLinkObject) request.getSource().getModel();
            IGmPath path = new GmPath(gmLink.getPath());
            @SuppressWarnings ("unchecked")
            List<MPoint> bendpoints = (List<MPoint>) path.getPathData();
            if (!bendpoints.get(index - 1).isFixed()) {
                return false;
            }
        
            Control viewerControl = getCurrentViewer().getControl();
            EditDomain editDomain = getCurrentViewer().getEditDomain();
        
            return openDeleteMPointMenu(request, index, viewerControl, editDomain);
        } else {
            return super.handleButtonDown(button);
        }
        
    }

    /**
     * Open a menu to delete user MPoints.
     */
    @objid ("92c85aad-e13c-493d-b5a9-95e8b88cb137")
    protected boolean openDeleteMPointMenu(BendpointRequest request, int index, Control viewerControl, EditDomain editDomain) {
        this.savedmenu = viewerControl.getMenu();
        
        Menu newMenu = new Menu(viewerControl);
        newMenu.addMenuListener(new MenuAdapter() {
            @Override
            public void menuHidden(MenuEvent e) {
                viewerControl.setMenu(OrientedBendpointTracker.this.savedmenu);
            }
        });
        viewerControl.setMenu(newMenu);
        
        MenuItem item = new MenuItem(newMenu, SWT.PUSH);
        String label = DiagramElements.I18N.getString("OrientedBendpointTracker.deletempoint.label");
        item.setText(label);
        item.setToolTipText(DiagramElements.I18N.getString("OrientedBendpointTracker.deletempoint.tooltip"));
        item.setImage(UIImages.REMOVE);
        item.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                DeleteMPointCommand command = new DeleteMPointCommand(request.getSource(), index - 1);
                command.setLabel(label);
                if (command.canExecute()) {
                    editDomain.getCommandStack().execute(command);
                }
            }
        });
        return true;
    }

    /**
     * Command that deletes an MPoint and synchronizes the routing constraint from the points computed by {@link AutoOrthogonalRouter}.
     */
    @objid ("fd33a842-1048-46b2-813d-5eaed9785d8e")
    public static class DeleteMPointCommand extends Command {
        @objid ("69ac277e-f079-46cb-8fae-52462af2395a")
        private final int index;

        @objid ("2167e638-466e-402e-960f-7d133c5fd756")
        private ConnectionEditPart connectionEditPart;

        @objid ("31801de5-dfd7-4fbc-a9f4-3adc440fc745")
        private IGmLinkObject gmLink;

        /**
         * Constructor.
         * @param connectionEditPart The link to edit
         * @param index the index of the point to remove.
         */
        @objid ("0504ba2a-39f1-4309-b677-615b0b44d1b6")
        public  DeleteMPointCommand(ConnectionEditPart connectionEditPart, int index) {
            this.index = index;
            
            this.connectionEditPart = connectionEditPart;
            this.gmLink = (IGmLinkObject) this.connectionEditPart.getModel();
            
        }

        @objid ("6736e289-d803-48ec-ace2-c9b692b98c73")
        @SuppressWarnings ("unchecked")
        @Override
        public void execute() {
            // we need a new GmPath in all cases otherwise no property change is detected...
            IGmPath path = new GmPath(this.gmLink.getPath());
            
            List<MPoint> bendpoints = (List<MPoint>) path.getPathData();
            bendpoints.remove(this.index);
            
            // remove next auto bend point too
            if (bendpoints.size() > this.index && !bendpoints.get(this.index).isFixed())
                bendpoints.remove(this.index);
            
            // remove previous auto bend point too
            if (this.index > 0 && !bendpoints.isEmpty() && !bendpoints.get(this.index-1).isFixed())
                bendpoints.remove(this.index-1);
            
            AutoOrthogonalRouter router = new AutoOrthogonalRouter()
                    .setCleanupManualPoints(false)
                    .setRerouteWrongSectionFromPreviousManualPoint(true);
            
            Connection conn = (Connection) this.connectionEditPart.getFigure();
            
            List<MPoint> newConstraint = router.computeMPointRoute(conn, bendpoints);
            //List<MPoint> newConstraint = router.computePartialRoute(conn, bendpoints, this.index, 1);
            
            // remove first and last points that are anchors
            AutoOrthogonalRouter.routeToConstraint(newConstraint);
            
            path.setPathData(newConstraint);
            this.gmLink.setLayoutData(path);
            
        }

        @objid ("9cea90f9-8e83-46ca-8497-0fd07596f4ed")
        @Override
        public boolean canExecute() {
            return MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement());
        }

    }

}
