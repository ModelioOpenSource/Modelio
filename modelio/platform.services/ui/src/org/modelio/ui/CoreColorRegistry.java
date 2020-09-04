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
import org.eclipse.jface.preference.JFacePreferences;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

@objid ("34bf29dd-186d-11e2-92d2-001ec947c8cc")
public class CoreColorRegistry {
    @objid ("26ee07a5-186f-11e2-92d2-001ec947c8cc")
    private static ColorRegistry colors = null;

    @objid ("26ee07a6-186f-11e2-92d2-001ec947c8cc")
    public static Color getColor(final RGB rgb) {
        if (colors == null) {
            Display.getDefault().syncExec(new Runnable() {
                @Override
                public void run() {
                    colors = new ColorRegistry();
                }
            });
        }
        
        final String key = rgb.toString();
        if (colors.get(key) == null) {
            colors.put(key, rgb);
            return colors.get(key);
        }
        return colors.get(key);
    }

    /**
     * Returned a color derived from the given color.
     * <p>
     * The returned color depends on the factor : <ul>
     * <li> 0 : black
     * <li> 1 : unchanged
     * <li> 2 : white
     * <li> darker for factor 0 <= f <= 1.0
     * <li> and brighter for factor 1.0 >= f >= 2.
     * </ul>
     * 
     * @param color the original color
     * @param factor a factor between 0 and 2.
     * @return a color lighter or darker
     */
    @objid ("dbc59984-83cd-45a9-a8c7-1404d5b64490")
    public static Color getDerivedColor(final Color color, final float factor) {
        final RGB rgb = color.getRGB();
        
        if (factor < 0 || factor > 2) {
            throw new IllegalArgumentException(String.valueOf(factor));
        } if (factor > 1) {
            rgb.red += (255.0 - rgb.red) * (1.0 - factor);
            rgb.green += (255.0 - rgb.green) * (1.0 - factor);
            rgb.blue += (255.0 - rgb.blue) * (1.0 - factor);
        } else {
            rgb.red *= factor;
            rgb.green *= factor;
            rgb.blue *= factor;
        }
        
        //RGB resRGB = new RGB(Math.min(255, (int) (color.getRed() * factor)), Math.min(255, (int) (color.getGreen() * factor)), Math.min(255, (int) (color.getBlue() * factor)));
        final Color resColor = getColor(rgb);
        return resColor;
    }

    /**
     * Get a color between 2 given colors.
     * <p>
     * The returned color depends on the factor : <ul>
     * <li> 0 : the source color
     * <li> 1 : the target color
     * <li> 0 < f < 1.0 : an intermediate color
     * </ul>
     * 
     * @param color the source color
     * @param target the target color
     * @param factor a float between 0 and 1.
     * @return the intermediate color.
     */
    @objid ("4e014cf8-fe9b-4883-a063-6272ea1cd9e9")
    public static Color getIntermediateColor(final Color color, final RGB target, final float factor) {
        if (factor < 0 || factor > 1) {
            throw new IllegalArgumentException(String.valueOf(factor));
        } else {
            final RGB rgb = color.getRGB();
        
            rgb.red += (target.red - rgb.red) * factor;
            rgb.green += (target.green - rgb.green) * factor;
            rgb.blue += (target.blue - rgb.blue) * factor;
        
            final Color resColor = getColor(rgb);
            return resColor;
        }
    }


static {
        final ColorRegistry colorRegistry = JFaceResources.getColorRegistry();

        if (colorRegistry.get(JFacePreferences.ERROR_COLOR) == null) {
            colorRegistry.put(JFacePreferences.ERROR_COLOR, UIColor.RED.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.HYPERLINK_COLOR) == null) {
            colorRegistry.put(JFacePreferences.HYPERLINK_COLOR, UIColor.SWT_LINK_FOREGROUND.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.ACTIVE_HYPERLINK_COLOR) == null) {
            colorRegistry.put(JFacePreferences.ACTIVE_HYPERLINK_COLOR, UIColor.SWT_LINK_FOREGROUND.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.QUALIFIER_COLOR) == null) {
            colorRegistry.put(JFacePreferences.QUALIFIER_COLOR, UIColor.SWT_WIDGET_FOREGROUND.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.DECORATIONS_COLOR) == null) {
            colorRegistry.put(JFacePreferences.DECORATIONS_COLOR, UIColor.SWT_WIDGET_FOREGROUND.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.COUNTER_COLOR) == null) {
            colorRegistry.put(JFacePreferences.COUNTER_COLOR, UIColor.SWT_WIDGET_FOREGROUND.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.CONTENT_ASSIST_BACKGROUND_COLOR) == null) {
            colorRegistry.put(JFacePreferences.CONTENT_ASSIST_BACKGROUND_COLOR, UIColor.SWT_INFO_BACKGROUND.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.CONTENT_ASSIST_FOREGROUND_COLOR) == null) {
            colorRegistry.put(JFacePreferences.CONTENT_ASSIST_FOREGROUND_COLOR, UIColor.SWT_INFO_FOREGROUND.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.INFORMATION_BACKGROUND_COLOR) == null) {
            colorRegistry.put(JFacePreferences.INFORMATION_BACKGROUND_COLOR, UIColor.SWT_INFO_BACKGROUND.getRGB());
        }

        if (colorRegistry.get(JFacePreferences.INFORMATION_FOREGROUND_COLOR) == null) {
            colorRegistry.put(JFacePreferences.INFORMATION_FOREGROUND_COLOR, UIColor.SWT_INFO_FOREGROUND.getRGB());
        }

    }
}
