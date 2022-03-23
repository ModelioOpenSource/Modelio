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
package org.modelio.diagram.styles.editingsupport.color;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Editor that opens immediately a {@link ColorDialog}.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("687b3904-63dc-4b6a-90b1-e5184e2f00e0")
public class ColorCellEditor3 extends CellEditor {
    /**
     * The parent composite.
     */
    @objid ("d776b4ea-3993-4e09-b6fd-5a2c2686770b")
    private Composite composite;

    /**
     * The current color.
     */
    @objid ("e5cd3c59-5f66-48d1-9470-f70215b9066b")
    private RGB value;

    @objid ("ec639f41-0ed2-4e97-a634-22e4f5498456")
    protected RGB openDialogBox(Control cellEditorWindow) {
        final Display display = cellEditorWindow.getDisplay();
        final Shell centerShell = new Shell(cellEditorWindow.getShell(), SWT.NO_TRIM);
        centerShell.setLocation(display.getCursorLocation());
        
        ColorDialog dialog = new ColorDialog(centerShell, SWT.NONE);
        
        Object val = getValue();
        if (val != null) {
            dialog.setRGB((RGB) val);
        }
        return dialog.open();
    }

    @objid ("1b158a7e-228f-40d8-8f4f-278e32b844a2")
    @Override
    public void activate() {
        RGB res = openDialogBox(this.composite);
        
        if (res != null && !Objects.equals(res, doGetValue())) {
            doSetValue(res);
            fireApplyEditorValue();
        }
        deactivate();
        
    }

    /**
     * Creates a new color cell editor parented under the given control. The cell editor value is black ( <code>RGB(0,0,0)</code>) initially, and has no validator.
     * @param parent the parent control
     */
    @objid ("71673ab3-1aec-4cec-97e3-5d8894845c93")
    public  ColorCellEditor3(final Composite parent) {
        this(parent, SWT.NONE);
    }

    /**
     * Creates a new color cell editor parented under the given control. The cell editor value is black ( <code>RGB(0,0,0)</code>) initially, and has no validator.
     * @param parent the parent control
     * @param style the style bits
     * @since 2.1
     */
    @objid ("5ad3fa83-612a-4ab9-bc78-3430a2833a9c")
    public  ColorCellEditor3(final Composite parent, final int style) {
        super(parent, style);
        doSetValue(new RGB(0, 0, 0));
        
    }

    @objid ("9d50cfef-b888-46a4-b262-41a8f027c258")
    @Override
    protected Control createControl(Composite parent) {
        this.composite = parent;
        return null;
    }

    @objid ("13bd1c35-8cd0-4ee6-b2a5-c35ecd47495e")
    @Override
    protected Object doGetValue() {
        return this.value;
    }

    @objid ("bca81117-021f-425d-815b-79c9a194c56d")
    @Override
    protected void doSetFocus() {
        // ignore
    }

    @objid ("b54d2bbb-bf9c-416a-ba57-7917350af38e")
    @Override
    protected void doSetValue(Object value) {
        this.value = (RGB) value;
    }

    @objid ("746f2a84-8371-4d0b-813b-38b6c54d14be")
    @Override
    public void activate(ColumnViewerEditorActivationEvent activationEvent) {
        if (activationEvent.eventType != ColumnViewerEditorActivationEvent.TRAVERSAL) {
            super.activate(activationEvent);
        }
        
    }

}
