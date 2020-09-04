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

package org.modelio.diagram.editor.statik.elements.signal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmSendSignalAction when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("a3ec82a2-751f-4106-8ded-34bccfde543b")
public class GmSignalUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("6a43458c-a53f-4fdb-8b5c-12b86aa5a38a")
     static final StyleKey REPMODE = GmSignalStructuredStyleKeys.REPMODE;

    @objid ("55d56366-fe5d-4d95-b371-b8cdfa8eed18")
     static final StyleKey FONT = GmSignalStructuredStyleKeys.FONT;

    @objid ("5bc4ecc5-dd38-4f2b-9c97-52a141a41b14")
     static final StyleKey TEXTCOLOR = GmSignalStructuredStyleKeys.TEXTCOLOR;

    @objid ("ba4f7f2d-0918-4856-8c6b-0eaae863ef98")
     static final StyleKey SHOWNAME = GmSignalStructuredStyleKeys.SHOWNAME;

    @objid ("f4ae2d32-ff4a-43f1-a169-3d88383e7bb1")
     static final StyleKey SHOWSTEREOTYPES = GmSignalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("86cc9142-e9e4-4325-8c6c-a676bc208ee3")
     static final StyleKey SHOWTAGS = GmSignalStructuredStyleKeys.SHOWTAGS;

    @objid ("7d059bb5-90d4-4de7-9965-17725d2e9f74")
     static final StyleKey SHOWVISIBILITY = GmSignalStructuredStyleKeys.SHOWVISIBILITY;

}
