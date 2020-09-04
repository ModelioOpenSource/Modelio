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

package org.modelio.diagram.styles.editingsupport.percentscale;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Slider;

@objid ("85b08248-1926-11e2-92d2-001ec947c8cc")
public class PercentScaleCellEditor extends CellEditor {
    @objid ("85b08249-1926-11e2-92d2-001ec947c8cc")
    private int min;

    @objid ("85b0824a-1926-11e2-92d2-001ec947c8cc")
    private int max;

    @objid ("d05e8eb5-8075-4c74-9cdf-ce3099a53e31")
    private Slider slider;

    @objid ("85b0824c-1926-11e2-92d2-001ec947c8cc")
    public PercentScaleCellEditor(Composite parent, int min, int max) {
        super(parent);
        this.min = min;
        this.max = max;
    }

    @objid ("85b08251-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void doSetValue(Object value) {
        this.slider.setSelection(valueToPercent((Integer) value));
    }

    @objid ("85b08255-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Object doGetValue() {
        return percentToValue(this.slider.getSelection());
    }

    @objid ("85b0825a-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Control createControl(Composite parent) {
        this.slider = new Slider(parent, SWT.NONE);
        this.slider.setMinimum(0);
        this.slider.setMaximum(101);
        this.slider.setIncrement(1);
        this.slider.setThumb(1);
        
        this.slider.addListener(SWT.Selection, new Listener() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void handleEvent(Event event) {
                fireEditorValueChanged(true, true);
            }
        });
        return this.slider;
    }

    @objid ("85b08260-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void doSetFocus() {
        // Nothing to do
    }

    @objid ("85b08263-1926-11e2-92d2-001ec947c8cc")
    private int valueToPercent(int value) {
        return ((value * 100) / (this.max - this.min));
    }

    @objid ("85b08268-1926-11e2-92d2-001ec947c8cc")
    private int percentToValue(int percent) {
        return percent * (this.max - this.min) / 100;
    }

}
