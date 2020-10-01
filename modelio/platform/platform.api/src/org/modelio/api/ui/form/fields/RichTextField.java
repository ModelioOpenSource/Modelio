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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.plugin.Api;
import org.modelio.api.ui.form.models.IFormFieldData;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.platform.ui.UIColor;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("ba2d2660-4f87-4a9e-954d-18708239feb0")
public class RichTextField extends AbstractField {
    @objid ("59e34f1b-6bb4-4dc6-8404-a0444845e70c")
    private Label image;

    @objid ("764f5659-5b60-4e6e-8813-abdd7869006d")
    private final IModuleContext moduleContext;

    @objid ("ce181e6c-c478-4369-9e02-398a75a99df6")
    public RichTextField(IModuleContext moduleContext, FormToolkit toolkit, Composite parent, IFormFieldData model) {
        super(toolkit, parent, model);
        this.moduleContext = moduleContext;
    }

    @objid ("1d29d6cd-b96a-462c-80b5-795c2d944f63")
    @Override
    public void apply() {
        // do nothing
    }

    @objid ("57d2dd5e-c8f3-4460-8bbb-c9e839ea2738")
    @Override
    public Control createControl(FormToolkit toolkit, Composite parent) {
        this.image = toolkit.createLabel(parent, "");
        this.image.setToolTipText(Api.I18N.getString("RichTextField.tooltip"));
        this.image.setForeground(UIColor.NONMODIFIABLE_ELEMENT_FG);
        
        // Initialize values
        getLabel().setText(getModel().getName());
        return this.image;
    }

    @objid ("e8d3e7c9-3729-42ca-bcac-7c962147ea2f")
    @Override
    public void refresh() {
        AbstractResource externDocument = (AbstractResource) getValue();
        Image newImage = externDocument != null ? this.moduleContext.getModelioServices().getImageService().getIcon(externDocument) : null;
        this.image.setImage(newImage);
        this.image.setText(newImage != null ? "" : Api.I18N.getString("RichTextField.emptylabel"));
    }

    @objid ("143f8582-f600-4383-bcdd-9fc9627cb05f")
    private MObject getValue() {
        try {
            final String value = (String) getModel().getValue();
            if (value != null) {
                final MRef mRef = new MRef(value);
                final MClass mclass = this.moduleContext.getModelioServices().getMetamodelService().getMetamodel().getMClass(mRef.mc);
                return this.moduleContext.getModelingSession().findElementById(mclass, mRef.uuid);
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
