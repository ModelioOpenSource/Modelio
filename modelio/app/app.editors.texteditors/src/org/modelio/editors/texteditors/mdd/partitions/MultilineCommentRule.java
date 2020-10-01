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
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

@objid ("7b60ef85-2a77-11e2-9fb9-bc305ba4815c")
public class MultilineCommentRule extends MultiLineRule {
    @objid ("7b60ef86-2a77-11e2-9fb9-bc305ba4815c")
    private MDDPartitionScanner _scanner;

    @objid ("7b60ef87-2a77-11e2-9fb9-bc305ba4815c")
    private IToken roToken;

    @objid ("7b60ef88-2a77-11e2-9fb9-bc305ba4815c")
    public MultilineCommentRule(String startSequence, String endSequence, IToken commentToken, IToken roToken, MDDPartitionScanner scanner) {
        super(startSequence, endSequence, commentToken);
        this._scanner = scanner;
        this.roToken = roToken;
    }

    @objid ("7b60ef8f-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner, boolean resume) {
        IToken token = super.evaluate(scanner, resume);
        // return token;
        return (this._scanner.editableArea) ? token : Token.UNDEFINED;
    }

    @objid ("7b60ef95-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        IToken token = super.evaluate(scanner);
        // return token;
        return (this._scanner.editableArea) ? token : Token.UNDEFINED;
    }

}
