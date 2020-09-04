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

package org.modelio.bpmn.ui.audit;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractAuditExecutionPlan;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.bpmn.ui.plugin.BpmnUi;

@objid ("58703556-600b-4cb3-9ebd-1eedf18909b9")
public class BpmnAuditPlan extends AbstractAuditExecutionPlan {
    @objid ("1fa9d783-86b9-483e-9e68-7019012c3b37")
    public BpmnAuditPlan(List<AuditCategory> rootCategories) {
        super(rootCategories);
    }

    @objid ("2b8c711f-b416-4e47-bdc3-45c3615b5292")
    @Override
    protected void initRule(AuditRule rule) {
        try {
            Class<?> ruleClass = Class.forName(rule.getImplClass());
            if (AbstractBpmnRule.class.isAssignableFrom(ruleClass)) {
                AbstractBpmnRule bpmnRule = (AbstractBpmnRule) ruleClass.newInstance();
                bpmnRule.setSeverity(rule.getSeverity());
                bpmnRule.autoRegister(this);
            }
        } catch (ClassNotFoundException e) {
            // Should be an old deleted rule.
            // BpmnUi.LOG.debug(e);
        } catch (InstantiationException e) {
            BpmnUi.LOG.error(e);
        } catch (IllegalAccessException e) {
            BpmnUi.LOG.error(e);
        }
    }

}
