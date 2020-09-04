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
import org.modelio.uml.ui.plugin.UmlUi;
import org.osgi.framework.Bundle;

@objid ("54f144a8-1e1b-471d-a907-98b7e148dede")
public class UmlAuditExtension implements IAuditExtension {
    @objid ("f1be89d6-0706-480b-b7bb-f1b5879a3b13")
    private UmlConfigurationPlan umlConfigurationPlan;

    @objid ("9219e5b9-4f3c-4f76-8296-931529d85723")
    private UmlAuditPlan umlAuditPlan;

    @objid ("43eb0ee4-cf90-4a65-a3b1-24ad23492b22")
    public UmlAuditExtension() {
        List<AuditCategory> categories = loadCategories();
        
        this.umlConfigurationPlan = new UmlConfigurationPlan(categories);
        this.umlAuditPlan = new UmlAuditPlan(categories);
    }

    @objid ("c5905e33-4e32-466e-a3fa-ced9640a9e0f")
    @Override
    public IAuditExecutionPlan getExecutionPlan() {
        return this.umlAuditPlan;
    }

    @objid ("a62a4e39-6a05-496c-a8d0-36b77e9ccdd8")
    @Override
    public IAuditConfigurationPlan getConfigurationPlan() {
        return this.umlConfigurationPlan;
    }

    @objid ("21f339dd-bbe6-4c8f-b3d5-f9930617d2e6")
    private List<AuditCategory> loadCategories() {
        List<AuditCategory> categories;
        
        Bundle bundle = UmlUi.getContext().getBundle();
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/res/umlconfiguration.xml";
        URL url = null;
        try {
            url = new URL(s);
            URL fileURL = FileLocator.toFileURL(url);
            java.nio.file.Path xmlFile = Paths.get(URIUtil.toURI(fileURL));
        
            categories = AuditCategoryBuilder.parseCategories(xmlFile.toFile());
        } catch (Exception e) {
            UmlUi.LOG.debug("File path %s is not found!", s);
            UmlUi.LOG.error(e);
            categories = Collections.emptyList();
        }
        return categories;
    }

}
