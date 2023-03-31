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
package org.modelio.diagram.elements.core.requests;

import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.Request;

/**
 * Request that forwards another request with another type.
 */
@objid ("22465844-0f7b-4065-a8b5-a1254f98108a")
public class WrappedRequest extends Request {
    
    @mdl.prop
    @objid ("911d60f2-e8bc-49f4-9b40-506a452dd298")
    private Request request;

    @mdl.propgetter
    public Request getRequest() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.request;
    }

    @mdl.propsetter
    public void setRequest(Request value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.request = value;
    }

    @objid ("5a8d9aea-ab11-4324-86d9-80f1d211d53b")
    public  WrappedRequest(Object type, Request request) {
        super(type);
        this.request = request;
        
    }

}
