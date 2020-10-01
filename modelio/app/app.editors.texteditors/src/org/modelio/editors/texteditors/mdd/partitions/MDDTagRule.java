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
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

@objid ("7b5f6913-2a77-11e2-9fb9-bc305ba4815c")
public class MDDTagRule extends EndOfLineRule {
    @objid ("7b5f6914-2a77-11e2-9fb9-bc305ba4815c")
    private MDDPartitionScanner _scanner;

    @objid ("7b5f6915-2a77-11e2-9fb9-bc305ba4815c")
    public MDDTagRule(String startSequence, IToken token, MDDPartitionScanner scanner) {
        super(startSequence, token);
        this._scanner = scanner;
    }

    @objid ("7b5f691a-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner, boolean resume) {
        // Swallow leading white spaces
        // then apply super class rule
        // if super method fails, restore the swallowed spaces
        int swallowedCharNb = 0;
        int c;
        while (Character.isWhitespace(c = scanner.read()) && c != 10) {
            if (c == ICharacterScanner.EOF) {
            // scanner.unread();
            return Token.UNDEFINED;
            }
            swallowedCharNb++;
        }
                
        scanner.unread();
        IToken token = super.evaluate(scanner, resume);
        if (token == this.fToken) {
            this._scanner.toggleRule(this);
        } else {
            // rewind
                
            while (swallowedCharNb-- > 0)
            scanner.unread();
        }
        return token;
    }

    @objid ("7b60ef80-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        // Swallow leading white spaces
        // then apply super class rule
        // if super method fails, restore the swallowed spaces
        int swallowedCharNb = 0;
        int c;
        while (Character.isWhitespace(c = scanner.read()) && c != 10) { // Dirty
                                        // test
                                        // !
            if (c == ICharacterScanner.EOF) {
            scanner.unread();
            return Token.UNDEFINED;
            }
            swallowedCharNb++;
        }
        scanner.unread();
                
        IToken token = super.evaluate(scanner);
        if (token == this.fToken) {
            this._scanner.toggleRule(this);
        } else {
            // rewind
                
            while (swallowedCharNb-- > 0)
            scanner.unread();
        }
        return token;
    }

}
