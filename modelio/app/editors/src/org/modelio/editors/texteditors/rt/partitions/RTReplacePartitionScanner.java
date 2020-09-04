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

package org.modelio.editors.texteditors.rt.partitions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

@objid ("7b658367-2a77-11e2-9fb9-bc305ba4815c")
public class RTReplacePartitionScanner extends RuleBasedPartitionScanner {
    @objid ("7b65836a-2a77-11e2-9fb9-bc305ba4815c")
    public boolean editableArea = false;

    @objid ("abfff823-2a77-11e2-9fb9-bc305ba4815c")
    public static final String RW_PARTITION = "_RW";

    @objid ("7b65836b-2a77-11e2-9fb9-bc305ba4815c")
     IToken roToken;

    @objid ("7b65aa78-2a77-11e2-9fb9-bc305ba4815c")
     IToken rwToken;

    @objid ("7b65aa79-2a77-11e2-9fb9-bc305ba4815c")
     IToken tagToken;

    @objid ("7b65aa7a-2a77-11e2-9fb9-bc305ba4815c")
     IPredicateRule[] rules = null;

    @objid ("7b65aa7d-2a77-11e2-9fb9-bc305ba4815c")
    public RTReplacePartitionScanner() {
        this.rwToken  = new Token(RW_PARTITION);
        this.rules = new IPredicateRule[1];
        //rules[0] = new AnyTextRule2(rwToken, rwToken, this);
        
        setPredicateRules(this.rules);
        setDefaultReturnToken(this.rwToken);
    }

}
