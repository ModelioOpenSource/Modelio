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

package org.modelio.diagram.editor.activity.elements.centralbuffer.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCentralBuffer when its representation mode is RepresentationMode.IMAGE
 */
@objid ("bcbd2e82-ef71-42ca-9016-7e9022a8e065")
class GmCentralBufferUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("331276a9-af59-435c-8244-91357691c667")
    public static final StyleKey REPMODE = createStyleKey("CENTRALBUFFER_REPMODE", MetaKey.REPMODE);

    @objid ("d69522af-26fc-47e1-adcc-e6438a437935")
    public static final StyleKey FONT = createStyleKey("CENTRALBUFFER_FONT", MetaKey.FONT);

    @objid ("5c3a57bc-83fc-46cf-a3fa-28921b663038")
    public static final StyleKey TEXTCOLOR = createStyleKey("CENTRALBUFFER_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("79b03636-4c80-4592-a332-72f6247d423a")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("CENTRALBUFFER_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("2a6c66f1-c507-40f3-8b67-1c4bdf9b2723")
    public static final StyleKey SHOWTAGS = createStyleKey("CENTRALBUFFER_SHOWTAGS", MetaKey.SHOWTAGS);

}
