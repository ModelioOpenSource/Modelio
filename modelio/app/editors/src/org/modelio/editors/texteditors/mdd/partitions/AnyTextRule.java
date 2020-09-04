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

package org.modelio.editors.texteditors.mdd.partitions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

@objid ("7b5ad4f8-2a77-11e2-9fb9-bc305ba4815c")
public class AnyTextRule implements IPredicateRule {
    @objid ("7b5ad4f9-2a77-11e2-9fb9-bc305ba4815c")
    private MDDPartitionScanner _scanner;

    @objid ("7b5ad4fa-2a77-11e2-9fb9-bc305ba4815c")
     IToken roToken;

    @objid ("7b5c5b98-2a77-11e2-9fb9-bc305ba4815c")
     IToken rwToken;

    @objid ("7b5c5b99-2a77-11e2-9fb9-bc305ba4815c")
    public AnyTextRule(IToken roToken, IToken rwToken, MDDPartitionScanner scanner) {
        this.roToken = roToken;
        this.rwToken = rwToken;
        this._scanner = scanner;
    }

    @objid ("7b5c5b9e-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner, boolean resume) {
        return Token.UNDEFINED;
    }

    @objid ("7b5c5ba4-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken getSuccessToken() {
        return (this._scanner.editableArea) ? this.rwToken : this.roToken;
    }

    @objid ("7b5c5ba9-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        int c;
                
        c = scanner.read();
        if (c == ICharacterScanner.EOF) {
            return Token.UNDEFINED;
        } else
            scanner.unread();
                
        if (!this._scanner.editableArea) {
            do {
            c = scanner.read();
            if (c == ICharacterScanner.EOF) {
                scanner.unread();
                return this.roToken;
            }
                
            } while (c != 10);
                
            return this.roToken;
                
        } else {
            int n = 0;
            c = scanner.read();
            n = n+1;
            if (c == ' ' || c == '\t') {
            // Swallow white spaces
            while (c == ' ' || c == '\t') {
                c = scanner.read();
                n++;
            }
            scanner.unread();
            n--;
            return (n > 0) ? this.rwToken : Token.UNDEFINED;
            } else if (Character.isLetterOrDigit(c)) {
            // Swallow a word
            while (Character.isLetterOrDigit(c)) {
                c = scanner.read();
                n++;
            }
            scanner.unread();
            n--;
            return (n > 0) ? this.rwToken : Token.UNDEFINED;
            } else {
            scanner.unread();
            return Token.UNDEFINED;
            }
        }
    }

}
