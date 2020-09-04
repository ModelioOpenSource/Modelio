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

package org.modelio.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

/**
 * Global font registry.
 * <p>
 * Usage of this class is discouraged because it "leaks" Font resources.
 * <p>
 * You are encouraged to use {@link LocalFontRegistry} instead that will dispose resources automatically with a SWT widget.
 */
@objid ("2f28a6ed-186d-11e2-92d2-001ec947c8cc")
public class CoreFontRegistry {
    @objid ("aab6dc1b-3dcf-4576-856d-4cf35e603518")
    private static LocalFontRegistry globalReg;

    /**
     * Get a font given a device and font data which describes the desired font's appearance.
     * <p>
     * {@link #getFont(FontData[])} should better be used to be fully compatible on Unix.
     * 
     * @param fontdata describes the desired font (must not be null)
     * @return the matching font
     * @deprecated Consider using {@link LocalFontRegistry}, or call {@link #getGlobal()} than this same method on the returned registry.
     */
    @objid ("3bc99bad-186d-11e2-92d2-001ec947c8cc")
    @Deprecated
    public static Font getFont(FontData fontdata) {
        return getGlobal().getFont(fontdata);
    }

    /**
     * Get a font given an array of font data which describes the desired font's appearance.
     * 
     * @param fontdatas the array of FontData that describes the desired font (must not be null)
     * @return the matching font
     * @deprecated Consider using {@link LocalFontRegistry}, or call {@link #getGlobal()} than this same method on the returned registry.
     */
    @objid ("3bc99bae-186d-11e2-92d2-001ec947c8cc")
    @Deprecated
    public static Font getFont(final FontData[] fontdatas) {
        return getGlobal().getFont(fontdatas);
    }

    /**
     * Get the global font registry.
     * <p>
     * Usage of this class is discouraged because it "leaks" Font resources.
     * It should be used only to allocate fonts used in the whole application.
     * <p>
     * You are encouraged to use {@link LocalFontRegistry} instead
     * 
     * @return the global font registry.
     */
    @objid ("d73cb578-4ad7-4e06-99b5-a6174c383ceb")
    public static LocalFontRegistry getGlobal() {
        if (globalReg == null) {
            globalReg = new LocalFontRegistry(JFaceResources.getResources());
        }
        return globalReg;
    }

    /**
     * Get a font with the same appearance (font face) as the given one modified by
     * <ul>
     * <li>the given style flags added (use SWT.NONE for no flags change)</li>
     * <li>the font height scaled by 'scaleFactor' (use {@link UIFont} constants and {@link UIFont#NORMAL_SIZE} for no size change)</li>
     * </ul>
     * @deprecated This methods tends to "leak" Font resources .
     * You are encouraged to use: {@link LocalFontRegistry#from(Font)} that returns a font builder with fluent API.
     * Example:
     * <pre><code>
     * LocalFontRegistry res = LocalFontRegistry.create(aSwtControl); // or CoreFontRegistry.getGlobal()
     * Font newFont = res.builder(aControl.getFont()).addStyle(styleToAdd).scale(scaleFactor).build;
     * </code></pre>
     */
    @objid ("a2a65da6-9dd0-4d14-a74e-e4f7f4b4c310")
    @Deprecated
    public static Font getModifiedFont(final Font font, final int styleToAdd, float scaleFactor) {
        return getGlobal().builder(font).addStyle(styleToAdd).scale(scaleFactor).build();
    }

}
