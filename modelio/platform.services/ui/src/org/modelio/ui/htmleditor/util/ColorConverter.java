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

package org.modelio.ui.htmleditor.util;

import java.awt.Color;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.RGB;

/**
 * Utility class for converting RGB colors to hex-colors.
 * 
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
@objid ("4feeb2be-bfdc-4905-a64a-27a5232b6a95")
public final class ColorConverter {
    @objid ("715a4d9c-3d3a-46ef-9eba-4d50d0b5c037")
    public static String convertRgbToHex(RGB rgb) {
        return new StringBuffer(toHex(rgb.red)).append(toHex(rgb.green)).append(toHex(rgb.blue)).toString();
    }

    @objid ("ca3ccf08-9b6c-47d1-9ad6-943816067f9a")
    private static String toHex(int color) {
        return new String(new char[] { "0123456789ABCDEF".charAt((color - color % 16) / 16), //$NON-NLS-1$
                                        "0123456789ABCDEF".charAt(color % 16) }, 0, 2); //$NON-NLS-1$
    }

    @objid ("a047c7ed-cb13-40bf-b446-d0b9f02492f6")
    public static RGB convertHexToRgb(String hex) {
        final Color color = Color.decode(hex.charAt(0) == '#' ? hex : "#" + hex); //$NON-NLS-1$
        return new RGB(color.getRed(), color.getGreen(), color.getBlue());
    }

}
