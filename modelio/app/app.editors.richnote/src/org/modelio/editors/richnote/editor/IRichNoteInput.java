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
package org.modelio.editors.richnote.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rich note input interface.
 */
@objid ("400dda86-910d-4d2e-88e6-d8b2cf39c338")
public interface IRichNoteInput {
    /**
     * @return the reference of the edited element.
     */
    @objid ("8f1fbd8c-64b1-48b0-806c-6c3b630fcf58")
    MObject getEditedElement();

    /**
     * @return the core modeling session.
     */
    @objid ("9b7d98e4-1838-4cb6-ba59-508d5826634b")
    ICoreSession getSession();

    /**
     * @return the rich note file manager
     */
    @objid ("fbd5604b-1051-47a8-a997-fb811bcd21d3")
    IRichNoteFileRepository getFileManager();

}
