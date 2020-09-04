/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.core.figures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Link figure with optional rounded corners and bridges on segment crossings.
 * <p>
 * Same as LinkFigure with:
 * <ul>
 * <li>All orthogonal link bends can be rounded.
 * <li>Round bridges can also drawn where vertical segment cross horizontal ones.
 * </ul>
 * 
 * @author cma,
 * @author credits to Alex Selkov
 * @see <a href="http://dev.eclipse.org/newslists/news.eclipse.tools.gef/msg04694.html">[news.eclipse.tools.gef] Re:
 * bendable connections</a>
 */
@objid ("7fae4dc4-1dec-11e2-8cad-001ec947c8cc")
public class RoundedLinkFigure extends LinkFigure {
    /**
     * Radius of bridges across connections
     */
    @objid ("7fb0afcc-1dec-11e2-8cad-001ec947c8cc")
    private static final int BRIDGE_RADIUS = 5;

    /**
     * If true, bridges are drawn where vertical segments cross horizontal ones.
     */
    @objid ("7fb0afcf-1dec-11e2-8cad-001ec947c8cc")
    private boolean bridgesEnabled = false;

    /**
     * Corners radius. Zero disable rounded corners.
     */
    @objid ("7fb0afd1-1dec-11e2-8cad-001ec947c8cc")
    private int radius = 0;

    /**
     * A collection remembering all connections of the diagram
     */
    @objid ("85e67cc1-0087-4c9b-8c4e-3d98fde2db83")
    private Collection<Connection> allDiagramConnections;

    @objid ("7fb0afd3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getBounds() {
        return super.getBounds().getCopy().expand(10, 10);
    }

    /**
     * Get the radius of the rounded line bends.
     * 
     * @return The radius
     */
    @objid ("7fb0afda-1dec-11e2-8cad-001ec947c8cc")
    public int getRadius() {
        return this.radius;
    }

