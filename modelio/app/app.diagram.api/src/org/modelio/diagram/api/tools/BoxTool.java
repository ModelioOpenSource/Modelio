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
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.tools.CreationTool;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.tools.IBoxTool;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.editor.IDiagramEditor;
import org.modelio.diagram.editor.plugin.DiagramEditorsManager;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * Tool used for "box like" interactions in diagrams for modules.
 */
@objid ("e35d9ce6-a6df-4360-ab71-aff5ca805b4d")
public class BoxTool extends CreationTool {
    @objid ("99af540c-14a1-4cc0-809c-f5b420c961e6")
    private IBoxTool boxCommand;

    @objid ("942d8d3c-c405-4052-ae03-316c0eeb7f99")
    private DiagramHandle diagramHandle = null;

    /**
     * PropertyDefinition name for the actual handler on the module side.
     */
    @objid ("86f5152f-8711-48d9-b7a5-5c03a659fd8c")
    public static final Object PROPERTY_HANDLER = "handler";

    /**
     * C'tor, used by platform to instantiate the tool by reflexion.
     */
    @objid ("6f93d935-c8eb-47f1-8c78-0104ccd2683f")
    public  BoxTool() {
        this.boxCommand = null;
    }

    @objid ("f229ab79-7478-4a78-bc14-063c9ac8bb44")
    @Override
    protected org.eclipse.gef.EditPartViewer.Conditional getTargetingConditional() {
        return new EditPartViewer.Conditional() {
                    @SuppressWarnings("synthetic-access")
                    @Override
                    public boolean evaluate(EditPart editpart) {
                        if (BoxTool.super.getTargetingConditional().evaluate(editpart)) {
                            return doAccept(editpart);
                        }
                        return false;
                    }
                };
        
    }

    @objid ("d7a6b122-d81d-41ae-ac0e-6493aff4bad9")
    @Override
    protected void executeCurrentCommand() {
        if (getTargetEditPart() == null) {
            return;
        }
        
        GmModel targetModel = (GmModel) getTargetEditPart().getModel();
        initDiagramHandle(targetModel);
        
        IDiagramGraphic dg = null;
        while (dg == null && targetModel != null) {
            dg = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, targetModel);
            targetModel = targetModel.getParent();
        }
        
        Point where = getCreateRequest().getLocation();
        Dimension size = (getCreateRequest().getSize() != null) ? getCreateRequest().getSize()
                : new Dimension(-1, -1);
        Rectangle rect = new Rectangle(where, size);
        
        //The translation is done in the policies
        //((GraphicalEditPart) getTargetEditPart().getViewer().getContents()).getFigure().translateToRelative(rect);
        
        // Delegate the execution to the BoxCommand handler
        this.boxCommand.actionPerformed(this.diagramHandle, dg, rect);
        
        setCurrentCommand(null);
        
    }

    @objid ("986d25a0-fee5-4f66-a2ed-e65b7090dd16")
    @Override
    protected void applyProperty(final Object key, final Object value) {
        if (BoxTool.PROPERTY_HANDLER.equals(key)) {
            if (value instanceof IBoxTool) {
                this.boxCommand = (IBoxTool) value;
            }
            return;
        }
        super.applyProperty(key, value);
        
    }

    /**
     * Updates the target editpart and returns <code>true</code> if the target changes. The target is updated by using
     * the target conditional and the target request. If the target has been locked, this method does nothing and
     * returns <code>false</code>.
     * @return <code>true</code> if the target was changed
     */
    @objid ("7cde64c4-a7aa-4172-b97c-1bb48a228437")
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

    @objid ("7eb9a6ac-bbde-4660-9fd5-1651c8bde123")
    protected boolean doAccept(final EditPart editpart) {
        GmModel targetModel = (GmModel) editpart.getModel();
        initDiagramHandle(targetModel);
        
        IDiagramGraphic dg = null;
        while (dg == null && targetModel != null) {
            dg = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, targetModel);
            targetModel = targetModel.getParent();
        }
        
        if (dg == null) {
            return false;
        }
        return BoxTool.this.boxCommand.acceptElement(this.diagramHandle, dg);
    }

    @objid ("66681780-1fa0-4ab8-a4ff-407847cf6355")
    private void initDiagramHandle(final GmModel targetModel) {
        if (this.diagramHandle == null) {
            // Create a diagram handle on the opened editor (there must be one: we are in one of its tools!).
            AbstractDiagram diagram = targetModel.getDiagram().getRelatedElement();
            IDiagramEditor editor = (IDiagramEditor) DiagramEditorsManager.getInstance().get(diagram).getObject();
        
            this.diagramHandle = DiagramHandle.create(editor, true);
        }
        
    }

    @objid ("2bc687cd-06bc-41a4-bdf3-dce6360ba6e8")
    @Override
    public void deactivate() {
        super.deactivate();
        if (this.diagramHandle != null) {
            this.diagramHandle.close();
            this.diagramHandle = null;
        }
        
    }

    @objid ("d3a515ba-1ca0-4e82-b2c9-19f720af7eab")
    @Override
    protected void enforceConstraintsForSizeOnDropCreate(CreateRequest request) {
        if (getTargetEditPart() != null) {
            super.enforceConstraintsForSizeOnDropCreate(request);
        }
        
    }

}
