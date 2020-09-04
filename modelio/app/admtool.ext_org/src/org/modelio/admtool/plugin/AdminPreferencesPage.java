/* 
 * Copyright 2013-2019 Modeliosoft - www.modeliosoft.com 
 * 
 * All information contained herein is, and remains the property of Modeliosoft.
 * The intellectual and technical concepts contained herein are proprietary 
 * to Modeliosoft and may be covered by French and Foreign Patents, patents
 * in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Modeliosoft.
 * 
 */

package org.modelio.admtool.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.modelio.app.preferences.AppSharedPreferencesKeys;
import org.modelio.app.preferences.plugin.AppPreferences;
import org.osgi.service.log.LogService;

/**
 * Log and administration mode preference page.
 */
@objid ("c00e39ab-e38e-4ee2-b853-a37adf3bf128")
public class AdminPreferencesPage extends FieldEditorPreferencePage {
    /**
     * Public constructor
     */
    @objid ("e7aac2fe-0e3d-456a-96af-6c31528b842c")
    public AdminPreferencesPage() {
        super(GRID);
        init();
    }

    @objid ("46cadc18-771c-45dc-87ab-8cdaf7f84794")
    @Override
    public void createFieldEditors() {
        //
        final String[][] logLevels = new String[][] { { AdmToolOrg.I18N.getString("LogLevel.ERROR"), Integer.toString(LogService.LOG_ERROR) },
            { AdmToolOrg.I18N.getString("LogLevel.WARNING"), Integer.toString(LogService.LOG_WARNING) },
            { AdmToolOrg.I18N.getString("LogLevel.INFO"), Integer.toString(LogService.LOG_INFO) },
            { AdmToolOrg.I18N.getString("LogLevel.DEBUG"), Integer.toString(LogService.LOG_DEBUG) }};
        
            RadioGroupFieldEditor logLevelFields = new RadioGroupFieldEditor(AppSharedPreferencesKeys.LOGLEVEL_PREFKEY, AdmToolOrg.I18N.getString("LogLevel.label"), 1, // nb
                    // columns
                    logLevels, getFieldEditorParent(), true);
            addField(logLevelFields);
        
            BooleanFieldEditor showAdmTools = new BooleanFieldEditor(AppSharedPreferencesKeys.SHOWADMTOOLS_PREFKEY, AdmToolOrg.I18N.getString("AdmTools.Show"), getFieldEditorParent());
            addField(showAdmTools);
    }

    @objid ("d49de3f5-60f5-4dbc-8013-8c9e5f4d9486")
    private void init() {
        setPreferenceStore(AppPreferences.getPreferences());
    }

}
