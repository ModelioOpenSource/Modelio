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

package org.modelio.model.browser.view.handlers.history;

import java.security.InvalidParameterException;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implements the 'select next/previous selection' command in the model explorer.
 */
@objid ("396fa02b-4603-11e2-960d-002564c97630")
public class SelectFromHistoryHandler {
    @objid ("60883af6-24ba-4733-aafd-ea92d09b5f6f")
    private static final String NEXT_ELEMENT = "next";

    @objid ("cfef9ddf-63d6-484e-ac4c-42ea913f0a01")
    private static final String PREVIOUS_ELEMENT = "previous";

    /**
     * Selection history is global, only one instance keeps track of the selection despite this handler
     * being part of a specific view.
     */
    @objid ("e21da8ac-7730-4a07-94d3-9b645b1ae8aa")
    private static SelectionHistory selectionHistory = new SelectionHistory();

    @objid ("47b1872c-4603-11e2-960d-002564c97630")
    @Execute
    public void execute(@Named("org.modelio.model.browser.view.command.selectfromhistory.direction") final String direction, IModelioNavigationService selectionService) {
        if (SelectFromHistoryHandler.NEXT_ELEMENT.equals(direction)) {
            SelectFromHistoryHandler.selectionHistory.selectNextSelection(selectionService);
        } else if (SelectFromHistoryHandler.PREVIOUS_ELEMENT.equals(direction)) {
            SelectFromHistoryHandler.selectionHistory.selectPreviousSelection(selectionService);
        } else {
            throw new InvalidParameterException("Invalid direction for the selection history");
        }
    }

    @objid ("47b1ae3d-4603-11e2-960d-002564c97630")
    @CanExecute
    public final boolean canExecute(@Named("org.modelio.model.browser.view.command.selectfromhistory.direction") final String direction, @Named(IServiceConstants.ACTIVE_SELECTION) final ISelection selection) {
        // Manually trigger 'onSelectionChanged' method, as it doesn't work when injected independantly
        onSelectionChanged(selection);
        
        if (SelectFromHistoryHandler.NEXT_ELEMENT.equals(direction)) {
            return SelectFromHistoryHandler.selectionHistory.hasNext();
        } else if (SelectFromHistoryHandler.PREVIOUS_ELEMENT.equals(direction)) {
            return SelectFromHistoryHandler.selectionHistory.hasPrevious();
        } else {
            return false;
        }
    }

    /**
     * When the selection changes, update the selection history.
     */
    @objid ("47b1d54c-4603-11e2-960d-002564c97630")
    private void onSelectionChanged(@Named(IServiceConstants.ACTIVE_SELECTION) final ISelection selection) {
        SelectFromHistoryHandler.selectionHistory.update(SelectionHelper.toList(selection, MObject.class));
    }

    /**
     * When the current project is closed, clear the selection history.
     */
    @objid ("4c60eebb-192f-47ce-a9be-8d4e708f6534")
    @Inject
    @Optional
    void onProjectClosed(@SuppressWarnings("unused") @UIEventTopic(ModelioEventTopics.PROJECT_CLOSING) final GProject project) {
        SelectFromHistoryHandler.selectionHistory.clear();
    }

}
