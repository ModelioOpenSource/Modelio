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
package org.modelio.audit.infrastructure;

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
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditCategoryBuilder;
import org.osgi.framework.Bundle;

@objid ("1fcd5d87-364a-467b-a72e-39a51b2eaf82")
public class InfrastructureAuditExtension implements IAuditExtension {
    @objid ("d9b3f7a2-e41e-4023-b677-7aa73551f060")
    private InfrastructureConfigurationPlan infrastructureConfigurationPlan;

    @objid ("fe916bfb-43ab-4964-b45f-1a4371c17af6")
    private InfrastructureAuditPlan infrastructureAuditPlan;

    @objid ("250167ea-43b3-4283-814a-e24ba1d24fff")
    public  InfrastructureAuditExtension() {
        List<AuditCategory> categories = loadCategories();
        
        this.infrastructureConfigurationPlan = new InfrastructureConfigurationPlan(categories);
        this.infrastructureAuditPlan = new InfrastructureAuditPlan(categories);
        
    }

    @objid ("5614e92f-29a0-4baa-8bcf-ff1cb681ae7c")
    @Override
    public IAuditExecutionPlan getExecutionPlan() {
        return this.infrastructureAuditPlan;
    }

    @objid ("565b97ef-ed92-4614-b7c3-e00bcfaac0b5")
    @Override
    public IAuditConfigurationPlan getConfigurationPlan() {
        return this.infrastructureConfigurationPlan;
    }

    @objid ("5934aa38-8e32-4bd3-9a02-5e800e409d78")
    private List<AuditCategory> loadCategories() {
        List<AuditCategory> categories;
        
        Bundle bundle = Audit.getContext().getBundle();
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/res/infrastructureconfiguration.xml";
        URL url = null;
        try {
            url = new URL(s);
            URL fileURL = FileLocator.toFileURL(url);
            java.nio.file.Path xmlFile = Paths.get(URIUtil.toURI(fileURL));
        
            categories = AuditCategoryBuilder.parseCategories(xmlFile.toFile());
        } catch (Exception e) {
            Audit.LOG.debug("File path %s is not found!", s);
            Audit.LOG.error(e);
            categories = Collections.emptyList();
        }
        return categories;
    }

}
