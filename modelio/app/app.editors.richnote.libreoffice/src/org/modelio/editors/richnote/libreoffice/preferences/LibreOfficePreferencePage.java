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
package org.modelio.editors.richnote.libreoffice.preferences;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.lib.loader.InstallationFinder;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;

/**
 * LibreOffice preferences page.
 * <p>
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */
@objid ("ed0eb8ea-7c93-4a18-a294-ee431ac66553")
public class LibreOfficePreferencePage extends FieldEditorPreferencePage {
    @objid ("11418684-70b2-4f95-80f9-65e6406e48e4")
    private DirectoryFieldEditor installPathField;

    /**
     * Constructor.
     */
    @objid ("3045c142-9083-4026-b30f-370eee13654e")
    public  LibreOfficePreferencePage() {
        super(GRID);
        init();
        
    }

    /**
     * Creates the field editors.
     * <p>
     * Field editors are abstractions of
     * the common GUI blocks needed to manipulate various types
     * of preferences. Each field editor knows how to save and
     * restore itself.
     */
    @objid ("f935bbd6-6734-4ad5-9ebe-75b955f32733")
    @Override
    public void createFieldEditors() {
        this.installPathField = new OooDirFieldEditor(PreferenceConstants.P_OOOPATH,
                LibreOfficeEditors.I18N.getString("preferences.installpath"),
                getFieldEditorParent());
        
        addField(this.installPathField);
        
    }

    @objid ("da547746-6994-4c4d-a3f3-6d8525fd7b1c")
    private void init() {
        setPreferenceStore(LibreOfficeEditors.PREFERENCES);
        setDescription(LibreOfficeEditors.I18N.getString("preferences.page.description"));
        
    }

    @objid ("65dfa4d0-6c59-4f4c-9f05-c737e4607de5")
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        super.propertyChange(event);
        if (event.getProperty().equals(FieldEditor.VALUE)) {
            checkState();
        }
        
    }

    @objid ("4dba4bc1-c052-4f61-842a-a2d22f74aabf")
    private static class OooDirFieldEditor extends DirectoryFieldEditor {
        @objid ("a6f43255-9b42-4cb3-b0db-df6a098d1e5d")
        public  OooDirFieldEditor(final String name, final String labelText, final Composite parent) {
            super(name, labelText,parent);
        }

        /**
         * Hack the call to force validation on key stroke.
         */
        @objid ("f137efad-22c8-46bd-8721-85742c8aa841")
        @Override
        public void setValidateStrategy(final int value) {
            super.setValidateStrategy(VALIDATE_ON_KEY_STROKE);
        }

        @objid ("4cba65ad-505f-40ea-9031-9f4148dd279d")
        @Override
        public boolean doCheckState() {
            final String installDir = getStringValue();
            
            if (installDir==null || installDir.trim().isEmpty()) {
                return true;
            }
            
            boolean ret = InstallationFinder.isProgramPathValid(new File(installDir));
            if (! ret) {
                setErrorMessage(LibreOfficeEditors.I18N.getMessage("preferences.installPathNotValid", installDir));
            }
            return ret;
        }

    }

}
