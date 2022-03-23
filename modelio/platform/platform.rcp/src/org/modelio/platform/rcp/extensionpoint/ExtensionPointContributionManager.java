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
package org.modelio.platform.rcp.extensionpoint;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.modelio.platform.rcp.plugin.CoreRcp;

@objid ("b0e67846-b5db-4924-a0b6-a28b0cf7238d")
public class ExtensionPointContributionManager {
    @objid ("379b82ec-9981-4436-ac01-024386ec6755")
    private final String extensionPointId;

    @objid ("df15a26f-be56-44a6-9df1-768e0623e883")
    public  ExtensionPointContributionManager(final String extensionPointId) {
        this.extensionPointId = extensionPointId;
    }

    @objid ("22f0a352-f7a7-4670-a207-6c637b3793c3")
    public Collection<IConfigurationElement> getExtensions(final String extensionName) {
        final Collection<IConfigurationElement> extensions = new ArrayList<>();
        
        for (final IConfigurationElement extension : RegistryFactory.getRegistry().getConfigurationElementsFor(this.extensionPointId)) {
            if (extension.getName().equals(extensionName)) {
                if (extension.getAttribute("validator") != null) {
                    // Check validator
                    try {
                        final Object validator = extension.createExecutableExtension("validator");
                        if (validator instanceof IExtensionPointContribution) {
                            if (!((IExtensionPointContribution)validator).isActive()) {
                                // Contribution is deactivated
                                continue;
                            }
                        }
                    } catch (final CoreException e) {
                        // Validation error, contribution is deactivated
                        CoreRcp.LOG.error("Extension ignored : " + extension);
                        CoreRcp.LOG.error(e);
                        continue;
                    }
                }
                extensions.add(extension);
            }
        }
        return extensions;
    }

}
