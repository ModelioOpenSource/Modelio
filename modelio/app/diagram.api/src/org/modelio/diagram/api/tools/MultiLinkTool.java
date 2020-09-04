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

package org.modelio.diagram.api.tools;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.tools.IMultiLinkTool;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.LinkPath;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.editor.IDiagramEditor;
import org.modelio.diagram.editor.plugin.DiagramEditorsManager;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.core.tools.multipoint.MultiPointCreationTool;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * Tool used for complex (multi-click) interactions in diagrams for modules.
 */
@objid ("fcd8a20d-ff05-4064-a0ea-dbbe9f724c83")
public class MultiLinkTool extends MultiPointCreationTool {
    @objid ("3969f8e1-217b-48c0-a29c-16fb518604e9")
    protected IMultiLinkTool multiLinkCommand;

    @objid ("da7536b7-0345-4c43-b06b-742a15d4bd9e")
    private DiagramHandle diagramHandle = null;

    /**
     * PropertyDefinition name for the actual handler on the module side.
     */
    @objid ("07996a73-7108-40f1-a97b-b585d0d3edd8")
    public static final Object PROPERTY_HANDLER = "handler";

    /**
     * C'tor, used by platform to instantiate the tool by reflexion.
     */
    @objid ("2be6d810-f043-41ac-bf17-5fa1c15a2322")
    public MultiLinkTool() {
        this.multiLinkCommand = null;
    }

    @objid ("ac9997d1-7b2c-4fe5-8ff4-8c9197ac7f00")
    @Override
    protected void executeCurrentCommand() {
        if (getTargetEditPart() == null) {
            return;
        }
        
        GmModel targetModel = (GmModel) getTargetEditPart().getModel();
        initDiagramHandle(targetModel);
        
        // Get the last node
        IDiagramGraphic lastNode = null;
        while (lastNode == null && targetModel != null) {
            lastNode = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, targetModel);
            targetModel = targetModel.getParent();
        }
        
        // Gather all other nodes
        List<IDiagramGraphic> otherNodes = getOtherNodes();
        otherNodes.remove(lastNode);
        
        // Gather the rectangle
        Point where = getTargetRequest().getLocation();
        Dimension size = (getTargetRequest().getSize() != null) ? getTargetRequest().getSize()
                : new Dimension(-1, -1);
        Rectangle rect = new Rectangle(where, size);
        ((GraphicalEditPart) getTargetEditPart().getViewer().getContents()).getFigure()
                .translateToRelative(rect);
        
        // TODO Additional step: add the optional bend points and routers.
        List<ILinkPath> paths = new ArrayList<>();
        List<LinkRouterKind> routerList = new ArrayList<>();
        for (int i = 0; i < otherNodes.size(); i++) {
            paths.add(new LinkPath());
            routerList.add(LinkRouterKind.BENDPOINT);
        }
        
        // Delegate the execution to the linkCommand handler
        this.multiLinkCommand.actionPerformed(this.diagramHandle,
                lastNode,
                otherNodes,
                routerList,
                paths,
                rect);
        
        setCurrentCommand(null);
    }

    @objid ("8bd99cec-26c6-4a8c-96a3-800f46e9009e")
    private List<IDiagramGraphic> getOtherNodes() {
        List<IDiagramGraphic> otherNodes = new ArrayList<>();
        for (EditPart ep : getTargetRequest().getAcceptedEditParts()) {
            GmModel targetModel = (GmModel) ep.getModel();
        
            IDiagramGraphic dg = null;
            while (dg == null && targetModel != null) {
                dg = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, targetModel);
                targetModel = targetModel.getParent();
            }
        
            if (dg != null) {
                otherNodes.add(dg);
            }
        }
        return otherNodes;
    }

    @objid ("373cdc15-810d-4a80-ad2c-2366cadd7207")
    @Override
    protected void applyProperty(final Object key, final Object value) {
        if (MultiLinkTool.PROPERTY_HANDLER.equals(key)) {
            if (value instanceof IMultiLinkTool) {
                this.multiLinkCommand = (IMultiLinkTool) value;
            }
            return;
        }
        super.applyProperty(key, value);
    }

    @objid ("5656a09f-dd7d-4268-85e9-43227800cfa8")
    @Override
    protected org.eclipse.gef.EditPartViewer.Conditional getTargetingConditional() {
        return new EditPartViewer.Conditional() {
                    @SuppressWarnings("synthetic-access")
                    @Override
                    public boolean evaluate(EditPart editpart) {
                        if (MultiLinkTool.super.getTargetingConditional().evaluate(editpart)) {
                            return doAccept(editpart);
                        }
                        return false;
                    }
                };
    }

    /**
     * Check if the multiLinkCommand accepts this edit part.<br>
     * A multi link creation has this behavior:<br>
     * With a REQ_MULTIPOINT_FIRST request: if (acceptFirst) return true, else return acceptLast.<br>
     * With a REQ_MULTIPOINT_ADDITIONAL request: if (acceptAdditional) return true, else return acceptLast.<br>
     * When acceptLast returns true, switches the current request to REQ_MULTIPOINT_LAST.
     * @param editpart The edit part to check.
     * @return true if the request is accepted.
     */
    @objid ("bbba50d6-2b87-4c9d-b67b-564f20cb1399")
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
        
        if (getTargetRequest().getType().equals(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST)) {
            // Accept First
            if (MultiLinkTool.this.multiLinkCommand.acceptFirstElement(this.diagramHandle, dg)) {
                return true;
            } else if (MultiLinkTool.this.multiLinkCommand.acceptLastElement(this.diagramHandle,
                    getOtherNodes(),
                    dg)) { // Accept Last
                // Swtich request type -> last point
                getTargetRequest().setType(CreateMultiPointRequest.REQ_MULTIPOINT_LAST);
                return true;
            }
        } else if (getTargetRequest().getType().equals(CreateMultiPointRequest.REQ_MULTIPOINT_ADDITIONAL)) {
            // Accept Additional
            if (MultiLinkTool.this.multiLinkCommand.acceptAdditionalElement(this.diagramHandle,
                    getOtherNodes(),
                    dg)) {
                return true;
            } else if (MultiLinkTool.this.multiLinkCommand.acceptLastElement(this.diagramHandle,
                    getOtherNodes(),
                    dg)) { // Accept Last
                // Swtich request type -> last point
                getTargetRequest().setType(CreateMultiPointRequest.REQ_MULTIPOINT_LAST);
                return true;
            }
        }
        return false;
    }

    @objid ("c3c9838a-0de6-4ed5-a94e-24baca1ae094")
    private void initDiagramHandle(final GmModel targetModel) {
        if (this.diagramHandle == null) {
            // Create a diagram handle on the opened editor (there must be one: we are in one of its tools!).
            AbstractDiagram diagram = targetModel.getDiagram().getRelatedElement();
            IDiagramEditor editor = (IDiagramEditor) DiagramEditorsManager.getInstance().get(diagram).getObject();
        
            this.diagramHandle = DiagramHandle.create(editor, true);
        }
    }

    @objid ("7ae62760-d4c7-4325-b3e4-28a0c6186fe8")
    @Override
    public void deactivate() {
        super.deactivate();
        if (this.diagramHandle != null) {
            this.diagramHandle.close();
            this.diagramHandle = null;
        }
    }

}
