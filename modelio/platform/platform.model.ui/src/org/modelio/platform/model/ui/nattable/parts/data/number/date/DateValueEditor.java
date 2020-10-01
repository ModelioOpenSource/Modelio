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
 * Nat table editor for an {@link IDateNatValue}.
 */
@objid ("84163dff-010b-4cff-8b70-a903e755b14e")
public class DateValueEditor extends AbstractCellEditor {
    @objid ("bcc2cb1a-1be2-4ccf-b345-848ee10bf81f")
    private CDateTime cdt;

    @objid ("40535ca2-0a4d-4b1e-a236-c8a527ca00fe")
    @Override
    public void close() {
        super.close();
        this.cdt = null;
    }

    @objid ("bbb98157-9ff6-42aa-907a-99e365ce5665")
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

    @objid ("fa024d62-74fc-4a53-b37b-d804643630dd")
    @Override
    public Control getEditorControl() {
        return this.cdt;
    }

    @objid ("ae4754e9-69f7-4364-924c-df83fd3be40a")
    @Override
    public Object getEditorValue() {
        return this.displayConverter.canonicalToDisplayValue(this.cdt.getSelection());
    }

    @objid ("f22acb48-da64-40f4-98af-05c23e5a5e24")
    @Override
    public void setEditorValue(Object value) {
        this.cdt.setSelection((Date) this.displayConverter.displayToCanonicalValue(value));
    }

    @objid ("9f038dd5-50a1-46e0-9b57-7703507da576")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.cdt = createEditorControl(parentComposite);
        this.cdt.setSelection((originalCanonicalValue instanceof IDateNatValue) ? ((IDateNatValue) originalCanonicalValue).getValue() : new Date());
        this.cdt.setFocus();
        return this.cdt;
    }

    @objid ("937d54fc-6cb9-42f7-999b-26dd551f95d3")
    public DateValueEditor() {
        super();
        this.focusListener = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!e.widget.equals(DateValueEditor.this.cdt)) {
                    if (!e.widget.isDisposed() && !commit(MoveDirectionEnum.NONE, true)) {
                        if (e.widget instanceof Control) {
                            ((Control) e.widget).forceFocus();
                        }
                    } else {
                        if (!DateValueEditor.this.parent.isDisposed()) {
                            DateValueEditor.this.parent.forceFocus();
                        }
                    }
                }
            }
        };
    }

    @objid ("0643f377-598e-4f7f-b12a-1e2afe733801")
    @Override
    public Rectangle calculateControlBounds(Rectangle cellBounds) {
        GC gc = new GC(this.getEditorControl());
        cellBounds.height = gc.getFontMetrics().getHeight();
        gc.dispose();
        return cellBounds;
    }

}
