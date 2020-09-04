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

package org.modelio.uml.ui.audit;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractAuditExecutionPlan;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.uml.ui.plugin.UmlUi;

@objid ("ac3a96b3-8a88-4575-a851-85bd5528ab38")
public class UmlAuditPlan extends AbstractAuditExecutionPlan {
    @objid ("6c507e0c-aa84-4c11-8a74-88855ead223f")
    public UmlAuditPlan(List<AuditCategory> rootCategories) {
        super(rootCategories);
    }

    @objid ("89f8273e-148f-4fde-a34e-adfb982afe0f")
    @Override
    protected void initRule(AuditRule rule) {
        try {
            Class<?> ruleClass = Class.forName(rule.getImplClass());
            if (AbstractUmlRule.class.isAssignableFrom(ruleClass)) {
                AbstractUmlRule umlRule = (AbstractUmlRule) ruleClass.newInstance();
                umlRule.setSeverity(rule.getSeverity());
                umlRule.autoRegister(this);
            }
        } catch (ClassNotFoundException e) {
            // Should be an old deleted rule.
            UmlUi.LOG.debug(e);
        } catch (InstantiationException e) {
            UmlUi.LOG.error(e);
        } catch (IllegalAccessException e) {
            UmlUi.LOG.error(e);
        }
    }

}
