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
import org.modelio.platform.model.ui.nattable.parts.data.number.date.IDateNatValue;

/**
 * Nat table editor for an {@link IDateNatValue}.
 */
@objid ("cf4db88a-ccf6-4eb0-bac1-b720710cdb4c")
public class TimeValueEditor extends AbstractCellEditor {
    @objid ("9f910969-40b5-4432-9247-abd868b7244c")
    private CDateTime cdt;

    @objid ("33c54ba1-ac5c-4623-a647-65405f6fb313")
    @Override
    public void close() {
        super.close();
        this.cdt = null;
        
    }

    @objid ("1ed12159-f12c-4fe7-b32e-6b1de9c5453a")
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

    @objid ("2dde801f-a260-4fed-82a1-5227c1cf8f28")
    @Override
    public Control getEditorControl() {
        return this.cdt;
    }

    @objid ("49ddf838-0fdf-46eb-95a1-8b771722e156")
    @Override
    public Object getEditorValue() {
        return this.displayConverter.canonicalToDisplayValue(this.cdt.getSelection());
    }

    @objid ("8461bb51-129c-4473-bb8c-63a87297caa7")
    @Override
    public void setEditorValue(Object value) {
        this.cdt.setSelection((Date) this.displayConverter.displayToCanonicalValue(value));
    }

    @objid ("7fbbfcdc-e7af-4177-9838-781beb81290a")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.cdt = createEditorControl(parentComposite);
        this.cdt.setSelection((originalCanonicalValue instanceof ITimeNatValue) ? ((ITimeNatValue) originalCanonicalValue).getValue() : new Date());
        this.cdt.setFocus();
        return this.cdt;
    }

    @objid ("2c6826b2-474b-490b-b460-8cb45685e475")
    @Override
    public Rectangle calculateControlBounds(Rectangle cellBounds) {
        GC gc = new GC(this.getEditorControl());
        cellBounds.height = gc.getFontMetrics().getHeight();
        gc.dispose();
        return cellBounds;
    }

}
