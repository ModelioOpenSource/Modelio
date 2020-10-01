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

package org.modelio.diagram.elements.common.embeddeddiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * {@link FreeformViewport} for embedded diagrams.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("2530ce9c-6be2-4444-93a2-9e748669da9a")
public class EmbeddedDiagramViewport extends FreeformViewport {
    /**
     * Redefined to :<ul>
     * <li> exclude (0,0) from extents
     * <li> update range model only if needed
     * </ul>
     * 
     * {@inheritDoc}
     */
    @objid ("bbd71e26-d25e-4ab1-a2ad-90eedbb8b760")
    @Override
    protected void readjustScrollBars() {
        IFigure contentFig = getContents();
        if (contentFig == null) {
            return;
        }
        if (!(contentFig instanceof FreeformFigure)) {
            return;
        }
        
        FreeformFigure freeformFig = (FreeformFigure) contentFig;
        Rectangle clientArea = getClientArea();
        Rectangle oldBounds = freeformFig.getBounds().getCopy();
        Rectangle newBounds = freeformFig.getFreeformExtent().getCopy();
        newBounds.union(newBounds.x, newBounds.y, clientArea.width, clientArea.height);
        
        // Freeform bounds must always be updated even if same because zoom change
        // don't update root layer but does change scaled layers bounds.
        freeformFig.setFreeformBounds(newBounds);
        
        if (!newBounds.equals(oldBounds)) {
            getVerticalRangeModel().setAll(newBounds.y, clientArea.height, newBounds.bottom());
            getHorizontalRangeModel().setAll(newBounds.x, clientArea.width, newBounds.right());
        }
    }

    @objid ("c4320d69-3bf6-47d2-91a1-c418f8c19b6f")
    @Override
    protected boolean isValidationRoot() {
        return true;
    }

    @objid ("6c037ae4-606c-4ef1-aa75-38a2cbc3b7e0")
    @Override
    public String toString() {
        return String.format("%s [bounds=%s, parent=%s]", getClass().getSimpleName(), getBounds(), getParent());
    }

}
