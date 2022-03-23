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
package org.modelio.diagram.api.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.tools.AbstractConnectionCreationTool;
import org.eclipse.gef.tools.AbstractTool;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.tools.IAttachedBoxTool;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.LinkRoute;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.editor.IDiagramEditor;
import org.modelio.diagram.editor.plugin.DiagramEditorsManager;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeCreationTool;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * Tool used for "attached box like" interactions in diagrams for modules.
 */
@objid ("cab0e968-95b7-4411-b711-701ebbc2059e")
public class AttachedBoxTool extends LinkedNodeCreationTool {
    @objid ("4420e223-2cb1-4614-b5de-74b31e998222")
    protected GmModel sourceGm;

    @objid ("18d8de3b-54c8-497c-8d07-f12c10d699fc")
    protected IAttachedBoxTool attachedBoxCommand;

    @objid ("048943c9-1e9a-4284-b8c2-2f90ec3e611e")
    private DiagramHandle diagramHandle = null;

    /**
     * PropertyDefinition name for the actual handler on the module side.
     */
    @objid ("0848519b-2289-46e9-a5f7-0ef7a47f5090")
    public static final Object PROPERTY_HANDLER = "handler";

    /**
     * C'tor, used by platform to instantiate the tool by reflexion.
     */
    @objid ("1bfb95b1-7eac-4c2f-8119-ff95c9b45d2f")
    public  AttachedBoxTool() {
        this.attachedBoxCommand = null;
    }

    @objid ("dc02904d-3b8b-46f2-acb0-5b3edcf7b906")
    @Override
    protected org.eclipse.gef.EditPartViewer.Conditional getTargetingConditional() {
        return new EditPartViewer.Conditional() {
                    @SuppressWarnings("synthetic-access")
                    @Override
                    public boolean evaluate(EditPart editpart) {
                        if (AttachedBoxTool.super.getTargetingConditional().evaluate(editpart)) {
                            return doAccept(editpart);
                        }
                        return false;
                    }
                };
        
    }

    @objid ("15d20bb6-15a7-4c30-90c2-345627595564")
    @Override
    protected void executeCurrentCommand() {
        if (getTargetEditPart() == null) {
            return;
        }
        
        GmModel targetModel = (GmModel) getTargetEditPart().getModel();
        initDiagramHandle(targetModel);
        
        // Additional step: add the optional bend points.
        LinkRoute path = LinkRoute.createEmpty();
        
        Point where = ((CreateConnectionRequest) getTargetRequest()).getLocation().getCopy();
        ((GraphicalEditPart) getTargetEditPart().getViewer().getContents()).getFigure()
                .translateToRelative(where);
        
        // Delegate the execution to the linkCommand handler
        IDiagramGraphic sourceDg = null;
        GmModel sourceModel = this.sourceGm;
        while (sourceDg == null && sourceModel != null) {
            sourceDg = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, sourceModel);
            sourceModel = sourceModel.getParent();
        }
        this.attachedBoxCommand.actionPerformed(this.diagramHandle,
                sourceDg,
                LinkRouterKind.DIRECT,
                path,
                where);
        
        // FIXME we must handle notes created in a diagram, without a link
        setCurrentCommand(null);
        
    }

    @objid ("386a4faa-b071-4368-b6e0-3651244ba487")
    @Override
    protected void applyProperty(final Object key, final Object value) {
        if (AttachedBoxTool.PROPERTY_HANDLER.equals(key)) {
            if (value instanceof IAttachedBoxTool) {
                this.attachedBoxCommand = (IAttachedBoxTool) value;
            }
            return;
        }
        super.applyProperty(key, value);
        
    }

    /**
     * Sets the tools state.
     * @param state the new state
     */
    @objid ("b1ffed17-fb68-4da7-a12c-12df0a387d31")
    @Override
    protected void setState(final int state) {
        super.setState(state);
        
        if (state == AbstractConnectionCreationTool.STATE_CONNECTION_STARTED) {
            this.sourceGm = (GmModel) getTargetEditPart().getModel();
        }
        
    }

    /**
     * Updates the target editpart and returns <code>true</code> if the target changes. The target is updated by using
     * the target conditional and the target request. If the target has been locked, this method does nothing and
     * returns <code>false</code>.
     * @return <code>true</code> if the target was changed
     */
    @objid ("26522056-d773-4c52-b5ff-e2d68c9e5cf1")
    @Override
    protected boolean updateTargetUnderMouse() {
        if (!isTargetLocked()) {
            EditPart editPart = getCurrentViewer().findObjectAtExcluding(getLocation(),
                    getExclusionSet(),
                    getTargetingConditional());
            if (editPart != null) {
                // Check accept!!!
                if (doAccept(editPart)) {
                    editPart = editPart.getTargetEditPart(getTargetRequest());
                } else {
                    editPart = null;
                }
            }
            boolean changed = getTargetEditPart() != editPart;
            setTargetEditPart(editPart);
            return changed;
        } else {
            return false;
        }
        
    }

    @objid ("a03ac566-dce5-46e1-8827-7fb116d7ff83")
    protected boolean doAccept(final EditPart editpart) {
        GmModel targetModel = (GmModel) editpart.getModel();
        initDiagramHandle(targetModel);
        
        // Delegate the execution to the handler
        IDiagramGraphic dg = null;
        while (dg == null && targetModel != null) {
            dg = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, targetModel);
            targetModel = targetModel.getParent();
        }
        
        if (dg == null) {
            return false;
        }
        
        if (!isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED | AbstractTool.STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
            return this.attachedBoxCommand.acceptElement(this.diagramHandle, dg);
        } else {
            return true;
        }
        
    }

    @objid ("e5a612c0-302e-44ef-a320-1cfd80d65db7")
    private void initDiagramHandle(final GmModel targetModel) {
        if (this.diagramHandle == null) {
            // Create a diagram handle on the opened editor (there must be one: we are in one of its tools!).
            AbstractDiagram diagram = targetModel.getDiagram().getRelatedElement();
            IDiagramEditor editor = (IDiagramEditor) DiagramEditorsManager.getInstance().get(diagram).getObject();
            this.diagramHandle = DiagramHandle.create(editor, true);
        }
        
    }

    @objid ("96384240-3587-4a03-b81b-e7130c06d3c2")
    @Override
    public void deactivate() {
        super.deactivate();
        if (this.diagramHandle != null) {
            this.diagramHandle.close();
            this.diagramHandle = null;
        }
        
    }

}
