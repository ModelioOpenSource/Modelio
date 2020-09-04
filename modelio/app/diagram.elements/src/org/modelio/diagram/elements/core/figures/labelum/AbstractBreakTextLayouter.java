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

package org.modelio.diagram.elements.core.figures.labelum;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextLayout;

/**
 * Text layouter that breaks lines at boundaries defined by sub classes.
 * <p>
 * Can also append a symbol at the end of each lines.
 */
@objid ("ae9b459b-5c22-4800-9d3a-134addb80100")
public abstract class AbstractBreakTextLayouter implements ILabelumTextLayouter {
    /**
     * Example line wrapping symbol.
     */
    @objid ("bac0729d-1ce0-4305-84f3-81285fcd561f")
    public static final String CR_END_SYMB1 = "\uE684";

    /**
     * Example line wrapping symbol.
     */
    @objid ("af290c09-919a-4fa5-a016-57ff5ef5a3ec")
    public static final String CR_END_SYMB2 = "\u23CE";

    /**
     * If true and wrapping is needed, break the string at all locations given by {@link #findBreak(String, int)}.
     */
    @objid ("8e021d91-abc2-4c15-90f0-2f844fed99e1")
    private boolean breakAll;

    /**
     * Indentation applied to all lines except the first.
     */
    @objid ("ad5f9778-3ee5-4979-8e92-17e5844ec867")
    private String indent = "";

    /**
     * String to append at the end of each line.
     * See {@value #CR_END_SYMB1} or {@value #CR_END_SYMB2}.
     * @see #CR_END_SYMB1
     * @see #CR_END_SYMB2
     */
    @objid ("b6cdf0c7-ada7-4ea0-9321-f5c15e72e3ce")
    private String lineEndSymbol = "";

    /**
     * Whether to strip white spaces when breaking line.
     */
    @objid ("ec796aa7-7169-4b44-9989-2183a5c960f1")
    private boolean stripWhiteSpaces = true;

    /**
     * String that truncates the label if it does not fit the space.
     */
    @objid ("2fb47687-2538-4511-bd61-93fa6a4606cd")
    private String truncationString = "...";

    @objid ("29386484-656a-43e5-9c3e-b2c752f601dc")
    public AbstractBreakTextLayouter() {
        super();
    }

    @objid ("a141829e-2e2f-4f2b-a884-b0f4d477ae17")
    @Override
    public String formatText(LabelumFigure labelumFigure, String origText, Dimension maximumTextSize) {
        if (isBreakAll())
            return formatTextBreakAll(labelumFigure, origText, maximumTextSize);
        else
            return formatTextBreakOnNeed(labelumFigure, origText, maximumTextSize);
    }

    /**
     * Indentation is applied to all lines except the first.
     * 
     * @return the indentation.
     */
    @objid ("3e9d3ff4-526b-4016-8ea5-8a7c7c698b19")
    public String getIndent() {
        return this.indent;
    }

    /**
     * get the symbol that will be added as line wrapping symbol.
     * 
     * @return the line wrap symbol.
     */
    @objid ("6d03cb89-f697-47d6-ba84-8814182f4166")
    public String getLineEndSymbol() {
        return this.lineEndSymbol;
    }

    /**
     * If true and wrapping is needed, break the string at all locations given by {@link #findBreak(String, int)}.
     * 
     * @return whether the string is broken anywhere.
     */
    @objid ("e40e9089-8458-4c4f-b47d-49b4211536f2")
    public boolean isBreakAll() {
        return this.breakAll;
    }

    /**
     * If true and wrapping is needed, break the string at all locations given by {@link #findBreak(String, int)}.
     * 
     * @param breakAll true to break the string everywhere.
     */
    @objid ("9faebf9a-ee09-40e3-8fbc-1f17573059e0")
    public void setBreakAll(boolean breakAll) {
        this.breakAll = breakAll;
    }

    /**
     * Indentation is applied to all lines except the first.
     * 
     * @param indent the indentation
     */
    @objid ("35148099-2afa-4927-8f8f-93b386017e6d")
    public void setIndent(String indent) {
        this.indent = indent;
    }

