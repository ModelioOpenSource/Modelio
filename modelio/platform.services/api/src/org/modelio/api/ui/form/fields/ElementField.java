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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.api.plugin.Api;
import org.modelio.api.ui.form.models.IFormFieldData;
import org.modelio.api.ui.text.TextWrapperForIElement;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * An AbstractField sub-classes specialized to display/edit {@link Element}
 * values.
 * Uses a {@link TextWrapperForIElement}.
 */
@objid ("43281116-3216-4c7d-b6e1-86bba80d0221")
public class ElementField extends AbstractField {
    @objid ("17c3010b-ea53-4f2e-a3ad-11d0ec16e02c")
    private List<Class<? extends MObject>> allowedMetaclasses = new ArrayList<>();

    @objid ("e5c47363-5b34-4ad8-b94e-44155dbdc32a")
    private ILabelProvider labelProvider = new LabelProvider();

    @objid ("d63ac076-92bb-45d4-a2f0-047ceff0f892")
    private Text text;

    @objid ("ecfb58da-17b2-4400-9388-bf47f5b07836")
    private TextWrapperForIElement textElement;

    @objid ("d7308fc7-94d3-4f7f-8400-b8d50c68ed4d")
    public ElementField(FormToolkit toolkit, Composite parent, IFormFieldData model, List<Class<? extends MObject>> allowedMetaclasses) {
        super(toolkit, parent, model);
        this.allowedMetaclasses.addAll(allowedMetaclasses);
    }

    @objid ("3c0f47d4-a8e3-4de8-9c78-7ea5adac54d7")
    public ElementField(FormToolkit toolkit, Composite parent, IFormFieldData model) {
        super(toolkit, parent, model);
        this.allowedMetaclasses.add(ModelElement.class);
    }

    @objid ("b4493d3c-3fd6-4997-97f0-58f3b862dfea")
    @Override
    public void apply() {
        MObject value = this.textElement.getSelectedElement();
        String validationError = getValidationError(value);
        if (validationError == null) {
            getModel().setValue(value);
        } else {
            throw new IllegalStateException(validationError);
        }
    }

    /**
     * {@inheritDoc}
     */
    @objid ("39179051-95e5-4b49-b91d-c462510cc2ad")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        this.textElement = new TextWrapperForIElement(parent, null, true, this.allowedMetaclasses);
        
        this.text = this.textElement.getTextField();
        
        toolkit.adapt(this.text, false, false);
        
        // Initialize values
        getLabel().setText(getModel().getName());
        
        this.text.setText(this.labelProvider.getText(getModel().getValue()));
        
        this.textElement.setAcceptNullValue(true);
        
        // Install Listeners
        this.textElement.addListener(this::selectedElementChanged);
        return this.text;
    }

    @objid ("d41f0327-528f-42ff-83f8-e7a3100b3df5")
    @Override
    public String getValidationError() {
        return getValidationError(this.textElement.getSelectedElement());
    }

    /**
     * {@inheritDoc}
     */
    @objid ("64ea28d3-56de-4d66-a0fd-525169360abd")
    @Override
    public void refresh() {
        this.textElement.setElement((MObject) getModel().getValue());
    }

    @objid ("99eedfc1-261a-4d50-aea7-dff0b3398cd4")
    private String getValidationError(MObject value) {
        if (value == null || value.isValid()) {
            return null;
        }
        
        String elLabel = this.labelProvider.getText(value);
        return Api.I18N.getMessage("ElementField.error.deleted", elLabel, value, value.getName());
    }

    @objid ("465ab524-984e-414d-9330-b01165f684f8")
    private void selectedElementChanged(MObject oldElement, MObject newElement) {
        if (!Objects.equals(oldElement, newElement)) {
            fireValueChanged(oldElement, newElement);
        }
    }

}
