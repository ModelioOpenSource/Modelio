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

package org.modelio.editors.texteditors.rt.partitions;

import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

@objid ("7b64c012-2a77-11e2-9fb9-bc305ba4815c")
public class RTPartitionScanner extends RuleBasedPartitionScanner {
    @objid ("abfe717c-2a77-11e2-9fb9-bc305ba4815c")
    public static final String[] keywords = {
					  "abstract", "continue", "for", "new", "switch", "assert", "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double", "implements", "protected", "throw", "byte",
	    "else", "import", "public", "throws", "case", "enum", "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final", "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const",
	    "float", "native", "super", "while",
					  };

    @objid ("7b64c013-2a77-11e2-9fb9-bc305ba4815c")
    private IToken tagToken;

    @objid ("7b64c014-2a77-11e2-9fb9-bc305ba4815c")
    private IPredicateRule[] rules = null;

    @objid ("7b64e729-2a77-11e2-9fb9-bc305ba4815c")
    private IToken keywordToken;

    @objid ("7b650e34-2a77-11e2-9fb9-bc305ba4815c")
    private IPredicateRule objidRule;

    @objid ("7b650e35-2a77-11e2-9fb9-bc305ba4815c")
    private Token commentToken;

    @objid ("7b650e36-2a77-11e2-9fb9-bc305ba4815c")
    public RTPartitionScanner() {
        this.tagToken = new Token(RTPartitionTypes.OBJINGID_PARTITION);
        this.keywordToken = new Token(RTPartitionTypes.KEYWORD_PARTITION);
        this.commentToken = new Token(RTPartitionTypes.COMMENT_PARTITION);
                
        Vector<IPredicateRule> therules = new Vector<>();
                
        KeywordRule keywordRule = new KeywordRule(this.keywordToken);
                
        for (int i = 0; i < keywords.length; i++)
            keywordRule.addKeyword(keywords[i], this.keywordToken);
                
        this.objidRule = new RTTagRule("@objid", this.tagToken);
                
        IPredicateRule comment = new CommentRule("//", this.commentToken, this);
        IPredicateRule multilineComment = new MultilineCommentRule("/*", "*/", this.commentToken, this);
        therules.add(keywordRule);
                
        therules.add(this.objidRule);
        therules.add(comment);
        therules.add(multilineComment);
                
        this.rules = new IPredicateRule[therules.size()];
        for (int i = 0; i < therules.size(); i++)
            this.rules[i] = therules.get(i);
                
        setPredicateRules(this.rules);
    }

}
