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

import java.beans.PropertyChangeListener;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.modelio.api.ui.form.models.IFormFieldData;

/**
 * Represents a field in an edition form.
 * <p>
 * A field is composed of:
 * <ul>
 * <li>a label - informative taken from {@link IFormFieldData#getName()} </li>
 * <li>a {@linkplain #getControl() control} - where edition takes place</li>
 * <li>a help button - bound to a {@linkplain #setHelpText(String) configurable help text}</li>
 * </ul>
 * The field uses its {@link #getModel() model} to load and saves the value.
 * <p>
 * <h3>Behavior change since 3.8 :</h3>
 * Before 3.8 field implementations immediately applied changes to the model when modified.
 * Now they fire listeners registered by {@link #addPropertyChangeListener(PropertyChangeListener)}
 * with {@link #PROP_VALUE} has key and the new value as event value. The old value may not be filled.
 * <p>
 * It is now up to the panel to decide whether the change should be applied immediately by calling {@link #apply()}
 * or defer it until form validation. The caller should also check whether {@link #canApply()} returns true
 * before doing so.
 */
@objid ("d8d8870e-fee4-4c7e-9751-3affdd89ce02")
public interface IField {
    /**
     * 'value changed' property change event key.
     */
    @objid ("ab127778-0b88-460c-8d55-eefa81e52c48")
    public static final String PROP_VALUE = "value";

    /**
     * Add a change listener to this field
     * @see #PROP_VALUE
     * @param listener a listener
     * @since Valkyrie 3.8
     */
    @objid ("7a0b27ca-7cd5-4b0e-9f47-b8428e182af8")
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Store the field value in the model.
     * <p>
     * May throw a runtime exception if the field value is not valid.
     * @since Valkyrie 3.8
     */
    @objid ("f2b0524b-d776-4f74-8610-3173ba35d593")
    void apply();

    /**
     * Gets the top most container control of the field which must be a <code>Composite</code>.
     */
    @objid ("44a81dcb-891f-43da-ac04-6fc0bb8348ed")
    Composite getComposite();

    /**
     * Gets the control in charge of displaying/editing the field value.
     */
    @objid ("d39e4710-d50f-451d-a698-fc8b640a417c")
    Control getControl();

    /**
     * Gets the IfieldData model of this field.
     */
    @objid ("29c0cfaf-642c-4914-a511-aa2597280a6a")
    IFormFieldData getModel();

    /**
     * Get the validation error message if the value is invalid.
     * <p>
     * Returns null when the value is valid.
     * Tells whether the value may be applied or it is invalid.
     * @return null if {@link #apply()} may be called safely, an error message in other cases.
     * @since Valkyrie 3.8
     */
    @objid ("c14acf4c-0313-4a20-9413-4d36e981f729")
    String getValidationError();

    /**
     * Layouts the label, edition control and help button in their container ({@link IField#getComposite()})
     * <p>
     * This method can be redefined by subclasses who need to change the standard layout for label, control and help button.<br/>
     * When it is called default LayoutData have already been applied to the widgets so that this method can typically either modify
     * or replace the existing LayoutData of the widgets.
     * <p>
     * The container layout is a FormLayout, therefore widget LayoutData must be FormData.
     * <p>
     * Note the the helpText will always be layouted so that it appears under the control (same width and X position).
     */
    @objid ("4a481815-1aa6-4873-83bb-e4ca7baaa2d3")
    void layout(Label label, Control control, ImageHyperlink helpButton);

    /**
     * Refresh the value displayed by the field.
     * <p>
     * The field will typically query its {@link IFormFieldData} model and refresh its contents.
     */
    @objid ("69e8d634-07fe-4e1f-9227-3d3af8663114")
    void refresh();

    /**
     * Remove a change listener
     * @param listener the listener to remove.
     * @since Valkyrie 3.8
     */
    @objid ("c262cfe1-dd47-43ca-8161-787e58c0b931")
    void removePropertyChangeListener(PropertyChangeListener listener);

    @objid ("87ed2f4f-d1b7-4c8c-91f4-08afce29c8b1")
    void setEditable(boolean onoff);

    /**
     * Set the text to be displayed in the help field of the control.
     */
    @objid ("47c62fea-7034-431d-a9de-f88c28ae1d90")
    void setHelpText(String s);

    @objid ("70a9c4c0-736b-4aeb-8f63-439becf48f08")
    void setModel(IFormFieldData data);
}

