/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.xmi.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * This class creates the validation composite.
 * It composed of two SWT button:
 * - one for the validation of the export or the import
 * - the second for the cancellation of the export or the import
 * 
 * @author ebrosse
 */
@objid ("fe1e149d-2a4f-465c-9e70-906b3be35d46")
public class ValidationBoutonComposite extends Composite {
    @objid ("2295ae0f-8539-4c6d-8063-f55806a9c094")
    private Button validationButton;

    @objid ("12788665-a8eb-4df2-8069-b6c942be9791")
    private Button cancelButton;

    /**
     * This methods return the Cancel Button
     * @return SWT button
     */
    @objid ("72c6e07f-9459-49f1-91a7-e6869c01537e")
    public Button getCancelButton() {
        return this.cancelButton;
    }

    /**
     * This methods return the Validation Button
     * @return SWT button
     */
    @objid ("640fc381-5fa5-4811-b19b-233e96f4b62e")
    public Button getValidationButton() {
        return this.validationButton;
    }

    /**
     * Contructor of the composite.
     * it needs a parent composite, its SWT style, the label of the cancellation button and the label of the validation button
     * @param parent : the parent composition
     * @param style : the SWT style
     * @param cancelField : the label of the cancellation button
     * @param validateField : the label of the validation button
     */
    @objid ("674ad497-ccd2-47af-a4d5-d6e714dde9b8")
    public ValidationBoutonComposite(final Composite parent, final int style, final String cancelField, final String validateField) {
        super(parent, style);
        setLayout(new FormLayout());
              
        this.validationButton = new Button(this, SWT.NONE);
        final FormData fd_validationButton = new FormData();
        fd_validationButton.right = new FormAttachment(100, -104);
        fd_validationButton.left = new FormAttachment(100, -183);
        this.validationButton.setLayoutData(fd_validationButton);
        this.validationButton.setText(validateField);
        
        this.cancelButton = new Button(this, SWT.NONE);
        fd_validationButton.bottom = new FormAttachment(this.cancelButton, 0, SWT.BOTTOM);
        final FormData fd_cancelButton = new FormData();
        fd_cancelButton.right = new FormAttachment(100, -5);
        fd_cancelButton.left = new FormAttachment(this.validationButton, 10, SWT.DEFAULT);
        this. cancelButton.setLayoutData(fd_cancelButton);
        this.cancelButton.setText(cancelField);
    }

}
