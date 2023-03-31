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
package org.modelio.audit.preferences;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Control;
import org.modelio.audit.plugin.Audit;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.smkernel.meta.SmClass;

@objid ("1a111bf6-2717-4510-83d6-a394c49c091d")
public class AuditPreferencePage extends FieldEditorPreferencePage {
    @objid ("062baf62-f074-41de-983c-6ff9f5b0d54f")
    public static final String RUN_AT_STARTUP = "Audit.RunAtStartup";

    @objid ("8f5fe0d5-c4e2-4a58-ace5-850e83401c06")
    public static final int MAX_MODEL_SIZE = 50000;

    @objid ("bceb88db-134e-4812-a0b6-aa006b4a3740")
    private BooleanFieldEditor runAtStartup;

    @objid ("b905cf78-cb17-43fa-8771-294031cdf0a5")
    private IProjectService projectService;

    @objid ("2e06a5ed-deeb-4425-9e5b-f8b4987fd2dc")
    @Inject
    public  AuditPreferencePage(IProjectService projectService) {
        super(GRID);
        init(projectService);
        
    }

    @objid ("027658ce-64d5-4330-8c36-d284c3cc6488")
    private void init(IProjectService projectService) {
        this.setDescription(Audit.I18N.getMessage("Audit.Preference.Description",MAX_MODEL_SIZE));
        
        if ((projectService == null) || (projectService.getOpenedProject() == null)) {
        
            setPreferenceStore(null);
            if (isControlCreated()) {
                setVisible(false);
            }
            noDefaultAndApplyButton();
        } else {
            // use project store
            IPreferenceStore preferenceStore = projectService.getProjectPreferences(Audit.PLUGIN_ID);
            setPreferenceStore(preferenceStore);
            preferenceStore.setDefault(RUN_AT_STARTUP, false);
        }
        
        this.projectService = projectService;
        
    }

    @objid ("636e1e7b-dff4-48b7-bc68-f930f069c910")
    @Override
    protected void createFieldEditors() {
        this.runAtStartup = new BooleanFieldEditor(AuditPreferencePage.RUN_AT_STARTUP, Audit.I18N.getString("Audit.Preference.RunAtStartup"), getFieldEditorParent());
        addField(this.runAtStartup);
        
        // Check model size and disable option if model is bigger than MAX_MODEL_SIZE
        if(!checkModelSize()) {
            this.runAtStartup.setEnabled(false, getFieldEditorParent());
        
        }
        
    }

    /**
     * Check if number of model elements in project is below MAX_MODEL_SIZE
     * @return return false if number of model elements is upper MAX_MODEL_SIZE
     */
    @objid ("84ee5e77-d2e7-4808-87e6-5eec3475652b")
    public boolean checkModelSize() {
        long projectSize = 0;
        IGProject project =  this.projectService.getOpenedProject();
        if (project == null)
            return false;
        
        SmClass melement = project.getSession().getMetamodel().getMClass(ModelElement.class);
        for (IGModelFragment iProjectFragment : project.getParts(IGModelFragment.class)) {
            if (iProjectFragment.getType().equals(GProjectPartType.EXMLFRAGMENT) || iProjectFragment.getType().equals(GProjectPartType.SVNFRAGMENT)) {
                projectSize = projectSize + iProjectFragment.getRepository().findByClass(melement, true).size();
            }
            if (projectSize > AuditPreferencePage.MAX_MODEL_SIZE) {
                return false;
            }
        }
        return true;
    }

    @objid ("ac1e6011-4efc-4afa-870c-70adfa796658")
    @Override
    public boolean performOk() {
        final boolean ret = super.performOk();
        if (this.runAtStartup != null) {
            this.runAtStartup.store();
        }
        return ret;
    }

    @objid ("fe23406b-ec32-41cb-a9f7-f88d91b408ae")
    @Override
    protected void setControl(Control newControl) {
        super.setControl(newControl);
        if (getPreferenceStore() == null) {
            this.setVisible(false);
        }
        
    }

}
