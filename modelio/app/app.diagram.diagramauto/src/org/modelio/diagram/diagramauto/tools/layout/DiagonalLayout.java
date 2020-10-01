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

package org.modelio.diagram.diagramauto.tools.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;

@objid ("caaf3d8a-cb43-4d2c-a413-a09bbd3bd895")
public class DiagonalLayout {
    @objid ("25a0667e-78fc-46d4-90ae-e0ca5dfa4120")
    private static final int HSPACING = 30;

    @objid ("f467c5f4-abbe-4371-969d-cb7ef6e10947")
    private static final int VSPACING = 30;

    @objid ("d6753a5b-e2ea-4c78-88a9-9c6ddc2bae0e")
    private static final int ANCHORSPACING = 30;

    @objid ("fe75abaf-fdc1-4dcd-b379-55b66616c156")
    private int _contentgW;

    @objid ("ab577120-36ad-4eb4-864b-6838382cfb99")
    private int _contentgH;

    @objid ("7cc87054-6807-491c-a326-21b26538fc36")
    public void layout(final IDiagramHandle dh, final List<IDiagramNode> contentDgs) throws InvalidSourcePointException, InvalidPointsPathException, InvalidDestinationPointException {
        // sort elements according to their NSU
        // - elements with more out links should be first
        // - elements with more in links should be last
        Collections.sort(contentDgs, new Comparator<IDiagramNode>() {
            @Override
            public int compare(IDiagramNode o1, IDiagramNode o2) {
                int o1from = o1.getFromLinks().size();
                int o1to = o1.getToLinks().size();
        
                int o2from = o2.getFromLinks().size();
                int o2to = o2.getToLinks().size();
        
                if (o1from < o2from) {
                    return 1;
                } else if (o1from > o2from) {
                    return -1;
                } else {
                    if (o1to > o2to) {
                        return 1;
                    } else if (o1to < o2to) {
                        return -1;
                    }
                }
                return 0;
            }
        });
        
        // compute content group height, diagonal group
        for (IDiagramNode content : contentDgs) {
            content.fitToContent();
        
            int toLinks = content.getToLinks().size();
            int fromLinks = content.getFromLinks().size();
            Rectangle r = content.getOverallBounds();
            content.setSize(Math.max(r.width, (toLinks>0?(toLinks-1):toLinks) * ANCHORSPACING), Math.max(r.height, (fromLinks>0?(fromLinks-1):fromLinks) * ANCHORSPACING));
        }
        
        Dimension size = computeDiagonalSize(contentDgs);
        this._contentgW = size.width;
        this._contentgH = size.height;
        //System.out.println("content  " + size);
        
        // position content group
        int _contentgX = HSPACING;
        int _contentgY = VSPACING;
        //System.out.println("left  Dimension(" + _leftgX + ", " + _leftgY + ")");
        
        // move content elements
        moveDiagonalElements(contentDgs, _contentgX, _contentgY);
        
        dh.save();
        
        // move links on content nodes
        for (IDiagramNode source : contentDgs) {
            //System.out.println("source=" + source);
            Rectangle sourceR = source.getOverallBounds();
            int sourceN = source.getFromLinks().size();
            int sourceH = sourceR.height;
            int sourceYoffset = (sourceH - ((sourceN-1) * ANCHORSPACING))/2;
        
            // Links to the content
            for (IDiagramLink link : source.getFromLinks()) {
                if (link.getTo() instanceof IDiagramNode) {
                    IDiagramNode target = (IDiagramNode)link.getTo();
                    //System.out.println("target=" + target);
                    Rectangle targetR = target.getOverallBounds();
                    int targetN = target.getToLinks().size();
                    int targetW = targetR.width;
                    int targetXoffset = (targetW - ((targetN-1) * ANCHORSPACING))/2 + (target.getToLinks().indexOf(link) * ANCHORSPACING);
        
                    ILinkPath path = link.getPath();
        
                    List<Point> points = new ArrayList<>();
                    if (sourceR.x < targetR.x) {
                        points.add(new Point(sourceR.x + sourceR.width, sourceR.y + sourceYoffset));
                        points.add(new Point(targetR.x + targetXoffset, sourceR.y + sourceYoffset));
                        points.add(new Point(targetR.x + targetXoffset, targetR.y));
                    } else {
                        points.add(new Point(sourceR.x, sourceR.y + sourceYoffset));
                        points.add(new Point(targetR.x + targetXoffset, sourceR.y + sourceYoffset));
                        points.add(new Point(targetR.x + targetXoffset, targetR.y + targetR.height));
                    }
        
                    path.setPoints(points);
                    link.setPath(path);
        
                    //System.out.println(points);
        
                    sourceYoffset = sourceYoffset + ANCHORSPACING;
                }
            }
            //System.out.println("");
        }
    }

    /**
     * Compute the size of a diagonal group
     */
    @objid ("cb5d6b2c-a0cf-4250-a068-b35caccdb7a4")
    private Dimension computeDiagonalSize(final List<IDiagramNode> dgs) {
        int width  = 0;
        int height = 0;
        
        for (IDiagramNode node : dgs) {
            node.fitToContent();
        
            Rectangle r = node.getOverallBounds();
            width  = width + r.height + HSPACING;
            height = height + r.height + VSPACING;
        }
        
        if (width > 0) {
            width = height - HSPACING;
        }
        
        if (height > 0) {
            height = height - VSPACING;
        }
        return new Dimension(width, height);
    }

    @objid ("032c8517-3d71-4770-b4f2-a815d41822ba")
    protected void moveDiagonalElements(final List<IDiagramNode> dgs, final int x0, final int y0) {
        int x = x0;
        int y = y0;
        for (IDiagramNode node : dgs) {
            node.setLocation(x, y);
            int w = node.getOverallBounds().width;
            int h = node.getOverallBounds().height;
        
            x = x + w + HSPACING;
            y = y + h + VSPACING;
        }
    }

}
