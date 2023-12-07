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
package org.modelio.platform.ui.dialog;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIFont;

@objid ("da3957e9-0e5b-4d6a-9a12-68912b1ee799")
public class PolluxWidgetConfigurator {
    /**
     * Vertical spacing between control, label and caption widgets of a same field.
     */
    @objid ("07395e70-fdf5-442e-9b45-8a10269dab51")
    public static final int VERTICAL_SPACING = 1;

    /**
     * Vertical spacing between 2 form fields.
     */
    @objid ("cd8cd459-536f-4947-9b1f-125084fb6078")
    public static final int FIELD_VERTICAL_SPACING = 5;

    /**
     * Prepare a SWT Composite to be the container of Pollux-like simple fields:
     * <ul>
     * <li>set the layout to a one column grid layout</li>
     * <li>set the background color (white)</li>
     * <li>set the vertical spacing</li>
     * </ul>
     * @param composite the composite to configure, cannot be <code>null<code>
     */
    @objid ("2cf5641b-87ed-4bbc-8585-6f1fbdea8044")
    public static void configureContainer(Composite composite) {
        if (composite == null) {
            throw new IllegalArgumentException("'control' cannot be null.");
        }
        GridLayout gridLayout = new GridLayout();
        gridLayout.verticalSpacing = VERTICAL_SPACING;
        composite.setLayout(gridLayout);
        composite.setBackground(UIColor.WHITE);
        
    }

    /**
     * Configure a triplet (label, control, caption) to approach the Pollux recommended appearance:
     * <ul>
     * <li>set the widget layout data (stacked: label, control, caption)</li>
     * <li>set the widget fonts</li>
     * <li>set the widget colors</li>
     * </ul>
     * @param label the label naming the field placed above the control, can be <code>null<code>
     * @param control the editable control of the field, cannot be <code>null<code>
     * @param caption a caption (usually a text) placed below the control, can be <code>null<code>
     */
    @objid ("3c33a2de-7f45-4668-95f3-3afbec4afe20")
    public static void configureSimpleField(Control label, Control control, Control caption) {
        if (control == null) {
            throw new IllegalArgumentException("'control' cannot be null.");
        }
        Composite container = control.getParent();
        
        if ((label != null && label.getParent() != container)
                || (caption != null && caption.getParent() != container)) {
            throw new IllegalArgumentException("All controls must belong to the same Composite parent.");
        }
        
        if (!(container.getLayout() instanceof GridLayout)) {
            throw new IllegalArgumentException("The controls parent Composite layout must be of type GridLayout.");
        }
        
        GridLayout gridLayout = (GridLayout) container.getLayout();
        
        if (gridLayout.numColumns != 1) {
            throw new IllegalArgumentException("The controls Composite parent layout must be of type one-column GridLayout.");
        }
        
        if (label != null) {
            GridData labelGd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
            labelGd.verticalIndent = FIELD_VERTICAL_SPACING;
            label.setLayoutData(labelGd);
            configureStyleForFieldLabel(label);
        }
        
        control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        configureStyleForFieldControl(control);
        
        if (caption != null) {
            caption.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
            configureStyleForFieldCaption(caption);
        }
        
    }

    /**
     * Configure colors and font for a field label.
     * @param label the field label
     */
    @objid ("0517b345-171a-4df4-82ff-b754cf20753a")
    public static void configureStyleForFieldLabel(Control label) {
        Composite container = label.getParent();
        label.setFont(UIFont.NORMALB);
        label.setForeground(UIColor.GRAY);
        
        label.setBackground(container.getBackground());
        
    }

    /**
     * Configure colors and font for a field editable control.
     * @param control the field control
     */
    @objid ("8b5b7f40-86de-424b-8b88-0ebc35fd6a21")
    public static void configureStyleForFieldControl(Control control) {
        Composite container = control.getParent();
        control.setFont(UIFont.NORMAL);
        control.setForeground(UIColor.TEXT_WRITABLE_FG);
        control.setBackground(container.getBackground());
        
    }

    /**
     * Configure colors and font for a field caption.
     * @param caption the field caption
     */
    @objid ("63b69531-9727-404e-93ce-207a92d751af")
    public static void configureStyleForFieldCaption(Control caption) {
        Composite container = caption.getParent();
        caption.setFont(UIFont.SMALLI);
        caption.setForeground(UIColor.SWT_LIST_SELECTION);
        caption.setBackground(container.getBackground());
        
    }

    /**
     * Configure a triplet (label, control, caption) to approach the Pollux recommended appearance:
     * <ul>
     * <li>set the widget layout data (stacked: label, control, caption)</li>
     * <li>set the widget fonts</li>
     * <li>set the widget colors</li>
     * </ul>
     * 
     * The widgets must belong to the passed composite.
     * The passed composite layout is set to a grid layout and the controls are laid out in the passed composite.
     * @param comp the composite parent of the laid out controls
     * @param label the label naming the field placed above the control, can be <code>null<code>
     * @param control the editable control of the field, cannot be <code>null<code>
     * @param caption a caption (usually a text) placed below the control, can be <code>null<code>
     */
    @objid ("a5279694-eb82-4d04-b633-f62ecacdc5c2")
    public static void configureSimpleCompositeField(Composite comp, Control label, Control control, Control caption) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.verticalSpacing = VERTICAL_SPACING;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        
        comp.setLayout(gridLayout);
        comp.setBackground(UIColor.WHITE);
        
