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

package org.modelio.linkeditor.ext.depfilter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.linkeditor.ext.plugin.LinkEditorOrg;
import org.modelio.platform.ui.dialog.ModelioDialog;

@objid ("1b61a65b-5e33-11e2-b81d-002564c97630")
class DialogView extends ModelioDialog {
    @objid ("1b61a65c-5e33-11e2-b81d-002564c97630")
    private DialogModel model;

    @objid ("4efcc4f6-4c55-4499-85fc-0e430aed7794")
    private TreeViewer leftTree;

    @objid ("91043ce5-06f9-4054-b021-76e3f412c313")
    private TreeViewer rightTree;

    @objid ("c97d7d16-f1e4-43a4-851d-a3f46c0e757b")
    private Composite composite;

    @objid ("546c9622-7776-4a0d-a0be-18bff93c3a84")
    private Composite buttonsZone;

    @objid ("92c055b6-0fe3-4a6a-b49e-1d9f4e3ac022")
    private Button add;

    @objid ("f00acc74-4358-41cf-a6c9-7e558d1bcbc1")
    private Button addAll;

    @objid ("c775a927-1cbf-41c0-85b9-9ee425df1318")
    private Button remove;

    @objid ("c305c8e0-ad88-4c2e-9382-293a514e7d57")
    private Button removeAll;

    @objid ("1b64076b-5e33-11e2-b81d-002564c97630")
    protected DialogView(final Shell parentShell, final DialogModel model) {
        super(parentShell);
        this.setModel(model);
        setShellStyle(SWT.CLOSE |
                      SWT.MIN |
                      SWT.MAX |
                      SWT.RESIZE |
                      SWT.TITLE |
                      SWT.BORDER |
                      SWT.APPLICATION_MODAL |
                      getDefaultOrientation());
    }

    @objid ("1b640771-5e33-11e2-b81d-002564c97630")
    @Override
    public Control createContentArea(final Composite parent) {
        this.composite = new Composite(parent, SWT.NONE);
        this.composite.setLayout(new FormLayout());
        this.composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        // Left tree
        this.setLeftTree(new TreeViewer(this.composite, SWT.MULTI | SWT.BORDER));
        FormData leftData = new FormData();
        leftData.top = new FormAttachment(0, 5);
        leftData.bottom = new FormAttachment(100, 0);
        leftData.right = new FormAttachment(40, -5);
        leftData.left = new FormAttachment(0, 5);
        this.getLeftTree().getTree().setLayoutData(leftData);
        this.getLeftTree().setContentProvider(new NotFilterContentProvider());
        this.getLeftTree().setSorter(new ViewerSorter());
        this.getLeftTree().setLabelProvider(new CommonLabelProvider());
        this.getLeftTree().setInput(this.getModel());
        this.getLeftTree().expandAll();
        this.getLeftTree().setAutoExpandLevel(2);
        
        // Right tree
        this.setRightTree(new TreeViewer(this.composite, SWT.MULTI | SWT.BORDER));
        FormData rightData = new FormData();
        rightData.top = new FormAttachment(0, 5);
        rightData.bottom = new FormAttachment(100, 0);
        rightData.right = new FormAttachment(100, -5);
        rightData.left = new FormAttachment(60, 5);
        this.getRightTree().getTree().setLayoutData(rightData);
        this.getRightTree().setContentProvider(new FilterContentProvider());
        this.getRightTree().setSorter(new ViewerSorter());
        this.getRightTree().setLabelProvider(new CommonLabelProvider());
        this.getRightTree().setInput(this.getModel());
        this.getRightTree().expandAll();
        this.getRightTree().setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
        
        // Buttons
        this.buttonsZone = new Composite(this.composite, SWT.NONE);
        FormData buttonsZoneData = new FormData();
        buttonsZoneData.top = new FormAttachment(40, 5);
        //buttonsZoneData.bottom = new FormAttachment(100, -5);
        buttonsZoneData.right = new FormAttachment(this.getRightTree().getTree(), -5);
        buttonsZoneData.left = new FormAttachment(this.getLeftTree().getTree(), 5);
        FillLayout layout = new FillLayout(SWT.VERTICAL);
        layout.spacing = 5;
        this.buttonsZone.setLayout(layout);
        this.buttonsZone.setLayoutData(buttonsZoneData);
        this.add = new Button(this.buttonsZone, SWT.PUSH);
        this.add.setText(LinkEditorOrg.I18N.getString("Add"));
        this.add.addSelectionListener(new AddButtonSelectionListener(this));
        
        this.addAll = new Button(this.buttonsZone, SWT.PUSH);
        this.addAll.setText(LinkEditorOrg.I18N.getString("AddAll"));
        this.addAll.addSelectionListener(new AddAllButtonSelectionListener(this));
        
        this.remove = new Button(this.buttonsZone, SWT.PUSH);
        this.remove.setText(LinkEditorOrg.I18N.getString("Remove"));
        this.remove.addSelectionListener(new RemoveButtonSelectionListener(this));
        this.removeAll = new Button(this.buttonsZone, SWT.PUSH);
        this.removeAll.setText(LinkEditorOrg.I18N.getString("RemoveAll"));
        this.removeAll.addSelectionListener(new RemoveAllButtonSelectionListener(this));
        return this.composite;
    }

    @objid ("1b640778-5e33-11e2-b81d-002564c97630")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    @objid ("1b64077d-5e33-11e2-b81d-002564c97630")
    @Override
    public void init() {
        // TODO Logo
        //setLogoImage(null);
        // Put the Window title, title and message
        getShell().setText(LinkEditorOrg.I18N.getMessage("DialogView.WindowTitle"));
        setTitle(LinkEditorOrg.I18N.getMessage("DialogView.DialogTitle"));
        setMessage(LinkEditorOrg.I18N.getMessage("DialogView.DialogMessage"));
        
        // Set size
        this.getShell().setSize(800, 600);
        this.getShell().setMinimumSize(800, 600);
    }

    @objid ("1b640780-5e33-11e2-b81d-002564c97630")
    private void setModel(final DialogModel model) {
        this.model = model;
    }

    @objid ("1b640784-5e33-11e2-b81d-002564c97630")
    DialogModel getModel() {
        return this.model;
    }

    @objid ("1b640788-5e33-11e2-b81d-002564c97630")
    private void setLeftTree(final TreeViewer leftTree) {
        this.leftTree = leftTree;
    }

    @objid ("1b64078c-5e33-11e2-b81d-002564c97630")
    TreeViewer getLeftTree() {
        return this.leftTree;
    }

    @objid ("1b640790-5e33-11e2-b81d-002564c97630")
    private void setRightTree(final TreeViewer rightTree) {
        this.rightTree = rightTree;
    }

    @objid ("1b640794-5e33-11e2-b81d-002564c97630")
    TreeViewer getRightTree() {
        return this.rightTree;
    }

}