    /**
     * Set the line ending symbol.
     * See {@value #CR_END_SYMB1} or {@value #CR_END_SYMB2}.
     * 
     * @param lineEndSymbol the symbol to add at end of broken lines.
     */
    @objid ("9e222342-b22b-4225-b886-1553bbb7b63b")
    public void setLineEndSymbol(String lineEndSymbol) {
        this.lineEndSymbol = lineEndSymbol;
    }

    /**
     * Set whether to strip white spaces when breaking line.
     * 
     * @param stripWhiteSpaces Whether to strip white spaces when breaking line.
     */
    @objid ("6d8496be-4026-4da2-b119-bb9a4a4b164e")
    public void setStripWhiteSpaces(boolean stripWhiteSpaces) {
        this.stripWhiteSpaces = stripWhiteSpaces;
    }

    /**
     * @param truncationString the string that indicates a line or label is truncated.
     */
    @objid ("89edcb01-9fc5-4b71-bfa1-ca6d5a881106")
    public void setTruncationString(String truncationString) {
        this.truncationString = truncationString;
    }

    /**
     * Break the string at every locations given by {@link #findBreak(String, int)}.
     * 
     * @param origText the original string
     * @return all lines.
     */
    @objid ("260eeab5-3380-4da1-90cc-5ec13554fcf4")
    protected List<String> breakAll(String origText) {
        List<String> lines = new ArrayList<>();
        
        int i = origText.length();
        while (i > 0) {
            int prevI = findBreak(origText, i);
            if (prevI> 0) {
                String line = origText.substring(prevI, i);
                lines.add(0, line);
            } else {
                String line = origText.substring(0, i);
                lines.add(0, line);
            }
            i = prevI - 1;
        }
        return lines;
    }

    /**
     * Find a return the line break range.
     * The returned range will be replaced by a line return.
     * 
     * @param text the text to search
     * @param beforeIndex the offset to start back from
     * @return the index where line must be broken
     */
    @objid ("2aa6b148-8a89-4117-8d2a-305601c0e473")
    protected abstract int findBreak(String text, int beforeIndex);

