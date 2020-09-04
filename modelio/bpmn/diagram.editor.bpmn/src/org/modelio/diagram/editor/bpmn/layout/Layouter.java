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

package org.modelio.diagram.editor.bpmn.layout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.editor.bpmn.layout.ILayoutableLink.Shape;

@objid ("af3b08d7-ffae-4d16-b822-2593e26cde74")
public class Layouter {
    @objid ("d1f0b7d1-8a4c-4c81-bb16-b0311a3e7e26")
    private IDiagramHandle dh;

    @objid ("f94dbb76-c7d6-44d0-a7eb-c5b422606d67")
    private LayoutModel layout;

    @objid ("a6bf637c-5089-495b-87f5-f4024efb479a")
    public Layouter(IDiagramHandle dh) {
        this.dh = dh;
        
        this.layout = new LayoutModel();
        
        loadDiagram();
    }

    @objid ("d831eed2-51b7-4e03-a3d3-d680a043aed2")
    public LayoutModel layout() {
        // System.out.printf("Graph.layout() (%d nodes)\n", this.layout.getNodeNumber());
        this.dh.setBatchMode(true);
        
        // Layouting nodes
        layoutNodes();
        
        // Layouting links
        layoutLinks();
        return this.layout;
    }

    @objid ("817317cf-45b3-4108-af65-eba190b10fd7")
    private void doLayout(ILayoutableNode diagramNode, int col, int row, Set<ILayoutableNode> processed, String indent) {
        // System.out.printf("%s doLayout(%s -> %d,%d)\n", indent, diagramNode.getName(), row, col);
        
        if (processed.contains(diagramNode)) {
            return;
        }
        diagramNode.setCol(col);
        diagramNode.setRow(row);
        
        this.layout.setNode(row, col, diagramNode);
        
        processed.add(diagramNode);
        
        // Find (not yet processed) successors and sort them on right depth (higher depth first)
        List<ILayoutableNode> nextNodes = new ArrayList<>();
        for (ILayoutableNode succ : diagramNode.getSuccessors()) {
            if (!processed.contains(succ)) {
                nextNodes.add(succ);
            }
        }
        
        nextNodes.sort(new Comparator<ILayoutableNode>() {
            @Override
            public int compare(ILayoutableNode e1, ILayoutableNode e2) {
                return -Integer.compare(e1.getRightDepth(), e2.getRightDepth());
            }
        });
        
        int r = row;
        for (ILayoutableNode nn : nextNodes) {
            doLayout(nn, col + 1, r, processed, indent + "  ");
            r = r + 1;
        }
    }

    @objid ("bb3b6702-285c-451b-ab5c-b8d5a3b26cae")
    private int computeRightDepth(ILayoutableNode lNode, Set<ILayoutableNode> processed) {
        int maxDepth = 0;
        if (!processed.contains(lNode)) {
            maxDepth = 0;
            processed.add(lNode);
            for (IDiagramLink l : lNode.getFromLinks()) {
                int d = computeRightDepth(this.layout.get((IDiagramNode) l.getTo()), processed);
                if (d > maxDepth) {
                    maxDepth = d;
                }
            }
        }
        return 1 + maxDepth;
    }

    @objid ("8017cf65-b6f3-4eda-b69c-9218520f30be")
    private List<ILayoutableNode> getRootNodes() {
        List<ILayoutableNode> startnodes = new ArrayList<>();
        
        // List<ILayoutableNode> nodes = new ArrayList<>(this.nodesLayout.values());
        List<ILayoutableNode> nodes = this.layout.getNodes();
        
        // Sort nodes by predecessors (lower values first) and than by right depth (higher values first)
        nodes.sort(new Comparator<ILayoutableNode>() {
            @Override
            public int compare(ILayoutableNode e1, ILayoutableNode e2) {
                int r = Integer.compare(e1.getPredecessors().size(), e2.getPredecessors().size());
                return (r != 0) ? r : -Integer.compare(e1.getRightDepth(), e2.getRightDepth());
            }
        });
        
        // nodes.sort(new Comparator<ILayoutableNode>() {
        // @Override
        // public int compare(ILayoutableNode n1, ILayoutableNode n2) {
        // return Integer.compare(n1.getPredecessors().size(), n2.getPredecessors().size());
        // }
        // });
        
        if (!nodes.isEmpty()) {
            int card = nodes.get(0).getPredecessors().size();
            for (ILayoutableNode n : nodes) {
                if (n.getPredecessors().size() == card) {
                    startnodes.add(n);
                }
            }
        }
        return startnodes;
    }

