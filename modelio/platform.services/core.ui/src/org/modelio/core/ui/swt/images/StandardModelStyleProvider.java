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

package org.modelio.core.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.ui.UIColor;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

@objid ("955488d4-175a-4616-826b-7673dd2315b3")
public class StandardModelStyleProvider {
    @objid ("be65f290-f867-4e6f-a6c3-24df7b5c7190")
    private static final Font normalFont = CoreFontRegistry.getFont(Display.getCurrent().getSystemFont().getFontData());

    @objid ("b3757650-605f-46a0-a54f-20caf3f5afe5")
    private static final Font italicFont = CoreFontRegistry.getModifiedFont(normalFont, SWT.ITALIC, 1.0f);

    @objid ("d5e6e4a0-4fa2-476f-ae94-ce8f1db04123")
    public static StyleRange[] getStyleRanges(Object obj, String label) {
        if (obj == null) {
            return null;
        }
        
        final StyleRange[] styles = new StyleRange[1];
        final StyleRange style = new StyleRange(0, label.length(), getForeground(obj), getBackground(obj));
        style.underline = isUnderlined(obj);
        style.underlineColor = style.foreground;
        style.font = isItalic(obj) ? italicFont : null;
        styles[0] = style;
        return styles;
    }

    @objid ("84eda5a0-21a3-4848-ac38-7872e29f079d")
    private static boolean isUnderlined(Object obj) {
        if (obj instanceof MObject) {
            MObject mObject = (MObject) obj;
            if (mObject.isShell()) {
                return false;
            }
        }
        if (obj instanceof Feature) {
            return ((Feature) obj).isIsClass();
        }
        return false;
    }

    /**
     * Return the foreground color for the given element according to those rules:
     * <ul>
     * <li>Modifiable model elements font color is black #000000.</li>
     * <li>Non-modifiable model component elements font color is dark grey #606060.</li>
     * <li>Incomplete model elements font color is light red #FF8080.</li>
     * <li>Ramc model elements font color is modified yellow #A0A000.</li>
     * </ul>
     * 
     * @return a Color.
     */
    @objid ("5641b7d4-d899-4ef4-9047-ad21c7945df1")
    private static Color getForeground(Object obj) {
        if (obj instanceof MObject) {
            try {
                MObject element = (MObject) obj;
                MStatus status = element.getStatus();
                if (status.isModifiable()) {
                    return UIColor.MODIFIABLE_ELEMENT_FG;
                } else if (status.isRamc()) {
                    return UIColor.RAMC_ELEMENT_FG;
                } else if (status.isShell()) {
                    return UIColor.SHELL_ELEMENT_FG;
                } else {
                    return UIColor.NONMODIFIABLE_ELEMENT_FG;
                }
            } catch (DeadObjectException ex) {
                return UIColor.SHELL_ELEMENT_FG;
            }
        }
        return UIColor.BLACK;
    }

    /**
     * Get the background color for the given element in the given state
     * @param element
     * the element
     * @param status
     * the element state
     * 
     * @return its background color
     */
    @objid ("f4fc4706-0bd2-460c-be32-877bfaeb5c5c")
    private static Color getBackground(@SuppressWarnings("unused") Object obj) {
        return null;
    }

    @objid ("946e8b2c-8cb3-4f97-9a10-e50b24cba7a0")
    private static boolean isItalic(Object obj) {
        if (obj instanceof MObject) {
            if (((MObject) obj).isShell()) {
                return false;
            }
        }
        if (obj instanceof Interface) {
            return true;
        }
        if (obj instanceof Classifier) {
            return ((Classifier) obj).isIsAbstract();
        }
        if (obj instanceof Feature) {
            return ((Feature) obj).isIsAbstract();
        }
        if (obj instanceof MClass) {
            return ((MClass) obj).isAbstract();
        }
        return false;
    }

}
