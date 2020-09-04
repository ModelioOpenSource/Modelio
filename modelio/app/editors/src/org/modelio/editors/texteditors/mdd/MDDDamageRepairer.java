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

package org.modelio.editors.texteditors.mdd;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.swt.custom.StyleRange;

@objid ("7b564124-2a77-11e2-9fb9-bc305ba4815c")
public class MDDDamageRepairer implements IPresentationDamager, IPresentationRepairer {
    /**
     * The document this object works on
     */
    @objid ("7b564125-2a77-11e2-9fb9-bc305ba4815c")
    protected IDocument fDocument;

    /**
     * The default text attribute if non is returned as data by the current token
     */
    @objid ("7b564127-2a77-11e2-9fb9-bc305ba4815c")
    protected TextAttribute fDefaultTextAttribute;

    /**
     * Constructor for NonRuleBasedDamagerRepairer.
     */
    @objid ("7b564129-2a77-11e2-9fb9-bc305ba4815c")
    public MDDDamageRepairer(TextAttribute defaultTextAttribute) {
        this.fDefaultTextAttribute = defaultTextAttribute;
    }

    /**
     * @see IPresentationRepairer#setDocument(IDocument)
     */
    @objid ("7b57c7b5-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void setDocument(IDocument document) {
        this.fDocument = document;
    }

    /**
     * Returns the end offset of the line that contains the specified offset or if the offset is inside a line delimiter, the end
     * offset of the next line.
     * 
     * @param offset the offset whose line end offset must be computed
     * @return the line end offset for the given offset
     * @exception BadLocationException
     * if offset is invalid in the current document
     */
    @objid ("7b57c7b9-2a77-11e2-9fb9-bc305ba4815c")
    protected int endOfLineOf(int offset) throws BadLocationException {
        IRegion info = this.fDocument.getLineInformationOfOffset(offset);
        if (offset <= info.getOffset() + info.getLength())
            return info.getOffset() + info.getLength();
        
        int line = this.fDocument.getLineOfOffset(offset);
        try {
            info = this.fDocument.getLineInformation(line + 1);
            return info.getOffset() + info.getLength();
        } catch (BadLocationException x) {
            return this.fDocument.getLength();
        }
    }

    /**
     * @see IPresentationDamager#getDamageRegion(ITypedRegion, DocumentEvent, boolean)
     */
    @objid ("7b57c7be-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent event, boolean documentPartitioningChanged) {
        if (!documentPartitioningChanged) {
            try {
        
                IRegion info = this.fDocument.getLineInformationOfOffset(event.getOffset());
                int start = Math.max(partition.getOffset(), info.getOffset());
        
                int end = event.getOffset() + (event.getText() == null ? event.getLength() : event.getText().length());
        
                if (info.getOffset() <= end && end <= info.getOffset() + info.getLength()) {
                    // optimize the case of the same line
                    end = info.getOffset() + info.getLength();
                } else
                    end = endOfLineOf(end);
        
                end = Math.min(partition.getOffset() + partition.getLength(), end);
                return new Region(start, end - start);
        
            } catch (BadLocationException x) {
                x.printStackTrace();
            }
        }
        return partition;
    }

    /**
     * @see IPresentationRepairer#createPresentation(TextPresentation, ITypedRegion)
     */
    @objid ("7b57c7c6-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void createPresentation(TextPresentation presentation, ITypedRegion region) {
        addRange(presentation, region.getOffset(), region.getLength(), this.fDefaultTextAttribute);
    }

    /**
     * Adds style information to the given text presentation.
     * 
     * @param presentation the text presentation to be extended
     * @param offset the offset of the range to be styled
     * @param length the length of the range to be styled
     * @param attr the attribute describing the style of the range to be styled
     */
    @objid ("7b57c7cb-2a77-11e2-9fb9-bc305ba4815c")
    protected void addRange(TextPresentation presentation, int offset, int length, TextAttribute attr) {
        if (attr != null)
            presentation.addStyleRange(new StyleRange(offset, length, attr.getForeground(), attr.getBackground(), attr.getStyle()));
    }

}
