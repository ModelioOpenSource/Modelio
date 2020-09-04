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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.modelio.api.ui.form.models.IFormFieldData;
import org.modelio.ui.UIColor;
import org.modelio.ui.UIImages;

/**
 * An abstract implementation of {@link IField} composed of:
 * <ul>
 * <li>a {@link Label}</li>
 * <li>an edition control</li>
 * <li>a help button</li>
 * <li>a help text</li>
 * </ul>
 * <br/>
 * The composite field controls are layouted as follows:<br/>
 * <pre>
 * [--- label ---] [-------- edition control -------- ] [---button --- ]
 * [-------------------- help text -------------------]
 * </pre>
 * where the help text if folded/unfolded by the help button.
 * The class is in charge of creating the different widgets and of their layout.
 * The creation of the edition control is delegated to concrete sub-classes.
 */
@objid ("0bd1394a-8404-436b-8808-6cadc328c916")
public abstract class AbstractField implements IField {
    @objid ("53f29cd5-2e6c-450b-83dc-0edb7d60d4ee")
    private static final String EMPTY_STRING = "";

    @mdl.prop
    @objid ("c36e7061-71b8-498a-881e-840b1c22fbb4")
    private boolean vertical = false;

    @mdl.propgetter
    public boolean isVertical() {
        // Automatically generated method. Please do not modify this code.
        return this.vertical;
    }

    @mdl.propsetter
    public void setVertical(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.vertical = value;
    }

    @objid ("b3fcbec7-5076-452b-ad07-61650afd07a6")
    protected final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @objid ("d901e4b1-a96c-4c53-8d20-aaf225a15b7a")
    private Control control;

    @objid ("8c93964d-0776-446b-8af5-05153384c4bc")
    private Composite fieldComposite;

    @objid ("c19bbd07-fb36-4284-b0c2-fcff9a0bc431")
    private ImageHyperlink helpButton;

    @objid ("b6303f86-3d16-4515-b6fe-6d95276855b9")
    private Label helpText;

    @objid ("ae2416c6-78da-4158-8602-ab9b6ba15865")
    private Label label;

    @objid ("d5062528-177d-489b-91eb-aa0c5d36542f")
    private IFormFieldData model;

    @objid ("17ca4f11-945f-4612-8297-6d6698128403")
    private final Composite parent;

    @objid ("594dcb08-d5b8-4811-bd03-07b207bd9416")
    private final FormToolkit toolkit;

    @objid ("77c1c8a1-7453-40e6-93b9-5606dfbcce42")
    public AbstractField(FormToolkit toolkit, Composite parent, IFormFieldData model) {
        this.toolkit = toolkit;
        this.parent = parent;
        this.model = model;
    }

    @objid ("3e47891d-fabf-4e1d-b552-3ed3116273f1")
    public AbstractField(FormToolkit toolkit, Composite parent) {
        this.toolkit = toolkit;
        this.parent = parent;
        this.model = null;
    }

    @objid ("57a9510b-3acb-495a-ae0b-5e3f4ac97c91")
    @Override
    public final void addPropertyChangeListener(PropertyChangeListener listener) {
        this.changeSupport.addPropertyChangeListener(listener);
    }

    @objid ("6a374959-886c-4b3e-a468-e4a8bbced877")
    @Override
    public void apply() {
        // If we reach here it means the field implementation does not anymore.
        // The field implementation has not been updated for Modelio Valkyrie 3.8 .
        throw new UnsupportedOperationException(getClass().getName()+".apply() Not implemented, ask the module developer for an update.");
    }

    /**
     * Create the edition control.
     * @param tk the {@link FormToolkit}
     * @param p the parent composite
     * @return the created control.
     */
    @objid ("17091519-a6ab-4ac0-b777-3d8fbdf4ce0b")
    public abstract Control createControl(FormToolkit tk, Composite p);

