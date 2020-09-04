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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.diagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * CompositeStructureDiagram v0.0.9054
 * 
 * 
 * <p>Composite Structure diagrams depict the internal structure of a classifier, as well as the internal details of a collaboration.</p><p>There you can define connection details between parts, show which port connects to which other one, insert collaboration uses within collaboration, etc.</p><p>This kind of diagram is used at a fine level of detail, to express how occurrences (parts) are related and connected.</p>
 */
@objid ("0072e322-c4bf-1fd8-97fe-001ec947cd2a")
public interface CompositeStructureDiagram extends StaticDiagram {
    /**
     * The metaclass simple name.
     */
    @objid ("2df366de-6dca-437b-8e92-ebc167e91795")
    public static final String MNAME = "CompositeStructureDiagram";

    /**
     * The metaclass qualified name.
     */
    @objid ("8322c1a0-ab3c-4483-b802-8966d58b6ed1")
    public static final String MQNAME = "Standard.CompositeStructureDiagram";

}
