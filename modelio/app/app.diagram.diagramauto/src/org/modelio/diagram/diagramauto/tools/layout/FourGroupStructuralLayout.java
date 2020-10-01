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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;

@objid ("29b85026-6983-4b73-9e5e-975e5d8bc938")
public class FourGroupStructuralLayout {
    @objid ("8c7d3be1-120c-496b-ac65-597a76d7d492")
    private static final int HSPACING = 20;

    @objid ("350a3d9a-6d16-40c8-a0e1-24be4222022e")
    private static final int VSPACING = 40;

    @objid ("0eef22dc-a49e-4fe1-be89-b7f3c6bb02fe")
    private static final int ANCHORSPACING = 50;

    @objid ("920d20ec-516b-4de2-8672-c1a752bd4381")
    private static final int ASSOCLENGTH = 250;

    @objid ("c497429a-f464-4b27-b6d8-9bb55e1f1726")
    private static final int GENLENGTH = 100;

    @objid ("45ebe48f-6349-482a-9350-9612b225cde2")
    private static final int REFLECT_SPACING = 20;

    @objid ("6364f1c3-162e-4042-a2fd-2a5930e1f0d7")
    private int _centerX = 0; // x for center

    @objid ("0762b9c1-0adf-4c8c-bfc2-26d2594adb08")
    private int _centerY = 0; // y for center

    @objid ("408a20ff-2777-45fe-ace7-daafc200cb34")
    private int _mainX = 0; // x for main

    @objid ("16c73402-e9e6-41a5-afc6-92c44e37edb4")
    private int _mainY = 0; // y for main

    @objid ("174d49de-2a83-4a78-ab99-65792eb21318")
    private int _mainW = 0; // width for main

    @objid ("ec9d77bb-d3c1-468f-a4f2-81ce0d98491d")
    private int _mainH = 0; // height for main

    @objid ("556e6e06-f2ac-4227-b179-9d88d8badc52")
    private int _topgW = 0; // top group width

    @objid ("e510c663-8479-4593-8163-9f88a3fb19de")
    private int _topgH = 0; // top group height

    @objid ("1b9f69e8-c11d-4c7f-83ec-57959c7cd846")
    private int _leftgW = 0; // left group width

    @objid ("9dd3f482-30d0-4df1-938f-98436144745b")
    private int _leftgH = 0; // left group height

    @objid ("5389f160-9008-451a-9930-3d69eb10f1a7")
    private int _rightgW = 0; // right group width

    @objid ("ebbd996f-8f91-482b-89a4-7157b9a1141b")
    private int _rightgH = 0; // right group height

    @objid ("35dbf30b-d886-4dfa-8d26-7c96450e078d")
    private int _bottomgW = 0; // top group width

    @objid ("bc032f6d-6bf2-49d0-991c-618dd465cced")
    private int _bottomgH = 0; // top group height

    @objid ("35886851-121b-4a11-9872-3954a2d9df4c")
    private int _reflexivegH = 0; // reflexive group width

    @objid ("08d88f8d-be78-464b-9067-e3b9ce8f25a1")
    private int _reflexivegW = 0; // reflexive group height

    /**
     * Compute the size of an horizontal group
     */
    @objid ("54708814-3e3d-448d-bd87-8981f336965f")
    private Dimension computeHgroupSize(final List<IDiagramNode> dgs) {
        int width  = 0;
        int height = 0;
        
        for (IDiagramNode node : dgs) {
            //TODO node.fitToContent();
        
            Rectangle r = node.getOverallBounds();
            width  = width + r.width + HSPACING;
            height = Math.max(height, r.height);
        }
        
        if (width > 0) {
            width = width - HSPACING;
        }
        return new Dimension(width, height);
    }

    /**
     * Compute the size of a vertical group
     */
    @objid ("9456a656-487b-4c36-8594-d0d079ea73b8")
    private Dimension computeVerticalSize(final List<IDiagramNode> dgs) {
        int width  = 0;
        int height = 0;
        
        for (IDiagramNode node : dgs) {
            //TODO node.fitToContent();
        
            Rectangle r = node.getOverallBounds();
            width  = Math.max(width, r.width);
            height = height + r.height + VSPACING;
        }
        
        if (height > 0) {
            height = height - VSPACING;
        }
        return new Dimension(width, height);
    }

