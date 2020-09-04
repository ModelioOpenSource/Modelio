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

package org.modelio.diagram.editor.statik.elements.operation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.common.groupitem.GroupItemEditPart;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.header.IHeaderFigure;
import org.modelio.diagram.elements.core.figures.labelum.ZwspBreakWithIndentTextLayouter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.ui.CoreFontRegistry;

/**
 * EditPart for {@link GmOperation}.
 * <p>
 * Allows element import, package import, raised exception and dependency links.
 * 
 * @author cmarin
 */
@objid ("35fbe3ac-55b7-11e2-877f-002564c97630")
public class OperationEditPart extends GroupItemEditPart {
    @objid ("35fbe3b0-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        refreshFromStyle(getFigure(), getModelStyle());
    }

    @objid ("35fbe3b3-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure fig, final IStyle style) {
        super.refreshFromStyle(fig, style);
        
        refreshStaticAbstract((IHeaderFigure) fig);
        
        refreshWrapping((IHeaderFigure) fig, style);
    }

    @objid ("35fbe3bc-55b7-11e2-877f-002564c97630")
    private void refreshStaticAbstract(final IHeaderFigure fig) {
        GmOperation gm = (GmOperation) getModel();
        Operation el = gm.getRelatedElement();
        
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

    @objid ("40f07382-4af6-4b97-a0f6-2d3a06a33411")
    @Override
    protected boolean refreshWrapping(IHeaderFigure fig, IStyle style) {
        boolean changed = super.refreshWrapping(fig, style);
        
        boolean wrap = fig.isWrapped();
        if (wrap && !(fig.getMainLabelFigure().getTextLayouter() instanceof ZwspBreakWithIndentTextLayouter)) {
            fig.getMainLabelFigure().setTextLayouter(ZwspBreakWithIndentTextLayouter.INSTANCE);
        }
        return changed;
    }

    @objid ("9687f6ff-8198-4b85-84da-de59ce6572e3")
    @Override
    protected boolean isFlat(GmModelElementHeader gm) {
        return true;
    }

}