    /**
     * {@inheritDoc}
     */
    @objid ("29f524d6-6ff9-4038-ad1d-e4937d692d92")
    @Override
    public final Composite getComposite() {
        // Ensure widgets exist.
        if (this.fieldComposite == null) {
            buildGui();
            assert (this.fieldComposite != null);
        }
        return this.fieldComposite;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("c929183e-78d4-4e90-bccb-d2c98fd09f64")
    @Override
    public Control getControl() {
        if (this.control == null) {
            buildGui();
            assert (this.control != null);
        }
        return this.control;
    }

    @objid ("f23e8e05-59a5-4bed-88c9-70ba914a3380")
    public final Label getLabel() {
        return this.label;
    }

    /**
     * {@inheritDoc}
     */
    @objid ("b32df004-b93f-4823-bf56-ea25854cc7ea")
    @Override
    public final IFormFieldData getModel() {
        return this.model;
    }

    /**
     * Default implementation that always return <i>null</i>.
     * <p>
     * Should be redefined if the implementation widget may contain invalid values.
     * @since Valkyrie 3.8
     */
    @objid ("1e0f1db4-c3ec-488b-a518-f7b91af86337")
    @Override
    public String getValidationError() {
        return null;
    }

    @objid ("2e2919c6-c635-4893-a123-04e7e23c503a")
    @Override
    public void layout(Label lbl, Control ctrl, ImageHyperlink button) {
        if (this.vertical) {
        
            // The label
            FormData formData = new FormData();
            formData.top = new FormAttachment(0, 6);
            formData.left = new FormAttachment(0);
            lbl.setLayoutData(formData);
        
            // The Help button
            formData = new FormData();
            formData.top = new FormAttachment(0, 3);
            formData.right = new FormAttachment(100, 0);
            button.setLayoutData(formData);
        
            // The Control
            formData = new FormData();
            formData.top = new FormAttachment(lbl, 0, SWT.BOTTOM);
            formData.bottom = new FormAttachment(this.helpText, 0);
            formData.left = new FormAttachment(0, 0);
            formData.right = new FormAttachment(100, 0);
            ctrl.setLayoutData(formData);
        
        } else {
            // The label
            FormData formData = new FormData();
            formData.top = new FormAttachment(this.control, 0, SWT.TOP);
            formData.bottom = new FormAttachment(this.control, 0, SWT.BOTTOM);
            formData.left = new FormAttachment(0);
            formData.right = new FormAttachment(20);
            lbl.setLayoutData(formData);
        
            // The Help button
            formData = new FormData();
            formData.top = new FormAttachment(this.control, 0, SWT.TOP);
            // formData.bottom = new FormAttachment(this.control, 0, SWT.BOTTOM);
            formData.right = new FormAttachment(100, 0);
            button.setLayoutData(formData);
        
            // The Control
            formData = new FormData();
            formData.top = new FormAttachment(0, 0);
            formData.bottom = new FormAttachment(this.helpText, 0);
            formData.left = new FormAttachment(this.label, 10);
            formData.right = new FormAttachment(this.helpButton, -2);
            ctrl.setLayoutData(formData);
        }
    }

    @objid ("d9ef5ce1-056a-4c7e-949b-47c80022276a")
    @Override
    public final void removePropertyChangeListener(PropertyChangeListener listener) {
        this.changeSupport.removePropertyChangeListener(listener);
    }

    @objid ("b29444ff-ae58-4f49-ade8-27b441ddbf67")
    @Override
    public void setEditable(boolean onoff) {
        if (this.control != null) {
            this.control.setEnabled(onoff);
            if (this.control.isVisible()) {
                refresh();
            }
        }
    }

    @objid ("e7e7a4f5-d3b5-4020-9812-4e7a5639c937")
    @Override
    public final void setHelpText(String s) {
        // Ensure widgets exist.
        getComposite();
        
        this.helpText.setText(s);
        this.helpButton.setVisible(!s.isEmpty());
    }

    @objid ("e0268ef8-2ae7-49ed-95b1-ca1181db44a2")
    @Override
    public void setModel(IFormFieldData data) {
        this.model = data;
    }

    @objid ("2e8df036-728d-4e2d-8ae3-bfe61dada67e")
    protected void fireValueChanged(Object oldValue, Object newValue) {
        this.changeSupport.firePropertyChange(PROP_VALUE, oldValue, newValue);
    }

    @objid ("0326488a-a12d-46b8-946d-16365a196cdd")
    protected Label getHelpText() {
        return this.helpText;
    }

    @objid ("8925d210-0086-4f04-9ef6-657f43edac5f")
    private void buildGui() {
        // The composite field is build on a Composite using a FormLayout
        this.fieldComposite = this.toolkit.createComposite(this.parent);
        this.fieldComposite.setBackground(this.parent.getBackground());
        final FormLayout l = new FormLayout();
        this.fieldComposite.setLayout(l);
        
        // Create the composite field controls
        this.label = this.toolkit.createLabel(this.fieldComposite, AbstractField.EMPTY_STRING);
        this.label.setBackground(this.parent.getBackground());
        
        this.helpButton = this.toolkit.createImageHyperlink(this.fieldComposite, SWT.FLAT);
        this.helpButton.setImage(UIImages.HELP);
        this.helpButton.setBackground(this.parent.getBackground());
        // At first, hide the 'help' button
        this.helpButton.setVisible(false);
        this.helpText = this.toolkit.createLabel(this.fieldComposite, "", SWT.WRAP);
        this.helpText.setBackground(UIColor.POSTIT_YELLOW);
        
        // Create the control
        this.control = createControl(this.toolkit, this.fieldComposite);
        if (this.control == null) {
            throw new NullPointerException(String.format("%s.createControl(...) returned null", this));
        }
        
        // Layout the composite field controls
        layout(this.label, this.control, this.helpButton);
        
        assert (this.label.getLayoutData() instanceof FormData);
        assert (this.control.getLayoutData() instanceof FormData);
        assert (this.helpButton.getLayoutData() instanceof FormData);
        
        // The Help text is layouted so that it folds/unfolds just under the control (same width and X position)
        final FormData formData = new FormData(0, 0);
        formData.bottom = new FormAttachment(100);
        this.helpText.setLayoutData(formData);
        this.helpText.setVisible(false);
        
        final boolean DEFERLAYOUT = false;
        
        this.helpButton.addHyperlinkListener(new HyperlinkAdapter() {
            @Override
            public void linkActivated(HyperlinkEvent e) {
                Composite composite = getComposite();
                Label lHelpText = getHelpText();
        
                if (lHelpText.isVisible()) {
                    // Make it invisible
                    final FormData fd = new FormData(0, 0);
                    fd.bottom = new FormAttachment(100);
                    lHelpText.setLayoutData(fd);
                    lHelpText.setVisible(false);
                    if (DEFERLAYOUT) {
                        lHelpText.requestLayout();
                    }
                } else {
                    // Make it visible
                    final FormData fd = new FormData(SWT.DEFAULT, SWT.DEFAULT);
                    // fd.top = new FormAttachment(CompositeField.this.getLabel(), 1);
                    fd.bottom = new FormAttachment(100);
                    fd.left = new FormAttachment(getControl(), 0, SWT.LEFT);
                    fd.right = new FormAttachment(getControl(), 0, SWT.RIGHT);
                    lHelpText.setLayoutData(fd);
                    lHelpText.setVisible(true);
                    if (DEFERLAYOUT) {
                        lHelpText.requestLayout();
                    }
                }
                if (DEFERLAYOUT) {
                    composite.requestLayout();
                } else {
                    composite.layout(true, true);
                    composite.pack();
                    composite.getParent().layout(true, true);
                }
            }
        });
        
        if (DEFERLAYOUT) {
            this.fieldComposite.requestLayout();
        } else {
            this.fieldComposite.layout(true, true);
            this.fieldComposite.pack();
            this.fieldComposite.getParent().layout(true, true);
        }
    }

}
