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
package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.draw2d.geometry.Vector;

/**
 * Copy of {@link org.eclipse.draw2d.ConnectionEndpointLocator} with
 * <ul>
 * <li>{@link #isEnd()} accessible.
 * <li>handles fixed size
 * </ul>
 * <h3>history</h3>
 * <ul>
 * <li>Modelio < 3.4 : did inherit from {@link org.eclipse.draw2d.ConnectionEndpointLocator} to get access to {@link #isEnd()}
 * <li>Modelio 3.4 : is now a copy to handle size fixed in the locator model.
 * </ul>
 * 
 * @author cmarin
 */
@objid ("800dab9e-1dec-11e2-8cad-001ec947c8cc")
public class SidedConnectionEndpointLocator implements IResizableFigureLocator {
    @objid ("82fd793b-2aa3-42e5-ae73-cf03b41e013f")
    private boolean end;

    @objid ("bed89d64-1b65-4737-bfd6-d3694d426564")
    private int uDistance;

    @objid ("e877cfde-6c76-4489-9d84-d0192f423c23")
    private int vDistance;

    @objid ("239e8a94-bd5f-4d9b-9c34-9abf3383bde9")
    private int widthConstraint = -1;

    @objid ("20fb0a09-897e-434b-9d22-fa5334fdcc2a")
    private int heightConstraint = -1;

    @objid ("e6b40101-eddb-46f2-a6f7-ba9982698e17")
    static final int QUANDRANT_TOP = 1;

    @objid ("37191120-14d0-4bcb-87a5-3b64fb8e5830")
    static final int QUANDRANT_BOTTOM = 3;

    @objid ("2ae9350d-36cd-4a6d-b90f-a1ab27f9136b")
    static final int QUANDRANT_LEFT = 4;

    @objid ("903574c9-6fa3-44d1-a2ca-556e2dd01dd8")
    static final int QUANDRANT_RIGHT = 2;

    @objid ("1d1524c7-445b-49f9-9e04-125924f925f3")
    private Connection connection;

    /**
     * Transposes the location if the connection point is along the top or
     * bottom of its owner figure.
     */
    @objid ("1306b54d-6cfd-4c47-8655-17c30adbf999")
    protected Transposer transposer = new Transposer();

    /**
     * Constructs a ConnectionEndpointLocator using the given {@link Connection}
     * . If <i>isEnd</i> is <code>true</code>, the location is relative to the
     * Connection's end (or target) point. If <i>isEnd</i> is <code>false</code>
     * , the location is relative to the Connection's start (or source) point.
     * @param c The Connection
     * @param isEnd <code>true</code> is location is relative to end point
     */
    @objid ("800daba4-1dec-11e2-8cad-001ec947c8cc")
    public  SidedConnectionEndpointLocator(Connection c, boolean isEnd) {
        this.end = isEnd;
        this.connection = c;
        this.uDistance = 14;
        this.vDistance = 4;
        
    }

    /**
     * Return <i>true</i> if the figure is located at the target end, <i>false</i> if the figure is located at the source end.
     * @return <i>true</i> if the figure is located at the target end, <i>false</i> if the figure is located at the source end.
     */
    @objid ("800dabab-1dec-11e2-8cad-001ec947c8cc")
    public boolean isEnd() {
        return this.end;
    }

    /**
     * Returns an integer representing the side of the passed Rectangle that a
     * point lies on. 1 == Top, 2 == Right, 3 == Bottom, 4 == Left
     * @param loc The point that is to be located
     */
    @objid ("f5e0edee-7442-4318-b481-e8254e6edcba")
    static int calculateConnectionLocation(Point loc, Point topLeft, Point center) {
        double m1, m2 = 0;
        m1 = (double) (topLeft.y - center.y) / (double) (topLeft.x - center.x);
        
        if (loc.x - center.x != 0) {
            m2 = (double) (loc.y - center.y) / (double) (loc.x - center.x);
        }
        
        if (loc.x == center.x) {
            // Case where m2 is vertical
            if (loc.y < center.y) {
                return QUANDRANT_BOTTOM;
            } else {
                return QUANDRANT_TOP;
            }
        } else if (Math.abs(m2) <= Math.abs(m1)) {
            // Connection start point along left or right side
            if (loc.x < center.x) {
                return QUANDRANT_LEFT;
            } else {
                return QUANDRANT_RIGHT;
            }
        } else {
            // Connection start point along top or bottom
            if (loc.y < center.y) {
                return QUANDRANT_BOTTOM;
            } else {
                return QUANDRANT_TOP;
            }
        }
        
    }

