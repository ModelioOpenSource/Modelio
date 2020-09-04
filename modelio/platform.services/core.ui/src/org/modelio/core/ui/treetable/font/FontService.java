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

package org.modelio.core.ui.treetable.font;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.modelio.core.ui.plugin.CoreUi;

/**
 * This class computes a label for a given Font.
 */
@objid ("6b396e03-1eba-11e2-9382-bc305ba4815c")
public final class FontService {
    @objid ("6b399511-1eba-11e2-9382-bc305ba4815c")
    public static String getFontLabel(org.eclipse.swt.graphics.Font font) {
        String fontDescription = "";
        FontData fData = font.getFontData()[0];
        if (fData != null) {
            int style = fData.getStyle();
            switch (style) {
                case SWT.NORMAL:
                    fontDescription = CoreUi.I18N.getString("TreeTable.NORMAL_FONTSTYLE.label");
                    break;
                case SWT.BOLD:
                    fontDescription = CoreUi.I18N.getString("TreeTable.BOLD_FONTSTYLE.label"); 
                    break;
                case SWT.ITALIC:
                    fontDescription = CoreUi.I18N.getString("TreeTable.ITALIC_FONTSTYLE.label"); 
                    break;
                case (SWT.BOLD + SWT.ITALIC):
                    fontDescription = CoreUi.I18N.getString("TreeTable.BOLDITALIC_FONTSTYLE.label");
                    break;
            }
        
            return fData.getName() + ", " + fontDescription + ", " + fData.getHeight();
        } else
            return "";
    }

    @objid ("6b39bc20-1eba-11e2-9382-bc305ba4815c")
    private FontService() {
        // private constructor to forbid class instantiation
    }

}
