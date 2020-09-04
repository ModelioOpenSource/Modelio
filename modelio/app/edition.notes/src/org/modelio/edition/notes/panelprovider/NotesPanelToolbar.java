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

package org.modelio.edition.notes.panelprovider;

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
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.ui.plugin.UI;

/**
 * Implement the tool bar of the Notes & Constraints panel.
 */
@objid ("2ddaa41e-4814-4f81-b61e-131e84da513c")
public class NotesPanelToolbar implements IPanelProvider {
    @objid ("14674e5a-0e10-4402-9aea-cb0e81f8c38c")
    private NotesPanelController controller;

    @objid ("0abff9e1-4b52-43bd-8078-8a5047dda006")
    private ToolItem addConstraint;

    @objid ("d5389283-9aa2-46a6-bff4-8f0d4535b4da")
    private Composite tbComp;

    @objid ("f15dba22-3ab2-43e9-a04a-cf4f33f94cc3")
    private ToolItem addNote;

    @objid ("c1403b0e-bb4e-4c7d-8b8e-754bbc8ba9df")
    private ToolItem addDescription;

    @objid ("1046dcf5-8587-4b16-88ab-d2164bd47adb")
    private ToolItem removeAnnotation;

    @objid ("8bcac6d6-b288-43fc-9085-9871e63baa81")
    private ToolItem cleanContent;

    @objid ("cc4f5129-e756-4388-b531-4358439032ab")
    private ToolItem moveUp;

    @objid ("2aa2e6be-8022-437d-97d7-f0444bae6ca3")
    private ToolItem moveDown;

    @objid ("e5e3763e-282b-439c-8ecc-941157fe3f1c")
    private MenuItem horizontalLayout;

    @objid ("cb5c66a3-9910-4c5c-a157-7d69b1039921")
    private MenuItem verticalLayout;

    @objid ("366be6bf-1e70-4bfb-8129-e88ee956b797")
    private MenuItem autoLayout;

    @objid ("52171d38-8327-462d-ad4d-13e3b2ed8f6d")
    private ToolItem addDocument;

