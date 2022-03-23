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
package org.modelio.uml.activitydiagram.editor.elements.sendsignal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmSendSignalAction when its representation mode is RepresentationMode.IMAGE
 */
@objid ("051b60a5-cb07-4f61-92e7-6d72883c2a45")
public class GmSendSignalUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("a47d5d48-9e34-405f-aa90-fd1620f598db")
    static final StyleKey REPMODE = GmSendSignalStructuredStyleKeys.REPMODE;

    @objid ("4827d774-a4a5-4249-8283-ba8b901e6ddc")
    static final StyleKey FONT = GmSendSignalStructuredStyleKeys.FONT;

    @objid ("462fde7a-61da-47bc-af3d-5fd1caa6ff69")
    static final StyleKey TEXTCOLOR = GmSendSignalStructuredStyleKeys.TEXTCOLOR;

    @objid ("591812d5-5cb0-4c8e-8d9b-44fb549514f8")
    static final StyleKey SHOWSTEREOTYPES = GmSendSignalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("b979121a-59de-4259-99ea-58092dd6a48a")
    static final StyleKey SHOWTAGS = GmSendSignalStructuredStyleKeys.SHOWTAGS;

    @objid ("105dfdd7-e302-4ca7-9ab8-0128f5abe6f3")
    static final StyleKey AUTOSHOWPINS = GmSendSignalStructuredStyleKeys.AUTOSHOWPINS;

}
