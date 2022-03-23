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
package org.modelio.diagram.styles.editingsupport.font;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.platform.ui.CoreFontRegistry;

/**
 * Editor that opens immediately a {@link FontDialog}.
 * 
 * @since 3.7
 */
@objid ("85a2342c-1926-11e2-92d2-001ec947c8cc")
public class FontDialogEditor extends CellEditor {
    /**
     * The current font.
     */
    @objid ("4ea8d850-fe0f-44bb-8994-3c0753fc9f97")
    private Font value;

    /**
     * The parent composite.
     */
    @objid ("c892b881-6bb5-43e7-abb4-5ab1300d2878")
    private Composite composite;

    /**
     * Creates a new color cell editor parented under the given control. The cell editor value is black (
     * <code>Font(0,0,0)</code>) initially, and has no validator.
     * @param parent the parent control
     */
    @objid ("85a23439-1926-11e2-92d2-001ec947c8cc")
    public  FontDialogEditor(final Composite parent) {
        this(parent, SWT.NONE);
    }

    /**
     * Creates a new font cell editor parented under the given control.
     * The cell editor value is null initially, and has no validator.
     * @param parent the parent control
     * @param style the style bits
     * @since 2.1
     */
    @objid ("85a2343d-1926-11e2-92d2-001ec947c8cc")
    public  FontDialogEditor(final Composite parent, final int style) {
        super(parent, style);
        doSetValue(null);
        
    }

    @objid ("85a49689-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Control createControl(Composite parent) {
        this.composite = parent;
        return null;
    }

    @objid ("85a49693-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Object doGetValue() {
        return this.value;
    }

    @objid ("85a49698-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void doSetFocus() {
        // ignore
    }

    @objid ("85a496a0-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void doSetValue(Object value) {
        this.value = (Font) value;
    }

    @objid ("85a496a9-1926-11e2-92d2-001ec947c8cc")
    protected Font openDialogBox(Control cellEditorWindow) {
        final Display display = cellEditorWindow.getDisplay();
        final Shell centerShell = new Shell(cellEditorWindow.getShell(), SWT.NO_TRIM);
        centerShell.setLocation(display.getCursorLocation());
        
        FontDialog ftDialog = new FontDialog(centerShell, SWT.NONE);
        if (this.value != null) {
            ftDialog.setFontList(this.value.getFontData());
        }
        ftDialog.setEffectsVisible(false);
        
        FontData fData = ftDialog.open();
        if (fData != null) {
            FontData[] fontList = ftDialog.getFontList();
            return CoreFontRegistry.getFont(fontList);
        }
        return null;
    }

    @objid ("5d0cd2cc-5a57-47ec-8f23-5969b29c938a")
    @Override
    public void activate() {
        Font res = openDialogBox(this.composite);
        
        if (res != null && !Objects.equals(res, doGetValue())) {
            doSetValue(res);
            fireApplyEditorValue();
        }
        deactivate();
        
    }

    @objid ("e483e907-d747-42c7-bb34-c918d1c8c6e3")
    @Override
    public void activate(ColumnViewerEditorActivationEvent activationEvent) {
        if (activationEvent.eventType != ColumnViewerEditorActivationEvent.TRAVERSAL) {
            super.activate(activationEvent);
        }
        
    }

    @objid ("1e2bdb62-7420-4788-a25b-6263de1162a4")
    @Override
    public void dispose() {
        
    }

}
