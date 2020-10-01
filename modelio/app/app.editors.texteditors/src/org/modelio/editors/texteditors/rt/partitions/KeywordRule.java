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

import java.util.HashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

@objid ("7b6423e3-2a77-11e2-9fb9-bc305ba4815c")
public class KeywordRule implements IPredicateRule {
    @objid ("7b6423e4-2a77-11e2-9fb9-bc305ba4815c")
     IToken keywordToken;

    @objid ("7b6423e5-2a77-11e2-9fb9-bc305ba4815c")
     HashMap<String, IToken> keywords;

    @objid ("7b6423e9-2a77-11e2-9fb9-bc305ba4815c")
    private StringBuffer buffer;

    @objid ("7b6423ea-2a77-11e2-9fb9-bc305ba4815c")
    public KeywordRule(IToken keywordToken) {
        this.keywordToken = keywordToken;
        this.buffer = new StringBuffer();
        this.keywords = new HashMap<>();
    }

    @objid ("7b6423ed-2a77-11e2-9fb9-bc305ba4815c")
    public void addKeyword(String keyword, IToken token) {
        this.keywords.put(keyword, token);
    }

    @objid ("7b6423f1-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner cscanner, boolean resume) {
        return evaluate(cscanner);
    }

    @objid ("7b6423f8-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken getSuccessToken() {
        return this.keywordToken;
    }

    @objid ("7b6423fd-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner cscanner) {
        this.buffer.setLength(0);
        
        if (cscanner.getColumn() != 0) {
            cscanner.unread();
            int previousChar = cscanner.read();
            if (Character.isLetter(previousChar)) {
                return Token.UNDEFINED;
            }
        }
        
        int c = cscanner.read();
        if (c != ICharacterScanner.EOF && Character.isLetter(c)) {
            do {
                this.buffer.append((char) c);
                c = cscanner.read();
            } while (c != ICharacterScanner.EOF && Character.isLetter(c));
        }
        cscanner.unread();
        
        // Look up in the keyword table
        String word = this.buffer.toString();
        IToken token = this.keywords.get(word);
        if (token == null) {
            // the word we swallowed was not a keyword => unread chars
            for (int i = this.buffer.length() - 1; i >= 0; i--) {
                cscanner.unread();
            }
            return Token.UNDEFINED;
        
        } else
            return this.keywordToken;
    }

}
