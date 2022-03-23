/* 
 * Copyright 2013-2020 Modeliosoft
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

import java.util.Calendar;
import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.api.ui.form.models.IFormFieldData;

/**
 * An AbstractField sub-class specialized to display/edit boolean values.
 * 
 * Uses a SWT {@link DateTime}.
 */
@objid ("4dcd3085-337f-4d32-aa05-26acb6c99695")
public class TimeField extends AbstractField {
    @objid ("ac5ecb3f-8583-4cb9-a362-d399ddc8fd25")
    private Button nullCheckBox;

    @objid ("26bb092e-67e3-4d6c-b8e6-58dce327c0a7")
    private DateTime time;

    @objid ("78440f64-01bf-4df3-977e-b4b0f11ad919")
    public  TimeField(FormToolkit toolkit, Composite parent, IFormFieldData model) {
        super(toolkit, parent, model);
    }

    @objid ("d53c7b19-56fe-4521-928a-1f42ee1ea7b3")
    @Override
    public void apply() {
        if (this.nullCheckBox.getSelection()) {
            final Calendar c = Calendar.getInstance();
            c.set(this.time.getYear(), this.time.getMonth(), this.time.getDay());
            getModel().setValue(c.getTime());
        } else {
            getModel().setValue(null);
        }
        this.time.setVisible(this.nullCheckBox.getSelection());
        
    }

    @objid ("790c98b4-a1c9-49f8-af30-b231b6bc5b9a")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        final Composite c = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2, false);
        l.marginHeight = 0;
        l.marginWidth = 0;
        c.setLayout(l);
        
        this.nullCheckBox = new Button(c, SWT.CHECK);
        toolkit.adapt(this.nullCheckBox, false, false);
        
        this.time = new DateTime(c, SWT.TIME);
        this.time.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        toolkit.adapt(this.time, false, false);
        
        // Initialize values
        getLabel().setText(getModel().getName());
        
        refresh();
        
        // Install Listeners
        this.nullCheckBox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                apply();
            }
        });
        
        this.time.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                apply();
            }
        });
        return c;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("37258135-9ba2-480d-9365-aa3cf65ddd21")
    @Override
    public void refresh() {
        final Calendar c = Calendar.getInstance();
        final Date d = (Date)getModel().getValue();
        if (d != null) {
            c.setTime((Date) getModel().getValue());
            this.time.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            this.time.setHours(c.get(Calendar.HOUR_OF_DAY));
            this.time.setMinutes(Calendar.MINUTE);
            this.time.setSeconds(Calendar.SECOND);
            this.nullCheckBox.setSelection(true);
        } else {
            this.nullCheckBox.setSelection(false);
            this.time.setVisible(false);
        }
        
    }

}
