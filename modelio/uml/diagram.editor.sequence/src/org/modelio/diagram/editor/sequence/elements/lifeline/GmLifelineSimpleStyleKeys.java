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

package org.modelio.diagram.editor.sequence.elements.lifeline;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.style.SequenceAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * StyleKey provider for the GmLifeline class.
 */
@objid ("d93f0b5e-55b6-11e2-877f-002564c97630")
public class GmLifelineSimpleStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("50426ac9-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("LIFELINE_REPMODE", MetaKey.REPMODE);

    @objid ("50426acb-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("LIFELINE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("50426acd-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("LIFELINE_FILLMODE", MetaKey.FILLMODE);

    @objid ("50426acf-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("LIFELINE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("50426ad1-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("LIFELINE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("50426ad3-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("LIFELINE_FONT", MetaKey.FONT);

    @objid ("50426ad5-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("LIFELINE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("50426ad7-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("LIFELINE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("50426ad9-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("LIFELINE_SHOWTAGS", MetaKey.SHOWTAGS);

}
