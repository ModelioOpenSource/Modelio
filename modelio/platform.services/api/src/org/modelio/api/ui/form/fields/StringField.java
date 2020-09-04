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

import java.util.function.Function;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.api.plugin.Api;
import org.modelio.api.ui.form.models.IFormFieldData;
import org.modelio.ui.UIColor;

/**
 * {@link IField} implementation for String based values.
 * <p>
 * A validator may also be set with {@link #setValidator(Predicate)}.
 */
@objid ("9d468a4b-056c-44e8-a102-627d9dc0e66e")
public class StringField extends AbstractField {
    @objid ("5825e8ab-8756-4161-aed2-741f33531c89")
    private static final String EMPTY_STRING = "";

    @objid ("bb39e9bd-52eb-4bb0-b180-e25f9f8ded0c")
    private Text text;

    @objid ("be785f1b-7675-4398-8928-7ee349efb649")
    private Function<String,String> validator = s -> null;

    @objid ("cffedb32-fe5a-4759-a495-657c076bad92")
    public StringField(FormToolkit toolkit, Composite parent, IFormFieldData model) {
        super(toolkit, parent, model);
    }

    @objid ("a29c5803-83fb-4ca3-a1cf-9528136b5e89")
    @Override
    public void apply() {
        final String value = this.text.getText();
        String err = getValidationError(value);
        if (err == null) {
            getModel().setValue(value);
        } else {
            throw new IllegalStateException(err);
        }
    }

    @objid ("dbfe9592-eabd-4f0c-8994-11471e8a14bb")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        this.text = toolkit.createText(parent, EMPTY_STRING, SWT.NONE);
        
        // Initialize values
        getLabel().setText(getModel().getName());
        
        Object value = getModel().getValue();
        this.text.setText(value != null ? value.toString() : EMPTY_STRING);
        
        // Install Listeners
        this.text.addListener(SWT.FocusOut, e -> valueChanged());
        
        this.text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                    valueChanged();
                } else if (e.keyCode == SWT.ESC) {
                    refresh();
                }
            }
        });
        
        this.text.addModifyListener(e -> validate());
        return this.text;
    }

    @objid ("8ca653a7-8577-4ed6-9718-dc2fc80ae63d")
    @Override
    public String getValidationError() {
        return getValidationError(this.text.getText());
    }

    @objid ("77c5a55b-5aae-4cab-870e-f13210abd356")
    @Override
    public void refresh() {
        final Object value = getModel().getValue();
        this.text.setText(value != null ? value.toString() : EMPTY_STRING);
    }

    /**
     * Set a value validator.
     * 
     * @param validator the validator that will be called with the new value.
     * @since Valkyrie 3.8
     */
    @objid ("e8bb54bf-2261-4eac-8ba1-64bac53612d9")
    public void setValidator(Function<String,String> validator) {
        this.validator = validator == null ? s->null : validator;
    }

    @objid ("510dc0fe-62d1-42d7-82da-c3d658b4dfe3")
    void validate() {
        final String value = this.text.getText();
        if (getValidationError(value)==null) {
            this.text.setForeground(null);
        } else {
            this.text.setForeground(UIColor.RED);
        }
    }

    @objid ("ed143876-7b7c-4fe6-839a-9c77922e8e73")
    void valueChanged() {
        fireValueChanged(null, this.text.getText());
    }

    @objid ("da532071-96cf-449d-83b1-b09421a066a9")
    protected String getValidationError(final String value) {
        if (!getModel().getType().isValidValue(value)) {
            return Api.I18N.getMessage("StringField.invalidValueForType", 
                    getLabel().getText(), 
                    value, 
                    getModel().getName(), 
                    getModel().getType().getName());
        }
        return this.validator.apply(value);
    }

}
