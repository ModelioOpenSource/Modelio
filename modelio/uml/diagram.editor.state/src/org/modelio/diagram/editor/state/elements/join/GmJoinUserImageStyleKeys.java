/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.state.elements.join;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmJoin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("a0b212f8-9bda-41c3-bce8-251ac95469ab")
public class GmJoinUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("47ab5732-c6b5-4c30-80b5-64dfd1fe4694")
    public static final StyleKey REPMODE = createStyleKey("JOIN_REPMODE", MetaKey.REPMODE);

    @objid ("f3cdd89d-0e2c-45f6-84a1-40c2d465221b")
    public static final StyleKey FONT = createStyleKey("JOIN_FONT", MetaKey.FONT);

    @objid ("30a73adf-55d6-4a96-ae74-8e8bdb70b0e4")
    public static final StyleKey TEXTCOLOR = createStyleKey("JOIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d0fb1f46-3e4a-436b-b1ac-7e371f006953")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("JOIN_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("afad7073-72f3-489e-9f9d-534705eee769")
    public static final StyleKey SHOWTAGS = createStyleKey("JOIN_SHOWTAGS", MetaKey.SHOWTAGS);

}
