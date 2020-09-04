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

package org.modelio.editors.texteditors.mdd.partitions;

import java.util.HashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

@objid ("7b5c5bc4-2a77-11e2-9fb9-bc305ba4815c")
public class KeywordRule implements IPredicateRule {
    @objid ("7b5c5bc5-2a77-11e2-9fb9-bc305ba4815c")
    private MDDPartitionScanner _scanner;

    @objid ("7b5c5bc6-2a77-11e2-9fb9-bc305ba4815c")
     IToken roToken;

    @objid ("7b5c5bc7-2a77-11e2-9fb9-bc305ba4815c")
     IToken rwToken;

    @objid ("7b5c5bc8-2a77-11e2-9fb9-bc305ba4815c")
     IToken keywordToken;

    @objid ("7b5c5bc9-2a77-11e2-9fb9-bc305ba4815c")
     HashMap<String, IToken> keywords;

    @objid ("7b5c5bcd-2a77-11e2-9fb9-bc305ba4815c")
    private StringBuffer buffer;

    @objid ("7b5c5bce-2a77-11e2-9fb9-bc305ba4815c")
    public KeywordRule(IToken roToken, IToken rwToken, IToken keywordToken, MDDPartitionScanner scanner) {
        this.roToken = roToken;
        this.rwToken = rwToken;
        this.keywordToken = keywordToken;
        this._scanner = scanner;
        this.buffer = new StringBuffer();
        this.keywords = new HashMap<>();
    }

    @objid ("7b5c5bd4-2a77-11e2-9fb9-bc305ba4815c")
    public void addKeyword(String keyword, IToken token) {
        this.keywords.put(keyword, token);
    }

    @objid ("7b5de239-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner cscanner, boolean resume) {
        // TODO Auto-generated method stub
        return evaluate(cscanner);
    }

    @objid ("7b5de240-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken getSuccessToken() {
        return this.keywordToken;
    }

    @objid ("7b5de245-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner cscanner) {
        this.buffer.setLength(0);
        int c = cscanner.read();
        //System.out.print("KeywordRule.Evaluate " + (char) c);
        if (c != ICharacterScanner.EOF && Character.isLetter(c)) {
            do {
            this.buffer.append((char) c);
            c = cscanner.read();
            //System.out.print("." + (char) c);
            } while (c != ICharacterScanner.EOF && Character.isLetter(c));
        }
        cscanner.unread();
        //System.out.println("!");
                
        // Look up in the keyword table
        String word = this.buffer.toString();
        IToken token = this.keywords.get(word);
                
        //System.out.println("     result >" + word + "< , " + token);
        if (token == null) {
            // the word we swallowed was not a keyword
            // unread chars
            ////System.out.print("    unreading ");
            for (int i = this.buffer.length() - 1; i >= 0; i--) {
            ////System.out.print(buffer.charAt(i));
            cscanner.unread();
            }
            ////System.out.println("");
            return Token.UNDEFINED;
        } else
            return  (this._scanner.editableArea)? token:this.roToken;
    }

}
