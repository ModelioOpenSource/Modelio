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

package org.modelio.model.property.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.ui.plugin.UI;

/**
 * Implement the tool bar of the Element property panel.
 */
@objid ("6618364d-17d0-4df5-bb28-e4f49497af02")
public class ElementPropertyPanelToolbar implements IPanelProvider {
    @objid ("48622d30-ad60-4867-ab99-6ae4e5c448c3")
    private ElementPropertyController controller;

    @objid ("5e1e5918-faf4-4324-b654-33bc9a23b69f")
    private Composite tbComp;

    @objid ("8c312450-4b8a-4b8e-83f4-cebe6e6729db")
    private ToolItem addStereotype;

    @objid ("8b426940-2ad6-41c6-b1c7-2cf4c6eaf82b")
    private ToolItem removeStereotype;

    @objid ("34b403f1-9c48-4eff-ad61-0c1a76e1e7b6")
    private ToolItem moveStereotypeUp;

    @objid ("46245ff5-84b2-4ffd-a8a2-fd771698e56d")
    private ToolItem moveStereotypeDown;

    @objid ("f2405b7f-0442-4040-aae9-10757437c20d")
    private MenuItem horizontalLayout;

    @objid ("4fbbbdd6-0f47-4096-acfe-cfe68c39e1e9")
    private MenuItem verticalLayout;

    @objid ("8beea3f6-c876-45c8-82d2-821effc609ea")
    private MenuItem autoLayout;

    @objid ("185ba38f-b047-499b-b2c4-da78b681cc34")
    private ToolItem showHidden;

    @objid ("2b4bde94-9c69-4fbd-ab52-941589ecf32a")
    private ToolBar toolbar;

    /**
     * C'Tor
     * 
     * @param controller the toolbar's controller.
     */
    @objid ("54e6101d-c84c-4aac-9bf7-80791e5b9aa9")
    public ElementPropertyPanelToolbar(ElementPropertyController controller) {
        this.controller = controller;
    }

    @objid ("e9c3a9f9-7ef0-4fe4-bed0-0df813c3cc34")
    @Override
    public boolean isRelevantFor(Object obj) {
        return true;
    }

    @objid ("05d36118-94ba-421c-b31c-f3284c3259a3")
    @Override
    public Object createPanel(Composite parent) {
        this.tbComp = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout(1, false);
        gl.horizontalSpacing = gl.verticalSpacing = 0;
        gl.marginHeight = gl.marginTop = gl.marginBottom = -1;
        gl.marginLeft = gl.marginRight = gl.marginWidth = 0;
        this.tbComp.setLayout(gl);
        
        this.toolbar = new ToolBar(this.tbComp, SWT.HORIZONTAL);
        this.toolbar.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        
        this.addStereotype = createToolButton(this.toolbar, SWT.PUSH, AppUi.getImageDescriptor("icons/addstereotype.png"), "$AddStereotype.tooltip");
        this.addStereotype.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ElementPropertyPanelToolbar.this.controller.onAddStereotype();
            }
        });
        
        this.removeStereotype = createToolButton(this.toolbar, SWT.PUSH, AppUi.getImageDescriptor("icons/removestereotype.png"), "$RemoveStereotype.tooltip");
        this.removeStereotype.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ElementPropertyPanelToolbar.this.controller.onRemoveStereotype();
            }
        });
        
        this.moveStereotypeUp = createToolButton(this.toolbar, SWT.PUSH, UI.getImageDescriptor("icons/uparrow.png"), "$MoveStereotypeUp.tooltip");
        this.moveStereotypeUp.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ElementPropertyPanelToolbar.this.controller.onMoveStereotypeUp();
            }
        });
        
        this.moveStereotypeDown = createToolButton(this.toolbar, SWT.PUSH, UI.getImageDescriptor("icons/downarrow.png"), "$MoveStereotypeDown.tooltip");
        this.moveStereotypeDown.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ElementPropertyPanelToolbar.this.controller.onMoveStereotypeDown();
            }
        });
        
        // Drop down menu to select layout mode
        final ToolItem dropDown = new ToolItem(this.toolbar, SWT.DROP_DOWN);
        final Menu menu = new Menu(this.toolbar.getShell(), SWT.POP_UP);
        
        this.autoLayout = createMenuItem(menu, SWT.RADIO, UI.getImageDescriptor("icons/automaticorientation.png"), "$SetAutoLayout.label",
                "$SetAutoLayout.tooltip");
        this.autoLayout.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ElementPropertyPanelToolbar.this.controller.onAutoLayout();
            }
        });
        
        this.horizontalLayout = createMenuItem(menu, SWT.RADIO, UI.getImageDescriptor("icons/horizontalorientation.png"), "$SetHorizontalLayout.label", "$SetHorizontalLayout.tooltip");
        this.horizontalLayout.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ElementPropertyPanelToolbar.this.controller.onHorizontalLayout();
            }
        });
        this.verticalLayout = createMenuItem(menu, SWT.RADIO, UI.getImageDescriptor("icons/verticalorientation.png"), "$SetVerticalLayout.label", "$SetVerticalLayout.tooltip");
        this.verticalLayout.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ElementPropertyPanelToolbar.this.controller.onVerticalLayout();
            }
        });
        
        dropDown.addListener(SWT.Selection, event -> {
            if (event.detail == SWT.ARROW) {
                Rectangle rect = dropDown.getBounds();
                Point pt = new Point(rect.x, rect.y + rect.height);
                pt = this.toolbar.toDisplay(pt);
                menu.setLocation(pt.x, pt.y);
                menu.setVisible(true);
            }
        });
        return this.tbComp;
    }

