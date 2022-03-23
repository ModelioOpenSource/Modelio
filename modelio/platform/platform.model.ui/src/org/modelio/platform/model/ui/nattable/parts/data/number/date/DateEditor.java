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
package org.modelio.platform.model.ui.nattable.parts.data.number.date;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * {@link ICellEditor} implementation for dates, using a {@link CDateTime} component as editor control.
 */
@objid ("375f6520-2909-4c82-a6b1-586e13c777bb")
public class DateEditor extends AbstractCellEditor {
    @objid ("f98196fd-201e-41b2-b2de-f4a2e72bacec")
    private CDateTime cdt;

    @objid ("fe0c54a2-4d9b-431f-a128-e7b4b2984f13")
    @Override
    public void close() {
        super.close();
        this.cdt = null;
        
    }

    @objid ("49205602-eade-41d5-b126-2b6d2f7dcecd")
    @Override
    public CDateTime createEditorControl(Composite parentComposite) {
        CDateTime2 editor = new CDateTime2(parentComposite, CDT.DATE_MEDIUM | CDT.DROP_DOWN);
        editor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                commit(MoveDirectionEnum.DOWN);
            }
        });
        editor.getWidget().addListener(SWT.KeyDown, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (event.stateMask != 0) {
                    return;
                }
                if (SWT.ESC == event.keyCode) {
                    close();
                }
            }
        });
        return editor;
    }

    @objid ("b1abf8e3-f866-4333-ba7c-be5eea6dc9e8")
    @Override
    public Control getEditorControl() {
        return this.cdt;
    }

    @objid ("f7243420-9e5b-4397-b038-b3f21875a6b4")
    @Override
    public Object getEditorValue() {
        return this.displayConverter.canonicalToDisplayValue(this.cdt.getSelection());
    }

    @objid ("f50cea2f-6745-4f3e-adfc-d8b956d4ec49")
    @Override
    public void setEditorValue(Object value) {
        this.cdt.setSelection((Date) this.displayConverter.displayToCanonicalValue(value));
    }

    @objid ("e2c40099-4c8a-45d0-a82e-6f2c328241c1")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.cdt = createEditorControl(parentComposite);
        this.cdt.setSelection((originalCanonicalValue != null) ? (Date) originalCanonicalValue : new Date());
        this.cdt.setFocus();
        return this.cdt;
    }

    @objid ("258285ef-c056-4930-8914-dcaf4667102f")
    public  DateEditor() {
        super();
        this.focusListener = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!e.widget.equals(DateEditor.this.cdt)) {
                    if (!e.widget.isDisposed() && !commit(MoveDirectionEnum.NONE, true)) {
                        if (e.widget instanceof Control) {
                            ((Control) e.widget).forceFocus();
                        }
                    } else {
                        if (!DateEditor.this.parent.isDisposed()) {
                            DateEditor.this.parent.forceFocus();
                        }
                    }
                }
            }
        };
        
    }

    @objid ("f9247120-3897-45e8-86bc-c3da4deeb717")
    @Override
    public Rectangle calculateControlBounds(Rectangle cellBounds) {
        GC gc = new GC(this.getEditorControl());
        cellBounds.height = gc.getFontMetrics().getHeight();
        gc.dispose();
        return cellBounds;
    }

}
