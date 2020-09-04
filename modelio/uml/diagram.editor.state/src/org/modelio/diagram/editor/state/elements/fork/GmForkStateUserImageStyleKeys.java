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

package org.modelio.diagram.editor.state.elements.fork;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmFork when its representation mode is RepresentationMode.IMAGE
 */
@objid ("4c62079a-16cb-4414-afa4-ae8a0310d7d5")
public class GmForkStateUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("04d4765e-3b51-4093-a2a4-b1f35d31e3de")
    public static final StyleKey REPMODE = createStyleKey("FORK_REPMODE", MetaKey.REPMODE);

    @objid ("febc139d-04ba-4feb-9a23-33905cd5756b")
    public static final StyleKey FONT = createStyleKey("FORK_FONT", MetaKey.FONT);

    @objid ("80752fbf-beea-4257-ab65-f13579da7e10")
    public static final StyleKey TEXTCOLOR = createStyleKey("FORK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("2d539a4b-0fbe-4f42-97a5-f8216e288e5a")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("FORK_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("a677ef95-f087-4b91-b0e4-4aca684cb9f6")
    public static final StyleKey SHOWTAGS = createStyleKey("FORK_SHOWTAGS", MetaKey.SHOWTAGS);

}
