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

package org.modelio.diagram.elements.common.abstractdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Free zone (and diagram) layout assitant style keys.
 * @author cmarin
 * @sinve 3.4.1
 */
@objid ("c059a275-c97c-4c54-a6c4-15ebffadd46c")
public class LayoutAssistantStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Enable or disable the assistant.
     */
    @objid ("863d65b8-b14a-4a07-ac16-1edcd45a207b")
    public static final StyleKey ENABLED = createStyleKey("LAYOUTASSISTANT_ENABLED", Boolean.class);

    /**
     * The minimal distance to keep between nodes.
     * <p>
     * If negative, min distance is grid size plus the (negative) value.
     */
    @objid ("180232f4-4f99-4a9b-84a6-1a5054f3996d")
    public static final StyleKey MINDIST = createStyleKey("LAYOUTASSISTANT_MINDIST", Integer.class);

    /**
     * Keep same distance between nodes on resize.
     */
    @objid ("28ff451c-6adf-48b5-9189-79b8ce845751")
    public static final StyleKey KEEP_DIST_ON_RESIZE = createStyleKey("LAYOUTASSISTANT_KEEP_DIST_ON_RESIZE", Boolean.class);

    /**
     * If activated, avoid collisions with connection bend points too.
     */
    @objid ("f6a39ff7-df7b-4b40-9872-80505fee8f3c")
    public static final StyleKey AVOIDBENDDPOINTS = createStyleKey("LAYOUTASSISTANT_AVOIDBENDDPOINTS",
                                                           Boolean.class);

}
