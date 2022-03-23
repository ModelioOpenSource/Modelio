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
package org.modelio.diagram.styles.manager;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.NamedStyle;

/**
 * Represents a missing named style.
 * @author cma
 * @since 3.7
 */
@objid ("40e34e28-b444-4cb2-b444-42c03e5c8deb")
class MissingNamedStyle extends NamedStyle {
    @objid ("a812f2ec-dd50-4879-9ca5-16c0b4cbb893")
    public  MissingNamedStyle(String name) {
        super(name, FactoryStyle.getInstance());
    }

    @objid ("f359c7da-5282-4d0a-96d4-5aebc593237d")
    @Override
    public void write(IDiagramWriter out) {
        // write a reference to a NamedStyle with same name.
        out.writeExtRef(new NamedStyle(getName(), getCascadedStyle()), "", getName());
        
    }

}
