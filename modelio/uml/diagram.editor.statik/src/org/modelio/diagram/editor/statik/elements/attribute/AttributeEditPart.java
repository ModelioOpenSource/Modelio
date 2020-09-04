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

package org.modelio.diagram.editor.statik.elements.attribute;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.common.groupitem.GroupItemEditPart;
import org.modelio.diagram.elements.common.header.IHeaderFigure;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.ui.CoreFontRegistry;

/**
 * EditPart for {@link GmAttribute}.
 * <p>
 */
@objid ("33fe3eda-55b7-11e2-877f-002564c97630")
public class AttributeEditPart extends GroupItemEditPart {
    @objid ("33fe3ee4-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure fig, final IStyle style) {
        super.refreshFromStyle(fig, style);
        
        refreshStaticAbstract((IHeaderFigure) fig);
    }

    @objid ("33fe3ee1-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        // to refresh abstract state
        refreshStaticAbstract(getHeaderFigure(getFigure()));
    }

    @objid ("33fe3eed-55b7-11e2-877f-002564c97630")
    private void refreshStaticAbstract(final IHeaderFigure fig) {
        GmAttribute gm = (GmAttribute) getModel();
        Attribute att = gm.getRelatedElement();
        
        // underline static
        fig.setUnderline(att.isIsClass());
        
        if (att.isIsAbstract()) {
            // italic abstract
            fig.setTextFont(CoreFontRegistry.getModifiedFont(fig.getTextFont(), SWT.ITALIC, 1));
        } else {
            // restore default font from style
            fig.setTextFont(gm.getDisplayedStyle().getFont(gm.getStyleKey(MetaKey.FONT)));
        }
    }

}
