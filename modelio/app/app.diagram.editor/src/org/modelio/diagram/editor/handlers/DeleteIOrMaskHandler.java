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
package org.modelio.diagram.editor.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.editor.preferences.DiagramPreferencesPage;
import org.modelio.diagram.editor.preferences.DiagramPreferencesPage.DeleteMode;
import org.modelio.platform.preferences.plugin.Preferences;

/**
 * Handler for the delete model element command.
 * <p>
 * According to the Modelio preferences, delegates to {@link DeleteInModelHandler} or {@link MaskHandler}.
 * </p>
 */
@objid ("a195b688-ad79-430d-91cc-11e99144ff21")
public class DeleteIOrMaskHandler {
    @objid ("d4e4abb0-1122-4f8c-a242-9b6b735df4eb")
    private static final MaskHandler MASK_HANDLER = new MaskHandler();

    @objid ("c550de79-d809-480c-ab63-1bd7e10a284c")
    private static final DeleteInModelHandler DELETE_HANDLER = new DeleteInModelHandler();

    @objid ("2713594d-ba4f-4292-bae7-b5480d4e30d4")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        if (isMaskModeOn()) {
            MASK_HANDLER.execute(selection);
        } else {
            DELETE_HANDLER.execute(selection);
        }
        
    }

    @objid ("3fa3e89f-2254-4cd9-9796-c108b9af05d7")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        if (isMaskModeOn()) {
            return MASK_HANDLER.canExecute(selection);
        } else {
            return DELETE_HANDLER.canExecute(selection);
        }
        
    }

    @objid ("3e5377ac-f822-4a94-b732-7f15cb5fb519")
    private boolean isMaskModeOn() {
        IPersistentPreferenceStore prefs = Preferences.getPreferences();
        String value = prefs.getString(DiagramPreferencesPage.DELETE_MODE_PREFKEY);
        if (value == null) {
            value = prefs.getDefaultString(DiagramPreferencesPage.DELETE_MODE_PREFKEY);
        }
        return DeleteMode.MASK.name().equals(value);
    }

}
