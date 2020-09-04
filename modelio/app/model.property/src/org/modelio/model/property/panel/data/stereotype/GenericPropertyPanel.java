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

package org.modelio.model.property.panel.data.stereotype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelio.core.ui.nattable.viewer.PropertyNatTableViewer;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.model.property.panel.data.DataPanelInput;
import org.modelio.model.property.panel.data.IPropertyPanel;
import org.modelio.model.property.panel.data.stereotype.model.StereotypeTableModel;

/**
 * Implementation of the "Extensions" part of the property panel, available when a module or a stereotype is selected in it.
 * <p>
 * Uses a {@link PropertyTableViewer} configured with a {@link StereotypeTableModel} to display its contents.
 * </p>
 */
@objid ("2ea10dd0-7cee-400b-beed-0320ff228666")
public class GenericPropertyPanel implements IPropertyPanel {
    @objid ("5ee1a076-6096-4e4c-99b9-796ee5c1a494")
    private Composite comp;

    @objid ("e73948d8-6516-4da3-914b-1e8c759a257d")
    private ModelElement editedElement;

    @objid ("c129c580-0657-4ddd-af02-51dba8eef1aa")
    private ModuleComponent module;

    @objid ("a69b01f7-c222-4381-b82e-cbd228858c9a")
    private Stereotype stereotype;

    @objid ("38d90394-8489-45dc-8947-67387e7413c8")
    private PropertyNatTableViewer tableViewer;

    /**
     * Create a new property panel for a ModuleComponent.
     * @param parent a widget which will be the parent of the new instance (cannot be null)
     * @param editedElement the element currently edited in the Element's view.
     * @param module the module to load the {@link TagType} & {@link PropertyDefinition} list from.
     */
    @objid ("28c06912-54ec-494f-8083-06042c8e6af7")
    public GenericPropertyPanel(final Composite parent, final ModelElement editedElement, final ModuleComponent module) {
        this.editedElement = editedElement;
        this.module = module;
        createGUI(parent);
    }

    /**
     * Create a new property panel for a ModuleComponent.
     * @param parent a widget which will be the parent of the new instance (cannot be null)
     * @param editedElement the element currently edited in the Element's view.
     * @param stereotype the stereotype to load the {@link TagType} & {@link PropertyDefinition} list from.
     */
    @objid ("7485b5d5-88be-4d90-91f3-b5880f810985")
    public GenericPropertyPanel(final Composite parent, final ModelElement editedElement, final Stereotype stereotype) {
        this.editedElement = editedElement;
        this.stereotype = stereotype;
        createGUI(parent);
    }

    @objid ("3b6d71c8-d929-4ef9-a8ab-c3d1db678fdc")
    private void createGUI(final Composite parent) {
        // An additional composite to force a transparent background
        // Create the composite to hold the controls
        this.comp = new Composite(parent, SWT.BORDER | SWT.NO_BACKGROUND);
        this.comp.setLayout(new FillLayout());
        
        // Create the nattable
        this.tableViewer = new PropertyNatTableViewer(this.comp);
    }

    @objid ("6b78c5f6-5d2c-45d1-960b-05987605066b")
    @Override
    public void disableGUI() {
        // Nothing to do
    }

    @objid ("6d938247-ee20-4642-8a67-d3738706c33a")
    @Override
    public void enableGUI() {
        // Nothing to do
    }

    @objid ("d41287a7-78d2-45d8-9b88-34c10f166bd1")
    @Override
    public Composite getComposite() {
        return this.comp;
    }

    /**
     * Refresh the panel from its current input.
     */
    @objid ("74b10f02-4559-4ef3-bf0a-20833b7a88e6")
    @Override
    public void refresh() {
        if (!this.comp.isDisposed()) {
            this.tableViewer.refresh();
            this.comp.layout(true);
            this.comp.redraw();
        }
    }

    @objid ("2d945f51-609d-47dc-8c96-66066eddd1c2")
    @Override
    public void setInput(DataPanelInput newInput) {
        if (newInput == null) {
            this.tableViewer.getControl().dispose();
            return;
        }
        
        // Data model for the editor
        IPropertyModel<?> dataModel;
        if (this.stereotype != null) {
            dataModel = new StereotypeTableModel(
                    this.editedElement, 
                    this.stereotype, 
                    newInput.getContext().getModelService(), 
                    newInput.isShowHiddenAnnotations());
        } else {
            dataModel = new StereotypeTableModel(
                    this.editedElement, 
                    this.module, 
                    newInput.getContext().getModelService(), 
                    newInput.isShowHiddenAnnotations());
        }
        
        this.tableViewer.setContext(newInput.getContext());
        this.tableViewer.setInput(dataModel);
    }

    @objid ("7865aacb-4907-4288-8e67-c38a5dd26ac4")
    @Override
    public void start() {
        // Nothing to do
    }

    @objid ("6533f518-25c9-463e-8d1f-d9a806ac9676")
    @Override
    public void stop() {
        setInput(null);
        disableGUI();
    }

}
