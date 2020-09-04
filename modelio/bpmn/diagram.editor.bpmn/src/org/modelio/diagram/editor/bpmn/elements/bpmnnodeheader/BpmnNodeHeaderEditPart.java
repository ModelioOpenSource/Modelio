/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnnodeheader;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.header.IHeaderFigure;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.header.WrappedHeaderFigure;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;

/**
 * Edit part of classifier header.
 */
@objid ("617aa4da-55b6-11e2-877f-002564c97630")
public class BpmnNodeHeaderEditPart extends ModelElementHeaderEditPart {
    @objid ("617aa4de-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("617aa4e3-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        WrappedHeaderFigure headerFigure = new WrappedHeaderFigure();
        
        refreshFromStyle(headerFigure, getModelStyle());
        return headerFigure;
    }

    @objid ("617c2b7a-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshMetaclassIcon(final IHeaderFigure headerFigure, final GmModelElementHeader gm, ShowStereotypeMode mode) {
        // The redefinition of the icons should not be processed here (it is a design mistake)
        // Instead the getMetaClassIcon() on the GM should have been redefined. Unfortunately getMetaClassIcon() returns only one image (not a list)...
        // Requires further investigation
        
        List<Image> images = ((GmBpmnNodeHeader) gm).getHeaderImage();
        if (images != null && images.size() > 0) {
            headerFigure.setLeftIcons(images);
        } else {
            super.refreshMetaclassIcon(headerFigure, gm, mode);
        }
    }

}
