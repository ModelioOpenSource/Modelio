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

import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

@objid ("7b5de24a-2a77-11e2-9fb9-bc305ba4815c")
public class MDDPartitionScanner extends RuleBasedPartitionScanner {
    @objid ("7b5de251-2a77-11e2-9fb9-bc305ba4815c")
    public boolean editableArea = false;

    @objid ("ab4be9e9-2a77-11e2-9fb9-bc305ba4815c")
    public static final String[] keywords = { "asm", "do", "if", "return", "typedef", "auto", "double", "inline", "short", "typeid", "bool", "dynamic_cast", "int", "signed", "typename", "break", "else", "long", "sizeof", "union", "case", "enum",
	    "mutable", "static", "unsigned", "catch", "explicit", "namespace", "static_cast", "using", "char", "export", "new", "struct", "virtual", "class", "extern", "operator", "switch", "void", "const", "false", "private", "template", "volatile",
	    "const_cast", "float", "protected", "this", "wchar_t", "continue", "for", "public", "throw", "while", "default", "friend", "register", "true", "delete", "goto", "reinterpret_cast", "try", 
	    "ifdef", "ifndef", "endif", "include", "define"};

    @objid ("7b5de24b-2a77-11e2-9fb9-bc305ba4815c")
    private IToken roToken;

    @objid ("7b5de24c-2a77-11e2-9fb9-bc305ba4815c")
    private IToken rwToken;

    @objid ("7b5de24d-2a77-11e2-9fb9-bc305ba4815c")
    private IToken tagToken;

    @objid ("7b5de24e-2a77-11e2-9fb9-bc305ba4815c")
    private IPredicateRule[] rules = null;

    @objid ("7b5de256-2a77-11e2-9fb9-bc305ba4815c")
    private IToken keywordToken;

    @objid ("7b5de257-2a77-11e2-9fb9-bc305ba4815c")
    private IPredicateRule begin;

    @objid ("7b5de258-2a77-11e2-9fb9-bc305ba4815c")
    private IPredicateRule end;

    @objid ("7b5de259-2a77-11e2-9fb9-bc305ba4815c")
    private Token commentToken;

    @objid ("7b5de25a-2a77-11e2-9fb9-bc305ba4815c")
    public MDDPartitionScanner() {
        this.roToken = new Token(MDDPartitionTypes.RO_PARTITION);
        this.rwToken = new Token(MDDPartitionTypes.RW_PARTITION);
        this.tagToken = new Token(MDDPartitionTypes.TAG_PARTITION);
        this.keywordToken = new Token(MDDPartitionTypes.KEYWORD_PARTITION);
        this.commentToken = new Token(MDDPartitionTypes.COMMENT_PARTITION);
                
        Vector<IPredicateRule> therules = new Vector<>();
                
        KeywordRule keywordRule = new KeywordRule(this.roToken, this.rwToken, this.keywordToken, this);
                
        for (int i = 0; i < keywords.length; i++)
            keywordRule.addKeyword(keywords[i], this.keywordToken);
                
        this.begin = new MDDTagRule("//begin of modifiable zone", this.tagToken, this);
        this.end = new MDDTagRule("//end of modifiable zone", this.tagToken, this);
                
        IPredicateRule comment = new CommentRule("//", this.commentToken, this.roToken, this);
        IPredicateRule multilineComment = new MultilineCommentRule("/*", "*/", this.commentToken, this.roToken, this);
        therules.add(keywordRule);
                
        therules.add(this.begin); //$NON-NLS-1$//$NON-NLS-2$
        therules.add(this.end); //$NON-NLS-1$//$NON-NLS-2$
        therules.add(comment);
        therules.add(multilineComment);
        therules.add(new AnyTextRule(this.roToken, this.rwToken, this));
                
        this.rules = new IPredicateRule[therules.size()];
        for (int i = 0; i < therules.size(); i++)
            this.rules[i] = therules.get(i);
                
        setPredicateRules(this.rules);
                
        setDefaultReturnToken(this.roToken);
    }

    @objid ("7b5de25c-2a77-11e2-9fb9-bc305ba4815c")
    public void toggleRule(MDDTagRule rule) {
        if (rule == this.begin) {
            this.editableArea = true;
            setDefaultReturnToken(this.rwToken);
        }
        if (rule == this.end) {
            this.editableArea = false;
            setDefaultReturnToken(this.roToken);
        }
        // System.out.println("Toggle edit mode " + editableArea);
    }

    @objid ("7b5de25f-2a77-11e2-9fb9-bc305ba4815c")
    public IToken getCurrentDefaultToken() {
        return this.editableArea ? this.rwToken : this.roToken;
    }

}