/*
     * (non-Javadoc)
     *
     * @see org.modelio.ui.panel.IPanelProvider#getPanel()
     */
    @objid ("cc92af58-e5a4-4ec9-92b0-0da352dbb21e")
    @Override
    public Object getPanel() {
        return this.tbComp;
    }

/*
     * (non-Javadoc)
     *
     * @see org.modelio.ui.panel.IPanelProvider#getHelpTopic()
     */
    @objid ("c20472fd-222f-44a2-8c22-43ed6c316db5")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("ae00d988-c3bb-4566-acee-3acd3dca4247")
    @Override
    public Object getInput() {
        return null;
    }

    @objid ("488abf7b-68ab-4bab-b16e-137596d92a15")
    @Override
    public void setInput(Object input) {
        update();
    }

    @objid ("5a563bd6-f66c-4d6e-ae38-4d4f835fc77f")
    private void update() {
        this.addStereotype.setEnabled(this.controller.canAddStereotype());
        this.removeStereotype.setEnabled(this.controller.canRemoveStereotype());
        this.moveStereotypeDown.setEnabled(this.controller.canMoveStereotypeDown());
        this.moveStereotypeUp.setEnabled(this.controller.canMoveStereotypeUp());
        
        this.horizontalLayout.setSelection(this.controller.isHorizontalLayout());
        this.verticalLayout.setSelection(this.controller.isVerticalLayout());
        this.autoLayout.setSelection(this.controller.isAutoLayout());
        
        if (this.controller.isAdminMode()) {
            // A button to show/hide the hidden MDA properties (visible only in
            // show administration command mode)
            if (this.showHidden == null) {
                this.showHidden = createToolButton(this.toolbar, SWT.CHECK, ModelProperty.getImageDescriptor("icons/showhiddenmda.png"), "$ShowHiddenMda.tooltip");
                this.showHidden.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        ElementPropertyPanelToolbar.this.controller.onShowHiddenMda();
                    }
                });
            }
            this.showHidden.setSelection(this.controller.isMdaHiddenShown());
            this.toolbar.layout(true, true);
        } else {
            if (this.showHidden != null) {
                this.showHidden.dispose();
            }
            this.showHidden = null;
        }
    }

    /**
     * Helper function to create tool item
     * 
     * @param parent the parent toolbar
     * @param style the SWT style of tool item to create
     * @param iconDescriptor the image to display on the tool item
     * @param tooltip the tool tip text for the tool item
     * @return ToolItem
     */
    @objid ("41fb6253-ee03-454e-959b-5290776ae02f")
    private ToolItem createToolButton(ToolBar parent, int style, ImageDescriptor iconDescriptor, String tooltip) {
        ToolItem item = new ToolItem(parent, style);
        
        // Get the icon and setup a listener for disposal
        if (iconDescriptor != null) {
            final Image icon = iconDescriptor.createImage();
            item.setImage(icon);
            parent.addDisposeListener(new DisposeListener() {
                @Override
                public void widgetDisposed(DisposeEvent e) {
                    icon.dispose();
                }
            });
        }
        
        item.setToolTipText(ModelProperty.I18N.getString(tooltip));
        return item;
    }

/*
     * (non-Javadoc)
     *
     * @see org.modelio.ui.panel.IPanelProvider#dispose()
     */
    @objid ("dac9068b-f236-4d1a-8da4-a4e017de8f40")
    @Override
    public void dispose() {
        // nothing to do
    }

    /**
     * Helper function to create tool item
     * 
     * @param parent the parent toolbar
     * @param style the SWT style of tool item to create
     * @param iconDescriptor the image to display on the tool item
     * @param label the text to display on the tool item
     * @param tooltip the tool tip text for the tool item
     * @return ToolItem
     */
    @objid ("99c84219-4d31-410c-810e-517b397785f9")
    private MenuItem createMenuItem(Menu parent, int style, ImageDescriptor iconDescriptor, String label, String tooltip) {
        MenuItem item = new MenuItem(parent, style);
        
        // Get the icon and setup a listener for disposal
        if (iconDescriptor != null) {
            final Image icon = iconDescriptor.createImage();
            item.setImage(icon);
        
            parent.addDisposeListener(new DisposeListener() {
                @Override
                public void widgetDisposed(DisposeEvent e) {
                    icon.dispose();
                }
            });
        }
        item.setText(ModelProperty.I18N.getString(label));
        item.setToolTipText(ModelProperty.I18N.getString(tooltip));
        return item;
    }

}
