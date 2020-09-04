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

import java.text.BreakIterator;
import java.util.Locale;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Text layouter that breaks lines at character boundaries.
 * <p>
 * Can also append a symbol at the end of each lines.
 */
@objid ("3a6bb0b1-a125-45ee-bb56-d675ff7510e6")
public class AnyCharBreakTextLayouter extends AbstractBreakTextLayouter {
    /**
     * The instance
     */
    @objid ("d1c4b35e-5e37-4829-9023-0db341cc9c0d")
    public static final AnyCharBreakTextLayouter INSTANCE = new AnyCharBreakTextLayouter();

    /**
     * Initialize the word breaking line layouter .
     */
    @objid ("252abd11-e95f-43b2-8f62-edaac97f2547")
    public AnyCharBreakTextLayouter() {
        super();
    }

    /**
     * Find a return the line break range.
     * The returned range will be replaced by a line return.
     * 
     * @param text the text to search
     * @param beforeIndex the offset to start back from
     * @return the text range to replace by a line break
     */
    @objid ("7e086761-62eb-4476-8957-bdf331747ea6")
    @Override
    protected int findBreak(String text, int beforeIndex) {
        BreakIterator wb = BreakIterator.getCharacterInstance(Locale.getDefault());
        
        wb.setText(text);
        return wb.preceding(beforeIndex);
    }

}
