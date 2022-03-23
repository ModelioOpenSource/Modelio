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
package org.modelio.editors.texteditors.rt.partitions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

@objid ("7b6423cf-2a77-11e2-9fb9-bc305ba4815c")
public class CommentRule extends EndOfLineRule {
    @objid ("7b6423d0-2a77-11e2-9fb9-bc305ba4815c")
    private RTPartitionScanner _scanner;

    @objid ("7b6423d1-2a77-11e2-9fb9-bc305ba4815c")
    public  CommentRule(String startSequence, IToken commentToken, RTPartitionScanner scanner) {
        super(startSequence, commentToken);
        this._scanner = scanner;
        
    }

    @objid ("7b6423d6-2a77-11e2-9fb9-bc305ba4815c")
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
            return token;
        } else {
            // rewind
            while (swallowedCharNb-- > 0)
            scanner.unread();
            return Token.UNDEFINED;
        }
        
    }

    @objid ("7b6423db-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
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
                
        IToken token = super.evaluate(scanner);
        if (token == this.fToken) {
            return token;
        } else {
            // rewind
            while (swallowedCharNb-- > 0)
            scanner.unread();
            return Token.UNDEFINED;
        }
        
    }

    @objid ("7b6423df-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken getSuccessToken() {
        return super.getSuccessToken();
    }

}
