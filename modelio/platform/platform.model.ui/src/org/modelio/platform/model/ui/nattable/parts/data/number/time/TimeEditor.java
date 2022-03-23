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
package org.modelio.platform.model.ui.nattable.parts.data.number.time;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.modelio.platform.model.ui.nattable.parts.data.number.date.CDateTime2;

/**
 * {@link ICellEditor} implementation for date times, using a {@link CDateTime} component as editor control.
 */
@objid ("945bdce3-3743-44f9-8a41-1803ef9a36ad")
public class TimeEditor extends AbstractCellEditor {
    @objid ("5a315bad-799b-4ad6-8f05-2c96804c9a89")
    private CDateTime cdt;

    @objid ("51cbe2aa-dda6-49ed-9347-889844999771")
    @Override
    public Rectangle calculateControlBounds(Rectangle cellBounds) {
        GC gc = new GC(this.getEditorControl());
        cellBounds.height = gc.getFontMetrics().getHeight();
        gc.dispose();
        return cellBounds;
    }

    @objid ("b035bad1-953b-4c39-8513-1dbada2630eb")
    @Override
    public void close() {
        super.close();
        this.cdt = null;
        
    }

    @objid ("66279a48-6e4d-4d29-b18a-ef304c1f1744")
    @Override
    public CDateTime createEditorControl(Composite parentComposite) {
        CDateTime2 editor = new CDateTime2(parentComposite, CDT.TIME_MEDIUM | CDT.TAB_FIELDS | CDT.TEXT_TRAIL | SWT.LEFT);
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

    @objid ("1b23d4c6-c7f4-4621-82a2-6c4104bd4838")
    @Override
    public Control getEditorControl() {
        return this.cdt;
    }

    @objid ("5ced1efc-dbc2-4825-afe1-c592e57aa65e")
    @Override
    public Object getEditorValue() {
        return this.displayConverter.canonicalToDisplayValue(this.cdt.getSelection());
    }

    @objid ("d736e24b-8a66-44ee-9372-d14644395a31")
    @Override
    public void setEditorValue(Object value) {
        this.cdt.setSelection((Date) this.displayConverter.displayToCanonicalValue(value));
    }

    @objid ("ff09672f-040f-4989-82a5-a7ef9f26a897")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.cdt = createEditorControl(parentComposite);
        this.cdt.setSelection((originalCanonicalValue != null) ? (Date) originalCanonicalValue : new Date());
        this.cdt.setFocus();
        return this.cdt;
    }

}
