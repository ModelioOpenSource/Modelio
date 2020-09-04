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

package org.modelio.diagram.editor.sequence.elements.modelmanipulation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This Predicate return <code>true</code> if <code>before + offset <(=) after</code>.
 * 
 * @author fpoyer
 */
@objid ("d96e58ba-55b6-11e2-877f-002564c97630")
public class IsBeforePredicate extends Predicate {
    @objid ("d96e58bc-55b6-11e2-877f-002564c97630")
    private int offset;

    @objid ("d96e58bd-55b6-11e2-877f-002564c97630")
    private boolean strict;

    /**
     * C'tor.
     * 
     * @param before the first variable. Predicate returns <code>true</code> if it is less than (or equal, depending on the value of "strict") <code>after - offset</code>.
     * @param after the second variable. Predicate returns <code>true</code> if it is greater than (or equal, depending on the value of "strict") <code>before + offset</code>.
     * @param offset an offset that may be used to define a minimal spacing between the variables.
     * @param strict <i>true</i> to refuse equality, <i>false</i> to accept equality.
     */
    @objid ("d96e58be-55b6-11e2-877f-002564c97630")
    public IsBeforePredicate(final Variable before, final Variable after, final int offset, final boolean strict) {
        super(before, after);
        this.offset = offset;
        this.strict = strict;
    }

    /**
     * Return <code>true</code> if the value of the first variable ("before") if less than (or equal if not strict) to the second variable ("after").
     */
    @objid ("d96e58c9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean evaluate() {
        if (this.strict) {
            return (this.firstVariable.getValue() + this.offset) < this.secondVariable.getValue();
        } else {
            return (this.firstVariable.getValue() + this.offset) <= this.secondVariable.getValue();
        }
    }

    @objid ("d590d08a-bc75-4348-a942-cbabd51735b6")
    @Override
    public String toString() {
        String op = this.strict ? "<" : "<=";
        return this.firstVariable.getRef() + " + " + this.offset + op + " " + this.secondVariable.getRef();
    }

}
