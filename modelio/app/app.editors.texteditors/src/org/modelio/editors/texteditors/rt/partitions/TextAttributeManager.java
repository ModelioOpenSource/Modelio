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
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.modelio.platform.ui.UIColor;

@objid ("7b6646bc-2a77-11e2-9fb9-bc305ba4815c")
public class TextAttributeManager {
    @objid ("7b6646bd-2a77-11e2-9fb9-bc305ba4815c")
    private static TextAttributeManager instance = null;

    @objid ("7b6646be-2a77-11e2-9fb9-bc305ba4815c")
    private HashMap<String, TextAttribute> attributes = null;

    @objid ("7b666dcf-2a77-11e2-9fb9-bc305ba4815c")
    private TextAttribute defaultTextAttribute = null;

    @objid ("7b666dd0-2a77-11e2-9fb9-bc305ba4815c")
    public static TextAttributeManager getInstance() {
        if (instance == null)
            instance = new TextAttributeManager();
        return instance;
    }

    @objid ("7b666dd4-2a77-11e2-9fb9-bc305ba4815c")
    private  TextAttributeManager() {
        TextAttribute att = null;
                
        this.attributes = new HashMap<>();
        this.defaultTextAttribute = new TextAttribute(UIColor.EDITOR_RWTEXT_FG, null, 0);
                
        // Keywords
        att = new TextAttribute(UIColor.EDITOR_KEYWORD_FG, null, SWT.BOLD);
        this.attributes.put(RTPartitionTypes.KEYWORD_PARTITION, att);
                
        // MDD tags
        att = new TextAttribute(UIColor.EDITOR_MDDTAG_FG, null, SWT.ITALIC);
        this.attributes.put(RTPartitionTypes.OBJINGID_PARTITION, att);
                
        // Comments
        att = new TextAttribute(UIColor.EDITOR_COMMENT_FG, null, SWT.ITALIC);
        this.attributes.put(RTPartitionTypes.COMMENT_PARTITION, att);
        
    }

    @objid ("7b6694df-2a77-11e2-9fb9-bc305ba4815c")
    public TextAttribute getTextAttribute(String partitionType) {
        TextAttribute att = this.attributes.get(partitionType);
        return (att != null) ? att : this.defaultTextAttribute;
    }

}
