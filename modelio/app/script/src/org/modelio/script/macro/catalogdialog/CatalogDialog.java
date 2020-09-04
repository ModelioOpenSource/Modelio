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

package org.modelio.script.macro.catalogdialog;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.modelio.script.macro.IMacroService.Scope;
import org.modelio.script.macro.IMacroService;
import org.modelio.script.macro.catalog.Catalog;
import org.modelio.script.macro.catalog.Macro;
import org.modelio.script.plugin.Script;
import org.modelio.ui.dialog.ModelioDialog;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Macro catalog edition dialog box.
 */
@objid ("0069666c-c497-106a-bf4f-001ec947cd2a")
public class CatalogDialog extends ModelioDialog {
    @objid ("006a9c76-c497-106a-bf4f-001ec947cd2a")
    private CatalogContentProvider provider;

    @objid ("006972c4-c497-106a-bf4f-001ec947cd2a")
    protected Macro selectedScript = null;

    @objid ("003fd1da-f1bd-106a-bf4f-001ec947cd2a")
    protected Button addMacroFromFileButton;

    @objid ("003fd95a-f1bd-106a-bf4f-001ec947cd2a")
    protected Button editMacroButton;

    @objid ("003ffd22-f1bd-106a-bf4f-001ec947cd2a")
    protected TreeViewer treeviewer;

    @objid ("004fc95a-faec-106a-bf4f-001ec947cd2a")
    protected Button removeMacroButton;

    @objid ("004fd332-faec-106a-bf4f-001ec947cd2a")
    protected Button runMacroButton;

    @objid ("b136a183-e03d-4163-9848-1363213b667b")
    protected List<MObject> selectedElements;

    @objid ("32d38702-f198-4a05-80e2-e34721761c13")
    private IMacroService macroService;

    /**
     * C'tor initializing the dialog.
     * 
     * @param parentShell the parent shell to attach the dialog to.
     * @param macroService the macro service, to execute macros.
     * @param selectedElements the current selection.
     */
    @objid ("00697af8-c497-106a-bf4f-001ec947cd2a")
    public CatalogDialog(Shell parentShell, IMacroService macroService, List<MObject> selectedElements) {
        super(parentShell);
        this.selectedElements = selectedElements;
        this.macroService = macroService;
        this.provider = new CatalogContentProvider();
        
        this.provider.addCatalog((macroService.getCatalog(IMacroService.Scope.MODELIO)));
        this.provider.addCatalog((macroService.getCatalog(Scope.WORSPACE)));
        Catalog projectCatalog = macroService.getCatalog(Scope.PROJECT);
        if (projectCatalog != null) {
            this.provider.addCatalog((projectCatalog));
        }
    }

    @objid ("006a9da2-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
    }

    @objid ("006a9e38-c497-106a-bf4f-001ec947cd2a")
    @Override
    public Control createContentArea(Composite parent) {
        // Catalog group:
        final Group catalogPanel = new Group(parent, SWT.SHADOW_ETCHED_IN);
        catalogPanel.setText(Script.I18N.getString("CatalogDialog.CatalogGroup"));
        catalogPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        this.treeviewer = new TreeViewer(catalogPanel, SWT.NONE);
        this.treeviewer.setContentProvider(this.provider);
        this.treeviewer.setLabelProvider(new CatalogLabelProvider());
        this.treeviewer.addSelectionChangedListener(new TreeSelectionChangedListener());
        
        // Catalog buttons
        Composite catalogToolbar = new Composite(catalogPanel, SWT.NONE);
        GridDataFactory.defaultsFor(catalogToolbar).align(SWT.END, SWT.BEGINNING) // align
                                                                                  // on
                                                                                  // the
                                                                                  // right
                .grab(false, false).applyTo(catalogToolbar);
        
        // Add macro from file button
        this.addMacroFromFileButton = new Button(catalogToolbar, SWT.PUSH);
        this.addMacroFromFileButton.setText(Script.I18N.getString("CatalogDialog.AddButton.label"));
        this.addMacroFromFileButton.setToolTipText(Script.I18N.getString("CatalogDialog.AddButton.tooltip"));
        this.addMacroFromFileButton.addSelectionListener(new NewMacroFromFileButtonListener());
        
        // Add macro from script view button
        this.editMacroButton = new Button(catalogToolbar, SWT.PUSH);
        this.editMacroButton.setText(Script.I18N.getString("CatalogDialog.EditButton.label"));
        this.editMacroButton.setToolTipText(Script.I18N.getString("CatalogDialog.EditButton.tooltip"));
        this.editMacroButton.addSelectionListener(new EditMacroButtonListener());
        
        // Remove macro button
        this.removeMacroButton = new Button(catalogToolbar, SWT.PUSH);
        this.removeMacroButton.setText(Script.I18N.getString("CatalogDialog.DeleteButton.label"));
        this.removeMacroButton.setToolTipText(Script.I18N.getString("CatalogDialog.DeleteButton.tooltip"));
        this.removeMacroButton.addSelectionListener(new DeleteMacroButtonListener());
        
        // Run macro button
        this.runMacroButton = new Button(catalogToolbar, SWT.PUSH);
        this.runMacroButton.setText(Script.I18N.getString("CatalogDialog.RunButton.label"));
        this.runMacroButton.setToolTipText(Script.I18N.getString("CatalogDialog.RunButton.tooltip"));
        this.runMacroButton.addSelectionListener(new RunMacroButtonListener(this.treeviewer));
        this.runMacroButton.setToolTipText(null);
        
        GridLayoutFactory.fillDefaults().numColumns(4).generateLayout(catalogToolbar);
        GridLayoutFactory.swtDefaults().generateLayout(catalogPanel);
        
        this.treeviewer.setInput(1);
        this.treeviewer.setExpandedElements(this.provider.getCatalogs().toArray());
        this.treeviewer.refresh();
        return catalogPanel;
    }