        configureSimpleField(label, control, caption);
        
    }

    @objid ("5d8c7d34-ed17-43ab-84ac-7b7f671e23d7")
    public static void configureMultiField(Control label, Control control, Control caption) {
        configureSimpleField(label, control, caption);
        ((GridData) control.getLayoutData()).grabExcessVerticalSpace = true;
        
    }

    @objid ("8fb55ffd-9076-42cd-a917-c154958520c7")
    public static void configureMultiCompositeField(Composite comp, Control label, Control control, Control caption) {
        configureSimpleCompositeField(comp, label, control, caption);
        ((GridData) control.getLayoutData()).grabExcessVerticalSpace = true;
        
    }

    /**
     * Configure a control to be used as a header in Pollux mode.
     * <ul>
     * <li>set the widget layout data (include a vertical indent to separate the next widget)</li>
     * <li>set the widget font (use a bigger bold font></li>
     * <li>set the widget color</li>
     * </ul>
     * @param control the control to be used as header (usually a Label), cannot be <code>null<code>
     */
    @objid ("071fe75b-8232-49b7-8397-9cf3f58a5d6e")
    public static void configureHeaderField(Control control) {
        if (control == null) {
            throw new IllegalArgumentException("'control' cannot be null.");
        }
        Composite container = control.getParent();
        GridLayout gridLayout = (GridLayout) container.getLayout();
        
        if (gridLayout.numColumns != 1) {
            throw new IllegalArgumentException("The controls Composite parent layout must be of type one-column GridLayout.");
        }
        
        control.setFont(UIFont.XLARGEB);
        control.setForeground(UIColor.SWT_LIST_SELECTION);
        control.setBackground(container.getBackground());
        
        GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false);
        gd.verticalIndent = VERTICAL_SPACING * 2;
        
        control.setLayoutData(gd);
        
    }

    @objid ("771edecd-16ba-4aa3-b982-746d85436534")
    public static void configureHeaderStyle(Control control) {
        if (control == null) {
            throw new IllegalArgumentException("'control' cannot be null.");
        }
        Composite container = control.getParent();
        
        control.setFont(UIFont.XLARGEB);
        control.setForeground(UIColor.SWT_LIST_SELECTION);
        control.setBackground(container.getBackground());
        
    }

    /**
     * This is similar to {@link #configureSimpleCompositeField(Composite, Control, Control, Control)} with an additional control typically used to open an advanced or specialized editor for the value of the main control.
     * For example a file value field has a button that opens a file selection dialog.
     * 
     * The widgets must belong to the passed composite.
     * The passed composite layout is set to a grid layout and the controls are laid out in the passed composite.
     * @param comp the composite parent of the laid out controls
     * @param label the label naming the field placed above the control, can be <code>null<code>
     * @param mainControl the editable control of the field, cannot be <code>null<code>
     * @param caption a caption (usually a text) placed below the control, can be <code>null<code>
     * @param editControl typically a button that opens a specialized editor to set the main control value, can be <code>null<code>
     */
    @objid ("a1f20e9a-5cc3-46db-9b0f-3b630b0dfdbe")
    public static void configureSimpleCompositeField(Composite comp, Control label, Control mainControl, Control caption, Control editControl) {
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.verticalSpacing = 1;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        comp.setLayout(gridLayout);
        comp.setBackground(UIColor.WHITE);
        
        
        
        
        if (label != null) {
            GridData labelGd = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1);
            labelGd.verticalIndent = gridLayout.verticalSpacing * 2;
            label.setLayoutData(labelGd);
            label.setFont(UIFont.NORMALB);
            label.setForeground(UIColor.SWT_LIST_SELECTION);
            label.setBackground(comp.getBackground());
        }
        
        if (editControl != null) {
            mainControl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            mainControl.setFont(UIFont.NORMAL);
            mainControl.setForeground(UIColor.TEXT_WRITABLE_FG);
            mainControl.setBackground(comp.getBackground());
        
            editControl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
            editControl.setFont(UIFont.NORMAL);
            editControl.setForeground(UIColor.TEXT_WRITABLE_FG);
            editControl.setBackground(comp.getBackground());
        
        } else {
            mainControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
            mainControl.setFont(UIFont.NORMAL);
            mainControl.setForeground(UIColor.TEXT_WRITABLE_FG);
            mainControl.setBackground(comp.getBackground());
        }
        
        if (caption != null) {
            caption.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));
            caption.setFont(UIFont.SMALLI);
            caption.setForeground(UIColor.SWT_LIST_SELECTION);
            caption.setBackground(comp.getBackground());
        }
        
    }

}
