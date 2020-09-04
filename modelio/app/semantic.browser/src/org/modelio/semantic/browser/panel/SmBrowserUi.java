/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.semantic.browser.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.core.ui.swt.dnd.MObjectViewerDragProvider;
import org.modelio.core.ui.swt.images.ElementDecoratedStyledLabelProvider;
import org.modelio.gproject.gproject.GProject;

@objid ("ec755c58-54e5-47e9-8715-712a04dc971f")
class SmBrowserUi {
    @objid ("29471303-3ca5-4456-8b77-d7ce8a8884fa")
    private static final String SEMANTICBROWSER_POPUP_ID = "org.modelio.semanticbrowser.popupmenu";

    @objid ("48868eb9-8ee2-428d-86f6-f1af4eafe672")
    private TreeViewer treeViewer;

    @objid ("af62893c-7aec-4fdd-8a1d-ce73c5cba917")
    private Composite composite;

    @objid ("d4fb6483-af92-4777-b449-f67ed06cf082")
    private final SmBrowserController controller;

    @objid ("63bcb742-a613-42f9-8416-2f45553bd5fc")
    private ElementDecoratedStyledLabelProvider labelProvider;

    @objid ("8102e669-9208-4b3d-a8f6-2311e0bc09d6")
    private SmBrowserContentProvider contentProvider;

    @objid ("faaca18d-66bf-4e07-a104-55508323c961")
    SmBrowserUi(Composite parent, SmBrowserController controller, EMenuService menuService) {
        this.controller = controller;
        this.composite = new Composite(parent, SWT.NONE);
        this.composite.setLayout(new FillLayout(SWT.VERTICAL));
        this.treeViewer = new TreeViewer(this.composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        this.treeViewer.setUseHashlookup(true);
        
        // Set up a label provider
        this.labelProvider = new ElementDecoratedStyledLabelProvider(new SmBrowserLabelProvider(), false, false);
        // Set up a content provider
        this.contentProvider = new SmBrowserContentProvider();
        
        this.treeViewer.setContentProvider(this.contentProvider);
        this.treeViewer.setLabelProvider(this.labelProvider);
        
        MObjectViewerDragProvider dragListener = new MObjectViewerDragProvider(this.treeViewer);
        this.treeViewer.addDragSupport(DND.DROP_MOVE | DND.DROP_COPY, new Transfer[] { ModelElementTransfer.getInstance() }, dragListener);
        
        // Add the selection provider
        this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                SmBrowserUi.this.controller.onSelectionChanged(event.getSelection());
            }
        });
        
        menuService.registerContextMenu(this.treeViewer.getTree(), SmBrowserUi.SEMANTICBROWSER_POPUP_ID);
    }

    /**
     * When changing the project being displayed by the view, new ContenProvider and LabelProvider are created and configured
     * 
     * @param project might be null
     */
    @objid ("96905266-2901-4323-bafa-16d7a7fe9a78")
    void setInput(GProject project) {
        this.treeViewer.setInput(project);
    }

    @objid ("2a4d9fc6-801e-490f-91c9-3a98746b98cd")
    Composite getComposite() {
        return this.composite;
    }

    @objid ("245c0280-361e-4cd6-b192-db1649fc8122")
    public void setSelection(IStructuredSelection selection) {
        this.treeViewer.setSelection(selection, true);
    }

    @objid ("a8f6bebb-bcc8-4046-8444-03ba05de2040")
    void addSelectionChangedListener(ISelectionChangedListener l) {
        if (this.treeViewer != null && !this.treeViewer.getTree().isDisposed()) {
            this.treeViewer.addSelectionChangedListener(l);
        }
    }

    @objid ("d96d180b-bda7-44a4-88f9-c5cfd8d1549f")
    public ISelection getSelection() {
        return this.treeViewer.getSelection();
    }

    /**
     * Remove a previously registered selection change listener
     * @param l
     */
    @objid ("3cb1560c-68fd-42b1-b2c2-7f4e2d4f1e93")
    void removeSelectionChangedListener(ISelectionChangedListener l) {
        if (this.treeViewer != null && !this.treeViewer.getTree().isDisposed()) {
            this.treeViewer.removeSelectionChangedListener(l);
        }
    }

}
