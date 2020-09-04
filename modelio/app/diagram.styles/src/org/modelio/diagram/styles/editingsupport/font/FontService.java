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

package org.modelio.diagram.styles.editingsupport.font;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * This class computes a label for a given {@link Font} or {@link FontData}.
 */
@objid ("85a496d4-1926-11e2-92d2-001ec947c8cc")
public final class FontService {
    @objid ("85a496d6-1926-11e2-92d2-001ec947c8cc")
    public static String getFontLabel(Font font) {
        FontData[] fontDatas = font.getFontData();
        return getFontLabel(fontDatas);
    }

    @objid ("85a496db-1926-11e2-92d2-001ec947c8cc")
    private FontService() {
        // private constructor to forbid class instantiation
    }

    @objid ("e83333b2-b6fa-40ac-8267-537772520d6d")
    public static String getFontLabel(FontData[] fontDatas) {
        FontData fData = fontDatas[0];
        if (fData != null) {
            return getFontLabel(fData);
        } else {
            return "";
        }
    }

    @objid ("050c7477-7009-4d10-9426-92394311e281")
    public static String getFontLabel(FontData fData) {
        String fontDescription;
        int style = fData.getStyle();
        switch (style) {
        default:
        case SWT.NORMAL:
            fontDescription = DiagramStyles.I18N.getString("NORMAL_FONTSTYLE.label");
            break;
        case SWT.BOLD:
            fontDescription = DiagramStyles.I18N.getString("BOLD_FONTSTYLE.label");
            break;
        case SWT.ITALIC:
            fontDescription = DiagramStyles.I18N.getString("ITALIC_FONTSTYLE.label");
            break;
        case (SWT.BOLD + SWT.ITALIC):
            fontDescription = DiagramStyles.I18N.getString("BOLDITALIC_FONTSTYLE.label");
            break;
        }
        return fData.getName() + ", " + fontDescription + ", " + fData.getHeight();
    }

}
