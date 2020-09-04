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

package org.modelio.diagram.elements.core.figures.labelum;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Font;

/**
 * Text layouter that never break lines.
 * <p>
 * It just put an ellipsis if the text is too long.
 */
@objid ("949a99a0-a2b0-4392-ad6c-8aabc689469b")
public class NoBreakTextLayouter implements ILabelumTextLayouter {
    /**
     * An singleton instance usable for everybody.
     */
    @objid ("139251e8-c8ff-4f4c-889f-f8325888635e")
    public static final NoBreakTextLayouter INSTANCE = new NoBreakTextLayouter();

    @objid ("9d3b0fa6-9fff-4570-b791-73b74d99c782")
    protected String getTruncationString() {
        return "...";
    }

    @objid ("de0616ed-c54b-4548-b340-95e13bf4e99d")
    @Override
    public String formatText(LabelumFigure labelumFigure, String origText, Dimension maxSize) {
        final TextUtilities textUtilities = labelumFigure.getTextUtilities();
        final Font font = labelumFigure.getTextFont();
        
        int origWidth = textUtilities.getTextExtents(origText, font).width;
        if (origWidth <= maxSize.width())
            return origText;
        
        int truncationWidth = textUtilities.getTextExtents(getTruncationString(), font).width;
        
        if (maxSize.width < truncationWidth)
            maxSize.width = truncationWidth;
        
        StringBuilder sb = new StringBuilder();
        
        int largestLineLength = textUtilities.getLargestSubstringConfinedTo(
                origText, font, maxSize.width - truncationWidth);
        
        sb.append(origText, 0 , largestLineLength);
        
        if (largestLineLength < origText.length())
            sb.append(getTruncationString());
        return sb.toString();
    }

}
