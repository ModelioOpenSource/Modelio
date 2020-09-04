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

package org.modelio.editors.texteditors.mdd.partitions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
import org.modelio.editors.texteditors.mdd.partitions.AnyTextRule2;

@objid ("7b5de26e-2a77-11e2-9fb9-bc305ba4815c")
public class MDDReplacePartitionScanner extends RuleBasedPartitionScanner {
    @objid ("7b5f68dc-2a77-11e2-9fb9-bc305ba4815c")
    public boolean editableArea = false;

    @objid ("abfb6446-2a77-11e2-9fb9-bc305ba4815c")
    public static final String RW_PARTITION = "_RW";

    @objid ("7b5f68dd-2a77-11e2-9fb9-bc305ba4815c")
     IToken roToken;

    @objid ("7b5f68de-2a77-11e2-9fb9-bc305ba4815c")
     IToken rwToken;

    @objid ("7b5f68df-2a77-11e2-9fb9-bc305ba4815c")
     IToken tagToken;

    @objid ("7b5f68e0-2a77-11e2-9fb9-bc305ba4815c")
     IPredicateRule[] rules = null;

    @objid ("7b5f68e3-2a77-11e2-9fb9-bc305ba4815c")
    public MDDReplacePartitionScanner() {
        this.rwToken  = new Token(RW_PARTITION);
        this.rules = new IPredicateRule[1];
        this.rules[0] = new AnyTextRule2(this.rwToken, this.rwToken, this);
        
        setPredicateRules(this.rules);
        setDefaultReturnToken(this.rwToken);
    }

}

@objid ("7b5f68e5-2a77-11e2-9fb9-bc305ba4815c")
class AnyTextRule2 implements IPredicateRule {
    @objid ("7b5f68e9-2a77-11e2-9fb9-bc305ba4815c")
     IToken roToken;

    @objid ("7b5f68ec-2a77-11e2-9fb9-bc305ba4815c")
     IToken rwToken;

    @objid ("7b5f68e8-2a77-11e2-9fb9-bc305ba4815c")
    private MDDReplacePartitionScanner _scanner;

    @objid ("7b5f68ef-2a77-11e2-9fb9-bc305ba4815c")
    public AnyTextRule2(IToken roToken, IToken rwToken, MDDReplacePartitionScanner scanner) {
        this.roToken = roToken;
        this.rwToken = rwToken;
        this._scanner = scanner;
    }

    @objid ("7b5f68f8-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner, boolean resume) {
        return evaluate(scanner);
    }

    @objid ("7b5f6902-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken getSuccessToken() {
        // TODO Auto-generated method stub
        return this.rwToken;
    }

    @objid ("7b5f6909-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        int c ;
        
        while ( (c = scanner.read()) != 10 ) {
            if (c == ICharacterScanner.EOF) {
                scanner.unread();
                return Token.UNDEFINED;
            }
        }
        return this.rwToken;
    }

}
