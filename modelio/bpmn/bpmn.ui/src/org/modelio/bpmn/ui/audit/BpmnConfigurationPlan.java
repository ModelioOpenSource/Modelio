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
package org.modelio.bpmn.ui.audit;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.bpmn.ui.plugin.BpmnUi;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;

@objid ("06f4c9fa-48cc-4ae5-a6fc-4ed768932a3f")
public class BpmnConfigurationPlan implements IAuditConfigurationPlan {
    @objid ("592a3ba2-6da0-410c-86d3-e50f709529fb")
    private List<AuditCategory> rootCategories;

    @objid ("3baea865-4957-44f5-8836-3587a6ba95bb")
    public  BpmnConfigurationPlan(List<AuditCategory> rootCategories) {
        super();
        this.rootCategories = rootCategories;
        
    }

    @objid ("010d23af-c891-4c13-9797-6ca6db362fc0")
    @Override
    public List<AuditCategory> getRootCategories() {
        return this.rootCategories;
    }

    @objid ("178372a3-cd61-431a-be3d-bb17b8486cf5")
    @Override
    public String getLabel(AuditCategory category) {
        return BpmnUi.I18N.getString(IAuditConfigurationPlan.CATEGORY_PREFIX + category.getId() + ".label");
    }

    @objid ("2a0d5727-aaba-47de-8fb2-eda8e5c86b4e")
    @Override
    public String getMessage(String ruleId) {
        return BpmnUi.I18N.getString(IAuditConfigurationPlan.RULE_PREFIX + ruleId + ".message");
    }

    @objid ("147ba3cc-90ec-4112-85cc-139257f10d76")
    @Override
    public String getDescription(AuditRule rule) {
        return BpmnUi.I18N.getString(IAuditConfigurationPlan.RULE_PREFIX + rule.getId() + ".description");
    }

    @objid ("38377ba8-563b-4767-b4fd-53fafff5b405")
    @Override
    public String getLabel(AuditRule element) {
        return element.getId();
    }

    @objid ("e617eec1-59ed-4774-8ec8-47584f0c434c")
    @Override
    public Image getImage(AuditCategory category) {
        return MetamodelImageService.getIcon(BpmnProcess.MQNAME);
    }

}
