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

package org.modelio.model.property.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.model.property.panel.actions.CopyAction;
import org.modelio.model.property.panel.actions.CutAction;
import org.modelio.model.property.panel.actions.PasteAction;
import org.modelio.model.property.panel.actions.RemoveAction;
import org.modelio.model.property.panel.data.ContentPanel;
import org.modelio.model.property.panel.data.DataPanelInput;
import org.modelio.model.property.panel.tree.TreePanel;
import org.modelio.model.property.panel.tree.TreePanelInput;

@objid ("e660d121-7bd6-44db-9650-620b8fb3cf2d")
public class ElementPropertyUi {
    /**
     * Minimum and maxim H/W ratio
     */
    @objid ("b2dadf5b-e327-4d35-aca2-50b53ab35d66")
    private static final float HWMIN = (float) 0.6;

    @objid ("6f88cf73-243a-490f-b950-2f7789720668")
    private static final float HWMAX = (float) 0.65;

    @objid ("4e74983c-b25c-4021-b08a-b5e264fce39f")
    private boolean autoLayout;

    @objid ("efdcfab2-5e68-4c1b-9a7d-513bed97b19f")
    private Composite parent;

    @objid ("110b4089-a40b-438b-8cc4-7c7f3ca6e93e")
    private Composite top;

    @objid ("7440c7ad-516d-48d8-b9cf-f2eed440801f")
    private ElementPropertyPanelToolbar toolBar;

    @objid ("3e401760-aadf-4624-acea-a82f8802e422")
    private SashForm shform;

    @objid ("5fc95515-384f-4e67-9cb6-e9e5b10d593e")
    private TreePanel treePanel;

    @objid ("43432c9a-0109-4dc5-b654-082cd062b6f7")
    private ContentPanel contentPanel;

    @objid ("d6a5a54f-12ea-4430-a900-bf247d8fbb58")
    private final ElementPropertyController controller;

    @objid ("f904f775-0124-46cf-ae30-2a439d5e76fc")
    private LayoutChangeListener layoutChangeListener;

    @objid ("879164cf-a270-46c5-be1f-ad6b0c63908a")
    private SelectionChangedListener treeSelectionListener;

    @objid ("2963ac57-4902-4a33-8038-53bc7d99030d")
    public ElementPropertyUi(ElementPropertyController controller) {
        this.controller = controller;
    }

