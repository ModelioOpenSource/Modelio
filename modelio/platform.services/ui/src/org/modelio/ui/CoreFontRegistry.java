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

package org.modelio.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

/**
 * Global font registry.
 * <p>
 * Usage of this class is discouraged because it "leaks" Font resources.
 * <p>
 * You are encouraged to instantiate {@link LocalResourceManager} using
 * {@link LocalResourceManager#LocalResourceManager(org.eclipse.jface.resource.ResourceManager, org.eclipse.swt.widgets.Control)
 * new LocalResourceManager(JFaceResources.getResources(), aSwtControl)} that will dispose resources automatically with the SWT widget.
 * 
 * <pre><code>
 * LocalResourceManager res = new LocalResourceManager(JFaceResources.getResources(), aControl);
 * res.createFont(FontDescriptor.createFrom(aControl.getFont()).withStyle(SWT.BOLD));
 * </code></pre>
 */
@objid ("2f28a6ed-186d-11e2-92d2-001ec947c8cc")
public class CoreFontRegistry {
    /**
     * Get a font given a device and font data which describes the desired font's appearance.
     * <p>
     * {@link #getFont(FontData[])} should better be used to be fully compatible on Unix.
     * @param fontdata describes the desired font (must not be null)
     * @return the matching font
     */
    @objid ("3bc99bad-186d-11e2-92d2-001ec947c8cc")
    public static Font getFont(FontData fontdata) {
        return (Font) JFaceResources.getResources().get(FontDescriptor.createFrom(fontdata));
    }

    /**
     * Get a font given an array of font data which describes the desired font's appearance.
     * @param fontdatas the array of FontData that describes the desired font (must not be null)
     * @return the matching font
     */
    @objid ("3bc99bae-186d-11e2-92d2-001ec947c8cc")
    public static Font getFont(final FontData[] fontdatas) {
        return (Font) JFaceResources.getResources().get(FontDescriptor.createFrom(fontdatas));
    }

    /**
     * Get a font with the same appearance (font face) as the given one modified by
     * <ul>
     * <li>the given style flags added (use SWT.NONE for no flags change)</li>
     * <li>the font height scaled by 'scaleFactor' (use {@link UIFont} constants and {@link UIFont#NORMAL_SIZE} for no size change)</li>
     * </ul>
     * @deprecated This methods tends to "leak" Font resources .
     * You are encouraged to use: <ul>
     * <li> {@link FontDescriptor#createFrom(Font)}
     * <li> {@link FontDescriptor#increaseHeight(int)} or {@link FontDescriptor#setHeight(int)}
     * <li> {@link LocalResourceManager#createFont(FontDescriptor)} and {@link FontDescriptor#withStyle(int)}
     * </ul>
     * Example:
     * <pre><code>
     * LocalResourceManager res = new LocalResourceManager(JFaceResources.getResources(), aControl);
     * res.createFont(FontDescriptor.createFrom(aControl.getFont()).withStyle(SWT.BOLD));
     * </code></pre>
     */
    @objid ("a2a65da6-9dd0-4d14-a74e-e4f7f4b4c310")
    @Deprecated
    public static Font getModifiedFont(final Font font, final int styleToAdd, float scaleFactor) {
        FontData[] fontdatas = font.getFontData();
        modify(fontdatas, styleToAdd, scaleFactor);
        return getFont(fontdatas);
    }

    /**
     * Modify the given font datas by :
     * <ul>
     * <li>adding the given style flags (use SWT.NONE for no flags change)</li>
     * <li>scaling the font height by 'scaleFactor' (use {@link UIFont} constants and {@link UIFont#NORMAL_SIZE} for no size change)</li>
     * </ul>
     */
    @objid ("182ba202-35e6-40a5-9c9c-7f25bbd657b6")
    private static void modify(final FontData[] fontdatas, final int styleToAdd, float scaleFactor) {
        for (FontData d : fontdatas) {
            d.setStyle(d.getStyle() | styleToAdd);
            d.setHeight((int) (d.getHeight() * scaleFactor));
        }
    }

}
