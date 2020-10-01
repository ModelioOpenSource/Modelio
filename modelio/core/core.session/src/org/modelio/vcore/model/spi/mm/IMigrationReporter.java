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

package org.modelio.vcore.model.spi.mm;

import java.io.PrintWriter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Interface to report migration log and result.
 * @author cmarin
 * @since 3.4
 */
@objid ("0d5da315-3ca2-4a09-87ef-752b5dc597a5")
public interface IMigrationReporter {
    /**
     * @return The logger to use to report migration details.
     */
    @objid ("c89d11f9-b769-4ad3-bf8b-b9eecfc6c9cd")
    PrintWriter getLogger();

    /**
     * @return the writer to use to display  migration result to the user.
     */
    @objid ("eb687b13-f5dc-4641-9545-11afdc51bec0")
    PrintWriter getResultReporter();

}
