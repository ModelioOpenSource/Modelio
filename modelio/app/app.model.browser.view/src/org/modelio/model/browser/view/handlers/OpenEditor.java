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
package org.modelio.model.browser.view.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.model.ui.swt.SelectionHelper;

/**
 * Handler for the "Open diagram..." command.
 */
@objid ("08606f49-2d38-4408-9ef8-77309bdc19e9")
public class OpenEditor {
    @objid ("691d086d-01a8-4d70-ad4d-b37ac792075c")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IActivationService activationService) {
        for (Element e : SelectionHelper.toList(selection, Element.class)) {
            activationService.activateMObject(e);
        }
        
    }

}
