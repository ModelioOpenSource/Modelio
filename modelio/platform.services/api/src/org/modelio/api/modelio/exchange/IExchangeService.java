/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.exchange;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Gathers services to import/export model elements.<br>
 * For now, only XMI format is supported.
 * @since 2.2
 */
@objid ("e5cd2f21-9501-11e1-a83f-002564c97630")
public interface IExchangeService {
    /**
     * This service exports a part of the model into an XMI file.
     * TODO progress monitor is unused yet.
     * @see XmiExportConfiguration for more info about the format and which element to export.
     * 
     * @param configuration the configuration of export process.
     * @param monitor a monitor for progress infos. Might be <code>null</code>.
     * @throws org.modelio.api.modelio.exchange.XmiException the first occurring exception
     */
    @objid ("a3ba28c9-0ecc-11e2-96c4-002564c97630")
    void exportXmiFile(final XmiExportConfiguration configuration, final IProgressMonitor monitor) throws XmiException;

    /**
     * This service imports a model from an XMI file into Modelio.
     * TODO progress monitor is unused yet.
     * @see XmiExportConfiguration for more info.
     * 
     * @param configuration the configuration of import process.
     * @param monitor a monitor for progress infos. Might be <code>null</code>.
     * @throws org.modelio.api.modelio.exchange.XmiException the first occurring exception
     */
    @objid ("a3ba4fdd-0ecc-11e2-96c4-002564c97630")
    void importXmiFile(final XmiImportConfiguration configuration, final IProgressMonitor monitor) throws XmiException;

}