    @objid ("6d1e8590-1144-45e1-a227-b644ec070f7a")
    public void layout(final IDiagramNode mainDG, final List<IDiagramNode> topDgs, final List<IDiagramNode> bottomDgs, final List<IDiagramNode> leftDgs, final List<IDiagramNode> rightDgs, final List<IDiagramLink> linkDgs) throws InvalidSourcePointException, InvalidPointsPathException, InvalidDestinationPointException {
        //System.out.println("   Sizes:");
        
        // compute top group width, horizontal group
        Dimension size = computeHgroupSize(topDgs);
        this._topgW = size.width;
        this._topgH = size.height;
        //System.out.println("top   " + size);
        
        // compute bottom group width, horizontal group
        size = computeHgroupSize(bottomDgs);
        this._bottomgW = size.width;
        this._bottomgH = size.height;
        //System.out.println("bottom   " + size);
        
        // compute left group height, vertical group
        for (IDiagramNode left : leftDgs) {
            int nlinks = left.getFromLinks().size() + left.getToLinks().size();
            Rectangle r = left.getOverallBounds();
            left.setSize(r.width, r.height + (nlinks-1) * ANCHORSPACING);
        }
        
        size = computeVerticalSize(leftDgs);
        this._leftgW = size.width;
        this._leftgH = size.height;
        //System.out.println("left  " + size);
        
        // compute right group height
        for (IDiagramNode right : rightDgs) {
            int nlinks = right.getFromLinks().size() + right.getToLinks().size();
            Rectangle r = right.getOverallBounds();
            right.setSize(r.width, r.height + (nlinks-1) * ANCHORSPACING);
        }
        
        size = computeVerticalSize(rightDgs);
        this._rightgW = size.width;
        this._rightgH = size.height;
        //System.out.println("right " + size);
        
        // compute reflexive group size
        this._reflexivegH = REFLECT_SPACING * (linkDgs.size() + 1);
        this._reflexivegW = REFLECT_SPACING * (linkDgs.size() + 1);
        
        mainDG.fitToContent();
        Rectangle mainRect = mainDG.getOverallBounds();
        
        // compute main size
        this._mainW = Math.max(180, Math.max(mainRect.width, this._reflexivegW)) + this._reflexivegW;
        this._mainH = Math.max(mainRect.height, Math.max(this._leftgH, this._rightgH)) + this._reflexivegH;
        //System.out.println("main  Dimension(" + this._mainW + ", " + this._mainH + ")");
        
        mainDG.setSize(this._mainW, this._mainH);
        
        this._centerX = (this._leftgW + ASSOCLENGTH + this._mainW + ASSOCLENGTH + this._rightgW) / 2;
        this._centerY = (VSPACING + this._topgH  + GENLENGTH + this._mainH / 2);
        
        //System.out.println("center Dimension(" + this._centerX + ", " + this._centerY + ")");
        
        //System.out.println("   Positions:");
        
        // position main element    
        this._mainX = this._centerX - (this._mainW/2);
        this._mainY = this._centerY - (this._mainH/2);
        
        // position left group
        int _leftgX = 0;
        int _leftgY = this._centerY - (this._leftgH/2) - this._reflexivegH/2;
        //System.out.println("left  Dimension(" + _leftgX + ", " + _leftgY + ")");
        
        // position right group    
        int _rightgX = this._mainX + this._mainW + ASSOCLENGTH;
        int _rightgY = this._centerY - (this._rightgH/2) - this._reflexivegH/2;
        //System.out.println("right Dimension(" + _rightgX + ", " + _rightgY + ")");
        
        // position top group    
        int _topgX = this._centerX - (this._topgW/2);
        int _topgY = this._mainY - this._topgH - GENLENGTH;
        //System.out.println("top   Dimension(" + _topgX + ", " + _topgY + ")");
        
        // position bottom group   
        int _bottomgX = this._centerX - (this._bottomgW/2);
        int _bottomgY = this._mainY + this._mainH + GENLENGTH;
        //System.out.println("bottom   Dimension(" + _bottomgX + ", " + _bottomgY + ")");
        
        // move mainDG
        mainDG.setLocation(this._mainX, this._mainY);
        //System.out.println("main  Dimension(" + this._mainX + ", " + this._mainY + ")");
        
        // move top elements
        moveHorizontalElements(topDgs, _topgX, _topgY, this._topgH);
        
        // move bottom elements
        moveHorizontalElements(bottomDgs, _bottomgX, _bottomgY, this._bottomgH);
        
        // move left elements
        moveVerticalElements(leftDgs, _leftgX, _leftgY, this._leftgW);
        
        // move right elements
        moveVerticalElements(rightDgs, _rightgX, _rightgY, this._rightgW);
        
        // move links on left nodes
        for (IDiagramNode left : leftDgs) {
            Rectangle r = left.getOverallBounds();
            int n = left.getFromLinks().size() + left.getToLinks().size();
            int h = r.height;
            int yoffset = (h - ((n-1) * ANCHORSPACING))/2;
        
            // Links from the left
            for (IDiagramLink link : left.getFromLinks()) {
                ILinkPath path = link.getPath();
        
                List<Point> points = new ArrayList<>();
                points.add(new Point(this._mainX , r.y + yoffset)); 
                points.add(new Point(r.x + r.width, r.y + yoffset));
        
                path.setPoints(points);
                link.setPath(path);
        
                yoffset = yoffset + ANCHORSPACING;
            }
        
            // Links to the left
            for (IDiagramLink link : left.getToLinks()) {
                ILinkPath path = link.getPath();
        
                List<Point> points = new ArrayList<>();
                points.add(new Point(r.x + r.width, r.y + yoffset));
                points.add(new Point(this._mainX , r.y + yoffset));
        
                path.setPoints(points);
                link.setPath(path);
        
                yoffset = yoffset + ANCHORSPACING;
            }
        }
        
        // move links on right nodes
        for (IDiagramNode right : rightDgs) {
            Rectangle r = right.getOverallBounds();
            int n = right.getFromLinks().size() + right.getToLinks().size();
            int h = r.height;
            int yoffset = (h - ((n-1) * ANCHORSPACING))/2;
        
            // Links from the right
            for (IDiagramLink link : right.getFromLinks()) {
                ILinkPath path = link.getPath();
        
                List<Point> points = new ArrayList<>();
                points.add(new Point(this._mainX + this._mainW, r.y + yoffset)); 
                points.add(new Point(r.x, r.y + yoffset));
        
                path.setPoints(points);
                link.setPath(path);
        
                yoffset = yoffset + ANCHORSPACING;
            }
        
            // Links to the right
            for (IDiagramLink link : right.getToLinks()) {
                ILinkPath path = link.getPath();
        
                List<Point> points = new ArrayList<>();
                points.add(new Point(r.x + r.width, r.y + yoffset));
                points.add(new Point(this._mainX , r.y + yoffset));
                
                path.setPoints(points);
                link.setPath(path);
        
                yoffset = yoffset + ANCHORSPACING;
            }
        }
        
        // links on top nodes
        for (IDiagramNode top : topDgs) {
            Rectangle r = top.getOverallBounds();
            int n = top.getFromLinks().size() + top.getToLinks().size();
            int w = r.width;
            int xoffset = (w - ((n-1) * ANCHORSPACING))/2;
        
            for (IDiagramLink link : top.getFromLinks()) {
                ILinkPath path = link.getPath();
        
                List<Point> points = new ArrayList<>();
                points.add(new Point(r.x + r.width/2, r.y + r.height));
                points.add(new Point(r.x + r.width/2, this._mainY - GENLENGTH/2));
                points.add(new Point(this._mainX + this._mainW/2, this._mainY - GENLENGTH/2));
                points.add(new Point(this._mainX + this._mainW/2, this._mainY));
        
                path.setPoints(points);
                link.setPath(path);
        
                xoffset = xoffset + ANCHORSPACING;
            }
        
            for (IDiagramLink link : top.getToLinks()) {
                ILinkPath path = link.getPath();
        
                List<Point> points = new ArrayList<>();
                points.add(new Point(this._mainX + this._mainW/2, this._mainY));
                points.add(new Point(this._mainX + this._mainW/2, this._mainY - GENLENGTH/2));
                points.add(new Point(r.x + r.width/2, this._mainY - GENLENGTH/2));
                points.add(new Point(r.x + r.width/2, r.y + r.height));
        
                path.setPoints(points);
                link.setPath(path);
        
                xoffset = xoffset + ANCHORSPACING;
            }
        }
        
        // links on top nodes
        for (IDiagramNode bottom : bottomDgs) {
            Rectangle r = bottom.getOverallBounds();
            int n = bottom.getFromLinks().size() + bottom.getToLinks().size();
            int w = r.width;
            int xoffset = (w - ((n-1) * ANCHORSPACING))/2;
        
            for (IDiagramLink link : bottom.getFromLinks()) {
                ILinkPath path = link.getPath();
        
                List<Point> points = new ArrayList<>();
                points.add(new Point(r.x + r.width/2, r.y));
                points.add(new Point(r.x + r.width/2, this._mainY + this._mainH + GENLENGTH/2));
                points.add(new Point(this._mainX + this._mainW/2, this._mainY + this._mainH + GENLENGTH/2));
                points.add(new Point(this._mainX + this._mainW/2, this._mainY + this._mainH));
        
                path.setPoints(points);
                link.setPath(path);
        
                xoffset = xoffset + ANCHORSPACING;
            }
        
            for (IDiagramLink link : bottom.getToLinks()) {
                ILinkPath path = link.getPath();
        
                List<Point> points = new ArrayList<>();
                points.add(new Point(this._mainX + this._mainW/2, this._mainY));
                points.add(new Point(this._mainX + this._mainW/2, this._mainY - GENLENGTH/2));
                points.add(new Point(r.x + r.width/2, this._mainY - GENLENGTH/2));
                points.add(new Point(r.x + r.width/2, r.y + r.height));
        
                path.setPoints(points);
                link.setPath(path);
        
                xoffset = xoffset + ANCHORSPACING;
            }
        }
        
        int offset = REFLECT_SPACING;
        
        // reflective links
        for (IDiagramLink link : linkDgs) {
            ILinkPath path = link.getPath();
        
            List<Point> points = new ArrayList<>();
            points.add(new Point(this._mainX + this._mainW, this._mainY + this._mainH - offset));
            points.add(new Point(this._mainX + this._mainW + offset, this._mainY + this._mainH - offset));
            points.add(new Point(this._mainX + this._mainW + offset, this._mainY + this._mainH + offset));
            points.add(new Point(this._mainX + this._mainW - offset, this._mainY + this._mainH + offset));
            points.add(new Point(this._mainX + this._mainW - offset, this._mainY + this._mainH));
        
            path.setPoints(points);
            link.setPath(path);
        
            offset = offset + REFLECT_SPACING;
        }
    }

    @objid ("c8f432b6-9db9-4b54-b7fc-5929b673dd57")
    protected void moveHorizontalElements(final List<IDiagramNode> dgs, final int x0, final int y0, final int nodeHeigth) {
        int x = x0;
        int y = y0;
        for (IDiagramNode right : dgs) {
            right.setLocation(x, y);
            int w = right.getOverallBounds().width;
            right.setSize(w, nodeHeigth);
            x = x + w + HSPACING;
        }
    }

    @objid ("a494bcf2-68fc-47ee-a73b-4a35de63bbfb")
    protected void moveVerticalElements(final List<IDiagramNode> dgs, final int x0, final int y0, final int nodeWidth) {
        int x = x0;
        int y = y0;
        for (IDiagramNode node : dgs) {
            node.setLocation(x, y);
            int h = node.getOverallBounds().height;
            node.setSize(nodeWidth, h);
            y = y + h + VSPACING;
        }
    }

}
