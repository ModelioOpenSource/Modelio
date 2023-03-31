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
package org.modelio.diagram.editor.preferences;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.platform.preferences.plugin.Preferences;

/**
 * Diagram preference page.
 */
@objid ("4cef4ff8-4401-49aa-b04c-19606afd0e89")
public class DiagramPreferencesPage extends FieldEditorPreferencePage {
    @objid ("0cfe8dc2-1eea-4b85-a098-aaa939406480")
    public static final String DELETE_MODE_PREFKEY = "DeleteMode";

    /**
     * Public constructor
     */
    @objid ("1a220966-8ed8-419f-84d2-d7ff8cda99bc")
    public  DiagramPreferencesPage() {
        super(GRID);
        init();
        
    }

    @objid ("14137be1-5eeb-4d12-a42a-f02e2193ec5f")
    @Override
    public void createFieldEditors() {
        //
        final String[][] logLevels = new String[][] {
                {DiagramEditor.I18N.getString("DiagramPreferences.DeleteMode.DELETE"), DeleteMode.DELETE.name()},
                {DiagramEditor.I18N.getString("DiagramPreferences.DeleteMode.MASK"), DeleteMode.MASK.name()}
            };
        
            addField(new RadioGroupFieldEditor(DELETE_MODE_PREFKEY,
                    DiagramEditor.I18N.getString("DiagramPreferences.DeleteMode.label"),
                    1, // nb columns
                    logLevels, getFieldEditorParent(),
                    true));
        
    }

    @objid ("96589756-d683-4ac5-9131-bc3585aba6d7")
    private void init() {
        setPreferenceStore(Preferences.getPreferences());
    }

    @objid ("58a8520d-120a-41a6-af99-cd3e230c2cf5")
    public enum DeleteMode {
        @objid ("cc3743d2-fb32-4abc-8b15-26153f314941")
        DELETE,
        @objid ("1dec516b-4fe3-483b-8c87-652b2e96a8c8")
        MASK;

    }

}
