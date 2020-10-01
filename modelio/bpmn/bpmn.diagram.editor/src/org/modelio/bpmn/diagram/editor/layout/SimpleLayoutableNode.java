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

package org.modelio.bpmn.diagram.editor.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode.Role;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.bpmn.diagram.editor.layout.ILayoutableLink.AnchorDirection;
import org.modelio.diagram.api.dg.common.PortContainerDG;

@objid ("3e0e98a2-9f6d-425a-9378-efa17e3bbaf7")
public class SimpleLayoutableNode implements ILayoutableNode {
    @objid ("d219d7bf-dc6d-44b8-8a06-52f1c68b79b3")
    private int col = 0;

    @objid ("02e9d0ce-2d86-4a0a-bfc0-6c19715bf902")
    private int row = 0;

    @objid ("65faa13b-e8aa-45cf-8ede-c98dede9950e")
    private int rightDepth = 0;

    @objid ("354c25ce-53c9-42fe-a19a-19d94abfb0cb")
    private IDiagramNode nodeDG;

    @objid ("b7b99066-5963-46e1-ae11-b79687c4f2ca")
    private List<ILayoutableNode> successors;

    @objid ("9e37ed98-a1cd-4dc4-9b8a-edf397f42ea1")
    private List<ILayoutableNode> predecessors;

    @objid ("9c6ab612-8376-4b1c-812b-3e342f1a68b4")
    private Map<org.modelio.bpmn.diagram.editor.layout.ILayoutableLink.AnchorDirection, List<ILayoutableLink>> inLinks = new HashMap<>();

    @objid ("44941f8e-dc65-4c69-90bb-9d238cea0859")
    private Map<org.modelio.bpmn.diagram.editor.layout.ILayoutableLink.AnchorDirection, List<ILayoutableLink>> outLinks = new HashMap<>();

    @objid ("66fd47b9-05e4-4c7a-ac55-29d193474705")
    public SimpleLayoutableNode(IDiagramNode nodeDG) {
        this.nodeDG = nodeDG;
        this.predecessors = new ArrayList<>();
        this.successors = new ArrayList<>();
        this.col = 0;
        this.row = 0;
        
        for (AnchorDirection ad : AnchorDirection.values()) {
            this.inLinks.put(ad, new ArrayList<>(0));
            this.outLinks.put(ad, new ArrayList<>(0));
        }
    }

    @objid ("41527c50-1380-4c0b-8344-9f1c81525a6c")
    @Override
    public String toString() {
        return String.format("%s [col=%d, row=%d] (%d pred, %d succ) ", this.nodeDG.getName(), this.col, this.row, this.predecessors.size(), this.successors.size());
    }

    @objid ("1ddb9254-5ebb-48d8-9a65-a28823f59f6f")
    @Override
    public List<ILayoutableNode> getPredecessors() {
        return this.predecessors;
    }

    @objid ("4d119c92-3b3f-4acf-ae6a-1a2f3ab3c3b8")
    @Override
    public List<ILayoutableNode> getSuccessors() {
        return this.successors;
    }

    @objid ("cb82ff75-4ca5-49fe-b355-41b84197bbb9")
    @Override
    public int getCol() {
        return this.col;
    }

    @objid ("a852b0b9-f424-4d6c-8017-7674faa5a288")
    @Override
    public void setCol(int col) {
        this.col = col;
    }

    @objid ("3d74b4e2-88f8-492c-ae02-792927555afe")
    @Override
    public int getRow() {
        return this.row;
    }

    @objid ("a2717c5e-e264-4857-98dd-8144528b670e")
    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @objid ("50731cdb-7712-4e87-a4da-a6528e9b3583")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return this.nodeDG.getFromLinks();
    }

    @objid ("ba48062f-8f77-43d5-baa4-f1360dca079a")
    @Override
    public Rectangle getBounds() {
        return this.nodeDG.getBounds();
    }

    @objid ("8ad10349-808a-42b6-a536-9fe3e6896f50")
    @Override
    public void setLocation(int x, int y) {
        this.nodeDG.setLocation(x, y);
        
        // Fix label coordinates
        if (this.nodeDG instanceof PortContainerDG) {
            PortContainerDG portContainer = (PortContainerDG) this.nodeDG;
            Rectangle containerBounds = portContainer.getBounds();
            Point containerCenter = containerBounds.getCenter();
        
            for (IDiagramNode label : portContainer.getNodes(Role.LABEL)) {
                Rectangle labelBounds = label.getBounds();
        
                // Make sure the label is not on the node
                if (labelBounds.y != containerBounds.height) {
                    labelBounds.y = containerBounds.height;
                }
        
                // Center label and node
                Point labelCenter = labelBounds.getCenter();
                if (labelCenter.x != containerCenter.x) {
                    labelBounds.x += (containerCenter.x - labelCenter.x);
                }
                label.setLocation(labelBounds.x, labelBounds.y);
            }
        }
    }

    @objid ("19ce9a07-690c-4198-9006-117341079768")
    @Override
    public void setRightDepth(int rightDepth) {
        this.rightDepth = rightDepth;
    }

    @objid ("ecb4bdbc-fa43-485b-88fd-ff0d46c11e35")
    @Override
    public int getRightDepth() {
        return this.rightDepth;
    }

    @objid ("e400a66c-a28d-4de4-82fd-71f42693e6dd")
    @Override
    public String getName() {
        return this.nodeDG.getName();
    }

    @objid ("beab4143-55bb-4598-ab0c-c0be689ff807")
    @Override
    public List<IDiagramLink> getToLinks() {
        return this.nodeDG.getToLinks();
    }

    @objid ("b9f39785-3bb0-4657-81e5-05f759713931")
    @Override
    public List<ILayoutableLink> getInLinks(AnchorDirection anchorDirection) {
        return this.inLinks.get(anchorDirection);
    }

    @objid ("72685e08-7efe-4741-8655-de1d2e3061a8")
    @Override
    public List<ILayoutableLink> getOutLinks(AnchorDirection anchorDirection) {
        return this.outLinks.get(anchorDirection);
    }

}
