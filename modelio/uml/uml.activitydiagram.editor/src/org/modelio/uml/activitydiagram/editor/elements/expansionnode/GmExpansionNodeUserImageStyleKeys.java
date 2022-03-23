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
package org.modelio.uml.activitydiagram.editor.elements.expansionnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmExpansionNode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("56447015-e074-40d2-94f5-eb23d6b94ce4")
public class GmExpansionNodeUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("07b02eb6-1761-462f-93a3-5adfb39fa6cf")
    static final StyleKey REPMODE = createStyleKey("EXPANSIONNODE_REPMODE", MetaKey.REPMODE);

    @objid ("699790e5-0b9b-4db6-a0bf-98c91921a082")
    static final StyleKey FONT = createStyleKey("EXPANSIONNODE_FONT", MetaKey.FONT);

    @objid ("ade44b50-7b2b-4549-ba9a-74760dd1cfcd")
    static final StyleKey TEXTCOLOR = createStyleKey("EXPANSIONNODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("42f1319b-d4c6-4732-ae41-5c18dc823821")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("EXPANSIONNODE_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("58d86fdb-f6e4-4932-b5c7-4f99f40cee2f")
    static final StyleKey SHOWTAGS = createStyleKey("EXPANSIONNODE_SHOWTAGS", MetaKey.SHOWTAGS);

}
