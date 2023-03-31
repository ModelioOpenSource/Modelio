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
package org.modelio.linkeditor.handlers.print;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;

@objid ("2e05ad42-835e-4483-8a2d-228b3f4e0571")
public class PrintMargin {
     // // Margin to the left side, in pixels
    @objid ("cdd140a7-376b-4d1b-a395-4b828a25e405")
    public int left;

     // //  Margins to the right side, in pixels
    @objid ("5abd9edf-25a2-422a-8214-e9662a6e85c6")
    public int right;

     // //  Margins to the top side, in pixels
    @objid ("35d49bb4-f4eb-48ad-928e-e223f6516dc6")
    public int top;

     // //  Margins to the bottom side, in pixels
    @objid ("245369c7-6bff-45ff-bc93-6c38ac676753")
    public int bottom;

    @objid ("3fd1d612-2fb9-4210-a145-07b5f37a254d")
    private  PrintMargin(int left, int right, int top, int bottom) {
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
     * @param margin @return
     */
    @objid ("e48a9c21-5693-411c-886e-d4a39fb04334")
    static PrintMargin getPrintMargin(Printer printer, double margin) {
        return getPrintMargin(printer, margin, margin, margin, margin);
    }

    /**
     * Returns a PrintMargin object containing the true border margins for the
     * specified printer with the given margin width (in inches) for each side.
     */
    @objid ("f5224b65-e954-48f9-8c9f-9ae5c0e808d8")
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

    @objid ("9082cfa4-028e-4d27-a64b-22d5421bf328")
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
