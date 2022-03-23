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
package org.modelio.uml.communicationdiagram.editor.elements.communicationnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.communicationdiagram.editor.style.CommunicationAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmCommunicationNode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("572d422d-cee4-4aaf-94fa-4d99d2cfb3ad")
public class GmCommunicationNodeUserImageStyleKeys extends CommunicationAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("4461cfc0-341d-482e-a260-f012bd356d13")
    public static final StyleKey REPMODE = createStyleKey("COMMUNICATIONNODE_REPMODE", MetaKey.REPMODE);

    /**
     * Text font.
     */
    @objid ("763ba6d5-2cc4-41cc-b245-ebb28ad99b4b")
    public static final StyleKey FONT = createStyleKey("COMMUNICATIONNODE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("15503183-6c0f-4312-a5f9-ab629c226278")
    public static final StyleKey TEXTCOLOR = createStyleKey("COMMUNICATIONNODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display stereotype.
     */
    @objid ("7bf630a0-09fa-4539-baad-dcc50879541f")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("COMMUNICATIONNODE_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("7cfebfbe-894d-4224-9fd2-9d50f88abbe9")
    public static final StyleKey SHOWTAGS = createStyleKey("COMMUNICATIONNODE_SHOWTAGS", MetaKey.SHOWTAGS);

}
