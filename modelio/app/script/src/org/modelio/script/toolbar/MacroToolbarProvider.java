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

package org.modelio.script.toolbar;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.swt.trimbarcomponent.TrimBarComponent;
import org.modelio.script.handlers.RunMacroHandler;
import org.modelio.script.macro.IMacroService;
import org.modelio.script.macro.catalog.Macro;
import org.modelio.script.plugin.Script;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Class used to fill a dynamic macro toolbar from the catalog.
 * <p>
 * Relies on a dynamic ToolControl in the e4 model.
 * </p>
 */
@objid ("c915c271-1ed0-4b06-baf2-b2a6ef4badce")
public class MacroToolbarProvider extends TrimBarComponent {
    @objid ("24949fca-15ce-44b5-8be1-f54399e2a013")
    @Inject
    private MApplication application;

    @objid ("3c1218c8-bf8a-42ec-8e64-27d9a88e89c7")
    @Inject
    private IMacroService macroService;

    @objid ("d4748348-7487-4db1-b15d-daa8de66f8ce")
    @Inject
    private EPartService partService;

    @objid ("4d380049-a0e9-4700-b6c8-6251ed111daf")
    public MacroToolbarProvider() {
        super(Script.I18N.getString("MacroToolbarProvider.MacrosZone.label"));
    }

    /**
     * Initialize the SWT control.
     * @param parent a widget which will be the parent of the new SWT components.
     */
    @objid ("86a0b24e-5c5f-4caa-9fa2-e2c79c974a0b")
    @Override
    protected Control createControl(Composite parent) {
        return new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
    }

    @objid ("27a79444-c836-4cbe-b1a0-9aaaa0592707")
    @Override
    protected ToolBar getControl() {
        return (ToolBar) super.getControl();
    }

    /**
     * Empty the toolbar of all its tool items.
     */
    @objid ("30d24b1f-d930-4409-9716-0a409cc0a66b")
    private void clearToolbar() {
        for (ToolItem item : getControl().getItems()) {
            deleteToolItem(item);
        }
    }

    /**
     * Create a new tool item from a macro.
     * @param entry the macro to create the tool item from.
     * @return a new toolbar item.
     */
    @objid ("554daeb8-49f1-4a03-b6da-6ac75b6f7c56")
    private ToolItem createToolItem(final Macro entry) {
        // create a new handled item
        ToolItem item = new ToolItem(getControl(), SWT.PUSH);
        
        // compute label, tooltip and icon
        item.setToolTipText(entry.getDescription());
        if (entry.getIconPath() != null) {
            try {
                // Instanciate a new image: it will be disposed with the item itself, when selection changes
                item.setImage(new Image(null, entry.getIconPath().toString()));
            } catch (@SuppressWarnings("unused") Exception e) {
                // invalid image, set the text instead
                item.setText(entry.getName());
            }
        } else {
            // no image, set the text instead
            item.setText(entry.getName());
        }
        
        // Set the macro as data
        item.setData(entry);
        
        item.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                RunMacroHandler runner = new RunMacroHandler();
                runner.execute(MacroToolbarProvider.this.partService, MacroToolbarProvider.this.application.getSelectedElement(), entry.getScriptPath().toString());
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // Nothing to do
            }
        });
        return item;
    }

    @objid ("74a41c21-7708-4b0d-a8df-9db5b352a637")
    private void deleteToolItem(ToolItem item) {
        final Image image = item.getImage();
        if (image != null) {
            item.setImage(null);
            image.dispose();
        }
        item.dispose();
    }

    /**
     * Create new tool items from a list of macros.
     * <p>
     * Only macros shown in the toolbar are returned.
     * </p>
     * @param entries the macros to build the toolbar.
     * @return a list of menu elements.
     */
    @objid ("15d21922-45b9-4216-9aa9-ecbeaef56daa")
    private List<ToolItem> fillToolbar(List<Macro> entries) {
        List<ToolItem> items = new ArrayList<>();
        for (Macro entry : entries) {
            if (entry.shownInToolbar()) {
                items.add(createToolItem(entry));
            }
        }
        return items;
    }

    @objid ("a8d95c61-19da-4f32-af8b-3dc2fce0d571")
    @Inject
    @Optional
    private void onSelectionChanged(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (getControl() != null) {
            // Clean up tool items
            clearToolbar();
        
            // Build new tool items
            List<ToolItem> items = fillToolbar(this.macroService.getMacros(SelectionHelper.toList(selection, MObject.class)));
        
            // Make sure the toolbar is visible only if not empty
            boolean newIsVisible = !items.isEmpty();
            if (isVisible() && newIsVisible) {
                refreshLayout();
            } else {
                setVisible(newIsVisible);
            }
        }
    }

}
