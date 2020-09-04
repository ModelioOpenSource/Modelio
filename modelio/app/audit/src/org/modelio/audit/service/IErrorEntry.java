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

package org.modelio.audit.service;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("23d5cb54-b526-4229-a985-4bc078606fbe")
interface IErrorEntry {
    @objid ("ac005706-6676-488e-a1a8-906650508106")
    String getRuleId();

    @objid ("03c9287f-2dab-4264-a948-63088b134826")
    AuditSeverity getSeverity();

    @objid ("d12b0a2c-2786-4068-91bd-ee7c0cd10d3b")
    Element getElement();

    @objid ("d906ee80-7c5d-4335-86d7-effbceb8f01a")
    List<Element> getLinkedElements();

    @objid ("6a0b7d36-1670-49ef-9af9-0edf60fa8c49")
    String getMessage();

    @objid ("52d81fdf-94d2-4eb5-88ff-86f1b5cb50fc")
    String getEntryId();

    @objid ("7cbc1391-a99e-4755-999a-b7649c48613e")
    String getPlanId();

}
