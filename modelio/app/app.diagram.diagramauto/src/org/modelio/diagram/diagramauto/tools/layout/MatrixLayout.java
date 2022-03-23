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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.diagramauto.tools.DgUtils;

@objid ("a9d4f391-9c3a-43f4-aef7-767c9304eba4")
public class MatrixLayout {
    @objid ("d1b89ddb-e677-476d-991c-26755809fec3")
    private int deltaX = 20;

    @objid ("e851455e-900e-4811-b360-eaa5eaa529b3")
    private int deltaY = 20;

    @objid ("eaa8bad6-1c13-448d-8073-b7b61fcbb391")
    private int x0 = 0;

    @objid ("a744449a-4f6a-4ad6-b17a-15f1e0100179")
    private int y0 = 0;

    @objid ("915cf46d-37bd-4348-81ae-7260ae60ceba")
    public  MatrixLayout() {
        
    }

    @objid ("3fcb58dc-f780-4daf-a71b-23be9e1852c0")
    public  MatrixLayout(int x0, int y0) {
        this.x0 = x0;
        this.y0 = y0;
        
    }

    @objid ("05e3851e-806b-4d95-ad94-5c9a487b3027")
    public  MatrixLayout(int x0, int y0, int dx, int dy) {
        this.x0 = x0;
        this.y0 = y0;
        this.deltaX = dx;
        this.deltaY = dy;
        
    }

    /**
     * Layout the nodes as a matrix. Nodes are first 'fit to contents' then resize to the size of the bigger found node.
     */
    @objid ("ef50cf4f-f95e-448f-89db-45482eee8f8a")
    public void layout(final IDiagramHandle dh, final List<IDiagramNode> contentDgs) {
        // Fit all nodes to their contents size.
        for (IDiagramNode dg : contentDgs) {
            dg.fitToContent();
        }
        
        // Compute the matrix geometry
        Point[] geo = getMatrixGeometry(contentDgs);
        int nrows = geo[0].x;
        int ncols = geo[0].y;
        int cellWidth = geo[1].x;
        int cellHeight = geo[1].y;
        
        // Place the elements in the matrix
        layoutElements(contentDgs, cellWidth, cellHeight, nrows, ncols);
        dh.save();
        
    }

    /**
     * Compute the matrix bounds without layouting the nodes. Preferably use after a layout() to ensure predictible results.
     * @return
     */
    @objid ("7ccad4e3-2b77-4359-952f-b9765f0670d6")
    public Rectangle getMatrixBounds(List<IDiagramNode> contentDgs) {
        // Compute the matrix geometry
        Point[] geo = getMatrixGeometry(contentDgs);
        int nrows = geo[0].x;
        int ncols = geo[0].y;
        int cellWidth = geo[1].x;
        int cellHeight = geo[1].y;
        return new Rectangle(this.x0, this.y0, ncols * (cellWidth + this.deltaX), nrows * (cellHeight + this.deltaY));
    }

    /**
     * Compute the matrix gemotry based on the contained node number and size.
     * @return Two int pairs. The Point class is used to represent int pairs. First returned Point is (nrows, ncols), the second returned point is (cellWidht, cellHeight).
     */
    @objid ("84f40b8f-01e1-40e7-8a09-02fa98fcb617")
    private Point[] getMatrixGeometry(List<IDiagramNode> contentDgs) {
        Point[] geo = new Point[2];
        
        int n = contentDgs.size();
        int ncols = n != 0 ? (int) Math.ceil(Math.sqrt(n * 16.0 / 9.0)) : 0;
        int nrows = n != 0 ? n / ncols : 0;
        
        int maxWidth = 0;
        int maxHeight = 0;
        // compute max height and width of the elements to layout.
        for (IDiagramNode dg : contentDgs) {
            Rectangle r = dg.getOverallBounds();
            maxWidth = Math.max(maxWidth, r.width);
            maxHeight = Math.max(maxHeight, r.height);
        }
        
        geo[0] = new Point(nrows, ncols);
        geo[1] = new Point(maxWidth, maxHeight);
        return geo;
    }

    /**
     * Sets locations and size of all dgs, aligned on a nrows, ncols matrix The center of each dg is part of the circle itself.
     * @param dgs the graphics to layout.
     */
    @objid ("e6cf7e87-3225-48e7-9413-96501700a52f")
    protected Rectangle layoutElements(final List<IDiagramNode> dgs, int w, int h, int nrows, int ncols) {
        int curRow = 0;
        int curCol = 0;
        
        for (IDiagramNode dg : dgs) {
            dg.setSize(w, h);
            DgUtils.setLocation(dg, this.x0 + curCol * (w + this.deltaX), this.y0 + curRow * (h + this.deltaY));
            curCol++;
            if (curCol >= ncols) {
                curCol = 0;
                curRow++;
            }
        }
        return new Rectangle(this.x0, this.y0, ncols * (w + this.deltaX), nrows * (h + this.deltaY));
    }

}
