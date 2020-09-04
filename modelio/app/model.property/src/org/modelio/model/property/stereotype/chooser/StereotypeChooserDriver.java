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

package org.modelio.model.property.stereotype.chooser;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.modelio.core.ui.dialogs.elementChooser.IElementChooserDriver;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;

/**
 * This class provide the gui for the stereotype chooser.
 */
@objid ("05c713d9-7fe4-4455-ac1a-221df78db761")
public class StereotypeChooserDriver implements IElementChooserDriver {
    @objid ("9bd32c3e-8bc1-46b2-9bed-c54385edcfbf")
    private IMModelServices modelService;

    @objid ("e8ed74a6-7065-4b6c-96c3-455a5bf406eb")
    protected TreeViewer leftViewer;

    @objid ("c90d90a0-4a19-4ca8-8f7f-dafe9ed75a50")
    private StereotypeChooserModel leftModel;

    @objid ("61536c84-4bde-4f31-935b-e6e3f2c82b43")
    private ModelElement element;

    @objid ("543c25ae-5135-4507-9daf-f50921e84625")
    private Stereotype createdStereotype;

    @objid ("d1d8f5f7-a49b-4ec9-9f69-011cc7e8de77")
    private StereotypeChooserSelectionListener stereotypeSelectionListener = null;

    @objid ("ff064c12-081f-473f-81bc-d6798b5545e1")
    protected ModuleComponent selectedModule = null;

    @objid ("88610cad-e03b-4be4-bfba-5f6a70dae510")
    private ICoreSession session;

    /**
     * Initialize the chooser driver.
     * @param modelService a model service
     * @param selectedModule a ModuleComponent
     */
    @objid ("df3aae4c-7769-45f9-a5db-4c024ab68ec1")
    public StereotypeChooserDriver(ICoreSession session, IMModelServices modelService, ModuleComponent selectedModule) {
        this.session = session;
        this.modelService = modelService;
        this.selectedModule = selectedModule;
        this.createdStereotype = null;
        this.stereotypeSelectionListener = new StereotypeChooserSelectionListener();
    }

    @objid ("c73b78b4-97e7-4b00-a10b-245034d92679")
    @Override
    protected void finalize() throws Throwable {
        this.leftViewer.removeSelectionChangedListener(this.stereotypeSelectionListener);
        super.finalize();
    }

    @objid ("c4fe5b93-bc5f-4a43-b657-f1956b50e2f6")
    @Override
    public StructuredViewer createViewer(Composite parent) {
        this.leftViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI);
        this.leftViewer.setContentProvider(new StereotypeChooserContentProvider(this.modelService));
        this.leftViewer.setLabelProvider(new StereotypeChooserLabelProvider());
        this.leftViewer.setSorter(new StereotypeChooserSorter());
        this.leftViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
        this.leftViewer.addSelectionChangedListener(this.stereotypeSelectionListener);
        return this.leftViewer;
    }

    /**
     * Get the last created stereotype.
     * @return the last created stereotype.
     */
    @objid ("0ff29a75-30fd-4cca-ba3f-3b72a6864bdc")
    public Stereotype getCreatedStereotype() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.createdStereotype;
    }

    @objid ("5eb84f60-a51b-48e2-bc26-9ababcf832fa")
    @Override
    public String getLeftLabel() {
        return ModelProperty.I18N.getString("StereotypeChooser.AddStereotypeLeftLabel");
    }

    @objid ("e1e500ef-b75a-4859-970f-5d9b85a7f3c5")
    @Override
    public String getMessage() {
        return ModelProperty.I18N.getString("StereotypeChooser.Message");
    }

    @objid ("7021aca8-fb42-434e-b720-630591b98b79")
    @Override
    public String getShellTitle() {
        return ModelProperty.I18N.getString("StereotypeChooser.ShellTitle");
    }

    @objid ("7d9dd104-ba48-44b6-b9a3-985ff069fd78")
    @Override
    public String getTitle() {
        return ModelProperty.I18N.getString("StereotypeChooser.Title");
    }

    @objid ("aeb97839-0890-4813-a2c0-9149da11f090")
    @Override
    public void init(Element theElement) {
        if (theElement instanceof ModelElement) {
            this.element = (ModelElement) theElement;
            this.leftModel = new StereotypeChooserModel(this.element);
            this.leftViewer.setInput(this.leftModel);
            setExpandedState();
        }
    }

    @objid ("71722c2f-a790-4162-a991-34f9f69cb6f8")
    @Override
    public void performCancel() {
        // Nothing to do
    }

    @objid ("6db33bc7-335a-4852-bbf0-8643da5a0ac7")
    @Override
    public void performFinish(StructuredViewer theViewer, List<Object> selection) {
        try (ITransaction transaction = this.session.getTransactionSupport().createTransaction(ModelProperty.I18N.getString("StereotypeChooser.AddStereotype"))) {
            for (Object obj : selection) {
                this.createdStereotype = (Stereotype) obj;
        
                List<Stereotype> ownedStereotypes = this.element.getExtension();
        
                if (!ownedStereotypes.contains(this.createdStereotype)) {
                    ownedStereotypes.add(this.createdStereotype);
                } else {
                    this.createdStereotype = null;
                }
            }
        
            transaction.commit();
        }
    }

    @objid ("9e7b94de-3dee-4deb-8ba2-f8cfa27422ef")
    private void setExpandedState() {
        this.leftViewer.setAutoExpandLevel(2);
    }

    @objid ("55148f0e-9f4e-4189-92c5-4eec9ac53328")
    private class StereotypeChooserSelectionListener implements ISelectionChangedListener {
        @objid ("c1484b20-b9b6-4f38-acd9-15ea6d576977")
        private boolean enable = true;

        @objid ("7aea407e-d247-48c4-a20d-abeca1821323")
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
            if (this.enable) {
                this.enable = false;
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    List<?> selectedAdapters = structuredSelection.toList();
            
                    List<Stereotype> selectedStereotypes = getStereotypeAdapters(selectedAdapters);
            
                    StereotypeChooserDriver.this.leftViewer.setSelection(new StructuredSelection(selectedStereotypes));
                }
                this.enable = true;
            }
        }

        @objid ("4b2a3b27-ab85-4286-a595-354d83e1a6db")
        public StereotypeChooserSelectionListener() {
            // Nothing to do
        }

        @objid ("2eeeae60-efe3-459e-8615-4e22acbd2e22")
        private List<Stereotype> getStereotypeAdapters(List<?> selectedAdapters) {
            List<Stereotype> adapters = new ArrayList<>();
            
            for (Object obj : selectedAdapters) {
                if (obj instanceof Stereotype) {
                    adapters.add((Stereotype) obj);
                }
            }
            return adapters;
        }

    }

}
