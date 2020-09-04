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

package org.modelio.diagram.editor.statik.elements.namespaceheader;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.ui.CoreFontRegistry;

/**
 * Edit part of classifier header.
 */
@objid ("359d48e9-55b7-11e2-877f-002564c97630")
public class NamespaceHeaderEditPart extends ModelElementHeaderEditPart {
    @objid ("359d48ed-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure fig, final IStyle style) {
        super.refreshFromStyle(fig, style);
        
        GmNamespaceHeader model = (GmNamespaceHeader) getModel();
        if (model.isAbstract()) {
            IPenOptionsSupport headerFigure = (IPenOptionsSupport) fig;
            headerFigure.setTextFont(CoreFontRegistry.getModifiedFont(headerFigure.getTextFont(), SWT.ITALIC, 1));
        }
    }

}
