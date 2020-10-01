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

package org.modelio.diagram.elements.core.figures.labelum;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;

/**
 * Text layouter that breaks lines at word boundaries if possible, else at any character.
 * <p>
 * Can also append a symbol at the end of each lines.
 * <p>
 * This implementation uses native wrapping capabilities of {@link TextLayout}
 */
@objid ("1a999e83-12b3-473b-8e7e-450327bd8cff")
public class NativeTextLayouter implements ILabelumTextLayouter {
    /**
     *  : &#59012 : \uE684
     */
    @objid ("ed8ec2dd-c07e-42a1-b97e-f4c762b1321a")
    public static final String CR_END_SYMB1 = "\uE684";

    /**
     * ⏎ : &#9166; : \u23CE
     */
    @objid ("876396b7-befa-4d77-aba4-b82050dc57b7")
    public static final String CR_END_SYMB2 = "\u23CE";

    @objid ("1847b76e-c6a9-42c5-bc60-e66c37384bf7")
    private String lineEndSymbol;

    /**
     * The instance
     */
    @objid ("f2ec18f4-4ff4-45ce-a16b-cc5c34ab3430")
    public static final NativeTextLayouter INSTANCE = new NativeTextLayouter();

    @objid ("bcb53c09-fdff-459f-ac5e-8922462171e1")
    public NativeTextLayouter() {
        this("");
    }

    /**
     * Initialize the word breaking line layouter .
     * 
     * @param lineEndSymbol the symbol to add at end of broken lines.
     * See {@value #CR_END_SYMB1} or {@value #CR_END_SYMB2}.
     */
    @objid ("17204efb-fecc-416d-909c-2d636b6876cc")
    public NativeTextLayouter(String lineEndSymbol) {
        this.lineEndSymbol = lineEndSymbol;
    }

    @objid ("dd533569-faf0-44ff-957b-d2b416e75a33")
    @Override
    public String formatText(LabelumFigure labelumFigure, String origText, Dimension maxSize) {
        final TextUtilities textUtilities = labelumFigure.getTextUtilities();
        
        String lineBreakSymbol = getLineEndSymbol();
        int lineBreakWidth = textUtilities.getTextExtents(
                lineBreakSymbol, labelumFigure.getFont()).width;
        
        String truncSymbol = getTruncationString();
        int truncationWidth = textUtilities.getTextExtents(
                truncSymbol, labelumFigure.getFont()).width;
        
        
        int allowedWidth;
        if (maxSize.width > 0) {
            allowedWidth = Math.max(maxSize.width - lineBreakWidth, truncationWidth);
        } else {
            allowedWidth = -1;
        }
        
        final TextLayout textLayout = labelumFigure.getTextDrawer( origText, allowedWidth);
        StringBuilder sb = new StringBuilder();
        
        
        Rectangle lineBounds;
        int[] offsets = textLayout.getLineOffsets();
        for (int i=0; i< offsets.length-1; i++) {
            final boolean isNextLine = i < offsets.length-2;
        
            lineBounds = textLayout.getLineBounds(i);
        
            if (sb.length() > 0 
                    && (isNextLine || lineBounds.width > lineBreakWidth)
                    && sb.charAt(sb.length()-1) != '\n') {
                // Append line break unless it is the last line and last line size is
                // thinner than line break symbol
                sb.append(lineBreakSymbol);
                sb.append("\n");
            }
        
            sb.append(origText, offsets[i], offsets[i+1]);
        
            if ( isNextLine) {
                // There is a next line
                Rectangle nextLineBounds = textLayout.getLineBounds(i+1) ;
                int lineBottom = nextLineBounds.y + nextLineBounds.height;
                if (maxSize.height > -1 && lineBottom > maxSize.height) {
                    // no vertical space left :
                    // put truncation string at the end of last displayed line and stop
        
                    String lastLine = origText.substring(offsets[i], offsets[i+1]);
                    int len = textUtilities.getLargestSubstringConfinedTo(
                            lastLine, labelumFigure.getFont(), maxSize.width - truncationWidth);
                    sb.replace(offsets[i] + len, sb.length(), truncSymbol);
        
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }

    /**
     * get the symbol that will be added as line wrapping symbol.
     * 
     * @return the line wrap symbol.
     */
    @objid ("eea4b592-fa1d-465d-8811-0c421df16013")
    public String getLineEndSymbol() {
        return this.lineEndSymbol;
    }

    @objid ("4876ba90-90d2-452b-9877-5633dc7d7671")
    private String getTruncationString() {
        return "...";
    }

}
