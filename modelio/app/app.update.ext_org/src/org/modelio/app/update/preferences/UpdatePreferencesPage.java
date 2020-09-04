/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.app.update.preferences;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.modelio.app.preferences.AppSharedPreferencesKeys;
import org.modelio.app.preferences.plugin.AppPreferences;
import org.modelio.app.update.plugin.AppUpdateExt;

@objid ("8e6ca370-b21a-4bc7-a63e-e87bac07b094")
public class UpdatePreferencesPage extends FieldEditorPreferencePage {
    @objid ("957f450f-11c2-4b86-a34a-e2a75f1ca44b")
    private StringFieldEditor updateSiteField;

    @objid ("ef069461-90d9-477c-b7e2-36f3a3bf387b")
    @Inject
    public UpdatePreferencesPage() {
        super(FieldEditorPreferencePage.GRID);
        init();
    }

    @objid ("b7cf49cc-7500-4cc5-ad9b-bd43e5862cd6")
    @Override
    public void createFieldEditors() {
        this.updateSiteField = new StringFieldEditor(AppSharedPreferencesKeys.UPDATESITE_PREFKEY, AppUpdateExt.I18N.getString("UpdatePrefPage.UpdateSite.label"), getFieldEditorParent());
        addField(this.updateSiteField);
    }

    @objid ("ea9495ef-af74-4e07-b255-b1170f628ccd")
    private void init() {
        setPreferenceStore(AppPreferences.getPreferences());
    }

    @objid ("572b2617-3c38-4def-8010-09cbd0c115a7")
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        super.propertyChange(event);
    }

    @objid ("7d88f96c-2ac9-4494-8938-3a54124dbd0a")
    @Override
    public boolean isValid() {
        return super.isValid();
    }

}