    /**
     * C'Tor
     * @param controller the controller of the GUI panel owning the tool bar
     */
    @objid ("3e929c96-c812-4811-8401-f0783c4a15d6")
    public NotesPanelToolbar(NotesPanelController controller) {
        NotesPanelToolbar.this.controller = controller;
    }

/*
     * (non-Javadoc)
     * 
     * @see org.modelio.ui.panel.IPanelProvider#isRelevantFor(java.lang.Object)
     */
    @objid ("de82d177-a31a-4ece-b16e-79b53a6ac164")
    @Override
    public boolean isRelevantFor(Object obj) {
        return true;
    }

/*
     * (non-Javadoc)
     * 
     * @see org.modelio.ui.panel.IPanelProvider#createPanel(org.eclipse.swt.widgets. Composite)
     */
    @objid ("d8d9e865-1ee1-4ec3-a210-dacb3926c593")
    @Override
    public Object createPanel(Composite parent) {
        this.tbComp = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout(2, false);
        gl.horizontalSpacing = gl.verticalSpacing = 0;
        gl.marginHeight = gl.marginTop = gl.marginBottom = -1;
        gl.marginLeft = gl.marginRight = gl.marginWidth = 0;
        this.tbComp.setLayout(gl);
        
        ToolBar tb = new ToolBar(this.tbComp, SWT.HORIZONTAL);
        tb.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        
        this.addConstraint = createToolButton(tb, SWT.PUSH, EditionNotes.getImageDescriptor("icons/addconstraint.png"), "AddConstraint.tooltip");
        this.addConstraint.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onAddConstraint();
            }
        });
        
        this.addNote = createToolButton(tb, SWT.PUSH, EditionNotes.getImageDescriptor("icons/addnote.png"), "AddNote.tooltip");
        this.addNote.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onAddNote();
            }
        });
        
        this.addDescription = createToolButton(tb, SWT.PUSH, EditionNotes.getImageDescriptor("icons/adddescription.png"), "AddDescription.tooltip");
        this.addDescription.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onAddDescription();
            }
        });
        
        this.addDocument = createToolButton(tb, SWT.PUSH, EditionNotes.getImageDescriptor("icons/adddocument.png"), "AddDocument.tooltip");
        this.addDocument.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onAddRichNote();
            }
        });
        
        this.removeAnnotation = createToolButton(tb, SWT.PUSH, UI.getImageDescriptor("icons/delete.png"), "RemoveAnnotation.tooltip");
        this.removeAnnotation.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onRemoveAnnotation();
            }
        });
        
        this.cleanContent = createToolButton(tb, SWT.PUSH, EditionNotes.getImageDescriptor("icons/cleannote.png"), "CleanNote.tooltip");
        this.cleanContent.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onCleanContent();
            }
        });
        
        this.moveUp = createToolButton(tb, SWT.PUSH, UI.getImageDescriptor("icons/uparrow.png"), "MoveUp.tooltip");
        this.moveUp.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onMoveUp();
            }
        });
        
        this.moveDown = createToolButton(tb, SWT.PUSH, UI.getImageDescriptor("icons/downarrow.png"), "MoveDown.tooltip");
        this.moveDown.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onMoveDown();
            }
        });
        
        // Drop down menu to select layout mode
        final ToolItem dropDown = new ToolItem(tb, SWT.DROP_DOWN);
        final Menu menu = new Menu(tb.getShell(), SWT.POP_UP);
        
        this.autoLayout = createMenuItem(menu, SWT.RADIO, UI.getImageDescriptor("icons/automaticorientation.png"), "SetAutoLayout.label", "SetAutoLayout.tooltip");
        this.autoLayout.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onAutomaticLayout();
            }
        });
        
        this.horizontalLayout = createMenuItem(menu, SWT.RADIO, UI.getImageDescriptor("icons/horizontalorientation.png"), "SetHorizontalLayout.label", "SetHorizontalLayout.tooltip");
        this.horizontalLayout.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onHorizontalLayout();
            }
        });
        this.verticalLayout = createMenuItem(menu, SWT.RADIO, UI.getImageDescriptor("icons/verticalorientation.png"), "SetVerticalLayout.label", "SetVerticalLayout.tooltip");
        this.verticalLayout.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NotesPanelToolbar.this.controller.onVerticalLayout();
            }
        });
        
        dropDown.addListener(SWT.Selection, event -> {
            if (event.detail == SWT.ARROW) {
                Rectangle rect = dropDown.getBounds();
                Point pt = new Point(rect.x, rect.y + rect.height);
                pt = tb.toDisplay(pt);
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
    @objid ("5117beae-3f2d-4b20-8ff8-64d20963a7dd")
    @Override
    public Object getPanel() {
        return this.tbComp;
    }

/*
     * (non-Javadoc)
     * 
     * @see org.modelio.ui.panel.IPanelProvider#getHelpTopic()
     */
    @objid ("05c6ce5c-d2c7-4334-823b-055f2b436406")
    @Override
    public String getHelpTopic() {
        return null;
    }

/*
     * (non-Javadoc)
     * 
     * @see org.modelio.ui.panel.IPanelProvider#getInput()
     */
    @objid ("8b820737-6027-4c93-b4ee-61ba04635a18")
    @Override
    public Object getInput() {
        // TODO Auto-generated method stub
        return null;
    }

/*
     * (non-Javadoc)
     * 
     * @see org.modelio.ui.panel.IPanelProvider#setInput(java.lang.Object)
     */
    @objid ("b4dbb353-ec36-44f0-8256-e93fa799ea08")
    @Override
    public void setInput(Object input) {
        update();
    }

    @objid ("de9caf6a-ec29-4b99-9b59-3f10736436ff")
    private void update() {
        this.addConstraint.setEnabled(this.controller.canAddConstraint());
        this.addNote.setEnabled(this.controller.canAddNote());
        this.addDescription.setEnabled(this.controller.canAddNote());
        this.addDocument.setEnabled(this.controller.canAddNote());
        this.removeAnnotation.setEnabled(this.controller.canRemoveAnnotation());
        this.cleanContent.setEnabled(this.controller.canCleanContent());
        this.moveDown.setEnabled(this.controller.canMoveDown());
        this.moveUp.setEnabled(this.controller.canMoveUp());
        
        this.verticalLayout.setSelection(this.controller.isVerticalLayout());
        this.horizontalLayout.setSelection(this.controller.isHorizontalLayout());
        this.autoLayout.setSelection(this.controller.isAutoLayout());
    }

    /**
     * Helper function to create tool item
     * @param parent the parent toolbar
     * @param iconDescriptor the image to display on the tool item
     * @param tooltip the tool tip text for the tool item
     * @return ToolItem
     */
    @objid ("9c262707-acc6-44ba-9ea7-27689faca7e9")
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
        
        item.setToolTipText(EditionNotes.I18N.getString(tooltip));
        return item;
    }

/*
     * (non-Javadoc)
     * 
     * @see org.modelio.ui.panel.IPanelProvider#dispose()
     */
    @objid ("119b4f32-f5ee-4bc5-aaa2-f164599f2b45")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("589fee9a-a0cc-43a5-814e-2240f9f97d7e")
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
        item.setText(EditionNotes.I18N.getString(label));
        item.setToolTipText(EditionNotes.I18N.getString(tooltip));
        return item;
    }

}
