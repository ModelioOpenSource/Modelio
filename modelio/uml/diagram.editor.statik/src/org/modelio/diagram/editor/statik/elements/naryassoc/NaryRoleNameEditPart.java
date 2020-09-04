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

package org.modelio.diagram.editor.statik.elements.naryassoc;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.common.header.WrappedHeaderFigure;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.ui.CoreFontRegistry;

/**
 * Edit part for {@link GmNaryRoleNameLabel}.
 */
@objid ("0c7e62bf-7a05-4eab-8cd8-8c918a64e380")
public class NaryRoleNameEditPart extends ModelElementLabelEditPart {
    @objid ("98f55c5a-7210-49bd-a46a-403fbc540e74")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        refreshFromStyle(getFigure(), getModelStyle());
    }

    @objid ("20a2dbd3-a336-42c6-a217-714a4da034cf")
    @Override
    protected void refreshFromStyle(final IFigure fig, final IStyle style) {
        super.refreshFromStyle(fig, style);
        refreshStaticAbstract((WrappedHeaderFigure) fig);
    }

    @objid ("7b4a2e76-5ce7-421f-9284-1f06fad9bfa0")
    private void refreshStaticAbstract(final WrappedHeaderFigure fig) {
        GmNaryRoleNameLabel gm = (GmNaryRoleNameLabel) getModel();
        NaryAssociationEnd el = (NaryAssociationEnd) gm.getRelatedElement();
        
        // underline static
        fig.setUnderline(el.isIsClass());
        
        if (el.isIsAbstract()) {
            // italic abstract
            fig.setTextFont(CoreFontRegistry.getModifiedFont(fig.getTextFont(), SWT.ITALIC, 1));
        } else {
            // restore default font from style
            fig.setTextFont(gm.getDisplayedStyle().getFont(gm.getStyleKey(MetaKey.FONT)));
        }
    }

}
