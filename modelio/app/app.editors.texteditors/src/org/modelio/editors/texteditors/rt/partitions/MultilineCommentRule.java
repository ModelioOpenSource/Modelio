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
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

@objid ("7b644adf-2a77-11e2-9fb9-bc305ba4815c")
public class MultilineCommentRule extends MultiLineRule {
    @objid ("7b644ae0-2a77-11e2-9fb9-bc305ba4815c")
    private RTPartitionScanner _scanner;

    @objid ("7b644ae1-2a77-11e2-9fb9-bc305ba4815c")
    public  MultilineCommentRule(String startSequence, String endSequence, IToken commentToken, RTPartitionScanner scanner) {
        super(startSequence, endSequence, commentToken);
        this._scanner = scanner;
        
    }

    @objid ("7b6471f3-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner, boolean resume) {
        IToken token = super.evaluate(scanner, resume);
        // return token;
        return token ;
    }

    @objid ("7b649902-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        return super.evaluate(scanner);
    }

}
