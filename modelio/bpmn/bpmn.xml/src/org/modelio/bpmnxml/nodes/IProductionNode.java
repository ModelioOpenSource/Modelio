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

package org.modelio.bpmnxml.nodes;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e945fe48-27cf-4730-b4ad-7a481de2018f")
public interface IProductionNode<U extends MObject, J> extends IProduction {
    @objid ("951fe740-0486-4967-86da-aa16c3a927c2")
    U findUMLElement(MObject context, J jaxbElement);

    @objid ("0e70cccb-b424-4cd4-a312-50050306c778")
    U createUMLElement(MObject context, J jaxbElement, BpmnImportFactory factory, boolean keepId);

    @objid ("eec26820-cb8d-4a57-9955-c92f3e5cbd0a")
    U updateUMLElement(MObject context, U modelioElement, J jaxbElement);

    @objid ("f5f8988b-3366-4a64-8804-6ca15b053002")
    J createJaxbElement(Object context, U modelioElement);

    @objid ("53609595-ae83-4b65-8ddb-7f1e7206f40e")
    J updateJaxbElement(Object context, J jaxbElement, U modelioElement);

    @objid ("03d7a0c1-9e27-476e-b44c-d25a6a3344fd")
    U findUMLElementById(J jaxbElement, ICoreSession session);

}
