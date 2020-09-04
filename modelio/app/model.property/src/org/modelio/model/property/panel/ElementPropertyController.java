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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.modelio.app.preferences.AppSharedPreferencesKeys;
import org.modelio.app.preferences.plugin.AppPreferences;
import org.modelio.core.ui.dialogs.elementChooser.ElementChooserDlg;
import org.modelio.core.ui.nattable.viewer.model.INatTableViewerContext;
import org.modelio.core.ui.nattable.viewer.model.NatTableViewerCompleteContext;
import org.modelio.core.ui.nattable.viewer.model.NatTableViewerContext;
import org.modelio.core.ui.swt.copy.PasteElementObject.PasteType;
import org.modelio.core.ui.swt.copy.PasteElementObject;
import org.modelio.core.ui.swt.copy.PasteElementTransfer;
import org.modelio.core.ui.swt.copy.TransferItem;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.property.panel.data.DataPanelInput;
import org.modelio.model.property.panel.tree.TreePanelInput;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.model.property.stereotype.chooser.StereotypeChooserDriver;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("a6fcef85-219f-4d38-ae1a-39b9dae5a0a3")
public class ElementPropertyController {
    @objid ("45537ba1-a79d-447e-8df6-fa6d3efcfe8d")
    private boolean showHiddenAnnotations;

    @objid ("4fe2e953-2cbe-4d40-958c-47ecf9b1cfd0")
    private boolean autoLayout;

    @objid ("8551e76d-a37c-4807-8cc3-22ed977cd574")
    private ElementPropertyUi ui;

    @objid ("75313588-509f-46ef-ae5f-cdaeb4d67a2b")
    private IEclipseContext context;

    @objid ("1dd91666-b1dc-4b2f-add9-c0c8be56fa43")
    private DataPanelInput dataInput;

    @objid ("f3dca94c-c681-49de-8ef6-aa8aa6567d1c")
    private TreePanelInput treeInput;

    @objid ("2e1decee-8efa-4f6b-ae29-62b4bd93509c")
    public boolean canAddStereotype() {
        Element selectedElement = getSelectedElement();
        return selectedElement instanceof ModelElement && selectedElement.getStatus().isModifiable();
    }

    @objid ("311f8acd-a995-4e44-8752-5ff7940781bb")
    public void onAddStereotype() {
        final ModelElement parentElement = (ModelElement) getSelectedElement();
        final CoreSession session = CoreSession.getSession(parentElement);
        StereotypeChooserDriver driver = new StereotypeChooserDriver(session, new MModelServices(session), null);
        ElementChooserDlg dialog = new ElementChooserDlg(this.ui.getComposite().getShell(), driver, parentElement);
        
        // Don't return from open() until window closes
        dialog.setBlockOnOpen(true);
        // Open the main window
        dialog.open();
    }

    @objid ("5c777bab-e39a-4831-88cb-882f864dd3a9")
    public boolean canRemoveStereotype() {
        // Sanity checks
        final Element parentElement = getSelectedElement();
        if (parentElement == null || !parentElement.isValid() || !parentElement.isModifiable()
                || !(parentElement instanceof ModelElement)) {
            return false;
        }
        
        final List<Object> selectedElements = getSelectedTypeItems();
        if (selectedElements.isEmpty()) {
            return false;
        }
        for (final Object element : selectedElements) {
            if (!(element instanceof Stereotype)) {
                return false;
            }
        }
        return true;
    }

