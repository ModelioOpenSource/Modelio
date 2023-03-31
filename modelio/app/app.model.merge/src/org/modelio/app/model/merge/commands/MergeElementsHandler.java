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
package org.modelio.app.model.merge.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.model.merge.internal.MergeElementsData;
import org.modelio.app.model.merge.internal.MergeElementsDialog;
import org.modelio.app.model.merge.plugin.AppModelMerge;
import org.modelio.gproject.mtools.merge.MergeMachine;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for the MergeElements command. <br/>
 * See e4 model in <b>e4model/app.model.merge.e4xmi</b> file.
 */
@objid ("cdc14a59-a39f-4141-83df-1b86e534bf5b")
public class MergeElementsHandler {
    /**
     * Create a new empty pattern.
     * @param patternService the pattern service.
     * @param selection the current modelio selection.
     */
    @objid ("3507af3a-6544-454e-9835-57e3c2ee40f5")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell activeShell, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IModelioNavigationService selectionService) {
        final MObject selectedElement = SelectionHelper.getFirst(selection, MObject.class);
        
        MergeElementsDialog dialog = new MergeElementsDialog(activeShell,selectionService);
        dialog.setModal(false);
        dialog.setInput(SelectionHelper.toList(selection, MObject.class));
        if (dialog.open() != IDialogConstants.OK_ID)
            return;
        
        MergeElementsData data = dialog.getDataModel();
        
        CoreSession session = CoreSession.getSession(selectedElement);
        String transactionName = AppModelMerge.I18N.getMessage("merge.transaction",
                data.streamTargets().map(MObject::getName).collect(Collectors.joining(", ")),
                data.getTarget().getName());
        try (ITransaction transaction = session.getTransactionSupport().createTransaction(transactionName);) {
            MergeMachine machine = new MergeMachine(data.getTarget());
            machine.setDeleteNewReflexiveLinks(data.isDeleteReflexiveLinks());
            machine.setReplaceAttributes(false);
        
            data.streamTargets().forEach(machine::addSource);
            machine.merge();
        
            transaction.commit();
        
            // Select created element
            selectionService.fireNavigate((MObject)null);
        } catch (org.modelio.vcore.smkernel.IllegalModelManipulationException e) {
            // error already displayed to the user by the audit
            AppModelMerge.LOG.error(e);
        } catch (RuntimeException e) {
            AppModelMerge.LOG.error(e);
            MessageDialog.openError(activeShell, AppModelMerge.I18N.getString("gui.error.title"), e.getMessage());
        }
        
    }

    /**
     * Active only when the selection contains at least 2 modifiable elements of the same type.
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("8b138771-2d48-4564-bc67-f70d7108b0ed")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        final List<MObject> selectedElements = SelectionHelper.toList(selection, MObject.class);
        
        if (selectedElements.size() < 2)
            return false;
        
        MObject first = selectedElements.get(0);
        return selectedElements.stream().allMatch(
                o -> o.getMClass() == first.getMClass()
                && o.isModifiable());
        
    }

    /**
     * Active only when the selection contains at least 2 elements of the same type.
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("0a1e9262-48bb-484e-8d14-32687c51246e")
    @Evaluate
    public final boolean isVisible(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        final List<MObject> selectedElements = SelectionHelper.toList(selection, MObject.class);
        
        if (selectedElements.size() < 2)
            return false;
        
        MObject first = selectedElements.get(0);
        return selectedElements.stream().allMatch(
                o -> o.getMClass() == first.getMClass());
        
    }

}