    /**
     * If true, bridges are drawn where vertical segments cross horizontal ones.
     * 
     * @return true if bridges are drawn, else false.
     */
    @objid ("7fb0afdf-1dec-11e2-8cad-001ec947c8cc")
    public boolean isBridgesEnabled() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.bridgesEnabled;
    }

    /**
     * Enable drawing bridges where vertical segments cross horizontal ones.
     * 
     * @param value true to enable bridges.
     */
    @objid ("7fb0afe4-1dec-11e2-8cad-001ec947c8cc")
    public void setBridgesEnabled(boolean value) {
        this.bridgesEnabled = value;
        
        invalidate();
        repaint();
    }

    /**
     * Set the radius of the rounded line corners.
     * <p>
     * Zero disable rounded corners.
     * 
     * @param radius The new radius.
     */
    @objid ("7fb0afe8-1dec-11e2-8cad-001ec947c8cc")
    public void setRadius(int radius) {
        if (this.radius == radius) {
            return;
        }
        
        this.radius = radius;
        invalidate();
        repaint();
    }

    @objid ("7fb0afec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void outlineShape(Graphics g) {
        if (this.radius == 0) {
            outlineStraightShape(g);
        } else {
            outlineRoundedShape(g);
        }
    }

    /**
     * Get all connection figures in the diagram.
     * 
     * @return the connection figures.
     */
    @objid ("7fb0aff2-1dec-11e2-8cad-001ec947c8cc")
    private Collection<Connection> collectConnections() {
        if (this.allDiagramConnections != null) {
            return this.allDiagramConnections;
        }
        
        DiagramElements.LOG.debug(new Throwable("this.allDiagramConnections not defined on "+this));
        return Collections.emptyList();
    }

    /**
     * Draw a line with bridges where other connections are crossing.
     * 
     * @param g The graphics to draw to.
     * @param pp The source of the line
     * @param p1 The end of the line
     * @param connections all connections to check for crossing.
     */
    @objid ("7fb0b006-1dec-11e2-8cad-001ec947c8cc")
    private void drawLine(Graphics g, Point pp, Point p1, Collection<Connection> connections) {
        if (this.bridgesEnabled && pp.x == p1.x) {
            // Vertical line, look for crossings
            final ArrayList<Integer> segments = new ArrayList<>();
        
            for (Connection conn : connections) {
                final PointList cps = conn.getPoints();
                final boolean differentRef = conn.getParent() != getParent();
                for (int j = 0; j < cps.size() - 1; j++) {
                    final Point cp1 = cps.getPoint(j);
                    final Point cp2 = cps.getPoint(j + 1);
                    if (differentRef) {
                        conn.translateToAbsolute(cp1);
                        conn.translateToAbsolute(cp2);
                        translateToRelative(cp1);
                        translateToRelative(cp2);
                    }
                    
                    if ((cp1.y == cp2.y) &&
                        ((cp1.x < pp.x && cp2.x > pp.x) || (cp1.x > pp.x && cp2.x < pp.x)) &&
                        ((pp.y < cp1.y && p1.y > cp1.y) || (pp.y > cp1.y && p1.y < cp1.y))) {
                        segments.add(Integer.valueOf(cp1.y));
                    }
                }
            }
        
            if (! segments.isEmpty()) {
                Collections.sort(segments);
                final int y1 = Math.min(pp.y, p1.y);
                final int y2 = Math.max(pp.y, p1.y);
                int y = y1;
                for (Integer integer : segments) {
                    int y0 = integer.intValue();
        
                    g.drawLine(pp.x, y, pp.x, y0 - BRIDGE_RADIUS);
        
                    if (y2 - y0 > BRIDGE_RADIUS) {
                        g.drawArc(pp.x - BRIDGE_RADIUS,
                                  y0 - BRIDGE_RADIUS,
                                  2 * BRIDGE_RADIUS,
                                  2 * BRIDGE_RADIUS,
                                  90,
                                  180);
                    } else {
                        g.drawLine(pp.x, y, pp.x, y2);
                    }
        
                    y = y0 + BRIDGE_RADIUS;
                }
                g.drawLine(pp.x, y, pp.x, y2);
            } else {
                g.drawLine(pp, p1);
            }
        } else {
            // Horizontal or oblique line, just draw the line.
            g.drawLine(pp, p1);
        }
    }

    /**
     * Get the root figure
     * 
     * @return the root figure.
     */
    @objid ("7fb0b017-1dec-11e2-8cad-001ec947c8cc")
    private IFigure getRoot() {
        IFigure figure = this;
        while (figure.getParent() != null) {
            figure = figure.getParent();
        }
        return figure;
    }

    @objid ("7fb0b01e-1dec-11e2-8cad-001ec947c8cc")
    private int min0(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        return Math.min(a, b);
    }

    /**
     * Draw the polyline with rounded corners.
     * 
     * @param g the graphics object
     */
    @objid ("7fb31226-1dec-11e2-8cad-001ec947c8cc")
    private void outlineRoundedShape(Graphics g) {
        final Collection<Connection> connections;
        if (this.bridgesEnabled) {
            connections = collectConnections();
        } else {
            connections = null;
        }
        
        final PointList ps = getPoints();
        final int psCount = ps.size();
        
        if (psCount == 0) {
            return;
        }
        
        ZoomDrawer.setLineWidth(g, getLineWidth());
        
        Point pp = ps.getPoint(0);
        for (int i = 1; i < psCount; i++) {
            final Point p = ps.getPoint(i);
            final Point ppo = ps.getPoint(i - 1);
        
            if (i == psCount - 1) {
                drawLine(g, pp, p, connections);
                continue;
            }
        
            final Point p1 = p.getCopy();
            final Point p2 = p.getCopy();
        
            final Point pn = ps.getPoint(i + 1);
        
            final int dx = min0(Math.abs(ppo.x - p.x), Math.abs(p.x - pn.x));
            final int dy = min0(Math.abs(ppo.y - p.y), Math.abs(p.y - pn.y));
            final int d = min0(dx, dy);
        
            final int r = Math.min(this.radius, d / 2);
            final int r2 = 2 * r;
            if ((pp.y == p.y) && (p.x == pn.x) && r2 != 0) {
                if ((pp.x < p.x) && (p.y < pn.y)) {
                    p1.x -= r;
                    p2.y += r;
                    g.drawArc(p.x - r2, p.y, r2, r2, 0, 90);
                } else if ((pp.x > p.x) && (p.y < pn.y)) {
                    p1.x += r;
                    p2.y += r;
                    g.drawArc(p.x, p.y, r2, r2, 90, 90);
                } else if ((pp.x < p.x) && (p.y > pn.y)) {
                    p1.x -= r;
                    p2.y -= r;
                    g.drawArc(p.x - r2, p.y - r2, r2, r2, 270, 90);
                } else if ((pp.x > p.x) && (p.y > pn.y)) {
                    p1.x += r;
                    p2.y -= r;
                    g.drawArc(p.x, p.y - r2, r2, r2, 180, 90);
                }
            }
        
            if ((pp.x == p.x) && (p.y == pn.y) && r2 != 0) {
                if ((pp.y < p.y) && (p.x < pn.x)) {
                    p1.y -= r;
                    p2.x += r;
                    g.drawArc(p.x, p.y - r2, r2, r2, 180, 90);
                } else if ((pp.y < p.y) && (p.x > pn.x)) {
                    p1.y -= r;
                    p2.x -= r;
                    g.drawArc(p.x - r2, p.y - r2, r2, r2, 270, 90);
                } else if ((pp.y > p.y) && (p.x < pn.x)) {
                    p1.y += r;
                    p2.x += r;
                    g.drawArc(p.x, p.y, r2, r2, 90, 90);
                } else if ((pp.y > p.y) && (p.x > pn.x)) {
                    p1.y += r;
                    p2.x -= r;
                    g.drawArc(p.x - r2, p.y, r2, r2, 0, 90);
                }
            }
        
            drawLine(g, pp, p1, connections);
            pp = p2;
        }
    }

    /**
     * Draw the outline with straight edges.
     * 
     * @param g the graphics object
     */
    @objid ("7fb3122c-1dec-11e2-8cad-001ec947c8cc")
    private void outlineStraightShape(Graphics g) {
        final Collection<Connection> connections;
        if (this.bridgesEnabled) {
            connections = collectConnections();
        } else {
            connections = null;
        }
        
        final PointList ps = getPoints();
        final int psCount = ps.size();
        
        if (psCount == 0) {
            return;
        }
        
        ZoomDrawer.setLineWidth(g, getLineWidth());
        
        Point pp = ps.getPoint(0);
        for (int i = 1; i < psCount; i++) {
            final Point p = ps.getPoint(i);
            drawLine(g, pp, p, connections);
            pp = p;
        }
    }

    @objid ("b3dc8905-ebb8-4ebf-b655-da17c8a8fb56")
    public void setAllDiagramConnections(Collection<Connection> allDiagramConnections) {
        this.allDiagramConnections = allDiagramConnections;
        this.allDiagramConnections.add(this);
    }

    @objid ("2de644f9-8bbe-48dc-b00a-f6f6424b58e4")
    @Override
    public void addNotify() {
        super.addNotify();
        
        if (this.allDiagramConnections != null) {
            this.allDiagramConnections.add(this);
        }
    }

    @objid ("9572d801-6ee6-4062-a627-bc7869216e62")
    @Override
    public void removeNotify() {
        if (this.allDiagramConnections != null) {
            this.allDiagramConnections.remove(this);
        }
        
        super.removeNotify();
    }

}