    /**
     * This method is used to calculate the "quadrant" value of a connection
     * that does not have an owner on its starting point.
     * 1 == Top 2 == Right 3 == Bottom 4 == Left
     * @param startPoint The starting point of the connection.
     * @param endPoint The end point of the connection.
     */
    @objid ("f52360da-3d1f-4011-9966-5deb07276286")
    static int calculateConnectionLocation(Point startPoint, Point endPoint) {
        if (Math.abs(endPoint.x - startPoint.x) > Math.abs(endPoint.y
                - startPoint.y)) {
            if (endPoint.x > startPoint.x) {
                return QUANDRANT_RIGHT;
            } else {
                return QUANDRANT_LEFT;
            }
        } else {
            if (endPoint.y > startPoint.y) {
                return QUANDRANT_TOP;
            } else {
                return QUANDRANT_BOTTOM;
            }
        }
        
    }

    /**
     * Calculates 'tan' which is used as a factor for y adjustment when placing
     * the connection label. 'tan' is capped at 1.0 in the positive direction
     * and -1.0 in the negative direction.
     * @param startPoint The starting point of the connection.
     * @param endPoint The end point of the connection.
     * @since 2.0
     */
    @objid ("c05e9db2-401c-469a-97d4-d2855896b036")
    static double calculateTan(Point startPoint, Point endPoint) {
        double tan = 0;
        if (endPoint.x == startPoint.x) {
            tan = 1.0;
        } else {
            tan = (double) (endPoint.y - startPoint.y)
                    / (double) (endPoint.x - startPoint.x);
        }
        if (tan > 1) {
            tan = 1.0;
        } else if (tan < -1) {
            tan = -1.0;
        }
        return tan;
    }

    @objid ("83214764-6614-4f16-bab6-657399cee56f")
    @SuppressWarnings("unused")
    private int calculateYShift(int figureWidth, int figureHeight) {
        int yShift = 0;
        if (this.vDistance < 0) {
            yShift = -figureHeight;
        } else if (this.vDistance == 0) {
            yShift = -figureHeight / 2;
        }
        return yShift;
    }

    @objid ("6887b3e4-e08a-40f2-a87e-c147ed2b7583")
    private Connection getConnection() {
        return this.connection;
    }

    @objid ("0a27e8fd-2234-4e55-a15e-dcc5cb5296c2")
    private IFigure getConnectionOwner() {
        IFigure connOwner;
        if (isEnd()) {
            ConnectionAnchor targetAnchor = this.connection.getTargetAnchor();
            connOwner = targetAnchor != null ? targetAnchor.getOwner() : null;
        } else {
            ConnectionAnchor sourceAnchor = this.connection.getSourceAnchor();
            connOwner = sourceAnchor != null ? sourceAnchor.getOwner() : null;
        }
        return connOwner;
    }

    /**
     * Returns the distance in pixels from the anchor's owner.
     * @return the offset distance from the endpoint figure
     */
    @objid ("e16a78be-1b24-4455-9a7e-44ca40649f6c")
    public int getUDistance() {
        return this.uDistance;
    }

    /**
     * Returns the distance in pixels from the connection
     * @return the offset from the connection itself
     */
    @objid ("4b9626a7-fdd0-4624-87fb-d26c471b6ae5")
    public int getVDistance() {
        return this.vDistance;
    }

