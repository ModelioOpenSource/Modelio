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
package org.modelio.linkeditor.gef.background.typeselection;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.linkeditor.LinkTypeDescriptor;
import org.modelio.linkeditor.plugin.LinkEditor;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.mda.infra.ModuleI18NService;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.ui.dialog.ModelioDialog;

/**
 * Dialog used to resolve ambiguity on the type a link to create
 */
@objid ("1b9865cd-5e33-11e2-b81d-002564c97630")
public class LinkTypeSelectionDialog extends ModelioDialog {
    @objid ("1b9865cf-5e33-11e2-b81d-002564c97630")
    TypeSelectionModel model;

    @objid ("a35a9ee8-3679-4649-a69e-e089eccebeb4")
    private Composite composite;

    @objid ("be001ecf-b935-4fc0-93af-1605dd3b0a94")
    private Button okButton;

    /**
     * C'tor.
     * @param shell The shell to use to create this dialog.
     * @param model The model to use for this popup.
     */
    @objid ("1b9865d2-5e33-11e2-b81d-002564c97630")
    public  LinkTypeSelectionDialog(final Shell shell, final TypeSelectionModel model) {
        super(shell);
        this.model = model;
        setShellStyle(SWT.CLOSE |
                SWT.MIN |
                SWT.MAX |
                SWT.RESIZE |
                SWT.TITLE |
                SWT.BORDER |
                SWT.APPLICATION_MODAL |
                Window.getDefaultOrientation());
        
    }

    @objid ("1b9865d9-5e33-11e2-b81d-002564c97630")
    @Override
    public Control createContentArea(final Composite parent) {
        this.composite = new Composite(parent, SWT.NONE);
        this.composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.composite.setLayout(new GridLayout(1, true));
        TreeViewer treeViewer = new TreeViewer(this.composite, SWT.BORDER);
        treeViewer.setContentProvider(new TypeSelectionContentProvider());
        treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new TypeSelectionLabelProvider(treeViewer)));
        treeViewer.setComparator(new ViewerComparator() {
            @Override
            public int category(Object element) {
                if (element instanceof LinkTypeDescriptor) {
                    LinkTypeDescriptor descriptor = (LinkTypeDescriptor) element;
                    Stereotype stereotype = descriptor.getStereotype();
                    if (stereotype != null) {
                        return ModuleI18NService.getPriority(stereotype.getModule());
                    }
                }
                return Integer.MAX_VALUE;
            }
        });
        treeViewer.setInput(this.model);
        
        treeViewer.expandAll();
        treeViewer.setAutoExpandLevel(2);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        treeViewer.getTree().setLayoutData(gridData);
        
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                LinkTypeDescriptor type = SelectionHelper.getFirst(selection, LinkTypeDescriptor.class);
                LinkTypeSelectionDialog.this.model.setSelectedType(type);
                updateButtons(type);
            }
        });
        return this.composite;
    }

    @objid ("1b9865e0-5e33-11e2-b81d-002564c97630")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        this.okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        
    }

    @objid ("1b9865e5-5e33-11e2-b81d-002564c97630")
    @Override
    public void init() {
        // Put the window title, dialog title and dialog message
        getShell().setText(LinkEditor.I18N.getMessage("TypeSelectionPopup.WindowTitle"));
        setTitle(LinkEditor.I18N.getMessage("TypeSelectionPopup.DialogTitle"));
        setMessage(LinkEditor.I18N.getMessage("TypeSelectionPopup.DialogMessage"));
        
        // Set minimum size
        getShell().setMinimumSize(400, 300);
        
    }

    @objid ("1b9865e8-5e33-11e2-b81d-002564c97630")
    @Override
    protected Point getInitialSize() {
        return new Point(400, 400);
    }

    @objid ("1b9ac6ee-5e33-11e2-b81d-002564c97630")
    void updateButtons(final Object selection) {
        if (selection instanceof LinkTypeDescriptor) {
            this.okButton.setEnabled(true);
        } else {
            this.okButton.setEnabled(false);
        }
        
    }

}