    @objid ("3172e457-5eb2-49b6-8f5e-569c350d992f")
    private void loadDiagram() {
        // Load nodes
        for (IDiagramNode n : this.dh.getDiagramNode().getNodes()) {
            this.layout.add(n, new SimpleLayoutableNode(n));
        }
        
        // Load links
        for (IDiagramNode n : this.dh.getDiagramNode().getNodes()) {
            ILayoutableNode lNode = this.layout.get(n);
            for (IDiagramLink link : n.getToLinks()) {
                // FIXME if 'to' is not a node
                lNode.getPredecessors().add(this.layout.get((IDiagramNode) link.getFrom()));
            }
            for (IDiagramLink link : n.getFromLinks()) {
                // FIXME if 'from' is not a node
                ILayoutableNode toLNode = this.layout.get((IDiagramNode) link.getTo());
                lNode.getSuccessors().add(toLNode);
                this.layout.add(link, new LayoutableLink(link, lNode, toLNode));
            }
        }
        
        // Compute right depth
        for (ILayoutableNode lNode : this.layout.getNodes()) {
            lNode.setRightDepth(computeRightDepth(lNode, new HashSet<ILayoutableNode>()));
            // System.out.printf("loadDiagram(): max right depth of '%s' = %d\n", lNode.getName(), lNode.getRightDepth());
        }
    }

// Logical layout of the links, consists in determining them in the (row, col) logical table
    @objid ("d5118d20-f84b-47e2-8d26-c56f29e1ed03")
    private void layoutLinks() {
        // for (ILayoutableLink link : this.linksLayout.values()) {
        for (ILayoutableLink link : this.layout.getLinks()) {
            routeLink(link);
        }
    }

// end routeHorizontalLink
// Logical layout of the nodes, consists in placing them in the (row, col) logical table
    @objid ("36f61962-3252-420a-aa37-1f7f79cfba51")
    private void layoutNodes() {
        // this.nodeTable = new NodeTable();
        
        Set<ILayoutableNode> processed = new HashSet<>();
        int row = 0;
        int col = 0;
        
        List<ILayoutableNode> roots = this.getRootNodes();
        // Sort roots on right depth (higher first)
        roots.sort(new Comparator<ILayoutableNode>() {
            @Override
            public int compare(ILayoutableNode e1, ILayoutableNode e2) {
                return -Integer.compare(e1.getRightDepth(), e2.getRightDepth());
            }
        });
        
        for (ILayoutableNode nld : this.getRootNodes()) {
            this.doLayout(nld, col, row, processed, "  ");
            row++;
        }
    }

    @objid ("34d94f98-86ae-40d8-aec0-5f0851c21840")
    private void routeLink(ILayoutableLink link) {
        if (link.isSelected()) {
            // System.out.println("Graph.routeLink() selected: " + link);
        }
        
        ILayoutableNode source = link.getFrom();
        int sourceCol = source.getCol();
        int sourceRow = source.getRow();
        
        ILayoutableNode target = link.getTo();
        int targetCol = target.getCol();
        int targetRow = target.getRow();
        
        // System.out.println("Graph.routeLink() " + source + " => " + target);
        
        if (sourceRow == targetRow) {
            // source and target on the same row
            routeHorizontalLink(link, sourceRow, sourceCol, targetCol);
        } else if (sourceCol == targetCol) {
            routeVerticalLink(link, sourceRow, targetRow, sourceCol);
        } else {
            routeQuadrantLink(link, sourceRow, sourceCol, targetRow, targetCol);
        }
        
        // Register layoutable links by anchor direction
        source.getOutLinks(link.getSourceAnchor()).add(link);
        target.getInLinks(link.getTargetAnchor()).add(link);
        
        // System.out.println(" link = " + link.toString());
    }

