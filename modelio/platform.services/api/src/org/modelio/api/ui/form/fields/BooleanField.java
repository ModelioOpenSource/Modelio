/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui.form.fields;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.api.ui.form.models.IFormFieldData;

/**
 * An AbstractField sub-classes specialized to display/edit boolean values.
 * 
 * Uses a SWT checkbox button.
 */
@objid ("48300f7b-6ae8-463d-b382-5de76b79d643")
public class BooleanField extends AbstractField {
    @objid ("6b771feb-e60e-409e-a09b-c1959b28835d")
    private Button checkbox;

    @objid ("9bf1e227-3381-46cb-8897-ae676651a934")
    public BooleanField(FormToolkit toolkit, Composite parent, IFormFieldData model) {
        super(toolkit, parent, model);
    }

    @objid ("248a303b-f68d-41d4-a264-b6893e3a3339")
    @Override
    public void apply() {
        getModel().setValue(this.checkbox.getSelection());
    }

    /**
     * {@inheritDoc}
     */
    @objid ("6380ab5b-6d22-4764-96dc-5110b5ac9f03")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        this.checkbox = toolkit.createButton(parent, "", SWT.CHECK);
        
        // Initialize values
        getLabel().setText(getModel().getName());
        refresh();
        
        // Install Listeners
        this.checkbox.addListener(SWT.Selection, ev -> {
            boolean newVal = this.checkbox.getSelection();
            fireValueChanged(!newVal, newVal);
        });
        return this.checkbox;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("dfeff12c-7361-4097-833d-dd2987d96a49")
    @Override
    public void refresh() {
        this.checkbox.setSelection((Boolean)getModel().getValue());
    }

}
