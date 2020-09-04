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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.modelio.api.ui.form.models.IFormFieldData;
import org.modelio.ui.htmleditor.HtmlComposer;

/**
 * display/edit the content of a html text.
 * 
 * Uses a HtmlComposer
 */
@objid ("06d5a8e0-ec8d-44ee-a060-2768282df6e8")
public class HtmlTextField extends AbstractField {
    @objid ("492c2043-b296-4798-aead-09e833931ce7")
    private static final String EMPTY_STRING = "";

    @objid ("79d4ecd4-580a-4365-8143-d1ff01db3017")
    private final int heightHint;

    @objid ("e06601d6-d55a-4120-855a-256621b68156")
    private HtmlComposer text;

    @objid ("3b2b2253-0c38-4e52-a24d-e6f3ec833129")
    public HtmlTextField(FormToolkit toolkit, Composite parent, IFormFieldData model) {
        this(toolkit, parent, model, -1);
    }

    @objid ("cf91f222-8298-4a7e-a5cf-17c8bdbb0da8")
    public HtmlTextField(FormToolkit toolkit, Composite parent, IFormFieldData model, int heightHint) {
        super(toolkit, parent, model);
        this.heightHint = heightHint;
    }

    @objid ("f170dc0a-8865-4619-a24d-74a74d69e70d")
    @Override
    public void apply() {
        getModel().setValue(this.text.getHtml());
    }

    /**
     * {@inheritDoc}
     */
    @objid ("e5d92927-e787-488d-8ce3-2849ea1a73de")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        Composite border = toolkit.createComposite(parent, SWT.BORDER);
        border.setLayout(new FillLayout());
        
        this.text = new HtmlComposer(border, SWT.NONE);
        toolkit.adapt(this.text.getBrowser(), false, false);
        
        // Initialize values
        getLabel().setText(getModel().getName());
        
        final Object value = getModel().getValue();
        this.text.setHtml(value != null ? value.toString() : EMPTY_STRING);
        
        // Install Listeners
        this.text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                fireValueChanged(null, HtmlTextField.this.text.getHtml());
            }
        });
        return border;
    }

    /**
     * {@inheritDoc}
     * 
     * Specializes the initial layout so that several lines of the text can be displayed.
     */
    @objid ("4593fdd3-d0f3-4199-8f91-877710f30b57")
    @Override
    public void layout(Label label, Control control, ImageHyperlink helpButton) {
        super.layout(label, control, helpButton);
        if (this.heightHint > 0) {
            ((FormData) control.getLayoutData()).height = this.heightHint;
        }
    }

    /**
     * {@inheritDoc}
     */
    @objid ("9e461306-be8b-4806-a626-0eaf823c6a79")
    @Override
    public void refresh() {
        final Object value = getModel().getValue();
        this.text.setHtml(value != null ? value.toString() : EMPTY_STRING);
    }

}
