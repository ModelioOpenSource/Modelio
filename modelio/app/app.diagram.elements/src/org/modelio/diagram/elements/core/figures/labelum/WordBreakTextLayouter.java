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

import java.text.BreakIterator;
import java.util.Locale;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Text layouter that breaks lines at character boundaries.
 * <p>
 * Can also append a symbol at the end of each lines.
 * 
 * @deprecated Use {@link NativeTextLayouter} that uses TextLayout capabilities
 * to break lines.
 */
@objid ("f25ff5e4-7be4-4b8e-9e84-cf1398750baa")
@Deprecated
public class WordBreakTextLayouter extends AbstractBreakTextLayouter {
    /**
     * The instance
     */
    @objid ("73f9926c-8f53-40dd-8285-c7fe3c9248c1")
    public static final WordBreakTextLayouter INSTANCE = new WordBreakTextLayouter();

    /**
     * Initialize the word breaking line layouter .
     */
    @objid ("3997ae94-0802-4e3a-942d-80f203668264")
    public  WordBreakTextLayouter() {
        super();
    }

    /**
     * Find a return the line break range.
     * The returned range will be replaced by a line return.
     * @param text the text to search
     * @param beforeIndex the offset to start back from
     * @return the text range to replace by a line break
     */
    @objid ("6390f665-2806-43ce-92a4-1f263b4b520e")
    @Override
    protected int findBreak(String text, int beforeIndex) {
        BreakIterator wb = BreakIterator.getLineInstance(Locale.getDefault());
        
        wb.setText(text);
        return wb.preceding(beforeIndex);
    }

}
