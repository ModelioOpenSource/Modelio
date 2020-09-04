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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

/**
 * Modelio implementation for {@link ConnectionEndpointEditPolicy}.
 * <p>
 * Handles read only connections.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("ed2e66d1-8922-408b-b814-8e07b0dc69ad")
public class DefaultConnectionEndpointEditPolicy extends ConnectionEndpointEditPolicy {
    @objid ("35e370a9-8603-4691-b75b-b80e944dd9e1")
    @Override
    protected List createSelectionHandles() {
        List<?> ret = super.createSelectionHandles();
        
        SelectionHandlesBuilder.disableHandlesIfReadOnly(getHost(), ret);
        return ret;
    }

}
