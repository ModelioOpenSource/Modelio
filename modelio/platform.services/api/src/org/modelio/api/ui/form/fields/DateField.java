/* 
 * Copyright 2013-2019 Modeliosoft
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
 * An AbstractField sub-classes specialized to display/edit date values.
 * 
 * Uses a SWT DateTime button.
 */
@objid ("722ed337-1aa5-47af-8f90-9eb0741d81c0")
public class DateField extends AbstractField {
    @objid ("f30558c0-30af-45c5-bf73-e47c4451ba34")
    private DateTime date;

    @objid ("3dc078e0-3ed4-46c9-b38c-50241e18e37f")
    private Button nullCheckBox;

    @objid ("e79c580b-d0c3-45b1-a70b-72f7c082a944")
    public DateField(FormToolkit toolkit, Composite parent, IFormFieldData model) {
        super(toolkit, parent, model);
    }

    @objid ("060dc9f5-6263-41ad-bfd9-f65019f7c259")
    @Override
    public void apply() {
        getModel().setValue(getLocalValue());
    }

    /**
     * {@inheritDoc}
     */
    @objid ("4e6132e2-0adf-48d2-94ab-5a101bb4ed68")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        final Composite c = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2,  false);
        l.marginHeight = 0;
        l.marginWidth = 0;
        c.setLayout(l);
        
        this.nullCheckBox = new Button(c, SWT.CHECK);
        toolkit.adapt(this.nullCheckBox, false, false);
        
        this.date = new DateTime(c, SWT.DATE | SWT.DROP_DOWN);
        this.date.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        toolkit.adapt(this.date, false, false);
        
        // Initialize values
        getLabel().setText(getModel().getName());
        refresh();
        
        // Install Listeners
        this.nullCheckBox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                onChange();
            }
        });
        
        this.date.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                onChange();
            }
        });
        return c;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("e200e967-c81c-42e6-8545-1a67c48699e6")
    @Override
    public void refresh() {
        final Calendar c = Calendar.getInstance();
        final Date d = (Date)getModel().getValue();
        if (d != null) {
            c.setTime((Date) getModel().getValue());
            this.date.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            this.nullCheckBox.setSelection(true);
        } else {
            this.nullCheckBox.setSelection(false);
            this.date.setVisible(false);
        }
    }

    @objid ("6db63148-1fd8-43c8-8ed0-303b537bad85")
    private Date getLocalValue() {
        if (this.nullCheckBox.getSelection()) {
            final Calendar c = Calendar.getInstance();
            c.set(this.date.getYear(), this.date.getMonth(), this.date.getDay());
            return (c.getTime());
        } else {
            return null;
        }
    }

    @objid ("96a45c5a-4745-4716-9d74-fe9c27422b46")
    private void onChange() {
        this.date.setVisible(this.nullCheckBox.getSelection());
        
        fireValueChanged(null, getLocalValue());
    }

}
