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

package org.modelio.diagram.editor.activity.elements.partition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmPartition when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2b026649-55b6-11e2-877f-002564c97630")
public class GmPartitionStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Partition fill color. The header fill color is based on the same key but darker.
     */
    @objid ("d2674a4f-55c0-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("PARTITION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d268d0eb-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("PARTITION_FILLMODE", MetaKey.FILLMODE);

    @objid ("d268d0ed-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("PARTITION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d268d0ef-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("PARTITION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d268d0f1-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("PARTITION_FONT", MetaKey.FONT);

    @objid ("d268d0f3-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("PARTITION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d268d0f5-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("PARTITION_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d268d0f7-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("PARTITION_SHOWTAGS", MetaKey.SHOWTAGS);

}
