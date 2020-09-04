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

package org.modelio.diagram.editor.sequence.elements.modelmanipulation;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * A functor object which return a boolean.
 */
@objid ("d96e58ce-55b6-11e2-877f-002564c97630")
public abstract class Predicate {
    @objid ("c0d11143-f1d8-40fd-8222-e247d3188fa5")
    protected Variable firstVariable;

    @objid ("594db0cb-8b2d-478d-819c-1ef4b275abff")
    protected Variable secondVariable;

    @objid ("d96e58d2-55b6-11e2-877f-002564c97630")
    public Predicate(final Variable firstVariable, final Variable secondVariable) {
        this.firstVariable = firstVariable;
        this.secondVariable = secondVariable;
    }

    @objid ("d96e58d8-55b6-11e2-877f-002564c97630")
    public abstract boolean evaluate();

    /**
     * @return the involved variables.
     */
    @objid ("9a04934e-3bd2-4fd5-8af1-e30c57ccbc12")
    public Iterable<Variable> getVariables() {
        return Arrays.asList(this.firstVariable, this.secondVariable);
    }

}