    /**
     * Source and target are on both different row and column.
     * @param link
     */
    @objid ("25840d19-ac88-4c8b-9b4e-2b698910dfd8")
    private void routeQuadrantLink(ILayoutableLink link, int sourceRow, int sourceCol, int targetRow, int targetCol) {
        // source and target are neither on the same nor on the same column => a Corner or a VerticalZ
        // Corner links are only possible if row cells (column cells) are available
        // If Corner is not possible => VerticalZ
        // 1 | 2
        // --S--
        // 3 | 4
        
        if (sourceCol < targetCol) {
            // source is on the left of target
            if (sourceRow < targetRow) {
                // Target is in quadrant 4
                // First try a corner route starting East, then a corner route South else a verticalZ
                if (routeCorner(link, ILayoutableLink.AnchorDirection.East, sourceRow, sourceCol, targetRow, targetCol, ILayoutableLink.AnchorDirection.North)) {
                    return;
                } else if (routeCorner(link, ILayoutableLink.AnchorDirection.South, sourceRow, sourceCol, targetRow, targetCol, ILayoutableLink.AnchorDirection.West)) {
                    return;
                } else {
                    routeVerticalZ(link, sourceRow, sourceCol, targetRow, targetCol);
                    return;
                }
            } else {
                // Target is in quadrant 2
                // First try a corner route starting East, then a corner route North else a verticalZ
                if (routeCorner(link, ILayoutableLink.AnchorDirection.East, sourceRow, sourceCol, targetRow, targetCol, ILayoutableLink.AnchorDirection.South)) {
                    return;
                } else if (routeCorner(link, ILayoutableLink.AnchorDirection.North, sourceRow, sourceCol, targetRow, targetCol, ILayoutableLink.AnchorDirection.West)) {
                    return;
                } else {
                    routeVerticalZ(link, sourceRow, sourceCol, targetRow, targetCol);
                    return;
                }
            }
        } else {
            // source is on the right of target
            if (sourceRow < targetRow) {
                // Target is in quadrant 3
                // First try a corner route starting West, then a corner route South else a verticalZ
                if (routeCorner(link, ILayoutableLink.AnchorDirection.West, sourceRow, sourceCol, targetRow, targetCol, ILayoutableLink.AnchorDirection.North)) {
                    return;
                } else if (routeCorner(link, ILayoutableLink.AnchorDirection.South, sourceRow, sourceCol, targetRow, targetCol, ILayoutableLink.AnchorDirection.West)) {
                    return;
                } else {
                    routeVerticalZ(link, sourceRow, sourceCol, targetRow, targetCol);
                    return;
                }
            } else {
                // Target is in quadrant 1
                // First try a corner route starting West, then a corner route North else a verticalZ
                if (routeCorner(link, ILayoutableLink.AnchorDirection.West, sourceRow, sourceCol, targetRow, targetCol, ILayoutableLink.AnchorDirection.South)) {
                    return;
                } else if (routeCorner(link, ILayoutableLink.AnchorDirection.North, sourceRow, sourceCol, targetRow, targetCol, ILayoutableLink.AnchorDirection.East)) {
                    return;
                } else {
                    routeVerticalZ(link, sourceRow, sourceCol, targetRow, targetCol);
                    return;
                }
            }
        }
    }

// end routeQuadrantLink()
    /**
     * @param link
     * @param sourceAnchor
     * @param sourceRow
     * @param sourceCol
     * @param targetRow
     * @param targetCol
     * @param targetAnchor
     * 
     * @return true if a corner route could be defined for the link. The link has been configured (kind and anchors) with proper values.
     */
    @objid ("cf32cf32-f6f2-4daa-93de-c39584b8238e")
    private boolean routeCorner(ILayoutableLink link, org.modelio.diagram.editor.bpmn.layout.ILayoutableLink.AnchorDirection sourceAnchor, int sourceRow, int sourceCol, int targetRow, int targetCol, org.modelio.diagram.editor.bpmn.layout.ILayoutableLink.AnchorDirection targetAnchor) {
        switch (sourceAnchor) {
        case East:
            // Cells on the left of the source between ]sourceCol, targetCol] must be empty on the sourceRow
            for (int j = sourceCol + 1; j <= targetCol; j++) {
                if (!this.layout.isFreeCell(sourceRow, j)) {
                    return false;
                }
            }
            // Cells below or above the target must be free for the target column
            if (targetAnchor == ILayoutableLink.AnchorDirection.North) {
                // Landing on target North, cells above target between [sourceRow, targetRow[ must be free
                for (int i = sourceRow; i <= targetRow - 1; i++) {
                    if (!this.layout.isFreeCell(i, targetCol)) {
                        return false;
                    }
                }
            } else {
                // Landing on target South, cells below target between [sourceRow, targetRow[ must be free
                for (int i = sourceRow; i > targetRow; i--) {
                    if (!this.layout.isFreeCell(i, targetCol)) {
                        return false;
                    }
                }
            }
            // Corner route is possible
            link.setShape(Shape.Corner);
            link.setSourceAnchor(sourceAnchor);
            link.setTargetAnchor(targetAnchor);
            return true;
        
        case West:
            // Cells on the right between ]sourceCol,targetCol] must be empty on the sourceRow
            for (int j = sourceCol - 1; j >= targetCol; j--) {
                if (!this.layout.isFreeCell(sourceRow, j)) {
                    return false;
                }
            }
        
            // Cells below or above the target must be free for the target column
            if (targetAnchor == ILayoutableLink.AnchorDirection.North) {
                // Landing on target North, cells above target between [sourceRow, targetRow[ must be free
                for (int i = sourceRow; i <= targetRow - 1; i++) {
                    if (!this.layout.isFreeCell(i, targetCol)) {
                        return false;
                    }
                }
            } else {
                // Landing on target South, cells below target between [sourceRow, targetRow[ must be free
                for (int i = sourceRow; i <= targetRow + 1; i--) {
                    if (!this.layout.isFreeCell(i, targetCol)) {
                        return false;
                    }
                }
            }
            // Corner route is possible
            link.setShape(Shape.Corner);
            link.setSourceAnchor(sourceAnchor);
            link.setTargetAnchor(targetAnchor);
            return true;
        
        case North:
            // Cells above between ]sourceRow, targetRow] must be empty on the sourceCol
            for (int i = sourceRow - 1; i >= targetRow; i--) {
                if (!this.layout.isFreeCell(i, sourceCol)) {
                    return false;
                }
            }
        
            // Cells on the left or on the right of the target must be free for the target row
            if (targetAnchor == ILayoutableLink.AnchorDirection.East) {
                // Landing on target East, cells on the left of the target between [sourceCol, targetCol[ must be free
                for (int j = sourceCol; j <= targetCol - 1; j++) {
                    if (!this.layout.isFreeCell(targetRow, j)) {
                        return false;
                    }
                }
            } else {
                // Landing on target West, cells on the right of the target between [sourceCol, targetCol[ must be free
                for (int j = sourceCol; j <= targetCol - 1; j--) {
                    if (!this.layout.isFreeCell(targetRow, j)) {
                        return false;
                    }
                }
            }
            // Corner route is possible
            link.setShape(Shape.Corner);
            link.setSourceAnchor(sourceAnchor);
            link.setTargetAnchor(targetAnchor);
            return true;
        
        case South:
            // Cells below between sourceRow+1 and targetRow must be empty on the sourceCol
            for (int i = sourceRow + 1; i <= targetRow; i++) {
                if (!this.layout.isFreeCell(i, sourceCol)) {
                    return false;
                }
            }
            // Cells on the left or on the right of the target must be free for the target row
            if (targetAnchor == ILayoutableLink.AnchorDirection.East) {
                // Landing on target East, cells on the left of the target between [sourceCol, targetCol[ must be free
                for (int j = sourceCol; j <= targetCol - 1; j++) {
                    if (!this.layout.isFreeCell(targetRow, j)) {
                        return false;
                    }
                }
            } else {
                // Landing on target West, cells on the right of the target between [sourceCol, targetCol[ must be free
                for (int j = sourceCol; j <= targetCol - 1; j++) {
                    if (!this.layout.isFreeCell(targetRow, j)) {
                        return false;
                    }
                }
            }
            // Corner route is possible
            link.setShape(Shape.Corner);
            link.setSourceAnchor(sourceAnchor);
            link.setTargetAnchor(targetAnchor);
            return true;
        
        default:
            return false;
        }
    }

