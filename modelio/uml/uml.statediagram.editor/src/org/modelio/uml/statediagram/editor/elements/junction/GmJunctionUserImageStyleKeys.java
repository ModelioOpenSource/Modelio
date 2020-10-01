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

package org.modelio.uml.statediagram.editor.elements.junction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmJunction when its representation mode is RepresentationMode.IMAGE
 */
@objid ("d83bc071-d376-4469-b79a-481b97f3bcf3")
public class GmJunctionUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("420aba89-5960-46da-a55b-44d275e885ca")
     static final StyleKey REPMODE = createStyleKey("JUNCTION_REPMODE", MetaKey.REPMODE);

    @objid ("0f237e99-9c74-41e7-8d75-435f044d4a32")
     static final StyleKey FONT = createStyleKey("JUNCTION_FONT", MetaKey.FONT);

    @objid ("e774914e-d389-464a-b128-3fc46a1e4033")
     static final StyleKey TEXTCOLOR = createStyleKey("JUNCTION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("bba6ef7c-b07b-4982-994b-8389ecbcad34")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("JUNCTION_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("398f259c-0f6b-42cd-89cd-0ecd347307f3")
     static final StyleKey SHOWTAGS = createStyleKey("JUNCTION_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("69f80f8e-337c-4750-a714-9df028e15528")
     static final StyleKey SHOWLABEL = createStyleKey("JUNCTION_SHOWLABEL", MetaKey.SHOWLABEL);

}
