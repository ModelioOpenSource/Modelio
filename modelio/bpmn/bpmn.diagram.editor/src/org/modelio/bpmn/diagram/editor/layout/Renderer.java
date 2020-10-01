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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.bpmn.diagram.editor.layout.ILayoutableLink.AnchorDirection;

/**
 * The Renderer is in charge of the graphic rendering of the layouter results, ie setting the (x,y) coordinates and 'w,h) size of the diagram elements based on the Layouter results.
 */
@objid ("6dd105cc-48b4-40ab-8f49-1ebaf002deee")
public class Renderer {
    @objid ("39db649e-46a8-45c2-93ad-53c87ed4dc52")
    private IDiagramHandle dh;

    @objid ("16e2aae8-82a5-429a-bb01-d9f478e50984")
    public Renderer(IDiagramHandle dh) {
        this.dh = dh;
    }

    @objid ("a554787b-bf93-4981-a09e-ba70b8e2ea83")
    public void renderNodes(LayoutModel layout) {
        // Positioning nodes: set the nodes coordinates
        NodeGrid nodeGrid = new NodeGrid(layout, layout.xSpacing, layout.ySpacing);
        nodeGrid.compute();
        
        for (ILayoutableNode layoutData : layout.getNodes()) {
            // for (Entry<IDiagramNode, ILayoutableNode> entry : this.nodesLayout.entrySet()) {
            // IDiagramNode node = entry.getKey();
            // ILayoutableNode layoutData = entry.getValue();
        
            Point pt = nodeGrid.getLocation(layoutData.getRow(), layoutData.getCol());
        
            layoutData.setLocation(pt.x, pt.y);
        
            // System.out.printf("Set node '%s'(%d,%d) location at %s\n",
            // layoutData.toString(),
            // layoutData.getRow(),
            // layoutData.getCol(), nodeGrid.getLocation(layoutData.getRow(), layoutData.getCol()));
        }
    }

    @objid ("f6e5af8a-05fa-4095-bf1e-c7c8f4d2254e")
    public void renderLinks2(LayoutModel layout) {
        // Routing links: define the paths of the links
        // source and target anchoring points have been computed before and are available on ILayoutableNode
        
        int hBridgeOffset = 40;
        int vBridgeOffset = 40;
        
        int[] vzLinksYoffsets = new int[layout.getRowNumber()];
        Arrays.fill(vzLinksYoffsets, 0);
        int[] vzLinksXoffsets = new int[layout.getColNumber()];
        Arrays.fill(vzLinksYoffsets, 0);
        
        // for (Entry<IDiagramLink, ILayoutableLink> linkEntry : this.linksLayout.entrySet()) {
        for (ILayoutableLink linkData : layout.getLinks()) {
        
            IDiagramLink link2 = linkData.getLinkDG();
        
            if (link2.isSelected()) {
                // System.out.println("Renderer.renderLinks2( ) selected:" + linkData);
            }
        
            link2.setRouterKind(IDiagramLink.LinkRouterKind.ORTHOGONAL);
            
        
            switch (linkData.getShape()) {
        
            case HorizontalDirect:
                // Horizontal link
                Point hd1 = linkData.getSourcePoint();
                Point hd2 = linkData.getTargetPoint();
                link2.setPath(Arrays.asList(hd1, hd2));
                break;
        
            case VerticalDirect:
                // Vertical link
                Point vd1 = linkData.getSourcePoint();
                Point vd2 = linkData.getTargetPoint();
                link2.setPath(Arrays.asList(vd1, vd2));
                break;
        
            case HorizontalBridge:
                // Horizontal bridge
                Point hb1 = linkData.getSourcePoint();
                Point hb2 = linkData.getTargetPoint();
                Point hbi = new Point(hb1.x(), hb1.y() - hBridgeOffset);
                Point hbj = new Point(hb2.x(), hbi.y());
                link2.setPath(Arrays.asList(hb1, hbi, hbj, hb2));
                hBridgeOffset += 4;
                break;
        
            case VerticalBridge:
                // Vertical bridge
                Point vb1 = linkData.getSourcePoint();
                Point vb2 = linkData.getTargetPoint();
                Point vbi = new Point(vb1.x() - vBridgeOffset, vb1.y());
                Point vbj = new Point(vbi.x(), vb2.y());
                link2.setPath(Arrays.asList(vb1, vbi, vbj, vb2));
                vBridgeOffset += 20;
                break;
        
            case VerticalZ:
                // Z link
                // A Z link is composed of four segments s1, s2,s3,s4
                // s1 = (vs, v1) vertical segment
                // s2 = (v1, v2) horizontal segment
                // s3 = (v2, v3) vertical segment
                // s4 = (v3, ve) horizontal segment
                // The s2 horizontal segment must be vertically adjusted in case there are several links in the same row interval.
                // The s3 vertical segment must be horizontally adjusted in case there are several links in the same col interval.
        
                Point vs = linkData.getSourcePoint();
                Point ve = linkData.getTargetPoint();
        
                int dy1 = vzLinksYoffsets[linkData.getFrom().getRow()];
                vzLinksYoffsets[linkData.getFrom().getRow()] += 4;
        
                int dx2 = vzLinksXoffsets[linkData.getFrom().getCol()];
                vzLinksXoffsets[linkData.getFrom().getCol()] += 4;
        
                int dxe = (linkData.getTargetAnchor() == AnchorDirection.West) ? -layout.xSpacing * 2 / 3 + dx2 : layout.xSpacing / 2 - dx2;
                int dys = (linkData.getSourceAnchor() == AnchorDirection.North) ? -layout.ySpacing * 2 / 3 - dy1 : layout.ySpacing * 2 / 3 + dy1; // layout.ySpacing / 2 + dy1;
        
                Point v1 = new Point(vs.x(), vs.y() + dys);
                Point v2 = new Point(ve.x() + dxe, v1.y());
                Point v3 = new Point(v2.x(), ve.y());
                link2.setPath(Arrays.asList(vs, v1, v2, v3, ve));
        
                break;
        
            case Corner:
                // Corner link
                Point c1 = linkData.getSourcePoint();
                Point c2 = linkData.getTargetPoint();
        
                Point ci = null;
        
                switch (linkData.getSourceAnchor()) {
                case East:
                case West:
                    ci = new Point(c2.x(), c1.y());
                    break;
                case North:
                case South:
                default:
                    ci = new Point(c1.x(), c2.y());
                    break;
                }
                link2.setPath(Arrays.asList(c1, ci, c2));
                break;
        
            default:
                // System.out.println("Graph.layout() Unknown kind !!! " + linkData.getShape());
            }
        
        }
        // }
        this.dh.setBatchMode(false);
        this.dh.save();
        //
        // print "computed link layout:"
        // for e in this.linksLayout:
        // t = this.linksLayout[e]
        // print " ", e.getName(),'=', t[0].getName() + '[' + t[1] + ']', t[0].getBounds(), "->", t[2].getName() + '[' + t[3] + ']', t[2].getBounds(), t[4]
        // print " layout path =", t[5]
        // print " dg path =", e.getPath()
    }

