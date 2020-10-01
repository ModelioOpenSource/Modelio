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

package org.modelio.api.impl.exchange;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.api.modelio.exchange.IExchangeService;
import org.modelio.api.modelio.exchange.XmiException;
import org.modelio.api.modelio.exchange.XmiExportConfiguration;
import org.modelio.api.modelio.exchange.XmiImportConfiguration;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.api.ExportConfiguration;
import org.modelio.xmi.api.FormatExport;
import org.modelio.xmi.api.IXMIService;
import org.modelio.xmi.api.ImportConfiguration;
import org.modelio.xmi.impl.XMIService;

/**
 * Implementation of {@link IExchangeService}.
 */
@objid ("e29dd4a2-f42e-4042-89a9-78d6b1b6db15")
public class ExchangeService implements IExchangeService {
    @objid ("d1778a13-a444-4989-b88a-ccdf7b28f695")
    private IXMIService xmiservice;

    @objid ("0531613f-2476-41f4-9bfc-50e9298876c4")
    private IMModelServices modelServices;

    @objid ("7366d2e9-3989-4178-83bd-31c965057121")
    private MMetamodel metamodel;

    @objid ("3c303e90-0577-4ba7-96ef-82069953a99e")
    private IModelioNavigationService navigationServices;

    /**
     * C'tor.
     * 
     * @param eclipseContext the eclipse context to initialize services from.
     * @param metamodel the metamodel of the currently opened project.
     */
    @objid ("d982e768-b426-42c9-8813-7b97f3450322")
    public ExchangeService(final IEclipseContext eclipseContext, MMetamodel metamodel) {
        this.xmiservice = new XMIService();
        this.modelServices = eclipseContext.get(IMModelServices.class);
        this.navigationServices = eclipseContext.get(IModelioNavigationService.class);
        this.metamodel = metamodel;
    }

    @objid ("8fb90118-1c5c-439c-9b53-45ce49a454f9")
    @Override
    public void exportXmiFile(final XmiExportConfiguration configuration, final IProgressMonitor monitor) throws XmiException {
        ExportConfiguration exportConf = new ExportConfiguration();
        Package entryPoint = configuration.getEntryPoint();
        exportConf.setEntryPoint(entryPoint);
        exportConf.setExportedAnotation(configuration.isExportAnnotations());
        exportConf.setVersionExport(FormatExport.valueOf(configuration.getVersionExport().name()));
        exportConf.setXmiFile(configuration.getXmiFile());
        
        try {
            if (entryPoint instanceof Profile) {
                this.xmiservice.exportXMIProfile(exportConf, monitor, this.modelServices, this.metamodel, this.navigationServices);
            } else {
                this.xmiservice.exportXMIFile(exportConf, monitor, this.modelServices, this.metamodel, this.navigationServices);
            }
        } catch (Exception e) {
            throw new XmiException(e);
        }
    }

    @objid ("8018f414-9ae1-4060-953d-7742aa67ffdb")
    @Override
    public void importXmiFile(final XmiImportConfiguration configuration, final IProgressMonitor monitor) throws XmiException {
        ImportConfiguration importConf = new ImportConfiguration();
        MObject owner = configuration.getOwner();
        importConf.setOwner(owner);
        importConf.setXmiFile(configuration.getXmiFile());
        
        try {
            if (owner instanceof ModuleComponent) {
                this.xmiservice.importXMIProfile(importConf, monitor, this.modelServices, this.metamodel, this.navigationServices);
            } else if (owner instanceof Package) {
                this.xmiservice.importXMIModel(importConf, monitor, this.modelServices, this.metamodel, this.navigationServices);
            } else {
                throw new XmiException("Owner must be a Package or a Module");
            }
        } catch (Exception e) {
            throw new XmiException(e);
        }
    }

}
