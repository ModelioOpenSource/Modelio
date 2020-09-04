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

package org.modelio.xmi.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * This interface represents the service provided by XMI plugin.
 * @author ebrosse
 */
@objid ("67b995e1-2743-4cc8-95ac-5c4fa224b254")
public interface IXMIService {
    /**
     * This service exports the given Package in the File xmiFile
     * 
     * @param configuration : the configuration of export process.
     * @throws java.lang.Exception : the first Exception occurring during the export
     */
    @objid ("9b2c5761-cdb7-488a-9667-2d1599fee930")
    void exportXMIFile(final ExportConfiguration configuration, IProgressMonitor monitor, IMModelServices modelServices, MMetamodel metamodel, IModelioNavigationService mns) throws Exception;

    /**
     * This service imports the Model saved in the given XMI file.
     * The import result will be owned by the given Package.
     * 
     * @param configuration : the configuration of import process.
     * @throws java.lang.Exception : the first Exception occurring during the export
     */
    @objid ("f22f71f9-3617-4cb4-92d0-b835a6d24a6f")
    void importXMIModel(final ImportConfiguration configuration, IProgressMonitor monitor, IMModelServices modelServices, MMetamodel metamodel, IModelioNavigationService mns) throws Exception;

    /**
     * This service imports the Profile saved in the given XMI file.
     * The import result will be owned by the given IModule.
     * 
     * @param configuration : the configuration of import process.
     * @throws java.lang.Exception : the first Exception occurring during the export
     */
    @objid ("e2d67d7f-5bd9-43a5-aa7b-407ba2296ff5")
    void importXMIProfile(final ImportConfiguration configuration, IProgressMonitor monitor, IMModelServices modelServices, MMetamodel metamodel, IModelioNavigationService mns) throws Exception;

    /**
     * This service export the Profile saved in the given XMI file.
     * The export result will be owned by the given IModule.
     * 
     * @param configuration : the configuration of import process.
     * @throws java.lang.Exception : the first Exception occurring during the export
     */
    @objid ("c3c622fa-9a80-49d3-b4e4-8975feaeb086")
    void exportXMIProfile(final ExportConfiguration configuration, IProgressMonitor monitor, IMModelServices modelServices, MMetamodel metamodel, IModelioNavigationService mns) throws Exception;

}
