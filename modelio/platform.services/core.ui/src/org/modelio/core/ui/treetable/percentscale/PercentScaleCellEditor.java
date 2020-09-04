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

package org.modelio.core.ui.treetable.percentscale;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Slider;

@objid ("6b413630-1eba-11e2-9382-bc305ba4815c")
public class PercentScaleCellEditor extends CellEditor {
    @objid ("6b413631-1eba-11e2-9382-bc305ba4815c")
    private int min;

    @objid ("6b413632-1eba-11e2-9382-bc305ba4815c")
    private int max;

    @objid ("6b413633-1eba-11e2-9382-bc305ba4815c")
    private Slider slider;

    @objid ("6b413634-1eba-11e2-9382-bc305ba4815c")
    public PercentScaleCellEditor(Composite parent, int min, int max) {
        super(parent);
        this.min = min;
        this.max = max;
    }

    @objid ("6b415d43-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected void doSetValue(Object value) {
        this.slider.setSelection(valueToPercent((Integer) value));
    }

    @objid ("6b418451-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Object doGetValue() {
        return percentToValue(this.slider.getSelection());
    }

    @objid ("6b41ab60-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Control createControl(Composite parent) {
        this.slider = new Slider(parent, SWT.NONE);
        this.slider.setMinimum(0);
        this.slider.setMaximum(101);
        this.slider.setIncrement(1);
        this.slider.setThumb(1);
        
        this.slider.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                fireEditorValueChanged(true, true);
            }
        });
        return this.slider;
    }

    @objid ("6b41d271-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected void doSetFocus() {
        // TODO Auto-generated method stub
    }

    @objid ("6b41d274-1eba-11e2-9382-bc305ba4815c")
    private int valueToPercent(int value) {
        return (((value) * 100) / (this.max - this.min));
    }

    @objid ("6b41f982-1eba-11e2-9382-bc305ba4815c")
    private int percentToValue(int percent) {
        return percent * (this.max - this.min) / 100;
    }

}
