/* 
 * Copyright 2013-2020 Modeliosoft
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

package org.modelio.platform.model.ui.dialogs.elementChooser;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.ui.dialog.ModelioDialog;

@objid ("26c956b7-186f-11e2-bc4e-002564c97630")
public class ElementChooserDlg extends ModelioDialog {
    @objid ("26c956b8-186f-11e2-bc4e-002564c97630")
    private Label leftViewerLabel;

    @objid ("26c956b9-186f-11e2-bc4e-002564c97630")
    private StructuredViewer leftViewer;

    @objid ("26c956ba-186f-11e2-bc4e-002564c97630")
    private Element input;

    @objid ("26c956bb-186f-11e2-bc4e-002564c97630")
    private IElementChooserDriver driver;

    @objid ("26c956bc-186f-11e2-bc4e-002564c97630")
    public ElementChooserDlg(Shell parentShell, IElementChooserDriver chooserDriver, ModelElement input) {
        super(parentShell);
        this.driver = chooserDriver;
        this.input = input;
    }

    @objid ("26c956c1-186f-11e2-bc4e-002564c97630")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    @objid ("26cbb80f-186f-11e2-bc4e-002564c97630")
    @Override
    public Control createContentArea(Composite parent) {
        Composite composite = new Composite(parent, 0);
        GridData data = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
        composite.setLayoutData(data);
        composite.setFont(parent.getFont());
        
        GridLayout compositeLayout = new GridLayout(3, false);
        compositeLayout.marginWidth = 3;
        compositeLayout.marginHeight = 3;
        compositeLayout.verticalSpacing = 0;
        compositeLayout.horizontalSpacing = 0;
        composite.setLayout(compositeLayout);
        
        // Create left viewer:
        // --------------------
        Composite leftViewerComposite = new Composite(composite, SWT.NONE);
        GridData leftViewerCompositeData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
        leftViewerComposite.setLayoutData(leftViewerCompositeData);
        FormLayout leftViewerLayout = new FormLayout();
        leftViewerComposite.setLayout(leftViewerLayout);
        
        this.leftViewerLabel = new Label(leftViewerComposite, SWT.NONE);
        this.leftViewerLabel.setText(this.driver.getLeftLabel());
        
        this.leftViewer = this.driver.createViewer(leftViewerComposite);
        FormData leftViewerData = new FormData();
        leftViewerData.left = new FormAttachment(0, 0);
        leftViewerData.right = new FormAttachment(100, 0);
        leftViewerData.top = new FormAttachment(this.leftViewerLabel, 3);
        leftViewerData.bottom = new FormAttachment(100, 0);
        this.leftViewer.getControl().setLayoutData(leftViewerData);
        
        // Validate the box on double click
        this.leftViewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(org.eclipse.jface.viewers.DoubleClickEvent event) {
                okPressed();
            }
        });
        return composite;
    }

    @objid ("26cbb815-186f-11e2-bc4e-002564c97630")
    @Override
    public void init() {
        getShell().setText(this.driver.getShellTitle());
        setTitle(this.driver.getTitle());
        setMessage(this.driver.getMessage());
        
        Shell parentShell = getShell().getParent().getShell();
        Point shellLocation = parentShell.getLocation();
        getShell().setSize(500, 500);
        getShell().setLocation(shellLocation.x + 300, shellLocation.y + 300);
        setLogoImage(null);
        
        this.driver.init(this.input);
    }

    @objid ("26cbb818-186f-11e2-bc4e-002564c97630")
    public void setLeftLabel(String label) {
        this.leftViewerLabel.setText(label);
    }

    @objid ("26cbb81b-186f-11e2-bc4e-002564c97630")
    @Override
    protected void cancelPressed() {
        this.driver.performCancel();
        super.cancelPressed();
    }

    @objid ("26cbb81e-186f-11e2-bc4e-002564c97630")
    @Override
    protected void okPressed() {
        ISelection selection = this.leftViewer.getSelection();
        List<Object> selectedElements = null;
        
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
        
            selectedElements = structuredSelection.toList();
        }
        
        this.driver.performFinish(this.leftViewer, selectedElements);
        super.okPressed();
    }

    @objid ("26cbb821-186f-11e2-bc4e-002564c97630")
    public Label getLeftViewerLabel() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.leftViewerLabel;
    }

    @objid ("26cbb825-186f-11e2-bc4e-002564c97630")
    public StructuredViewer getLeftViewer() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.leftViewer;
    }

    @objid ("26cbb829-186f-11e2-bc4e-002564c97630")
    public Element getInput() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.input;
    }

    @objid ("26cbb82d-186f-11e2-bc4e-002564c97630")
    public IElementChooserDriver getDriver() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.driver;
    }

    @objid ("5b31a8ad-ab32-44de-bdb3-067d1de20a1e")
    @Override
    protected String getHelpId() {
        return CoreUi.I18N.getString("ElementChooserDlg.HelpId");
    }

}
