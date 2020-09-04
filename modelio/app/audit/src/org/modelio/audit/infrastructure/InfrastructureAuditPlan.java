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

package org.modelio.audit.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractAuditExecutionPlan;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;

@objid ("cd638ce7-6591-4afd-96ac-7ecb5d85b39e")
public class InfrastructureAuditPlan extends AbstractAuditExecutionPlan {
    @objid ("8f95f407-6c6a-4e1d-a42f-af742bdd5da0")
    public InfrastructureAuditPlan(List<AuditCategory> rootCategories) {
        super(rootCategories);
    }

    @objid ("b957fa22-8976-4a30-91c6-e443d9237843")
    @Override
    protected void initRule(AuditRule rule) {
        try {
            Class<?> ruleClass = Class.forName(rule.getImplClass());
            if (AbstractInfrastructureRule.class.isAssignableFrom(ruleClass)) {
                AbstractInfrastructureRule infraRule = (AbstractInfrastructureRule) ruleClass.newInstance();
                infraRule.setSeverity(rule.getSeverity());
                infraRule.autoRegister(this);
            }
        } catch (ClassNotFoundException e) {
            // Should be an old deleted rule.
            Audit.LOG.debug(e);
        } catch (InstantiationException e) {
            Audit.LOG.error(e);
        } catch (IllegalAccessException e) {
            Audit.LOG.error(e);
        }
    }

}
