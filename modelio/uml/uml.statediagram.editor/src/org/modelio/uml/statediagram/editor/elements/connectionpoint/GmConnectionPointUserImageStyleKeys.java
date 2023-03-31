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
package org.modelio.uml.statediagram.editor.elements.connectionpoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmInputPin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("d6b6596e-16af-4072-9c9a-cb29e854914d")
public class GmConnectionPointUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("2953a076-a087-4faf-9147-875c3feb3293")
    static final StyleKey REPMODE = createStyleKey("CONNECTIONPOINT_REPMODE", MetaKey.REPMODE);

    @objid ("4739bcdc-a667-4371-85f0-49b5ef50ea98")
    static final StyleKey FONT = createStyleKey("CONNECTIONPOINT_FONT", MetaKey.FONT);

    @objid ("6ece3461-8398-42bd-b1d6-cc447725aa47")
    static final StyleKey TEXTCOLOR = createStyleKey("CONNECTIONPOINT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("5b229de2-e932-4e71-8eaa-76f76b50b86b")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("CONNECTIONPOINT_SHOWSTEREOTYPES",
                                                               MetaKey.SHOWSTEREOTYPES);

    @objid ("168cf6a7-a22d-4803-a557-b5a9c397b8df")
    static final StyleKey SHOWTAGS = createStyleKey("CONNECTIONPOINT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("fd37a349-cb2f-4a9c-b2d0-655622127b88")
    static final StyleKey SHOWLABEL = createStyleKey("CONNECTIONPOINT_SHOWLABEL", MetaKey.SHOWLABEL);

}
