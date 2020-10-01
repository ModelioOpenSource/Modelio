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

package org.modelio.diagram.editor.handlers.print;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;

@objid ("65b47a81-33f7-11e2-95fe-001ec947c8cc")
public class PrintMargin {
// Margin to the left side, in pixels
    @objid ("65b47a82-33f7-11e2-95fe-001ec947c8cc")
    public int left;

//  Margins to the right side, in pixels
    @objid ("65b47a84-33f7-11e2-95fe-001ec947c8cc")
    public int right;

//  Margins to the top side, in pixels
    @objid ("65b47a86-33f7-11e2-95fe-001ec947c8cc")
    public int top;

//  Margins to the bottom side, in pixels
    @objid ("65b6dcd3-33f7-11e2-95fe-001ec947c8cc")
    public int bottom;

    @objid ("65b6dcd5-33f7-11e2-95fe-001ec947c8cc")
    private PrintMargin(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * Returns a PrintMargin object containing the true border margins for the
     * specified printer with the given margin in inches.
     * Note: all four sides share the same margin width.
     * @param printer
     * 
     * @param margin @return
     */
    @objid ("65b6dcdb-33f7-11e2-95fe-001ec947c8cc")
    static PrintMargin getPrintMargin(Printer printer, double margin) {
        return getPrintMargin(printer, margin, margin, margin, margin);
    }

    /**
     * Returns a PrintMargin object containing the true border margins for the
     * specified printer with the given margin width (in inches) for each side.
     */
    @objid ("65b6dce2-33f7-11e2-95fe-001ec947c8cc")
    static PrintMargin getPrintMargin(Printer printer, double marginLeft, double marginRight, double marginTop, double marginBottom) {
        Rectangle clientArea = printer.getClientArea();
        Rectangle trim = printer.computeTrim(0, 0, 0, 0);
        
        Point dpi = printer.getDPI();
        
        int leftMargin = (int) (marginLeft * dpi.x) - trim.x;
        int rightMargin =
          clientArea.width
            + trim.width
            - (int) (marginRight * dpi.x)
            - trim.x;
        int topMargin = (int) (marginTop * dpi.y) - trim.y;
        int bottomMargin =
          clientArea.height
            + trim.height
            - (int) (marginBottom * dpi.y)
            - trim.y;
        return new PrintMargin(
                                          leftMargin,
                                          rightMargin,
                                          topMargin,
                                          bottomMargin);
    }

    @objid ("65b6dcec-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public String toString() {
        return "Margin { "
                                          + this.left
                                          + ", "
                                          + this.right
                                          + "; "
                                          + this.top
                                          + ", "
                                          + this.bottom
                                          + " }";
    }

}
