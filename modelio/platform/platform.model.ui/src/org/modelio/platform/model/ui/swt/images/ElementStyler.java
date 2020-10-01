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

package org.modelio.platform.model.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.platform.ui.UIColor;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * Default {@link Styler} implementation for {@link MObject}.
 * <p>
 * Set the foreground color for the given element according to those rules:
 * <ul>
 * <li>Modifiable model elements font color is {@link UIColor#MODIFIABLE_ELEMENT_FG}.</li>
 * <li>Non-modifiable model component elements font color is  {@link UIColor#NONMODIFIABLE_ELEMENT_FG}.</li>
 * <li>Incomplete model elements font color is {@link UIColor#SHELL_ELEMENT_FG}.</li>
 * <li>Ramc model elements font color is {@link UIColor#RAMC_ELEMENT_FG}.</li>
 * </ul>
 */
@objid ("cc2ea6eb-1fd3-4577-95b1-4f0b88b8b6fe")
public class ElementStyler extends Styler {
    @objid ("5c34aac3-907e-4714-979d-0691521651ca")
    private final boolean shellVariant;

    @objid ("932a31a9-cfa4-49ca-85c4-e434ab570c7a")
    private final boolean ramcVariant;

    @objid ("48b49845-ebdf-49e4-b4be-232d715330c5")
    private final boolean isModifiable;

    @objid ("ec5f5315-79fb-4776-a27a-046e7903a8fa")
    private static final Font normalFont = CoreFontRegistry.getFont(Display.getCurrent().getSystemFont().getFontData());

    @objid ("298c9994-a195-4fcc-990c-99c8a1c9c086")
    private static final Font italicFont = CoreFontRegistry.getModifiedFont(normalFont, SWT.ITALIC, 1.0f);

    @objid ("24c14b30-5f2a-48b4-8957-fa958b75dcee")
    public static final Styler NORMAL = new Styler() {
        @Override
        public void applyStyles(TextStyle textStyle) {
            //right now, nothing to do
        }
    };

    @objid ("34d9373e-9cd7-48ad-a552-adcfd863a0b6")
    private final MObject element;

    @objid ("71ed7d93-539f-4397-b0cb-9a7bb2faf18f")
    public static Styler getStyler(MObject e) {
        return new ElementStyler(e, false, false, true);
    }

    @objid ("fb1e4e37-8640-419f-b6ac-4441430f16a7")
    public static Styler getStyler(MObject element, MObject subElement) {
        if (subElement == null) {
            return new ElementStyler(element, true, false, true);
        } else {
            return new ElementStyler(element, subElement.isShell(), subElement.getStatus().isRamc(), subElement.isModifiable());
        }
    }

    @objid ("c2722cb1-2a1b-4733-8149-249f55dccb28")
    @Override
    public void applyStyles(TextStyle textStyle) {
        if (this.element == null) {
            return;
        }
        
        textStyle.foreground = getForeground(this.element);
        textStyle.background = getBackground(this.element);
        textStyle.underline = isUnderlined(this.element);
        textStyle.underlineColor = textStyle.foreground;
        textStyle.font = isItalic(this.element) ? italicFont : null;
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
    @objid ("b39b363e-d878-4ff1-a17c-b8efd05263ea")
    private Color getForeground(MObject e) {
        try {
            MStatus status = e.getStatus();
            
            if (status.isShell() || this.shellVariant) {
                return UIColor.SHELL_ELEMENT_FG;
            } else if (status.isRamc() || this.ramcVariant) {
                return UIColor.RAMC_ELEMENT_FG;
            } else if (this.isModifiable && status.isModifiable()) {
                return UIColor.MODIFIABLE_ELEMENT_FG;
            } else {
                return UIColor.NONMODIFIABLE_ELEMENT_FG;
            }
        } catch (DeadObjectException ex) {
            return UIColor.SHELL_ELEMENT_FG;
        }
    }

    /**
     * Get the background color for the given element in the given state.
     * 
     * @param e the element
     * @return its background color
     */
    @objid ("baa543e9-dcb0-4e48-8fd5-d50876836c1d")
    private Color getBackground(MObject e) {
        return null;
    }

    @objid ("56363527-0036-4509-bf10-1dfe1572aa66")
    private boolean isItalic(MObject e) {
        if (e.isShell()) {
            return false;
        }
        if (e instanceof Interface) {
            return true;
        }
        if (e instanceof Classifier) {
            return ((Classifier) e).isIsAbstract();
        }
        if (e instanceof Feature) {
            return ((Feature) e).isIsAbstract();
        }
        return false;
    }

    @objid ("4670601d-82eb-4118-b568-be4cd634bcb1")
    private boolean isUnderlined(MObject e) {
        if (e.isShell()) {
            return false;
        }
        
        if (e instanceof Feature) {
            return ((Feature) e).isIsClass();
        }
        return false;
    }

    @objid ("30a7077e-73a7-4fa8-bad9-b048b0ba02ff")
    private ElementStyler(MObject e, boolean shellVariant, boolean ramcVariant, boolean isModifiable) {
        this.element = e;
        this.shellVariant = shellVariant;
        this.ramcVariant = ramcVariant;
        this.isModifiable = isModifiable;
    }

}