    @objid ("006a9f6e-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void init() {
        getShell().setText(Script.I18N.getString("CatalogDialog.Title"));
        setTitle(Script.I18N.getString("CatalogDialog.Title"));
        setMessage(Script.I18N.getString("CatalogDialog.Description"));
        getShell().setMinimumSize(400, 400);
    }

    /**
     * On cancel pressed: do not save any catalog, clean up the provider
     */
    @objid ("006a9ffa-c497-106a-bf4f-001ec947cd2a")
    @Override
    protected void cancelPressed() {
        this.provider.dispose();
        this.provider = null;
        super.cancelPressed();
    }

    /**
     * On ok pressed, save all the provided catalogs and clean up the provider
     */
    @objid ("006aa0a4-c497-106a-bf4f-001ec947cd2a")
    @Override
    protected void okPressed() {
        this.provider.save();
        for (Catalog catalog : this.provider.getCatalogs()) {
            catalog.save();
        }
        
        this.provider.dispose();
        this.provider = null;
        super.okPressed();
    }

    /**
     * Update the buttons and their enabled/disabled status when the tree selection changes
     */
    @objid ("006966ee-c497-106a-bf4f-001ec947cd2a")
    private class TreeSelectionChangedListener implements ISelectionChangedListener {
        @objid ("006a3010-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
            ISelection newSelection = event.getSelection();
            // if (newSelection instanceof IStructuredSelection) {
            Object selectionElement = ((IStructuredSelection) newSelection).getFirstElement();
            
            if (selectionElement != null) {
                // if (CatalogDialog.this.selectedScript != null &&
                // !CatalogDialog.this.selectedScript.equals(selectionElement))
                // {
                // CatalogDialog.this.treeviewer.setSelection(new
                // StructuredSelection(CatalogDialog.this.selectedScript));
                // return;
                // }
            
                if (selectionElement instanceof Macro) {
                    Macro selectedMacro = (Macro) selectionElement;
                    CatalogDialog.this.runMacroButton.setEnabled(isMacroRunnable((Macro) selectionElement));
                    CatalogDialog.this.editMacroButton.setEnabled(selectedMacro.getCatalog().isModifiable());
                    CatalogDialog.this.removeMacroButton.setEnabled(selectedMacro.getCatalog().isModifiable());
                    CatalogDialog.this.addMacroFromFileButton.setEnabled(false);
                    return;
                }
            
                if (selectionElement instanceof Catalog && ((Catalog) selectionElement).isModifiable()) {
                    CatalogDialog.this.addMacroFromFileButton.setEnabled(true);
                    CatalogDialog.this.removeMacroButton.setEnabled(false);
                    CatalogDialog.this.editMacroButton.setEnabled(false);
                    CatalogDialog.this.runMacroButton.setEnabled(false);
                    return;
                }
            }
            // any other case is unknown, disable all buttons
            CatalogDialog.this.addMacroFromFileButton.setEnabled(false);
            CatalogDialog.this.editMacroButton.setEnabled(false);
            CatalogDialog.this.removeMacroButton.setEnabled(false);
            CatalogDialog.this.runMacroButton.setEnabled(false);
        }

