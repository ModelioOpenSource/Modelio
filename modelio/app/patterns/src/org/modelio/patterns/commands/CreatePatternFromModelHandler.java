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

package org.modelio.patterns.commands;

import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.api.IPatternRepository;
import org.modelio.patterns.api.IPatternService;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for the CreatePatternFromModel command. <br/>
 * See e4 model in <b>e4model/patterns.e4xmi</b> file.
 */
@objid ("ec37e7ff-9446-4eb7-8ac4-95f72b32f3fb")
public class CreatePatternFromModelHandler {
    /**
     * Create a new pattern from an existing model.
     * @param selection the current modelio selection.
     * @param patternService the pattern service.
     */
    @objid ("9653df19-8e32-4769-a19e-cf25b26b1a86")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell activeShell, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IPatternService patternService, IModelioNavigationService selectionService) {
        IPatternRepository repository = patternService.getCatalog();
        
        final ModelElement selectedElement = SelectionHelper.getFirst(selection, ModelElement.class);
        
        CoreSession session = CoreSession.getSession(selectedElement);
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Create Pattern From Model")) {
            Package pattern = ProfileUtils.createPatternFromModel(selectedElement, repository);
            transaction.commit();
        
            // Select created element
            selectionService.fireNavigate(pattern);
        } catch (Exception e) {
            Patterns.LOG.debug(e);
            MessageDialog.openError(activeShell, Patterns.I18N.getString("Gui.ErrorTitle"), e.getMessage());
        }
    }

    /**
     * Available only when the selection contains only a ModelElement that is not a pattern.
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("1df12406-6675-4a93-9c41-bbbec7e7df6c")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        final List<MObject> selectedElements = SelectionHelper.toList(selection, MObject.class);
        
        if (selectedElements.size() == 1 && selectedElements.get(0) instanceof ModelElement) {
            ModelElement element = (ModelElement) selectedElements.get(0);
            if (element.isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERN)) {
                return false;
            }
            return ProfileUtils.getRoot(element) != null;
        }
        return false;
    }

}
