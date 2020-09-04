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

package org.modelio.uml.ui.audit;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.uml.ui.plugin.UmlUi;

@objid ("d43c27a2-9ad2-4882-ad3c-259940feabed")
public class UmlConfigurationPlan implements IAuditConfigurationPlan {
    @objid ("71dcc19f-7e3b-4fc3-a343-d610d21f8fd5")
    private List<AuditCategory> rootCategories;

    @objid ("fce7a07b-c9fe-4e34-902c-9855ad6e679e")
    public UmlConfigurationPlan(List<AuditCategory> rootCategories) {
        this.rootCategories = rootCategories;
    }

    @objid ("bc0592f0-05d0-458d-bc48-6423fd16f7b4")
    @Override
    public List<AuditCategory> getRootCategories() {
        return this.rootCategories;
    }

    @objid ("e497a843-6cf9-40ce-a8c3-789e2c15713a")
    @Override
    public String getLabel(AuditCategory category) {
        return UmlUi.I18N.getString(IAuditConfigurationPlan.CATEGORY_PREFIX + category.getId() + ".label");
    }

    @objid ("5960a37a-4a45-40a8-a16a-b5cd7c91f0e1")
    @Override
    public String getMessage(String ruleId) {
        return UmlUi.I18N.getString(IAuditConfigurationPlan.RULE_PREFIX + ruleId + ".message");
    }

    @objid ("ae1b684b-8be0-4fc1-8151-0b63c741772d")
    @Override
    public String getDescription(AuditRule rule) {
        return UmlUi.I18N.getString(IAuditConfigurationPlan.RULE_PREFIX + rule.getId() + ".description");
    }

    @objid ("beb26e40-e8c5-45b5-8ea7-d3e6991bc063")
    @Override
    public String getLabel(AuditRule element) {
        return element.getId();
    }

    @objid ("62891743-e582-4955-881e-37696f1f7b8d")
    @Override
    public Image getImage(AuditCategory category) {
        switch (category.getId()) {
        case "Activity":
            return MetamodelImageService.getIcon(Activity.MQNAME);
        case "Behaviour":
            return MetamodelImageService.getIcon(Behavior.MQNAME);
        case "Sequence":
            return MetamodelImageService.getIcon(Interaction.MQNAME);
        case "State":
            return MetamodelImageService.getIcon(StateMachine.MQNAME);
        case "Static":
            return MetamodelImageService.getIcon(Class.MQNAME);
        case "UseCase":
            return MetamodelImageService.getIcon(UseCase.MQNAME);
        case "Others":
            // No icon yet...
        default:
            break;
        
        }
        return null;
    }

}
