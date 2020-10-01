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

package org.modelio.model.property.panel.data.standard;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelio.model.property.panel.data.DataPanelInput;
import org.modelio.model.property.panel.data.IPropertyPanel;
import org.modelio.model.property.panel.data.standard.common.DynamicPropertyModel;
import org.modelio.model.property.panel.data.standard.common.NoElementPropertyModel;
import org.modelio.platform.core.metamodel.MetamodelExtensionPoint;
import org.modelio.platform.model.ui.nattable.viewer.PropertyNatTableViewer;
import org.modelio.platform.model.ui.nattable.viewer.model.INatTableViewerContext;
import org.modelio.platform.model.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.platform.model.ui.nattable.viewer.model.IPropertyModelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Property panel for the model property view.
 * <p>
 * Relies on property model providers declared in the "org.modelio.app.model.property.propertymodelprovider" extension point.
 * </p>
 */
@objid ("407cf705-4e06-4a80-b83a-57bc2444f16e")
public class StandardPropertyPanel implements IPropertyPanel {
    @objid ("8657285b-8449-4f30-b5ab-4991adc2653f")
    private static final String PROPERTYMODEL_PROVIDER_EXTENSION_POINT = "org.modelio.app.model.property.propertymodelprovider";

    @objid ("e4a2a466-38f0-4271-8145-2374479fcf93")
    private Composite comp;

    @objid ("31a90f38-7aaf-4fd5-98d9-9a081cc28925")
    private PropertyNatTableViewer tableViewer;

    @objid ("780d42d2-5043-43b1-b441-90cd73f28bb3")
    private MObject editedElement;

    @objid ("f6e24481-2a7f-4374-b1e7-364106902f93")
    private final MetamodelExtensionPoint<IPropertyModelProvider> propertyModelProviderExtensions = new MetamodelExtensionPoint<>(StandardPropertyPanel.PROPERTYMODEL_PROVIDER_EXTENSION_POINT);

    /**
     * Create a new instance of the standard property panel.
     * 
     * @param parent the SWT parent.
     * @param editedElement the element currently edited.
     */
    @objid ("163c9216-6cca-436d-adf1-96a144243fb3")
    public StandardPropertyPanel(final Composite parent, final MObject editedElement) {
        this.editedElement = editedElement;
        createGUI(parent);
    }

    @objid ("2d7ca699-50d9-4e5d-8299-1f8f22258e34")
    private void createGUI(final Composite parent) {
        // An additional composite to force a transparent background
        // Create the composite to hold the controls
        this.comp = new Composite(parent, SWT.BORDER | SWT.NO_BACKGROUND);
        this.comp.setLayout(new FillLayout());
        
        // Create the nattable
        this.tableViewer = new PropertyNatTableViewer(this.comp);
    }

    @objid ("531a5b33-3f05-47d9-a804-845a3c3a7782")
    @Override
    public void disableGUI() {
        // Nothing to do
    }

    @objid ("ce5778b0-f1bd-4c18-9439-62c217d6a2fb")
    @Override
    public void enableGUI() {
        // Nothing to do
    }

    @objid ("328b153c-9eaa-4350-98d2-4d158c2eaaab")
    @Override
    public Composite getComposite() {
        return this.comp;
    }

    /**
     * Refresh the panel from its current input.
     */
    @objid ("9050782b-38f1-4a16-9fd1-7be40f76b30e")
    @Override
    public void refresh() {
        if (!this.comp.isDisposed()) {
            this.tableViewer.refresh();
            this.comp.redraw();
        }
    }

    @objid ("60319d83-3bd1-45cc-abd6-3fccdd318679")
    @Override
    public void setInput(DataPanelInput newInput) {
        if (newInput == null) {
            this.tableViewer.getControl().dispose();
            return;
        }
        
        this.editedElement = newInput.getTypedElement();
        
        // Data model for the editor
        final IPropertyModel<?> dataModel = getPropertyModel(this.editedElement, newInput.getContext());
        
        this.tableViewer.setContext(newInput.getContext());
        this.tableViewer.setInput(dataModel);
    }

    @objid ("d0a31828-b317-4116-9245-791ff701dd88")
    @Override
    public void start() {
        // Nothing to do
    }

    @objid ("03cbf521-cd1d-4b50-887e-b91d0d2ef804")
    @Override
    public void stop() {
        setInput(null);
        disableGUI();
    }

    /**
     * Provides the data model matching a given model element.
     * <p>
     * Asks property model providers defined in the extension point, according to the element's metaclass fragment.
     * </p>
     * 
     * @param element The element to display in the property view.
     * @param context The project context
     * @return The matching property model.
     */
    @objid ("55058641-6f65-4403-aeaa-256facb07bbc")
    private IPropertyModel<?> getPropertyModel(MObject element, INatTableViewerContext context) {
        if (element != null) {
            IPropertyModelProvider propertyModelProvider = this.propertyModelProviderExtensions.get(element);
            if (propertyModelProvider == null) {
                // Metaclass from an unknown fragment, use a dynamic property model
                return new DynamicPropertyModel(element);
            } else {
                IPropertyModel<?> propertyModel = propertyModelProvider.getPropertyModel(element, context);
                if (propertyModel != null) {
                    return propertyModel;
                }
            }
        }
        
        // No property model
        return new NoElementPropertyModel();
    }

}
