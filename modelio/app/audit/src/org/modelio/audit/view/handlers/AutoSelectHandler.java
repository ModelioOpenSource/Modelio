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

package org.modelio.audit.view.handlers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.audit.view.AuditView;

@objid ("10e68666-44f2-4f43-bc11-9c63cda98e74")
public class AutoSelectHandler {
    @objid ("95dbf7c3-01ee-4094-87da-0eb4b0c09688")
    @Execute
    public void execute(EModelService modelService, MApplication application) {
        List<String> tagsToMatch = new ArrayList<>();
        List<MPart> parts = modelService.findElements(application, AuditView.VIEW_ID, MPart.class, tagsToMatch);
        for (MPart part : parts) {
            if (part.getObject() instanceof AuditView) {
                AuditView view = (AuditView) part.getObject();
                view.setAutoSelectInExplorer(!view.isAutoSelectInExplorer());
            }
        }
    }

}
