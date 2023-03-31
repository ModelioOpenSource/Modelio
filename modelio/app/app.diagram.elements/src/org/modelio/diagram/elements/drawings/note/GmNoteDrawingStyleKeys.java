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
package org.modelio.diagram.elements.drawings.note;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmNoteDrawing}.
 */
@objid ("aa11d639-cdb4-4b5b-be4d-5868c491725f")
public class GmNoteDrawingStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Fill color
     */
    @objid ("e792d4f7-8471-4656-b34c-a4317dac37b8")
    public static final StyleKey FILLCOLOR = createStyleKey("DRAWNOTE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode
     */
    @objid ("f8e785d9-8ca6-4b31-ad2a-149a5ffa8348")
    public static final StyleKey FILLMODE = createStyleKey("DRAWNOTE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color
     */
    @objid ("62455f4d-12ca-4423-93a9-dae5b6f56ae8")
    public static final StyleKey LINECOLOR = createStyleKey("DRAWNOTE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("85a6a52c-70a5-4147-9c5d-bc8258a6e106")
    public static final StyleKey LINEWIDTH = createStyleKey("DRAWNOTE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line style
     */
    @objid ("5723050b-3637-446f-8da8-6e01eee54ba0")
    public static final StyleKey LINEPATTERN = createStyleKey("DRAWNOTE_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Fill mode
     */
    @objid ("bfdf01b2-e8b6-41d0-808f-2dc3d943ab41")
    public static final StyleKey FILLALPHA = createStyleKey("DRAWNOTE_FILLALPHA", MetaKey.FILLALPHA);

}
