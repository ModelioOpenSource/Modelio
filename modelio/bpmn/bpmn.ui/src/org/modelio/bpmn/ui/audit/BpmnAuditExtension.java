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

package org.modelio.bpmn.ui.audit;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;
import org.modelio.audit.engine.core.IAuditExecutionPlan;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.extension.IAuditExtension;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditCategoryBuilder;
import org.modelio.bpmn.ui.plugin.BpmnUi;
import org.osgi.framework.Bundle;

@objid ("3777f3b9-3b71-479a-a5f9-256c08377bab")
public class BpmnAuditExtension implements IAuditExtension {
    @objid ("a4e03006-15ed-4ff6-8cc4-1500824ae5c7")
    private BpmnConfigurationPlan bpmnConfigurationPlan;

    @objid ("76371386-056f-4526-9ed2-82bd127a6e2b")
    private BpmnAuditPlan bpmnAuditPlan;

    @objid ("1f723c2c-f8d0-4273-a41a-c7fabf8debe4")
    public BpmnAuditExtension() {
        List<AuditCategory> categories = loadCategories();
        
        this.bpmnConfigurationPlan = new BpmnConfigurationPlan(categories);
        this.bpmnAuditPlan = new BpmnAuditPlan(categories);
    }

    @objid ("d818ec91-93a4-4f86-9a9b-8e6985dac469")
    @Override
    public IAuditExecutionPlan getExecutionPlan() {
        return this.bpmnAuditPlan;
    }

    @objid ("76364aac-9c76-4d2f-a6cc-000958dc456c")
    @Override
    public IAuditConfigurationPlan getConfigurationPlan() {
        return this.bpmnConfigurationPlan;
    }

    @objid ("814fb282-63fa-46be-a16f-cf058c0ed28b")
    private List<AuditCategory> loadCategories() {
        List<AuditCategory> categories;
        
        Bundle bundle = BpmnUi.getContext().getBundle();
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/res/bpmnconfiguration.xml";
        URL url = null;
        try {
            url = new URL(s);
            URL fileURL = FileLocator.toFileURL(url);
            java.nio.file.Path xmlFile = Paths.get(URIUtil.toURI(fileURL));
        
            categories = AuditCategoryBuilder.parseCategories(xmlFile.toFile());
        } catch (Exception e) {
            BpmnUi.LOG.debug("File path %s is not found!", s);
            BpmnUi.LOG.error(e);
            categories = Collections.emptyList();
        }
        return categories;
    }

}