    /**
     * Relocates the given IFigure at either the source or target end of the
     * Connection, based on the <code>boolean</code> given in the constructor
     * {@link #SidedConnectionEndpointLocator(Connection, boolean)}.
     * @param figure The figure to relocate
     */
    @objid ("5630b531-5aaa-45b5-a4db-be3211ceb8b1")
    @Override
    public void relocate(IFigure figure) {
        Connection conn = getConnection();
        PrecisionPoint startPoint = new PrecisionPoint();
        PrecisionPoint endPoint = new PrecisionPoint();
        
        int startPointPosition = 0;
        int endPointPosition = 1;
        if (isEnd()) {
            startPointPosition = conn.getPoints().size() - 1;
            endPointPosition = startPointPosition - 1;
        }
        
        conn.getPoints().getPoint(startPoint, startPointPosition);
        conn.getPoints().getPoint(endPoint, endPointPosition);
        
        
        if (false) {
            _relocate_chatgpt_way1(figure, startPoint, endPoint);
        } else {
        
            IFigure connOwner = getConnectionOwner();
            int quadrant;
            if (connOwner != null) {
                Rectangle connOwnerBounds = connOwner.getBounds();
                Point connOwnerCenter = connOwnerBounds.getCenter();
                Point connOwnerTL = connOwnerBounds.getTopLeft();
                quadrant = calculateConnectionLocation(startPoint, connOwnerTL,
                        connOwnerCenter);
            } else {
                quadrant = calculateConnectionLocation(startPoint, endPoint);
            }
        
            this.transposer.setEnabled(false);
        
            /*
             * Label placement calculations are done as if the connection point is
             * along the left or right side of the figure. If the connection point
             * is along the top or bottom, values are transposed.
             */
            if (quadrant == QUANDRANT_TOP || quadrant == QUANDRANT_BOTTOM) {
                this.transposer.setEnabled(true);
            }
        
            int cos ;
            if (quadrant == QUANDRANT_RIGHT || quadrant == QUANDRANT_TOP) {
                cos = 1;
            } else {
                cos = -1;
            }
        
            Dimension figureSize = this.transposer.t(computeFigureSize(figure));
        
            startPoint = (PrecisionPoint) this.transposer.t(startPoint);
            endPoint = (PrecisionPoint) this.transposer.t(endPoint);
        
            double tan = calculateTan(startPoint, endPoint);
        
            int figureWidth = figureSize.width;
            int figureHeight = figureSize.height;
            int yShift = calculateYShift(figureWidth, figureHeight);
        
            Point figurePoint = new Point(
                    startPoint.x + (this.uDistance * cos) + figureWidth * ((cos - 1) / 2),
                    (int) (startPoint.y + cos * this.uDistance * tan + this.vDistance + yShift));
        
            Rectangle figureBounds = new Rectangle();
            figureBounds.setSize(this.transposer.t(figureSize));
            figureBounds.setLocation(this.transposer.t(figurePoint));
            figure.setBounds(figureBounds);
        }
        
    }