    @objid ("1fb2cbad-f33d-4d95-bd6a-afbfcd36fc68")
    public void render(LayoutModel layoutModel) {
        renderNodes(layoutModel);
        renderLinks(layoutModel);
    }

    @objid ("2dc19659-9b4b-4628-bfee-dfbaf3a0cd36")
    public void renderLinks(LayoutModel layout) {
        int[] vzLinksYoffsets = new int[layout.getRowNumber()];
        Arrays.fill(vzLinksYoffsets, 0);
        
        int[] vzLinksXoffsets = new int[layout.getColNumber()];
        Arrays.fill(vzLinksXoffsets, 0);
        
        // Loop on all nodes
        for (ILayoutableNode lNode : layout.getNodes()) {
            // System.out.printf("\nRenderer.renderLinks() node %s\n", lNode.getName());
            // System.out.printf(" - north in : %s\n", lNode.getInLinks(AnchorDirection.North));
            // System.out.printf(" - north out: %s\n", lNode.getOutLinks(AnchorDirection.North));
            // System.out.printf(" - south in : %s\n", lNode.getInLinks(AnchorDirection.South));
            // System.out.printf(" - south out: %s\n", lNode.getOutLinks(AnchorDirection.South));
            // System.out.printf(" - east in : %s\n", lNode.getInLinks(AnchorDirection.East));
            // System.out.printf(" - east out: %s\n", lNode.getOutLinks(AnchorDirection.East));
            // System.out.printf(" - west in : %s\n", lNode.getInLinks(AnchorDirection.West));
            // System.out.printf(" - west out: %s\n", lNode.getOutLinks(AnchorDirection.West));
        
            distributeNorthAnchors(lNode);
            distributeSouthAnchors(lNode);
            distributeEastAnchors(lNode);
            distributeWestAnchors(lNode);
        }
        
        renderLinks2(layout);
    }

