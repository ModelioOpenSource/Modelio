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

package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.modelio.diagram.elements.core.requests.NavigationRequest;

/**
 * {@link SelectEditPartTracker} that fires a Modelio navigation event on &lt;ctrl>+&lt;alt>+click.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("55ac4918-6fe0-4a7f-9bcd-3571d637aac3")
public class FireNavigateEditPartTracker extends SelectEditPartTracker {
    @objid ("5950d12e-e497-4cd6-a4c4-a4af31821391")
    public FireNavigateEditPartTracker(EditPart owner) {
        super(owner);
    }

    /**
     * Fire a Modelio navigation event on &lt;ctrl>+&lt;alt>+click.
     */
    @objid ("944f63c6-2b30-4131-b7ef-9fc5c7464af7")
    @Override
    protected boolean handleButtonDown(int button) {
        if (button == 1) {
            final Input currentInput = getCurrentInput();
        
            if (currentInput.isControlKeyDown() && currentInput.isAltKeyDown()) {
                performNavigation();
                return true;
            }
        }
        return super.handleButtonDown(button);
    }

    /**
     * Creates a {@link NavigationRequest} and sends it to the source edit part
     * via {@link EditPart#performRequest(Request)}.
     * <p>
     * Uses are to fire a Modelio navigation on
     * the selected item to select it in the browser.
     */
    @objid ("377c1a17-b6dd-4d6c-bf9d-99f93b658956")
    protected void performNavigation() {
        NavigationRequest request = new NavigationRequest();
        request.setLocation(getLocation());
        getSourceEditPart().performRequest(request);
    }

}
