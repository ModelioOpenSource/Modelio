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
@objid ("472b1f61-fab7-4217-98d3-c5ebc2add462")
public class WrappedRequest extends Request {
    
    @mdl.prop
    @objid ("1d603291-66a4-4cf1-8758-edfea5a14774")
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

    @objid ("a963b04a-c7f1-4c7b-9861-4241167dc0bc")
    public  WrappedRequest(Object type, Request request) {
        super(type);
        this.request = request;
        
    }

}
