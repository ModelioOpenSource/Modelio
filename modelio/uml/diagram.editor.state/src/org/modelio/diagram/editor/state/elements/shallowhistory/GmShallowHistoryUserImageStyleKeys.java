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

package org.modelio.diagram.editor.state.elements.shallowhistory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmShallowHistory when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("3a2e01e3-5683-46dc-b05e-1b9027b00621")
public class GmShallowHistoryUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("48eb2f5a-1e28-481a-ad95-4e91ad13e20a")
    public static final StyleKey REPMODE = createStyleKey("SHALLOWHISTORY_REPMODE", MetaKey.REPMODE);

    @objid ("d29bea71-f9fd-45bd-ae59-4180284231a6")
    public static final StyleKey FONT = createStyleKey("SHALLOWHISTORY_FONT", MetaKey.FONT);

    @objid ("b0cc6512-62c4-44a3-a4e3-9d4e57d5054c")
    public static final StyleKey TEXTCOLOR = createStyleKey("SHALLOWHISTORY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("7bebbf53-4985-48e9-9946-44332702d2e1")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("SHALLOWHISTORY_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("02c554ce-f9e4-4962-b315-a54b68991320")
    public static final StyleKey SHOWTAGS = createStyleKey("SHALLOWHISTORY_SHOWTAGS", MetaKey.SHOWTAGS);

}
