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

package org.modelio.model.property.stereotype.chooser;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("f38229c8-c38e-4e14-8db0-5095f4d2d638")
public class StereotypeChooserModel {
    @objid ("57b4173f-059b-411c-994a-3768de58f000")
    private ModelElement element;

    @objid ("80576a9f-989a-428c-9606-d0819c5b9a1a")
    public StereotypeChooserModel(ModelElement element) {
        this.element = element;
    }

    /**
     * Get the element beeing edited.
     * 
     * @return a model element
     */
    @objid ("895c275e-97b1-4518-b722-02ce25e9ffbf")
    public ModelElement getElement() {
        return this.element;
    }

}