    @objid ("13a1eb5a-03a3-484f-a835-87a28ebe926b")
    private boolean routeVerticalZ(ILayoutableLink link, int sourceRow, int sourceCol, int targetRow, int targetCol) {
        link.setShape(Shape.VerticalZ);
        link.setSourceAnchor(sourceRow < targetRow ? ILayoutableLink.AnchorDirection.South : ILayoutableLink.AnchorDirection.North);
        link.setTargetAnchor(sourceCol < targetCol ? ILayoutableLink.AnchorDirection.West : ILayoutableLink.AnchorDirection.East);
        return true;
    }

    /**
     * Source and target on the same column.
     * 
     * <ol>
     * <li>=> VerticalDirect if source and target are neighbours on the row</li>
     * <li>=> VerticalBridge if source and target are separated by at least one non empty row cell</li>
     * </ol>
     */
    @objid ("bf6435e4-6341-412a-a3d3-80b3dddec727")
    private void routeVerticalLink(ILayoutableLink link, int sourceRow, int targetRow, int col) {
        if (Math.abs(sourceRow - targetRow) > 1) {
            // TODO can reduce the bridge to a direct link if empty columns for the row
            link.setShape(Shape.VerticalBridge);
            link.setSourceAnchor(ILayoutableLink.AnchorDirection.West);
            link.setTargetAnchor(ILayoutableLink.AnchorDirection.West);
        } else {
            link.setShape(Shape.VerticalDirect);
            boolean downward = (sourceRow < targetRow);
            link.setSourceAnchor(downward ? ILayoutableLink.AnchorDirection.South : ILayoutableLink.AnchorDirection.North);
            link.setTargetAnchor(downward ? ILayoutableLink.AnchorDirection.North : ILayoutableLink.AnchorDirection.South);
        }
    }

// end routeVerticalLink
    /**
     * Source and target on the same row.
     * 
     * <ol>
     * <li>=> HorizontalDirect if source and target are neighbours on the row</li>
     * <li>=> HorizontalBridge if source and target are separated by at least one non empty column cell</li>
     * </ol>
     */
    @objid ("25cf47f4-e77f-41f6-845e-5803eb65728d")
    private void routeHorizontalLink(ILayoutableLink link, int row, int sourceCol, int targetCol) {
        if (Math.abs(sourceCol - targetCol) > 1) {
            // TODO can reduce the bridge to a direct link if empty columns for the row
            link.setShape(Shape.HorizontalBridge);
            link.setSourceAnchor(ILayoutableLink.AnchorDirection.North);
            link.setTargetAnchor(ILayoutableLink.AnchorDirection.North);
        } else {
            link.setShape(Shape.HorizontalDirect);
            boolean forward = (sourceCol < targetCol);
            link.setSourceAnchor(forward ? ILayoutableLink.AnchorDirection.East : ILayoutableLink.AnchorDirection.West);
            link.setTargetAnchor(forward ? ILayoutableLink.AnchorDirection.West : ILayoutableLink.AnchorDirection.East);
        }
    }

}