    @objid ("edc4cb19-de52-42a4-8d53-fe866613f9e5")
    protected String formatTextBreakAll(LabelumFigure labelumFigure, String origText, Dimension maximumTextSize) {
        final TextUtilities textUtilities = labelumFigure.getTextUtilities();
        
        final Font font = labelumFigure.getTextFont();
        int truncationWidth = textUtilities.getTextExtents(
                getTruncationString(), font).width;
        
        // Normalize maximum text size
        Dimension maxSize = maximumTextSize.getCopy();
        if (maxSize.width == -1)
            maxSize.width = Integer.MAX_VALUE;
        else if (maxSize.width < truncationWidth)
            maxSize.width = truncationWidth;
        
        if (maxSize.height == -1)
            maxSize.height = Integer.MAX_VALUE;
        
        String lineBreakSymbol = getLineEndSymbol();
        int lineBreakWidth = textUtilities.getTextExtents(
                lineBreakSymbol, font).width;
        
        // Test if wrapping is needed
        TextLayout drawer = labelumFigure.getTextDrawer(origText, -1);
        int neededWidth = drawer.getBounds().width;
        if (neededWidth <= maxSize.width)
            return origText;
        
        // Here, wrapping is needed. Break the string everywhere specified by sub class.
        List<String> lines = breakAll(origText);
        
        int remainingHeight = maxSize.height;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < lines.size(); i++) {
            String line = lines.get(i);
        
            if (stripWhiteSpaces())
                line = line.trim();
            if (i > 0)
                line = getIndent() + line;
        
            Dimension lineBounds = textUtilities.getTextExtents(line, font);
            //Rectangle lineBounds = drawer.getLineBounds(i);
            //int nextLineHeight = (i+1 < indentedLines.length) ? drawer.getLineBounds(i+1).height : 0;
        
            if (remainingHeight < lineBounds.height  ) {
                // no vertical space left :
                // put truncation string at the end of last displayed line
                final int lastLineStart = sb.lastIndexOf("\n", sb.length() - 2)+1;
                String lastLine = sb.substring(lastLineStart);
                int len = textUtilities.getLargestSubstringConfinedTo(
                        lastLine, font, maxSize.width - truncationWidth);
                sb.replace(lastLineStart + len, sb.length(), getTruncationString());
        
                return sb.toString();
            } else if (lineBounds.width <= maxSize.width - lineBreakWidth) {
                // line fits horizontally
                if (i>0) {
                    sb.append(lineBreakSymbol);
                    sb.append("\n");
                }
                sb.append(line);
            } else {
                // line does not fit horizontally
                if (i>0) {
                    sb.append(lineBreakSymbol);
                    sb.append("\n");
                }
        
                // truncate this line
                int largestLineLength = textUtilities.getLargestSubstringConfinedTo(
                        line, font, maxSize.width - truncationWidth);
                sb.append(line, 0, largestLineLength);
                sb.append(getTruncationString());
            }
        
            remainingHeight -= lineBounds.height;
        }
        return sb.toString().trim();
    }

    @objid ("76e288ab-762e-4b99-a1e9-ee54966b26fd")
    protected String formatTextBreakOnNeed(LabelumFigure labelumFigure, String origText, Dimension maximumTextSize) {
        final TextUtilities textUtilities = labelumFigure.getTextUtilities();
        
        final Font font = labelumFigure.getTextFont();
        int truncationWidth = textUtilities.getTextExtents(
                getTruncationString(), font).width;
        
        // Normalize maximum text size
        Dimension maxSize = maximumTextSize.getCopy();
        if (maxSize.width == -1)
            maxSize.width = Integer.MAX_VALUE;
        else if (maxSize.width < truncationWidth)
            maxSize.width = truncationWidth;
        
        if (maxSize.height == -1)
            maxSize.height = Integer.MAX_VALUE;
        
        String remainingText = origText;
        
        String lineBreakSymbol = getLineEndSymbol();
        int lineBreakWidth = textUtilities.getTextExtents(
                lineBreakSymbol, font).width;
        
        StringBuilder sb = new StringBuilder();
        int remainingHeight = maxSize.height;
        boolean finished = false;
        do {
            // 1) get largest string
            int largestLineLength = textUtilities.getLargestSubstringConfinedTo(
                    remainingText, font, maxSize.width - lineBreakWidth);
        
            if (largestLineLength < remainingText.length()) {
                // Remaining text does not fit,
                // 2) find last location where line can be break
                int breakIdx = findBreak(remainingText, largestLineLength);
                if (breakIdx <= 0)
                    breakIdx = largestLineLength -1;
        
                // Compute line and its dimensions
                String textLine = remainingText.substring(0, breakIdx +1 );
                final int lineHeight = textUtilities.getTextExtents(textLine, font).height;
        
                if (remainingHeight < lineHeight && sb.length() > 0) {
                    // no vertical space left :
                    // put truncation string at the end of last displayed line
                    final int lastLineStart = sb.lastIndexOf("\n", sb.length() - 2)+1;
                    String lastLine = sb.substring(lastLineStart);
                    int len = textUtilities.getLargestSubstringConfinedTo(
                            lastLine, font, maxSize.width - truncationWidth);
                    sb.replace(lastLineStart + len, sb.length(), getTruncationString());
        
                    finished = true;
                } else {
                    // vertical space left,
                    // 3) break line and loop until nothing more
                    if (stripWhiteSpaces())
                        textLine = textLine.trim();
        
                    if (sb.length() > 0)
                        sb.append(getIndent());
        
                    sb.append(textLine);
                    sb.append(lineBreakSymbol);
                    sb.append("\n");
                    remainingText = remainingText.substring(breakIdx);
        
                    if (stripWhiteSpaces())
                        remainingText = remainingText.trim();
        
                    remainingHeight -= lineHeight;
                }
            } else {
                // Remaining text fits remaining space, finished
                finished = true;
            }
        } while(! finished) ;
        return sb.toString();
    }

    @objid ("a9d2d717-f28c-4a74-84e2-f8d94b271c5f")
    protected String getTruncationString() {
        return this.truncationString;
    }

    /**
     * If true, white spaces are stripped when breaking lines.
     * 
     * @return whether to strip white spaces on line break.
     */
    @objid ("25577076-d65b-42e7-bd00-745f5ab8cf6a")
    protected boolean stripWhiteSpaces() {
        return this.stripWhiteSpaces;
    }

}
