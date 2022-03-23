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
package org.modelio.diagram.elements.umlcommon.diagramview.diagramselection;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.ui.dialog.ModelioDialog;

/**
 * Dialog used to select a diagram amonst a list.
 */
@objid ("fbf3eca2-7088-4d59-a3df-91a6fea67b3b")
public class DiagramSelectionPopup extends ModelioDialog {
    @objid ("34b59a1d-8460-44d4-95ec-268ef1e4a04b")
    protected DiagramSelectionModel model;

    @objid ("29c0e2a0-1936-445e-b30d-fe5298269763")
    protected Composite composite;

    @objid ("8c0ae270-122f-4be5-8b8b-cb8a52d04aaa")
    protected Button okButton;

    /**
     * C'tor.
     * @param shell The shell to use to create this dialog.
     * @param model The model to use for this popup.
     */
    @objid ("c9ccdcf3-dcef-4a78-af5f-bfc7e9d06d41")
    public  DiagramSelectionPopup(final Shell shell, final DiagramSelectionModel model) {
        super(shell);
        this.model = model;
        setShellStyle(SWT.TITLE |
                SWT.RESIZE |
                SWT.BORDER |
                SWT.APPLICATION_MODAL |
                Window.getDefaultOrientation());
        
    }

    @objid ("bf427f04-6d1f-4653-ac00-35318d97dc66")
    @Override
    protected boolean canHandleShellCloseEvent() {
        return false;
    }

    @objid ("67df0d3a-25bf-4928-a1d8-c8cdc426c673")
    @Override
    public Control createContentArea(final Composite parent) {
        this.composite = new Composite(parent, SWT.NONE);
        this.composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.composite.setLayout(new GridLayout(1, true));
        
        TreeViewer treeViewer = new TreeViewer(this.composite, SWT.BORDER);
        treeViewer.setContentProvider(new TypeSelectionContentProvider());
        treeViewer.setLabelProvider(new TypeSelectionLabelProvider());
        treeViewer.setComparator(new ViewerComparator());
        treeViewer.setInput(this.model);
        treeViewer.expandAll();
        treeViewer.setAutoExpandLevel(2);
        treeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (selection instanceof StructuredSelection) {
                    AbstractDiagram selectedDiagram = (AbstractDiagram) ((StructuredSelection) selection).getFirstElement();
                    DiagramSelectionPopup.this.model.setSelected(selectedDiagram);
                    DiagramSelectionPopup.this.okButton.setEnabled(!((StructuredSelection) selection).isEmpty());
                }
            }
        });
        return this.composite;
    }

    @objid ("e5143b32-1014-410a-9ca8-28b8533d97be")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        this.okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        
    }

    @objid ("fb594973-30de-4142-afc9-4481a362932a")
    @Override
    public void init() {
        // Put the window title, dialog title and dialog message
        getShell().setText(DiagramElements.I18N.getMessage("DiagramSelectionPopup.WindowTitle"));
        setTitle(DiagramElements.I18N.getMessage("DiagramSelectionPopup.DialogTitle"));
        setMessage(DiagramElements.I18N.getMessage("DiagramSelectionPopup.DialogMessage"));
        
        // Set minimum size
        getShell().setMinimumSize(400, 300);
        
    }

    @objid ("b6d2e91e-e3b5-48fb-ab79-6d5f71075216")
    @Override
    protected Point getInitialSize() {
        return new Point(400, 400);
    }

    @objid ("727e8507-bfe3-4c0f-b171-76da43eea610")
    @Override
    protected Point getInitialLocation(final Point initialSize) {
        return super.getInitialLocation(initialSize);
    }

    /**
     * Label and image provider for the type selection dialog.
     */
    @objid ("9c7c0f83-1419-443e-bde8-a0dd143b8c37")
    private static class TypeSelectionLabelProvider extends LabelProvider {
        @objid ("7623c44e-0182-4e76-91fa-234c1d1e3a27")
        public  TypeSelectionLabelProvider() {
            super();
        }

        @objid ("2c18ad72-3095-4ca3-aff2-0c67c04258ec")
        @Override
        public Image getImage(final Object obj) {
            if (obj instanceof AbstractDiagram) {
                return ElementImageService.getIcon((AbstractDiagram) obj);
            }
            return super.getImage(obj);
        }

        @objid ("06a87850-6e9e-447a-83ec-1ac734ad7ced")
        @Override
        public String getText(final Object obj) {
            if (obj instanceof AbstractDiagram) {
                return ((AbstractDiagram) obj).getName();
            }
            return super.getText(obj);
        }

    }

    /**
     * Content provider for the type selection dialog.
     */
    @objid ("76ef302b-abcf-43d4-9b7c-d70421f1d1b4")
    private static class TypeSelectionContentProvider implements ITreeContentProvider {
        @objid ("8b4d786f-1ae3-4bba-a9da-320663e2714b")
        public  TypeSelectionContentProvider() {
            super();
        }

        @objid ("fa1a65fa-241c-427e-a9b5-c7827f2ec405")
        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // Nothing to do.
        }

        @objid ("87cee8ff-6167-4cf4-8353-e4ac7978c06a")
        @Override
        public void dispose() {
            // Nothing to do.
        }

        @objid ("eb8f21d4-c9bf-4b84-b590-56789c16eb0b")
        @Override
        public boolean hasChildren(final Object element) {
            return element instanceof DiagramSelectionModel;
        }

        @objid ("571bf089-7a20-4389-85c0-18c729358b21")
        @Override
        public Object getParent(final Object element) {
            return null;
        }

        @objid ("c7d96cd9-1262-4f71-ab14-aadd73d1d96d")
        @Override
        public Object[] getElements(final Object inputElement) {
            return getChildren(inputElement);
        }

        @objid ("d293bce8-328f-4ed2-b0a1-5b611f1cd377")
        @Override
        public Object[] getChildren(final Object parentElement) {
            if (parentElement instanceof DiagramSelectionModel) {
                return ((DiagramSelectionModel) parentElement).getAllowed().toArray();
            }
            return null;
        }

    }

}
