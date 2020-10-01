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

package org.modelio.diagram.editor.gmdbg;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.anchors.GmRaySlidableAnchor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;

@objid ("0bcfcdbc-a7f8-455c-ac4b-74ceec3b1db6")
class Formatter {
    @objid ("c8cd4800-0fab-4312-9771-5b972c3e9d08")
    public static String toString(Object o) {
        if (o == null) {
            return "<null>";
        }
        
        if (o instanceof Rectangle) {
            return convertRectangle((Rectangle) o);
        } else if (o instanceof GmPath) {
            return convertGmPath((IGmPath) o);
        } else if (o instanceof GmRaySlidableAnchor) {
            return convertRaySlidableAnchor((GmRaySlidableAnchor) o);
        }
        return o.toString();
    }

    @objid ("22a4735b-b7a0-4456-a2b3-913766b1c2ab")
    private static String convertRectangle(Rectangle r) {
        return r.toString();
    }

    @objid ("5ec995f8-7a2a-4d4b-b818-f23295316764")
    private static String convertIGmObject(IGmObject gm) {
        String s1 = gm.getClass().getSimpleName();
        String s2 = "";
        if (gm instanceof GmModel) {
            GmModel m = (GmModel) gm;
            if (m.getRepresentedElement() != null) {
                s2 = " '" + m.getRepresentedElement().getName() + "'";
            }
        }
        return s1 + s2;
    }

    @objid ("4d125254-2996-4da0-8fe3-ae79106886eb")
    @SuppressWarnings ("unchecked")
    private static String convertGmPath(IGmPath p) {
        StringBuilder sb = new StringBuilder();
        sb.append(Formatter.toString(p.getSourceAnchor()));
        sb.append(", ");
        
        for (Point pt : (List<Point>) p.getPathData()) {
            sb.append(String.format("(%d, %d), ", pt.x, pt.y));
        }
        
        sb.append(Formatter.toString(p.getTargetAnchor()));
        return sb.toString();
    }

    @objid ("c5195cf9-5f6b-4800-9d3c-b060886d9a0d")
    private static String convertRaySlidableAnchor(GmRaySlidableAnchor a) {
        Dimension d = a.getDifference();
        return String.format("A(%d, %d)", d.width, d.height);
    }

}
