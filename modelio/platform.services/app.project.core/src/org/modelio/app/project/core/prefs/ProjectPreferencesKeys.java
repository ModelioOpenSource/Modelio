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

package org.modelio.app.project.core.prefs;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.core.plugin.AppProjectCore;

@objid ("dfdfc6bb-52a5-4617-abe2-905ba00aaa04")
public interface ProjectPreferencesKeys {
    @objid ("ba4ce917-4fa4-4010-bb8d-72d1e0ec763b")
    public static final String ATT_DEFAULT_TYPE_PREFKEY = "ProjectPrefs.AttDefType";

    @objid ("ddc65d37-c6db-4de3-a3d6-69d3f8cecee4")
    public static final String ATT_DEFAULT_VIS_PREFKEY = "ProjectPrefs.AttDefVis";

    @objid ("4e18e9b2-c636-4390-bb74-e95bf19fff47")
    public static final String PARAM_DEFAULT_TYPE_PREFKEY = "ProjectPrefs.ParamDefType";

    @objid ("c4f1c404-fff5-48e1-82e7-1084583c5893")
    public static final String RETURN_DEFAULT_TYPE_PREFKEY = "ProjectPrefs.ReturnDefType";

    @objid ("68d9e2db-073b-4cab-a9b2-8b1a71d72b54")
    public static final String NODE_ID = AppProjectCore.PLUGIN_ID;

    @objid ("57bbe87a-387f-41e6-a6f1-b2b429a48812")
    public static final String RICHNOTE_DEFAULT_TYPE_PREFKEY = "ProjectPrefs.RichNoteDefType";

    @objid ("2066995c-e002-4318-9836-f1e4e3a8d2db")
    public static final String NOTE_DEFAULT_MIMETYPE_PREFKEY = "ProjectPrefs.NoteDefMimeType";

    @objid ("520c0c8c-edf0-4179-bfb8-5a89b70580b8")
    public static final String DIAGRAM_DEFAULT_THEME_PREFKEY = "ProjectPrefs.DiagramDefTheme";

}
