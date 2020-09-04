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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Text layouter that breaks lines after given characters sequences.
 * <p>
 * Breaking can be specified at construction or with {@link #setBreakAfter(String[])}.
 * <p>
 * Inherits capabilities of {@link AbstractBreakTextLayouter}.
 */
@objid ("5731052e-8efb-4cce-bcb1-9d1046e1ce53")
public class CharSeqBreakTextLayouter extends AbstractBreakTextLayouter {
    /**
     * This character is intended for invisible word separation and for line break control;
     * it has no width, but its presence between two characters does not prevent increased letter spacing in justification.
     * <p>
     * It may be used as invisible line breaking character for {@link #setBreakAfter(String[])}
     * 
     * @see http://www.fileformat.info/info/unicode/char/200B/index.htm
     */
    @objid ("4fb1b9bc-40f2-41fe-8499-e2edef5d4360")
    public static final String ZERO_WIDTH_SPACE = "\u200B";

    /**
     * A regex Pattern used to find the <b>last</b> break.
     * <p>
     * This pattern is required to find the last pattern and to have a group
     * around the break sequence.
     */
    @objid ("d56f50e2-5c7d-4967-82b8-942c312c6e56")
    private Pattern findLastBreakPattern;

    /**
     * Find a return the line break range.
     * The returned range will be replaced by a line return.
     * 
     * @param text the text to search
     * @param beforeIndex the offset to start back from
     * @return the text range to replace by a line break
     */
    @objid ("cbd225d7-dfd4-47b5-aadb-d362c489c8b5")
    @Override
    protected int findBreak(String text, int beforeIndex) {
        Matcher m = this.findLastBreakPattern.matcher(text);
        m.region(0, beforeIndex);
        if (m.find()) {
            return m.end(1);
        } else {
            return -1;
        }
    }

    /**
     * Initialize the layouter to break after any of the given character sequences.
     * 
     * @param breakAfter the breaking character sequences.
     */
    @objid ("fbf6177f-7b2c-4e55-a453-bc3d055a1a2f")
    public CharSeqBreakTextLayouter(String[] breakAfter) {
        super();
        setBreakAfter(breakAfter);
    }

    @objid ("acd02f40-10a5-4df6-8049-94484d5f5731")
    public CharSeqBreakTextLayouter() {
        super();
    }

    /**
     * Configure the layouter to break after any of the given character sequences.
     * 
     * @param breakAfter the breaking character sequences.
     */
    @objid ("153e2e73-b926-4eda-a310-14a3b1c3f2a8")
    public void setBreakAfter(String... breakAfter) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(".*("); // this will match the longer string possible
        
        boolean first = true;
        for (String s : breakAfter) {
            if (first)
                first = false;
            else
                sb.append("|");
            sb.append(Pattern.quote(s));
        }
        
        sb.append(")");
        
        this.findLastBreakPattern = Pattern.compile(sb.toString());
    }

}
