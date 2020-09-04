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
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.modelio.ui.UIColor;

@objid ("7b60ef9a-2a77-11e2-9fb9-bc305ba4815c")
public class TextAttributeManager {
    @objid ("7b60ef9b-2a77-11e2-9fb9-bc305ba4815c")
    private static TextAttributeManager instance = null;

    @objid ("7b60ef9c-2a77-11e2-9fb9-bc305ba4815c")
    private HashMap<String, TextAttribute> attributes = null;

    @objid ("7b60efa0-2a77-11e2-9fb9-bc305ba4815c")
    private TextAttribute defaultTextAttribute = null;

    @objid ("7b60efa1-2a77-11e2-9fb9-bc305ba4815c")
    public static TextAttributeManager getInstance() {
        if (instance == null)
            instance = new TextAttributeManager();
        return instance;
    }

    @objid ("7b60efa5-2a77-11e2-9fb9-bc305ba4815c")
    private TextAttributeManager() {
        TextAttribute  att = null;
        
        this.attributes = new HashMap<>();
        this.defaultTextAttribute = new TextAttribute(UIColor.BLACK);
        
        // Read-only text
        att   = new TextAttribute(UIColor.EDITOR_ROTEXT_FG, null, 0);
        this.attributes.put(MDDPartitionTypes.RO_PARTITION, att);
                
        // Keywords
        att   = new TextAttribute(UIColor.EDITOR_KEYWORD_FG, null, SWT.BOLD);
        this.attributes.put(MDDPartitionTypes.KEYWORD_PARTITION, att);
                
        // MDD tags
        att   = new TextAttribute(UIColor.EDITOR_MDDTAG_FG, null, SWT.ITALIC);
        this.attributes.put(MDDPartitionTypes.TAG_PARTITION, att);
                
        // Read-write text
        att   = new TextAttribute(UIColor.EDITOR_RWTEXT_FG, null, 0);
        this.attributes.put(MDDPartitionTypes.RW_PARTITION, att);
                
        // Comments
        att   = new TextAttribute(UIColor.EDITOR_COMMENT_FG, null, SWT.ITALIC);
        this.attributes.put(MDDPartitionTypes.COMMENT_PARTITION, att);
    }

    @objid ("7b60efa7-2a77-11e2-9fb9-bc305ba4815c")
    public TextAttribute getTextAttribute(String partitionType) {
        TextAttribute att = this.attributes.get(partitionType);
        return (att != null)? att:this.defaultTextAttribute;
    }

}