    @objid ("ec80c124-0137-4f7f-8988-c8169ed7e130")
    public void onRemoveStereotype() {
        final ModelElement parentElement = (ModelElement) getSelectedElement();
        
        final CoreSession session = CoreSession.getSession(parentElement);
        final List<Object> selectedElements = getSelectedTypeItems();
        
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Remove stereotype")) {
            parentElement.getExtension().removeAll(selectedElements);
            transaction.commit();
        } catch (final Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            ElementPropertyController.reportException(ModelProperty.I18N.getMessage("CannotRemoveStereotype"), e);
        }
    }

    @objid ("4cf5a279-2c40-48c5-8262-5a4d9fb4648f")
    public boolean canMoveStereotypeUp() {
        // Sanity checks
        final Element parentElement = getSelectedElement();
        if (parentElement == null || !parentElement.isModifiable() || !(parentElement instanceof ModelElement)) {
            return false;
        }
        
        final List<Object> selectedElements = getSelectedTypeItems();
        if (selectedElements.isEmpty()) {
            return false;
        }
        
        final List<Stereotype> listToReorder = ((ModelElement) parentElement).getExtension();
        for (final Object element : selectedElements) {
            if (!(element instanceof Stereotype)) {
                return false;
            } else {
                if (ElementPropertyController.computeNewIndexUp((Stereotype) element, listToReorder) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    @objid ("0dd9b5f9-d56f-4783-9f75-da9f6328869e")
    public void onMoveStereotypeUp() {
        final ModelElement parentElement = (ModelElement) getSelectedElement();
        final CoreSession session = CoreSession.getSession(parentElement);
        final List<Object> selectedElements = getSelectedTypeItems();
        
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Move stereotype down")) {
            int nbToMove = 0;
        
            final List<Stereotype> listToReorder = parentElement.getExtension();
            // We first move down the Last selected element of the list; This
            // way the positions of other
            // selected elements are not affected by the move of the current
            // element.
            for (int i = selectedElements.size() - 1; i >= 0; --i) {
                final Stereotype element = (Stereotype) selectedElements.get(i);
        
                // Retrieve the new index of the element
                final int index = ElementPropertyController.computeNewIndexUp(element, listToReorder);
        
                if (index == -1) {
                    // Invalid case, just exit
                    transaction.rollback();
                    return;
                }
        
                // Move the element in the list
                nbToMove++;
                listToReorder.remove(element);
                listToReorder.add(index, element);
        
            }
        
            if (nbToMove > 0) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (
        
        final Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            ElementPropertyController.reportException(ModelProperty.I18N.getMessage("CannotMoveStereotypeUp"), e);
        }
    }

    @objid ("e556064b-3170-437f-af36-7f3d579496cc")
    public boolean canMoveStereotypeDown() {
        // Sanity checks
        final Element parentElement = getSelectedElement();
        if (parentElement == null || !parentElement.isModifiable() || !(parentElement instanceof ModelElement)) {
            return false;
        }
        
        final List<Object> selectedElements = getSelectedTypeItems();
        if (selectedElements.isEmpty()) {
            return false;
        }
        
        final List<Stereotype> listToReorder = ((ModelElement) parentElement).getExtension();
        for (final Object element : selectedElements) {
            if (!(element instanceof Stereotype)) {
                return false;
            } else {
                if (ElementPropertyController.computeNewIndexDown((Stereotype) element, listToReorder) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    @objid ("9015e025-ce1c-42d3-8e9b-197dafe099cd")
    public void onMoveStereotypeDown() {
        final ModelElement parentElement = (ModelElement) getSelectedElement();
        final CoreSession session = CoreSession.getSession(parentElement);
        final List<Object> selectedElements = getSelectedTypeItems();
        
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Move stereotype     down")) {
            int nbToMove = 0;
        
            final List<Stereotype> listToReorder = parentElement.getExtension();
            // We first move down the Last selected element of the list; This
            // way the positions of other
            // selected elements are not affected by the move of the current
            // element.
            for (int i = selectedElements.size() - 1; i >= 0; --i) {
                final Stereotype element = (Stereotype) selectedElements.get(i);
        
                // Retrieve the new index of the element
                final int index = ElementPropertyController.computeNewIndexDown(element, listToReorder);
        
                if (index == -1) {
                    // Invalid case, just exit
                    transaction.rollback();
                    return;
                }
        
                // Move the element in the list
                nbToMove++;
                listToReorder.remove(element);
                listToReorder.add(index, element);
        
            }
        
            if (nbToMove > 0) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (final Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            ElementPropertyController.reportException(ModelProperty.I18N.getMessage("CannotMoveStereotypeDown"), e);
        }
    }

    /**
     * Called when the 'horizontal layout' toolbar button is activated
     */
    @objid ("08179f88-52cc-45ee-ae1f-fe6eb1611701")
    public void onHorizontalLayout() {
        this.ui.disableAutoLayout();
        this.ui.setHorizontalLayout();
    }

    /**
     * Called when the 'vertical layout' toolbar button is activated
     */
    @objid ("c6c62f43-8927-492e-b140-a86cf1bd56f0")
    public void onVerticalLayout() {
        this.ui.disableAutoLayout();
        this.ui.setVerticalLayout();
    }

    /**
     * Called when the 'auto layout' toolbar button is activated
     */
    @objid ("692ba212-ed67-4d58-b747-698577a285c7")
    public void onAutoLayout() {
        this.ui.enableAutoLayout();
    }

    /**
     * Called when the 'show hidden annotations' toolbar button is activated
     */
    @objid ("497e0207-3021-4279-aba8-45ca165f610c")
    public void onShowHiddenMda() {
        this.showHiddenAnnotations = !this.showHiddenAnnotations;
        this.treeInput.setDisplayHiddenAnnotations(this.showHiddenAnnotations);
        this.dataInput.setShowHiddenAnnotations(this.showHiddenAnnotations);
        this.ui.refreshView();
    }

    @objid ("f3e58b98-45ca-4194-85c6-00877f1be81e")
    public ElementPropertyController(IEclipseContext context) {
        this.context = context;
        this.treeInput = createTreeInput(null);
        this.dataInput = createDataInput(null, null);
    }

    @objid ("08fa0692-6359-4149-a506-d767dd4b13e1")
    public ElementPropertyUi createView(Composite parent) {
        this.ui = new ElementPropertyUi(this);
        this.ui.createContents(parent);
        this.ui.setInputs(this.treeInput, this.dataInput);
        
        AppPreferences.getPreferences().addPropertyChangeListener(new IPropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
        
                if (ElementPropertyController.this.ui.getComposite().isDisposed()) {
                    AppPreferences.getPreferences().removePropertyChangeListener(this);
                } else {
                    if (AppSharedPreferencesKeys.SHOWADMTOOLS_PREFKEY.equals(event.getProperty())) {
                        ElementPropertyController.this.ui.refreshView();
                    }
                }
        
            }
        });
        return this.ui;
    }

    @objid ("1e867cca-1ddb-4568-a75f-d01ce2c4f676")
    public Element getSelectedElement() {
        return this.treeInput.getTypedElement();
    }

    @objid ("fbf734f3-e6d5-4066-8941-8f83d1c88cef")
    public void setInputs(Element selectedElement, ModelElement selectedAnnotation) {
        if (!Objects.equals(selectedElement, this.treeInput.getTypedElement())) {
            this.treeInput.setTypedElement(selectedElement);
            this.treeInput.setPreselectedTypingElement(selectedAnnotation);
            this.dataInput.setTypedElement(selectedElement);
            this.dataInput.setTypingElement(selectedAnnotation);
        }
        this.ui.setInputs(this.treeInput, this.dataInput);
    }

    @objid ("ee6fd575-e193-4aec-b774-875c968552e9")
    private TreePanelInput createTreeInput(Element selectedElement) {
        return new TreePanelInput(selectedElement, null, isMdaHiddenShown());
    }

    @objid ("77ba1b91-44c8-412e-a66b-400aa14cd83e")
    private DataPanelInput createDataInput(Element selectedElement, ModelElement typeItem) {
        INatTableViewerContext tableContext = null;
        
        if (this.context != null) {
            tableContext = new NatTableViewerCompleteContext(this.context);
        } else if (selectedElement != null) {
            CoreSession session = CoreSession.getSession(selectedElement);
            tableContext = new NatTableViewerContext(
                    CoreSession.getSession(selectedElement),
                    new MModelServices(session),
                    null,
                    null,
                    null);
        } else {
            tableContext = NatTableViewerContext.empty();
        }
        return new DataPanelInput(tableContext, selectedElement, typeItem, isMdaHiddenShown());
    }

    @objid ("e86de5d4-763c-45d6-8924-125227685fc6")
    public boolean isAdminMode() {
        return AppPreferences.getPreferences().getBoolean(AppSharedPreferencesKeys.SHOWADMTOOLS_PREFKEY);
    }

    @objid ("93e77042-7c82-47b3-a74d-e7219551c91c")
    public boolean isMdaHiddenShown() {
        return this.showHiddenAnnotations;
    }

    @objid ("e341b0e8-48f1-42a8-845c-49045c77c38f")
    public boolean isHorizontalLayout() {
        return this.ui.isHorizontalLayout();
    }

    @objid ("2cfcc545-7069-4cc0-92d7-95192b2884da")
    public boolean isVerticalLayout() {
        return this.ui.isVerticalLayout();
    }

    @objid ("70d04057-09b3-462b-a681-813f1833feb6")
    public boolean isAutoLayout() {
        return this.ui.isAutoLayout();
    }

    @objid ("01c74bef-700d-4e7f-ae12-6d04bf6cea5f")
    public void setShowHiddenMdaElements(boolean v) {
        this.showHiddenAnnotations = v;
    }

    /**
     * Called when the selection change in the annotation tree. Sets the given tagged values set (from Stereotype or Module) in the data panel.
     * 
     * @param typeItem : the type item whose contents is to be displayed in the content panel. May be null
     */
    @objid ("d4e66867-b90b-4a92-9232-555393019dd8")
    public void onTreeSelectionChange(Object typeItem) {
        if (!Objects.equals(this.dataInput.getTypingElement(), typeItem)) {
            this.dataInput.setTypingElement(typeItem);
            this.treeInput.setPreselectedTypingElement(typeItem);
            this.ui.setInputs(this.treeInput, this.dataInput);
        }
    }

    /**
     * Get the selected elements in the left tree.
     * 
     * @return the selected elements.
     */
    @objid ("8faa29b8-c068-11e1-8c0a-002564c97630")
    public List<Object> getSelectedTypeItems() {
        ISelection selection = this.ui.getTreePanel().getTreeViewer().getSelection();
        if (selection instanceof IStructuredSelection) {
            return ((IStructuredSelection) selection).toList();
        }
        return Collections.emptyList();
    }

    @objid ("9979bbb2-75f6-4666-8637-8e2b07537102")
    static void reportException(String title, Exception e) {
        // Show an error box
        MessageDialog.openError(null, title, e.getLocalizedMessage());
        ModelProperty.LOG.error(e);
    }

    @objid ("35cba603-0b49-491e-a283-5003bb9c5ec7")
    private static int computeNewIndexUp(Stereotype element, List<Stereotype> listToReorder) {
        int index = listToReorder.indexOf(element);
        if (index < 1) {
            return -1;
        }
        index--;
        // Iterate until we find an element of the same metaclass or until we
        // find the begining of the list.
        while (index != -1 && listToReorder.get(index).getClass() != element.getClass()) {
            index--;
        }
        return index;
    }

    @objid ("7d2addbe-b725-4290-8b53-1a8bfdcec259")
    private static int computeNewIndexDown(Stereotype element, List<Stereotype> listToReorder) {
        int index = listToReorder.indexOf(element) + 1;
        
        // Iterate until we find an element of the same metaclass or until we
        // find the end of the list.
        while (index < listToReorder.size() && listToReorder.get(index).getClass() != element.getClass()) {
            index++;
        }
        
        // If that would move outside of the list, that means element is already
        // the last one.
        if (index >= listToReorder.size()) {
            return -1;
        }
        return index;
    }

    @objid ("d3ce0017-e7a6-4fd2-b724-18076a581d49")
    public boolean canCopyStereotype() {
        // Sanity checks
        if (getSelectedElement() == null) {
            return false;
        }
        
        if (this.ui == null) {
            return false;
        }
        
        List<Object> selectedElements = getSelectedTypeItems();
        if (selectedElements.isEmpty()) {
            return false;
        }
        for (Object element : selectedElements) {
            if (!(element instanceof Stereotype)) {
                return false;
            }
        }
        return true;
    }

    @objid ("63a31cb8-8068-4669-b995-c8963476070e")
    public void onCopyStereotype() {
        List<Object> selectedElements = getSelectedTypeItems();
        PasteElementObject toCopy = new PasteElementObject(PasteType.COPY);
        for (Object element : selectedElements) {
            toCopy.addTransferedItems(new TransferItem((Stereotype) element, this.treeInput.getTypedElement()));
        }
        Clipboard clipboard = new Clipboard(this.ui.getComposite().getDisplay());
        clipboard.setContents(new Object[] { toCopy }, new Transfer[] { PasteElementTransfer.getInstance() });
    }

    @objid ("25a498f6-cad2-4438-834e-5f51bd6f46d3")
    public boolean canCutStereotype() {
        // Sanity checks
        Element me = getSelectedElement();
        if (me == null || !me.isModifiable()) {
            return false;
        }
        
        if (this.ui == null) {
            return false;
        }
        
        List<Object> selectedElements = getSelectedTypeItems();
        if (selectedElements.isEmpty()) {
            return false;
        }
        for (Object element : selectedElements) {
            if (!(element instanceof Stereotype)) {
                return false;
            }
        }
        return true;
    }

    @objid ("1873574b-6510-4843-8211-653382ed706f")
    public void onCutStereotype() {
        List<Object> selectedElements = getSelectedTypeItems();
        
        PasteElementObject toCopy = new PasteElementObject(PasteType.CUT);
        for (Object element : selectedElements) {
            toCopy.addTransferedItems(new TransferItem((Stereotype) element, this.treeInput.getTypedElement()));
        }
        
        Clipboard clipboard = new Clipboard(this.ui.getComposite().getDisplay());
        clipboard.setContents(new Object[] { toCopy }, new Transfer[] { PasteElementTransfer.getInstance() });
    }

    @objid ("e09fd019-e489-4da8-b2f3-99b9c36dc710")
    public boolean canPasteStereotype() {
        // Sanity checks
        if (this.ui == null) {
            return false;
        }
        
        Element me = getSelectedElement();
        if (me == null || !me.isModifiable() || !(me instanceof ModelElement)) {
            return false;
        }
        
        Clipboard clipboard = new Clipboard(this.ui.getComposite().getDisplay());
        final PasteElementObject pastedObject = (PasteElementObject) clipboard
                .getContents(PasteElementTransfer.getInstance());
        // There is no data corresponding to PasteElementTransfer
        if (pastedObject == null) {
            return false;
        }
        
        final List<TransferItem> items = pastedObject.getTransferedItems();
        List<TransferItem> pastedStereotypeItems = new ArrayList<>();
        for (TransferItem item : items) {
            if (item.getTransferedElementRef().mc.equals("Infrastructure.Stereotype")) {
                pastedStereotypeItems.add(item);
            }
        }
        
        if (pastedStereotypeItems.isEmpty()) {
            return false;
        }
        for (TransferItem item : pastedStereotypeItems) {
            if (((ModelElement) this.treeInput.getTypedElement()).isStereotyped(null,
                    item.getTransferedElementRef().name)) {
                return false;
            }
            Stereotype stereotype = (Stereotype) CoreSession.getSession(this.treeInput.getTypedElement()).getModel()
                    .findByRef(item.getTransferedElementRef());
            if (!(this.treeInput.getTypedElement().getMClass()).hasBase(this.treeInput.getTypedElement().getMClass()
                    .getMetamodel().getMClass(stereotype.getBaseClassName()))) {
                return false;
            }
        }
        return true;
    }

    @objid ("632385b2-e228-415e-bb19-ff8895ed062a")
    public void onPasteStereotype() {
        final MObject targetElement = this.treeInput.getTypedElement();
        CoreSession session = CoreSession.getSession(targetElement);
        
        Clipboard clipboard = new Clipboard(this.ui.getComposite().getDisplay());
        final PasteElementObject pastedObject = (PasteElementObject) clipboard
                .getContents(PasteElementTransfer.getInstance());
        
        final List<TransferItem> items = pastedObject.getTransferedItems();
        List<TransferItem> pastedStereotypeItems = new ArrayList<>();
        for (TransferItem item : items) {
            if (item.getTransferedElementRef().mc.equals("Infrastructure.Stereotype")) {
                pastedStereotypeItems.add(item);
            }
        }
        
        if (pastedObject.getPasteType() == PasteType.COPY) {
            try (ITransaction transaction = session.getTransactionSupport().createTransaction("Paste stereotypes")) {
                // paste stereotypes
                ModelElement selectedModelElement = (ModelElement) targetElement;
                for (TransferItem item : pastedStereotypeItems) {
                    Stereotype stereotype = (Stereotype) session.getModel().findByRef(item.getTransferedElementRef());
                    selectedModelElement.getExtension().add(stereotype);
                }
                transaction.commit();
            } catch (Exception e) {
                // Should catch InvalidModelManipulationException to display a
                // popup box, but it
                // is not a RuntimeException.
                ElementPropertyController.reportException(ModelProperty.I18N.getMessage("CannotPasteClipboard"), e);
            }
        } else if (pastedObject.getPasteType() == PasteType.CUT) {
            // cannot cut/paste an element onto itself or a child
            try (ITransaction transaction = session.getTransactionSupport().createTransaction("Cut stereotypes")) {
                // paste stereotypes
                ModelElement selectedModelElement = (ModelElement) targetElement;
                for (TransferItem item : pastedStereotypeItems) {
                    Stereotype stereotype = (Stereotype) session.getModel().findByRef(item.getTransferedElementRef());
                    MRef oldParentRef = item.getOldParentRef();
                    ModelElement oldParent = (ModelElement) session.getModel().findByRef(oldParentRef);
                    oldParent.getExtension().remove(stereotype);
                    selectedModelElement.getExtension().add(stereotype);
                }
        
                transaction.commit();
        
                // Keep the elements in the clipboard, but as a copy
                pastedObject.setPasteType(PasteType.COPY);
                clipboard.setContents(new Object[] { pastedObject },
                        new Transfer[] { PasteElementTransfer.getInstance() });
            } catch (Exception e) {
                ElementPropertyController.reportException(ModelProperty.I18N.getMessage("CannotPasteClipboard"), e);
            }
        }
    }

}