    @objid ("66971f39-cfb1-4cf2-99f1-4a7382c338e9")
    private void distributeNorthAnchors(ILayoutableNode lNode) {
        List<ILayoutableLink> outLinks = lNode.getOutLinks(AnchorDirection.North);
        
        outLinks.sort(new Comparator<ILayoutableLink>() {
            @Override
            public int compare(ILayoutableLink ll1, ILayoutableLink ll2) {
                int r = Double.compare(ll1.getSlope(), ll2.getSlope());
                if (r == 0) {
                    return Double.compare(ll1.getTo().getCol(), ll2.getTo().getCol());
                } else {
                    return r;
                }
            }
        });
        
        List<ILayoutableLink> inLinks = lNode.getInLinks(AnchorDirection.North);
        
        inLinks.sort(new Comparator<ILayoutableLink>() {
            @Override
            public int compare(ILayoutableLink ll1, ILayoutableLink ll2) {
                int r = Double.compare(ll1.getSlope(), ll2.getSlope());
                if (r == 0) {
                    return Double.compare(ll1.getTo().getCol(), ll2.getTo().getCol());
                } else {
                    return r;
                }
            }
        });
        
        int deltaX = lNode.getBounds().width / (outLinks.size() + inLinks.size() + 1);
        int curX = lNode.getBounds().x + deltaX;
        int curY = lNode.getBounds().y;
        
        for (ILayoutableLink ll : outLinks) {
            ll.setSourcePoint(new Point(curX, curY));
        
            // routeLink(ll, curX, -1, -1, -1);
            curX += deltaX;
        }
        for (ILayoutableLink ll : inLinks) {
            ll.setTargetPoint(new Point(curX, curY));
            // routeLink(ll, curX, -1, -1, -1);
            curX += deltaX;
        }
    }

    @objid ("9f645644-c93c-4e09-8c7e-f92b39b28f0f")
    private void distributeSouthAnchors(ILayoutableNode lNode) {
        List<ILayoutableLink> outLinks = lNode.getOutLinks(AnchorDirection.South);
        
        outLinks.sort(new Comparator<ILayoutableLink>() {
            @Override
            public int compare(ILayoutableLink ll1, ILayoutableLink ll2) {
                int r = -Double.compare(ll1.getSlope(), ll2.getSlope());
                if (r == 0) {
                    return Double.compare(ll1.getTo().getCol(), ll2.getTo().getCol());
                } else {
                    return r;
                }
            }
        });
        
        List<ILayoutableLink> inLinks = lNode.getInLinks(AnchorDirection.South);
        
        inLinks.sort(new Comparator<ILayoutableLink>() {
            @Override
            public int compare(ILayoutableLink ll1, ILayoutableLink ll2) {
                int r = Double.compare(ll1.getSlope(), ll2.getSlope());
                if (r == 0) {
                    return Double.compare(ll1.getTo().getCol(), ll2.getTo().getCol());
                } else {
                    return r;
                }
            }
        });
        
        int deltaX = lNode.getBounds().width / (outLinks.size() + inLinks.size() + 1);
        int curX = lNode.getBounds().x + deltaX;
        int curY = lNode.getBounds().y + lNode.getBounds().height;
        
        for (ILayoutableLink ll : outLinks) {
            ll.setSourcePoint(new Point(curX, curY));
        
            // routeLink(ll, curX, -1, -1, -1);
            curX += deltaX;
        }
        for (ILayoutableLink ll : inLinks) {
            ll.setTargetPoint(new Point(curX, curY));
            // routeLink(ll, curX, -1, -1, -1);
            curX += deltaX;
        }
    }

    @objid ("ea10c916-ae84-4f60-8318-5d8a1b1cfa24")
    private void distributeEastAnchors(ILayoutableNode lNode) {
        List<ILayoutableLink> outLinks = lNode.getOutLinks(AnchorDirection.East);
        
        outLinks.sort(new Comparator<ILayoutableLink>() {
            @Override
            public int compare(ILayoutableLink ll1, ILayoutableLink ll2) {
                int r = Double.compare(ll1.getSlope(), ll2.getSlope());
                if (r == 0) {
                    return Double.compare(ll1.getTo().getCol(), ll2.getTo().getCol());
                } else {
                    return r;
                }
            }
        });
        
        List<ILayoutableLink> inLinks = lNode.getInLinks(AnchorDirection.East);
        
        inLinks.sort(new Comparator<ILayoutableLink>() {
            @Override
            public int compare(ILayoutableLink ll1, ILayoutableLink ll2) {
                int r = -Double.compare(ll1.getSlope(), ll2.getSlope());
                if (r == 0) {
                    return Double.compare(ll1.getTo().getCol(), ll2.getTo().getCol());
                } else {
                    return r;
                }
            }
        });
        
        int deltaY = lNode.getBounds().height / (outLinks.size() + inLinks.size() + 1);
        int curY = lNode.getBounds().y + deltaY;
        int curX = lNode.getBounds().x + lNode.getBounds().width;
        
        for (ILayoutableLink ll : outLinks) {
            ll.setSourcePoint(new Point(curX, curY));
        
            // routeLink(ll, curX, -1, -1, -1);
            curY += deltaY;
        }
        for (ILayoutableLink ll : inLinks) {
            ll.setTargetPoint(new Point(curX, curY));
        
            // routeLink(ll, curX, -1, -1, -1);
            curY += deltaY;
        }
    }