    @objid ("b6589a30-58f5-4ec0-9e3b-3c5f5c7bcd1a")
    public Composite createContents(Composite currentParent) {
        this.parent = currentParent;
        
        // Top level container: a Composite
        this.top = new Composite(currentParent, SWT.NONE);
        final GridLayout gl = new GridLayout(1, true);
        gl.marginBottom = gl.marginTop = gl.marginHeight = 0;
        gl.marginLeft = gl.marginRight = gl.marginWidth = 0;
        gl.horizontalSpacing = gl.verticalSpacing = 0;
        this.top.setLayout(gl);
        
        // The tool bar
        this.toolBar = new ElementPropertyPanelToolbar(this.controller);
        this.toolBar.createPanel(this.top);
        ((Composite) this.toolBar.getPanel()).setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false));
        
        this.shform = new SashForm(this.top, SWT.HORIZONTAL);
        this.shform.setLayout(new FillLayout());
        this.shform.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        this.treePanel = new TreePanel(this.shform, SWT.NONE);
        this.treePanel.setLayout(new FillLayout());
        
        this.contentPanel = new ContentPanel(this.shform, SWT.NONE);
        this.contentPanel.setLayout(new FillLayout());
        
        this.shform.setWeights(new int[] { 30, 70 });
        
        this.layoutChangeListener = new LayoutChangeListener(this);
        enableAutoLayout();
        
        this.treeSelectionListener = new SelectionChangedListener(this.controller);
        this.treePanel.getTreeViewer().addSelectionChangedListener(this.treeSelectionListener);
        currentParent.layout();
        
        // The tree popup menu
        initPopupMenu(this.treePanel.getTreeViewer());
        
        // The tree shortcuts
        initTreeShortcuts();
        return this.top;
    }

    /**
     * Get the top level container of the UI.
     * 
     * @return the top level container of the UI.
     */
    @objid ("0a2f0ccf-d59a-4ee4-8065-af810437f5b5")
    public Composite getComposite() {
        return this.top;
    }

    /**
     * Set the layout to horizontal.
     */
    @objid ("a9415fcf-e70f-472d-a59f-6e939480f4a7")
    public void setHorizontalLayout() {
        this.shform.setOrientation(SWT.HORIZONTAL);
    }

    /**
     * Set the layout to vertical.
     */
    @objid ("814d3477-4709-468b-9636-0fa2c70eae0d")
    public void setVerticalLayout() {
        this.shform.setOrientation(SWT.VERTICAL);
    }

    /**
     * Enable automatic horizontal/vertical layout change when resizing the
     * view.
     */
    @objid ("ff99bc1f-ea49-4335-bc0b-b77b267cb065")
    public void enableAutoLayout() {
        this.layoutChangeListener = new LayoutChangeListener(this);
        this.parent.addControlListener(this.layoutChangeListener);
        autoLayout();
    }

    /**
     * Disable automatic horizontal/vertical layout change when resizing the
     * view.
     */
    @objid ("dfdc7f44-6195-4270-8425-492fb6be4f24")
    public void disableAutoLayout() {
        if (this.layoutChangeListener != null) {
            this.parent.removeControlListener(this.layoutChangeListener);
            this.layoutChangeListener = null;
        }
    }

    /**
     * Automatically choose horizontal or vertical layout depending on parent
     * composite size.
     */
    @objid ("af345025-0159-4f1b-b0f7-7bedfd33454c")
    private void autoLayout() {
        final float ratio = (float) this.parent.getSize().y / (float) this.parent.getSize().x;
        if (ratio < ElementPropertyUi.HWMIN) {
            setHorizontalLayout();
        } else if (ratio > ElementPropertyUi.HWMAX) {
            setVerticalLayout();
        }
    }

    @objid ("0d004ed8-9d50-4897-bdf4-fc84a10df6dd")
    public boolean isAutoLayout() {
        return this.autoLayout;
    }

    @objid ("d5103cb4-4d43-4ec3-a5fb-73d9b12d48b9")
    public boolean isVerticalLayout() {
        return !this.autoLayout && this.shform.getOrientation() == SWT.VERTICAL;
    }

    @objid ("f8f89ee7-2ce0-44a3-abc7-0f078c9402dc")
    public boolean isHorizontalLayout() {
        return !this.autoLayout && this.shform.getOrientation() == SWT.HORIZONTAL;
    }

    @objid ("6ec9f721-50ff-4e16-87fa-bee25b6c3d62")
    public void refreshView() {
        final Tree tree = this.treePanel.getTreeViewer().getTree();
        if (tree == null || tree.isDisposed()) {
            // No graphic control, nothing to update, return.
            return;
        }
        Element me = this.controller.getSelectedElement();
        this.toolBar.setInput(me);
        
        // Force an update by firing a tree selection change
        Object previousTypeItem = this.controller.getSelectedTypeItems().isEmpty() ? null : this.controller.getSelectedTypeItems().get(0);
        this.controller.onTreeSelectionChange(null);
        this.controller.onTreeSelectionChange(previousTypeItem);
    }

    @objid ("8ad7f439-2906-4250-905a-c9b3ce510c83")
    public void setInputs(TreePanelInput treeInput, DataPanelInput dataInput) {
        this.treePanel.setInput(treeInput);
        this.contentPanel.setInput(dataInput);
        this.toolBar.setInput(dataInput.getTypedElement());
    }

    @objid ("cd41a824-371c-459e-9cfe-ad10048b9383")
    public TreePanel getTreePanel() {
        return this.treePanel;
    }

    @objid ("84523e8a-a048-4235-8cad-27af4b2df714")
    private void initTreeShortcuts() {
        this.treePanel.getTreeViewer().getTree().addKeyListener(new KeyAdapter() {
        
            @Override
            public void keyReleased(KeyEvent e) {
                // CTRL-C, CTRL-V, CTRL-X
                if ((e.stateMask &= SWT.MOD1) != 0) {
                    switch (e.keyCode) {
                    case 'c':
                    case 'C':
                        if (ElementPropertyUi.this.controller.canCopyStereotype()) {
                            ElementPropertyUi.this.controller.onCopyStereotype();
                        }
                        break;
                    case 'x':
                    case 'X':
                        if (ElementPropertyUi.this.controller.canCutStereotype()) {
                            ElementPropertyUi.this.controller.onCutStereotype();
                        }
                        break;
                    case 'v':
                    case 'V':
                        if (ElementPropertyUi.this.controller.canPasteStereotype()) {
                            ElementPropertyUi.this.controller.onPasteStereotype();
                        }
                        break;
                    case 'u':
                    case 'U':
                        if (ElementPropertyUi.this.controller.canMoveStereotypeUp()) {
                            ElementPropertyUi.this.controller.onMoveStereotypeUp();
                        }
                        break;
                    case 'd':
                    case 'D':
                        if (ElementPropertyUi.this.controller.canMoveStereotypeDown()) {
                            ElementPropertyUi.this.controller.onMoveStereotypeDown();
                        }
                        break;
                    default:
                        break;
                    }
                } else {
                    // DEL
                    switch (e.keyCode) {
                    case SWT.DEL:
                        if (ElementPropertyUi.this.controller.canRemoveStereotype()) {
                            ElementPropertyUi.this.controller.onRemoveStereotype();
                        }
                        break;
                    default:
                        break;
                    }
                }
        
            }
        
        });
    }

    @objid ("b423d61c-3130-4841-9990-fc5ee70780de")
    private void initPopupMenu(TreeViewer treeViewer) {
        // initalize the context menu
        MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager manager) {
                manager.add(new CopyAction(ElementPropertyUi.this.controller));
                manager.add(new CutAction(ElementPropertyUi.this.controller));
                manager.add(new PasteAction(ElementPropertyUi.this.controller));
                manager.add(new Separator());
                manager.add(new RemoveAction(ElementPropertyUi.this.controller));
            }
        });
        Menu menu = menuMgr.createContextMenu(treeViewer.getTree());
        treeViewer.getTree().setMenu(menu);
    }

    @objid ("fa04acc0-b591-4dbd-bc4c-95367244ed8e")
    private static class LayoutChangeListener implements ControlListener {
        @objid ("41c67a0f-8850-424f-a0d7-f9af50503c95")
        private final ElementPropertyUi view;

        @objid ("132dcab7-9e7c-4591-8243-d970b0fd31ee")
        @Override
        public void controlResized(ControlEvent theEvent) {
            changeLayout(theEvent);
        }

        @objid ("371f5b7c-3ce0-4803-a28a-caca2bdb3dff")
        @Override
        public void controlMoved(ControlEvent theEvent) {
            // Nothing to do
        }

        /**
         * C'tor
         * 
         * @param view the panel view
         */
        @objid ("3ff46de3-8d23-449e-910f-ec0acaa39a86")
        public LayoutChangeListener(final ElementPropertyUi view) {
            super();
            this.view = view;
        }

        @objid ("694014c5-ccba-425b-bf39-6859ab9b102d")
        private void changeLayout(ControlEvent theEvent) {
            final Composite comp = (Composite) theEvent.widget;
            comp.layout();
            this.view.autoLayout();
        }

    }

    /**
     * This listener is the Tree selection listener of the note view. Its
     * responsibility is to update the data panel contents when a particular
     * note is selected in the tree.
     * 
     * @author phv
     */
    @objid ("8faa29d5-c068-11e1-8c0a-002564c97630")
    private static class SelectionChangedListener implements ISelectionChangedListener {
        @objid ("8fac8ae7-c068-11e1-8c0a-002564c97630")
        private final ElementPropertyController controller;

        @objid ("8fac8aed-c068-11e1-8c0a-002564c97630")
        protected SelectionChangedListener(final ElementPropertyController controller) {
            this.controller = controller;
        }

        @objid ("8fac8ae8-c068-11e1-8c0a-002564c97630")
        @Override
        public void selectionChanged(final SelectionChangedEvent event) {
            // This method listen to the selection changes in the tree viewer.
            ISelection selection = event.getSelection();
            
            Object obj = SelectionHelper.getFirst(selection, Object.class);
            this.controller.onTreeSelectionChange(obj);
        }

    }

}
