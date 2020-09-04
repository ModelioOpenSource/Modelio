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

package org.modelio.audit.view.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.view.model.AuditElementModel;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("3a93dbae-7e9c-4f08-930a-113dc7ef0142")
public class SelectInExplorer extends AbstractAuditEntryHandler {
    @objid ("ba4c4093-4f46-4da9-8bef-249edcae4020")
    @Execute
    public void execute(EModelService modelService, MApplication application, IModelioNavigationService navigationService) {
        Object obj = getSelectedAuditEntry(modelService, application);
        MObject element = null;
        if (obj instanceof IAuditEntry) {
            element = ((IAuditEntry) obj).getElement();
        } else if (obj instanceof AuditElementModel) {
            element = ((AuditElementModel) obj).element;
        }
        
        if (element != null) {
            navigationService.fireNavigate(element);
        }
    }

    @objid ("ce5efdcc-ab12-4924-952f-cdc2ca3cfb2e")
    @CanExecute
    public boolean isEnabled(EModelService modelService, MApplication application) {
        Object obj = getSelectedAuditEntry(modelService, application);
        return obj instanceof IAuditEntry || obj instanceof AuditElementModel;
    }

}
