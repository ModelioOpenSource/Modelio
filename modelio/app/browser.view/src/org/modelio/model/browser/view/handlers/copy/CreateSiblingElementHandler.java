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

package org.modelio.model.browser.view.handlers.copy;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create sibling element(s) of the selected the one(s).
 */
@objid ("c5f5ca71-1584-4ded-8d2a-9c383504045d")
public class CreateSiblingElementHandler {
    @objid ("27db23e3-d6cd-4787-bfc6-bcfc9bc69667")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selection contains only one modifiable element.
     * 
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("5c23fc3d-162f-41b3-abd6-6438020801b2")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        // Must have at least an element
        List<MObject> toSiblings = CreateSiblingElementHandler.getSelectedElements(selection);
        if (toSiblings.isEmpty()) {
            return false;
        }
        
        MObject dest = CreateSiblingElementHandler.getPasteTarget(toSiblings);
        if (dest == null) {
            return false;
        }
        
        // Check the sibling elements can be added to destination element
        for (MObject element : toSiblings) {
            if (!MTools.getAuthTool().canAdd(dest, element.getMClass())) {
                return false;
            }
            if (!CreateSiblingElementHandler.canBeParentOf(dest, element)) {
                return false;
            }
            if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                if (dest instanceof Operation && parameter.getReturned() != null) {
                    Operation targetOperation = (Operation) dest;
                    if (targetOperation.getReturn() != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @objid ("fe2e2df7-886d-4f0b-968c-e450fb99a816")
    private static MObject getPasteTarget(List<MObject> toSiblings) {
        MObject ret = null;
        for (MObject obj : toSiblings) {
            // All elements to clone must have the same parent
            MObject compositionOwner = obj.getCompositionOwner();
            if (ret != null && ret != compositionOwner) {
                return null;
            } else {
                ret = compositionOwner;
            }
        }
        return ret;
    }

    @objid ("497e7883-e5a9-4415-8372-180d4db52839")
    private static List<MObject> getSelectedElements(final Object selection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (selection instanceof MObject) {
            selectedElements.add((MObject) selection);
        } else if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() >= 1) {
            Object[] elements = ((IStructuredSelection) selection).toArray();
            for (Object element : elements) {
                if (element instanceof MObject) {
                    selectedElements.add((MObject) element);
                } else if (element instanceof IAdaptable) {
                    final MObject adapter = ((IAdaptable) element).getAdapter(MObject.class);
                    if (adapter != null) {
                        selectedElements.add(adapter);
                    }
                }
            }
        }
        return selectedElements;
    }

    /**
     * Cut the currently selected elements.
     * 
     * @param selection the current modelio selection.
     * @param currentDisplay the display Modelio runs into.
     */
    @objid ("46474e53-bb88-4cfe-9824-331e53015720")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection, Display currentDisplay, IMModelServices mmServices) {
        final List<MObject> toSiblings = CreateSiblingElementHandler.getSelectedElements(selection);
        ICoreSession session = this.projectService.getSession();
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("CreateSiblingElement")) {
            IModelFactory mmFactory = mmServices.getModelFactory();
            IElementNamer mmNamer = mmServices.getElementNamer();
            for (MObject obj : toSiblings) {
                Element owner = (Element) obj.getCompositionOwner();
                MClass metaclass = obj.getMClass();
                MDependency dependency = ((SmObjectImpl) obj).getCompositionRelation().dep.getSymetric();
                MObject sibling = mmFactory.createElement(metaclass, owner, dependency);
                sibling.setName(mmNamer.getUniqueName(sibling));
                if (obj instanceof ModelElement) {
                    ((ModelElement) sibling).getExtension().addAll(((ModelElement) obj).getExtension());
                }
            }
            transaction.commit();
        } catch (Exception e) {
            // Should catch InvalidModelManipulationException to display a popup box, but it
            // is not a RuntimeException.
            reportException(e);
        }
    }

    @objid ("9351e962-c590-4a8e-bb55-213dd169d117")
    private void reportException(Exception e) {
        // Show an error box
        String title = BrowserViewActivator.I18N.getMessage("CannotCreateSiblingElement");
        
        MessageDialog.openError(null, title, e.getLocalizedMessage());
        
        BrowserViewActivator.LOG.error(e);
    }

    /**
     * Tells whether 'child' can be owned by 'parent'.
     * 
     * @param owner The future parent element
     * @param composed a child element
     * @return true only if parent can contain the child.
     */
    @objid ("ce546df1-488f-4139-967d-1c91cfa7972d")
    private static boolean canBeParentOf(final MObject owner, final MObject composed) {
        return owner.getMClass().getMetamodel().getMExpert().canCompose(owner, composed, null);
    }

}