    /**
     * Asked ChatGPT for:
     * 
     * <pre>
     * J'ai un segment AB avec les points A et B connus: (startPoint, endPoint).
     * J'ai un segment AC avec les points A et C connus. (C is a point to make a segment on node figure bounds)
     * J'ai une distance v depuis la droite AB, qui forme une droite D parall�le � AB.
     * J'ai un vecteur u(ux, uy) depuis le point A, qui forme une droite E parall�le � AC.
     * 
     * Il me faut un algorithme java pour calculer l'intersection entre D et E.
     * </pre>
     * @deprecated does not work :-\
     * @author ChatGPT
     */
    @objid ("0eaab1bc-8000-47b4-86f1-2d8af4f5e609")
    @Deprecated
    private void _relocate_chatgpt_way(IFigure figure, PrecisionPoint startPoint, PrecisionPoint endPoint) {
        if (false) {
        // Compute input for ChatGPT algo
        Vector a = new Vector(startPoint, endPoint);
        Vector u = new Vector(0, 0);
        Point b = endPoint;
        Point c = new Point(startPoint);
        int quadrant = calculateConnectionLocation(startPoint, endPoint);
        switch (quadrant) {
        case QUANDRANT_BOTTOM:
            c.translate(10, 0);
            u.y = this.uDistance;
            break;
        case QUANDRANT_TOP:
            c.translate(10, 0);
            u.y = -this.uDistance;
            break;
        case QUANDRANT_LEFT:
            c.translate(0, 10);
            u.x = - this.uDistance;
            break;
        case QUANDRANT_RIGHT:
            c.translate(0, 10);
            u.x = + this.uDistance;
            break;
        }
        
        
        // ChatGPT algo begins here
        // ========================
        // Calculer le vecteur AB et le vecteur AC
        Vector ab = new Vector(b.x - a.x, b.y - a.y);
        Vector ac = new Vector(c.x - a.x, c.y - a.y);
        
        // Normaliser le vecteur AB
        ab = ab.getDivided(ab.getLength());
        
        // Calculer la distance entre A et la droite D
        double distanceAD = ab.getCrossProduct(ac);
        
        // Calculer le vecteur de translation pour projeter u sur la droite D
        Vector translation = ab.getMultiplied(u.getDotProduct(ab) / ab.getDotProduct(ab));
        
        // Calculer le vecteur projet� de u sur la droite D
        Vector projectedU = u.getSubtracted(translation);
        
        // Calculer la distance entre A et la droite E
        double distanceAE = ac.getCrossProduct(projectedU);
        
        // Calculer le point d'intersection entre les droites D et E
        double t = distanceAE / (distanceAE - distanceAD);
        int v = this.vDistance;
        Point intersection = new PrecisionPoint(a.x + t * ab.x * v, a.y + t * ab.y * v);
        
        PrecisionRectangle figureBounds = new PrecisionRectangle(intersection, computeFigureSize(figure));
        
        if (a.x < 0)
            figureBounds.translate(-figureBounds.preciseWidth(), 0);
        if (a.y < 0)
            figureBounds.translate(0, -figureBounds.preciseHeight());
        
        figure.setBounds(figureBounds);
        }
        
    }

    @objid ("287d0f6a-81ad-49a1-ae47-8a77c22ad672")
    private static Vector normalized(Vector v) {
        return v.getDivided(v.getLength());
    }

    /**
     * Asked ChatGPT for:
     * 
     * <pre>
     * J'ai un segment AB avec les points A et B connus: (startPoint, endPoint).
     * J'ai un segment AC avec les points A et C connus. (C is a point to make a segment on node figure bounds)
     * J'ai une distance v depuis la droite AB, qui forme une droite D parall�le � AB.
     * J'ai un vecteur u(ux, uy) depuis le point A, qui forme une droite E parall�le � AC.
     * 
     * Il me faut un algorithme java pour calculer l'intersection entre D et E.
     * </pre>
     * @deprecated does not work :-\
     * @author ChatGPT
     */
    @objid ("63abc6bb-97e2-4d41-8ccf-ca967d7b9772")
    @Deprecated
    private void _relocate_chatgpt_way1(IFigure figure, PrecisionPoint startPoint, PrecisionPoint endPoint) {
        if (false) {
        
        // Compute input for ChatGPT algo
        Vector A = new Vector(startPoint, endPoint);
        Vector u = new Vector(0, 0);
        Vector B = new Vector(endPoint);
        Vector C = new Vector(startPoint);
        int quadrant = calculateConnectionLocation(startPoint, endPoint);
        switch (quadrant) {
        case QUANDRANT_BOTTOM:
            C.x += 10;
            u.y = this.uDistance;
            break;
        case QUANDRANT_TOP:
            C.x += 10;
            u.y = -this.uDistance;
            break;
        case QUANDRANT_LEFT:
            C.y += 10;
            u.x = - this.uDistance;
            break;
        case QUANDRANT_RIGHT:
            C.y += 10;
            u.x = + this.uDistance;
            break;
        }
        
        int v = this.vDistance;
        
        /*
         *   A       C
         *   +-------+
         *   .*
         *  U. *
         *   .  *
         *   .   *P    E
         *  Q+.../*......
         *    . /  *
         *     . V  *
         *      .    +B
         *     D .
         */
        // ChatGPT algo begins here
        // ========================
        
        Vector AB = B.getSubtracted(A);
        Vector AC = C.getSubtracted(A);
        Vector AB_norm = normalized(AB);
        Vector AC_norm = normalized(AC);
        Vector AP = AB_norm.getMultiplied(u.getDotProduct(AB_norm));
        
        /*
         * Ensuite, nous allons calculer la projection du vecteur u sur la droite AB,
         * pour trouver le point P qui se situe sur la droite AB et le plus proche de u.
         * Pour cela, nous allons utiliser la formule suivante :
         *
         * AP = u . AB_norm * AB_norm
         * P = A + AP
         */
        Vector P = A.getAdded(AP);
        Vector D_norm = new Vector(-AB_norm.y, AB_norm.x);
        // Le point Q est l'intersection entre les droites D et E
        //  Q = P + (v / D_norm . AC_norm) * AC_norm
        Vector Q = P.getAdded(AC_norm.getMultiplied(v / D_norm.getDotProduct(AC_norm)));
        Vector intersection = Q.getAdded(A);
        
        
        //Point intersection = new PrecisionPoint(a.x + t * ab.x * v, a.y + t * ab.y * v);
        
        PrecisionRectangle figureBounds = new PrecisionRectangle(intersection.toPoint(), computeFigureSize(figure));
        
        if (A.x < 0)
            figureBounds.translate(-figureBounds.preciseWidth(), 0);
        if (A.y < 0)
            figureBounds.translate(0, -figureBounds.preciseHeight());
        
        figure.setBounds(figureBounds);
        }
        
    }

