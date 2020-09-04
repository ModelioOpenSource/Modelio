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

package org.modelio.diagram.editor.gmdbg;

import java.util.Objects;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;

/**
 * Dialog displaying the Gm tree of an {@link IGmDiagram} for debugging purpose.
 * <p>
 * Opens with the "<b>M1 + g, M1 + d, M1 + b</b>" key sequence after clicking inside an opened diagram.
 * </p>
 */
@objid ("1d8a609b-d609-49fd-9b47-d6abb93fe4c4")
public class GmDebugger extends Dialog {
    @objid ("45752e8f-a2eb-4e26-ad23-8d8811863f87")
    @Inject
    private ESelectionService selectionService;

    @objid ("c4564aa9-7392-44d4-a1cc-1b4900a5ca6e")
    private ISelectionListener selectionListener = null;

    @objid ("2383f55e-3a53-401c-8989-c198b8b4a81c")
    private static GmDebugger instance;

    @objid ("a1ea3dad-8a09-440d-af6b-5610ac4f7a1c")
    private GmTreeView gmTreeView;

    @objid ("f3c9d989-6c56-453c-b89f-255a520ca581")
    private GmPropView gmPropView;

    @objid ("f296d9ec-fbb1-4082-b80a-7921038a4521")
    private FigureView figPropView;

    @objid ("6cfed21e-b5f4-4a62-b26c-07774c10f8f5")
    private AbstractDiagramEditPart diagramEditPart;

    @objid ("edc80141-1060-4345-9ee9-e989e3a29943")
    private EditPartView editPartView;

    @objid ("8d4e89f2-c890-4ecc-8be2-97e78a1ad8b2")
    private GmAbstractObject gmModel;

    /**
     * @return the only instance of of {@link GmDebugger}.
     */
    @objid ("1ec4910b-7f9a-4db2-b2fe-f2b4c2d431ca")
    public static synchronized GmDebugger getInstance() {
        if (GmDebugger.instance == null) {
            GmDebugger.instance = new GmDebugger(Display.getCurrent().getActiveShell());
            GmDebugger.instance.open();
        }
        return GmDebugger.instance;
    }