    @objid ("05e2a494-c886-4716-b404-8f121230940b")
    private void distributeWestAnchors(ILayoutableNode lNode) {
        List<ILayoutableLink> outLinks = lNode.getOutLinks(AnchorDirection.West);
        
        outLinks.sort(new Comparator<ILayoutableLink>() {
            @Override
            public int compare(ILayoutableLink ll1, ILayoutableLink ll2) {
                int r = -Double.compare(ll1.getSlope(), ll2.getSlope());
                if (r == 0) {
                    return Double.compare(ll1.getTo().getCol(), ll2.getTo().getCol());
                } else {
                    return r;
                }
            }
        });
        
        List<ILayoutableLink> inLinks = lNode.getInLinks(AnchorDirection.West);
        
        inLinks.sort(new Comparator<ILayoutableLink>() {
            @Override
            public int compare(ILayoutableLink ll1, ILayoutableLink ll2) {
                int r = -Double.compare(ll1.getSlope(), ll2.getSlope());
                if (r == 0) {
                    return -Double.compare(ll1.getTo().getCol(), ll2.getTo().getCol());
                } else {
                    return -r;
                }
            }
        });
        
        int deltaY = lNode.getBounds().height / (outLinks.size() + inLinks.size() + 1);
        int curY = lNode.getBounds().y + deltaY;
        int curX = lNode.getBounds().x;
        
        for (ILayoutableLink ll : outLinks) {
            ll.setSourcePoint(new Point(curX, curY));
        
            // routeLink(ll, curX, -1, -1, -1);
            curY += deltaY;
        }
        for (ILayoutableLink ll : inLinks) {
            ll.setTargetPoint(new Point(curX, curY));
        
            // routeLink(ll, curX, -1, -1, -1);
            curY += deltaY;
        }
    }

    @objid ("ccd9eb39-317f-499e-aadc-ba00154b5ced")
    private static class NodeGrid {
        @objid ("3b103bb8-b3b1-4905-8173-84f2531c964b")
        private int[] xTabs; // For columns

        @objid ("77200d77-d77c-486d-9f63-b194674558fc")
        private int[] yTabs; // For rows

        @objid ("3f60847a-4ea8-4346-b95e-aa589518eb9b")
        private int xSpacing;

        @objid ("2008505d-30c5-4ab0-a770-c65435991ebc")
        private int ySpacing;

        @objid ("fc27129c-c0f1-4097-a2d7-6fb2f723b3c3")
        private LayoutModel layout;

        @objid ("c7f720e5-f2b3-417b-867f-a14b5fa0f042")
        public NodeGrid(LayoutModel layout, int xSpacing, int ySpacing) {
            this.layout = layout;
            this.xSpacing = xSpacing;
            this.ySpacing = ySpacing;
        }

        @objid ("ba273961-ecb5-4d2d-b606-9e7107ac6144")
        public void compute() {
            int nbRows = this.layout.getRowNumber();
            int nbCols = this.layout.getColNumber();
            
            // int nbRows = this.nodeTable.getRowNumber();
            // int nbCols = this.nodeTable.getColNumber();
            //
            this.xTabs = new int[nbCols];
            this.yTabs = new int[nbRows];
            
            int colMaxWidths[] = new int[nbCols];
            
            int rowY = 0;
            for (int row = 0; row < nbRows; row++) {
                this.yTabs[row] = rowY;
                int maxHeight = 0;
                for (int col = 0; col < this.layout.getColNumber(row); col++) {
                    maxHeight = Math.max(maxHeight, this.layout.getNode(row, col).getBounds().height);
                    colMaxWidths[col] = Math.max(colMaxWidths[col], this.layout.getNode(row, col).getBounds().width);
                }
                rowY = rowY + maxHeight + this.ySpacing;
            }
            
            int colX = 0;
            for (int j = 0; j < colMaxWidths.length; j++) {
                this.xTabs[j] = colX;
                colX = colX + colMaxWidths[j] + this.xSpacing;
            }
            
            // System.out.println("X Tabs = " + this.xTabs);
            // for (int xTab : this.xTabs)
            // System.out.printf("%d, ", xTab);
            // System.out.println();
            
            // System.out.println("Y Tabs = " + this.yTabs);
            // for (int yTab : this.yTabs)
            // System.out.printf("%d, ", yTab);
            
            // System.out.println();
        }

        @objid ("8019206b-575b-4ba4-8104-5c572bf8de40")
        public Point getLocation(int row, int col) {
            return new Point(this.xTabs[col], this.yTabs[row]);
        }

    }

}