    /**
     * Calculate the figure size from this locator
     * @param target the figure to relocate.
     * @return the figure size to set.
     */
    @objid ("efe6a76d-e7f7-44f9-805f-eb15cb63a061")
    protected Dimension computeFigureSize(IFigure figure) {
        return figure.getPreferredSize(getWidthConstraint(), getHeightConstraint());
    }

    /**
     * Sets the distance in pixels from the Connection's owner.
     * @param distance Number of pixels to place the ConnectionEndpointLocator from
     * its owner.
     * @since 2.0
     */
    @objid ("a79f65a5-3d19-4f46-ac02-17fd4747a718")
    public void setUDistance(int distance) {
        this.uDistance = distance;
    }

    /**
     * Sets the distance in pixels from the Connection.
     * @param distance Number of pixels to place the ConnectionEndpointLocator from
     * its Connection.
     * @since 2.0
     */
    @objid ("df35ef10-aaa2-4e68-8250-508c3c1a6fe3")
    public void setVDistance(int distance) {
        this.vDistance = distance;
    }

    /**
     * Get the width constraint.
     * <p>
     * -1 means no constraint.
     * @return the width constraint.
     */
    @objid ("fd56bd88-b2a9-4809-a228-633443663d21")
    @Override
    public int getWidthConstraint() {
        return this.widthConstraint;
    }

    /**
     * Set the width constraint.
     * <p>
     * -1 means no constraint.
     * @param fixedWidth the width constraint.
     */
    @objid ("fc6b2254-5db2-42c7-89a4-c960f682f376")
    @Override
    public void setWidthConstraint(int fixedWidth) {
        this.widthConstraint = fixedWidth;
    }

    /**
     * Get the height constraint.
     * <p>
     * -1 means no constraint.
     * @return the height constraint.
     */
    @objid ("bdfe5240-7580-4f2e-9dc5-223d47615e76")
    @Override
    public int getHeightConstraint() {
        return this.heightConstraint;
    }

    /**
     * Set the height constraint.
     * <p>
     * -1 means no constraint.
     * @param fixedHeight the height constraint.
     */
    @objid ("280cc6a2-a9ed-4766-9f21-0d94b8363a2d")
    @Override
    public void setHeightConstraint(int fixedHeight) {
        this.heightConstraint = fixedHeight;
    }

    @objid ("3b7d189b-8307-42b3-8389-32fdf01b6529")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [end=");
        builder.append(this.end ? "target" : "source");
        builder.append(", u=");
        builder.append(this.uDistance);
        builder.append(", v=");
        builder.append(this.vDistance);
        builder.append(", widthConstraint=");
        builder.append(this.widthConstraint);
        builder.append(", heightConstraint=");
        builder.append(this.heightConstraint);
        builder.append("]");
        return builder.toString();
    }

}