    @objid ("10e6cf6c-9bea-4f58-aacf-48af2733f4bc")
    @Override
    protected Control createDialogArea(Composite parent) {
        SashForm sash = new SashForm(parent, SWT.BORDER | SWT.VERTICAL);
        sash.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        
        
        /*
         * The Gm tree
         */
        this.gmTreeView = new GmTreeView(sash);
        this.gmTreeView.getTreeViewer().getControl().setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true));
        
        
        
        SashForm sash3 = new SashForm(sash, SWT.BORDER | SWT.HORIZONTAL);
        sash3.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        /*
         * The Gm properties
         */
        this.gmPropView = new GmPropView(sash3);
        this.gmPropView.getTableViewer().getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
        
        /*
         * The editpart properties
         */
        this.editPartView = new EditPartView(sash3);
        this.editPartView.getTableViewer().getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
        
        /*
         * The figure properties
         */
        this.figPropView = new FigureView(sash3);
        this.figPropView.getTableViewer().getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
        
        /*
         * Branch gm tree selection listener
         */
        this.gmTreeView.getTreeViewer().addSelectionChangedListener((event) -> onTreeSelect((IStructuredSelection) event.getSelection()));
        return sash;
    }

    /**
     * Set the tree's input.
     * @param gmDiagram a diagram.
     */
    @objid ("cc3469d5-9d30-468d-b761-1a3254d175ba")
    private GmDebugger(Shell parent) {
        super(parent);
        setBlockOnOpen(false);
    }

    @objid ("99981d52-2105-4a72-81f2-89e54a9f2f8c")
    @Override
    protected void initializeBounds() {
        getShell().setSize(1024, 512);
    }

    @objid ("85c61647-ec08-4981-bf50-8e663903bb1e")
    @Override
    protected int getShellStyle() {
        return SWT.MODELESS | SWT.BORDER | SWT.TITLE | SWT.RESIZE;
    }

    @objid ("d942b77b-c13f-41b7-81a7-702edaa9d879")
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("GmViewer");
    }

    @objid ("06eb0f7c-c7e1-467b-80aa-3f5c87543378")
    @Override
    public boolean close() {
        if (this.selectionListener != null) {
            this.selectionService.removeSelectionListener(this.selectionListener);
            this.selectionListener = null;
        }
        GmDebugger.instance = null;
        this.gmTreeView = null;
        this.gmPropView = null;
        return super.close();
    }

    @objid ("dff6d247-e4b9-469d-97ff-837dcadf21cd")
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
    }

    /**
     * Called when the selection in the Gm tree changes.
     * @param selection
     */
    @objid ("fe89bb42-0133-401f-8864-4c9e4a8cd246")
    private void onTreeSelect(IStructuredSelection selection) {
        if ((selection != null) && !selection.isEmpty()) {
            if (!Objects.equals(SelectionHelper.getFirst(selection, GmAbstractObject.class), this.gmModel)) {
                setInput2(this.diagramEditPart, SelectionHelper.getFirst(selection, GmAbstractObject.class));
            }
        }
    }

    @objid ("0d7d4a49-4c85-4fa5-84bf-dd2f6c12c4cb")
    private void onAppSelect(ISelection selection) {
        Object o = SelectionHelper.getFirst(selection, Object.class);
        
        if (o instanceof AbstractDiagramEditPart) {
            AbstractDiagramEditPart diagramEditpart = (AbstractDiagramEditPart) o;
            setInput2(diagramEditpart, ((AbstractDiagramEditPart) o).getModel());
            return;
        } else if (o instanceof AbstractNodeEditPart) {
            AbstractNodeEditPart nodeEditPart = (AbstractNodeEditPart) o;
        
            RootEditPart root = nodeEditPart.getRoot();
        
            setInput2((AbstractDiagramEditPart) root.getContents(), nodeEditPart.getModel());
        } else if (o instanceof LinkEditPart) {
        
            LinkEditPart linkEditPart = (LinkEditPart) o;
            RootEditPart root = linkEditPart.getRoot();
        
            setInput2((AbstractDiagramEditPart) root.getContents(), linkEditPart.getModel());
        } else {
            // got a non-supported selection
            return;
        }
    }

    /**
     * @param diagramEditPart - the diagram we are working in
     * @param selection - the Gm to select in the tree
     */
    @objid ("443d936c-f24e-4d4a-ab84-8da2e174753b")
    public void setInput2(AbstractDiagramEditPart diagramEditPart, GmAbstractObject selection) {
        this.diagramEditPart = diagramEditPart;
        
        this.gmTreeView.setInput((IGmDiagram) diagramEditPart.getModel());
        
        this.gmModel = selection;
        
        if (selection != null) {
            this.gmTreeView.setSelection(new StructuredSelection(selection), true);
        
            this.gmPropView.setInput(this.gmModel);
        
            GraphicalEditPart selectionEditPart = (GraphicalEditPart) diagramEditPart.getViewer().getEditPartRegistry().getOrDefault(selection, null);
            this.editPartView.setInput(selectionEditPart);
            this.figPropView.setInput(selectionEditPart);
        
        
        } else {
            this.gmTreeView.setSelection(new StructuredSelection(), false);
            this.gmPropView.setInput(null);
            this.editPartView.setInput(null);
            this.figPropView.setInput(null);
        
        }
        
        // Branch a application-level selection listener to catch application-level selection changes
        if (this.selectionListener == null)
        
        {
            this.selectionListener = new ISelectionListener() {
                @Override
                public void selectionChanged(MPart part, Object selection) {
                    onAppSelect((ISelection) selection);
                }
            };
            this.selectionService.addSelectionListener(this.selectionListener);
        }
    }

}