        @objid ("5983ac16-b8dd-4593-972a-5545293746e6")
        public TreeSelectionChangedListener() {
            //
        }

        @objid ("4691c492-534b-4a67-bb54-5f104620adc3")
        private boolean isMacroRunnable(Macro macro) {
            boolean runnable = true;
            if (!(macro.getMetaclasses().isEmpty())) {
                if (!CatalogDialog.this.selectedElements.isEmpty()) {
                    MObject firstElement = CatalogDialog.this.selectedElements.iterator().next();
                    MMetamodel metamodel = firstElement.getMClass().getMetamodel();
            
                    if (!macro.getMetaclasses().isEmpty()) {
                        runnable = false;
                        for (String acceptMClassName : macro.getMetaclasses()) {
                            MClass mClass = metamodel.getMClass(acceptMClassName);
                            Class<? extends MObject> clazz = mClass.getJavaInterface();
                            for (MObject selected : CatalogDialog.this.selectedElements) {
                                if (clazz.isInstance(selected)) {
                                    runnable = true;
                                    break;
                                }
                            }
                            if (runnable) {
                                break;
                            }
                        }
                    }
                }
            }
            return runnable;
        }

    }

    /**
     * New macro from file button listener
     */
    @objid ("0069677a-c497-106a-bf4f-001ec947cd2a")
    private class NewMacroFromFileButtonListener implements SelectionListener {
        @objid ("006a3196-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
            //
        }

        @objid ("006a322c-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void widgetSelected(SelectionEvent e) {
            ISelection iSelection = CatalogDialog.this.treeviewer.getSelection();
            Object selection = ((IStructuredSelection) iSelection).getFirstElement();
            if (selection instanceof Catalog) {
                Catalog catalog = (Catalog) selection;
                MacroDialog dlg = new MacroDialog(getShell(), catalog);
                dlg.open();
                CatalogDialog.this.treeviewer.refresh(true);
            }
        }

        @objid ("cd90c4cc-46fd-4ebc-b422-c073af8bf373")
        public NewMacroFromFileButtonListener() {
            //
        }

    }

    /**
     * Delete macro button listener
     */
    @objid ("00696810-c497-106a-bf4f-001ec947cd2a")
    private class DeleteMacroButtonListener implements SelectionListener {
        @objid ("006a33a8-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
            //
        }

        @objid ("006a343e-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void widgetSelected(SelectionEvent e) {
            ISelection iSelection = CatalogDialog.this.treeviewer.getSelection();
            Object selection = ((IStructuredSelection) iSelection).getFirstElement();
            if (selection instanceof Macro) {
            
                Macro script = (Macro) selection;
                Catalog cat = script.getCatalog();
                cat.removeMacro(script);
            
                CatalogDialog.this.selectedScript = null;
                CatalogDialog.this.treeviewer.refresh(false);
                CatalogDialog.this.treeviewer.setSelection(new StructuredSelection(cat));
            }
        }

        @objid ("a3a96500-bb2d-4715-ad78-03156abfc084")
        public DeleteMacroButtonListener() {
            //
        }

    }

    /**
     * New macro from console button listener
     */
    @objid ("0069689c-c497-106a-bf4f-001ec947cd2a")
    private class EditMacroButtonListener implements SelectionListener {
        @objid ("006a35ba-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
            //
        }

        @objid ("006a3650-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void widgetSelected(SelectionEvent e) {
            ISelection iSelection = CatalogDialog.this.treeviewer.getSelection();
            Object selection = ((IStructuredSelection) iSelection).getFirstElement();
            if (selection instanceof Macro) {
                Macro macroToEdit = (Macro) selection;
            
                MacroDialog dlg = new MacroDialog(getShell(), macroToEdit);
                dlg.open();
            
                // Whatever the result, refresh the view
                CatalogDialog.this.treeviewer.refresh(true);
            }
        }

        @objid ("b1645ea9-53b6-437b-8627-baf57895e101")
        public EditMacroButtonListener() {
            // Empty
        }

    }

}
