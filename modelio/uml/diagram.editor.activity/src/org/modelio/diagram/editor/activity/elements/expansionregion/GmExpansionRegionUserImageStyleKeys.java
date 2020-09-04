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

package org.modelio.diagram.editor.activity.elements.expansionregion;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmStructuredactivity when its representation mode is RepresentationMode.IMAGE
 */
@objid ("245d4031-cfa5-4abb-a14e-5f54502a599e")
public class GmExpansionRegionUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d934992a-e26f-41c3-81ad-a79fdb04334f")
     static final StyleKey REPMODE = createStyleKey("EXPANSIONREGION_REPMODE", MetaKey.REPMODE);

    @objid ("d388b148-e83b-4b82-b394-42c6e1272437")
     static final StyleKey FONT = createStyleKey("EXPANSIONREGION_FONT", MetaKey.FONT);

    @objid ("0b8e68b1-a1e5-4be2-9f95-e0f41a9080f9")
     static final StyleKey TEXTCOLOR = createStyleKey("EXPANSIONREGION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("10cfd22c-b356-41bf-92da-aab6ddae8a06")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("EXPANSIONREGION_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("48f48dc0-5ee5-451f-921c-aa09875d152e")
     static final StyleKey SHOWTAGS = createStyleKey("EXPANSIONREGION_SHOWTAGS", MetaKey.SHOWTAGS);

}
